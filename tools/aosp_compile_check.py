#!/usr/bin/env python3
#
# Copyright (C) 2026 The Cabin Authors
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""Compile Cabin's Soong modules against an AOSP tree's own toolchain.

    python3 tools/aosp_compile_check.py --aosp ~/aosp [--out DIR] [--module CabinCompose]

`m` analyzes the whole tree, so one broken directory anywhere blocks it. This
tool skips analysis: it reads Cabin's Android.bp files, resolves static_libs /
libs through the tree's prebuilt androidx and Kotlin modules (and built
intermediates for `libs`), then runs the tree's aapt2 (R classes) and kotlinc
(with the Compose plugin when `kotlin_plugins` asks for it) per module, in
dependency order, against `prebuilts/sdk/<sdk_version>/android.jar`.

It checks that the sources compile against the exact library versions the
tree ships. It is not a substitute for `m` (no dexing, lint, or packaging),
and it never writes into the AOSP tree.
"""

from __future__ import annotations

import argparse
import glob
import os
import re
import shutil
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
MODULE_DIRS = [
    "cabin-tokens", "cabin-compliance", "cabin-foundation", "cabin-views",
    "cabin-compose", "cabin-screens", "cabin-gauges",
]
PREBUILT_ROOTS = ["prebuilts/sdk/current/androidx", "prebuilts/sdk/current/extras", "external/kotlinc"]


# ------------------------------------------------------------------ blueprint

def _strip_comments(text: str) -> str:
    """Drop // comments, keeping // inside quoted strings."""
    out = []
    for line in text.splitlines():
        in_str, cut = False, len(line)
        for i, ch in enumerate(line):
            if ch == '"':
                in_str = not in_str
            elif not in_str and line.startswith("//", i):
                cut = i
                break
        out.append(line[:cut])
    return "\n".join(out)


def parse_bp(path: Path) -> list[dict]:
    """Tiny Android.bp reader: top-level modules with string / list / bool props."""
    text = _strip_comments(path.read_text(encoding="utf-8", errors="replace"))
    modules = []
    for m in re.finditer(r"^([a-z_]+)\s*\{", text, re.M):
        depth, i = 0, m.end() - 1
        while i < len(text):
            if text[i] == "{":
                depth += 1
            elif text[i] == "}":
                depth -= 1
                if depth == 0:
                    break
            i += 1
        body = text[m.end():i]
        mod = {"type": m.group(1), "dir": path.parent}
        for key, val in re.findall(r"^\s{4}([a-z_]+)\s*:\s*(\[.*?\]|\".*?\"|true|false)", body, re.M | re.S):
            if val.startswith("["):
                mod[key] = re.findall(r'"([^"]+)"', val)
            elif val.startswith('"'):
                mod[key] = val.strip('"')
            else:
                mod[key] = val == "true"
        if "name" in mod:
            modules.append(mod)
    return modules


def index_prebuilts(aosp: Path) -> dict[str, dict]:
    index: dict[str, dict] = {}
    for root in PREBUILT_ROOTS:
        for bp in glob.glob(str(aosp / root / "**" / "Android.bp"), recursive=True):
            for mod in parse_bp(Path(bp)):
                index.setdefault(mod["name"], mod)
    return index


# ------------------------------------------------------------------ resolution

class Resolver:
    def __init__(self, aosp: Path, cache: Path):
        self.aosp = aosp
        self.cache = cache
        self.index = index_prebuilts(aosp)
        self.jars: dict[str, list[Path]] = {}
        self.res_zips: dict[str, list[Path]] = {}
        self.missing: set[str] = set()

    def _aar(self, mod: dict, aar: str) -> tuple[list[Path], Path | None]:
        src = mod["dir"] / aar
        dest = self.cache / "aar" / mod["name"]
        if not dest.exists():
            dest.mkdir(parents=True)
            with zipfile.ZipFile(src) as z:
                z.extractall(dest)
        jars = [dest / "classes.jar"] if (dest / "classes.jar").exists() else []
        jars += sorted((dest / "libs").glob("*.jar")) if (dest / "libs").exists() else []
        res = dest / "res"
        return jars, (res if res.exists() and any(res.iterdir()) else None)

    def _intermediate(self, name: str) -> list[Path]:
        base = self.aosp / "out/soong/.intermediates"
        hits = glob.glob(str(base / "**" / name / "*" / "*" / f"{name}.jar"), recursive=True)
        rank = {s: i for i, s in enumerate(
            ("turbine-combined", "combined", "kotlin_headers", "turbine", "kotlin", "javac-header", "javac"))}
        hits = [h for h in hits if Path(h).parent.name in rank]
        hits.sort(key=lambda h: (Path(h).parent.parent.name != "android_common", rank[Path(h).parent.name]))
        return [Path(hits[0])] if hits else []

    def closure(self, names: list[str]) -> tuple[list[Path], list[Path]]:
        """Class jars and resource dirs for [names] and their static_libs."""
        jars: list[Path] = []
        res: list[Path] = []
        seen: set[str] = set()

        def visit(name: str) -> None:
            if name in seen:
                return
            seen.add(name)
            mod = self.index.get(name) or self.index.get(name + "-nodeps")
            if mod is None:
                found = self._intermediate(name)
                if found:
                    jars.extend(found)
                else:
                    self.missing.add(name)
                return
            for aar in mod.get("aars", []):
                j, r = self._aar(mod, aar)
                jars.extend(j)
                if r:
                    res.append(r)
            for jar in mod.get("jars", []):
                jars.append(mod["dir"] / jar)
            for dep in mod.get("static_libs", []) + mod.get("libs", []):
                visit(dep)

        for n in names:
            visit(n)
        return jars, res


# ------------------------------------------------------------------ build

def run(cmd: list[str], **kw) -> subprocess.CompletedProcess:
    return subprocess.run([str(c) for c in cmd], capture_output=True, text=True, **kw)


def manifest_package(manifest: Path, fallback: str) -> tuple[str, bool]:
    text = manifest.read_text(encoding="utf-8")
    m = re.search(r'package="([^"]+)"', text)
    return (m.group(1), True) if m else (fallback, False)


def gradle_namespace(module_dir: Path) -> str:
    text = (module_dir / "build.gradle.kts").read_text(encoding="utf-8")
    return re.search(r'namespace\s*=\s*"([^"]+)"', text).group(1)


def main(argv: list[str] | None = None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("--aosp", required=True, type=Path)
    ap.add_argument("--out", type=Path, default=ROOT / "build" / "aosp-compile")
    ap.add_argument("--module", action="append", help="limit to these Soong module names")
    args = ap.parse_args(argv)
    aosp = args.aosp.resolve()
    out = args.out.resolve()
    if out.exists():
        shutil.rmtree(out / "modules", ignore_errors=True)
    out.mkdir(parents=True, exist_ok=True)

    kotlinc_lib = aosp / "external/kotlinc/lib"
    java_home = aosp / "prebuilts/jdk/jdk21/linux-x86"
    aapt2 = aosp / "prebuilts/sdk/tools/linux/bin/aapt2"
    compose_plugin = kotlinc_lib / "compose-compiler-plugin.jar"
    for p in (kotlinc_lib / "kotlin-compiler.jar", java_home / "bin/java", aapt2):
        if not p.exists():
            print(f"error: missing {p}", file=sys.stderr)
            return 2

    cabin = {}
    for d in MODULE_DIRS:
        for mod in parse_bp(ROOT / d / "Android.bp"):
            mod["src_dir"] = ROOT / d
            cabin[mod["name"]] = mod
    defaults = {m["name"]: m for m in parse_bp(ROOT / "Android.bp")}

    resolver = Resolver(aosp, out / "cache")
    built_jars: dict[str, Path] = {}
    built_res: dict[str, list[Path]] = {}
    order: list[str] = []

    def topo(name: str) -> None:
        if name in order or name not in cabin:
            return
        for dep in cabin[name].get("static_libs", []) + cabin[name].get("libs", []):
            topo(dep)
        order.append(name)

    for name in (args.module or list(cabin)):
        topo(name)

    failures = 0
    for name in order:
        mod = cabin[name]
        mdir: Path = mod["src_dir"]
        work = out / "modules" / name
        (work / "gen").mkdir(parents=True, exist_ok=True)
        (work / "classes").mkdir(parents=True, exist_ok=True)
        sdk = mod.get("sdk_version") or next(
            (defaults[d].get("sdk_version") for d in mod.get("defaults", []) if d in defaults), "current")
        api = sdk.replace("_current", "").replace("current", "")
        android_jar = aosp / "prebuilts/sdk/current" / (api or "public") / "android.jar"

        cabin_deps = [d for d in mod.get("static_libs", []) if d in cabin]
        ext = [d for d in mod.get("static_libs", []) + mod.get("libs", []) if d not in cabin]
        ext_jars, ext_res = resolver.closure(ext)
        dep_jars, dep_res = [], []
        for d in cabin_deps:
            stack = [d]
            while stack:
                cur = stack.pop()
                if cur in built_jars and built_jars[cur] not in dep_jars:
                    dep_jars.append(built_jars[cur])
                    dep_res.extend(built_res.get(cur, []))
                    stack.extend(x for x in cabin[cur].get("static_libs", []) if x in cabin)
                    j, r = resolver.closure([x for x in cabin[cur].get("static_libs", []) if x not in cabin])
                    ext_jars += [x for x in j if x not in ext_jars]
                    ext_res += [x for x in r if x not in ext_res]

        manifest = mdir / mod.get("manifest", "src/main/AndroidManifest.xml")
        package, declared = manifest_package(manifest, gradle_namespace(mdir))
        notes = []
        if not declared:
            print(f"[{name}] FAILED: {manifest.relative_to(ROOT)} has no package attribute (Soong's aapt2 needs one)")
            failures += 1

        # --- resources → R
        own_res = [mdir / r for r in mod.get("resource_dirs", []) if (mdir / r).exists()]
        if own_res or True:
            flats = []
            for i, rdir in enumerate(ext_res + dep_res + own_res):
                z = work / f"res{i}.zip"
                r = run([aapt2, "compile", "--dir", rdir, "-o", z])
                if r.returncode != 0:
                    print(f"[{name}] aapt2 compile failed for {rdir}:\n{r.stderr}")
                    failures += 1
                flats.append(z)
            tmp_manifest = work / "AndroidManifest.xml"
            tmp_manifest.write_text(f'<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="{package}" />\n')
            link = [aapt2, "link", "--static-lib", "--auto-add-overlay", "--no-version-vectors",
                    "-I", android_jar, "--manifest", tmp_manifest, "--java", work / "gen",
                    "-o", work / "res.apk"]
            for f in flats:
                link += ["-R", f]
            r = run(link)
            if r.returncode != 0:
                print(f"[{name}] aapt2 link failed:\n{r.stderr[:3000]}")
                failures += 1
                continue
            built_res[name] = own_res
            rjava = list((work / "gen").rglob("R.java"))
            if rjava:
                r = run([java_home / "bin/javac", "-nowarn", "-d", work / "classes", "-source", "17", "-target", "17", *rjava])
                if r.returncode != 0:
                    print(f"[{name}] javac R failed:\n{r.stderr[:2000]}")
                    failures += 1
                    continue

        # --- Kotlin
        srcs = []
        for pattern in mod.get("srcs", []):
            srcs += glob.glob(str(mdir / pattern), recursive=True)
        classpath = [android_jar, work / "classes", *dep_jars, *ext_jars]
        cmd = [java_home / "bin/java", "-Xmx4g", "-cp", kotlinc_lib / "kotlin-compiler.jar",
               "org.jetbrains.kotlin.cli.jvm.K2JVMCompiler",
               "-no-stdlib", "-no-reflect", "-jvm-target", "17", "-Xjvm-default=all",
               "-d", work / "classes",
               "-classpath", os.pathsep.join(str(p) for p in classpath),
               "-Xallow-unstable-dependencies", "-Xsuppress-version-warnings", "-nowarn"]
        if "kotlin-compose-compiler-plugin" in mod.get("kotlin_plugins", []):
            cmd.append(f"-Xplugin={compose_plugin}")
        if not (kotlinc_lib / "kotlin-stdlib.jar").exists():
            pass
        else:
            cmd[cmd.index("-classpath") + 1] += os.pathsep + str(kotlinc_lib / "kotlin-stdlib.jar")
        cmd += srcs
        r = run(cmd)
        errors = [l for l in r.stderr.splitlines() if l.startswith("error") or ": error:" in l or l.startswith("exception")]
        if r.returncode != 0:
            failures += 1
            print(f"[{name}] FAILED ({len(srcs)} sources, kotlinc exit {r.returncode})")
            print("\n".join((errors or r.stderr.splitlines())[:40]))
        else:
            jar = work / f"{name}.jar"
            shutil.make_archive(str(jar.with_suffix("")), "zip", work / "classes")
            jar.with_suffix(".zip").rename(jar)
            built_jars[name] = jar
            print(f"[{name}] OK ({len(srcs)} sources" + (", compose" if "-Xplugin" in " ".join(map(str, cmd)) else "") + ")")
        for n in notes:
            print(f"[{name}] note: {n}")

    if resolver.missing:
        print("unresolved (not on classpath): " + ", ".join(sorted(resolver.missing)))
    print("RESULT: " + ("OK" if failures == 0 else f"{failures} failure(s)"))
    return 0 if failures == 0 else 1


if __name__ == "__main__":
    sys.exit(main())

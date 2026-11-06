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
"""Cabin-specific quality rules (AGENTS.md hard rules as machine checks).

    python3 tools/check_cabin_rules.py              # check; exit 1 on new findings
    python3 tools/check_cabin_rules.py --list       # print the rule catalog
    python3 tools/check_cabin_rules.py --update-baseline

Rules apply to hand-written library sources (`cabin-*/src/main`, excluding
`*.generated.kt`). Pre-existing findings live in `config/cabin-rules/baseline.txt`;
only new findings fail. Suppress a deliberate exception on its line (or the
line above) with `// cabin-rules: allow <RULE-ID> — reason`.

See docs/quality.md for the rationale behind each rule.
"""

from __future__ import annotations

import argparse
import hashlib
import re
import sys
from dataclasses import dataclass
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BASELINE = ROOT / "config" / "cabin-rules" / "baseline.txt"

UI_FREE = ("cabin-tokens", "cabin-compliance", "cabin-foundation")
COMPOSE_MODULES = ("cabin-compose", "cabin-screens", "cabin-gauges")
KIT_DIRS = ("cabin-compose/src/main/java/dev/decoupled/cabin/compose/kit", "cabin-screens/src/main")


@dataclass(frozen=True)
class Rule:
    id: str
    title: str
    why: str


RULES = {
    r.id: r
    for r in (
        Rule("CR001", "UI-free modules import no UI toolkit",
             "AGENTS rule 2: tokens, compliance and foundation stay usable from SystemUI and tests."),
        Rule("CR002", "Compose and Views never import each other",
             "AGENTS rule 3: adopting one stack must not drag in the other."),
        Rule("CR003", "No color literals in kit or screens",
             "Colors come from the look (CabinTheme.look.colors) so OEM theming and safety locks hold."),
        Rule("CR004", "No hardcoded user-visible strings in library code",
             "Text and contentDescription come from string resources so AAOS can localize them."),
        Rule("CR005", "Interactive surfaces must be gated",
             "AGENTS rule 7: anything clickable asks the Restriction Engine (cabinGate / CabinButton)."),
        Rule("CR006", "No raw clickable/toggleable modifiers in kit or screens",
             "Use Cabin controls, which enforce the 76 dp target, semantics and gating."),
        Rule("CR007", "Library code never reads driving state directly",
             "Policy lives in the Restriction Engine; widgets must not check speed/gear themselves."),
        Rule("CR008", "No network, GMS or downloadable fonts in libraries",
             "AOSP-upstream bar: libraries must work offline on any AAOS build."),
    )
}

ALLOW_RE = re.compile(r"cabin-rules:\s*allow\s+(CR\d{3})")


@dataclass(frozen=True)
class Finding:
    rule: str
    path: str
    line: int
    text: str

    def key(self) -> str:
        digest = hashlib.sha1(self.text.strip().encode()).hexdigest()[:12]
        return f"{self.rule} {self.path} {digest}"

    def __str__(self) -> str:
        return f"{self.path}:{self.line}: {self.rule} {RULES[self.rule].title}\n    {self.text.strip()}"


def library_sources() -> list[Path]:
    files = []
    for module in sorted(ROOT.glob("cabin-*")):
        main = module / "src" / "main"
        if main.is_dir():
            files += [p for p in main.rglob("*.kt") if not p.name.endswith(".generated.kt")]
    return files


def module_of(path: Path) -> str:
    return path.relative_to(ROOT).parts[0]


def in_kit(rel: str) -> bool:
    return rel.startswith(KIT_DIRS)


def functions(lines: list[str]) -> list[tuple[int, int]]:
    """(start, end) line ranges of top-level / member functions (brace matched)."""
    ranges = []
    i = 0
    while i < len(lines):
        if re.match(r"\s*(?:(?:private|internal|public|inline|override)\s+)*fun\s", lines[i]):
            depth, started, j = 0, False, i
            while j < len(lines):
                depth += lines[j].count("{") - lines[j].count("}")
                started = started or "{" in lines[j]
                if started and depth <= 0:
                    break
                j += 1
            ranges.append((i, j))
            i = j + 1
        else:
            i += 1
    return ranges


def check_file(path: Path) -> list[Finding]:
    rel = path.relative_to(ROOT).as_posix()
    module = module_of(path)
    text = path.read_text(encoding="utf-8")
    lines = text.splitlines()
    out: list[Finding] = []

    def add(rule: str, idx: int) -> None:
        here = lines[idx]
        above = lines[idx - 1] if idx > 0 else ""
        for candidate in (here, above):
            m = ALLOW_RE.search(candidate)
            if m and m.group(1) == rule:
                return
        out.append(Finding(rule, rel, idx + 1, here))

    for i, line in enumerate(lines):
        stripped = line.strip()
        if stripped.startswith(("//", "*", "/*")):
            continue
        if stripped.startswith("import "):
            target = stripped[len("import "):]
            if module in UI_FREE and re.match(r"(androidx\.compose|android\.view|android\.widget)\b", target):
                add("CR001", i)
            if module in COMPOSE_MODULES and target.startswith("dev.decoupled.cabin.views"):
                add("CR002", i)
            if module == "cabin-views" and (target.startswith("dev.decoupled.cabin.compose") or target.startswith("androidx.compose")):
                add("CR002", i)
            if re.match(r"(com\.google\.android\.gms|java\.net\.(URL|HttpURLConnection)|okhttp3|androidx\.compose\.ui\.text\.googlefonts)\b", target):
                add("CR008", i)
            continue
        if in_kit(rel):
            if re.search(r"\bColor\(\s*0x|\bColor\(\s*red\s*=|\bColor\.(Red|Green|Blue|Yellow|Cyan|Magenta|Gray|LightGray|DarkGray)\b", line):
                add("CR003", i)
            if re.search(r"\.(clickable|toggleable|selectable)\(", line):
                add("CR006", i)
            if re.search(r"\b(speedKph|parkMode|isDriving)\b", line):
                add("CR007", i)
        if module in COMPOSE_MODULES:
            for m in re.finditer(r'\b(?:Text|CabinLabel)\(\s*(?:text\s*=\s*)?"((?:[^"\\]|\\.)*)"|contentDescription\s*=\s*"((?:[^"\\]|\\.)*)"', line):
                literal = m.group(1) if m.group(1) is not None else m.group(2)
                # Templates that only join localized values ("$a, $b") are fine.
                words = re.sub(r"\$\{[^}]*\}|\$[A-Za-z_][A-Za-z0-9_.]*", "", literal)
                if re.search(r"[A-Za-z]{2,}", words):
                    add("CR004", i)
                    break

    if in_kit(rel):
        for start, end in functions(lines):
            body = "\n".join(lines[start:end + 1])
            if re.search(r"Surface\(\s*(?:\n\s*)?(onClick|checked|selected)\s*=", body) and not re.search(
                r"cabinGate\(|CabinButton\(|CabinToggleButton\(", body
            ):
                for k in range(start, end + 1):
                    if re.search(r"Surface\(\s*$|Surface\(\s*(onClick|checked|selected)\s*=", lines[k]):
                        nxt = " ".join(lines[k:k + 3])
                        if re.search(r"(onClick|checked|selected)\s*=", nxt):
                            add("CR005", k)
                            break
    return out


def load_baseline() -> set[str]:
    if not BASELINE.exists():
        return set()
    return {l.strip() for l in BASELINE.read_text(encoding="utf-8").splitlines() if l.strip() and not l.startswith("#")}


def main(argv: list[str] | None = None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("--list", action="store_true", help="print the rule catalog")
    ap.add_argument("--update-baseline", action="store_true")
    ap.add_argument("--all", action="store_true", help="also print baselined findings")
    args = ap.parse_args(argv)

    if args.list:
        for r in RULES.values():
            print(f"{r.id}  {r.title}\n       {r.why}")
        return 0

    findings = [f for p in library_sources() for f in check_file(p)]
    if args.update_baseline:
        BASELINE.parent.mkdir(parents=True, exist_ok=True)
        BASELINE.write_text(
            "# Pre-existing Cabin rule findings. Fix and remove; never add by hand.\n"
            "# Regenerate: python3 tools/check_cabin_rules.py --update-baseline\n"
            + "".join(f"{k}\n" for k in sorted({f.key() for f in findings})),
            encoding="utf-8",
        )
        print(f"baseline: {len(findings)} finding(s) recorded in {BASELINE.relative_to(ROOT)}")
        return 0

    baseline = load_baseline()
    new = [f for f in findings if f.key() not in baseline]
    old = len(findings) - len(new)
    if args.all:
        for f in findings:
            print(("  (baselined) " if f.key() in baseline else "") + str(f))
    if new:
        print(f"error: {len(new)} new Cabin rule finding(s):", file=sys.stderr)
        for f in new:
            print(f"  {f}", file=sys.stderr)
        print("\nFix them, or suppress a deliberate exception with "
              "'// cabin-rules: allow <ID> — reason'. Rules: docs/quality.md", file=sys.stderr)
        return 1
    stale = len(baseline - {f.key() for f in findings})
    print(f"ok: Cabin rules pass ({len(findings)} finding(s), {old} baselined"
          + (f", {stale} stale baseline entr{'y' if stale == 1 else 'ies'} — run --update-baseline" if stale else "") + ")")
    return 0


if __name__ == "__main__":
    sys.exit(main())

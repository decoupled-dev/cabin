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
"""Apache-2.0 license headers for Cabin source files.

    python3 tools/license_headers.py --check   # CI: list files missing a header
    python3 tools/license_headers.py --fix     # add the header where missing

Code generators call `apply()` so generated files carry the header too and the
`--check` drift gates stay clean.

Scope: tracked Kotlin, Java, Gradle Kotlin script, Python, Soong, Android XML
and generated CSS under the library, sample and tools trees. The web apps
(`apps/www`, `website`) declare the license in package.json instead.
"""

from __future__ import annotations

import argparse
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
MARKER = "Licensed under the Apache License, Version 2.0"
HOLDER = "Copyright (C) 2026 The Cabin Authors"

_LINES = [
    HOLDER,
    "",
    'Licensed under the Apache License, Version 2.0 (the "License");',
    "you may not use this file except in compliance with the License.",
    "You may obtain a copy of the License at",
    "",
    "     http://www.apache.org/licenses/LICENSE-2.0",
    "",
    "Unless required by applicable law or agreed to in writing, software",
    'distributed under the License is distributed on an "AS IS" BASIS,',
    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.",
    "See the License for the specific language governing permissions and",
    "limitations under the License.",
]

EXCLUDED_PREFIXES = ("apps/", "website/", "node_modules/", "reports/", "research_notes/")
SUFFIXES = {".kt", ".kts", ".java", ".py", ".bp", ".xml", ".css"}
# Tool-written files that are regenerated wholesale (a header would be dropped).
EXCLUDED_NAMES = {"lint-baseline.xml", "detekt-baseline.xml"}


def _block() -> str:
    body = "\n".join((" * " + line).rstrip() for line in _LINES)
    return "/*\n" + body + "\n */\n"


def _hash() -> str:
    return "#\n" + "\n".join(("# " + line).rstrip() for line in _LINES) + "\n"


def _slashes() -> str:
    return "\n".join(("// " + line).rstrip() for line in _LINES) + "\n"


def _xml() -> str:
    return "<!--\n" + "\n".join(("  " + line).rstrip() for line in _LINES) + "\n-->\n"


def applies_to(path: Path) -> bool:
    return path.suffix in SUFFIXES and path.name not in EXCLUDED_NAMES


def has_header(text: str) -> bool:
    return MARKER in text[:2000]


def apply(path: Path, text: str) -> str:
    """Return [text] with the header for [path]'s type; idempotent."""
    if not applies_to(path) or has_header(text):
        return text
    suffix = path.suffix
    if suffix in {".kt", ".kts", ".java", ".css"}:
        return _block() + ("\n" if text and not text.startswith("\n") else "") + text
    if suffix == ".bp":
        return _slashes() + "\n" + text
    if suffix == ".py":
        lines = text.splitlines(keepends=True)
        if lines and lines[0].startswith("#!"):
            return lines[0] + _hash() + "".join(lines[1:])
        return _hash() + text
    if suffix == ".xml":
        if text.startswith("<?xml"):
            first, _, rest = text.partition("\n")
            return first + "\n" + _xml() + rest
        return _xml() + text
    return text


def tracked_files() -> list[Path]:
    out = subprocess.run(
        ["git", "ls-files", "--cached", "--others", "--exclude-standard"],
        cwd=ROOT,
        check=True,
        capture_output=True,
        text=True,
    ).stdout
    files = []
    for rel in out.splitlines():
        if rel.startswith(EXCLUDED_PREFIXES) or "/build/" in rel:
            continue
        path = ROOT / rel
        if applies_to(path) and path.is_file():
            files.append(path)
    return files


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    mode = parser.add_mutually_exclusive_group(required=True)
    mode.add_argument("--check", action="store_true")
    mode.add_argument("--fix", action="store_true")
    args = parser.parse_args(argv)

    missing = [p for p in tracked_files() if not has_header(p.read_text(encoding="utf-8"))]
    if args.check:
        if missing:
            print("error: missing Apache-2.0 header:", file=sys.stderr)
            for path in missing:
                print(f"  - {path.relative_to(ROOT)}", file=sys.stderr)
            print("\nRun: python3 tools/license_headers.py --fix", file=sys.stderr)
            return 1
        print("ok: every source file carries the Apache-2.0 header")
        return 0
    for path in missing:
        path.write_text(apply(path, path.read_text(encoding="utf-8")), encoding="utf-8")
        print(f"header added: {path.relative_to(ROOT)}")
    return 0


if __name__ == "__main__":
    sys.exit(main())

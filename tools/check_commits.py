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
"""Commit standards: Conventional Commits subjects and (optionally) DCO sign-off.

    python3 tools/check_commits.py origin/main..HEAD [--require-signoff]
    python3 tools/check_commits.py --message-file .git/COMMIT_EDITMSG   # commit-msg hook
"""

from __future__ import annotations

import argparse
import re
import subprocess
import sys

TYPES = ("feat", "fix", "docs", "chore", "refactor", "test", "build", "ci", "perf", "style", "revert")
SUBJECT_RE = re.compile(r"^(?P<type>[a-z]+)(?:\((?P<scope>[a-z0-9./-]+)\))?(?P<bang>!)?: (?P<desc>\S.*)$")
MAX_SUBJECT = 100


def problems(message: str, require_signoff: bool) -> list[str]:
    lines = message.strip().splitlines()
    if not lines:
        return ["empty message"]
    subject = lines[0]
    if subject.startswith(("Merge ", "Revert \"", "fixup! ", "squash! ")):
        return []
    out = []
    m = SUBJECT_RE.match(subject)
    if not m:
        out.append(f"subject is not Conventional Commits ('type(scope): description'): {subject!r}")
    elif m.group("type") not in TYPES:
        out.append(f"unknown type {m.group('type')!r}; use one of {', '.join(TYPES)}")
    if len(subject) > MAX_SUBJECT:
        out.append(f"subject is {len(subject)} chars (max {MAX_SUBJECT})")
    if subject.endswith("."):
        out.append("subject ends with a period")
    if len(lines) > 1 and lines[1].strip():
        out.append("second line must be blank")
    if require_signoff and not re.search(r"^Signed-off-by: .+ <.+@.+>$", message, re.M):
        out.append("missing 'Signed-off-by:' (DCO); commit with 'git commit -s'")
    return out


def main(argv: list[str] | None = None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("range", nargs="?", help="git revision range, e.g. origin/main..HEAD")
    ap.add_argument("--message-file")
    ap.add_argument("--require-signoff", action="store_true")
    args = ap.parse_args(argv)

    if args.message_file:
        text = open(args.message_file, encoding="utf-8").read()
        text = "\n".join(l for l in text.splitlines() if not l.startswith("#"))
        found = problems(text, args.require_signoff)
        for p in found:
            print(f"commit message: {p}", file=sys.stderr)
        return 1 if found else 0

    if not args.range:
        ap.error("give a revision range or --message-file")
    shas = subprocess.run(["git", "rev-list", "--no-merges", args.range], capture_output=True, text=True, check=True).stdout.split()
    bad = 0
    for sha in shas:
        msg = subprocess.run(["git", "log", "-1", "--format=%B", sha], capture_output=True, text=True, check=True).stdout
        for p in problems(msg, args.require_signoff):
            print(f"{sha[:10]}: {p}", file=sys.stderr)
            bad += 1
    print(f"{'ok' if not bad else 'error'}: {len(shas)} commit(s) checked, {bad} problem(s)")
    return 1 if bad else 0


if __name__ == "__main__":
    sys.exit(main())

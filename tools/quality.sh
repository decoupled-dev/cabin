#!/usr/bin/env bash
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
#
# Cabin quality pipeline — one entry point for local runs and CI.
#
#   tools/quality.sh fast     # Python gates only (seconds; pre-commit hook)
#   tools/quality.sh static   # fast + detekt + Android Lint + public API check
#   tools/quality.sh test     # unit tests (incl. screenshot render)
#   tools/quality.sh all      # everything above (default)
#
# Rules and rationale: docs/quality.md
set -euo pipefail
cd "$(dirname "$0")/.."

stage="${1:-all}"
GRADLE="./gradlew --console=plain"
LIBS="cabin-tokens cabin-compliance cabin-foundation cabin-views cabin-compose cabin-screens cabin-gauges"
ALL_TEST_MODULES="$LIBS catalog samples:kitchen-sink"

step() { printf '\n\033[1m== %s\033[0m\n' "$1"; }

fast() {
  step "License headers";         python3 tools/license_headers.py --check
  step "Cabin rules";             python3 tools/check_cabin_rules.py
  step "Soong thin deps";         python3 tools/check_soong_thin_deps.py
  step "Token codegen drift";     python3 tools/generate_cabin_tokens.py --check
  step "Component codegen drift"; python3 tools/generate_cabin_components.py --check
  step "Tool tests"
  python3 tools/test_generate_cabin_tokens.py
  python3 tools/test_generate_cabin_components.py
  python3 tools/test_check_cabin_rules.py
}

static() {
  step "detekt (style, complexity, formatting)"
  $GRADLE $(for m in $LIBS; do printf ':%s:detekt ' "$m"; done) --continue
  step "Android Lint"
  $GRADLE $(for m in $LIBS catalog samples:kitchen-sink; do printf ':%s:lintDebug ' "$m"; done) --continue
  step "Public API (binary compatibility)"
  $GRADLE apiCheck
}

tests() {
  step "Unit tests"
  $GRADLE $(for m in $ALL_TEST_MODULES; do printf ':%s:test ' "$m"; done)
}

case "$stage" in
  fast) fast ;;
  static) fast; static ;;
  test) tests ;;
  all) fast; static; tests ;;
  *) echo "usage: $0 [fast|static|test|all]" >&2; exit 2 ;;
esac
printf '\n\033[1mquality: %s passed\033[0m\n' "$stage"

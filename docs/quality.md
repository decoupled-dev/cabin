# Quality pipeline and standards

Cabin ships safety-relevant UI and aims to be good enough for AAOS to carry it
by default ([ADR 0006](adr/0006-material3-looks-and-tiers.md)). The quality
pipeline turns the hard rules in [AGENTS.md](../AGENTS.md) and the
[AOSP-upstream bar](adoption/aosp-upstream.md) into checks that run the same
way on a laptop and in CI.

## One entry point

```bash
tools/quality.sh fast     # Python gates, under a second (also the pre-commit hook)
tools/quality.sh static   # fast + detekt + Android Lint + public API check
tools/quality.sh test     # unit tests, incl. reference-screen screenshots
tools/quality.sh all      # everything (default)

git config core.hooksPath .githooks   # once per clone: pre-commit + commit-msg hooks
```

CI (`.github/workflows/quality.yml`) runs the same script, so a green local
run means a green CI run.

## Stages

| Stage | Check | Tool | Fails on |
| --- | --- | --- | --- |
| fast | Apache-2.0 header on every source file | `tools/license_headers.py` | missing header |
| fast | Cabin rules (below) | `tools/check_cabin_rules.py` | new finding |
| fast | Soong thin deps, no umbrella module, SystemUI stays Views-only | `tools/check_soong_thin_deps.py` | violation |
| fast | Generated tokens and scaffolds match their sources | `generate_cabin_*.py --check` | drift |
| fast | Token gates: safety lock, 4.5:1 contrast, required roles | `tools/cabin_looks.py` (via token codegen) | violation |
| fast | Tool self-tests | `tools/test_*.py` | failure |
| static | Kotlin style, complexity, formatting (ktlint rules) | detekt + `config/detekt/detekt.yml` | new finding |
| static | Android Lint: accessibility, hardcoded text, API levels | `config/lint/lint.xml` | new error |
| static | Public API unchanged unless the dump is updated | binary-compatibility-validator | API diff |
| test | Unit, Robolectric and gating tests; screenshot render | Gradle | failure |
| PR | Conventional Commits; DCO sign-off when enforced | `tools/check_commits.py` | bad subject / no sign-off |
| PR | No new high-severity or copyleft dependencies | dependency-review | violation |
| weekly + PR | Security analysis (Kotlin/Java, Python) | CodeQL | alert |
| local, optional | Soong modules compile with an AOSP tree's toolchain | `tools/aosp_compile_check.py` | compile error |

## Cabin rules

`python3 tools/check_cabin_rules.py --list` prints the catalog.

| ID | Rule | Why |
| --- | --- | --- |
| CR001 | UI-free modules (`tokens`, `compliance`, `foundation`) import no UI toolkit | AGENTS rule 2 |
| CR002 | Compose and Views never import each other | AGENTS rule 3 |
| CR003 | No color literals in kit or screens | Colors come from the look so OEM theming and safety locks hold |
| CR004 | No hardcoded user-visible strings in library code | AAOS localizes resources, not literals |
| CR005 | Clickable surfaces in kit or screens call the Restriction Engine | AGENTS rule 7 |
| CR006 | No raw `clickable` / `toggleable` / `selectable` in kit or screens | Cabin controls enforce 76 dp targets, semantics and gating |
| CR007 | Kit and screens never read speed, gear or driving flags | Policy lives in the Restriction Engine |
| CR008 | No GMS, network or downloadable fonts in libraries | Must work on any AAOS build, offline |

A deliberate exception is suppressed on its line or the line above, with a
reason:

```kotlin
// cabin-rules: allow CR005 — gated by the caller (UpNextPanel checks MediaComplex).
Surface(onClick = { onSelect(item) }, ...)
```

## Standards the checks encode

- **Kotlin:** Kotlin coding conventions via ktlint (Android Studio style,
  trailing commas, no wildcard imports), 140-column lines; see `.editorconfig`.
- **Compose API guidelines:** `modifier` is the first optional parameter,
  state is hoisted, slots for content. Lint's `ModifierParameter` enforces the
  first point; the frozen generated scaffolds are the only baselined exception.
- **Accessibility:** content descriptions on icon-only controls, state
  descriptions on toggles, 76 dp minimum targets, 24 sp minimum text, 4.5:1
  contrast. Enforced by lint, Cabin rules, token codegen and tests.
- **Public API:** each library's API lives in `<module>/api/<module>.api` (our
  `api/current.txt`). An API change fails CI until the dump is updated with
  `./gradlew apiDump` and reviewed in the same PR.
- **Commits:** [Conventional Commits](https://www.conventionalcommits.org/),
  subject ≤ 100 chars, blank second line. DCO sign-off (`git commit -s`) is
  required once the repository variable `CABIN_ENFORCE_DCO` is `true`.
- **Licensing:** Apache-2.0 headers; dependency review blocks GPL/AGPL/LGPL;
  sample-only fonts are OFL with license files alongside.

## Baselines

Findings that existed when a check was introduced are recorded so that only
**new** findings fail. Fix baselined items when you touch the code, then shrink
the baseline. Never add entries by hand.

| Baseline | Contents at introduction (2026-11-06) | Refresh |
| --- | --- | --- |
| `<module>/detekt-baseline.xml` | 46 findings (magic numbers, long lines, a few complex functions) | `./gradlew detektBaseline` |
| `<module>/lint-baseline.xml` | 313 `ModifierParameter` in generated scaffolds; 7 `SetTextI18n` in `CabinClimateTileView`; a few sample-app items | delete the file, run `./gradlew lintDebug` |
| `config/cabin-rules/baseline.txt` | none | `tools/check_cabin_rules.py --update-baseline` |

The kit (`compose.kit`) and `cabin-screens` started with zero lint and Cabin
rule findings.

## Fixing findings

```bash
./gradlew detekt --auto-correct       # formatting fixes
./gradlew apiDump                     # accept an intended public API change
python3 tools/license_headers.py --fix
python3 tools/generate_cabin_tokens.py && python3 tools/generate_cabin_components.py
```

Reports: `<module>/build/reports/detekt/`, `<module>/build/reports/lint-results-debug.html`.
In CI, detekt and lint SARIF results appear under the repository's code
scanning alerts.

# CLAUDE.md

Guidance for Claude Code in this repository. The authoritative agent rules live
in `AGENTS.md` (shared with other agents) and are imported here — edit them
there, not in this file.

@AGENTS.md

## Claude Code specifics

### Skills

The repo skills under `skills/` are exposed to Claude Code through symlinks in
`.claude/skills/` (`cabin-overview`, `cabin-tokens`, `cabin-compliance`,
`cabin-compose`, `cabin-views`, `cabin-soong`, `cabin-component-spec`). Load the
relevant one before working in its area. Edit the originals in `skills/`.

### Module map

| Module | Status | Notes |
| --- | --- | --- |
| `cabin-tokens` | Alpha | Generated from `tokens/cabin.tokens.json`; no UI framework deps |
| `cabin-compliance` | Alpha | Restriction Engine + `Signal`; no UI framework deps |
| `cabin-foundation` | Experimental | Size classes, shared State/Action; no UI framework deps |
| `cabin-views` | Alpha | Theme Kit, System/Status bars; SystemUI-safe |
| `cabin-compose` | Experimental | Material 3 theme + six looks + `compose.kit` primitives/cards; must not depend on `cabin-views` (and vice versa) |
| `cabin-screens` | Experimental | Opt-in reference screens (Home/Media/Climate); apps only, never SystemUI |
| `cabin-gauges` | Experimental | Opt-in cluster; never a SystemUI dep |
| `catalog`, `samples/kitchen-sink` | Samples | Never in the `cabin-*` publish graph |
| `apps/www` | Next.js marketing site | Follow `apps/www/DESIGN.md` |
| `website/` | Docusaurus docs site | Separate from `apps/www` |

### Commands

Requires JDK 17+ and `local.properties` with `sdk.dir=` (gitignored).

```bash
# Full unit test suite (matches CI)
./gradlew :cabin-tokens:test :cabin-compliance:test :cabin-foundation:test \
  :cabin-views:test :cabin-compose:test :cabin-screens:test :cabin-gauges:test :catalog:test \
  :samples:kitchen-sink:test

# Single module / single test class
./gradlew :cabin-compliance:test
./gradlew :cabin-compliance:test --tests '*CabinRestrictionEngineTest'

# Quality pipeline (same as CI) — see docs/quality.md
tools/quality.sh fast      # Python gates, <1 s (pre-commit hook)
tools/quality.sh static    # + detekt, Android Lint, public API check
tools/quality.sh           # everything incl. unit tests
./gradlew apiDump          # accept an intended public API change
./gradlew detekt --auto-correct
git config core.hooksPath .githooks

# Compile the Soong modules with an AOSP tree's toolchain (no writes to the tree)
python3 tools/aosp_compile_check.py --aosp /home/dc/Abhinav/aosp-17

# Regenerate after editing sources of truth
python3 tools/generate_cabin_tokens.py       # after tokens/cabin.tokens.json
python3 tools/generate_cabin_components.py   # after components/cabin.components.yaml

# Sample app
./gradlew :catalog:installDebug

# Web (each has its own CLAUDE.md with details)
(cd apps/www && npm ci && npm run dev)    # marketing site (Next.js, Vercel)
(cd website && npm ci && npm start)       # docs site (Docusaurus, GitHub Pages)
```

### Workflow notes

- Never hand-edit generated token/component output; change the JSON/YAML source
  and regenerate, then run the `--check` gates.
- Before finishing any change, run `tools/quality.sh` (AGENTS rule 13).
- Changes touching architecture, compliance contracts, or module dependencies:
  plan first (plan mode) and cite the relevant doc/ADR in `docs/`.
- When adding a dependency to any `build.gradle.kts` or `Android.bp`, re-check
  hard rules 1–4 in `AGENTS.md` and run `tools/check_soong_thin_deps.py`.

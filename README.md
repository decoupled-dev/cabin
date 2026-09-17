# Cabin

**Cabin** is an open-source design language and dual UI component library for
[Android Automotive OS (AAOS)](https://source.android.com/docs/automotive).
It targets OEMs, Tier-1 suppliers, and app developers who need a
Material Design 3–class platform purpose-built for the vehicle cabin —
not a phone UI stretched onto a bigger screen.

> **Library Alpha (in progress):** `cabin-tokens` + `cabin-compliance` +
> `cabin-views` (Theme Kit + System/Status bar Views chrome). Compose Theme +
> System/Status bars are **Experimental** (`cabin-compose`). Packaging /
> Maven publish notes remain. See [MVP v0.1](docs/mvp.md) ·
> [theme-kit](docs/adoption/theme-kit.md) · [compose](docs/platforms/compose.md).

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/platform-AAOS-green.svg)](docs/vision.md)
[![UI](https://img.shields.io/badge/UI-Compose%20%2B%20Views-orange.svg)](docs/platforms/compose.md)
[![Status](https://img.shields.io/badge/status-library%20alpha-yellow.svg)](docs/mvp.md)

---

## Build (library modules)

Requires JDK 17+ and an Android SDK (`local.properties` → `sdk.dir=`).

```bash
./gradlew :cabin-tokens:test :cabin-compliance:test :cabin-views:test :cabin-compose:test :catalog:test
```

Catalog (sample) — installable demo APK, not a publish artifact:

```bash
./gradlew :catalog:installDebug
# see catalog/README.md
```

Regenerate tokens from the stub after editing [`tokens/cabin.tokens.json`](tokens/cabin.tokens.json):

```bash
python3 tools/generate_cabin_tokens.py
# or: ./gradlew :cabin-tokens:generateCabinTokens
python3 tools/generate_cabin_tokens.py --check   # CI drift gate
```
Emits Android resources, Compose theme mappings, and CSS
(`apps/www/src/styles/cabin.tokens.css`). See
[token codegen](docs/design-language/token-codegen.md).

Maven coordinates / Soong names: [api-contracts](docs/api-contracts.md).
Soong sketches: `cabin-tokens/Android.bp` (`CabinTokens`),
`cabin-compliance/Android.bp` (`CabinCompliance`),
`cabin-views/Android.bp` (`CabinViews`),
`cabin-compose/Android.bp` (`CabinCompose` — not for SystemUI).

---

## Before implementation

Clear the [pre-implementation gate](docs/pre-implementation.md) and read the
frozen [MVP v0.1](docs/mvp.md) before expanding beyond tokens + compliance +
Theme Kit + Views bars. Token stub: [`tokens/cabin.tokens.json`](tokens/cabin.tokens.json).

---
## Why Cabin?

Automotive HMI has constraints phones do not: driving state, distraction
limits, glanceability, larger touch targets, day/night cabin lighting, and
safety-critical controls. Generic Material components do not encode those
rules. Cabin does.

| Audience | What Cabin gives you |
| --- | --- |
| **OEMs** | A brandable design system with compliance gates, without forking |
| **Tier-1s** | Shared tokens + dual-stack components across system UI and apps |
| **App developers** | Drop-in modules that respect AAOS UX restrictions by default |

Cabin ships as a **dual stack**:

- **Jetpack Compose** — modern app and media experiences
- **Legacy Views / XML** — system UI, status bar, build-tree, and existing apps

Both stacks share the same [design tokens](docs/design-language/tokens.md),
[compliance layer](docs/compliance/README.md), and component contracts so
parity is enforceable — not aspirational.

Cabin also supports **dual consumption**: Gradle/Maven AARs for app developers
and AOSP **Soong** (`Android.bp`) modules for SystemUI, CarLauncher, and other
build-tree apps — same source, Views-first on platform
([build-tree](docs/adoption/build-tree.md)).

---

## Quick links

| Doc | Description |
| --- | --- |
| [Docs index](docs/README.md) | Full table of contents |
| [Vision](docs/vision.md) | Positioning and open-source adoption strategy |
| [Principles](docs/principles.md) | Clean Code, SOLID, safety-critical UI, android/skills alignment |
| [Architecture](docs/architecture.md) | Planned multi-module layout and dependency rules |
| [Compliance](docs/compliance/README.md) | Driving, UX, accessibility, safety-critical patterns |
| [Design language](docs/design-language/README.md) | Foundations and tokens |
| [Components](docs/components/README.md) | System bars, media, EV, HVAC, vehicle controls |
| [Compose](docs/platforms/compose.md) / [Views](docs/platforms/views.md) / [Soong](docs/platforms/soong.md) | Platform guidelines and parity |
| [Adoption](docs/adoption/integration.md) | Integrate with minimal footprint |
| [Build-tree](docs/adoption/build-tree.md) | SystemUI / Soong adoption |
| [Marketing site](apps/www/README.md) | User-facing marketing app (`apps/www`) — separate from docs |
| [Website](website/README.md) | Public docs site (Docusaurus shell under `website/`) |
| [Website plan](docs/website/site-plan.md) | Public docs site IA (Material-style) |
| [Roadmap](docs/roadmap.md) | Phased delivery plan |
| [Contributing](docs/contributing.md) | How to contribute |

---

## Module map

> Maven publish is not configured yet (Alpha source modules). Soong names match
> [api-contracts](docs/api-contracts.md).

```
cabin/
├── cabin-tokens          # Alpha — design tokens (codegen from tokens/cabin.tokens.json)
├── cabin-compliance      # Alpha — Restriction Engine (System/Status bar matrix)
├── cabin-views           # Alpha — Theme Kit + System/Status bars (Views-first)
├── cabin-compose         # Experimental — Theme + System/Status bar parity
├── catalog/              # Sample — thin chrome catalog (not a product dep)
├── apps/www              # Marketing site (Next.js) — not the docs shell
├── samples/              # Planned — reference apps
└── website/              # Docs site (Docusaurus) → GitHub Pages
```

**Maven coordinates** (illustrative; publish later):

```text
dev.decoupled.cabin:cabin-tokens:<version>
dev.decoupled.cabin:cabin-compliance:<version>
dev.decoupled.cabin:cabin-compose:<version>
dev.decoupled.cabin:cabin-views:<version>
```

Adopt only what you need — see [packaging](docs/adoption/packaging.md).

---

## Adoption teaser

```kotlin
// Planned — Compose (Gradle apps)
dependencies {
    implementation("dev.decoupled.cabin:cabin-compose:<version>")
    implementation("dev.decoupled.cabin:cabin-compliance:<version>")
}

// Planned — Views (Gradle-built apps only — not SystemUI)
dependencies {
    implementation("dev.decoupled.cabin:cabin-views:<version>")
    implementation("dev.decoupled.cabin:cabin-compliance:<version>")
}
```

SystemUI and other AOSP build-tree apps consume Cabin via **Soong** modules
(`CabinViews`, …) — see [build-tree](docs/adoption/build-tree.md).

Full guidance: [integration](docs/adoption/integration.md) ·
[build-tree](docs/adoption/build-tree.md) ·
[migration](docs/adoption/migration.md) ·
[packaging](docs/adoption/packaging.md).

---

## Design coverage

Cabin aims for full cabin HMI coverage:

- [System bars](docs/components/system-bars.md)
- [Status bars](docs/components/status-bars.md)
- [Media](docs/components/media.md)
- [EV / energy](docs/components/ev.md)
- [HVAC](docs/components/hvac.md)
- [Vehicle controls](docs/components/vehicle-controls.md)
- [OEM extension model](docs/components/extension-model.md)

---

## Quality bar

Cabin is built for production AAOS programs:

- Clean Code, SOLID, and deliberate decoupling
- Modern Android and Compose guidelines
- Grounded in [android/skills](https://github.com/android/skills) patterns
- Compliance and dual-stack parity as first-class contracts
- Anti-bloat packaging — pick modules, don't import the kitchen sink

Details: [principles](docs/principles.md) · [architecture](docs/architecture.md).

---

## Agent-friendly

Cabin ships instructions so coding agents stay aligned with humans:

| Artifact | Purpose |
| --- | --- |
| [AGENTS.md](AGENTS.md) | Hard rules for any AI agent in this repo |
| [llms.txt](llms.txt) | Concise LLM doc map and reading order |
| [skills/](skills/) | Modular Agent Skills (tokens, compliance, Compose, Views, Soong, …) |
| [docs/agents/](docs/agents/README.md) | How to use agent affordances |

Cursor loads [.cursor/rules/cabin.mdc](.cursor/rules/cabin.mdc). Skills follow
the [Agent Skills](https://agentskills.io) layout.

---

## License

Apache License 2.0 — see [LICENSE](LICENSE).

---

## Status

Library **Alpha** source modules ship in-tree (tokens, compliance, Theme Kit).
Maven publish is not configured yet. System/Status bars and broader kits remain
on the [roadmap](docs/roadmap.md).

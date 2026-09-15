# AGENTS.md

Authoritative instructions for AI coding agents working on **Cabin**.

Cabin is an open-source **AAOS design language** and dual UI kit
(**Jetpack Compose + legacy Views**), with **compliance-first** gates and
**dual distribution** (Gradle/Maven for apps, Soong for AOSP build-tree).

This repo is currently **docs Phase 1**. Library source is not published yet.
Treat APIs, module coordinates, and `Android.bp` names as **planned** unless
code exists.

## Product pillars

Every feature must pass:

1. **Glanceable** — under a second, at speed, day/night
2. **Quiet** — fewer elements, stronger hierarchy
3. **Safe by default** — driving/UX restrictions shape UI
4. **One language, two skins** — Compose + Views share meaning; OEM brand on top
5. **Thin by design** — adopt one module without dragging the rest

Details: [docs/product/stance.md](docs/product/stance.md),
[docs/product/pillars.md](docs/product/pillars.md).

## Read first

| Priority | Doc |
| --- | --- |
| 1 | [docs/vision.md](docs/vision.md) · [docs/product/README.md](docs/product/README.md) |
| 2 | [docs/architecture.md](docs/architecture.md) · [docs/compliance/README.md](docs/compliance/README.md) |
| 3 | [docs/design-language/tokens.md](docs/design-language/tokens.md) |
| 4 | Stack: [compose](docs/platforms/compose.md) / [views](docs/platforms/views.md) / [soong](docs/platforms/soong.md) |
| 5 | Adoption: [integration](docs/adoption/integration.md) · [build-tree](docs/adoption/build-tree.md) · [packaging](docs/adoption/packaging.md) |

Full map: [docs/README.md](docs/README.md) · LLM map: [llms.txt](llms.txt).

## Hard rules

1. **Do not bloat modules.** No umbrella AAR/Soong meta-module; samples/catalog/website never leak into product deps.
2. **`cabin-tokens` and `cabin-compliance` have no UI framework dependencies** (no Compose, no Views widgets).
3. **`cabin-compose` ⊀ `cabin-views`** and vice versa (no cross-stack dependency).
4. **Views-first for SystemUI / build-tree.** Platform chrome uses Soong `CabinViews` + tokens + compliance. Compose is **not** a gate for SystemUI. Do not tell agents to `implementation` Cabin into SystemUI via Gradle.
5. **Plan before large implementation** when the user prefers planning, or when changing architecture/compliance contracts.
6. **Mark planned vs implemented.** Never invent “shipped” APIs. Label sketches **planned**.
7. **Compliance is not optional.** Do not hardcode driving policy inside widgets; use the Restriction Engine / compliance APIs ([docs/compliance](docs/compliance/README.md)).
8. **OEM brand without forks.** Tokens + RROs + extension slots — not core edits ([docs/components/extension-model.md](docs/components/extension-model.md)).
9. **Docs-only until asked.** Do not add Android library source in Phase 1 unless maintainers explicitly request it.
10. **American English**; restrained, production-minded tone.

## Soong vs Gradle

| Consumer | Build | Depend on (planned) |
| --- | --- | --- |
| App developers | Gradle → Maven | `dev.decoupled.cabin:cabin-*` |
| SystemUI, CarLauncher, platform media | Soong → `Android.bp` | `CabinTokens`, `CabinCompliance`, `CabinViews` |

See [docs/adoption/build-tree.md](docs/adoption/build-tree.md). Skill:
[skills/cabin-soong](skills/cabin-soong/SKILL.md).

## How to add a component

1. Confirm it passes pillars ([docs/product/pillars.md](docs/product/pillars.md)).
2. Specify tokens / type / space needs — no hardcoded hex/sp.
3. Declare interaction classes and compliance gates.
4. Define shared states/actions; then **Compose and Views** API shapes (parity).
5. Update component docs under `docs/components/`; cross-link compliance.
6. Catalog entry comes later — do not block on catalog in early phases.
7. Follow [skills/cabin-component-spec](skills/cabin-component-spec/SKILL.md).

## Skills (load when relevant)

Modular Agent Skills ([agentskills.io](https://agentskills.io) layout), aligned
in spirit with [android/skills](https://github.com/android/skills):

| Skill | Use when |
| --- | --- |
| [cabin-overview](skills/cabin-overview/SKILL.md) | Orienting to Cabin, modules, dual distribution |
| [cabin-tokens](skills/cabin-tokens/SKILL.md) | Tokens, theming, RRO, no framework deps |
| [cabin-compliance](skills/cabin-compliance/SKILL.md) | Driving/UX/a11y/safety; Restriction Engine |
| [cabin-compose](skills/cabin-compose/SKILL.md) | Implementing Compose Cabin UI |
| [cabin-views](skills/cabin-views/SKILL.md) | Views/XML, SystemUI, legacy apps |
| [cabin-soong](skills/cabin-soong/SKILL.md) | `Android.bp`, manifest, thin platform deps |
| [cabin-component-spec](skills/cabin-component-spec/SKILL.md) | Writing or implementing a component spec |

Index: [docs/agents/README.md](docs/agents/README.md). Cursor rules:
[.cursor/rules/cabin.mdc](.cursor/rules/cabin.mdc).

## Out of scope for agents (unless asked)

- Implementing Android library source during docs-only phases
- Kitchen-sink packs, vanity animation, phone-first patterns
- Remapping safety semantic colors for brand
- Replacing Car framework APIs or claiming to supersede `car-ui-lib`

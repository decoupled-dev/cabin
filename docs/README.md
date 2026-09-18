# Cabin documentation

Authoritative documentation for **Cabin**, an open-source AAOS design language
and dual UI component library (Jetpack Compose + legacy Views).

> Library Alpha: `cabin-tokens`, `cabin-compliance`, and Theme Kit in
> `cabin-views`. Planned APIs beyond that are marked as such.

## Table of contents

### Orientation

| Document | Description |
| --- | --- |
| [Vision](vision.md) | Positioning, uniqueness vs Material, OEM/Tier-1/dev adoption |
| [Principles](principles.md) | Clean Code, SOLID, safety-critical UI, android/skills alignment |
| [Architecture](architecture.md) | Multi-module plan, dependency rules, theming, anti-bloat |
| [Roadmap](roadmap.md) | Phased delivery aligned to the product feature layers |
| [Contributing](contributing.md) | Contribution guide |

### Pre-implementation (before library code)

| Document | Description |
| --- | --- |
| [Pre-implementation gate](pre-implementation.md) | Checklist before writing library source |
| [MVP v0.1](mvp.md) | Frozen scope: tokens + restriction + Theme Kit + two bars (Views-first) |
| [API contracts](api-contracts.md) | Maven, Soong names, packages, semver |
| [Support matrix](support-matrix.md) | Planned API / AAOS / build-tree targets |
| [Testing](testing.md) | v0.1 unit, Views, CI vs Soong expectations |
| [ADRs](adr/README.md) | Architecture decision records |

### Product

| Document | Description |
| --- | --- |
| [Product index](product/README.md) | Product design docs — stance, pillars, feature plan |
| [Stance](product/stance.md) | Calm, legible, inevitable cabin HMI |
| [Pillars](product/pillars.md) | Five gates every feature must pass |
| [Features](product/features.md) | Layered feature plan (Foundations → Craft) |

### Compliance (first-class)

| Document | Description |
| --- | --- |
| [Compliance overview](compliance/README.md) | How compliance is modeled and enforced |
| [Restriction states](compliance/restriction-states.md) | MVP state machine + System/Status Bar matrix |
| [Driving restrictions](compliance/driving-restrictions.md) | Distraction minimization, gated interactions |
| [UX restrictions](compliance/ux-restrictions.md) | Touch targets, density, contrast, day/night |
| [Accessibility & glanceability](compliance/accessibility-glanceability.md) | Font size, readability, driver glance patterns |
| [Safety-critical UI](compliance/safety-critical.md) | Deterministic behavior, fail-safe defaults |

### Design language

| Document | Description |
| --- | --- |
| [Overview](design-language/README.md) | Cabin design language scope |
| [Foundations](design-language/foundations.md) | Color, type, space, elevation, motion, icons |
| [Tokens](design-language/tokens.md) | Semantic + component tokens, OEM theming without forks |
| [Token schema](design-language/token-schema.md) | JSON stub, naming, overlay/RRO mapping |

### Components

| Document | Description |
| --- | --- |
| [Inventory](components/README.md) | Taxonomy and coverage map |
| [System bars](components/system-bars.md) | Nav / system chrome |
| [Status bars](components/status-bars.md) | Vehicle and system status |
| [Spec: System Bar](components/specs/system-bar.md) | MVP implementable spec |
| [Spec: Status Bar](components/specs/status-bar.md) | MVP implementable spec |
| [Media](components/media.md) | Playback, browse, now-playing |
| [EV](components/ev.md) | Range, charge, energy |
| [HVAC](components/hvac.md) | Climate zones and controls |
| [Vehicle controls](components/vehicle-controls.md) | Doors, lights, drive modes, etc. |
| [Extension model](components/extension-model.md) | OEM custom screens on Cabin rails |

### Platforms

| Document | Description |
| --- | --- |
| [Compose](platforms/compose.md) | Compose guidelines and Views parity contracts |
| [Views](platforms/views.md) | View/XML for SystemUI and build-tree apps |
| [Soong](platforms/soong.md) | Platform Soong engineering notes (`Android.bp`) |

### Adoption

| Document | Description |
| --- | --- |
| [Integration](adoption/integration.md) | Add Cabin to an existing AAOS/app repo |
| [Theme Kit](adoption/theme-kit.md) | Views Theme Kit + OEM overlay / RRO path (MVP #3) |
| [Build-tree / Soong](adoption/build-tree.md) | SystemUI, CarLauncher, platform media via Soong |
| [SystemUI wiring sketch](adoption/sketches/systemui-cabin/) | Thin `static_libs` fragment for host SystemUI |
| [Migration](adoption/migration.md) | From stock Material / AAOS widgets |
| [Packaging](adoption/packaging.md) | Maven vs Soong dual distribution, minimal footprint |
| [v0.1.0 release readiness](release/v0.1.0.md) | Library install path, CI gates, tag blockers |

### Agents

| Document | Description |
| --- | --- |
| [Agents index](agents/README.md) | AGENTS.md, skills/, Cursor rules, android/skills relationship |
| [AGENTS.md](../AGENTS.md) | Hard rules for coding agents (repo root) |
| [llms.txt](../llms.txt) | LLM-oriented doc map |
| [skills/](../skills/) | Modular Agent Skills (`SKILL.md` per concern) |

### Website

| Document | Description |
| --- | --- |
| [website/](https://github.com/decoupled-dev/cabin/tree/main/website) | Public docs shell (Docusaurus) — run/build/deploy |
| [Site plan](website/site-plan.md) | Public docs IA modeled on m3.material.io |
| [Marketing craft](website/marketing-craft.md) | Docs vs marketing roles; points to `apps/www/DESIGN.md` |

## How to read these docs

1. Start with [vision](vision.md) and the [product stance](product/stance.md).
2. Filter ideas through [pillars](product/pillars.md) and the
   [feature plan](product/features.md).
3. Before library code: [pre-implementation](pre-implementation.md) and
   [MVP](mvp.md).
4. Internalize [compliance](compliance/README.md) — it constrains every component.
5. Learn [tokens](design-language/tokens.md) /
   [token schema](design-language/token-schema.md) before implementing UI.
6. Pick a platform ([Compose](platforms/compose.md) or [Views](platforms/views.md))
   and keep parity contracts in mind. Platform chrome uses Soong —
   [build-tree](adoption/build-tree.md).
7. Use [architecture](architecture.md) and [packaging](adoption/packaging.md)
   when wiring modules into a product.

## Dual stack reminder

Every component and pattern is specified for **both**:

- Jetpack Compose (`cabin-compose`, Experimental Theme + bars)
- Legacy Views / XML (`cabin-views`, planned)

Shared tokens and compliance live outside both UI stacks so OEMs can theme
once and ship everywhere. See [architecture](architecture.md).

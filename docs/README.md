# Cabin documentation

Authoritative documentation for **Cabin**, an open-source AAOS design language
and dual UI component library (Jetpack Compose + legacy Views).

> Library source code is not in this repository yet. These docs define the
> contracts implementers will code against. Planned APIs are marked as such.

## Table of contents

### Orientation

| Document | Description |
| --- | --- |
| [Vision](vision.md) | Positioning, uniqueness vs Material, OEM/Tier-1/dev adoption |
| [Principles](principles.md) | Clean Code, SOLID, safety-critical UI, android/skills alignment |
| [Architecture](architecture.md) | Multi-module plan, dependency rules, theming, anti-bloat |
| [Roadmap](roadmap.md) | Phased delivery aligned to the product feature layers |
| [Contributing](contributing.md) | Contribution guide |

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

### Components

| Document | Description |
| --- | --- |
| [Inventory](components/README.md) | Taxonomy and coverage map |
| [System bars](components/system-bars.md) | Nav / system chrome |
| [Status bars](components/status-bars.md) | Vehicle and system status |
| [Media](components/media.md) | Playback, browse, now-playing |
| [EV](components/ev.md) | Range, charge, energy |
| [HVAC](components/hvac.md) | Climate zones and controls |
| [Vehicle controls](components/vehicle-controls.md) | Doors, lights, drive modes, etc. |
| [Extension model](components/extension-model.md) | OEM custom screens on Cabin rails |

### Platforms

| Document | Description |
| --- | --- |
| [Compose](platforms/compose.md) | Compose guidelines and Views parity contracts |
| [Views](platforms/views.md) | View/XML for system and build-tree apps |

### Adoption

| Document | Description |
| --- | --- |
| [Integration](adoption/integration.md) | Add Cabin to an existing AAOS/app repo |
| [Migration](adoption/migration.md) | From stock Material / AAOS widgets |
| [Packaging](adoption/packaging.md) | Gradle modules, artifacts, minimal footprint |

### Website (future)

| Document | Description |
| --- | --- |
| [Site plan](website/site-plan.md) | Public docs IA modeled on m3.material.io |

## How to read these docs

1. Start with [vision](vision.md) and the [product stance](product/stance.md).
2. Filter ideas through [pillars](product/pillars.md) and the
   [feature plan](product/features.md).
3. Internalize [compliance](compliance/README.md) — it constrains every component.
4. Learn [tokens](design-language/tokens.md) before implementing UI.
5. Pick a platform ([Compose](platforms/compose.md) or [Views](platforms/views.md))
   and keep parity contracts in mind.
6. Use [architecture](architecture.md) and [packaging](adoption/packaging.md)
   when wiring modules into a product.

## Dual stack reminder

Every component and pattern is specified for **both**:

- Jetpack Compose (`cabin-compose`, planned)
- Legacy Views / XML (`cabin-views`, planned)

Shared tokens and compliance live outside both UI stacks so OEMs can theme
once and ship everywhere. See [architecture](architecture.md).

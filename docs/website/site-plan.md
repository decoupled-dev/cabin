# Website site plan

Plan for Cabin’s public documentation site — a Material Design 3–class
showcase for the automobile cabin ([m3.material.io](https://m3.material.io/)
as IA reference, not a visual clone).

> Phase 7 deliverable ([roadmap](../roadmap.md)). This document freezes
> information architecture so docs and future implementation stay aligned.

## Goals

- Help OEMs, Tier-1s, and app developers learn Cabin quickly.
- Showcase foundations, components, and patterns with dual-stack callouts.
- Version docs with library releases.
- Host an interactive catalog entry point.

## Primary navigation (IA)

Modeled on Material’s discoverability, adapted for automotive:

| Top-level | Purpose |
| --- | --- |
| **Home** | Pitch, dual-stack, compliance story, get started |
| **Foundations** | Color, type, space, elevation, motion, iconography |
| **Styles** | Token themes, day/night, OEM overlay guide |
| **Components** | Chrome + domain component gallery |
| **Patterns** | Driving-safe browse, fault recovery, zone climate, charge session |
| **Compliance** | Driving, UX, a11y/glance, safety-critical |
| **Develop** | Compose, Views, adoption, packaging |
| **Blog / Versions** | Release notes, migration notes, roadmap highlights |

### Mapping to repo docs

| Site section | Repo docs |
| --- | --- |
| Home | [vision](../vision.md), root README |
| Foundations | [foundations](../design-language/foundations.md) |
| Styles | [tokens](../design-language/tokens.md) |
| Components | [components/*](../components/README.md) |
| Patterns | Derived from compliance + component flows |
| Compliance | [compliance/*](../compliance/README.md) |
| Develop | [platforms](../platforms/compose.md), [adoption](../adoption/integration.md) |
| Blog / Versions | [roadmap](../roadmap.md), release notes (future) |

## Page templates

### Foundation page

- Principle summary
- Do / don’t
- Token references
- Day/night examples
- Compose + Views usage snippets (**planned** APIs labeled)

### Component page

- Overview + placement in cabin
- Anatomy
- States
- Compliance behavior
- Dual-stack demos (Compose live; Views video/screenshot if needed)
- API tabs: Compose | Views
- Accessibility notes

### Pattern page

- When to use
- Driving vs Park behavior
- Composition of components
- Failure modes

## Catalog

- Interactive Compose catalog app linked from Components.
- Filters: stack, domain, safety class, compliance relevance.
- Deep links from site component pages to catalog destinations.

## Content principles

- Authoritative, specific — same tone as these docs.
- Always call out **Compose + Views** and **compliance** where relevant.
- Mark unimplemented demos clearly during early phases.
- American English.

## Technical preferences

**Stack (chosen for the early docs shell):** Docusaurus in top-level
`website/`, fully decoupled from Android library modules. See
[website/README.md](https://github.com/decoupled-dev/cabin/blob/main/website/README.md)
for install / start / build / deploy.

Planned capabilities as the site matures:

- Versioned routes (`/1.0/…`)
- Search
- Dark/light matching Cabin day/night where helpful
- Fast CI publish from `website/` + `docs/`

> Maintainer override: thin public docs shell may ship before the original
> Phase 7 / catalog milestone. Full interactive catalog remains later.

## Launch milestones

1. IA + content migration from `docs/` (structure already matches).
2. Foundations + Compliance live (no interactive catalog yet).
3. Components with static visuals.
4. Catalog integration.
5. Version switcher + blog.

## Out of scope for v1 site

- Full OEM white-label docs hosting
- Private program portals
- Live vehicle signal demos

## Related

- [Vision](../vision.md)
- [Design language](../design-language/README.md)
- [Roadmap](../roadmap.md)

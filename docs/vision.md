# Vision

Cabin is the open-source design platform for the automobile cabin on
Android Automotive OS — a Material Design 3–class system built for drivers,
passengers, and the constraints of the road.

## Positioning

Material Design 3 ([m3.material.io](https://m3.material.io/)) is the de-facto
reference for phone and tablet Android UI. Automotive deserves the same class
of platform: foundations, tokens, components, patterns, and a public catalog —
but every decision must respect **driving state**, **distraction limits**,
**glanceability**, and **safety-critical** controls.

Cabin aims to be that platform:

| Layer | Role |
| --- | --- |
| **Design language** | Cabin HMI foundations: color, type, space, motion, iconography |
| **Tokens** | Semantic + component tokens OEMs brand without forking |
| **Components** | Dual-stack UI (Compose + Views) for cabin surfaces |
| **Compliance** | First-class gates for driving, UX, a11y, safety |
| **Catalog + site** | Browseable reference (future), Material-style IA |

Cabin is **not** “Material with bigger buttons.” It is an automotive-native
system that can still feel familiar to Android developers.

## Product stance

Cabin’s product bar is simple: the cabin should feel **calm**, **legible**, and
**inevitable**. Every surface earns its place; compliance is design material,
not a bolt-on. Features must pass five pillars — glanceable, quiet, safe by
default, one language / two skins, thin by design — and land in a layered
feature plan rather than a kitchen-sink backlog.

See [product stance](product/stance.md), [pillars](product/pillars.md), and
[features](product/features.md).

## Who it is for

### OEMs

- Ship a coherent cabin UI across system UI, launcher, media, EV, HVAC, and
  third-party apps.
- Brand via tokens ([tokens](design-language/tokens.md)) without maintaining a
  divergent fork of every component.
- Encode program-specific driving restrictions on top of Cabin baselines
  ([compliance](compliance/README.md), [extension model](components/extension-model.md)).

### Tier-1 suppliers

- Deliver reusable HMI modules that plug into OEM brand systems.
- Share one component contract across Compose apps and View-based system UI.
- Reduce per-program reinvention of status bars, climate, and vehicle controls.

### App developers

- Adopt cabin-safe components with minimal footprint
  ([integration](adoption/integration.md)).
- Get compliance defaults instead of rediscovering AAOS UX restrictions.
- Target both modern Compose apps and legacy View surfaces when required.

## Uniqueness vs Material and generic Android

| Concern | Material / generic Android | Cabin |
| --- | --- | --- |
| Primary context | Handheld / lean-back | Moving vehicle, cabin HMI |
| Interaction budget | Rich, multi-step flows | Glanceable, gated while driving |
| Touch targets | Phone/tablet baselines | Automotive minimums and density rules |
| Dual UI stack | Compose-first; Views legacy | Compose **and** Views as first-class peers |
| Compliance | App-level responsibility | Shared library contracts + gates |
| Branding | Material You / dynamic color | OEM brand tokens without forking |
| Surfaces | Apps | System bars, status, media, EV, HVAC, vehicle controls |

Cabin may **align** with Material concepts (roles, elevation, state layers)
where they help Android developers, but Cabin tokens and components are
cabin-owned. See [foundations](design-language/foundations.md).

## Dual stack as a product requirement

AAOS programs still ship substantial View/XML UI: system UI, status bar,
car settings, build-tree apps, and vendor overlays. Compose alone cannot
cover adoption.

Cabin therefore commits to:

1. **Shared tokens and compliance** — no Compose-only truth.
2. **Parity contracts** — same behaviors and states on both stacks
   ([compose](platforms/compose.md), [views](platforms/views.md)).
3. **Independent packaging** — adopt Compose, Views, or both
   ([packaging](adoption/packaging.md)).

## Open-source adoption strategy

### Principles

- **Apache 2.0** — clear IP posture for OEM legal review ([LICENSE](../LICENSE)).
- **Modular artifacts** — OEMs pick `cabin-tokens` + one UI stack; they do not
  pull samples, catalog, or website into the image.
- **Extension over fork** — brand and extend via tokens and documented
  extension points ([extension model](components/extension-model.md)).
- **Compliance as code** — restrictions are APIs and policies, not wiki pages
  alone ([compliance](compliance/README.md)).
- **Production posture** — Clean Code, SOLID, modern Android rules
  ([principles](principles.md)).

### Adoption path (intended)

1. **Docs** (this phase) — shared vocabulary and contracts.
2. **Tokens** — OEMs map brand to Cabin semantic tokens.
3. **One UI stack** — Compose *or* Views for a pilot surface (e.g. media).
4. **Compliance wiring** — driving/UX gates in real product configuration.
5. **Expand surfaces** — HVAC, EV, system chrome; then the other UI stack.
6. **Catalog + website** — public reference accelerates third-party apps.

### What “de-facto” means

Success is measured by:

- Multiple OEMs/Tier-1s shipping Cabin-tokenized UI without private forks of
  the core libraries.
- Third-party AAOS apps defaulting to Cabin components for cabin safety.
- A public site and catalog that play the role Material’s site plays for
  handheld Android ([site plan](website/site-plan.md)).

## Non-goals (near term)

- Replacing the entire AAOS system image or Car framework.
- Shipping a full OEM launcher as the primary product.
- Implementing every regional regulation in v1 — Cabin provides **extensible
  baselines**; programs specialize ([compliance](compliance/README.md)).
- Android library source in Phase 1 — docs only ([roadmap](roadmap.md)).

## Related

- [Product stance](product/stance.md) · [Pillars](product/pillars.md) · [Features](product/features.md)
- [Principles](principles.md)
- [Architecture](architecture.md)
- [Roadmap](roadmap.md)
- [Website plan](website/site-plan.md)

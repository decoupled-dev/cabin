# Roadmap

Phased delivery for Cabin. Phase 1 (this PR) is documentation only — including
the [product stance](product/stance.md), [pillars](product/pillars.md), and
[feature plan](product/features.md).

## Sequencing

Delivery follows product layers, not toolkit novelty:

```text
Docs
  → Tokens + Restriction Engine
  → System / Status bars
  → Media + HVAC
  → EV + Vehicle Controls
  → Catalog
  → Website
```

Compose Kit and Views Kit grow **with** system surfaces and core screens so
parity stays a release gate — not a trailing rewrite. See
[features](product/features.md) for layer definitions and v1 non-goals.

## Phase overview

| Phase | Focus | Feature layer | Outcome |
| --- | --- | --- | --- |
| **1 — Docs** | Vision, product stance/pillars/features, architecture, compliance, design language, components, platforms, adoption, site IA | Plan | Implementable contracts |
| **2 — Tokens + Restriction Engine** | `cabin-tokens`, Theme Kit basics, `cabin-compliance` policy APIs | Layer 0 | Shared meaning + gates |
| **3 — System / Status bars** | System Bar, Status Bar; Overlay & Toast baselines; dual-stack as required | Layer 1 | Brandable chrome |
| **4 — Media + HVAC** | Now-playing/transport + climate zones/defrost on both stacks | Layer 2 (first) | First core domains |
| **5 — EV + Vehicle Controls** | SOC/charge + body controls/hazards; extension slots | Layer 2 (second) | Energy + vehicle domains |
| **6 — Catalog** | Interactive component catalog; parity fixtures visible | Layer 4 | Browseable reference |
| **7 — Website** | Public docs site (Material-class IA) | Layer 4 | Public showcase |

Patterns (Primary Action, Confirm Destructive, Cabin List/Grid, Empty & Error)
land alongside Layers 1–2 as those screens need them — not as a disconnected
phase.

## Phase 1 — Documentation foundation (current)

- [x] Root README, LICENSE (Apache-2.0), `.gitignore`
- [x] Docs index and cross-linked instruction set
- [x] Product stance, pillars, and layered feature plan
- [x] Compliance suite
- [x] Design language + component inventory
- [x] Dual-stack platform guidelines
- [x] Adoption + packaging guidance
- [x] Website IA plan
- [x] Contributing guide

**Exit criteria:** A new contributor or OEM engineer can implement tokens and
components against these docs without inventing architecture or product scope.

## Phase 2 — Tokens + Restriction Engine

- Token schema (semantic + component) and Theme Kit resolution
- OEM overlay example (fictional brand)
- Restriction Engine APIs (`CabinCompliance`, interaction gating)
- Default AAOS-oriented baselines + program tighten overlays
- Validation hooks for contrast / safety color locks

**Exit criteria:** Compose and Views prototypes consume the same tokens and
honor the same restriction fixtures.

## Phase 3 — System / Status bars

- System Bar slot model (Views-first for system UI; Compose parity)
- Status Bar items with unavailable/stale/fault honesty
- Overlay & Toast patterns that respect driving budgets
- Screenshot / parity baselines for chrome

**Exit criteria:** OEM can brand chrome via tokens/slots without forking bar
widgets; driving substitutes work on both stacks where both exist.

## Phase 4 — Media + HVAC

- Media now-playing, transport, restriction-aware browse
- HVAC zones, steppers, defrost (safety-critical availability)
- Compliance hooks wired through Theme Kit / locals / hosts
- Sample surfaces for media and climate

**Exit criteria:** Dual-stack parity for media transport + HVAC temp/fan/defrost
on shared state fixtures.

## Phase 5 — EV + Vehicle Controls

- EV SOC, range, charge session, charge fault banners
- Vehicle control grid, latched toggles, hazards
- Extension slots for OEM-specific controls
- Samples for energy and vehicle controls

**Exit criteria:** Fault/unavailable handling and latched states match across
stacks; extension model proven with at least one OEM-shaped tile.

## Phase 6 — Catalog

- Interactive catalog (Compose-first; Views documented)
- Filters by domain, stack, safety class, compliance relevance
- Deep links ready for the public site

**Exit criteria:** Catalog demonstrates Layers 1–2 without kitchen-sink
dependencies in product artifacts.

## Phase 7 — Website

- Implement [site plan](website/site-plan.md)
- Versioned docs aligned with library releases
- Blog / versions for migration notes

**Exit criteria:** Public IA matches Foundations / Styles / Components /
Patterns / Compliance / Develop — with dual-stack callouts throughout.

## Stability labels (future)

| Label | Meaning |
| --- | --- |
| **Experimental** | API may change without notice |
| **Alpha / Beta** | Tracking toward stable; migration notes provided |
| **Stable** | SemVer; parity + compliance tests green; pillars satisfied |

## Non-goals for early phases

- Vanity animation, phone-first patterns, kitchen-sink packs, OEM forks as
  default ([features](product/features.md))
- Full OEM launcher as the primary product
- Every vehicle domain in v1
- Shipping website before tokens, restriction engine, and real surfaces to show

## Related

- [Product features](product/features.md)
- [Product pillars](product/pillars.md)
- [Vision](vision.md)
- [Architecture](architecture.md)
- [Website plan](website/site-plan.md)

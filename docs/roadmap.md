# Roadmap

Phased delivery for Cabin. Phase 1 (this PR) is documentation only.

## Phase overview

| Phase | Focus | Outcome |
| --- | --- | --- |
| **1 — Docs** | Vision, principles, architecture, compliance, design language, components, platforms, adoption, site IA | Implementable contracts |
| **2 — Tokens** | `cabin-tokens` module, token formats, OEM overlay samples | Brandable token package |
| **3 — Compose** | `cabin-compose` theme + core components | Compose dual-stack half |
| **4 — Views** | `cabin-views` theme + core components | Views dual-stack half |
| **5 — Compliance** | `cabin-compliance` policies wired into both stacks | Enforceable gates |
| **6 — Samples** | Media, EV, HVAC, vehicle-controls reference apps | Prove adoption paths |
| **7 — Website + catalog** | Public docs site + interactive catalog | Material-class showcase |

Phases 3–5 may overlap once tokens stabilize; **parity** remains a release
gate for any component marked stable.

## Phase 1 — Documentation foundation (current)

- [x] Root README, LICENSE (Apache-2.0), `.gitignore`
- [x] Docs index and cross-linked instruction set
- [x] Compliance suite
- [x] Design language + component inventory
- [x] Dual-stack platform guidelines
- [x] Adoption + packaging guidance
- [x] Website IA plan
- [x] Contributing guide

**Exit criteria:** A new contributor or OEM engineer can implement tokens and
components against these docs without inventing architecture.

## Phase 2 — Tokens

- Define token schema (semantic + component)
- Kotlin multiplatform-friendly or Android library packaging (decision in phase)
- OEM overlay example (fictional brand)
- Export story for design tools (optional stretch)

**Exit criteria:** Compose and Views prototypes can consume the same token
source.

## Phase 3 — Compose (`cabin-compose`)

- `CabinTheme` and foundational components
- Priority surfaces: media now-playing, status chips, HVAC basics
- Compliance hooks as composable/local providers
- Screenshot / Paparazzi (or equivalent) baselines

**Exit criteria:** Sample Compose app ships with branded tokens + driving gate.

## Phase 4 — Views (`cabin-views`)

- Theme overlays, styleables, foundational widgets
- Priority surfaces matching Compose set
- System-UI-friendly inflation and configuration changes
- Parity tests against Compose contracts

**Exit criteria:** Sample View-based screen matches Compose states for the
priority set.

## Phase 5 — Compliance hardening

- Formalize policy APIs
- Default AAOS-oriented baselines + OEM override points
- Instrumentation for gated interactions
- Documentation of regional specialization patterns

**Exit criteria:** Compliance is a published artifact with tests; UI stacks
depend on it rather than embedding one-off checks.

## Phase 6 — Samples

- `sample-media`, `sample-ev`, `sample-hvac`, `sample-vehicle-controls`
- Integration README mirroring [adoption](adoption/integration.md)
- Minimal-footprint demo (tokens + one stack only)

## Phase 7 — Website and catalog

- Implement [site plan](website/site-plan.md)
- Interactive catalog (Compose-first; Views documented)
- Versioned docs aligned with library releases

## Stability labels (future)

| Label | Meaning |
| --- | --- |
| **Experimental** | API may change without notice |
| **Alpha / Beta** | Tracking toward stable; migration notes provided |
| **Stable** | SemVer; parity + compliance tests green |

## Non-goals for early phases

- Full OEM launcher
- Every vehicle domain in v1
- Shipping website before tokens + at least one UI stack

## Related

- [Vision](vision.md)
- [Architecture](architecture.md)
- [Website plan](website/site-plan.md)

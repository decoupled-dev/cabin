# Roadmap

Phased delivery for Cabin. Phase 1 (this PR) is documentation only — including
the [product stance](product/stance.md), [pillars](product/pillars.md),
[feature plan](product/features.md), and the
[pre-implementation pack](pre-implementation.md) with a **frozen v0.1 MVP**.

## Sequencing

Delivery follows product layers, not toolkit novelty:

```text
Docs (+ pre-implementation gate)
  → MVP v0.1: Tokens + Restriction Engine + Theme Kit + System/Status bars (Views-first)
  → Media + HVAC
  → EV + Vehicle Controls
  → Catalog
  → Website
```

**Do not** start media/HVAC/EV/Compose domain screens until
[MVP success criteria](mvp.md) are met. Compose Kit grows with surfaces after
MVP; Views-first platform chrome remains the SystemUI path
([ADR 0002](adr/0002-views-first-platform.md)).

## Phase overview

| Phase | Focus | Feature layer | Outcome |
| --- | --- | --- | --- |
| **1 — Docs + pre-implementation** | Vision, product, compliance, tokens stub, MVP freeze, specs, ADRs, agents | Plan | Implementable contracts |
| **2 — MVP v0.1** | `cabin-tokens`, Restriction Engine, Theme Kit, System + Status bars (Views), dual Gradle/Soong | Layer 0–1 (frozen) | Shippable thin chrome |
| **3 — Media + HVAC** | Domain screens on dual stack as needed | Layer 2 (first) | First core domains |
| **4 — EV + Vehicle Controls** | Energy + body controls | Layer 2 (second) | Energy + vehicle domains |
| **5 — Catalog** | Interactive catalog | Layer 4 | Browseable reference |
| **6 — Website** | Public docs site | Layer 4 | Public showcase |

> **Early start:** a thin Docusaurus shell under
> [`website/`](https://github.com/decoupled-dev/cabin/tree/main/website) may
> ship before the full Phase 6/7 catalog. Interactive demos remain later.
> Night-scheme expansion and Theme Kit precede Views System/Status bars on the
> MVP track. Bars are now Alpha in `cabin-views`; packaging notes remain.

## Phase 1 — Documentation + pre-implementation (current)

- [x] Root README, LICENSE, `.gitignore`
- [x] Product stance, pillars, feature plan
- [x] Compliance suite + restriction state matrix
- [x] Token JSON stub + schema doc
- [x] MVP freeze + pre-implementation gate
- [x] System/Status Bar implementable specs
- [x] API contracts, support matrix, testing, ADRs
- [x] Build-tree / Soong + agent affordances

**Exit criteria:** An implementer or agent can build MVP without inventing
module layout, gating matrix, or bar contracts — after clearing
[pre-implementation.md](pre-implementation.md).

## Phase 2 — MVP v0.1 (in progress)

See [mvp.md](mvp.md) success criteria. Summary:

- [x] Tokens from `tokens/cabin.tokens.json`
- [x] Restriction Engine per [restriction-states.md](compliance/restriction-states.md)
- [x] Theme Kit (Views) per [theme-kit](adoption/theme-kit.md)
- [x] Views System Bar + Status Bar per specs
- [ ] Thin Soong + Maven packaging notes / publish
## Phase 3 — Media + HVAC

Unblocked only after MVP done. Dual-stack as required; compliance hooks
mandatory.

## Phase 4 — EV + Vehicle Controls

Same gating as Phase 3.

## Phase 5 — Catalog

Interactive catalog after real Layer 1–2 surfaces exist.

## Phase 6 — Website

Implement [site plan](website/site-plan.md).

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
- Domain screens before MVP
- Shipping website before tokens, restriction engine, and real surfaces

## Related

- [Pre-implementation](pre-implementation.md) · [MVP](mvp.md)
- [Product features](product/features.md)
- [ADRs](adr/README.md)
- [Vision](vision.md)
- [Architecture](architecture.md)

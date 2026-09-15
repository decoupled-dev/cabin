# MVP v0.1 (frozen)

**Frozen scope** for the first implementable Cabin library drop. Anything not
listed here is **explicitly later** — see [feature plan](product/features.md)
and [roadmap](roadmap.md).

## In scope

| Piece | Module (planned) | Stack | Notes |
| --- | --- | --- | --- |
| **Cabin Tokens** | `cabin-tokens` / `CabinTokens` | N/A (no UI toolkit) | Source stub: [`tokens/cabin.tokens.json`](../tokens/cabin.tokens.json) |
| **Restriction Engine** | `cabin-compliance` / `CabinCompliance` | N/A (no UI toolkit) | States + matrix: [restriction-states.md](compliance/restriction-states.md) |
| **Theme Kit** | Part of `cabin-views` / `CabinViews` (optional Compose mirror later) | **Views-first** | Alpha: resolves tokens → theme attrs / day-night; [theme-kit](adoption/theme-kit.md) |
| **System Bar** | `cabin-views` / `CabinViews` | **Views-first** | Spec: [specs/system-bar.md](components/specs/system-bar.md) |
| **Status Bar** | `cabin-views` / `CabinViews` | **Views-first** | Spec: [specs/status-bar.md](components/specs/status-bar.md) |

Dual distribution remains required: Gradle (Maven) **and** Soong
([api-contracts.md](api-contracts.md), [build-tree](adoption/build-tree.md)).

## Non-goals (v0.1)

- Media, HVAC, EV, vehicle-controls **screens** and domain component packs
- Full Compose component kit / Compose-first SystemUI
- Catalog app, public website implementation
- Overlay & Toast pattern library (beyond whatever minimal chrome needs)
- Kitchen-sink umbrella AAR/Soong module
- OEM forks of Cabin core; regional regulation packs as product defaults
- Replacing Car framework APIs or `car-ui-lib`

## Success criteria (MVP done)

MVP is complete when **all** of the following hold:

1. **Tokens** — `cabin-tokens` publishes from (or clearly codegen’d from)
   `tokens/cabin.tokens.json`; day/night semantic roles resolvable; no UI
   framework dependency.
2. **Restriction Engine** — `cabin-compliance` implements the state model and
   allow/deny for System Bar and Status Bar interaction categories per
   [restriction-states.md](compliance/restriction-states.md); unit tests green
   ([testing.md](testing.md)).
3. **Theme Kit** — Views theme resolves token roles; OEM overlay / RRO path
   documented and demoable without forking widgets
   ([ADR 0003](adr/0003-tokens-via-overlay-rro.md),
   [theme-kit](adoption/theme-kit.md)). **Alpha in `cabin-views`.**
4. **System Bar + Status Bar (Views)** — match
   [specs](components/specs/system-bar.md); restriction-aware; thin Soong
   modules consumable by a SystemUI-shaped target. **Not started** (next).5. **Packaging** — Maven coordinates and Soong names per
   [api-contracts.md](api-contracts.md); SystemUI cannot pull Compose/catalog/
   samples through Cabin deps.
6. **Support** — builds against [support-matrix.md](support-matrix.md) targets.

Compose parity for the two bars may trail as **experimental** and is **not**
required to call MVP done — Views-first is the gate
([ADR 0002](adr/0002-views-first-platform.md)).

## After MVP

Unblock Layer 2 domain screens and fuller Compose kit per
[roadmap](roadmap.md). Do not start those until this page’s success criteria
are met.

## Related

- [Pre-implementation gate](pre-implementation.md)
- [Support matrix](support-matrix.md)
- [ADRs](adr/README.md)

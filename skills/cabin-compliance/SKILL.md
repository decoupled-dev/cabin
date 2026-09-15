---
name: cabin-compliance
description: >-
  Cabin Restriction Engine and compliance — driving restrictions, UX minima,
  accessibility/glanceability, safety-critical fail-safes. Use when gating
  interactions while driving, handling CarUxRestrictions, sizing touch targets,
  rendering unavailable/stale/fault vehicle signals, or preventing hardcoded
  policy inside widgets.
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin compliance

## Core rule

**Never hardcode driving/UX policy inside individual widgets.** Declare
interactions; ask `CabinCompliance` / Restriction Engine (**planned**).

`cabin-compliance` has **no** Compose or Views widget dependency.

## Document map

| Topic | Doc |
| --- | --- |
| Overview | [docs/compliance/README.md](../../docs/compliance/README.md) |
| Driving | [driving-restrictions.md](../../docs/compliance/driving-restrictions.md) |
| UX | [ux-restrictions.md](../../docs/compliance/ux-restrictions.md) |
| Glance / a11y | [accessibility-glanceability.md](../../docs/compliance/accessibility-glanceability.md) |
| Safety | [safety-critical.md](../../docs/compliance/safety-critical.md) |

## Patterns

- **Disable / hide / substitute** denied interactions (prefer substitute for primary journeys).
- Map platform `CarUxRestrictions` into Cabin `VehicleUiState` adapters — do not reimplement Car services.
- Exhaustive `Signal` handling: unavailable / stale / fault — never invent values.
- Safety-critical actions stay reachable; no login walls.

## Dual stack

Same allow/deny for the same fixture on Compose and Views
([docs/platforms/compose.md](../../docs/platforms/compose.md),
[docs/platforms/views.md](../../docs/platforms/views.md)).

## Do not

- Bypass compliance locals/hosts “for the demo”
- Loosen safety defaults silently in OEM overlays
- Put Restriction Engine logic only in one UI stack

Related: skill `cabin-component-spec` · [AGENTS.md](../../AGENTS.md)

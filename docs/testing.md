# Testing

Testing expectations for Cabin. **v0.1 MVP** focuses on Restriction Engine
unit tests and Views chrome verification. Compose golden parity is **later**.

## v0.1 required

### Restriction Engine (unit)

- Fixtures for Parked / Idling / Moving / Restricted / Unknown
- Assert allow / substitute / block for System Bar and Status Bar categories
  per [restriction-states.md](compliance/restriction-states.md)
- Unknown defaults documented and tested
- No Android UI toolkit required in `cabin-compliance` tests

### Tokens

- Codegen or resource packaging produces expected keys from
  [`tokens/cabin.tokens.json`](../tokens/cabin.tokens.json)
- Safety-lock metadata preserved for `warning` / `error`
- Day/night scheme roles present: `surface`, `onSurface`, `surfaceVariant`,
  `outline`, `container`, `onContainer`, plus feedback (`warning` / `error` /
  `charging`)
- Night contrast locked for `warning` / `error` / `charging` (no soft-wash)
- Body-text roles (`onSurface`, `onContainer`) stay off domain accents

### Views System Bar / Status Bar

- Instrumentation or Robolectric: slot/item binding
- Gated clicks do not fire when Block
- Content descriptions set on icon-only entries
- Signal unavailable / stale / fault rendering for status items
- Theme overlay / RRO smoke (manual or automated) without forking widgets

### Packaging / thin deps

- Gradle module dependency tests (or lint) ensuring Views ⊀ Compose
- Document Soong `static_libs` review for SystemUI sample wiring
  (no `CabinCompose`)

## Later (post-MVP)

| Area | Expectation |
| --- | --- |
| Screenshot / golden | Paparazzi (or equiv.) for Views; Compose parity when Compose bars exist |
| Dual-stack parity | Shared fixtures for System/Status bar state |
| Catalog | Visual QA harness off production images |
| Platform image CI | Optional userdebug jobs consuming Soong modules |

## Gradle CI vs platform / Soong

| Lane | v0.1 |
| --- | --- |
| **Gradle CI** (GitHub/etc.) | Unit tests for compliance + tokens; Views module tests; lint/format as added |
| **Platform / Soong** | Manual or partner tree verification that `Cabin*` modules build and SystemUI-shaped target links thinly; full AAOS image CI is partner-owned |

Cabin does not require hosting a full AAOS tree in this repo for MVP.

## Related

- [MVP](mvp.md)
- [Pre-implementation](pre-implementation.md)
- [Support matrix](support-matrix.md)

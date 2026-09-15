# Testing

Testing expectations for Cabin. **v0.1 MVP** focuses on Restriction Engine
unit tests and Views chrome verification. Compose bar parity is
**Experimental** (post-MVP) with gate / Signal / tone unit tests.

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

### Theme Kit (Views)

- Robolectric (or instrumentation): `Theme.Cabin` resolves scheme roles day/night
- OEM theme overlay remaps brand (`primary`) without changing locked
  `warning` / `error` / `charging` ([theme-kit](adoption/theme-kit.md),
  [ADR 0003](adr/0003-tokens-via-overlay-rro.md))
- No AppCompat/Material required in the Theme Kit module graph for resolution

### Views System Bar / Status Bar

- Instrumentation or Robolectric: slot/item binding
- Gated clicks do not fire when Block
- Content descriptions set on icon-only entries
- Signal unavailable / stale / fault rendering for status items
- Theme overlay / RRO smoke (manual or automated) without forking widgets
  (Theme Kit overlay tests cover the brand path ahead of bar widgets)

### Packaging / thin deps

- Gradle module dependency tests (or lint) ensuring Views ⊀ Compose
- Document Soong `static_libs` review for SystemUI sample wiring
  (no `CabinCompose`)

## Later (post-MVP)

| Area | Expectation |
| --- | --- |
| Compose System/Status bars | **Experimental** — gate / Signal / tone parity tests in `cabin-compose` (shipped); screenshot/golden later |
| Screenshot / golden | Paparazzi (or equiv.) for Views; Compose parity screenshots |
| Dual-stack parity | Shared fixtures for System/Status bar state |
| Catalog | Visual QA harness off production images |
| Platform image CI | Optional userdebug jobs consuming Soong modules |

## Gradle CI vs platform / Soong

| Lane | v0.1 |
| --- | --- |
| **Gradle CI** (GitHub/etc.) | Unit tests for compliance + tokens + Theme Kit / Views bars (`cabin-views`) + Experimental Compose bars (`cabin-compose`); lint/format as added |
| **Platform / Soong** | Manual or partner tree verification that `Cabin*` modules build and SystemUI-shaped target links thinly; full AAOS image CI is partner-owned |

Cabin does not require hosting a full AAOS tree in this repo for MVP.

## Related

- [MVP](mvp.md)
- [Pre-implementation](pre-implementation.md)
- [Support matrix](support-matrix.md)

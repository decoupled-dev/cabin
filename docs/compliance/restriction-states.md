# Restriction states

Concrete Restriction Engine state model for **v0.1 MVP**. Policy lives in
`cabin-compliance` (**planned**) — widgets query it; they do not embed OEM
if/else trees. Broader context: [compliance README](README.md),
[driving restrictions](driving-restrictions.md).

## Vehicle UI states (MVP)

| State | Meaning | Typical signals (adapter-owned) |
| --- | --- | --- |
| **Parked** | Vehicle secured for configuration-heavy UI | Park / gear P, speed ≈ 0 |
| **Idling** | Powered, not in purposeful motion; still distraction-sensitive | Gear not P or brake held; speed ≈ 0 |
| **Moving** | Vehicle in motion | Speed above program threshold |
| **Restricted** | Explicit UX restriction profile active (distraction optimization) | Mapped from `CarUxRestrictions` / OEM policy |

Notes:

- Adapters map Car APIs → these states; Cabin does not own vehicle services.
- If state is **unknown**, fail per [safety-critical](safety-critical.md)
  (treat as **Restricted** for chrome configuration entry points unless
  product policy documents otherwise).
- Programs may tighten (e.g. treat Idling as Restricted); they must not silently
  loosen safety-adjacent defaults.

## Interaction categories (MVP)

| Category | Examples in chrome |
| --- | --- |
| `Glance` | Status icons, clock, SOC glyph |
| `NavigateSimple` | Home / map entry that opens a driving-safe destination |
| `OpenComplexApp` | App grid, settings, pairing, deep setup |
| `OpenKeyboard` | Search fields requiring IME |
| `FilterOrSort` | Dense collection controls |
| `MediaTransport` | Play/pause peek (if slotted into system bar) |
| `HvacPeek` | Climate shortcut (if slotted) |
| `HvacAdjust` | ClimateTile temp / fan / seat-heat adjustments |
| `MediaComplex` | Now-playing source picker, scrub/seek, queue entry |
| `StatusDeepLink` | Tap status item → detail / settings |

## Allow matrix — System Bar & Status Bar

Legend: **A** = allow · **S** = substitute/disable (keep layout if possible) ·
**B** = block (no activation)

### System Bar

| Interaction | Parked | Idling | Moving | Restricted |
| --- | --- | --- | --- | --- |
| `NavigateSimple` | A | A | A | A |
| `MediaTransport` | A | A | A | A |
| `HvacPeek` | A | A | A | A / S¹ |
| `HvacAdjust` (ClimateTile) | A | A | B | B |
| `MediaComplex` | A | S | B | B |
| `OpenComplexApp` | A | S | B | B |
| `OpenKeyboard` | A | B | B | B |
| `FilterOrSort` | A | B | B | B |

¹ Program may allow a **limited** HVAC peek (temp ± only) while Restricted;
deep climate setup stays blocked.

### Status Bar

| Interaction | Parked | Idling | Moving | Restricted |
| --- | --- | --- | --- | --- |
| `Glance` (display only) | A | A | A | A |
| `StatusDeepLink` → informational | A | A | S | S |
| `StatusDeepLink` → settings / setup | A | S | B | B |
| `OpenKeyboard` from status | A | B | B | B |

Unavailable / stale / fault **presentation** is always required when signals
are bad — that is display honesty, not an “interaction allow.”

## Planned policy API sketch

```kotlin
// Planned — cabin-compliance
enum class CabinUiMode { Parked, Idling, Moving, Restricted, Unknown }

enum class CabinInteraction {
    Glance,
    NavigateSimple,
    OpenComplexApp,
    OpenKeyboard,
    FilterOrSort,
    MediaTransport,
    HvacPeek,
    HvacAdjust,
    MediaComplex,
    StatusDeepLink,
}

interface CabinCompliance {
    fun uiMode(state: VehicleUiState): CabinUiMode
    fun allows(interaction: CabinInteraction, state: VehicleUiState): Boolean
    fun disposition(interaction: CabinInteraction, state: VehicleUiState): GateDisposition
}

enum class GateDisposition { Allow, Substitute, Block }
```

Views (**planned**): `CabinComplianceHost` + gated click listeners on bar
items. Compose (**planned**, post-MVP for chrome parity):
`LocalCabinCompliance`.

## MVP test fixtures

Minimum unit cases ([testing.md](../testing.md)):

- Moving + `OpenComplexApp` → Block on System Bar
- Restricted + `OpenKeyboard` → Block
- Moving + `NavigateSimple` → Allow
- Restricted + status settings deep link → Block
- Unknown mode → Restricted-equivalent for complex entry (default)

## Related

- [MVP](../mvp.md)
- [System Bar spec](../components/specs/system-bar.md)
- [Status Bar spec](../components/specs/status-bar.md)
- [ADR 0004](../adr/0004-compliance-separate-module.md)

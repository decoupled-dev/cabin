# HVAC components

Climate control UI for multi-zone cabins: temperature, fan, modes, seat heat /
vent, and visibility-critical actions (defrost).

## Purpose

- Fast, low-distraction adjustment of climate.
- Clear zone ownership (driver / passenger / rear).
- Keep defrost and visibility actions available and obvious.

## Component set

| Component | Role | Safety class | Status |
| --- | --- | --- | --- |
| `ClimateTile` | Zone temp / fan / seat heat | Convenience | **Alpha** Views · **Experimental** Compose — [spec](specs/climate-tile.md) |
| `HvacZonePanel` | Temp + fan for a zone | Convenience | Planned |
| `HvacTempStepper` | Large ± temperature | Convenience | Planned (folded into ClimateTile for now) |
| `HvacFanControl` | Fan level / auto | Convenience | Planned (folded into ClimateTile for now) |
| `HvacModeSelector` | Face / foot / bi-level / etc. | Convenience | Planned |
| `HvacSeatClimate` | Heat / vent levels | Convenience | Planned (folded into ClimateTile for now) |
| `HvacDefrostControls` | Front/rear defrost | **Safety-critical** (visibility) | Planned |
| `HvacSyncToggle` | Sync zones | Convenience | Planned |
| `HvacPeek` | System bar shortcut | Convenience | Alpha (System Bar slot) |

## States

- Auto vs manual
- Sync on/off
- Seat climate levels
- Defrost active
- HVAC power off (still show how to power on with large target)
- Signal unavailable per zone

## Compliance

| Concern | Rule |
| --- | --- |
| Driving | `HvacAdjust` on ClimateTile is **Block** while Moving / Restricted / Unknown (fail-closed). System bar `HvacPeek` stays limited Allow ([restriction-states](../compliance/restriction-states.md)) |
| UX | Large steppers; generous spacing ([ux](../compliance/ux-restrictions.md)) |
| Safety | Defrost always reachable; not behind setup ([safety](../compliance/safety-critical.md)) |
| Glance | Numeric temp tabular; zone labels short |

## Token dependencies

- `cabin.color.semantic.climate`
- `cabin.component.climateTile.*`
- Touch minima tokens

## Compose API (Experimental — ClimateTile)

```kotlin
@Composable
fun CabinClimateTile(
    state: CabinClimateTileState,
    onAction: (CabinClimateTileAction) -> Unit,
    modifier: Modifier = Modifier,
)
```

## Views API (Alpha — ClimateTile)

```kotlin
class CabinClimateTileView : /* … */ {
    fun bind(state: CabinClimateTileState)
    fun setOnActionListener(listener: ((CabinClimateTileAction) -> Unit)?)
    fun setCompliance(host: CabinComplianceHost?)
}
```

## Planned (later)

```kotlin
// Planned
@Composable
fun HvacZonePanel(
    zone: HvacZoneUiState,
    onChange: (HvacZoneAction) -> Unit,
    modifier: Modifier = Modifier,
)

@Composable
fun HvacDefrostControls(
    state: DefrostUiState,
    onChange: (DefrostAction) -> Unit,
    modifier: Modifier = Modifier,
)
```

## Parity notes

Zone models and ClimateTile Signal honesty must match across stacks. Animation
of temp changes should respect driving motion budgets on both stacks.

## Acceptance criteria

- [x] ClimateTile dual-stack with RE fail-closed while Moving
- [x] Unavailable zone signals handled (no invented values)
- [ ] Defrost classified and always available (later)
- [ ] Sync behavior documented (later)
- [x] Compose/Views parity for temp/fan/seat heat

## Related

- [ClimateTile spec](specs/climate-tile.md)
- [System bars](system-bars.md) (HVAC peek)
- [Vehicle controls](vehicle-controls.md)
- [Safety-critical](../compliance/safety-critical.md)

# HVAC components

Climate control UI for multi-zone cabins: temperature, fan, modes, seat heat /
vent, and visibility-critical actions (defrost).

## Purpose

- Fast, low-distraction adjustment of climate.
- Clear zone ownership (driver / passenger / rear).
- Keep defrost and visibility actions available and obvious.

## Component set (planned)

| Component | Role | Safety class |
| --- | --- | --- |
| `HvacZonePanel` | Temp + fan for a zone | Convenience |
| `HvacTempStepper` | Large ± temperature | Convenience |
| `HvacFanControl` | Fan level / auto | Convenience |
| `HvacModeSelector` | Face / foot / bi-level / etc. | Convenience |
| `HvacSeatClimate` | Heat / vent levels | Convenience |
| `HvacDefrostControls` | Front/rear defrost | **Safety-critical** (visibility) |
| `HvacSyncToggle` | Sync zones | Convenience |
| `HvacPeek` | System bar shortcut | Convenience |

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
| Driving | Stepper and toggles allowed; deep HVAC settings may be limited ([driving](../compliance/driving-restrictions.md)) |
| UX | Large steppers; generous spacing ([ux](../compliance/ux-restrictions.md)) |
| Safety | Defrost always reachable; not behind setup ([safety](../compliance/safety-critical.md)) |
| Glance | Numeric temp tabular; zone labels short |

## Token dependencies

- `cabin.color.semantic.climate`
- `cabin.component.hvac.zone.gap`
- `cabin.component.hvac.stepper.minSize`
- Touch minima tokens

## Planned Compose API

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

## Planned Views API

```xml
<!-- Planned -->
<dev.decoupled.cabin.views.hvac.HvacZonePanelView
    android:layout_width="0dp"
    android:layout_weight="1"
    android:layout_height="match_parent" />
```

## Parity notes

Zone models and defrost latched states must match. Animation of temp changes
should respect driving motion budgets on both stacks.

## Acceptance criteria

- [ ] Defrost classified and always available
- [ ] Zone panels meet touch minima
- [ ] Unavailable zone signals handled
- [ ] Sync behavior documented
- [ ] Compose/Views parity for temp/fan/defrost

## Related

- [System bars](system-bars.md) (HVAC peek)
- [Vehicle controls](vehicle-controls.md)
- [Safety-critical](../compliance/safety-critical.md)

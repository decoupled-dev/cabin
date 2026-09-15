# Vehicle controls

Vehicle body and convenience controls presented in HMI: doors/locks, lights,
windows, mirrors, wipers, drive modes, and similar affordances. Physical
hard-keys may coexist; HMI must remain honest about state.

## Purpose

- Provide a consistent control language for body features across OEM apps and
  system settings.
- Distinguish convenience toggles from safety-critical indicators/actions.
- Support extension for OEM-specific features (frunk, camping mode, etc.).

## Component set (planned)

| Component | Role | Safety class |
| --- | --- | --- |
| `LockToggle` | Lock/unlock | Convenience (context-dependent) |
| `LightModes` | Headlights / auto / fog | Convenience / informational |
| `WindowControls` | Window positions | Convenience; restrict complexity while driving |
| `DriveModeSelector` | Eco / normal / sport / … | Convenience; confirmations per program |
| `Trailer / TowingStatus` | Tow state | Informational / warning |
| `HazardControl` | Hazards when in HMI | **Safety-critical** |
| `VehicleControlGrid` | Launcher of controls | Convenience (restriction-aware) |

OEM-specific controls use the [extension model](extension-model.md).

## States

- On / off / auto / unavailable / fault
- Partially open (windows) with clear indeterminate UI
- Drive mode pending vs applied
- Powered-off vehicle limited control set

## Compliance

| Concern | Rule |
| --- | --- |
| Driving | Prefer simple toggles; block multi-step mirror fold sequences if restricted ([driving](../compliance/driving-restrictions.md)) |
| Safety | Hazards and critical telltales follow [safety-critical](../compliance/safety-critical.md) |
| Determinism | Never show “unlocked” without signal confirmation |
| UX | Grid items meet touch minima; clear off targets |

## Token dependencies

- Control grid spacing / icon sizes
- Semantic warning/error
- Shape tokens for control tiles (modest radii)

## Planned Compose API

```kotlin
// Planned
@Composable
fun VehicleControlGrid(
    items: List<VehicleControlItem>,
    onToggle: (VehicleControlItem.Id, Boolean) -> Unit,
    modifier: Modifier = Modifier,
)

@Composable
fun HazardControl(
    signal: Signal<Boolean>,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
)
```

## Planned Views API

```kotlin
// Planned
class VehicleControlGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : GridLayout(context, attrs) {
    fun bind(items: List<VehicleControlItem>) { /* … */ }
}
```

## Parity notes

Latched states (locks, hazards, lights) must match exactly across stacks for
the same `Signal` fixtures. Indeterminate window positions need shared enum
mapping.

## Acceptance criteria

- [ ] Safety-critical controls identified
- [ ] Unavailable ≠ off
- [ ] Driving-gated multi-step flows
- [ ] Extension slots for OEM controls documented
- [ ] Dual-stack parity for latched toggles

## Related

- [HVAC](hvac.md)
- [Extension model](extension-model.md)
- [Safety-critical](../compliance/safety-critical.md)

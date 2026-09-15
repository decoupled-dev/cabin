# System bars

System bars are the persistent chrome of the AAOS experience: navigation affordances,
app entry points, and system-level actions that frame application content.

> **MVP implementable spec:** [specs/system-bar.md](specs/system-bar.md)
> (Views-first). This page is the overview; the spec is the contract.

## Purpose

- Provide consistent wayfinding across OEM launchers and system UI.
- Remain usable under driving restrictions with large targets.
- Host OEM brand marks via tokens without custom forks.

## Typical contents

| Element | Notes |
| --- | --- |
| Home / map / app grid entry | Core navigation |
| HVAC peek / shortcut | May deep-link to [HVAC](hvac.md) |
| Media mini controls | Optional; see [media](media.md) |
| Notifications shade entry | Restriction-aware |
| OEM Quick controls | Extension slots |

Exact layout is OEM-specific; Cabin standardizes **slots**, **sizes**, and
**compliance behavior**.

## States

- Default / scrolled (if content influences chrome)
- Driving-restricted (hide or substitute dense entries)
- Night / day schemes
- Focused (rotary)

## Compliance

| Concern | Rule |
| --- | --- |
| Driving | Limit entries that open setup/keyboard-heavy flows ([driving](../compliance/driving-restrictions.md)) |
| UX | Min touch targets for all tappable slots ([ux](../compliance/ux-restrictions.md)) |
| Safety class | **Convenience** chrome; do not bury safety controls exclusively here |
| Glance | Icons + short labels; stable positions |

## Token dependencies

- `cabin.component.systemBar.height`
- `cabin.component.systemBar.iconSize`
- `cabin.color.semantic.surface` / `onSurface`
- `cabin.size.touch.minimum`

## Planned Compose API

```kotlin
// Planned — often used in app-level mock system UI / embedded chrome
@Composable
fun CabinSystemBar(
    slots: CabinSystemBarSlots,
    compliance: CabinCompliance = LocalCabinCompliance.current,
    modifier: Modifier = Modifier,
)
```

## Planned Views API

```xml
<!-- Planned -->
<dev.decoupled.cabin.views.CabinSystemBarView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cabinSlots="@xml/oem_system_bar_slots" />
```

System UI integrations typically prefer the Views path.

## Parity notes

Slot model and restriction substitution must match. Visual height and icon
sizes resolve from the same tokens.

## Acceptance criteria

- [ ] Slot configuration without core fork
- [ ] Driving substitutes applied
- [ ] Touch minima enforced
- [ ] Day/night correct
- [ ] Dual-stack parity for shared apps / previews

## Related

- [Status bars](status-bars.md)
- [Extension model](extension-model.md)
- [Views platform](../platforms/views.md)

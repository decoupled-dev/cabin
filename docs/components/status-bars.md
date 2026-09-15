# Status bars

Status bars communicate vehicle and system state at a glance: connectivity,
battery/charge, temperature outdoor, user profiles, and alerts.

> **MVP implementable spec:** [specs/status-bar.md](specs/status-bar.md)
> (Views-first). This page is the overview; the spec is the contract.

## Purpose

- Provide persistent, glanceable status for drivers and passengers.
- Standardize iconography and ordering conventions across OEM programs.
- Surface warnings without hijacking the entire display (unless safety policy
  requires).

## Typical status items

| Item | Class | Notes |
| --- | --- | --- |
| Clock | Informational | Tabular figures |
| Connectivity (LTE/Wi‑Fi) | Informational | |
| Battery / SOC / charging | Informational / fault | See [EV](ev.md) |
| Outside temperature | Informational | |
| User / profile | Convenience | May gate while driving |
| Active alerts count | Warning | Deep-link to details when allowed |

## States

- Normal
- Warning present
- Charging active
- Offline / signal unavailable ([safety-critical](../compliance/safety-critical.md)
  patterns for unknown)
- Compact vs expanded (program choice)

## Compliance

| Concern | Rule |
| --- | --- |
| Glanceability | `status` type role; short labels ([a11y](../compliance/accessibility-glanceability.md)) |
| Driving | Taps into complex settings restricted |
| Color | Do not rely on color alone for faults |
| Safety | Persistent fault icons remain visible |

## Token dependencies

- `cabin.component.statusBar.height`
- `cabin.component.statusBar.iconSize`
- `cabin.type.role.status.*`
- `cabin.color.semantic.warning` / `error` / `charging`

## Planned Compose API

```kotlin
// Planned
@Composable
fun CabinStatusBar(
    items: List<CabinStatusItem>,
    onItemClick: (CabinStatusItem) -> Unit = {},
    modifier: Modifier = Modifier,
)

sealed interface CabinStatusItem {
    val id: String
    val contentDescription: String
}
```

## Planned Views API

```kotlin
// Planned
class CabinStatusBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun setItems(items: List<CabinStatusItem>) { /* … */ }
}
```

## Parity notes

Item order, overflow behavior, and unavailable-state glyphs must match across
stacks for the same item list fixture.

## Acceptance criteria

- [ ] Exhaustive unavailable/stale rendering for signal-backed items
- [ ] Fault/warning contrast validated day/night
- [ ] Click paths compliance-gated
- [ ] Content descriptions present
- [ ] Compose/Views parity

## Related

- [System bars](system-bars.md)
- [EV](ev.md)
- [Accessibility & glanceability](../compliance/accessibility-glanceability.md)

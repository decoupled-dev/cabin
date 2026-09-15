# EV and energy components

Electric vehicle energy UI: state of charge, range, charging session status,
and energy usage — optimized for glanceability and clear fault presentation.

## Purpose

- Show SOC, range, and charge state without forcing deep navigation.
- Make charging faults and cable state obvious.
- Support OEM brand while locking semantic charging colors’ meaning.

## Component set (planned)

| Component | Role | Safety class |
| --- | --- | --- |
| `EvSocGauge` | Primary SOC visualization | Informational |
| `EvRangeLabel` | Distance estimate | Informational |
| `EvChargeSession` | Plug state, power, time-to-full | Informational / fault |
| `EvChargeFaultBanner` | Charge faults | Safety-adjacent warning |
| `EvEnergyUsage` | Consumption breakdown | Informational (Park-friendly) |
| `EvQuickStatus` | Status bar compact SOC | Informational |

## States

- Idle / driving / charging / charge complete / fault / unavailable
- AC vs DC charging (presentation may differ; tokens shared)
- Target SOC scheduled charging (configuration often Park-only)

## Compliance

| Concern | Rule |
| --- | --- |
| Glance | Large SOC; tabular figures ([a11y](../compliance/accessibility-glanceability.md)) |
| Driving | Defer energy analytics and schedule editing ([driving](../compliance/driving-restrictions.md)) |
| Safety | Fault banners use locked semantic colors; exhaustive signal handling ([safety](../compliance/safety-critical.md)) |
| Contrast | Gauge readable day/night |

## Token dependencies

- `cabin.color.semantic.charging`
- `cabin.component.ev.gauge.size`
- `cabin.type.role.display` / `status`
- Warning/error semantics for faults

## Planned Compose API

```kotlin
// Planned
@Composable
fun EvSocGauge(
    signal: Signal<EvSoc>,
    modifier: Modifier = Modifier,
)

@Composable
fun EvChargeSession(
    session: Signal<ChargeSession>,
    onStopCharge: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
)
```

## Planned Views API

```kotlin
// Planned
class EvSocGaugeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    fun bind(signal: Signal<EvSoc>) { /* … */ }
}
```

## Parity notes

`Signal` degradation (unavailable/stale/fault) must render equivalent copy and
icons on both stacks. Gauge geometry may differ slightly; numeric SOC must not.

## Acceptance criteria

- [ ] SOC/range never show invented values when unavailable
- [ ] Fault banner meets safety patterns
- [ ] Schedule/settings gated while driving
- [ ] Charging semantic color preserved under OEM overlay
- [ ] Dual-stack parity for SOC + fault

## Related

- [Status bars](status-bars.md)
- [Safety-critical](../compliance/safety-critical.md)
- [Tokens](../design-language/tokens.md)

# Spec: ClimateTile

Implementable contract for the Cabin **ClimateTile** — automotive climate
glance + adjustment (temp / fan / seat heat). Not a Material card clone.
Overview: [hvac.md](../hvac.md). Layer 2 domain surface
([features](../../product/features.md)).

## Purpose

One zone’s climate at a glance: temperature, fan level, and seat heat with
large steppers. Restriction Engine gates adjustments while Moving (fail-closed).
Display remains honest under unavailable / stale / fault signals.

## Anatomy

```text
┌──────────────────────────────────────────────┐
│ Zone label                                   │
│  [−]  22°  [+]     Fan  [−]  ■■■□□  [+]     │
│  Seat heat  [1] [2] [3]                      │
└──────────────────────────────────────────────┘
```

| Region | Role |
| --- | --- |
| Zone label | Short ownership (Driver / Passenger / Rear) |
| Temp stepper | ± with tabular numeric display |
| Fan stepper | ± with discrete level glyphs |
| Seat heat | Discrete level cycle / steps (0–max) |

## States

| State | Behavior |
| --- | --- |
| Live | `Signal.Value` for temp / fan / seat heat |
| Unavailable | Show em dash / empty glyphs — **never invent** values |
| Stale | Show last value with stale labeling when product wires it |
| Fault | Fault code presentation; controls non-activatable for that channel |
| Power off | Show off affordance; large target to power on when allowed |
| Restricted (gate) | Adjustments Block → visible, non-activatable |
| Day / Night | Theme Kit scheme; climate accent for domain emphasis only |

## Sizes & type (from tokens)

| Token | Role |
| --- | --- |
| `cabin.component.climateTile.controlMinSize` | 76dp touch minimum |
| `cabin.component.climateTile.gap` | Inter-control gap |
| `cabin.component.climateTile.padding` | Tile inset |
| `cabin.component.climateTile.cornerRadius` | Modest radius |
| `cabin.color.semantic.climate` | Domain accent (not body text) |
| `cabin.color.scheme.*.outline` | Quiet separators |
| `cabin.type.role.title` / `label` | Temp numeral / labels |

Stub: [`tokens/cabin.tokens.json`](../../../tokens/cabin.tokens.json).

Night `warning` / `error` / `charging` stay locked — ClimateTile must not
consume them for decoration.

## Compliance gates

| Interaction | ClimateTile use | Moving / Restricted / Unknown |
| --- | --- | --- |
| `Glance` | Display only | Allow |
| `HvacAdjust` | Temp ±, fan ±, seat heat | **Block** (fail-closed) |
| `HvacPeek` | System bar limited peek only — **not** this tile | Allow (bar matrix) |

Missing `CabinComplianceHost` / `LocalCabinComplianceState` is **fail-closed**
(`GateDisposition.Block`) for adjustments.

Safety class: **Convenience**. Defrost stays on dedicated safety-critical
controls ([safety-critical](../../compliance/safety-critical.md)) — not buried
solely in this tile.

## Views API (Alpha)

```kotlin
class CabinClimateTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun bind(state: CabinClimateTileState) { /* … */ }
    fun setOnActionListener(listener: ((CabinClimateTileAction) -> Unit)?) { /* … */ }
    fun setCompliance(host: CabinComplianceHost?) { /* … */ }
}
```

Package: `dev.decoupled.cabin.views`. Module: `cabin-views` / `CabinViews`.

## Compose API (Experimental)

```kotlin
@Composable
fun CabinClimateTile(
    state: CabinClimateTileState,
    onAction: (CabinClimateTileAction) -> Unit,
    modifier: Modifier = Modifier,
)
```

Package: `dev.decoupled.cabin.compose`. Same gates and Signal honesty as Views;
wrap with `CabinTheme`. **Not** for SystemUI Soong adoption.

## Shared state / actions (parity)

```kotlin
data class CabinClimateTileState(
    val zoneLabel: String,
    val temperatureC: Signal<Int>,
    val fanLevel: Signal<Int>,       // 0..fanMax
    val fanMax: Int = 5,
    val seatHeatLevel: Signal<Int>,  // 0..seatHeatMax
    val seatHeatMax: Int = 3,
    val powerOn: Boolean = true,
)

sealed interface CabinClimateTileAction {
    data object TempUp : CabinClimateTileAction
    data object TempDown : CabinClimateTileAction
    data object FanUp : CabinClimateTileAction
    data object FanDown : CabinClimateTileAction
    data object SeatHeatUp : CabinClimateTileAction
    data object SeatHeatDown : CabinClimateTileAction
}
```

Identical contracts on Compose and Views (duplicated types; same fields).

## Acceptance criteria

- [x] Automotive ClimateTile (not Material Button/List clone)
- [x] Views-first Alpha + Compose Experimental parity
- [x] `HvacAdjust` Block while Moving / Restricted / Unknown; fail-closed null host
- [x] Exhaustive `Signal` for temp / fan / seat heat — no invented values
- [x] 76dp touch minima from tokens; forest/outline Theme Kit; locked night safety unused as chrome
- [x] Unit tests for RE + states on both stacks
- [x] No catalog / marketing / docs-site edits as product deps

## Related

- [HVAC overview](../hvac.md)
- [Restriction states](../../compliance/restriction-states.md)
- [Views](../../platforms/views.md) · [Compose](../../platforms/compose.md)

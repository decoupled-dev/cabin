# Spec: Status Bar (MVP)

Implementable contract for **v0.1** Status Bar. Overview narrative:
[status-bars.md](../status-bars.md). Scope freeze: [mvp.md](../../mvp.md).

## Purpose

Glanceable vehicle/system status: connectivity, clock, battery/SOC, alerts.
Honest unavailable/stale/fault presentation. Deep links gated by Restriction
Engine.

## Anatomy

```text
┌──────────────────────────────────────────────────────────┐
│ 🕒  📶  🔋 80%  72°F                    ⚠ (alerts)       │
└──────────────────────────────────────────────────────────┘
```

Items are an ordered list; overflow policy is program-defined but must not
drop active **fault** affordances.

## States

| State | Behavior |
| --- | --- |
| Normal | Items show live values |
| Warning / fault present | Semantic warning/error tokens; persistent until cleared |
| Charging | Charging accent recognizable |
| Signal unavailable / stale | Explicit degraded UI — never fake “off” |
| Restricted | Settings deep links Block/Substitute per matrix |
| Day / Night | Token schemes |

## Sizes & type (from tokens)

| Token (stub) | Role |
| --- | --- |
| `cabin.component.statusBar.height` | Bar height |
| `cabin.component.statusBar.iconSize` | Icons |
| `cabin.component.statusBar.itemGap` | Gap |
| `cabin.type.role.status` | Status typography |
| `cabin.color.semantic.warning` / `error` / `charging` | Feedback / domain |

## Compliance gates

| Interaction | Gating |
| --- | --- |
| Glance display | Always |
| `StatusDeepLink` informational | Substitute while Moving/Restricted |
| `StatusDeepLink` settings/setup | Block while Moving/Restricted |
| `OpenKeyboard` | Block outside Parked |

Safety class: mostly **Informational**; fault presentation is
safety-adjacent ([safety-critical](../../compliance/safety-critical.md)).

## Planned Views API (MVP primary)

```kotlin
// Planned
class CabinStatusBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun setItems(items: List<CabinStatusItem>) { /* … */ }
    fun setCompliance(host: CabinComplianceHost) { /* … */ }
}

sealed interface CabinStatusItem {
    val id: String
    val contentDescription: String
}

data class CabinStatusGlyph(
    override val id: String,
    override val contentDescription: String,
    val icon: Drawable?,
    val text: CharSequence? = null,
    val signal: Signal<*>? = null,
    val deepLink: StatusDeepLink? = null,
) : CabinStatusItem

data class StatusDeepLink(
    val interaction: CabinInteraction = CabinInteraction.StatusDeepLink,
    val opensSettings: Boolean,
    val onActivate: () -> Unit,
)
```

Soong: part of `CabinViews` (**planned**).

## Planned Compose API (parity later; not MVP-required)

```kotlin
// Planned — experimental until post-MVP
@Composable
fun CabinStatusBar(
    items: List<CabinStatusItem>,
    modifier: Modifier = Modifier,
)
```

## Acceptance criteria (MVP)

- [ ] Exhaustive unavailable/stale/fault rendering for signal-backed items
- [ ] Matrix behavior matches restriction-states for Status Bar
- [ ] Fault/warning contrast day/night
- [ ] Content descriptions present
- [ ] No dependency on `cabin-compose`

## Related

- [System Bar spec](system-bar.md)
- [Restriction states](../../compliance/restriction-states.md)
- [EV](../ev.md) (SOC item semantics; full EV screens post-MVP)

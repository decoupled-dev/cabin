# Spec: ListItem

Implementable contract for Cabin **ListItem** — low-density, restriction-aware
row for Layer 3 Cabin List / Grid ([features](../../product/features.md)).
Overview inventory: [components README](../README.md).

## Purpose

A single collection row sized for cabin density: large touch target, quiet
hierarchy (title primary, supporting recessed), and honest Restriction Engine
gating on activation. Not a phone-density list cell.

## Anatomy

```text
┌──────────────────────────────────────────────────────────┐
│ [leading]   Title                             [trailing] │
│             Supporting (optional)                        │
└──────────────────────────────────────────────────────────┘
```

| Region | Role |
| --- | --- |
| Leading | Optional icon / glyph |
| Title | Primary glance label (`title` / `body` type) |
| Supporting | Optional secondary line (`status` / recessed) |
| Trailing | Optional affordance (chevron, value, IconButton slot) |

## States

| State | Behavior |
| --- | --- |
| Default | Activatable when gate is Allow and `enabled` |
| Selected | Optional outline / container emphasis (token roles only) |
| Disabled (product) | Visible, non-activatable |
| Restricted (gate) | Substitute / Block → keep layout, non-activatable |
| Day / Night | Theme Kit surface / onSurface / outline / container |

## Density

Cabin density is **low**:

- Row `minHeight` ≥ `cabin.size.touch.minimum` (via component token)
- Generous horizontal padding; no dense multi-meta columns in the driver zone
- One primary text column; supporting stays secondary

Passenger / richer density profiles remain a compliance concern — do not bake
phone density into the widget default ([ux-restrictions](../../compliance/ux-restrictions.md)).

## Sizes & type (from tokens)

| Token | Role |
| --- | --- |
| `cabin.component.listItem.minHeight` | Touch / density floor |
| `cabin.component.listItem.horizontalPadding` | Inset |
| `cabin.component.listItem.verticalPadding` | Vertical inset |
| `cabin.component.listItem.gap` | Leading–text / text–trailing gap |
| `cabin.component.listItem.iconSize` | Leading icon |
| `cabin.component.listItem.dividerInset` | Optional divider inset |
| `cabin.type.role.body` / `title` | Title |
| `cabin.type.role.status` | Supporting |
| `cabin.color.scheme.*.surface` / `onSurface` / `outline` / `container` | Chrome |

Stub: [`tokens/cabin.tokens.json`](../../../tokens/cabin.tokens.json).

## Compliance gates

ListItem **must** declare a [CabinInteraction](../../compliance/restriction-states.md)
for activation. Typical mappings:

| Interaction | List use |
| --- | --- |
| `NavigateSimple` | Open a driving-safe destination |
| `OpenComplexApp` | Deep settings / pairing / app entry |
| `FilterOrSort` | Collection controls (usually Parked-only) |
| `OpenKeyboard` | Rows that launch IME |
| `MediaTransport` / `HvacPeek` | Limited domain peeks |

Missing compliance host / local is **fail-closed** (`GateDisposition.Block`).

Widgets must not hardcode Moving/Restricted if/else trees — query
`CabinComplianceHost` / `LocalCabinComplianceState` (ADR 0004).

Safety class: **Convenience** by default.

## Views API (Alpha)

```kotlin
class CabinListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun bind(state: CabinListItemState) { /* … */ }
    fun setCompliance(host: CabinComplianceHost?) { /* … */ }
}
```

Package: `dev.decoupled.cabin.views` in `cabin-views` / `CabinViews`.

## Compose API (Experimental parity)

```kotlin
@Composable
fun CabinListItem(
    state: CabinListItemState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
)
```

Package: `dev.decoupled.cabin.compose`. Same gates and token roles as Views;
no dependency on `cabin-views`.

## Shared state (parity)

```kotlin
data class CabinListItemState(
    val title: String,
    val interaction: CabinInteraction,
    val supportingText: String? = null,
    val leadingIcon: /* Drawable / Painter */? = null,
    val trailingLabel: String? = null,
    val selected: Boolean = false,
    val enabled: Boolean = true,
    val contentDescription: String? = null,
)
```

## Acceptance criteria

- [x] Cabin density: min height from touch token
- [x] Restriction Engine on interactions; fail-closed without host/local
- [x] Theme Kit day/night roles (outline / container / surface)
- [x] Compose + Views parity for states / gating
- [x] No compose→views edge; no `cabin-*` → catalog reverse dep
- [x] No Dialog / Confirm / Toast in this slice

## Related

- [Button / IconButton spec](button.md)
- [Views](../../platforms/views.md)
- [Compose](../../platforms/compose.md)
- [Restriction states](../../compliance/restriction-states.md)

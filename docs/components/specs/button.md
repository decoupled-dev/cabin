# Spec: Button / IconButton (Primary Action)

Implementable contract for Cabin **Button** and **IconButton** primitives —
Layer 3 Primary Action pattern ([features](../../product/features.md)).
Overview inventory: [components README](../README.md).

## Purpose

One obvious, glance-sized action control. Prefer a single primary action
cluster per glance region ([pillars](../../product/pillars.md)). Buttons
declare an interaction class and gate via the Restriction Engine — they do
not embed driving policy.

## Anatomy

### Button

```text
┌──────────────────────────────┐
│  [optional icon]  Label      │
└──────────────────────────────┘
```

### IconButton

```text
┌────────┐
│  icon  │   contentDescription required
└────────┘
```

## Variants

| Variant | Fill / stroke | Content color | Use |
| --- | --- | --- | --- |
| **Filled** | `primary` | `onPrimary` | The one primary action in a cluster |
| **Outlined** | transparent + `outline` stroke | `onSurface` | Secondary / alternate |
| **Quiet** | transparent | `onSurface` | Tertiary; keep quiet |

IconButton uses the same variants with icon-only chrome.

## States

| State | Behavior |
| --- | --- |
| Default | Activatable when gate is Allow |
| Pressed / Focused | Token motion / focus ring; no vanity animation |
| Disabled (product) | Visible, non-activatable (`enabled = false`) |
| Restricted (gate) | Substitute / Block → visible, non-activatable; honest disable look |
| Day / Night | Theme Kit scheme roles; brand via primary overlay only |

Locked safety colors (`warning` / `error` / `charging`) are **not** Button
chrome roles — do not remap them for brand decoration.

## Sizes & type (from tokens)

| Token | Role |
| --- | --- |
| `cabin.component.button.minHeight` | Touch minimum height |
| `cabin.component.button.horizontalPadding` | Horizontal padding |
| `cabin.component.button.iconSize` | Leading / icon glyph |
| `cabin.component.button.gap` | Icon–label gap |
| `cabin.component.button.cornerRadius` | Modest radius |
| `cabin.component.iconButton.minSize` | Icon-only touch square |
| `cabin.component.iconButton.iconSize` | Icon-only glyph |
| `cabin.type.role.label` | Button label |

Stub: [`tokens/cabin.tokens.json`](../../../tokens/cabin.tokens.json).

## Compliance gates

| Interaction (examples) | Typical use |
| --- | --- |
| `NavigateSimple` | Driving-safe primary navigate |
| `MediaTransport` / `HvacPeek` | Limited transport / climate |
| `OpenComplexApp` / `OpenKeyboard` / `FilterOrSort` | Parked-heavy; Block/Substitute while Moving/Restricted |

Safety class: **Convenience** (unless a product wires a safety-critical path —
then follow [safety-critical](../../compliance/safety-critical.md)).

Missing compliance host / local is **fail-closed** (`GateDisposition.Block`).

## Views API (Alpha)

```kotlin
class CabinButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun bind(state: CabinButtonState) { /* … */ }
    fun setCompliance(host: CabinComplianceHost?) { /* … */ }
}

class CabinIconButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    fun bind(state: CabinIconButtonState) { /* … */ }
    fun setCompliance(host: CabinComplianceHost?) { /* … */ }
}
```

Package: `dev.decoupled.cabin.views` in `cabin-views` / `CabinViews`.

## Compose API (Experimental parity)

```kotlin
@Composable
fun CabinButton(
    state: CabinButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
)

@Composable
fun CabinIconButton(
    state: CabinIconButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
)
```

Package: `dev.decoupled.cabin.compose` in `cabin-compose` / `CabinCompose`.
Same Restriction Engine gates and token roles as Views; wrap with `CabinTheme`.
**Must not** depend on `cabin-views`.

## Shared state (parity)

```kotlin
enum class CabinButtonVariant { Filled, Outlined, Quiet }

data class CabinButtonState(
    val label: String,
    val variant: CabinButtonVariant = CabinButtonVariant.Filled,
    val interaction: CabinInteraction,
    val enabled: Boolean = true,
    val icon: /* Drawable / Painter */? = null,
    val contentDescription: String? = null,
)

data class CabinIconButtonState(
    val icon: /* Drawable / Painter */,
    val contentDescription: String,
    val variant: CabinButtonVariant = CabinButtonVariant.Quiet,
    val interaction: CabinInteraction,
    val enabled: Boolean = true,
)
```

## Acceptance criteria

- [x] Views-first in `cabin-views`; Compose parity without compose→views edge
- [x] Touch minima from tokens (≥ cabin touch minimum)
- [x] Day/night via Theme Kit / `CabinTheme` (outline / container / primary)
- [x] Restriction Engine gates activation; fail-closed without host/local
- [x] IconButton requires content description
- [x] No Dialog / Confirm / Toast in this slice
- [x] No catalog / `apps/www` / `website/` dependency from `cabin-*`

## Related

- [ListItem spec](list-item.md)
- [Views](../../platforms/views.md)
- [Compose](../../platforms/compose.md)
- [Restriction states](../../compliance/restriction-states.md)

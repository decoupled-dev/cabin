# Spec: Button

Production visual for the Cabin **Button** primitive — Layer 3 Primary Action
([features](../../product/features.md)). Shared state lives in
`CabinButtonState` / `CabinButtonAction` (foundation). Compose and Views
share variants, touch size, and Restriction Engine gating.

## Purpose

One obvious, glance-sized action. Prefer a single filled button per glance
cluster ([pillars](../../product/pillars.md)). The widget declares
`NavigateSimple` and asks the Restriction Engine — it does not embed driving
policy.

## Anatomy

```text
filled / tonal / outlined / text
┌──────────────────────────────┐
│           Label              │
└──────────────────────────────┘

icon
┌────────┐
│   ＋    │
└────────┘
```

Label is the glance string. The icon variant is the same control with no
fill: a square touch target and a primary glyph. There is no second
IconButton type.

## Variants

Matches the theme-board button styles (`buttonVariantStyle`).

| Variant | Fill | Label | Stroke |
| --- | --- | --- | --- |
| `filled` | `primary` | `onPrimary` | none |
| `tonal` | `primary` at 18% over a raised surface | `onSurface` | none |
| `outlined` | transparent | `onSurface` | 1dp `outline` |
| `text` | transparent | `primary` | none |
| `icon` | transparent | `primary` glyph | none |

Disabled, loading, or a non-Allow gate uses a quiet disabled chrome: surface
fill, muted label, subtle stroke. Focus uses the token focus ring. Error
uses the `error` stroke. Safety colors (`warning`, `error`, `charging`) are
not remapped for brand.

## States

| State | Behavior |
| --- | --- |
| Default | Variant chrome; activation allowed when the gate is Allow |
| Focused | Focus ring from tokens |
| Pressed | Slightly darker fill; still the same variant |
| Disabled / loading | Visible, not activatable |
| Error | Error stroke on the variant (or on disabled chrome) |
| Restricted | Presentation hint; the gate still decides activation |
| Absent compliance host | Fail-closed (`Block`) |

`NavigateSimple` is Allow while moving. A missing compliance host is Block.

## Sizes (tokens)

| Token | Role |
| --- | --- |
| `component.button.minHeight` | Touch floor (cabin minimum, raised by the compliance profile) |
| `component.button.horizontalPadding` | Label inset |
| `component.button.cornerRadius` | Corner (`shape.corner.md`) |
| `component.button.iconSize` | Glyph box for the icon variant |
| `type.role.label` | Label size and weight |

## Compose

```kotlin
@Composable
fun CabinButton(
    state: CabinButtonState = CabinButtonState(),
    onAction: (CabinButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
)
```

Package: `dev.decoupled.cabin.compose.action`. Gate via
`LocalCabinComplianceState`.

## Views

```kotlin
class CabinButtonView : LinearLayout {
    fun bind(state: CabinButtonState)
    fun setOnAction(listener: ((CabinButtonAction) -> Unit)?)
    fun setCompliance(host: CabinComplianceHost?)
}
```

Package: `dev.decoupled.cabin.views.action`. Null host is fail-closed.
Theme colors come from `Theme.Cabin` — no hardcoded hex.

## Acceptance

- Parked (and moving) `NavigateSimple` click emits `Activate`
- Disabled, loading, and a missing compliance host do not emit `Activate`
- Filled / tonal / outlined / text / icon match the theme board
- Min touch is at least the token floor
- Compose and Views share `CabinButtonState`

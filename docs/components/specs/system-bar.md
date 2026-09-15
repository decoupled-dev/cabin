# Spec: System Bar (MVP)

Implementable contract for **v0.1** System Bar. Overview narrative:
[system-bars.md](../system-bars.md). Scope freeze: [mvp.md](../../mvp.md).

## Purpose

Persistent wayfinding chrome: slotted entries with large targets, stable
positions, and restriction-aware activation.

## Anatomy

```text
┌──────────────────────────────────────────────────────────┐
│ [leading slots…]     [center slots…]     [trailing…]     │
└──────────────────────────────────────────────────────────┘
```

| Region | Typical entries |
| --- | --- |
| Leading | Home / map |
| Center | Optional OEM mark or HVAC/media peek |
| Trailing | App grid / notifications (gated) |

Slots are data-driven — OEMs configure without forking the widget
([extension model](../extension-model.md)).

## States

| State | Behavior |
| --- | --- |
| Default | All configured slots visible per policy |
| Restricted / Moving | Complex entries Substitute or Block per [restriction-states](../../compliance/restriction-states.md) |
| Day / Night | Token schemes |
| Focused | Rotary/DPAD focus ring from tokens |
| Disabled item | Visible, non-activatable (when Substitute/Disable) |

## Sizes & type (from tokens)

| Token (stub) | Role |
| --- | --- |
| `cabin.component.systemBar.height` | Bar height |
| `cabin.component.systemBar.iconSize` | Icons |
| `cabin.component.systemBar.itemMinSize` | Touch minimum |
| `cabin.component.systemBar.gap` | Inter-item gap |
| `cabin.type.role.label` | Optional short labels |
| `cabin.color.scheme.*.container` / `onContainer` | Bar chrome fill + content |
| `cabin.color.scheme.*.outline` | Separators / unselected chrome |
| `cabin.color.scheme.*.surface` / `onSurface` | Surrounding surface roles |

Stub file: [`tokens/cabin.tokens.json`](../../../tokens/cabin.tokens.json).
Day/night schemes also carry locked `warning` / `error` / `charging` for
adjacent Status chrome — see [token-schema](../../design-language/token-schema.md).

## Compliance gates

| Interaction | Gating |
| --- | --- |
| `NavigateSimple` | Allow in Moving/Restricted |
| `MediaTransport` / `HvacPeek` | Allow (limited); no deep setup |
| `OpenComplexApp` / `OpenKeyboard` / `FilterOrSort` | Block or Substitute while Moving/Restricted |

Safety class: **Convenience** chrome — do not bury safety-critical controls
exclusively here.

## Planned Views API (MVP primary)

```kotlin
class CabinSystemBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun setSlots(slots: CabinSystemBarSlots) { /* … */ }
    fun setCompliance(host: CabinComplianceHost) { /* … */ }
}
```

Alpha implementation: `dev.decoupled.cabin.views.CabinSystemBarView` in
`cabin-views` / `CabinViews`.

## Planned Compose API (parity later; not MVP-required)

```kotlin
// Planned — experimental until post-MVP
@Composable
fun CabinSystemBar(
    slots: CabinSystemBarSlots,
    modifier: Modifier = Modifier,
)
```

## Acceptance criteria (MVP)

- [x] Slot configuration without core fork
- [x] Matrix behavior matches restriction-states for System Bar
- [x] Touch minima from tokens
- [x] Day/night via Theme Kit
- [x] Content descriptions on icon-only entries
- [x] No dependency on `cabin-compose`

## Related

- [Status Bar spec](status-bar.md)
- [Views](../../platforms/views.md)
- [Build-tree](../../adoption/build-tree.md)

# Tokens

Tokens are the stable contract between design, Compose, Views, and OEM brand
systems. Cabin uses **semantic tokens** (intent) and **component tokens**
(localized decisions), with OEM overlays that remap without forking.

## Goals

- One token source for both UI stacks ([architecture](../architecture.md)).
- Brand customization without editing component source
  ([extension model](../components/extension-model.md)).
- Validation hooks for contrast, min sizes, and safety color locks.

## Token layers

```text
Reference (raw palettes, type ramps)
        ▼
Semantic (primary, surface, space.md, motion.fast, …)
        ▼
Component (button.padding.x, statusBar.iconSize, hvac.zoneGap, …)
```

Components consume **component** and **semantic** tokens — never raw reference
hex values.

## Planned naming

```text
cabin.color.semantic.primary
cabin.color.semantic.onPrimary
cabin.color.semantic.charging
cabin.color.scheme.night.surface
cabin.type.role.status.size
cabin.size.touch.minimum
cabin.space.md
cabin.motion.fast.duration
cabin.component.media.transport.size
cabin.component.hvac.zone.gap
```

Exact schema ships in Phase 2 (`cabin-tokens`). Formats under consideration:
Kotlin objects, Android resources generated from a single YAML/JSON source, or
both.

## Semantic tokens (categories)

| Category | Examples |
| --- | --- |
| Color | brand, surface, domain accents, feedback |
| Typography | roles, weights, line heights |
| Spacing | density scale |
| Sizing | touch minima, icon sizes |
| Motion | duration, easing |
| Opacity | scrims, disabled |
| Shape | corner radii (cabin tends toward modest radii) |

## Component tokens

Component tokens bind semantics to a widget without leaking brand decisions
into Kotlin/XML repeatedly:

```text
cabin.component.button.primary.containerColor → semantic.primary
cabin.component.button.primary.minSize → size.touch.minimum
cabin.component.status.warning.iconColor → semantic.warning
```

## OEM theming without forks

```text
Cabin baseline tokens
        +
OEM overlay (brand primary, type family, radius)
        −
Locked safety semantics (not overridable or only with explicit break-glass)
        =
Product theme
```

### Planned overlay sketch

```kotlin
// Planned
object OemBrandOverlay : CabinTokenOverlay {
    override val primary = Color(0xFF0B6E4F) // example only
    override val typographyFamily = CabinFontFamily.OemSans
    // cannot override semantic.error meaning
}
```

Views (**planned**): generate `values-oem/` overlays or runtime `CabinTokenBridge`.

## Dual-stack resolution

| Stack | Resolution |
| --- | --- |
| Compose | `CabinTheme.tokens` / composition locals |
| Views | Theme attributes + resource lookups generated from the same source |

Parity tests assert identical resolved values for a fixture overlay.

## Anti-bloat

- Apps depend on `cabin-tokens` (small) even if they only need colors for a
  custom screen.
- Do not ship full reference palettes for unused OEM brands in the default
  artifact — overlays are product-owned.

## Acceptance criteria (Phase 2)

- [x] Single source generates Compose, Views, and CSS consumables
- [ ] Overlay mechanism documented and sampled
- [x] Safety color lock enforced in codegen validation
- [x] Day/night schemes complete for core roles

## Related

- [Foundations](foundations.md)
- [Architecture](../architecture.md)
- [Packaging](../adoption/packaging.md)
- [Extension model](../components/extension-model.md)

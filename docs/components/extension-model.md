# Extension model

OEMs and Tier-1s will always need product-specific screens and controls. Cabin
supports extension **without forking** core libraries: brand through tokens,
add surfaces through documented slots and composition.

## Goals

- Keep Cabin core stable across programs.
- Allow deep brand and feature differentiation.
- Preserve compliance and token discipline on custom surfaces.

## Extension layers

```text
1. Token overlay          — brand colors, type, shape
2. Component slots        — plug OEM content into chrome
3. Composed screens       — build screens from Cabin primitives
4. Custom components      — new widgets using tokens + compliance
5. Policy overlay         — tighten driving/UX rules
```

Forking Cabin core is a last resort; prefer layers 1–5.

## 1. Token overlay

See [tokens](../design-language/tokens.md). Overlays remap brand-facing
semantics; safety meanings stay locked.

## 2. Component slots

Chrome components expose slots:

| Host | Slot examples |
| --- | --- |
| [System bars](system-bars.md) | Quick controls, OEM app entry |
| [Status bars](status-bars.md) | Custom status items |
| [Media](media.md) | Source switcher extras |
| [Vehicle controls](vehicle-controls.md) | OEM control tiles |

### Planned slot API sketch

```kotlin
// Planned
data class CabinSystemBarSlots(
    val leading: List<SystemBarEntry>,
    val center: List<SystemBarEntry> = emptyList(),
    val trailing: List<SystemBarEntry>,
)
```

## 3. Composed screens

Product apps assemble HVAC + EV + media using Cabin components inside OEM
navigation. Screens should:

- Wrap with `CabinTheme` / Views theme overlay
- Read `CabinCompliance` before enabling interactions
- Use semantic type roles and spacing tokens

## 4. Custom components

When inventing a new control:

1. Classify safety level.
2. Consume tokens only (no hardcoding).
3. Declare `CabinInteraction`s for gating.
4. Implement Compose and Views **or** document single-stack exception with
   maintainer approval.
5. Contribute upstream if the control is generic.

### Checklist

- [ ] Tokens only
- [ ] Compliance hooks
- [ ] Day/night
- [ ] Touch minima
- [ ] Signal unavailable handling
- [ ] Parity plan

## 5. Policy overlay

Programs inject stricter `DrivingRestrictionPolicy` / UX profiles
([compliance](../compliance/README.md), [architecture](../architecture.md)).
Loosening Cabin safety defaults requires explicit break-glass review.

## Packaging custom code

Keep OEM extensions in **product modules**, not patches to Cabin AARs:

```text
oem-product/
  oem-tokens-overlay/
  oem-system-ui/          // depends on cabin-views
  oem-climate-app/        // depends on cabin-compose
```

See [packaging](../adoption/packaging.md).

## Anti-patterns

- Copy-pasting Cabin components into OEM repos “for small tweaks”
- Remapping `error`/`warning` to brand colors
- Compose-only custom safety controls without Views equivalent when system UI
  needs them
- Bypassing compliance locals/hosts to “make the demo work”

## Related

- [Tokens](../design-language/tokens.md)
- [Architecture](../architecture.md)
- [Adoption integration](../adoption/integration.md)
- [Contributing](../contributing.md)

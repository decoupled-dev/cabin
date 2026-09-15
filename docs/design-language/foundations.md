# Foundations

Cabin foundations define the raw design decisions that tokens package for
implementation.

## Color

### Roles (planned semantic set)

| Role | Purpose |
| --- | --- |
| `primary` / `onPrimary` | Brand actions |
| `secondary` / `onSecondary` | Secondary actions |
| `surface` / `onSurface` | App backgrounds and text |
| `surfaceVariant` | Recessed panels |
| `outline` | Separators, unselected chrome |
| `success` / `warning` / `error` | Feedback (error/warning partially safety-locked) |
| `charging` / `climate` / `mediaAccent` | Domain accents |
| `scrim` | Content protection over imagery |

### Cabin-specific rules

- Maintain **day** and **night** schemes ([UX restrictions](../compliance/ux-restrictions.md)).
- Domain accents (`charging`, `climate`) stay recognizable under OEM overlays.
- Safety-adjacent warning/error meanings must not be remapped to brand pinks
  for decoration ([safety-critical](../compliance/safety-critical.md)).
- Prefer muted large fields at night; reserve high luminance for signals.

## Typography

See glanceability roles in
[accessibility & glanceability](../compliance/accessibility-glanceability.md).

Additional foundation rules:

- Default family: a highly legible grotesque suitable for automotive distance
  (exact font choice is OEM-overridable via tokens).
- Tabular figures for range, speed, SOC, temperature.
- Avoid italic for critical status.

## Spacing

| Token step (planned) | Example use |
| --- | --- |
| `space.xs` | Icon-text gaps |
| `space.sm` | Dense chip padding |
| `space.md` | Default control padding |
| `space.lg` | Section gaps |
| `space.xl` | Driver-zone separation |

Spacing must preserve touch target minima
([UX restrictions](../compliance/ux-restrictions.md)).

## Elevation and layering

Cabin uses elevation sparingly — cabin UIs often prefer flat, high-contrast
layers over deep shadow stacks (which wash out at night).

| Level | Use |
| --- | --- |
| 0 | Base surface |
| 1 | Recessed well / inset |
| 2 | Floating controls (rare) |
| Alert | Fault banners above content regardless of level |

## Motion

| Token (planned) | Duration guidance |
| --- | --- |
| `motion.fast` | Press feedback |
| `motion.medium` | Panel transitions (Park) |
| `motion.slow` | Rare; avoid while driving |

Easing should feel mechanical/precise rather than playful. Driving state
reduces or disables decorative motion
([driving restrictions](../compliance/driving-restrictions.md)).

## Iconography

- Prefer simple geometric glyphs optimized for distance.
- Optical sizing for status bar vs. center-stack vs. cluster.
- Pair icons with text for safety-critical and first-run actions.
- OEM icon packs map to Cabin icon slots; do not replace semantic meaning.

## Sound and haptics (out of scope for Phase 1 UI tokens)

Documented as future extension points; not required for token v1.

## Dual-stack application

Foundations are expressed only through tokens in product code — no
hardcoded hex/sp in components. Compose and Views both resolve tokens at
theme inflation time.

## Related

- [Tokens](tokens.md)
- [Design language overview](README.md)
- [Compliance](../compliance/README.md)

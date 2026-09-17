# Design language

Cabin’s design language is the visual and interaction vocabulary for AAOS cabin
HMI. It is documented for humans and encoded as tokens for both Compose and
Views.

## Goals

- Feel purpose-built for the cabin — not a phone theme on a large display.
- Support OEM brand expression without forking components
  ([tokens](tokens.md)).
- Encode glanceability, day/night, and safety semantics
  ([compliance](../compliance/README.md)).
- Stay learnable for Android developers familiar with Material concepts,
  without being Material-identical ([vision](../vision.md)).

## Pillars

| Pillar | Doc |
| --- | --- |
| Foundations | [foundations.md](foundations.md) — color, type, space, elevation, motion, iconography |
| Tokens | [tokens.md](tokens.md) — semantic + component tokens, OEM overlays |
| Token schema | [token-schema.md](token-schema.md) — JSON stub + codegen notes |
| Token codegen | [token-codegen.md](token-codegen.md) — Android + Compose + CSS + CI drift |

## Relationship to Material Design 3

Cabin may parallel Material roles (primary, surface, on-surface) where that
aids adoption, but:

- Sizing, type scale, and motion budgets are automotive-first.
- Safety semantic colors are locked against decorative remapping.
- Components cover cabin domains (HVAC, EV, vehicle controls, system chrome)
  absent from handheld Material catalogs.

## Dual-stack encoding

| Concern | Compose (planned) | Views (planned) |
| --- | --- | --- |
| Theme | `CabinTheme` + `CabinTokens` | Theme overlays + attr references |
| Color | `CabinColorScheme` | Color resources / color state lists |
| Type | `CabinTypography` | TextAppearances |
| Space | token dp via theme | dimen resources |

Single source of truth: `cabin-tokens` ([architecture](../architecture.md)).

## Public site mapping

Foundations and styles on the future website map directly to these docs
([site plan](../website/site-plan.md)).

Machine-readable stub: [`tokens/cabin.tokens.json`](../../tokens/cabin.tokens.json)
— see [token schema](token-schema.md).

## Related

- [Foundations](foundations.md)
- [Tokens](tokens.md)
- [Token schema](token-schema.md)
- [Components](../components/README.md)

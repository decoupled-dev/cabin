# Architecture

Planned multi-module architecture for Cabin. Nothing below is published yet;
coordinates and module names are **planned** contracts for implementers.

## Goals

- Dual-stack UI without duplicating tokens or compliance.
- OEM theming without forking.
- Minimal footprint in AAOS images and app APKs.
- Clear dependency direction (tokens/compliance → UI → samples).

## Planned repository layout

```text
cabin/                          # multi-project Gradle root (future)
├── cabin-tokens/               # design tokens only
├── cabin-compliance/           # driving / UX / a11y / safety policies
├── cabin-compose/              # Jetpack Compose components + theme
├── cabin-views/                # View/XML components + attrs
├── samples/
│   ├── sample-media/
│   ├── sample-ev/
│   ├── sample-hvac/
│   └── sample-vehicle-controls/
├── catalog/                    # interactive component browser
├── website/                    # public docs site
└── docs/                       # this documentation set (present now)
```

## Planned artifacts

| Module | Planned coordinate | Depends on | Contains |
| --- | --- | --- | --- |
| `cabin-tokens` | `dev.decoupled.cabin:cabin-tokens` | — | Color, type, space, elevation, motion, icon semantic tokens |
| `cabin-compliance` | `dev.decoupled.cabin:cabin-compliance` | `cabin-tokens` (optional metadata only) | Driving gates, UX rules, a11y baselines, safety defaults |
| `cabin-compose` | `dev.decoupled.cabin:cabin-compose` | `cabin-tokens`, `cabin-compliance` | Compose theme + components |
| `cabin-views` | `dev.decoupled.cabin:cabin-views` | `cabin-tokens`, `cabin-compliance` | Views theme + components |

Samples, catalog, and website are **not** published as runtime AARs for
product images.

## Dependency rules

```text
                    ┌─────────────────┐
                    │  cabin-tokens   │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │cabin-compliance │
                    └────────┬────────┘
              ┌──────────────┴──────────────┐
              ▼                             ▼
     ┌────────────────┐            ┌────────────────┐
     │ cabin-compose  │            │  cabin-views   │
     └────────┬───────┘            └────────┬───────┘
              │                             │
              └──────────────┬──────────────┘
                             ▼
                      samples / catalog
```

**Hard rules (planned lint / module checks):**

1. `cabin-tokens` must not depend on Compose, AppCompat, or Material.
2. `cabin-compliance` must not depend on Compose UI or View widgets.
3. `cabin-compose` must not depend on `cabin-views`, and vice versa.
4. OEM brand overlays depend on tokens (+ optional compliance), never on
   samples.
5. Apps may depend on one UI stack only.

## Extension and theming points

### Token overlay

OEMs supply a brand overlay that remaps semantic tokens
([tokens](design-language/tokens.md)):

```kotlin
// Planned API sketch — not implemented
CabinTheme(
    tokens = CabinTokens.fromOemOverlay(OemBrandTokens),
    compliance = CabinCompliance.fromConfig(productConfig),
) {
    MediaNowPlaying(/* … */)
}
```

### Compliance policy injection

Driving and UX policies are injectable so programs can tighten baselines
without forking components ([compliance](compliance/README.md)):

```kotlin
// Planned
interface DrivingRestrictionPolicy {
    fun isInteractionAllowed(interaction: CabinInteraction, state: VehicleUiState): Boolean
}
```

### Component slots / extension screens

Product-specific surfaces compose Cabin primitives and register with the
extension model ([extension model](components/extension-model.md)).

## Anti-bloat packaging

| Technique | Intent |
| --- | --- |
| Split AARs | Adopt Compose without Views (or reverse) |
| Token-only apps | Headless theming / cross-stack consistency checks |
| No transitive Material forcing | Where Cabin wraps Material, keep optional; do not force full M3 into system UI |
| Explicit feature modules in samples | Demonstrate selective adoption |
| R8 / consumer ProGuard rules (planned) | Keep public API surface tight |

See [packaging](adoption/packaging.md).

## Parity contracts

Each component specification lists:

- States and state transitions
- Minimum touch targets and typography roles
- Compliance hooks (which gates apply)
- Compose API shape (**planned**)
- Views API shape (**planned**)

Catalog and screenshot tests (later phases) enforce parity.

## Runtime integration sketch (AAOS)

```text
┌──────────────────────────────────────────────┐
│                 System UI / Apps             │
│  ┌─────────────┐  ┌─────────────┐            │
│  │ cabin-views │  │cabin-compose│            │
│  └──────┬──────┘  └──────┬──────┘            │
│         └────────┬───────┘                   │
│           cabin-compliance                   │
│           cabin-tokens                       │
│                  │                           │
│         Vehicle signals / Car APIs           │
└──────────────────────────────────────────────┘
```

Cabin does not replace Car framework APIs; it consumes vehicle/UI state
adapters supplied by the product.

## Related

- [Principles](principles.md)
- [Design language](design-language/README.md)
- [Platforms: Compose](platforms/compose.md) · [Views](platforms/views.md)
- [Adoption: packaging](adoption/packaging.md)
- [Roadmap](roadmap.md)

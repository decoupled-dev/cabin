# Architecture

Planned multi-module architecture for Cabin. Nothing below is published yet;
coordinates and module names are **planned** contracts for implementers.

## Goals

- Dual-stack UI without duplicating tokens or compliance.
- **Dual build systems** — same sources via Gradle (Maven) and Soong
  (`Android.bp`) for build-tree apps ([build-tree](adoption/build-tree.md)).
- OEM theming without forking (apps: overlays; platform: RROs).
- Minimal footprint in AAOS images and app APKs.
- Clear dependency direction (tokens/compliance → UI → samples).
- **Views-first for platform** — SystemUI adopts `cabin-views` / `CabinViews`;
  Compose is not a gate for chrome.

## Planned repository layout

```text
cabin/                          # synced to Android tree via repo manifest (future)
├── cabin-tokens/               # design tokens only
│   ├── build.gradle.kts        # Maven (apps)
│   └── Android.bp              # Soong (platform) — planned
├── cabin-compliance/           # driving / UX / a11y / safety policies
│   ├── build.gradle.kts
│   └── Android.bp              # planned
├── cabin-compose/              # Jetpack Compose components + theme
│   ├── build.gradle.kts
│   └── Android.bp              # planned; opt-in on platform
├── cabin-views/                # View/XML components + attrs (primary for SystemUI)
│   ├── build.gradle.kts
│   └── Android.bp              # planned
├── samples/
│   ├── sample-media/
│   ├── sample-ev/
│   ├── sample-hvac/
│   └── sample-vehicle-controls/
├── catalog/                    # thin chrome catalog sample (not product dep)
├── website/                    # public docs site
└── docs/                       # this documentation set (present now)
```

## Dual consumption

| Path | Artifact | Primary consumers |
| --- | --- | --- |
| **Gradle / Maven** | `dev.decoupled.cabin:cabin-*` (**planned**) | App developers |
| **Soong** | `CabinTokens`, `CabinCompliance`, `CabinViews`, `CabinCompose` (**planned**) | SystemUI, CarLauncher, platform media |

Same Kotlin/Java/resources; two build graphs. Prefer source-in-tree Soong for
SystemUI; AAR prebuilts are secondary ([build-tree](adoption/build-tree.md)).

## Planned artifacts

| Module | Planned Maven coordinate | Planned Soong name | Depends on | Contains |
| --- | --- | --- | --- | --- |
| `cabin-tokens` | `dev.decoupled.cabin:cabin-tokens` | `CabinTokens` | — | Color, type, space, elevation, motion, icon semantic tokens |
| `cabin-compliance` | `dev.decoupled.cabin:cabin-compliance` | `CabinCompliance` | tokens | Driving gates, UX rules, a11y baselines, safety defaults |
| `cabin-compose` | `dev.decoupled.cabin:cabin-compose` | `CabinCompose` | tokens, compliance | Compose theme + components |
| `cabin-views` | `dev.decoupled.cabin:cabin-views` | `CabinViews` | tokens, compliance | Views theme + components |

Samples, catalog, and website are **not** published as runtime AARs and are
**not** Soong deps for SystemUI.

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

1. `cabin-tokens` / `CabinTokens` must not depend on Compose, AppCompat, or Material.
2. `cabin-compliance` / `CabinCompliance` must not depend on Compose UI or View widgets.
3. `cabin-compose` must not depend on `cabin-views`, and vice versa (same for Soong names).
4. OEM brand overlays / RROs depend on tokens (+ optional compliance), never on
   samples.
5. Apps may depend on one UI stack only.
6. SystemUI Soong deps must not include `CabinCompose`, catalog, or samples.

## Extension and theming points

### Token overlay

OEMs supply a brand overlay that remaps semantic tokens
([tokens](design-language/tokens.md)):

```kotlin
// Planned API sketch — not implemented (Gradle / Compose apps)
CabinTheme(
    tokens = CabinTokens.fromOemOverlay(OemBrandTokens),
    compliance = CabinCompliance.fromConfig(productConfig),
) {
    MediaNowPlaying(/* … */)
}
```

On platform images, prefer **RROs** and optional Soong token overlay modules
([build-tree](adoption/build-tree.md)).
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
| Split AARs / Soong modules | Adopt Compose without Views (or reverse) |
| Views-only SystemUI | Platform chrome never pulls Compose |
| Token-only apps | Headless theming / cross-stack consistency checks |
| No transitive Material forcing | Where Cabin wraps Material, keep optional; do not force full M3 into system UI |
| Explicit feature modules in samples | Demonstrate selective adoption |
| R8 / consumer ProGuard rules (planned) | Keep public API surface tight |

See [packaging](adoption/packaging.md) and [build-tree](adoption/build-tree.md).

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
┌────────────────────────────────────────────────────────────┐
│                 Build-tree (Soong)      │  Apps (Gradle)   │
│  SystemUI / Launcher / platform media   │  Feature APKs    │
│  ┌─────────────┐                        │ ┌─────────────┐  │
│  │ CabinViews  │  (Compose opt-in only) │ │cabin-compose│  │
│  └──────┬──────┘                        │ │ cabin-views │  │
│         │                               │ └──────┬──────┘  │
│  CabinCompliance + CabinTokens  ◄───────────────┘         │
│                  │                                         │
│         Vehicle signals / Car APIs                         │
└────────────────────────────────────────────────────────────┘
```

Cabin does not replace Car framework APIs; it consumes vehicle/UI state
adapters supplied by the product. Platform consumption mirrors familiar
Soong library patterns; it is not a replacement claim for `car-ui-lib` or Car
services ([build-tree](adoption/build-tree.md)).

## Related

- [Principles](principles.md)
- [Design language](design-language/README.md)
- [Platforms: Compose](platforms/compose.md) · [Views](platforms/views.md) · [Soong](platforms/soong.md)
- [Adoption: packaging](adoption/packaging.md) · [build-tree](adoption/build-tree.md)
- [Roadmap](roadmap.md)

# API contracts (planned)

Frozen naming targets for **v0.1 MVP** and forward. Nothing here is published
until implementation lands.

## Maven coordinates (Gradle apps)

Group: `dev.decoupled.cabin` (illustrative; finalize at first publish).

| Artifact | Coordinate | MVP |
| --- | --- | --- |
| Tokens | `dev.decoupled.cabin:cabin-tokens` | **Yes** |
| Compliance | `dev.decoupled.cabin:cabin-compliance` | **Yes** |
| Foundation | `dev.decoupled.cabin:cabin-foundation` | Post-MVP (**Experimental** shared State/Action + size classes) |
| Views | `dev.decoupled.cabin:cabin-views` | **Yes** (Theme Kit + System/Status bars Alpha) |
| Compose | `dev.decoupled.cabin:cabin-compose` | Post-MVP (**Experimental** Theme + bars + kit scaffold) |
| Gauges | `dev.decoupled.cabin:cabin-gauges` | Post-MVP (**Experimental** cluster stubs; never SystemUI) |

## Soong module names (build-tree)

| Module | MVP SystemUI | In-repo scaffolding |
| --- | --- | --- |
| `CabinTokens` | Required | `cabin-tokens/Android.bp` |
| `CabinCompliance` | Required | `cabin-compliance/Android.bp` |
| `CabinFoundation` | Transitive via Views | `cabin-foundation/Android.bp` |
| `CabinViews` | Required | `cabin-views/Android.bp` |
| `CabinCompose` | **Do not** depend from SystemUI | `cabin-compose/Android.bp` (opt-in) |
| `CabinGauges` | **Do not** depend from SystemUI | `cabin-gauges/Android.bp` (opt-in) |

Shared defaults: root `Android.bp` → `CabinAndroidLibraryDefaults` (not an
umbrella library). SystemUI wiring sketch:
[sketches/systemui-cabin](adoption/sketches/systemui-cabin/).

See [build-tree](adoption/build-tree.md).

## Kotlin / Java package roots (planned)

| Module | Package root |
| --- | --- |
| `cabin-tokens` | `dev.decoupled.cabin.tokens` |
| `cabin-compliance` | `dev.decoupled.cabin.compliance` |
| `cabin-foundation` | `dev.decoupled.cabin.foundation` |
| `cabin-views` | `dev.decoupled.cabin.views` |
| `cabin-compose` | `dev.decoupled.cabin.compose` |
| `cabin-gauges` | `dev.decoupled.cabin.gauges` |

Views Theme Kit types: `dev.decoupled.cabin.views.theme.CabinThemes`,
`…CabinThemeResolver`. Views chrome: `…CabinSystemBarView`,
`…CabinStatusBarView` (Alpha). Domain: `…CabinClimateTileView`,
`…CabinMediaNowPlayingView` (Alpha).

Compose Theme + bars (Experimental): `dev.decoupled.cabin.compose.theme.CabinTheme`,
`…CabinSystemBar`, `…CabinStatusBar`. Domain (Experimental): `…CabinClimateTile`,
`…CabinMediaNowPlaying`.

## Stability

| Label | Meaning |
| --- | --- |
| **Experimental** | May change without notice; Compose bar parity starts here |
| **Alpha / Beta** | Tracking to stable; migration notes required |
| **Stable** | SemVer; tests green; pillars satisfied |

v0.1 library drop may ship Tokens + Compliance + Views bars as **Alpha**.

## SemVer policy (sketch)

- **MAJOR** — breaking token renames, removed public types, Soong name changes
- **MINOR** — additive components/APIs, new optional interactions
- **PATCH** — bugfixes, doc-equivalent behavior fixes

Token renames are high-cost; prefer additive aliases then deprecate.
Platform pins Cabin by manifest **tag/revision**, not floating main
([soong](platforms/soong.md)).

## Dependency rules (contract)

```text
cabin-tokens
     ▲
cabin-compliance
     ▲
cabin-foundation     (Experimental size classes + shared State/Action)
     ▲
cabin-views     cabin-compose     cabin-gauges (opt-in; never SystemUI)
     (Views Alpha bars + ClimateTile/MediaNowPlaying + build-tree subset;
      Compose Experimental kit scaffold)
```

Theme Kit Alpha in `cabin-views` depends on **tokens only**; bars and domain
tiles also wire `cabin-compliance`. Compose Theme + components depend on tokens
+ compliance and must not depend on `cabin-views`.

- Tokens & compliance: **no** UI toolkit deps
- Foundation: **no** Compose or Views widgets
- Views ⊀ Compose and Compose ⊀ Views
- Gauges must not be linked from SystemUI
- SystemUI static_libs: tokens + compliance + views only (never `CabinCompose` / `CabinGauges`)

## Related

- [MVP](mvp.md)
- [Architecture](architecture.md)
- [Packaging](adoption/packaging.md)
- [Support matrix](support-matrix.md)

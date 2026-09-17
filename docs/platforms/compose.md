# Compose platform

Guidelines for implementing and consuming Cabin on Jetpack Compose, including
**parity contracts** with the Views stack.

## Role of Compose in Cabin

Compose is the preferred toolkit for **application** surfaces: media, EV, HVAC
apps, settings screens, and OEM feature apps. System UI and build-tree
components may still require Views ([views](views.md)); Cabin treats both as
first-class.

Compose bar parity is **Experimental** and does **not** replace Views for
SystemUI Soong adoption ([ADR 0002](../adr/0002-views-first-platform.md)).

## Artifact

```text
dev.decoupled.cabin:cabin-compose:<version>
```

| Path | Identifier | Status |
| --- | --- | --- |
| Maven (Gradle apps) | `dev.decoupled.cabin:cabin-compose` | **Experimental** (Theme + System/Status bars) |
| Soong | `CabinCompose` | Alpha scaffolding — **do not** link from SystemUI |

Depends on `cabin-tokens` and `cabin-compliance` ([architecture](../architecture.md)).
Must **not** depend on `cabin-views`.

## Theme

Compose Theme Kit mirror — same token **roles** as Views Theme Kit (day/night
`outline` / `container` / safety feedback), resolved from `CabinTokens` into
Compose `Color` values (no Android Views theme attrs).

```kotlin
@Composable
fun CabinTheme(
    colorScheme: CabinColorScheme = /* DayNight from UiMode */,
    brand: CabinBrandOverrides = CabinBrandOverrides.None,
    compliance: CabinCompliance = CabinRestrictionEngine.Default,
    vehicleState: VehicleUiState = VehicleUiState.unknown(),
    content: @Composable () -> Unit,
)
```

- Provide colors + compliance via composition locals
  (`LocalCabinColors`, `LocalCabinComplianceState`).
- OEM brand via [CabinBrandOverrides] (primary / onPrimary) — do not remap
  warning / error / charging for decoration ([ADR 0003](../adr/0003-tokens-via-overlay-rro.md)).
- Missing compliance local is **fail-closed** (`GateDisposition.Block`).

## System Bar + Status Bar (Experimental)

Parity with Views Alpha chrome ([system-bar](../components/specs/system-bar.md),
[status-bar](../components/specs/status-bar.md)):

```kotlin
@Composable
fun CabinSystemBar(slots: CabinSystemBarSlots, modifier: Modifier = Modifier)

@Composable
fun CabinStatusBar(items: List<CabinStatusItem>, modifier: Modifier = Modifier)
```

Package: `dev.decoupled.cabin.compose`. Same Restriction Engine gates, Signal
exhaustiveness, and tone roles as Views — API shape differs (`Composable` vs
`View`).

## Implementation guidelines

Aligned with modern Compose practice and
[android/skills](https://github.com/android/skills) patterns:

| Topic | Cabin rule |
| --- | --- |
| State | UDF; hoist vehicle signals; exhaustive `Signal` handling |
| Stability | Prefer immutable state models; avoid unstable collections in public APIs |
| Modifiers | Public components accept `Modifier` as first optional param after required state |
| Theming | Colors/type/space from tokens only |
| Accessibility | Semantics and content descriptions mandatory for icon-only controls |
| Performance | Avoid expensive work in composition; large gauges use draw-efficient paths |
| Testing | Behavior + gate/Signal/tone parity with Views; screenshot later |

## Compliance wiring

```kotlin
CabinTheme(vehicleState = adapterState) {
    CabinSystemBar(slots = …)
    CabinStatusBar(items = …)
}
```

Driving and UX gates are not optional “if the app remembers.” Absent
`CabinTheme` / compliance local → fail-closed Block.

## Parity contracts with Views

For each stable component:

| Contract item | Requirement |
| --- | --- |
| State model | Same fields and enums (shared or mirrored) |
| Actions | Same action sealed types / IDs |
| Min sizes | Same token-resolved dp |
| Gating | Same allow/deny for a `VehicleUiState` fixture |
| Unavailable UI | Same copy keys / icon slots |
| Day/night | Same scheme roles |

API **shape** differs (`Composable` vs `View`); behavior must not.

Views System/Status bars are **Alpha**. Compose counterparts are
**Experimental** until parity fixtures harden further.

## Interop

- Embedding Compose in View system UI: use `ComposeView` with Cabin theme
  wrappers; still prefer Views for critical system chrome when product
  mandates.
- Embedding Views in Compose: `AndroidView` with Cabin View themes when
  reusing legacy widgets.
- Never create a Gradle/Soong cycle between `cabin-compose` and `cabin-views`.

## Do not

- Depend on `cabin-views` from `cabin-compose`.
- Ship sample-only dependencies into the AAR.
- Force Compose into SystemUI `static_libs`.
- Bypass compliance locals in “debug” builds without a clear debug policy API.

## Related

- [Views](views.md)
- [Architecture](../architecture.md)
- [Theme Kit](../adoption/theme-kit.md)
- [Components](../components/README.md)
- [Principles](../principles.md)

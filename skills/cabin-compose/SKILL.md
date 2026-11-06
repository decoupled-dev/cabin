---
name: cabin-compose
description: >-
  Implement Cabin Jetpack Compose UI — CabinTheme, token usage, compliance
  locals, UDF, and parity contracts with Views. Use when writing Compose
  components, app screens, or Maven cabin-compose consumers — not for SystemUI
  Soong adoption (use cabin-views and cabin-soong instead).
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin Compose

## When to use Compose

- Gradle/Maven feature apps and media experiences
- Platform apps that **already** use Compose

**Not** a prerequisite for SystemUI — use Views + Soong there.

## Artifact

`dev.decoupled.cabin:cabin-compose` → depends on tokens + compliance.
Must **not** depend on `cabin-views`.

**Status:** Experimental Theme + System/Status bars. Views bars remain Alpha
and the SystemUI path.

## v0.2 kit (ADR 0006) — use this for new app UI

```kotlin
CabinTheme(look = CabinLook.Quiet, colorScheme = scheme, vehicleState = state, fonts = myFonts) {
    CabinScreenFrame(status, climate, onClimate, CabinDestinations.defaultDock(), dest, onDest) {
        CabinHomeSplitScreen(media, onMedia, energy, quickControls, onQuick, nextTurn = turn)
    }
}
```

- **Theme:** `CabinTheme(look, colorScheme, brand, fonts, compliance, vehicleState)`
  builds a Material 3 `MaterialTheme`. Read car extensions with
  `CabinTheme.look` (`colors`, `shapes`, `type`, `activeStyle`, `edge`,
  `labelText()`) and `CabinTheme.compliance`.
- **Looks:** `CabinLook.{Quiet (default), Precision, Warm, Glass, Graphic, Mono}`.
  Safety colors (`warning`, `error`, `charging`) are identical in every look.
- **Fonts:** libraries ship none. Pass a `CabinFontResolver`
  (`CabinFonts.of(mapOf("Manrope" to family))`); default is platform families.
- **Kit (`dev.decoupled.cabin.compose.kit`):** `CabinPanel`, `CabinBackdrop`,
  `CabinButton`, `CabinIconButton`, `CabinToggleButton`, `CabinSegmentedControl`,
  `CabinStepper`, `CabinProgressBar`, `CabinLevelDots`, `CabinRestrictedHint`,
  `CabinIcons`; cards `CabinNowPlayingCard`, `CabinNextTurnCard`, `CabinEtaChip`,
  `CabinEnergyCard`, `CabinQuickControlTile`, `CabinZoneTemperature`,
  `CabinSeatHeatButton`, `CabinFanSelector`, `CabinStatusRow`, `CabinDock`.
- **Every activating control takes a required `interaction: CabinInteraction`.**
  Pick the narrowest: `MediaTransport`, `MediaComplex`, `HvacQuick` (single-tap
  climate), `HvacAdjust` (multi-step), `VehicleQuick`, `VehicleAdjust`, `Glance`.
- **Screens (`cabin-screens`, opt-in):** `CabinScreenFrame`,
  `CabinHomeSplitScreen`, `CabinMediaScreen`, `CabinClimateScreen`,
  `CabinScreenSamples` (fictional data + reference climate reducer).
- **Sizes and strings:** dimensions from `CabinKitMetrics`; user-visible
  strings as resources; no color literals.
- **Visual check:** `./gradlew :cabin-screens:testDebugUnitTest` writes every
  screen × look × scheme to `cabin-screens/build/showcase-shots/`.

## Guidelines

- `CabinTheme` + composition locals for colors and compliance
- Colors/type/space from tokens only (`resolveCabinColors`)
- OEM brand via `CabinBrandOverrides` (primary only) — never remap safety
- UDF; exhaustive vehicle `Signal` handling
- Public composables accept `Modifier`
- Gate interactions via `LocalCabinComplianceState` — skill `cabin-compliance`
- Missing compliance local is fail-closed (`Block`)
- Semantics / content descriptions for icon-only controls

→ [docs/platforms/compose.md](../../docs/platforms/compose.md)

## Experimental bar APIs

```kotlin
CabinTheme(vehicleState = …) {
    CabinSystemBar(slots = …)
    CabinStatusBar(items = …)
}
```

Same Restriction Engine matrix, Signal tones, and day/night roles as Views.

## Parity with Views

Same states, actions, min sizes, gating, unavailable UI, day/night roles.
API shape differs; behavior must not.

## Do not

- Depend on Views modules from Compose
- Force Compose into SystemUI bp
- Skip driving substitutes

Related: `cabin-views` · `cabin-component-spec` · [android/skills](https://github.com/android/skills) for general Compose craft

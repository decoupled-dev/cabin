# Integration

How to add Cabin to an existing AAOS image or application repository with
**minimal footprint**. Artifacts below are **planned**.

## Choose your slice

| Goal | Depend on | Build |
| --- | --- | --- |
| Brand tokens only (custom UI) | `cabin-tokens` / `CabinTokens` | Gradle or Soong |
| Compose app | `cabin-compose` (+ tokens/compliance) | Gradle (typical) |
| Views app (Gradle-built) | `cabin-views` (+ tokens/compliance) | Gradle |
| SystemUI / CarLauncher / platform media | `CabinViews` + `CabinTokens` + `CabinCompliance` | **Soong** — see [build-tree](build-tree.md) |
| Dual UI in one product | Both UI stacks; still **no** samples/catalog | Prefer split by partition |

See [packaging](packaging.md) and [architecture](../architecture.md).

## Platform / build-tree apps

SystemUI, CarLauncher, platform media, and other AOSP targets use **Soong**
(`Android.bp`), not Gradle `implementation(...)`.

1. Sync Cabin into the Android tree via repo manifest (e.g. `external/cabin`).
2. Depend on planned Soong modules: `CabinTokens`, `CabinCompliance`,
   `CabinViews` (Views-first; Compose is not required for SystemUI).
3. Brand with RROs and optional token overlay modules — do not fork Cabin core.

Full guide: [build-tree](build-tree.md) · engineering notes:
[soong](../platforms/soong.md).

## Planned Gradle setup (app developers)

### App / Compose module

```kotlin
// Planned
dependencies {
    implementation("dev.decoupled.cabin:cabin-compose:<version>")
}
```

```kotlin
// Planned usage
setContent {
    CabinTheme(tokens = OemOverlay.toCabinTokens()) {
        MediaNowPlaying(state = state, onAction = viewModel::onAction)
    }
}
```

### Views module (Gradle-built apps only)

```kotlin
// Planned — for app modules still on Views, built with Gradle
dependencies {
    implementation("dev.decoupled.cabin:cabin-views:<version>")
}
```

```xml
<!-- Planned -->
<application android:theme="@style/Theme.Cabin.Oem">
```

For SystemUI and other build-tree targets, use Soong — not this Gradle block.

## Wire vehicle state

Cabin does not replace Car APIs. Provide an adapter:

```kotlin
// Planned product code
fun CarUxRestrictions.toVehicleUiState(): VehicleUiState = /* map */
```

Inject the resulting compliance policy into theme/host.

## OEM overlay

1. **Apps (Gradle):** create `oem-tokens` depending only on `cabin-tokens`.
2. **Platform (Soong):** prefer RROs + optional `OemCabinTokens`-style module
   ([build-tree](build-tree.md)).
3. Map brand colors/type to semantic roles at the theme boundary — not inside
   feature screens.
4. Keep safety semantic locks intact ([tokens](../design-language/tokens.md)).

## Minimal footprint checklist

- [ ] No dependency on `samples` or `catalog`
- [ ] Only one UI stack unless both are required
- [ ] Token overlay / RRO isolated from Cabin core
- [ ] R8/shrinking enabled in Gradle apps; verify Cabin consumer rules (future)
- [ ] SystemUI uses Soong Views path only (no Compose, no Gradle AARs required)
- [ ] System image packages Views chrome separately from app APKs

## Existing Material / AAOS widgets

You can adopt Cabin incrementally — see [migration](migration.md).

## Verification

- Day/night smoke test
- Driving restriction fixture test on one screen
- Touch target visual QA on target hardware density
- Font scale large
- Platform: confirm SystemUI bp deps stay thin ([build-tree](build-tree.md))

## Related

- [Build-tree / Soong](build-tree.md)
- [Migration](migration.md)
- [Packaging](packaging.md)
- [Extension model](../components/extension-model.md)
- [Compose](../platforms/compose.md) · [Views](../platforms/views.md) · [Soong](../platforms/soong.md)

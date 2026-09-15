# Integration

How to add Cabin to an existing AAOS image or application repository with
**minimal footprint**. Artifacts below are **planned**.

## Choose your slice

| Goal | Depend on |
| --- | --- |
| Brand tokens only (custom UI) | `cabin-tokens` |
| Compose app | `cabin-compose` (+ transitive tokens/compliance) |
| System UI / legacy app | `cabin-views` (+ transitive tokens/compliance) |
| Dual UI in one product | Both UI artifacts; still **no** samples/catalog |

See [packaging](packaging.md) and [architecture](../architecture.md).

## Planned Gradle setup

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

### System UI / Views module

```kotlin
// Planned
dependencies {
    implementation("dev.decoupled.cabin:cabin-views:<version>")
}
```

```xml
<!-- Planned -->
<application android:theme="@style/Theme.Cabin.Oem">
```

## Wire vehicle state

Cabin does not replace Car APIs. Provide an adapter:

```kotlin
// Planned product code
fun CarUxRestrictions.toVehicleUiState(): VehicleUiState = /* map */
```

Inject the resulting compliance policy into theme/host.

## OEM overlay

1. Create `oem-tokens` module depending only on `cabin-tokens`.
2. Map brand colors/type to semantic roles.
3. Apply overlay at theme boundary — not inside feature screens.
4. Keep safety semantic locks intact ([tokens](../design-language/tokens.md)).

## Minimal footprint checklist

- [ ] No dependency on `samples` or `catalog`
- [ ] Only one UI stack unless both are required
- [ ] Token overlay isolated in its own thin module
- [ ] R8/shrinking enabled in apps; verify Cabin consumer rules (future)
- [ ] System image packages Views chrome separately from app APKs

## Existing Material / AAOS widgets

You can adopt Cabin incrementally — see [migration](migration.md).

## Verification

- Day/night smoke test
- Driving restriction fixture test on one screen
- Touch target visual QA on target hardware density
- Font scale large

## Related

- [Migration](migration.md)
- [Packaging](packaging.md)
- [Extension model](../components/extension-model.md)
- [Compose](../platforms/compose.md) · [Views](../platforms/views.md)

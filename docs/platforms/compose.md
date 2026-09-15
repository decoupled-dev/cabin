# Compose platform

Guidelines for implementing and consuming Cabin on Jetpack Compose, including
**parity contracts** with the Views stack.

## Role of Compose in Cabin

Compose is the preferred toolkit for **application** surfaces: media, EV, HVAC
apps, settings screens, and OEM feature apps. System UI and build-tree
components may still require Views ([views](views.md)); Cabin treats both as
first-class.

## Planned artifact

```text
dev.decoupled.cabin:cabin-compose:<version>
```

Depends on `cabin-tokens` and `cabin-compliance` ([architecture](../architecture.md)).

## Theme

```kotlin
// Planned
@Composable
fun CabinTheme(
    tokens: CabinTokens = CabinTokens.Default,
    compliance: CabinCompliance = CabinCompliance.Default,
    content: @Composable () -> Unit,
)
```

- Provide tokens and compliance via composition locals.
- Do not read OEM hardcodes inside components.
- Support day/night through scheme tokens, synced with system UiMode when
  product requests it.

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
| Testing | Screenshot + behavior tests; parity fixtures shared with Views |

## Compliance wiring

```kotlin
// Planned
val compliance = LocalCabinCompliance.current
CabinGated(
    interaction = CabinInteraction.OpenKeyboard,
    onDenied = { /* substitute UI */ },
) {
    /* allowed UI */
}
```

Driving and UX gates are not optional “if the app remembers.”

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

### Shared fixture example (planned)

```kotlin
// Planned test fixture used by both stacks
val PlayingMedia = MediaUiState(
    title = "Example Track",
    artist = "Example Artist",
    isPlaying = true,
    positionMs = 12_000,
    durationMs = 200_000,
)
```

## Interop

- Embedding Compose in View system UI: use `ComposeView` with Cabin theme
  wrappers; still prefer Views for critical system chrome when product
  mandates.
- Embedding Views in Compose: `AndroidView` with Cabin View themes when
  reusing legacy widgets.

## Do not

- Depend on `cabin-views` from `cabin-compose`.
- Ship sample-only dependencies into the AAR.
- Bypass compliance locals in “debug” builds without a clear debug policy API.

## Related

- [Views](views.md)
- [Architecture](../architecture.md)
- [Components](../components/README.md)
- [Principles](../principles.md)

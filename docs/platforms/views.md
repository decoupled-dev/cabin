# Views platform

Guidelines for implementing and consuming Cabin with Android Views / XML —
essential for **SystemUI**, **status bar**, **CarLauncher**, other **AOSP
build-tree** apps, and legacy surfaces that cannot yet adopt Compose.

## Role of Views in Cabin

Views are not a compatibility afterthought. They are the **primary** stack for
platform chrome. Many AAOS images deliver SystemUI and vendor apps as View
hierarchies built with **Soong**. Cabin’s Views stack must be as intentional as
Compose: tokenized, compliance-aware, and parity-tested.

Compose remains first-class for apps and for platform apps that already use
it — but Compose is **not** a gate for SystemUI adoption
([build-tree](../adoption/build-tree.md)).

## Planned artifacts

| Path | Identifier | Status |
| --- | --- | --- |
| Maven (Gradle apps) | `dev.decoupled.cabin:cabin-views:<version>` | Alpha (Theme Kit + System/Status bars + ClimateTile + MediaNowPlaying) |
| Soong (build-tree) | `CabinViews` | Alpha scaffolding `cabin-views/Android.bp` |

Depends on `cabin-tokens` / `CabinTokens` and `cabin-compliance` /
`CabinCompliance` for restriction-aware bars. Does **not** depend on
`cabin-compose` / `CabinCompose`. No AppCompat / Material (platform DayNight
parent).
## Theme and attributes

Theme Kit (Alpha) lives in `cabin-views` — see
[theme-kit](../adoption/theme-kit.md).

- Styles: `Theme.Cabin` / `Theme.Cabin.DayNight`, `ThemeOverlay.Cabin`
- Attrs (`cabin_color*`) defined in `cabin-tokens`; Theme Kit binds them to
  semantic + scheme color resources (day/night via `values-night`)
- Resolve in code with `CabinThemeResolver` — no hardcoded hex in widgets
- OEM brand via theme overlay / **RRO** without forking
  ([ADR 0003](../adr/0003-tokens-via-overlay-rro.md),
  [build-tree](../adoption/build-tree.md))

```xml
<style name="Theme.Cabin" parent="@android:style/Theme.DeviceDefault.DayNight">
    <!-- Token-backed cabin_* attributes (see cabin-views res); NoActionBar via window flags -->
</style>
```

System/Status bar widgets: `CabinSystemBarView`, `CabinStatusBarView` (Alpha).
Domain: `CabinClimateTileView`, `CabinMediaNowPlayingView` (Alpha).
## Implementation guidelines

| Topic | Cabin rule |
| --- | --- |
| Inflation | Safe in system UI contexts; avoid Compose-only assumptions |
| Configuration | Handle day/night and locale without losing vehicle state |
| State binding | Explicit `bind()` / adapters; no hidden global singletons |
| Focus / rotary | `focusable`, orderly `nextFocus*`, visible focus from tokens |
| Accessibility | `contentDescription`, important-for-accessibility set correctly |
| Performance | Avoid overdraw in persistent chrome; flatten where practical |
| Testing | Robolectric/instrumentation + screenshot; shared fixtures with Compose; on-image tests for SystemUI |

## Compliance wiring

```kotlin
val host = CabinComplianceHost(initialState = VehicleUiState.parked())
systemBar.setCompliance(host)
statusBar.setCompliance(host)
host.updateState(adapterState) // map CarUxRestrictions → VehicleUiState
```

Gated click listeners disable or substitute interactions consistently with
Compose ([driving](../compliance/driving-restrictions.md)). Null compliance
host is fail-closed (`Block`) so chrome cannot activate ungated.

## Parity contracts with Compose

Same table as [compose.md](compose.md): shared state/actions, token sizes,
gating, unavailable UI, day/night roles. Views may use XML layouts for
driving substitutes (`layout` / `layout-driving` patterns or programmatic
swap) but outcomes must match Compose substitutes.

## System UI and build-tree adoption

Primary Views consumers:

| Target | Consumption |
| --- | --- |
| SystemUI / status + system bars | Soong: `CabinTokens` + `CabinCompliance` + `CabinViews` |
| CarLauncher | Soong Views path; Compose only if that subtree already uses it |
| Platform media (Views) | Soong `CabinViews` |
| Gradle-built legacy apps | Maven `cabin-views` |

Rules:

- Keep dependencies minimal — tokens + compliance + views only
  ([packaging](../adoption/packaging.md), [build-tree](../adoption/build-tree.md)).
- Never pull `CabinCompose`, catalog, or samples into SystemUI.
- Prefer source-in-tree over AAR prebuilts for privileged chrome.
- Soong engineering notes: [soong](soong.md).

## Interop

- Host Compose islands via `ComposeView` when a subtree is Compose-first
  (apps / Compose-capable platform modules only).
- Do not create a cyclic Gradle or Soong dependency between stacks.

## Do not

- Reintroduce handheld-sized touch targets via AppCompat defaults without
  Cabin style overlays.
- Encode OEM brand in widget source.
- Skip unavailable/stale signal UI because “Views make it hard.”
- Document or implement SystemUI adoption as a Gradle `implementation` of
  Cabin AARs.

## Related

- [Build-tree](../adoption/build-tree.md)
- [Soong](soong.md)
- [Compose](compose.md)
- [System bars](../components/system-bars.md)
- [Status bars](../components/status-bars.md)
- [Integration](../adoption/integration.md)

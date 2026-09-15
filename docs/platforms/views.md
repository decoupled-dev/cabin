# Views platform

Guidelines for implementing and consuming Cabin with Android Views / XML —
essential for **system UI**, **status bar**, **build-tree apps**, and legacy
surfaces that cannot yet adopt Compose.

## Role of Views in Cabin

Views are not a compatibility afterthought. Many AAOS images still deliver
chrome and vendor apps as View hierarchies. Cabin’s Views stack must be as
intentional as Compose: tokenized, compliance-aware, and parity-tested.

## Planned artifact

```text
dev.decoupled.cabin:cabin-views:<version>
```

Depends on `cabin-tokens` and `cabin-compliance`. Does **not** depend on
`cabin-compose`.

## Theme and attributes

- Expose styleables for core widgets (`CabinButton`, `MediaNowPlayingView`, …).
- Map attributes to token roles, not raw colors, whenever possible.
- Support OEM overlays via Android theme overlay mechanisms + Cabin token
  bridge ([tokens](../design-language/tokens.md)).

```xml
<!-- Planned -->
<style name="Theme.Cabin" parent="Theme.AppCompat.DayNight.NoActionBar">
    <!-- Token-backed attributes -->
</style>
```

## Implementation guidelines

| Topic | Cabin rule |
| --- | --- |
| Inflation | Safe in system UI contexts; avoid Compose-only assumptions |
| Configuration | Handle day/night and locale without losing vehicle state |
| State binding | Explicit `bind()` / adapters; no hidden global singletons |
| Focus / rotary | `focusable`, orderly `nextFocus*`, visible focus from tokens |
| Accessibility | `contentDescription`, important-for-accessibility set correctly |
| Performance | Avoid overdraw in persistent chrome; flatten where practical |
| Testing | Robolectric/instrumentation + screenshot; shared fixtures with Compose |

## Compliance wiring

```kotlin
// Planned
class CabinComplianceHost(context: Context) {
    fun attach(policy: CabinCompliance) { /* … */ }
}

// Widgets query host / context theme for allow/deny
```

Gated click listeners disable or substitute interactions consistently with
Compose ([driving](../compliance/driving-restrictions.md)).

## Parity contracts with Compose

Same table as [compose.md](compose.md): shared state/actions, token sizes,
gating, unavailable UI, day/night roles. Views may use XML layouts for
driving substitutes (`layout` / `layout-driving` patterns or programmatic
swap) but outcomes must match Compose substitutes.

## System UI and build-tree adoption

- Prefer `cabin-views` for status/system bars in system images.
- Keep dependencies minimal — tokens + compliance + views only
  ([packaging](../adoption/packaging.md)).
- Avoid pulling Media/Compose app samples into the system partition.

## Interop

- Host Compose islands via `ComposeView` when a subtree is Compose-first.
- Do not create a cyclic Gradle dependency between stacks.

## Do not

- Reintroduce handheld-sized touch targets via AppCompat defaults without
  Cabin style overlays.
- Encode OEM brand in widget source.
- Skip unavailable/stale signal UI because “Views make it hard.”

## Related

- [Compose](compose.md)
- [System bars](../components/system-bars.md)
- [Status bars](../components/status-bars.md)
- [Integration](../adoption/integration.md)

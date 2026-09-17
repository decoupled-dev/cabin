# Media components

Media is a primary AAOS application domain: browse, now-playing, transport
controls, and queue management under driving restrictions.

## Purpose

- Deliver glanceable now-playing and large transport controls.
- Provide restriction-aware browse/search substitutes.
- Keep parity for Compose media apps and View-based OEM skins.

## Component set

| Component | Role | Safety class | Status |
| --- | --- | --- | --- |
| `MediaNowPlaying` | Title, artist, art, progress, transport, source | Convenience | **Alpha** Views · **Experimental** Compose — [spec](specs/media-now-playing.md) |
| `MediaTransport` | Play/pause, next, previous | Convenience | Included in MediaNowPlaying |
| `MediaBrowseList` | Hierarchy browse | Convenience (restricted) | Planned |
| `MediaQueue` | Upcoming tracks | Convenience (restricted) | Planned |
| `MediaMiniPlayer` | Chrome / system bar peek | Convenience | Planned |
| `MediaErrorBanner` | Source errors | Informational / warning | Planned |

## States

- Playing / paused / buffering / error / no source
- Connecting to Bluetooth / projection (program-specific)
- Driving-restricted browse (limited depth, no keyboard search)
- Artwork unavailable (tokenized placeholder — never blank chaotic layout)

## Compliance

| Concern | Rule |
| --- | --- |
| Driving | Transport via `MediaTransport` (Allow). Source / scrub via `MediaComplex` (Block while Moving). Prefer transport + now-playing; limit browse complexity ([restriction-states](../compliance/restriction-states.md)) |
| UX | Transport buttons ≥ touch minimum ([ux](../compliance/ux-restrictions.md)) |
| Glance | Title one line; secondary artist recessed ([a11y](../compliance/accessibility-glanceability.md)) |
| Motion | Progress updates without layout thrash; reduce decorative viz while driving |
| Honesty | Position / duration only from live `Signal.Value` — **no fake telemetry** |

## Token dependencies

- `cabin.component.mediaNowPlaying.*`
- `cabin.color.semantic.mediaAccent`
- Type roles `title`, `body`, `label`

## Compose API (Experimental — MediaNowPlaying)

```kotlin
@Composable
fun CabinMediaNowPlaying(
    state: CabinMediaNowPlayingState,
    onAction: (CabinMediaNowPlayingAction) -> Unit,
    modifier: Modifier = Modifier,
)
```

## Views API (Alpha — MediaNowPlaying)

```kotlin
class CabinMediaNowPlayingView : /* … */ {
    fun bind(state: CabinMediaNowPlayingState)
    fun setOnActionListener(listener: ((CabinMediaNowPlayingAction) -> Unit)?)
    fun setCompliance(host: CabinComplianceHost?)
}
```

## Planned (later)

```kotlin
// Planned
@Composable
fun MediaTransport(
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier,
)
```

## Parity notes

`CabinMediaNowPlayingState` and `CabinMediaNowPlayingAction` are identical
contracts on Compose and Views. Shared fixtures must emit the same actions.

## Acceptance criteria

- [x] Transport usable while driving
- [x] Complex source gated while Moving (fail-closed missing host)
- [x] Error/no-source / unavailable progress specified — no invented numbers
- [x] Artwork fallback stable
- [x] Compose/Views parity for transport + now-playing
- [ ] Browse driving substitute (later)

## Related

- [MediaNowPlaying spec](specs/media-now-playing.md)
- [Components inventory](README.md)
- [Driving restrictions](../compliance/driving-restrictions.md)
- [System bars](system-bars.md) (mini player slot)

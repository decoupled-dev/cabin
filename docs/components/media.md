# Media components

Media is a primary AAOS application domain: browse, now-playing, transport
controls, and queue management under driving restrictions.

## Purpose

- Deliver glanceable now-playing and large transport controls.
- Provide restriction-aware browse/search substitutes.
- Keep parity for Compose media apps and View-based OEM skins.

## Component set (planned)

| Component | Role | Safety class |
| --- | --- | --- |
| `MediaNowPlaying` | Title, artist, art, progress | Convenience |
| `MediaTransport` | Play/pause, next, previous | Convenience |
| `MediaBrowseList` | Hierarchy browse | Convenience (restricted) |
| `MediaQueue` | Upcoming tracks | Convenience (restricted) |
| `MediaMiniPlayer` | Chrome / system bar peek | Convenience |
| `MediaErrorBanner` | Source errors | Informational / warning |

## States

- Playing / paused / buffering / error / no source
- Connecting to Bluetooth / projection (program-specific)
- Driving-restricted browse (limited depth, no keyboard search)
- Artwork unavailable (tokenized placeholder — never blank chaotic layout)

## Compliance

| Concern | Rule |
| --- | --- |
| Driving | Prefer transport + now-playing; limit browse complexity ([driving](../compliance/driving-restrictions.md)) |
| UX | Transport buttons ≥ touch minimum ([ux](../compliance/ux-restrictions.md)) |
| Glance | Title one line; secondary artist recessed ([a11y](../compliance/accessibility-glanceability.md)) |
| Motion | Progress updates without layout thrash; reduce decorative viz while driving |

## Token dependencies

- `cabin.component.media.transport.size`
- `cabin.component.media.artwork.size`
- `cabin.color.semantic.mediaAccent`
- Type roles `title`, `body`, `label`

## Planned Compose API

```kotlin
// Planned
@Composable
fun MediaNowPlaying(
    state: MediaUiState,
    onAction: (MediaAction) -> Unit,
    modifier: Modifier = Modifier,
)

@Composable
fun MediaTransport(
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier,
)
```

## Planned Views API

```xml
<!-- Planned -->
<dev.decoupled.cabin.views.media.MediaNowPlayingView
    android:id="@+id/nowPlaying"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

```kotlin
// Planned
mediaNowPlayingView.bind(MediaUiState(/* … */))
mediaNowPlayingView.setOnMediaActionListener { action -> /* … */ }
```

## Parity notes

`MediaUiState` and `MediaAction` are shared models in a non-UI module or
duplicated as identical contracts — implementations must accept the same state
fixture and emit the same actions.

## Acceptance criteria

- [ ] Transport usable while driving
- [ ] Browse has driving substitute
- [ ] Error/no-source states specified
- [ ] Artwork fallback stable
- [ ] Compose/Views parity for transport + now-playing

## Related

- [Components inventory](README.md)
- [Driving restrictions](../compliance/driving-restrictions.md)
- [System bars](system-bars.md) (mini player slot)

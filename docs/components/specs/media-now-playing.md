# Spec: MediaNowPlaying

Implementable contract for Cabin **MediaNowPlaying** — automotive now-playing
chrome: artwork, metadata, transport, and source. Not a Material media card
clone. Overview: [media.md](../media.md). Layer 2 domain surface
([features](../../product/features.md)).

## Purpose

Glanceable now-playing with large transport controls. Complex media
interactions (source picker, scrub/seek) are Restriction Engine–gated while
Moving. Metadata and progress use honest `Signal` values — **no fake
telemetry**.

## Anatomy

```text
┌──────────────────────────────────────────────┐
│ [artwork]  Title                             │
│            Artist                            │
│            Source                            │
│          [⏮]  [⏯]  [⏭]                       │
│          ── progress (when known) ──          │
└──────────────────────────────────────────────┘
```

| Region | Role |
| --- | --- |
| Artwork | Album / station art or stable placeholder |
| Title / artist | One-line primary + recessed secondary |
| Source | Active source label; tap opens source (complex) |
| Transport | Previous / play-pause / next |
| Progress | Display-only when position + duration are live |

## States

| State | Behavior |
| --- | --- |
| Playing / paused | Transport reflects `isPlaying` |
| No source | Honest empty copy; transport non-activatable |
| Artwork unavailable | Tokenized placeholder — never chaotic blank |
| Position / duration unavailable | Hide progress numbers — **do not invent** |
| Stale metadata | Show last with stale labeling when product wires it |
| Fault | Source / playback fault presentation |
| Restricted (gate) | Complex actions Block/Substitute; transport per matrix |
| Day / Night | Theme Kit; `mediaAccent` for domain emphasis only |

## Sizes & type (from tokens)

| Token | Role |
| --- | --- |
| `cabin.component.mediaNowPlaying.artworkSize` | Artwork square |
| `cabin.component.mediaNowPlaying.transportMinSize` | 76dp transport targets |
| `cabin.component.mediaNowPlaying.gap` / `padding` | Spacing |
| `cabin.component.mediaNowPlaying.cornerRadius` | Modest radius |
| `cabin.color.semantic.mediaAccent` | Domain accent (not body text) |
| `cabin.color.scheme.*.outline` | Quiet rules / placeholder |
| `cabin.type.role.title` / `body` / `label` | Metadata hierarchy |

Stub: [`tokens/cabin.tokens.json`](../../../tokens/cabin.tokens.json).

Night `warning` / `error` / `charging` stay locked — unused as MediaNowPlaying
chrome decoration.

## Compliance gates

| Interaction | MediaNowPlaying use | Moving / Restricted / Unknown |
| --- | --- | --- |
| `Glance` | Title / artist / art display | Allow |
| `MediaTransport` | Play/pause, next, previous | Allow |
| `MediaComplex` | Open source, scrub/seek | **Block** |

Idling: `MediaComplex` → Substitute (visible, non-activatable). Parked → Allow.

Missing compliance host / local is **fail-closed** (`GateDisposition.Block`)
for activatable controls that require a gate (including transport when host is
absent — safe by default).

Safety class: **Convenience**.

## Views API (Alpha)

```kotlin
class CabinMediaNowPlayingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ViewGroup(context, attrs) {
    fun bind(state: CabinMediaNowPlayingState) { /* … */ }
    fun setOnActionListener(listener: ((CabinMediaNowPlayingAction) -> Unit)?) { /* … */ }
    fun setCompliance(host: CabinComplianceHost?) { /* … */ }
}
```

Package: `dev.decoupled.cabin.views`. Module: `cabin-views` / `CabinViews`.

## Compose API (Experimental)

```kotlin
@Composable
fun CabinMediaNowPlaying(
    state: CabinMediaNowPlayingState,
    onAction: (CabinMediaNowPlayingAction) -> Unit,
    modifier: Modifier = Modifier,
)
```

Package: `dev.decoupled.cabin.compose`. Same gates and Signal honesty as Views;
wrap with `CabinTheme`. **Not** for SystemUI Soong adoption.

## Shared state / actions (parity)

```kotlin
data class CabinMediaNowPlayingState(
    val title: Signal<String>,
    val artist: Signal<String>,
    val sourceLabel: Signal<String>,
    val isPlaying: Boolean,
    val artworkAvailable: Boolean,
    val positionMs: Signal<Long>,
    val durationMs: Signal<Long>,
    val hasSource: Boolean = true,
)

sealed interface CabinMediaNowPlayingAction {
    data object PlayPause : CabinMediaNowPlayingAction
    data object Next : CabinMediaNowPlayingAction
    data object Previous : CabinMediaNowPlayingAction
    data object OpenSource : CabinMediaNowPlayingAction
    /** Complex scrub — only when both position and duration are live. */
    data class SeekTo(val positionMs: Long) : CabinMediaNowPlayingAction
}
```

Identical contracts on Compose and Views (duplicated types; same fields).

Progress UI renders only when both `positionMs` and `durationMs` are
`Signal.Value` with `duration > 0`. Otherwise omit numeric progress — never
fabricate elapsed / remaining.

## Acceptance criteria

- [x] Automotive MediaNowPlaying (not Material media card clone)
- [x] Views-first Alpha + Compose Experimental parity
- [x] Transport via `MediaTransport`; source/seek via `MediaComplex` (Block while Moving)
- [x] Fail-closed when compliance host / local absent
- [x] Honest media numbers — no fake position / duration / telemetry
- [x] Artwork unavailable → stable placeholder
- [x] 76dp transport minima; Theme Kit forest/outline; locked night safety unused as chrome
- [x] Unit tests for RE + states on both stacks
- [x] No catalog reverse deps; marketing/docs sites frozen

## Related

- [Media overview](../media.md)
- [Restriction states](../../compliance/restriction-states.md)
- [Views](../../platforms/views.md) · [Compose](../../platforms/compose.md)

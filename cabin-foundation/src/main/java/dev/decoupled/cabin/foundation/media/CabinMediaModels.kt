package dev.decoupled.cabin.foundation.media

import dev.decoupled.cabin.compliance.Signal

/**
 * Shared MediaNowPlaying presentation state (Compose + Views parity).
 *
 * Spec: docs/components/specs/media-now-playing.md
 */
data class CabinMediaNowPlayingState(
    val title: Signal<String>,
    val artist: Signal<String>,
    val sourceLabel: Signal<String>,
    val isPlaying: Boolean,
    val artworkAvailable: Boolean,
    val positionMs: Signal<Long>,
    val durationMs: Signal<Long>,
    val hasSource: Boolean = true,
) {
    companion object {
        fun empty(): CabinMediaNowPlayingState = CabinMediaNowPlayingState(
            title = Signal.Unavailable,
            artist = Signal.Unavailable,
            sourceLabel = Signal.Unavailable,
            isPlaying = false,
            artworkAvailable = false,
            positionMs = Signal.Unavailable,
            durationMs = Signal.Unavailable,
            hasSource = false,
        )
    }
}

sealed interface CabinMediaNowPlayingAction {
    data object PlayPause : CabinMediaNowPlayingAction
    data object Next : CabinMediaNowPlayingAction
    data object Previous : CabinMediaNowPlayingAction
    data object OpenSource : CabinMediaNowPlayingAction
    data class SeekTo(val positionMs: Long) : CabinMediaNowPlayingAction
}

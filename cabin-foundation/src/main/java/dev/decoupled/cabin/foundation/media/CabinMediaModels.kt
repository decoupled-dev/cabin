/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

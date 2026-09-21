package dev.decoupled.cabin.sink

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.CabinStatusEmphasis
import dev.decoupled.cabin.compose.CabinStatusGlyph
import dev.decoupled.cabin.compose.CabinStatusItem
import dev.decoupled.cabin.compose.CabinSystemBarEntry
import dev.decoupled.cabin.compose.CabinSystemBarSlots
import dev.decoupled.cabin.compose.StatusDeepLink
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileState
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingState

internal object SinkFixtures {
    fun systemBarSlots(onAction: (String) -> Unit): CabinSystemBarSlots =
        CabinSystemBarSlots(
            leading = listOf(
                CabinSystemBarEntry(
                    id = "home",
                    label = "Home",
                    contentDescription = "Home",
                    interaction = CabinInteraction.NavigateSimple,
                    onActivate = { onAction("Home") },
                ),
            ),
            center = listOf(
                CabinSystemBarEntry(
                    id = "apps",
                    label = "Apps",
                    contentDescription = "Apps",
                    interaction = CabinInteraction.OpenComplexApp,
                    onActivate = { onAction("Apps") },
                ),
            ),
            trailing = listOf(
                CabinSystemBarEntry(
                    id = "media",
                    label = "Media",
                    contentDescription = "Media",
                    interaction = CabinInteraction.MediaTransport,
                    onActivate = { onAction("Media") },
                ),
            ),
        )

    fun statusItems(onAction: (String) -> Unit): List<CabinStatusItem> = listOf(
        CabinStatusGlyph(
            id = "range",
            contentDescription = "Range",
            text = "240 mi",
            signal = Signal.Value("240 mi", atMillis = 1L),
            deepLink = StatusDeepLink(
                opensSettings = false,
                onActivate = { onAction("Range") },
            ),
        ),
        CabinStatusGlyph(
            id = "charge",
            contentDescription = "Charge",
            text = "82%",
            signal = Signal.Value("82%", atMillis = 1L),
            emphasis = CabinStatusEmphasis.Charging,
        ),
        CabinStatusGlyph(
            id = "network",
            contentDescription = "Network",
            signal = Signal.Unavailable,
        ),
    )

    fun climateLive(): CabinClimateTileState = CabinClimateTileState(
        zoneLabel = "Driver",
        temperatureC = Signal.Value(21, atMillis = 1L),
        fanLevel = Signal.Value(2, atMillis = 1L),
        seatHeatLevel = Signal.Value(1, atMillis = 1L),
    )

    fun mediaLive(): CabinMediaNowPlayingState = CabinMediaNowPlayingState(
        title = Signal.Value("Night Drive", atMillis = 1L),
        artist = Signal.Value("Cabin Radio", atMillis = 1L),
        sourceLabel = Signal.Value("FM 98.7", atMillis = 1L),
        isPlaying = true,
        artworkAvailable = false,
        positionMs = Signal.Value(90_000L, atMillis = 1L),
        durationMs = Signal.Value(240_000L, atMillis = 1L),
    )
}

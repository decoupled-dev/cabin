package dev.decoupled.cabin.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Automotive now-playing: art, metadata, transport, source.
 *
 * Spec: docs/components/specs/media-now-playing.md
 *
 * **Experimental** Compose parity with Views `CabinMediaNowPlayingView` (Alpha).
 * Transport → [CabinInteraction.MediaTransport]; source → [CabinInteraction.MediaComplex].
 * Progress numbers only when both Signals are live — no fake telemetry.
 */
@Composable
fun CabinMediaNowPlaying(
    state: CabinMediaNowPlayingState,
    onAction: (CabinMediaNowPlayingAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val transportGate = dispositionOf(compliance, CabinInteraction.MediaTransport)
    val complexGate = dispositionOf(compliance, CabinInteraction.MediaComplex)
    val padding = CabinTokens.Component.MediaNowPlaying.padding.dp.dp
    val gap = CabinTokens.Component.MediaNowPlaying.gap.dp.dp
    val radius = CabinTokens.Component.MediaNowPlaying.cornerRadius.dp.dp
    val artworkSize = CabinTokens.Component.MediaNowPlaying.artworkSize.dp.dp
    val transportMin = maxOf(
        CabinTokens.Component.MediaNowPlaying.transportMinSize.dp.dp,
        compliance.touchTargetMinDp().dp,
    )
    val titleSize = CabinTokens.Type.Role.Title.size.sp.sp
    val bodySize = CabinTokens.Type.Role.Body.size.sp.sp
    val labelSize = CabinTokens.Type.Role.Label.size.sp.sp
    val statusSize = CabinTokens.Type.Role.Status.size.sp.sp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceVariant, RoundedCornerShape(radius))
            .border(1.dp, colors.outline, RoundedCornerShape(radius))
            .padding(padding)
            .testTag("cabin_media_now_playing"),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(artworkSize)
                    .background(colors.container, RoundedCornerShape(radius / 2))
                    .border(1.dp, colors.outline, RoundedCornerShape(radius / 2))
                    .testTag("media_artwork")
                    .semantics {
                        contentDescription = if (state.artworkAvailable) {
                            "Album artwork"
                        } else {
                            "Artwork unavailable"
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    text = if (state.artworkAvailable) "ART" else "—",
                    style = TextStyle(color = colors.mediaAccent, fontSize = labelSize),
                )
            }
            Spacer(Modifier.width(gap))
            Column(modifier = Modifier.weight(1f)) {
                BasicText(
                    text = formatMediaText(state.title, fallback = "No title"),
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = titleSize,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    modifier = Modifier.testTag("media_title"),
                )
                BasicText(
                    text = formatMediaText(state.artist, fallback = "—"),
                    style = TextStyle(color = colors.onSurface, fontSize = bodySize),
                    modifier = Modifier.testTag("media_artist"),
                )
                val sourceActivatable =
                    GateVisuals.activatable(state.hasSource, complexGate)
                BasicText(
                    text = formatMediaText(state.sourceLabel, fallback = "No source"),
                    style = TextStyle(color = colors.mediaAccent, fontSize = labelSize),
                    modifier = Modifier
                        .alpha(
                            if (!state.hasSource) 0.4f else GateVisuals.alpha(complexGate),
                        )
                        .testTag("media_source")
                        .semantics { contentDescription = "Media source" }
                        .then(
                            if (sourceActivatable) {
                                Modifier.clickable {
                                    onAction(CabinMediaNowPlayingAction.OpenSource)
                                }
                            } else {
                                Modifier
                            },
                        ),
                )
            }
        }
        Spacer(Modifier.height(gap))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MediaTransportControl(
                label = "⏮",
                description = "Previous",
                tag = "media_previous",
                transportMin = transportMin,
                textSize = titleSize,
                contentColor = colors.onSurface,
                outlineColor = colors.outline,
                enabled = state.hasSource,
                disposition = transportGate,
                onClick = { onAction(CabinMediaNowPlayingAction.Previous) },
            )
            MediaTransportControl(
                label = if (state.isPlaying) "⏸" else "▶",
                description = "Play or pause",
                tag = "media_play_pause",
                transportMin = transportMin,
                textSize = titleSize,
                contentColor = colors.onSurface,
                outlineColor = colors.outline,
                enabled = state.hasSource,
                disposition = transportGate,
                onClick = { onAction(CabinMediaNowPlayingAction.PlayPause) },
            )
            MediaTransportControl(
                label = "⏭",
                description = "Next",
                tag = "media_next",
                transportMin = transportMin,
                textSize = titleSize,
                contentColor = colors.onSurface,
                outlineColor = colors.outline,
                enabled = state.hasSource,
                disposition = transportGate,
                onClick = { onAction(CabinMediaNowPlayingAction.Next) },
            )
        }
        val progress = liveProgressText(state.positionMs, state.durationMs)
        if (progress != null) {
            Spacer(Modifier.height(gap))
            BasicText(
                text = progress,
                style = TextStyle(color = colors.onSurface, fontSize = statusSize),
                modifier = Modifier.testTag("media_progress"),
            )
        }
    }
}

@Composable
private fun MediaTransportControl(
    label: String,
    description: String,
    tag: String,
    transportMin: Dp,
    textSize: TextUnit,
    contentColor: Color,
    outlineColor: Color,
    enabled: Boolean,
    disposition: GateDisposition,
    onClick: () -> Unit,
) {
    val canActivate = GateVisuals.activatable(enabled, disposition)
    Box(
        modifier = Modifier
            .size(transportMin)
            .alpha(if (!enabled) 0.4f else GateVisuals.alpha(disposition))
            .border(1.dp, outlineColor, RoundedCornerShape(4.dp))
            .testTag(tag)
            .semantics { contentDescription = description }
            .then(if (canActivate) Modifier.clickable(onClick = onClick) else Modifier),
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = label,
            style = TextStyle(color = contentColor, fontSize = textSize),
        )
    }
}

internal fun formatMediaText(signal: Signal<String>, fallback: String): String = when (signal) {
    is Signal.Value -> signal.value
    is Signal.Stale -> signal.last
    Signal.Unavailable -> fallback
    is Signal.Fault -> "!"
}

/**
 * Honest progress: only when both position and duration are live Values
 * with duration > 0. Otherwise null — never invent telemetry.
 */
internal fun liveProgressText(
    positionMs: Signal<Long>,
    durationMs: Signal<Long>,
): String? {
    if (positionMs !is Signal.Value || durationMs !is Signal.Value) return null
    if (durationMs.value <= 0L) return null
    return formatProgress(positionMs.value, durationMs.value)
}

internal fun formatProgress(positionMs: Long, durationMs: Long): String {
    fun mmss(ms: Long): String {
        val totalSec = (ms / 1000L).coerceAtLeast(0L)
        val m = totalSec / 60L
        val s = totalSec % 60L
        return "%d:%02d".format(m, s)
    }
    return "${mmss(positionMs)} / ${mmss(durationMs)}"
}

/** MediaNowPlaying presentation state (parity with Views). */
@Immutable
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

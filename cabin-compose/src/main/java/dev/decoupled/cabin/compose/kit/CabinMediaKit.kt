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

package dev.decoupled.cabin.compose.kit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.R
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingAction
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingState

/**
 * Abstract artwork stand-in drawn from the look's colors. Use when the media
 * source has no art; decorative only.
 */
@Composable
fun CabinArtworkPlaceholder(modifier: Modifier = Modifier) {
    val c = CabinTheme.look.colors
    val base = lerp(c.surfaceHigh, c.primary, 0.28f)
    Canvas(modifier.clearAndSetSemantics {}) {
        drawRect(base)
        val d = size.minDimension
        drawCircle(c.primary, radius = d * 0.33f, center = Offset(d * 0.12f, size.height - d * 0.08f))
        drawCircle(c.onSurface.copy(alpha = 0.85f), radius = d * 0.21f, center = Offset(size.width - d * 0.33f, d * 0.33f))
    }
}

/** Title / artist / source lines. */
@Composable
fun CabinTrackInfo(
    state: CabinMediaNowPlayingState,
    modifier: Modifier = Modifier,
    large: Boolean = false,
) {
    val look = CabinTheme.look
    val title = cabinSignalText(state.title) { it }
    val artist = cabinSignalText(state.artist) { it }
    val source = cabinSignalText(state.sourceLabel) { it }
    Column(modifier, verticalArrangement = Arrangement.spacedBy(if (large) CabinKitMetrics.gapSm else 6.dp)) {
        Text(
            text = if (state.hasSource) title.text else stringResource(R.string.cabin_kit_media_no_source),
            style = if (large) look.type.numeralMedium.copy(fontFeatureSettings = null) else look.type.title,
            maxLines = if (large) 2 else 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.semantics { contentDescription = title.description },
        )
        if (state.hasSource) {
            Text(
                artist.text,
                style = if (large) look.type.title.copy(fontWeight = look.type.body.fontWeight) else look.type.body,
                color = look.colors.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            CabinLabel(source.text, color = look.colors.onSurfaceMuted)
        }
    }
}

/** Progress bar with elapsed and total time. */
@Composable
fun CabinMediaProgress(state: CabinMediaNowPlayingState, modifier: Modifier = Modifier) {
    val look = CabinTheme.look
    val position = state.positionMs.valueOrNull()
    val duration = state.durationMs.valueOrNull()
    val fraction = if (position != null && duration != null && duration > 0) position.toFloat() / duration else 0f
    Column(modifier, verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm)) {
        CabinProgressBar(fraction)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(cabinSignalText(state.positionMs, ::formatMediaTime).text, style = look.type.body.copy(fontFeatureSettings = "tnum"), color = look.colors.onSurfaceMuted)
            Text(cabinSignalText(state.durationMs, ::formatMediaTime).text, style = look.type.body.copy(fontFeatureSettings = "tnum"), color = look.colors.onSurfaceMuted)
        }
    }
}

/**
 * Previous / play-pause / next. Transport is `MediaTransport`, allowed in
 * every drive state.
 */
@Composable
fun CabinMediaTransport(
    isPlaying: Boolean,
    onAction: (CabinMediaNowPlayingAction) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = CabinKitMetrics.controlLarge,
    playWidth: Dp = size * 1.83f,
    enabled: Boolean = true,
) {
    val interaction = CabinInteraction.MediaTransport
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd), verticalAlignment = Alignment.CenterVertically) {
        CabinIconButton(
            icon = CabinIcons.Previous,
            contentDescription = stringResource(R.string.cabin_kit_media_previous),
            onClick = { if (enabled) onAction(CabinMediaNowPlayingAction.Previous) },
            interaction = interaction,
            size = size,
        )
        CabinButton(
            onClick = { if (enabled) onAction(CabinMediaNowPlayingAction.PlayPause) },
            interaction = interaction,
            style = CabinButtonStyle.Filled,
            modifier = Modifier.width(playWidth).height(size),
            contentDescription = stringResource(if (isPlaying) R.string.cabin_kit_media_pause else R.string.cabin_kit_media_play),
        ) {
            Icon(if (isPlaying) CabinIcons.Pause else CabinIcons.Play, contentDescription = null, modifier = Modifier.size(CabinKitMetrics.iconLg + 4.dp))
        }
        CabinIconButton(
            icon = CabinIcons.Next,
            contentDescription = stringResource(R.string.cabin_kit_media_next),
            onClick = { if (enabled) onAction(CabinMediaNowPlayingAction.Next) },
            interaction = interaction,
            size = size,
        )
    }
}

/**
 * Browse entry point. While the Restriction Engine blocks `MediaComplex`
 * it becomes a muted "Browse when parked" hint in the same place.
 */
@Composable
fun CabinMediaBrowseEntry(
    onBrowse: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (cabinGate(CabinInteraction.MediaComplex) == GateDisposition.Allow) {
        CabinButton(onClick = onBrowse, interaction = CabinInteraction.MediaComplex, modifier = modifier) {
            Icon(CabinIcons.Queue, contentDescription = null, modifier = Modifier.size(CabinKitMetrics.iconMd))
            CabinLabel(stringResource(R.string.cabin_kit_media_browse))
        }
    } else {
        CabinRestrictedHint(stringResource(R.string.cabin_kit_media_browse_when_parked), modifier)
    }
}

/**
 * Now-playing card for Home (H1): art and track info, progress, transport,
 * and the browse entry.
 *
 * @param artwork artwork slot; defaults to [CabinArtworkPlaceholder]
 */
@Composable
fun CabinNowPlayingCard(
    state: CabinMediaNowPlayingState,
    onAction: (CabinMediaNowPlayingAction) -> Unit,
    modifier: Modifier = Modifier,
    artwork: @Composable BoxScope.() -> Unit = { CabinArtworkPlaceholder(Modifier.fillMaxSize()) },
) {
    val look = CabinTheme.look
    CabinPanel(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Row(horizontalArrangement = Arrangement.spacedBy(22.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(168.dp)
                    .clip(look.shapes.medium)
                    .background(look.colors.surfaceHigh),
                content = artwork,
            )
            CabinTrackInfo(state, Modifier.weight(1f))
        }
        CabinMediaProgress(state)
        CabinMediaTransport(
            isPlaying = state.isPlaying,
            onAction = onAction,
            enabled = state.hasSource,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        CabinMediaBrowseEntry(
            onBrowse = { onAction(CabinMediaNowPlayingAction.OpenSource) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

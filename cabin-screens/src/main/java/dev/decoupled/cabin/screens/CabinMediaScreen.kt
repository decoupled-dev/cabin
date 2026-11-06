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

package dev.decoupled.cabin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.kit.CabinArtworkPlaceholder
import dev.decoupled.cabin.compose.kit.CabinKitMetrics
import dev.decoupled.cabin.compose.kit.CabinLabel
import dev.decoupled.cabin.compose.kit.CabinMediaBrowseEntry
import dev.decoupled.cabin.compose.kit.CabinMediaProgress
import dev.decoupled.cabin.compose.kit.CabinMediaTransport
import dev.decoupled.cabin.compose.kit.CabinPanel
import dev.decoupled.cabin.compose.kit.CabinRestrictedHint
import dev.decoupled.cabin.compose.kit.CabinTrackInfo
import dev.decoupled.cabin.compose.kit.cabinGate
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingAction
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingState
import dev.decoupled.cabin.compose.R as KitR

/** A row in the Up next list. */
@Immutable
data class CabinQueueItem(val id: String, val title: String, val subtitle: String)

/**
 * Media, M2 Art hero (reference screen): artwork leads, track info and
 * transport beside it, and an Up next panel. While the Restriction Engine
 * blocks `MediaComplex`, the panel shows only the next track and a
 * "Library opens when parked" hint. Transport always works.
 * Place inside [CabinScreenFrame].
 *
 * @param artwork artwork slot; defaults to [CabinArtworkPlaceholder]
 */
@Composable
fun CabinMediaScreen(
    state: CabinMediaNowPlayingState,
    onAction: (CabinMediaNowPlayingAction) -> Unit,
    queue: List<CabinQueueItem>,
    onQueueItemSelect: (CabinQueueItem) -> Unit,
    modifier: Modifier = Modifier,
    artwork: @Composable BoxScope.() -> Unit = { CabinArtworkPlaceholder(Modifier.fillMaxSize()) },
) {
    val look = CabinTheme.look
    Row(modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
        CabinPanel(
            Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentPadding = PaddingValues(CabinKitMetrics.gapLg),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(look.shapes.large)
                        .background(look.colors.surfaceHigh),
                    content = artwork,
                )
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(vertical = CabinKitMetrics.gapSm),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    CabinTrackInfo(state, large = true)
                    Column(verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapLg)) {
                        CabinMediaProgress(state)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CabinMediaTransport(
                                isPlaying = state.isPlaying,
                                onAction = onAction,
                                enabled = state.hasSource,
                            )
                            Spacer(Modifier.weight(1f))
                            CabinMediaBrowseEntry(onBrowse = { onAction(CabinMediaNowPlayingAction.OpenSource) })
                        }
                    }
                }
            }
        }
        UpNextPanel(
            queue = queue,
            onSelect = onQueueItemSelect,
            modifier = Modifier
                .width(520.dp)
                .fillMaxHeight(),
        )
    }
}

@Composable
private fun UpNextPanel(
    queue: List<CabinQueueItem>,
    onSelect: (CabinQueueItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    val browsable = cabinGate(CabinInteraction.MediaComplex) == GateDisposition.Allow
    CabinPanel(modifier, verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
        CabinLabel(stringResource(KitR.string.cabin_kit_media_up_next), color = look.colors.onSurfaceMuted)
        val visible = if (browsable) queue.take(4) else queue.take(1)
        visible.forEach { item -> QueueRow(item, browsable, onSelect) }
        if (!browsable) {
            Spacer(Modifier.weight(1f))
            CabinRestrictedHint(
                stringResource(KitR.string.cabin_kit_media_library_when_parked),
                Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(CabinKitMetrics.gapSm))
        }
    }
}

@Composable
private fun QueueRow(item: CabinQueueItem, enabled: Boolean, onSelect: (CabinQueueItem) -> Unit) {
    val look = CabinTheme.look
    val content: @Composable () -> Unit = {
        Row(
            Modifier.padding(horizontal = CabinKitMetrics.gapMd),
            horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier
                    .size(64.dp)
                    .clip(look.shapes.medium),
            ) { CabinArtworkPlaceholder(Modifier.fillMaxSize()) }
            Column(Modifier.weight(1f)) {
                Text(
                    item.title,
                    style = look.type.body.copy(fontWeight = look.type.title.fontWeight),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    item.subtitle,
                    style = look.type.body,
                    color = look.colors.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
    val rowModifier = Modifier
        .fillMaxWidth()
        .height(CabinKitMetrics.controlLarge)
        .semantics(mergeDescendants = true) { contentDescription = "${item.title}, ${item.subtitle}" }
    if (enabled) {
        // cabin-rules: allow CR005 — gated by the caller (UpNextPanel checks MediaComplex).
        Surface(
            onClick = { onSelect(item) },
            modifier = rowModifier,
            shape = look.shapes.medium,
            color = look.colors.surfaceHigh,
        ) { Box(contentAlignment = Alignment.CenterStart) { content() } }
    } else {
        // Read-only while browsing is restricted: no click target, same layout.
        Surface(modifier = rowModifier, shape = look.shapes.medium, color = look.colors.surfaceHigh) {
            Box(contentAlignment = Alignment.CenterStart) { content() }
        }
    }
}

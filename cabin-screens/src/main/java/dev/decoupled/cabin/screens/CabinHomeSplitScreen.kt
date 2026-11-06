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

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compose.kit.CabinButtonStyle
import dev.decoupled.cabin.compose.kit.CabinEnergyCard
import dev.decoupled.cabin.compose.kit.CabinEtaChip
import dev.decoupled.cabin.compose.kit.CabinIconButton
import dev.decoupled.cabin.compose.kit.CabinIcons
import dev.decoupled.cabin.compose.kit.CabinKitMetrics
import dev.decoupled.cabin.compose.kit.CabinNextTurnCard
import dev.decoupled.cabin.compose.kit.CabinNowPlayingCard
import dev.decoupled.cabin.compose.kit.CabinQuickControlTile
import dev.decoupled.cabin.compose.kit.edgeBorder
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.ev.CabinEnergyState
import dev.decoupled.cabin.foundation.maps.CabinNextTurnState
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingAction
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingState

/** A Home quick control (lock, defrost, lights, drive mode …). */
@Immutable
data class CabinQuickControl(
    val key: String,
    val label: String,
    val icon: ImageVector,
    val on: Boolean,
    /** Single-tap body controls default to `VehicleQuick`; defrost-style controls use `HvacQuick`. */
    val interaction: CabinInteraction = CabinInteraction.VehicleQuick,
)

/**
 * Home, H1 Split (reference screen): map with next turn on the left,
 * now playing in the middle, battery and four quick controls on the right.
 * Place inside [CabinScreenFrame].
 *
 * @param map map slot; defaults to [CabinSampleMap]
 */
@Composable
fun CabinHomeSplitScreen(
    media: CabinMediaNowPlayingState,
    onMediaAction: (CabinMediaNowPlayingAction) -> Unit,
    energy: CabinEnergyState,
    quickControls: List<CabinQuickControl>,
    onQuickControlChange: (CabinQuickControl, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    nextTurn: CabinNextTurnState? = null,
    onRecenter: () -> Unit = {},
    map: @Composable BoxScope.() -> Unit = { CabinSampleMap() },
) {
    val look = CabinTheme.look
    val mapDescription = stringResource(R.string.cabin_screens_map)
    Row(modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
        Box(
            Modifier
                .weight(980f)
                .fillMaxHeight()
                .clip(look.shapes.large)
                .then(
                    look.edgeBorder()?.let { Modifier.border(it, look.shapes.large) } ?: Modifier,
                )
                .semantics { contentDescription = mapDescription },
        ) {
            map()
            if (nextTurn != null) {
                CabinNextTurnCard(nextTurn, Modifier.align(Alignment.TopStart).padding(20.dp))
                if (nextTurn.etaText != null && nextTurn.tripText != null) {
                    CabinEtaChip(
                        nextTurn.etaText!!,
                        nextTurn.tripText!!,
                        Modifier.align(Alignment.BottomStart).padding(20.dp),
                    )
                }
            }
            CabinIconButton(
                icon = CabinIcons.Navigation,
                contentDescription = stringResource(dev.decoupled.cabin.compose.R.string.cabin_kit_nav_recenter),
                onClick = onRecenter,
                interaction = CabinInteraction.NavigateSimple,
                style = CabinButtonStyle.Tonal,
                modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp),
            )
        }
        CabinNowPlayingCard(
            state = media,
            onAction = onMediaAction,
            modifier = Modifier
                .weight(520f)
                .fillMaxHeight(),
        )
        Column(
            Modifier
                .weight(388f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd),
        ) {
            CabinEnergyCard(energy, Modifier.fillMaxWidth())
            QuickControlGrid(quickControls, onQuickControlChange, Modifier.weight(1f))
        }
    }
}

@Composable
private fun QuickControlGrid(
    controls: List<CabinQuickControl>,
    onChange: (CabinQuickControl, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
        controls.take(4).chunked(2).forEach { row ->
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
                row.forEach { control ->
                    CabinQuickControlTile(
                        icon = control.icon,
                        label = control.label,
                        checked = control.on,
                        onCheckedChange = { onChange(control, it) },
                        interaction = control.interaction,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                    )
                }
            }
        }
    }
}

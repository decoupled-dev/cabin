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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compose.R as KitR
import dev.decoupled.cabin.compose.kit.CabinClimateModeToggle
import dev.decoupled.cabin.compose.kit.CabinFanSelector
import dev.decoupled.cabin.compose.kit.CabinIconButton
import dev.decoupled.cabin.compose.kit.CabinIcons
import dev.decoupled.cabin.compose.kit.CabinKitMetrics
import dev.decoupled.cabin.compose.kit.CabinLabel
import dev.decoupled.cabin.compose.kit.CabinPanel
import dev.decoupled.cabin.compose.kit.CabinSeatHeatButton
import dev.decoupled.cabin.compose.kit.CabinToggleButton
import dev.decoupled.cabin.compose.kit.cabinClimateModes
import dev.decoupled.cabin.compose.kit.cabinSignalText
import dev.decoupled.cabin.compose.kit.formatCelsius
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.hvac.CabinClimateAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateState
import dev.decoupled.cabin.foundation.hvac.CabinClimateZone

/**
 * Climate, C4 Panel (reference screen): driver and passenger zones with
 * large set-points on either side, and the panel (fan, modes, seats, sync)
 * between them. Every control is single-tap (`HvacQuick`), so the whole
 * screen stays usable while driving. Place inside [CabinScreenFrame].
 */
@Composable
fun CabinClimateScreen(
    state: CabinClimateState,
    onAction: (CabinClimateAction) -> Unit,
    modifier: Modifier = Modifier,
    format: (Float) -> String = ::formatCelsius,
) {
    Row(modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
        state.zones.getOrNull(0)?.let { ZoneColumn(it, onAction, format, Modifier.weight(1f)) }
        ClimatePanel(state, onAction, Modifier.width(920.dp).fillMaxHeight())
        state.zones.getOrNull(1)?.let { ZoneColumn(it, onAction, format, Modifier.weight(1f)) }
    }
}

@Composable
private fun ZoneColumn(
    zone: CabinClimateZone,
    onAction: (CabinClimateAction) -> Unit,
    format: (Float) -> String,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    val value = cabinSignalText(zone.temperatureC, format)
    CabinPanel(modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        CabinLabel(zone.label, color = look.colors.onSurfaceMuted)
        Text(
            value.text,
            style = look.type.numeralHero,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "${zone.label} ${value.description}" },
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapMd)) {
            CabinIconButton(
                icon = CabinIcons.Minus,
                contentDescription = stringResource(KitR.string.cabin_kit_climate_temp_down, zone.label),
                onClick = { onAction(CabinClimateAction.TemperatureStep(zone.id, -1)) },
                interaction = CabinInteraction.HvacQuick,
                modifier = Modifier.weight(1f),
                size = CabinKitMetrics.controlLarge,
            )
            CabinIconButton(
                icon = CabinIcons.Plus,
                contentDescription = stringResource(KitR.string.cabin_kit_climate_temp_up, zone.label),
                onClick = { onAction(CabinClimateAction.TemperatureStep(zone.id, +1)) },
                interaction = CabinInteraction.HvacQuick,
                modifier = Modifier.weight(1f),
                size = CabinKitMetrics.controlLarge,
            )
        }
    }
}

@Composable
private fun ClimatePanel(
    state: CabinClimateState,
    onAction: (CabinClimateAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    CabinPanel(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Column(verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm)) {
            CabinLabel(stringResource(KitR.string.cabin_kit_climate_fan), color = look.colors.onSurfaceMuted)
            CabinFanSelector(state, onAction, Modifier.fillMaxWidth())
        }
        Column(verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm)) {
            CabinLabel(stringResource(KitR.string.cabin_kit_climate_modes), color = look.colors.onSurfaceMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                cabinClimateModes(state).forEach { mode ->
                    CabinClimateModeToggle(
                        icon = mode.icon,
                        label = mode.label,
                        checked = mode.on,
                        onToggle = { onAction(mode.action) },
                        modifier = Modifier
                            .weight(1f)
                            .height(CabinKitMetrics.control),
                    )
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm)) {
            CabinLabel(stringResource(KitR.string.cabin_kit_climate_seats), color = look.colors.onSurfaceMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                state.zones.take(2).forEach { zone ->
                    CabinSeatHeatButton(
                        zone = zone,
                        onAction = onAction,
                        label = zone.label,
                        modifier = Modifier
                            .weight(1f)
                            .height(CabinKitMetrics.control),
                    )
                }
                val sync = stringResource(KitR.string.cabin_kit_climate_sync)
                CabinToggleButton(
                    checked = state.zonesSynced,
                    onCheckedChange = { onAction(CabinClimateAction.ToggleSync) },
                    interaction = CabinInteraction.HvacQuick,
                    modifier = Modifier
                        .weight(0.7f)
                        .height(CabinKitMetrics.control),
                    stateDescription = stringResource(if (state.zonesSynced) KitR.string.cabin_kit_state_on else KitR.string.cabin_kit_state_off),
                ) {
                    androidx.compose.material3.Icon(CabinIcons.Sync, contentDescription = null)
                    CabinLabel(sync)
                }
            }
        }
    }
}

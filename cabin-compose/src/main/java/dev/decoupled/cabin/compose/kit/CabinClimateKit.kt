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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compose.R
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.hvac.CabinClimateAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateState
import dev.decoupled.cabin.foundation.hvac.CabinClimateZone

/** Default °C formatting with one decimal ("21.5°"). Hosts pass their own for °F. */
fun formatCelsius(value: Float): String = "%.1f°".format(value)

/**
 * Temperature stepper for one zone. Single-tap, gated as `HvacQuick`.
 */
@Composable
fun CabinZoneTemperature(
    zone: CabinClimateZone,
    onAction: (CabinClimateAction) -> Unit,
    modifier: Modifier = Modifier,
    format: (Float) -> String = ::formatCelsius,
) {
    val value = cabinSignalText(zone.temperatureC, format)
    CabinStepper(
        value = value.text,
        valueDescription = stringResource(R.string.cabin_kit_climate_temp_value, zone.label, value.description),
        onDecrement = { onAction(CabinClimateAction.TemperatureStep(zone.id, -1)) },
        onIncrement = { onAction(CabinClimateAction.TemperatureStep(zone.id, +1)) },
        interaction = CabinInteraction.HvacQuick,
        decrementDescription = stringResource(R.string.cabin_kit_climate_temp_down, zone.label),
        incrementDescription = stringResource(R.string.cabin_kit_climate_temp_up, zone.label),
        modifier = modifier,
    )
}

/**
 * Seat heat button: icon plus level dots; each tap cycles the level.
 *
 * @param label optional text beside the icon (the Climate panel shows the zone name)
 */
@Composable
fun CabinSeatHeatButton(
    zone: CabinClimateZone,
    onAction: (CabinClimateAction) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    size: Dp = CabinKitMetrics.barControl,
) {
    val level = zone.seatHeatLevel.valueOrNull() ?: 0
    val description = if (level > 0) {
        stringResource(R.string.cabin_kit_climate_seat_level, zone.label, level, zone.seatHeatMax)
    } else {
        stringResource(R.string.cabin_kit_climate_seat_off, zone.label)
    }
    CabinToggleButton(
        checked = level > 0,
        onCheckedChange = { onAction(CabinClimateAction.SeatHeatCycle(zone.id)) },
        interaction = CabinInteraction.HvacQuick,
        modifier = (if (label == null) {
            modifier.size(
                size,
            )
        } else {
            modifier
        }).semantics { contentDescription = description },
        contentPadding = if (label == null) PaddingValues() else PaddingValues(horizontal = CabinKitMetrics.gapLg),
    ) {
        if (label == null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Icon(
                    CabinIcons.SeatHeat,
                    contentDescription = null,
                    modifier = Modifier.size(CabinKitMetrics.iconMd + 2.dp),
                )
                CabinLevelDots(level, zone.seatHeatMax)
            }
        } else {
            Icon(CabinIcons.SeatHeat, contentDescription = null, modifier = Modifier.size(CabinKitMetrics.iconMd))
            CabinLabel(label)
            CabinLevelDots(level, zone.seatHeatMax)
        }
    }
}

/** Fan selector: Auto, then levels 1..fanMax, on a segmented track. */
@Composable
fun CabinFanSelector(
    state: CabinClimateState,
    onAction: (CabinClimateAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val auto = stringResource(R.string.cabin_kit_climate_fan_auto)
    val options = listOf(0) + (1..state.fanMax).toList()
    val selected = if (state.fanAuto) 0 else state.fanLevel.valueOrNull()
    val levelDescriptions = (1..state.fanMax).associateWith {
        stringResource(R.string.cabin_kit_climate_fan_level, it.toString())
    }
    CabinSegmentedControl(
        options = options,
        selected = selected,
        onSelect = { if (it == 0) {
            onAction(
                CabinClimateAction.SetFanAuto,
            )
        } else {
            onAction(CabinClimateAction.SetFanLevel(it))
        } },
        interaction = CabinInteraction.HvacQuick,
        label = { if (it == 0) auto else it.toString() },
        optionDescription = { if (it == 0) auto else levelDescriptions.getValue(it) },
        modifier = modifier,
    )
}

/** Compact fan button for the system bar: shows Auto or the level. */
@Composable
fun CabinFanButton(
    state: CabinClimateState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    val off = stringResource(R.string.cabin_kit_climate_fan_off)
    val text = when {
        state.fanAuto -> stringResource(R.string.cabin_kit_climate_fan_auto)
        else -> cabinSignalText(state.fanLevel) { if (it == 0) off else it.toString() }.text
    }
    CabinButton(
        onClick = onClick,
        interaction = CabinInteraction.HvacQuick,
        modifier = modifier.size(height = CabinKitMetrics.barControl, width = CabinKitMetrics.barControl * 1.6f),
        style = CabinButtonStyle.Tonal,
        shape = look.shapes.control,
        contentDescription = "${stringResource(R.string.cabin_kit_climate_fan)}, $text",
        contentPadding = PaddingValues(horizontal = CabinKitMetrics.gapLg),
    ) {
        Icon(CabinIcons.Fan, contentDescription = null, modifier = Modifier.size(CabinKitMetrics.iconMd + 2.dp))
        CabinLabel(text)
    }
}

/** A climate mode toggle (A/C, recirculation, defrost …). */
@Composable
fun CabinClimateModeToggle(
    icon: ImageVector,
    label: String,
    checked: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = stringResource(if (checked) R.string.cabin_kit_state_on else R.string.cabin_kit_state_off)
    CabinToggleButton(
        checked = checked,
        onCheckedChange = { onToggle() },
        interaction = CabinInteraction.HvacQuick,
        modifier = modifier,
        stateDescription = state,
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(CabinKitMetrics.iconMd))
        CabinLabel(label)
    }
}

/** The standard mode set for [state], in display order. */
@Composable
fun cabinClimateModes(state: CabinClimateState): List<CabinClimateModeItem> = listOf(
    CabinClimateModeItem(
        CabinIcons.AirConditioning,
        stringResource(R.string.cabin_kit_climate_ac),
        state.acOn,
        CabinClimateAction.ToggleAc,
    ),
    CabinClimateModeItem(
        CabinIcons.Recirculation,
        stringResource(R.string.cabin_kit_climate_recirculation),
        state.recirculationOn,
        CabinClimateAction.ToggleRecirculation,
    ),
    CabinClimateModeItem(
        CabinIcons.Defrost,
        stringResource(R.string.cabin_kit_climate_front_defrost),
        state.frontDefrostOn,
        CabinClimateAction.ToggleFrontDefrost,
    ),
    CabinClimateModeItem(
        CabinIcons.RearDefrost,
        stringResource(R.string.cabin_kit_climate_rear_defrost),
        state.rearDefrostOn,
        CabinClimateAction.ToggleRearDefrost,
    ),
)

/** One climate mode: icon, label, state and the action that toggles it. */
data class CabinClimateModeItem(
    val icon: ImageVector,
    val label: String,
    val on: Boolean,
    val action: CabinClimateAction,
)

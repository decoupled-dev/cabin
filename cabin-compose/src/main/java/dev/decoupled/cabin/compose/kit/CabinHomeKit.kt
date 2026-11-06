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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compose.R
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.ev.CabinEnergyState
import dev.decoupled.cabin.foundation.maps.CabinManeuver
import dev.decoupled.cabin.foundation.maps.CabinNextTurnState

/** Icon for a [CabinManeuver]. */
fun CabinManeuver.icon(): ImageVector = when (this) {
    CabinManeuver.Straight -> CabinIcons.Straight
    CabinManeuver.TurnLeft -> CabinIcons.TurnLeft
    CabinManeuver.TurnRight -> CabinIcons.TurnRight
    CabinManeuver.Arrive -> CabinIcons.Arrive
}

/**
 * Next-turn card that floats over a map: maneuver chip, distance,
 * instruction. Read-only (`Glance`).
 */
@Composable
fun CabinNextTurnCard(
    state: CabinNextTurnState,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    Surface(
        modifier = modifier.semantics(mergeDescendants = true) {},
        shape = look.shapes.large,
        color = look.colors.surface,
        contentColor = look.colors.onSurface,
        border = look.edgeBorder(),
    ) {
        Row(
            Modifier.padding(start = 18.dp, top = 18.dp, bottom = 18.dp, end = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.size(72.dp),
                shape = look.shapes.control,
                color = look.colors.primary,
                contentColor = look.colors.onPrimary,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(state.maneuver.icon(), contentDescription = null, modifier = Modifier.size(40.dp))
                }
            }
            Column {
                Text(state.distanceText, style = look.type.numeralMedium)
                Text(state.instruction, style = look.type.body, color = look.colors.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

/** Trip summary pill: time to arrival, then distance and arrival time. */
@Composable
fun CabinEtaChip(
    etaText: String,
    tripText: String,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    Surface(
        modifier = modifier.semantics(mergeDescendants = true) {},
        shape = look.shapes.control,
        color = look.colors.surface,
        contentColor = look.colors.onSurface,
        border = look.edgeBorder(),
    ) {
        Row(
            Modifier.padding(horizontal = 28.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(etaText, style = look.type.titleSmall)
            Text(tripText, style = look.type.body, color = look.colors.onSurfaceVariant)
        }
    }
}

/**
 * Battery card: large percentage, level bar, range line. The bar uses the
 * locked warning color at or below the low threshold and the charging color
 * while charging.
 */
@Composable
fun CabinEnergyCard(
    state: CabinEnergyState,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    val percent = state.batteryPercent.valueOrNull()
    val percentText = cabinSignalText(state.batteryPercent) { "$it%" }
    val range = cabinSignalText(state.rangeText) { it }
    val low = percent != null && percent <= state.lowThresholdPercent
    val barColor = when {
        state.charging -> look.colors.charging
        low -> look.colors.warning
        else -> look.colors.onSurface
    }
    val label = stringResource(
        when {
            state.charging -> R.string.cabin_kit_energy_charging
            low -> R.string.cabin_kit_energy_low
            else -> R.string.cabin_kit_energy_battery
        },
    )
    CabinPanel(
        modifier.semantics(mergeDescendants = true) {
            contentDescription = "$label ${percentText.description}, ${range.description}"
        },
        contentPadding = PaddingValues(horizontal = 28.dp, vertical = CabinKitMetrics.gapLg),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Text(percentText.text, style = look.type.numeralLarge)
            CabinLabel(
                label,
                color = when {
                    state.charging -> look.colors.charging
                    low -> look.colors.warning
                    else -> look.colors.onSurfaceMuted
                },
            )
        }
        CabinProgressBar((percent ?: 0) / 100f, color = barColor, height = CabinKitMetrics.progressThick)
        Text(
            listOfNotNull(range.text, state.note).joinToString(" · "),
            style = look.type.body,
            color = look.colors.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Quick-control tile (lock, defrost, lights …): icon top-left, label
 * bottom-left, drawn in the look's active style when on.
 */
@Composable
fun CabinQuickControlTile(
    icon: ImageVector,
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    interaction: CabinInteraction,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    val state = stringResource(if (checked) R.string.cabin_kit_state_on else R.string.cabin_kit_state_off)
    CabinToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        interaction = interaction,
        modifier = modifier.semantics { stateDescription = state },
        shape = look.shapes.large,
        restingContainer = look.colors.surface,
        contentPadding = PaddingValues(0.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp))
            Text(label, style = look.type.body.copy(fontWeight = look.type.label.fontWeight), maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

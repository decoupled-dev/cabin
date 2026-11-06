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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.theme.CabinTheme

/** Connectivity indicator for [CabinStatusRow]. */
@Immutable
data class CabinStatusIndicator(val icon: ImageVector, val description: String)

/**
 * Top status row (S1): time and outside temperature on the left,
 * connectivity and profile on the right. Read-only.
 */
@Composable
fun CabinStatusRow(
    timeText: String,
    modifier: Modifier = Modifier,
    outsideTemperatureText: String? = null,
    indicators: List<CabinStatusIndicator> = emptyList(),
    profileInitial: String? = null,
    profileDescription: String? = null,
) {
    val look = CabinTheme.look
    Row(
        modifier
            .fillMaxWidth()
            .height(CabinKitMetrics.statusBar)
            .padding(horizontal = CabinKitMetrics.gapXl),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(timeText, style = look.type.titleSmall)
            if (outsideTemperatureText != null) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        CabinIcons.Sun,
                        contentDescription = null,
                        tint = look.colors.onSurfaceVariant,
                        modifier = Modifier.size(CabinKitMetrics.iconSm),
                    )
                    Text(outsideTemperatureText, style = look.type.body, color = look.colors.onSurfaceVariant)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
            indicators.forEach {
                Icon(
                    it.icon,
                    contentDescription = it.description,
                    tint = look.colors.onSurfaceVariant,
                    modifier = Modifier.size(CabinKitMetrics.iconMd),
                )
            }
            if (profileInitial != null) {
                Surface(
                    modifier = Modifier
                        .size(CabinKitMetrics.avatar)
                        .semantics { if (profileDescription != null) contentDescription = profileDescription },
                    shape = look.shapes.control,
                    color = look.colors.activeContainer,
                    contentColor = look.colors.onActiveContainer,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(profileInitial, style = look.type.label)
                    }
                }
            }
        }
    }
}

/** One destination in [CabinDock]. */
@Immutable
data class CabinDockItem(
    val key: String,
    val label: String,
    val icon: ImageVector,
    /** Opening most destinations is simple navigation; app launchers may be complex. */
    val interaction: CabinInteraction = CabinInteraction.NavigateSimple,
)

/**
 * App dock (N1, pill dock): icons only, the selected destination expands to
 * show its label in the look's active style.
 */
@Composable
fun CabinDock(
    items: List<CabinDockItem>,
    selectedKey: String?,
    onSelect: (CabinDockItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    Surface(
        modifier = modifier.height(CabinKitMetrics.barControl),
        shape = look.shapes.control,
        color = look.colors.surface,
        border = look.edgeBorder(),
    ) {
        Row(
            Modifier.padding(horizontal = CabinKitMetrics.trackInset),
            horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapXs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                val selected = item.key == selectedKey
                val enabled = cabinGate(item.interaction) == GateDisposition.Allow
                val colors = cabinActiveColors(selected, Color.Transparent, look.colors.onSurfaceVariant)
                Surface(
                    selected = selected,
                    onClick = { onSelect(item) },
                    enabled = enabled,
                    modifier = Modifier
                        .height(CabinKitMetrics.touchMin + CabinKitMetrics.gapXs)
                        .widthIn(min = CabinKitMetrics.controlLarge)
                        .semantics {
                            role = Role.Tab
                            this.selected = selected
                            contentDescription = item.label
                        },
                    shape = look.shapes.control,
                    color = colors.container,
                    contentColor = if (enabled) colors.content else colors.content.copy(alpha = DisabledAlpha),
                ) {
                    Row(
                        Modifier
                            .activeIndicator(colors.indicator)
                            .padding(PaddingValues(horizontal = if (selected) 28.dp else 0.dp)),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(item.icon, contentDescription = null, modifier = Modifier.size(30.dp))
                        if (selected) CabinLabel(item.label)
                    }
                }
            }
        }
    }
}

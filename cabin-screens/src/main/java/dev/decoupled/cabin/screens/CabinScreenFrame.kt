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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compose.kit.CabinBackdrop
import dev.decoupled.cabin.compose.kit.CabinDock
import dev.decoupled.cabin.compose.kit.CabinDockItem
import dev.decoupled.cabin.compose.kit.CabinFanButton
import dev.decoupled.cabin.compose.kit.CabinIcons
import dev.decoupled.cabin.compose.kit.CabinKitMetrics
import dev.decoupled.cabin.compose.kit.CabinSeatHeatButton
import dev.decoupled.cabin.compose.kit.CabinStatusIndicator
import dev.decoupled.cabin.compose.kit.CabinStatusRow
import dev.decoupled.cabin.compose.kit.CabinZoneTemperature
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.hvac.CabinClimateAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateState

/** Status row content for [CabinScreenFrame]. */
@Immutable
data class CabinStatusContent(
    val timeText: String,
    val outsideTemperatureText: String? = null,
    val indicators: List<CabinStatusIndicator> = emptyList(),
    val profileInitial: String? = null,
    val profileDescription: String? = null,
)

/** Destinations used by the reference screens' dock. */
object CabinDestinations {
    const val Home: String = "home"
    const val Maps: String = "maps"
    const val Media: String = "media"
    const val Phone: String = "phone"
    const val Climate: String = "climate"
    const val Apps: String = "apps"

    /** Default dock: Home, Maps, Media, Phone, Climate, with localized labels. */
    @Composable
    fun defaultDock(): List<CabinDockItem> = listOf(
        CabinDockItem(Home, stringResource(R.string.cabin_screens_dest_home), CabinIcons.Home),
        CabinDockItem(Maps, stringResource(R.string.cabin_screens_dest_maps), CabinIcons.Navigation),
        CabinDockItem(Media, stringResource(R.string.cabin_screens_dest_media), CabinIcons.Music),
        CabinDockItem(Phone, stringResource(R.string.cabin_screens_dest_phone), CabinIcons.Phone),
        CabinDockItem(Climate, stringResource(R.string.cabin_screens_dest_climate), CabinIcons.Fan),
    )
}

/**
 * Shared shell for the reference screens on a wide center display:
 * status row, [content], and the system bar (driver climate, dock, fan,
 * passenger climate). Climate controls are single-tap (`HvacQuick`), so they
 * stay live in every drive state.
 */
@Composable
fun CabinScreenFrame(
    status: CabinStatusContent,
    climate: CabinClimateState,
    onClimateAction: (CabinClimateAction) -> Unit,
    dockItems: List<CabinDockItem>,
    selectedDestination: String?,
    onDestinationSelect: (CabinDockItem) -> Unit,
    modifier: Modifier = Modifier,
    onFanClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    CabinBackdrop(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            CabinStatusRow(
                timeText = status.timeText,
                outsideTemperatureText = status.outsideTemperatureText,
                indicators = status.indicators,
                profileInitial = status.profileInitial,
                profileDescription = status.profileDescription,
            )
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = CabinKitMetrics.gapMd, end = CabinKitMetrics.gapMd, top = CabinKitMetrics.gapXs, bottom = CabinKitMetrics.gapSm),
                content = content,
            )
            CabinSystemBar(
                climate = climate,
                onClimateAction = onClimateAction,
                dockItems = dockItems,
                selectedDestination = selectedDestination,
                onDestinationSelect = onDestinationSelect,
                onFanClick = onFanClick,
            )
        }
    }
}

/** Bottom system bar used by [CabinScreenFrame]. */
@Composable
fun CabinSystemBar(
    climate: CabinClimateState,
    onClimateAction: (CabinClimateAction) -> Unit,
    dockItems: List<CabinDockItem>,
    selectedDestination: String?,
    onDestinationSelect: (CabinDockItem) -> Unit,
    modifier: Modifier = Modifier,
    onFanClick: () -> Unit = {},
) {
    val driver = climate.zones.getOrNull(0)
    val passenger = climate.zones.getOrNull(1)
    Row(
        modifier
            .fillMaxWidth()
            .height(CabinKitMetrics.systemBar)
            .padding(start = CabinKitMetrics.gapMd, end = CabinKitMetrics.gapMd, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            if (driver != null) {
                CabinZoneTemperature(driver, onClimateAction)
                CabinSeatHeatButton(driver, onClimateAction)
            }
        }
        CabinDock(dockItems, selectedDestination, onDestinationSelect)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            CabinFanButton(climate, onClick = onFanClick)
            if (passenger != null) CabinZoneTemperature(passenger, onClimateAction)
        }
    }
}

/**
 * Stand-in map drawn from the look's colors, for demos and previews. Real
 * products put their map provider's view in the map slot instead.
 */
@Composable
fun CabinSampleMap(modifier: Modifier = Modifier) {
    val look = CabinTheme.look
    val c = look.colors
    // Composite translucent (Glass) surfaces over the background first.
    val surface = c.surface.compositeOver(c.background)
    val surfaceHigh = c.surfaceHigh.compositeOver(surface)
    val ground = lerp(c.background, surface, 0.55f)
    val block = lerp(surface, surfaceHigh, 0.35f)
    val road = surfaceHigh
    val water = lerp(ground, c.primary, 0.10f)
    val radius = look.spec.cornerMediumDp
    Canvas(modifier.fillMaxSize().clearAndSetSemantics {}) {
        val sx = size.width / 1000f
        val sy = size.height / 560f
        fun p(x: Float, y: Float) = Offset(x * sx, y * sy)
        drawRect(ground)
        drawPath(
            Path().apply {
                moveTo(650 * sx, 0f)
                cubicTo(710 * sx, 120 * sy, 830 * sx, 170 * sy, 1000 * sx, 160 * sy)
                lineTo(1000 * sx, 0f)
                close()
            },
            water,
        )
        listOf(
            floatArrayOf(60f, 260f, 180f, 110f), floatArrayOf(300f, 70f, 210f, 120f), floatArrayOf(580f, 300f, 150f, 150f),
            floatArrayOf(780f, 240f, 190f, 100f), floatArrayOf(320f, 410f, 170f, 110f),
        ).forEach { (x, y, w, h) ->
            drawRoundRect(block, topLeft = p(x, y), size = Size(w * sx, h * sy), cornerRadius = CornerRadius(radius.dp.toPx()))
        }
        fun roadLine(width: Float, vararg pts: Float) {
            val path = Path().apply {
                moveTo(pts[0] * sx, pts[1] * sy)
                var i = 2
                while (i < pts.size) { lineTo(pts[i] * sx, pts[i + 1] * sy); i += 2 }
            }
            drawPath(path, road, style = Stroke(width = width * sx, cap = StrokeCap.Round))
        }
        roadLine(20f, 0f, 225f, 1000f, 215f)
        roadLine(16f, 270f, 0f, 280f, 560f)
        roadLine(24f, 545f, 560f, 550f, 0f)
        roadLine(14f, 0f, 390f, 500f, 388f, 1000f, 370f)
        roadLine(14f, 755f, 560f, 765f, 225f)
        drawPath(
            Path().apply { moveTo(546 * sx, 560 * sy); lineTo(549 * sx, 220 * sy); lineTo(1000 * sx, 212 * sy) },
            c.primary,
            style = Stroke(width = 12f * sx, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawCircle(c.primary.copy(alpha = 0.25f), radius = 30f * sx, center = p(547f, 440f))
        drawCircle(ground, radius = 21f * sx, center = p(547f, 440f))
        drawCircle(c.primary, radius = 16f * sx, center = p(547f, 440f))
    }
}

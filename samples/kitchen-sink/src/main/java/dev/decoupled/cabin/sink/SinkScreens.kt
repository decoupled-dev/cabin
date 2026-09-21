package dev.decoupled.cabin.sink

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compose.CabinClimateTile
import dev.decoupled.cabin.compose.CabinMediaNowPlaying
import dev.decoupled.cabin.compose.CabinStatusBar
import dev.decoupled.cabin.compose.CabinSystemBar
import dev.decoupled.cabin.compose.ev.CabinChargeLimitSlider
import dev.decoupled.cabin.compose.ev.CabinChargeSessionCard
import dev.decoupled.cabin.compose.ev.CabinRangeEstimator
import dev.decoupled.cabin.compose.hvac.CabinAirflowDirection
import dev.decoupled.cabin.compose.hvac.CabinFanSpeed
import dev.decoupled.cabin.compose.layout.CabinAdaptiveScaffold
import dev.decoupled.cabin.compose.media.CabinMiniPlayer
import dev.decoupled.cabin.compose.media.CabinQueueList
import dev.decoupled.cabin.compose.settings.CabinPreference
import dev.decoupled.cabin.compose.settings.CabinSettingsSearch
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.compose.vehicle.CabinDoorControl
import dev.decoupled.cabin.compose.vehicle.CabinLockControl
import dev.decoupled.cabin.compose.vehicle.CabinWindowControl
import dev.decoupled.cabin.foundation.CabinDisplayProfile
import dev.decoupled.cabin.foundation.CabinScaffold
import dev.decoupled.cabin.foundation.CabinWindowSizeClass

@Composable
internal fun SinkScreenIndex(onRoute: (SinkRoute) -> Unit) {
    val colors = LocalCabinColors.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("sink_screens"),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        BasicText(
            text = "Screens",
            style = TextStyle(
                color = colors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        SinkScreensCatalog.all.forEach { spec ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, colors.outline.copy(alpha = 0.4f))
                    .clickable { onRoute(SinkRoute.Screen(spec.id)) }
                    .padding(14.dp)
                    .testTag("screen_${spec.id}"),
            ) {
                BasicText(
                    text = spec.title,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
                BasicText(
                    text = spec.summary,
                    style = TextStyle(color = colors.outline, fontSize = 13.sp),
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
    }
}

@OptIn(CabinScaffold::class)
@Composable
internal fun SinkComposedScreen(
    id: String,
    onAction: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("sink_screen_$id"),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        when (id) {
            "dashboard" -> DashboardScreen(onAction)
            "media" -> MediaScreen(onAction)
            "climate" -> ClimateScreen(onAction)
            "charge" -> ChargeScreen(onAction)
            "settings" -> SettingsScreen(onAction)
            "vehicle" -> VehicleScreen(onAction)
        }
    }
}

@OptIn(CabinScaffold::class)
@Composable
private fun DashboardScreen(onAction: (String) -> Unit) {
    val colors = LocalCabinColors.current
    CabinSystemBar(slots = SinkFixtures.systemBarSlots(onAction))
    CabinStatusBar(items = SinkFixtures.statusItems(onAction))
    CabinAdaptiveScaffold(
        profile = CabinDisplayProfile(sizeClass = CabinWindowSizeClass.StandardLandscape),
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .border(1.dp, colors.outline.copy(alpha = 0.3f)),
        primary = {
            CabinMediaNowPlaying(
                state = SinkFixtures.mediaLive(),
                onAction = { onAction("Dashboard media · $it") },
            )
        },
        secondary = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CabinClimateTile(
                    state = SinkFixtures.climateLive(),
                    onAction = { onAction("Dashboard climate · $it") },
                )
                CabinChargeSessionCard(onAction = { onAction("Dashboard charge") })
            }
        },
    )
}

@OptIn(CabinScaffold::class)
@Composable
private fun MediaScreen(onAction: (String) -> Unit) {
    CabinMediaNowPlaying(
        state = SinkFixtures.mediaLive(),
        onAction = { onAction("Media · $it") },
    )
    CabinMiniPlayer(onAction = { onAction("Mini player") })
    CabinQueueList(onAction = { onAction("Queue") })
}

@OptIn(CabinScaffold::class)
@Composable
private fun ClimateScreen(onAction: (String) -> Unit) {
    CabinClimateTile(
        state = SinkFixtures.climateLive(),
        onAction = { onAction("Climate · $it") },
    )
    CabinFanSpeed(onAction = { onAction("Fan") })
    CabinAirflowDirection(onAction = { onAction("Airflow") })
}

@OptIn(CabinScaffold::class)
@Composable
private fun ChargeScreen(onAction: (String) -> Unit) {
    CabinRangeEstimator(onAction = { onAction("Range") })
    CabinChargeSessionCard(onAction = { onAction("Session") })
    CabinChargeLimitSlider(onAction = { onAction("Limit") })
}

@OptIn(CabinScaffold::class)
@Composable
private fun SettingsScreen(onAction: (String) -> Unit) {
    CabinAdaptiveScaffold(
        profile = CabinDisplayProfile(sizeClass = CabinWindowSizeClass.LargeLandscape),
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        primary = {
            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CabinSettingsSearch(onAction = { onAction("Search") })
                CabinPreference(onAction = { onAction("Display") })
            }
        },
        secondary = {
            CabinPreference(onAction = { onAction("Sound") })
        },
    )
}

@OptIn(CabinScaffold::class)
@Composable
private fun VehicleScreen(onAction: (String) -> Unit) {
    CabinLockControl(onAction = { onAction("Lock") })
    CabinDoorControl(onAction = { onAction("Door") })
    CabinWindowControl(onAction = { onAction("Window") })
}

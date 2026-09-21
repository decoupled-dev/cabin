package dev.decoupled.cabin.sink

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinColorScheme

@Composable
fun KitchenSinkApp(
    colorScheme: CabinColorScheme,
    onColorSchemeChange: (CabinColorScheme) -> Unit,
    vehicleState: VehicleUiState,
    onVehicleStateChange: (VehicleUiState) -> Unit,
    route: SinkRoute,
    onRoute: (SinkRoute) -> Unit,
    lastAction: String?,
    onAction: (String) -> Unit,
) {
    CabinTheme(colorScheme = colorScheme, vehicleState = vehicleState) {
        val colors = LocalCabinColors.current
        val mode = CabinRestrictionEngine.Default.uiMode(vehicleState)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surface)
                .testTag("sink_root"),
        ) {
            SinkHeader(
                route = route,
                onRoute = onRoute,
                lastAction = lastAction,
                mode = mode.toString(),
            )
            SinkToolbar(
                colorScheme = colorScheme,
                onColorSchemeChange = onColorSchemeChange,
                vehicleState = vehicleState,
                onVehicleStateChange = onVehicleStateChange,
            )
            Box(modifier = Modifier.weight(1f)) {
                when (route) {
                    SinkRoute.Home -> SinkHome(onRoute = onRoute)
                    SinkRoute.Components -> SinkBrowser(onRoute = onRoute)
                    is SinkRoute.Inspector -> SinkInspector(
                        id = route.id,
                        onAction = onAction,
                    )
                    SinkRoute.Screens -> SinkScreenIndex(onRoute = onRoute)
                    is SinkRoute.Screen -> SinkComposedScreen(
                        id = route.id,
                        onAction = onAction,
                    )
                }
            }
        }
    }
}

@Composable
private fun SinkHeader(
    route: SinkRoute,
    onRoute: (SinkRoute) -> Unit,
    lastAction: String?,
    mode: String,
) {
    val colors = LocalCabinColors.current
    val canBack = route !is SinkRoute.Home
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.container)
            .padding(horizontal = 20.dp, vertical = 14.dp)
            .testTag("sink_header"),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BasicText(
                text = stringResource(R.string.sink_app_name),
                style = TextStyle(
                    color = colors.onContainer,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
            if (canBack) {
                Box(
                    modifier = Modifier
                        .border(1.dp, colors.outline)
                        .clickable {
                            onRoute(
                                when (route) {
                                    is SinkRoute.Inspector -> SinkRoute.Components
                                    is SinkRoute.Screen -> SinkRoute.Screens
                                    else -> SinkRoute.Home
                                },
                            )
                        }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .testTag("sink_back"),
                ) {
                    BasicText(
                        text = "Back",
                        style = TextStyle(color = colors.onContainer, fontSize = 13.sp),
                    )
                }
            }
        }
        BasicText(
            text = stringResource(R.string.sink_tagline),
            style = TextStyle(color = colors.outline, fontSize = 13.sp),
            modifier = Modifier.padding(top = 4.dp),
        )
        BasicText(
            text = "Mode: $mode",
            style = TextStyle(color = colors.onContainer, fontSize = 13.sp),
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag("sink_mode"),
        )
        BasicText(
            text = lastAction?.let { "Last: $it" } ?: "No activations yet",
            style = TextStyle(color = colors.outline, fontSize = 13.sp),
            modifier = Modifier.testTag("sink_last_action"),
        )
    }
}

@Composable
private fun SinkToolbar(
    colorScheme: CabinColorScheme,
    onColorSchemeChange: (CabinColorScheme) -> Unit,
    vehicleState: VehicleUiState,
    onVehicleStateChange: (VehicleUiState) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SinkChoiceRow(
            options = listOf(
                CabinColorScheme.Day to "Day",
                CabinColorScheme.Night to "Night",
            ),
            selectedValue = colorScheme,
            onSelect = onColorSchemeChange,
            testTagPrefix = "theme",
        )
        SinkChoiceRow(
            options = listOf(
                VehicleUiState.parked() to "Parked",
                VehicleUiState.moving() to "Moving",
            ),
            selectedValue = vehicleState,
            onSelect = onVehicleStateChange,
            testTagPrefix = "restriction",
            selectedEquals = { a, b -> a.label == b.label },
        )
    }
}

@Composable
internal fun SinkHome(onRoute: (SinkRoute) -> Unit) {
    val colors = LocalCabinColors.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("sink_home"),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        HomeCard(
            title = "Components",
            body = "Inspect every inventory row: states, variants, Restriction Engine.",
            tag = "sink_open_components",
            onClick = { onRoute(SinkRoute.Components) },
        )
        HomeCard(
            title = "Screens",
            body = "Cabin surfaces built from the kit — dashboard, media, climate, charge.",
            tag = "sink_open_screens",
            onClick = { onRoute(SinkRoute.Screens) },
        )
        BasicText(
            text = "${SinkRegistry.entries.size} components · ${SinkScreensCatalog.all.size} screens",
            style = TextStyle(color = colors.outline, fontSize = 13.sp),
        )
    }
}

@Composable
private fun HomeCard(
    title: String,
    body: String,
    tag: String,
    onClick: () -> Unit,
) {
    val colors = LocalCabinColors.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colors.outline.copy(alpha = 0.45f))
            .clickable(onClick = onClick)
            .padding(16.dp)
            .testTag(tag),
    ) {
        BasicText(
            text = title,
            style = TextStyle(
                color = colors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        BasicText(
            text = body,
            style = TextStyle(color = colors.outline, fontSize = 14.sp),
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}

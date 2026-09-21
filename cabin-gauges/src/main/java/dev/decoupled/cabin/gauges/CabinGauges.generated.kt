@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.gauges

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.gauges.scaffold.CabinGaugeHost
import dev.decoupled.cabin.foundation.components.gauges.*

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRadialGauge(
    state: CabinRadialGaugeState = CabinRadialGaugeState(),
    onAction: (CabinRadialGaugeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Radial gauge" },
        testTag = "cabin_radial_gauge",
        interaction = CabinRadialGaugeInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinRadialGaugeAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLinearGauge(
    state: CabinLinearGaugeState = CabinLinearGaugeState(),
    onAction: (CabinLinearGaugeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Linear gauge" },
        testTag = "cabin_linear_gauge",
        interaction = CabinLinearGaugeInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinLinearGaugeAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinArcGauge(
    state: CabinArcGaugeState = CabinArcGaugeState(),
    onAction: (CabinArcGaugeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Arc gauge" },
        testTag = "cabin_arc_gauge",
        interaction = CabinArcGaugeInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinArcGaugeAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSegmentedGauge(
    state: CabinSegmentedGaugeState = CabinSegmentedGaugeState(),
    onAction: (CabinSegmentedGaugeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Segmented gauge" },
        testTag = "cabin_segmented_gauge",
        interaction = CabinSegmentedGaugeInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinSegmentedGaugeAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSpeedometer(
    state: CabinSpeedometerState = CabinSpeedometerState(),
    onAction: (CabinSpeedometerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Speedometer" },
        testTag = "cabin_speedometer",
        interaction = CabinSpeedometerInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinSpeedometerAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTachometer(
    state: CabinTachometerState = CabinTachometerState(),
    onAction: (CabinTachometerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Tachometer" },
        testTag = "cabin_tachometer",
        interaction = CabinTachometerInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinTachometerAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPowerMeter(
    state: CabinPowerMeterState = CabinPowerMeterState(),
    onAction: (CabinPowerMeterAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Power meter" },
        testTag = "cabin_power_meter",
        interaction = CabinPowerMeterInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinPowerMeterAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinGForce(
    state: CabinGForceState = CabinGForceState(),
    onAction: (CabinGForceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "G-force display" },
        testTag = "cabin_g_force",
        interaction = CabinGForceInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinGForceAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNeedle(
    state: CabinNeedleState = CabinNeedleState(),
    onAction: (CabinNeedleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Needle primitive" },
        testTag = "cabin_needle",
        interaction = CabinNeedleInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinNeedleAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTickScale(
    state: CabinTickScaleState = CabinTickScaleState(),
    onAction: (CabinTickScaleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Tick / scale primitive" },
        testTag = "cabin_tick_scale",
        interaction = CabinTickScaleInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinTickScaleAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinGearIndicator(
    state: CabinGearIndicatorState = CabinGearIndicatorState(),
    onAction: (CabinGearIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Gear indicator" },
        testTag = "cabin_gear_indicator",
        interaction = CabinGearIndicatorInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinGearIndicatorAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDigitalSpeed(
    state: CabinDigitalSpeedState = CabinDigitalSpeedState(),
    onAction: (CabinDigitalSpeedAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Digital speed" },
        testTag = "cabin_digital_speed",
        interaction = CabinDigitalSpeedInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinDigitalSpeedAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSpeedLimitIndicator(
    state: CabinSpeedLimitIndicatorState = CabinSpeedLimitIndicatorState(),
    onAction: (CabinSpeedLimitIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Speed-limit indicator" },
        testTag = "cabin_speed_limit_indicator",
        interaction = CabinSpeedLimitIndicatorInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinSpeedLimitIndicatorAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTelltaleStrip(
    state: CabinTelltaleStripState = CabinTelltaleStripState(),
    onAction: (CabinTelltaleStripAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Telltale strip" },
        testTag = "cabin_telltale_strip",
        interaction = CabinTelltaleStripInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinTelltaleStripAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWarningOverlay(
    state: CabinWarningOverlayState = CabinWarningOverlayState(),
    onAction: (CabinWarningOverlayAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Warning overlay" },
        testTag = "cabin_warning_overlay",
        interaction = CabinWarningOverlayInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinWarningOverlayAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinClusterInfoPanel(
    state: CabinClusterInfoPanelState = CabinClusterInfoPanelState(),
    onAction: (CabinClusterInfoPanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Cluster info panel" },
        testTag = "cabin_cluster_info_panel",
        interaction = CabinClusterInfoPanelInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinClusterInfoPanelAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinClusterModeSwitcher(
    state: CabinClusterModeSwitcherState = CabinClusterModeSwitcherState(),
    onAction: (CabinClusterModeSwitcherAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Cluster mode switcher" },
        testTag = "cabin_cluster_mode_switcher",
        interaction = CabinClusterModeSwitcherInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinClusterModeSwitcherAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHudPrimitive(
    state: CabinHudPrimitiveState = CabinHudPrimitiveState(),
    onAction: (CabinHudPrimitiveAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "HUD primitive" },
        testTag = "cabin_hud_primitive",
        interaction = CabinHudPrimitiveInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinHudPrimitiveAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinClusterThemeSet(
    state: CabinClusterThemeSetState = CabinClusterThemeSetState(),
    onAction: (CabinClusterThemeSetAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinGaugeHost(
        title = state.label.ifBlank { "Cluster theme set" },
        testTag = "cabin_cluster_theme_set",
        interaction = CabinClusterThemeSetInteraction,
        ui = state.ui,
        onActivate = { onAction(CabinClusterThemeSetAction.Activate) },
        modifier = modifier,
        family = "gauges",
    )
}

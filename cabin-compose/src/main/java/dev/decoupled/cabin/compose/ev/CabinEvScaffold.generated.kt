@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.ev

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.ev.*

/**
 * Experimental scaffold: Range estimator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRangeEstimator(
    state: CabinRangeEstimatorState = CabinRangeEstimatorState(),
    onAction: (CabinRangeEstimatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Range estimator" },
        family = "ev",
        testTag = "cabin_range_estimator",
        interaction = CabinRangeEstimatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRangeEstimatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Range on map.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRangeOnMap(
    state: CabinRangeOnMapState = CabinRangeOnMapState(),
    onAction: (CabinRangeOnMapAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Range on map" },
        family = "ev",
        testTag = "cabin_range_on_map",
        interaction = CabinRangeOnMapInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRangeOnMapAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Charge session card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargeSessionCard(
    state: CabinChargeSessionCardState = CabinChargeSessionCardState(),
    onAction: (CabinChargeSessionCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Charge session card" },
        family = "ev",
        testTag = "cabin_charge_session_card",
        interaction = CabinChargeSessionCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargeSessionCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Charge limit slider.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargeLimitSlider(
    state: CabinChargeLimitSliderState = CabinChargeLimitSliderState(),
    onAction: (CabinChargeLimitSliderAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Charge limit slider" },
        family = "ev",
        testTag = "cabin_charge_limit_slider",
        interaction = CabinChargeLimitSliderInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargeLimitSliderAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Charging schedule.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargingSchedule(
    state: CabinChargingScheduleState = CabinChargingScheduleState(),
    onAction: (CabinChargingScheduleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Charging schedule" },
        family = "ev",
        testTag = "cabin_charging_schedule",
        interaction = CabinChargingScheduleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargingScheduleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Charge-port status.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargePortStatus(
    state: CabinChargePortStatusState = CabinChargePortStatusState(),
    onAction: (CabinChargePortStatusAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Charge-port status" },
        family = "ev",
        testTag = "cabin_charge_port_status",
        interaction = CabinChargePortStatusInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargePortStatusAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Charging-station card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargingStationCard(
    state: CabinChargingStationCardState = CabinChargingStationCardState(),
    onAction: (CabinChargingStationCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Charging-station card" },
        family = "ev",
        testTag = "cabin_charging_station_card",
        interaction = CabinChargingStationCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargingStationCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Connector-type chip.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinConnectorTypeChip(
    state: CabinConnectorTypeChipState = CabinConnectorTypeChipState(),
    onAction: (CabinConnectorTypeChipAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Connector-type chip" },
        family = "ev",
        testTag = "cabin_connector_type_chip",
        interaction = CabinConnectorTypeChipInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinConnectorTypeChipAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Energy flow diagram.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEnergyFlowDiagram(
    state: CabinEnergyFlowDiagramState = CabinEnergyFlowDiagramState(),
    onAction: (CabinEnergyFlowDiagramAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Energy flow diagram" },
        family = "ev",
        testTag = "cabin_energy_flow_diagram",
        interaction = CabinEnergyFlowDiagramInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEnergyFlowDiagramAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Consumption chart.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinConsumptionChart(
    state: CabinConsumptionChartState = CabinConsumptionChartState(),
    onAction: (CabinConsumptionChartAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Consumption chart" },
        family = "ev",
        testTag = "cabin_consumption_chart",
        interaction = CabinConsumptionChartInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinConsumptionChartAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Regen indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRegenIndicator(
    state: CabinRegenIndicatorState = CabinRegenIndicatorState(),
    onAction: (CabinRegenIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Regen indicator" },
        family = "ev",
        testTag = "cabin_regen_indicator",
        interaction = CabinRegenIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRegenIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Precondition for charging.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargePrecondition(
    state: CabinChargePreconditionState = CabinChargePreconditionState(),
    onAction: (CabinChargePreconditionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Precondition for charging" },
        family = "ev",
        testTag = "cabin_charge_precondition",
        interaction = CabinChargePreconditionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargePreconditionAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Trip charge planner.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTripChargePlanner(
    state: CabinTripChargePlannerState = CabinTripChargePlannerState(),
    onAction: (CabinTripChargePlannerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Trip charge planner" },
        family = "ev",
        testTag = "cabin_trip_charge_planner",
        interaction = CabinTripChargePlannerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTripChargePlannerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: V2L / V2H controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinV2xControls(
    state: CabinV2xControlsState = CabinV2xControlsState(),
    onAction: (CabinV2xControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "V2L / V2H controls" },
        family = "ev",
        testTag = "cabin_v2x_controls",
        interaction = CabinV2xControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinV2xControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Fuel level.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFuelLevel(
    state: CabinFuelLevelState = CabinFuelLevelState(),
    onAction: (CabinFuelLevelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Fuel level" },
        family = "ev",
        testTag = "cabin_fuel_level",
        interaction = CabinFuelLevelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFuelLevelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Hybrid range breakdown.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHybridRangeBreakdown(
    state: CabinHybridRangeBreakdownState = CabinHybridRangeBreakdownState(),
    onAction: (CabinHybridRangeBreakdownAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Hybrid range breakdown" },
        family = "ev",
        testTag = "cabin_hybrid_range_breakdown",
        interaction = CabinHybridRangeBreakdownInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHybridRangeBreakdownAction.Activate) },
        modifier = modifier,
    )
}

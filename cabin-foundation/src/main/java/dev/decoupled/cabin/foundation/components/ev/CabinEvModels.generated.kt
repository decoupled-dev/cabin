@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.ev

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinRangeEstimatorState(
    val label: String = "Range estimator",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRangeEstimatorAction {
    data object Activate : CabinRangeEstimatorAction
}

val CabinRangeEstimatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinRangeOnMapState(
    val label: String = "Range on map",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRangeOnMapAction {
    data object Activate : CabinRangeOnMapAction
}

val CabinRangeOnMapInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinChargeSessionCardState(
    val label: String = "Charge session card",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargeSessionCardAction {
    data object Activate : CabinChargeSessionCardAction
}

val CabinChargeSessionCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinChargeLimitSliderState(
    val label: String = "Charge limit slider",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargeLimitSliderAction {
    data object Activate : CabinChargeLimitSliderAction
}

val CabinChargeLimitSliderInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinChargingScheduleState(
    val label: String = "Charging schedule",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargingScheduleAction {
    data object Activate : CabinChargingScheduleAction
}

val CabinChargingScheduleInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinChargePortStatusState(
    val label: String = "Charge-port status",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargePortStatusAction {
    data object Activate : CabinChargePortStatusAction
}

val CabinChargePortStatusInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinChargingStationCardState(
    val label: String = "Charging-station card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargingStationCardAction {
    data object Activate : CabinChargingStationCardAction
}

val CabinChargingStationCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinConnectorTypeChipState(
    val label: String = "Connector-type chip",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinConnectorTypeChipAction {
    data object Activate : CabinConnectorTypeChipAction
}

val CabinConnectorTypeChipInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinEnergyFlowDiagramState(
    val label: String = "Energy flow diagram",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEnergyFlowDiagramAction {
    data object Activate : CabinEnergyFlowDiagramAction
}

val CabinEnergyFlowDiagramInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinConsumptionChartState(
    val label: String = "Consumption chart",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinConsumptionChartAction {
    data object Activate : CabinConsumptionChartAction
}

val CabinConsumptionChartInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinRegenIndicatorState(
    val label: String = "Regen indicator",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRegenIndicatorAction {
    data object Activate : CabinRegenIndicatorAction
}

val CabinRegenIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinChargePreconditionState(
    val label: String = "Precondition for charging",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargePreconditionAction {
    data object Activate : CabinChargePreconditionAction
}

val CabinChargePreconditionInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinTripChargePlannerState(
    val label: String = "Trip charge planner",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTripChargePlannerAction {
    data object Activate : CabinTripChargePlannerAction
}

val CabinTripChargePlannerInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinV2xControlsState(
    val label: String = "V2L / V2H controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinV2xControlsAction {
    data object Activate : CabinV2xControlsAction
}

val CabinV2xControlsInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinFuelLevelState(
    val label: String = "Fuel level",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFuelLevelAction {
    data object Activate : CabinFuelLevelAction
}

val CabinFuelLevelInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinHybridRangeBreakdownState(
    val label: String = "Hybrid range breakdown",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHybridRangeBreakdownAction {
    data object Activate : CabinHybridRangeBreakdownAction
}

val CabinHybridRangeBreakdownInteraction: CabinInteraction = CabinInteraction.Glance

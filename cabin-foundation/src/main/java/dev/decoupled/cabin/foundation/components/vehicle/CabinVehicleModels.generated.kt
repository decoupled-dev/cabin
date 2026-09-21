@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.vehicle

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinDoorControlState(
    val label: String = "Door control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDoorControlAction {
    data object Activate : CabinDoorControlAction
}

val CabinDoorControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinWindowControlState(
    val label: String = "Window control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWindowControlAction {
    data object Activate : CabinWindowControlAction
}

val CabinWindowControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinTrunkControlState(
    val label: String = "Trunk / frunk",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTrunkControlAction {
    data object Activate : CabinTrunkControlAction
}

val CabinTrunkControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinLockControlState(
    val label: String = "Lock control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLockControlAction {
    data object Activate : CabinLockControlAction
}

val CabinLockControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinMirrorControlState(
    val label: String = "Mirror control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMirrorControlAction {
    data object Activate : CabinMirrorControlAction
}

val CabinMirrorControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinSeatMemoryState(
    val label: String = "Seat memory",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSeatMemoryAction {
    data object Activate : CabinSeatMemoryAction
}

val CabinSeatMemoryInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinChargePortDoorState(
    val label: String = "Charge-port door",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChargePortDoorAction {
    data object Activate : CabinChargePortDoorAction
}

val CabinChargePortDoorInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinWiperControlState(
    val label: String = "Wiper control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWiperControlAction {
    data object Activate : CabinWiperControlAction
}

val CabinWiperControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinLightControlState(
    val label: String = "Light control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLightControlAction {
    data object Activate : CabinLightControlAction
}

val CabinLightControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinDriveModeSelectorState(
    val label: String = "Drive-mode selector",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDriveModeSelectorAction {
    data object Activate : CabinDriveModeSelectorAction
}

val CabinDriveModeSelectorInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinRegenSelectorState(
    val label: String = "Regen-level selector",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRegenSelectorAction {
    data object Activate : CabinRegenSelectorAction
}

val CabinRegenSelectorInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinSuspensionControlState(
    val label: String = "Suspension / ride height",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSuspensionControlAction {
    data object Activate : CabinSuspensionControlAction
}

val CabinSuspensionControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinSteeringFeelState(
    val label: String = "Steering feel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSteeringFeelAction {
    data object Activate : CabinSteeringFeelAction
}

val CabinSteeringFeelInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinTractionControlState(
    val label: String = "Traction control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTractionControlAction {
    data object Activate : CabinTractionControlAction
}

val CabinTractionControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinTelltaleSetState(
    val label: String = "Telltale set",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTelltaleSetAction {
    data object Activate : CabinTelltaleSetAction
}

val CabinTelltaleSetInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinWarningListState(
    val label: String = "Warning list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWarningListAction {
    data object Activate : CabinWarningListAction
}

val CabinWarningListInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinServiceReminderState(
    val label: String = "Service reminder",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinServiceReminderAction {
    data object Activate : CabinServiceReminderAction
}

val CabinServiceReminderInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVehicleOpenMapState(
    val label: String = "Vehicle-open map",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVehicleOpenMapAction {
    data object Activate : CabinVehicleOpenMapAction
}

val CabinVehicleOpenMapInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTirePressureState(
    val label: String = "Tire pressure",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTirePressureAction {
    data object Activate : CabinTirePressureAction
}

val CabinTirePressureInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinFluidLevelsState(
    val label: String = "Fluid levels",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFluidLevelsAction {
    data object Activate : CabinFluidLevelsAction
}

val CabinFluidLevelsInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinBattery12vState(
    val label: String = "12V battery",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBattery12vAction {
    data object Activate : CabinBattery12vAction
}

val CabinBattery12vInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTripComputerState(
    val label: String = "Trip computer",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTripComputerAction {
    data object Activate : CabinTripComputerAction
}

val CabinTripComputerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinConsumptionGraphState(
    val label: String = "Consumption graph",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinConsumptionGraphAction {
    data object Activate : CabinConsumptionGraphAction
}

val CabinConsumptionGraphInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinEcoScoreState(
    val label: String = "Eco-score",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEcoScoreAction {
    data object Activate : CabinEcoScoreAction
}

val CabinEcoScoreInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVehicleModelViewState(
    val label: String = "Vehicle model view",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVehicleModelViewAction {
    data object Activate : CabinVehicleModelViewAction
}

val CabinVehicleModelViewInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinConfirmSafetyActionState(
    val label: String = "Confirm safety action",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinConfirmSafetyActionAction {
    data object Activate : CabinConfirmSafetyActionAction
}

val CabinConfirmSafetyActionInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinDigitalKeyState(
    val label: String = "Digital key",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDigitalKeyAction {
    data object Activate : CabinDigitalKeyAction
}

val CabinDigitalKeyInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinValetModeState(
    val label: String = "Valet mode",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinValetModeAction {
    data object Activate : CabinValetModeAction
}

val CabinValetModeInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinGuestModeState(
    val label: String = "Guest mode",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinGuestModeAction {
    data object Activate : CabinGuestModeAction
}

val CabinGuestModeInteraction: CabinInteraction = CabinInteraction.ParkedOnly

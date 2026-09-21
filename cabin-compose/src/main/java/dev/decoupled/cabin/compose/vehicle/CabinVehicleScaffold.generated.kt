@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.vehicle

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.vehicle.*

/**
 * Experimental scaffold: Door control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDoorControl(
    state: CabinDoorControlState = CabinDoorControlState(),
    onAction: (CabinDoorControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Door control" },
        family = "vehicle",
        testTag = "cabin_door_control",
        interaction = CabinDoorControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDoorControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Window control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWindowControl(
    state: CabinWindowControlState = CabinWindowControlState(),
    onAction: (CabinWindowControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Window control" },
        family = "vehicle",
        testTag = "cabin_window_control",
        interaction = CabinWindowControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWindowControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Trunk / frunk.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTrunkControl(
    state: CabinTrunkControlState = CabinTrunkControlState(),
    onAction: (CabinTrunkControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Trunk / frunk" },
        family = "vehicle",
        testTag = "cabin_trunk_control",
        interaction = CabinTrunkControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTrunkControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Lock control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLockControl(
    state: CabinLockControlState = CabinLockControlState(),
    onAction: (CabinLockControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Lock control" },
        family = "vehicle",
        testTag = "cabin_lock_control",
        interaction = CabinLockControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLockControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Mirror control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMirrorControl(
    state: CabinMirrorControlState = CabinMirrorControlState(),
    onAction: (CabinMirrorControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Mirror control" },
        family = "vehicle",
        testTag = "cabin_mirror_control",
        interaction = CabinMirrorControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMirrorControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Seat memory.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSeatMemory(
    state: CabinSeatMemoryState = CabinSeatMemoryState(),
    onAction: (CabinSeatMemoryAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Seat memory" },
        family = "vehicle",
        testTag = "cabin_seat_memory",
        interaction = CabinSeatMemoryInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSeatMemoryAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Charge-port door.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChargePortDoor(
    state: CabinChargePortDoorState = CabinChargePortDoorState(),
    onAction: (CabinChargePortDoorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Charge-port door" },
        family = "vehicle",
        testTag = "cabin_charge_port_door",
        interaction = CabinChargePortDoorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChargePortDoorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Wiper control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWiperControl(
    state: CabinWiperControlState = CabinWiperControlState(),
    onAction: (CabinWiperControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Wiper control" },
        family = "vehicle",
        testTag = "cabin_wiper_control",
        interaction = CabinWiperControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWiperControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Light control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLightControl(
    state: CabinLightControlState = CabinLightControlState(),
    onAction: (CabinLightControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Light control" },
        family = "vehicle",
        testTag = "cabin_light_control",
        interaction = CabinLightControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLightControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Drive-mode selector.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDriveModeSelector(
    state: CabinDriveModeSelectorState = CabinDriveModeSelectorState(),
    onAction: (CabinDriveModeSelectorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Drive-mode selector" },
        family = "vehicle",
        testTag = "cabin_drive_mode_selector",
        interaction = CabinDriveModeSelectorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDriveModeSelectorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Regen-level selector.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRegenSelector(
    state: CabinRegenSelectorState = CabinRegenSelectorState(),
    onAction: (CabinRegenSelectorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Regen-level selector" },
        family = "vehicle",
        testTag = "cabin_regen_selector",
        interaction = CabinRegenSelectorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRegenSelectorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Suspension / ride height.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSuspensionControl(
    state: CabinSuspensionControlState = CabinSuspensionControlState(),
    onAction: (CabinSuspensionControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Suspension / ride height" },
        family = "vehicle",
        testTag = "cabin_suspension_control",
        interaction = CabinSuspensionControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSuspensionControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Steering feel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSteeringFeel(
    state: CabinSteeringFeelState = CabinSteeringFeelState(),
    onAction: (CabinSteeringFeelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Steering feel" },
        family = "vehicle",
        testTag = "cabin_steering_feel",
        interaction = CabinSteeringFeelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSteeringFeelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Traction control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTractionControl(
    state: CabinTractionControlState = CabinTractionControlState(),
    onAction: (CabinTractionControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Traction control" },
        family = "vehicle",
        testTag = "cabin_traction_control",
        interaction = CabinTractionControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTractionControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Telltale set.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTelltaleSet(
    state: CabinTelltaleSetState = CabinTelltaleSetState(),
    onAction: (CabinTelltaleSetAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Telltale set" },
        family = "vehicle",
        testTag = "cabin_telltale_set",
        interaction = CabinTelltaleSetInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTelltaleSetAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Warning list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWarningList(
    state: CabinWarningListState = CabinWarningListState(),
    onAction: (CabinWarningListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Warning list" },
        family = "vehicle",
        testTag = "cabin_warning_list",
        interaction = CabinWarningListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWarningListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Service reminder.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinServiceReminder(
    state: CabinServiceReminderState = CabinServiceReminderState(),
    onAction: (CabinServiceReminderAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Service reminder" },
        family = "vehicle",
        testTag = "cabin_service_reminder",
        interaction = CabinServiceReminderInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinServiceReminderAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Vehicle-open map.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVehicleOpenMap(
    state: CabinVehicleOpenMapState = CabinVehicleOpenMapState(),
    onAction: (CabinVehicleOpenMapAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Vehicle-open map" },
        family = "vehicle",
        testTag = "cabin_vehicle_open_map",
        interaction = CabinVehicleOpenMapInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVehicleOpenMapAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Tire pressure.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTirePressure(
    state: CabinTirePressureState = CabinTirePressureState(),
    onAction: (CabinTirePressureAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Tire pressure" },
        family = "vehicle",
        testTag = "cabin_tire_pressure",
        interaction = CabinTirePressureInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTirePressureAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Fluid levels.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFluidLevels(
    state: CabinFluidLevelsState = CabinFluidLevelsState(),
    onAction: (CabinFluidLevelsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Fluid levels" },
        family = "vehicle",
        testTag = "cabin_fluid_levels",
        interaction = CabinFluidLevelsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFluidLevelsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: 12V battery.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBattery12v(
    state: CabinBattery12vState = CabinBattery12vState(),
    onAction: (CabinBattery12vAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "12V battery" },
        family = "vehicle",
        testTag = "cabin_battery_12v",
        interaction = CabinBattery12vInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBattery12vAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Trip computer.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTripComputer(
    state: CabinTripComputerState = CabinTripComputerState(),
    onAction: (CabinTripComputerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Trip computer" },
        family = "vehicle",
        testTag = "cabin_trip_computer",
        interaction = CabinTripComputerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTripComputerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Consumption graph.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinConsumptionGraph(
    state: CabinConsumptionGraphState = CabinConsumptionGraphState(),
    onAction: (CabinConsumptionGraphAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Consumption graph" },
        family = "vehicle",
        testTag = "cabin_consumption_graph",
        interaction = CabinConsumptionGraphInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinConsumptionGraphAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Eco-score.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEcoScore(
    state: CabinEcoScoreState = CabinEcoScoreState(),
    onAction: (CabinEcoScoreAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Eco-score" },
        family = "vehicle",
        testTag = "cabin_eco_score",
        interaction = CabinEcoScoreInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEcoScoreAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Vehicle model view.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVehicleModelView(
    state: CabinVehicleModelViewState = CabinVehicleModelViewState(),
    onAction: (CabinVehicleModelViewAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Vehicle model view" },
        family = "vehicle",
        testTag = "cabin_vehicle_model_view",
        interaction = CabinVehicleModelViewInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVehicleModelViewAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Confirm safety action.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinConfirmSafetyAction(
    state: CabinConfirmSafetyActionState = CabinConfirmSafetyActionState(),
    onAction: (CabinConfirmSafetyActionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Confirm safety action" },
        family = "vehicle",
        testTag = "cabin_confirm_safety_action",
        interaction = CabinConfirmSafetyActionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinConfirmSafetyActionAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Digital key.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDigitalKey(
    state: CabinDigitalKeyState = CabinDigitalKeyState(),
    onAction: (CabinDigitalKeyAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Digital key" },
        family = "vehicle",
        testTag = "cabin_digital_key",
        interaction = CabinDigitalKeyInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDigitalKeyAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Valet mode.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinValetMode(
    state: CabinValetModeState = CabinValetModeState(),
    onAction: (CabinValetModeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Valet mode" },
        family = "vehicle",
        testTag = "cabin_valet_mode",
        interaction = CabinValetModeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinValetModeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Guest mode.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinGuestMode(
    state: CabinGuestModeState = CabinGuestModeState(),
    onAction: (CabinGuestModeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Guest mode" },
        family = "vehicle",
        testTag = "cabin_guest_mode",
        interaction = CabinGuestModeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinGuestModeAction.Activate) },
        modifier = modifier,
    )
}

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.adas

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.adas.*

/**
 * Experimental scaffold: Camera view.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCameraView(
    state: CabinCameraViewState = CabinCameraViewState(),
    onAction: (CabinCameraViewAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Camera view" },
        family = "adas",
        testTag = "cabin_camera_view",
        interaction = CabinCameraViewInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCameraViewAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Camera view selector.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinViewSelector(
    state: CabinViewSelectorState = CabinViewSelectorState(),
    onAction: (CabinViewSelectorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Camera view selector" },
        family = "adas",
        testTag = "cabin_view_selector",
        interaction = CabinViewSelectorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinViewSelectorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Hitch / trailer view.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHitchView(
    state: CabinHitchViewState = CabinHitchViewState(),
    onAction: (CabinHitchViewAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Hitch / trailer view" },
        family = "adas",
        testTag = "cabin_hitch_view",
        interaction = CabinHitchViewInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHitchViewAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Parking sensor.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinParkingSensor(
    state: CabinParkingSensorState = CabinParkingSensorState(),
    onAction: (CabinParkingSensorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Parking sensor" },
        family = "adas",
        testTag = "cabin_parking_sensor",
        interaction = CabinParkingSensorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinParkingSensorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Auto-park flow.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAutoParkFlow(
    state: CabinAutoParkFlowState = CabinAutoParkFlowState(),
    onAction: (CabinAutoParkFlowAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Auto-park flow" },
        family = "adas",
        testTag = "cabin_auto_park_flow",
        interaction = CabinAutoParkFlowInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAutoParkFlowAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: ADAS status chip.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAdasStatusChip(
    state: CabinAdasStatusChipState = CabinAdasStatusChipState(),
    onAction: (CabinAdasStatusChipAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "ADAS status chip" },
        family = "adas",
        testTag = "cabin_adas_status_chip",
        interaction = CabinAdasStatusChipInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAdasStatusChipAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Driver-attention indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDriverAttention(
    state: CabinDriverAttentionState = CabinDriverAttentionState(),
    onAction: (CabinDriverAttentionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Driver-attention indicator" },
        family = "adas",
        testTag = "cabin_driver_attention",
        interaction = CabinDriverAttentionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDriverAttentionAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Adaptive-cruise controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAccControls(
    state: CabinAccControlsState = CabinAccControlsState(),
    onAction: (CabinAccControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Adaptive-cruise controls" },
        family = "adas",
        testTag = "cabin_acc_controls",
        interaction = CabinAccControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAccControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Blind-spot alert.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBlindSpotAlert(
    state: CabinBlindSpotAlertState = CabinBlindSpotAlertState(),
    onAction: (CabinBlindSpotAlertAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Blind-spot alert" },
        family = "adas",
        testTag = "cabin_blind_spot_alert",
        interaction = CabinBlindSpotAlertInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBlindSpotAlertAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Dashcam controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDashcamControls(
    state: CabinDashcamControlsState = CabinDashcamControlsState(),
    onAction: (CabinDashcamControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dashcam controls" },
        family = "adas",
        testTag = "cabin_dashcam_controls",
        interaction = CabinDashcamControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDashcamControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Recordings list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRecordingsList(
    state: CabinRecordingsListState = CabinRecordingsListState(),
    onAction: (CabinRecordingsListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Recordings list" },
        family = "adas",
        testTag = "cabin_recordings_list",
        interaction = CabinRecordingsListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRecordingsListAction.Activate) },
        modifier = modifier,
    )
}

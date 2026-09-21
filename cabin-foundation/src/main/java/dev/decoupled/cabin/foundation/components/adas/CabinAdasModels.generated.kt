@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.adas

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinCameraViewState(
    val label: String = "Camera view",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCameraViewAction {
    data object Activate : CabinCameraViewAction
}

val CabinCameraViewInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinViewSelectorState(
    val label: String = "Camera view selector",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinViewSelectorAction {
    data object Activate : CabinViewSelectorAction
}

val CabinViewSelectorInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinHitchViewState(
    val label: String = "Hitch / trailer view",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHitchViewAction {
    data object Activate : CabinHitchViewAction
}

val CabinHitchViewInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinParkingSensorState(
    val label: String = "Parking sensor",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinParkingSensorAction {
    data object Activate : CabinParkingSensorAction
}

val CabinParkingSensorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinAutoParkFlowState(
    val label: String = "Auto-park flow",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAutoParkFlowAction {
    data object Activate : CabinAutoParkFlowAction
}

val CabinAutoParkFlowInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinAdasStatusChipState(
    val label: String = "ADAS status chip",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAdasStatusChipAction {
    data object Activate : CabinAdasStatusChipAction
}

val CabinAdasStatusChipInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDriverAttentionState(
    val label: String = "Driver-attention indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDriverAttentionAction {
    data object Activate : CabinDriverAttentionAction
}

val CabinDriverAttentionInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinAccControlsState(
    val label: String = "Adaptive-cruise controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAccControlsAction {
    data object Activate : CabinAccControlsAction
}

val CabinAccControlsInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinBlindSpotAlertState(
    val label: String = "Blind-spot alert",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBlindSpotAlertAction {
    data object Activate : CabinBlindSpotAlertAction
}

val CabinBlindSpotAlertInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDashcamControlsState(
    val label: String = "Dashcam controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDashcamControlsAction {
    data object Activate : CabinDashcamControlsAction
}

val CabinDashcamControlsInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinRecordingsListState(
    val label: String = "Recordings list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRecordingsListAction {
    data object Activate : CabinRecordingsListAction
}

val CabinRecordingsListInteraction: CabinInteraction = CabinInteraction.ParkedOnly

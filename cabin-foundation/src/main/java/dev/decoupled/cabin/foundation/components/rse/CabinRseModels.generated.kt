@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.rse

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinPassengerScaffoldState(
    val label: String = "Passenger scaffold",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPassengerScaffoldAction {
    data object Activate : CabinPassengerScaffoldAction
}

val CabinPassengerScaffoldInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinRseHomeState(
    val label: String = "Rear-seat home",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRseHomeAction {
    data object Activate : CabinRseHomeAction
}

val CabinRseHomeInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinZoneMediaState(
    val label: String = "Per-zone media",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinZoneMediaAction {
    data object Activate : CabinZoneMediaAction
}

val CabinZoneMediaInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinZoneVolumeState(
    val label: String = "Per-zone volume",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinZoneVolumeAction {
    data object Activate : CabinZoneVolumeAction
}

val CabinZoneVolumeInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinCrossDisplayHandoffState(
    val label: String = "Cross-display handoff",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCrossDisplayHandoffAction {
    data object Activate : CabinCrossDisplayHandoffAction
}

val CabinCrossDisplayHandoffInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinOccupantZoneIndicatorState(
    val label: String = "Occupant-zone indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinOccupantZoneIndicatorAction {
    data object Activate : CabinOccupantZoneIndicatorAction
}

val CabinOccupantZoneIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDisplayLockState(
    val label: String = "Display lock",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDisplayLockAction {
    data object Activate : CabinDisplayLockAction
}

val CabinDisplayLockInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinSharedContentPatternState(
    val label: String = "Shared vs personal content",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSharedContentPatternAction {
    data object Activate : CabinSharedContentPatternAction
}

val CabinSharedContentPatternInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinClusterCenterHandoffState(
    val label: String = "Cluster-to-center handoff",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinClusterCenterHandoffAction {
    data object Activate : CabinClusterCenterHandoffAction
}

val CabinClusterCenterHandoffInteraction: CabinInteraction = CabinInteraction.NavigateSimple

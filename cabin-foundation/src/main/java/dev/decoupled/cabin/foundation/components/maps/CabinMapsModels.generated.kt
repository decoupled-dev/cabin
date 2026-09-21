@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.maps

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinMapContainerState(
    val label: String = "Map container",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMapContainerAction {
    data object Activate : CabinMapContainerAction
}

val CabinMapContainerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinMapControlsState(
    val label: String = "Map controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMapControlsAction {
    data object Activate : CabinMapControlsAction
}

val CabinMapControlsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinRouteCardState(
    val label: String = "Route card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRouteCardAction {
    data object Activate : CabinRouteCardAction
}

val CabinRouteCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinEtaPanelState(
    val label: String = "ETA panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEtaPanelAction {
    data object Activate : CabinEtaPanelAction
}

val CabinEtaPanelInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTurnInstructionState(
    val label: String = "Turn instruction",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTurnInstructionAction {
    data object Activate : CabinTurnInstructionAction
}

val CabinTurnInstructionInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinLaneGuidanceState(
    val label: String = "Lane guidance",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLaneGuidanceAction {
    data object Activate : CabinLaneGuidanceAction
}

val CabinLaneGuidanceInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinJunctionViewState(
    val label: String = "Junction view",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinJunctionViewAction {
    data object Activate : CabinJunctionViewAction
}

val CabinJunctionViewInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinNavSpeedLimitState(
    val label: String = "Speed-limit indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNavSpeedLimitAction {
    data object Activate : CabinNavSpeedLimitAction
}

val CabinNavSpeedLimitInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinNavSearchBarState(
    val label: String = "Navigation search",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNavSearchBarAction {
    data object Activate : CabinNavSearchBarAction
}

val CabinNavSearchBarInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinPlaceCardState(
    val label: String = "Place card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPlaceCardAction {
    data object Activate : CabinPlaceCardAction
}

val CabinPlaceCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinFavoritesListState(
    val label: String = "Favorites / recents",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFavoritesListAction {
    data object Activate : CabinFavoritesListAction
}

val CabinFavoritesListInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinPoiChipsState(
    val label: String = "POI category chips",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPoiChipsAction {
    data object Activate : CabinPoiChipsAction
}

val CabinPoiChipsInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinArrivalPanelState(
    val label: String = "Arrival panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinArrivalPanelAction {
    data object Activate : CabinArrivalPanelAction
}

val CabinArrivalPanelInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinRouteOptionsState(
    val label: String = "Route options",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRouteOptionsAction {
    data object Activate : CabinRouteOptionsAction
}

val CabinRouteOptionsInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinTrafficIndicatorState(
    val label: String = "Traffic indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTrafficIndicatorAction {
    data object Activate : CabinTrafficIndicatorAction
}

val CabinTrafficIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinWaypointListState(
    val label: String = "Waypoint list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWaypointListAction {
    data object Activate : CabinWaypointListAction
}

val CabinWaypointListInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinTripSummaryState(
    val label: String = "Trip summary",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTripSummaryAction {
    data object Activate : CabinTripSummaryAction
}

val CabinTripSummaryInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinParkingAvailabilityState(
    val label: String = "Parking availability",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinParkingAvailabilityAction {
    data object Activate : CabinParkingAvailabilityAction
}

val CabinParkingAvailabilityInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinNavWidgetState(
    val label: String = "Nav widget",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNavWidgetAction {
    data object Activate : CabinNavWidgetAction
}

val CabinNavWidgetInteraction: CabinInteraction = CabinInteraction.Glance

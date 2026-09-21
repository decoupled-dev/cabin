@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.maps

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.maps.*

/**
 * Experimental scaffold: Map container.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMapContainer(
    state: CabinMapContainerState = CabinMapContainerState(),
    onAction: (CabinMapContainerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Map container" },
        family = "nav",
        testTag = "cabin_map_container",
        interaction = CabinMapContainerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMapContainerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Map controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMapControls(
    state: CabinMapControlsState = CabinMapControlsState(),
    onAction: (CabinMapControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Map controls" },
        family = "nav",
        testTag = "cabin_map_controls",
        interaction = CabinMapControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMapControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Route card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRouteCard(
    state: CabinRouteCardState = CabinRouteCardState(),
    onAction: (CabinRouteCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Route card" },
        family = "nav",
        testTag = "cabin_route_card",
        interaction = CabinRouteCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRouteCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: ETA panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEtaPanel(
    state: CabinEtaPanelState = CabinEtaPanelState(),
    onAction: (CabinEtaPanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "ETA panel" },
        family = "nav",
        testTag = "cabin_eta_panel",
        interaction = CabinEtaPanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEtaPanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Turn instruction.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTurnInstruction(
    state: CabinTurnInstructionState = CabinTurnInstructionState(),
    onAction: (CabinTurnInstructionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Turn instruction" },
        family = "nav",
        testTag = "cabin_turn_instruction",
        interaction = CabinTurnInstructionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTurnInstructionAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Lane guidance.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLaneGuidance(
    state: CabinLaneGuidanceState = CabinLaneGuidanceState(),
    onAction: (CabinLaneGuidanceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Lane guidance" },
        family = "nav",
        testTag = "cabin_lane_guidance",
        interaction = CabinLaneGuidanceInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLaneGuidanceAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Junction view.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinJunctionView(
    state: CabinJunctionViewState = CabinJunctionViewState(),
    onAction: (CabinJunctionViewAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Junction view" },
        family = "nav",
        testTag = "cabin_junction_view",
        interaction = CabinJunctionViewInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinJunctionViewAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Speed-limit indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNavSpeedLimit(
    state: CabinNavSpeedLimitState = CabinNavSpeedLimitState(),
    onAction: (CabinNavSpeedLimitAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Speed-limit indicator" },
        family = "nav",
        testTag = "cabin_speed_limit",
        interaction = CabinNavSpeedLimitInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNavSpeedLimitAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Navigation search.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNavSearchBar(
    state: CabinNavSearchBarState = CabinNavSearchBarState(),
    onAction: (CabinNavSearchBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Navigation search" },
        family = "nav",
        testTag = "cabin_nav_search_bar",
        interaction = CabinNavSearchBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNavSearchBarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Place card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPlaceCard(
    state: CabinPlaceCardState = CabinPlaceCardState(),
    onAction: (CabinPlaceCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Place card" },
        family = "nav",
        testTag = "cabin_place_card",
        interaction = CabinPlaceCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPlaceCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Favorites / recents.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFavoritesList(
    state: CabinFavoritesListState = CabinFavoritesListState(),
    onAction: (CabinFavoritesListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Favorites / recents" },
        family = "nav",
        testTag = "cabin_favorites_list",
        interaction = CabinFavoritesListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFavoritesListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: POI category chips.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPoiChips(
    state: CabinPoiChipsState = CabinPoiChipsState(),
    onAction: (CabinPoiChipsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "POI category chips" },
        family = "nav",
        testTag = "cabin_poi_chips",
        interaction = CabinPoiChipsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPoiChipsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Arrival panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinArrivalPanel(
    state: CabinArrivalPanelState = CabinArrivalPanelState(),
    onAction: (CabinArrivalPanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Arrival panel" },
        family = "nav",
        testTag = "cabin_arrival_panel",
        interaction = CabinArrivalPanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinArrivalPanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Route options.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRouteOptions(
    state: CabinRouteOptionsState = CabinRouteOptionsState(),
    onAction: (CabinRouteOptionsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Route options" },
        family = "nav",
        testTag = "cabin_route_options",
        interaction = CabinRouteOptionsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRouteOptionsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Traffic indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTrafficIndicator(
    state: CabinTrafficIndicatorState = CabinTrafficIndicatorState(),
    onAction: (CabinTrafficIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Traffic indicator" },
        family = "nav",
        testTag = "cabin_traffic_indicator",
        interaction = CabinTrafficIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTrafficIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Waypoint list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWaypointList(
    state: CabinWaypointListState = CabinWaypointListState(),
    onAction: (CabinWaypointListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Waypoint list" },
        family = "nav",
        testTag = "cabin_waypoint_list",
        interaction = CabinWaypointListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWaypointListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Trip summary.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTripSummary(
    state: CabinTripSummaryState = CabinTripSummaryState(),
    onAction: (CabinTripSummaryAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Trip summary" },
        family = "nav",
        testTag = "cabin_trip_summary",
        interaction = CabinTripSummaryInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTripSummaryAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Parking availability.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinParkingAvailability(
    state: CabinParkingAvailabilityState = CabinParkingAvailabilityState(),
    onAction: (CabinParkingAvailabilityAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Parking availability" },
        family = "nav",
        testTag = "cabin_parking_availability",
        interaction = CabinParkingAvailabilityInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinParkingAvailabilityAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Nav widget.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNavWidget(
    state: CabinNavWidgetState = CabinNavWidgetState(),
    onAction: (CabinNavWidgetAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Nav widget" },
        family = "nav",
        testTag = "cabin_nav_widget",
        interaction = CabinNavWidgetInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNavWidgetAction.Activate) },
        modifier = modifier,
    )
}

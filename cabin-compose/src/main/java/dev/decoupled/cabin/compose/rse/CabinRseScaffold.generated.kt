@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.rse

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.rse.*

/**
 * Experimental scaffold: Passenger scaffold.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPassengerScaffold(
    state: CabinPassengerScaffoldState = CabinPassengerScaffoldState(),
    onAction: (CabinPassengerScaffoldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Passenger scaffold" },
        family = "rse",
        testTag = "cabin_passenger_scaffold",
        interaction = CabinPassengerScaffoldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPassengerScaffoldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Rear-seat home.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRseHome(
    state: CabinRseHomeState = CabinRseHomeState(),
    onAction: (CabinRseHomeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Rear-seat home" },
        family = "rse",
        testTag = "cabin_rse_home",
        interaction = CabinRseHomeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRseHomeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Per-zone media.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinZoneMedia(
    state: CabinZoneMediaState = CabinZoneMediaState(),
    onAction: (CabinZoneMediaAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Per-zone media" },
        family = "rse",
        testTag = "cabin_zone_media",
        interaction = CabinZoneMediaInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinZoneMediaAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Per-zone volume.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinZoneVolume(
    state: CabinZoneVolumeState = CabinZoneVolumeState(),
    onAction: (CabinZoneVolumeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Per-zone volume" },
        family = "rse",
        testTag = "cabin_zone_volume",
        interaction = CabinZoneVolumeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinZoneVolumeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Cross-display handoff.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCrossDisplayHandoff(
    state: CabinCrossDisplayHandoffState = CabinCrossDisplayHandoffState(),
    onAction: (CabinCrossDisplayHandoffAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Cross-display handoff" },
        family = "rse",
        testTag = "cabin_cross_display_handoff",
        interaction = CabinCrossDisplayHandoffInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCrossDisplayHandoffAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Occupant-zone indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinOccupantZoneIndicator(
    state: CabinOccupantZoneIndicatorState = CabinOccupantZoneIndicatorState(),
    onAction: (CabinOccupantZoneIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Occupant-zone indicator" },
        family = "rse",
        testTag = "cabin_occupant_zone_indicator",
        interaction = CabinOccupantZoneIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinOccupantZoneIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Display lock.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDisplayLock(
    state: CabinDisplayLockState = CabinDisplayLockState(),
    onAction: (CabinDisplayLockAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Display lock" },
        family = "rse",
        testTag = "cabin_display_lock",
        interaction = CabinDisplayLockInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDisplayLockAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Shared vs personal content.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSharedContentPattern(
    state: CabinSharedContentPatternState = CabinSharedContentPatternState(),
    onAction: (CabinSharedContentPatternAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Shared vs personal content" },
        family = "rse",
        testTag = "cabin_shared_content_pattern",
        interaction = CabinSharedContentPatternInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSharedContentPatternAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Cluster-to-center handoff.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinClusterCenterHandoff(
    state: CabinClusterCenterHandoffState = CabinClusterCenterHandoffState(),
    onAction: (CabinClusterCenterHandoffAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Cluster-to-center handoff" },
        family = "rse",
        testTag = "cabin_cluster_center_handoff",
        interaction = CabinClusterCenterHandoffInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinClusterCenterHandoffAction.Activate) },
        modifier = modifier,
    )
}

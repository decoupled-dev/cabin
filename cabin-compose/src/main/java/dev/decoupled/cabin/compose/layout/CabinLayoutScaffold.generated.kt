@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.layout

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.layout.*

/**
 * Experimental scaffold: Pane divider.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPaneDivider(
    state: CabinPaneDividerState = CabinPaneDividerState(),
    onAction: (CabinPaneDividerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Pane divider" },
        family = "layout",
        testTag = "cabin_pane_divider",
        interaction = CabinPaneDividerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPaneDividerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Collapsible side panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCollapsibleSidePanel(
    state: CabinCollapsibleSidePanelState = CabinCollapsibleSidePanelState(),
    onAction: (CabinCollapsibleSidePanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Collapsible side panel" },
        family = "layout",
        testTag = "cabin_collapsible_side_panel",
        interaction = CabinCollapsibleSidePanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCollapsibleSidePanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Adaptive container.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAdaptiveContainer(
    state: CabinAdaptiveContainerState = CabinAdaptiveContainerState(),
    onAction: (CabinAdaptiveContainerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Adaptive container" },
        family = "layout",
        testTag = "cabin_adaptive_container",
        interaction = CabinAdaptiveContainerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAdaptiveContainerAction.Activate) },
        modifier = modifier,
    )
}

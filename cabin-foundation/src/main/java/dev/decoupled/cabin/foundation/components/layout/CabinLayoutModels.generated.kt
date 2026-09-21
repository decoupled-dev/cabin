@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.layout

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinPaneDividerState(
    val label: String = "Pane divider",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPaneDividerAction {
    data object Activate : CabinPaneDividerAction
}

val CabinPaneDividerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinCollapsibleSidePanelState(
    val label: String = "Collapsible side panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCollapsibleSidePanelAction {
    data object Activate : CabinCollapsibleSidePanelAction
}

val CabinCollapsibleSidePanelInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinAdaptiveContainerState(
    val label: String = "Adaptive container",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAdaptiveContainerAction {
    data object Activate : CabinAdaptiveContainerAction
}

val CabinAdaptiveContainerInteraction: CabinInteraction = CabinInteraction.Glance

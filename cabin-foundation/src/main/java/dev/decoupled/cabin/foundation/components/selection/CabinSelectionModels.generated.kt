@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.selection

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinSwitchState(
    val label: String = "Switch",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSwitchAction {
    data object Activate : CabinSwitchAction
}

val CabinSwitchInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinCheckboxState(
    val label: String = "Checkbox",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCheckboxAction {
    data object Activate : CabinCheckboxAction
}

val CabinCheckboxInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinRadioState(
    val label: String = "Radio",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRadioAction {
    data object Activate : CabinRadioAction
}

val CabinRadioInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinRadioGroupState(
    val label: String = "Radio group",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRadioGroupAction {
    data object Activate : CabinRadioGroupAction
}

val CabinRadioGroupInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinChipState(
    val label: String = "Chip",
    val variant: String = "filter",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChipAction {
    data object Activate : CabinChipAction
}

val CabinChipInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinChipGroupState(
    val label: String = "Chip group",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChipGroupAction {
    data object Activate : CabinChipGroupAction
}

val CabinChipGroupInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinSelectionCardState(
    val label: String = "Selection card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSelectionCardAction {
    data object Activate : CabinSelectionCardAction
}

val CabinSelectionCardInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinMultiSelectToolbarState(
    val label: String = "Multi-select toolbar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMultiSelectToolbarAction {
    data object Activate : CabinMultiSelectToolbarAction
}

val CabinMultiSelectToolbarInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinToggleTileState(
    val label: String = "Toggle tile",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinToggleTileAction {
    data object Activate : CabinToggleTileAction
}

val CabinToggleTileInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinTriStateToggleState(
    val label: String = "Tri-state toggle",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTriStateToggleAction {
    data object Activate : CabinTriStateToggleAction
}

val CabinTriStateToggleInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinMultiPositionSelectorState(
    val label: String = "Multi-position selector",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMultiPositionSelectorAction {
    data object Activate : CabinMultiPositionSelectorAction
}

val CabinMultiPositionSelectorInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.action

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinButtonState(
    val label: String = "Button",
    val variant: String = "filled",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinButtonAction {
    data object Activate : CabinButtonAction
}

val CabinButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinFabState(
    val label: String = "FAB",
    val variant: String = "standard",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFabAction {
    data object Activate : CabinFabAction
}

val CabinFabInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinButtonGroupState(
    val label: String = "Button group",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinButtonGroupAction {
    data object Activate : CabinButtonGroupAction
}

val CabinButtonGroupInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinToggleButtonState(
    val label: String = "Toggle button",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinToggleButtonAction {
    data object Activate : CabinToggleButtonAction
}

val CabinToggleButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinSegmentedButtonState(
    val label: String = "Segmented button",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSegmentedButtonAction {
    data object Activate : CabinSegmentedButtonAction
}

val CabinSegmentedButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinSplitButtonState(
    val label: String = "Split button",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSplitButtonAction {
    data object Activate : CabinSplitButtonAction
}

val CabinSplitButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinLongPressButtonState(
    val label: String = "Long-press button",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLongPressButtonAction {
    data object Activate : CabinLongPressButtonAction
}

val CabinLongPressButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinActionRowState(
    val label: String = "Rotary action row",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinActionRowAction {
    data object Activate : CabinActionRowAction
}

val CabinActionRowInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinHoldToConfirmButtonState(
    val label: String = "Hold-to-confirm",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHoldToConfirmButtonAction {
    data object Activate : CabinHoldToConfirmButtonAction
}

val CabinHoldToConfirmButtonInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinSafetyActionButtonState(
    val label: String = "Safety-critical action",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSafetyActionButtonAction {
    data object Activate : CabinSafetyActionButtonAction
}

val CabinSafetyActionButtonInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinPressHoldRepeaterState(
    val label: String = "Press-and-hold repeater",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPressHoldRepeaterAction {
    data object Activate : CabinPressHoldRepeaterAction
}

val CabinPressHoldRepeaterInteraction: CabinInteraction = CabinInteraction.NavigateSimple

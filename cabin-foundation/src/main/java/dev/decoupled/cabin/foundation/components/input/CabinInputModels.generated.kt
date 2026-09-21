@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.input

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinTextFieldState(
    val label: String = "Text field",
    val variant: String = "filled",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTextFieldAction {
    data object Activate : CabinTextFieldAction
}

val CabinTextFieldInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinSearchFieldState(
    val label: String = "Search field",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSearchFieldAction {
    data object Activate : CabinSearchFieldAction
}

val CabinSearchFieldInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinPinFieldState(
    val label: String = "PIN field",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPinFieldAction {
    data object Activate : CabinPinFieldAction
}

val CabinPinFieldInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinOtpFieldState(
    val label: String = "OTP field",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinOtpFieldAction {
    data object Activate : CabinOtpFieldAction
}

val CabinOtpFieldInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinMultilineTextState(
    val label: String = "Multi-line text",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMultilineTextAction {
    data object Activate : CabinMultilineTextAction
}

val CabinMultilineTextInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinAutocompleteState(
    val label: String = "Autocomplete",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAutocompleteAction {
    data object Activate : CabinAutocompleteAction
}

val CabinAutocompleteInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinMaskedInputState(
    val label: String = "Masked input",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMaskedInputAction {
    data object Activate : CabinMaskedInputAction
}

val CabinMaskedInputInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinNumericKeypadState(
    val label: String = "Numeric keypad",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNumericKeypadAction {
    data object Activate : CabinNumericKeypadAction
}

val CabinNumericKeypadInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinDialPadState(
    val label: String = "Dial pad",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDialPadAction {
    data object Activate : CabinDialPadAction
}

val CabinDialPadInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinRotaryTextEntryState(
    val label: String = "Rotary text entry",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRotaryTextEntryAction {
    data object Activate : CabinRotaryTextEntryAction
}

val CabinRotaryTextEntryInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinSliderState(
    val label: String = "Slider",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSliderAction {
    data object Activate : CabinSliderAction
}

val CabinSliderInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinRangeSliderState(
    val label: String = "Range slider",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRangeSliderAction {
    data object Activate : CabinRangeSliderAction
}

val CabinRangeSliderInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinStepperState(
    val label: String = "Stepper",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinStepperAction {
    data object Activate : CabinStepperAction
}

val CabinStepperInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinNumberPickerState(
    val label: String = "Number picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNumberPickerAction {
    data object Activate : CabinNumberPickerAction
}

val CabinNumberPickerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinDialKnobState(
    val label: String = "Dial / knob",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDialKnobAction {
    data object Activate : CabinDialKnobAction
}

val CabinDialKnobInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinDatePickerState(
    val label: String = "Date picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDatePickerAction {
    data object Activate : CabinDatePickerAction
}

val CabinDatePickerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinTimePickerState(
    val label: String = "Time picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTimePickerAction {
    data object Activate : CabinTimePickerAction
}

val CabinTimePickerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinDurationPickerState(
    val label: String = "Duration picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDurationPickerAction {
    data object Activate : CabinDurationPickerAction
}

val CabinDurationPickerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinDropdownState(
    val label: String = "Dropdown",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDropdownAction {
    data object Activate : CabinDropdownAction
}

val CabinDropdownInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinListPickerState(
    val label: String = "List picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinListPickerAction {
    data object Activate : CabinListPickerAction
}

val CabinListPickerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinColorPickerState(
    val label: String = "Color picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinColorPickerAction {
    data object Activate : CabinColorPickerAction
}

val CabinColorPickerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinWheelPickerState(
    val label: String = "Wheel picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWheelPickerAction {
    data object Activate : CabinWheelPickerAction
}

val CabinWheelPickerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinOptionPickerState(
    val label: String = "Option picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinOptionPickerAction {
    data object Activate : CabinOptionPickerAction
}

val CabinOptionPickerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinVoiceMicButtonState(
    val label: String = "Voice mic button",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVoiceMicButtonAction {
    data object Activate : CabinVoiceMicButtonAction
}

val CabinVoiceMicButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinListeningIndicatorState(
    val label: String = "Listening indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinListeningIndicatorAction {
    data object Activate : CabinListeningIndicatorAction
}

val CabinListeningIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTranscriptionFieldState(
    val label: String = "Transcription field",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTranscriptionFieldAction {
    data object Activate : CabinTranscriptionFieldAction
}

val CabinTranscriptionFieldInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinRatingInputState(
    val label: String = "Rating input",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRatingInputAction {
    data object Activate : CabinRatingInputAction
}

val CabinRatingInputInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinGesturePadState(
    val label: String = "Gesture pad",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinGesturePadAction {
    data object Activate : CabinGesturePadAction
}

val CabinGesturePadInteraction: CabinInteraction = CabinInteraction.NavigateSimple

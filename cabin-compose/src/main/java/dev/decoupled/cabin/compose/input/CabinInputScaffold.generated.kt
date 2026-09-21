@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.input

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.input.*

/**
 * Experimental scaffold: Text field.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTextField(
    state: CabinTextFieldState = CabinTextFieldState(),
    onAction: (CabinTextFieldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Text field" },
        family = "input",
        testTag = "cabin_text_field",
        interaction = CabinTextFieldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTextFieldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Search field.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSearchField(
    state: CabinSearchFieldState = CabinSearchFieldState(),
    onAction: (CabinSearchFieldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Search field" },
        family = "input",
        testTag = "cabin_search_field",
        interaction = CabinSearchFieldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSearchFieldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: PIN field.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPinField(
    state: CabinPinFieldState = CabinPinFieldState(),
    onAction: (CabinPinFieldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "PIN field" },
        family = "input",
        testTag = "cabin_pin_field",
        interaction = CabinPinFieldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPinFieldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: OTP field.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinOtpField(
    state: CabinOtpFieldState = CabinOtpFieldState(),
    onAction: (CabinOtpFieldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "OTP field" },
        family = "input",
        testTag = "cabin_otp_field",
        interaction = CabinOtpFieldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinOtpFieldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Multi-line text.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMultilineText(
    state: CabinMultilineTextState = CabinMultilineTextState(),
    onAction: (CabinMultilineTextAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Multi-line text" },
        family = "input",
        testTag = "cabin_multiline_text",
        interaction = CabinMultilineTextInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMultilineTextAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Autocomplete.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAutocomplete(
    state: CabinAutocompleteState = CabinAutocompleteState(),
    onAction: (CabinAutocompleteAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Autocomplete" },
        family = "input",
        testTag = "cabin_autocomplete",
        interaction = CabinAutocompleteInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAutocompleteAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Masked input.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMaskedInput(
    state: CabinMaskedInputState = CabinMaskedInputState(),
    onAction: (CabinMaskedInputAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Masked input" },
        family = "input",
        testTag = "cabin_masked_input",
        interaction = CabinMaskedInputInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMaskedInputAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Numeric keypad.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNumericKeypad(
    state: CabinNumericKeypadState = CabinNumericKeypadState(),
    onAction: (CabinNumericKeypadAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Numeric keypad" },
        family = "input",
        testTag = "cabin_numeric_keypad",
        interaction = CabinNumericKeypadInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNumericKeypadAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Dial pad.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDialPad(
    state: CabinDialPadState = CabinDialPadState(),
    onAction: (CabinDialPadAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dial pad" },
        family = "input",
        testTag = "cabin_dial_pad",
        interaction = CabinDialPadInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDialPadAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Rotary text entry.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRotaryTextEntry(
    state: CabinRotaryTextEntryState = CabinRotaryTextEntryState(),
    onAction: (CabinRotaryTextEntryAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Rotary text entry" },
        family = "input",
        testTag = "cabin_rotary_text_entry",
        interaction = CabinRotaryTextEntryInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRotaryTextEntryAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Slider.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSlider(
    state: CabinSliderState = CabinSliderState(),
    onAction: (CabinSliderAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Slider" },
        family = "input",
        testTag = "cabin_slider",
        interaction = CabinSliderInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSliderAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Range slider.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRangeSlider(
    state: CabinRangeSliderState = CabinRangeSliderState(),
    onAction: (CabinRangeSliderAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Range slider" },
        family = "input",
        testTag = "cabin_range_slider",
        interaction = CabinRangeSliderInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRangeSliderAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Stepper.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinStepper(
    state: CabinStepperState = CabinStepperState(),
    onAction: (CabinStepperAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Stepper" },
        family = "input",
        testTag = "cabin_stepper",
        interaction = CabinStepperInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinStepperAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Number picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNumberPicker(
    state: CabinNumberPickerState = CabinNumberPickerState(),
    onAction: (CabinNumberPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Number picker" },
        family = "input",
        testTag = "cabin_number_picker",
        interaction = CabinNumberPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNumberPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Dial / knob.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDialKnob(
    state: CabinDialKnobState = CabinDialKnobState(),
    onAction: (CabinDialKnobAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dial / knob" },
        family = "input",
        testTag = "cabin_dial_knob",
        interaction = CabinDialKnobInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDialKnobAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Date picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDatePicker(
    state: CabinDatePickerState = CabinDatePickerState(),
    onAction: (CabinDatePickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Date picker" },
        family = "input",
        testTag = "cabin_date_picker",
        interaction = CabinDatePickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDatePickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Time picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTimePicker(
    state: CabinTimePickerState = CabinTimePickerState(),
    onAction: (CabinTimePickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Time picker" },
        family = "input",
        testTag = "cabin_time_picker",
        interaction = CabinTimePickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTimePickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Duration picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDurationPicker(
    state: CabinDurationPickerState = CabinDurationPickerState(),
    onAction: (CabinDurationPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Duration picker" },
        family = "input",
        testTag = "cabin_duration_picker",
        interaction = CabinDurationPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDurationPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Dropdown.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDropdown(
    state: CabinDropdownState = CabinDropdownState(),
    onAction: (CabinDropdownAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dropdown" },
        family = "input",
        testTag = "cabin_dropdown",
        interaction = CabinDropdownInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDropdownAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: List picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinListPicker(
    state: CabinListPickerState = CabinListPickerState(),
    onAction: (CabinListPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "List picker" },
        family = "input",
        testTag = "cabin_list_picker",
        interaction = CabinListPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinListPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Color picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinColorPicker(
    state: CabinColorPickerState = CabinColorPickerState(),
    onAction: (CabinColorPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Color picker" },
        family = "input",
        testTag = "cabin_color_picker",
        interaction = CabinColorPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinColorPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Wheel picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWheelPicker(
    state: CabinWheelPickerState = CabinWheelPickerState(),
    onAction: (CabinWheelPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Wheel picker" },
        family = "input",
        testTag = "cabin_wheel_picker",
        interaction = CabinWheelPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWheelPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Option picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinOptionPicker(
    state: CabinOptionPickerState = CabinOptionPickerState(),
    onAction: (CabinOptionPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Option picker" },
        family = "input",
        testTag = "cabin_option_picker",
        interaction = CabinOptionPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinOptionPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Voice mic button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVoiceMicButton(
    state: CabinVoiceMicButtonState = CabinVoiceMicButtonState(),
    onAction: (CabinVoiceMicButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Voice mic button" },
        family = "input",
        testTag = "cabin_voice_mic_button",
        interaction = CabinVoiceMicButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVoiceMicButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Listening indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinListeningIndicator(
    state: CabinListeningIndicatorState = CabinListeningIndicatorState(),
    onAction: (CabinListeningIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Listening indicator" },
        family = "input",
        testTag = "cabin_listening_indicator",
        interaction = CabinListeningIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinListeningIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Transcription field.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTranscriptionField(
    state: CabinTranscriptionFieldState = CabinTranscriptionFieldState(),
    onAction: (CabinTranscriptionFieldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Transcription field" },
        family = "input",
        testTag = "cabin_transcription_field",
        interaction = CabinTranscriptionFieldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTranscriptionFieldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Rating input.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRatingInput(
    state: CabinRatingInputState = CabinRatingInputState(),
    onAction: (CabinRatingInputAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Rating input" },
        family = "input",
        testTag = "cabin_rating_input",
        interaction = CabinRatingInputInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRatingInputAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Gesture pad.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinGesturePad(
    state: CabinGesturePadState = CabinGesturePadState(),
    onAction: (CabinGesturePadAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Gesture pad" },
        family = "input",
        testTag = "cabin_gesture_pad",
        interaction = CabinGesturePadInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinGesturePadAction.Activate) },
        modifier = modifier,
    )
}

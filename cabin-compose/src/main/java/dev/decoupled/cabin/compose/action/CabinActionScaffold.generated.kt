@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.action

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.action.*

/**
 * Experimental scaffold: Button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinButton(
    state: CabinButtonState = CabinButtonState(),
    onAction: (CabinButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Button" },
        family = "action",
        testTag = "cabin_button",
        interaction = CabinButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: FAB.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFab(
    state: CabinFabState = CabinFabState(),
    onAction: (CabinFabAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "FAB" },
        family = "action",
        testTag = "cabin_fab",
        interaction = CabinFabInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFabAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Button group.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinButtonGroup(
    state: CabinButtonGroupState = CabinButtonGroupState(),
    onAction: (CabinButtonGroupAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Button group" },
        family = "action",
        testTag = "cabin_button_group",
        interaction = CabinButtonGroupInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinButtonGroupAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Toggle button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinToggleButton(
    state: CabinToggleButtonState = CabinToggleButtonState(),
    onAction: (CabinToggleButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Toggle button" },
        family = "action",
        testTag = "cabin_toggle_button",
        interaction = CabinToggleButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinToggleButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Segmented button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSegmentedButton(
    state: CabinSegmentedButtonState = CabinSegmentedButtonState(),
    onAction: (CabinSegmentedButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Segmented button" },
        family = "action",
        testTag = "cabin_segmented_button",
        interaction = CabinSegmentedButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSegmentedButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Split button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSplitButton(
    state: CabinSplitButtonState = CabinSplitButtonState(),
    onAction: (CabinSplitButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Split button" },
        family = "action",
        testTag = "cabin_split_button",
        interaction = CabinSplitButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSplitButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Long-press button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLongPressButton(
    state: CabinLongPressButtonState = CabinLongPressButtonState(),
    onAction: (CabinLongPressButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Long-press button" },
        family = "action",
        testTag = "cabin_long_press_button",
        interaction = CabinLongPressButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLongPressButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Rotary action row.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinActionRow(
    state: CabinActionRowState = CabinActionRowState(),
    onAction: (CabinActionRowAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Rotary action row" },
        family = "action",
        testTag = "cabin_action_row",
        interaction = CabinActionRowInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinActionRowAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Hold-to-confirm.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHoldToConfirmButton(
    state: CabinHoldToConfirmButtonState = CabinHoldToConfirmButtonState(),
    onAction: (CabinHoldToConfirmButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Hold-to-confirm" },
        family = "action",
        testTag = "cabin_hold_to_confirm",
        interaction = CabinHoldToConfirmButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHoldToConfirmButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Safety-critical action.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSafetyActionButton(
    state: CabinSafetyActionButtonState = CabinSafetyActionButtonState(),
    onAction: (CabinSafetyActionButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Safety-critical action" },
        family = "action",
        testTag = "cabin_safety_action",
        interaction = CabinSafetyActionButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSafetyActionButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Press-and-hold repeater.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPressHoldRepeater(
    state: CabinPressHoldRepeaterState = CabinPressHoldRepeaterState(),
    onAction: (CabinPressHoldRepeaterAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Press-and-hold repeater" },
        family = "action",
        testTag = "cabin_press_hold_repeater",
        interaction = CabinPressHoldRepeaterInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPressHoldRepeaterAction.Activate) },
        modifier = modifier,
    )
}

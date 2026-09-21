@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.selection

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.selection.*

/**
 * Experimental scaffold: Switch.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSwitch(
    state: CabinSwitchState = CabinSwitchState(),
    onAction: (CabinSwitchAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Switch" },
        family = "selection",
        testTag = "cabin_switch",
        interaction = CabinSwitchInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSwitchAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Checkbox.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCheckbox(
    state: CabinCheckboxState = CabinCheckboxState(),
    onAction: (CabinCheckboxAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Checkbox" },
        family = "selection",
        testTag = "cabin_checkbox",
        interaction = CabinCheckboxInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCheckboxAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Radio.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRadio(
    state: CabinRadioState = CabinRadioState(),
    onAction: (CabinRadioAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Radio" },
        family = "selection",
        testTag = "cabin_radio",
        interaction = CabinRadioInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRadioAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Radio group.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRadioGroup(
    state: CabinRadioGroupState = CabinRadioGroupState(),
    onAction: (CabinRadioGroupAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Radio group" },
        family = "selection",
        testTag = "cabin_radio_group",
        interaction = CabinRadioGroupInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRadioGroupAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Chip.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChip(
    state: CabinChipState = CabinChipState(),
    onAction: (CabinChipAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Chip" },
        family = "selection",
        testTag = "cabin_chip",
        interaction = CabinChipInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChipAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Chip group.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChipGroup(
    state: CabinChipGroupState = CabinChipGroupState(),
    onAction: (CabinChipGroupAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Chip group" },
        family = "selection",
        testTag = "cabin_chip_group",
        interaction = CabinChipGroupInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChipGroupAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Selection card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSelectionCard(
    state: CabinSelectionCardState = CabinSelectionCardState(),
    onAction: (CabinSelectionCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Selection card" },
        family = "selection",
        testTag = "cabin_selection_card",
        interaction = CabinSelectionCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSelectionCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Multi-select toolbar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMultiSelectToolbar(
    state: CabinMultiSelectToolbarState = CabinMultiSelectToolbarState(),
    onAction: (CabinMultiSelectToolbarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Multi-select toolbar" },
        family = "selection",
        testTag = "cabin_multi_select_toolbar",
        interaction = CabinMultiSelectToolbarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMultiSelectToolbarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Toggle tile.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinToggleTile(
    state: CabinToggleTileState = CabinToggleTileState(),
    onAction: (CabinToggleTileAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Toggle tile" },
        family = "selection",
        testTag = "cabin_toggle_tile",
        interaction = CabinToggleTileInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinToggleTileAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Tri-state toggle.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTriStateToggle(
    state: CabinTriStateToggleState = CabinTriStateToggleState(),
    onAction: (CabinTriStateToggleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Tri-state toggle" },
        family = "selection",
        testTag = "cabin_tri_state_toggle",
        interaction = CabinTriStateToggleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTriStateToggleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Multi-position selector.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMultiPositionSelector(
    state: CabinMultiPositionSelectorState = CabinMultiPositionSelectorState(),
    onAction: (CabinMultiPositionSelectorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Multi-position selector" },
        family = "selection",
        testTag = "cabin_multi_position_selector",
        interaction = CabinMultiPositionSelectorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMultiPositionSelectorAction.Activate) },
        modifier = modifier,
    )
}

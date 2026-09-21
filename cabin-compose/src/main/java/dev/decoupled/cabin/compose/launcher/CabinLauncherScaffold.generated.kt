@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.launcher

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.launcher.*

/**
 * Experimental scaffold: Launcher scaffold.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLauncherScaffold(
    state: CabinLauncherScaffoldState = CabinLauncherScaffoldState(),
    onAction: (CabinLauncherScaffoldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Launcher scaffold" },
        family = "launcher",
        testTag = "cabin_launcher_scaffold",
        interaction = CabinLauncherScaffoldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLauncherScaffoldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: App icon.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAppIcon(
    state: CabinAppIconState = CabinAppIconState(),
    onAction: (CabinAppIconAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "App icon" },
        family = "launcher",
        testTag = "cabin_app_icon",
        interaction = CabinAppIconInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAppIconAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Recents / task switcher.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRecentsSwitcher(
    state: CabinRecentsSwitcherState = CabinRecentsSwitcherState(),
    onAction: (CabinRecentsSwitcherAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Recents / task switcher" },
        family = "launcher",
        testTag = "cabin_recents_switcher",
        interaction = CabinRecentsSwitcherInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRecentsSwitcherAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Widget host.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWidgetHost(
    state: CabinWidgetHostState = CabinWidgetHostState(),
    onAction: (CabinWidgetHostAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Widget host" },
        family = "launcher",
        testTag = "cabin_widget_host",
        interaction = CabinWidgetHostInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWidgetHostAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Widget picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWidgetPicker(
    state: CabinWidgetPickerState = CabinWidgetPickerState(),
    onAction: (CabinWidgetPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Widget picker" },
        family = "launcher",
        testTag = "cabin_widget_picker",
        interaction = CabinWidgetPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWidgetPickerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Widget edit mode.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWidgetEditMode(
    state: CabinWidgetEditModeState = CabinWidgetEditModeState(),
    onAction: (CabinWidgetEditModeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Widget edit mode" },
        family = "launcher",
        testTag = "cabin_widget_edit_mode",
        interaction = CabinWidgetEditModeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWidgetEditModeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Suggestion card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSuggestionCard(
    state: CabinSuggestionCardState = CabinSuggestionCardState(),
    onAction: (CabinSuggestionCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Suggestion card" },
        family = "launcher",
        testTag = "cabin_suggestion_card",
        interaction = CabinSuggestionCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSuggestionCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Shortcut tile.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinShortcutTile(
    state: CabinShortcutTileState = CabinShortcutTileState(),
    onAction: (CabinShortcutTileAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Shortcut tile" },
        family = "launcher",
        testTag = "cabin_shortcut_tile",
        interaction = CabinShortcutTileInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinShortcutTileAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Home template.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHomeTemplate(
    state: CabinHomeTemplateState = CabinHomeTemplateState(),
    onAction: (CabinHomeTemplateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Home template" },
        family = "launcher",
        testTag = "cabin_home_template",
        interaction = CabinHomeTemplateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHomeTemplateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Screensaver / idle.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinScreensaver(
    state: CabinScreensaverState = CabinScreensaverState(),
    onAction: (CabinScreensaverAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Screensaver / idle" },
        family = "launcher",
        testTag = "cabin_screensaver",
        interaction = CabinScreensaverInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinScreensaverAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Welcome screen.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWelcomeScreen(
    state: CabinWelcomeScreenState = CabinWelcomeScreenState(),
    onAction: (CabinWelcomeScreenAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Welcome screen" },
        family = "launcher",
        testTag = "cabin_welcome_screen",
        interaction = CabinWelcomeScreenInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWelcomeScreenAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Wallpaper picker.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWallpaperPicker(
    state: CabinWallpaperPickerState = CabinWallpaperPickerState(),
    onAction: (CabinWallpaperPickerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Wallpaper picker" },
        family = "launcher",
        testTag = "cabin_wallpaper_picker",
        interaction = CabinWallpaperPickerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWallpaperPickerAction.Activate) },
        modifier = modifier,
    )
}

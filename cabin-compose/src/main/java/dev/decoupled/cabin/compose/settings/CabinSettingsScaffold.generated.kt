@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.settings

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.settings.*

/**
 * Experimental scaffold: Preference row.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPreference(
    state: CabinPreferenceState = CabinPreferenceState(),
    onAction: (CabinPreferenceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Preference row" },
        family = "settings",
        testTag = "cabin_preference",
        interaction = CabinPreferenceInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPreferenceAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Two-pane settings.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPreferenceScaffold(
    state: CabinPreferenceScaffoldState = CabinPreferenceScaffoldState(),
    onAction: (CabinPreferenceScaffoldAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Two-pane settings" },
        family = "settings",
        testTag = "cabin_preference_scaffold",
        interaction = CabinPreferenceScaffoldInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPreferenceScaffoldAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Settings search.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSettingsSearch(
    state: CabinSettingsSearchState = CabinSettingsSearchState(),
    onAction: (CabinSettingsSearchAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Settings search" },
        family = "settings",
        testTag = "cabin_settings_search",
        interaction = CabinSettingsSearchInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSettingsSearchAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Settings tile.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSettingsTile(
    state: CabinSettingsTileState = CabinSettingsTileState(),
    onAction: (CabinSettingsTileAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Settings tile" },
        family = "settings",
        testTag = "cabin_settings_tile",
        interaction = CabinSettingsTileInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSettingsTileAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Settings homepage.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSettingsHomepage(
    state: CabinSettingsHomepageState = CabinSettingsHomepageState(),
    onAction: (CabinSettingsHomepageAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Settings homepage" },
        family = "settings",
        testTag = "cabin_settings_homepage",
        interaction = CabinSettingsHomepageInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSettingsHomepageAction.Activate) },
        modifier = modifier,
    )
}

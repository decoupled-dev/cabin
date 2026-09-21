@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.settings

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinPreferenceState(
    val label: String = "Preference row",
    val variant: String = "switch",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPreferenceAction {
    data object Activate : CabinPreferenceAction
}

val CabinPreferenceInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkSettings

@CabinScaffold
data class CabinPreferenceScaffoldState(
    val label: String = "Two-pane settings",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPreferenceScaffoldAction {
    data object Activate : CabinPreferenceScaffoldAction
}

val CabinPreferenceScaffoldInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkSettings

@CabinScaffold
data class CabinSettingsSearchState(
    val label: String = "Settings search",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSettingsSearchAction {
    data object Activate : CabinSettingsSearchAction
}

val CabinSettingsSearchInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinSettingsTileState(
    val label: String = "Settings tile",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSettingsTileAction {
    data object Activate : CabinSettingsTileAction
}

val CabinSettingsTileInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkSettings

@CabinScaffold
data class CabinSettingsHomepageState(
    val label: String = "Settings homepage",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSettingsHomepageAction {
    data object Activate : CabinSettingsHomepageAction
}

val CabinSettingsHomepageInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkSettings

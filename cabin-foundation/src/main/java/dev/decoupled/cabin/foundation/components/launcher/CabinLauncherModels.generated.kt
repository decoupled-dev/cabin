/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.launcher

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinLauncherScaffoldState(
    val label: String = "Launcher scaffold",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLauncherScaffoldAction {
    data object Activate : CabinLauncherScaffoldAction
}

val CabinLauncherScaffoldInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinAppIconState(
    val label: String = "App icon",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAppIconAction {
    data object Activate : CabinAppIconAction
}

val CabinAppIconInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinRecentsSwitcherState(
    val label: String = "Recents / task switcher",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRecentsSwitcherAction {
    data object Activate : CabinRecentsSwitcherAction
}

val CabinRecentsSwitcherInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinWidgetHostState(
    val label: String = "Widget host",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWidgetHostAction {
    data object Activate : CabinWidgetHostAction
}

val CabinWidgetHostInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinWidgetPickerState(
    val label: String = "Widget picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWidgetPickerAction {
    data object Activate : CabinWidgetPickerAction
}

val CabinWidgetPickerInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinWidgetEditModeState(
    val label: String = "Widget edit mode",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWidgetEditModeAction {
    data object Activate : CabinWidgetEditModeAction
}

val CabinWidgetEditModeInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinSuggestionCardState(
    val label: String = "Suggestion card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSuggestionCardAction {
    data object Activate : CabinSuggestionCardAction
}

val CabinSuggestionCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinShortcutTileState(
    val label: String = "Shortcut tile",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinShortcutTileAction {
    data object Activate : CabinShortcutTileAction
}

val CabinShortcutTileInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinHomeTemplateState(
    val label: String = "Home template",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHomeTemplateAction {
    data object Activate : CabinHomeTemplateAction
}

val CabinHomeTemplateInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinScreensaverState(
    val label: String = "Screensaver / idle",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinScreensaverAction {
    data object Activate : CabinScreensaverAction
}

val CabinScreensaverInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinWelcomeScreenState(
    val label: String = "Welcome screen",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWelcomeScreenAction {
    data object Activate : CabinWelcomeScreenAction
}

val CabinWelcomeScreenInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinWallpaperPickerState(
    val label: String = "Wallpaper picker",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWallpaperPickerAction {
    data object Activate : CabinWallpaperPickerAction
}

val CabinWallpaperPickerInteraction: CabinInteraction = CabinInteraction.ParkedOnly

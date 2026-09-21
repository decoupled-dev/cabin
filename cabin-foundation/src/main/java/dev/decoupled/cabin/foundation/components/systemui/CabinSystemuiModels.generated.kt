@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.systemui

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinQuickSettingsState(
    val label: String = "Quick settings",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinQuickSettingsAction {
    data object Activate : CabinQuickSettingsAction
}

val CabinQuickSettingsInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkSettings

@CabinScaffold
data class CabinNotificationCenterState(
    val label: String = "Notification center",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNotificationCenterAction {
    data object Activate : CabinNotificationCenterAction
}

val CabinNotificationCenterInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinHeadsUpNotificationState(
    val label: String = "Heads-up notification",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHeadsUpNotificationAction {
    data object Activate : CabinHeadsUpNotificationAction
}

val CabinHeadsUpNotificationInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVolumeUiState(
    val label: String = "Volume UI",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVolumeUiAction {
    data object Activate : CabinVolumeUiAction
}

val CabinVolumeUiInteraction: CabinInteraction = CabinInteraction.MediaTransport

@CabinScaffold
data class CabinBrightnessUiState(
    val label: String = "Brightness UI",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBrightnessUiAction {
    data object Activate : CabinBrightnessUiAction
}

val CabinBrightnessUiInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinCleanModeOverlayState(
    val label: String = "Clean-mode overlay",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCleanModeOverlayAction {
    data object Activate : CabinCleanModeOverlayAction
}

val CabinCleanModeOverlayInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinShutdownUiState(
    val label: String = "Power / shutdown UI",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinShutdownUiAction {
    data object Activate : CabinShutdownUiAction
}

val CabinShutdownUiInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinUpdateProgressState(
    val label: String = "Update in progress",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinUpdateProgressAction {
    data object Activate : CabinUpdateProgressAction
}

val CabinUpdateProgressInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDisplayModeSwitcherState(
    val label: String = "Display-mode switcher",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDisplayModeSwitcherAction {
    data object Activate : CabinDisplayModeSwitcherAction
}

val CabinDisplayModeSwitcherInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinImmersiveIndicatorState(
    val label: String = "Immersive-mode indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinImmersiveIndicatorAction {
    data object Activate : CabinImmersiveIndicatorAction
}

val CabinImmersiveIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinPrivacyIndicatorsState(
    val label: String = "Privacy indicators",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPrivacyIndicatorsAction {
    data object Activate : CabinPrivacyIndicatorsAction
}

val CabinPrivacyIndicatorsInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinEcallUiState(
    val label: String = "Emergency / eCall",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEcallUiAction {
    data object Activate : CabinEcallUiAction
}

val CabinEcallUiInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.feedback

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinDialogState(
    val label: String = "Dialog",
    val variant: String = "alert",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDialogAction {
    data object Activate : CabinDialogAction
}

val CabinDialogInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinSnackbarState(
    val label: String = "Snackbar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSnackbarAction {
    data object Activate : CabinSnackbarAction
}

val CabinSnackbarInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinToastState(
    val label: String = "Toast",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinToastAction {
    data object Activate : CabinToastAction
}

val CabinToastInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinInlineMessageState(
    val label: String = "Inline message",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinInlineMessageAction {
    data object Activate : CabinInlineMessageAction
}

val CabinInlineMessageInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinCalloutState(
    val label: String = "Callout",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCalloutAction {
    data object Activate : CabinCalloutAction
}

val CabinCalloutInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinLinearProgressState(
    val label: String = "Linear progress",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLinearProgressAction {
    data object Activate : CabinLinearProgressAction
}

val CabinLinearProgressInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinCircularProgressState(
    val label: String = "Circular progress",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCircularProgressAction {
    data object Activate : CabinCircularProgressAction
}

val CabinCircularProgressInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSkeletonState(
    val label: String = "Skeleton",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSkeletonAction {
    data object Activate : CabinSkeletonAction
}

val CabinSkeletonInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinStepProgressState(
    val label: String = "Step progress",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinStepProgressAction {
    data object Activate : CabinStepProgressAction
}

val CabinStepProgressInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinContextMenuState(
    val label: String = "Context menu",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinContextMenuAction {
    data object Activate : CabinContextMenuAction
}

val CabinContextMenuInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinTooltipState(
    val label: String = "Tooltip",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTooltipAction {
    data object Activate : CabinTooltipAction
}

val CabinTooltipInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinOfflineStateState(
    val label: String = "Offline state",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinOfflineStateAction {
    data object Activate : CabinOfflineStateAction
}

val CabinOfflineStateInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinNoPermissionStateState(
    val label: String = "No-permission state",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNoPermissionStateAction {
    data object Activate : CabinNoPermissionStateAction
}

val CabinNoPermissionStateInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDrivingRestrictedStateState(
    val label: String = "Driving-restricted state",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDrivingRestrictedStateAction {
    data object Activate : CabinDrivingRestrictedStateAction
}

val CabinDrivingRestrictedStateInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinBadgeState(
    val label: String = "Badge",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBadgeAction {
    data object Activate : CabinBadgeAction
}

val CabinBadgeInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinStatusPillState(
    val label: String = "Status pill",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinStatusPillAction {
    data object Activate : CabinStatusPillAction
}

val CabinStatusPillInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTagState(
    val label: String = "Tag",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTagAction {
    data object Activate : CabinTagAction
}

val CabinTagInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSplashScreenState(
    val label: String = "Splash / loading screen",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSplashScreenAction {
    data object Activate : CabinSplashScreenAction
}

val CabinSplashScreenInteraction: CabinInteraction = CabinInteraction.Glance

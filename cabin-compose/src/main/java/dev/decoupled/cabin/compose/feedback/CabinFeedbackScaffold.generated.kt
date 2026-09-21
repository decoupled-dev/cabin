@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.feedback

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.feedback.*

/**
 * Experimental scaffold: Dialog.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDialog(
    state: CabinDialogState = CabinDialogState(),
    onAction: (CabinDialogAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dialog" },
        family = "feedback",
        testTag = "cabin_dialog",
        interaction = CabinDialogInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDialogAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Snackbar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSnackbar(
    state: CabinSnackbarState = CabinSnackbarState(),
    onAction: (CabinSnackbarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Snackbar" },
        family = "feedback",
        testTag = "cabin_snackbar",
        interaction = CabinSnackbarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSnackbarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Toast.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinToast(
    state: CabinToastState = CabinToastState(),
    onAction: (CabinToastAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Toast" },
        family = "feedback",
        testTag = "cabin_toast",
        interaction = CabinToastInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinToastAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Inline message.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinInlineMessage(
    state: CabinInlineMessageState = CabinInlineMessageState(),
    onAction: (CabinInlineMessageAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Inline message" },
        family = "feedback",
        testTag = "cabin_inline_message",
        interaction = CabinInlineMessageInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinInlineMessageAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Callout.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCallout(
    state: CabinCalloutState = CabinCalloutState(),
    onAction: (CabinCalloutAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Callout" },
        family = "feedback",
        testTag = "cabin_callout",
        interaction = CabinCalloutInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCalloutAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Linear progress.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLinearProgress(
    state: CabinLinearProgressState = CabinLinearProgressState(),
    onAction: (CabinLinearProgressAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Linear progress" },
        family = "feedback",
        testTag = "cabin_linear_progress",
        interaction = CabinLinearProgressInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLinearProgressAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Circular progress.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCircularProgress(
    state: CabinCircularProgressState = CabinCircularProgressState(),
    onAction: (CabinCircularProgressAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Circular progress" },
        family = "feedback",
        testTag = "cabin_circular_progress",
        interaction = CabinCircularProgressInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCircularProgressAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Skeleton.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSkeleton(
    state: CabinSkeletonState = CabinSkeletonState(),
    onAction: (CabinSkeletonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Skeleton" },
        family = "feedback",
        testTag = "cabin_skeleton",
        interaction = CabinSkeletonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSkeletonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Step progress.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinStepProgress(
    state: CabinStepProgressState = CabinStepProgressState(),
    onAction: (CabinStepProgressAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Step progress" },
        family = "feedback",
        testTag = "cabin_step_progress",
        interaction = CabinStepProgressInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinStepProgressAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Context menu.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinContextMenu(
    state: CabinContextMenuState = CabinContextMenuState(),
    onAction: (CabinContextMenuAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Context menu" },
        family = "feedback",
        testTag = "cabin_context_menu",
        interaction = CabinContextMenuInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinContextMenuAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Tooltip.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTooltip(
    state: CabinTooltipState = CabinTooltipState(),
    onAction: (CabinTooltipAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Tooltip" },
        family = "feedback",
        testTag = "cabin_tooltip",
        interaction = CabinTooltipInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTooltipAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Offline state.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinOfflineState(
    state: CabinOfflineStateState = CabinOfflineStateState(),
    onAction: (CabinOfflineStateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Offline state" },
        family = "feedback",
        testTag = "cabin_offline_state",
        interaction = CabinOfflineStateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinOfflineStateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: No-permission state.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNoPermissionState(
    state: CabinNoPermissionStateState = CabinNoPermissionStateState(),
    onAction: (CabinNoPermissionStateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "No-permission state" },
        family = "feedback",
        testTag = "cabin_no_permission_state",
        interaction = CabinNoPermissionStateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNoPermissionStateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Driving-restricted state.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDrivingRestrictedState(
    state: CabinDrivingRestrictedStateState = CabinDrivingRestrictedStateState(),
    onAction: (CabinDrivingRestrictedStateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Driving-restricted state" },
        family = "feedback",
        testTag = "cabin_driving_restricted_state",
        interaction = CabinDrivingRestrictedStateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDrivingRestrictedStateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Badge.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBadge(
    state: CabinBadgeState = CabinBadgeState(),
    onAction: (CabinBadgeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Badge" },
        family = "feedback",
        testTag = "cabin_badge",
        interaction = CabinBadgeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBadgeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Status pill.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinStatusPill(
    state: CabinStatusPillState = CabinStatusPillState(),
    onAction: (CabinStatusPillAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Status pill" },
        family = "feedback",
        testTag = "cabin_status_pill",
        interaction = CabinStatusPillInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinStatusPillAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Tag.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTag(
    state: CabinTagState = CabinTagState(),
    onAction: (CabinTagAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Tag" },
        family = "feedback",
        testTag = "cabin_tag",
        interaction = CabinTagInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTagAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Splash / loading screen.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSplashScreen(
    state: CabinSplashScreenState = CabinSplashScreenState(),
    onAction: (CabinSplashScreenAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Splash / loading screen" },
        family = "feedback",
        testTag = "cabin_splash_screen",
        interaction = CabinSplashScreenInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSplashScreenAction.Activate) },
        modifier = modifier,
    )
}

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.systemui

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.systemui.*

/**
 * Experimental scaffold: Quick settings.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinQuickSettings(
    state: CabinQuickSettingsState = CabinQuickSettingsState(),
    onAction: (CabinQuickSettingsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Quick settings" },
        family = "systemui",
        testTag = "cabin_quick_settings",
        interaction = CabinQuickSettingsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinQuickSettingsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Notification center.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNotificationCenter(
    state: CabinNotificationCenterState = CabinNotificationCenterState(),
    onAction: (CabinNotificationCenterAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Notification center" },
        family = "systemui",
        testTag = "cabin_notification_center",
        interaction = CabinNotificationCenterInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNotificationCenterAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Heads-up notification.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHeadsUpNotification(
    state: CabinHeadsUpNotificationState = CabinHeadsUpNotificationState(),
    onAction: (CabinHeadsUpNotificationAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Heads-up notification" },
        family = "systemui",
        testTag = "cabin_heads_up_notification",
        interaction = CabinHeadsUpNotificationInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHeadsUpNotificationAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Volume UI.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVolumeUi(
    state: CabinVolumeUiState = CabinVolumeUiState(),
    onAction: (CabinVolumeUiAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Volume UI" },
        family = "systemui",
        testTag = "cabin_volume_ui",
        interaction = CabinVolumeUiInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVolumeUiAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Brightness UI.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBrightnessUi(
    state: CabinBrightnessUiState = CabinBrightnessUiState(),
    onAction: (CabinBrightnessUiAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Brightness UI" },
        family = "systemui",
        testTag = "cabin_brightness_ui",
        interaction = CabinBrightnessUiInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBrightnessUiAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Clean-mode overlay.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCleanModeOverlay(
    state: CabinCleanModeOverlayState = CabinCleanModeOverlayState(),
    onAction: (CabinCleanModeOverlayAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Clean-mode overlay" },
        family = "systemui",
        testTag = "cabin_clean_mode_overlay",
        interaction = CabinCleanModeOverlayInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCleanModeOverlayAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Power / shutdown UI.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinShutdownUi(
    state: CabinShutdownUiState = CabinShutdownUiState(),
    onAction: (CabinShutdownUiAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Power / shutdown UI" },
        family = "systemui",
        testTag = "cabin_shutdown_ui",
        interaction = CabinShutdownUiInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinShutdownUiAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Update in progress.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinUpdateProgress(
    state: CabinUpdateProgressState = CabinUpdateProgressState(),
    onAction: (CabinUpdateProgressAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Update in progress" },
        family = "systemui",
        testTag = "cabin_update_progress",
        interaction = CabinUpdateProgressInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinUpdateProgressAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Display-mode switcher.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDisplayModeSwitcher(
    state: CabinDisplayModeSwitcherState = CabinDisplayModeSwitcherState(),
    onAction: (CabinDisplayModeSwitcherAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Display-mode switcher" },
        family = "systemui",
        testTag = "cabin_display_mode_switcher",
        interaction = CabinDisplayModeSwitcherInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDisplayModeSwitcherAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Immersive-mode indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinImmersiveIndicator(
    state: CabinImmersiveIndicatorState = CabinImmersiveIndicatorState(),
    onAction: (CabinImmersiveIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Immersive-mode indicator" },
        family = "systemui",
        testTag = "cabin_immersive_indicator",
        interaction = CabinImmersiveIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinImmersiveIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Privacy indicators.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPrivacyIndicators(
    state: CabinPrivacyIndicatorsState = CabinPrivacyIndicatorsState(),
    onAction: (CabinPrivacyIndicatorsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Privacy indicators" },
        family = "systemui",
        testTag = "cabin_privacy_indicators",
        interaction = CabinPrivacyIndicatorsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPrivacyIndicatorsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Emergency / eCall.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEcallUi(
    state: CabinEcallUiState = CabinEcallUiState(),
    onAction: (CabinEcallUiAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Emergency / eCall" },
        family = "systemui",
        testTag = "cabin_ecall_ui",
        interaction = CabinEcallUiInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEcallUiAction.Activate) },
        modifier = modifier,
    )
}

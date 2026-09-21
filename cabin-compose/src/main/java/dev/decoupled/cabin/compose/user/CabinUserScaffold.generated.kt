@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.user

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.user.*

/**
 * Experimental scaffold: Profile switcher.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinProfileSwitcher(
    state: CabinProfileSwitcherState = CabinProfileSwitcherState(),
    onAction: (CabinProfileSwitcherAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Profile switcher" },
        family = "user",
        testTag = "cabin_profile_switcher",
        interaction = CabinProfileSwitcherInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinProfileSwitcherAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Profile card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinProfileCard(
    state: CabinProfileCardState = CabinProfileCardState(),
    onAction: (CabinProfileCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Profile card" },
        family = "user",
        testTag = "cabin_profile_card",
        interaction = CabinProfileCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinProfileCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Profile creation.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinProfileCreate(
    state: CabinProfileCreateState = CabinProfileCreateState(),
    onAction: (CabinProfileCreateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Profile creation" },
        family = "user",
        testTag = "cabin_profile_create",
        interaction = CabinProfileCreateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinProfileCreateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: PIN / pattern / password.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPinPatternPassword(
    state: CabinPinPatternPasswordState = CabinPinPatternPasswordState(),
    onAction: (CabinPinPatternPasswordAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "PIN / pattern / password" },
        family = "user",
        testTag = "cabin_pin_pattern_password",
        interaction = CabinPinPatternPasswordInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPinPatternPasswordAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Biometric prompt.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBiometricPrompt(
    state: CabinBiometricPromptState = CabinBiometricPromptState(),
    onAction: (CabinBiometricPromptAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Biometric prompt" },
        family = "user",
        testTag = "cabin_biometric_prompt",
        interaction = CabinBiometricPromptInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBiometricPromptAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Onboarding wizard.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinOnboardingWizard(
    state: CabinOnboardingWizardState = CabinOnboardingWizardState(),
    onAction: (CabinOnboardingWizardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Onboarding wizard" },
        family = "user",
        testTag = "cabin_onboarding_wizard",
        interaction = CabinOnboardingWizardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinOnboardingWizardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: EULA / consent.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEulaConsent(
    state: CabinEulaConsentState = CabinEulaConsentState(),
    onAction: (CabinEulaConsentAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "EULA / consent" },
        family = "user",
        testTag = "cabin_eula_consent",
        interaction = CabinEulaConsentInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEulaConsentAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Account linking.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAccountLinking(
    state: CabinAccountLinkingState = CabinAccountLinkingState(),
    onAction: (CabinAccountLinkingAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Account linking" },
        family = "user",
        testTag = "cabin_account_linking",
        interaction = CabinAccountLinkingInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAccountLinkingAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Privacy notice.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPrivacyNotice(
    state: CabinPrivacyNoticeState = CabinPrivacyNoticeState(),
    onAction: (CabinPrivacyNoticeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Privacy notice" },
        family = "user",
        testTag = "cabin_privacy_notice",
        interaction = CabinPrivacyNoticeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPrivacyNoticeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Child-lock notice.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinChildLockNotice(
    state: CabinChildLockNoticeState = CabinChildLockNoticeState(),
    onAction: (CabinChildLockNoticeAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Child-lock notice" },
        family = "user",
        testTag = "cabin_child_lock_notice",
        interaction = CabinChildLockNoticeInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinChildLockNoticeAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Factory reset confirm.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFactoryResetConfirm(
    state: CabinFactoryResetConfirmState = CabinFactoryResetConfirmState(),
    onAction: (CabinFactoryResetConfirmAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Factory reset confirm" },
        family = "user",
        testTag = "cabin_factory_reset_confirm",
        interaction = CabinFactoryResetConfirmInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFactoryResetConfirmAction.Activate) },
        modifier = modifier,
    )
}

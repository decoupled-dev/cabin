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

package dev.decoupled.cabin.foundation.components.user

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinProfileSwitcherState(
    val label: String = "Profile switcher",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinProfileSwitcherAction {
    data object Activate : CabinProfileSwitcherAction
}

val CabinProfileSwitcherInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinProfileCardState(
    val label: String = "Profile card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinProfileCardAction {
    data object Activate : CabinProfileCardAction
}

val CabinProfileCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinProfileCreateState(
    val label: String = "Profile creation",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinProfileCreateAction {
    data object Activate : CabinProfileCreateAction
}

val CabinProfileCreateInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinPinPatternPasswordState(
    val label: String = "PIN / pattern / password",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPinPatternPasswordAction {
    data object Activate : CabinPinPatternPasswordAction
}

val CabinPinPatternPasswordInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinBiometricPromptState(
    val label: String = "Biometric prompt",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBiometricPromptAction {
    data object Activate : CabinBiometricPromptAction
}

val CabinBiometricPromptInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinOnboardingWizardState(
    val label: String = "Onboarding wizard",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinOnboardingWizardAction {
    data object Activate : CabinOnboardingWizardAction
}

val CabinOnboardingWizardInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinEulaConsentState(
    val label: String = "EULA / consent",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEulaConsentAction {
    data object Activate : CabinEulaConsentAction
}

val CabinEulaConsentInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinAccountLinkingState(
    val label: String = "Account linking",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAccountLinkingAction {
    data object Activate : CabinAccountLinkingAction
}

val CabinAccountLinkingInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinPrivacyNoticeState(
    val label: String = "Privacy notice",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPrivacyNoticeAction {
    data object Activate : CabinPrivacyNoticeAction
}

val CabinPrivacyNoticeInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinChildLockNoticeState(
    val label: String = "Child-lock notice",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinChildLockNoticeAction {
    data object Activate : CabinChildLockNoticeAction
}

val CabinChildLockNoticeInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinFactoryResetConfirmState(
    val label: String = "Factory reset confirm",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFactoryResetConfirmAction {
    data object Activate : CabinFactoryResetConfirmAction
}

val CabinFactoryResetConfirmInteraction: CabinInteraction = CabinInteraction.ParkedOnly

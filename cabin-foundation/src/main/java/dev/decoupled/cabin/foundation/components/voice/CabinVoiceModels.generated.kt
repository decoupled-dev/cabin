@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.voice

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinAssistantSurfaceState(
    val label: String = "Assistant surface",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAssistantSurfaceAction {
    data object Activate : CabinAssistantSurfaceAction
}

val CabinAssistantSurfaceInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinInvocationButtonState(
    val label: String = "Invocation button",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinInvocationButtonAction {
    data object Activate : CabinInvocationButtonAction
}

val CabinInvocationButtonInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinMicPrivacyIndicatorState(
    val label: String = "Mic privacy indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMicPrivacyIndicatorAction {
    data object Activate : CabinMicPrivacyIndicatorAction
}

val CabinMicPrivacyIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSuggestionChipsState(
    val label: String = "Voice suggestion chips",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSuggestionChipsAction {
    data object Activate : CabinSuggestionChipsAction
}

val CabinSuggestionChipsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinYouCanSayBarState(
    val label: String = "You-can-say bar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinYouCanSayBarAction {
    data object Activate : CabinYouCanSayBarAction
}

val CabinYouCanSayBarInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVoiceResultsCardState(
    val label: String = "Voice results card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVoiceResultsCardAction {
    data object Activate : CabinVoiceResultsCardAction
}

val CabinVoiceResultsCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVoiceConfirmCancelState(
    val label: String = "Voice confirm / cancel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVoiceConfirmCancelAction {
    data object Activate : CabinVoiceConfirmCancelAction
}

val CabinVoiceConfirmCancelInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBargeInIndicatorState(
    val label: String = "Barge-in indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBargeInIndicatorAction {
    data object Activate : CabinBargeInIndicatorAction
}

val CabinBargeInIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinMultiZoneVoiceState(
    val label: String = "Multi-zone voice",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMultiZoneVoiceAction {
    data object Activate : CabinMultiZoneVoiceAction
}

val CabinMultiZoneVoiceInteraction: CabinInteraction = CabinInteraction.Glance

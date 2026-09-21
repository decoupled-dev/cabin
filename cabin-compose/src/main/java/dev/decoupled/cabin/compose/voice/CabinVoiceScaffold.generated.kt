@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.voice

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.voice.*

/**
 * Experimental scaffold: Assistant surface.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAssistantSurface(
    state: CabinAssistantSurfaceState = CabinAssistantSurfaceState(),
    onAction: (CabinAssistantSurfaceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Assistant surface" },
        family = "voice",
        testTag = "cabin_assistant_surface",
        interaction = CabinAssistantSurfaceInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAssistantSurfaceAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Invocation button.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinInvocationButton(
    state: CabinInvocationButtonState = CabinInvocationButtonState(),
    onAction: (CabinInvocationButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Invocation button" },
        family = "voice",
        testTag = "cabin_invocation_button",
        interaction = CabinInvocationButtonInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinInvocationButtonAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Mic privacy indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMicPrivacyIndicator(
    state: CabinMicPrivacyIndicatorState = CabinMicPrivacyIndicatorState(),
    onAction: (CabinMicPrivacyIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Mic privacy indicator" },
        family = "voice",
        testTag = "cabin_mic_privacy_indicator",
        interaction = CabinMicPrivacyIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMicPrivacyIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Voice suggestion chips.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSuggestionChips(
    state: CabinSuggestionChipsState = CabinSuggestionChipsState(),
    onAction: (CabinSuggestionChipsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Voice suggestion chips" },
        family = "voice",
        testTag = "cabin_suggestion_chips",
        interaction = CabinSuggestionChipsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSuggestionChipsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: You-can-say bar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinYouCanSayBar(
    state: CabinYouCanSayBarState = CabinYouCanSayBarState(),
    onAction: (CabinYouCanSayBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "You-can-say bar" },
        family = "voice",
        testTag = "cabin_you_can_say_bar",
        interaction = CabinYouCanSayBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinYouCanSayBarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Voice results card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVoiceResultsCard(
    state: CabinVoiceResultsCardState = CabinVoiceResultsCardState(),
    onAction: (CabinVoiceResultsCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Voice results card" },
        family = "voice",
        testTag = "cabin_voice_results_card",
        interaction = CabinVoiceResultsCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVoiceResultsCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Voice confirm / cancel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVoiceConfirmCancel(
    state: CabinVoiceConfirmCancelState = CabinVoiceConfirmCancelState(),
    onAction: (CabinVoiceConfirmCancelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Voice confirm / cancel" },
        family = "voice",
        testTag = "cabin_confirm_cancel",
        interaction = CabinVoiceConfirmCancelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVoiceConfirmCancelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Barge-in indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBargeInIndicator(
    state: CabinBargeInIndicatorState = CabinBargeInIndicatorState(),
    onAction: (CabinBargeInIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Barge-in indicator" },
        family = "voice",
        testTag = "cabin_barge_in_indicator",
        interaction = CabinBargeInIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBargeInIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Multi-zone voice.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMultiZoneVoice(
    state: CabinMultiZoneVoiceState = CabinMultiZoneVoiceState(),
    onAction: (CabinMultiZoneVoiceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Multi-zone voice" },
        family = "voice",
        testTag = "cabin_multi_zone_voice",
        interaction = CabinMultiZoneVoiceInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMultiZoneVoiceAction.Activate) },
        modifier = modifier,
    )
}

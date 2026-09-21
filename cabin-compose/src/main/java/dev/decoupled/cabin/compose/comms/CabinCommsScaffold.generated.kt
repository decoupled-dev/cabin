@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.comms

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.comms.*

/**
 * Experimental scaffold: Dialer.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDialer(
    state: CabinDialerState = CabinDialerState(),
    onAction: (CabinDialerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Dialer" },
        family = "comms",
        testTag = "cabin_dialer",
        interaction = CabinDialerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDialerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: In-call screen.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinInCallScreen(
    state: CabinInCallScreenState = CabinInCallScreenState(),
    onAction: (CabinInCallScreenAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "In-call screen" },
        family = "comms",
        testTag = "cabin_in_call_screen",
        interaction = CabinInCallScreenInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinInCallScreenAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Incoming-call HUD.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinIncomingCallHud(
    state: CabinIncomingCallHudState = CabinIncomingCallHudState(),
    onAction: (CabinIncomingCallHudAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Incoming-call HUD" },
        family = "comms",
        testTag = "cabin_incoming_call_hud",
        interaction = CabinIncomingCallHudInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinIncomingCallHudAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Call controls.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCallControls(
    state: CabinCallControlsState = CabinCallControlsState(),
    onAction: (CabinCallControlsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Call controls" },
        family = "comms",
        testTag = "cabin_call_controls",
        interaction = CabinCallControlsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCallControlsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Contact list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinContactList(
    state: CabinContactListState = CabinContactListState(),
    onAction: (CabinContactListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Contact list" },
        family = "comms",
        testTag = "cabin_contact_list",
        interaction = CabinContactListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinContactListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Contact card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinContactCard(
    state: CabinContactCardState = CabinContactCardState(),
    onAction: (CabinContactCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Contact card" },
        family = "comms",
        testTag = "cabin_contact_card",
        interaction = CabinContactCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinContactCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Conversation list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinConversationList(
    state: CabinConversationListState = CabinConversationListState(),
    onAction: (CabinConversationListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Conversation list" },
        family = "comms",
        testTag = "cabin_conversation_list",
        interaction = CabinConversationListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinConversationListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Message bubble.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMessageBubble(
    state: CabinMessageBubbleState = CabinMessageBubbleState(),
    onAction: (CabinMessageBubbleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Message bubble" },
        family = "comms",
        testTag = "cabin_message_bubble",
        interaction = CabinMessageBubbleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMessageBubbleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Voice reply.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVoiceReply(
    state: CabinVoiceReplyState = CabinVoiceReplyState(),
    onAction: (CabinVoiceReplyAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Voice reply" },
        family = "comms",
        testTag = "cabin_voice_reply",
        interaction = CabinVoiceReplyInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVoiceReplyAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Canned reply chips.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCannedReplyChips(
    state: CabinCannedReplyChipsState = CabinCannedReplyChipsState(),
    onAction: (CabinCannedReplyChipsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Canned reply chips" },
        family = "comms",
        testTag = "cabin_canned_reply_chips",
        interaction = CabinCannedReplyChipsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCannedReplyChipsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Bluetooth pairing.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBluetoothPairing(
    state: CabinBluetoothPairingState = CabinBluetoothPairingState(),
    onAction: (CabinBluetoothPairingAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Bluetooth pairing" },
        family = "comms",
        testTag = "cabin_bluetooth_pairing",
        interaction = CabinBluetoothPairingInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBluetoothPairingAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Device list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDeviceList(
    state: CabinDeviceListState = CabinDeviceListState(),
    onAction: (CabinDeviceListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Device list" },
        family = "comms",
        testTag = "cabin_device_list",
        interaction = CabinDeviceListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDeviceListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Projection status.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinProjectionStatus(
    state: CabinProjectionStatusState = CabinProjectionStatusState(),
    onAction: (CabinProjectionStatusAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Projection status" },
        family = "comms",
        testTag = "cabin_projection_status",
        interaction = CabinProjectionStatusInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinProjectionStatusAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Voicemail.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinVoicemail(
    state: CabinVoicemailState = CabinVoicemailState(),
    onAction: (CabinVoicemailAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Voicemail" },
        family = "comms",
        testTag = "cabin_voicemail",
        interaction = CabinVoicemailInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinVoicemailAction.Activate) },
        modifier = modifier,
    )
}

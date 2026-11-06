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

package dev.decoupled.cabin.foundation.components.comms

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinDialerState(
    val label: String = "Dialer",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDialerAction {
    data object Activate : CabinDialerAction
}

val CabinDialerInteraction: CabinInteraction = CabinInteraction.OpenKeyboard

@CabinScaffold
data class CabinInCallScreenState(
    val label: String = "In-call screen",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinInCallScreenAction {
    data object Activate : CabinInCallScreenAction
}

val CabinInCallScreenInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinIncomingCallHudState(
    val label: String = "Incoming-call HUD",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinIncomingCallHudAction {
    data object Activate : CabinIncomingCallHudAction
}

val CabinIncomingCallHudInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinCallControlsState(
    val label: String = "Call controls",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCallControlsAction {
    data object Activate : CabinCallControlsAction
}

val CabinCallControlsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinContactListState(
    val label: String = "Contact list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinContactListAction {
    data object Activate : CabinContactListAction
}

val CabinContactListInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinContactCardState(
    val label: String = "Contact card",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinContactCardAction {
    data object Activate : CabinContactCardAction
}

val CabinContactCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinConversationListState(
    val label: String = "Conversation list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinConversationListAction {
    data object Activate : CabinConversationListAction
}

val CabinConversationListInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinMessageBubbleState(
    val label: String = "Message bubble",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMessageBubbleAction {
    data object Activate : CabinMessageBubbleAction
}

val CabinMessageBubbleInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVoiceReplyState(
    val label: String = "Voice reply",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVoiceReplyAction {
    data object Activate : CabinVoiceReplyAction
}

val CabinVoiceReplyInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinCannedReplyChipsState(
    val label: String = "Canned reply chips",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCannedReplyChipsAction {
    data object Activate : CabinCannedReplyChipsAction
}

val CabinCannedReplyChipsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBluetoothPairingState(
    val label: String = "Bluetooth pairing",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBluetoothPairingAction {
    data object Activate : CabinBluetoothPairingAction
}

val CabinBluetoothPairingInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinDeviceListState(
    val label: String = "Device list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDeviceListAction {
    data object Activate : CabinDeviceListAction
}

val CabinDeviceListInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinProjectionStatusState(
    val label: String = "Projection status",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinProjectionStatusAction {
    data object Activate : CabinProjectionStatusAction
}

val CabinProjectionStatusInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinVoicemailState(
    val label: String = "Voicemail",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinVoicemailAction {
    data object Activate : CabinVoicemailAction
}

val CabinVoicemailInteraction: CabinInteraction = CabinInteraction.ParkedOnly

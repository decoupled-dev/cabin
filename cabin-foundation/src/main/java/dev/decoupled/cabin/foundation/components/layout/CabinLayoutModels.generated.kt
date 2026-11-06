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

package dev.decoupled.cabin.foundation.components.layout

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinPaneDividerState(
    val label: String = "Pane divider",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPaneDividerAction {
    data object Activate : CabinPaneDividerAction
}

val CabinPaneDividerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinCollapsibleSidePanelState(
    val label: String = "Collapsible side panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCollapsibleSidePanelAction {
    data object Activate : CabinCollapsibleSidePanelAction
}

val CabinCollapsibleSidePanelInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinAdaptiveContainerState(
    val label: String = "Adaptive container",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAdaptiveContainerAction {
    data object Activate : CabinAdaptiveContainerAction
}

val CabinAdaptiveContainerInteraction: CabinInteraction = CabinInteraction.Glance

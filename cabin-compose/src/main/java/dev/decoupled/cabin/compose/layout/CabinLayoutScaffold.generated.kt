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

package dev.decoupled.cabin.compose.layout

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.layout.*

/**
 * Experimental scaffold: Pane divider.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPaneDivider(
    state: CabinPaneDividerState = CabinPaneDividerState(),
    onAction: (CabinPaneDividerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Pane divider" },
        family = "layout",
        testTag = "cabin_pane_divider",
        interaction = CabinPaneDividerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPaneDividerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Collapsible side panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCollapsibleSidePanel(
    state: CabinCollapsibleSidePanelState = CabinCollapsibleSidePanelState(),
    onAction: (CabinCollapsibleSidePanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Collapsible side panel" },
        family = "layout",
        testTag = "cabin_collapsible_side_panel",
        interaction = CabinCollapsibleSidePanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCollapsibleSidePanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Adaptive container.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAdaptiveContainer(
    state: CabinAdaptiveContainerState = CabinAdaptiveContainerState(),
    onAction: (CabinAdaptiveContainerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Adaptive container" },
        family = "layout",
        testTag = "cabin_adaptive_container",
        interaction = CabinAdaptiveContainerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAdaptiveContainerAction.Activate) },
        modifier = modifier,
    )
}

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

package dev.decoupled.cabin.compliance

/**
 * Restriction Engine / compliance policy surface.
 *
 * No Compose or Views widget dependencies. Chrome queries this API instead of
 * embedding OEM if/else trees (ADR 0004).
 */
interface CabinCompliance {
    /** Map adapter signals to a [CabinUiMode]. */
    fun uiMode(state: VehicleUiState): CabinUiMode

    /**
     * `true` when the interaction may activate ([GateDisposition.Allow]).
     * Substitute and Block both return `false`.
     */
    fun allows(interaction: CabinInteraction, state: VehicleUiState): Boolean

    /** Allow / substitute / block for the interaction under [state]. */
    fun disposition(interaction: CabinInteraction, state: VehicleUiState): GateDisposition

    /**
     * Baseline minimum touch target in dp from cabin-tokens.
     * Programs may tighten via policy overlays later.
     */
    fun touchTargetMinDp(state: VehicleUiState): Int
}

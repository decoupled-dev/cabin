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

import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Default Cabin Restriction Engine for System Bar and Status Bar interaction
 * categories per docs/compliance/restriction-states.md.
 *
 * Policy notes (MVP Alpha + Layer 2 domain gates):
 * - [CabinUiMode.Unknown] is evaluated with the **Restricted** matrix
 *   (fail-safe for complex chrome entry).
 * - Restricted + [CabinInteraction.HvacPeek] defaults to [GateDisposition.Allow]
 *   for a limited climate peek; programs may tighten to Substitute.
 * - [CabinInteraction.HvacQuick] (single-tap temp / fan / seat / defrost) is
 *   **Allow** in every mode.
 * - [CabinInteraction.VehicleQuick] (single-tap lock / lights / drive mode)
 *   is **Allow** in every mode; [CabinInteraction.VehicleAdjust] follows the
 *   HvacAdjust matrix.
 * - [CabinInteraction.HvacAdjust] (multi-step climate setup) is **Block** while Moving /
 *   Restricted / Unknown (fail-closed); Parked / Idling Allow.
 * - [CabinInteraction.MediaComplex] is Block while Moving / Restricted /
 *   Unknown; Idling Substitute; Parked Allow. [CabinInteraction.MediaTransport]
 *   stays Allow.
 */
class CabinRestrictionEngine(
    private val treatUnknownAs: CabinUiMode = CabinUiMode.Restricted,
) : CabinCompliance {

    override fun uiMode(state: VehicleUiState): CabinUiMode {
        if (state.uxRestrictions.contains(UxRestriction.DistractionOptimized)) {
            return CabinUiMode.Restricted
        }

        when (isMoving(state)) {
            true -> return CabinUiMode.Moving
            false -> {
                return when (state.parkMode) {
                    true -> CabinUiMode.Parked
                    false -> CabinUiMode.Idling
                    null -> {
                        // Speed known ≈ 0 but park unknown — still distraction-sensitive.
                        if (state.speedKph != null || state.isDriving == false) {
                            CabinUiMode.Idling
                        } else {
                            CabinUiMode.Unknown
                        }
                    }
                }
            }
            null -> {
                return when (state.parkMode) {
                    true -> CabinUiMode.Parked
                    false -> CabinUiMode.Idling
                    null -> CabinUiMode.Unknown
                }
            }
        }
    }

    override fun allows(interaction: CabinInteraction, state: VehicleUiState): Boolean =
        disposition(interaction, state) == GateDisposition.Allow

    override fun disposition(
        interaction: CabinInteraction,
        state: VehicleUiState,
    ): GateDisposition {
        val mode = uiMode(state).let { mode ->
            if (mode == CabinUiMode.Unknown) treatUnknownAs else mode
        }
        return matrixDisposition(interaction, mode)
    }

    override fun touchTargetMinDp(state: VehicleUiState): Int =
        CabinTokens.Size.Touch.minimum.dp.toInt()

    private fun isMoving(state: VehicleUiState): Boolean? {
        if (state.isDriving == true) return true
        val speed = state.speedKph
        if (speed != null) {
            return speed >= state.movingSpeedThresholdKph
        }
        if (state.isDriving == false) return false
        return null
    }

    private fun matrixDisposition(
        interaction: CabinInteraction,
        mode: CabinUiMode,
    ): GateDisposition {
        return when (interaction) {
            CabinInteraction.Glance,
            CabinInteraction.NavigateSimple,
            CabinInteraction.MediaTransport,
            CabinInteraction.HvacPeek,
            CabinInteraction.HvacQuick,
            CabinInteraction.VehicleQuick,
            -> GateDisposition.Allow

            CabinInteraction.HvacAdjust -> when (mode) {
                CabinUiMode.Parked,
                CabinUiMode.Idling,
                -> GateDisposition.Allow
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }

            CabinInteraction.MediaComplex -> when (mode) {
                CabinUiMode.Parked -> GateDisposition.Allow
                CabinUiMode.Idling -> GateDisposition.Substitute
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }

            CabinInteraction.OpenComplexApp -> when (mode) {
                CabinUiMode.Parked -> GateDisposition.Allow
                CabinUiMode.Idling -> GateDisposition.Substitute
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }

            CabinInteraction.OpenKeyboard,
            CabinInteraction.FilterOrSort,
            -> when (mode) {
                CabinUiMode.Parked -> GateDisposition.Allow
                CabinUiMode.Idling,
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }

            CabinInteraction.StatusDeepLinkInformational -> when (mode) {
                CabinUiMode.Parked,
                CabinUiMode.Idling,
                -> GateDisposition.Allow
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Substitute
            }

            CabinInteraction.StatusDeepLinkSettings -> when (mode) {
                CabinUiMode.Parked -> GateDisposition.Allow
                CabinUiMode.Idling -> GateDisposition.Substitute
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }

            CabinInteraction.ParkedOnly -> when (mode) {
                CabinUiMode.Parked -> GateDisposition.Allow
                CabinUiMode.Idling,
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }

            CabinInteraction.VehicleAdjust -> when (mode) {
                CabinUiMode.Parked,
                CabinUiMode.Idling,
                -> GateDisposition.Allow
                CabinUiMode.Moving,
                CabinUiMode.Restricted,
                CabinUiMode.Unknown,
                -> GateDisposition.Block
            }
        }
    }

    companion object {
        /** Shared baseline instance for hosts that do not need custom policy. */
        val Default: CabinCompliance = CabinRestrictionEngine()
    }
}

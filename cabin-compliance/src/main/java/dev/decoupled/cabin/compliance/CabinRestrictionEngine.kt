package dev.decoupled.cabin.compliance

import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Default Cabin Restriction Engine for System Bar and Status Bar interaction
 * categories per docs/compliance/restriction-states.md.
 *
 * Policy notes (MVP Alpha):
 * - [CabinUiMode.Unknown] is evaluated with the **Restricted** matrix
 *   (fail-safe for complex chrome entry).
 * - Restricted + [CabinInteraction.HvacPeek] defaults to [GateDisposition.Allow]
 *   for a limited climate peek; programs may tighten to Substitute.
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
            -> GateDisposition.Allow

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
        }
    }

    companion object {
        /** Shared baseline instance for hosts that do not need custom policy. */
        val Default: CabinCompliance = CabinRestrictionEngine()
    }
}

package dev.decoupled.cabin.compose.compliance

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import dev.decoupled.cabin.compliance.CabinCompliance
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.VehicleUiState

/**
 * Compose-side Restriction Engine host state.
 *
 * Widgets query [disposition] — they must not embed driving/UX if/else trees
 * (ADR 0004). Mirrors Views [dev.decoupled.cabin.views.compliance.CabinComplianceHost]
 * meaning without depending on cabin-views.
 */
@Immutable
data class CabinComplianceState(
    val policy: CabinCompliance = CabinRestrictionEngine.Default,
    val vehicleState: VehicleUiState = VehicleUiState.unknown(),
) {
    fun disposition(interaction: CabinInteraction): GateDisposition =
        policy.disposition(interaction, vehicleState)

    fun allows(interaction: CabinInteraction): Boolean =
        policy.allows(interaction, vehicleState)

    fun touchTargetMinDp(): Int = policy.touchTargetMinDp(vehicleState)

    companion object {
        /**
         * Sentinel used when [LocalCabinComplianceState] is absent.
         * All activating interactions resolve to [GateDisposition.Block]
         * (fail-closed — same contract as a null Views host).
         */
        val Absent: CabinComplianceState = CabinComplianceState(
            policy = FailClosedCompliance,
            vehicleState = VehicleUiState.unknown(),
        )
    }
}

/**
 * Fail-closed policy: every interaction is [GateDisposition.Block].
 * Used when product chrome forgot to provide compliance via [CabinTheme].
 */
private object FailClosedCompliance : CabinCompliance {
    override fun uiMode(state: VehicleUiState) =
        CabinRestrictionEngine.Default.uiMode(state)

    override fun allows(interaction: CabinInteraction, state: VehicleUiState): Boolean = false

    override fun disposition(
        interaction: CabinInteraction,
        state: VehicleUiState,
    ): GateDisposition = GateDisposition.Block

    override fun touchTargetMinDp(state: VehicleUiState): Int =
        CabinRestrictionEngine.Default.touchTargetMinDp(state)
}

/**
 * Provided by [dev.decoupled.cabin.compose.theme.CabinTheme].
 * Default [CabinComplianceState.Absent] is fail-closed.
 */
val LocalCabinComplianceState = staticCompositionLocalOf {
    CabinComplianceState.Absent
}

/** Resolve gate for [interaction]; missing local → Block. */
fun dispositionOf(
    compliance: CabinComplianceState?,
    interaction: CabinInteraction,
): GateDisposition {
    val host = compliance ?: return GateDisposition.Block
    return host.disposition(interaction)
}

package dev.decoupled.cabin.views.compliance

import dev.decoupled.cabin.compliance.CabinCompliance
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.VehicleUiState

/**
 * Views host that holds Restriction Engine policy + current [VehicleUiState].
 *
 * Widgets declare [CabinInteraction]s and query this host — they must not
 * embed driving/UX if/else trees (ADR 0004).
 */
class CabinComplianceHost(
    policy: CabinCompliance = CabinRestrictionEngine.Default,
    initialState: VehicleUiState = VehicleUiState.unknown(),
) {
    var policy: CabinCompliance = policy
        private set

    var state: VehicleUiState = initialState
        private set

    private val listeners = mutableListOf<() -> Unit>()

    /** Replace the Restriction Engine implementation. */
    fun attach(policy: CabinCompliance) {
        this.policy = policy
        notifyChanged()
    }

    /** Push adapter-owned vehicle / UX signals. */
    fun updateState(state: VehicleUiState) {
        this.state = state
        notifyChanged()
    }

    fun disposition(interaction: CabinInteraction): GateDisposition =
        policy.disposition(interaction, state)

    fun allows(interaction: CabinInteraction): Boolean =
        policy.allows(interaction, state)

    fun touchTargetMinDp(): Int = policy.touchTargetMinDp(state)

    fun addOnChangeListener(listener: () -> Unit) {
        listeners += listener
    }

    fun removeOnChangeListener(listener: () -> Unit) {
        listeners -= listener
    }

    private fun notifyChanged() {
        // Copy to avoid ConcurrentModification if a listener detaches.
        listeners.toList().forEach { it.invoke() }
    }
}

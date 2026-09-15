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

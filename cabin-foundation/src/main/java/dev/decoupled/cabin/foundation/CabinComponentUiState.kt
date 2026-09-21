package dev.decoupled.cabin.foundation

/**
 * Cross-cutting visual / interaction state every scaffold component carries.
 *
 * Restriction is applied by widgets via the Restriction Engine — [restricted]
 * is a presentation hint after gating, not a substitute for
 * [dev.decoupled.cabin.compliance.CabinCompliance.disposition].
 */
data class CabinComponentUiState(
    val enabled: Boolean = true,
    val focused: Boolean = false,
    val pressed: Boolean = false,
    val selected: Boolean = false,
    val disabled: Boolean = false,
    val error: Boolean = false,
    val loading: Boolean = false,
    val restricted: Boolean = false,
) {
    val effectivelyEnabled: Boolean
        get() = enabled && !disabled && !restricted && !loading
}

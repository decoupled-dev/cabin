package dev.decoupled.cabin.compose

import dev.decoupled.cabin.compliance.GateDisposition

/**
 * Shared Restriction Engine visual / activation outcomes for Compose widgets.
 *
 * Widgets must query [dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState]
 * for disposition — never embed driving if/else trees (ADR 0004).
 */
object GateVisuals {
    fun alpha(disposition: GateDisposition): Float = when (disposition) {
        GateDisposition.Allow -> 1f
        GateDisposition.Substitute -> 0.55f
        GateDisposition.Block -> 0.4f
    }

    /** Domain-tile RE-quiet disable (no heavy wash while Moving). */
    fun quietAlpha(disposition: GateDisposition): Float = when (disposition) {
        GateDisposition.Allow -> 1f
        GateDisposition.Substitute -> 0.75f
        GateDisposition.Block -> 0.7f
    }

    fun activatable(enabled: Boolean, disposition: GateDisposition): Boolean =
        enabled && disposition == GateDisposition.Allow
}

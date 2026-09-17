package dev.decoupled.cabin.views

import android.view.View
import dev.decoupled.cabin.compliance.GateDisposition

/**
 * Shared Restriction Engine visual / activation outcomes for Views widgets.
 *
 * Widgets must query [dev.decoupled.cabin.views.compliance.CabinComplianceHost]
 * for disposition — never embed driving if/else trees (ADR 0004).
 */
internal object GateVisuals {

    /** Chrome / bar gating — more visible disable. */
    fun alpha(disposition: GateDisposition): Float = when (disposition) {
        GateDisposition.Allow -> 1f
        GateDisposition.Substitute -> 0.55f
        GateDisposition.Block -> 0.4f
    }

    /**
     * Domain-tile gating while Moving — **RE-quiet**: keep glanceable layout,
     * soft-disable activators without a heavy grey wash.
     */
    fun quietAlpha(disposition: GateDisposition): Float = when (disposition) {
        GateDisposition.Allow -> 1f
        GateDisposition.Substitute -> 0.75f
        GateDisposition.Block -> 0.7f
    }

    /** Product-enabled AND gate Allow. */
    fun activatable(enabled: Boolean, disposition: GateDisposition): Boolean =
        enabled && disposition == GateDisposition.Allow

    fun apply(view: View, enabled: Boolean, disposition: GateDisposition) {
        apply(view, enabled, disposition, quiet = false)
    }

    fun applyQuiet(view: View, enabled: Boolean, disposition: GateDisposition) {
        apply(view, enabled, disposition, quiet = true)
    }

    private fun apply(
        view: View,
        enabled: Boolean,
        disposition: GateDisposition,
        quiet: Boolean,
    ) {
        val canActivate = activatable(enabled, disposition)
        view.isEnabled = canActivate
        view.isClickable = canActivate
        view.alpha = when {
            !enabled -> if (quiet) 0.7f else 0.4f
            quiet -> quietAlpha(disposition)
            else -> alpha(disposition)
        }
    }
}

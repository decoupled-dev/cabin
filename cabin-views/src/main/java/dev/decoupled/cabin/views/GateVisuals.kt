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

    fun alpha(disposition: GateDisposition): Float = when (disposition) {
        GateDisposition.Allow -> 1f
        GateDisposition.Substitute -> 0.55f
        GateDisposition.Block -> 0.4f
    }

    /** Product-enabled AND gate Allow. */
    fun activatable(enabled: Boolean, disposition: GateDisposition): Boolean =
        enabled && disposition == GateDisposition.Allow

    fun apply(view: View, enabled: Boolean, disposition: GateDisposition) {
        val canActivate = activatable(enabled, disposition)
        view.isEnabled = canActivate
        view.isClickable = canActivate
        view.alpha = if (!enabled) {
            0.4f
        } else {
            alpha(disposition)
        }
    }
}

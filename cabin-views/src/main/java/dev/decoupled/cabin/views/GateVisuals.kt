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

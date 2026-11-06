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

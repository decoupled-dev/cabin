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

package dev.decoupled.cabin.compose.scaffold

import androidx.compose.ui.graphics.Color
import dev.decoupled.cabin.compose.theme.CabinColors

/**
 * Quiet family mark — accent as a stripe only, never body copy or washes.
 *
 * Climate / media / EV / ADAS / privacy keep their semantic roles. Safety
 * feedback (`warning` / `error` / `charging`) is not remapped for brand.
 */
fun cabinFamilyAccent(family: String, colors: CabinColors): Color = when (family) {
    "hvac" -> colors.climate
    "media" -> colors.mediaAccent
    "ev" -> colors.charging
    "adas" -> colors.adasActive
    "user", "voice" -> colors.privacy
    "feedback" -> colors.warning
    "action", "selection", "input" -> colors.primary
    "systemui", "navigation", "launcher" -> colors.secondary
    "gauges", "vehicle" -> colors.primary
    "settings", "collection", "surface", "layout" -> colors.secondary
    "comms", "maps", "rse" -> colors.primary
    else -> colors.secondary
}

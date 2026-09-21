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

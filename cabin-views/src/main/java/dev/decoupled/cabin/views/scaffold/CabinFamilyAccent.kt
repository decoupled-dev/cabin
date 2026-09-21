package dev.decoupled.cabin.views.scaffold

import androidx.annotation.ColorInt
import dev.decoupled.cabin.views.theme.CabinResolvedColors

/** Views counterpart of Compose [dev.decoupled.cabin.compose.scaffold.cabinFamilyAccent]. */
@ColorInt
fun cabinFamilyAccent(family: String, colors: CabinResolvedColors): Int = when (family) {
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

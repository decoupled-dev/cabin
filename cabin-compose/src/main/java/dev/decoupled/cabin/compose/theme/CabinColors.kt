package dev.decoupled.cabin.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import dev.decoupled.cabin.tokens.CabinColor
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Resolved Compose colors mirroring Views Theme Kit roles.
 *
 * Day/night scheme roles ([surface], [outline], [container], [warning],
 * [error], [charging]) come from [CabinTokens.colorScheme]. Brand roles are
 * semantic baselines with optional OEM overrides — never remap safety feedback
 * for decoration (ADR 0003).
 *
 * Experimental: Compose Theme Kit mirror; Views Theme Kit remains Alpha/stable path.
 */
@Immutable
data class CabinColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val outline: Color,
    val container: Color,
    val onContainer: Color,
    val success: Color,
    val warning: Color,
    val error: Color,
    val charging: Color,
    val climate: Color,
    val mediaAccent: Color,
    val scrim: Color,
)

/**
 * Optional OEM brand remaps for Compose (primary / onPrimary only).
 *
 * Mirrors Views `ThemeOverlay.Cabin.OemBrandDemo` intent without Android theme
 * attrs — products pass overrides into [resolveCabinColors].
 */
@Immutable
data class CabinBrandOverrides(
    val primary: Color? = null,
    val onPrimary: Color? = null,
) {
    companion object {
        val None: CabinBrandOverrides = CabinBrandOverrides()
    }
}

/** Resolve Theme Kit–equivalent colors from cabin-tokens for [scheme]. */
fun resolveCabinColors(
    scheme: CabinColorScheme,
    brand: CabinBrandOverrides = CabinBrandOverrides.None,
): CabinColors {
    val semantic = CabinTokens.Color.Semantic
    val schemeColors = CabinTokens.colorScheme(scheme)
    return CabinColors(
        primary = brand.primary ?: semantic.primary.toComposeColor(),
        onPrimary = brand.onPrimary ?: semantic.onPrimary.toComposeColor(),
        secondary = semantic.secondary.toComposeColor(),
        onSecondary = semantic.onSecondary.toComposeColor(),
        surface = schemeColors.surface.toComposeColor(),
        onSurface = schemeColors.onSurface.toComposeColor(),
        surfaceVariant = schemeColors.surfaceVariant.toComposeColor(),
        outline = schemeColors.outline.toComposeColor(),
        container = schemeColors.container.toComposeColor(),
        onContainer = schemeColors.onContainer.toComposeColor(),
        success = semantic.success.toComposeColor(),
        warning = schemeColors.warning.toComposeColor(),
        error = schemeColors.error.toComposeColor(),
        charging = schemeColors.charging.toComposeColor(),
        climate = semantic.climate.toComposeColor(),
        mediaAccent = semantic.mediaAccent.toComposeColor(),
        scrim = semantic.scrim.toComposeColor(),
    )
}

internal fun CabinColor.toComposeColor(): Color = Color(argb)

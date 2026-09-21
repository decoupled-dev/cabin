package dev.decoupled.cabin.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import dev.decoupled.cabin.tokens.CabinColor
import dev.decoupled.cabin.tokens.CabinColorScheme

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
    val critical: Color,
    val adasActive: Color,
    val privacy: Color,
    val focusRing: Color,
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

/**
 * Resolve Theme Kit–equivalent colors from cabin-tokens for [scheme].
 *
 * Delegates to [CabinComposeTokens.resolveColors] so Compose mappings stay
 * generated from `tokens/cabin.tokens.json` (same names as Android / CSS).
 */
fun resolveCabinColors(
    scheme: CabinColorScheme,
    brand: CabinBrandOverrides = CabinBrandOverrides.None,
): CabinColors = CabinComposeTokens.resolveColors(scheme, brand)

internal fun CabinColor.toComposeColor(): Color = Color(argb)

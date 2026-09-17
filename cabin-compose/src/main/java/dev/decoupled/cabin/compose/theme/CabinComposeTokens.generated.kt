@file:JvmName("CabinComposeTokensGenerated")

package dev.decoupled.cabin.compose.theme

import androidx.compose.ui.graphics.Color
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Compose theme Color mappings generated from `tokens/cabin.tokens.json`.
 *
 * Same semantic names as Android resources and CSS `--cabin-*` variables.
 * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.
 * Stub version: 0.1.1-stub
 */
object CabinComposeTokens {
    const val SOURCE_VERSION: String = "0.1.1-stub"

    object Semantic {
        val primary: Color get() = CabinTokens.Color.Semantic.primary.toComposeColor()
        val onPrimary: Color get() = CabinTokens.Color.Semantic.onPrimary.toComposeColor()
        val secondary: Color get() = CabinTokens.Color.Semantic.secondary.toComposeColor()
        val onSecondary: Color get() = CabinTokens.Color.Semantic.onSecondary.toComposeColor()
        val surface: Color get() = CabinTokens.Color.Semantic.surface.toComposeColor()
        val onSurface: Color get() = CabinTokens.Color.Semantic.onSurface.toComposeColor()
        val surfaceVariant: Color get() = CabinTokens.Color.Semantic.surfaceVariant.toComposeColor()
        val outline: Color get() = CabinTokens.Color.Semantic.outline.toComposeColor()
        val success: Color get() = CabinTokens.Color.Semantic.success.toComposeColor()
        val warning: Color get() = CabinTokens.Color.Semantic.warning.toComposeColor()
        val error: Color get() = CabinTokens.Color.Semantic.error.toComposeColor()
        val charging: Color get() = CabinTokens.Color.Semantic.charging.toComposeColor()
        val climate: Color get() = CabinTokens.Color.Semantic.climate.toComposeColor()
        val mediaAccent: Color get() = CabinTokens.Color.Semantic.mediaAccent.toComposeColor()
        val scrim: Color get() = CabinTokens.Color.Semantic.scrim.toComposeColor()
    }

    /** Day/night scheme chrome + locked feedback roles. */
    data class SchemeColors(
        val surface: Color,
        val onSurface: Color,
        val surfaceVariant: Color,
        val outline: Color,
        val container: Color,
        val onContainer: Color,
        val warning: Color,
        val error: Color,
        val charging: Color,
    )

    fun scheme(scheme: CabinColorScheme): SchemeColors {
        val s = CabinTokens.colorScheme(scheme)
        return SchemeColors(
            surface = s.surface.toComposeColor(),
            onSurface = s.onSurface.toComposeColor(),
            surfaceVariant = s.surfaceVariant.toComposeColor(),
            outline = s.outline.toComposeColor(),
            container = s.container.toComposeColor(),
            onContainer = s.onContainer.toComposeColor(),
            warning = s.warning.toComposeColor(),
            error = s.error.toComposeColor(),
            charging = s.charging.toComposeColor(),
        )
    }

    /**
     * Resolve [CabinColors] for [CabinTheme].
     * Brand overrides may remap primary only — never safety feedback.
     */
    fun resolveColors(
        scheme: CabinColorScheme,
        brand: CabinBrandOverrides = CabinBrandOverrides.None,
    ): CabinColors {
        val semantic = Semantic
        val schemeColors = scheme(scheme)
        return CabinColors(
            primary = brand.primary ?: semantic.primary,
            onPrimary = brand.onPrimary ?: semantic.onPrimary,
            secondary = semantic.secondary,
            onSecondary = semantic.onSecondary,
            surface = schemeColors.surface,
            onSurface = schemeColors.onSurface,
            surfaceVariant = schemeColors.surfaceVariant,
            outline = schemeColors.outline,
            container = schemeColors.container,
            onContainer = schemeColors.onContainer,
            success = semantic.success,
            warning = schemeColors.warning,
            error = schemeColors.error,
            charging = schemeColors.charging,
            climate = semantic.climate,
            mediaAccent = semantic.mediaAccent,
            scrim = semantic.scrim,
        )
    }
}

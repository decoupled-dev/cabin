package dev.decoupled.cabin.views.theme

import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import dev.decoupled.cabin.tokens.R as TokensR
import dev.decoupled.cabin.views.R as ViewsR

/**
 * Cabin Theme Kit style identifiers (Views-first).
 *
 * Apply [ThemeCabin] / [ThemeCabinDayNight] as an activity/application theme, or
 * layer [ThemeOverlayCabin] / [ThemeOverlayCabinOemBrandDemo] on a host theme.
 * Widgets resolve colors via [CabinThemeResolver] — never hardcoded hex.
 *
 * System/Status bar widgets use the same Theme Kit attrs for day/night chrome.
 */
object CabinThemes {
    /** Default DayNight theme resolving cabin-tokens roles. */
    @StyleRes
    val ThemeCabin: Int = ViewsR.style.Theme_Cabin

    /** Alias documenting DayNight UiMode contract (same as [ThemeCabin]). */
    @StyleRes
    val ThemeCabinDayNight: Int = ViewsR.style.Theme_Cabin_DayNight

    /** Overlay that sets cabin_* attrs without replacing the host theme tree. */
    @StyleRes
    val ThemeOverlayCabin: Int = ViewsR.style.ThemeOverlay_Cabin

    /**
     * Demo OEM brand overlay — remaps primary only.
     * Products replace this with their own overlay / RRO (ADR 0003).
     */
    @StyleRes
    val ThemeOverlayCabinOemBrandDemo: Int = ViewsR.style.ThemeOverlay_Cabin_OemBrandDemo

    /** Token attribute ids widgets should resolve (from cabin-tokens). */
    object Attr {
        @AttrRes val colorPrimary: Int = TokensR.attr.cabin_colorPrimary
        @AttrRes val colorOnPrimary: Int = TokensR.attr.cabin_colorOnPrimary
        @AttrRes val colorSecondary: Int = TokensR.attr.cabin_colorSecondary
        @AttrRes val colorOnSecondary: Int = TokensR.attr.cabin_colorOnSecondary
        @AttrRes val colorSurface: Int = TokensR.attr.cabin_colorSurface
        @AttrRes val colorOnSurface: Int = TokensR.attr.cabin_colorOnSurface
        @AttrRes val colorSurfaceVariant: Int = TokensR.attr.cabin_colorSurfaceVariant
        @AttrRes val colorOutline: Int = TokensR.attr.cabin_colorOutline
        @AttrRes val colorContainer: Int = TokensR.attr.cabin_colorContainer
        @AttrRes val colorOnContainer: Int = TokensR.attr.cabin_colorOnContainer
        @AttrRes val colorSuccess: Int = TokensR.attr.cabin_colorSuccess
        @AttrRes val colorWarning: Int = TokensR.attr.cabin_colorWarning
        @AttrRes val colorError: Int = TokensR.attr.cabin_colorError
        @AttrRes val colorCharging: Int = TokensR.attr.cabin_colorCharging
        @AttrRes val colorClimate: Int = TokensR.attr.cabin_colorClimate
        @AttrRes val colorMediaAccent: Int = TokensR.attr.cabin_colorMediaAccent
        @AttrRes val colorScrim: Int = TokensR.attr.cabin_colorScrim
        @AttrRes val colorCritical: Int = TokensR.attr.cabin_colorCritical
        @AttrRes val colorAdasActive: Int = TokensR.attr.cabin_colorAdasActive
        @AttrRes val colorPrivacy: Int = TokensR.attr.cabin_colorPrivacy
        @AttrRes val colorFocusRing: Int = TokensR.attr.cabin_colorFocusRing
    }
}

/**
 * Resolved ARGB colors from a themed [android.content.Context].
 *
 * Scheme roles ([surface], [outline], [container], [warning], [error], [charging])
 * flip with day/night via CabinTokens `values` / `values-night`.
 */
data class CabinResolvedColors(
    @ColorInt val primary: Int,
    @ColorInt val onPrimary: Int,
    @ColorInt val secondary: Int,
    @ColorInt val onSecondary: Int,
    @ColorInt val surface: Int,
    @ColorInt val onSurface: Int,
    @ColorInt val surfaceVariant: Int,
    @ColorInt val outline: Int,
    @ColorInt val container: Int,
    @ColorInt val onContainer: Int,
    @ColorInt val success: Int,
    @ColorInt val warning: Int,
    @ColorInt val error: Int,
    @ColorInt val charging: Int,
    @ColorInt val climate: Int,
    @ColorInt val mediaAccent: Int,
    @ColorInt val scrim: Int,
    @ColorInt val critical: Int,
    @ColorInt val adasActive: Int,
    @ColorInt val privacy: Int,
    @ColorInt val focusRing: Int,
)

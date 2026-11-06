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

package dev.decoupled.cabin.views.theme

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import dev.decoupled.cabin.tokens.CabinColorScheme

/**
 * Resolves Cabin token roles from a themed [Context].
 *
 * Prefer applying [CabinThemes.ThemeCabin] (or an overlay) so attrs resolve;
 * do not hardcode hex in widgets. OEM brand remaps via theme overlay / RRO
 * without forking Theme Kit or future bar widgets (ADR 0003).
 *
 * No AppCompat / Material dependency — platform DayNight + cabin-tokens only.
 */
object CabinThemeResolver {

    /**
     * Resolve a single cabin_* theme attribute to an ARGB color.
     *
     * @throws IllegalArgumentException if the attribute is not set on the theme
     */
    @ColorInt
    fun color(context: Context, @AttrRes attr: Int): Int {
        val typedValue = TypedValue()
        val resolved = context.theme.resolveAttribute(attr, typedValue, true)
        require(resolved) {
            "Theme attribute 0x${Integer.toHexString(attr)} is not set. " +
                "Apply Theme.Cabin / ThemeOverlay.Cabin (or an OEM overlay)."
        }
        return when {
            typedValue.resourceId != 0 ->
                context.resources.getColor(typedValue.resourceId, context.theme)
            typedValue.type in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT ->
                typedValue.data
            else -> error(
                "Theme attribute 0x${Integer.toHexString(attr)} did not resolve to a color",
            )
        }
    }

    /** All semantic + scheme color roles from the current theme. */
    fun resolveColors(context: Context): CabinResolvedColors {
        val attr = CabinThemes.Attr
        return CabinResolvedColors(
            primary = color(context, attr.colorPrimary),
            onPrimary = color(context, attr.colorOnPrimary),
            secondary = color(context, attr.colorSecondary),
            onSecondary = color(context, attr.colorOnSecondary),
            surface = color(context, attr.colorSurface),
            onSurface = color(context, attr.colorOnSurface),
            surfaceVariant = color(context, attr.colorSurfaceVariant),
            outline = color(context, attr.colorOutline),
            container = color(context, attr.colorContainer),
            onContainer = color(context, attr.colorOnContainer),
            success = color(context, attr.colorSuccess),
            warning = color(context, attr.colorWarning),
            error = color(context, attr.colorError),
            charging = color(context, attr.colorCharging),
            climate = color(context, attr.colorClimate),
            mediaAccent = color(context, attr.colorMediaAccent),
            scrim = color(context, attr.colorScrim),
            critical = color(context, attr.colorCritical),
            adasActive = color(context, attr.colorAdasActive),
            privacy = color(context, attr.colorPrivacy),
            focusRing = color(context, attr.colorFocusRing),
        )
    }

    /**
     * Wrap [base] with [themeResId] (typically [CabinThemes.ThemeCabin] or an overlay).
     */
    fun wrap(base: Context, @StyleRes themeResId: Int): Context =
        ContextThemeWrapper(base, themeResId)

    /**
     * Infer [CabinColorScheme] from the context configuration's night bit.
     * Useful for tests and diagnostics; widgets should still prefer theme attrs.
     */
    fun colorSchemeOf(context: Context): CabinColorScheme {
        val night =
            context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return if (night == Configuration.UI_MODE_NIGHT_YES) {
            CabinColorScheme.Night
        } else {
            CabinColorScheme.Day
        }
    }
}

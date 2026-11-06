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

package dev.decoupled.cabin.compose.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.tokens.CabinActiveStyle
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinEdgeStyle
import dev.decoupled.cabin.tokens.CabinLook
import dev.decoupled.cabin.tokens.CabinLookSpec
import dev.decoupled.cabin.tokens.CabinLooks
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Resolved colors for the active look and scheme.
 *
 * Material 3 roles plus Cabin's extensions: [onSurfaceMuted], the locked
 * safety roles, and the active-state colors that follow the look's
 * [CabinActiveStyle].
 */
@Immutable
data class CabinLookColors(
    val background: Color,
    val surface: Color,
    val surfaceHigh: Color,
    val outline: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val onSurfaceMuted: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    /** Locked (ADR 0006): identical in every look. */
    val warning: Color,
    val warningContainer: Color,
    val error: Color,
    val errorContainer: Color,
    val charging: Color,
    val chargingContainer: Color,
    /** Fill of a selected / active control. */
    val activeContainer: Color,
    /** Content on [activeContainer]. */
    val onActiveContainer: Color,
    /** Underline drawn under active controls; [Color.Unspecified] when the look has none. */
    val activeIndicator: Color,
    /** Glass looks only; [Color.Unspecified] otherwise. */
    val backdropGlowStart: Color,
    val backdropGlowEnd: Color,
)

/** Shapes for the active look. */
@Immutable
data class CabinLookShapes(
    /** Panels and cards. */
    val large: CornerBasedShape,
    /** Art, tiles inside cards, list rows. */
    val medium: CornerBasedShape,
    /** Buttons, chips, pills, segmented controls. */
    val control: CornerBasedShape,
)

/**
 * Driving-safe text styles for the active look. Nothing is smaller than
 * 24 sp. Numerals use tabular figures.
 */
@Immutable
data class CabinLookType(
    /** Hero numerals (battery %, speed): 112 sp, display family. */
    val numeralHero: TextStyle,
    /** Large numerals (card values): 72 sp. */
    val numeralLarge: TextStyle,
    /** Medium numerals (next-turn distance): 48 sp. */
    val numeralMedium: TextStyle,
    /** Temperatures in bars and steppers: 40 sp. */
    val numeralSmall: TextStyle,
    /** Screen and card titles: 32 sp. */
    val title: TextStyle,
    /** Status bar time, emphasized secondary: 28 sp. */
    val titleSmall: TextStyle,
    /** Body and secondary lines: 24 sp. */
    val body: TextStyle,
    /** Labels, button text, metadata: 24 sp, label family. */
    val label: TextStyle,
    /** True when the look sets labels in capitals; see [CabinLookTheme.labelText]. */
    val labelAllCaps: Boolean,
)

/**
 * Everything a Cabin kit component needs from the active look. Provided by
 * [CabinTheme]; read through [CabinTheme.look].
 */
@Immutable
class CabinLookTheme(
    val spec: CabinLookSpec,
    val scheme: CabinColorScheme,
    val colors: CabinLookColors,
    val shapes: CabinLookShapes,
    val type: CabinLookType,
) {
    val look: CabinLook get() = spec.look
    val activeStyle: CabinActiveStyle get() = spec.activeStyle
    val edge: CabinEdgeStyle get() = spec.edge

    /** Minimum touch target (76 dp baseline, from tokens). */
    val touchTarget: Dp get() = CabinTokens.Size.Touch.minimum.dp.dp

    /** Apply the look's label casing to [text]. */
    fun labelText(text: String): String = if (type.labelAllCaps) text.uppercase() else text
}

/** Provided by [CabinTheme]; defaults to the Quiet night look so previews render. */
val LocalCabinLook = staticCompositionLocalOf {
    cabinLookTheme(CabinLooks.Default, CabinColorScheme.Night)
}

/** Build the [CabinLookTheme] for [look] in [scheme]. */
fun cabinLookTheme(
    look: CabinLook,
    scheme: CabinColorScheme,
    brand: CabinBrandOverrides = CabinBrandOverrides.None,
    fonts: CabinFontResolver = CabinFonts.Platform,
): CabinLookTheme {
    val spec = CabinLooks.spec(look)
    return CabinLookTheme(
        spec = spec,
        scheme = scheme,
        colors = lookColors(spec, scheme, brand),
        shapes = lookShapes(spec),
        type = lookType(spec, fonts),
    )
}

private fun lookColors(
    spec: CabinLookSpec,
    scheme: CabinColorScheme,
    brand: CabinBrandOverrides,
): CabinLookColors {
    val s = spec.scheme(scheme)
    val safety = if (scheme == CabinColorScheme.Night) CabinLooks.Safety.night else CabinLooks.Safety.day
    val primary = brand.primary ?: Color(s.primary)
    val onPrimary = brand.onPrimary ?: Color(s.onPrimary)
    val underline = spec.activeStyle == CabinActiveStyle.Underline
    return CabinLookColors(
        background = Color(s.background),
        surface = Color(s.surface),
        surfaceHigh = Color(s.surfaceContainerHigh),
        outline = Color(s.outline),
        onSurface = Color(s.onSurface),
        onSurfaceVariant = Color(s.onSurfaceVariant),
        onSurfaceMuted = Color(s.onSurfaceMuted),
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = Color(s.primaryContainer),
        onPrimaryContainer = Color(s.onPrimaryContainer),
        warning = Color(safety.warning),
        warningContainer = Color(safety.warningContainer),
        error = Color(safety.error),
        errorContainer = Color(safety.errorContainer),
        charging = Color(safety.charging),
        chargingContainer = Color(safety.chargingContainer),
        activeContainer = if (underline) Color(s.surfaceContainerHigh) else Color(s.primaryContainer),
        onActiveContainer = if (underline) Color(s.onSurface) else Color(s.onPrimaryContainer),
        activeIndicator = if (underline) primary else Color.Unspecified,
        backdropGlowStart = s.backdropGlowStart?.let(::Color) ?: Color.Unspecified,
        backdropGlowEnd = s.backdropGlowEnd?.let(::Color) ?: Color.Unspecified,
    )
}

private fun lookShapes(spec: CabinLookSpec): CabinLookShapes = CabinLookShapes(
    large = RoundedCornerShape(spec.cornerLargeDp.dp),
    medium = RoundedCornerShape(spec.cornerMediumDp.dp),
    control = if (spec.controlIsPill) RoundedCornerShape(percent = 50) else RoundedCornerShape(spec.cornerControlDp.dp),
)

private const val TABULAR = "tnum"

private fun lookType(spec: CabinLookSpec, fonts: CabinFontResolver): CabinLookType {
    val ui = fonts.resolve(spec.uiFontFamily)
    val display = fonts.resolve(spec.displayFontFamily)
    val label = fonts.resolve(spec.labelFontFamily)
    val displayWeight = FontWeight(spec.displayWeight)
    val titleWeight = FontWeight(spec.titleWeight)
    fun numeral(size: TextUnit, lineHeight: TextUnit) = style(display, displayWeight, size, lineHeight, TABULAR)
    return CabinLookType(
        numeralHero = numeral(112.sp, 112.sp),
        numeralLarge = numeral(72.sp, 72.sp),
        numeralMedium = numeral(48.sp, 52.sp),
        numeralSmall = numeral(40.sp, 44.sp),
        title = style(ui, titleWeight, 32.sp, 38.sp),
        titleSmall = style(ui, titleWeight, 28.sp, 34.sp, TABULAR),
        body = style(ui, FontWeight.Normal, 24.sp, 32.sp),
        label = style(label, FontWeight(spec.labelWeight), 24.sp, 30.sp)
            .copy(letterSpacing = spec.labelTrackingEm.em),
        labelAllCaps = spec.labelAllCaps,
    )
}

private fun style(
    family: FontFamily,
    weight: FontWeight,
    size: TextUnit,
    lineHeight: TextUnit,
    features: String? = null,
) = TextStyle(
    fontFamily = family,
    fontWeight = weight,
    fontSize = size,
    lineHeight = lineHeight,
    fontFeatureSettings = features,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    ),
)

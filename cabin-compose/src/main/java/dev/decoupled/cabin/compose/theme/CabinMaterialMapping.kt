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

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import dev.decoupled.cabin.tokens.CabinColorScheme

/**
 * Material 3 [ColorScheme] for a Cabin look, so stock Material components
 * inside [CabinTheme] pick up the look too.
 */
fun CabinLookTheme.toMaterialColorScheme(): ColorScheme {
    val c = colors
    val base = if (scheme == CabinColorScheme.Night) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = c.primary,
        onPrimary = c.onPrimary,
        primaryContainer = c.primaryContainer,
        onPrimaryContainer = c.onPrimaryContainer,
        inversePrimary = c.primary,
        secondary = c.primary,
        onSecondary = c.onPrimary,
        secondaryContainer = c.activeContainer,
        onSecondaryContainer = c.onActiveContainer,
        tertiary = c.primary,
        onTertiary = c.onPrimary,
        background = c.background,
        onBackground = c.onSurface,
        surface = c.surface,
        onSurface = c.onSurface,
        surfaceVariant = c.surfaceHigh,
        onSurfaceVariant = c.onSurfaceVariant,
        surfaceTint = Color.Transparent,
        inverseSurface = c.onSurface,
        inverseOnSurface = c.background,
        error = c.error,
        onError = c.background,
        errorContainer = c.errorContainer,
        onErrorContainer = c.error,
        outline = c.outline,
        outlineVariant = c.outline,
        scrim = Color.Black.copy(alpha = 0.6f),
        surfaceBright = c.surfaceHigh,
        surfaceDim = c.background,
        surfaceContainerLowest = c.background,
        surfaceContainerLow = c.surface,
        surfaceContainer = c.surface,
        surfaceContainerHigh = c.surfaceHigh,
        surfaceContainerHighest = c.surfaceHigh,
    )
}

/** Material 3 [Typography] on the driving-safe scale (nothing under 24 sp). */
fun CabinLookTheme.toMaterialTypography(): Typography = Typography(
    displayLarge = type.numeralHero,
    displayMedium = type.numeralLarge,
    displaySmall = type.numeralMedium,
    headlineLarge = type.numeralSmall,
    headlineMedium = type.title,
    headlineSmall = type.title,
    titleLarge = type.title,
    titleMedium = type.titleSmall,
    titleSmall = type.titleSmall,
    bodyLarge = type.body,
    bodyMedium = type.body,
    bodySmall = type.body,
    labelLarge = type.label,
    labelMedium = type.label,
    labelSmall = type.label,
)

/** Material 3 [Shapes] from the look's corner tokens. */
fun CabinLookTheme.toMaterialShapes(): Shapes = Shapes(
    extraSmall = shapes.control,
    small = shapes.control,
    medium = shapes.medium,
    large = shapes.large,
    extraLarge = shapes.large,
)

/**
 * Legacy [CabinColors] view of a look, so v0.1 components and the frozen
 * scaffolds follow the active look. Domain accents the looks don't define
 * (climate, media, ADAS, privacy, …) keep their token values.
 */
fun CabinLookTheme.toCabinColors(brand: CabinBrandOverrides = CabinBrandOverrides.None): CabinColors {
    val legacy = resolveCabinColors(scheme, brand)
    val c = colors
    return legacy.copy(
        primary = c.primary,
        onPrimary = c.onPrimary,
        secondary = c.primaryContainer,
        onSecondary = c.onPrimaryContainer,
        surface = c.background,
        onSurface = c.onSurface,
        surfaceVariant = c.surfaceHigh,
        outline = c.outline,
        container = c.surface,
        onContainer = c.onSurface,
        warning = c.warning,
        error = c.error,
        charging = c.charging,
        focusRing = c.primary,
    )
}

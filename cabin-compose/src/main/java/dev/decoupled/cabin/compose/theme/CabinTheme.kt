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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import dev.decoupled.cabin.compliance.CabinCompliance
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.compliance.CabinComplianceState
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinLook
import dev.decoupled.cabin.tokens.CabinLooks

/**
 * Composition local for Theme Kit–equivalent colors.
 *
 * Default is Day scheme so previews compose; product UIs should wrap with
 * [CabinTheme].
 */
val LocalCabinColors = staticCompositionLocalOf {
    resolveCabinColors(CabinColorScheme.Day)
}

/** Active day/night scheme (diagnostics + tests). */
val LocalCabinColorScheme = staticCompositionLocalOf { CabinColorScheme.Day }

/**
 * Cabin Compose theme: a Material 3 [MaterialTheme] built from a Cabin look,
 * plus the Restriction Engine and car-only extensions ([LocalCabinLook]).
 *
 * ```
 * CabinTheme(look = CabinLook.Quiet, vehicleState = state) {
 *     CabinHomeSplitScreen(...)
 * }
 * ```
 *
 * Stock Material 3 components inside pick up the look's colors, type and
 * shapes. Cabin kit components also read [CabinTheme.look] for the
 * active-state style, panel edge and locked safety colors.
 *
 * @param colorScheme day or night; defaults to the system dark-theme setting
 * @param look token preset; defaults to [CabinLooks.Default] (Quiet)
 * @param brand OEM overrides for the primary roles; safety roles can't be overridden
 * @param fonts maps the look's font family names to installed or bundled fonts
 * @param compliance Restriction Engine policy
 * @param vehicleState current drive state from an adapter or simulator
 *
 * Experimental (ADR 0006).
 */
@Composable
fun CabinTheme(
    colorScheme: CabinColorScheme = if (isSystemInDarkTheme()) {
        CabinColorScheme.Night
    } else {
        CabinColorScheme.Day
    },
    look: CabinLook = CabinLooks.Default,
    brand: CabinBrandOverrides = CabinBrandOverrides.None,
    fonts: CabinFontResolver = CabinFonts.Platform,
    compliance: CabinCompliance = CabinRestrictionEngine.Default,
    vehicleState: VehicleUiState = VehicleUiState.unknown(),
    content: @Composable () -> Unit,
) {
    val lookTheme = remember(look, colorScheme, brand, fonts) {
        cabinLookTheme(look, colorScheme, brand, fonts)
    }
    val colors = remember(lookTheme, brand) { lookTheme.toCabinColors(brand) }
    val complianceState = remember(compliance, vehicleState) {
        CabinComplianceState(policy = compliance, vehicleState = vehicleState)
    }
    MaterialTheme(
        colorScheme = remember(lookTheme) { lookTheme.toMaterialColorScheme() },
        typography = remember(lookTheme) { lookTheme.toMaterialTypography() },
        shapes = remember(lookTheme) { lookTheme.toMaterialShapes() },
    ) {
        CompositionLocalProvider(
            LocalCabinLook provides lookTheme,
            LocalCabinColors provides colors,
            LocalCabinColorScheme provides colorScheme,
            LocalCabinComplianceState provides complianceState,
            LocalContentColor provides lookTheme.colors.onSurface,
            content = content,
        )
    }
}

/**
 * Reads the active Cabin theme, in the style of `MaterialTheme.colorScheme`.
 */
object CabinTheme {
    /** Active look: colors, shapes, type, active-state style, edge. */
    val look: CabinLookTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCabinLook.current

    /** Restriction Engine state for gating interactions. */
    val compliance: CabinComplianceState
        @Composable
        @ReadOnlyComposable
        get() = LocalCabinComplianceState.current
}

/** Convenience accessors inside Cabin themed composition. */
object CabinThemeAccessors {
    val colors: CabinColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCabinColors.current

    val colorScheme: CabinColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCabinColorScheme.current

    val compliance: CabinComplianceState
        @Composable
        @ReadOnlyComposable
        get() = LocalCabinComplianceState.current
}

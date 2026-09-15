package dev.decoupled.cabin.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
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
 * Cabin Compose theme — tokens + compliance composition locals.
 *
 * Mirrors Views Theme Kit **meaning** (scheme roles, locked safety feedback,
 * OEM brand via overrides) without Android Views theme attrs.
 *
 * Experimental until post-MVP bar parity hardens.
 */
@Composable
fun CabinTheme(
    colorScheme: CabinColorScheme = if (isSystemInDarkTheme()) {
        CabinColorScheme.Night
    } else {
        CabinColorScheme.Day
    },
    brand: CabinBrandOverrides = CabinBrandOverrides.None,
    compliance: CabinCompliance = CabinRestrictionEngine.Default,
    vehicleState: VehicleUiState = VehicleUiState.unknown(),
    content: @Composable () -> Unit,
) {
    val colors = remember(colorScheme, brand) {
        resolveCabinColors(colorScheme, brand)
    }
    val complianceState = remember(compliance, vehicleState) {
        CabinComplianceState(policy = compliance, vehicleState = vehicleState)
    }
    CompositionLocalProvider(
        LocalCabinColors provides colors,
        LocalCabinColorScheme provides colorScheme,
        LocalCabinComplianceState provides complianceState,
        content = content,
    )
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

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

@file:JvmName("CabinLooksGenerated")

package dev.decoupled.cabin.tokens

/**
 * Look presets and the locked safety tier, generated from
 * `tokens/cabin.tokens.json` (`cabin.look.*`, `cabin.safety.*`).
 *
 * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.
 * Source version: 0.2.0-looks
 */
object CabinLooks {
    const val SOURCE_VERSION: String = "0.2.0-looks"

    /** Preset used when a product does not pick one. */
    val Default: CabinLook = CabinLook.Quiet

    /** Locked across every look (ADR 0006). */
    object Safety {
        val night: CabinSafetyScheme =
            CabinSafetyScheme(
                warning = 0xFFFFB020.toInt(),
                warningContainer = 0xFF3A2A10.toInt(),
                error = 0xFFFF5A4E.toInt(),
                errorContainer = 0xFF3D1512.toInt(),
                charging = 0xFF3DDC84.toInt(),
                chargingContainer = 0xFF0F2E1E.toInt(),
            )
        val day: CabinSafetyScheme =
            CabinSafetyScheme(
                warning = 0xFF905D00.toInt(),
                warningContainer = 0xFFFCEFD6.toInt(),
                error = 0xFFC2261B.toInt(),
                errorContainer = 0xFFFBE3E0.toInt(),
                charging = 0xFF1A7742.toInt(),
                chargingContainer = 0xFFDDF3E5.toInt(),
            )
    }

    /** Every preset, in canvas order. */
    val all: List<CabinLookSpec> by lazy {
        listOf(Quiet, Precision, Warm, Glass, Graphic, Mono)
    }

    /** Spec for [look]. */
    fun spec(look: CabinLook): CabinLookSpec = when (look) {
        CabinLook.Quiet -> Quiet
        CabinLook.Precision -> Precision
        CabinLook.Warm -> Warm
        CabinLook.Glass -> Glass
        CabinLook.Graphic -> Graphic
        CabinLook.Mono -> Mono
    }

    private val Quiet: CabinLookSpec = CabinLookSpec(
        look = CabinLook.Quiet,
        displayName = "Quiet",
        description = "Calm luxury. Warm graphite, one champagne accent, soft shapes and light numerals.",
        uiFontFamily = "Manrope",
        displayFontFamily = "Manrope",
        labelFontFamily = "Manrope",
        displayWeight = 300,
        titleWeight = 600,
        labelWeight = 600,
        labelAllCaps = false,
        labelTrackingEm = 0.0f,
        cornerLargeDp = 28.0f,
        cornerMediumDp = 18.0f,
        cornerControlDp = 24.0f,
        activeStyle = CabinActiveStyle.Tonal,
        edge = CabinEdgeStyle.None,
        night =
            CabinLookScheme(
                background = 0xFF121110.toInt(),
                surface = 0xFF1E1C1A.toInt(),
                surfaceContainerHigh = 0xFF2C2926.toInt(),
                outline = 0xFF3A3631.toInt(),
                onSurface = 0xFFF2EEE8.toInt(),
                onSurfaceVariant = 0xFFBDB6AC.toInt(),
                onSurfaceMuted = 0xFF958F86.toInt(),
                primary = 0xFFCDB894.toInt(),
                onPrimary = 0xFF1A1712.toInt(),
                primaryContainer = 0xFF3A3326.toInt(),
                onPrimaryContainer = 0xFFE6D6B4.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
        day =
            CabinLookScheme(
                background = 0xFFF4F1EC.toInt(),
                surface = 0xFFFFFFFF.toInt(),
                surfaceContainerHigh = 0xFFECE7DF.toInt(),
                outline = 0xFFD8D0C4.toInt(),
                onSurface = 0xFF1C1A17.toInt(),
                onSurfaceVariant = 0xFF4D4842.toInt(),
                onSurfaceMuted = 0xFF6B655D.toInt(),
                primary = 0xFF8A6F3E.toInt(),
                onPrimary = 0xFFFFFFFF.toInt(),
                primaryContainer = 0xFFF0E6D2.toInt(),
                onPrimaryContainer = 0xFF5C4824.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
    )

    private val Precision: CabinLookSpec = CabinLookSpec(
        look = CabinLook.Precision,
        displayName = "Precision",
        description = "Instrument-grade. True black, hairline rules, mono caps labels, tabular numbers.",
        uiFontFamily = "IBM Plex Sans",
        displayFontFamily = "IBM Plex Sans",
        labelFontFamily = "IBM Plex Mono",
        displayWeight = 300,
        titleWeight = 500,
        labelWeight = 400,
        labelAllCaps = true,
        labelTrackingEm = 0.04f,
        cornerLargeDp = 4.0f,
        cornerMediumDp = 2.0f,
        cornerControlDp = 4.0f,
        activeStyle = CabinActiveStyle.Underline,
        edge = CabinEdgeStyle.Line,
        night =
            CabinLookScheme(
                background = 0xFF000000.toInt(),
                surface = 0xFF0C0D0E.toInt(),
                surfaceContainerHigh = 0xFF17191C.toInt(),
                outline = 0xFF2A2E33.toInt(),
                onSurface = 0xFFF5F6F7.toInt(),
                onSurfaceVariant = 0xFFA9AFB6.toInt(),
                onSurfaceMuted = 0xFF848B93.toInt(),
                primary = 0xFFD4FF3F.toInt(),
                onPrimary = 0xFF000000.toInt(),
                primaryContainer = 0xFF1E2608.toInt(),
                onPrimaryContainer = 0xFFD4FF3F.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
        day =
            CabinLookScheme(
                background = 0xFFE9ECEF.toInt(),
                surface = 0xFFFFFFFF.toInt(),
                surfaceContainerHigh = 0xFFDDE1E5.toInt(),
                outline = 0xFFB9C0C8.toInt(),
                onSurface = 0xFF0A0B0C.toInt(),
                onSurfaceVariant = 0xFF3A4048.toInt(),
                onSurfaceMuted = 0xFF555C64.toInt(),
                primary = 0xFF4D6B00.toInt(),
                onPrimary = 0xFFFFFFFF.toInt(),
                primaryContainer = 0xFFE3EDC8.toInt(),
                onPrimaryContainer = 0xFF2F4200.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
    )

    private val Warm: CabinLookSpec = CabinLookSpec(
        look = CabinLook.Warm,
        displayName = "Warm",
        description = "Friendly and tactile. Ink blue instead of black, chunky tonal tiles, pill controls.",
        uiFontFamily = "Plus Jakarta Sans",
        displayFontFamily = "Plus Jakarta Sans",
        labelFontFamily = "Plus Jakarta Sans",
        displayWeight = 700,
        titleWeight = 700,
        labelWeight = 600,
        labelAllCaps = false,
        labelTrackingEm = 0.0f,
        cornerLargeDp = 32.0f,
        cornerMediumDp = 22.0f,
        cornerControlDp = 999.0f,
        activeStyle = CabinActiveStyle.Tonal,
        edge = CabinEdgeStyle.None,
        night =
            CabinLookScheme(
                background = 0xFF0F1420.toInt(),
                surface = 0xFF1B2338.toInt(),
                surfaceContainerHigh = 0xFF27314B.toInt(),
                outline = 0xFF34405E.toInt(),
                onSurface = 0xFFEEF2FA.toInt(),
                onSurfaceVariant = 0xFFB4BED2.toInt(),
                onSurfaceMuted = 0xFF909BB3.toInt(),
                primary = 0xFF8AB4FF.toInt(),
                onPrimary = 0xFF0B1630.toInt(),
                primaryContainer = 0xFF24375F.toInt(),
                onPrimaryContainer = 0xFFB9D1FF.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
        day =
            CabinLookScheme(
                background = 0xFFF3F5FA.toInt(),
                surface = 0xFFFFFFFF.toInt(),
                surfaceContainerHigh = 0xFFE4E9F2.toInt(),
                outline = 0xFFCDD5E3.toInt(),
                onSurface = 0xFF111827.toInt(),
                onSurfaceVariant = 0xFF3F4A5E.toInt(),
                onSurfaceMuted = 0xFF5A6478.toInt(),
                primary = 0xFF2357C6.toInt(),
                onPrimary = 0xFFFFFFFF.toInt(),
                primaryContainer = 0xFFDCE6FB.toInt(),
                onPrimaryContainer = 0xFF1A428F.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
    )

    private val Glass: CabinLookSpec = CabinLookSpec(
        look = CabinLook.Glass,
        displayName = "Glass",
        description = "Layered and spatial. Frosted panels float over the map; depth instead of borders.",
        uiFontFamily = "Figtree",
        displayFontFamily = "Figtree",
        labelFontFamily = "Figtree",
        displayWeight = 500,
        titleWeight = 600,
        labelWeight = 600,
        labelAllCaps = false,
        labelTrackingEm = 0.0f,
        cornerLargeDp = 30.0f,
        cornerMediumDp = 20.0f,
        cornerControlDp = 999.0f,
        activeStyle = CabinActiveStyle.Tonal,
        edge = CabinEdgeStyle.Glass,
        night =
            CabinLookScheme(
                background = 0xFF070B12.toInt(),
                surface = 0x14FFFFFF.toInt(),
                surfaceContainerHigh = 0x1FFFFFFF.toInt(),
                outline = 0x29FFFFFF.toInt(),
                onSurface = 0xFFF4F8FB.toInt(),
                onSurfaceVariant = 0xFFC6D0DA.toInt(),
                onSurfaceMuted = 0xFF9EAAB6.toInt(),
                primary = 0xFF6EE7D8.toInt(),
                onPrimary = 0xFF04201D.toInt(),
                primaryContainer = 0x2E6EE7D8.toInt(),
                onPrimaryContainer = 0xFF9AF2E7.toInt(),
                backdropGlowStart = 0xFF12324A.toInt(),
                backdropGlowEnd = 0xFF2B2050.toInt(),
            ),
        day =
            CabinLookScheme(
                background = 0xFFEEF2F6.toInt(),
                surface = 0xB8FFFFFF.toInt(),
                surfaceContainerHigh = 0x120A1E32.toInt(),
                outline = 0xF2FFFFFF.toInt(),
                onSurface = 0xFF0B1520.toInt(),
                onSurfaceVariant = 0xFF33414F.toInt(),
                onSurfaceMuted = 0xFF4F5D6B.toInt(),
                primary = 0xFF0B7A70.toInt(),
                onPrimary = 0xFFFFFFFF.toInt(),
                primaryContainer = 0x240B7A70.toInt(),
                onPrimaryContainer = 0xFF075E56.toInt(),
                backdropGlowStart = 0xFFCBE6F1.toInt(),
                backdropGlowEnd = 0xFFE2DBF6.toInt(),
            ),
    )

    private val Graphic: CabinLookSpec = CabinLookSpec(
        look = CabinLook.Graphic,
        displayName = "Graphic",
        description = "Bold and editorial. Flat color blocks, heavy grotesk type, solid violet active state.",
        uiFontFamily = "Space Grotesk",
        displayFontFamily = "Space Grotesk",
        labelFontFamily = "Space Grotesk",
        displayWeight = 700,
        titleWeight = 700,
        labelWeight = 700,
        labelAllCaps = true,
        labelTrackingEm = 0.02f,
        cornerLargeDp = 12.0f,
        cornerMediumDp = 8.0f,
        cornerControlDp = 12.0f,
        activeStyle = CabinActiveStyle.Solid,
        edge = CabinEdgeStyle.None,
        night =
            CabinLookScheme(
                background = 0xFF0B0B0F.toInt(),
                surface = 0xFF17171F.toInt(),
                surfaceContainerHigh = 0xFF25252F.toInt(),
                outline = 0xFF33333F.toInt(),
                onSurface = 0xFFFFFFFF.toInt(),
                onSurfaceVariant = 0xFFC8C8D6.toInt(),
                onSurfaceMuted = 0xFF9C9CB0.toInt(),
                primary = 0xFFA78BFA.toInt(),
                onPrimary = 0xFF120A2B.toInt(),
                primaryContainer = 0xFFA78BFA.toInt(),
                onPrimaryContainer = 0xFF120A2B.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
        day =
            CabinLookScheme(
                background = 0xFFF5F5F7.toInt(),
                surface = 0xFFFFFFFF.toInt(),
                surfaceContainerHigh = 0xFFE8E8EE.toInt(),
                outline = 0xFFCFCFDA.toInt(),
                onSurface = 0xFF0B0B0F.toInt(),
                onSurfaceVariant = 0xFF3A3A48.toInt(),
                onSurfaceMuted = 0xFF565666.toInt(),
                primary = 0xFF5B3FD6.toInt(),
                onPrimary = 0xFFFFFFFF.toInt(),
                primaryContainer = 0xFF5B3FD6.toInt(),
                onPrimaryContainer = 0xFFFFFFFF.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
    )

    private val Mono: CabinLookSpec = CabinLookSpec(
        look = CabinLook.Mono,
        displayName = "Mono",
        description = "Monochrome. Grayscale only, so warning, error and charging are the only color on screen.",
        uiFontFamily = "Instrument Sans",
        displayFontFamily = "Instrument Serif",
        labelFontFamily = "Instrument Sans",
        displayWeight = 400,
        titleWeight = 600,
        labelWeight = 500,
        labelAllCaps = false,
        labelTrackingEm = 0.0f,
        cornerLargeDp = 20.0f,
        cornerMediumDp = 14.0f,
        cornerControlDp = 999.0f,
        activeStyle = CabinActiveStyle.Invert,
        edge = CabinEdgeStyle.None,
        night =
            CabinLookScheme(
                background = 0xFF0A0A0A.toInt(),
                surface = 0xFF161616.toInt(),
                surfaceContainerHigh = 0xFF242424.toInt(),
                outline = 0xFF333333.toInt(),
                onSurface = 0xFFF4F4F4.toInt(),
                onSurfaceVariant = 0xFFBDBDBD.toInt(),
                onSurfaceMuted = 0xFF959595.toInt(),
                primary = 0xFFF4F4F4.toInt(),
                onPrimary = 0xFF0A0A0A.toInt(),
                primaryContainer = 0xFFF4F4F4.toInt(),
                onPrimaryContainer = 0xFF0A0A0A.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
        day =
            CabinLookScheme(
                background = 0xFFF2F2F2.toInt(),
                surface = 0xFFFFFFFF.toInt(),
                surfaceContainerHigh = 0xFFE6E6E6.toInt(),
                outline = 0xFFCFCFCF.toInt(),
                onSurface = 0xFF0A0A0A.toInt(),
                onSurfaceVariant = 0xFF3D3D3D.toInt(),
                onSurfaceMuted = 0xFF5C5C5C.toInt(),
                primary = 0xFF0A0A0A.toInt(),
                onPrimary = 0xFFFFFFFF.toInt(),
                primaryContainer = 0xFF0A0A0A.toInt(),
                onPrimaryContainer = 0xFFFFFFFF.toInt(),
                backdropGlowStart = null,
                backdropGlowEnd = null,
            ),
    )

}

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

package dev.decoupled.cabin.tokens

/**
 * A Cabin look: one token preset over the shared schema (ADR 0006).
 *
 * OEMs pick a look, then override brand roles through tokens or RROs. Safety
 * roles are not part of a look; see [CabinLooks.Safety].
 */
enum class CabinLook {
    /** Calm luxury: warm graphite, champagne accent, soft shapes. The default. */
    Quiet,

    /** Instrument-grade: true black, hairlines, mono caps labels. */
    Precision,

    /** Friendly and tactile: ink blue, chunky tonal tiles, pills. */
    Warm,

    /** Layered: translucent panels over the map or backdrop. */
    Glass,

    /** Editorial: flat blocks, heavy grotesk type, solid active state. */
    Graphic,

    /** Grayscale only, so safety colors are the only color on screen. */
    Mono,
}

/** How a selected / active control is drawn. */
enum class CabinActiveStyle {
    /** Filled with the primary container color. */
    Tonal,

    /** Raised surface plus an accent underline. */
    Underline,

    /** Filled with a strong accent block. */
    Solid,

    /** Foreground and background swap. */
    Invert,
}

/** How panels separate from what is behind them. */
enum class CabinEdgeStyle {
    /** Tone only; no border. */
    None,

    /** Hairline outline. */
    Line,

    /** Translucent surface with a light rim; expects a backdrop. */
    Glass,
}

/**
 * Color roles for one look in one day/night scheme, as ARGB ints.
 *
 * Role names follow Material 3 (and therefore `com.android.oem.tokens`).
 * [onSurfaceMuted] is a Cabin extension for tertiary text that still meets
 * 4.5:1 on [background] and [surface].
 */
data class CabinLookScheme(
    val background: Int,
    val surface: Int,
    val surfaceContainerHigh: Int,
    val outline: Int,
    val onSurface: Int,
    val onSurfaceVariant: Int,
    val onSurfaceMuted: Int,
    val primary: Int,
    val onPrimary: Int,
    val primaryContainer: Int,
    val onPrimaryContainer: Int,
    /** Glass looks only: backdrop glow colors. */
    val backdropGlowStart: Int?,
    val backdropGlowEnd: Int?,
)

/** Locked safety colors for one scheme; identical across looks. */
data class CabinSafetyScheme(
    val warning: Int,
    val warningContainer: Int,
    val error: Int,
    val errorContainer: Int,
    val charging: Int,
    val chargingContainer: Int,
)

/**
 * Everything a UI toolkit needs to render [look].
 *
 * Font families are names only. Cabin libraries ship no font files; hosts
 * map names to installed or bundled fonts.
 *
 * @property cornerControlDp values of 999 or more mean fully rounded (pill).
 */
data class CabinLookSpec(
    val look: CabinLook,
    val displayName: String,
    val description: String,
    val uiFontFamily: String,
    val displayFontFamily: String,
    val labelFontFamily: String,
    val displayWeight: Int,
    val titleWeight: Int,
    val labelWeight: Int,
    val labelAllCaps: Boolean,
    val labelTrackingEm: Float,
    val cornerLargeDp: Float,
    val cornerMediumDp: Float,
    val cornerControlDp: Float,
    val activeStyle: CabinActiveStyle,
    val edge: CabinEdgeStyle,
    val night: CabinLookScheme,
    val day: CabinLookScheme,
) {
    /** Colors for [scheme]. */
    fun scheme(scheme: CabinColorScheme): CabinLookScheme =
        if (scheme == CabinColorScheme.Night) night else day

    /** True when controls should be drawn as pills. */
    val controlIsPill: Boolean get() = cornerControlDp >= PILL_THRESHOLD_DP

    companion object {
        const val PILL_THRESHOLD_DP: Float = 999f
    }
}

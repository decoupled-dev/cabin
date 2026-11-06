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

package dev.decoupled.cabin.compose.kit

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.tokens.CabinMeasure
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Every dimension the kit uses, in one place, built on the size and space
 * tokens. Components never hardcode sizes inline.
 *
 * Values are for a 1920 × 720 dp center display and scale with density.
 */
object CabinKitMetrics {
    private val CabinMeasure.asDp: Dp get() = dp.dp

    /** Minimum touch target (76 dp token). */
    val touchMin: Dp = CabinTokens.Size.Touch.minimum.asDp

    /** Standard control height: touch minimum plus a small margin. */
    val control: Dp = touchMin + CabinTokens.Space.sm.asDp

    /** Primary transport controls (play/pause and neighbors). */
    val controlLarge: Dp = control + CabinTokens.Space.md.asDp - CabinTokens.Space.xs.asDp

    /** Control inside a segmented track or a pill group. */
    val controlInset: Dp = control - CabinTokens.Space.sm.asDp * 2 + CabinTokens.Space.xs.asDp

    /** Height of pill groups in the system bar. */
    val barControl: Dp = control + CabinTokens.Space.sm.asDp

    val gapXs: Dp = CabinTokens.Space.xs.asDp
    val gapSm: Dp = CabinTokens.Space.sm.asDp
    val gapMd: Dp = CabinTokens.Space.md.asDp
    val gapLg: Dp = CabinTokens.Space.lg.asDp
    val gapXl: Dp = CabinTokens.Space.xl.asDp

    /** Track inset inside segmented controls and pill groups. */
    val trackInset: Dp = 6.dp

    /** Bottom underline for the Underline active style. */
    val activeIndicator: Dp = CabinTokens.Space.xs.asDp

    /** Panel edge for Line and Glass looks. */
    val hairline: Dp = 1.dp

    val iconSm: Dp = CabinTokens.Size.Icon.status.asDp
    val iconMd: Dp = CabinTokens.Size.Icon.systemBar.asDp
    val iconLg: Dp = 34.dp

    /** Progress bars. */
    val progressThin: Dp = 10.dp
    val progressThick: Dp = 14.dp

    /** Level dots (seat heat). */
    val levelDot: Dp = 10.dp

    /** Status bar height. */
    val statusBar: Dp = 64.dp

    /** System bar (climate + dock) height. */
    val systemBar: Dp = 104.dp

    /** Avatar in the status bar. */
    val avatar: Dp = 44.dp
}

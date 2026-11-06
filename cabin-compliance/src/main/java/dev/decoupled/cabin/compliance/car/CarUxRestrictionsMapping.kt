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

package dev.decoupled.cabin.compliance.car

import dev.decoupled.cabin.compliance.UxRestriction
import dev.decoupled.cabin.compliance.VehicleUiState

/**
 * Maps `android.car.drivingstate.CarUxRestrictions` values into
 * [VehicleUiState] without touching Car APIs, so the mapping is testable on
 * the JVM and reusable by hosts that already hold the restrictions.
 *
 * Flag values mirror the public `CarUxRestrictions.UX_RESTRICTIONS_*`
 * constants.
 */
object CarUxRestrictionsMapping {
    const val UX_RESTRICTIONS_BASELINE: Int = 0
    const val UX_RESTRICTIONS_NO_DIALPAD: Int = 1
    const val UX_RESTRICTIONS_NO_FILTERING: Int = 1 shl 1
    const val UX_RESTRICTIONS_LIMIT_STRING_LENGTH: Int = 1 shl 2
    const val UX_RESTRICTIONS_NO_KEYBOARD: Int = 1 shl 3
    const val UX_RESTRICTIONS_NO_VIDEO: Int = 1 shl 4
    const val UX_RESTRICTIONS_LIMIT_CONTENT: Int = 1 shl 5
    const val UX_RESTRICTIONS_NO_SETUP: Int = 1 shl 6
    const val UX_RESTRICTIONS_NO_TEXT_MESSAGE: Int = 1 shl 7
    const val UX_RESTRICTIONS_NO_VOICE_TRANSCRIPTION: Int = 1 shl 8

    /**
     * Build a [VehicleUiState] from platform restriction values.
     *
     * When the platform requires distraction optimization, the state carries
     * [UxRestriction.DistractionOptimized] and `isDriving = true`, so the
     * Restriction Engine resolves Restricted. Otherwise the vehicle is treated
     * as stationary: Parked when [parked] is `true`, Idling when `false` or
     * unknown (Idling is the more conservative stationary mode).
     *
     * @param requiresDistractionOptimization `CarUxRestrictions.isRequiresDistractionOptimization()`
     * @param activeRestrictions `CarUxRestrictions.getActiveRestrictions()`
     * @param parked gear/park signal when the host has one
     * @param speedKph vehicle speed when the host has one
     */
    fun toVehicleUiState(
        requiresDistractionOptimization: Boolean,
        activeRestrictions: Int,
        parked: Boolean? = null,
        speedKph: Float? = null,
    ): VehicleUiState {
        val flags = buildSet {
            if (requiresDistractionOptimization) add(UxRestriction.DistractionOptimized)
            if (activeRestrictions has UX_RESTRICTIONS_NO_SETUP) add(UxRestriction.NoSetup)
            if (activeRestrictions has UX_RESTRICTIONS_NO_FILTERING) add(UxRestriction.NoFiltering)
            if (activeRestrictions has UX_RESTRICTIONS_NO_KEYBOARD) add(UxRestriction.NoKeyboard)
            if (activeRestrictions has UX_RESTRICTIONS_NO_VIDEO) add(UxRestriction.NoVideo)
            if (activeRestrictions has UX_RESTRICTIONS_LIMIT_STRING_LENGTH) {
                add(UxRestriction.LimitStringLength)
            }
        }
        return if (requiresDistractionOptimization) {
            VehicleUiState(
                parkMode = false,
                speedKph = speedKph,
                isDriving = true,
                uxRestrictions = flags,
                label = "CarUxRestrictions(DO)",
            )
        } else {
            VehicleUiState(
                parkMode = parked ?: false,
                speedKph = speedKph ?: 0f,
                isDriving = false,
                uxRestrictions = flags,
                label = if (parked == true) "CarUxRestrictions(parked)" else "CarUxRestrictions(idle)",
            )
        }
    }

    private infix fun Int.has(flag: Int): Boolean = this and flag == flag
}

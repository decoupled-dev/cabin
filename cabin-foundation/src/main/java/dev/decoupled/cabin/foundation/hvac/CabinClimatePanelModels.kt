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

package dev.decoupled.cabin.foundation.hvac

import dev.decoupled.cabin.compliance.Signal

/** One climate zone (driver, passenger, rear …). */
data class CabinClimateZone(
    val id: String,
    val label: String,
    /** Set-point in °C (hosts convert for display units). */
    val temperatureC: Signal<Float>,
    /** Seat heat level, 0 = off. */
    val seatHeatLevel: Signal<Int>,
    val seatHeatMax: Int = 3,
)

/**
 * Climate state for the Home climate bar and the Climate panel (v0.2).
 *
 * Every control here is single-tap and gated as `HvacQuick` (allowed in every
 * drive state). Multi-step setup stays on `HvacAdjust`.
 */
data class CabinClimateState(
    val zones: List<CabinClimateZone>,
    /** 0 = off; [fanMax] = highest. Ignored while [fanAuto]. */
    val fanLevel: Signal<Int>,
    val fanMax: Int = 5,
    val fanAuto: Boolean,
    val acOn: Boolean,
    val recirculationOn: Boolean,
    val frontDefrostOn: Boolean,
    val rearDefrostOn: Boolean,
    val zonesSynced: Boolean,
    val temperatureStepC: Float = 0.5f,
    val temperatureMinC: Float = 16f,
    val temperatureMaxC: Float = 28f,
)

/** Climate actions; the host applies them to vehicle properties. */
sealed interface CabinClimateAction {
    data class TemperatureStep(val zoneId: String, val steps: Int) : CabinClimateAction
    data class SeatHeatCycle(val zoneId: String) : CabinClimateAction
    data class SetFanLevel(val level: Int) : CabinClimateAction
    data object SetFanAuto : CabinClimateAction
    data object ToggleAc : CabinClimateAction
    data object ToggleRecirculation : CabinClimateAction
    data object ToggleFrontDefrost : CabinClimateAction
    data object ToggleRearDefrost : CabinClimateAction
    data object ToggleSync : CabinClimateAction
}

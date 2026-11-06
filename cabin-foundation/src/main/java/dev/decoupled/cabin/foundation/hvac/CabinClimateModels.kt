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

/**
 * Shared ClimateTile presentation state (Compose + Views parity).
 *
 * Spec: docs/components/specs/climate-tile.md
 */
data class CabinClimateTileState(
    val zoneLabel: String,
    val temperatureC: Signal<Int>,
    val fanLevel: Signal<Int>,
    val fanMax: Int = 5,
    val seatHeatLevel: Signal<Int>,
    val seatHeatMax: Int = 3,
    val powerOn: Boolean = true,
) {
    companion object {
        fun empty(): CabinClimateTileState = CabinClimateTileState(
            zoneLabel = "",
            temperatureC = Signal.Unavailable,
            fanLevel = Signal.Unavailable,
            seatHeatLevel = Signal.Unavailable,
            powerOn = false,
        )
    }
}

/** ClimateTile actions — all gated as HvacAdjust. */
sealed interface CabinClimateTileAction {
    data object TempUp : CabinClimateTileAction
    data object TempDown : CabinClimateTileAction
    data object FanUp : CabinClimateTileAction
    data object FanDown : CabinClimateTileAction
    data object SeatHeatUp : CabinClimateTileAction
    data object SeatHeatDown : CabinClimateTileAction
}

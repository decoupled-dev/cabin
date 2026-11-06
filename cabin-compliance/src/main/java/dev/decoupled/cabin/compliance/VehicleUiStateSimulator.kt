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

package dev.decoupled.cabin.compliance

/**
 * Drive-state script for previews, demos and tests. Not a vehicle signal.
 *
 * Steps through [steps] in order and wraps around. Hosts call [next] from a
 * button or a timer and feed [current] into the theme.
 */
class VehicleUiStateSimulator(
    val steps: List<VehicleUiState> = DefaultDrive,
) {
    init {
        require(steps.isNotEmpty()) { "steps must not be empty" }
    }

    var index: Int = 0
        private set

    val current: VehicleUiState get() = steps[index]

    /** Advance to the next step (wrapping) and return it. */
    fun next(): VehicleUiState {
        index = (index + 1) % steps.size
        return current
    }

    /** Jump to the first step whose label matches [label]. */
    fun jumpTo(label: String): VehicleUiState {
        val found = steps.indexOfFirst { it.label == label }
        require(found >= 0) { "no step labeled $label" }
        index = found
        return current
    }

    companion object {
        /** Parked → Idling → Moving → Restricted. */
        val DefaultDrive: List<VehicleUiState> = listOf(
            VehicleUiState.parked(),
            VehicleUiState.idling(),
            VehicleUiState.moving(),
            VehicleUiState.restricted(),
        )
    }
}

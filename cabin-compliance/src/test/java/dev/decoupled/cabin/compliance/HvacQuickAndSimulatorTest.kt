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

import org.junit.Assert.assertEquals
import org.junit.Test

class HvacQuickAndSimulatorTest {
    private val engine = CabinRestrictionEngine()

    @Test
    fun hvacQuickAllowedInEveryMode() {
        listOf(
            VehicleUiState.parked(),
            VehicleUiState.idling(),
            VehicleUiState.moving(),
            VehicleUiState.restricted(),
            VehicleUiState.unknown(),
        ).forEach { state ->
            assertEquals(state.label, GateDisposition.Allow, engine.disposition(CabinInteraction.HvacQuick, state))
            assertEquals(state.label, GateDisposition.Allow, engine.disposition(CabinInteraction.VehicleQuick, state))
        }
    }

    @Test
    fun hvacAdjustStillFailClosedWhileMoving() {
        assertEquals(GateDisposition.Block, engine.disposition(CabinInteraction.HvacAdjust, VehicleUiState.moving()))
        assertEquals(GateDisposition.Block, engine.disposition(CabinInteraction.VehicleAdjust, VehicleUiState.moving()))
    }

    @Test
    fun simulatorWrapsThroughDefaultDrive() {
        val sim = VehicleUiStateSimulator()
        assertEquals("Parked", sim.current.label)
        assertEquals(listOf("Idling", "Moving", "Restricted", "Parked"), List(4) { sim.next().label })
        assertEquals("Moving", sim.jumpTo("Moving").label)
    }
}

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

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.CabinUiMode
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.UxRestriction
import dev.decoupled.cabin.compliance.car.CarUxRestrictionsMapping.UX_RESTRICTIONS_BASELINE
import dev.decoupled.cabin.compliance.car.CarUxRestrictionsMapping.UX_RESTRICTIONS_NO_KEYBOARD
import dev.decoupled.cabin.compliance.car.CarUxRestrictionsMapping.UX_RESTRICTIONS_NO_SETUP
import dev.decoupled.cabin.compliance.car.CarUxRestrictionsMapping.UX_RESTRICTIONS_NO_VIDEO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CarUxRestrictionsMappingTest {
    private val engine = CabinRestrictionEngine()

    @Test
    fun distractionOptimizationResolvesRestricted() {
        val state = CarUxRestrictionsMapping.toVehicleUiState(
            requiresDistractionOptimization = true,
            activeRestrictions = UX_RESTRICTIONS_NO_KEYBOARD or UX_RESTRICTIONS_NO_VIDEO,
        )
        assertEquals(CabinUiMode.Restricted, engine.uiMode(state))
        assertTrue(UxRestriction.NoKeyboard in state.uxRestrictions)
        assertTrue(UxRestriction.NoVideo in state.uxRestrictions)
        assertEquals(GateDisposition.Block, engine.disposition(CabinInteraction.MediaComplex, state))
    }

    @Test
    fun baselineWithParkSignalResolvesParked() {
        val state = CarUxRestrictionsMapping.toVehicleUiState(
            requiresDistractionOptimization = false,
            activeRestrictions = UX_RESTRICTIONS_BASELINE,
            parked = true,
        )
        assertEquals(CabinUiMode.Parked, engine.uiMode(state))
        assertTrue(state.uxRestrictions.isEmpty())
    }

    @Test
    fun baselineWithoutParkSignalIsConservativeIdling() {
        val state = CarUxRestrictionsMapping.toVehicleUiState(
            requiresDistractionOptimization = false,
            activeRestrictions = UX_RESTRICTIONS_NO_SETUP,
        )
        assertEquals(CabinUiMode.Idling, engine.uiMode(state))
        assertTrue(UxRestriction.NoSetup in state.uxRestrictions)
    }
}

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

package dev.decoupled.cabin.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinClimateTileTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun parked_adjustmentsFire() {
        val actions = mutableListOf<CabinClimateTileAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinClimateTile(state = liveState(), onAction = { actions += it })
            }
        }

        composeRule.onNodeWithTag("climate_temp_up").performClick()
        composeRule.onNodeWithTag("climate_fan_down").performClick()
        composeRule.onNodeWithTag("climate_seat_up").performClick()

        assertEquals(
            listOf(
                CabinClimateTileAction.TempUp,
                CabinClimateTileAction.FanDown,
                CabinClimateTileAction.SeatHeatUp,
            ),
            actions,
        )
    }

    @Test
    fun moving_blocksHvacAdjust() {
        val actions = mutableListOf<CabinClimateTileAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinClimateTile(state = liveState(), onAction = { actions += it })
            }
        }

        composeRule.onNodeWithTag("climate_temp_up").performClick()
        composeRule.onNodeWithTag("climate_fan_up").performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun absentCompliance_failClosed() {
        val actions = mutableListOf<CabinClimateTileAction>()
        composeRule.setContent {
            // No CabinTheme → LocalCabinComplianceState defaults to Absent.
            CabinClimateTile(state = liveState(), onAction = { actions += it })
        }

        composeRule.onNodeWithTag("climate_temp_up").performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun unavailableSignals_showEmDash() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinClimateTile(
                    state = CabinClimateTileState(
                        zoneLabel = "Driver",
                        temperatureC = Signal.Unavailable,
                        fanLevel = Signal.Unavailable,
                        seatHeatLevel = Signal.Unavailable,
                    ),
                    onAction = {},
                )
            }
        }

        composeRule.onNodeWithTag("climate_temp_value").assertIsDisplayed()
        composeRule.onNodeWithTag("climate_fan_value").assertIsDisplayed()
        composeRule.onNodeWithTag("climate_seat_value").assertIsDisplayed()
        assertEquals("—", formatTemp(Signal.Unavailable))
        assertEquals("—", formatLevel(Signal.Unavailable, 5))
        assertEquals("21° · stale", formatTemp(Signal.Stale(21, atMillis = 1L)))
    }

    private fun liveState() = CabinClimateTileState(
        zoneLabel = "Driver",
        temperatureC = Signal.Value(22, atMillis = 1L),
        fanLevel = Signal.Value(3, atMillis = 1L),
        seatHeatLevel = Signal.Value(1, atMillis = 1L),
    )
}

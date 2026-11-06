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

package dev.decoupled.cabin.screens

import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.hvac.CabinClimateAction
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingAction
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE], qualifiers = "w1920dp-h720dp-mdpi")
class CabinScreensGatingTest {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun mediaWhileMovingHidesBrowseButKeepsTransport() {
        val actions = mutableListOf<CabinMediaNowPlayingAction>()
        var vehicle by mutableStateOf(VehicleUiState.moving())
        compose.setContent {
            CabinTheme(vehicleState = vehicle) {
                CabinMediaScreen(CabinScreenSamples.media, { actions += it }, CabinScreenSamples.queue, {})
            }
        }
        compose.onNodeWithText("Browse when parked").assertExists()
        compose.onNodeWithText("Library opens when parked").assertExists()
        compose.onAllNodesWithText("Browse").assertCountEquals(0)
        compose.onAllNodesWithText("Slow Coast").assertCountEquals(0)
        compose.onNodeWithContentDescription("Pause").assertIsEnabled().performClick()
        assertEquals(listOf(CabinMediaNowPlayingAction.PlayPause), actions)

        vehicle = VehicleUiState.parked()
        compose.onNodeWithText("Browse").assertExists()
        compose.onNodeWithText("Slow Coast").assertExists()
    }

    @Test
    fun climateStaysUsableWhileMoving() {
        val actions = mutableListOf<CabinClimateAction>()
        compose.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinClimateScreen(CabinScreenSamples.climate, { actions += it })
            }
        }
        compose.onNodeWithContentDescription("Driver temperature up").assertIsEnabled().performClick()
        compose.onNodeWithText("Front").performClick()
        assertEquals(
            listOf(CabinClimateAction.TemperatureStep("driver", 1), CabinClimateAction.ToggleFrontDefrost),
            actions,
        )
    }

    @Test
    fun sampleReducerSyncsZonesAndClampsTemperature() {
        var s = CabinScreenSamples.reduce(CabinScreenSamples.climate, CabinClimateAction.ToggleSync)
        s = CabinScreenSamples.reduce(s, CabinClimateAction.TemperatureStep("driver", 100))
        assertEquals(listOf(28f, 28f), s.zones.map { (it.temperatureC as dev.decoupled.cabin.compliance.Signal.Value).value })
    }

    private fun androidx.compose.ui.test.SemanticsNodeInteractionCollection.assertCountEquals(n: Int) {
        assertEquals(n, fetchSemanticsNodes().size)
    }
}

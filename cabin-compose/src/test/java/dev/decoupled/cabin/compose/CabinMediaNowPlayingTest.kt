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
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingAction
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinMediaNowPlayingTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun parked_transportAndSourceFire() {
        val actions = mutableListOf<CabinMediaNowPlayingAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinMediaNowPlaying(state = liveState(), onAction = { actions += it })
            }
        }

        composeRule.onNodeWithTag("media_play_pause").performClick()
        composeRule.onNodeWithTag("media_next").performClick()
        composeRule.onNodeWithTag("media_source").performClick()

        assertEquals(
            listOf(
                CabinMediaNowPlayingAction.PlayPause,
                CabinMediaNowPlayingAction.Next,
                CabinMediaNowPlayingAction.OpenSource,
            ),
            actions,
        )
    }

    @Test
    fun moving_allowsTransport_blocksComplexSource() {
        val actions = mutableListOf<CabinMediaNowPlayingAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinMediaNowPlaying(state = liveState(), onAction = { actions += it })
            }
        }

        composeRule.onNodeWithTag("media_previous").performClick()
        assertEquals(listOf(CabinMediaNowPlayingAction.Previous), actions)

        composeRule.onNodeWithTag("media_source").performClick()
        assertEquals(listOf(CabinMediaNowPlayingAction.Previous), actions)
    }

    @Test
    fun absentCompliance_failClosed() {
        val actions = mutableListOf<CabinMediaNowPlayingAction>()
        composeRule.setContent {
            CabinMediaNowPlaying(state = liveState(), onAction = { actions += it })
        }

        composeRule.onNodeWithTag("media_play_pause").performClick()
        composeRule.onNodeWithTag("media_source").performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun unavailableProgress_omitsNumbers() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinMediaNowPlaying(
                    state = liveState().copy(
                        positionMs = Signal.Unavailable,
                        durationMs = Signal.Unavailable,
                    ),
                    onAction = {},
                )
            }
        }

        assertEquals(
            0,
            composeRule.onAllNodesWithTag("media_progress").fetchSemanticsNodes().size,
        )
        assertNull(liveProgressText(Signal.Unavailable, Signal.Unavailable))
    }

    @Test
    fun liveProgress_rendersHonestValues() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinMediaNowPlaying(
                    state = liveState().copy(
                        positionMs = Signal.Value(65_000L, atMillis = 1L),
                        durationMs = Signal.Value(180_000L, atMillis = 1L),
                    ),
                    onAction = {},
                )
            }
        }

        composeRule.onNodeWithTag("media_progress").assertIsDisplayed()
        composeRule.onNodeWithText("1:05 / 3:00").assertIsDisplayed()
    }

    private fun liveState() = CabinMediaNowPlayingState(
        title = Signal.Value("Track", atMillis = 1L),
        artist = Signal.Value("Artist", atMillis = 1L),
        sourceLabel = Signal.Value("Bluetooth", atMillis = 1L),
        isPlaying = true,
        artworkAvailable = true,
        positionMs = Signal.Value(30_000L, atMillis = 1L),
        durationMs = Signal.Value(120_000L, atMillis = 1L),
        hasSource = true,
    )
}

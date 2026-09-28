@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.action

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.components.action.CabinButtonAction
import dev.decoupled.cabin.foundation.components.action.CabinButtonState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinButtonTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun parked_activateFires() {
        val actions = mutableListOf<CabinButtonAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinButton(
                    state = CabinButtonState(label = "Continue", variant = "filled"),
                    onAction = { actions += it },
                )
            }
        }

        composeRule.onNodeWithText("Continue").assertIsDisplayed()
        composeRule.onNodeWithTag("cabin_button").performClick()
        assertEquals(listOf(CabinButtonAction.Activate), actions)
    }

    @Test
    fun moving_navigateSimpleStillActivates() {
        val actions = mutableListOf<CabinButtonAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinButton(
                    state = CabinButtonState(label = "Home"),
                    onAction = { actions += it },
                )
            }
        }

        composeRule.onNodeWithTag("cabin_button").performClick()
        assertEquals(listOf(CabinButtonAction.Activate), actions)
    }

    @Test
    fun absentCompliance_failClosed() {
        val actions = mutableListOf<CabinButtonAction>()
        composeRule.setContent {
            CabinButton(
                state = CabinButtonState(label = "Continue"),
                onAction = { actions += it },
            )
        }

        composeRule.onNodeWithTag("cabin_button").performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun disabled_doesNotActivate() {
        val actions = mutableListOf<CabinButtonAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinButton(
                    state = CabinButtonState(
                        label = "Continue",
                        ui = CabinComponentUiState(enabled = false),
                    ),
                    onAction = { actions += it },
                )
            }
        }

        composeRule.onNodeWithTag("cabin_button").performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun iconVariant_showsGlyphWhenLabelBlank() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinButton(state = CabinButtonState(label = "", variant = "icon"))
            }
        }

        composeRule.onNodeWithText("＋").assertIsDisplayed()
    }
}

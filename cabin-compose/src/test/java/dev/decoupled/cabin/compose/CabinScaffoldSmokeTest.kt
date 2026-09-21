package dev.decoupled.cabin.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.action.CabinButton
import dev.decoupled.cabin.compose.layout.CabinAdaptiveScaffold
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.CabinScaffold
import dev.decoupled.cabin.foundation.CabinWindowSizeClass
import dev.decoupled.cabin.foundation.components.action.CabinButtonAction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(CabinScaffold::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinScaffoldSmokeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun parked_buttonActivateFires() {
        val actions = mutableListOf<CabinButtonAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinButton(onAction = { actions += it })
            }
        }
        composeRule.onNodeWithTag("cabin_button").assertIsDisplayed()
        composeRule.onNodeWithTag("cabin_button_activate").performClick()
        assertEquals(listOf(CabinButtonAction.Activate), actions)
    }

    @Test
    fun moving_complexDialogDoesNotActivate() {
        val actions = mutableListOf<dev.decoupled.cabin.foundation.components.feedback.CabinDialogAction>()
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                dev.decoupled.cabin.compose.feedback.CabinDialog(onAction = { actions += it })
            }
        }
        composeRule.onNodeWithTag("cabin_dialog_activate").performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun adaptiveScaffold_listDetailShowsPanes() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinAdaptiveScaffold(
                    profile = dev.decoupled.cabin.foundation.CabinDisplayProfile(
                        sizeClass = CabinWindowSizeClass.StandardLandscape,
                    ),
                    primary = {},
                    secondary = {},
                )
            }
        }
        composeRule.onNodeWithTag("cabin_adaptive_scaffold").assertIsDisplayed()
        composeRule.onNodeWithTag("scaffold_primary").assertIsDisplayed()
        composeRule.onNodeWithTag("scaffold_secondary").assertIsDisplayed()
    }

    @Test
    fun focused_showsFamilyMark() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinButton(
                    state = dev.decoupled.cabin.foundation.components.action.CabinButtonState(
                        ui = dev.decoupled.cabin.foundation.CabinComponentUiState(focused = true),
                    ),
                )
            }
        }
        composeRule.onNodeWithTag("cabin_button_mark").assertIsDisplayed()
    }
}

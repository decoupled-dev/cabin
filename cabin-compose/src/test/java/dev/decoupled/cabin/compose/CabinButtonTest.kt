package dev.decoupled.cabin.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import org.junit.Assert.assertEquals
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
    fun parked_allowsNavigateSimple_clickFires() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinButton(
                    state = CabinButtonState(
                        label = "Go",
                        interaction = CabinInteraction.NavigateSimple,
                        testTag = "go",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("go").assertIsDisplayed()
        composeRule.onNodeWithTag("go").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun moving_blocksOpenComplexApp_clickDoesNotFire() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinButton(
                    state = CabinButtonState(
                        label = "Settings",
                        interaction = CabinInteraction.OpenComplexApp,
                        testTag = "settings",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("settings").performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun missingThemeCompliance_failClosed_blocksActivation() {
        var clicks = 0
        composeRule.setContent {
            CabinButton(
                state = CabinButtonState(
                    label = "Go",
                    interaction = CabinInteraction.NavigateSimple,
                    testTag = "go",
                ),
                onClick = { clicks++ },
            )
        }

        composeRule.onNodeWithTag("go").performClick()
        assertEquals(0, clicks)
        assertEquals(0.4f, gateAlpha(GateDisposition.Block))
    }

    @Test
    fun iconButton_activatesWhenAllowed() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinIconButton(
                    state = CabinIconButtonState(
                        icon = null,
                        contentDescription = "Mute",
                        interaction = CabinInteraction.MediaTransport,
                        testTag = "mute",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("mute").assertIsDisplayed()
        composeRule.onNodeWithTag("mute").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun iconButton_moving_blocksOpenKeyboard() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinIconButton(
                    state = CabinIconButtonState(
                        icon = null,
                        contentDescription = "Search",
                        interaction = CabinInteraction.OpenKeyboard,
                        testTag = "search",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("search").performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun touchMinima_matchTokens() {
        assertEquals(76f, CabinButtonTokens.minHeightDp)
        assertEquals(16f, CabinButtonTokens.horizontalPaddingDp)
        assertEquals(28f, CabinButtonTokens.iconSizeDp)
        assertEquals(76f, CabinIconButtonTokens.minSizeDp)
        assertEquals(28f, CabinIconButtonTokens.iconSizeDp)
        assertEquals(8f, CabinButtonTokens.cornerRadiusDp)
    }
}

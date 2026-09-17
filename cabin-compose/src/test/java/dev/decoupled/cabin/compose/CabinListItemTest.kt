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
class CabinListItemTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun parked_allowsNavigateSimple_clickFires() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinListItem(
                    state = CabinListItemState(
                        title = "Home",
                        supportingText = "Map",
                        interaction = CabinInteraction.NavigateSimple,
                        testTag = "home",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("home").assertIsDisplayed()
        composeRule.onNodeWithTag("home").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun moving_blocksOpenComplexApp_clickDoesNotFire() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinListItem(
                    state = CabinListItemState(
                        title = "Settings",
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
    fun restricted_blocksFilterOrSort() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.restricted()) {
                CabinListItem(
                    state = CabinListItemState(
                        title = "Sort",
                        interaction = CabinInteraction.FilterOrSort,
                        testTag = "sort",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("sort").performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun idling_substitutesOpenComplexApp_keepsVisible() {
        var clicks = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.idling()) {
                CabinListItem(
                    state = CabinListItemState(
                        title = "Apps",
                        interaction = CabinInteraction.OpenComplexApp,
                        testTag = "apps",
                    ),
                    onClick = { clicks++ },
                )
            }
        }

        composeRule.onNodeWithTag("apps").assertIsDisplayed()
        composeRule.onNodeWithTag("apps").performClick()
        assertEquals(0, clicks)
        assertEquals(0.55f, gateAlpha(GateDisposition.Substitute))
    }

    @Test
    fun missingThemeCompliance_failClosed_blocksActivation() {
        var clicks = 0
        composeRule.setContent {
            CabinListItem(
                state = CabinListItemState(
                    title = "Home",
                    interaction = CabinInteraction.NavigateSimple,
                    testTag = "home",
                ),
                onClick = { clicks++ },
            )
        }

        composeRule.onNodeWithTag("home").performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun cabinDensity_minHeightMatchesTouchToken() {
        assertEquals(76f, CabinListItemTokens.minHeightDp)
        assertEquals(16f, CabinListItemTokens.horizontalPaddingDp)
        assertEquals(8f, CabinListItemTokens.verticalPaddingDp)
        assertEquals(16f, CabinListItemTokens.gapDp)
        assertEquals(28f, CabinListItemTokens.iconSizeDp)
    }
}

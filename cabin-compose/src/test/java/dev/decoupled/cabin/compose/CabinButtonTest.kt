package dev.decoupled.cabin.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.compose.theme.resolveCabinColors
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
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

    @Test
    fun craft_filledUsesForestPrimary_outlinedUsesOutline_notSafety() {
        val colors = resolveCabinColors(CabinColorScheme.Day)
        assertEquals(Color(CabinTokens.Color.Semantic.primary.argb), colors.primary)
        assertEquals(Color(CabinTokens.Color.Semantic.onPrimary.argb), colors.onPrimary)
        assertEquals("#0B6E4F", CabinTokens.Color.Semantic.primary.hex)
        assertNotEquals(colors.primary, colors.warning)
        assertNotEquals(colors.primary, colors.error)
        assertNotEquals(colors.outline, colors.warning)
        assertNotEquals(colors.outline, colors.error)
    }

    @Test
    fun craft_nightWarningErrorStayLocked() {
        val night = resolveCabinColors(CabinColorScheme.Night)
        val day = resolveCabinColors(CabinColorScheme.Day)
        assertEquals(Color(CabinTokens.colorScheme(CabinColorScheme.Night).warning.argb), night.warning)
        assertEquals(Color(CabinTokens.colorScheme(CabinColorScheme.Night).error.argb), night.error)
        assertNotEquals(day.warning, night.warning)
        assertNotEquals(day.error, night.error)
        // Button chrome must not adopt locked safety roles.
        assertNotEquals(night.primary, night.warning)
        assertNotEquals(night.primary, night.error)
        assertNotEquals(night.outline, night.warning)
        assertNotEquals(night.outline, night.error)
    }
}

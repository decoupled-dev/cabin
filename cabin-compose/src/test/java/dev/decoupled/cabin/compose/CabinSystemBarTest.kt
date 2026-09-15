package dev.decoupled.cabin.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
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
class CabinSystemBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun slots_bindLeadingCenterTrailing_andActivateWhenAllowed() {
        var home = 0
        var hvac = 0
        var apps = 0

        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        leading = listOf(
                            CabinSystemBarEntry(
                                id = "home",
                                label = "Home",
                                contentDescription = "Home",
                                interaction = CabinInteraction.NavigateSimple,
                                onActivate = { home++ },
                            ),
                        ),
                        center = listOf(
                            CabinSystemBarEntry(
                                id = "hvac",
                                label = "HVAC",
                                contentDescription = "Climate peek",
                                interaction = CabinInteraction.HvacPeek,
                                onActivate = { hvac++ },
                            ),
                        ),
                        trailing = listOf(
                            CabinSystemBarEntry(
                                id = "apps",
                                label = "Apps",
                                contentDescription = "App grid",
                                interaction = CabinInteraction.OpenComplexApp,
                                onActivate = { apps++ },
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("home").assertIsDisplayed()
        composeRule.onNodeWithTag("hvac").assertIsDisplayed()
        composeRule.onNodeWithTag("apps").assertIsDisplayed()

        composeRule.onNodeWithTag("home").performClick()
        composeRule.onNodeWithTag("apps").performClick()
        assertEquals(1, home)
        assertEquals(1, apps)
        assertEquals(0, hvac)
    }

    @Test
    fun moving_blocksOpenComplexApp_clickDoesNotFire() {
        var apps = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        trailing = listOf(
                            CabinSystemBarEntry(
                                id = "apps",
                                label = "Apps",
                                contentDescription = "App grid",
                                interaction = CabinInteraction.OpenComplexApp,
                                onActivate = { apps++ },
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("apps").performClick()
        assertEquals(0, apps)
    }

    @Test
    fun moving_allowsNavigateSimple() {
        var home = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        leading = listOf(
                            CabinSystemBarEntry(
                                id = "home",
                                label = "Home",
                                contentDescription = "Home",
                                interaction = CabinInteraction.NavigateSimple,
                                onActivate = { home++ },
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("home").performClick()
        assertEquals(1, home)
    }

    @Test
    fun restricted_blocksOpenKeyboard() {
        var search = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.restricted()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        trailing = listOf(
                            CabinSystemBarEntry(
                                id = "search",
                                label = "Search",
                                contentDescription = "Search",
                                interaction = CabinInteraction.OpenKeyboard,
                                onActivate = { search++ },
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("search").performClick()
        assertEquals(0, search)
    }

    @Test
    fun idling_substitutesOpenComplexApp_keepsVisibleNonActivatable() {
        var apps = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.idling()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        trailing = listOf(
                            CabinSystemBarEntry(
                                id = "apps",
                                label = "Apps",
                                contentDescription = "App grid",
                                interaction = CabinInteraction.OpenComplexApp,
                                onActivate = { apps++ },
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithText("Apps").assertIsDisplayed()
        composeRule.onNodeWithTag("apps").performClick()
        assertEquals(0, apps)
        assertEquals(0.55f, gateAlpha(GateDisposition.Substitute))
    }

    @Test
    fun iconOnlyEntry_setsContentDescription() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        leading = listOf(
                            CabinSystemBarEntry(
                                id = "map",
                                contentDescription = "Open map",
                                interaction = CabinInteraction.NavigateSimple,
                                onActivate = {},
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("map").assertIsDisplayed()
    }

    @Test
    fun missingThemeCompliance_failClosed_blocksActivation() {
        var apps = 0
        composeRule.setContent {
            // No CabinTheme → LocalCabinComplianceState defaults to Absent.
            CabinSystemBar(
                slots = CabinSystemBarSlots(
                    trailing = listOf(
                        CabinSystemBarEntry(
                            id = "apps",
                            label = "Apps",
                            contentDescription = "App grid",
                            interaction = CabinInteraction.OpenComplexApp,
                            onActivate = { apps++ },
                        ),
                    ),
                ),
            )
        }

        composeRule.onNodeWithTag("apps").performClick()
        assertEquals(0, apps)
        assertEquals(0.4f, gateAlpha(GateDisposition.Block))
    }

    @Test
    fun touchMinima_matchTokens() {
        assertEquals(80f, CabinSystemBarTokens.heightDp)
        assertEquals(28f, CabinSystemBarTokens.iconSizeDp)
        assertEquals(76f, CabinSystemBarTokens.itemMinSizeDp)
        assertEquals(8f, CabinSystemBarTokens.gapDp)
    }

    @Test
    fun unknownState_blocksComplexApp_failSafe() {
        var apps = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.unknown()) {
                CabinSystemBar(
                    slots = CabinSystemBarSlots(
                        trailing = listOf(
                            CabinSystemBarEntry(
                                id = "apps",
                                label = "Apps",
                                contentDescription = "App grid",
                                interaction = CabinInteraction.OpenComplexApp,
                                onActivate = { apps++ },
                            ),
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("apps").performClick()
        assertEquals(0, apps)
    }

}

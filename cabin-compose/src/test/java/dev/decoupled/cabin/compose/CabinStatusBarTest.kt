package dev.decoupled.cabin.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.compose.theme.resolveCabinColors
import dev.decoupled.cabin.tokens.CabinColorScheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinStatusBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun items_bindOrderedGlyphs() {
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinStatusBar(
                    items = listOf(
                        CabinStatusGlyph(id = "clock", contentDescription = "Clock", text = "12:00"),
                        CabinStatusGlyph(id = "soc", contentDescription = "Battery", text = "80%"),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("clock").assertIsDisplayed()
        composeRule.onNodeWithTag("soc").assertIsDisplayed()
        composeRule.onNodeWithTag("clock_text").assertIsDisplayed()
    }

    @Test
    fun signalUnavailable_rendersExplicitDegradedUi() {
        val presentation = presentStatusSignal(
            item = CabinStatusGlyph(
                id = "temp",
                contentDescription = "Outside temperature",
                signal = Signal.Unavailable,
            ),
            unavailableText = "—",
            unavailableA11y = { "$it unavailable" },
            staleText = { "$it ·" },
            staleA11y = { "$it stale" },
            faultText = { "!$it" },
            faultA11y = { label, code -> "$label fault $code" },
        )
        assertEquals("—", presentation.text)
        assertEquals(StatusTone.Degraded, presentation.tone)
        assertTrue(presentation.contentDescription.contains("unavailable", ignoreCase = true))
        assertTrue(presentation.showIconPlaceholder)
    }

    @Test
    fun signalStale_labelsLastValue() {
        val presentation = presentStatusSignal(
            item = CabinStatusGlyph(
                id = "soc",
                contentDescription = "Battery",
                text = "72%",
                signal = Signal.Stale(last = "72%", atMillis = 1L),
            ),
            unavailableText = "—",
            unavailableA11y = { "$it unavailable" },
            staleText = { "$it ·" },
            staleA11y = { "$it stale" },
            faultText = { "!$it" },
            faultA11y = { label, code -> "$label fault $code" },
        )
        assertTrue(presentation.text.toString().contains("72%"))
        assertEquals(StatusTone.Degraded, presentation.tone)
        assertTrue(presentation.contentDescription.contains("stale", ignoreCase = true))
    }

    @Test
    fun signalFault_rendersFaultTone() {
        val presentation = presentStatusSignal(
            item = CabinStatusGlyph(
                id = "charge",
                contentDescription = "Charging",
                signal = Signal.Fault(code = "E42"),
            ),
            unavailableText = "—",
            unavailableA11y = { "$it unavailable" },
            staleText = { "$it ·" },
            staleA11y = { "$it stale" },
            faultText = { "!$it" },
            faultA11y = { label, code -> "$label fault $code" },
        )
        assertTrue(presentation.text.toString().contains("E42"))
        assertEquals(StatusTone.Fault, presentation.tone)
        assertTrue(presentation.contentDescription.contains("fault", ignoreCase = true))
    }

    @Test
    fun restricted_blocksSettingsDeepLink() {
        var opened = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.restricted()) {
                CabinStatusBar(
                    items = listOf(
                        CabinStatusGlyph(
                            id = "wifi",
                            contentDescription = "Wi-Fi",
                            text = "Wi-Fi",
                            deepLink = StatusDeepLink(opensSettings = true) { opened++ },
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("wifi").performClick()
        assertEquals(0, opened)
    }

    @Test
    fun moving_substitutesInformationalDeepLink() {
        var opened = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.moving()) {
                CabinStatusBar(
                    items = listOf(
                        CabinStatusGlyph(
                            id = "alert",
                            contentDescription = "Alerts",
                            text = "1",
                            deepLink = StatusDeepLink(opensSettings = false) { opened++ },
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("alert").assertIsDisplayed()
        composeRule.onNodeWithTag("alert").performClick()
        assertEquals(0, opened)
    }

    @Test
    fun parked_allowsSettingsDeepLink() {
        var opened = 0
        composeRule.setContent {
            CabinTheme(vehicleState = VehicleUiState.parked()) {
                CabinStatusBar(
                    items = listOf(
                        CabinStatusGlyph(
                            id = "wifi",
                            contentDescription = "Wi-Fi settings",
                            text = "Wi-Fi",
                            deepLink = StatusDeepLink(opensSettings = true) { opened++ },
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithTag("wifi").performClick()
        assertEquals(1, opened)
    }

    @Test
    fun missingThemeCompliance_failClosed_blocksDeepLink() {
        var opened = 0
        composeRule.setContent {
            CabinStatusBar(
                items = listOf(
                    CabinStatusGlyph(
                        id = "wifi",
                        contentDescription = "Wi-Fi settings",
                        text = "Wi-Fi",
                        deepLink = StatusDeepLink(opensSettings = true) { opened++ },
                    ),
                ),
            )
        }

        composeRule.onNodeWithTag("wifi").performClick()
        assertEquals(0, opened)
    }

    @Test
    fun emphasisWarning_mapsToWarningToneColor() {
        val colors = resolveCabinColors(CabinColorScheme.Day)
        val presentation = presentStatusSignal(
            item = CabinStatusGlyph(
                id = "alerts",
                contentDescription = "Active alerts",
                text = "2",
                emphasis = CabinStatusEmphasis.Warning,
            ),
            unavailableText = "—",
            unavailableA11y = { it },
            staleText = { it },
            staleA11y = { it },
            faultText = { it },
            faultA11y = { label, _ -> label },
        )
        assertEquals(StatusTone.Warning, presentation.tone)
        assertEquals(colors.warning, toneColor(colors, presentation.tone))
    }

    @Test
    fun emphasisCharging_mapsToChargingToneColor() {
        val colors = resolveCabinColors(CabinColorScheme.Day)
        val presentation = presentStatusSignal(
            item = CabinStatusGlyph(
                id = "soc",
                contentDescription = "Battery charging",
                text = "80%",
                signal = Signal.Value(value = "80%", atMillis = 1L),
                emphasis = CabinStatusEmphasis.Charging,
            ),
            unavailableText = "—",
            unavailableA11y = { it },
            staleText = { it },
            staleA11y = { it },
            faultText = { it },
            faultA11y = { label, _ -> label },
        )
        assertEquals(StatusTone.Charging, presentation.tone)
        assertEquals(colors.charging, toneColor(colors, presentation.tone))
    }

    @Test
    fun signalFault_overridesChargingEmphasis() {
        val colors = resolveCabinColors(CabinColorScheme.Night)
        val presentation = presentStatusSignal(
            item = CabinStatusGlyph(
                id = "soc",
                contentDescription = "Battery",
                signal = Signal.Fault(code = "E42"),
                emphasis = CabinStatusEmphasis.Charging,
            ),
            unavailableText = "—",
            unavailableA11y = { it },
            staleText = { it },
            staleA11y = { it },
            faultText = { "!$it" },
            faultA11y = { label, code -> "$label fault $code" },
        )
        assertEquals(StatusTone.Fault, presentation.tone)
        assertEquals(colors.error, toneColor(colors, presentation.tone))
        assertTrue(toneColor(colors, StatusTone.Degraded) == colors.outline)
        assertTrue(toneColor(colors, StatusTone.Normal) == colors.onContainer)
    }

    @Test
    fun theme_dayNight_containerAndFeedbackResolve() {
        val day = resolveCabinColors(CabinColorScheme.Day)
        val night = resolveCabinColors(CabinColorScheme.Night)
        assertTrue(day.container != Color.Unspecified)
        assertTrue(day.onContainer != Color.Unspecified)
        assertTrue(day.outline != Color.Unspecified)
        assertTrue(day.error != Color.Unspecified)
        assertTrue(night.container != day.container)
        assertTrue(night.charging != day.charging)
    }

    @Test
    fun tokenSizes_matchStub() {
        assertEquals(48f, CabinStatusBarTokens.heightDp)
        assertEquals(24f, CabinStatusBarTokens.iconSizeDp)
        assertEquals(8f, CabinStatusBarTokens.itemGapDp)
    }
}

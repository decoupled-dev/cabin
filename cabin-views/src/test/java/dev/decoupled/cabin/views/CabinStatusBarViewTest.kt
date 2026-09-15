package dev.decoupled.cabin.views

import android.view.ContextThemeWrapper
import android.view.View
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver
import dev.decoupled.cabin.views.theme.CabinThemes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinStatusBarViewTest {

    private lateinit var themedContext: android.content.Context
    private lateinit var bar: CabinStatusBarView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        themedContext = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        bar = CabinStatusBarView(themedContext)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        bar.setCompliance(host)
    }

    @Test
    fun setItems_bindsOrderedGlyphs() {
        bar.setItems(
            listOf(
                CabinStatusGlyph(id = "clock", contentDescription = "Clock", text = "12:00"),
                CabinStatusGlyph(id = "soc", contentDescription = "Battery", text = "80%"),
            ),
        )
        assertEquals(2, bar.itemViewCount())
        assertNotNull(bar.findItemView("clock"))
        assertNotNull(bar.findItemView("soc"))
        assertEquals("12:00", bar.itemPrimaryText("clock"))
    }

    @Test
    fun signalUnavailable_rendersExplicitDegradedUi() {
        bar.setItems(
            listOf(
                CabinStatusGlyph(
                    id = "temp",
                    contentDescription = "Outside temperature",
                    signal = Signal.Unavailable,
                ),
            ),
        )
        val text = bar.itemPrimaryText("temp")!!.toString()
        assertEquals("—", text)
        assertTrue(
            bar.findItemView("temp")!!.contentDescription.toString()
                .contains("unavailable", ignoreCase = true),
        )
    }

    @Test
    fun signalStale_labelsLastValue() {
        bar.setItems(
            listOf(
                CabinStatusGlyph(
                    id = "soc",
                    contentDescription = "Battery",
                    text = "72%",
                    signal = Signal.Stale(last = "72%", atMillis = 1L),
                ),
            ),
        )
        val text = bar.itemPrimaryText("soc")!!.toString()
        assertTrue(text.contains("72%"))
        assertTrue(
            bar.findItemView("soc")!!.contentDescription.toString()
                .contains("stale", ignoreCase = true),
        )
    }

    @Test
    fun signalFault_rendersFaultAndKeepsItem() {
        bar.setItems(
            listOf(
                CabinStatusGlyph(id = "clock", contentDescription = "Clock", text = "9:00"),
                CabinStatusGlyph(
                    id = "charge",
                    contentDescription = "Charging",
                    signal = Signal.Fault(code = "E42"),
                ),
            ),
        )
        assertEquals(2, bar.itemViewCount())
        assertNotNull(bar.findItemView("charge"))
        assertTrue(bar.itemPrimaryText("charge")!!.toString().contains("E42"))
        assertTrue(
            bar.findItemView("charge")!!.contentDescription.toString()
                .contains("fault", ignoreCase = true),
        )
    }

    @Test
    fun restricted_blocksSettingsDeepLink() {
        var opened = 0
        bar.setItems(
            listOf(
                CabinStatusGlyph(
                    id = "wifi",
                    contentDescription = "Wi-Fi",
                    text = "Wi-Fi",
                    deepLink = StatusDeepLink(opensSettings = true) { opened++ },
                ),
            ),
        )
        host.updateState(VehicleUiState.restricted())

        val view = bar.findItemView("wifi")!!
        assertFalse(view.isEnabled)
        view.performClick()
        assertEquals(0, opened)
    }

    @Test
    fun moving_substitutesInformationalDeepLink() {
        var opened = 0
        bar.setItems(
            listOf(
                CabinStatusGlyph(
                    id = "alert",
                    contentDescription = "Alerts",
                    text = "1",
                    deepLink = StatusDeepLink(opensSettings = false) { opened++ },
                ),
            ),
        )
        host.updateState(VehicleUiState.moving())

        val view = bar.findItemView("alert")!!
        assertEquals(View.VISIBLE, view.visibility)
        assertFalse(view.isEnabled)
        view.performClick()
        assertEquals(0, opened)
    }

    @Test
    fun parked_allowsSettingsDeepLink() {
        var opened = 0
        bar.setItems(
            listOf(
                CabinStatusGlyph(
                    id = "wifi",
                    contentDescription = "Wi-Fi settings",
                    text = "Wi-Fi",
                    deepLink = StatusDeepLink(opensSettings = true) { opened++ },
                ),
            ),
        )
        host.updateState(VehicleUiState.parked())

        bar.findItemView("wifi")!!.performClick()
        assertEquals(1, opened)
    }

    @Test
    fun glanceOnly_hasContentDescription_noClickPath() {
        bar.setItems(
            listOf(
                CabinStatusGlyph(
                    id = "clock",
                    contentDescription = "Twelve o'clock",
                    text = "12:00",
                ),
            ),
        )
        val view = bar.findItemView("clock")!!
        assertEquals("Twelve o'clock", view.contentDescription)
        assertFalse(view.isClickable)
    }

    @Test
    fun theme_dayNight_containerAndFeedbackResolve() {
        val day = CabinThemeResolver.resolveColors(themedContext)
        assertTrue(day.container != 0)
        assertTrue(day.onContainer != 0)
        assertTrue(day.outline != 0)
        assertTrue(day.error != 0)
        assertTrue(day.warning != 0)
        assertTrue(day.charging != 0)
    }

    @Test
    fun tokenSizes_matchStub() {
        assertEquals(48f, CabinStatusBarTokens.heightDp)
        assertEquals(24f, CabinStatusBarTokens.iconSizeDp)
        assertEquals(8f, CabinStatusBarTokens.itemGapDp)
    }
}

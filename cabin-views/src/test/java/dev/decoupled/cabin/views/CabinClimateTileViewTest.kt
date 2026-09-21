package dev.decoupled.cabin.views

import android.view.ContextThemeWrapper
import android.view.View
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileState
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinClimateTileViewTest {

    private lateinit var themedContext: android.content.Context
    private lateinit var tile: CabinClimateTileView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        themedContext = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        tile = CabinClimateTileView(themedContext)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        tile.setCompliance(host)
        tile.bind(liveState())
    }

    @Test
    fun parked_tempFanSeatAdjustmentsFire() {
        val actions = mutableListOf<CabinClimateTileAction>()
        tile.setOnActionListener { actions += it }

        tile.findControl("climate_temp_up")!!.performClick()
        tile.findControl("climate_fan_down")!!.performClick()
        tile.findControl("climate_seat_up")!!.performClick()

        assertEquals(
            listOf(
                CabinClimateTileAction.TempUp,
                CabinClimateTileAction.FanDown,
                CabinClimateTileAction.SeatHeatUp,
            ),
            actions,
        )
    }

    @Test
    fun moving_blocksHvacAdjust_failClosed() {
        val actions = mutableListOf<CabinClimateTileAction>()
        tile.setOnActionListener { actions += it }
        host.updateState(VehicleUiState.moving())

        val tempUp = tile.findControl("climate_temp_up")!!
        assertFalse(tempUp.isEnabled)
        tempUp.performClick()
        tile.findControl("climate_fan_up")!!.performClick()
        tile.findControl("climate_seat_down")!!.performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun nullComplianceHost_failClosed() {
        val actions = mutableListOf<CabinClimateTileAction>()
        tile.setOnActionListener { actions += it }
        tile.setCompliance(null)

        tile.findControl("climate_temp_up")!!.performClick()
        assertTrue(actions.isEmpty())
        assertFalse(tile.findControl("climate_temp_up")!!.isEnabled)
    }

    @Test
    fun unavailableSignals_showEmDash_andDoNotActivate() {
        val actions = mutableListOf<CabinClimateTileAction>()
        tile.setOnActionListener { actions += it }
        tile.bind(
            CabinClimateTileState(
                zoneLabel = "Driver",
                temperatureC = Signal.Unavailable,
                fanLevel = Signal.Unavailable,
                seatHeatLevel = Signal.Unavailable,
                powerOn = true,
            ),
        )

        assertEquals("—", (tile.findControl("climate_temp_value") as android.widget.TextView).text.toString())
        assertEquals("—", (tile.findControl("climate_fan_value") as android.widget.TextView).text.toString())
        assertEquals("—", (tile.findControl("climate_seat_value") as android.widget.TextView).text.toString())
        tile.findControl("climate_temp_up")!!.performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun staleSignals_labelHonestly() {
        tile.bind(
            CabinClimateTileState(
                zoneLabel = "Driver",
                temperatureC = Signal.Stale(21, atMillis = 1L),
                fanLevel = Signal.Stale(2, atMillis = 1L),
                seatHeatLevel = Signal.Stale(1, atMillis = 1L),
            ),
        )
        val temp = tile.findControl("climate_temp_value") as android.widget.TextView
        val fan = tile.findControl("climate_fan_value") as android.widget.TextView
        assertEquals("21° · stale", temp.text.toString())
        assertEquals("2/5 · stale", fan.text.toString())
        assertTrue(temp.contentDescription.toString().contains("stale"))
    }

    @Test
    fun moving_valuesStayGlanceable_controlsQuiet() {
        host.updateState(VehicleUiState.moving())
        val tempUp = tile.findControl("climate_temp_up")!!
        val tempValue = tile.findControl("climate_temp_value")!!
        assertFalse(tempUp.isEnabled)
        assertEquals(1f, tempValue.alpha)
        // RE-quiet soft-disable — not the heavy chrome Block wash (0.4).
        assertTrue(tempUp.alpha >= 0.65f)
    }

    @Test
    fun touchMinimum_is76dp() {
        assertEquals(76f, CabinClimateTileTokens.controlMinSizeDp)
        assertTrue(tile.findControl("climate_temp_up")!!.minimumHeight >= dp(76))
    }

    @Test
    fun restricted_blocksAdjustments() {
        val actions = mutableListOf<CabinClimateTileAction>()
        tile.setOnActionListener { actions += it }
        host.updateState(VehicleUiState.restricted())
        tile.findControl("climate_temp_down")!!.performClick()
        assertTrue(actions.isEmpty())
    }

    private fun liveState() = CabinClimateTileState(
        zoneLabel = "Driver",
        temperatureC = Signal.Value(22, atMillis = 1L),
        fanLevel = Signal.Value(3, atMillis = 1L),
        seatHeatLevel = Signal.Value(1, atMillis = 1L),
        powerOn = true,
    )

    private fun dp(value: Int): Int {
        val density = themedContext.resources.displayMetrics.density
        return (value * density).toInt()
    }
}

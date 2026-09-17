package dev.decoupled.cabin.views

import android.view.ContextThemeWrapper
import android.view.View
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
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
class CabinMediaNowPlayingViewTest {

    private lateinit var themedContext: android.content.Context
    private lateinit var view: CabinMediaNowPlayingView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        themedContext = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        view = CabinMediaNowPlayingView(themedContext)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        view.setCompliance(host)
        view.bind(liveState())
    }

    @Test
    fun parked_transportAndSourceFire() {
        val actions = mutableListOf<CabinMediaNowPlayingAction>()
        view.setOnActionListener { actions += it }

        view.findControl("media_play_pause")!!.performClick()
        view.findControl("media_next")!!.performClick()
        view.findControl("media_source")!!.performClick()

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
        view.setOnActionListener { actions += it }
        host.updateState(VehicleUiState.moving())

        view.findControl("media_previous")!!.performClick()
        assertEquals(listOf(CabinMediaNowPlayingAction.Previous), actions)

        view.findControl("media_source")!!.performClick()
        assertEquals(listOf(CabinMediaNowPlayingAction.Previous), actions)
        assertFalse(view.findControl("media_source")!!.isEnabled)
    }

    @Test
    fun nullComplianceHost_failClosed() {
        val actions = mutableListOf<CabinMediaNowPlayingAction>()
        view.setOnActionListener { actions += it }
        view.setCompliance(null)

        view.findControl("media_play_pause")!!.performClick()
        view.findControl("media_source")!!.performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun unavailableProgress_hidesNumbers_noFakeTelemetry() {
        view.bind(
            liveState().copy(
                positionMs = Signal.Unavailable,
                durationMs = Signal.Unavailable,
            ),
        )
        val progress = view.findControl("media_progress")!!
        assertEquals(View.GONE, progress.visibility)
        assertEquals("", (progress as android.widget.TextView).text.toString())
    }

    @Test
    fun liveProgress_rendersHonestValues() {
        view.bind(
            liveState().copy(
                positionMs = Signal.Value(65_000L, atMillis = 1L),
                durationMs = Signal.Value(180_000L, atMillis = 1L),
            ),
        )
        val progress = view.findControl("media_progress") as android.widget.TextView
        assertEquals(View.VISIBLE, progress.visibility)
        assertEquals("1:05 / 3:00", progress.text.toString())
    }

    @Test
    fun artworkUnavailable_stablePlaceholder() {
        view.bind(liveState().copy(artworkAvailable = false))
        val art = view.findControl("media_artwork") as android.widget.TextView
        assertEquals("—", art.text.toString())
        assertEquals("Artwork unavailable", art.contentDescription)
    }

    @Test
    fun touchMinimum_is76dp() {
        assertEquals(76f, CabinMediaNowPlayingTokens.transportMinSizeDp)
        assertTrue(view.findControl("media_play_pause")!!.minimumHeight >= dp(76))
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

    private fun dp(value: Int): Int {
        val density = themedContext.resources.displayMetrics.density
        return (value * density).toInt()
    }
}

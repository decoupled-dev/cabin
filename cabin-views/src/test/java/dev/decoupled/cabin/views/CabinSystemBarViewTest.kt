package dev.decoupled.cabin.views

import android.view.ContextThemeWrapper
import android.view.View
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
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
class CabinSystemBarViewTest {

    private lateinit var themedContext: android.content.Context
    private lateinit var bar: CabinSystemBarView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        themedContext = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        bar = CabinSystemBarView(themedContext)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        bar.setCompliance(host)
    }

    @Test
    fun setSlots_bindsLeadingCenterTrailing() {
        var home = 0
        var hvac = 0
        var apps = 0
        bar.setSlots(
            CabinSystemBarSlots(
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

        assertEquals(3, bar.entryViewCount())
        assertNotNull(bar.findEntryView("home"))
        assertNotNull(bar.findEntryView("hvac"))
        assertNotNull(bar.findEntryView("apps"))

        bar.findEntryView("home")!!.performClick()
        bar.findEntryView("apps")!!.performClick()
        assertEquals(1, home)
        assertEquals(1, apps)
        assertEquals(0, hvac)
    }

    @Test
    fun moving_blocksOpenComplexApp_clickDoesNotFire() {
        var apps = 0
        bar.setSlots(
            CabinSystemBarSlots(
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
        host.updateState(VehicleUiState.moving())

        val view = bar.findEntryView("apps")!!
        assertFalse(view.isEnabled)
        view.performClick()
        assertEquals(0, apps)
    }

    @Test
    fun moving_allowsNavigateSimple() {
        var home = 0
        bar.setSlots(
            CabinSystemBarSlots(
                leading = listOf(
                    CabinSystemBarEntry(
                        id = "home",
                        contentDescription = "Home",
                        interaction = CabinInteraction.NavigateSimple,
                        onActivate = { home++ },
                    ),
                ),
            ),
        )
        host.updateState(VehicleUiState.moving())

        val view = bar.findEntryView("home")!!
        assertTrue(view.isEnabled)
        view.performClick()
        assertEquals(1, home)
    }

    @Test
    fun restricted_blocksOpenKeyboard() {
        var search = 0
        bar.setSlots(
            CabinSystemBarSlots(
                trailing = listOf(
                    CabinSystemBarEntry(
                        id = "search",
                        contentDescription = "Search",
                        interaction = CabinInteraction.OpenKeyboard,
                        onActivate = { search++ },
                    ),
                ),
            ),
        )
        host.updateState(VehicleUiState.restricted())

        bar.findEntryView("search")!!.performClick()
        assertEquals(0, search)
    }

    @Test
    fun idling_substitutesOpenComplexApp_keepsVisibleNonActivatable() {
        var apps = 0
        bar.setSlots(
            CabinSystemBarSlots(
                trailing = listOf(
                    CabinSystemBarEntry(
                        id = "apps",
                        contentDescription = "App grid",
                        interaction = CabinInteraction.OpenComplexApp,
                        onActivate = { apps++ },
                    ),
                ),
            ),
        )
        host.updateState(VehicleUiState.idling())

        val view = bar.findEntryView("apps")!!
        assertEquals(View.VISIBLE, view.visibility)
        assertFalse(view.isEnabled)
        view.performClick()
        assertEquals(0, apps)
    }

    @Test
    fun iconOnlyEntry_setsContentDescription() {
        bar.setSlots(
            CabinSystemBarSlots(
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
        assertEquals("Open map", bar.findEntryView("map")!!.contentDescription)
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
        bar.setSlots(
            CabinSystemBarSlots(
                trailing = listOf(
                    CabinSystemBarEntry(
                        id = "apps",
                        contentDescription = "App grid",
                        interaction = CabinInteraction.OpenComplexApp,
                        onActivate = { apps++ },
                    ),
                ),
            ),
        )
        host.updateState(VehicleUiState.unknown())
        bar.findEntryView("apps")!!.performClick()
        assertEquals(0, apps)
    }
}

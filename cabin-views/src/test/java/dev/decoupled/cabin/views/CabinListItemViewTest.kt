package dev.decoupled.cabin.views

import android.view.ContextThemeWrapper
import android.view.View
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver
import dev.decoupled.cabin.views.theme.CabinThemes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinListItemViewTest {

    private lateinit var themedContext: android.content.Context
    private lateinit var item: CabinListItemView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        themedContext = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        item = CabinListItemView(themedContext)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        item.setCompliance(host)
    }

    @Test
    fun parked_allowsNavigateSimple_clickFires() {
        var clicks = 0
        item.bind(
            CabinListItemState(
                title = "Home",
                supportingText = "Map",
                interaction = CabinInteraction.NavigateSimple,
            ),
            onClick = { clicks++ },
        )

        assertTrue(item.isEnabled)
        item.performClick()
        assertEquals(1, clicks)
        assertEquals("Home, Map", item.contentDescription)
    }

    @Test
    fun moving_blocksOpenComplexApp_clickDoesNotFire() {
        var clicks = 0
        item.bind(
            CabinListItemState(
                title = "Settings",
                interaction = CabinInteraction.OpenComplexApp,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.moving())

        assertFalse(item.isEnabled)
        item.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun restricted_blocksFilterOrSort() {
        var clicks = 0
        item.bind(
            CabinListItemState(
                title = "Sort",
                interaction = CabinInteraction.FilterOrSort,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.restricted())

        item.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun idling_substitutesOpenComplexApp_keepsVisible() {
        var clicks = 0
        item.bind(
            CabinListItemState(
                title = "Apps",
                interaction = CabinInteraction.OpenComplexApp,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.idling())

        assertEquals(View.VISIBLE, item.visibility)
        assertFalse(item.isEnabled)
        item.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun nullComplianceHost_failClosed_blocksActivation() {
        var clicks = 0
        item.setCompliance(null)
        item.bind(
            CabinListItemState(
                title = "Home",
                interaction = CabinInteraction.NavigateSimple,
            ),
            onClick = { clicks++ },
        )

        assertFalse(item.isEnabled)
        item.performClick()
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

    @Test
    fun craft_selectionUsesOutlineContainer_notLockedSafety() {
        val colors = CabinThemeResolver.resolveColors(themedContext)
        assertNotEquals(colors.outline, colors.warning)
        assertNotEquals(colors.outline, colors.error)
        assertNotEquals(colors.container, colors.warning)
        assertNotEquals(colors.container, colors.error)

        item.bind(
            CabinListItemState(
                title = "Selected",
                interaction = CabinInteraction.NavigateSimple,
                selected = true,
            ),
        )
        assertTrue(item.currentState().selected)
    }

    @Test
    fun unknownState_blocksComplexApp_failSafe() {
        var clicks = 0
        item.bind(
            CabinListItemState(
                title = "Pairing",
                interaction = CabinInteraction.OpenComplexApp,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.unknown())

        item.performClick()
        assertEquals(0, clicks)
    }
}

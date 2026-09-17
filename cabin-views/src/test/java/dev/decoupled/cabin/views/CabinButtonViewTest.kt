package dev.decoupled.cabin.views

import android.view.ContextThemeWrapper
import android.view.View
import dev.decoupled.cabin.compliance.CabinInteraction
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
class CabinButtonViewTest {

    private lateinit var themedContext: android.content.Context
    private lateinit var button: CabinButtonView
    private lateinit var iconButton: CabinIconButtonView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        themedContext = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        button = CabinButtonView(themedContext)
        iconButton = CabinIconButtonView(themedContext)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        button.setCompliance(host)
        iconButton.setCompliance(host)
    }

    @Test
    fun parked_allowsNavigateSimple_clickFires() {
        var clicks = 0
        button.bind(
            CabinButtonState(
                label = "Go",
                interaction = CabinInteraction.NavigateSimple,
                variant = CabinButtonVariant.Filled,
            ),
            onClick = { clicks++ },
        )

        assertTrue(button.isEnabled)
        button.performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun moving_blocksOpenComplexApp_clickDoesNotFire() {
        var clicks = 0
        button.bind(
            CabinButtonState(
                label = "Settings",
                interaction = CabinInteraction.OpenComplexApp,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.moving())

        assertFalse(button.isEnabled)
        button.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun idling_substitutesOpenComplexApp_keepsVisible() {
        var clicks = 0
        button.bind(
            CabinButtonState(
                label = "Apps",
                interaction = CabinInteraction.OpenComplexApp,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.idling())

        assertEquals(View.VISIBLE, button.visibility)
        assertFalse(button.isEnabled)
        button.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun nullComplianceHost_failClosed_blocksActivation() {
        var clicks = 0
        button.setCompliance(null)
        button.bind(
            CabinButtonState(
                label = "Go",
                interaction = CabinInteraction.NavigateSimple,
            ),
            onClick = { clicks++ },
        )

        assertFalse(button.isEnabled)
        button.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun productDisabled_blocksEvenWhenGateAllows() {
        var clicks = 0
        button.bind(
            CabinButtonState(
                label = "Go",
                interaction = CabinInteraction.NavigateSimple,
                enabled = false,
            ),
            onClick = { clicks++ },
        )

        assertFalse(button.isEnabled)
        button.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun iconButton_setsContentDescription_andActivatesWhenAllowed() {
        var clicks = 0
        iconButton.bind(
            CabinIconButtonState(
                icon = null,
                contentDescription = "Mute",
                interaction = CabinInteraction.MediaTransport,
            ),
            onClick = { clicks++ },
        )

        assertEquals("Mute", iconButton.contentDescription)
        assertTrue(iconButton.isEnabled)
        iconButton.performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun iconButton_moving_blocksOpenKeyboard() {
        var clicks = 0
        iconButton.bind(
            CabinIconButtonState(
                icon = null,
                contentDescription = "Search",
                interaction = CabinInteraction.OpenKeyboard,
            ),
            onClick = { clicks++ },
        )
        host.updateState(VehicleUiState.moving())

        iconButton.performClick()
        assertEquals(0, clicks)
    }

    @Test
    fun touchMinima_matchTokens() {
        assertEquals(76f, CabinButtonTokens.minHeightDp)
        assertEquals(16f, CabinButtonTokens.horizontalPaddingDp)
        assertEquals(28f, CabinButtonTokens.iconSizeDp)
        assertEquals(8f, CabinButtonTokens.gapDp)
        assertEquals(8f, CabinButtonTokens.cornerRadiusDp)
        assertEquals(76f, CabinIconButtonTokens.minSizeDp)
        assertEquals(28f, CabinIconButtonTokens.iconSizeDp)
    }
}

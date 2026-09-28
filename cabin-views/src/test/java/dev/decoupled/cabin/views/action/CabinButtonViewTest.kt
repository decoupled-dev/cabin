@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.action

import android.graphics.drawable.GradientDrawable
import android.view.ContextThemeWrapper
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.components.action.CabinButtonAction
import dev.decoupled.cabin.foundation.components.action.CabinButtonState
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver
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

    private lateinit var button: CabinButtonView
    private lateinit var host: CabinComplianceHost

    @Before
    fun setUp() {
        val themed = ContextThemeWrapper(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        button = CabinButtonView(themed)
        host = CabinComplianceHost(initialState = VehicleUiState.parked())
        button.setCompliance(host)
        button.bind(CabinButtonState(label = "Continue", variant = "filled"))
    }

    @Test
    fun parked_activateFires_andUsesPrimaryFill() {
        val actions = mutableListOf<CabinButtonAction>()
        button.setOnAction { actions += it }

        assertTrue(button.performClick())
        assertEquals(listOf(CabinButtonAction.Activate), actions)
        val colors = CabinThemeResolver.resolveColors(button.context)
        val fill = (button.background as GradientDrawable).color!!.defaultColor
        assertEquals(colors.primary, fill)
        val minPx = (CabinTokens.Component.Button.minHeight.dp * button.resources.displayMetrics.density).toInt()
        assertTrue(button.minimumHeight >= minPx)
    }

    @Test
    fun moving_navigateSimpleStillActivates() {
        val actions = mutableListOf<CabinButtonAction>()
        button.setOnAction { actions += it }
        host.updateState(VehicleUiState.moving())

        assertTrue(button.performClick())
        assertEquals(listOf(CabinButtonAction.Activate), actions)
    }

    @Test
    fun nullComplianceHost_failClosed() {
        val actions = mutableListOf<CabinButtonAction>()
        button.setOnAction { actions += it }
        button.setCompliance(null)

        assertFalse(button.isEnabled)
        button.performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun disabled_doesNotActivate() {
        val actions = mutableListOf<CabinButtonAction>()
        button.setOnAction { actions += it }
        button.bind(
            CabinButtonState(
                label = "Continue",
                ui = CabinComponentUiState(enabled = false),
            ),
        )

        assertFalse(button.isEnabled)
        button.performClick()
        assertTrue(actions.isEmpty())
    }

    @Test
    fun outlined_isTransparentAndKeepsLabel() {
        button.bind(CabinButtonState(label = "Later", variant = "outlined"))
        val drawable = button.background as GradientDrawable
        assertEquals(0, drawable.color!!.defaultColor)
        assertEquals("Later", button.contentDescription)
    }
}

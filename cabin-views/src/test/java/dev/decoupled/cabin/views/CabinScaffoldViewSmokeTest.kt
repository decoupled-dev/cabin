package dev.decoupled.cabin.views

import android.os.Build
import android.view.View
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.foundation.CabinScaffold
import dev.decoupled.cabin.foundation.components.action.CabinButtonAction
import dev.decoupled.cabin.foundation.components.action.CabinButtonState
import dev.decoupled.cabin.views.action.CabinButtonView
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver
import dev.decoupled.cabin.views.theme.CabinThemes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@OptIn(CabinScaffold::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
class CabinScaffoldViewSmokeTest {

    private fun themedButton(): Pair<CabinButtonView, CabinComplianceHost> {
        val context = CabinThemeResolver.wrap(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        val host = CabinComplianceHost(initialState = VehicleUiState.parked())
        val view = CabinButtonView(context)
        view.setCompliance(host)
        view.bind(CabinButtonState(label = "Go"))
        return view to host
    }

    @Test
    fun parked_activateFires() {
        val (view, _) = themedButton()
        val actions = mutableListOf<CabinButtonAction>()
        view.setOnAction { actions += it }
        val activate = view.findViewWithTag<View>("cabin_button_activate")
        activate.performClick()
        assertEquals(listOf(CabinButtonAction.Activate), actions)
    }

    @Test
    fun moving_openComplexPreferenceBlocks() {
        val context = CabinThemeResolver.wrap(
            RuntimeEnvironment.getApplication(),
            CabinThemes.ThemeCabin,
        )
        val host = CabinComplianceHost(initialState = VehicleUiState.moving())
        val view = dev.decoupled.cabin.views.settings.CabinPreferenceView(context)
        view.setCompliance(host)
        val actions = mutableListOf<dev.decoupled.cabin.foundation.components.settings.CabinPreferenceAction>()
        view.setOnAction { actions += it }
        view.findViewWithTag<View>("cabin_preference_activate").performClick()
        assertTrue(actions.isEmpty())
    }
}

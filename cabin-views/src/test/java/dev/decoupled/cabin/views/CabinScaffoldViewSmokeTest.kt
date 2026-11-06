/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

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

package dev.decoupled.cabin.screens

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.Density
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinLook
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * Renders every reference screen in every look and scheme to PNG under
 * `build/showcase-shots/` for visual review. Asserts only that rendering
 * succeeds; it is a review aid, not a pixel-diff gate.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE], qualifiers = "w1920dp-h720dp-mdpi")
class CabinScreenShotsTest {

    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val outDir = File("build/showcase-shots").apply { mkdirs() }

    private enum class Screen { Home, Media, Climate }

    @Test
    fun renderAllScreensLooksAndSchemes() {
        var look by mutableStateOf(CabinLook.Quiet)
        var scheme by mutableStateOf(CabinColorScheme.Night)
        var screen by mutableStateOf(Screen.Home)
        var vehicle by mutableStateOf(VehicleUiState.moving())
        compose.runOnUiThread { compose.activity.actionBar?.hide() }
        compose.setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f, 1f)) {
                CabinTheme(colorScheme = scheme, look = look, vehicleState = vehicle) {
                    Frame(screen)
                }
            }
        }
        val only = System.getProperty("cabin.shots.only")
        CabinLook.entries.forEach { l ->
            CabinColorScheme.entries.forEach { s ->
                Screen.entries.forEach { sc ->
                    if (only != null && only != "${l.name}-${s.name}") return@forEach
                    look = l;
                    scheme = s;
                    screen = sc
                    save("${sc.name}-${l.name}-${s.name}".lowercase())
                }
            }
        }
        // Parked state shows the full Up next list and the Browse button.
        look = CabinLook.Quiet;
        scheme = CabinColorScheme.Night;
        vehicle = VehicleUiState.parked()
        screen = Screen.Media;
        save("media-quiet-night-parked")
        screen = Screen.Home;
        save("home-quiet-night-parked")
        assertTrue(outDir.listFiles().orEmpty().isNotEmpty())
    }

    private fun save(name: String) {
        compose.waitForIdle()
        val view = compose.activity.window.decorView.rootView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(bitmap)) }
        File(outDir, "$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }

    @Composable
    private fun Frame(screen: Screen) {
        val dest = when (screen) {
            Screen.Home -> CabinDestinations.Home
            Screen.Media -> CabinDestinations.Media
            Screen.Climate -> CabinDestinations.Climate
        }
        CabinScreenFrame(
            status = CabinScreenSamples.status,
            climate = CabinScreenSamples.climate,
            onClimateAction = {},
            dockItems = CabinDestinations.defaultDock(),
            selectedDestination = dest,
            onDestinationSelect = {},
            modifier = Modifier.fillMaxSize(),
        ) {
            when (screen) {
                Screen.Home -> CabinHomeSplitScreen(
                    media = CabinScreenSamples.media,
                    onMediaAction = {},
                    energy = CabinScreenSamples.energy,
                    quickControls = CabinScreenSamples.quickControls,
                    onQuickControlChange = { _, _ -> },
                    nextTurn = CabinScreenSamples.nextTurn,
                )
                Screen.Media -> CabinMediaScreen(
                    state = CabinScreenSamples.media,
                    onAction = {},
                    queue = CabinScreenSamples.queue,
                    onQueueItemSelect = {},
                )
                Screen.Climate -> CabinClimateScreen(CabinScreenSamples.climate, onAction = {})
            }
        }
    }
}

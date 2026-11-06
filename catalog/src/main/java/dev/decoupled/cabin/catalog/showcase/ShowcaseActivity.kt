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

package dev.decoupled.cabin.catalog.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.VehicleUiStateSimulator
import dev.decoupled.cabin.compose.kit.CabinButton
import dev.decoupled.cabin.compose.kit.CabinButtonStyle
import dev.decoupled.cabin.compose.kit.CabinLabel
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingAction
import dev.decoupled.cabin.screens.CabinClimateScreen
import dev.decoupled.cabin.screens.CabinDestinations
import dev.decoupled.cabin.screens.CabinHomeSplitScreen
import dev.decoupled.cabin.screens.CabinMediaScreen
import dev.decoupled.cabin.screens.CabinScreenFrame
import dev.decoupled.cabin.screens.CabinScreenSamples
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinLook
import dev.decoupled.cabin.tokens.CabinLooks

/**
 * Cabin showcase (sample only): Home, Media and Climate reference screens
 * with live controls for look, day/night, and a simulated drive state.
 * Not a product and not a SystemUI dependency.
 */
class ShowcaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ShowcaseApp() }
    }
}

@Composable
fun ShowcaseApp() {
    var lookName by rememberSaveable { mutableStateOf(CabinLooks.Default.name) }
    var schemeName by rememberSaveable { mutableStateOf(CabinColorScheme.Night.name) }
    var destination by rememberSaveable { mutableStateOf(CabinDestinations.Home) }
    val simulator = remember { VehicleUiStateSimulator() }
    var vehicle by remember { mutableStateOf(simulator.jumpTo("Moving")) }
    var climate by remember { mutableStateOf(CabinScreenSamples.climate) }
    var media by remember { mutableStateOf(CabinScreenSamples.media) }
    var quick by remember { mutableStateOf(CabinScreenSamples.quickControls) }

    val look = CabinLook.valueOf(lookName)
    val scheme = CabinColorScheme.valueOf(schemeName)

    CabinTheme(colorScheme = scheme, look = look, fonts = ShowcaseFonts.resolver, vehicleState = vehicle) {
        Box(Modifier.fillMaxSize().testTag("showcase")) {
            CabinScreenFrame(
                status = CabinScreenSamples.status,
                climate = climate,
                onClimateAction = { climate = CabinScreenSamples.reduce(climate, it) },
                dockItems = CabinDestinations.defaultDock(),
                selectedDestination = destination,
                onDestinationSelect = { destination = it.key },
                onFanClick = { destination = CabinDestinations.Climate },
            ) {
                val onMedia: (CabinMediaNowPlayingAction) -> Unit = { action ->
                    if (action == CabinMediaNowPlayingAction.PlayPause) media = media.copy(isPlaying = !media.isPlaying)
                    if (action == CabinMediaNowPlayingAction.OpenSource) destination = CabinDestinations.Media
                }
                when (destination) {
                    CabinDestinations.Media -> CabinMediaScreen(media, onMedia, CabinScreenSamples.queue, onQueueItemSelect = {})
                    CabinDestinations.Climate -> CabinClimateScreen(climate, { climate = CabinScreenSamples.reduce(climate, it) })
                    else -> CabinHomeSplitScreen(
                        media = media,
                        onMediaAction = onMedia,
                        energy = CabinScreenSamples.energy,
                        quickControls = quick,
                        onQuickControlChange = { control, on ->
                            quick = quick.map { if (it.key == control.key) it.copy(on = on) else it }
                        },
                        nextTurn = CabinScreenSamples.nextTurn,
                    )
                }
            }
            ShowcaseControls(
                lookName = look.name,
                schemeName = if (scheme == CabinColorScheme.Night) "Night" else "Day",
                driveName = vehicle.label ?: "Unknown",
                onNextLook = { lookName = CabinLook.entries[(look.ordinal + 1) % CabinLook.entries.size].name },
                onToggleScheme = {
                    schemeName = (if (scheme == CabinColorScheme.Night) CabinColorScheme.Day else CabinColorScheme.Night).name
                },
                onNextDrive = { vehicle = simulator.next() },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
            )
        }
    }
}

/** Demo controls in the empty middle of the status row. Glance-level, always allowed. */
@Composable
private fun ShowcaseControls(
    lookName: String,
    schemeName: String,
    driveName: String,
    onNextLook: () -> Unit,
    onToggleScheme: () -> Unit,
    onNextDrive: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier.height(48.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(
            Triple("Look · $lookName", onNextLook, "showcase_look"),
            Triple(schemeName, onToggleScheme, "showcase_scheme"),
            Triple("Drive · $driveName", onNextDrive, "showcase_drive"),
        ).forEach { (label, onClick, tag) ->
            CabinButton(
                onClick = onClick,
                interaction = CabinInteraction.Glance,
                style = CabinButtonStyle.Tonal,
                modifier = Modifier.testTag(tag),
                contentPadding = PaddingValues(horizontal = 20.dp),
            ) { CabinLabel(label) }
        }
    }
}

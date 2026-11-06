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

package dev.decoupled.cabin.sink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.tokens.CabinColorScheme

/**
 * Kitchen-sink sample — not a product library and not a SystemUI dependency.
 *
 * Hosts a component inspector and composed cabin screens that consume
 * `cabin-compose` / handwritten chrome / `cabin-gauges`.
 */
class KitchenSinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var colorScheme by remember { mutableStateOf(CabinColorScheme.Day) }
            var vehicleState by remember { mutableStateOf(VehicleUiState.parked()) }
            var route by remember { mutableStateOf<SinkRoute>(SinkRoute.Home) }
            var lastAction by remember { mutableStateOf<String?>(null) }

            KitchenSinkApp(
                colorScheme = colorScheme,
                onColorSchemeChange = { colorScheme = it },
                vehicleState = vehicleState,
                onVehicleStateChange = { vehicleState = it },
                route = route,
                onRoute = { route = it },
                lastAction = lastAction,
                onAction = { lastAction = it },
            )
        }
    }
}

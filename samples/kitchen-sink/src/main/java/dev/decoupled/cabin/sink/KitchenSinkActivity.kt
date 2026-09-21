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

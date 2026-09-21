package dev.decoupled.cabin.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.foundation.CabinWindowSizeClass
import dev.decoupled.cabin.tokens.CabinColorScheme

/**
 * Thin sample catalog — not a product library and not a SystemUI dependency.
 *
 * Hosts Compose chrome demos and embeds Views System/Status bars via
 * AndroidView so both stacks share one shell.
 */
class CatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var colorScheme by remember { mutableStateOf(CabinColorScheme.Day) }
            var vehicleState by remember { mutableStateOf(VehicleUiState.parked()) }
            var lastAction by remember { mutableStateOf<String?>(null) }
            var kitFamily by remember { mutableStateOf("action") }
            var sizeClass by remember { mutableStateOf(CabinWindowSizeClass.StandardLandscape) }

            CatalogApp(
                colorScheme = colorScheme,
                onColorSchemeChange = { colorScheme = it },
                vehicleState = vehicleState,
                onVehicleStateChange = { vehicleState = it },
                lastAction = lastAction,
                onAction = { lastAction = it },
                kitFamily = kitFamily,
                onKitFamilyChange = { kitFamily = it },
                sizeClass = sizeClass,
                onSizeClassChange = { sizeClass = it },
            )
        }
    }
}

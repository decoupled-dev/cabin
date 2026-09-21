@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.hvac

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.hvac.*

/**
 * Experimental scaffold: Temperature control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTemperatureControl(
    state: CabinTemperatureControlState = CabinTemperatureControlState(),
    onAction: (CabinTemperatureControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Temperature control" },
        family = "hvac",
        testTag = "cabin_temperature_control",
        interaction = CabinTemperatureControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTemperatureControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Fan speed.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFanSpeed(
    state: CabinFanSpeedState = CabinFanSpeedState(),
    onAction: (CabinFanSpeedAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Fan speed" },
        family = "hvac",
        testTag = "cabin_fan_speed",
        interaction = CabinFanSpeedInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFanSpeedAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Airflow direction.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAirflowDirection(
    state: CabinAirflowDirectionState = CabinAirflowDirectionState(),
    onAction: (CabinAirflowDirectionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Airflow direction" },
        family = "hvac",
        testTag = "cabin_airflow_direction",
        interaction = CabinAirflowDirectionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAirflowDirectionAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Zone selector.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinZoneSelector(
    state: CabinZoneSelectorState = CabinZoneSelectorState(),
    onAction: (CabinZoneSelectorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Zone selector" },
        family = "hvac",
        testTag = "cabin_zone_selector",
        interaction = CabinZoneSelectorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinZoneSelectorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Seat heat / vent.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSeatClimate(
    state: CabinSeatClimateState = CabinSeatClimateState(),
    onAction: (CabinSeatClimateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Seat heat / vent" },
        family = "hvac",
        testTag = "cabin_seat_climate",
        interaction = CabinSeatClimateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSeatClimateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Steering wheel heat.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSteeringWheelHeat(
    state: CabinSteeringWheelHeatState = CabinSteeringWheelHeatState(),
    onAction: (CabinSteeringWheelHeatAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Steering wheel heat" },
        family = "hvac",
        testTag = "cabin_steering_wheel_heat",
        interaction = CabinSteeringWheelHeatInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSteeringWheelHeatAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Mirror heat.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMirrorHeat(
    state: CabinMirrorHeatState = CabinMirrorHeatState(),
    onAction: (CabinMirrorHeatAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Mirror heat" },
        family = "hvac",
        testTag = "cabin_mirror_heat",
        interaction = CabinMirrorHeatInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMirrorHeatAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Defrost.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDefrostToggle(
    state: CabinDefrostToggleState = CabinDefrostToggleState(),
    onAction: (CabinDefrostToggleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Defrost" },
        family = "hvac",
        testTag = "cabin_defrost_toggle",
        interaction = CabinDefrostToggleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDefrostToggleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Recirculation.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinRecircToggle(
    state: CabinRecircToggleState = CabinRecircToggleState(),
    onAction: (CabinRecircToggleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Recirculation" },
        family = "hvac",
        testTag = "cabin_recirc_toggle",
        interaction = CabinRecircToggleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinRecircToggleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Auto / A/C.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAutoAcToggle(
    state: CabinAutoAcToggleState = CabinAutoAcToggleState(),
    onAction: (CabinAutoAcToggleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Auto / A/C" },
        family = "hvac",
        testTag = "cabin_auto_ac_toggle",
        interaction = CabinAutoAcToggleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAutoAcToggleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Eco climate.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEcoToggle(
    state: CabinEcoToggleState = CabinEcoToggleState(),
    onAction: (CabinEcoToggleAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Eco climate" },
        family = "hvac",
        testTag = "cabin_eco_toggle",
        interaction = CabinEcoToggleInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEcoToggleAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Air quality.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAirQualityIndicator(
    state: CabinAirQualityIndicatorState = CabinAirQualityIndicatorState(),
    onAction: (CabinAirQualityIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Air quality" },
        family = "hvac",
        testTag = "cabin_air_quality_indicator",
        interaction = CabinAirQualityIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAirQualityIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Fragrance.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFragranceControl(
    state: CabinFragranceControlState = CabinFragranceControlState(),
    onAction: (CabinFragranceControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Fragrance" },
        family = "hvac",
        testTag = "cabin_fragrance_control",
        interaction = CabinFragranceControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFragranceControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Pre-conditioning scheduler.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPreconditioningScheduler(
    state: CabinPreconditioningSchedulerState = CabinPreconditioningSchedulerState(),
    onAction: (CabinPreconditioningSchedulerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Pre-conditioning scheduler" },
        family = "hvac",
        testTag = "cabin_preconditioning_scheduler",
        interaction = CabinPreconditioningSchedulerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPreconditioningSchedulerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Ambient lighting.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAmbientLighting(
    state: CabinAmbientLightingState = CabinAmbientLightingState(),
    onAction: (CabinAmbientLightingAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Ambient lighting" },
        family = "hvac",
        testTag = "cabin_ambient_lighting",
        interaction = CabinAmbientLightingInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAmbientLightingAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Sunroof / sunshade.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSunroofControl(
    state: CabinSunroofControlState = CabinSunroofControlState(),
    onAction: (CabinSunroofControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Sunroof / sunshade" },
        family = "hvac",
        testTag = "cabin_sunroof_control",
        interaction = CabinSunroofControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSunroofControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: HVAC overlay.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHvacOverlay(
    state: CabinHvacOverlayState = CabinHvacOverlayState(),
    onAction: (CabinHvacOverlayAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "HVAC overlay" },
        family = "hvac",
        testTag = "cabin_hvac_overlay",
        interaction = CabinHvacOverlayInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHvacOverlayAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Persistent HVAC bar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPersistentHvacBar(
    state: CabinPersistentHvacBarState = CabinPersistentHvacBarState(),
    onAction: (CabinPersistentHvacBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Persistent HVAC bar" },
        family = "hvac",
        testTag = "cabin_persistent_hvac_bar",
        interaction = CabinPersistentHvacBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPersistentHvacBarAction.Activate) },
        modifier = modifier,
    )
}

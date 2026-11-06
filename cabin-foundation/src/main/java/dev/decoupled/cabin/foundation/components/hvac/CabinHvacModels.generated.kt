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

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.hvac

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinTemperatureControlState(
    val label: String = "Temperature control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTemperatureControlAction {
    data object Activate : CabinTemperatureControlAction
}

val CabinTemperatureControlInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinFanSpeedState(
    val label: String = "Fan speed",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFanSpeedAction {
    data object Activate : CabinFanSpeedAction
}

val CabinFanSpeedInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinAirflowDirectionState(
    val label: String = "Airflow direction",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAirflowDirectionAction {
    data object Activate : CabinAirflowDirectionAction
}

val CabinAirflowDirectionInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinZoneSelectorState(
    val label: String = "Zone selector",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinZoneSelectorAction {
    data object Activate : CabinZoneSelectorAction
}

val CabinZoneSelectorInteraction: CabinInteraction = CabinInteraction.HvacPeek

@CabinScaffold
data class CabinSeatClimateState(
    val label: String = "Seat heat / vent",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSeatClimateAction {
    data object Activate : CabinSeatClimateAction
}

val CabinSeatClimateInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinSteeringWheelHeatState(
    val label: String = "Steering wheel heat",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSteeringWheelHeatAction {
    data object Activate : CabinSteeringWheelHeatAction
}

val CabinSteeringWheelHeatInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinMirrorHeatState(
    val label: String = "Mirror heat",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMirrorHeatAction {
    data object Activate : CabinMirrorHeatAction
}

val CabinMirrorHeatInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinDefrostToggleState(
    val label: String = "Defrost",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDefrostToggleAction {
    data object Activate : CabinDefrostToggleAction
}

val CabinDefrostToggleInteraction: CabinInteraction = CabinInteraction.HvacPeek

@CabinScaffold
data class CabinRecircToggleState(
    val label: String = "Recirculation",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRecircToggleAction {
    data object Activate : CabinRecircToggleAction
}

val CabinRecircToggleInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinAutoAcToggleState(
    val label: String = "Auto / A/C",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAutoAcToggleAction {
    data object Activate : CabinAutoAcToggleAction
}

val CabinAutoAcToggleInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinEcoToggleState(
    val label: String = "Eco climate",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEcoToggleAction {
    data object Activate : CabinEcoToggleAction
}

val CabinEcoToggleInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinAirQualityIndicatorState(
    val label: String = "Air quality",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAirQualityIndicatorAction {
    data object Activate : CabinAirQualityIndicatorAction
}

val CabinAirQualityIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinFragranceControlState(
    val label: String = "Fragrance",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFragranceControlAction {
    data object Activate : CabinFragranceControlAction
}

val CabinFragranceControlInteraction: CabinInteraction = CabinInteraction.HvacAdjust

@CabinScaffold
data class CabinPreconditioningSchedulerState(
    val label: String = "Pre-conditioning scheduler",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPreconditioningSchedulerAction {
    data object Activate : CabinPreconditioningSchedulerAction
}

val CabinPreconditioningSchedulerInteraction: CabinInteraction = CabinInteraction.ParkedOnly

@CabinScaffold
data class CabinAmbientLightingState(
    val label: String = "Ambient lighting",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAmbientLightingAction {
    data object Activate : CabinAmbientLightingAction
}

val CabinAmbientLightingInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinSunroofControlState(
    val label: String = "Sunroof / sunshade",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSunroofControlAction {
    data object Activate : CabinSunroofControlAction
}

val CabinSunroofControlInteraction: CabinInteraction = CabinInteraction.VehicleAdjust

@CabinScaffold
data class CabinHvacOverlayState(
    val label: String = "HVAC overlay",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHvacOverlayAction {
    data object Activate : CabinHvacOverlayAction
}

val CabinHvacOverlayInteraction: CabinInteraction = CabinInteraction.HvacPeek

@CabinScaffold
data class CabinPersistentHvacBarState(
    val label: String = "Persistent HVAC bar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPersistentHvacBarAction {
    data object Activate : CabinPersistentHvacBarAction
}

val CabinPersistentHvacBarInteraction: CabinInteraction = CabinInteraction.HvacPeek

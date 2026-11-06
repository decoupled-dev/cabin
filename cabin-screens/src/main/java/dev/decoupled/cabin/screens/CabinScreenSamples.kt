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

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.kit.CabinIcons
import dev.decoupled.cabin.compose.kit.CabinStatusIndicator
import dev.decoupled.cabin.foundation.ev.CabinEnergyState
import dev.decoupled.cabin.foundation.hvac.CabinClimateAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateState
import dev.decoupled.cabin.foundation.hvac.CabinClimateZone
import dev.decoupled.cabin.foundation.maps.CabinManeuver
import dev.decoupled.cabin.foundation.maps.CabinNextTurnState
import dev.decoupled.cabin.foundation.media.CabinMediaNowPlayingState

/**
 * Fictional sample content for previews, tests and the showcase. Not
 * vehicle data. All names are invented.
 */
object CabinScreenSamples {
    private const val AT = 0L

    val status: CabinStatusContent = CabinStatusContent(
        timeText = "10:42",
        outsideTemperatureText = "18°",
        indicators = listOf(
            CabinStatusIndicator(CabinIcons.Bluetooth, "Bluetooth connected"),
            CabinStatusIndicator(CabinIcons.Signal, "Signal strong"),
        ),
        profileInitial = "A",
        profileDescription = "Profile Alex",
    )

    val media: CabinMediaNowPlayingState = CabinMediaNowPlayingState(
        title = Signal.Value("Northern Lights", AT),
        artist = Signal.Value("The Quiet Hours", AT),
        sourceLabel = Signal.Value("Phone · Bluetooth", AT),
        isPlaying = true,
        artworkAvailable = false,
        positionMs = Signal.Value(92_000L, AT),
        durationMs = Signal.Value(245_000L, AT),
    )

    val queue: List<CabinQueueItem> = listOf(
        CabinQueueItem("1", "Harbor Road", "The Quiet Hours"),
        CabinQueueItem("2", "Slow Coast", "Mira Vale"),
        CabinQueueItem("3", "Paper Moons", "The Quiet Hours"),
        CabinQueueItem("4", "Long Light", "North Of Here"),
    )

    val nextTurn: CabinNextTurnState = CabinNextTurnState(
        distanceText = "400 m",
        instruction = "Turn right onto Harbor Road",
        maneuver = CabinManeuver.TurnRight,
        etaText = "18 min",
        tripText = "14.2 km · 10:58",
    )

    val energy: CabinEnergyState = CabinEnergyState(
        batteryPercent = Signal.Value(78, AT),
        rangeText = Signal.Value("312 km", AT),
        note = "charges at 22:00",
    )

    val quickControls: List<CabinQuickControl> = listOf(
        CabinQuickControl("lock", "Locked", CabinIcons.Lock, on = true),
        CabinQuickControl(
            "defrost",
            "Defrost",
            CabinIcons.Defrost,
            on = false,
            interaction = CabinInteraction.HvacQuick,
        ),
        CabinQuickControl("lights", "Lights auto", CabinIcons.Lights, on = false),
        CabinQuickControl("mode", "Comfort", CabinIcons.DriveMode, on = false),
    )

    val climate: CabinClimateState = CabinClimateState(
        zones = listOf(
            CabinClimateZone("driver", "Driver", Signal.Value(21.5f, AT), Signal.Value(2, AT)),
            CabinClimateZone("passenger", "Passenger", Signal.Value(22.0f, AT), Signal.Value(0, AT)),
        ),
        fanLevel = Signal.Value(3, AT),
        fanAuto = false,
        acOn = true,
        recirculationOn = false,
        frontDefrostOn = false,
        rearDefrostOn = true,
        zonesSynced = false,
    )

    /**
     * Reference reducer for [CabinClimateAction] on sample state. Real hosts
     * write vehicle properties and observe the result instead.
     */
    fun reduce(state: CabinClimateState, action: CabinClimateAction): CabinClimateState = when (action) {
        is CabinClimateAction.TemperatureStep -> state.copy(
            zones = state.zones.map { zone ->
                val targets = if (state.zonesSynced) state.zones.map { it.id } else listOf(action.zoneId)
                if (zone.id !in targets) return@map zone
                val current = (zone.temperatureC as? Signal.Value)?.value ?: return@map zone
                val next = (current + action.steps * state.temperatureStepC)
                    .coerceIn(state.temperatureMinC, state.temperatureMaxC)
                zone.copy(temperatureC = Signal.Value(next, AT))
            },
        )
        is CabinClimateAction.SeatHeatCycle -> state.copy(
            zones = state.zones.map { zone ->
                if (zone.id != action.zoneId) return@map zone
                val level = (zone.seatHeatLevel as? Signal.Value)?.value ?: 0
                zone.copy(seatHeatLevel = Signal.Value((level + 1) % (zone.seatHeatMax + 1), AT))
            },
        )
        is CabinClimateAction.SetFanLevel -> state.copy(fanAuto = false, fanLevel = Signal.Value(action.level, AT))
        CabinClimateAction.SetFanAuto -> state.copy(fanAuto = true)
        CabinClimateAction.ToggleAc -> state.copy(acOn = !state.acOn)
        CabinClimateAction.ToggleRecirculation -> state.copy(recirculationOn = !state.recirculationOn)
        CabinClimateAction.ToggleFrontDefrost -> state.copy(frontDefrostOn = !state.frontDefrostOn)
        CabinClimateAction.ToggleRearDefrost -> state.copy(rearDefrostOn = !state.rearDefrostOn)
        CabinClimateAction.ToggleSync -> state.copy(zonesSynced = !state.zonesSynced)
    }
}

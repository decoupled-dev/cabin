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

package dev.decoupled.cabin.foundation.maps

/**
 * Turn-by-turn summary shown over the map. Strings arrive formatted and
 * localized by the navigation provider; Cabin doesn't compute routes.
 */
data class CabinNextTurnState(
    val distanceText: String,
    val instruction: String,
    val maneuver: CabinManeuver = CabinManeuver.TurnRight,
    val etaText: String? = null,
    val tripText: String? = null,
)

enum class CabinManeuver { Straight, TurnLeft, TurnRight, Arrive }

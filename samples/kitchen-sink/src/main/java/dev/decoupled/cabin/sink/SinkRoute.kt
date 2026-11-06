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

sealed class SinkRoute {
    data object Home : SinkRoute()
    data object Components : SinkRoute()
    data class Inspector(val id: String) : SinkRoute()
    data object Screens : SinkRoute()
    data class Screen(val id: String) : SinkRoute()
}

data class SinkScreenSpec(
    val id: String,
    val title: String,
    val summary: String,
)

object SinkScreensCatalog {
    val all: List<SinkScreenSpec> = listOf(
        SinkScreenSpec("dashboard", "Dashboard", "Chrome + now playing + climate + charge"),
        SinkScreenSpec("media", "Now playing", "MediaNowPlaying with queue and mini player"),
        SinkScreenSpec("climate", "Climate", "ClimateTile plus HVAC controls"),
        SinkScreenSpec("charge", "Charge", "Range, session, and charge limit"),
        SinkScreenSpec("settings", "Settings", "Preference rows in a two-pane frame"),
        SinkScreenSpec("vehicle", "Vehicle", "Doors, windows, locks"),
    )
}

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

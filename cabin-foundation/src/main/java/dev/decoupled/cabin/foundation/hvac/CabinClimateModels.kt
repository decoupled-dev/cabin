package dev.decoupled.cabin.foundation.hvac

import dev.decoupled.cabin.compliance.Signal

/**
 * Shared ClimateTile presentation state (Compose + Views parity).
 *
 * Spec: docs/components/specs/climate-tile.md
 */
data class CabinClimateTileState(
    val zoneLabel: String,
    val temperatureC: Signal<Int>,
    val fanLevel: Signal<Int>,
    val fanMax: Int = 5,
    val seatHeatLevel: Signal<Int>,
    val seatHeatMax: Int = 3,
    val powerOn: Boolean = true,
) {
    companion object {
        fun empty(): CabinClimateTileState = CabinClimateTileState(
            zoneLabel = "",
            temperatureC = Signal.Unavailable,
            fanLevel = Signal.Unavailable,
            seatHeatLevel = Signal.Unavailable,
            powerOn = false,
        )
    }
}

/** ClimateTile actions — all gated as HvacAdjust. */
sealed interface CabinClimateTileAction {
    data object TempUp : CabinClimateTileAction
    data object TempDown : CabinClimateTileAction
    data object FanUp : CabinClimateTileAction
    data object FanDown : CabinClimateTileAction
    data object SeatHeatUp : CabinClimateTileAction
    data object SeatHeatDown : CabinClimateTileAction
}

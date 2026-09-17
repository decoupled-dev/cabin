package dev.decoupled.cabin.compose

import dev.decoupled.cabin.compliance.Signal

/** Honest a11y description for vehicle Signals — never invent values. */
internal fun <T> signalDescription(signal: Signal<T>, display: String): String = when (signal) {
    is Signal.Value -> display
    is Signal.Stale -> "$display (stale)"
    Signal.Unavailable -> "Unavailable"
    is Signal.Fault -> "Fault ${signal.code}"
}

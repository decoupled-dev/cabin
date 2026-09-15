package dev.decoupled.cabin.compliance

/**
 * Vehicle / system signal with exhaustive degradation states.
 *
 * Components must render all branches — never invent values when unavailable,
 * stale, or faulted (see docs/compliance/safety-critical.md).
 *
 * No Compose or Views dependencies (ADR 0004).
 */
sealed interface Signal<out T> {
    /** Live value observed at [atMillis] (epoch millis). */
    data class Value<T>(val value: T, val atMillis: Long) : Signal<T>

    /** Never received / explicitly absent — do not invent a value. */
    data object Unavailable : Signal<Nothing>

    /**
     * Last known [last] is past TTL; may show frozen value only when labeled
     * stale in the UI.
     */
    data class Stale<T>(val last: T, val atMillis: Long) : Signal<T>

    /** Transport or domain fault; [code] is for diagnostics / a11y. */
    data class Fault(val code: String) : Signal<Nothing>
}

package dev.decoupled.cabin.compliance

import org.junit.Assert.assertEquals
import org.junit.Test

class SignalTest {

    @Test
    fun exhaustiveBranches_coverSafetyCriticalStates() {
        val samples: List<Signal<String>> = listOf(
            Signal.Value("80%", atMillis = 1L),
            Signal.Unavailable,
            Signal.Stale(last = "79%", atMillis = 1L),
            Signal.Fault(code = "E1"),
        )
        val kinds = samples.map { signal ->
            when (signal) {
                is Signal.Value -> "value:${signal.value}"
                is Signal.Unavailable -> "unavailable"
                is Signal.Stale -> "stale:${signal.last}"
                is Signal.Fault -> "fault:${signal.code}"
            }
        }
        assertEquals(
            listOf("value:80%", "unavailable", "stale:79%", "fault:E1"),
            kinds,
        )
        assertEquals("E1", (samples.last() as Signal.Fault).code)
    }
}

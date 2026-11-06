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

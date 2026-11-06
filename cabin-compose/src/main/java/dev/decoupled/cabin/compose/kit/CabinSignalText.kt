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

package dev.decoupled.cabin.compose.kit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.R

/**
 * Display text plus spoken description for a vehicle [Signal]. Never invents
 * a value: unavailable and faulted signals show a dash.
 */
@Immutable
data class CabinSignalText(val text: String, val description: String, val live: Boolean)

/** Format [signal] with [format]; stale values are shown but announced as not updated. */
@Composable
fun <T> cabinSignalText(signal: Signal<T>, format: (T) -> String): CabinSignalText {
    val dash = stringResource(R.string.cabin_kit_value_unavailable)
    val unavailable = stringResource(R.string.cabin_kit_value_unavailable_a11y)
    return when (signal) {
        is Signal.Value -> format(signal.value).let { CabinSignalText(it, it, live = true) }
        is Signal.Stale -> format(signal.last).let {
            CabinSignalText(it, stringResource(R.string.cabin_kit_value_stale_a11y, it), live = false)
        }
        Signal.Unavailable, is Signal.Fault -> CabinSignalText(dash, unavailable, live = false)
    }
}

/** Value of [this] when live or stale, else null. */
fun <T> Signal<T>.valueOrNull(): T? = when (this) {
    is Signal.Value -> value
    is Signal.Stale -> last
    else -> null
}

/** m:ss for media positions. */
fun formatMediaTime(ms: Long): String {
    val total = (ms / 1000).coerceAtLeast(0)
    return "%d:%02d".format(total / 60, total % 60)
}

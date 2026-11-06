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

package dev.decoupled.cabin.foundation.ev

import dev.decoupled.cabin.compliance.Signal

/**
 * Battery / energy summary for the Home energy card.
 *
 * @property rangeText range already formatted in the host's units ("312 km")
 * @property note optional secondary line ("charges at 22:00")
 */
data class CabinEnergyState(
    val batteryPercent: Signal<Int>,
    val rangeText: Signal<String>,
    val note: String? = null,
    val charging: Boolean = false,
    /** At or below this level the bar uses the locked warning color. */
    val lowThresholdPercent: Int = 15,
)

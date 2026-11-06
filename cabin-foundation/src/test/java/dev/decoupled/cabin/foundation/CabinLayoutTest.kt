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

package dev.decoupled.cabin.foundation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinLayoutTest {

    @Test
    fun sizeClasses_coverAutomotiveProfiles() {
        assertEquals(8, CabinWindowSizeClass.entries.size)
        assertTrue(CabinWindowSizeClass.entries.contains(CabinWindowSizeClass.Ultrawide))
        assertTrue(CabinWindowSizeClass.entries.contains(CabinWindowSizeClass.Cluster))
    }

    @Test
    fun componentUiState_effectivelyEnabled() {
        assertTrue(CabinComponentUiState().effectivelyEnabled)
        assertFalse(CabinComponentUiState(restricted = true).effectivelyEnabled)
        assertFalse(CabinComponentUiState(disabled = true).effectivelyEnabled)
        assertFalse(CabinComponentUiState(loading = true).effectivelyEnabled)
    }

    @Test
    fun displayProfile_defaults() {
        assertEquals(CabinWindowSizeClass.StandardLandscape, CabinDisplayProfile.StandardCenter.sizeClass)
        assertEquals(CabinOccupantZone.RearLeft, CabinDisplayProfile.RearSeat.occupantZone)
    }
}

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

package dev.decoupled.cabin.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinLooksTest {

    @Test
    fun defaultIsQuiet() {
        assertEquals(CabinLook.Quiet, CabinLooks.Default)
    }

    @Test
    fun everyLookHasASpecInEnumOrder() {
        assertEquals(CabinLook.entries.toList(), CabinLooks.all.map { it.look })
        CabinLook.entries.forEach { assertEquals(it, CabinLooks.spec(it).look) }
    }

    @Test
    fun schemeSelectsNightOrDay() {
        val quiet = CabinLooks.spec(CabinLook.Quiet)
        assertEquals(quiet.night, quiet.scheme(CabinColorScheme.Night))
        assertEquals(quiet.day, quiet.scheme(CabinColorScheme.Day))
        assertEquals(0xFF121110.toInt(), quiet.night.background)
        assertEquals(0xFFCDB894.toInt(), quiet.night.primary)
    }

    @Test
    fun onlyGlassHasBackdropGlowAndTranslucentSurfaces() {
        CabinLooks.all.forEach { spec ->
            if (spec.look == CabinLook.Glass) {
                assertNotNull(spec.night.backdropGlowStart)
                assertTrue("glass surface is translucent", alpha(spec.night.surface) < 255)
            } else {
                assertNull(spec.night.backdropGlowStart)
                assertEquals(255, alpha(spec.night.surface))
            }
        }
    }

    @Test
    fun pillControls() {
        assertTrue(CabinLooks.spec(CabinLook.Warm).controlIsPill)
        assertTrue(!CabinLooks.spec(CabinLook.Precision).controlIsPill)
    }

    @Test
    fun safetyNightValuesAreLocked() {
        assertEquals(0xFFFFB020.toInt(), CabinLooks.Safety.night.warning)
        assertEquals(0xFFFF5A4E.toInt(), CabinLooks.Safety.night.error)
        assertEquals(0xFF3DDC84.toInt(), CabinLooks.Safety.night.charging)
    }

    private fun alpha(argb: Int): Int = (argb ushr 24) and 0xFF
}

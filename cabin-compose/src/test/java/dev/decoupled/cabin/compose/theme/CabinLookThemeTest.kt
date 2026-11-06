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

package dev.decoupled.cabin.compose.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinLook
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinLookThemeTest {

    private val schemes = CabinColorScheme.entries

    @Test
    fun safetyColorsAreIdenticalAcrossLooks() {
        schemes.forEach { scheme ->
            val reference = cabinLookTheme(CabinLook.Quiet, scheme).colors
            CabinLook.entries.forEach { look ->
                val c = cabinLookTheme(look, scheme).colors
                assertEquals("$look/$scheme warning", reference.warning, c.warning)
                assertEquals("$look/$scheme error", reference.error, c.error)
                assertEquals("$look/$scheme charging", reference.charging, c.charging)
            }
        }
    }

    @Test
    fun brandOverridesPrimaryButNeverSafety() {
        val brand = CabinBrandOverrides(primary = Color(0xFFFF0000), onPrimary = Color.White)
        val base = cabinLookTheme(CabinLook.Quiet, CabinColorScheme.Night).colors
        val branded = cabinLookTheme(CabinLook.Quiet, CabinColorScheme.Night, brand).colors
        assertEquals(Color(0xFFFF0000), branded.primary)
        assertEquals(base.warning, branded.warning)
        assertEquals(base.error, branded.error)
    }

    @Test
    fun noTextStyleBelow24sp() {
        CabinLook.entries.forEach { look ->
            val t = cabinLookTheme(look, CabinColorScheme.Night).type
            listOf(
                t.numeralHero,
                t.numeralLarge,
                t.numeralMedium,
                t.numeralSmall,
                t.title,
                t.titleSmall,
                t.body,
                t.label,
            )
                .forEach { assertTrue("$look ${it.fontSize}", it.fontSize.value >= 24f) }
            val m = cabinLookTheme(look, CabinColorScheme.Night).toMaterialTypography()
            listOf(m.bodySmall, m.labelSmall, m.titleSmall).forEach { assertTrue(it.fontSize.value >= 24f) }
        }
    }

    @Test
    fun underlineLookUsesIndicatorOthersUseTonalContainer() {
        val precision = cabinLookTheme(CabinLook.Precision, CabinColorScheme.Night).colors
        assertEquals(precision.primary, precision.activeIndicator)
        assertEquals(precision.surfaceHigh, precision.activeContainer)
        val quiet = cabinLookTheme(CabinLook.Quiet, CabinColorScheme.Night).colors
        assertEquals(Color.Unspecified, quiet.activeIndicator)
        assertEquals(quiet.primaryContainer, quiet.activeContainer)
    }

    @Test
    fun materialSchemeFollowsLook() {
        val look = cabinLookTheme(CabinLook.Warm, CabinColorScheme.Day)
        val m = look.toMaterialColorScheme()
        assertEquals(look.colors.primary, m.primary)
        assertEquals(look.colors.background, m.background)
        assertEquals(look.colors.error, m.error)
        assertNotEquals(
            cabinLookTheme(CabinLook.Mono, CabinColorScheme.Day).toMaterialColorScheme().primary,
            m.primary,
        )
    }

    @Test
    fun labelCasingFollowsLook() {
        assertEquals("RANGE", cabinLookTheme(CabinLook.Precision, CabinColorScheme.Night).labelText("Range"))
        assertEquals("Range", cabinLookTheme(CabinLook.Quiet, CabinColorScheme.Night).labelText("Range"))
    }

    @Test
    fun platformFontsFallBackByName() {
        assertEquals(FontFamily.Monospace, CabinFonts.Platform.resolve("IBM Plex Mono"))
        assertEquals(FontFamily.Serif, CabinFonts.Platform.resolve("Instrument Serif"))
        assertEquals(FontFamily.SansSerif, CabinFonts.Platform.resolve("Instrument Sans"))
    }
}

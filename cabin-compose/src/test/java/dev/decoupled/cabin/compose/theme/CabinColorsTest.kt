package dev.decoupled.cabin.compose.theme

import androidx.compose.ui.graphics.Color
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CabinColorsTest {

    @Test
    fun dayScheme_resolvesOutlineContainerAndFeedback() {
        val colors = resolveCabinColors(CabinColorScheme.Day)
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)

        assertEquals(Color(day.surface.argb), colors.surface)
        assertEquals(Color(day.onSurface.argb), colors.onSurface)
        assertEquals(Color(day.outline.argb), colors.outline)
        assertEquals(Color(day.container.argb), colors.container)
        assertEquals(Color(day.onContainer.argb), colors.onContainer)
        assertEquals(Color(day.warning.argb), colors.warning)
        assertEquals(Color(day.error.argb), colors.error)
        assertEquals(Color(day.charging.argb), colors.charging)
    }

    @Test
    fun nightScheme_resolvesOutlineContainerAndLockedFeedback() {
        val colors = resolveCabinColors(CabinColorScheme.Night)
        val night = CabinTokens.colorScheme(CabinColorScheme.Night)
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)

        assertEquals(Color(night.outline.argb), colors.outline)
        assertEquals(Color(night.container.argb), colors.container)
        assertEquals(Color(night.onContainer.argb), colors.onContainer)
        assertEquals(Color(night.warning.argb), colors.warning)
        assertEquals(Color(night.error.argb), colors.error)
        assertEquals(Color(night.charging.argb), colors.charging)

        // Locked night feedback stays high-chroma vs day (no soft-wash).
        assertNotEquals(Color(day.warning.argb), colors.warning)
        assertNotEquals(Color(day.error.argb), colors.error)
        assertNotEquals(Color(day.charging.argb), colors.charging)
    }

    @Test
    fun brandOverride_remapsPrimaryWithoutTouchingSafetyFeedback() {
        val baseline = resolveCabinColors(CabinColorScheme.Day)
        val oem = resolveCabinColors(
            CabinColorScheme.Day,
            CabinBrandOverrides(
                primary = Color(0xFF1565C0),
                onPrimary = Color.White,
            ),
        )

        assertEquals(Color(0xFF1565C0), oem.primary)
        assertEquals(Color.White, oem.onPrimary)
        assertNotEquals(baseline.primary, oem.primary)
        assertEquals(baseline.warning, oem.warning)
        assertEquals(baseline.error, oem.error)
        assertEquals(baseline.charging, oem.charging)
        assertEquals(baseline.outline, oem.outline)
        assertEquals(baseline.container, oem.container)
    }

    @Test
    fun bodyTextRoles_areNotDomainAccents() {
        for (scheme in CabinColorScheme.entries) {
            val colors = resolveCabinColors(scheme)
            val accents = setOf(colors.charging, colors.climate, colors.mediaAccent)
            assertFalse(accents.contains(colors.onSurface))
            assertFalse(accents.contains(colors.onContainer))
        }
    }

    @Test
    fun semanticBrandRoles_matchTokens() {
        val colors = resolveCabinColors(CabinColorScheme.Day)
        assertEquals(Color(CabinTokens.Color.Semantic.primary.argb), colors.primary)
        assertEquals(Color(CabinTokens.Color.Semantic.success.argb), colors.success)
        assertEquals(Color(CabinTokens.Color.Semantic.climate.argb), colors.climate)
    }
}

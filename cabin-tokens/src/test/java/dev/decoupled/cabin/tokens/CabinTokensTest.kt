package dev.decoupled.cabin.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinTokensTest {

    @Test
    fun sourceMetadata_matchesStub() {
        assertEquals("0.1.1-stub", CabinTokens.SOURCE_VERSION)
        assertEquals("tokens/cabin.tokens.json", CabinTokens.SOURCE_PATH)
    }

    @Test
    fun safetyLocks_preserveWarningAndError() {
        assertTrue(CabinTokens.safetyLockedSemanticColors.contains("warning"))
        assertTrue(CabinTokens.safetyLockedSemanticColors.contains("error"))
        assertTrue(CabinTokens.Color.Semantic.warning.isSafetyLocked)
        assertTrue(CabinTokens.Color.Semantic.error.isSafetyLocked)
        assertEquals(CabinColor.LOCK_SAFETY_ADJACENT, CabinTokens.Color.Semantic.warning.lock)
    }

    @Test
    fun dayNightSchemes_resolveChromeAndFeedbackRoles() {
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)
        val night = CabinTokens.colorScheme(CabinColorScheme.Night)

        // Before (v0.1.0): only surface / onSurface flipped.
        // After: outline + container (+ surfaceVariant, onContainer) and feedback.
        assertEquals("#F5F5F5", day.surface.hex)
        assertEquals("#121212", day.onSurface.hex)
        assertEquals("#E8E8E8", day.surfaceVariant.hex)
        assertEquals("#757575", day.outline.hex)
        assertEquals("#FFFFFF", day.container.hex)
        assertEquals("#121212", day.onContainer.hex)
        assertEquals("#F9A825", day.warning.hex)
        assertEquals("#D32F2F", day.error.hex)
        assertEquals("#00897B", day.charging.hex)

        assertEquals("#121212", night.surface.hex)
        assertEquals("#F2F2F2", night.onSurface.hex)
        assertEquals("#1E1E1E", night.surfaceVariant.hex)
        assertEquals("#8A8A8A", night.outline.hex)
        assertEquals("#1A1A1A", night.container.hex)
        assertEquals("#F2F2F2", night.onContainer.hex)

        assertEquals("cabin_color_scheme_outline", day.outline.resourceName)
        assertEquals("cabin_color_scheme_container", night.container.resourceName)
        assertEquals("cabin_color_scheme_surface", day.surface.resourceName)
        assertEquals("cabin_color_scheme_surface", night.surface.resourceName)
    }

    @Test
    fun nightScheme_locksWarningErrorChargingContrast() {
        val night = CabinTokens.colorScheme(CabinColorScheme.Night)
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)

        assertEquals(
            setOf("warning", "error", "charging"),
            CabinTokens.nightContrastLockedSchemeColors,
        )
        assertTrue(night.warning.isSafetyLocked)
        assertTrue(night.error.isSafetyLocked)
        assertTrue(night.charging.isSafetyLocked)

        // Locked night values stay high-chroma — not soft-washed toward day neutrals.
        assertEquals("#FFB300", night.warning.hex)
        assertEquals("#FF5252", night.error.hex)
        assertEquals("#1DE9B6", night.charging.hex)
        assertFalse(night.warning.hex == day.warning.hex)
        assertFalse(night.error.hex == day.error.hex)
        assertFalse(night.charging.hex == day.charging.hex)
    }

    @Test
    fun bodyTextRoles_areNotDomainAccents() {
        val domainAccents = setOf(
            CabinTokens.Color.Semantic.charging.hex,
            CabinTokens.Color.Semantic.climate.hex,
            CabinTokens.Color.Semantic.mediaAccent.hex,
            CabinTokens.colorScheme(CabinColorScheme.Day).charging.hex,
            CabinTokens.colorScheme(CabinColorScheme.Night).charging.hex,
        )
        for (scheme in CabinColorScheme.entries) {
            val colors = CabinTokens.colorScheme(scheme)
            assertFalse(
                "onSurface must stay neutral for $scheme",
                domainAccents.contains(colors.onSurface.hex),
            )
            assertFalse(
                "onContainer must stay neutral for $scheme",
                domainAccents.contains(colors.onContainer.hex),
            )
        }
    }

    @Test
    fun semanticPrimary_andTouchMinimum() {
        assertEquals("#0B6E4F", CabinTokens.Color.Semantic.primary.hex)
        assertEquals(76f, CabinTokens.Size.Touch.minimum.dp)
        assertEquals(80f, CabinTokens.Component.SystemBar.height.dp)
        assertEquals(48f, CabinTokens.Component.StatusBar.height.dp)
    }

    @Test
    fun componentTokens_resolveRefs() {
        assertEquals(
            CabinTokens.Size.Icon.systemBar.dp,
            CabinTokens.Component.SystemBar.iconSize.dp,
        )
        assertEquals(
            CabinTokens.Size.Touch.minimum.dp,
            CabinTokens.Component.SystemBar.itemMinSize.dp,
        )
        assertEquals(
            CabinTokens.Space.sm.dp,
            CabinTokens.Component.SystemBar.gap.dp,
        )
    }
}

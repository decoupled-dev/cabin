package dev.decoupled.cabin.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinTokensTest {

    @Test
    fun sourceMetadata_matchesStub() {
        assertEquals("0.1.0-stub", CabinTokens.SOURCE_VERSION)
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
    fun dayNightSchemes_resolveDistinctSurfaces() {
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)
        val night = CabinTokens.colorScheme(CabinColorScheme.Night)
        assertEquals("#F5F5F5", day.surface.hex)
        assertEquals("#121212", day.onSurface.hex)
        assertEquals("#121212", night.surface.hex)
        assertEquals("#F2F2F2", night.onSurface.hex)
        assertEquals("cabin_color_scheme_surface", day.surface.resourceName)
        assertEquals("cabin_color_scheme_surface", night.surface.resourceName)
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

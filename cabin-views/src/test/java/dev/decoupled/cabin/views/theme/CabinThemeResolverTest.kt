package dev.decoupled.cabin.views.theme

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.view.ContextThemeWrapper
import androidx.annotation.ColorInt
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CabinThemeResolverTest {

    private fun themedContext(night: Boolean = false): Context {
        val base = RuntimeEnvironment.getApplication()
        val config = Configuration(base.resources.configuration)
        config.uiMode = (
            config.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()
            ) or if (night) {
            Configuration.UI_MODE_NIGHT_YES
        } else {
            Configuration.UI_MODE_NIGHT_NO
        }
        val configured = base.createConfigurationContext(config)
        return ContextThemeWrapper(configured, CabinThemes.ThemeCabin)
    }

    private fun assertArgbMatchesHex(@ColorInt argb: Int, hex: String) {
        val expected = Color.parseColor(hex)
        assertEquals(
            "expected $hex but was #${Integer.toHexString(argb)}",
            expected,
            argb,
        )
    }

    @Test
    fun dayTheme_resolvesSchemeChromeRoles() {
        val colors = CabinThemeResolver.resolveColors(themedContext(night = false))
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)

        assertArgbMatchesHex(colors.surface, day.surface.hex)
        assertArgbMatchesHex(colors.onSurface, day.onSurface.hex)
        assertArgbMatchesHex(colors.surfaceVariant, day.surfaceVariant.hex)
        assertArgbMatchesHex(colors.outline, day.outline.hex)
        assertArgbMatchesHex(colors.container, day.container.hex)
        assertArgbMatchesHex(colors.onContainer, day.onContainer.hex)
        assertArgbMatchesHex(colors.warning, day.warning.hex)
        assertArgbMatchesHex(colors.error, day.error.hex)
        assertArgbMatchesHex(colors.charging, day.charging.hex)
        assertEquals(CabinColorScheme.Day, CabinThemeResolver.colorSchemeOf(themedContext(false)))
    }

    @Test
    @Config(qualifiers = "night")
    fun nightTheme_resolvesOutlineContainerAndLockedFeedback() {
        val colors = CabinThemeResolver.resolveColors(themedContext(night = true))
        val night = CabinTokens.colorScheme(CabinColorScheme.Night)

        assertArgbMatchesHex(colors.surface, night.surface.hex)
        assertArgbMatchesHex(colors.onSurface, night.onSurface.hex)
        assertArgbMatchesHex(colors.outline, night.outline.hex)
        assertArgbMatchesHex(colors.container, night.container.hex)
        assertArgbMatchesHex(colors.onContainer, night.onContainer.hex)
        assertArgbMatchesHex(colors.warning, night.warning.hex)
        assertArgbMatchesHex(colors.error, night.error.hex)
        assertArgbMatchesHex(colors.charging, night.charging.hex)

        // Locked night feedback stays high-chroma vs day (no soft-wash).
        val day = CabinTokens.colorScheme(CabinColorScheme.Day)
        assertNotEquals(Color.parseColor(day.warning.hex), colors.warning)
        assertNotEquals(Color.parseColor(day.error.hex), colors.error)
        assertNotEquals(Color.parseColor(day.charging.hex), colors.charging)
    }

    @Test
    fun semanticBrandRoles_resolveFromTokens() {
        val colors = CabinThemeResolver.resolveColors(themedContext())
        assertArgbMatchesHex(colors.primary, CabinTokens.Color.Semantic.primary.hex)
        assertArgbMatchesHex(colors.onPrimary, CabinTokens.Color.Semantic.onPrimary.hex)
        assertArgbMatchesHex(colors.success, CabinTokens.Color.Semantic.success.hex)
        assertArgbMatchesHex(colors.climate, CabinTokens.Color.Semantic.climate.hex)
        assertArgbMatchesHex(colors.mediaAccent, CabinTokens.Color.Semantic.mediaAccent.hex)
    }

    @Test
    fun bodyTextRoles_areNotDomainAccents() {
        for (night in listOf(false, true)) {
            val colors = CabinThemeResolver.resolveColors(themedContext(night))
            val accents = setOf(colors.charging, colors.climate, colors.mediaAccent)
            assertFalse(accents.contains(colors.onSurface))
            assertFalse(accents.contains(colors.onContainer))
        }
    }

    @Test
    fun oemBrandOverlay_remapsPrimaryWithoutTouchingSafetyFeedback() {
        val baseline = themedContext()
        val overlaid = ContextThemeWrapper(
            baseline,
            CabinThemes.ThemeOverlayCabinOemBrandDemo,
        )

        val baseColors = CabinThemeResolver.resolveColors(baseline)
        val oemColors = CabinThemeResolver.resolveColors(overlaid)

        assertArgbMatchesHex(oemColors.primary, "#1565C0")
        assertArgbMatchesHex(oemColors.onPrimary, "#FFFFFF")
        assertNotEquals(baseColors.primary, oemColors.primary)

        // Safety / contrast-locked feedback unchanged by brand overlay.
        assertEquals(baseColors.warning, oemColors.warning)
        assertEquals(baseColors.error, oemColors.error)
        assertEquals(baseColors.charging, oemColors.charging)
        assertEquals(baseColors.outline, oemColors.outline)
        assertEquals(baseColors.container, oemColors.container)
    }

    @Test
    fun wrap_appliesThemeCabin() {
        val raw = RuntimeEnvironment.getApplication()
        val wrapped = CabinThemeResolver.wrap(raw, CabinThemes.ThemeCabin)
        val colors = CabinThemeResolver.resolveColors(wrapped)
        assertTrue(colors.primary != 0)
        assertArgbMatchesHex(colors.primary, CabinTokens.Color.Semantic.primary.hex)
    }
}

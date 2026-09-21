package dev.decoupled.cabin.catalog

import android.os.Build
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.views.theme.CabinThemeResolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
class CatalogDemoDataTest {

    @Test
    fun composeFixtures_coverSignalBranchesAndTones() {
        val items = CatalogDemoData.composeStatusItems { }
        val signals = items.mapNotNull { (it as? dev.decoupled.cabin.compose.CabinStatusGlyph)?.signal }
        assertTrue(signals.any { it is Signal.Value })
        assertTrue(signals.any { it is Signal.Unavailable })
        assertTrue(signals.any { it is Signal.Stale<*> })
        assertTrue(signals.any { it is Signal.Fault })

        val glyphs = items.filterIsInstance<dev.decoupled.cabin.compose.CabinStatusGlyph>()
        assertTrue(glyphs.any { it.emphasis == dev.decoupled.cabin.compose.CabinStatusEmphasis.Warning })
        assertTrue(glyphs.any { it.emphasis == dev.decoupled.cabin.compose.CabinStatusEmphasis.Charging })
    }

    @Test
    fun viewsFixtures_mirrorComposeSignalCoverage() {
        val context = themedCatalogContext(RuntimeEnvironment.getApplication(), night = false)
        val items = CatalogDemoData.viewsStatusItems(context) { }
        assertEquals(6, items.size)
        val signals = items.mapNotNull { (it as? dev.decoupled.cabin.views.CabinStatusGlyph)?.signal }
        assertTrue(signals.any { it is Signal.Unavailable })
        assertTrue(signals.any { it is Signal.Fault })
    }

    @Test
    fun systemBarSlots_declareRestrictionAwareInteractions() {
        val slots = CatalogDemoData.composeSystemBarSlots { }
        val interactions = (slots.leading + slots.center + slots.trailing).map { it.interaction }
        assertTrue(interactions.contains(CabinInteraction.NavigateSimple))
        assertTrue(interactions.contains(CabinInteraction.OpenComplexApp))
        assertTrue(interactions.contains(CabinInteraction.MediaTransport))
    }

    @Test
    fun restrictionFixtures_mapToExpectedModes() {
        val engine = CabinRestrictionEngine.Default
        assertEquals(
            dev.decoupled.cabin.compliance.CabinUiMode.Parked,
            engine.uiMode(VehicleUiState.parked()),
        )
        assertEquals(
            dev.decoupled.cabin.compliance.CabinUiMode.Idling,
            engine.uiMode(VehicleUiState.idling()),
        )
        assertEquals(
            dev.decoupled.cabin.compliance.CabinUiMode.Moving,
            engine.uiMode(VehicleUiState.moving()),
        )
        assertEquals(
            dev.decoupled.cabin.compliance.CabinUiMode.Restricted,
            engine.uiMode(VehicleUiState.restricted()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.OpenComplexApp, VehicleUiState.moving()),
        )
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.NavigateSimple, VehicleUiState.moving()),
        )
    }

    @Test
    fun themedCatalogContext_flipsSchemeOutlineDayNight() {
        val app = RuntimeEnvironment.getApplication()
        val day = themedCatalogContext(app, night = false)
        val night = themedCatalogContext(app, night = true)
        val dayColors = CabinThemeResolver.resolveColors(day)
        val nightColors = CabinThemeResolver.resolveColors(night)
        assertEquals(CabinColorScheme.Day, CabinThemeResolver.colorSchemeOf(day))
        assertEquals(CabinColorScheme.Night, CabinThemeResolver.colorSchemeOf(night))
        assertNotEquals(dayColors.container, nightColors.container)
        assertNotEquals(dayColors.outline, nightColors.outline)
        // Locked feedback must remain distinct (night contrast path).
        assertNotEquals(0, nightColors.warning)
        assertNotEquals(0, nightColors.charging)
    }

    @Test
    fun registry_coversFamiliesAndViewsSubset() {
        assertTrue(CatalogRegistry.entries.size > 100)
        assertTrue(CatalogRegistry.families.contains("action"))
        assertTrue(CatalogRegistry.families.contains("gauges"))
        assertTrue(CatalogRegistry.entries.any { it.id == "button" && it.stacks.contains("views") })
        assertTrue(CatalogRegistry.entries.any { it.id == "system-bar" && it.handwritten })
        assertTrue(CatalogRegistry.entries.none { it.id == "speedometer" && it.stacks.contains("views") })
    }

    @Test
    fun catalogActivity_launches() {
        val controller = Robolectric.buildActivity(CatalogActivity::class.java).setup()
        assertTrue(controller.get() != null)
        controller.pause().stop().destroy()
    }
}

/**
 * Packaging guard: product libraries must not depend on the catalog sample.
 */
class CatalogPackagingTest {
    @Test
    fun productModules_doNotListCatalogDependency() {
        val roots = listOf(
            "cabin-tokens/build.gradle.kts",
            "cabin-compliance/build.gradle.kts",
            "cabin-foundation/build.gradle.kts",
            "cabin-views/build.gradle.kts",
            "cabin-compose/build.gradle.kts",
            "cabin-gauges/build.gradle.kts",
        )
        roots.forEach { relative ->
            val file = resolveRepoFile(relative)
            val text = file.readText()
            assertFalse(
                "$relative must not depend on :catalog",
                text.contains("project(\":catalog\")") || text.contains("project(':catalog')"),
            )
        }
    }

    private fun resolveRepoFile(relative: String): File {
        val candidates = listOf(
            File(relative),
            File("..", relative),
            File(System.getProperty("user.dir") ?: ".", relative),
            File(System.getProperty("user.dir") ?: ".", "../$relative"),
        )
        return candidates.firstOrNull { it.isFile }
            ?: error("Could not find $relative from cwd=${System.getProperty("user.dir")}")
    }
}

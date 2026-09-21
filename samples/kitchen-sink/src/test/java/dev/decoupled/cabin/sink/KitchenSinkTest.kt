package dev.decoupled.cabin.sink

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import dev.decoupled.cabin.foundation.CabinScaffold
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File

@OptIn(CabinScaffold::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
class KitchenSinkTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<KitchenSinkActivity>()

    @Test
    fun activity_launches() {
        composeRule.onNodeWithTag("sink_home").assertIsDisplayed()
    }

    @Test
    fun home_opensComponentsAndScreens() {
        composeRule.onNodeWithTag("sink_home").assertIsDisplayed()
        composeRule.onNodeWithTag("sink_open_components").assertIsDisplayed()
        composeRule.onNodeWithTag("sink_open_screens").assertIsDisplayed()

        composeRule.onNodeWithTag("sink_open_components").performClick()
        composeRule.onNodeWithTag("sink_browser").assertIsDisplayed()

        composeRule.onNodeWithTag("sink_back").performClick()
        composeRule.onNodeWithTag("sink_open_screens").performClick()
        composeRule.onNodeWithTag("sink_screens").assertIsDisplayed()
    }

    @Test
    fun inspector_showsButtonScaffold() {
        composeRule.onNodeWithTag("sink_open_components").performClick()
        composeRule.onNodeWithTag("entry_button").performScrollTo().performClick()
        composeRule.onNodeWithTag("sink_inspector").assertIsDisplayed()
        composeRule.onNodeWithTag("cabin_button").assertIsDisplayed()
        composeRule.onNodeWithTag("cabin_button_mark").assertIsDisplayed()
    }

    @Test
    fun dashboard_composesChromeAndTiles() {
        composeRule.onNodeWithTag("sink_open_screens").performClick()
        composeRule.onNodeWithTag("screen_dashboard").performClick()
        composeRule.onNodeWithTag("sink_screen_dashboard").assertIsDisplayed()
        composeRule.onNodeWithTag("cabin_system_bar").assertIsDisplayed()
        composeRule.onNodeWithTag("cabin_climate_tile").assertIsDisplayed()
    }

    @Test
    fun registry_coversInventory() {
        assertTrue(SinkRegistry.entries.size > 100)
        assertTrue(SinkRegistry.entries.any { it.id == "button" })
        assertTrue(SinkRegistry.entries.any { it.id == "climate-tile" && it.handwritten })
    }
}

class KitchenSinkPackagingTest {
    @Test
    fun productModules_doNotListKitchenSink() {
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
                "$relative must not depend on kitchen-sink",
                text.contains("kitchen-sink"),
            )
        }
    }

    private fun resolveRepoFile(relative: String): File {
        val candidates = listOf(
            File(relative),
            File("..", relative),
            File(File("..", ".."), relative),
            File(System.getProperty("user.dir") ?: ".", relative),
            File(System.getProperty("user.dir") ?: ".", "../$relative"),
            File(System.getProperty("user.dir") ?: ".", "../../$relative"),
        )
        return candidates.firstOrNull { it.isFile }
            ?: error("Could not find $relative from cwd=${System.getProperty("user.dir")}")
    }
}

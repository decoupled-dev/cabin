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

package dev.decoupled.cabin.catalog.showcase

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE], qualifiers = "w1920dp-h720dp-mdpi")
class ShowcaseActivityTest {

    @get:Rule
    val compose = createAndroidComposeRule<ShowcaseActivity>()

    @Test
    fun showcaseCyclesLooksSchemesAndDriveStates() {
        compose.onNodeWithTag("showcase").assertIsDisplayed()
        compose.onNodeWithText("Look · Quiet", ignoreCase = true).assertIsDisplayed()
        compose.onNodeWithText("Drive · Moving", ignoreCase = true).assertIsDisplayed()
        shot("showcase-home-quiet-night")

        compose.onNodeWithTag("showcase_look").performClick()
        compose.onNodeWithText("Look · Precision", ignoreCase = true).assertIsDisplayed()
        compose.onNodeWithTag("showcase_scheme").performClick()
        compose.onNodeWithText("Day", ignoreCase = true).assertIsDisplayed()
        shot("showcase-home-precision-day")

        compose.onNodeWithContentDescription("Media").performClick()
        compose.onNodeWithText("Library opens when parked", ignoreCase = true).assertIsDisplayed()
        compose.onNodeWithTag("showcase_drive").performClick() // Moving -> Restricted
        compose.onNodeWithTag("showcase_drive").performClick() // -> Parked
        compose.onNodeWithText("Drive · Parked", ignoreCase = true).assertIsDisplayed()
        compose.onNodeWithText("Up next", ignoreCase = true).assertIsDisplayed()
        shot("showcase-media-precision-day-parked")
    }

    private fun shot(name: String) {
        compose.waitForIdle()
        val view = compose.activity.window.decorView.rootView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(bitmap)) }
        val dir = File("build/showcase-shots").apply { mkdirs() }
        File(dir, "$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }
}

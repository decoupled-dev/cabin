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

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.font.FontFamily

/**
 * Maps a look's font family names ([dev.decoupled.cabin.tokens.CabinLookSpec.uiFontFamily] and friends)
 * to Compose [FontFamily] values.
 *
 * Cabin libraries ship no font files and never download fonts. Hosts that
 * bundle or install the look fonts pass their own resolver; otherwise
 * [CabinFonts.Platform] falls back to the platform's generic families.
 */
@Stable
fun interface CabinFontResolver {
    fun resolve(familyName: String): FontFamily
}

object CabinFonts {
    /**
     * Platform families only: names containing "Mono" map to
     * [FontFamily.Monospace], "Serif" (but not "Sans") to [FontFamily.Serif],
     * everything else to [FontFamily.SansSerif].
     */
    val Platform: CabinFontResolver = CabinFontResolver { name ->
        when {
            name.contains("Mono", ignoreCase = true) -> FontFamily.Monospace
            name.contains("Serif", ignoreCase = true) &&
                !name.contains("Sans", ignoreCase = true) -> FontFamily.Serif
            else -> FontFamily.SansSerif
        }
    }

    /** Resolver backed by a name → family map, falling back to [Platform]. */
    fun of(families: Map<String, FontFamily>): CabinFontResolver = CabinFontResolver { name ->
        families[name] ?: Platform.resolve(name)
    }
}

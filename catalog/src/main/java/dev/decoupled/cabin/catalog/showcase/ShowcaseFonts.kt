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

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import dev.decoupled.cabin.catalog.R
import dev.decoupled.cabin.compose.theme.CabinFontResolver
import dev.decoupled.cabin.compose.theme.CabinFonts

/**
 * Maps the looks' font names to the OFL fonts bundled in this sample (see
 * `catalog/fonts/README.md`). Libraries ship no fonts; this is how a host
 * provides them.
 */
@OptIn(ExperimentalTextApi::class)
object ShowcaseFonts {
    private val weights = listOf(300, 400, 500, 600, 700)

    private fun variable(resId: Int): FontFamily = FontFamily(
        weights.map { w ->
            Font(resId, FontWeight(w), variationSettings = FontVariation.Settings(FontVariation.weight(w)))
        },
    )

    val resolver: CabinFontResolver by lazy {
        CabinFonts.of(
            mapOf(
                "Manrope" to variable(R.font.manrope),
                "IBM Plex Sans" to variable(R.font.ibm_plex_sans),
                "IBM Plex Mono" to FontFamily(
                    Font(R.font.ibm_plex_mono_regular, FontWeight.Normal),
                    Font(R.font.ibm_plex_mono_medium, FontWeight.Medium),
                ),
                "Plus Jakarta Sans" to variable(R.font.plus_jakarta_sans),
                "Figtree" to variable(R.font.figtree),
                "Space Grotesk" to variable(R.font.space_grotesk),
                "Instrument Sans" to variable(R.font.instrument_sans),
                "Instrument Serif" to FontFamily(Font(R.font.instrument_serif, FontWeight.Normal)),
            ),
        )
    }
}

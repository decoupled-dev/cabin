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

package dev.decoupled.cabin.compose.layout

import dev.decoupled.cabin.foundation.CabinAdaptiveScaffoldKind
import dev.decoupled.cabin.foundation.CabinWindowSizeClass
import org.junit.Assert.assertEquals
import org.junit.Test

class CabinLayoutKindTest {

    @Test
    fun compactAndCluster_useSinglePane() {
        assertEquals(
            CabinAdaptiveScaffoldKind.SinglePane,
            cabinScaffoldKindFor(CabinWindowSizeClass.Cluster),
        )
        assertEquals(
            CabinAdaptiveScaffoldKind.SinglePane,
            cabinScaffoldKindFor(CabinWindowSizeClass.CompactCenter),
        )
    }

    @Test
    fun ultrawide_usesDashboardGrid() {
        assertEquals(
            CabinAdaptiveScaffoldKind.DashboardGrid,
            cabinScaffoldKindFor(CabinWindowSizeClass.Ultrawide),
        )
    }

    @Test
    fun portrait_usesStacked() {
        assertEquals(
            CabinAdaptiveScaffoldKind.PortraitStacked,
            cabinScaffoldKindFor(CabinWindowSizeClass.TallPortrait),
        )
    }
}

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

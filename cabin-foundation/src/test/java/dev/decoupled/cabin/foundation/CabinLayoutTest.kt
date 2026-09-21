package dev.decoupled.cabin.foundation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinLayoutTest {

    @Test
    fun sizeClasses_coverAutomotiveProfiles() {
        assertEquals(8, CabinWindowSizeClass.entries.size)
        assertTrue(CabinWindowSizeClass.entries.contains(CabinWindowSizeClass.Ultrawide))
        assertTrue(CabinWindowSizeClass.entries.contains(CabinWindowSizeClass.Cluster))
    }

    @Test
    fun componentUiState_effectivelyEnabled() {
        assertTrue(CabinComponentUiState().effectivelyEnabled)
        assertFalse(CabinComponentUiState(restricted = true).effectivelyEnabled)
        assertFalse(CabinComponentUiState(disabled = true).effectivelyEnabled)
        assertFalse(CabinComponentUiState(loading = true).effectivelyEnabled)
    }

    @Test
    fun displayProfile_defaults() {
        assertEquals(CabinWindowSizeClass.StandardLandscape, CabinDisplayProfile.StandardCenter.sizeClass)
        assertEquals(CabinOccupantZone.RearLeft, CabinDisplayProfile.RearSeat.occupantZone)
    }
}

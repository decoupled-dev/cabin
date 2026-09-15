package dev.decoupled.cabin.views.compliance

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.VehicleUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CabinComplianceHostTest {

    @Test
    fun updateState_notifiesAndReevaluatesDisposition() {
        val host = CabinComplianceHost(initialState = VehicleUiState.parked())
        var notifications = 0
        host.addOnChangeListener { notifications++ }

        assertTrue(host.allows(CabinInteraction.OpenComplexApp))
        host.updateState(VehicleUiState.moving())
        assertEquals(1, notifications)
        assertEquals(
            GateDisposition.Block,
            host.disposition(CabinInteraction.OpenComplexApp),
        )
        assertFalse(host.allows(CabinInteraction.OpenComplexApp))
    }

    @Test
    fun attach_swapsPolicy() {
        val host = CabinComplianceHost(initialState = VehicleUiState.parked())
        var notifications = 0
        host.addOnChangeListener { notifications++ }
        host.attach(host.policy)
        assertEquals(1, notifications)
        assertEquals(76, host.touchTargetMinDp())
    }
}

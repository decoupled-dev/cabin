package dev.decoupled.cabin.compose.compliance

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.VehicleUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class CabinComplianceStateTest {

    @Test
    fun absent_failClosed_blocksAllActivatingInteractions() {
        val absent = CabinComplianceState.Absent
        for (interaction in CabinInteraction.entries) {
            assertEquals(GateDisposition.Block, absent.disposition(interaction))
            assertFalse(absent.allows(interaction))
        }
    }

    @Test
    fun dispositionOf_null_isFailClosed() {
        assertEquals(
            GateDisposition.Block,
            dispositionOf(null, CabinInteraction.OpenComplexApp),
        )
    }

    @Test
    fun parked_allowsOpenComplexApp() {
        val state = CabinComplianceState(vehicleState = VehicleUiState.parked())
        assertEquals(
            GateDisposition.Allow,
            state.disposition(CabinInteraction.OpenComplexApp),
        )
    }

    @Test
    fun moving_blocksOpenComplexApp_allowsNavigateSimple() {
        val state = CabinComplianceState(vehicleState = VehicleUiState.moving())
        assertEquals(
            GateDisposition.Block,
            state.disposition(CabinInteraction.OpenComplexApp),
        )
        assertEquals(
            GateDisposition.Allow,
            state.disposition(CabinInteraction.NavigateSimple),
        )
    }
}

package dev.decoupled.cabin.compliance

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Restriction Engine fixtures from docs/compliance/restriction-states.md and
 * docs/testing.md.
 */
class CabinRestrictionEngineTest {

    private val engine = CabinRestrictionEngine()

    @Test
    fun moving_openComplexApp_isBlock() {
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.OpenComplexApp, VehicleUiState.moving()),
        )
        assertFalse(engine.allows(CabinInteraction.OpenComplexApp, VehicleUiState.moving()))
    }

    @Test
    fun restricted_openKeyboard_isBlock() {
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.OpenKeyboard, VehicleUiState.restricted()),
        )
    }

    @Test
    fun moving_navigateSimple_isAllow() {
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.NavigateSimple, VehicleUiState.moving()),
        )
        assertTrue(engine.allows(CabinInteraction.NavigateSimple, VehicleUiState.moving()))
    }

    @Test
    fun restricted_statusSettingsDeepLink_isBlock() {
        assertEquals(
            GateDisposition.Block,
            engine.disposition(
                CabinInteraction.StatusDeepLinkSettings,
                VehicleUiState.restricted(),
            ),
        )
    }

    @Test
    fun unknown_mode_usesRestrictedMatrix_forComplexEntry() {
        assertEquals(CabinUiMode.Unknown, engine.uiMode(VehicleUiState.unknown()))
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.OpenComplexApp, VehicleUiState.unknown()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.OpenKeyboard, VehicleUiState.unknown()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(
                CabinInteraction.StatusDeepLinkSettings,
                VehicleUiState.unknown(),
            ),
        )
    }

    @Test
    fun uiMode_fixtures() {
        assertEquals(CabinUiMode.Parked, engine.uiMode(VehicleUiState.parked()))
        assertEquals(CabinUiMode.Idling, engine.uiMode(VehicleUiState.idling()))
        assertEquals(CabinUiMode.Moving, engine.uiMode(VehicleUiState.moving()))
        assertEquals(CabinUiMode.Restricted, engine.uiMode(VehicleUiState.restricted()))
        assertEquals(CabinUiMode.Unknown, engine.uiMode(VehicleUiState.unknown()))
    }

    @Test
    fun parked_allowsComplexAndKeyboard() {
        val parked = VehicleUiState.parked()
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.OpenComplexApp, parked),
        )
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.OpenKeyboard, parked),
        )
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.FilterOrSort, parked),
        )
    }

    @Test
    fun idling_substitutesComplexApp_blocksKeyboard() {
        val idling = VehicleUiState.idling()
        assertEquals(
            GateDisposition.Substitute,
            engine.disposition(CabinInteraction.OpenComplexApp, idling),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.OpenKeyboard, idling),
        )
        assertEquals(
            GateDisposition.Substitute,
            engine.disposition(CabinInteraction.StatusDeepLinkSettings, idling),
        )
    }

    @Test
    fun statusInformational_substitutesWhenMoving() {
        assertEquals(
            GateDisposition.Substitute,
            engine.disposition(
                CabinInteraction.StatusDeepLinkInformational,
                VehicleUiState.moving(),
            ),
        )
    }

    @Test
    fun glanceAndTransportAlwaysAllow() {
        for (state in listOf(
            VehicleUiState.parked(),
            VehicleUiState.idling(),
            VehicleUiState.moving(),
            VehicleUiState.restricted(),
            VehicleUiState.unknown(),
        )) {
            assertEquals(
                GateDisposition.Allow,
                engine.disposition(CabinInteraction.Glance, state),
            )
            assertEquals(
                GateDisposition.Allow,
                engine.disposition(CabinInteraction.MediaTransport, state),
            )
            assertEquals(
                GateDisposition.Allow,
                engine.disposition(CabinInteraction.HvacPeek, state),
            )
        }
    }

    @Test
    fun touchTargetMinDp_fromTokens() {
        assertEquals(76, engine.touchTargetMinDp(VehicleUiState.parked()))
    }
}

@RunWith(Parameterized::class)
class SystemBarMatrixTest(
    private val interaction: CabinInteraction,
    private val state: VehicleUiState,
    private val expected: GateDisposition,
) {
    private val engine = CabinRestrictionEngine()

    @Test
    fun dispositionMatchesMatrix() {
        assertEquals(expected, engine.disposition(interaction, state))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0} @ {1} -> {2}")
        fun data(): Collection<Array<Any>> {
            val parked = VehicleUiState.parked()
            val idling = VehicleUiState.idling()
            val moving = VehicleUiState.moving()
            val restricted = VehicleUiState.restricted()
            return listOf(
                arrayOf(CabinInteraction.NavigateSimple, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.NavigateSimple, idling, GateDisposition.Allow),
                arrayOf(CabinInteraction.NavigateSimple, moving, GateDisposition.Allow),
                arrayOf(CabinInteraction.NavigateSimple, restricted, GateDisposition.Allow),
                arrayOf(CabinInteraction.OpenComplexApp, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.OpenComplexApp, idling, GateDisposition.Substitute),
                arrayOf(CabinInteraction.OpenComplexApp, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenComplexApp, restricted, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.OpenKeyboard, idling, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, restricted, GateDisposition.Block),
                arrayOf(CabinInteraction.FilterOrSort, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.FilterOrSort, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.MediaTransport, restricted, GateDisposition.Allow),
                arrayOf(CabinInteraction.HvacPeek, restricted, GateDisposition.Allow),
            )
        }
    }
}

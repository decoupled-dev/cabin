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
    fun idling_substitutesComplexApp_blocksKeyboardAndFilterOrSort() {
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
            GateDisposition.Block,
            engine.disposition(CabinInteraction.FilterOrSort, idling),
        )
        assertEquals(
            GateDisposition.Substitute,
            engine.disposition(CabinInteraction.StatusDeepLinkSettings, idling),
        )
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.StatusDeepLinkInformational, idling),
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
    fun hvacAdjust_blocksWhileMovingRestrictedUnknown() {
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.HvacAdjust, VehicleUiState.parked()),
        )
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.HvacAdjust, VehicleUiState.idling()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.HvacAdjust, VehicleUiState.moving()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.HvacAdjust, VehicleUiState.restricted()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.HvacAdjust, VehicleUiState.unknown()),
        )
    }

    @Test
    fun mediaComplex_blocksWhileMoving_substitutesIdling() {
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.MediaComplex, VehicleUiState.parked()),
        )
        assertEquals(
            GateDisposition.Substitute,
            engine.disposition(CabinInteraction.MediaComplex, VehicleUiState.idling()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.MediaComplex, VehicleUiState.moving()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.MediaComplex, VehicleUiState.restricted()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.MediaComplex, VehicleUiState.unknown()),
        )
    }

    @Test
    fun parkedOnly_blocksUnlessParked() {
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.ParkedOnly, VehicleUiState.parked()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.ParkedOnly, VehicleUiState.moving()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.ParkedOnly, VehicleUiState.unknown()),
        )
    }

    @Test
    fun vehicleAdjust_matchesHvacAdjustMatrix() {
        assertEquals(
            GateDisposition.Allow,
            engine.disposition(CabinInteraction.VehicleAdjust, VehicleUiState.parked()),
        )
        assertEquals(
            GateDisposition.Block,
            engine.disposition(CabinInteraction.VehicleAdjust, VehicleUiState.moving()),
        )
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
                // NavigateSimple — A for all modes (incl. Idling)
                arrayOf(CabinInteraction.NavigateSimple, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.NavigateSimple, idling, GateDisposition.Allow),
                arrayOf(CabinInteraction.NavigateSimple, moving, GateDisposition.Allow),
                arrayOf(CabinInteraction.NavigateSimple, restricted, GateDisposition.Allow),
                // MediaTransport — A for all modes (incl. Idling)
                arrayOf(CabinInteraction.MediaTransport, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.MediaTransport, idling, GateDisposition.Allow),
                arrayOf(CabinInteraction.MediaTransport, moving, GateDisposition.Allow),
                arrayOf(CabinInteraction.MediaTransport, restricted, GateDisposition.Allow),
                // HvacPeek — A for all modes (Restricted baseline Allow for limited peek)
                arrayOf(CabinInteraction.HvacPeek, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.HvacPeek, idling, GateDisposition.Allow),
                arrayOf(CabinInteraction.HvacPeek, moving, GateDisposition.Allow),
                arrayOf(CabinInteraction.HvacPeek, restricted, GateDisposition.Allow),
                // HvacAdjust (ClimateTile) — A / A / B / B
                arrayOf(CabinInteraction.HvacAdjust, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.HvacAdjust, idling, GateDisposition.Allow),
                arrayOf(CabinInteraction.HvacAdjust, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.HvacAdjust, restricted, GateDisposition.Block),
                // MediaComplex — A / S / B / B
                arrayOf(CabinInteraction.MediaComplex, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.MediaComplex, idling, GateDisposition.Substitute),
                arrayOf(CabinInteraction.MediaComplex, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.MediaComplex, restricted, GateDisposition.Block),
                // OpenComplexApp — A / S / B / B
                arrayOf(CabinInteraction.OpenComplexApp, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.OpenComplexApp, idling, GateDisposition.Substitute),
                arrayOf(CabinInteraction.OpenComplexApp, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenComplexApp, restricted, GateDisposition.Block),
                // OpenKeyboard — A / B / B / B
                arrayOf(CabinInteraction.OpenKeyboard, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.OpenKeyboard, idling, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, restricted, GateDisposition.Block),
                // FilterOrSort — A / B / B / B (Idling + Moving + Restricted → Block)
                arrayOf(CabinInteraction.FilterOrSort, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.FilterOrSort, idling, GateDisposition.Block),
                arrayOf(CabinInteraction.FilterOrSort, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.FilterOrSort, restricted, GateDisposition.Block),
            )
        }
    }
}

@RunWith(Parameterized::class)
class StatusBarMatrixTest(
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
                // Glance — A for all modes (incl. Idling)
                arrayOf(CabinInteraction.Glance, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.Glance, idling, GateDisposition.Allow),
                arrayOf(CabinInteraction.Glance, moving, GateDisposition.Allow),
                arrayOf(CabinInteraction.Glance, restricted, GateDisposition.Allow),
                // StatusDeepLink → informational — A / A / S / S
                arrayOf(
                    CabinInteraction.StatusDeepLinkInformational,
                    parked,
                    GateDisposition.Allow,
                ),
                arrayOf(
                    CabinInteraction.StatusDeepLinkInformational,
                    idling,
                    GateDisposition.Allow,
                ),
                arrayOf(
                    CabinInteraction.StatusDeepLinkInformational,
                    moving,
                    GateDisposition.Substitute,
                ),
                arrayOf(
                    CabinInteraction.StatusDeepLinkInformational,
                    restricted,
                    GateDisposition.Substitute,
                ),
                // StatusDeepLink → settings / setup — A / S / B / B
                arrayOf(
                    CabinInteraction.StatusDeepLinkSettings,
                    parked,
                    GateDisposition.Allow,
                ),
                arrayOf(
                    CabinInteraction.StatusDeepLinkSettings,
                    idling,
                    GateDisposition.Substitute,
                ),
                arrayOf(
                    CabinInteraction.StatusDeepLinkSettings,
                    moving,
                    GateDisposition.Block,
                ),
                arrayOf(
                    CabinInteraction.StatusDeepLinkSettings,
                    restricted,
                    GateDisposition.Block,
                ),
                // OpenKeyboard from status — A / B / B / B
                arrayOf(CabinInteraction.OpenKeyboard, parked, GateDisposition.Allow),
                arrayOf(CabinInteraction.OpenKeyboard, idling, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, moving, GateDisposition.Block),
                arrayOf(CabinInteraction.OpenKeyboard, restricted, GateDisposition.Block),
            )
        }
    }
}

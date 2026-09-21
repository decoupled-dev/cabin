@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.gauges

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinRadialGaugeState(
    val label: String = "Radial gauge",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinRadialGaugeAction {
    data object Activate : CabinRadialGaugeAction
}

val CabinRadialGaugeInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinLinearGaugeState(
    val label: String = "Linear gauge",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLinearGaugeAction {
    data object Activate : CabinLinearGaugeAction
}

val CabinLinearGaugeInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinArcGaugeState(
    val label: String = "Arc gauge",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinArcGaugeAction {
    data object Activate : CabinArcGaugeAction
}

val CabinArcGaugeInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSegmentedGaugeState(
    val label: String = "Segmented gauge",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSegmentedGaugeAction {
    data object Activate : CabinSegmentedGaugeAction
}

val CabinSegmentedGaugeInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSpeedometerState(
    val label: String = "Speedometer",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSpeedometerAction {
    data object Activate : CabinSpeedometerAction
}

val CabinSpeedometerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTachometerState(
    val label: String = "Tachometer",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTachometerAction {
    data object Activate : CabinTachometerAction
}

val CabinTachometerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinPowerMeterState(
    val label: String = "Power meter",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPowerMeterAction {
    data object Activate : CabinPowerMeterAction
}

val CabinPowerMeterInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinGForceState(
    val label: String = "G-force display",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinGForceAction {
    data object Activate : CabinGForceAction
}

val CabinGForceInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinNeedleState(
    val label: String = "Needle primitive",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNeedleAction {
    data object Activate : CabinNeedleAction
}

val CabinNeedleInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTickScaleState(
    val label: String = "Tick / scale primitive",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTickScaleAction {
    data object Activate : CabinTickScaleAction
}

val CabinTickScaleInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinGearIndicatorState(
    val label: String = "Gear indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinGearIndicatorAction {
    data object Activate : CabinGearIndicatorAction
}

val CabinGearIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDigitalSpeedState(
    val label: String = "Digital speed",
    val variant: String = "",
    val reading: Signal<Int> = Signal.Unavailable,
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDigitalSpeedAction {
    data object Activate : CabinDigitalSpeedAction
}

val CabinDigitalSpeedInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSpeedLimitIndicatorState(
    val label: String = "Speed-limit indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSpeedLimitIndicatorAction {
    data object Activate : CabinSpeedLimitIndicatorAction
}

val CabinSpeedLimitIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTelltaleStripState(
    val label: String = "Telltale strip",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTelltaleStripAction {
    data object Activate : CabinTelltaleStripAction
}

val CabinTelltaleStripInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinWarningOverlayState(
    val label: String = "Warning overlay",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWarningOverlayAction {
    data object Activate : CabinWarningOverlayAction
}

val CabinWarningOverlayInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinClusterInfoPanelState(
    val label: String = "Cluster info panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinClusterInfoPanelAction {
    data object Activate : CabinClusterInfoPanelAction
}

val CabinClusterInfoPanelInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinClusterModeSwitcherState(
    val label: String = "Cluster mode switcher",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinClusterModeSwitcherAction {
    data object Activate : CabinClusterModeSwitcherAction
}

val CabinClusterModeSwitcherInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinHudPrimitiveState(
    val label: String = "HUD primitive",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHudPrimitiveAction {
    data object Activate : CabinHudPrimitiveAction
}

val CabinHudPrimitiveInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinClusterThemeSetState(
    val label: String = "Cluster theme set",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinClusterThemeSetAction {
    data object Activate : CabinClusterThemeSetAction
}

val CabinClusterThemeSetInteraction: CabinInteraction = CabinInteraction.Glance

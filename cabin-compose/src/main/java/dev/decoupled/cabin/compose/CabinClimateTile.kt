package dev.decoupled.cabin.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Automotive climate tile: zone temp / fan / seat heat.
 *
 * Spec: docs/components/specs/climate-tile.md
 *
 * Craft: cabin density, climate accent as mark only, RE-quiet while Moving,
 * honest empty/stale — not a Material card clone.
 */
@Composable
fun CabinClimateTile(
    state: CabinClimateTileState,
    onAction: (CabinClimateTileAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, CabinInteraction.HvacAdjust)
    val padding = CabinTokens.Component.ClimateTile.padding.dp.dp
    val gap = CabinTokens.Component.ClimateTile.gap.dp.dp
    val controlMin = maxOf(
        CabinTokens.Component.ClimateTile.controlMinSize.dp.dp,
        compliance.touchTargetMinDp().dp,
    )
    val titleSize = CabinTokens.Type.Role.Title.size.sp.sp
    val labelSize = CabinTokens.Type.Role.Label.size.sp.sp
    val statusSize = CabinTokens.Type.Role.Status.size.sp.sp

    Column(
        // Flat cabin surface — no Material card fill / elevation wash.
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding, vertical = padding / 2)
            .testTag("cabin_climate_tile"),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(CabinTokens.Space.xs.dp.dp)
                    .height(controlMin / 3)
                    .background(colors.climate)
                    .testTag("climate_accent_mark"),
            )
            Spacer(Modifier.width(gap))
            BasicText(
                text = state.zoneLabel,
                style = TextStyle(
                    color = colors.onSurface,
                    fontSize = labelSize,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
        Spacer(Modifier.height(gap))
        ClimateStepperRow(
            valueText = formatTemp(state.temperatureC),
            valueDescription = signalDescription(state.temperatureC, formatTemp(state.temperatureC)),
            // Body stays onSurface — climate is mark-only.
            valueColor = colors.onSurface,
            valueTag = "climate_temp_value",
            downTag = "climate_temp_down",
            upTag = "climate_temp_up",
            downDescription = "Decrease temperature",
            upDescription = "Increase temperature",
            controlMin = controlMin,
            textSize = titleSize,
            contentColor = colors.onSurface,
            outlineColor = colors.outline,
            enabled = state.powerOn && state.temperatureC is Signal.Value,
            disposition = disposition,
            onDown = { onAction(CabinClimateTileAction.TempDown) },
            onUp = { onAction(CabinClimateTileAction.TempUp) },
        )
        Spacer(Modifier.height(gap))
        ClimateLabeledStepper(
            label = "Fan",
            valueText = formatLevel(state.fanLevel, state.fanMax),
            valueDescription = signalDescription(
                state.fanLevel,
                formatLevel(state.fanLevel, state.fanMax),
            ),
            valueTag = "climate_fan_value",
            downTag = "climate_fan_down",
            upTag = "climate_fan_up",
            downDescription = "Decrease fan",
            upDescription = "Increase fan",
            controlMin = controlMin,
            labelSize = statusSize,
            textSize = labelSize,
            contentColor = colors.onSurface,
            outlineColor = colors.outline,
            enabled = state.powerOn && state.fanLevel is Signal.Value,
            disposition = disposition,
            onDown = { onAction(CabinClimateTileAction.FanDown) },
            onUp = { onAction(CabinClimateTileAction.FanUp) },
        )
        Spacer(Modifier.height(gap))
        ClimateLabeledStepper(
            label = "Seat",
            valueText = formatLevel(state.seatHeatLevel, state.seatHeatMax),
            valueDescription = signalDescription(
                state.seatHeatLevel,
                formatLevel(state.seatHeatLevel, state.seatHeatMax),
            ),
            valueTag = "climate_seat_value",
            downTag = "climate_seat_down",
            upTag = "climate_seat_up",
            downDescription = "Decrease seat heat",
            upDescription = "Increase seat heat",
            controlMin = controlMin,
            labelSize = statusSize,
            textSize = labelSize,
            contentColor = colors.onSurface,
            outlineColor = colors.outline,
            enabled = state.powerOn && state.seatHeatLevel is Signal.Value,
            disposition = disposition,
            onDown = { onAction(CabinClimateTileAction.SeatHeatDown) },
            onUp = { onAction(CabinClimateTileAction.SeatHeatUp) },
        )
    }
}

@Composable
private fun ClimateLabeledStepper(
    label: String,
    valueText: String,
    valueDescription: String,
    valueTag: String,
    downTag: String,
    upTag: String,
    downDescription: String,
    upDescription: String,
    controlMin: Dp,
    labelSize: TextUnit,
    textSize: TextUnit,
    contentColor: Color,
    outlineColor: Color,
    enabled: Boolean,
    disposition: GateDisposition,
    onDown: () -> Unit,
    onUp: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        BasicText(
            text = label,
            style = TextStyle(
                color = contentColor,
                fontSize = labelSize,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.padding(end = CabinTokens.Component.ClimateTile.gap.dp.dp),
        )
        ClimateStepperRow(
            valueText = valueText,
            valueDescription = valueDescription,
            valueColor = contentColor,
            valueTag = valueTag,
            downTag = downTag,
            upTag = upTag,
            downDescription = downDescription,
            upDescription = upDescription,
            controlMin = controlMin,
            textSize = textSize,
            contentColor = contentColor,
            outlineColor = outlineColor,
            enabled = enabled,
            disposition = disposition,
            onDown = onDown,
            onUp = onUp,
        )
    }
}

@Composable
private fun ClimateStepperRow(
    valueText: String,
    valueDescription: String,
    valueColor: Color,
    valueTag: String,
    downTag: String,
    upTag: String,
    downDescription: String,
    upDescription: String,
    controlMin: Dp,
    textSize: TextUnit,
    contentColor: Color,
    outlineColor: Color,
    enabled: Boolean,
    disposition: GateDisposition,
    onDown: () -> Unit,
    onUp: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ClimateControl(
            label = "−",
            description = downDescription,
            tag = downTag,
            controlMin = controlMin,
            textSize = textSize,
            contentColor = contentColor,
            outlineColor = outlineColor,
            enabled = enabled,
            disposition = disposition,
            onClick = onDown,
        )
        BasicText(
            text = valueText,
            style = TextStyle(
                color = valueColor,
                fontSize = textSize,
                fontWeight = FontWeight.SemiBold,
            ),
            // Values stay full opacity while Moving (RE-quiet / glanceable).
            modifier = Modifier
                .widthIn(min = controlMin)
                .testTag(valueTag)
                .semantics { contentDescription = valueDescription },
        )
        ClimateControl(
            label = "+",
            description = upDescription,
            tag = upTag,
            controlMin = controlMin,
            textSize = textSize,
            contentColor = contentColor,
            outlineColor = outlineColor,
            enabled = enabled,
            disposition = disposition,
            onClick = onUp,
        )
    }
}

@Composable
private fun ClimateControl(
    label: String,
    description: String,
    tag: String,
    controlMin: Dp,
    textSize: TextUnit,
    contentColor: Color,
    outlineColor: Color,
    enabled: Boolean,
    disposition: GateDisposition,
    onClick: () -> Unit,
) {
    val canActivate = GateVisuals.activatable(enabled, disposition)
    Box(
        modifier = Modifier
            .size(controlMin)
            .alpha(
                if (!enabled) GateVisuals.quietAlpha(GateDisposition.Block)
                else GateVisuals.quietAlpha(disposition),
            )
            .border(1.dp, outlineColor)
            .testTag(tag)
            .semantics { contentDescription = description }
            .then(if (canActivate) Modifier.clickable(onClick = onClick) else Modifier),
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = label,
            style = TextStyle(color = contentColor, fontSize = textSize),
        )
    }
}

internal fun formatTemp(signal: Signal<Int>): String = when (signal) {
    is Signal.Value -> "${signal.value}°"
    is Signal.Stale -> "${signal.last}° · stale"
    Signal.Unavailable -> "—"
    is Signal.Fault -> "Fault"
}

internal fun formatLevel(signal: Signal<Int>, max: Int): String = when (signal) {
    is Signal.Value -> "${signal.value}/$max"
    is Signal.Stale -> "${signal.last}/$max · stale"
    Signal.Unavailable -> "—"
    is Signal.Fault -> "Fault"
}

/** ClimateTile presentation state (parity with Views). */
@Immutable
data class CabinClimateTileState(
    val zoneLabel: String,
    val temperatureC: Signal<Int>,
    val fanLevel: Signal<Int>,
    val fanMax: Int = 5,
    val seatHeatLevel: Signal<Int>,
    val seatHeatMax: Int = 3,
    val powerOn: Boolean = true,
) {
    companion object {
        fun empty(): CabinClimateTileState = CabinClimateTileState(
            zoneLabel = "",
            temperatureC = Signal.Unavailable,
            fanLevel = Signal.Unavailable,
            seatHeatLevel = Signal.Unavailable,
            powerOn = false,
        )
    }
}

sealed interface CabinClimateTileAction {
    data object TempUp : CabinClimateTileAction
    data object TempDown : CabinClimateTileAction
    data object FanUp : CabinClimateTileAction
    data object FanDown : CabinClimateTileAction
    data object SeatHeatUp : CabinClimateTileAction
    data object SeatHeatDown : CabinClimateTileAction
}

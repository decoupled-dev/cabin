package dev.decoupled.cabin.gauges.scaffold

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.GateVisuals
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.scaffold.cabinFamilyAccent
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Cluster/HUD scaffold host — Canvas arc placeholder with Restriction Engine.
 *
 * Not a production gauge: no needle physics, no frame-time budget. Replace
 * the body in a later gauges pass.
 */
@Composable
fun CabinGaugeHost(
    title: String,
    testTag: String,
    interaction: CabinInteraction,
    ui: CabinComponentUiState,
    onActivate: () -> Unit,
    modifier: Modifier = Modifier,
    family: String = "gauges",
) {
    val colors = LocalCabinColors.current
    val disposition = dispositionOf(LocalCabinComplianceState.current, interaction)
    val activatable = GateVisuals.activatable(ui.effectivelyEnabled, disposition)
    val accent = cabinFamilyAccent(family, colors)
    val arcColor = when {
        ui.error -> colors.error
        ui.restricted || disposition != GateDisposition.Allow -> colors.warning
        else -> accent
    }
    val track = colors.outline.copy(alpha = 0.45f)
    val restricted = disposition != GateDisposition.Allow || ui.restricted
    val status = when {
        ui.loading -> "Loading"
        ui.error -> "Error"
        restricted -> "Restricted"
        else -> "Live"
    }
    val sweep = when {
        ui.loading -> 80f
        restricted -> 40f
        else -> 160f
    }
    val ring = if (ui.focused) colors.focusRing else colors.outline.copy(alpha = 0.35f)

    Column(
        modifier = modifier
            .alpha(GateVisuals.quietAlpha(disposition))
            .padding(CabinTokens.Space.sm.dp.dp)
            .focusable()
            .semantics { contentDescription = "$title. $status" }
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicText(
            text = title,
            style = TextStyle(
                color = colors.onSurface,
                fontSize = CabinTokens.Type.Role.Label.size.sp.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        Box(
            modifier = Modifier
                .padding(top = CabinTokens.Space.sm.dp.dp)
                .size(128.dp)
                .border(CabinTokens.Focus.Ring.width.dp.dp, ring, CircleShape)
                .background(colors.surface, CircleShape)
                .clickable(enabled = activatable, onClick = onActivate)
                .testTag("${testTag}_activate"),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(Modifier.size(112.dp)) {
                val stroke = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round)
                drawArc(
                    color = track,
                    startAngle = 140f,
                    sweepAngle = 260f,
                    useCenter = false,
                    style = stroke,
                )
                drawArc(
                    color = arcColor,
                    startAngle = 140f,
                    sweepAngle = sweep,
                    useCenter = false,
                    style = stroke,
                )
                drawLine(
                    color = arcColor,
                    start = center,
                    end = Offset(center.x, 10.dp.toPx()),
                    strokeWidth = 4.dp.toPx(),
                    cap = StrokeCap.Round,
                )
            }
            BasicText(
                text = if (ui.loading) "—" else "64",
                style = TextStyle(
                    color = colors.onSurface,
                    fontSize = CabinTokens.Type.Role.Title.size.sp.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
        BasicText(
            text = status,
            style = TextStyle(
                color = if (restricted || ui.error) colors.warning else colors.outline,
                fontSize = CabinTokens.Type.Role.Status.size.sp.sp,
            ),
            modifier = Modifier
                .padding(top = 4.dp)
                .testTag("${testTag}_status"),
        )
    }
}

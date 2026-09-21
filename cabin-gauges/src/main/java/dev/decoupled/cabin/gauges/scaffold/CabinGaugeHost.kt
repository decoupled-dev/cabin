package dev.decoupled.cabin.gauges.scaffold

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, interaction)
    val activatable = GateVisuals.activatable(ui.effectivelyEnabled, disposition)
    val arcColor = colors.primary
    val track = colors.outline

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(CabinTokens.Space.sm.dp.dp)
            .focusable()
            .semantics { contentDescription = title }
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
                .size(120.dp)
                .clickable(enabled = activatable, onClick = onActivate)
                .testTag("${testTag}_activate"),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(Modifier.size(120.dp)) {
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
                    sweepAngle = 160f,
                    useCenter = false,
                    style = stroke,
                )
                drawLine(
                    color = colors.warning,
                    start = center,
                    end = Offset(center.x, 8.dp.toPx()),
                    strokeWidth = 4.dp.toPx(),
                    cap = StrokeCap.Round,
                )
            }
        }
        val status = if (disposition == GateDisposition.Allow) "live" else "restricted"
        BasicText(
            text = status,
            style = TextStyle(color = colors.outline, fontSize = 12.sp),
            modifier = Modifier.testTag("${testTag}_status"),
        )
    }
}

package dev.decoupled.cabin.compose.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
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
 * Shared Experimental scaffold chrome for generated Compose components.
 *
 * Token colors, min touch, Restriction Engine gating, family accent mark,
 * visible focus ring. Quiet cabin surface — not a Material card.
 */
@Composable
fun CabinScaffoldHost(
    title: String,
    family: String,
    testTag: String,
    interaction: CabinInteraction,
    ui: CabinComponentUiState,
    onActivate: () -> Unit,
    modifier: Modifier = Modifier,
    variant: String = "",
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, interaction)
    val activatable = GateVisuals.activatable(ui.effectivelyEnabled, disposition)
    val minSize = maxOf(
        CabinTokens.Size.Touch.minimum.dp.dp,
        compliance.touchTargetMinDp().dp,
    )
    val ring = CabinTokens.Focus.Ring.width.dp.dp
    val corner = CabinTokens.Shape.Corner.md.dp.dp
    val mark = cabinFamilyAccent(family, colors)
    val restricted = disposition != GateDisposition.Allow || ui.restricted
    val interactionSource = remember { MutableInteractionSource() }
    val pressedNow by interactionSource.collectIsPressedAsState()
    var focusedNow by remember { mutableStateOf(false) }
    val showFocus = ui.focused || focusedNow
    val showPressed = ui.pressed || pressedNow
    val borderColor = when {
        ui.error -> colors.error
        showFocus -> colors.focusRing
        ui.selected -> mark
        else -> colors.outline.copy(alpha = 0.55f)
    }
    val status = when {
        ui.loading -> "Loading"
        ui.error -> "Error"
        restricted -> "Restricted while driving — park to continue"
        ui.disabled || !ui.enabled -> "Off"
        ui.selected -> "Selected"
        else -> family
    }
    val fill = when {
        ui.error -> colors.surface
        ui.selected -> mark.copy(alpha = 0.08f)
        showPressed -> colors.surfaceVariant
        else -> colors.surface
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minSize)
            .alpha(GateVisuals.quietAlpha(disposition))
            .border(if (showFocus) ring else 1.dp, borderColor, RoundedCornerShape(corner))
            .background(fill, RoundedCornerShape(corner))
            .onFocusChanged { focusedNow = it.isFocused }
            .focusable()
            .semantics {
                contentDescription = "$title. $status"
                role = Role.Button
            }
            .testTag(testTag),
    ) {
        Box(
            modifier = Modifier
                .width(CabinTokens.Space.xs.dp.dp)
                .heightIn(min = minSize)
                .fillMaxHeight()
                .background(mark, RoundedCornerShape(topStart = corner, bottomStart = corner))
                .testTag("${testTag}_mark"),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(CabinTokens.Space.sm.dp.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicText(
                    text = title,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = CabinTokens.Type.Role.Title.size.sp.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    modifier = Modifier.weight(1f),
                )
                if (variant.isNotBlank()) {
                    BasicText(
                        text = variant,
                        style = TextStyle(
                            color = colors.outline,
                            fontSize = CabinTokens.Type.Role.Status.size.sp.sp,
                        ),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .border(1.dp, colors.outline.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                            .testTag("${testTag}_variant"),
                    )
                }
            }
            BasicText(
                text = status,
                style = TextStyle(
                    color = when {
                        ui.error -> colors.error
                        restricted -> colors.warning
                        ui.loading -> colors.outline
                        else -> colors.outline
                    },
                    fontSize = CabinTokens.Type.Role.Label.size.sp.sp,
                ),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .testTag("${testTag}_status"),
            )
            if (ui.loading) {
                Box(
                    modifier = Modifier
                        .padding(top = CabinTokens.Space.sm.dp.dp)
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(mark.copy(alpha = 0.35f), RoundedCornerShape(2.dp))
                        .testTag("${testTag}_loading"),
                )
            }
            Spacer(Modifier.height(CabinTokens.Space.sm.dp.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minSize)
                    .border(
                        1.dp,
                        if (activatable) mark.copy(alpha = 0.45f) else colors.outline.copy(alpha = 0.35f),
                        RoundedCornerShape(corner),
                    )
                    .background(
                        when {
                            !activatable -> colors.surface
                            showPressed -> mark.copy(alpha = 0.28f)
                            else -> mark.copy(alpha = 0.14f)
                        },
                        RoundedCornerShape(corner),
                    )
                    .clickable(
                        enabled = activatable,
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onActivate,
                    )
                    .testTag("${testTag}_activate"),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    text = if (activatable) "Activate" else "Unavailable",
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = CabinTokens.Type.Role.Label.size.sp.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }
        }
    }
}

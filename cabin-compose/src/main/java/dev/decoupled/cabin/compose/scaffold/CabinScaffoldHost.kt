package dev.decoupled.cabin.compose.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusProperties
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
 * Token colors, min touch, Restriction Engine gating, visible focus ring.
 * Not a production visual — later passes replace the host body.
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
    val restricted = disposition != GateDisposition.Allow || ui.restricted
    val borderColor = if (ui.focused) colors.focusRing else colors.outline
    val status = when {
        ui.loading -> "Loading"
        ui.error -> "Error"
        restricted -> "Restricted while driving — park to continue"
        else -> family
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .alpha(GateVisuals.quietAlpha(disposition))
            .border(ring, borderColor, RoundedCornerShape(corner))
            .background(colors.surfaceVariant, RoundedCornerShape(corner))
            .padding(CabinTokens.Space.sm.dp.dp)
            .focusable()
            .focusProperties { canFocus = true }
            .semantics(mergeDescendants = true) {
                contentDescription = "$title. $status"
                role = Role.Button
            }
            .testTag(testTag),
    ) {
        BasicText(
            text = title,
            style = TextStyle(
                color = colors.onSurface,
                fontSize = CabinTokens.Type.Role.Title.size.sp.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        if (variant.isNotBlank()) {
            BasicText(
                text = variant,
                style = TextStyle(
                    color = colors.outline,
                    fontSize = CabinTokens.Type.Role.Status.size.sp.sp,
                ),
                modifier = Modifier.padding(top = 2.dp),
            )
        }
        BasicText(
            text = status,
            style = TextStyle(
                color = if (restricted) colors.warning else colors.outline,
                fontSize = CabinTokens.Type.Role.Label.size.sp.sp,
            ),
            modifier = Modifier
                .padding(top = 4.dp)
                .testTag("${testTag}_status"),
        )
        Spacer(Modifier.height(CabinTokens.Space.sm.dp.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minSize)
                .border(1.dp, colors.outline, RoundedCornerShape(corner))
                .background(if (activatable) colors.primary.copy(alpha = 0.16f) else colors.surface)
                .clickable(enabled = activatable, onClick = onActivate)
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

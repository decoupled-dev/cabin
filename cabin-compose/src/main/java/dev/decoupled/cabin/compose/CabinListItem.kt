package dev.decoupled.cabin.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Cabin ListItem — low-density, restriction-aware collection row.
 *
 * Craft: 76dp density floor; Restriction Engine on activation; selection uses
 * outline/container — not locked night warning/error.
 *
 * Activation is gated via [LocalCabinComplianceState] (fail-closed when absent).
 *
 * Spec: docs/components/specs/list-item.md
 *
 * **Experimental** Compose parity with Views `CabinListItemView` (Alpha).
 */
@Composable
fun CabinListItem(
    state: CabinListItemState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, state.interaction)
    val activatable = GateVisuals.activatable(state.enabled, disposition)
    val alpha = if (!state.enabled) 0.4f else GateVisuals.alpha(disposition)

    val tokenMin = CabinTokens.Component.ListItem.minHeight.dp.dp
    val complianceMin = compliance.touchTargetMinDp().dp
    val minHeight = maxOf(tokenMin, complianceMin)
    val horizontalPadding = CabinTokens.Component.ListItem.horizontalPadding.dp.dp
    val verticalPadding = CabinTokens.Component.ListItem.verticalPadding.dp.dp
    val gap = CabinTokens.Component.ListItem.gap.dp.dp
    val iconSize = CabinTokens.Component.ListItem.iconSize.dp.dp
    val titleSize = CabinTokens.Type.Role.Body.size.sp.sp
    val supportingSize = CabinTokens.Type.Role.Status.size.sp.sp

    val background = if (state.selected) colors.container else colors.surface
    val description = state.contentDescription
        ?: buildString {
            append(state.title)
            if (!state.supportingText.isNullOrBlank()) {
                append(", ")
                append(state.supportingText)
            }
        }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight)
            .defaultMinSize(minHeight = minHeight)
            .background(background)
            .then(
                if (state.selected) {
                    Modifier.border(2.dp, colors.outline)
                } else {
                    Modifier
                },
            )
            .alpha(alpha)
            .testTag(state.testTag)
            .semantics { contentDescription = description }
            .then(
                if (activatable) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                },
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap),
    ) {
        if (state.leadingIcon != null) {
            Image(
                painter = state.leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                colorFilter = ColorFilter.tint(colors.onSurface),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            BasicText(
                text = state.title,
                style = TextStyle(
                    color = colors.onSurface,
                    fontSize = titleSize,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1,
            )
            if (!state.supportingText.isNullOrBlank()) {
                BasicText(
                    text = state.supportingText,
                    style = TextStyle(
                        color = colors.onSurface.copy(alpha = 0.72f),
                        fontSize = supportingSize,
                        fontWeight = FontWeight.Medium,
                    ),
                    maxLines = 1,
                )
            }
        }
        if (!state.trailingLabel.isNullOrBlank()) {
            BasicText(
                text = state.trailingLabel,
                style = TextStyle(
                    color = colors.onSurface.copy(alpha = 0.72f),
                    fontSize = supportingSize,
                    fontWeight = FontWeight.Medium,
                ),
                maxLines = 1,
            )
        }
    }
}

/**
 * Shared ListItem state across Compose / Views parity.
 *
 * @property interaction declared category for Restriction Engine gating
 */
@Immutable
data class CabinListItemState(
    val title: String,
    val interaction: CabinInteraction,
    val supportingText: String? = null,
    val leadingIcon: Painter? = null,
    val trailingLabel: String? = null,
    val selected: Boolean = false,
    val enabled: Boolean = true,
    val contentDescription: String? = null,
    val testTag: String = "cabin_list_item",
)

/** Token constants surfaced for tests / diagnostics. */
object CabinListItemTokens {
    val minHeightDp: Float = CabinTokens.Component.ListItem.minHeight.dp
    val horizontalPaddingDp: Float = CabinTokens.Component.ListItem.horizontalPadding.dp
    val verticalPaddingDp: Float = CabinTokens.Component.ListItem.verticalPadding.dp
    val gapDp: Float = CabinTokens.Component.ListItem.gap.dp
    val iconSizeDp: Float = CabinTokens.Component.ListItem.iconSize.dp
    val dividerInsetDp: Float = CabinTokens.Component.ListItem.dividerInset.dp
}

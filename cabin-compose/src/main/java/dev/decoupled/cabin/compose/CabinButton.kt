package dev.decoupled.cabin.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
 * Button / IconButton visual style (parity with Views).
 */
enum class CabinButtonVariant {
    /** Primary filled action — one per glance cluster. */
    Filled,
    /** Secondary outlined. */
    Outlined,
    /** Tertiary quiet (no fill / stroke). */
    Quiet,
}

/**
 * Cabin Button — glance-sized Primary Action primitive.
 *
 * Craft: 76dp touch min; Filled = forest primary/onPrimary; Outlined secondary
 * uses outline; night warning/error stay locked (not Button chrome).
 *
 * Activation is gated via [LocalCabinComplianceState] (fail-closed when absent).
 *
 * Spec: docs/components/specs/button.md
 *
 * **Experimental** Compose parity with Views `CabinButtonView` (Alpha).
 */
@Composable
fun CabinButton(
    state: CabinButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, state.interaction)
    val activatable = GateVisuals.activatable(state.enabled, disposition)
    val alpha = if (!state.enabled) 0.4f else GateVisuals.alpha(disposition)

    val tokenMin = CabinTokens.Component.Button.minHeight.dp.dp
    val complianceMin = compliance.touchTargetMinDp().dp
    val minHeight = maxOf(tokenMin, complianceMin)
    val horizontalPadding = CabinTokens.Component.Button.horizontalPadding.dp.dp
    val iconSize = CabinTokens.Component.Button.iconSize.dp.dp
    val gap = CabinTokens.Component.Button.gap.dp.dp
    val corner = CabinTokens.Component.Button.cornerRadius.dp.dp
    val labelSize = CabinTokens.Type.Role.Label.size.sp.sp
    val shape = RoundedCornerShape(corner)

    val (background, contentColor, borderColor) = when (state.variant) {
        CabinButtonVariant.Filled -> Triple(colors.primary, colors.onPrimary, null)
        CabinButtonVariant.Outlined -> Triple(Color.Transparent, colors.onSurface, colors.outline)
        CabinButtonVariant.Quiet -> Triple(Color.Transparent, colors.onSurface, null)
    }

    val description = state.contentDescription ?: state.label

    Row(
        modifier = modifier
            .heightIn(min = minHeight)
            .defaultMinSize(minHeight = minHeight)
            .clip(shape)
            .background(background, shape)
            .then(
                if (borderColor != null) {
                    Modifier.border(2.dp, borderColor, shape)
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
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap, Alignment.CenterHorizontally),
    ) {
        if (state.icon != null) {
            Image(
                painter = state.icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                colorFilter = ColorFilter.tint(contentColor),
            )
        }
        BasicText(
            text = state.label,
            style = TextStyle(
                color = contentColor,
                fontSize = labelSize,
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }
}

/**
 * Cabin IconButton — icon-only Primary Action primitive.
 *
 * [CabinIconButtonState.contentDescription] is required for accessibility.
 *
 * Spec: docs/components/specs/button.md
 *
 * **Experimental** Compose parity with Views `CabinIconButtonView` (Alpha).
 */
@Composable
fun CabinIconButton(
    state: CabinIconButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    require(state.contentDescription.isNotBlank()) {
        "IconButton requires a non-blank contentDescription"
    }

    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, state.interaction)
    val activatable = GateVisuals.activatable(state.enabled, disposition)
    val alpha = if (!state.enabled) 0.4f else GateVisuals.alpha(disposition)

    val tokenMin = CabinTokens.Component.IconButton.minSize.dp.dp
    val complianceMin = compliance.touchTargetMinDp().dp
    val minSize = maxOf(tokenMin, complianceMin)
    val iconSize = CabinTokens.Component.IconButton.iconSize.dp.dp
    val corner = CabinTokens.Component.IconButton.cornerRadius.dp.dp
    val shape = RoundedCornerShape(corner)

    val (background, contentColor, borderColor) = when (state.variant) {
        CabinButtonVariant.Filled -> Triple(colors.primary, colors.onPrimary, null)
        CabinButtonVariant.Outlined -> Triple(Color.Transparent, colors.onSurface, colors.outline)
        CabinButtonVariant.Quiet -> Triple(Color.Transparent, colors.onSurface, null)
    }

    Box(
        modifier = modifier
            .size(minSize)
            .clip(shape)
            .background(background, shape)
            .then(
                if (borderColor != null) {
                    Modifier.border(2.dp, borderColor, shape)
                } else {
                    Modifier
                },
            )
            .alpha(alpha)
            .testTag(state.testTag)
            .semantics { contentDescription = state.contentDescription }
            .then(
                if (activatable) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (state.icon != null) {
            Image(
                painter = state.icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                colorFilter = ColorFilter.tint(contentColor),
            )
        }
    }
}

/**
 * Shared Button state across Compose / Views parity.
 *
 * @property interaction declared category for Restriction Engine gating
 */
@Immutable
data class CabinButtonState(
    val label: String,
    val interaction: CabinInteraction,
    val variant: CabinButtonVariant = CabinButtonVariant.Filled,
    val enabled: Boolean = true,
    val icon: Painter? = null,
    val contentDescription: String? = null,
    val testTag: String = "cabin_button",
)

/**
 * Shared IconButton state across Compose / Views parity.
 *
 * @property contentDescription required for icon-only a11y
 * @property interaction declared category for Restriction Engine gating
 */
@Immutable
data class CabinIconButtonState(
    val icon: Painter?,
    val contentDescription: String,
    val interaction: CabinInteraction,
    val variant: CabinButtonVariant = CabinButtonVariant.Quiet,
    val enabled: Boolean = true,
    val testTag: String = "cabin_icon_button",
)

/** Token constants surfaced for tests / diagnostics. */
object CabinButtonTokens {
    val minHeightDp: Float = CabinTokens.Component.Button.minHeight.dp
    val horizontalPaddingDp: Float = CabinTokens.Component.Button.horizontalPadding.dp
    val iconSizeDp: Float = CabinTokens.Component.Button.iconSize.dp
    val gapDp: Float = CabinTokens.Component.Button.gap.dp
    val cornerRadiusDp: Float = CabinTokens.Component.Button.cornerRadius.dp
}

/** Token constants surfaced for tests / diagnostics. */
object CabinIconButtonTokens {
    val minSizeDp: Float = CabinTokens.Component.IconButton.minSize.dp
    val iconSizeDp: Float = CabinTokens.Component.IconButton.iconSize.dp
    val cornerRadiusDp: Float = CabinTokens.Component.IconButton.cornerRadius.dp
}

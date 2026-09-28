@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.action

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.foundation.components.action.CabinButtonAction
import dev.decoupled.cabin.foundation.components.action.CabinButtonInteraction
import dev.decoupled.cabin.foundation.components.action.CabinButtonState
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Cabin Button — glance-sized primary action.
 *
 * Variants match the theme-board button styles: filled, tonal, outlined,
 * text, icon. Activation is gated by [LocalCabinComplianceState]
 * (fail-closed when absent). Spec: docs/components/specs/button.md
 */
@Composable
fun CabinButton(
    state: CabinButtonState = CabinButtonState(),
    onAction: (CabinButtonAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val disposition = dispositionOf(compliance, CabinButtonInteraction)
    val activatable = state.ui.effectivelyEnabled && disposition == GateDisposition.Allow
    val interactionSource = remember { MutableInteractionSource() }
    val pressedNow by interactionSource.collectIsPressedAsState()
    val showFocus = state.ui.focused
    val showPressed = state.ui.pressed || (pressedNow && activatable)
    val iconVariant = state.variant == "icon"

    val minHeight = maxOf(
        CabinTokens.Component.Button.minHeight.dp.dp,
        compliance.touchTargetMinDp().dp,
    )
    val corner = CabinTokens.Component.Button.cornerRadius.dp.dp
    val shape = RoundedCornerShape(corner)
    val labelSize = CabinTokens.Type.Role.Label.size.sp.sp
    val pad = if (iconVariant) 0.dp else CabinTokens.Component.Button.horizontalPadding.dp.dp
    val surfaceHigh = colors.surface.mix(colors.onSurface, 0.18f)
    val onSurfaceVariant = colors.onSurface.mix(colors.surface, 0.42f)
    val outlineSubtle = colors.onSurface.copy(alpha = 0.12f)
    val chrome = buttonChrome(
        variant = state.variant,
        primary = colors.primary,
        onPrimary = colors.onPrimary,
        onSurface = colors.onSurface,
        surface = colors.surface,
        surfaceHigh = surfaceHigh,
        outline = colors.outline,
        onSurfaceVariant = onSurfaceVariant,
        outlineSubtle = outlineSubtle,
        disabled = !activatable,
    )
    val fill = if (showPressed && activatable) chrome.background.mix(colors.onSurface, 0.08f)
    else chrome.background
    val borderColor = when {
        state.ui.error -> colors.error
        showFocus && activatable -> colors.focusRing
        else -> chrome.border
    }
    val borderWidth = when {
        borderColor == null -> 0.dp
        state.ui.error || (showFocus && activatable) -> CabinTokens.Focus.Ring.width.dp.dp
        else -> 1.dp
    }
    val label = when {
        state.label.isNotBlank() -> state.label
        iconVariant -> "＋"
        else -> "Button"
    }

    Row(
        modifier = modifier
            .heightIn(min = minHeight)
            .defaultMinSize(minWidth = minHeight, minHeight = minHeight)
            .clip(shape)
            .background(fill, shape)
            .then(
                if (borderColor != null && borderWidth > 0.dp) {
                    Modifier.border(borderWidth, borderColor, shape)
                } else {
                    Modifier
                },
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = activatable,
                role = Role.Button,
                onClick = { onAction(CabinButtonAction.Activate) },
            )
            .semantics {
                contentDescription = label
                role = Role.Button
                if (!activatable) disabled()
            }
            .testTag("cabin_button")
            .padding(horizontal = pad),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        BasicText(
            text = label,
            style = TextStyle(
                color = chrome.content,
                fontSize = if (iconVariant) CabinTokens.Type.Role.Title.size.sp.sp else labelSize,
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }
}

private data class ButtonChrome(
    val background: Color,
    val content: Color,
    val border: Color?,
)

/**
 * Theme-board variants. Disabled chrome is a quiet surface, not a second
 * opacity wash on top of the enabled fill.
 */
private fun buttonChrome(
    variant: String,
    primary: Color,
    onPrimary: Color,
    onSurface: Color,
    surface: Color,
    surfaceHigh: Color,
    outline: Color,
    onSurfaceVariant: Color,
    outlineSubtle: Color,
    disabled: Boolean,
): ButtonChrome {
    if (disabled) {
        return ButtonChrome(surface, onSurfaceVariant, outlineSubtle)
    }
    return when (variant) {
        "filled" -> ButtonChrome(primary, onPrimary, null)
        "outlined" -> ButtonChrome(Color.Transparent, onSurface, outline)
        "text", "icon" -> ButtonChrome(Color.Transparent, primary, null)
        "tonal" -> ButtonChrome(surfaceHigh.mix(primary, 0.18f), onSurface, null)
        else -> ButtonChrome(surfaceHigh.mix(primary, 0.18f), onSurface, null)
    }
}

private fun Color.mix(other: Color, otherFraction: Float): Color {
    val t = otherFraction.coerceIn(0f, 1f)
    val inv = 1f - t
    return Color(
        red = red * inv + other.red * t,
        green = green * inv + other.green * t,
        blue = blue * inv + other.blue * t,
        alpha = alpha * inv + other.alpha * t,
    )
}

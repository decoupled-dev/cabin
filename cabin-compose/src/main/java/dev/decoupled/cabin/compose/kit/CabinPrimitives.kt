/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.decoupled.cabin.compose.kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.theme.CabinLookTheme
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.tokens.CabinEdgeStyle

// ---------------------------------------------------------------- gating

/**
 * Restriction Engine verdict for [interaction] in the current drive state.
 * Kit components call this; they never embed driving policy (AGENTS rule 7).
 */
@Composable
fun cabinGate(interaction: CabinInteraction): GateDisposition =
    CabinTheme.compliance.disposition(interaction)

/** Content alpha for controls the engine substitutes or blocks. */
internal const val DisabledAlpha: Float = 0.38f

// ---------------------------------------------------------------- surfaces

/**
 * Page background for a Cabin screen. Glass looks add their backdrop glows.
 */
@Composable
fun CabinBackdrop(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val look = CabinTheme.look
    val c = look.colors
    Box(
        modifier = modifier
            .background(c.background)
            .drawBehind {
                if (c.backdropGlowStart.isSpecified) {
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(c.backdropGlowStart, c.backdropGlowStart.copy(alpha = 0f)),
                            center = Offset(size.width * 0.12f, 0f),
                            radius = size.width * 0.5f,
                        ),
                    )
                }
                if (c.backdropGlowEnd.isSpecified) {
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(c.backdropGlowEnd, c.backdropGlowEnd.copy(alpha = 0f)),
                            center = Offset(size.width * 0.92f, size.height),
                            radius = size.width * 0.5f,
                        ),
                    )
                }
            },
    ) {
        CompositionLocalProvider(LocalContentColor provides c.onSurface) { content() }
    }
}

/** Border for the look's edge style, or null. */
fun CabinLookTheme.edgeBorder(): BorderStroke? = when (edge) {
    CabinEdgeStyle.None -> null
    CabinEdgeStyle.Line, CabinEdgeStyle.Glass -> BorderStroke(CabinKitMetrics.hairline, colors.outline)
}

/**
 * A card or panel: the look's surface color, large shape and edge.
 */
@Composable
fun CabinPanel(
    modifier: Modifier = Modifier,
    shape: Shape = CabinTheme.look.shapes.large,
    color: Color = CabinTheme.look.colors.surface,
    contentPadding: PaddingValues = PaddingValues(CabinKitMetrics.gapLg),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit,
) {
    val look = CabinTheme.look
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = look.colors.onSurface,
        border = look.edgeBorder(),
    ) {
        Column(Modifier.padding(contentPadding), verticalArrangement = verticalArrangement, content = content)
    }
}

// ---------------------------------------------------------------- buttons

/** Visual weight of a [CabinButton]. */
enum class CabinButtonStyle {
    /** Primary color; one per region (play/pause, start route). */
    Filled,

    /** Raised surface; secondary actions. */
    Tonal,

    /** No fill; actions inside a pill group or toolbar. */
    Plain,
}

/**
 * Cabin button. The Restriction Engine decides whether [interaction] can run
 * now; when it can't, the button stays in place, dims, and ignores taps.
 *
 * @param interaction what this button does, for the Restriction Engine
 * @param contentDescription spoken label when the content is icon-only
 */
@Composable
fun CabinButton(
    onClick: () -> Unit,
    interaction: CabinInteraction,
    modifier: Modifier = Modifier,
    style: CabinButtonStyle = CabinButtonStyle.Tonal,
    shape: Shape = CabinTheme.look.shapes.control,
    contentDescription: String? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = CabinKitMetrics.gapXl),
    content: @Composable RowScope.() -> Unit,
) {
    val look = CabinTheme.look
    val enabled = cabinGate(interaction) == GateDisposition.Allow
    val (container, contentColor) = when (style) {
        CabinButtonStyle.Filled -> look.colors.primary to look.colors.onPrimary
        CabinButtonStyle.Tonal -> look.colors.surfaceHigh to look.colors.onSurface
        CabinButtonStyle.Plain -> Color.Transparent to look.colors.onSurface
    }
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .sizeIn(minWidth = CabinKitMetrics.touchMin, minHeight = CabinKitMetrics.touchMin)
            .then(if (contentDescription != null) Modifier.semantics { this.contentDescription = contentDescription } else Modifier),
        shape = shape,
        color = container,
        contentColor = if (enabled) contentColor else contentColor.copy(alpha = DisabledAlpha),
        border = if (style == CabinButtonStyle.Tonal) look.edgeBorder() else null,
    ) {
        Row(
            modifier = Modifier.padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

/** Icon-only [CabinButton]; [contentDescription] is required. */
@Composable
fun CabinIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    interaction: CabinInteraction,
    modifier: Modifier = Modifier,
    style: CabinButtonStyle = CabinButtonStyle.Tonal,
    size: Dp = CabinKitMetrics.control,
    iconSize: Dp = CabinKitMetrics.iconLg,
) {
    CabinButton(
        onClick = onClick,
        interaction = interaction,
        modifier = modifier.size(size),
        style = style,
        contentDescription = contentDescription,
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(iconSize))
    }
}

/** Label text using the look's label family, weight and casing. */
@Composable
fun CabinLabel(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    maxLines: Int = 1,
) {
    val look = CabinTheme.look
    Text(
        text = look.labelText(text),
        modifier = modifier,
        color = color,
        style = look.type.label,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}

// ---------------------------------------------------------------- active state

/**
 * Draws the look's active state: the active container fill, and for the
 * Underline look an accent bar along the bottom edge.
 */
@Immutable
data class CabinActiveColors(val container: Color, val content: Color, val indicator: Color)

/** Active / inactive colors for a control resting on [restingContainer]. */
@Composable
fun cabinActiveColors(active: Boolean, restingContainer: Color, restingContent: Color): CabinActiveColors {
    val c = CabinTheme.look.colors
    return if (active) {
        CabinActiveColors(c.activeContainer, c.onActiveContainer, c.activeIndicator)
    } else {
        CabinActiveColors(restingContainer, restingContent, Color.Unspecified)
    }
}

internal fun Modifier.activeIndicator(color: Color): Modifier =
    if (!color.isSpecified) {
        this
    } else {
        drawWithContent {
            drawContent()
            val h = CabinKitMetrics.activeIndicator.toPx()
            drawRect(color, topLeft = Offset(0f, size.height - h), size = Size(size.width, h))
        }
    }

/**
 * A control with an on/off or selected state, drawn in the look's active
 * style. Announces its state to accessibility services.
 *
 * @param stateDescription spoken state, e.g. "On" or "Level 2 of 3"
 */
@Composable
fun CabinToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    interaction: CabinInteraction,
    modifier: Modifier = Modifier,
    shape: Shape = CabinTheme.look.shapes.control,
    restingContainer: Color = CabinTheme.look.colors.surfaceHigh,
    restingContent: Color = CabinTheme.look.colors.onSurface,
    stateDescription: String? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = CabinKitMetrics.gapLg),
    horizontalArrangement: Arrangement.Horizontal =
        Arrangement.spacedBy(CabinKitMetrics.gapSm, Alignment.CenterHorizontally),
    content: @Composable RowScope.() -> Unit,
) {
    val look = CabinTheme.look
    val enabled = cabinGate(interaction) == GateDisposition.Allow
    val colors = cabinActiveColors(checked, restingContainer, restingContent)
    Surface(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        modifier = modifier
            .sizeIn(minWidth = CabinKitMetrics.touchMin, minHeight = CabinKitMetrics.touchMin)
            .then(if (stateDescription != null) Modifier.semantics { this.stateDescription = stateDescription } else Modifier),
        shape = shape,
        color = colors.container,
        contentColor = if (enabled) colors.content else colors.content.copy(alpha = DisabledAlpha),
        border = if (restingContainer != Color.Transparent) look.edgeBorder() else null,
    ) {
        Row(
            modifier = Modifier.activeIndicator(colors.indicator).padding(contentPadding),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

/**
 * One-of-many selector on a recessed track (fan levels, drive modes).
 */
@Composable
fun <T> CabinSegmentedControl(
    options: List<T>,
    selected: T?,
    onSelect: (T) -> Unit,
    interaction: CabinInteraction,
    label: (T) -> String,
    modifier: Modifier = Modifier,
    optionDescription: (T) -> String = label,
) {
    val look = CabinTheme.look
    val enabled = cabinGate(interaction) == GateDisposition.Allow
    Surface(
        modifier = modifier.height(CabinKitMetrics.control),
        shape = look.shapes.control,
        color = look.colors.surfaceHigh,
        border = look.edgeBorder(),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = CabinKitMetrics.trackInset),
            horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapXs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            options.forEach { option ->
                val isSelected = option == selected
                val colors = cabinActiveColors(isSelected, Color.Transparent, look.colors.onSurfaceVariant)
                Surface(
                    selected = isSelected,
                    onClick = { onSelect(option) },
                    enabled = enabled,
                    modifier = Modifier
                        .weight(1f)
                        .height(CabinKitMetrics.controlInset)
                        .semantics {
                            role = Role.RadioButton
                            this.selected = isSelected
                            contentDescription = optionDescription(option)
                        },
                    shape = look.shapes.control,
                    color = colors.container,
                    contentColor = if (enabled) colors.content else colors.content.copy(alpha = DisabledAlpha),
                ) {
                    Box(Modifier.activeIndicator(colors.indicator), contentAlignment = Alignment.Center) {
                        CabinLabel(label(option))
                    }
                }
            }
        }
    }
}

/**
 * − value + stepper (temperature). The value slot is fixed-width so the
 * buttons don't move as the number changes.
 */
@Composable
fun CabinStepper(
    value: String,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    interaction: CabinInteraction,
    decrementDescription: String,
    incrementDescription: String,
    modifier: Modifier = Modifier,
    valueWidth: Dp = 108.dp,
    valueDescription: String = value,
    container: Color = CabinTheme.look.colors.surface,
) {
    val look = CabinTheme.look
    Surface(
        modifier = modifier.height(CabinKitMetrics.barControl),
        shape = look.shapes.control,
        color = container,
        border = look.edgeBorder(),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = CabinKitMetrics.trackInset),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CabinIconButton(
                icon = CabinIcons.Minus,
                contentDescription = decrementDescription,
                onClick = onDecrement,
                interaction = interaction,
                style = CabinButtonStyle.Plain,
                size = CabinKitMetrics.touchMin + CabinKitMetrics.gapXs,
                iconSize = CabinKitMetrics.iconMd,
            )
            Text(
                text = value,
                modifier = Modifier
                    .width(valueWidth)
                    .semantics { contentDescription = valueDescription },
                style = look.type.numeralSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
            CabinIconButton(
                icon = CabinIcons.Plus,
                contentDescription = incrementDescription,
                onClick = onIncrement,
                interaction = interaction,
                style = CabinButtonStyle.Plain,
                size = CabinKitMetrics.touchMin + CabinKitMetrics.gapXs,
                iconSize = CabinKitMetrics.iconMd,
            )
        }
    }
}

// ---------------------------------------------------------------- indicators

/** Linear progress / level bar. [fraction] is clamped to 0..1. */
@Composable
fun CabinProgressBar(
    fraction: Float,
    modifier: Modifier = Modifier,
    color: Color = CabinTheme.look.colors.primary,
    trackColor: Color = CabinTheme.look.colors.surfaceHigh,
    height: Dp = CabinKitMetrics.progressThin,
) {
    val shape = CabinTheme.look.shapes.control
    Box(
        modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor),
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction.coerceIn(0f, 1f))
                .clip(shape)
                .background(color),
        )
    }
}

/** Dots showing a discrete level (seat heat 2 of 3). Decorative; describe the level on the parent. */
@Composable
fun CabinLevelDots(
    level: Int,
    max: Int,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapXs + 1.dp)) {
        repeat(max) { i ->
            Box(
                Modifier
                    .size(CabinKitMetrics.levelDot)
                    .clip(CircleShape)
                    .background(if (i < level) color else color.copy(alpha = 0.3f)),
            )
        }
    }
}

/**
 * "Opens when parked" hint shown in place of content the Restriction Engine
 * blocks. Muted, with a lock icon; never a modal.
 */
@Composable
fun CabinRestrictedHint(
    text: String,
    modifier: Modifier = Modifier,
) {
    val look = CabinTheme.look
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.spacedBy(CabinKitMetrics.gapSm + 2.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(CabinIcons.Lock, contentDescription = null, tint = look.colors.onSurfaceMuted, modifier = Modifier.size(CabinKitMetrics.iconSm))
        Text(text, style = look.type.body, color = look.colors.onSurfaceMuted)
    }
}

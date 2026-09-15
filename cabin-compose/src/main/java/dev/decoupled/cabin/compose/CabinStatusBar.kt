package dev.decoupled.cabin.compose

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.R
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.theme.CabinColors
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Glanceable vehicle / system status chrome.
 *
 * Items are an ordered list. Signal-backed glyphs render unavailable / stale /
 * fault exhaustively. Deep links are gated via [LocalCabinComplianceState].
 *
 * Spec: docs/components/specs/status-bar.md
 *
 * **Experimental** Compose parity with Views `CabinStatusBarView` (Alpha).
 */
@Composable
fun CabinStatusBar(
    items: List<CabinStatusItem>,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val height = CabinTokens.Component.StatusBar.height.dp.dp
    val gap = CabinTokens.Component.StatusBar.itemGap.dp.dp
    val unavailableText = stringResource(R.string.cabin_status_unavailable)
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(colors.container)
            .padding(horizontal = gap / 2)
            .testTag("cabin_status_bar"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        items.forEach { item ->
            when (item) {
                is CabinStatusGlyph -> {
                    val presentation = presentStatusSignal(
                        item = item,
                        unavailableText = unavailableText,
                        unavailableA11y = { label ->
                            context.getString(R.string.cabin_status_unavailable_a11y, label)
                        },
                        staleText = { value ->
                            context.getString(R.string.cabin_status_stale_value, value)
                        },
                        staleA11y = { label ->
                            context.getString(R.string.cabin_status_stale_a11y, label)
                        },
                        faultText = { code ->
                            context.getString(R.string.cabin_status_fault, code)
                        },
                        faultA11y = { label, code ->
                            context.getString(R.string.cabin_status_fault_a11y, label, code)
                        },
                    )
                    StatusGlyphItem(
                        item = item,
                        presentation = presentation,
                        colors = colors,
                        disposition = item.deepLink?.let {
                            dispositionOf(compliance, it.interaction)
                        },
                        gap = gap,
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusGlyphItem(
    item: CabinStatusGlyph,
    presentation: StatusPresentation,
    colors: CabinColors,
    disposition: GateDisposition?,
    gap: androidx.compose.ui.unit.Dp,
) {
    val iconSize = CabinTokens.Component.StatusBar.iconSize.dp.dp
    val statusSize = CabinTokens.Type.Role.Status.size.sp.sp
    val contentColor = toneColor(colors, presentation.tone)
    val alpha = disposition?.let { gateAlpha(it) } ?: 1f
    val deepLink = item.deepLink
    val activatable = deepLink != null && disposition == GateDisposition.Allow

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = gap / 2)
            .alpha(alpha)
            .testTag(item.id)
            .semantics { contentDescription = presentation.contentDescription }
            .then(
                if (activatable) {
                    Modifier.clickable(onClick = deepLink!!.onActivate)
                } else {
                    Modifier
                },
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (item.icon != null || presentation.showIconPlaceholder) {
            if (item.icon != null) {
                Image(
                    painter = item.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = gap / 2),
                    colorFilter = ColorFilter.tint(contentColor),
                )
            } else {
                BoxPlaceholder(
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = gap / 2),
                    color = contentColor,
                )
            }
        }
        BasicText(
            text = presentation.text.toString(),
            style = TextStyle(
                color = contentColor,
                fontSize = statusSize,
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier.testTag("${item.id}_text"),
        )
    }
}

@Composable
private fun BoxPlaceholder(
    modifier: Modifier,
    color: Color,
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier.background(color.copy(alpha = 0.35f)),
    )
}

internal fun toneColor(colors: CabinColors, tone: StatusTone): Color = when (tone) {
    StatusTone.Normal -> colors.onContainer
    StatusTone.Degraded -> colors.outline
    StatusTone.Warning -> colors.warning
    StatusTone.Fault -> colors.error
    StatusTone.Charging -> colors.charging
}

/**
 * Exhaustive Signal → presentation (parity with Views Status Bar).
 *
 * Unavailable / stale / fault always override [CabinStatusEmphasis].
 */
@VisibleForTesting
internal fun presentStatusSignal(
    item: CabinStatusGlyph,
    unavailableText: String,
    unavailableA11y: (String) -> String,
    staleText: (String) -> String,
    staleA11y: (String) -> String,
    faultText: (String) -> String,
    faultA11y: (String, String) -> String,
): StatusPresentation {
    val signal = item.signal
    if (signal == null) {
        return StatusPresentation(
            text = item.text ?: "",
            contentDescription = item.contentDescription,
            tone = toneForLiveEmphasis(item.emphasis),
            showIconPlaceholder = false,
        )
    }
    return when (signal) {
        is Signal.Value -> StatusPresentation(
            text = item.text ?: signal.value.toString(),
            contentDescription = item.contentDescription,
            tone = toneForLiveEmphasis(item.emphasis),
            showIconPlaceholder = false,
        )
        is Signal.Unavailable -> StatusPresentation(
            text = unavailableText,
            contentDescription = unavailableA11y(item.contentDescription),
            tone = StatusTone.Degraded,
            showIconPlaceholder = true,
        )
        is Signal.Stale -> StatusPresentation(
            text = staleText(item.text?.toString() ?: signal.last.toString()),
            contentDescription = staleA11y(item.contentDescription),
            tone = StatusTone.Degraded,
            showIconPlaceholder = false,
        )
        is Signal.Fault -> StatusPresentation(
            text = faultText(signal.code),
            contentDescription = faultA11y(item.contentDescription, signal.code),
            tone = StatusTone.Fault,
            showIconPlaceholder = true,
        )
    }
}

internal fun toneForLiveEmphasis(emphasis: CabinStatusEmphasis): StatusTone =
    when (emphasis) {
        CabinStatusEmphasis.None -> StatusTone.Normal
        CabinStatusEmphasis.Warning -> StatusTone.Warning
        CabinStatusEmphasis.Charging -> StatusTone.Charging
    }

/** Status Bar item contract. */
sealed interface CabinStatusItem {
    val id: String
    val contentDescription: String
}

/**
 * Glyph + optional text / signal / deep link.
 *
 * When [signal] is set, presentation is derived exhaustively from its state.
 * [emphasis] applies Theme Kit warning / charging colors for live values;
 * unavailable / stale / fault always override emphasis for honest degradation.
 */
@Immutable
data class CabinStatusGlyph(
    override val id: String,
    override val contentDescription: String,
    val icon: Painter? = null,
    val text: String? = null,
    val signal: Signal<*>? = null,
    val deepLink: StatusDeepLink? = null,
    val emphasis: CabinStatusEmphasis = CabinStatusEmphasis.None,
) : CabinStatusItem

/**
 * Live-value presentation emphasis for Status Bar glyphs.
 *
 * Warning / Charging map to Theme Kit safety and charging colors (night
 * contrast locked). Not used when [Signal] is unavailable, stale, or faulted.
 */
enum class CabinStatusEmphasis {
    None,
    Warning,
    Charging,
}

/**
 * Optional deep link from a status item.
 *
 * [opensSettings] selects settings vs informational Restriction Engine matrix
 * rows ([CabinInteraction.StatusDeepLinkSettings] /
 * [CabinInteraction.StatusDeepLinkInformational]).
 */
@Immutable
data class StatusDeepLink(
    val opensSettings: Boolean,
    val onActivate: () -> Unit,
) {
    val interaction: CabinInteraction
        get() = if (opensSettings) {
            CabinInteraction.StatusDeepLinkSettings
        } else {
            CabinInteraction.StatusDeepLinkInformational
        }
}

internal enum class StatusTone {
    Normal,
    Degraded,
    Warning,
    Fault,
    Charging,
}

@Immutable
internal data class StatusPresentation(
    val text: CharSequence,
    val contentDescription: String,
    val tone: StatusTone,
    val showIconPlaceholder: Boolean,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinStatusBarTokens {
    val heightDp: Float = CabinTokens.Component.StatusBar.height.dp
    val iconSizeDp: Float = CabinTokens.Component.StatusBar.iconSize.dp
    val itemGapDp: Float = CabinTokens.Component.StatusBar.itemGap.dp
}

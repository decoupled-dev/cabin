package dev.decoupled.cabin.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compose.compliance.LocalCabinComplianceState
import dev.decoupled.cabin.compose.compliance.dispositionOf
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinTokens

/**
 * Persistent wayfinding chrome with leading / center / trailing slots.
 *
 * Slot configuration is data-driven — OEMs configure without forking.
 * Activation is gated via [LocalCabinComplianceState] (fail-closed when absent).
 *
 * Spec: docs/components/specs/system-bar.md
 *
 * **Experimental** Compose parity with Views `CabinSystemBarView` (Alpha).
 * Does not replace Views for SystemUI Soong adoption.
 */
@Composable
fun CabinSystemBar(
    slots: CabinSystemBarSlots,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    val compliance = LocalCabinComplianceState.current
    val height = CabinTokens.Component.SystemBar.height.dp.dp
    val gap = CabinTokens.Component.SystemBar.gap.dp.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(colors.container)
            .testTag("cabin_system_bar"),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = gap / 2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                slots.leading.forEach { entry ->
                    SystemBarEntry(
                        entry = entry,
                        contentColor = colors.onContainer,
                        disposition = dispositionOf(compliance, entry.interaction),
                    )
                }
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                slots.center.forEach { entry ->
                    SystemBarEntry(
                        entry = entry,
                        contentColor = colors.onContainer,
                        disposition = dispositionOf(compliance, entry.interaction),
                    )
                }
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                slots.trailing.forEach { entry ->
                    SystemBarEntry(
                        entry = entry,
                        contentColor = colors.onContainer,
                        disposition = dispositionOf(compliance, entry.interaction),
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemBarEntry(
    entry: CabinSystemBarEntry,
    contentColor: Color,
    disposition: GateDisposition,
) {
    val tokenMin = CabinTokens.Component.SystemBar.itemMinSize.dp.dp
    val complianceMin = LocalCabinComplianceState.current.touchTargetMinDp().dp
    val itemMin = maxOf(tokenMin, complianceMin)
    val iconSize = CabinTokens.Component.SystemBar.iconSize.dp.dp
    val gap = CabinTokens.Component.SystemBar.gap.dp.dp
    val labelSize = CabinTokens.Type.Role.Label.size.sp.sp
    val alpha = gateAlpha(disposition)
    val activatable = disposition == GateDisposition.Allow

    Column(
        modifier = Modifier
            .widthIn(min = itemMin)
            .height(itemMin)
            .padding(gap / 2)
            .alpha(alpha)
            .testTag(entry.id)
            .semantics { contentDescription = entry.contentDescription }
            .then(
                if (activatable) {
                    Modifier.clickable(onClick = entry.onActivate)
                } else {
                    Modifier
                },
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (entry.icon != null) {
            Image(
                painter = entry.icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                colorFilter = ColorFilter.tint(contentColor),
            )
        }
        if (!entry.label.isNullOrBlank()) {
            BasicText(
                text = entry.label,
                style = TextStyle(
                    color = contentColor,
                    fontSize = labelSize,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
    }
}

internal fun gateAlpha(disposition: GateDisposition): Float = when (disposition) {
    GateDisposition.Allow -> 1f
    GateDisposition.Substitute -> 0.55f
    GateDisposition.Block -> 0.4f
}

/** Slotted System Bar configuration (OEM-extensible without core forks). */
@Immutable
data class CabinSystemBarSlots(
    val leading: List<CabinSystemBarEntry> = emptyList(),
    val center: List<CabinSystemBarEntry> = emptyList(),
    val trailing: List<CabinSystemBarEntry> = emptyList(),
)

/**
 * One System Bar slot entry.
 *
 * @property interaction declared category for Restriction Engine gating
 * @property contentDescription required for icon-only entries (a11y)
 */
@Immutable
data class CabinSystemBarEntry(
    val id: String,
    val icon: Painter? = null,
    val label: String? = null,
    val contentDescription: String,
    val interaction: CabinInteraction,
    val onActivate: () -> Unit,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinSystemBarTokens {
    val heightDp: Float = CabinTokens.Component.SystemBar.height.dp
    val iconSizeDp: Float = CabinTokens.Component.SystemBar.iconSize.dp
    val itemMinSizeDp: Float = CabinTokens.Component.SystemBar.itemMinSize.dp
    val gapDp: Float = CabinTokens.Component.SystemBar.gap.dp
}

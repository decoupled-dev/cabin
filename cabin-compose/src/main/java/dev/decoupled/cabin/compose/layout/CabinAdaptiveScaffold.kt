package dev.decoupled.cabin.compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.foundation.CabinAdaptiveScaffoldKind
import dev.decoupled.cabin.foundation.CabinDisplayProfile
import dev.decoupled.cabin.foundation.CabinScaffoldInsets
import dev.decoupled.cabin.foundation.CabinWindowSizeClass
import dev.decoupled.cabin.tokens.CabinTokens

val LocalCabinDisplayProfile = staticCompositionLocalOf {
    CabinDisplayProfile.StandardCenter
}

val LocalCabinScaffoldInsets = staticCompositionLocalOf {
    CabinScaffoldInsets.None
}

/**
 * Pick a scaffold kind from an automotive size class.
 *
 * Compact / cluster → single pane; ultrawide → dashboard grid; portrait → stacked.
 */
fun cabinScaffoldKindFor(sizeClass: CabinWindowSizeClass): CabinAdaptiveScaffoldKind =
    when (sizeClass) {
        CabinWindowSizeClass.Cluster,
        CabinWindowSizeClass.CompactCenter,
        -> CabinAdaptiveScaffoldKind.SinglePane
        CabinWindowSizeClass.TallPortrait,
        -> CabinAdaptiveScaffoldKind.PortraitStacked
        CabinWindowSizeClass.Ultrawide,
        -> CabinAdaptiveScaffoldKind.DashboardGrid
        CabinWindowSizeClass.RearSeat,
        CabinWindowSizeClass.Passenger,
        CabinWindowSizeClass.StandardLandscape,
        CabinWindowSizeClass.LargeLandscape,
        -> CabinAdaptiveScaffoldKind.ListDetail
    }

@Composable
fun CabinAdaptiveScaffold(
    profile: CabinDisplayProfile = LocalCabinDisplayProfile.current,
    insets: CabinScaffoldInsets = LocalCabinScaffoldInsets.current,
    kind: CabinAdaptiveScaffoldKind = cabinScaffoldKindFor(profile.sizeClass),
    modifier: Modifier = Modifier,
    primary: @Composable () -> Unit,
    secondary: @Composable (() -> Unit)? = null,
    tertiary: @Composable (() -> Unit)? = null,
) {
    val colors = LocalCabinColors.current
    CompositionLocalProvider(
        LocalCabinDisplayProfile provides profile,
        LocalCabinScaffoldInsets provides insets,
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.surface)
                .padding(
                    start = (insets.startDp + insets.curvedNoGoStartDp).dp,
                    top = insets.topDp.dp,
                    end = (insets.endDp + insets.curvedNoGoEndDp).dp,
                    bottom = (insets.bottomDp + insets.dockDp).dp,
                )
                .testTag("cabin_adaptive_scaffold"),
        ) {
            when (kind) {
                CabinAdaptiveScaffoldKind.SinglePane,
                CabinAdaptiveScaffoldKind.Immersive,
                -> Box(Modifier.fillMaxSize().testTag("scaffold_primary")) { primary() }
                CabinAdaptiveScaffoldKind.PortraitStacked -> Column(Modifier.fillMaxSize()) {
                    Box(Modifier.weight(1f).fillMaxWidth().testTag("scaffold_primary")) {
                        primary()
                    }
                    if (secondary != null) {
                        Box(Modifier.weight(1f).fillMaxWidth().testTag("scaffold_secondary")) {
                            secondary()
                        }
                    }
                }
                CabinAdaptiveScaffoldKind.DashboardGrid -> Row(
                    Modifier
                        .fillMaxSize()
                        .horizontalScroll(rememberScrollState()),
                ) {
                    Box(Modifier.width(420.dp).fillMaxHeight().testTag("scaffold_primary")) {
                        primary()
                    }
                    if (secondary != null) {
                        Box(Modifier.width(420.dp).fillMaxHeight().testTag("scaffold_secondary")) {
                            secondary()
                        }
                    }
                    if (tertiary != null) {
                        Box(Modifier.width(420.dp).fillMaxHeight().testTag("scaffold_tertiary")) {
                            tertiary()
                        }
                    }
                }
                CabinAdaptiveScaffoldKind.ListDetail,
                CabinAdaptiveScaffoldKind.SupportingPane,
                CabinAdaptiveScaffoldKind.TwoPane,
                CabinAdaptiveScaffoldKind.SplitScreen,
                CabinAdaptiveScaffoldKind.ThreePane,
                -> Row(Modifier.fillMaxSize()) {
                    Box(Modifier.weight(1f).fillMaxHeight().testTag("scaffold_primary")) {
                        primary()
                    }
                    Box(
                        Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .background(colors.outline),
                    )
                    Box(Modifier.weight(1f).fillMaxHeight().testTag("scaffold_secondary")) {
                        if (secondary != null) {
                            secondary()
                        } else {
                            BasicText(
                                "Secondary pane",
                                style = TextStyle(color = colors.outline, fontSize = 14.sp),
                                modifier = Modifier.padding(CabinTokens.Space.md.dp.dp),
                            )
                        }
                    }
                    if (kind == CabinAdaptiveScaffoldKind.ThreePane && tertiary != null) {
                        Box(
                            Modifier
                                .width(1.dp)
                                .fillMaxHeight()
                                .background(colors.outline),
                        )
                        Box(Modifier.weight(1f).fillMaxHeight().testTag("scaffold_tertiary")) {
                            tertiary()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CabinSizeClassSwitcher(
    selectedClass: CabinWindowSizeClass,
    onSelect: (CabinWindowSizeClass) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCabinColors.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .testTag("cabin_size_class_switcher"),
    ) {
        CabinWindowSizeClass.entries.forEach { sizeClass ->
            val selectedNow = sizeClass == selectedClass
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .border(
                        width = 1.dp,
                        color = if (selectedNow) colors.primary else colors.outline,
                    )
                    .background(
                        if (selectedNow) colors.primary.copy(alpha = 0.12f) else colors.surface,
                    )
                    .clickable { onSelect(sizeClass) }
                    .semantics {
                        role = Role.Button
                        selected = selectedNow
                    }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .testTag("size_class_${sizeClass.name}"),
            ) {
                BasicText(
                    text = sizeClass.name,
                    style = TextStyle(color = colors.onSurface, fontSize = 13.sp),
                )
            }
        }
    }
}

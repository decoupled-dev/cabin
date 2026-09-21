@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.surface

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.surface.*

/**
 * Experimental scaffold: Surface.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSurface(
    state: CabinSurfaceState = CabinSurfaceState(),
    onAction: (CabinSurfaceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Surface" },
        family = "surface",
        testTag = "cabin_surface",
        interaction = CabinSurfaceInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSurfaceAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Card.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCard(
    state: CabinCardState = CabinCardState(),
    onAction: (CabinCardAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Card" },
        family = "surface",
        testTag = "cabin_card",
        interaction = CabinCardInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCardAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Tile.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTile(
    state: CabinTileState = CabinTileState(),
    onAction: (CabinTileAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Tile" },
        family = "surface",
        testTag = "cabin_tile",
        interaction = CabinTileInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTileAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Banner.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBanner(
    state: CabinBannerState = CabinBannerState(),
    onAction: (CabinBannerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Banner" },
        family = "surface",
        testTag = "cabin_banner",
        interaction = CabinBannerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBannerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Panel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPanel(
    state: CabinPanelState = CabinPanelState(),
    onAction: (CabinPanelAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Panel" },
        family = "surface",
        testTag = "cabin_panel",
        interaction = CabinPanelInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPanelAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Section container.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSectionContainer(
    state: CabinSectionContainerState = CabinSectionContainerState(),
    onAction: (CabinSectionContainerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Section container" },
        family = "surface",
        testTag = "cabin_section_container",
        interaction = CabinSectionContainerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSectionContainerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Expandable container.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinExpandableContainer(
    state: CabinExpandableContainerState = CabinExpandableContainerState(),
    onAction: (CabinExpandableContainerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Expandable container" },
        family = "surface",
        testTag = "cabin_expandable_container",
        interaction = CabinExpandableContainerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinExpandableContainerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Accordion.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAccordion(
    state: CabinAccordionState = CabinAccordionState(),
    onAction: (CabinAccordionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Accordion" },
        family = "surface",
        testTag = "cabin_accordion",
        interaction = CabinAccordionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAccordionAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Divider.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDivider(
    state: CabinDividerState = CabinDividerState(),
    onAction: (CabinDividerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Divider" },
        family = "surface",
        testTag = "cabin_divider",
        interaction = CabinDividerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDividerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Spacer.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSpacer(
    state: CabinSpacerState = CabinSpacerState(),
    onAction: (CabinSpacerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Spacer" },
        family = "surface",
        testTag = "cabin_spacer",
        interaction = CabinSpacerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSpacerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Scrim.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinScrimOverlay(
    state: CabinScrimOverlayState = CabinScrimOverlayState(),
    onAction: (CabinScrimOverlayAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Scrim" },
        family = "surface",
        testTag = "cabin_scrim_overlay",
        interaction = CabinScrimOverlayInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinScrimOverlayAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Paged scroll.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPagedScroll(
    state: CabinPagedScrollState = CabinPagedScrollState(),
    onAction: (CabinPagedScrollAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Paged scroll" },
        family = "surface",
        testTag = "cabin_paged_scroll",
        interaction = CabinPagedScrollInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPagedScrollAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Scroll position indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinScrollPositionIndicator(
    state: CabinScrollPositionIndicatorState = CabinScrollPositionIndicatorState(),
    onAction: (CabinScrollPositionIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Scroll position indicator" },
        family = "surface",
        testTag = "cabin_scroll_position_indicator",
        interaction = CabinScrollPositionIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinScrollPositionIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Scroll buttons.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinScrollButtons(
    state: CabinScrollButtonsState = CabinScrollButtonsState(),
    onAction: (CabinScrollButtonsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Scroll buttons" },
        family = "surface",
        testTag = "cabin_scroll_buttons",
        interaction = CabinScrollButtonsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinScrollButtonsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Sticky header.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinStickyHeader(
    state: CabinStickyHeaderState = CabinStickyHeaderState(),
    onAction: (CabinStickyHeaderAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Sticky header" },
        family = "surface",
        testTag = "cabin_sticky_header",
        interaction = CabinStickyHeaderInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinStickyHeaderAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Pull-free refresh.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPullFreeRefresh(
    state: CabinPullFreeRefreshState = CabinPullFreeRefreshState(),
    onAction: (CabinPullFreeRefreshAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Pull-free refresh" },
        family = "surface",
        testTag = "cabin_pull_free_refresh",
        interaction = CabinPullFreeRefreshInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPullFreeRefreshAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Bottom sheet.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBottomSheet(
    state: CabinBottomSheetState = CabinBottomSheetState(),
    onAction: (CabinBottomSheetAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Bottom sheet" },
        family = "surface",
        testTag = "cabin_bottom_sheet",
        interaction = CabinBottomSheetInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBottomSheetAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Side sheet.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSideSheet(
    state: CabinSideSheetState = CabinSideSheetState(),
    onAction: (CabinSideSheetAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Side sheet" },
        family = "surface",
        testTag = "cabin_side_sheet",
        interaction = CabinSideSheetInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSideSheetAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Modal drawer.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinModalDrawer(
    state: CabinModalDrawerState = CabinModalDrawerState(),
    onAction: (CabinModalDrawerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Modal drawer" },
        family = "surface",
        testTag = "cabin_modal_drawer",
        interaction = CabinModalDrawerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinModalDrawerAction.Activate) },
        modifier = modifier,
    )
}

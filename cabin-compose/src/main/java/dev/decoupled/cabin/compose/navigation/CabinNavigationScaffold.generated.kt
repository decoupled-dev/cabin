@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.navigation

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.navigation.*

/**
 * Experimental scaffold: Navigation dock.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNavigationDock(
    state: CabinNavigationDockState = CabinNavigationDockState(),
    onAction: (CabinNavigationDockAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Navigation dock" },
        family = "navigation",
        testTag = "cabin_navigation_dock",
        interaction = CabinNavigationDockInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNavigationDockAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Navigation rail.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinNavigationRail(
    state: CabinNavigationRailState = CabinNavigationRailState(),
    onAction: (CabinNavigationRailAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Navigation rail" },
        family = "navigation",
        testTag = "cabin_navigation_rail",
        interaction = CabinNavigationRailInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinNavigationRailAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Bottom bar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBottomBar(
    state: CabinBottomBarState = CabinBottomBarState(),
    onAction: (CabinBottomBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Bottom bar" },
        family = "navigation",
        testTag = "cabin_bottom_bar",
        interaction = CabinBottomBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBottomBarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Side drawer.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSideDrawer(
    state: CabinSideDrawerState = CabinSideDrawerState(),
    onAction: (CabinSideDrawerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Side drawer" },
        family = "navigation",
        testTag = "cabin_side_drawer",
        interaction = CabinSideDrawerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSideDrawerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Top app bar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTopBar(
    state: CabinTopBarState = CabinTopBarState(),
    onAction: (CabinTopBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Top app bar" },
        family = "navigation",
        testTag = "cabin_top_bar",
        interaction = CabinTopBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTopBarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Tabs.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTabs(
    state: CabinTabsState = CabinTabsState(),
    onAction: (CabinTabsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Tabs" },
        family = "navigation",
        testTag = "cabin_tabs",
        interaction = CabinTabsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTabsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Breadcrumbs.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBreadcrumbs(
    state: CabinBreadcrumbsState = CabinBreadcrumbsState(),
    onAction: (CabinBreadcrumbsAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Breadcrumbs" },
        family = "navigation",
        testTag = "cabin_breadcrumbs",
        interaction = CabinBreadcrumbsInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBreadcrumbsAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Back / up.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinBackAffordance(
    state: CabinBackAffordanceState = CabinBackAffordanceState(),
    onAction: (CabinBackAffordanceAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Back / up" },
        family = "navigation",
        testTag = "cabin_back_affordance",
        interaction = CabinBackAffordanceInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinBackAffordanceAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Hardware back handler.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinHardwareBackHandler(
    state: CabinHardwareBackHandlerState = CabinHardwareBackHandlerState(),
    onAction: (CabinHardwareBackHandlerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Hardware back handler" },
        family = "navigation",
        testTag = "cabin_hardware_back_handler",
        interaction = CabinHardwareBackHandlerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinHardwareBackHandlerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Page indicator.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPageIndicator(
    state: CabinPageIndicatorState = CabinPageIndicatorState(),
    onAction: (CabinPageIndicatorAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Page indicator" },
        family = "navigation",
        testTag = "cabin_page_indicator",
        interaction = CabinPageIndicatorInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPageIndicatorAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Pager.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPager(
    state: CabinPagerState = CabinPagerState(),
    onAction: (CabinPagerAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Pager" },
        family = "navigation",
        testTag = "cabin_pager",
        interaction = CabinPagerInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPagerAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Carousel.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinCarousel(
    state: CabinCarouselState = CabinCarouselState(),
    onAction: (CabinCarouselAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Carousel" },
        family = "navigation",
        testTag = "cabin_carousel",
        interaction = CabinCarouselInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinCarouselAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Wizard stepper.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinWizardStepper(
    state: CabinWizardStepperState = CabinWizardStepperState(),
    onAction: (CabinWizardStepperAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Wizard stepper" },
        family = "navigation",
        testTag = "cabin_wizard_stepper",
        interaction = CabinWizardStepperInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinWizardStepperAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Deep-link helper.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDeepLinkHelper(
    state: CabinDeepLinkHelperState = CabinDeepLinkHelperState(),
    onAction: (CabinDeepLinkHelperAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Deep-link helper" },
        family = "navigation",
        testTag = "cabin_deep_link_helper",
        interaction = CabinDeepLinkHelperInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDeepLinkHelperAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Focus area.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFocusArea(
    state: CabinFocusAreaState = CabinFocusAreaState(),
    onAction: (CabinFocusAreaAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Focus area" },
        family = "navigation",
        testTag = "cabin_focus_area",
        interaction = CabinFocusAreaInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFocusAreaAction.Activate) },
        modifier = modifier,
    )
}

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.navigation

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinNavigationDockState(
    val label: String = "Navigation dock",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNavigationDockAction {
    data object Activate : CabinNavigationDockAction
}

val CabinNavigationDockInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinNavigationRailState(
    val label: String = "Navigation rail",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinNavigationRailAction {
    data object Activate : CabinNavigationRailAction
}

val CabinNavigationRailInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBottomBarState(
    val label: String = "Bottom bar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBottomBarAction {
    data object Activate : CabinBottomBarAction
}

val CabinBottomBarInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinSideDrawerState(
    val label: String = "Side drawer",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSideDrawerAction {
    data object Activate : CabinSideDrawerAction
}

val CabinSideDrawerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinTopBarState(
    val label: String = "Top app bar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTopBarAction {
    data object Activate : CabinTopBarAction
}

val CabinTopBarInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinTabsState(
    val label: String = "Tabs",
    val variant: String = "fixed",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTabsAction {
    data object Activate : CabinTabsAction
}

val CabinTabsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBreadcrumbsState(
    val label: String = "Breadcrumbs",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBreadcrumbsAction {
    data object Activate : CabinBreadcrumbsAction
}

val CabinBreadcrumbsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBackAffordanceState(
    val label: String = "Back / up",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBackAffordanceAction {
    data object Activate : CabinBackAffordanceAction
}

val CabinBackAffordanceInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinHardwareBackHandlerState(
    val label: String = "Hardware back handler",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinHardwareBackHandlerAction {
    data object Activate : CabinHardwareBackHandlerAction
}

val CabinHardwareBackHandlerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinPageIndicatorState(
    val label: String = "Page indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPageIndicatorAction {
    data object Activate : CabinPageIndicatorAction
}

val CabinPageIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinPagerState(
    val label: String = "Pager",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPagerAction {
    data object Activate : CabinPagerAction
}

val CabinPagerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinCarouselState(
    val label: String = "Carousel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCarouselAction {
    data object Activate : CabinCarouselAction
}

val CabinCarouselInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinWizardStepperState(
    val label: String = "Wizard stepper",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinWizardStepperAction {
    data object Activate : CabinWizardStepperAction
}

val CabinWizardStepperInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinDeepLinkHelperState(
    val label: String = "Deep-link helper",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDeepLinkHelperAction {
    data object Activate : CabinDeepLinkHelperAction
}

val CabinDeepLinkHelperInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkInformational

@CabinScaffold
data class CabinFocusAreaState(
    val label: String = "Focus area",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFocusAreaAction {
    data object Activate : CabinFocusAreaAction
}

val CabinFocusAreaInteraction: CabinInteraction = CabinInteraction.NavigateSimple

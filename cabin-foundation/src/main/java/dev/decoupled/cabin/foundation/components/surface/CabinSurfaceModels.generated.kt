@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.surface

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinSurfaceState(
    val label: String = "Surface",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSurfaceAction {
    data object Activate : CabinSurfaceAction
}

val CabinSurfaceInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinCardState(
    val label: String = "Card",
    val variant: String = "filled",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinCardAction {
    data object Activate : CabinCardAction
}

val CabinCardInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTileState(
    val label: String = "Tile",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTileAction {
    data object Activate : CabinTileAction
}

val CabinTileInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBannerState(
    val label: String = "Banner",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBannerAction {
    data object Activate : CabinBannerAction
}

val CabinBannerInteraction: CabinInteraction = CabinInteraction.StatusDeepLinkInformational

@CabinScaffold
data class CabinPanelState(
    val label: String = "Panel",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPanelAction {
    data object Activate : CabinPanelAction
}

val CabinPanelInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSectionContainerState(
    val label: String = "Section container",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSectionContainerAction {
    data object Activate : CabinSectionContainerAction
}

val CabinSectionContainerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinExpandableContainerState(
    val label: String = "Expandable container",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinExpandableContainerAction {
    data object Activate : CabinExpandableContainerAction
}

val CabinExpandableContainerInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinAccordionState(
    val label: String = "Accordion",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAccordionAction {
    data object Activate : CabinAccordionAction
}

val CabinAccordionInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinDividerState(
    val label: String = "Divider",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDividerAction {
    data object Activate : CabinDividerAction
}

val CabinDividerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinSpacerState(
    val label: String = "Spacer",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSpacerAction {
    data object Activate : CabinSpacerAction
}

val CabinSpacerInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinScrimOverlayState(
    val label: String = "Scrim",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinScrimOverlayAction {
    data object Activate : CabinScrimOverlayAction
}

val CabinScrimOverlayInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinPagedScrollState(
    val label: String = "Paged scroll",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPagedScrollAction {
    data object Activate : CabinPagedScrollAction
}

val CabinPagedScrollInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinScrollPositionIndicatorState(
    val label: String = "Scroll position indicator",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinScrollPositionIndicatorAction {
    data object Activate : CabinScrollPositionIndicatorAction
}

val CabinScrollPositionIndicatorInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinScrollButtonsState(
    val label: String = "Scroll buttons",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinScrollButtonsAction {
    data object Activate : CabinScrollButtonsAction
}

val CabinScrollButtonsInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinStickyHeaderState(
    val label: String = "Sticky header",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinStickyHeaderAction {
    data object Activate : CabinStickyHeaderAction
}

val CabinStickyHeaderInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinPullFreeRefreshState(
    val label: String = "Pull-free refresh",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPullFreeRefreshAction {
    data object Activate : CabinPullFreeRefreshAction
}

val CabinPullFreeRefreshInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinBottomSheetState(
    val label: String = "Bottom sheet",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinBottomSheetAction {
    data object Activate : CabinBottomSheetAction
}

val CabinBottomSheetInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinSideSheetState(
    val label: String = "Side sheet",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSideSheetAction {
    data object Activate : CabinSideSheetAction
}

val CabinSideSheetInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinModalDrawerState(
    val label: String = "Modal drawer",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinModalDrawerAction {
    data object Activate : CabinModalDrawerAction
}

val CabinModalDrawerInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

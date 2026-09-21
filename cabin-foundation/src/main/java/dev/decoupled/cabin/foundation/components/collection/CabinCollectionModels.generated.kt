@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.foundation.components.collection

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

@CabinScaffold
data class CabinListItemState(
    val label: String = "List item",
    val variant: String = "one-line",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinListItemAction {
    data object Activate : CabinListItemAction
}

val CabinListItemInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinPagedListState(
    val label: String = "Paged list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinPagedListAction {
    data object Activate : CabinPagedListAction
}

val CabinPagedListInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinSectionedListState(
    val label: String = "Sectioned list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSectionedListAction {
    data object Activate : CabinSectionedListAction
}

val CabinSectionedListInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinGroupedListState(
    val label: String = "Grouped list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinGroupedListAction {
    data object Activate : CabinGroupedListAction
}

val CabinGroupedListInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinJumpListState(
    val label: String = "Jump list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinJumpListAction {
    data object Activate : CabinJumpListAction
}

val CabinJumpListInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinAppGridState(
    val label: String = "App grid",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAppGridAction {
    data object Activate : CabinAppGridAction
}

val CabinAppGridInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinMediaGridState(
    val label: String = "Media grid",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinMediaGridAction {
    data object Activate : CabinMediaGridAction
}

val CabinMediaGridInteraction: CabinInteraction = CabinInteraction.MediaComplex

@CabinScaffold
data class CabinAdaptiveGridState(
    val label: String = "Adaptive grid",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinAdaptiveGridAction {
    data object Activate : CabinAdaptiveGridAction
}

val CabinAdaptiveGridInteraction: CabinInteraction = CabinInteraction.NavigateSimple

@CabinScaffold
data class CabinDataTableState(
    val label: String = "Data table",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDataTableAction {
    data object Activate : CabinDataTableAction
}

val CabinDataTableInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinKeyValueRowState(
    val label: String = "Key-value row",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinKeyValueRowAction {
    data object Activate : CabinKeyValueRowAction
}

val CabinKeyValueRowInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinInfoRowState(
    val label: String = "Info row",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinInfoRowAction {
    data object Activate : CabinInfoRowAction
}

val CabinInfoRowInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinDescriptionListState(
    val label: String = "Description list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinDescriptionListAction {
    data object Activate : CabinDescriptionListAction
}

val CabinDescriptionListInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinTimelineListState(
    val label: String = "Timeline list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinTimelineListAction {
    data object Activate : CabinTimelineListAction
}

val CabinTimelineListInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinFilterBarState(
    val label: String = "Filter bar",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinFilterBarAction {
    data object Activate : CabinFilterBarAction
}

val CabinFilterBarInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinSortControlState(
    val label: String = "Sort control",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSortControlAction {
    data object Activate : CabinSortControlAction
}

val CabinSortControlInteraction: CabinInteraction = CabinInteraction.FilterOrSort

@CabinScaffold
data class CabinSearchResultListState(
    val label: String = "Search result list",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinSearchResultListAction {
    data object Activate : CabinSearchResultListAction
}

val CabinSearchResultListInteraction: CabinInteraction = CabinInteraction.OpenComplexApp

@CabinScaffold
data class CabinEmptyStateState(
    val label: String = "Empty state",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinEmptyStateAction {
    data object Activate : CabinEmptyStateAction
}

val CabinEmptyStateInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinErrorStateState(
    val label: String = "Error state",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinErrorStateAction {
    data object Activate : CabinErrorStateAction
}

val CabinErrorStateInteraction: CabinInteraction = CabinInteraction.Glance

@CabinScaffold
data class CabinLoadingCollectionState(
    val label: String = "Loading collection",
    val variant: String = "",
    val ui: CabinComponentUiState = CabinComponentUiState(),
)

@CabinScaffold
sealed interface CabinLoadingCollectionAction {
    data object Activate : CabinLoadingCollectionAction
}

val CabinLoadingCollectionInteraction: CabinInteraction = CabinInteraction.Glance

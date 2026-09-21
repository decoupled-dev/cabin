@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.compose.collection

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost
import dev.decoupled.cabin.foundation.components.collection.*

/**
 * Experimental scaffold: List item.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinListItem(
    state: CabinListItemState = CabinListItemState(),
    onAction: (CabinListItemAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "List item" },
        family = "collection",
        testTag = "cabin_list_item",
        interaction = CabinListItemInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinListItemAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Paged list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinPagedList(
    state: CabinPagedListState = CabinPagedListState(),
    onAction: (CabinPagedListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Paged list" },
        family = "collection",
        testTag = "cabin_paged_list",
        interaction = CabinPagedListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinPagedListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Sectioned list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSectionedList(
    state: CabinSectionedListState = CabinSectionedListState(),
    onAction: (CabinSectionedListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Sectioned list" },
        family = "collection",
        testTag = "cabin_sectioned_list",
        interaction = CabinSectionedListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSectionedListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Grouped list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinGroupedList(
    state: CabinGroupedListState = CabinGroupedListState(),
    onAction: (CabinGroupedListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Grouped list" },
        family = "collection",
        testTag = "cabin_grouped_list",
        interaction = CabinGroupedListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinGroupedListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Jump list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinJumpList(
    state: CabinJumpListState = CabinJumpListState(),
    onAction: (CabinJumpListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Jump list" },
        family = "collection",
        testTag = "cabin_jump_list",
        interaction = CabinJumpListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinJumpListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: App grid.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAppGrid(
    state: CabinAppGridState = CabinAppGridState(),
    onAction: (CabinAppGridAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "App grid" },
        family = "collection",
        testTag = "cabin_app_grid",
        interaction = CabinAppGridInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAppGridAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Media grid.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinMediaGrid(
    state: CabinMediaGridState = CabinMediaGridState(),
    onAction: (CabinMediaGridAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Media grid" },
        family = "collection",
        testTag = "cabin_media_grid",
        interaction = CabinMediaGridInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinMediaGridAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Adaptive grid.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinAdaptiveGrid(
    state: CabinAdaptiveGridState = CabinAdaptiveGridState(),
    onAction: (CabinAdaptiveGridAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Adaptive grid" },
        family = "collection",
        testTag = "cabin_adaptive_grid",
        interaction = CabinAdaptiveGridInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinAdaptiveGridAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Data table.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDataTable(
    state: CabinDataTableState = CabinDataTableState(),
    onAction: (CabinDataTableAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Data table" },
        family = "collection",
        testTag = "cabin_data_table",
        interaction = CabinDataTableInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDataTableAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Key-value row.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinKeyValueRow(
    state: CabinKeyValueRowState = CabinKeyValueRowState(),
    onAction: (CabinKeyValueRowAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Key-value row" },
        family = "collection",
        testTag = "cabin_key_value_row",
        interaction = CabinKeyValueRowInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinKeyValueRowAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Info row.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinInfoRow(
    state: CabinInfoRowState = CabinInfoRowState(),
    onAction: (CabinInfoRowAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Info row" },
        family = "collection",
        testTag = "cabin_info_row",
        interaction = CabinInfoRowInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinInfoRowAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Description list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinDescriptionList(
    state: CabinDescriptionListState = CabinDescriptionListState(),
    onAction: (CabinDescriptionListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Description list" },
        family = "collection",
        testTag = "cabin_description_list",
        interaction = CabinDescriptionListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinDescriptionListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Timeline list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinTimelineList(
    state: CabinTimelineListState = CabinTimelineListState(),
    onAction: (CabinTimelineListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Timeline list" },
        family = "collection",
        testTag = "cabin_timeline_list",
        interaction = CabinTimelineListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinTimelineListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Filter bar.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinFilterBar(
    state: CabinFilterBarState = CabinFilterBarState(),
    onAction: (CabinFilterBarAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Filter bar" },
        family = "collection",
        testTag = "cabin_filter_bar",
        interaction = CabinFilterBarInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinFilterBarAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Sort control.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSortControl(
    state: CabinSortControlState = CabinSortControlState(),
    onAction: (CabinSortControlAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Sort control" },
        family = "collection",
        testTag = "cabin_sort_control",
        interaction = CabinSortControlInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSortControlAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Search result list.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinSearchResultList(
    state: CabinSearchResultListState = CabinSearchResultListState(),
    onAction: (CabinSearchResultListAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Search result list" },
        family = "collection",
        testTag = "cabin_search_result_list",
        interaction = CabinSearchResultListInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinSearchResultListAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Empty state.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinEmptyState(
    state: CabinEmptyStateState = CabinEmptyStateState(),
    onAction: (CabinEmptyStateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Empty state" },
        family = "collection",
        testTag = "cabin_empty_state",
        interaction = CabinEmptyStateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinEmptyStateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Error state.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinErrorState(
    state: CabinErrorStateState = CabinErrorStateState(),
    onAction: (CabinErrorStateAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Error state" },
        family = "collection",
        testTag = "cabin_error_state",
        interaction = CabinErrorStateInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinErrorStateAction.Activate) },
        modifier = modifier,
    )
}

/**
 * Experimental scaffold: Loading collection.
 * Restriction-aware; token-driven host. Not a production visual.
 */
@dev.decoupled.cabin.foundation.CabinScaffold
@Composable
fun CabinLoadingCollection(
    state: CabinLoadingCollectionState = CabinLoadingCollectionState(),
    onAction: (CabinLoadingCollectionAction) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CabinScaffoldHost(
        title = state.label.ifBlank { "Loading collection" },
        family = "collection",
        testTag = "cabin_loading_collection",
        interaction = CabinLoadingCollectionInteraction,
        ui = state.ui,
        variant = state.variant,
        onActivate = { onAction(CabinLoadingCollectionAction.Activate) },
        modifier = modifier,
    )
}

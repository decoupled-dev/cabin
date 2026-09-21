@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.collection

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.collection.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinListItemState = CabinListItemState()
    private var actionListener: ((CabinListItemAction) -> Unit)? = null

    init {
        bindHost("List item", "collection", "cabin_list_item", CabinListItemInteraction)
        setOnActivate { actionListener?.invoke(CabinListItemAction.Activate) }
    }

    fun bind(state: CabinListItemState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinListItemAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinPagedListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinPagedListState = CabinPagedListState()
    private var actionListener: ((CabinPagedListAction) -> Unit)? = null

    init {
        bindHost("Paged list", "collection", "cabin_paged_list", CabinPagedListInteraction)
        setOnActivate { actionListener?.invoke(CabinPagedListAction.Activate) }
    }

    fun bind(state: CabinPagedListState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinPagedListAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

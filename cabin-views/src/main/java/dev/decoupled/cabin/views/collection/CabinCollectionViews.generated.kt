/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

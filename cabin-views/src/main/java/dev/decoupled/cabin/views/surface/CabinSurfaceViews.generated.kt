@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.surface

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.surface.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinPagedScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinPagedScrollState = CabinPagedScrollState()
    private var actionListener: ((CabinPagedScrollAction) -> Unit)? = null

    init {
        bindHost("Paged scroll", "surface", "cabin_paged_scroll", CabinPagedScrollInteraction)
        setOnActivate { actionListener?.invoke(CabinPagedScrollAction.Activate) }
    }

    fun bind(state: CabinPagedScrollState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinPagedScrollAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.selection

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.selection.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinSwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinSwitchState = CabinSwitchState()
    private var actionListener: ((CabinSwitchAction) -> Unit)? = null

    init {
        bindHost("Switch", "selection", "cabin_switch", CabinSwitchInteraction)
        setOnActivate { actionListener?.invoke(CabinSwitchAction.Activate) }
    }

    fun bind(state: CabinSwitchState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinSwitchAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

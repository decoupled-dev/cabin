@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.action

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.action.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinButtonState = CabinButtonState()
    private var actionListener: ((CabinButtonAction) -> Unit)? = null

    init {
        bindHost("Button", "action", "cabin_button", CabinButtonInteraction)
        setOnActivate { actionListener?.invoke(CabinButtonAction.Activate) }
    }

    fun bind(state: CabinButtonState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinButtonAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinPressHoldRepeaterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinPressHoldRepeaterState = CabinPressHoldRepeaterState()
    private var actionListener: ((CabinPressHoldRepeaterAction) -> Unit)? = null

    init {
        bindHost("Press-and-hold repeater", "action", "cabin_press_hold_repeater", CabinPressHoldRepeaterInteraction)
        setOnActivate { actionListener?.invoke(CabinPressHoldRepeaterAction.Activate) }
    }

    fun bind(state: CabinPressHoldRepeaterState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinPressHoldRepeaterAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

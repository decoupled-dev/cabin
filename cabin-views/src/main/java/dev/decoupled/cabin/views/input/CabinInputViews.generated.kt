@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.input

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.input.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinSliderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinSliderState = CabinSliderState()
    private var actionListener: ((CabinSliderAction) -> Unit)? = null

    init {
        bindHost("Slider", "input", "cabin_slider", CabinSliderInteraction)
        setOnActivate { actionListener?.invoke(CabinSliderAction.Activate) }
    }

    fun bind(state: CabinSliderState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinSliderAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinStepperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinStepperState = CabinStepperState()
    private var actionListener: ((CabinStepperAction) -> Unit)? = null

    init {
        bindHost("Stepper", "input", "cabin_stepper", CabinStepperInteraction)
        setOnActivate { actionListener?.invoke(CabinStepperAction.Activate) }
    }

    fun bind(state: CabinStepperState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinStepperAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

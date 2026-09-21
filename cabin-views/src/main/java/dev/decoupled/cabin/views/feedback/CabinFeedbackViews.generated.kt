@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.feedback

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.feedback.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinDialogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinDialogState = CabinDialogState()
    private var actionListener: ((CabinDialogAction) -> Unit)? = null

    init {
        bindHost("Dialog", "feedback", "cabin_dialog", CabinDialogInteraction)
        setOnActivate { actionListener?.invoke(CabinDialogAction.Activate) }
    }

    fun bind(state: CabinDialogState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinDialogAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

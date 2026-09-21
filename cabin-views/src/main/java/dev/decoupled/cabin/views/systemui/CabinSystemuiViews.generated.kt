@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.systemui

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.systemui.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinQuickSettingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinQuickSettingsState = CabinQuickSettingsState()
    private var actionListener: ((CabinQuickSettingsAction) -> Unit)? = null

    init {
        bindHost("Quick settings", "systemui", "cabin_quick_settings", CabinQuickSettingsInteraction)
        setOnActivate { actionListener?.invoke(CabinQuickSettingsAction.Activate) }
    }

    fun bind(state: CabinQuickSettingsState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinQuickSettingsAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.navigation

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.navigation.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinNavigationDockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinNavigationDockState = CabinNavigationDockState()
    private var actionListener: ((CabinNavigationDockAction) -> Unit)? = null

    init {
        bindHost("Navigation dock", "navigation", "cabin_navigation_dock", CabinNavigationDockInteraction)
        setOnActivate { actionListener?.invoke(CabinNavigationDockAction.Activate) }
    }

    fun bind(state: CabinNavigationDockState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinNavigationDockAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinTopBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinTopBarState = CabinTopBarState()
    private var actionListener: ((CabinTopBarAction) -> Unit)? = null

    init {
        bindHost("Top app bar", "navigation", "cabin_top_bar", CabinTopBarInteraction)
        setOnActivate { actionListener?.invoke(CabinTopBarAction.Activate) }
    }

    fun bind(state: CabinTopBarState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinTopBarAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinBackAffordanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinBackAffordanceState = CabinBackAffordanceState()
    private var actionListener: ((CabinBackAffordanceAction) -> Unit)? = null

    init {
        bindHost("Back / up", "navigation", "cabin_back_affordance", CabinBackAffordanceInteraction)
        setOnActivate { actionListener?.invoke(CabinBackAffordanceAction.Activate) }
    }

    fun bind(state: CabinBackAffordanceState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinBackAffordanceAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinFocusAreaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinFocusAreaState = CabinFocusAreaState()
    private var actionListener: ((CabinFocusAreaAction) -> Unit)? = null

    init {
        bindHost("Focus area", "navigation", "cabin_focus_area", CabinFocusAreaInteraction)
        setOnActivate { actionListener?.invoke(CabinFocusAreaAction.Activate) }
    }

    fun bind(state: CabinFocusAreaState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinFocusAreaAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

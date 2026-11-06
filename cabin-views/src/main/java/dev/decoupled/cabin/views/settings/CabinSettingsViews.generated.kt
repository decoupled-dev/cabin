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

package dev.decoupled.cabin.views.settings

/** GENERATED from components/cabin.components.yaml. Do not edit by hand — run python3 tools/generate_cabin_components.py. */

import android.content.Context
import android.util.AttributeSet
import dev.decoupled.cabin.views.scaffold.CabinScaffoldView
import dev.decoupled.cabin.foundation.components.settings.*

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinPreferenceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinPreferenceState = CabinPreferenceState()
    private var actionListener: ((CabinPreferenceAction) -> Unit)? = null

    init {
        bindHost("Preference row", "settings", "cabin_preference", CabinPreferenceInteraction)
        setOnActivate { actionListener?.invoke(CabinPreferenceAction.Activate) }
    }

    fun bind(state: CabinPreferenceState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinPreferenceAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

@dev.decoupled.cabin.foundation.CabinScaffold
class CabinPreferenceScaffoldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CabinScaffoldView(context, attrs, defStyleAttr) {
    private var state: CabinPreferenceScaffoldState = CabinPreferenceScaffoldState()
    private var actionListener: ((CabinPreferenceScaffoldAction) -> Unit)? = null

    init {
        bindHost("Two-pane settings", "settings", "cabin_preference_scaffold", CabinPreferenceScaffoldInteraction)
        setOnActivate { actionListener?.invoke(CabinPreferenceScaffoldAction.Activate) }
    }

    fun bind(state: CabinPreferenceScaffoldState) {
        this.state = state
        applyState(state.label, state.variant, state.ui)
    }

    fun setOnAction(listener: ((CabinPreferenceScaffoldAction) -> Unit)?) {
        actionListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyState(state.label, state.variant, state.ui)
    }
}

package dev.decoupled.cabin.views.scaffold

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.views.GateVisuals
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Shared Experimental scaffold chrome for generated Views components.
 *
 * Token colors, min touch, Restriction Engine gating, family accent mark.
 * Hosts attach a [CabinComplianceHost] the same way System/Status bars do.
 */
open class CabinScaffoldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var title: String = "Cabin"
    private var family: String = ""
    private var testTagValue: String = "cabin_scaffold"
    private var interaction: CabinInteraction = CabinInteraction.Glance
    private var variant: String = ""
    private var ui: CabinComponentUiState = CabinComponentUiState()
    private var complianceHost: CabinComplianceHost? = null
    private var activateListener: (() -> Unit)? = null

    private val accentView = View(context).apply { tag = "cabin_scaffold_mark" }
    private val titleView = TextView(context)
    private val variantView = TextView(context)
    private val statusView = TextView(context)
    private val loadingView = View(context)
    private val activateView = TextView(context).apply {
        gravity = Gravity.CENTER
        isFocusable = true
        isFocusableInTouchMode = true
        isClickable = true
        minHeight = touchMinPx()
        setOnClickListener { maybeActivate() }
    }

    private val onComplianceChanged: () -> Unit = { applyVisuals() }

    init {
        orientation = VERTICAL
        val pad = (CabinTokens.Space.sm.dp * resources.displayMetrics.density).toInt()
        val markH = (CabinTokens.Space.xs.dp * resources.displayMetrics.density).toInt()
        setPadding(pad, pad, pad, pad)
        addView(accentView, LayoutParams(LayoutParams.MATCH_PARENT, markH))
        addView(
            titleView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).also {
                it.topMargin = pad
            },
        )
        addView(variantView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(statusView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(
            loadingView,
            LayoutParams(LayoutParams.MATCH_PARENT, markH).also { it.topMargin = pad / 2 },
        )
        addView(
            activateView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).also {
                it.topMargin = pad
            },
        )
        isFocusable = true
        descendantFocusability = FOCUS_AFTER_DESCENDANTS
    }

    fun bindHost(
        title: String,
        family: String,
        testTag: String,
        interaction: CabinInteraction,
    ) {
        this.title = title
        this.family = family
        this.testTagValue = testTag
        this.interaction = interaction
        tag = testTag
        accentView.tag = "${testTag}_mark"
        activateView.tag = "${testTag}_activate"
        statusView.tag = "${testTag}_status"
        loadingView.tag = "${testTag}_loading"
        titleView.text = title
        applyVisuals()
    }

    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyVisuals()
    }

    fun setOnActivate(listener: (() -> Unit)?) {
        activateListener = listener
    }

    fun applyState(label: String, variant: String, ui: CabinComponentUiState) {
        this.title = label.ifBlank { title }
        this.variant = variant
        this.ui = ui
        titleView.text = this.title
        variantView.text = variant
        variantView.visibility = if (variant.isBlank()) GONE else VISIBLE
        applyVisuals()
    }

    override fun onDetachedFromWindow() {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        super.onDetachedFromWindow()
    }

    private fun maybeActivate() {
        if (!activatable()) return
        activateListener?.invoke()
    }

    private fun activatable(): Boolean {
        val disposition = complianceHost?.disposition(interaction) ?: GateDisposition.Block
        return GateVisuals.activatable(ui.effectivelyEnabled, disposition)
    }

    private fun applyVisuals() {
        val colors = runCatching { CabinThemeResolver.resolveColors(context) }.getOrNull()
        val disposition = complianceHost?.disposition(interaction) ?: GateDisposition.Block
        val restricted = disposition != GateDisposition.Allow || ui.restricted
        val status = when {
            ui.loading -> "Loading"
            ui.error -> "Error"
            restricted -> "Restricted while driving — park to continue"
            ui.disabled || !ui.enabled -> "Off"
            ui.selected -> "Selected"
            else -> family
        }
        statusView.text = status
        activateView.text = if (activatable()) "Activate" else "Unavailable"
        activateView.isEnabled = activatable()
        activateView.contentDescription = "$title. $status"
        titleView.contentDescription = title
        loadingView.visibility = if (ui.loading) VISIBLE else GONE
        if (colors != null) {
            val mark = cabinFamilyAccent(family, colors)
            titleView.setTextColor(colors.onSurface)
            variantView.setTextColor(colors.outline)
            statusView.setTextColor(
                when {
                    ui.error -> colors.error
                    restricted -> colors.warning
                    else -> colors.outline
                },
            )
            activateView.setTextColor(colors.onSurface)
            accentView.setBackgroundColor(mark)
            loadingView.setBackgroundColor(mark)
            val stroke = when {
                ui.error -> colors.error
                ui.focused -> colors.focusRing
                else -> colors.outline
            }
            val bg = GradientDrawable().apply {
                setColor(if (ui.selected) colors.surfaceVariant else colors.surface)
                setStroke(
                    (CabinTokens.Focus.Ring.width.dp * resources.displayMetrics.density).toInt()
                        .let { if (ui.focused) it else maxOf(1, it / 3) },
                    stroke,
                )
                cornerRadius = CabinTokens.Shape.Corner.md.dp * resources.displayMetrics.density
            }
            background = bg
            val activateBg = GradientDrawable().apply {
                setColor(if (activatable()) colors.surfaceVariant else colors.surface)
                setStroke(1, if (activatable()) mark else colors.outline)
                cornerRadius = CabinTokens.Shape.Corner.md.dp * resources.displayMetrics.density
            }
            activateView.background = activateBg
            activateView.minHeight = touchMinPx()
        }
        alpha = GateVisuals.quietAlpha(disposition)
    }

    private fun touchMinPx(): Int {
        val tokenMin = (
            CabinTokens.Size.Touch.minimum.dp * resources.displayMetrics.density
            ).toInt()
        val hostMin = complianceHost?.touchTargetMinDp()?.let { dp ->
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                resources.displayMetrics,
            ).toInt()
        } ?: tokenMin
        return maxOf(tokenMin, hostMin)
    }
}

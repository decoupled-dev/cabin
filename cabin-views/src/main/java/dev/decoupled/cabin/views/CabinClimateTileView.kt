package dev.decoupled.cabin.views

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.tokens.R as TokensR
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileAction
import dev.decoupled.cabin.foundation.hvac.CabinClimateTileState
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Automotive climate tile: zone temp / fan / seat heat with RE-gated adjustments.
 *
 * Spec: docs/components/specs/climate-tile.md
 *
 * Craft: cabin density (76dp), climate accent as mark only (not body copy /
 * washes), RE-quiet while Moving, honest empty/stale, not a Material card.
 */
class CabinClimateTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var state: CabinClimateTileState =
        dev.decoupled.cabin.foundation.hvac.CabinClimateTileState.empty()
    private var complianceHost: CabinComplianceHost? = null
    private var actionListener: ((CabinClimateTileAction) -> Unit)? = null

    /** Thin climate accent mark — accent role only, not body wash. */
    private val accentMark = View(context).apply { tag = "climate_accent_mark" }
    private val zoneLabelView = TextView(context)
    private val tempValueView = TextView(context)
    private val fanValueView = TextView(context)
    private val seatValueView = TextView(context)
    private val fanLabelView = TextView(context)
    private val seatLabelView = TextView(context)

    private val tempDown = controlButton("−", "Decrease temperature") {
        emit(CabinClimateTileAction.TempDown)
    }
    private val tempUp = controlButton("+", "Increase temperature") {
        emit(CabinClimateTileAction.TempUp)
    }
    private val fanDown = controlButton("−", "Decrease fan") {
        emit(CabinClimateTileAction.FanDown)
    }
    private val fanUp = controlButton("+", "Increase fan") {
        emit(CabinClimateTileAction.FanUp)
    }
    private val seatDown = controlButton("−", "Decrease seat heat") {
        emit(CabinClimateTileAction.SeatHeatDown)
    }
    private val seatUp = controlButton("+", "Increase seat heat") {
        emit(CabinClimateTileAction.SeatHeatUp)
    }

    private val onComplianceChanged: () -> Unit = { applyCompliance() }

    private val controlMinPx: Int
        get() {
            val tokenMin = resources.getDimensionPixelSize(
                TokensR.dimen.cabin_component_climate_tile_controlMinSize,
            )
            val hostMin = complianceHost?.touchTargetMinDp()?.let { dp ->
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp.toFloat(),
                    resources.displayMetrics,
                ).toInt()
            } ?: tokenMin
            return maxOf(tokenMin, hostMin)
        }

    private val gapPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_climate_tile_gap)

    private val paddingPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_climate_tile_padding)

    private val accentMarkWidthPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_space_xs)

    init {
        orientation = VERTICAL
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        // Flat cabin surface — no Material card fill / elevation wash.
        setBackgroundColor(android.graphics.Color.TRANSPARENT)
        val pad = paddingPx
        setPadding(pad, pad / 2, pad, pad / 2)

        zoneLabelView.apply {
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
            )
        }
        listOf(fanLabelView, seatLabelView).forEach { label ->
            label.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_status_size),
            )
        }
        fanLabelView.text = "Fan"
        seatLabelView.text = "Seat"
        tempValueView.apply {
            gravity = Gravity.CENTER
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_title_size),
            )
            minimumWidth = controlMinPx
            tag = "climate_temp_value"
        }
        fanValueView.apply {
            gravity = Gravity.CENTER
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
            )
            minimumWidth = controlMinPx
            tag = "climate_fan_value"
        }
        seatValueView.apply {
            gravity = Gravity.CENTER
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
            )
            minimumWidth = controlMinPx
            tag = "climate_seat_value"
        }

        val header = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            addView(
                accentMark,
                LayoutParams(accentMarkWidthPx, controlMinPx / 3).apply {
                    marginEnd = gapPx
                },
            )
            addView(
                zoneLabelView,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT),
            )
        }
        addView(header, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(row(tempDown, tempValueView, tempUp))
        addView(labeledRow(fanLabelView, fanDown, fanValueView, fanUp))
        addView(labeledRow(seatLabelView, seatDown, seatValueView, seatUp))

        applyChrome()
        bind(state)
    }

    fun bind(state: CabinClimateTileState) {
        this.state = state
        zoneLabelView.text = state.zoneLabel
        bindSignal(tempValueView, state.temperatureC, ::formatTemp)
        bindSignal(fanValueView, state.fanLevel) { formatLevel(it, state.fanMax) }
        bindSignal(seatValueView, state.seatHeatLevel) { formatLevel(it, state.seatHeatMax) }
        applyCompliance()
    }

    fun setOnActionListener(listener: ((CabinClimateTileAction) -> Unit)?) {
        actionListener = listener
    }

    /**
     * Attach Restriction Engine host. Null host is fail-closed for adjustments.
     */
    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyCompliance()
    }

    @VisibleForTesting
    internal fun currentState(): CabinClimateTileState = state

    @VisibleForTesting
    internal fun findControl(tag: String): View? {
        return when (tag) {
            "climate_temp_down" -> tempDown
            "climate_temp_up" -> tempUp
            "climate_fan_down" -> fanDown
            "climate_fan_up" -> fanUp
            "climate_seat_down" -> seatDown
            "climate_seat_up" -> seatUp
            "climate_temp_value" -> tempValueView
            "climate_fan_value" -> fanValueView
            "climate_seat_value" -> seatValueView
            "climate_accent_mark" -> accentMark
            else -> null
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyChrome()
        applyCompliance()
    }

    override fun onDetachedFromWindow() {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        super.onDetachedFromWindow()
    }

    private fun emit(action: CabinClimateTileAction) {
        if (dispositionFor(CabinInteraction.HvacAdjust) != GateDisposition.Allow) return
        if (!state.powerOn) return
        if (!channelLive(action)) return
        actionListener?.invoke(action)
    }

    private fun channelLive(action: CabinClimateTileAction): Boolean = when (action) {
        CabinClimateTileAction.TempUp,
        CabinClimateTileAction.TempDown,
        -> state.temperatureC is Signal.Value
        CabinClimateTileAction.FanUp,
        CabinClimateTileAction.FanDown,
        -> state.fanLevel is Signal.Value
        CabinClimateTileAction.SeatHeatUp,
        CabinClimateTileAction.SeatHeatDown,
        -> state.seatHeatLevel is Signal.Value
    }

    private fun dispositionFor(interaction: CabinInteraction): GateDisposition {
        val host = complianceHost ?: return GateDisposition.Block
        return host.disposition(interaction)
    }

    private fun applyCompliance() {
        val disposition = dispositionFor(CabinInteraction.HvacAdjust)
        val power = state.powerOn
        // RE-quiet: soft-disable steppers; values stay full opacity (glance).
        GateVisuals.applyQuiet(tempDown, power && state.temperatureC is Signal.Value, disposition)
        GateVisuals.applyQuiet(tempUp, power && state.temperatureC is Signal.Value, disposition)
        GateVisuals.applyQuiet(fanDown, power && state.fanLevel is Signal.Value, disposition)
        GateVisuals.applyQuiet(fanUp, power && state.fanLevel is Signal.Value, disposition)
        GateVisuals.applyQuiet(seatDown, power && state.seatHeatLevel is Signal.Value, disposition)
        GateVisuals.applyQuiet(seatUp, power && state.seatHeatLevel is Signal.Value, disposition)
        tempValueView.alpha = 1f
        fanValueView.alpha = 1f
        seatValueView.alpha = 1f
        applyChrome()
    }

    private fun applyChrome() {
        try {
            val colors = CabinThemeResolver.resolveColors(context)
            // Flat — no card wash / Material elevation clone.
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            accentMark.setBackgroundColor(colors.climate)
            zoneLabelView.setTextColor(colors.onSurface)
            // Body / title stay onSurface — climate accent is mark-only.
            tempValueView.setTextColor(colors.onSurface)
            fanValueView.setTextColor(colors.onSurface)
            seatValueView.setTextColor(colors.onSurface)
            fanLabelView.setTextColor(colors.onSurface)
            seatLabelView.setTextColor(colors.onSurface)
            listOf(tempDown, tempUp, fanDown, fanUp, seatDown, seatUp).forEach {
                it.setTextColor(colors.onSurface)
                // Quiet outline affordance — not filled Material buttons.
                it.background = GradientDrawable().apply {
                    setColor(android.graphics.Color.TRANSPARENT)
                    setStroke(
                        resources.getDimensionPixelSize(TokensR.dimen.cabin_space_xs) / 2,
                        colors.outline,
                    )
                }
            }
        } catch (_: IllegalArgumentException) {
            // Host may not yet apply Theme.Cabin.
        }
    }

    private fun <T> bindSignal(
        view: TextView,
        signal: Signal<T>,
        formatter: (Signal<T>) -> String,
    ) {
        view.text = formatter(signal)
        view.contentDescription = when (signal) {
            is Signal.Value -> view.text.toString()
            is Signal.Stale -> "${formatter(signal)} (stale)"
            Signal.Unavailable -> "Unavailable"
            is Signal.Fault -> "Fault ${signal.code}"
        }
    }

    private fun controlButton(label: String, description: String, onClick: () -> Unit): TextView {
        return TextView(context).apply {
            text = label
            gravity = Gravity.CENTER
            contentDescription = description
            importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
            isFocusable = true
            isClickable = true
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_title_size),
            )
            minimumWidth = controlMinPx
            minimumHeight = controlMinPx
            tag = when (description) {
                "Decrease temperature" -> "climate_temp_down"
                "Increase temperature" -> "climate_temp_up"
                "Decrease fan" -> "climate_fan_down"
                "Increase fan" -> "climate_fan_up"
                "Decrease seat heat" -> "climate_seat_down"
                "Increase seat heat" -> "climate_seat_up"
                else -> description
            }
            setOnClickListener { onClick() }
        }
    }

    private fun row(vararg children: TextView): LinearLayout {
        return LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            lp.topMargin = gapPx
            layoutParams = lp
            children.forEach { addView(it) }
        }
    }

    private fun labeledRow(
        label: TextView,
        down: TextView,
        value: TextView,
        up: TextView,
    ): LinearLayout {
        return LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            lp.topMargin = gapPx
            layoutParams = lp
            val labelLp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            labelLp.marginEnd = gapPx
            label.layoutParams = labelLp
            addView(label)
            addView(down)
            addView(value)
            addView(up)
        }
    }

    companion object {
        /** Honest signal text — stale labeled; unavailable never invented. */
        internal fun formatTemp(signal: Signal<Int>): String = when (signal) {
            is Signal.Value -> "${signal.value}°"
            is Signal.Stale -> "${signal.last}° · stale"
            Signal.Unavailable -> "—"
            is Signal.Fault -> "Fault"
        }

        internal fun formatLevel(signal: Signal<Int>, max: Int): String = when (signal) {
            is Signal.Value -> "${signal.value}/$max"
            is Signal.Stale -> "${signal.last}/$max · stale"
            Signal.Unavailable -> "—"
            is Signal.Fault -> "Fault"
        }
    }
}

/** Token constants for tests / diagnostics. */
object CabinClimateTileTokens {
    val controlMinSizeDp: Float = CabinTokens.Component.ClimateTile.controlMinSize.dp
    val gapDp: Float = CabinTokens.Component.ClimateTile.gap.dp
}

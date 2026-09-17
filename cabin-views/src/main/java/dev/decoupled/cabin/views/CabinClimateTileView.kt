package dev.decoupled.cabin.views

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.tokens.R as TokensR
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Automotive climate tile: zone temp / fan / seat heat with RE-gated adjustments.
 *
 * Spec: docs/components/specs/climate-tile.md
 *
 * Adjustments declare [CabinInteraction.HvacAdjust] — Block while Moving /
 * Restricted / Unknown. Missing compliance host is fail-closed.
 */
class CabinClimateTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var state: CabinClimateTileState = CabinClimateTileState.empty()
    private var complianceHost: CabinComplianceHost? = null
    private var actionListener: ((CabinClimateTileAction) -> Unit)? = null

    private val zoneLabelView = TextView(context)
    private val tempValueView = TextView(context)
    private val fanValueView = TextView(context)
    private val seatValueView = TextView(context)

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

    init {
        orientation = VERTICAL
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        val pad = paddingPx
        setPadding(pad, pad, pad, pad)
        dividerPadding = gapPx

        zoneLabelView.apply {
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
            )
        }
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

        addView(zoneLabelView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(row(tempDown, tempValueView, tempUp))
        addView(labeledRow("Fan", fanDown, fanValueView, fanUp))
        addView(labeledRow("Seat", seatDown, seatValueView, seatUp))

        applyChrome()
        bind(state)
    }

    fun bind(state: CabinClimateTileState) {
        this.state = state
        zoneLabelView.text = state.zoneLabel
        tempValueView.text = formatTemp(state.temperatureC)
        fanValueView.text = formatLevel(state.fanLevel, state.fanMax)
        seatValueView.text = formatLevel(state.seatHeatLevel, state.seatHeatMax)
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
    internal fun findControl(tag: String): TextView? {
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
        GateVisuals.apply(tempDown, power && state.temperatureC is Signal.Value, disposition)
        GateVisuals.apply(tempUp, power && state.temperatureC is Signal.Value, disposition)
        GateVisuals.apply(fanDown, power && state.fanLevel is Signal.Value, disposition)
        GateVisuals.apply(fanUp, power && state.fanLevel is Signal.Value, disposition)
        GateVisuals.apply(seatDown, power && state.seatHeatLevel is Signal.Value, disposition)
        GateVisuals.apply(seatUp, power && state.seatHeatLevel is Signal.Value, disposition)
        applyChrome()
    }

    private fun applyChrome() {
        try {
            val colors = CabinThemeResolver.resolveColors(context)
            val radius = resources.getDimension(TokensR.dimen.cabin_component_climate_tile_cornerRadius)
            background = GradientDrawable().apply {
                setColor(colors.surfaceVariant)
                setStroke(
                    resources.getDimensionPixelSize(TokensR.dimen.cabin_space_xs) / 2,
                    colors.outline,
                )
                cornerRadius = radius
            }
            zoneLabelView.setTextColor(colors.onSurface)
            tempValueView.setTextColor(colors.climate)
            fanValueView.setTextColor(colors.onSurface)
            seatValueView.setTextColor(colors.onSurface)
            listOf(tempDown, tempUp, fanDown, fanUp, seatDown, seatUp).forEach {
                it.setTextColor(colors.onSurface)
                it.background = GradientDrawable().apply {
                    setStroke(
                        resources.getDimensionPixelSize(TokensR.dimen.cabin_space_xs) / 2,
                        colors.outline,
                    )
                    cornerRadius = radius / 2f
                }
            }
        } catch (_: IllegalArgumentException) {
            // Host may not yet apply Theme.Cabin.
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
        label: String,
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
            addView(
                TextView(context).apply {
                    text = label
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
                    )
                    val labelLp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                    labelLp.marginEnd = gapPx
                    layoutParams = labelLp
                },
            )
            addView(down)
            addView(value)
            addView(up)
        }
    }

    companion object {
        internal fun formatTemp(signal: Signal<Int>): String = when (signal) {
            is Signal.Value -> "${signal.value}°"
            is Signal.Stale -> "${signal.last}°"
            Signal.Unavailable -> "—"
            is Signal.Fault -> "!"
        }

        internal fun formatLevel(signal: Signal<Int>, max: Int): String = when (signal) {
            is Signal.Value -> "${signal.value}/$max"
            is Signal.Stale -> "${signal.last}/$max"
            Signal.Unavailable -> "—"
            is Signal.Fault -> "!"
        }
    }
}

/** ClimateTile presentation state (parity with Compose). */
data class CabinClimateTileState(
    val zoneLabel: String,
    val temperatureC: Signal<Int>,
    val fanLevel: Signal<Int>,
    val fanMax: Int = 5,
    val seatHeatLevel: Signal<Int>,
    val seatHeatMax: Int = 3,
    val powerOn: Boolean = true,
) {
    companion object {
        fun empty(): CabinClimateTileState = CabinClimateTileState(
            zoneLabel = "",
            temperatureC = Signal.Unavailable,
            fanLevel = Signal.Unavailable,
            seatHeatLevel = Signal.Unavailable,
            powerOn = false,
        )
    }
}

/** ClimateTile actions — all gated as [CabinInteraction.HvacAdjust]. */
sealed interface CabinClimateTileAction {
    data object TempUp : CabinClimateTileAction
    data object TempDown : CabinClimateTileAction
    data object FanUp : CabinClimateTileAction
    data object FanDown : CabinClimateTileAction
    data object SeatHeatUp : CabinClimateTileAction
    data object SeatHeatDown : CabinClimateTileAction
}

/** Token constants for tests / diagnostics. */
object CabinClimateTileTokens {
    val controlMinSizeDp: Float = CabinTokens.Component.ClimateTile.controlMinSize.dp
    val gapDp: Float = CabinTokens.Component.ClimateTile.gap.dp
}

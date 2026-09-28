@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)

package dev.decoupled.cabin.views.action

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.foundation.components.action.CabinButtonAction
import dev.decoupled.cabin.foundation.components.action.CabinButtonInteraction
import dev.decoupled.cabin.foundation.components.action.CabinButtonState
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Cabin Button — glance-sized primary action (Views).
 *
 * Variants match the theme-board button styles. Null compliance host is
 * fail-closed. Spec: docs/components/specs/button.md
 */
class CabinButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var state: CabinButtonState = CabinButtonState()
    private var complianceHost: CabinComplianceHost? = null
    private var actionListener: ((CabinButtonAction) -> Unit)? = null
    private val labelView = TextView(context).apply {
        gravity = Gravity.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
    }
    private val onComplianceChanged: () -> Unit = { applyVisuals() }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        isClickable = true
        isFocusable = true
        tag = "cabin_button"
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        setOnClickListener {
            if (activatable()) actionListener?.invoke(CabinButtonAction.Activate)
        }
        applyVisuals()
    }

    fun bind(state: CabinButtonState) {
        this.state = state
        applyVisuals()
    }

    fun setOnAction(listener: ((CabinButtonAction) -> Unit)?) {
        actionListener = listener
    }

    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyVisuals()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyVisuals()
    }

    override fun onDetachedFromWindow() {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        super.onDetachedFromWindow()
    }

    private fun activatable(): Boolean {
        val disposition = complianceHost?.disposition(CabinButtonInteraction) ?: GateDisposition.Block
        return state.ui.effectivelyEnabled && disposition == GateDisposition.Allow
    }

    private fun applyVisuals() {
        val iconVariant = state.variant == "icon"
        val label = when {
            state.label.isNotBlank() -> state.label
            iconVariant -> "＋"
            else -> "Button"
        }
        labelView.text = label
        contentDescription = label
        val minPx = touchMinPx()
        minimumHeight = minPx
        minimumWidth = minPx
        val pad = if (iconVariant) 0 else dp(CabinTokens.Component.Button.horizontalPadding.dp)
        setPadding(pad, 0, pad, 0)
        labelView.setTextSize(
            TypedValue.COMPLEX_UNIT_SP,
            if (iconVariant) CabinTokens.Type.Role.Title.size.sp
            else CabinTokens.Type.Role.Label.size.sp,
        )
        isEnabled = activatable()
        val colors = runCatching { CabinThemeResolver.resolveColors(context) }.getOrNull() ?: return
        val surfaceHigh = blend(colors.surface, colors.onSurface, 0.18f)
        val onSurfaceVariant = blend(colors.onSurface, colors.surface, 0.42f)
        val outlineSubtle = Color.argb(
            (0.12f * 255).toInt(),
            Color.red(colors.onSurface),
            Color.green(colors.onSurface),
            Color.blue(colors.onSurface),
        )
        val disabled = !activatable()
        var background = when {
            disabled -> colors.surface
            state.variant == "filled" -> colors.primary
            state.variant == "outlined" || state.variant == "text" || state.variant == "icon" ->
                Color.TRANSPARENT
            else -> blend(surfaceHigh, colors.primary, 0.18f)
        }
        val content = when {
            disabled -> onSurfaceVariant
            state.variant == "filled" -> colors.onPrimary
            state.variant == "text" || state.variant == "icon" -> colors.primary
            else -> colors.onSurface
        }
        var border: Int? = when {
            disabled -> outlineSubtle
            state.variant == "outlined" -> colors.outline
            else -> null
        }
        var borderWidth = if (border == null) 0 else dp(1f)
        if (state.ui.error) {
            border = colors.error
            borderWidth = dp(CabinTokens.Focus.Ring.width.dp)
        } else if (state.ui.focused && !disabled) {
            border = colors.focusRing
            borderWidth = dp(CabinTokens.Focus.Ring.width.dp)
        }
        if ((state.ui.pressed || isPressed) && !disabled) {
            background = blend(background, colors.onSurface, 0.08f)
        }
        labelView.setTextColor(content)
        val drawable = GradientDrawable().apply {
            setColor(background)
            cornerRadius = CabinTokens.Component.Button.cornerRadius.dp * resources.displayMetrics.density
            if (border != null && borderWidth > 0) setStroke(borderWidth, border)
        }
        this.background = drawable
    }

    private fun touchMinPx(): Int {
        val tokenMin = dp(CabinTokens.Component.Button.minHeight.dp)
        val hostMin = complianceHost?.touchTargetMinDp()?.let { dp(it.toFloat()) } ?: tokenMin
        return maxOf(tokenMin, hostMin)
    }

    private fun dp(value: Float): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            resources.displayMetrics,
        ).toInt().coerceAtLeast(0)
}

private fun blend(from: Int, to: Int, toFraction: Float): Int {
    val t = toFraction.coerceIn(0f, 1f)
    val inv = 1f - t
    return Color.argb(
        (Color.alpha(from) * inv + Color.alpha(to) * t).toInt(),
        (Color.red(from) * inv + Color.red(to) * t).toInt(),
        (Color.green(from) * inv + Color.green(to) * t).toInt(),
        (Color.blue(from) * inv + Color.blue(to) * t).toInt(),
    )
}

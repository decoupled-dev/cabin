package dev.decoupled.cabin.views

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.tokens.R as TokensR
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Cabin Button — glance-sized Primary Action primitive.
 *
 * Activation is gated via [CabinComplianceHost]. Missing host is fail-closed.
 *
 * Spec: docs/components/specs/button.md
 */
class CabinButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var state: CabinButtonState = CabinButtonState(
        label = "",
        interaction = CabinInteraction.NavigateSimple,
    )
    private var onClickAction: (() -> Unit)? = null
    private var complianceHost: CabinComplianceHost? = null

    private val iconView = ImageView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        visibility = GONE
    }
    private val labelView = TextView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        gravity = Gravity.CENTER
    }

    private val onComplianceChanged: () -> Unit = { applyCompliance() }

    private val minHeightPx: Int
        get() {
            val tokenMin =
                resources.getDimensionPixelSize(TokensR.dimen.cabin_component_button_minHeight)
            val hostMin = complianceHost?.touchTargetMinDp()?.let { dpToPx(it) } ?: tokenMin
            return maxOf(tokenMin, hostMin)
        }

    private val horizontalPaddingPx: Int
        get() = resources.getDimensionPixelSize(
            TokensR.dimen.cabin_component_button_horizontalPadding,
        )

    private val iconSizePx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_button_iconSize)

    private val gapPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_button_gap)

    private val cornerRadiusPx: Float
        get() = resources.getDimension(TokensR.dimen.cabin_component_button_cornerRadius)

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        isFocusable = true
        isClickable = true
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        minimumHeight = minHeightPx
        setPadding(horizontalPaddingPx, 0, horizontalPaddingPx, 0)
        addView(iconView, LayoutParams(iconSizePx, iconSizePx).apply {
            marginEnd = gapPx
        })
        addView(
            labelView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT),
        )
        setOnClickListener {
            if (GateVisuals.activatable(state.enabled, dispositionFor(state.interaction))) {
                onClickAction?.invoke()
            }
        }
        applyChrome()
        applyCompliance()
    }

    /** Bind label / variant / interaction. Replaces previous configuration. */
    fun bind(state: CabinButtonState, onClick: (() -> Unit)? = null) {
        this.state = state
        this.onClickAction = onClick
        labelView.text = state.label
        labelView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
        )
        if (state.icon != null) {
            iconView.setImageDrawable(state.icon)
            iconView.visibility = VISIBLE
        } else {
            iconView.setImageDrawable(null)
            iconView.visibility = GONE
        }
        contentDescription = state.contentDescription ?: state.label
        applyChrome()
        applyCompliance()
        requestLayout()
    }

    /**
     * Attach Restriction Engine host. Null host is fail-closed
     * ([GateDisposition.Block]).
     */
    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyCompliance()
    }

    @VisibleForTesting
    internal fun currentState(): CabinButtonState = state

    @VisibleForTesting
    internal fun performActivateForTest() {
        if (GateVisuals.activatable(state.enabled, dispositionFor(state.interaction))) {
            onClickAction?.invoke()
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        minimumHeight = minHeightPx
        val height = resolveSize(minHeightPx, heightMeasureSpec).coerceAtLeast(minHeightPx)
        val heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightSpec)
    }

    private fun dispositionFor(interaction: CabinInteraction): GateDisposition {
        val host = complianceHost ?: return GateDisposition.Block
        return host.disposition(interaction)
    }

    private fun applyCompliance() {
        GateVisuals.apply(this, state.enabled, dispositionFor(state.interaction))
    }

    private fun applyChrome() {
        try {
            val colors = CabinThemeResolver.resolveColors(context)
            val bg = GradientDrawable().apply {
                cornerRadius = cornerRadiusPx
            }
            val contentColor: Int
            when (state.variant) {
                CabinButtonVariant.Filled -> {
                    bg.setColor(colors.primary)
                    contentColor = colors.onPrimary
                }
                CabinButtonVariant.Outlined -> {
                    bg.setColor(android.graphics.Color.TRANSPARENT)
                    bg.setStroke(dpToPx(2), colors.outline)
                    contentColor = colors.onSurface
                }
                CabinButtonVariant.Quiet -> {
                    bg.setColor(android.graphics.Color.TRANSPARENT)
                    contentColor = colors.onSurface
                }
            }
            background = bg
            labelView.setTextColor(contentColor)
            iconView.setColorFilter(contentColor)
        } catch (_: IllegalArgumentException) {
            // Host may not yet apply Theme.Cabin; leave platform defaults.
        }
    }

    private fun dpToPx(dp: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics,
        ).toInt()
}

/**
 * Shared Button state across Views / Compose parity.
 *
 * @property interaction declared category for Restriction Engine gating
 */
data class CabinButtonState(
    val label: CharSequence,
    val interaction: CabinInteraction,
    val variant: CabinButtonVariant = CabinButtonVariant.Filled,
    val enabled: Boolean = true,
    val icon: Drawable? = null,
    val contentDescription: String? = null,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinButtonTokens {
    val minHeightDp: Float = CabinTokens.Component.Button.minHeight.dp
    val horizontalPaddingDp: Float = CabinTokens.Component.Button.horizontalPadding.dp
    val iconSizeDp: Float = CabinTokens.Component.Button.iconSize.dp
    val gapDp: Float = CabinTokens.Component.Button.gap.dp
    val cornerRadiusDp: Float = CabinTokens.Component.Button.cornerRadius.dp
}

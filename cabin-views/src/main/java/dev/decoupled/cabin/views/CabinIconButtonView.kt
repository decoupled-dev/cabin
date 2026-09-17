package dev.decoupled.cabin.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.VisibleForTesting
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.GateDisposition
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.tokens.R as TokensR
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Cabin IconButton — icon-only Primary Action primitive.
 *
 * [CabinIconButtonState.contentDescription] is required for accessibility.
 * Activation is gated via [CabinComplianceHost]. Missing host is fail-closed.
 *
 * Spec: docs/components/specs/button.md
 */
class CabinIconButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private var state: CabinIconButtonState? = null
    private var onClickAction: (() -> Unit)? = null
    private var complianceHost: CabinComplianceHost? = null

    private val iconView = ImageView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        scaleType = ImageView.ScaleType.CENTER_INSIDE
    }

    private val onComplianceChanged: () -> Unit = { applyCompliance() }

    private val minSizePx: Int
        get() {
            val tokenMin =
                resources.getDimensionPixelSize(TokensR.dimen.cabin_component_icon_button_minSize)
            val hostMin = complianceHost?.touchTargetMinDp()?.let { dpToPx(it) } ?: tokenMin
            return maxOf(tokenMin, hostMin)
        }

    private val iconSizePx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_icon_button_iconSize)

    private val cornerRadiusPx: Float
        get() = resources.getDimension(TokensR.dimen.cabin_component_icon_button_cornerRadius)

    init {
        isFocusable = true
        isClickable = true
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        addView(
            iconView,
            LayoutParams(iconSizePx, iconSizePx, Gravity.CENTER),
        )
        setOnClickListener {
            val current = state ?: return@setOnClickListener
            if (GateVisuals.activatable(current.enabled, dispositionFor(current.interaction))) {
                onClickAction?.invoke()
            }
        }
        applyChrome()
        applyCompliance()
    }

    /** Bind icon / variant / interaction. Replaces previous configuration. */
    fun bind(state: CabinIconButtonState, onClick: (() -> Unit)? = null) {
        require(state.contentDescription.isNotBlank()) {
            "IconButton requires a non-blank contentDescription"
        }
        this.state = state
        this.onClickAction = onClick
        iconView.setImageDrawable(state.icon)
        contentDescription = state.contentDescription
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
    internal fun currentState(): CabinIconButtonState? = state

    @VisibleForTesting
    internal fun performActivateForTest() {
        val current = state ?: return
        if (GateVisuals.activatable(current.enabled, dispositionFor(current.interaction))) {
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
        val size = minSizePx
        val sizeSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        super.onMeasure(sizeSpec, sizeSpec)
        setMeasuredDimension(size, size)
    }

    private fun dispositionFor(interaction: CabinInteraction): GateDisposition {
        val host = complianceHost ?: return GateDisposition.Block
        return host.disposition(interaction)
    }

    private fun applyCompliance() {
        val current = state
        if (current == null) {
            isEnabled = false
            isClickable = false
            return
        }
        GateVisuals.apply(this, current.enabled, dispositionFor(current.interaction))
    }

    private fun applyChrome() {
        val current = state ?: return
        try {
            val colors = CabinThemeResolver.resolveColors(context)
            val bg = GradientDrawable().apply {
                cornerRadius = cornerRadiusPx
            }
            val contentColor: Int
            when (current.variant) {
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
 * Shared IconButton state across Views / Compose parity.
 *
 * @property contentDescription required for icon-only a11y
 * @property interaction declared category for Restriction Engine gating
 */
data class CabinIconButtonState(
    val icon: Drawable?,
    val contentDescription: String,
    val interaction: CabinInteraction,
    val variant: CabinButtonVariant = CabinButtonVariant.Quiet,
    val enabled: Boolean = true,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinIconButtonTokens {
    val minSizeDp: Float = CabinTokens.Component.IconButton.minSize.dp
    val iconSizeDp: Float = CabinTokens.Component.IconButton.iconSize.dp
    val cornerRadiusDp: Float = CabinTokens.Component.IconButton.cornerRadius.dp
}

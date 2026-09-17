package dev.decoupled.cabin.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
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
 * Cabin ListItem — low-density, restriction-aware collection row.
 *
 * Craft: 76dp density floor; Restriction Engine on activation; selection uses
 * outline/container — not locked night warning/error.
 *
 * Activation is gated via [CabinComplianceHost]. Missing host is fail-closed.
 *
 * Spec: docs/components/specs/list-item.md
 */
class CabinListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var state: CabinListItemState = CabinListItemState(
        title = "",
        interaction = CabinInteraction.NavigateSimple,
    )
    private var onClickAction: (() -> Unit)? = null
    private var complianceHost: CabinComplianceHost? = null

    private val leadingIcon = ImageView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        visibility = GONE
    }
    private val titleView = TextView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        maxLines = 1
    }
    private val supportingView = TextView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        maxLines = 1
        visibility = GONE
    }
    private val trailingView = TextView(context).apply {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        visibility = GONE
        gravity = Gravity.END or Gravity.CENTER_VERTICAL
    }
    private val textColumn = LinearLayout(context).apply {
        orientation = VERTICAL
        gravity = Gravity.CENTER_VERTICAL
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
    }

    private val onComplianceChanged: () -> Unit = { applyCompliance() }

    private val minHeightPx: Int
        get() {
            val tokenMin =
                resources.getDimensionPixelSize(TokensR.dimen.cabin_component_list_item_minHeight)
            val hostMin = complianceHost?.touchTargetMinDp()?.let { dpToPx(it) } ?: tokenMin
            return maxOf(tokenMin, hostMin)
        }

    private val horizontalPaddingPx: Int
        get() = resources.getDimensionPixelSize(
            TokensR.dimen.cabin_component_list_item_horizontalPadding,
        )

    private val verticalPaddingPx: Int
        get() = resources.getDimensionPixelSize(
            TokensR.dimen.cabin_component_list_item_verticalPadding,
        )

    private val gapPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_list_item_gap)

    private val iconSizePx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_list_item_iconSize)

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        isFocusable = true
        isClickable = true
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        minimumHeight = minHeightPx
        setPadding(
            horizontalPaddingPx,
            verticalPaddingPx,
            horizontalPaddingPx,
            verticalPaddingPx,
        )

        addView(leadingIcon, LayoutParams(iconSizePx, iconSizePx).apply {
            marginEnd = gapPx
        })
        textColumn.addView(
            titleView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT),
        )
        textColumn.addView(
            supportingView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT),
        )
        addView(textColumn, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))
        addView(
            trailingView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                marginStart = gapPx
            },
        )

        setOnClickListener {
            if (GateVisuals.activatable(state.enabled, dispositionFor(state.interaction))) {
                onClickAction?.invoke()
            }
        }
        applyChrome()
        applyCompliance()
    }

    /** Bind title / supporting / interaction. Replaces previous configuration. */
    fun bind(state: CabinListItemState, onClick: (() -> Unit)? = null) {
        this.state = state
        this.onClickAction = onClick

        titleView.text = state.title
        titleView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_body_size),
        )

        if (!state.supportingText.isNullOrBlank()) {
            supportingView.text = state.supportingText
            supportingView.visibility = VISIBLE
            supportingView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_status_size),
            )
        } else {
            supportingView.text = null
            supportingView.visibility = GONE
        }

        if (state.leadingIcon != null) {
            leadingIcon.setImageDrawable(state.leadingIcon)
            leadingIcon.visibility = VISIBLE
        } else {
            leadingIcon.setImageDrawable(null)
            leadingIcon.visibility = GONE
        }

        if (!state.trailingLabel.isNullOrBlank()) {
            trailingView.text = state.trailingLabel
            trailingView.visibility = VISIBLE
            trailingView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_status_size),
            )
        } else {
            trailingView.text = null
            trailingView.visibility = GONE
        }

        contentDescription = state.contentDescription
            ?: buildString {
                append(state.title)
                if (!state.supportingText.isNullOrBlank()) {
                    append(", ")
                    append(state.supportingText)
                }
            }

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
    internal fun currentState(): CabinListItemState = state

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
            val bg = GradientDrawable()
            if (state.selected) {
                bg.setColor(colors.container)
                bg.setStroke(dpToPx(2), colors.outline)
            } else {
                bg.setColor(colors.surface)
            }
            background = bg
            titleView.setTextColor(colors.onSurface)
            supportingView.setTextColor(colors.onSurface)
            supportingView.alpha = 0.72f
            trailingView.setTextColor(colors.onSurface)
            trailingView.alpha = 0.72f
            leadingIcon.setColorFilter(colors.onSurface)
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
 * Shared ListItem state across Views / Compose parity.
 *
 * @property interaction declared category for Restriction Engine gating
 */
data class CabinListItemState(
    val title: CharSequence,
    val interaction: CabinInteraction,
    val supportingText: CharSequence? = null,
    val leadingIcon: Drawable? = null,
    val trailingLabel: CharSequence? = null,
    val selected: Boolean = false,
    val enabled: Boolean = true,
    val contentDescription: String? = null,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinListItemTokens {
    val minHeightDp: Float = CabinTokens.Component.ListItem.minHeight.dp
    val horizontalPaddingDp: Float = CabinTokens.Component.ListItem.horizontalPadding.dp
    val verticalPaddingDp: Float = CabinTokens.Component.ListItem.verticalPadding.dp
    val gapDp: Float = CabinTokens.Component.ListItem.gap.dp
    val iconSizeDp: Float = CabinTokens.Component.ListItem.iconSize.dp
    val dividerInsetDp: Float = CabinTokens.Component.ListItem.dividerInset.dp
}

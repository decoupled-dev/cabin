package dev.decoupled.cabin.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
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
 * Persistent wayfinding chrome with leading / center / trailing slots.
 *
 * Slot configuration is data-driven ([setSlots]) — OEMs configure without
 * forking this widget. Activation is gated via [CabinComplianceHost].
 *
 * Spec: docs/components/specs/system-bar.md
 */
class CabinSystemBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ViewGroup(context, attrs, defStyleAttr) {

    private var slots: CabinSystemBarSlots = CabinSystemBarSlots()
    private var complianceHost: CabinComplianceHost? = null

    private val leadingRow = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }
    private val centerRow = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
    }
    private val trailingRow = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL or Gravity.END
    }

    private val onComplianceChanged: () -> Unit = { applyComplianceToChildren() }

    private val barHeightPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_system_bar_height)

    private val gapPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_system_bar_gap)

    private val itemMinPx: Int
        get() {
            val tokenMin =
                resources.getDimensionPixelSize(TokensR.dimen.cabin_component_system_bar_itemMinSize)
            val hostMin = complianceHost?.touchTargetMinDp()?.let { dp ->
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp.toFloat(),
                    resources.displayMetrics,
                ).toInt()
            } ?: tokenMin
            return maxOf(tokenMin, hostMin)
        }

    private val iconSizePx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_system_bar_iconSize)

    init {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        addView(leadingRow)
        addView(centerRow)
        addView(trailingRow)
        applyChromeColors()
    }

    /** Bind slotted entries. Replaces any previous configuration. */
    fun setSlots(slots: CabinSystemBarSlots) {
        this.slots = slots
        rebuildChildren()
    }

    /** Attach Restriction Engine host; widgets re-evaluate gates on state changes. */
    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyComplianceToChildren()
    }

    @VisibleForTesting
    internal fun currentSlots(): CabinSystemBarSlots = slots

    @VisibleForTesting
    internal fun entryViewCount(): Int =
        leadingRow.childCount + centerRow.childCount + trailingRow.childCount

    @VisibleForTesting
    internal fun findEntryView(id: String): View? {
        for (i in 0 until leadingRow.childCount) {
            val child = leadingRow.getChildAt(i)
            if (child.tag == id) return child
        }
        for (i in 0 until centerRow.childCount) {
            val child = centerRow.getChildAt(i)
            if (child.tag == id) return child
        }
        for (i in 0 until trailingRow.childCount) {
            val child = trailingRow.getChildAt(i)
            if (child.tag == id) return child
        }
        return null
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        applyChromeColors()
        applyComplianceToChildren()
    }

    override fun onDetachedFromWindow() {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        super.onDetachedFromWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = resolveSize(barHeightPx + paddingTop + paddingBottom, heightMeasureSpec)
        val childHeightSpec = MeasureSpec.makeMeasureSpec(
            height - paddingTop - paddingBottom,
            MeasureSpec.EXACTLY,
        )
        val unbounded = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        leadingRow.measure(unbounded, childHeightSpec)
        centerRow.measure(unbounded, childHeightSpec)
        trailingRow.measure(unbounded, childHeightSpec)
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val contentLeft = paddingLeft
        val contentRight = measuredWidth - paddingRight
        val contentTop = paddingTop
        val contentBottom = measuredHeight - paddingBottom
        val contentHeight = contentBottom - contentTop

        leadingRow.layout(
            contentLeft,
            contentTop,
            contentLeft + leadingRow.measuredWidth,
            contentTop + contentHeight,
        )
        trailingRow.layout(
            contentRight - trailingRow.measuredWidth,
            contentTop,
            contentRight,
            contentTop + contentHeight,
        )
        val centerWidth = centerRow.measuredWidth
        val centerLeft = contentLeft + ((contentRight - contentLeft) - centerWidth) / 2
        centerRow.layout(
            centerLeft,
            contentTop,
            centerLeft + centerWidth,
            contentTop + contentHeight,
        )
    }

    private fun rebuildChildren() {
        leadingRow.removeAllViews()
        centerRow.removeAllViews()
        trailingRow.removeAllViews()
        slots.leading.forEach { leadingRow.addView(createEntryView(it)) }
        slots.center.forEach { centerRow.addView(createEntryView(it)) }
        slots.trailing.forEach { trailingRow.addView(createEntryView(it)) }
        applyComplianceToChildren()
        requestLayout()
    }

    private fun createEntryView(entry: CabinSystemBarEntry): View {
        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            tag = entry.id
            minimumWidth = itemMinPx
            minimumHeight = itemMinPx
            val pad = gapPx / 2
            setPadding(pad, pad, pad, pad)
            isFocusable = true
            isClickable = true
            contentDescription = entry.contentDescription
            importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        }

        if (entry.icon != null) {
            container.addView(
                ImageView(context).apply {
                    setImageDrawable(entry.icon)
                    layoutParams = LinearLayout.LayoutParams(iconSizePx, iconSizePx)
                    importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
                },
            )
        }
        if (!entry.label.isNullOrBlank()) {
            container.addView(
                TextView(context).apply {
                    text = entry.label
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
                    )
                    gravity = Gravity.CENTER
                    importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
                },
            )
        }

        container.setOnClickListener {
            when (dispositionFor(entry.interaction)) {
                GateDisposition.Allow -> entry.onActivate()
                GateDisposition.Substitute,
                GateDisposition.Block,
                -> {
                    // Visible, non-activatable — keep layout quiet.
                }
            }
        }
        // Stash entry for compliance refresh.
        container.setTag(R.id.cabin_system_bar_entry_tag, entry)
        return container
    }

    private fun applyComplianceToChildren() {
        applyChromeColors()
        listOf(leadingRow, centerRow, trailingRow).forEach { row ->
            for (i in 0 until row.childCount) {
                val child = row.getChildAt(i)
                val entry = child.getTag(R.id.cabin_system_bar_entry_tag) as? CabinSystemBarEntry
                    ?: continue
                applyGateVisual(child, dispositionFor(entry.interaction))
            }
        }
    }

    private fun dispositionFor(interaction: CabinInteraction): GateDisposition {
        val host = complianceHost ?: return GateDisposition.Allow
        return host.disposition(interaction)
    }

    private fun applyGateVisual(view: View, disposition: GateDisposition) {
        when (disposition) {
            GateDisposition.Allow -> {
                view.isEnabled = true
                view.isClickable = true
                view.alpha = 1f
            }
            GateDisposition.Substitute -> {
                // Keep layout; non-activatable (disabled look).
                view.isEnabled = false
                view.isClickable = false
                view.alpha = 0.55f
            }
            GateDisposition.Block -> {
                view.isEnabled = false
                view.isClickable = false
                view.alpha = 0.4f
            }
        }
    }

    private fun applyChromeColors() {
        try {
            val colors = CabinThemeResolver.resolveColors(context)
            setBackgroundColor(colors.container)
            applyContentColor(leadingRow, colors.onContainer)
            applyContentColor(centerRow, colors.onContainer)
            applyContentColor(trailingRow, colors.onContainer)
        } catch (_: IllegalArgumentException) {
            // Host may not yet apply Theme.Cabin; leave platform defaults.
        }
    }

    private fun applyContentColor(row: LinearLayout, color: Int) {
        for (i in 0 until row.childCount) {
            val child = row.getChildAt(i)
            if (child is ViewGroup) {
                for (j in 0 until child.childCount) {
                    when (val nested = child.getChildAt(j)) {
                        is ImageView -> nested.setColorFilter(color)
                        is TextView -> nested.setTextColor(color)
                    }
                }
            }
        }
    }
}

/** Slotted System Bar configuration (OEM-extensible without core forks). */
data class CabinSystemBarSlots(
    val leading: List<CabinSystemBarEntry> = emptyList(),
    val center: List<CabinSystemBarEntry> = emptyList(),
    val trailing: List<CabinSystemBarEntry> = emptyList(),
)

/**
 * One System Bar slot entry.
 *
 * @property interaction declared category for Restriction Engine gating
 * @property contentDescription required for icon-only entries (a11y)
 */
data class CabinSystemBarEntry(
    val id: String,
    val icon: Drawable? = null,
    val label: CharSequence? = null,
    val contentDescription: String,
    val interaction: CabinInteraction,
    val onActivate: () -> Unit,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinSystemBarTokens {
    val heightDp: Float = CabinTokens.Component.SystemBar.height.dp
    val iconSizeDp: Float = CabinTokens.Component.SystemBar.iconSize.dp
    val itemMinSizeDp: Float = CabinTokens.Component.SystemBar.itemMinSize.dp
    val gapDp: Float = CabinTokens.Component.SystemBar.gap.dp
}

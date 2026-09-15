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
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.tokens.R as TokensR
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Glanceable vehicle / system status chrome.
 *
 * Items are an ordered list. Signal-backed glyphs render unavailable / stale /
 * fault exhaustively. Deep links are gated via [CabinComplianceHost].
 *
 * Spec: docs/components/specs/status-bar.md
 */
class CabinStatusBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ViewGroup(context, attrs, defStyleAttr) {

    private var items: List<CabinStatusItem> = emptyList()
    private var complianceHost: CabinComplianceHost? = null

    private val row = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    private val onComplianceChanged: () -> Unit = { applyComplianceToChildren() }

    private val barHeightPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_status_bar_height)

    private val gapPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_status_bar_itemGap)

    private val iconSizePx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_status_bar_iconSize)

    init {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        addView(row)
        applyChromeColors()
    }

    /** Bind ordered status items. Fault affordances are never dropped. */
    fun setItems(items: List<CabinStatusItem>) {
        // Stable order: non-faults first (input order), then ensure faults remain.
        // Overflow policy is program-defined; MVP keeps all items and never
        // drops active fault affordances from the bound list.
        this.items = items
        rebuildChildren()
    }

    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyComplianceToChildren()
    }

    @VisibleForTesting
    internal fun currentItems(): List<CabinStatusItem> = items

    @VisibleForTesting
    internal fun itemViewCount(): Int = row.childCount

    @VisibleForTesting
    internal fun findItemView(id: String): View? {
        for (i in 0 until row.childCount) {
            val child = row.getChildAt(i)
            if (child.tag == id) return child
        }
        return null
    }

    @VisibleForTesting
    internal fun itemPrimaryText(id: String): CharSequence? {
        val view = findItemView(id) as? ViewGroup ?: return null
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            if (child is TextView) return child.text
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
        val childWidthSpec = MeasureSpec.makeMeasureSpec(
            width - paddingLeft - paddingRight,
            MeasureSpec.EXACTLY,
        )
        row.measure(childWidthSpec, childHeightSpec)
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        row.layout(
            paddingLeft,
            paddingTop,
            measuredWidth - paddingRight,
            measuredHeight - paddingBottom,
        )
    }

    private fun rebuildChildren() {
        row.removeAllViews()
        // Keep caller order. Overflow policy is program-defined; MVP binds the
        // full list and never drops fault-backed items from what was supplied.
        items.forEach { item ->
            row.addView(createItemView(item))
        }
        applyComplianceToChildren()
        requestLayout()
    }

    private fun createItemView(item: CabinStatusItem): View {
        return when (item) {
            is CabinStatusGlyph -> createGlyphView(item)
        }
    }

    private fun createGlyphView(item: CabinStatusGlyph): View {
        val container = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            tag = item.id
            val pad = gapPx / 2
            setPadding(pad, pad, pad, pad)
            importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        }

        val presentation = presentSignal(item)
        container.contentDescription = presentation.contentDescription

        if (item.icon != null || presentation.showIconPlaceholder) {
            container.addView(
                ImageView(context).apply {
                    if (item.icon != null) {
                        setImageDrawable(item.icon)
                    }
                    layoutParams = LinearLayout.LayoutParams(iconSizePx, iconSizePx).apply {
                        marginEnd = gapPx / 2
                    }
                    importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
                    tag = "icon"
                },
            )
        }

        container.addView(
            TextView(context).apply {
                text = presentation.text
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(TokensR.dimen.cabin_type_role_status_size),
                )
                importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
                tag = "text"
            },
        )

        val deepLink = item.deepLink
        if (deepLink != null) {
            container.isFocusable = true
            container.isClickable = true
            container.setOnClickListener {
                when (dispositionFor(deepLink.interaction)) {
                    GateDisposition.Allow -> deepLink.onActivate()
                    GateDisposition.Substitute,
                    GateDisposition.Block,
                    -> Unit
                }
            }
        } else {
            container.isClickable = false
            container.isFocusable = false
        }

        container.setTag(R.id.cabin_status_bar_item_tag, item)
        container.setTag(R.id.cabin_status_bar_tone_tag, presentation.tone)
        return container
    }

    private fun presentSignal(item: CabinStatusGlyph): StatusPresentation {
        val signal = item.signal
        if (signal == null) {
            return StatusPresentation(
                text = item.text ?: "",
                contentDescription = item.contentDescription,
                tone = StatusTone.Normal,
                showIconPlaceholder = false,
            )
        }
        return when (signal) {
            is Signal.Value -> StatusPresentation(
                text = item.text ?: signal.value.toString(),
                contentDescription = item.contentDescription,
                tone = StatusTone.Normal,
                showIconPlaceholder = false,
            )
            is Signal.Unavailable -> StatusPresentation(
                text = context.getString(R.string.cabin_status_unavailable),
                contentDescription = context.getString(
                    R.string.cabin_status_unavailable_a11y,
                    item.contentDescription,
                ),
                tone = StatusTone.Degraded,
                showIconPlaceholder = true,
            )
            is Signal.Stale -> StatusPresentation(
                text = context.getString(
                    R.string.cabin_status_stale_value,
                    item.text ?: signal.last.toString(),
                ),
                contentDescription = context.getString(
                    R.string.cabin_status_stale_a11y,
                    item.contentDescription,
                ),
                tone = StatusTone.Degraded,
                showIconPlaceholder = false,
            )
            is Signal.Fault -> StatusPresentation(
                text = context.getString(R.string.cabin_status_fault, signal.code),
                contentDescription = context.getString(
                    R.string.cabin_status_fault_a11y,
                    item.contentDescription,
                    signal.code,
                ),
                tone = StatusTone.Fault,
                showIconPlaceholder = true,
            )
        }
    }

    private fun applyComplianceToChildren() {
        applyChromeColors()
        for (i in 0 until row.childCount) {
            val child = row.getChildAt(i)
            val item = child.getTag(R.id.cabin_status_bar_item_tag) as? CabinStatusGlyph
                ?: continue
            val tone = child.getTag(R.id.cabin_status_bar_tone_tag) as? StatusTone
                ?: StatusTone.Normal
            applyToneColors(child, tone)
            val deepLink = item.deepLink
            if (deepLink != null) {
                applyGateVisual(child, dispositionFor(deepLink.interaction))
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
        } catch (_: IllegalArgumentException) {
            // Theme.Cabin not applied yet.
        }
    }

    private fun applyToneColors(view: View, tone: StatusTone) {
        val colors = try {
            CabinThemeResolver.resolveColors(context)
        } catch (_: IllegalArgumentException) {
            return
        }
        val contentColor = when (tone) {
            StatusTone.Normal -> colors.onContainer
            StatusTone.Degraded -> colors.outline
            StatusTone.Warning -> colors.warning
            StatusTone.Fault -> colors.error
            StatusTone.Charging -> colors.charging
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                when (val child = view.getChildAt(i)) {
                    is ImageView -> child.setColorFilter(contentColor)
                    is TextView -> child.setTextColor(contentColor)
                }
            }
        }
    }
}

/** Status Bar item contract. */
sealed interface CabinStatusItem {
    val id: String
    val contentDescription: String
}

/**
 * Glyph + optional text / signal / deep link.
 *
 * When [signal] is set, presentation is derived exhaustively from its state.
 */
data class CabinStatusGlyph(
    override val id: String,
    override val contentDescription: String,
    val icon: Drawable? = null,
    val text: CharSequence? = null,
    val signal: Signal<*>? = null,
    val deepLink: StatusDeepLink? = null,
) : CabinStatusItem

/**
 * Optional deep link from a status item.
 *
 * [opensSettings] selects settings vs informational Restriction Engine matrix
 * rows ([CabinInteraction.StatusDeepLinkSettings] /
 * [CabinInteraction.StatusDeepLinkInformational]).
 */
data class StatusDeepLink(
    val opensSettings: Boolean,
    val onActivate: () -> Unit,
) {
    val interaction: CabinInteraction
        get() = if (opensSettings) {
            CabinInteraction.StatusDeepLinkSettings
        } else {
            CabinInteraction.StatusDeepLinkInformational
        }
}

internal enum class StatusTone {
    Normal,
    Degraded,
    Warning,
    Fault,
    Charging,
}

internal data class StatusPresentation(
    val text: CharSequence,
    val contentDescription: String,
    val tone: StatusTone,
    val showIconPlaceholder: Boolean,
)

/** Token constants surfaced for tests / diagnostics. */
object CabinStatusBarTokens {
    val heightDp: Float = CabinTokens.Component.StatusBar.height.dp
    val iconSizeDp: Float = CabinTokens.Component.StatusBar.iconSize.dp
    val itemGapDp: Float = CabinTokens.Component.StatusBar.itemGap.dp
}

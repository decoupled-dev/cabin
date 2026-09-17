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
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Automotive now-playing: art, metadata, transport, source.
 *
 * Spec: docs/components/specs/media-now-playing.md
 *
 * Craft: cabin density (76dp transport), mediaAccent as mark only (not body /
 * washes), RE-quiet on complex while Moving, honest empty/stale, not a
 * Material media card.
 */
class CabinMediaNowPlayingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var state: CabinMediaNowPlayingState = CabinMediaNowPlayingState.empty()
    private var complianceHost: CabinComplianceHost? = null
    private var actionListener: ((CabinMediaNowPlayingAction) -> Unit)? = null

    /** Thin media accent mark — accent role only. */
    private val accentMark = View(context).apply { tag = "media_accent_mark" }
    private val artworkView = TextView(context).apply {
        gravity = Gravity.CENTER
        tag = "media_artwork"
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
    }
    private val titleView = TextView(context).apply { tag = "media_title" }
    private val artistView = TextView(context).apply { tag = "media_artist" }
    private val sourceView = TextView(context).apply {
        tag = "media_source"
        contentDescription = "Media source"
        isFocusable = true
        isClickable = true
        setOnClickListener {
            if (dispositionFor(CabinInteraction.MediaComplex) == GateDisposition.Allow) {
                actionListener?.invoke(CabinMediaNowPlayingAction.OpenSource)
            }
        }
    }
    private val progressView = TextView(context).apply {
        tag = "media_progress"
        visibility = View.GONE
    }

    private val previous = transportButton("⏮", "Previous", "media_previous") {
        emitTransport(CabinMediaNowPlayingAction.Previous)
    }
    private val playPause = transportButton("⏯", "Play or pause", "media_play_pause") {
        emitTransport(CabinMediaNowPlayingAction.PlayPause)
    }
    private val next = transportButton("⏭", "Next", "media_next") {
        emitTransport(CabinMediaNowPlayingAction.Next)
    }

    private val onComplianceChanged: () -> Unit = { applyCompliance() }

    private val transportMinPx: Int
        get() {
            val tokenMin = resources.getDimensionPixelSize(
                TokensR.dimen.cabin_component_media_now_playing_transportMinSize,
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

    private val artworkSizePx: Int
        get() = resources.getDimensionPixelSize(
            TokensR.dimen.cabin_component_media_now_playing_artworkSize,
        )

    private val gapPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_component_media_now_playing_gap)

    private val paddingPx: Int
        get() = resources.getDimensionPixelSize(
            TokensR.dimen.cabin_component_media_now_playing_padding,
        )

    private val accentMarkWidthPx: Int
        get() = resources.getDimensionPixelSize(TokensR.dimen.cabin_space_xs)

    init {
        orientation = VERTICAL
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
        // Flat cabin surface — no Material card wash.
        setBackgroundColor(android.graphics.Color.TRANSPARENT)
        val pad = paddingPx
        setPadding(pad, pad / 2, pad, pad / 2)

        titleView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_title_size),
        )
        artistView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_body_size),
        )
        sourceView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_label_size),
        )
        progressView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_status_size),
        )
        artworkView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(TokensR.dimen.cabin_type_role_status_size),
        )

        val sourceRow = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            addView(
                accentMark,
                LayoutParams(accentMarkWidthPx, gapPx * 2).apply { marginEnd = gapPx / 2 },
            )
            addView(sourceView)
        }
        val meta = LinearLayout(context).apply {
            orientation = VERTICAL
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
            addView(titleView)
            addView(artistView)
            addView(sourceRow)
        }
        val header = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            artworkView.layoutParams = LayoutParams(artworkSizePx, artworkSizePx)
            addView(artworkView)
            val metaLp = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
                marginStart = gapPx
            }
            meta.layoutParams = metaLp
            addView(meta)
        }
        addView(header)

        val transport = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER
            val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            lp.topMargin = gapPx
            layoutParams = lp
            addView(previous)
            addView(playPause)
            addView(next)
        }
        addView(transport)
        addView(
            progressView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                topMargin = gapPx
            },
        )

        applyChrome()
        bind(state)
    }

    fun bind(state: CabinMediaNowPlayingState) {
        this.state = state
        bindText(titleView, state.title, fallback = "No title")
        bindText(artistView, state.artist, fallback = "—")
        bindText(sourceView, state.sourceLabel, fallback = "No source")
        artworkView.text = if (state.artworkAvailable) "" else "—"
        artworkView.contentDescription = if (state.artworkAvailable) {
            "Album artwork"
        } else {
            "Artwork unavailable"
        }
        playPause.text = if (state.isPlaying) "⏸" else "▶"
        bindProgress(state)
        applyCompliance()
    }

    fun setOnActionListener(listener: ((CabinMediaNowPlayingAction) -> Unit)?) {
        actionListener = listener
    }

    /** Null host is fail-closed for transport and complex media actions. */
    fun setCompliance(host: CabinComplianceHost?) {
        complianceHost?.removeOnChangeListener(onComplianceChanged)
        complianceHost = host
        host?.addOnChangeListener(onComplianceChanged)
        applyCompliance()
    }

    @VisibleForTesting
    internal fun currentState(): CabinMediaNowPlayingState = state

    @VisibleForTesting
    internal fun findControl(tag: String): View? = when (tag) {
        "media_previous" -> previous
        "media_play_pause" -> playPause
        "media_next" -> next
        "media_source" -> sourceView
        "media_progress" -> progressView
        "media_title" -> titleView
        "media_artist" -> artistView
        "media_artwork" -> artworkView
        "media_accent_mark" -> accentMark
        else -> null
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

    private fun emitTransport(action: CabinMediaNowPlayingAction) {
        if (dispositionFor(CabinInteraction.MediaTransport) != GateDisposition.Allow) return
        if (!state.hasSource) return
        actionListener?.invoke(action)
    }

    private fun dispositionFor(interaction: CabinInteraction): GateDisposition {
        val host = complianceHost ?: return GateDisposition.Block
        return host.disposition(interaction)
    }

    private fun applyCompliance() {
        val transport = dispositionFor(CabinInteraction.MediaTransport)
        val complex = dispositionFor(CabinInteraction.MediaComplex)
        val hasSource = state.hasSource
        // Transport stays Allow while Moving; complex is RE-quiet when blocked.
        GateVisuals.applyQuiet(previous, hasSource, transport)
        GateVisuals.applyQuiet(playPause, hasSource, transport)
        GateVisuals.applyQuiet(next, hasSource, transport)
        GateVisuals.applyQuiet(sourceView, hasSource, complex)
        titleView.alpha = 1f
        artistView.alpha = 1f
        applyChrome()
    }

    private fun bindProgress(state: CabinMediaNowPlayingState) {
        val position = state.positionMs
        val duration = state.durationMs
        if (position is Signal.Value && duration is Signal.Value && duration.value > 0L) {
            progressView.visibility = View.VISIBLE
            progressView.text = formatProgress(position.value, duration.value)
        } else {
            // Honest media: no invented elapsed / remaining.
            progressView.visibility = View.GONE
            progressView.text = ""
        }
    }

    private fun bindText(view: TextView, signal: Signal<String>, fallback: String) {
        view.text = formatText(signal, fallback)
        view.contentDescription = when (signal) {
            is Signal.Value -> signal.value
            is Signal.Stale -> "${signal.last} (stale)"
            Signal.Unavailable -> fallback
            is Signal.Fault -> "Fault ${signal.code}"
        }
    }

    private fun applyChrome() {
        try {
            val colors = CabinThemeResolver.resolveColors(context)
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            artworkView.background = GradientDrawable().apply {
                // Neutral well — not a mediaAccent wash.
                setColor(android.graphics.Color.TRANSPARENT)
                setStroke(
                    resources.getDimensionPixelSize(TokensR.dimen.cabin_space_xs) / 2,
                    colors.outline,
                )
            }
            accentMark.setBackgroundColor(colors.mediaAccent)
            // Body copy stays onSurface; accent mark only.
            artworkView.setTextColor(colors.onSurface)
            titleView.setTextColor(colors.onSurface)
            artistView.setTextColor(colors.onSurface)
            sourceView.setTextColor(colors.onSurface)
            progressView.setTextColor(colors.onSurface)
            listOf(previous, playPause, next).forEach {
                it.setTextColor(colors.onSurface)
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

    private fun transportButton(
        label: String,
        description: String,
        tagValue: String,
        onClick: () -> Unit,
    ): TextView {
        return TextView(context).apply {
            text = label
            gravity = Gravity.CENTER
            contentDescription = description
            importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
            isFocusable = true
            isClickable = true
            tag = tagValue
            minimumWidth = transportMinPx
            minimumHeight = transportMinPx
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(TokensR.dimen.cabin_type_role_title_size),
            )
            setOnClickListener { onClick() }
        }
    }

    companion object {
        internal fun formatText(signal: Signal<String>, fallback: String): String = when (signal) {
            is Signal.Value -> signal.value
            is Signal.Stale -> "${signal.last} · stale"
            Signal.Unavailable -> fallback
            is Signal.Fault -> "Fault"
        }

        /**
         * Format live progress only. Callers must pass known values —
         * do not invent when Signals are unavailable.
         */
        internal fun formatProgress(positionMs: Long, durationMs: Long): String {
            fun mmss(ms: Long): String {
                val totalSec = (ms / 1000L).coerceAtLeast(0L)
                val m = totalSec / 60L
                val s = totalSec % 60L
                return "%d:%02d".format(m, s)
            }
            return "${mmss(positionMs)} / ${mmss(durationMs)}"
        }
    }
}

/** MediaNowPlaying presentation state (parity with Compose). */
data class CabinMediaNowPlayingState(
    val title: Signal<String>,
    val artist: Signal<String>,
    val sourceLabel: Signal<String>,
    val isPlaying: Boolean,
    val artworkAvailable: Boolean,
    val positionMs: Signal<Long>,
    val durationMs: Signal<Long>,
    val hasSource: Boolean = true,
) {
    companion object {
        fun empty(): CabinMediaNowPlayingState = CabinMediaNowPlayingState(
            title = Signal.Unavailable,
            artist = Signal.Unavailable,
            sourceLabel = Signal.Unavailable,
            isPlaying = false,
            artworkAvailable = false,
            positionMs = Signal.Unavailable,
            durationMs = Signal.Unavailable,
            hasSource = false,
        )
    }
}

sealed interface CabinMediaNowPlayingAction {
    data object PlayPause : CabinMediaNowPlayingAction
    data object Next : CabinMediaNowPlayingAction
    data object Previous : CabinMediaNowPlayingAction
    data object OpenSource : CabinMediaNowPlayingAction
    data class SeekTo(val positionMs: Long) : CabinMediaNowPlayingAction
}

object CabinMediaNowPlayingTokens {
    val artworkSizeDp: Float = CabinTokens.Component.MediaNowPlaying.artworkSize.dp
    val transportMinSizeDp: Float = CabinTokens.Component.MediaNowPlaying.transportMinSize.dp
}

package dev.decoupled.cabin.catalog

import android.content.Context
import android.graphics.drawable.ColorDrawable
import dev.decoupled.cabin.compliance.CabinInteraction
import dev.decoupled.cabin.compliance.Signal
import dev.decoupled.cabin.compose.CabinStatusEmphasis as ComposeStatusEmphasis
import dev.decoupled.cabin.compose.CabinStatusGlyph as ComposeStatusGlyph
import dev.decoupled.cabin.compose.CabinStatusItem as ComposeStatusItem
import dev.decoupled.cabin.compose.CabinSystemBarEntry as ComposeSystemBarEntry
import dev.decoupled.cabin.compose.CabinSystemBarSlots as ComposeSystemBarSlots
import dev.decoupled.cabin.compose.StatusDeepLink as ComposeStatusDeepLink
import dev.decoupled.cabin.views.CabinStatusEmphasis as ViewsStatusEmphasis
import dev.decoupled.cabin.views.CabinStatusGlyph as ViewsStatusGlyph
import dev.decoupled.cabin.views.CabinStatusItem as ViewsStatusItem
import dev.decoupled.cabin.views.CabinSystemBarEntry as ViewsSystemBarEntry
import dev.decoupled.cabin.views.CabinSystemBarSlots as ViewsSystemBarSlots
import dev.decoupled.cabin.views.StatusDeepLink as ViewsStatusDeepLink
import dev.decoupled.cabin.views.theme.CabinThemeResolver

/**
 * Shared demo fixtures for Compose + Views chrome.
 *
 * No media / HVAC / EV / vehicle-controls screens — chrome + signals only.
 */
object CatalogDemoData {

    fun composeSystemBarSlots(onAction: (String) -> Unit): ComposeSystemBarSlots =
        ComposeSystemBarSlots(
            leading = listOf(
                ComposeSystemBarEntry(
                    id = "home",
                    label = "Home",
                    contentDescription = "Home",
                    interaction = CabinInteraction.NavigateSimple,
                    onActivate = { onAction("Compose · Home") },
                ),
            ),
            center = listOf(
                ComposeSystemBarEntry(
                    id = "apps",
                    label = "Apps",
                    contentDescription = "Apps",
                    interaction = CabinInteraction.OpenComplexApp,
                    onActivate = { onAction("Compose · Apps") },
                ),
            ),
            trailing = listOf(
                ComposeSystemBarEntry(
                    id = "settings",
                    label = "Settings",
                    contentDescription = "Settings",
                    interaction = CabinInteraction.OpenComplexApp,
                    onActivate = { onAction("Compose · Settings") },
                ),
                ComposeSystemBarEntry(
                    id = "media",
                    label = "Media",
                    contentDescription = "Media transport",
                    interaction = CabinInteraction.MediaTransport,
                    onActivate = { onAction("Compose · Media") },
                ),
            ),
        )

    fun composeStatusItems(onAction: (String) -> Unit): List<ComposeStatusItem> = listOf(
        ComposeStatusGlyph(
            id = "range_live",
            contentDescription = "Range",
            text = "240 mi",
            signal = Signal.Value("240 mi", atMillis = 1_700_000_000_000L),
            deepLink = ComposeStatusDeepLink(
                opensSettings = false,
                onActivate = { onAction("Compose · Range info") },
            ),
        ),
        ComposeStatusGlyph(
            id = "temp_warning",
            contentDescription = "Cabin temperature",
            text = "98°F",
            signal = Signal.Value("98°F", atMillis = 1_700_000_000_000L),
            emphasis = ComposeStatusEmphasis.Warning,
        ),
        ComposeStatusGlyph(
            id = "charge_tone",
            contentDescription = "Charge",
            text = "82%",
            signal = Signal.Value("82%", atMillis = 1_700_000_000_000L),
            emphasis = ComposeStatusEmphasis.Charging,
            deepLink = ComposeStatusDeepLink(
                opensSettings = true,
                onActivate = { onAction("Compose · Charge settings") },
            ),
        ),
        ComposeStatusGlyph(
            id = "network_unavailable",
            contentDescription = "Network",
            signal = Signal.Unavailable,
        ),
        ComposeStatusGlyph(
            id = "speed_stale",
            contentDescription = "Speed",
            text = "42",
            signal = Signal.Stale(last = "42", atMillis = 1_699_000_000_000L),
        ),
        ComposeStatusGlyph(
            id = "tpms_fault",
            contentDescription = "Tire pressure",
            signal = Signal.Fault(code = "TPMS"),
            deepLink = ComposeStatusDeepLink(
                opensSettings = true,
                onActivate = { onAction("Compose · TPMS settings") },
            ),
        ),
    )

    fun viewsSystemBarSlots(
        context: Context,
        onAction: (String) -> Unit,
    ): ViewsSystemBarSlots {
        // Optional tinted placeholders — Theme Kit still colors content via attrs.
        val icon = ColorDrawable(CabinThemeResolver.resolveColors(context).onContainer)
        return ViewsSystemBarSlots(
            leading = listOf(
                ViewsSystemBarEntry(
                    id = "home",
                    icon = icon,
                    label = context.getString(R.string.catalog_home),
                    contentDescription = context.getString(R.string.catalog_home),
                    interaction = CabinInteraction.NavigateSimple,
                    onActivate = { onAction("Views · Home") },
                ),
            ),
            center = listOf(
                ViewsSystemBarEntry(
                    id = "apps",
                    label = context.getString(R.string.catalog_apps),
                    contentDescription = context.getString(R.string.catalog_apps),
                    interaction = CabinInteraction.OpenComplexApp,
                    onActivate = { onAction("Views · Apps") },
                ),
            ),
            trailing = listOf(
                ViewsSystemBarEntry(
                    id = "settings",
                    label = context.getString(R.string.catalog_settings),
                    contentDescription = context.getString(R.string.catalog_settings),
                    interaction = CabinInteraction.OpenComplexApp,
                    onActivate = { onAction("Views · Settings") },
                ),
                ViewsSystemBarEntry(
                    id = "media",
                    label = context.getString(R.string.catalog_media),
                    contentDescription = context.getString(R.string.catalog_media),
                    interaction = CabinInteraction.MediaTransport,
                    onActivate = { onAction("Views · Media") },
                ),
            ),
        )
    }

    fun viewsStatusItems(
        context: Context,
        onAction: (String) -> Unit,
    ): List<ViewsStatusItem> = listOf(
        ViewsStatusGlyph(
            id = "range_live",
            contentDescription = "Range",
            text = "240 mi",
            signal = Signal.Value("240 mi", atMillis = 1_700_000_000_000L),
            deepLink = ViewsStatusDeepLink(
                opensSettings = false,
                onActivate = { onAction("Views · Range info") },
            ),
        ),
        ViewsStatusGlyph(
            id = "temp_warning",
            contentDescription = "Cabin temperature",
            text = "98°F",
            signal = Signal.Value("98°F", atMillis = 1_700_000_000_000L),
            emphasis = ViewsStatusEmphasis.Warning,
        ),
        ViewsStatusGlyph(
            id = "charge_tone",
            contentDescription = "Charge",
            text = "82%",
            signal = Signal.Value("82%", atMillis = 1_700_000_000_000L),
            emphasis = ViewsStatusEmphasis.Charging,
            deepLink = ViewsStatusDeepLink(
                opensSettings = true,
                onActivate = { onAction("Views · Charge settings") },
            ),
        ),
        ViewsStatusGlyph(
            id = "network_unavailable",
            contentDescription = "Network",
            signal = Signal.Unavailable,
        ),
        ViewsStatusGlyph(
            id = "speed_stale",
            contentDescription = "Speed",
            text = "42",
            signal = Signal.Stale(last = "42", atMillis = 1_699_000_000_000L),
        ),
        ViewsStatusGlyph(
            id = "tpms_fault",
            contentDescription = "Tire pressure",
            signal = Signal.Fault(code = "TPMS"),
            deepLink = ViewsStatusDeepLink(
                opensSettings = true,
                onActivate = { onAction("Views · TPMS settings") },
            ),
        ),
    )
}

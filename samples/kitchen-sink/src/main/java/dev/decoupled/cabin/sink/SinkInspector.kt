package dev.decoupled.cabin.sink

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compose.CabinClimateTile
import dev.decoupled.cabin.compose.CabinMediaNowPlaying
import dev.decoupled.cabin.compose.CabinStatusBar
import dev.decoupled.cabin.compose.CabinSystemBar
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.foundation.CabinComponentUiState
import dev.decoupled.cabin.foundation.CabinScaffold

private enum class Playground {
    Default,
    Focused,
    Pressed,
    Selected,
    Disabled,
    Loading,
    Error,
    Restricted,
}

@OptIn(CabinScaffold::class)
@Composable
internal fun SinkInspector(
    id: String,
    onAction: (String) -> Unit,
) {
    val colors = LocalCabinColors.current
    val entry = SinkRegistry.entries.firstOrNull { it.id == id }
    var playground by remember { mutableStateOf(Playground.Default) }
    var variant by remember { mutableStateOf(entry?.variants?.firstOrNull().orEmpty()) }
    val ui = when (playground) {
        Playground.Default -> CabinComponentUiState()
        Playground.Focused -> CabinComponentUiState(focused = true)
        Playground.Pressed -> CabinComponentUiState(pressed = true)
        Playground.Selected -> CabinComponentUiState(selected = true)
        Playground.Disabled -> CabinComponentUiState(disabled = true)
        Playground.Loading -> CabinComponentUiState(loading = true)
        Playground.Error -> CabinComponentUiState(error = true)
        Playground.Restricted -> CabinComponentUiState(restricted = true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("sink_inspector"),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        BasicText(
            text = entry?.title ?: id,
            style = TextStyle(
                color = colors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        BasicText(
            text = "${entry?.family} · ${entry?.interaction} · ${entry?.module}",
            style = TextStyle(color = colors.outline, fontSize = 13.sp),
        )
        SinkChoiceRow(
            options = Playground.entries.map { it to it.name },
            selectedValue = playground,
            onSelect = { playground = it },
            testTagPrefix = "state",
        )
        if (!entry?.variants.isNullOrEmpty()) {
            SinkChoiceRow(
                options = entry!!.variants.map { it to it },
                selectedValue = variant,
                onSelect = { variant = it },
                testTagPrefix = "variant",
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, colors.outline.copy(alpha = 0.35f))
                .padding(12.dp)
                .testTag("sink_inspector_host"),
        ) {
            SinkHandwrittenOrGenerated(
                id = id,
                ui = ui,
                variant = variant,
                onAction = onAction,
            )
        }
    }
}

@OptIn(CabinScaffold::class)
@Composable
internal fun SinkHandwrittenOrGenerated(
    id: String,
    ui: CabinComponentUiState,
    variant: String,
    onAction: (String) -> Unit,
) {
    when (id) {
        "system-bar" -> CabinSystemBar(
            slots = SinkFixtures.systemBarSlots(onAction),
            modifier = Modifier.fillMaxWidth(),
        )
        "status-bar" -> CabinStatusBar(
            items = SinkFixtures.statusItems(onAction),
            modifier = Modifier.fillMaxWidth(),
        )
        "climate-tile" -> CabinClimateTile(
            state = SinkFixtures.climateLive(),
            onAction = { onAction("Climate · $it") },
        )
        "media-now-playing" -> CabinMediaNowPlaying(
            state = SinkFixtures.mediaLive(),
            onAction = { onAction("Media · $it") },
        )
        else -> SinkComponentDemo(
            id = id,
            ui = ui,
            variant = variant,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

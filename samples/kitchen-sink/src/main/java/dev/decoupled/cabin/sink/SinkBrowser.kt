package dev.decoupled.cabin.sink

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.foundation.CabinScaffold

@OptIn(CabinScaffold::class)
@Composable
internal fun SinkBrowser(onRoute: (SinkRoute) -> Unit) {
    val colors = LocalCabinColors.current
    var family by remember { mutableStateOf("action") }
    val entries = SinkRegistry.entries.filter { it.family == family }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .testTag("sink_browser"),
    ) {
        BasicText(
            text = "Components",
            style = TextStyle(
                color = colors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.padding(bottom = 8.dp),
        )
        SinkChoiceRow(
            options = SinkRegistry.families.map { it to it },
            selectedValue = family,
            onSelect = { family = it },
            testTagPrefix = "family",
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            entries.forEach { entry ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, colors.outline.copy(alpha = 0.4f))
                        .clickable { onRoute(SinkRoute.Inspector(entry.id)) }
                        .padding(12.dp)
                        .testTag("entry_${entry.id}"),
                ) {
                    BasicText(
                        text = entry.title,
                        style = TextStyle(
                            color = colors.onSurface,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                    BasicText(
                        text = "${entry.id} · ${entry.interaction}" +
                            if (entry.handwritten) " · Alpha" else " · scaffold",
                        style = TextStyle(color = colors.outline, fontSize = 12.sp),
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }
            }
        }
    }
}

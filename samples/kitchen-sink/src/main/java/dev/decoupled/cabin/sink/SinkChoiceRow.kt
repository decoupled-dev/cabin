package dev.decoupled.cabin.sink

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compose.theme.LocalCabinColors

@Composable
internal fun <T> SinkChoiceRow(
    options: List<Pair<T, String>>,
    selectedValue: T,
    onSelect: (T) -> Unit,
    testTagPrefix: String,
    modifier: Modifier = Modifier,
    selectedEquals: (T, T) -> Boolean = { a, b -> a == b },
) {
    val colors = LocalCabinColors.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        options.forEach { (value, label) ->
            val isSelected = selectedEquals(value, selectedValue)
            Box(
                modifier = Modifier
                    .border(1.dp, if (isSelected) colors.primary else colors.outline)
                    .background(
                        if (isSelected) colors.primary.copy(alpha = 0.12f) else Color.Transparent,
                    )
                    .clickable { onSelect(value) }
                    .semantics {
                        role = Role.Button
                        selected = isSelected
                    }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .testTag("${testTagPrefix}_$label"),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    text = label,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    ),
                )
            }
        }
    }
}

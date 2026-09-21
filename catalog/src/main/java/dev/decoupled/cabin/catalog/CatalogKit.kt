package dev.decoupled.cabin.catalog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.decoupled.cabin.compose.layout.CabinAdaptiveScaffold
import dev.decoupled.cabin.compose.layout.CabinSizeClassSwitcher
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.foundation.CabinDisplayProfile
import dev.decoupled.cabin.foundation.CabinScaffold
import dev.decoupled.cabin.foundation.CabinWindowSizeClass

@OptIn(CabinScaffold::class)
@Composable
fun CatalogKitSection(
    family: String,
    onFamilyChange: (String) -> Unit,
    sizeClass: CabinWindowSizeClass,
    onSizeClassChange: (CabinWindowSizeClass) -> Unit,
) {
    val colors = LocalCabinColors.current
    val entries = CatalogRegistry.entries.filter { it.family == family && !it.handwritten }
    Column(modifier = Modifier.testTag("catalog_kit")) {
        BasicText(
            text = "Experimental kit",
            style = TextStyle(
                color = colors.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.padding(bottom = 8.dp),
        )
        FamilyChipRow(
            selectedFamily = family,
            onSelect = onFamilyChange,
        )
        CabinSizeClassSwitcher(
            selectedClass = sizeClass,
            onSelect = onSizeClassChange,
            modifier = Modifier.padding(vertical = 8.dp),
        )
        CabinAdaptiveScaffold(
            profile = CabinDisplayProfile(sizeClass = sizeClass),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(bottom = 8.dp),
            primary = {
                BasicText(
                    text = "Primary · ${sizeClass.name}",
                    style = TextStyle(color = colors.onSurface, fontSize = 14.sp),
                    modifier = Modifier.padding(8.dp),
                )
            },
            secondary = {
                BasicText(
                    text = "Secondary",
                    style = TextStyle(color = colors.outline, fontSize = 14.sp),
                    modifier = Modifier.padding(8.dp),
                )
            },
        )
        if (entries.isEmpty()) {
            BasicText(
                text = "Handwritten family — see chrome above.",
                style = TextStyle(color = colors.outline, fontSize = 13.sp),
            )
        } else {
            entries.forEach { entry ->
                Box(modifier = Modifier.padding(bottom = 10.dp)) {
                    CatalogComponentDemo(id = entry.id, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
private fun FamilyChipRow(
    selectedFamily: String,
    onSelect: (String) -> Unit,
) {
    val colors = LocalCabinColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .testTag("catalog_family_row"),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CatalogRegistry.families.forEach { family ->
            val isSelected = family == selectedFamily
            Box(
                modifier = Modifier
                    .border(1.dp, if (isSelected) colors.primary else colors.outline)
                    .clickable { onSelect(family) }
                    .semantics {
                        role = Role.Button
                        selected = isSelected
                    }
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .testTag("family_$family"),
            ) {
                BasicText(
                    text = family,
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

package dev.decoupled.cabin.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import android.content.Context
import android.content.res.Configuration
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dev.decoupled.cabin.compliance.CabinRestrictionEngine
import dev.decoupled.cabin.compliance.CabinUiMode
import dev.decoupled.cabin.compliance.VehicleUiState
import dev.decoupled.cabin.compose.CabinStatusBar
import dev.decoupled.cabin.compose.CabinSystemBar
import dev.decoupled.cabin.compose.theme.CabinTheme
import dev.decoupled.cabin.compose.theme.LocalCabinColors
import dev.decoupled.cabin.tokens.CabinColorScheme
import dev.decoupled.cabin.tokens.CabinTokens
import dev.decoupled.cabin.views.CabinStatusBarView
import dev.decoupled.cabin.views.CabinSystemBarView
import dev.decoupled.cabin.views.compliance.CabinComplianceHost
import dev.decoupled.cabin.views.theme.CabinThemeResolver
import dev.decoupled.cabin.views.theme.CabinThemes

@Composable
fun CatalogApp(
    colorScheme: CabinColorScheme,
    onColorSchemeChange: (CabinColorScheme) -> Unit,
    vehicleState: VehicleUiState,
    onVehicleStateChange: (VehicleUiState) -> Unit,
    lastAction: String?,
    onAction: (String) -> Unit,
    kitFamily: String,
    onKitFamilyChange: (String) -> Unit,
    sizeClass: dev.decoupled.cabin.foundation.CabinWindowSizeClass,
    onSizeClassChange: (dev.decoupled.cabin.foundation.CabinWindowSizeClass) -> Unit,
) {
    CabinTheme(
        colorScheme = colorScheme,
        vehicleState = vehicleState,
    ) {
        val colors = LocalCabinColors.current
        val mode = CabinRestrictionEngine.Default.uiMode(vehicleState)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surface)
                .testTag("catalog_root"),
        ) {
            CatalogHeader(
                lastAction = lastAction,
                mode = mode,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                ThemeSection(
                    colorScheme = colorScheme,
                    onColorSchemeChange = onColorSchemeChange,
                )
                RestrictionSection(
                    vehicleState = vehicleState,
                    onVehicleStateChange = onVehicleStateChange,
                    mode = mode,
                )
                ComposeChromeSection(onAction = onAction)
                ViewsChromeSection(
                    colorScheme = colorScheme,
                    vehicleState = vehicleState,
                    onAction = onAction,
                )
                CatalogKitSection(
                    family = kitFamily,
                    onFamilyChange = onKitFamilyChange,
                    sizeClass = sizeClass,
                    onSizeClassChange = onSizeClassChange,
                )
                SignalToneLegend()
                Box(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun CatalogHeader(
    lastAction: String?,
    mode: CabinUiMode,
) {
    val colors = LocalCabinColors.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.container)
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .testTag("catalog_header"),
    ) {
        BasicText(
            text = stringResource(R.string.catalog_app_name),
            style = TextStyle(
                color = colors.onContainer,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        BasicText(
            text = stringResource(R.string.catalog_tagline),
            style = TextStyle(color = colors.outline, fontSize = 14.sp),
            modifier = Modifier.padding(top = 4.dp),
        )
        BasicText(
            text = "Mode: $mode",
            style = TextStyle(color = colors.onContainer, fontSize = 13.sp),
            modifier = Modifier
                .padding(top = 10.dp)
                .testTag("catalog_mode"),
        )
        BasicText(
            text = lastAction?.let { stringResource(R.string.catalog_last_action, it) }
                ?: stringResource(R.string.catalog_last_action_none),
            style = TextStyle(color = colors.outline, fontSize = 13.sp),
            modifier = Modifier
                .padding(top = 4.dp)
                .testTag("catalog_last_action"),
        )
    }
}

@Composable
private fun ThemeSection(
    colorScheme: CabinColorScheme,
    onColorSchemeChange: (CabinColorScheme) -> Unit,
) {
    val colors = LocalCabinColors.current
    SectionCard(title = stringResource(R.string.catalog_section_theme)) {
        ChoiceRow(
            options = listOf(
                CabinColorScheme.Day to stringResource(R.string.catalog_day),
                CabinColorScheme.Night to stringResource(R.string.catalog_night),
            ),
            selectedValue = colorScheme,
            onSelect = onColorSchemeChange,
            testTagPrefix = "theme",
        )
        BasicText(
            text = stringResource(R.string.catalog_locked_note),
            style = TextStyle(color = colors.outline, fontSize = 12.sp),
            modifier = Modifier.padding(top = 10.dp),
        )
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Swatch("outline", colors.outline)
            Swatch("container", colors.container, border = colors.outline)
            Swatch("warning", colors.warning)
            Swatch("error", colors.error)
            Swatch("charging", colors.charging)
        }
        BasicText(
            text = "Locks: ${CabinTokens.nightContrastLockedSchemeColors.joinToString()}",
            style = TextStyle(color = colors.onSurface, fontSize = 12.sp),
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
private fun RestrictionSection(
    vehicleState: VehicleUiState,
    onVehicleStateChange: (VehicleUiState) -> Unit,
    mode: CabinUiMode,
) {
    SectionCard(title = stringResource(R.string.catalog_section_restriction)) {
        ChoiceRow(
            options = CatalogDemoStates.all.map { it.state to it.label },
            selectedValue = CatalogDemoStates.all
                .firstOrNull { it.state.label == vehicleState.label }
                ?.state
                ?: CatalogDemoStates.all.first().state,
            onSelect = onVehicleStateChange,
            testTagPrefix = "restriction",
            optionTestTag = { state, _ ->
                "restriction_${CatalogDemoStates.tagFor(state)}"
            },
            selectedPredicate = { option, current ->
                option.label == current.label
            },
        )
        BasicText(
            text = "Gates System/Status bar activations via Restriction Engine ($mode).",
            style = TextStyle(color = LocalCabinColors.current.outline, fontSize = 12.sp),
            modifier = Modifier.padding(top = 10.dp),
        )
    }
}

@Composable
private fun ComposeChromeSection(onAction: (String) -> Unit) {
    SectionCard(title = stringResource(R.string.catalog_section_compose)) {
        CabinSystemBar(
            slots = CatalogDemoData.composeSystemBarSlots(onAction),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, LocalCabinColors.current.outline)
                .testTag("compose_system_bar_host"),
        )
        Box(modifier = Modifier.height(8.dp))
        CabinStatusBar(
            items = CatalogDemoData.composeStatusItems(onAction),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, LocalCabinColors.current.outline)
                .testTag("compose_status_bar_host"),
        )
    }
}

@Composable
private fun ViewsChromeSection(
    colorScheme: CabinColorScheme,
    vehicleState: VehicleUiState,
    onAction: (String) -> Unit,
) {
    val colors = LocalCabinColors.current
    SectionCard(title = stringResource(R.string.catalog_section_views)) {
        // Recreate Views chrome when day/night flips so Theme.Cabin resolves scheme resources.
        key(colorScheme) {
            AndroidView(
                factory = { context ->
                    val themed = themedCatalogContext(
                        context,
                        night = colorScheme == CabinColorScheme.Night,
                    )
                    val host = CabinComplianceHost(initialState = vehicleState)
                    val root = LinearLayout(themed).apply {
                        orientation = LinearLayout.VERTICAL
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        )
                    }
                    val systemBar = CabinSystemBarView(themed).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        )
                        setCompliance(host)
                        setSlots(CatalogDemoData.viewsSystemBarSlots(themed, onAction))
                    }
                    val statusBar = CabinStatusBarView(themed).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        ).also {
                            it.topMargin = (8 * themed.resources.displayMetrics.density).toInt()
                        }
                        setCompliance(host)
                        setItems(CatalogDemoData.viewsStatusItems(themed, onAction))
                    }
                    val caption = TextView(themed).apply {
                        text = "Views · Theme.Cabin · Restriction host"
                        setTextColor(CabinThemeResolver.resolveColors(themed).outline)
                        textSize = 12f
                        setPadding(0, (8 * themed.resources.displayMetrics.density).toInt(), 0, 0)
                    }
                    root.addView(systemBar)
                    root.addView(statusBar)
                    root.addView(caption)
                    root.tag = ViewsChromeHolder(host, systemBar, statusBar)
                    root
                },
                update = { root ->
                    val holder = root.tag as ViewsChromeHolder
                    holder.host.updateState(vehicleState)
                    holder.systemBar.setSlots(
                        CatalogDemoData.viewsSystemBarSlots(root.context, onAction),
                    )
                    holder.statusBar.setItems(
                        CatalogDemoData.viewsStatusItems(root.context, onAction),
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, colors.outline)
                    .testTag("views_chrome_host"),
            )
        }
    }
}

@Composable
private fun SignalToneLegend() {
    val colors = LocalCabinColors.current
    SectionCard(title = stringResource(R.string.catalog_section_signals)) {
        BasicText(
            text = "Status bars above show live / unavailable / stale / fault plus Warning and Charging tones.",
            style = TextStyle(color = colors.outline, fontSize = 12.sp),
        )
        Row(
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Swatch("warning", colors.warning)
            Swatch("error", colors.error)
            Swatch("charging", colors.charging)
            Swatch("degraded", colors.outline)
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit,
) {
    val colors = LocalCabinColors.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colors.outline.copy(alpha = 0.45f))
            .padding(14.dp),
    ) {
        BasicText(
            text = title,
            style = TextStyle(
                color = colors.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.padding(bottom = 10.dp),
        )
        content()
    }
}

@Composable
private fun <T> ChoiceRow(
    options: List<Pair<T, String>>,
    selectedValue: T,
    onSelect: (T) -> Unit,
    testTagPrefix: String,
    optionTestTag: ((T, String) -> String)? = null,
    selectedPredicate: ((T, T) -> Boolean)? = null,
) {
    val colors = LocalCabinColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        options.forEach { (value, label) ->
            val isSelected = selectedPredicate?.invoke(value, selectedValue)
                ?: (value == selectedValue)
            val tag = optionTestTag?.invoke(value, label) ?: "${testTagPrefix}_$label"
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = if (isSelected) colors.primary else colors.outline,
                    )
                    .background(if (isSelected) colors.primary.copy(alpha = 0.12f) else Color.Transparent)
                    .clickable { onSelect(value) }
                    .semantics {
                        role = Role.RadioButton
                        selected = isSelected
                    }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .testTag(tag),
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

@Composable
private fun Swatch(
    label: String,
    color: Color,
    border: Color = color,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color)
                .border(1.dp, border)
                .testTag("swatch_$label"),
        )
        BasicText(
            text = label,
            style = TextStyle(color = LocalCabinColors.current.onSurface, fontSize = 11.sp),
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

private data class ViewsChromeHolder(
    val host: CabinComplianceHost,
    val systemBar: CabinSystemBarView,
    val statusBar: CabinStatusBarView,
)

/** Apply Theme.Cabin under an explicit day/night configuration. */
internal fun themedCatalogContext(base: Context, night: Boolean): Context {
    val config = Configuration(base.resources.configuration)
    val nightBits = if (night) {
        Configuration.UI_MODE_NIGHT_YES
    } else {
        Configuration.UI_MODE_NIGHT_NO
    }
    config.uiMode = (config.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or nightBits
    val configured = base.createConfigurationContext(config)
    return CabinThemeResolver.wrap(configured, CabinThemes.ThemeCabin)
}

/** Demo Restriction Engine fixtures for the catalog shell. */
object CatalogDemoStates {
    data class DemoState(val state: VehicleUiState, val label: String, val tag: String)

    val all: List<DemoState> = listOf(
        DemoState(VehicleUiState.parked(), "Parked", "parked"),
        DemoState(VehicleUiState.idling(), "Idling", "idling"),
        DemoState(VehicleUiState.moving(), "Moving", "moving"),
        DemoState(VehicleUiState.restricted(), "Restricted", "restricted"),
    )

    fun tagFor(state: VehicleUiState): String =
        all.firstOrNull { it.state.label == state.label }?.tag
            ?: state.label?.lowercase() ?: "unknown"
}

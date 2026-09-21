#!/usr/bin/env python3
"""Generate Experimental Cabin component scaffolds from the inventory.

Emits:
  1. components/cabin.components.yaml (JSON subset, checked in)
  2. Foundation State/Action types
  3. Compose scaffold composables
  4. Views scaffold widgets when stacks includes views
  5. Gauges scaffold composables
  6. Catalog registry + demo switch
  7. docs/components/coverage.generated.md
  8. Marketing kit catalog (apps/www Theme Builder)

Usage:
    python3 tools/generate_cabin_components.py
    python3 tools/generate_cabin_components.py --check
"""

from __future__ import annotations

import argparse
import json
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "tools"))

from cabin_components_inventory import load_inventory  # noqa: E402

YAML_OUT = ROOT / "components" / "cabin.components.yaml"
COVERAGE_OUT = ROOT / "docs" / "components" / "coverage.generated.md"

FOUNDATION_DIR = (
    ROOT
    / "cabin-foundation"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "foundation"
    / "components"
)
COMPOSE_DIR = (
    ROOT
    / "cabin-compose"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "compose"
)
VIEWS_DIR = (
    ROOT
    / "cabin-views"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "views"
)
GAUGES_DIR = (
    ROOT
    / "cabin-gauges"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "gauges"
)
CATALOG_DIR = (
    ROOT
    / "catalog"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "catalog"
)
SINK_DIR = (
    ROOT
    / "samples"
    / "kitchen-sink"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "sink"
)
WWW_CATALOG_OUT = (
    ROOT / "apps" / "www" / "src" / "lib" / "kit-catalog.generated.ts"
)

COMPOSE_FAMILY_PKG = {
    "nav": "maps",
}

HEADER = (
    "GENERATED from components/cabin.components.yaml. "
    "Do not edit by hand — run python3 tools/generate_cabin_components.py."
)


def compose_pkg(family: str) -> str:
    return COMPOSE_FAMILY_PKG.get(family, family)


def kotlin_file_header(package: str) -> str:
    return "\n".join(
        [
            f"@file:OptIn(dev.decoupled.cabin.foundation.CabinScaffold::class)",
            "",
            f"package {package}",
            "",
            f"/** {HEADER} */",
            "",
        ]
    )


def write(path: Path, content: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding="utf-8")


def dump_yaml(items: list[dict]) -> str:
    return json.dumps({"version": 1, "components": items}, indent=2) + "\n"


def foundation_models(family: str, items: list[dict]) -> str:
    pkg = f"dev.decoupled.cabin.foundation.components.{compose_pkg(family)}"
    uses_signal = any(item["telemetry"] for item in items)
    lines = [
        kotlin_file_header(pkg),
        "import dev.decoupled.cabin.compliance.CabinInteraction",
    ]
    if uses_signal:
        lines.append("import dev.decoupled.cabin.compliance.Signal")
    lines += [
        "import dev.decoupled.cabin.foundation.CabinComponentUiState",
        "import dev.decoupled.cabin.foundation.CabinScaffold",
        "",
    ]
    for item in items:
        name = item["typeName"]
        variants = item["variants"]
        default_variant = f'"{variants[0]}"' if variants else '""'
        signal_prop = ""
        if item["telemetry"]:
            signal_prop = "    val reading: Signal<Int> = Signal.Unavailable,\n"
        lines += [
            "@CabinScaffold",
            f"data class Cabin{name}State(",
            f'    val label: String = "{item["title"]}",',
            f"    val variant: String = {default_variant},",
            signal_prop + "    val ui: CabinComponentUiState = CabinComponentUiState(),",
            ")",
            "",
            "@CabinScaffold",
            f"sealed interface Cabin{name}Action {{",
            f"    data object Activate : Cabin{name}Action",
            "}",
            "",
            f"val Cabin{name}Interaction: CabinInteraction = CabinInteraction.{item['interaction']}",
            "",
        ]
    return "\n".join(lines)


def compose_scaffolds(family: str, items: list[dict]) -> str:
    pkg = f"dev.decoupled.cabin.compose.{compose_pkg(family)}"
    fpkg = f"dev.decoupled.cabin.foundation.components.{compose_pkg(family)}"
    lines = [
        kotlin_file_header(pkg),
        "import androidx.compose.runtime.Composable",
        "import androidx.compose.ui.Modifier",
        "import dev.decoupled.cabin.compose.scaffold.CabinScaffoldHost",
        f"import {fpkg}.*",
        "",
    ]
    for item in items:
        name = item["typeName"]
        tag = f"cabin_{item['id'].replace('-', '_')}"
        lines += [
            "/**",
            f" * Experimental scaffold: {item['title']}.",
            " * Restriction-aware; token-driven host. Not a production visual.",
            " */",
            "@dev.decoupled.cabin.foundation.CabinScaffold",
            "@Composable",
            f"fun Cabin{name}(",
            f"    state: Cabin{name}State = Cabin{name}State(),",
            f"    onAction: (Cabin{name}Action) -> Unit = {{}},",
            "    modifier: Modifier = Modifier,",
            ") {",
            "    CabinScaffoldHost(",
            f'        title = state.label.ifBlank {{ "{item["title"]}" }},',
            f'        family = "{item["family"]}",',
            f'        testTag = "{tag}",',
            f"        interaction = Cabin{name}Interaction,",
            "        ui = state.ui,",
            f'        variant = state.variant,',
            f"        onActivate = {{ onAction(Cabin{name}Action.Activate) }},",
            "        modifier = modifier,",
            "    )",
            "}",
            "",
        ]
    return "\n".join(lines)


def views_scaffolds(family: str, items: list[dict]) -> str:
    pkg = f"dev.decoupled.cabin.views.{compose_pkg(family)}"
    fpkg = f"dev.decoupled.cabin.foundation.components.{compose_pkg(family)}"
    lines = [
        kotlin_file_header(pkg),
        "import android.content.Context",
        "import android.util.AttributeSet",
        "import dev.decoupled.cabin.views.scaffold.CabinScaffoldView",
        f"import {fpkg}.*",
        "",
    ]
    for item in items:
        name = item["typeName"]
        tag = f"cabin_{item['id'].replace('-', '_')}"
        lines += [
            "@dev.decoupled.cabin.foundation.CabinScaffold",
            f"class Cabin{name}View @JvmOverloads constructor(",
            "    context: Context,",
            "    attrs: AttributeSet? = null,",
            "    defStyleAttr: Int = 0,",
            f") : CabinScaffoldView(context, attrs, defStyleAttr) {{",
            f"    private var state: Cabin{name}State = Cabin{name}State()",
            f"    private var actionListener: ((Cabin{name}Action) -> Unit)? = null",
            "",
            "    init {",
            f'        bindHost("{item["title"]}", "{item["family"]}", "{tag}", Cabin{name}Interaction)',
            "        setOnActivate { actionListener?.invoke(Cabin"
            + name
            + "Action.Activate) }",
            "    }",
            "",
            f"    fun bind(state: Cabin{name}State) {{",
            "        this.state = state",
            "        applyState(state.label, state.variant, state.ui)",
            "    }",
            "",
            f"    fun setOnAction(listener: ((Cabin{name}Action) -> Unit)?) {{",
            "        actionListener = listener",
            "    }",
            "",
            "    override fun onAttachedToWindow() {",
            "        super.onAttachedToWindow()",
            "        applyState(state.label, state.variant, state.ui)",
            "    }",
            "}",
            "",
        ]
    return "\n".join(lines)


def gauges_scaffolds(items: list[dict]) -> str:
    pkg = "dev.decoupled.cabin.gauges"
    fpkg = "dev.decoupled.cabin.foundation.components.gauges"
    lines = [
        kotlin_file_header(pkg),
        "import androidx.compose.runtime.Composable",
        "import androidx.compose.ui.Modifier",
        "import dev.decoupled.cabin.gauges.scaffold.CabinGaugeHost",
        f"import {fpkg}.*",
        "",
    ]
    for item in items:
        name = item["typeName"]
        tag = f"cabin_{item['id'].replace('-', '_')}"
        lines += [
            "@dev.decoupled.cabin.foundation.CabinScaffold",
            "@Composable",
            f"fun Cabin{name}(",
            f"    state: Cabin{name}State = Cabin{name}State(),",
            f"    onAction: (Cabin{name}Action) -> Unit = {{}},",
            "    modifier: Modifier = Modifier,",
            ") {",
            "    CabinGaugeHost(",
            f'        title = state.label.ifBlank {{ "{item["title"]}" }},',
            f'        testTag = "{tag}",',
            f"        interaction = Cabin{name}Interaction,",
            "        ui = state.ui,",
            f"        onActivate = {{ onAction(Cabin{name}Action.Activate) }},",
            "        modifier = modifier,",
            '        family = "gauges",',
            "    )",
            "}",
            "",
        ]
    return "\n".join(lines)


def catalog_registry(
    items: list[dict],
    *,
    package: str = "dev.decoupled.cabin.catalog",
    entry_type: str = "CatalogEntry",
    object_name: str = "CatalogRegistry",
) -> str:
    lines = [
        kotlin_file_header(package),
        f"data class {entry_type}(",
        "    val id: String,",
        "    val typeName: String,",
        "    val family: String,",
        "    val title: String,",
        "    val interaction: String,",
        "    val stacks: List<String>,",
        "    val variants: List<String>,",
        "    val handwritten: Boolean,",
        "    val module: String,",
        "    val testTag: String,",
        ")",
        "",
        f"object {object_name} {{",
        f"    val entries: List<{entry_type}> = listOf(",
    ]
    for item in items:
        stacks = ", ".join(f'"{s}"' for s in item["stacks"])
        variants = ", ".join(f'"{v}"' for v in item["variants"])
        hw = "true" if item["handwritten"] else "false"
        tag = f"cabin_{item['id'].replace('-', '_')}"
        lines.append(
            f"        {entry_type}("
            f'id = "{item["id"]}", typeName = "{item["typeName"]}", '
            f'family = "{item["family"]}", title = "{item["title"]}", '
            f'interaction = "{item["interaction"]}", stacks = listOf({stacks}), '
            f"variants = listOf({variants}), handwritten = {hw}, "
            f'module = "{item["module"]}", testTag = "{tag}"),'
        )
    lines += [
        "    )",
        "",
        "    val families: List<String> = entries.map { it.family }.distinct().sorted()",
        "}",
        "",
    ]
    return "\n".join(lines)


def catalog_demo(
    items: list[dict],
    *,
    package: str = "dev.decoupled.cabin.catalog",
    fun_name: str = "CatalogComponentDemo",
) -> str:
    generated = [i for i in items if not i["handwritten"]]
    imports: set[str] = {
        "import androidx.compose.runtime.Composable",
        "import androidx.compose.ui.Modifier",
        "import dev.decoupled.cabin.foundation.CabinComponentUiState",
    }
    for item in generated:
        name = item["typeName"]
        if item["module"] == "gauges":
            imports.add(f"import dev.decoupled.cabin.gauges.Cabin{name}")
            imports.add(
                f"import dev.decoupled.cabin.foundation.components.gauges.Cabin{name}State"
            )
        else:
            pkg = compose_pkg(item["family"])
            imports.add(f"import dev.decoupled.cabin.compose.{pkg}.Cabin{name}")
            imports.add(
                f"import dev.decoupled.cabin.foundation.components.{pkg}.Cabin{name}State"
            )
    lines = [
        kotlin_file_header(package),
        *sorted(imports),
        "",
        "@Composable",
        f"fun {fun_name}(",
        "    id: String,",
        "    modifier: Modifier = Modifier,",
        "    ui: CabinComponentUiState = CabinComponentUiState(),",
        '    variant: String = "",',
        ") {",
        "    when (id) {",
    ]
    for item in generated:
        name = item["typeName"]
        lines.append(
            f'        "{item["id"]}" -> Cabin{name}('
            f"state = Cabin{name}State("
            f"variant = variant.ifBlank {{ Cabin{name}State().variant }}, "
            f"ui = ui), modifier = modifier)"
        )
    lines += [
        "        else -> {}",
        "    }",
        "}",
        "",
    ]
    return "\n".join(lines)


def www_kit_catalog(items: list[dict]) -> str:
    slim = [
        {
            "id": item["id"],
            "typeName": item["typeName"],
            "family": item["family"],
            "title": item["title"],
            "interaction": item["interaction"],
            "stacks": item["stacks"],
            "variants": item["variants"],
            "handwritten": item["handwritten"],
            "module": item["module"],
        }
        for item in items
    ]
    body = json.dumps(slim, indent=2)
    return (
        f"/** {HEADER} */\n\n"
        "export type KitCatalogEntry = {\n"
        "  id: string;\n"
        "  typeName: string;\n"
        "  family: string;\n"
        "  title: string;\n"
        "  interaction: string;\n"
        "  stacks: string[];\n"
        "  variants: string[];\n"
        "  handwritten: boolean;\n"
        "  module: string;\n"
        "};\n\n"
        f"export const KIT_CATALOG: readonly KitCatalogEntry[] = {body};\n"
    )


def coverage_md(items: list[dict]) -> str:
    lines = [
        "<!-- " + HEADER + " -->",
        "",
        "# Component coverage map (generated)",
        "",
        "Experimental scaffold status. Existing System/Status bars, ClimateTile,",
        "and MediaNowPlaying remain Alpha / handwritten. Everything else is a",
        "callable stub: renders, exposes Activate, honors Restriction Engine.",
        "",
        "| Id | Family | Compose | Views | Interaction | Module | Notes |",
        "| --- | --- | --- | --- | --- | --- | --- |",
    ]
    for item in items:
        compose = "handwritten" if item["handwritten"] else "scaffold"
        views = "scaffold" if "views" in item["stacks"] else "—"
        if item["handwritten"] and "views" in item["stacks"]:
            views = "Alpha"
        if item["module"] == "gauges":
            compose = "gauges-scaffold"
        notes = "variants: " + ", ".join(item["variants"]) if item["variants"] else ""
        lines.append(
            f"| `{item['id']}` | {item['family']} | {compose} | {views} | "
            f"{item['interaction']} | {item['module']} | {notes} |"
        )
    lines.append("")
    return "\n".join(lines)


def collect_outputs(items: list[dict]) -> dict[Path, str]:
    outputs: dict[Path, str] = {
        YAML_OUT: dump_yaml(items),
        COVERAGE_OUT: coverage_md(items),
        CATALOG_DIR / "CatalogRegistry.generated.kt": catalog_registry(items),
        CATALOG_DIR / "CatalogComponentDemo.generated.kt": catalog_demo(items),
        SINK_DIR / "SinkRegistry.generated.kt": catalog_registry(
            items,
            package="dev.decoupled.cabin.sink",
            entry_type="SinkEntry",
            object_name="SinkRegistry",
        ),
        SINK_DIR / "SinkComponentDemo.generated.kt": catalog_demo(
            items,
            package="dev.decoupled.cabin.sink",
            fun_name="SinkComponentDemo",
        ),
        WWW_CATALOG_OUT: www_kit_catalog(items),
    }

    by_family: dict[str, list[dict]] = defaultdict(list)
    for item in items:
        if item["handwritten"]:
            continue
        by_family[item["family"]].append(item)

    for family, family_items in by_family.items():
        fname = compose_pkg(family)
        outputs[FOUNDATION_DIR / fname / f"Cabin{fname.capitalize()}Models.generated.kt"] = (
            foundation_models(family, family_items)
        )
        if family == "gauges" or family_items[0]["module"] == "gauges":
            outputs[GAUGES_DIR / "CabinGauges.generated.kt"] = gauges_scaffolds(
                family_items
            )
        else:
            outputs[COMPOSE_DIR / fname / f"Cabin{fname.capitalize()}Scaffold.generated.kt"] = (
                compose_scaffolds(family, family_items)
            )
        views_items = [i for i in family_items if "views" in i["stacks"]]
        if views_items:
            outputs[VIEWS_DIR / fname / f"Cabin{fname.capitalize()}Views.generated.kt"] = (
                views_scaffolds(family, views_items)
            )
    return outputs


def generate(*, write_files: bool = True) -> dict[Path, str]:
    items = load_inventory()
    outputs = collect_outputs(items)
    if write_files:
        for path, content in outputs.items():
            write(path, content)
            print(f"wrote {path.relative_to(ROOT)}")
    return outputs


def check_drift() -> int:
    outputs = generate(write_files=False)
    drifted: list[str] = []
    for path, expected in outputs.items():
        rel = path.relative_to(ROOT).as_posix()
        if not path.is_file():
            drifted.append(f"missing {rel}")
            continue
        if path.read_text(encoding="utf-8") != expected:
            drifted.append(f"stale {rel}")
    if drifted:
        print("error: component codegen drift detected:", file=sys.stderr)
        for item in drifted:
            print(f"  - {item}", file=sys.stderr)
        print("\nRun: python3 tools/generate_cabin_components.py", file=sys.stderr)
        return 1
    print("ok: generated component scaffolds match inventory")
    return 0


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="Generate Cabin component scaffolds")
    parser.add_argument("--check", action="store_true")
    args = parser.parse_args(argv)
    if args.check:
        return check_drift()
    generate(write_files=True)
    return 0


if __name__ == "__main__":
    sys.exit(main())

#!/usr/bin/env python3
"""Generate Cabin tokens from tokens/cabin.tokens.json (single source of truth).

Emits:
  1. cabin-tokens — Kotlin constants + Android resources / Theme.Cabin attrs
  2. cabin-compose — CabinTheme Color mappings (same semantic names)
  3. CSS variables — apps/www/src/styles/cabin.tokens.css (+ tokens/generated/)

Catalog consumes generated Kotlin/Android outputs via cabin-tokens.

Usage:
    python3 tools/generate_cabin_tokens.py
    python3 tools/generate_cabin_tokens.py --check   # CI drift gate
    ./gradlew :cabin-tokens:generateCabinTokens
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TOKENS_JSON = ROOT / "tokens" / "cabin.tokens.json"

KOTLIN_OUT = (
    ROOT
    / "cabin-tokens"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "tokens"
    / "CabinTokens.generated.kt"
)
RES_VALUES = ROOT / "cabin-tokens" / "src" / "main" / "res" / "values"
RES_NIGHT = ROOT / "cabin-tokens" / "src" / "main" / "res" / "values-night"

COMPOSE_OUT = (
    ROOT
    / "cabin-compose"
    / "src"
    / "main"
    / "java"
    / "dev"
    / "decoupled"
    / "cabin"
    / "compose"
    / "theme"
    / "CabinComposeTokens.generated.kt"
)

CSS_WWW_OUT = ROOT / "apps" / "www" / "src" / "styles" / "cabin.tokens.css"
CSS_GENERATED_OUT = ROOT / "tokens" / "generated" / "cabin.tokens.css"
GENERATED_README = ROOT / "tokens" / "generated" / "README.md"

REQUIRED_NIGHT_LOCKS = ("warning", "error", "charging")
LOCK_SAFETY = "safety-adjacent"

THEME_ATTRS: list[tuple[str, str]] = [
    ("Primary", "primary"),
    ("OnPrimary", "onPrimary"),
    ("Secondary", "secondary"),
    ("OnSecondary", "onSecondary"),
    ("Surface", "surface"),
    ("OnSurface", "onSurface"),
    ("SurfaceVariant", "surfaceVariant"),
    ("Outline", "outline"),
    ("Container", "container"),
    ("OnContainer", "onContainer"),
    ("Success", "success"),
    ("Warning", "warning"),
    ("Error", "error"),
    ("Charging", "charging"),
    ("Climate", "climate"),
    ("MediaAccent", "mediaAccent"),
    ("Scrim", "scrim"),
]


def resolve_ref(doc: dict, ref: str) -> dict:
    if not ref.startswith("#/"):
        raise ValueError(f"Unsupported $ref: {ref}")
    node: object = doc
    for part in ref[2:].split("/"):
        if not isinstance(node, dict) or part not in node:
            raise KeyError(f"Unresolved $ref: {ref}")
        node = node[part]
    if not isinstance(node, dict):
        raise TypeError(f"$ref did not point to object: {ref}")
    if "$ref" in node:
        return resolve_ref(doc, node["$ref"])
    return node


def leaf(doc: dict, node: dict) -> dict:
    if "$ref" in node:
        return resolve_ref(doc, node["$ref"])
    return node


def parse_color(value: str) -> tuple[str, int]:
    raw = value.removeprefix("#")
    if len(raw) == 6:
        argb = int(raw, 16) | 0xFF000000
        if argb >= 0x80000000:
            argb -= 0x100000000
        hex8 = "#" + raw.upper()
    elif len(raw) == 8:
        # Stub uses RRGGBBAA; Android wants AARRGGBB.
        rr, gg, bb, aa = raw[0:2], raw[2:4], raw[4:6], raw[6:8]
        argb = int(aa + rr + gg + bb, 16)
        if argb >= 0x80000000:
            argb -= 0x100000000
        hex8 = "#" + (aa + rr + gg + bb).upper()
    else:
        raise ValueError(f"Unsupported color: {value}")
    return hex8, argb


def css_color(value: str) -> str:
    raw = value.removeprefix("#")
    if len(raw) == 6:
        return "#" + raw.upper()
    if len(raw) == 8:
        rr, gg, bb, aa = raw[0:2], raw[2:4], raw[4:6], raw[6:8]
        alpha = int(aa, 16) / 255.0
        return f"rgba({int(rr, 16)}, {int(gg, 16)}, {int(bb, 16)}, {alpha:.4g})"
    raise ValueError(f"Unsupported color: {value}")


def resource_name(*parts: str) -> str:
    return "cabin_" + "_".join(parts)


def css_var(*parts: str) -> str:
    return "--cabin-" + "-".join(parts)


def kotlin_const(name: str) -> str:
    return name[0].upper() + name[1:] if name else name


def camel_to_snake(name: str) -> str:
    return re.sub(r"(?<!^)(?=[A-Z])", "_", name).lower()


def camel_to_kebab(name: str) -> str:
    return re.sub(r"(?<!^)(?=[A-Z])", "-", name).lower()


def dedupe(items: list[tuple[str, str]]) -> list[tuple[str, str]]:
    seen: set[str] = set()
    out: list[tuple[str, str]] = []
    for key, value in items:
        if key in seen:
            continue
        seen.add(key)
        out.append((key, value))
    return out


def write(path: Path, content: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding="utf-8")
    print(f"wrote {path.relative_to(ROOT)}")


def indent(lines: list[str], spaces: int) -> str:
    pad = " " * spaces
    return "\n".join(pad + line if line else line for line in lines)


def token_lock(entry: dict) -> str | None:
    return entry.get("extensions", {}).get("cabin.lock")


def validate_safety_locks(doc: dict) -> None:
    """Fail closed so OEMs cannot soft-wash night warning / error / charging."""
    errors: list[str] = []
    night = doc["cabin"]["color"]["scheme"]["night"]
    for role in REQUIRED_NIGHT_LOCKS:
        if role not in night:
            errors.append(f"missing cabin.color.scheme.night.{role}")
            continue
        entry = leaf(doc, night[role])
        lock = token_lock(entry)
        if lock != LOCK_SAFETY:
            errors.append(
                f'cabin.color.scheme.night.{role} must set '
                f'extensions["cabin.lock"] = "{LOCK_SAFETY}" (found {lock!r})'
            )
    semantic = doc["cabin"]["color"]["semantic"]
    for role in ("warning", "error"):
        entry = leaf(doc, semantic[role])
        lock = token_lock(entry)
        if lock != LOCK_SAFETY:
            errors.append(
                f'cabin.color.semantic.{role} must set '
                f'extensions["cabin.lock"] = "{LOCK_SAFETY}" (found {lock!r})'
            )
    if errors:
        raise SystemExit(
            "error: safety lock check failed:\n  - " + "\n  - ".join(errors)
        )


def colors_xml(entries: list[tuple[str, str]], comment: str) -> str:
    lines = [
        '<?xml version="1.0" encoding="utf-8"?>',
        f"<!-- GENERATED from tokens/cabin.tokens.json. {comment}. Do not edit. -->",
        "<resources>",
    ]
    for name, value in entries:
        lines.append(f'    <color name="{name}">{value}</color>')
    lines.append("</resources>")
    lines.append("")
    return "\n".join(lines)


def dimens_xml(entries: list[tuple[str, str]]) -> str:
    lines = [
        '<?xml version="1.0" encoding="utf-8"?>',
        "<!-- GENERATED from tokens/cabin.tokens.json. Do not edit. -->",
        "<resources>",
    ]
    for name, value in entries:
        lines.append(f'    <dimen name="{name}">{value}</dimen>')
    lines.append("</resources>")
    lines.append("")
    return "\n".join(lines)


def integers_xml(entries: list[tuple[str, str]]) -> str:
    lines = [
        '<?xml version="1.0" encoding="utf-8"?>',
        "<!-- GENERATED from tokens/cabin.tokens.json. Do not edit. -->",
        "<resources>",
    ]
    for name, value in entries:
        lines.append(f'    <integer name="{name}">{value}</integer>')
    lines.append("</resources>")
    lines.append("")
    return "\n".join(lines)


def strings_xml(entries: list[tuple[str, str]]) -> str:
    lines = [
        '<?xml version="1.0" encoding="utf-8"?>',
        "<!-- GENERATED from tokens/cabin.tokens.json. Do not edit. -->",
        "<resources>",
    ]
    for name, value in entries:
        lines.append(
            f'    <string name="{name}" translatable="false">{value}</string>'
        )
    lines.append("</resources>")
    lines.append("")
    return "\n".join(lines)


def attrs_xml() -> str:
    lines = [
        '<?xml version="1.0" encoding="utf-8"?>',
        "<!-- GENERATED from tokens/cabin.tokens.json. Theme / RRO attr hooks. Do not edit. -->",
        "<resources>",
    ]
    for suffix, _role in THEME_ATTRS:
        lines.append(
            f'    <attr name="cabin_color{suffix}" format="color|reference" />'
        )
    lines.append("</resources>")
    lines.append("")
    return "\n".join(lines)


def build_kotlin(
    version: str,
    locks: list[str],
    night_locks: list[str],
    semantic_lines: list[str],
    scheme_props: list[str],
    scheme_when: list[str],
    type_lines: list[str],
    space_lines: list[str],
    size_lines: list[str],
    elevation_lines: list[str],
    motion_lines: list[str],
    component_lines: list[str],
) -> str:
    lock_set = ", ".join(f'"{n}"' for n in locks)
    night_set = ", ".join(f'"{n}"' for n in night_locks)
    body: list[str] = [
        '@file:JvmName("CabinTokensGenerated")',
        "",
        "package dev.decoupled.cabin.tokens",
        "",
        "/**",
        " * Public Cabin token constants generated from `tokens/cabin.tokens.json`.",
        " *",
        " * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.",
        f" * Stub version: {version}",
        " *",
        " * This module has no Compose, Views widget, AppCompat, or Material dependencies.",
        " */",
        "object CabinTokens {",
        f'    const val SOURCE_VERSION: String = "{version}"',
        '    const val SOURCE_PATH: String = "tokens/cabin.tokens.json"',
        "",
        "    /** Semantic color role names locked as safety-adjacent. */",
        f"    val safetyLockedSemanticColors: Set<String> = setOf({lock_set})",
        "",
        "    /** Night scheme roles with locked contrast (no soft-wash). */",
        f"    val nightContrastLockedSchemeColors: Set<String> = setOf({night_set})",
        "",
        "    /** Resolve day/night scheme chrome + feedback roles. */",
        "    fun colorScheme(scheme: CabinColorScheme): CabinColorSchemeColors = Color.scheme(scheme)",
        "",
        "    object Color {",
        "        object Semantic {",
        indent(semantic_lines, 12),
        "        }",
        "",
        indent(scheme_props, 8),
        "",
        "        fun scheme(scheme: CabinColorScheme): CabinColorSchemeColors = when (scheme) {",
        indent(scheme_when, 12),
        "        }",
        "    }",
        "",
        "    object Type {",
        indent(type_lines, 8),
        "    }",
        "",
        "    object Space {",
        indent(space_lines, 8),
        "    }",
        "",
        "    object Size {",
        indent(size_lines, 8),
        "    }",
        "",
        "    object Elevation {",
        indent(elevation_lines, 8),
        "    }",
        "",
        "    object Motion {",
        indent(motion_lines, 8),
        "    }",
        "",
        "    object Component {",
        indent(component_lines, 8),
        "    }",
        "}",
        "",
    ]
    return "\n".join(body)


def build_compose(
    version: str,
    semantic_names: list[str],
    scheme_roles: list[str],
) -> str:
    lines: list[str] = [
        '@file:JvmName("CabinComposeTokensGenerated")',
        "",
        "package dev.decoupled.cabin.compose.theme",
        "",
        "import androidx.compose.ui.graphics.Color",
        "import dev.decoupled.cabin.tokens.CabinColorScheme",
        "import dev.decoupled.cabin.tokens.CabinTokens",
        "",
        "/**",
        " * Compose theme Color mappings generated from `tokens/cabin.tokens.json`.",
        " *",
        " * Same semantic names as Android resources and CSS `--cabin-*` variables.",
        " * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.",
        f" * Stub version: {version}",
        " */",
        "object CabinComposeTokens {",
        f'    const val SOURCE_VERSION: String = "{version}"',
        "",
        "    object Semantic {",
    ]
    for name in semantic_names:
        lines.append(
            f"        val {name}: Color get() = "
            f"CabinTokens.Color.Semantic.{name}.toComposeColor()"
        )
    lines += [
        "    }",
        "",
        "    /** Day/night scheme chrome + locked feedback roles. */",
        "    data class SchemeColors(",
    ]
    for role in scheme_roles:
        lines.append(f"        val {role}: Color,")
    lines += [
        "    )",
        "",
        "    fun scheme(scheme: CabinColorScheme): SchemeColors {",
        "        val s = CabinTokens.colorScheme(scheme)",
        "        return SchemeColors(",
    ]
    for role in scheme_roles:
        lines.append(f"            {role} = s.{role}.toComposeColor(),")
    lines += [
        "        )",
        "    }",
        "",
        "    /**",
        "     * Resolve [CabinColors] for [CabinTheme].",
        "     * Brand overrides may remap primary only — never safety feedback.",
        "     */",
        "    fun resolveColors(",
        "        scheme: CabinColorScheme,",
        "        brand: CabinBrandOverrides = CabinBrandOverrides.None,",
        "    ): CabinColors {",
        "        val semantic = Semantic",
        "        val schemeColors = scheme(scheme)",
        "        return CabinColors(",
        "            primary = brand.primary ?: semantic.primary,",
        "            onPrimary = brand.onPrimary ?: semantic.onPrimary,",
        "            secondary = semantic.secondary,",
        "            onSecondary = semantic.onSecondary,",
        "            surface = schemeColors.surface,",
        "            onSurface = schemeColors.onSurface,",
        "            surfaceVariant = schemeColors.surfaceVariant,",
        "            outline = schemeColors.outline,",
        "            container = schemeColors.container,",
        "            onContainer = schemeColors.onContainer,",
        "            success = semantic.success,",
        "            warning = schemeColors.warning,",
        "            error = schemeColors.error,",
        "            charging = schemeColors.charging,",
        "            climate = semantic.climate,",
        "            mediaAccent = semantic.mediaAccent,",
        "            scrim = semantic.scrim,",
        "        )",
        "    }",
        "}",
        "",
    ]
    return "\n".join(lines)


def build_css(
    version: str,
    semantic_css: list[tuple[str, str, str | None]],
    day_roles: list[tuple[str, str, str | None]],
    night_roles: list[tuple[str, str, str | None]],
    measure_css: list[tuple[str, str]],
    type_family: str,
) -> str:
    lines: list[str] = [
        "/*",
        " * GENERATED from tokens/cabin.tokens.json — do not edit by hand.",
        f" * Stub version: {version}",
        " * Run: python3 tools/generate_cabin_tokens.py",
        " *",
        " * Same semantic names as Android (cabin_color_*) and Compose (CabinTokens).",
        " * Night warning / error / charging are safety-locked — do not soft-wash.",
        " */",
        "",
        ":root {",
        f'  --cabin-tokens-version: "{version}";',
        f"  --cabin-type-family-plain: {type_family}, system-ui, sans-serif;",
        "",
        "  /* Semantic colors */",
    ]
    for var_name, value, lock in semantic_css:
        note = f" /* lock:{lock} */" if lock else ""
        lines.append(f"  {var_name}: {value};{note}")
    lines.append("")
    lines.append("  /* Space / size / type / elevation / motion */")
    for var_name, value in measure_css:
        lines.append(f"  {var_name}: {value};")
    lines.append("}")
    lines.append("")

    def emit_scheme(
        selector: str,
        roles: list[tuple[str, str, str | None]],
        title: str,
    ) -> None:
        lines.append(f"/* {title} */")
        lines.append(f"{selector} {{")
        for var_name, value, lock in roles:
            note = " /* lock:safety-adjacent — no soft-wash */" if lock else ""
            lines.append(f"  {var_name}: {value};{note}")
        lines.append("")
        lines.append("  /* Web aliases (Tailwind / globals) — same roles */")
        for alias, kebab in [
            ("surface", "surface"),
            ("on-surface", "on-surface"),
            ("surface-variant", "surface-variant"),
            ("outline", "outline"),
            ("container", "container"),
            ("on-container", "on-container"),
            ("warning", "warning"),
            ("error", "error"),
            ("charging", "charging"),
        ]:
            lines.append(f"  --{alias}: var(--cabin-color-scheme-{kebab});")
        lines.append("  --primary: var(--cabin-color-semantic-primary);")
        lines.append("  --on-primary: var(--cabin-color-semantic-on-primary);")
        lines.append("  --secondary: var(--cabin-color-semantic-secondary);")
        lines.append("  --success: var(--cabin-color-semantic-success);")
        lines.append("  --climate: var(--cabin-color-semantic-climate);")
        lines.append("  --media: var(--cabin-color-semantic-media-accent);")
        lines.append("  --scrim: var(--cabin-color-semantic-scrim);")
        lines.append("}")
        lines.append("")

    emit_scheme(
        ':root,\n[data-scheme="night"]',
        night_roles,
        "Night scheme (default) — locked warning / error / charging contrast",
    )
    emit_scheme(
        '[data-scheme="day"]',
        day_roles,
        "Day scheme — glare-legible chrome + feedback",
    )
    return "\n".join(lines)


def generated_readme_text() -> str:
    return "\n".join(
        [
            "# Generated token outputs",
            "",
            "Do not edit files in this directory by hand.",
            "",
            "Source of truth: [`../cabin.tokens.json`](../cabin.tokens.json)",
            "",
            "Regenerate:",
            "",
            "```bash",
            "python3 tools/generate_cabin_tokens.py",
            "```",
            "",
            "| File | Consumer |",
            "| --- | --- |",
            "| `cabin.tokens.css` | `apps/www` (mirrored at "
            "`apps/www/src/styles/cabin.tokens.css`) |",
            "",
            "Android / Compose outputs live under `cabin-tokens/` and "
            "`cabin-compose/` (same generator run).",
            "",
            "See [token codegen](../../docs/design-language/token-codegen.md).",
            "",
        ]
    )


def collect_outputs(doc: dict) -> dict[Path, str]:
    validate_safety_locks(doc)

    cabin = doc["cabin"]
    version = doc.get("meta", {}).get("version", "0.0.0")

    colors_day: list[tuple[str, str]] = []
    colors_night: list[tuple[str, str]] = []
    dimens: list[tuple[str, str]] = []
    integers: list[tuple[str, str]] = []
    strings: list[tuple[str, str]] = []
    locks: list[str] = []

    semantic_lines: list[str] = []
    semantic_names: list[str] = []
    semantic_css: list[tuple[str, str, str | None]] = []

    for name, node in cabin["color"]["semantic"].items():
        entry = leaf(doc, node)
        hex8, argb = parse_color(entry["value"])
        res = resource_name("color", "semantic", name)
        colors_day.append((res, hex8))
        lock = token_lock(entry)
        lock_expr = f'"{lock}"' if lock else "null"
        if lock:
            locks.append(name)
        semantic_names.append(name)
        semantic_lines.append(
            f"val {name}: CabinColor = CabinColor("
            f"argb = {argb}, hex = \"{hex8}\", "
            f'resourceName = "{res}", lock = {lock_expr})'
        )
        semantic_css.append(
            (
                css_var("color", "semantic", camel_to_kebab(name)),
                css_color(entry["value"]),
                lock,
            )
        )

    scheme_props: list[str] = []
    scheme_when: list[str] = []
    night_locks: list[str] = []
    day_roles_css: list[tuple[str, str, str | None]] = []
    night_roles_css: list[tuple[str, str, str | None]] = []
    scheme_role_names: list[str] = []

    for scheme_name, roles in cabin["color"]["scheme"].items():
        args: list[str] = []
        for role, node in roles.items():
            entry = leaf(doc, node)
            hex8, argb = parse_color(entry["value"])
            res = resource_name("color", "scheme", role)
            if scheme_name == "day":
                colors_day.append((res, hex8))
            else:
                colors_night.append((res, hex8))
            lock = token_lock(entry)
            lock_expr = f'"{lock}"' if lock else "null"
            if scheme_name == "night" and lock:
                night_locks.append(role)
            args.append(
                f"{role} = CabinColor(argb = {argb}, hex = \"{hex8}\", "
                f'resourceName = "{res}", lock = {lock_expr})'
            )
            css_entry = (
                css_var("color", "scheme", camel_to_kebab(role)),
                css_color(entry["value"]),
                lock if scheme_name == "night" else None,
            )
            if scheme_name == "day":
                day_roles_css.append(css_entry)
                if role not in scheme_role_names:
                    scheme_role_names.append(role)
            else:
                night_roles_css.append(css_entry)
        prop = f"scheme{kotlin_const(scheme_name)}"
        joined = ",\n            ".join(args)
        scheme_props.append(
            f"private val {prop} = CabinColorSchemeColors(\n"
            f"            {joined},\n"
            f"        )"
        )
        scheme_when.append(
            f"CabinColorScheme.{kotlin_const(scheme_name)} -> {prop}"
        )

    for required in REQUIRED_NIGHT_LOCKS:
        if required not in night_locks:
            raise SystemExit(
                f"error: night lock for {required!r} not collected after validation"
            )
    night_locks = list(REQUIRED_NIGHT_LOCKS)

    type_lines: list[str] = []
    measure_css: list[tuple[str, str]] = []
    type_family = "CabinSans"

    for name, node in cabin["type"]["family"].items():
        entry = leaf(doc, node)
        res = resource_name("type", "family", name)
        strings.append((res, entry["value"]))
        type_family = entry["value"]
        type_lines.append("object Family {")
        type_lines.append(f'    const val {name}: String = "{entry["value"]}"')
        type_lines.append(f'    const val {name}ResourceName: String = "{res}"')
        type_lines.append("}")

    type_lines.append("object Role {")
    for role, props in cabin["type"]["role"].items():
        type_lines.append(f"    object {kotlin_const(role)} {{")
        for prop, node in props.items():
            entry = leaf(doc, node)
            value = entry["value"]
            unit = entry.get("unit")
            res = resource_name("type", "role", role, prop)
            if unit in ("sp", "dp"):
                dimens.append((res, f"{value}{unit}"))
                type_lines.append(
                    f"        val {prop}: CabinMeasure = CabinMeasure("
                    f'{value}f, CabinUnit.{unit.upper()}, "{res}")'
                )
                measure_css.append(
                    (
                        css_var("type", "role", role, camel_to_kebab(prop)),
                        f"{value}px",
                    )
                )
            else:
                integers.append((res, str(value)))
                type_lines.append(
                    f'        val {prop}: Int = {value} // resource "{res}"'
                )
                measure_css.append(
                    (
                        css_var("type", "role", role, camel_to_kebab(prop)),
                        str(value),
                    )
                )
        type_lines.append("    }")
    type_lines.append("}")

    space_lines: list[str] = []
    for name, node in cabin["space"].items():
        entry = leaf(doc, node)
        res = resource_name("space", name)
        dimens.append((res, f'{entry["value"]}{entry.get("unit", "dp")}'))
        space_lines.append(
            f"val {name}: CabinMeasure = CabinMeasure("
            f'{entry["value"]}f, CabinUnit.DP, "{res}")'
        )
        measure_css.append((css_var("space", name), f'{entry["value"]}px'))

    size_lines: list[str] = []
    for group, items in cabin["size"].items():
        size_lines.append(f"object {kotlin_const(group)} {{")
        for name, node in items.items():
            entry = leaf(doc, node)
            res = resource_name("size", group, name)
            dimens.append((res, f'{entry["value"]}{entry.get("unit", "dp")}'))
            size_lines.append(
                f"    val {name}: CabinMeasure = CabinMeasure("
                f'{entry["value"]}f, CabinUnit.DP, "{res}")'
            )
            measure_css.append(
                (
                    css_var(
                        "size",
                        camel_to_kebab(group),
                        camel_to_kebab(name),
                    ),
                    f'{entry["value"]}px',
                )
            )
        size_lines.append("}")

    elevation_lines: list[str] = []
    for name, node in cabin["elevation"].items():
        entry = leaf(doc, node)
        res = resource_name("elevation", name)
        dimens.append((res, f'{entry["value"]}{entry.get("unit", "dp")}'))
        elevation_lines.append(
            f"val {name}: CabinMeasure = CabinMeasure("
            f'{entry["value"]}f, CabinUnit.DP, "{res}")'
        )
        measure_css.append((css_var("elevation", name), f'{entry["value"]}px'))

    motion_lines: list[str] = []
    for name, node in cabin["motion"].items():
        entry = leaf(doc, node["duration"])
        res = resource_name("motion", name, "duration")
        integers.append((res, str(entry["value"])))
        motion_lines.append(f"object {kotlin_const(name)} {{")
        motion_lines.append(f"    const val durationMs: Int = {entry['value']}")
        motion_lines.append(f'    const val durationResourceName: String = "{res}"')
        motion_lines.append("}")
        measure_css.append(
            (css_var("motion", name, "duration"), f'{entry["value"]}ms')
        )

    component_lines: list[str] = []
    for component, props in cabin["component"].items():
        component_lines.append(f"object {kotlin_const(component)} {{")
        for name, node in props.items():
            entry = leaf(doc, node)
            res = resource_name("component", camel_to_snake(component), name)
            unit = entry.get("unit", "dp")
            dimens.append((res, f'{entry["value"]}{unit}'))
            component_lines.append(
                f"    val {name}: CabinMeasure = CabinMeasure("
                f'{entry["value"]}f, CabinUnit.{unit.upper()}, "{res}")'
            )
            measure_css.append(
                (
                    css_var(
                        "component",
                        camel_to_kebab(component),
                        camel_to_kebab(name),
                    ),
                    f'{entry["value"]}px',
                )
            )
        component_lines.append("}")

    colors_day = dedupe(colors_day)
    colors_night = dedupe(colors_night)
    dimens = dedupe(dimens)
    integers = dedupe(integers)
    strings = dedupe(strings)

    kotlin = build_kotlin(
        version,
        locks,
        night_locks,
        semantic_lines,
        scheme_props,
        scheme_when,
        type_lines,
        space_lines,
        size_lines,
        elevation_lines,
        motion_lines,
        component_lines,
    )
    compose = build_compose(version, semantic_names, scheme_role_names)
    css = build_css(
        version,
        semantic_css,
        day_roles_css,
        night_roles_css,
        measure_css,
        type_family,
    )

    return {
        KOTLIN_OUT: kotlin,
        RES_VALUES / "cabin_colors.xml": colors_xml(
            colors_day, "Day baseline + semantic colors"
        ),
        RES_NIGHT / "cabin_colors.xml": colors_xml(
            colors_night, "Night scheme chrome + locked feedback contrast"
        ),
        RES_VALUES / "cabin_dimens.xml": dimens_xml(dimens),
        RES_VALUES / "cabin_integers.xml": integers_xml(integers),
        RES_VALUES / "cabin_strings.xml": strings_xml(strings),
        RES_VALUES / "cabin_attrs.xml": attrs_xml(),
        COMPOSE_OUT: compose,
        CSS_WWW_OUT: css,
        CSS_GENERATED_OUT: css,
        GENERATED_README: generated_readme_text(),
    }


def generate(*, write_files: bool = True) -> dict[Path, str]:
    doc = json.loads(TOKENS_JSON.read_text(encoding="utf-8"))
    outputs = collect_outputs(doc)
    if write_files:
        for path, content in outputs.items():
            write(path, content)
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
        print("error: token codegen drift detected:", file=sys.stderr)
        for item in drifted:
            print(f"  - {item}", file=sys.stderr)
        print(
            "\nRun: python3 tools/generate_cabin_tokens.py",
            file=sys.stderr,
        )
        return 1
    print(
        "ok: Android / Compose / CSS token outputs match tokens/cabin.tokens.json"
    )
    return 0


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(
        description="Generate Cabin tokens from tokens/cabin.tokens.json"
    )
    parser.add_argument(
        "--check",
        action="store_true",
        help="Exit non-zero if generated outputs drift from JSON (CI)",
    )
    args = parser.parse_args(argv)
    try:
        if args.check:
            return check_drift()
        generate(write_files=True)
        return 0
    except SystemExit as exc:
        code = exc.code
        if isinstance(code, int):
            return code
        if code is None:
            return 0
        print(str(code), file=sys.stderr)
        return 1


if __name__ == "__main__":
    sys.exit(main())

#!/usr/bin/env python3
"""Generate cabin-tokens Kotlin + Android resources from tokens/cabin.tokens.json.

Source of truth: tokens/cabin.tokens.json (see docs/design-language/token-schema.md).
Regenerate after editing the stub:

    python3 tools/generate_cabin_tokens.py
"""

from __future__ import annotations

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
        # Signed 32-bit for Kotlin Int
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


def resource_name(*parts: str) -> str:
    return "cabin_" + "_".join(parts)


def kotlin_const(name: str) -> str:
    return name[0].upper() + name[1:] if name else name


def camel_to_snake(name: str) -> str:
    return re.sub(r"(?<!^)(?=[A-Z])", "_", name).lower()


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


def generate() -> None:
    doc = json.loads(TOKENS_JSON.read_text(encoding="utf-8"))
    cabin = doc["cabin"]
    version = doc.get("meta", {}).get("version", "0.0.0")

    colors_day: list[tuple[str, str]] = []
    colors_night: list[tuple[str, str]] = []
    dimens: list[tuple[str, str]] = []
    integers: list[tuple[str, str]] = []
    strings: list[tuple[str, str]] = []
    locks: list[str] = []

    semantic_lines: list[str] = []
    for name, node in cabin["color"]["semantic"].items():
        entry = leaf(doc, node)
        hex8, argb = parse_color(entry["value"])
        res = resource_name("color", "semantic", camel_to_snake(name))
        # Keep camelCase resource suffix for readability matching token names
        res = resource_name("color", "semantic", name)
        colors_day.append((res, hex8))
        lock = entry.get("extensions", {}).get("cabin.lock")
        lock_expr = f'"{lock}"' if lock else "null"
        if lock:
            locks.append(name)
        semantic_lines.append(
            f'val {name}: CabinColor = CabinColor('
            f'argb = {argb}, hex = "{hex8}", '
            f'resourceName = "{res}", lock = {lock_expr})'
        )

    scheme_props: list[str] = []
    scheme_when: list[str] = []
    night_contrast_locks: list[str] = []
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
            lock = entry.get("extensions", {}).get("cabin.lock")
            lock_expr = f'"{lock}"' if lock else "null"
            if scheme_name == "night" and lock:
                night_contrast_locks.append(role)
            args.append(
                f'{role} = CabinColor(argb = {argb}, hex = "{hex8}", '
                f'resourceName = "{res}", lock = {lock_expr})'
            )
        prop = f"scheme{kotlin_const(scheme_name)}"
        # One role per line keeps generated scheme objects readable.
        joined = ",\n            ".join(args)
        scheme_props.append(
            f"private val {prop} = CabinColorSchemeColors(\n"
            f"            {joined},\n"
            f"        )"
        )
        scheme_when.append(
            f"CabinColorScheme.{kotlin_const(scheme_name)} -> {prop}"
        )

    type_lines: list[str] = []
    for name, node in cabin["type"]["family"].items():
        entry = leaf(doc, node)
        res = resource_name("type", "family", name)
        strings.append((res, entry["value"]))
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
                    f"{value}f, CabinUnit.{unit.upper()}, \"{res}\")"
                )
            else:
                integers.append((res, str(value)))
                type_lines.append(
                    f"        val {prop}: Int = {value} // resource \"{res}\""
                )
        type_lines.append("    }")
    type_lines.append("}")

    space_lines: list[str] = []
    for name, node in cabin["space"].items():
        entry = leaf(doc, node)
        res = resource_name("space", name)
        dimens.append((res, f'{entry["value"]}{entry.get("unit", "dp")}'))
        space_lines.append(
            f'val {name}: CabinMeasure = CabinMeasure('
            f'{entry["value"]}f, CabinUnit.DP, "{res}")'
        )

    size_lines: list[str] = []
    for group, items in cabin["size"].items():
        size_lines.append(f"object {kotlin_const(group)} {{")
        for name, node in items.items():
            entry = leaf(doc, node)
            res = resource_name("size", group, name)
            dimens.append((res, f'{entry["value"]}{entry.get("unit", "dp")}'))
            size_lines.append(
                f'    val {name}: CabinMeasure = CabinMeasure('
                f'{entry["value"]}f, CabinUnit.DP, "{res}")'
            )
        size_lines.append("}")

    elevation_lines: list[str] = []
    for name, node in cabin["elevation"].items():
        entry = leaf(doc, node)
        res = resource_name("elevation", name)
        dimens.append((res, f'{entry["value"]}{entry.get("unit", "dp")}'))
        elevation_lines.append(
            f'val {name}: CabinMeasure = CabinMeasure('
            f'{entry["value"]}f, CabinUnit.DP, "{res}")'
        )

    motion_lines: list[str] = []
    for name, node in cabin["motion"].items():
        entry = leaf(doc, node["duration"])
        res = resource_name("motion", name, "duration")
        integers.append((res, str(entry["value"])))
        motion_lines.append(f"object {kotlin_const(name)} {{")
        motion_lines.append(f"    const val durationMs: Int = {entry['value']}")
        motion_lines.append(f'    const val durationResourceName: String = "{res}"')
        motion_lines.append("}")

    component_lines: list[str] = []
    for component, props in cabin["component"].items():
        component_lines.append(f"object {kotlin_const(component)} {{")
        for name, node in props.items():
            entry = leaf(doc, node)
            res = resource_name("component", camel_to_snake(component), name)
            unit = entry.get("unit", "dp")
            dimens.append((res, f'{entry["value"]}{unit}'))
            component_lines.append(
                f'    val {name}: CabinMeasure = CabinMeasure('
                f'{entry["value"]}f, CabinUnit.{unit.upper()}, "{res}")'
            )
        component_lines.append("}")

    colors_day = dedupe(colors_day)
    colors_night = dedupe(colors_night)
    dimens = dedupe(dimens)
    integers = dedupe(integers)
    strings = dedupe(strings)

    lock_set = ", ".join(f'"{n}"' for n in locks)
    night_lock_set = ", ".join(f'"{n}"' for n in night_contrast_locks)

    body = []
    body.append("@file:JvmName(\"CabinTokensGenerated\")")
    body.append("")
    body.append("package dev.decoupled.cabin.tokens")
    body.append("")
    body.append("/**")
    body.append(" * Public Cabin token constants generated from `tokens/cabin.tokens.json`.")
    body.append(" *")
    body.append(" * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.")
    body.append(f" * Stub version: {version}")
    body.append(" *")
    body.append(" * This module has no Compose, Views widget, AppCompat, or Material dependencies.")
    body.append(" */")
    body.append("object CabinTokens {")
    body.append(f'    const val SOURCE_VERSION: String = "{version}"')
    body.append('    const val SOURCE_PATH: String = "tokens/cabin.tokens.json"')
    body.append("")
    body.append("    /** Semantic color role names locked as safety-adjacent. */")
    body.append(f"    val safetyLockedSemanticColors: Set<String> = setOf({lock_set})")
    body.append("")
    body.append("    /** Night scheme roles with locked contrast (no soft-wash). */")
    body.append(
        f"    val nightContrastLockedSchemeColors: Set<String> = setOf({night_lock_set})"
    )
    body.append("")
    body.append("    /** Resolve day/night scheme chrome + feedback roles. */")
    body.append("    fun colorScheme(scheme: CabinColorScheme): CabinColorSchemeColors = Color.scheme(scheme)")
    body.append("")
    body.append("    object Color {")
    body.append("        object Semantic {")
    body.append(indent(semantic_lines, 12))
    body.append("        }")
    body.append("")
    body.append(indent(scheme_props, 8))
    body.append("")
    body.append("        fun scheme(scheme: CabinColorScheme): CabinColorSchemeColors = when (scheme) {")
    body.append(indent(scheme_when, 12))
    body.append("        }")
    body.append("    }")
    body.append("")
    body.append("    object Type {")
    body.append(indent(type_lines, 8))
    body.append("    }")
    body.append("")
    body.append("    object Space {")
    body.append(indent(space_lines, 8))
    body.append("    }")
    body.append("")
    body.append("    object Size {")
    body.append(indent(size_lines, 8))
    body.append("    }")
    body.append("")
    body.append("    object Elevation {")
    body.append(indent(elevation_lines, 8))
    body.append("    }")
    body.append("")
    body.append("    object Motion {")
    body.append(indent(motion_lines, 8))
    body.append("    }")
    body.append("")
    body.append("    object Component {")
    body.append(indent(component_lines, 8))
    body.append("    }")
    body.append("}")
    body.append("")

    write(KOTLIN_OUT, "\n".join(body))
    write(
        RES_VALUES / "cabin_colors.xml",
        colors_xml(colors_day, "Day baseline + semantic colors"),
    )
    write(
        RES_NIGHT / "cabin_colors.xml",
        colors_xml(colors_night, "Night scheme chrome + locked feedback contrast"),
    )
    write(RES_VALUES / "cabin_dimens.xml", dimens_xml(dimens))
    write(RES_VALUES / "cabin_integers.xml", integers_xml(integers))
    write(RES_VALUES / "cabin_strings.xml", strings_xml(strings))
    write(RES_VALUES / "cabin_attrs.xml", ATTRS_XML)


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


ATTRS_XML = """<?xml version="1.0" encoding="utf-8"?>
<!-- Theme / RRO attribute hooks for Cabin semantic / scheme roles. Alpha; Theme Kit binds these. -->
<resources>
    <attr name="cabin_colorPrimary" format="color|reference" />
    <attr name="cabin_colorOnPrimary" format="color|reference" />
    <attr name="cabin_colorSecondary" format="color|reference" />
    <attr name="cabin_colorOnSecondary" format="color|reference" />
    <attr name="cabin_colorSurface" format="color|reference" />
    <attr name="cabin_colorOnSurface" format="color|reference" />
    <attr name="cabin_colorSurfaceVariant" format="color|reference" />
    <attr name="cabin_colorOutline" format="color|reference" />
    <attr name="cabin_colorContainer" format="color|reference" />
    <attr name="cabin_colorOnContainer" format="color|reference" />
    <attr name="cabin_colorSuccess" format="color|reference" />
    <attr name="cabin_colorWarning" format="color|reference" />
    <attr name="cabin_colorError" format="color|reference" />
    <attr name="cabin_colorCharging" format="color|reference" />
    <attr name="cabin_colorClimate" format="color|reference" />
    <attr name="cabin_colorMediaAccent" format="color|reference" />
    <attr name="cabin_colorScrim" format="color|reference" />
</resources>
"""


if __name__ == "__main__":
    try:
        generate()
    except Exception as exc:  # noqa: BLE001
        print(f"error: {exc}", file=sys.stderr)
        raise

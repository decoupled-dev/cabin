"""Look presets (cabin.look.*) and the locked safety tier (cabin.safety.*).

Used by tools/generate_cabin_tokens.py. Kept separate so the v0.1 scheme
pipeline stays untouched (ADR 0006).

Outputs:
  - cabin-tokens/.../CabinLooks.generated.kt  (framework-free Kotlin values)
  - tokens/generated/cabin.looks.css          ([data-cabin-look][data-cabin-scheme] vars)

Validation (fails codegen and --check):
  - every look defines every scheme role for night and day
  - no look defines a safety role (warning/error/charging + containers)
  - every safety token carries extensions["cabin.lock"] = "safety"
  - WCAG contrast >= 4.5:1 for text roles on their surfaces (translucent
    surfaces are composited over the look's background first)
"""

from __future__ import annotations

LOOK_ORDER = ("quiet", "precision", "warm", "glass", "graphic", "mono")
SCHEMES = ("night", "day")

SCHEME_ROLES = (
    "background",
    "surface",
    "surfaceContainerHigh",
    "outline",
    "onSurface",
    "onSurfaceVariant",
    "onSurfaceMuted",
    "primary",
    "onPrimary",
    "primaryContainer",
    "onPrimaryContainer",
)
OPTIONAL_ROLES = ("backdropGlowStart", "backdropGlowEnd")

SAFETY_ROLES = (
    "warning",
    "warningContainer",
    "error",
    "errorContainer",
    "charging",
    "chargingContainer",
)
SAFETY_LOCK = "safety"

ACTIVE_STYLES = {"tonal": "Tonal", "underline": "Underline", "solid": "Solid", "invert": "Invert"}
EDGES = {"none": "None", "line": "Line", "glass": "Glass"}

MIN_TEXT_CONTRAST = 4.5

# (foreground role, background roles) pairs that carry text.
TEXT_PAIRS: tuple[tuple[str, tuple[str, ...]], ...] = (
    ("onSurface", ("background", "surface", "surfaceContainerHigh")),
    ("onSurfaceVariant", ("background", "surface", "surfaceContainerHigh")),
    ("onSurfaceMuted", ("background", "surface")),
    ("onPrimary", ("primary",)),
    ("onPrimaryContainer", ("primaryContainer",)),
)

# Safety colors used as text/icons on their containers and on look surfaces.
SAFETY_TEXT_PAIRS: tuple[tuple[str, str], ...] = (
    ("warning", "warningContainer"),
    ("error", "errorContainer"),
    ("charging", "chargingContainer"),
)


# ---------------------------------------------------------------- color math


def rgba(value: str) -> tuple[int, int, int, float]:
    raw = value.removeprefix("#")
    if len(raw) == 6:
        return int(raw[0:2], 16), int(raw[2:4], 16), int(raw[4:6], 16), 1.0
    if len(raw) == 8:  # stub order RRGGBBAA
        return (
            int(raw[0:2], 16),
            int(raw[2:4], 16),
            int(raw[4:6], 16),
            int(raw[6:8], 16) / 255.0,
        )
    raise ValueError(f"Unsupported color: {value}")


def composite(top: str, bottom: str) -> tuple[int, int, int]:
    r, g, b, a = rgba(top)
    br, bg, bb, _ = rgba(bottom)
    return (
        round(r * a + br * (1 - a)),
        round(g * a + bg * (1 - a)),
        round(b * a + bb * (1 - a)),
    )


def luminance(rgb: tuple[int, int, int]) -> float:
    def channel(c: int) -> float:
        s = c / 255.0
        return s / 12.92 if s <= 0.03928 else ((s + 0.055) / 1.055) ** 2.4

    r, g, b = (channel(c) for c in rgb)
    return 0.2126 * r + 0.7152 * g + 0.0722 * b


def contrast(fg: tuple[int, int, int], bg: tuple[int, int, int]) -> float:
    la, lb = luminance(fg), luminance(bg)
    hi, lo = max(la, lb), min(la, lb)
    return (hi + 0.05) / (lo + 0.05)


def argb_int(value: str) -> int:
    r, g, b, a = rgba(value)
    argb = (round(a * 255) << 24) | (r << 16) | (g << 8) | b
    return argb - 0x100000000 if argb >= 0x80000000 else argb


def argb_hex(value: str) -> str:
    r, g, b, a = rgba(value)
    return f"0x{round(a * 255):02X}{r:02X}{g:02X}{b:02X}"


def css_rgba(value: str) -> str:
    r, g, b, a = rgba(value)
    if a >= 1.0:
        return f"#{r:02X}{g:02X}{b:02X}"
    return f"rgba({r}, {g}, {b}, {a:.3g})"


# ---------------------------------------------------------------- validation


def _val(node: dict) -> object:
    return node["value"]


def validate(doc: dict) -> list[str]:
    cabin = doc["cabin"]
    errors: list[str] = []
    looks = cabin.get("look")
    safety = cabin.get("safety")
    if not isinstance(looks, dict):
        return ["missing cabin.look"]
    if not isinstance(safety, dict):
        return ["missing cabin.safety"]

    default = _val(looks.get("default", {"value": None}))
    if default not in LOOK_ORDER:
        errors.append(f"cabin.look.default must be one of {LOOK_ORDER}, found {default!r}")

    for scheme in SCHEMES:
        roles = safety.get(scheme, {})
        for role in SAFETY_ROLES:
            node = roles.get(role)
            if node is None:
                errors.append(f"missing cabin.safety.{scheme}.{role}")
                continue
            lock = node.get("extensions", {}).get("cabin.lock")
            if lock != SAFETY_LOCK:
                errors.append(
                    f'cabin.safety.{scheme}.{role} must set extensions["cabin.lock"] = '
                    f'"{SAFETY_LOCK}" (found {lock!r})'
                )
        for fg, bg in SAFETY_TEXT_PAIRS:
            if fg in roles and bg in roles:
                ratio = contrast(composite(_val(roles[fg]), "#000000"), composite(_val(roles[bg]), "#000000"))
                if ratio < MIN_TEXT_CONTRAST:
                    errors.append(
                        f"cabin.safety.{scheme}.{fg} on {bg}: contrast {ratio:.2f} < {MIN_TEXT_CONTRAST}"
                    )

    for name in LOOK_ORDER:
        look = looks.get(name)
        if look is None:
            errors.append(f"missing cabin.look.{name}")
            continue
        style = look["style"]
        if _val(style["activeStyle"]) not in ACTIVE_STYLES:
            errors.append(f"cabin.look.{name}.style.activeStyle invalid")
        if _val(style["edge"]) not in EDGES:
            errors.append(f"cabin.look.{name}.style.edge invalid")
        for scheme in SCHEMES:
            roles = look["scheme"].get(scheme, {})
            for role in SCHEME_ROLES:
                if role not in roles:
                    errors.append(f"missing cabin.look.{name}.scheme.{scheme}.{role}")
            for role in roles:
                if role in SAFETY_ROLES:
                    errors.append(
                        f"cabin.look.{name}.scheme.{scheme}.{role}: looks must not define "
                        "safety roles (locked in cabin.safety)"
                    )
                elif role not in SCHEME_ROLES and role not in OPTIONAL_ROLES:
                    errors.append(f"cabin.look.{name}.scheme.{scheme}.{role}: unknown role")
            if any(role not in roles for role in SCHEME_ROLES):
                continue
            base = _val(roles["background"])
            if rgba(base)[3] < 1.0:
                errors.append(f"cabin.look.{name}.scheme.{scheme}.background must be opaque")
                continue

            def solid(role: str) -> tuple[int, int, int]:
                return composite(_val(roles[role]), base)

            for fg, bgs in TEXT_PAIRS:
                for bg in bgs:
                    bg_rgb = solid(bg)
                    fg_rgb = composite(_val(roles[fg]), "#{:02X}{:02X}{:02X}".format(*bg_rgb))
                    ratio = contrast(fg_rgb, bg_rgb)
                    if ratio < MIN_TEXT_CONTRAST:
                        errors.append(
                            f"cabin.look.{name}.scheme.{scheme}: {fg} on {bg} contrast "
                            f"{ratio:.2f} < {MIN_TEXT_CONTRAST}"
                        )
            # Safety text on every opaque-composited look surface.
            for role in ("warning", "error", "charging"):
                color = _val(safety[scheme][role])
                for bg in ("background", "surface"):
                    ratio = contrast(composite(color, "#000000"), solid(bg))
                    if ratio < MIN_TEXT_CONTRAST:
                        errors.append(
                            f"cabin.safety.{scheme}.{role} on look {name} {bg}: contrast "
                            f"{ratio:.2f} < {MIN_TEXT_CONTRAST}"
                        )
    return errors


# ---------------------------------------------------------------- codegen

HEADER_KT = """\
/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
"""


def _kt_string(value: str) -> str:
    return '"' + value.replace("\\", "\\\\").replace('"', '\\"') + '"'


def _scheme_kt(roles: dict, indent: str) -> str:
    lines = [f"{indent}CabinLookScheme("]
    for role in SCHEME_ROLES:
        lines.append(f"{indent}    {role} = {argb_hex(_val(roles[role]))}.toInt(),")
    for role in OPTIONAL_ROLES:
        value = f"{argb_hex(_val(roles[role]))}.toInt()" if role in roles else "null"
        lines.append(f"{indent}    {role} = {value},")
    lines.append(f"{indent})")
    return "\n".join(lines)


def _safety_kt(roles: dict, indent: str) -> str:
    lines = [f"{indent}CabinSafetyScheme("]
    for role in SAFETY_ROLES:
        lines.append(f"{indent}    {role} = {argb_hex(_val(roles[role]))}.toInt(),")
    lines.append(f"{indent})")
    return "\n".join(lines)


def build_kotlin(doc: dict, version: str) -> str:
    cabin = doc["cabin"]
    looks = cabin["look"]
    safety = cabin["safety"]
    default = _val(looks["default"])
    out: list[str] = [HEADER_KT]
    out.append("@file:JvmName(\"CabinLooksGenerated\")\n")
    out.append("package dev.decoupled.cabin.tokens\n")
    out.append(
        "/**\n"
        " * Look presets and the locked safety tier, generated from\n"
        " * `tokens/cabin.tokens.json` (`cabin.look.*`, `cabin.safety.*`).\n"
        " *\n"
        " * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.\n"
        f" * Source version: {version}\n"
        " */\n"
        "object CabinLooks {\n"
        f"    const val SOURCE_VERSION: String = {_kt_string(version)}\n"
    )
    out.append(f"    /** Preset used when a product does not pick one. */\n"
               f"    val Default: CabinLook = CabinLook.{default.capitalize()}\n")
    out.append("    /** Locked across every look (ADR 0006). */\n    object Safety {")
    for scheme in SCHEMES:
        out.append(f"        val {scheme}: CabinSafetyScheme =\n{_safety_kt(safety[scheme], '            ')}")
    out.append("    }\n")
    out.append("    /** Every preset, in canvas order. */\n"
               "    val all: List<CabinLookSpec> by lazy {\n"
               "        listOf(" + ", ".join(n.capitalize() for n in LOOK_ORDER) + ")\n    }\n")
    out.append("    /** Spec for [look]. */\n"
               "    fun spec(look: CabinLook): CabinLookSpec = when (look) {")
    for n in LOOK_ORDER:
        out.append(f"        CabinLook.{n.capitalize()} -> {n.capitalize()}")
    out.append("    }\n")
    for n in LOOK_ORDER:
        lk = looks[n]
        t, sh, st = lk["type"], lk["shape"], lk["style"]
        fam = t["family"]
        out.append(f"    private val {n.capitalize()}: CabinLookSpec = CabinLookSpec(")
        out.append(f"        look = CabinLook.{n.capitalize()},")
        out.append(f"        displayName = {_kt_string(lk['meta']['displayName'])},")
        out.append(f"        description = {_kt_string(lk['meta']['description'])},")
        out.append(f"        uiFontFamily = {_kt_string(_val(fam['ui']))},")
        out.append(f"        displayFontFamily = {_kt_string(_val(fam['display']))},")
        out.append(f"        labelFontFamily = {_kt_string(_val(fam['label']))},")
        out.append(f"        displayWeight = {int(_val(t['displayWeight']))},")
        out.append(f"        titleWeight = {int(_val(t['titleWeight']))},")
        out.append(f"        labelWeight = {int(_val(t['labelWeight']))},")
        out.append(f"        labelAllCaps = {'true' if _val(t['labelAllCaps']) else 'false'},")
        out.append(f"        labelTrackingEm = {float(_val(t['labelTracking']))}f,")
        out.append(f"        cornerLargeDp = {float(_val(sh['large']))}f,")
        out.append(f"        cornerMediumDp = {float(_val(sh['medium']))}f,")
        out.append(f"        cornerControlDp = {float(_val(sh['control']))}f,")
        out.append(f"        activeStyle = CabinActiveStyle.{ACTIVE_STYLES[_val(st['activeStyle'])]},")
        out.append(f"        edge = CabinEdgeStyle.{EDGES[_val(st['edge'])]},")
        for scheme in SCHEMES:
            out.append(f"        {scheme} =\n{_scheme_kt(lk['scheme'][scheme], '            ')},")
        out.append("    )\n")
    out.append("}\n")
    return "\n".join(out)


def build_css(doc: dict, version: str) -> str:
    cabin = doc["cabin"]
    looks = cabin["look"]
    safety = cabin["safety"]
    default = _val(looks["default"])

    def kebab(s: str) -> str:
        return "".join("-" + c.lower() if c.isupper() else c for c in s)

    out = [
        "/*",
        " * Cabin look presets -- generated from tokens/cabin.tokens.json.",
        " * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.",
        f" * Source version: {version}",
        " * Usage: <div data-cabin-look=\"quiet\" data-cabin-scheme=\"night\">",
        " */",
        "",
    ]
    for scheme in SCHEMES:
        sel = f'[data-cabin-scheme="{scheme}"]'
        out.append(f"{sel} {{")
        for role in SAFETY_ROLES:
            out.append(f"  --cabin-safety-{kebab(role)}: {css_rgba(_val(safety[scheme][role]))};")
        out.append("}\n")
    for n in LOOK_ORDER:
        lk = looks[n]
        fam = lk["type"]["family"]
        selector = f'[data-cabin-look="{n}"]'
        if n == default:
            selector = f":root, {selector}"
        out.append(f"{selector} {{")
        out.append(f"  --cabin-look-font-ui: '{_val(fam['ui'])}', system-ui, sans-serif;")
        out.append(f"  --cabin-look-font-display: '{_val(fam['display'])}', system-ui, sans-serif;")
        out.append(f"  --cabin-look-font-label: '{_val(fam['label'])}', system-ui, sans-serif;")
        out.append(f"  --cabin-look-display-weight: {_val(lk['type']['displayWeight'])};")
        out.append(f"  --cabin-look-radius-large: {_val(lk['shape']['large'])}px;")
        out.append(f"  --cabin-look-radius-medium: {_val(lk['shape']['medium'])}px;")
        out.append(f"  --cabin-look-radius-control: {_val(lk['shape']['control'])}px;")
        out.append("}\n")
        for scheme in SCHEMES:
            out.append(f'[data-cabin-look="{n}"][data-cabin-scheme="{scheme}"] {{')
            roles = lk["scheme"][scheme]
            for role in SCHEME_ROLES + OPTIONAL_ROLES:
                if role in roles:
                    out.append(f"  --cabin-look-{kebab(role)}: {css_rgba(_val(roles[role]))};")
            out.append("}\n")
    return "\n".join(out)

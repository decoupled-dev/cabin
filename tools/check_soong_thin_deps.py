#!/usr/bin/env python3
"""Guard thin Soong packaging for Cabin (no full AAOS tree required).

Checks Alpha Android.bp scaffolding and the SystemUI wiring sketch so
catalog / Compose cannot sneak into the SystemUI-shaped dependency set.
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]

STATIC_LIBS_RE = re.compile(
    r"static_libs\s*:\s*\[(.*?)\]",
    re.DOTALL,
)
NAME_RE = re.compile(r'name\s*:\s*"([^"]+)"')


def strip_bp_comments(text: str) -> str:
    """Remove // line comments (sufficient for Cabin bp files)."""
    out: list[str] = []
    for line in text.splitlines():
        if "//" in line:
            line = line[: line.index("//")]
        out.append(line)
    return "\n".join(out)


def static_libs_for(path: Path) -> set[str]:
    text = strip_bp_comments(path.read_text(encoding="utf-8"))
    libs: set[str] = set()
    for block in STATIC_LIBS_RE.findall(text):
        libs.update(re.findall(r'"([^"]+)"', block))
    return libs


def module_name(path: Path) -> str | None:
    text = strip_bp_comments(path.read_text(encoding="utf-8"))
    match = NAME_RE.search(text)
    return match.group(1) if match else None


def main() -> int:
    errors: list[str] = []

    catalog_bp = ROOT / "catalog" / "Android.bp"
    if catalog_bp.exists():
        errors.append(
            f"{catalog_bp.relative_to(ROOT)} must not exist "
            "(catalog stays off SystemUI / product images)"
        )

    for forbidden in ("apps/www/Android.bp", "website/Android.bp"):
        path = ROOT / forbidden
        if path.exists():
            errors.append(f"{forbidden} must not exist (frozen / non-product)")

    expected = {
        "cabin-tokens/Android.bp": "CabinTokens",
        "cabin-compliance/Android.bp": "CabinCompliance",
        "cabin-views/Android.bp": "CabinViews",
        "cabin-compose/Android.bp": "CabinCompose",
        "cabin-foundation/Android.bp": "CabinFoundation",
        "cabin-gauges/Android.bp": "CabinGauges",
    }
    for rel, name in expected.items():
        path = ROOT / rel
        if not path.is_file():
            errors.append(f"missing {rel}")
            continue
        found = module_name(path)
        if found != name:
            errors.append(f"{rel}: expected name {name!r}, found {found!r}")

    defaults = ROOT / "Android.bp"
    if not defaults.is_file():
        errors.append("missing root Android.bp (CabinAndroidLibraryDefaults)")
    else:
        text = defaults.read_text(encoding="utf-8")
        if "CabinAndroidLibraryDefaults" not in text:
            errors.append("root Android.bp missing CabinAndroidLibraryDefaults")
        if re.search(r'name\s*:\s*"CabinAll"', text):
            errors.append("root Android.bp must not define umbrella CabinAll")

    views_libs = static_libs_for(ROOT / "cabin-views" / "Android.bp")
    if "CabinCompose" in views_libs:
        errors.append("CabinViews must not static_libs CabinCompose")
    if "CabinGauges" in views_libs:
        errors.append("CabinViews must not static_libs CabinGauges")
    if "CabinTokens" not in views_libs or "CabinCompliance" not in views_libs:
        errors.append("CabinViews must static_libs CabinTokens + CabinCompliance")
    if "CabinFoundation" not in views_libs:
        errors.append("CabinViews must static_libs CabinFoundation")

    compose_libs = static_libs_for(ROOT / "cabin-compose" / "Android.bp")
    if "CabinViews" in compose_libs:
        errors.append("CabinCompose must not static_libs CabinViews")
    if "CabinGauges" in compose_libs:
        errors.append("CabinCompose must not static_libs CabinGauges")
    if "CabinFoundation" not in compose_libs:
        errors.append("CabinCompose must static_libs CabinFoundation")

    foundation_libs = static_libs_for(ROOT / "cabin-foundation" / "Android.bp")
    allowed_foundation = {"CabinTokens", "CabinCompliance", "androidx.annotation_annotation"}
    if foundation_libs - allowed_foundation:
        errors.append(
            "CabinFoundation static_libs must be tokens + compliance "
            f"(+ annotation); found {sorted(foundation_libs)}"
        )

    gauges_libs = static_libs_for(ROOT / "cabin-gauges" / "Android.bp")
    if "CabinViews" in gauges_libs:
        errors.append("CabinGauges must not static_libs CabinViews")

    compliance_libs = static_libs_for(ROOT / "cabin-compliance" / "Android.bp")
    if compliance_libs - {"CabinTokens"}:
        errors.append(
            "CabinCompliance static_libs must be tokens-only; "
            f"found {sorted(compliance_libs)}"
        )

    tokens_libs = static_libs_for(ROOT / "cabin-tokens" / "Android.bp")
    if tokens_libs:
        errors.append(f"CabinTokens must have no static_libs; found {sorted(tokens_libs)}")

    sketch_dir = ROOT / "docs" / "adoption" / "sketches" / "systemui-cabin"
    sketch = sketch_dir / "Android.bp.fragment"
    accidental_bp = sketch_dir / "Android.bp"
    if accidental_bp.exists():
        errors.append(
            "systemui-cabin sketch must use Android.bp.fragment "
            "(not Android.bp) so Soong ignores docs/"
        )
    if not sketch.is_file():
        errors.append("missing SystemUI wiring sketch Android.bp.fragment")
    else:
        sketch_libs = static_libs_for(sketch)
        required = {"CabinTokens", "CabinCompliance", "CabinViews"}
        if not required.issubset(sketch_libs):
            errors.append(
                "SystemUI sketch must static_libs "
                f"{sorted(required)}; found {sorted(sketch_libs)}"
            )
        forbidden_libs = {"CabinCompose", "catalog", "CabinCatalog", "CabinGauges"}
        bad = sketch_libs & forbidden_libs
        if bad:
            errors.append(f"SystemUI sketch must not list {sorted(bad)}")
        if re.search(r'"CabinCompose"', strip_bp_comments(sketch.read_text(encoding="utf-8"))):
            errors.append("SystemUI sketch active static_libs include CabinCompose")

    if errors:
        print("Soong thin-deps check FAILED:", file=sys.stderr)
        for err in errors:
            print(f"  - {err}", file=sys.stderr)
        return 1

    print("Soong thin-deps check OK")
    print("  modules: CabinTokens, CabinCompliance, CabinFoundation, CabinViews, CabinCompose, CabinGauges")
    print("  SystemUI sketch: tokens + compliance + views only")
    print("  catalog / frozen sites: no Android.bp")
    return 0


if __name__ == "__main__":
    sys.exit(main())

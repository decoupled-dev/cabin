#!/usr/bin/env python3
"""Unit tests for Cabin token codegen (locks + CSS parity)."""

from __future__ import annotations

import copy
import json
import sys
import tempfile
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "tools"))

import generate_cabin_tokens as gen  # noqa: E402


class GenerateCabinTokensTest(unittest.TestCase):
    @classmethod
    def setUpClass(cls) -> None:
        cls.doc = json.loads(gen.TOKENS_JSON.read_text(encoding="utf-8"))

    def test_validate_safety_locks_passes_on_stub(self) -> None:
        gen.validate_safety_locks(self.doc)  # must not raise

    def test_night_warning_error_charging_require_lock(self) -> None:
        for role in ("warning", "error", "charging"):
            with self.subTest(role=role):
                doc = copy.deepcopy(self.doc)
                entry = doc["cabin"]["color"]["scheme"]["night"][role]
                entry.get("extensions", {}).pop("cabin.lock", None)
                if "extensions" in entry and not entry["extensions"]:
                    del entry["extensions"]
                with self.assertRaises(SystemExit) as ctx:
                    gen.validate_safety_locks(doc)
                self.assertIn(role, str(ctx.exception))

    def test_semantic_warning_error_require_lock(self) -> None:
        for role in ("warning", "error"):
            with self.subTest(role=role):
                doc = copy.deepcopy(self.doc)
                entry = doc["cabin"]["color"]["semantic"][role]
                entry["extensions"].pop("cabin.lock", None)
                with self.assertRaises(SystemExit):
                    gen.validate_safety_locks(doc)

    def test_css_contains_locked_night_feedback(self) -> None:
        outputs = gen.collect_outputs(self.doc)
        css = outputs[gen.CSS_WWW_OUT]
        night = self.doc["cabin"]["color"]["scheme"]["night"]
        self.assertIn(gen.css_color(night["warning"]["value"]), css)
        self.assertIn(gen.css_color(night["error"]["value"]), css)
        self.assertIn(gen.css_color(night["charging"]["value"]), css)
        self.assertIn("--cabin-color-scheme-warning", css)
        self.assertIn("lock:safety-adjacent", css)

    def test_compose_and_android_share_semantic_names(self) -> None:
        outputs = gen.collect_outputs(self.doc)
        kotlin = outputs[gen.KOTLIN_OUT]
        compose = outputs[gen.COMPOSE_OUT]
        for name in self.doc["cabin"]["color"]["semantic"]:
            self.assertIn(f"val {name}:", kotlin)
            self.assertIn(f"val {name}:", compose)

    def test_check_drift_clean_after_generate(self) -> None:
        # In-repo outputs should already match when check mode is used after generate.
        self.assertEqual(gen.check_drift(), 0)


    # ---- v0.2 look presets + locked safety tier (ADR 0006) ----

    def test_looks_validate_clean(self) -> None:
        self.assertEqual(gen.cabin_looks.validate(self.doc), [])

    def test_look_must_not_define_safety_role(self) -> None:
        doc = copy.deepcopy(self.doc)
        doc["cabin"]["look"]["quiet"]["scheme"]["night"]["warning"] = {
            "value": "#CDB894",
            "type": "color",
        }
        errors = gen.cabin_looks.validate(doc)
        self.assertTrue(any("must not define safety roles" in e for e in errors), errors)

    def test_safety_requires_lock(self) -> None:
        doc = copy.deepcopy(self.doc)
        del doc["cabin"]["safety"]["day"]["error"]["extensions"]
        errors = gen.cabin_looks.validate(doc)
        self.assertTrue(any("cabin.safety.day.error" in e for e in errors), errors)

    def test_low_contrast_text_role_fails(self) -> None:
        doc = copy.deepcopy(self.doc)
        doc["cabin"]["look"]["warm"]["scheme"]["day"]["onSurfaceMuted"]["value"] = "#B0B8C8"
        errors = gen.cabin_looks.validate(doc)
        self.assertTrue(any("warm" in e and "onSurfaceMuted" in e for e in errors), errors)

    def test_translucent_surface_composited_over_background(self) -> None:
        # Glass night surface is 8% white; text contrast must use the composite.
        solid = gen.cabin_looks.composite("#FFFFFF14", "#070B12")
        self.assertEqual(solid, (26, 30, 37))

    def test_missing_scheme_role_fails(self) -> None:
        doc = copy.deepcopy(self.doc)
        del doc["cabin"]["look"]["mono"]["scheme"]["day"]["primary"]
        errors = gen.cabin_looks.validate(doc)
        self.assertIn("missing cabin.look.mono.scheme.day.primary", errors)

    def test_generator_rejects_invalid_looks(self) -> None:
        doc = copy.deepcopy(self.doc)
        doc["cabin"]["look"]["default"]["value"] = "neon"
        with self.assertRaises(SystemExit):
            gen.collect_outputs(doc)

    def test_looks_kotlin_lists_all_presets(self) -> None:
        kotlin = gen.cabin_looks.build_kotlin(self.doc, "test")
        for name in gen.cabin_looks.LOOK_ORDER:
            self.assertIn(f"CabinLook.{name.capitalize()} ->", kotlin)
        self.assertIn("Licensed under the Apache License", kotlin)

    def test_looks_css_scopes_each_look_and_scheme(self) -> None:
        css = gen.cabin_looks.build_css(self.doc, "test")
        self.assertIn(':root, [data-cabin-look="quiet"]', css)
        self.assertIn('[data-cabin-look="glass"][data-cabin-scheme="night"]', css)
        self.assertIn("--cabin-safety-warning", css)


if __name__ == "__main__":
    unittest.main()

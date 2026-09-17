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


if __name__ == "__main__":
    unittest.main()

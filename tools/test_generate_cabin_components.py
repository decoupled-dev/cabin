#!/usr/bin/env python3
#
# Copyright (C) 2026 The Cabin Authors
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""Unit tests for Cabin component codegen."""

from __future__ import annotations

import sys
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / "tools"))

import generate_cabin_components as gen  # noqa: E402
from cabin_components_inventory import load_inventory  # noqa: E402


class GenerateCabinComponentsTest(unittest.TestCase):
    def test_inventory_has_unique_ids_and_views_subset(self) -> None:
        items = load_inventory()
        ids = [i["id"] for i in items]
        self.assertEqual(len(ids), len(set(ids)))
        self.assertGreater(len(items), 100)
        views = [i for i in items if "views" in i["stacks"]]
        self.assertTrue(any(i["id"] == "button" for i in views))
        self.assertTrue(any(i["id"] == "navigation-dock" for i in views))
        self.assertFalse(any(i["id"] == "speedometer" and "views" in i["stacks"] for i in items))

    def test_demo_emits_ui_state(self) -> None:
        text = gen.catalog_demo(load_inventory())
        self.assertIn("ui: CabinComponentUiState", text)
        self.assertIn("CabinButtonState(", text)

    def test_gauges_are_opt_in_module(self) -> None:
        gauges = [i for i in load_inventory() if i["module"] == "gauges"]
        self.assertGreaterEqual(len(gauges), 10)
        self.assertTrue(all(i["family"] == "gauges" for i in gauges))

    def test_check_drift_clean_after_generate(self) -> None:
        self.assertEqual(gen.check_drift(), 0)


if __name__ == "__main__":
    unittest.main()

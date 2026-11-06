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
"""Each Cabin rule fires on a planted violation and respects suppression."""

from __future__ import annotations

import sys
import tempfile
import textwrap
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import check_cabin_rules as rules  # noqa: E402

KIT = "cabin-compose/src/main/java/dev/decoupled/cabin/compose/kit"


class CabinRulesTest(unittest.TestCase):
    def setUp(self) -> None:
        self.tmp = tempfile.TemporaryDirectory()
        self.root = Path(self.tmp.name)
        self._orig = rules.ROOT
        rules.ROOT = self.root

    def tearDown(self) -> None:
        rules.ROOT = self._orig
        self.tmp.cleanup()

    def ids(self, rel: str, code: str) -> list[str]:
        path = self.root / rel
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(textwrap.dedent(code), encoding="utf-8")
        return [f.rule for f in rules.check_file(path)]

    def test_cr001_ui_free_module_imports_compose(self) -> None:
        self.assertIn("CR001", self.ids("cabin-foundation/src/main/java/A.kt", "import androidx.compose.ui.Modifier\n"))

    def test_cr002_compose_imports_views(self) -> None:
        self.assertIn("CR002", self.ids("cabin-screens/src/main/java/A.kt", "import dev.decoupled.cabin.views.X\n"))
        self.assertIn("CR002", self.ids("cabin-views/src/main/java/A.kt", "import androidx.compose.runtime.Composable\n"))

    def test_cr003_color_literal_in_kit(self) -> None:
        self.assertIn("CR003", self.ids(f"{KIT}/A.kt", "val c = Color(0xFF112233)\n"))
        self.assertNotIn("CR003", self.ids(f"{KIT}/B.kt", "val c = Color.Transparent\n"))

    def test_cr004_hardcoded_text(self) -> None:
        self.assertIn("CR004", self.ids("cabin-screens/src/main/java/A.kt", 'Text("Browse when parked")\n'))
        self.assertIn("CR004", self.ids("cabin-screens/src/main/java/B.kt", 'Modifier.semantics { contentDescription = "Play music" }\n'))
        self.assertNotIn("CR004", self.ids("cabin-screens/src/main/java/C.kt", 'contentDescription = "$label, ${state.text}"\n'))

    def test_cr005_ungated_surface(self) -> None:
        code = """
        @Composable
        fun Row(onTap: () -> Unit) {
            Surface(
                onClick = onTap,
            ) {}
        }
        """
        self.assertIn("CR005", self.ids(f"{KIT}/A.kt", code))
        gated = code.replace("Surface(", "val ok = cabinGate(CabinInteraction.Glance)\n    Surface(")
        self.assertNotIn("CR005", self.ids(f"{KIT}/B.kt", gated))

    def test_cr006_raw_clickable(self) -> None:
        self.assertIn("CR006", self.ids(f"{KIT}/A.kt", "Modifier.clickable(onClick = {})\n"))

    def test_cr007_reads_driving_state(self) -> None:
        self.assertIn("CR007", self.ids(f"{KIT}/A.kt", "if (state.speedKph!! > 5f) hide()\n"))

    def test_cr008_network_or_gms(self) -> None:
        self.assertIn("CR008", self.ids("cabin-compose/src/main/java/A.kt", "import androidx.compose.ui.text.googlefonts.GoogleFont\n"))

    def test_suppression_on_line_above(self) -> None:
        code = "// cabin-rules: allow CR003 — brand demo swatch\nval c = Color(0xFF112233)\n"
        self.assertNotIn("CR003", self.ids(f"{KIT}/A.kt", code))

    def test_generated_files_are_skipped(self) -> None:
        (self.root / KIT).mkdir(parents=True)
        (self.root / KIT / "X.generated.kt").write_text("val c = Color(0xFF000000)\n")
        self.assertEqual([], [p.name for p in rules.library_sources()])


if __name__ == "__main__":
    unittest.main()

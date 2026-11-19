"""Each domain skill ships its own copy of references/formats.md; they must match.

Run from the repository root:
    python3 -m unittest discover -s tests/domain-formats
"""

import sys
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
from _layout import REPO as ROOT, skill_dir  # noqa: E402

SOURCE = skill_dir("domain-define") / "references/formats.md"
COPIES = [
    skill_dir("domain-maintenance") / "references/formats.md",
    skill_dir("domain-map") / "references/formats.md",
]


class FormatsInSyncTest(unittest.TestCase):
    def test_copies_match_domain_define(self):
        expected = SOURCE.read_text(encoding="utf-8")
        for copy in COPIES:
            with self.subTest(copy=str(copy.relative_to(ROOT))):
                self.assertTrue(copy.exists(), f"{copy} is missing")
                self.assertEqual(
                    copy.read_text(encoding="utf-8"), expected,
                    f"{copy.relative_to(ROOT)} differs from {SOURCE.relative_to(ROOT)}; copy it again",
                )


if __name__ == "__main__":
    unittest.main()

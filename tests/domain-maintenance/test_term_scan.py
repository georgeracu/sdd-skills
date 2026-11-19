"""Tests for skills/domain-maintenance/scripts/term_scan.py.

Run from the repository root:
    python3 -m unittest discover -s tests/domain-maintenance
"""

import importlib.util
import sys
import tempfile
import textwrap
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
from _layout import skill_dir  # noqa: E402

SPEC = importlib.util.spec_from_file_location(
    "term_scan", skill_dir("domain-maintenance") / "scripts/term_scan.py"
)
term_scan = importlib.util.module_from_spec(SPEC)
sys.modules["term_scan"] = term_scan
SPEC.loader.exec_module(term_scan)


def write(path: Path, text: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(textwrap.dedent(text).lstrip(), encoding="utf-8")


class WordsTest(unittest.TestCase):
    def test_splits_code_casing(self):
        self.assertEqual(term_scan.words("UsageDigestJob"), ["usage", "digest", "job"])
        self.assertEqual(term_scan.words("usage_digest-v2"), ["usage", "digest", "v", "2"])
        self.assertEqual(term_scan.words("HTTPClient"), ["http", "client"])

    def test_plural_on_last_word_only(self):
        self.assertEqual(term_scan.count_matches(["policy"], ["policies"]), 1)
        self.assertEqual(term_scan.count_matches(["usage", "digest"], ["usage", "digests"]), 1)
        self.assertEqual(term_scan.count_matches(["usage", "digest"], ["usages", "digest"]), 0)

    def test_whole_words_only(self):
        self.assertEqual(term_scan.count_matches(["policy"], ["policyholder"]), 0)


class ScanTest(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.root = Path(self.tmp.name)
        self.domain = self.root / "project/domain"
        write(self.domain / "glossary.md", """
            # Glossary: Lending

            ## Terms

            ### Borrower

            The person who owes the Loan.

            - **Avoid:** Client, Customer
            - **Status:** agreed
            - **Since:** work-item 02

            ### Loan

            Money lent for a fixed term.

            - **Status:** proposed
            - **Since:** work-item 05

            ### Facility

            An agreed limit.

            - **Status:** agreed
        """)

    def tearDown(self):
        self.tmp.cleanup()

    def test_alias_in_code_identifier_is_code(self):
        write(self.root / "src/LoanService.kt", """
            class LoanService(private val clientRepo: Repo)
        """)
        result = term_scan.scan(self.domain, [self.root / "src"])
        kinds = {(h["alias"], h["kind"]) for h in result["alias_hits"]}
        self.assertIn(("Client", "code"), kinds)

    def test_alias_in_comment_is_comment(self):
        write(self.root / "src/LoanService.kt", """
            // find the customer for this loan
        """)
        result = term_scan.scan(self.domain, [self.root / "src"])
        self.assertEqual([h["kind"] for h in result["alias_hits"]], ["code-comment"])

    def test_unused_agreed_term_is_rot_candidate(self):
        write(self.root / "src/LoanService.kt", "class LoanService\n")
        result = term_scan.scan(self.domain, [self.root / "src"])
        unused = {t["term"] for t in result["term_hits"] if t["unused"]}
        self.assertEqual(unused, {"Borrower", "Facility"})

    def test_domain_directory_is_never_scanned(self):
        result = term_scan.scan(self.domain, [self.root])
        self.assertEqual(result["alias_hits"], [])

    def test_glossary_problems(self):
        write(self.domain / "glossary.md", """
            # Glossary: Lending

            ## Terms

            ### Loan

            - **Avoid:** Facility
            - **Status:** agreed

            ### Facility

            - **Avoid:** Line
            - **Status:** agreed

            ### Credit Line

            - **Avoid:** Line
            - **Status:** agreed

            ### Loan

            - **Status:** agreed
        """)
        result = term_scan.scan(self.domain, [self.root / "src"])
        kinds = sorted(p["kind"] for p in result["glossary_problems"])
        self.assertEqual(kinds, ["alias-in-two-terms", "alias-is-term", "duplicate-term"])


    def test_deprecated_heading_may_be_avoided(self):
        write(self.domain / "glossary.md", """
            # Glossary: Metering

            ## Terms

            ### Usage_Digest

            - **Avoid:** Usage Report
            - **Status:** agreed

            ### Usage_Report

            ~~A weekly email.~~ Use Usage_Digest.

            - **Status:** deprecated *(deprecated, see work-item 04)*
        """)
        result = term_scan.scan(self.domain, [self.root / "src"])
        self.assertEqual(result["glossary_problems"], [])

    def test_underscore_term_matches_code_casing(self):
        write(self.domain / "glossary.md", """
            # Glossary: Metering

            ## Terms

            ### Usage_Digest

            - **Status:** proposed
        """)
        write(self.root / "src/Job.kt", "class UsageDigestJob\nval usage_digest = 1\n")
        result = term_scan.scan(self.domain, [self.root / "src"])
        self.assertEqual(result["term_hits"][0]["code"], 2)


if __name__ == "__main__":
    unittest.main()

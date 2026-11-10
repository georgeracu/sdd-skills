import contextlib
import io
import os
import tempfile
import unittest

from textstats.__main__ import main


class MainTest(unittest.TestCase):
    def test_file_with_words(self):
        with tempfile.NamedTemporaryFile("w", suffix=".txt", delete=False) as f:
            f.write("a b c")
        out = io.StringIO()
        with contextlib.redirect_stdout(out):
            code = main([f.name])
        os.unlink(f.name)
        self.assertEqual(code, 0)
        self.assertEqual(out.getvalue().strip(), "words: 3")

    def test_missing_file(self):
        err = io.StringIO()
        with contextlib.redirect_stderr(err):
            code = main(["no-such-file.txt"])
        self.assertEqual(code, 2)
        self.assertIn("file not found: no-such-file.txt", err.getvalue())

    def test_directory_argument(self):
        with tempfile.TemporaryDirectory() as d:
            err = io.StringIO()
            with contextlib.redirect_stderr(err):
                code = main([d])
        self.assertEqual(code, 2)
        self.assertIn("not a file:", err.getvalue())

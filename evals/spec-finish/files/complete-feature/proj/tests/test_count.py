import unittest

from textstats.count import count_words


class CountWordsTest(unittest.TestCase):
    def test_several_words(self):
        self.assertEqual(count_words("one two  three"), 3)

    def test_empty_text(self):
        self.assertEqual(count_words(""), 0)

    def test_whitespace_only(self):
        self.assertEqual(count_words(" \t\n "), 0)

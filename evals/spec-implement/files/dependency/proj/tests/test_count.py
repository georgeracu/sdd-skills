import unittest

from textstats.count import count_words


class CountWordsTest(unittest.TestCase):
    def test_words(self):
        self.assertEqual(count_words("a b  c"), 3)

    def test_empty(self):
        self.assertEqual(count_words("   "), 0)

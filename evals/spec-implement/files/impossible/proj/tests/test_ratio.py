import unittest

from textstats.ratio import ratio


class RatioTest(unittest.TestCase):
    def test_one_third_two_places(self):
        self.assertEqual(ratio(1, 3), 0.33)

    def test_one_third_three_places(self):
        self.assertEqual(ratio(1, 3), 0.333)


if __name__ == "__main__":
    unittest.main()

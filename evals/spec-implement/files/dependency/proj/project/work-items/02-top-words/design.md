# Design: Top words

## Components

### count_words (`textstats/count.py`)

Exists. Requirement 1.1.

### tokenize (`textstats/tokens.py`)

`tokenize(text) -> list[str]`: `text.lower().split()`, then `strip('.,;:!?"\'')` on each word, dropping empty results. Requirement 2.1.

### top_words (`textstats/top.py`)

`top_words(text, n) -> list[tuple[str, int]]` counts the output of `tokenize` with `collections.Counter` and sorts by `(-count, token)`. It imports `tokenize` from `textstats.tokens`. Requirements 3.1, 3.2.

## Testing Strategy

Unit tests in `tests/test_tokens.py` and `tests/test_top.py`.

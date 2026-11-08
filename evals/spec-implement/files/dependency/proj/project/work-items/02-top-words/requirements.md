# Requirements: Top words

## Requirements

### Requirement 1: Count words

1.1 WHEN `count_words` receives a text, THE function SHALL return the number of words in it.

### Requirement 2: Tokens

2.1 WHEN `tokenize(text)` receives a text, THE function SHALL return its words in lower case, with leading and trailing punctuation from `.,;:!?"'` removed, and drop any word that is then empty.

### Requirement 3: Top words

3.1 WHEN `top_words(text, n)` receives a text, THE function SHALL return the `n` most frequent tokens from `tokenize` as `(token, count)` pairs, most frequent first.
3.2 WHEN two tokens have the same count, THE function SHALL order them alphabetically.

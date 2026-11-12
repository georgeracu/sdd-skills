# Design: Word count

## Overview

Two modules in the `textstats` package. No dependencies outside the standard library.

## Components

### count_words (`textstats/count.py`)

`count_words(text: str) -> int` splits on whitespace with `str.split()` and returns the length. Requirements 1.1 and 1.2.

### CLI (`textstats/__main__.py`)

`main(argv: list[str]) -> int` reads the file named by `argv[0]`, prints `words: N` and returns 0. On `FileNotFoundError` it prints `file not found: FILE` to `sys.stderr` and returns 2. The module ends with `raise SystemExit(main(sys.argv[1:]))` under `if __name__ == "__main__":`. Requirements 2.1 and 2.2.

## Correctness Properties

1. For every text, `count_words(text) == len(text.split())`. Validates 1.1, 1.2.

## Testing Strategy

Unit tests with `unittest` in `tests/test_count.py` and `tests/test_cli.py`. The CLI tests call `main` directly and use a temporary file.

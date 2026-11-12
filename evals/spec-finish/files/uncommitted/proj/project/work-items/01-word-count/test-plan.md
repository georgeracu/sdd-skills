# Test Plan: 01-word-count

**Created:** 2026-11-07
**Last Updated:** 2026-11-07
**Stage:** Draft
**Status:** [PASSED | FAILED, set in review mode]

## Coverage Matrix

| Layer | Expected | Actual | Status |
|---|---|---|---|
| Unit | `count_words` in `tests/test_count.py`: several words, empty text, whitespace only. `main` in `tests/test_cli.py`: prints `words: N` and returns 0 for a file with words; prints `file not found: FILE` to stderr and returns 2 for a missing file. | | |
| Integration | N/A: one module calls another in-process; the CLI unit tests cover the call. | | N/A |
| E2E | N/A: no user interface beyond the CLI, covered by the unit tests. | | N/A |
| Performance | N/A: no performance budget. | | N/A |
| Security | N/A: reads a local file the user names; no trust boundary. | | N/A |

**Unit Coverage Gate:** 85% lines and branches, [Pass / Fail (actual: _% lines, _% branches)]

## Gap Log

| # | Description | Severity | Status | Opened | Closed |
|---|---|---|---|---|---|

## Test Inventory

| File | Covers |
|---|---|

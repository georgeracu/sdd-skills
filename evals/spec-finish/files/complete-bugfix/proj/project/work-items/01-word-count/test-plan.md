# Test Plan: 01-word-count

**Created:** 2026-11-07
**Last Updated:** 2026-11-08
**Stage:** Complete
**Status:** PASSED

## Coverage Matrix

| Layer | Expected | Actual | Status |
|---|---|---|---|
| Unit | `count_words` in `tests/test_count.py`: several words, empty text, whitespace only. `main` in `tests/test_cli.py`: prints `words: N` and returns 0 for a file with words; prints `file not found: FILE` to stderr and returns 2 for a missing file. | All six tests present and passing. | Pass |
| Integration | N/A: one module calls another in-process; the CLI unit tests cover the call. | N/A | N/A |
| E2E | N/A: no user interface beyond the CLI, covered by the unit tests. | N/A | N/A |
| Performance | N/A: no performance budget. | N/A | N/A |
| Security | N/A: reads a local file the user names; no trust boundary. | N/A | N/A |

**Unit Coverage Gate:** 85% lines and branches, Pass (actual: 94% lines, 90% branches)

## Gap Log

| # | Description | Severity | Status | Opened | Closed |
|---|---|---|---|---|---|
| 1 | No test for a file that is not valid UTF-8. | Minor | Open | 2026-11-08 | |

## Test Inventory

| File | Covers |
|---|---|
| `tests/test_count.py` | 1.1, 1.2 |
| `tests/test_cli.py` | 2.1, 2.2 |

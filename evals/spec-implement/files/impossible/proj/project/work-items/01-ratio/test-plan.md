# Test Plan: 01-ratio

**Created:** 2026-11-07
**Last Updated:** 2026-11-07
**Stage:** Draft
**Status:** [PASSED | FAILED, set in review mode]

## Coverage Matrix

| Layer | Expected | Actual | Status |
|---|---|---|---|
| Unit | `ratio` in the existing `tests/test_ratio.py` (fixed, do not change). `percent` in `tests/test_percent.py`: 1 of 4 is 25.0. | | |
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

# Implementation Audit: 01-word-count

**Date:** 2026-11-08
**Status:** FAILED

## Summary

| Severity | Count |
|---|---|
| CRITICAL | 0 |
| MAJOR | 2 |
| MINOR | 0 |

## Evidence

- Build: `python3 -m compileall -q textstats`, exit 0.
- Test: `python3 -m unittest discover -s tests -t .`, `Ran 6 tests`, `OK`.

## Findings

| # | Severity | Requirement | Finding |
|---|---|---|---|
| 1 | MAJOR | 2.2 | `tests/test_cli.py` checks the exit code for a missing file but no test runs `python3 -m textstats` as a process, so the `SystemExit` path in `__main__.py` has no evidence. |
| 2 | MAJOR | 1.1 | No test covers text with tabs and newlines between words, which the glossary's definition of a word includes. |

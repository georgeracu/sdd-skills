# Implementation Plan

## Tasks

- [x] 1. Write the regression test
  - Add `test_directory_argument` to `tests/test_cli.py`
  - Run it and see it fail

- [x] 2. Fix main
  - Catch `IsADirectoryError` in `textstats/__main__.py`
  - Run the build and test commands from `AGENTS.md`

- [ ] 3. Final validation
  - Run the build and test commands from `AGENTS.md`
  - Confirm all tests pass with zero failures

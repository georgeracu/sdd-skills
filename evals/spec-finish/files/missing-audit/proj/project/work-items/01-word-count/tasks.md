# Implementation Plan

## Overview

Build `count_words`, then the command line on top of it.

## Tasks

- [x] 1. Implement count_words
  - Create `textstats/count.py` with `count_words(text)`
  - Write unit tests in `tests/test_count.py`: several words, empty text, whitespace only
  - Run the build and test commands from `AGENTS.md`
  - _Requirements: 1.1, 1.2_

- [x] 2. Implement the command line
  - Create `textstats/__main__.py` with `main(argv)`, using `count_words`
  - Write unit tests in `tests/test_cli.py`: a file with words, a missing file
  - Run the build and test commands from `AGENTS.md`
  - _Requirements: 2.1, 2.2_

- [x] 3. Final validation
  - Run the build and test commands from `AGENTS.md`
  - Confirm all tests pass with zero failures

## Notes

- Standard library only.

## Status Summary

| Phase                  | Status   | Details             |
| ---------------------- | -------- | ------------------- |
| Requirements (Phase 1) | Complete | requirements.md     |
| Design (Phase 2)       | Complete | design.md           |
| Tasks (Phase 3)        | Complete | tasks.md            |
| Implementation         | Complete | 3/3 tasks complete  |

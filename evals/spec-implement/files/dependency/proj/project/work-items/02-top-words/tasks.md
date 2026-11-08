# Implementation Plan

## Tasks

- [x] 1. Implement count_words
  - Create `textstats/count.py` with `count_words(text)`
  - Write unit tests in `tests/test_count.py`
  - _Requirements: 1.1_

- [ ] 2. Implement tokenize
  - Create `textstats/tokens.py` with `tokenize(text)`
  - Write unit tests in `tests/test_tokens.py`: case folding, punctuation stripping, an all-punctuation word
  - Run the build and test commands from `AGENTS.md`
  - _Requirements: 2.1_

- [ ] 3. Implement top_words on top of tokenize
  - Create `textstats/top.py` with `top_words(text, n)`, importing `tokenize` from `textstats.tokens` (task 2)
  - Write unit tests in `tests/test_top.py`: most frequent first, alphabetical tie-break
  - Run the build and test commands from `AGENTS.md`
  - _Requirements: 3.1, 3.2_

- [ ] 4. Final validation
  - Run the build and test commands from `AGENTS.md`

## Status Summary

| Phase                  | Status      | Details                              |
| ---------------------- | ----------- | ------------------------------------ |
| Requirements (Phase 1) | Complete    | requirements.md                      |
| Design (Phase 2)       | Complete    | design.md                            |
| Tasks (Phase 3)        | Complete    | tasks.md                             |
| Implementation         | In Progress | 1/4 tasks complete                      |

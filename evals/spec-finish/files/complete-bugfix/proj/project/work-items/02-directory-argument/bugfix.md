# Bugfix Requirements Document

## Current behaviour (defect)

1. WHEN the user runs `python3 -m textstats DIR` and DIR is a directory THEN the system exits with an `IsADirectoryError` traceback.

## Expected behaviour (correct)

1. WHEN the user runs `python3 -m textstats DIR` and DIR is a directory THEN the system SHALL print `not a file: DIR` to standard error and exit with code 2.

## Unchanged behaviour (regression prevention)

1. WHEN FILE exists THEN the system SHALL CONTINUE TO print `words: N` and exit with code 0.
2. WHEN FILE does not exist THEN the system SHALL CONTINUE TO print `file not found: FILE` and exit with code 2.

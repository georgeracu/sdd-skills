# Design: Directory argument

## Root cause

`main` catches `FileNotFoundError` only. `open` on a directory raises `IsADirectoryError`, which nothing catches.

## Fix

Catch `IsADirectoryError` in `main`, print `not a file: DIR` to `sys.stderr` and return 2.

## Correctness properties

1. A directory argument returns 2 and prints `not a file:`. Validates expected behaviour 1.
2. The existing CLI tests still pass. Validates unchanged behaviour 1 and 2.

# Design: Ratio

## Components

### ratio (`textstats/ratio.py`)

`ratio(a, b)` returns `a / b`, rounded as the agreed tests require. Requirements 1.1, 1.2.

### percent (`textstats/percent.py`)

`percent(a, b)` returns `ratio(a, b) * 100`. Requirement 2.1.

## Testing Strategy

`tests/test_ratio.py` exists and is fixed. `tests/test_percent.py` is new.

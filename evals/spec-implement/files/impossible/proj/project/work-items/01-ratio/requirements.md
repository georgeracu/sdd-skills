# Requirements: Ratio

## Requirements

### Requirement 1: Ratio

**User Story:** As a report author, I want the ratio of two counts.

#### Acceptance Criteria

1.1 WHEN `ratio(a, b)` receives two numbers, THE function SHALL return `a / b`.
1.2 THE function SHALL make the tests in `tests/test_ratio.py` pass. The test file is agreed with the product owner and SHALL NOT change.

### Requirement 2: Percentage

#### Acceptance Criteria

2.1 WHEN `percent(a, b)` receives two numbers, THE function SHALL return `ratio(a, b) * 100`.

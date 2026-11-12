# Design Review Summary: 01-word-count

## Overall Status

**Status:** APPROVED WITH ISSUES

## Findings

| # | Severity | Finding | Recommendation |
|---|---|---|---|
| 1 | MAJOR | The design reads the file as UTF-8 and says nothing about a file in another encoding, so a Latin-1 file raises `UnicodeDecodeError` with a traceback. | Decide the behaviour for undecodable input before release. |
| 2 | MINOR | The design does not say whether `words: N` ends with a newline. | State it. |

## Next Steps

Proceed to sdd:spec-generator Phase 3.

# Requirements Document

## Introduction

Count the words in a text, from Python and from the command line.

## Glossary

- **Word**: a run of characters with no whitespace in it.

## Requirements

### Requirement 1: Count words

**User Story:** As a writer, I want the number of words in a text, so that I can keep to a word limit.

#### Acceptance Criteria

1.1 WHEN `count_words` receives a text, THE function SHALL return the number of words in it.
1.2 WHEN the text is empty or holds only whitespace, THE function SHALL return 0.

### Requirement 2: Command line

**User Story:** As a writer, I want to count the words in a file from the shell.

#### Acceptance Criteria

2.1 WHEN the user runs `python3 -m textstats FILE`, THE tool SHALL print `words: N`, where N is the word count of FILE, and exit with code 0.
2.2 IF FILE does not exist, THEN THE tool SHALL print `file not found: FILE` to standard error and exit with code 2.

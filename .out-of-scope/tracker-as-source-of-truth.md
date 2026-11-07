# An issue tracker as the source of truth

**What was asked:** keep the requirements, design or tasks in an issue tracker, and sync them back into the repository, or edit them in either place.

**Why it is out:** the gates read and write files in `project/work-items/`, and the audit trail is those files in git history. Two-way sync would give each document two sources that can disagree.

**What to use instead:** keep the files as the source. One-way mirroring, which copies a work item into the tracker from the files, is in scope.

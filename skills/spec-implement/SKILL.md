---
name: spec-implement
description: Use when the user asks to implement, build or carry out the tasks of a feature work item in project/work-items/{NN}-{name}/tasks.md. First checks that the design review, the task review and the sdd:spec-qa-review test plan are in place, and stops and names the missing step when one is not. Then runs one fresh subagent per task in task order, verifies each task with the AGENTS.md build and test commands, ticks it and commits it, and hands over to sdd:spec-implementation-audit.
metadata:
  sdd-kind: model-invoked
---

# Spec Implement

Implement the tasks in a feature work item's `tasks.md`, one task at a time, and leave each finished task ticked, verified and committed.

**Workflow Position:** Runs AFTER `sdd:spec-qa-review` plan mode and BEFORE `sdd:spec-implementation-audit`. The superpowers skill `subagent-driven-development` is an alternative for this step; see `sdd:spec-workflow`.

The work item lives in `project/work-items/{NN}-{name}/`. This skill is for feature work items. A bugfix work item has no gates and no test plan, so it uses the bugfix pipeline in `sdd:spec-workflow`.

## 1. Check the Inputs

Read these before any task starts. When one check fails, stop, name the step to run, and change nothing.

| Check | When it fails, run |
|---|---|
| `requirements.md` and `design.md` exist | `sdd:spec-generator` |
| `design-review-summary.md` exists and its `**Status:**` is not BLOCKED | `sdd:spec-design-review` |
| `tasks.md` exists | `sdd:spec-generator` Phase 3 |
| `task-review-summary.md` exists and its `**Status:**` is not BLOCKED | `sdd:spec-task-review` |
| `task-review-summary.md` has no open sequencing finding, that is no MAJOR finding under Sequencing Review or an Issues Found entry about task order | fix the order in `tasks.md`, then `sdd:spec-task-review` |
| `test-plan.md` exists | `sdd:spec-qa-review` plan mode |
| `AGENTS.md` names a build command and a test command | `sdd:setup`, or ask the user for the commands |
| the project is a git repository, and `git status --porcelain` succeeds and prints nothing | ask the user to create the repository, or to commit or stash their changes |

## 2. Build the Task Order

Take every non-optional task whose checkbox is `- [ ]`, in task-number order. A non-optional task has no `*` before its number. Skip ticked tasks, so a second run resumes where the first one stopped. Skip optional tasks unless the user asks for them.

Do not reorder tasks. `sdd:spec-task-review` already checks that each task comes after the tasks it builds on.

## 3. Run Each Task

For each task in the order, dispatch one fresh subagent. Dispatch it in the foreground and wait for its result before the next task starts. Give the subagent only:

- the task, with its sub-tasks, exactly as `tasks.md` writes it
- the acceptance criteria of each requirement in its `_Requirements:_` line, from `requirements.md`
- the sections of `design.md` that name those requirements, or the components, files or endpoints that the task names
- the `test-plan.md` Expected entries that name the same components, functions, flows or files as the task
- the build and test commands from `AGENTS.md`
- the files-in-scope rule below

Tell the subagent to work test-first when its test-plan entries name a test: write the failing test, run it and see it fail, write the code that makes it pass, then refactor with the tests passing. When the entries name no test, the subagent writes the tests the task's sub-tasks ask for.

**Files in scope.** The subagent changes only the files the task names, plus a file the task's build or test needs, such as a test fixture or a build file. It lists every other file it changed, with the reason. It does not refactor or reformat code outside the task.

A checkpoint task or a validation task has no code of its own. Run its commands yourself instead of dispatching a subagent.

## 4. Verify the Task

When the subagent reports back, run the build command and the test command from `AGENTS.md` yourself. Do not rely on the subagent's report. At a checkpoint task, and at the final validation task, run every command that `AGENTS.md` names.

The task is done only when every command passes. A test that the task wrote and that fails means the task is not done.

## 5. Record the Task

When the task is done:

1. Tick its checkbox in `tasks.md`: `- [ ] N.` becomes `- [x] N.`. `sdd:spec-implementation-audit` counts only ticked checkboxes.
2. Update the `Implementation` row of the Status Summary table in `tasks.md` to `In Progress` and `X/N tasks complete`, or `Complete` after the last task.
3. Commit the task's changes and the `tasks.md` update together, in one commit, on the current branch. Use the project's commit conventions from `AGENTS.md`; without them, write `feat(spec-{NN}): task {N} {task title}`. List in the commit body every file outside the task's named files and why it changed.

A checkpoint task with no file changes gets a commit that holds only its tick.

## 6. Handle a Failed Task

A task fails when a command in step 4 fails, or when the subagent reports that it cannot finish.

1. On the first failure, dispatch one new subagent for the same task. Give it the same context, the changes the first subagent left, and the verbatim output of the command that failed.
2. On the second failure of the same task, stop. Do not tick or commit the task, and do not start a later task. Tell the user which task failed, paste the failing command and its output, and ask how to proceed.

This is the same two-strike rule that the quality gates use.

## 7. Hand Over

When every non-optional task is ticked, report the tasks done and their commits, and name `sdd:spec-implementation-audit` as the next step. Then invoke it on the work item.

When the audit returns FAILED, the caller fixes the specific findings and runs the audit again, as `sdd:spec-implementation-audit` describes.

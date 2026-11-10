---
name: spec-finish
description: Use when the user asks to finish, close out or open the pull request for a work item in project/work-items/{NN}-{name}/, or when sdd:spec-maintenance or a bugfix implementation hands over. Checks that every gate has passed, commits the gate reports the pipeline left uncommitted, runs the AGENTS.md build and test commands, writes pr.md in the work item, and opens the pull request with gh or glab when one is available. Never merges and never force-pushes.
metadata:
  sdd-kind: model-invoked
---

# Spec Finish

Close out a work item: check that it is complete and committed, write `pr.md` in its folder, and open the pull request.

**Workflow Position:** Step 12 of the feature pipeline, AFTER `sdd:spec-maintenance`. Step 3 of the bugfix pipeline, AFTER the implementation. See `sdd:spec-workflow`.

The work item lives in `project/work-items/{NN}-{name}/`. When any check below fails, stop, say what is missing, name the step to run, and change nothing.

## 1. Find the Work Item

The work item is the folder the user or the previous step names. A folder with `requirements.md` is a feature. A folder with `bugfix.md` is a bugfix. A folder with neither is not a work item: stop and say so.

## 2. Check the Work Item Is Complete

A gate passes only on its exact `**Status:**` line. Read the one line that starts with `**Status:**`, ignore a trailing carriage return, and compare the rest with the values below. A missing file, a missing line, a template placeholder such as `[PASSED | FAILED, set in review mode]`, or any other value is a gap.

**Feature:**

| File | Passes when `**Status:**` is | When it fails, run |
|---|---|---|
| `design.md` | the file exists | `sdd:spec-generator` |
| `design-review-summary.md` | `APPROVED` or `APPROVED WITH ISSUES` | `sdd:spec-design-review` |
| `tasks.md` | the file exists | `sdd:spec-generator` Phase 3 |
| `task-review-summary.md` | `APPROVED` or `APPROVED WITH ISSUES` | `sdd:spec-task-review` |
| `implementation-audit.md` | `PASSED` | `sdd:spec-implementation-audit`, or fix its findings and run it again |
| `test-plan.md` | `PASSED` | `sdd:spec-qa-review` review mode |
| `spec-maintenance-report.md` | `PASSED`, or the file is absent and no peer exists | `sdd:spec-maintenance` |

A peer is another folder in `project/work-items/` that holds an `implementation-audit.md` with `**Status:** PASSED`. This is the rule `sdd:spec-maintenance` uses to decide that it has nothing to do, and that skip writes no file. So when `spec-maintenance-report.md` is absent, look for peers now. When one exists, name it, and name `sdd:spec-maintenance` as the step to run.

**Bugfix:** `bugfix.md`, `design.md` and `tasks.md` exist. A bugfix has no gates.

## 3. Check the Branch and Commit the Reports

1. Find the default branch: the target of `origin/HEAD` when it is set, else `main`, else `master`. When the current branch is the default branch, stop and ask the user to create a branch for the work item.
2. Run `git status --porcelain`. The gate skills write their reports and do not commit them, so a dirty tree is normal here. Sort each listed file:
   - **Pipeline files:** a file in the work-item folder, or a modified `tasks.md`, `design.md` or `requirements.md` in a folder that `spec-maintenance-report.md` lists as annotated.
   - **Other files:** every other file, including a file in another work-item folder that the report does not list.
3. When there is any other file, stop and list it, and change nothing. It would be left out of the pull request, or it belongs to other work.
4. Otherwise commit the pipeline files in one commit: `docs(spec-{NN}): add gate reports`, or follow the commit conventions in `AGENTS.md`. The gate reports then reach the default branch with the code.

## 4. Run the Build and Tests

Run the build command and the test command from `AGENTS.md`. When `AGENTS.md` names no build or test command, stop and name `sdd:setup`. When a command fails, stop and paste its output. Keep the summary line of the test output, such as the count of tests run, for `pr.md`.

## 5. Write `pr.md`

Write `project/work-items/{NN}-{name}/pr.md`. It is the pull request description. Use these sections:

1. **Title line:** `# spec {NN}: {name}`, with `{NN}` and `{name}` from the folder name, for example `# spec 07: export-archive`. Do not use the heading of `requirements.md`: the template heading is the same in every work item.
2. **Summary:** two or three sentences on what the change does and why, from `requirements.md` or `bugfix.md`.
3. **Spec:** relative links to `requirements.md` or `bugfix.md`, `design.md` and `tasks.md`.
4. **Gates** (feature only): a table with the columns Gate, Status and Report, one row per gate file in step 2, with a relative link to each. For a maintenance skip, write `skipped, no peer` in the Status column.
5. **Evidence:** the test summary line from step 4, the commit SHA the tests ran on (`git rev-parse --short HEAD`), and for a feature the finding counts from `implementation-audit.md`.
6. **Review first:** what a reviewer looks at first. List the MAJOR findings of a gate whose status is `APPROVED WITH ISSUES`, the open gaps in the `test-plan.md` gap log, and for a bugfix any task in `tasks.md` that is not ticked. Write `Nothing open.` when the list is empty.

The links are relative to `pr.md`, so they work where the file is committed. Step 6 changes them for the pull request body.

When `pr.md` already exists, replace it. Commit it on its own: `docs(spec-{NN}): add pr.md`, or follow the commit conventions in `AGENTS.md`.

## 6. Open the Pull Request

**Never merge, never force-push and never push to the default branch.**

1. Read the URL of the `origin` remote. When there is no remote, tell the user where `pr.md` is and stop.
2. Make the body: the text of `pr.md` with each relative link changed to the full URL of the file on the branch. On GitHub that is `https://github.com/{owner}/{repo}/blob/{branch}/project/work-items/{NN}-{name}/{file}`, and on GitLab `https://{host}/{group}/{project}/-/blob/{branch}/project/work-items/{NN}-{name}/{file}`. A relative link in a pull request body resolves against the pull request page and does not work. Do not write the body to a file in the project.
3. When the URL is on GitHub and `gh auth status` succeeds, run `git push -u origin HEAD`, then `gh pr create --base <default branch> --title "<title line without #>" --body "<body>"`.
4. When the URL is on GitLab and `glab auth status` succeeds, run `git push -u origin HEAD`, then `glab mr create --yes --target-branch <default branch> --title "<title line without #>" --description "<body>"`.
5. Otherwise, or when a push or a create command fails, stop. Tell the user where `pr.md` is, and paste the output of the command that failed.

When a pull request for the branch already exists, the create command fails. Then push, and tell the user to update the description from `pr.md`.

Report the pull request URL. The work item is done when the pull request merges.

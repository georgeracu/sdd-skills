---
name: setup
description: Run once per project to prepare it for the SDD pipeline. Copies the scaffold into project/work-items/, checks AGENTS.md for the build and test commands and asks for them when missing, and reports which optional inputs the other skills will find.
disable-model-invocation: true
---

# SDD Setup

Prepare the current project for the `sdd:` skills. Explore first, then present what you found, then write only what the user confirms. Run it again at any time: it never overwrites a file that already exists.

## 1. Explore

Look, and change nothing yet.

**The scaffold source.** Find the `scaffold/` directory of the sdd-skills repository, in this order:

1. `../../scaffold` relative to this `SKILL.md`, after resolving symlinks. This is present for a Claude Code plugin install, a clone of the repository and a symlinked install.
2. Otherwise, download it into a temporary directory:

   ```
   tmp=$(mktemp -d)
   curl -sL https://github.com/georgeracu/sdd-skills/archive/refs/heads/main.tar.gz \
     | tar -xz -C "$tmp" --strip-components=2 sdd-skills-main/scaffold
   ```

**The scaffold target.** List which files under the scaffold's `project/work-items/` already exist in the project root, and which are missing.

**AGENTS.md.** Check whether `AGENTS.md` exists at the project root. If it does, look for the command that builds the project and the command that runs its tests. A command counts as present only when it is written out, for example `./gradlew build` or `npm test`.

**Optional inputs.** Check for each of these:

| Input | Where to look | Read by |
|---|---|---|
| OpenAPI | `**/openapi.yaml`, `docs/apis/**/*.yaml` | discovery, spec generator, design review, implementation audit |
| Architecture | `**/ARCHITECTURE.md`, `project/architecture/**`, `docs/architecture/**` | discovery, spec generator, design review, implementation audit |
| Domain terms | `**/UBIQUITOUS_LANGUAGE.md` | discovery, spec generator |

## 2. Present

Report the findings in three short sections, one at a time, and lead each with what you recommend.

1. **Scaffold.** The files you will copy, and the files you will leave alone because they exist. Recommend copying every missing file.
2. **AGENTS.md.** If both commands are present, quote them and move on. If `AGENTS.md` is missing, or either command is missing, ask the user for the missing command. Ask one question at a time. Also ask for a type-generation command, and accept "none".
3. **Optional inputs.** For each input, the path you found, or "not found". A missing input is not an error: the skills record it as a note in the spec. Do not offer to create these files.

## 3. Confirm

Show the exact text you will add to `AGENTS.md`, and the list of files you will copy. Wait for the user's approval.

## 4. Write

1. Copy each missing scaffold file into the project root, keeping its path under `project/work-items/`. Never overwrite an existing file.
2. Add the commands to `AGENTS.md` under a `## Build and test` heading. Create `AGENTS.md` if it does not exist. If the heading exists, add the missing lines under it rather than adding a second heading.
3. Delete the temporary download directory, if you made one.

## 5. Done

Tell the user:

- What you copied and what you left alone.
- That the next step for a feature is `/sdd:spec-discovery`, and for a bug report `/sdd:spec-generator`.
- That `/sdd:spec-workflow` describes the whole pipeline, and that implementation and branch close-out need the superpowers plugin or an equivalent.

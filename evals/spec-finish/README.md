# spec-finish eval pack

Each case makes a git repository in `./proj` with a base commit on `main`, then switches to a work-item branch. The fixtures have no remote, so no run can push or open a pull request, and every case that gets as far as `pr.md` ends by saying where `pr.md` is.

`runner-settings.json` also denies `gh`, `glab` and `git push`. A command such as `git -C ./proj push` does not match that deny rule, so the missing remote is the protection that holds. Do not add a remote to a fixture.

The prompts copy folders with `rsync -a`, not `cp -R`. From Claude Code 2.1.322, a `cp` command with a flag needs manual approval even when the allow list permits `cp`, and a headless run cannot give it. Do not allow `python3 -c` for a copy instead: a run could then call `gh` from Python.

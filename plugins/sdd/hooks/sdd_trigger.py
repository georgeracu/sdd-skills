#!/usr/bin/env python3
"""Start the sdd domain skills from file state, so that no pipeline skill names them.

Claude Code runs this script from hooks/hooks.json. It reads the hook
event as JSON on stdin and prints at most one JSON object on stdout.

PostToolUse (Write or Edit):
- discovery.md set to Approved, with a term marked new, and project/domain/
  present: tell the agent to run sdd:domain-define before sdd:spec-generator.
- spec-maintenance-report.md written and project/domain/ present: tell the
  agent to run sdd:domain-maintenance before sdd:spec-finish.
- a glossary.md or context-map.md under project/domain/ changed: check it
  against the formats and send any problem back as blocking feedback.
- a code file changed and project/domain/ present: point out avoided words
  in it, as non-blocking context.

Stop:
- a spec-maintenance-report.md written in this session has no
  domain-maintenance-report.md beside it: keep the agent going once.

Every rule does nothing when project/domain/ does not exist. Standard library
only. Any unexpected error exits 0 with no output, so a hook bug never blocks
the agent.
"""

from __future__ import annotations

import importlib.util
import json
import os
import re
import sys
import tempfile
from pathlib import Path

HERE = Path(__file__).resolve().parent
# The plugin root holds hooks/ and either the skill folders themselves (the
# original layout, plugin root = skills/) or a skills/ folder (Agent Plugins
# 1.0 layout, plugin root = plugins/sdd/).
TERM_SCAN = next(
    (
        candidate
        for candidate in (
            HERE.parent / "skills" / "domain-maintenance" / "scripts" / "term_scan.py",
            HERE.parent / "domain-maintenance" / "scripts" / "term_scan.py",
        )
        if candidate.exists()
    ),
    HERE.parent / "skills" / "domain-maintenance" / "scripts" / "term_scan.py",
)
CODE_EXT = {".java", ".kt", ".kts", ".scala", ".groovy", ".clj", ".py", ".ts",
            ".tsx", ".js", ".jsx", ".go", ".rs", ".cs"}
STATUSES = {"proposed", "agreed", "deprecated"}
MAX_HINTS = 5


def load_term_scan():
    spec = importlib.util.spec_from_file_location("sdd_term_scan", TERM_SCAN)
    module = importlib.util.module_from_spec(spec)
    sys.modules["sdd_term_scan"] = module
    spec.loader.exec_module(module)
    return module


def find_root(start: Path) -> Path | None:
    """The nearest directory at or above start that holds project/."""
    for candidate in [start, *start.parents]:
        if (candidate / "project").is_dir():
            return candidate
    return None


def state_file(session_id: str) -> Path:
    base = os.environ.get("CLAUDE_PLUGIN_DATA") or tempfile.gettempdir()
    safe = re.sub(r"[^A-Za-z0-9_-]", "_", session_id or "unknown")
    return Path(base) / "sdd-trigger" / f"{safe}.json"


def remember(session_id: str, path: Path) -> None:
    target = state_file(session_id)
    target.parent.mkdir(parents=True, exist_ok=True)
    seen = set(json.loads(target.read_text())) if target.exists() else set()
    seen.add(str(path))
    target.write_text(json.dumps(sorted(seen)))


def context(event: str, text: str) -> dict:
    return {"hookSpecificOutput": {"hookEventName": event, "additionalContext": text}}


def discovery_needs_domain_define(path: Path) -> bool:
    text = path.read_text(encoding="utf-8")
    if not re.search(r"^\*\*Status:\*\*\s*Approved", text, re.MULTILINE):
        return False
    terms = re.search(r"^## Terms\s*$(.*?)(?=^## |\Z)", text, re.MULTILINE | re.DOTALL)
    if not terms:
        return False
    return any(
        re.search(r"\bnew\.?\s*$", line, re.IGNORECASE)
        for line in terms.group(1).splitlines()
        if line.lstrip().startswith("-")
    )


def format_problems(path: Path, domain: Path) -> list[str]:
    """Problems with a glossary.md or context-map.md that break the formats."""
    problems: list[str] = []
    if path.name == "glossary.md":
        term_scan = load_term_scan()
        _, terms = term_scan.parse_glossary(path, domain)
        for term in terms:
            if term.status not in STATUSES:
                problems.append(
                    f"{term.name} (line {term.line}) has no **Status:** of proposed, agreed or deprecated"
                )
        for problem in term_scan.glossary_problems(terms):
            if problem["kind"] == "duplicate-term":
                problems.append(f"{problem['term']} is defined twice (lines {problem['lines']})")
            elif problem["kind"] == "alias-is-term":
                # Normal while a rename is in progress; sdd:domain-maintenance
                # decides with the CHANGELOG.md entry in view.
                continue
            else:
                problems.append(f"{problem['alias']} is avoided under two terms: {', '.join(problem['terms'])}")
    elif path.name == "context-map.md":
        text = path.read_text(encoding="utf-8")
        for heading, columns in (
            ("Contexts", "| Context | Purpose | Classification | Code location | Glossary |"),
            ("Relationships", "| Upstream | Downstream | Pattern | Messages | Status | Since |"),
        ):
            section = re.search(rf"^## {heading}\s*$(.*?)(?=^## |\Z)", text, re.MULTILINE | re.DOTALL)
            if not section:
                problems.append(f"the ## {heading} section is missing")
                continue
            header = next((l.strip() for l in section.group(1).splitlines() if l.strip().startswith("|")), "")
            if re.sub(r"\s+", " ", header) != columns:
                problems.append(f"the {heading} table header should be: {columns}")
    return problems


def avoided_words_in(path: Path, domain: Path) -> list[str]:
    term_scan = load_term_scan()
    result = term_scan.scan(domain, [path])
    hints = []
    for hit in result["alias_hits"]:
        if hit["kind"] not in ("code", "contract"):
            continue
        hints.append(
            f"line {hit['line']}: '{hit['alias']}' is an avoided word; use {' or '.join(hit['use_instead'])}"
        )
    return hints[:MAX_HINTS]


def on_post_tool_use(event: dict) -> dict | None:
    raw = (event.get("tool_input") or {}).get("file_path")
    if not raw:
        return None
    path = Path(raw).resolve()
    root = find_root(path.parent)
    if root is None:
        return None
    domain = root / "project" / "domain"
    if not domain.is_dir() or not path.exists():
        return None

    work_items = root / "project" / "work-items"
    if path.parent.parent == work_items:
        if path.name == "discovery.md" and discovery_needs_domain_define(path):
            return context(
                "PostToolUse",
                f"sdd: {path.parent.name}/discovery.md is approved and marks a term as new, and this project "
                "has a domain model in project/domain/. Run sdd:domain-define for this work item before "
                "sdd:spec-generator.",
            )
        if path.name == "spec-maintenance-report.md":
            remember(event.get("session_id", ""), path)
            return context(
                "PostToolUse",
                f"sdd: spec maintenance for {path.parent.name} is done and this project has a domain model "
                "in project/domain/. Run sdd:domain-maintenance for this work item before sdd:spec-finish.",
            )
        return None

    if domain in path.parents and path.name in ("glossary.md", "context-map.md"):
        problems = format_problems(path, domain)
        if problems:
            return {
                "decision": "block",
                "reason": f"sdd: {path.relative_to(root)} breaks the domain formats: "
                + "; ".join(problems)
                + ". Fix it as references/formats.md describes.",
            }
        return None

    if path.suffix.lower() in CODE_EXT:
        hints = avoided_words_in(path, domain)
        if hints:
            return context(
                "PostToolUse",
                f"sdd: {path.name} uses words the glossary avoids. " + "; ".join(hints)
                + ". Rename where this change added them; leave older code to sdd:domain-maintenance.",
            )
    return None


def on_stop(event: dict) -> dict | None:
    if event.get("stop_hook_active"):
        return None
    target = state_file(event.get("session_id", ""))
    if not target.exists():
        return None
    pending = []
    for raw in json.loads(target.read_text()):
        report = Path(raw)
        if report.exists() and not (report.parent / "domain-maintenance-report.md").exists():
            pending.append(report.parent.name)
    if not pending:
        return None
    return {
        "decision": "block",
        "reason": "sdd: spec maintenance finished for "
        + ", ".join(sorted(pending))
        + " but sdd:domain-maintenance has not run. Run it for each before sdd:spec-finish, "
        "or tell the user why it is being skipped.",
    }


def log_event(event: dict) -> None:
    """Append one line per call to $SDD_TRIGGER_LOG, if set: shows which clients run the hook."""
    target = os.environ.get("SDD_TRIGGER_LOG")
    if not target:
        return
    tool_input = event.get("tool_input") or {}
    with open(target, "a", encoding="utf-8") as fh:
        fh.write(json.dumps({
            "event": event.get("hook_event_name"),
            "tool": event.get("tool_name"),
            "file": tool_input.get("file_path"),
        }) + "\n")


def main() -> int:
    try:
        event = json.load(sys.stdin)
        log_event(event)
        name = event.get("hook_event_name")
        if name == "PostToolUse":
            output = on_post_tool_use(event)
        elif name == "Stop":
            output = on_stop(event)
        else:
            output = None
        if output:
            json.dump(output, sys.stdout)
    except Exception:  # a hook must never break the session
        return 0
    return 0


if __name__ == "__main__":
    sys.exit(main())

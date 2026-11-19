"""Tests for plugins/sdd/hooks/sdd_trigger.py, run the way Claude Code runs it: JSON on stdin.

Run from the repository root:
    python3 -m unittest discover -s tests/hooks
"""

import json
import os
import subprocess
import sys
import tempfile
import textwrap
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
from _layout import hooks_dir  # noqa: E402

SCRIPT = hooks_dir() / "sdd_trigger.py"
HOOKS_JSON = hooks_dir() / "hooks.json"

GLOSSARY = """
    # Glossary: Metering

    ## Terms

    ### Usage_Meter

    The running count of Metered_Events for an Account in the current Billing_Period.

    - **Avoid:** Counter, Usage Count
    - **Status:** agreed
    - **Since:** import
"""

DISCOVERY = """
    # Discovery: Weekly usage digest

    **Work item:** 04-usage-digest
    **Status:** {status}

    ## Terms

    - `Account_Owner`: the user who receives account-level notifications. Existing.
    - `Usage_Digest`: the weekly email summary of usage. {marker}

    ## Open questions

    None.
"""


def write(path: Path, *parts: str) -> Path:
    """Write the parts, each dedented on its own, one after another."""
    path.parent.mkdir(parents=True, exist_ok=True)
    text = "\n".join(textwrap.dedent(part).strip("\n") for part in parts) + "\n"
    path.write_text(text, encoding="utf-8")
    return path


class TriggerTest(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.root = Path(self.tmp.name).resolve()
        self.data = self.root / "plugin-data"
        self.work_item = self.root / "project/work-items/04-usage-digest"
        self.work_item.mkdir(parents=True)

    def tearDown(self):
        self.tmp.cleanup()

    def with_domain(self):
        write(self.root / "project/domain/metering/glossary.md", GLOSSARY)

    def run_hook(self, event: dict) -> dict | None:
        event.setdefault("session_id", "s1")
        event.setdefault("cwd", str(self.root))
        env = dict(os.environ, CLAUDE_PLUGIN_DATA=str(self.data))
        done = subprocess.run(
            [sys.executable, "-I", str(SCRIPT)], input=json.dumps(event),
            capture_output=True, text=True, env=env, timeout=30,
        )
        self.assertEqual(done.returncode, 0, done.stderr)
        return json.loads(done.stdout) if done.stdout.strip() else None

    def post(self, path: Path) -> dict | None:
        return self.run_hook({
            "hook_event_name": "PostToolUse", "tool_name": "Write",
            "tool_input": {"file_path": str(path)},
        })

    def context_of(self, output) -> str:
        self.assertIsNotNone(output)
        return output["hookSpecificOutput"]["additionalContext"]

    # discovery.md

    def test_approved_discovery_with_new_term_triggers_domain_define(self):
        self.with_domain()
        path = write(self.work_item / "discovery.md",
                     DISCOVERY.format(status="Approved 2026-11-19", marker="New."))
        self.assertIn("sdd:domain-define", self.context_of(self.post(path)))

    def test_draft_discovery_triggers_nothing(self):
        self.with_domain()
        path = write(self.work_item / "discovery.md", DISCOVERY.format(status="Draft", marker="New."))
        self.assertIsNone(self.post(path))

    def test_approved_discovery_with_only_existing_terms_triggers_nothing(self):
        self.with_domain()
        path = write(self.work_item / "discovery.md",
                     DISCOVERY.format(status="Approved 2026-11-19", marker="Existing."))
        self.assertIsNone(self.post(path))

    def test_nothing_happens_without_a_domain_model(self):
        path = write(self.work_item / "discovery.md",
                     DISCOVERY.format(status="Approved 2026-11-19", marker="New."))
        self.assertIsNone(self.post(path))

    # spec-maintenance-report.md and the Stop safety net

    def test_spec_maintenance_report_triggers_domain_maintenance(self):
        self.with_domain()
        path = write(self.work_item / "spec-maintenance-report.md", "**Status:** PASSED\n")
        self.assertIn("sdd:domain-maintenance", self.context_of(self.post(path)))

    def test_stop_blocks_once_until_domain_maintenance_has_run(self):
        self.with_domain()
        self.post(write(self.work_item / "spec-maintenance-report.md", "**Status:** PASSED\n"))

        blocked = self.run_hook({"hook_event_name": "Stop", "stop_hook_active": False})
        self.assertEqual(blocked["decision"], "block")
        self.assertIn("04-usage-digest", blocked["reason"])

        again = self.run_hook({"hook_event_name": "Stop", "stop_hook_active": True})
        self.assertIsNone(again)

        write(self.work_item / "domain-maintenance-report.md", "**Status:** PASSED\n")
        self.assertIsNone(self.run_hook({"hook_event_name": "Stop", "stop_hook_active": False}))

    def test_stop_ignores_other_sessions(self):
        self.with_domain()
        self.post(write(self.work_item / "spec-maintenance-report.md", "**Status:** PASSED\n"))
        other = self.run_hook({"hook_event_name": "Stop", "session_id": "s2", "stop_hook_active": False})
        self.assertIsNone(other)

    # domain files

    def test_broken_glossary_is_blocked_with_the_reason(self):
        self.with_domain()
        path = write(self.root / "project/domain/metering/glossary.md", GLOSSARY, """
            ### Usage_Meter

            Defined again.

            - **Status:** agred
        """)
        output = self.post(path)
        self.assertEqual(output["decision"], "block")
        self.assertIn("defined twice", output["reason"])
        self.assertIn("**Status:**", output["reason"])

    def test_rename_in_progress_is_not_blocked(self):
        self.with_domain()
        path = write(self.root / "project/domain/metering/glossary.md", GLOSSARY, """
            ### Usage_Tally

            The new name.

            - **Avoid:** Usage Meter
            - **Status:** proposed
            - **Since:** work-item 05
        """)
        self.assertIsNone(self.post(path))

    def test_context_map_with_wrong_columns_is_blocked(self):
        self.with_domain()
        path = write(self.root / "project/domain/context-map.md", """
            # Context Map

            ## Contexts

            | Context | Purpose |
            |---|---|
            | Metering | Counts usage |
        """)
        output = self.post(path)
        self.assertEqual(output["decision"], "block")
        self.assertIn("Relationships", output["reason"])

    # code

    def test_avoided_word_in_code_gives_a_hint(self):
        self.with_domain()
        path = write(self.root / "backend/src/main/kotlin/UsageCountReader.kt",
                     "class UsageCountReader\n")
        text = self.context_of(self.post(path))
        self.assertIn("Usage Count", text)
        self.assertIn("Usage_Meter", text)

    def test_clean_code_gives_nothing(self):
        self.with_domain()
        path = write(self.root / "backend/src/main/kotlin/UsageMeterReader.kt",
                     "class UsageMeterReader\n")
        self.assertIsNone(self.post(path))

    # robustness

    def test_garbage_input_exits_quietly(self):
        done = subprocess.run([sys.executable, "-I", str(SCRIPT)], input="not json",
                              capture_output=True, text=True, timeout=30)
        self.assertEqual((done.returncode, done.stdout), (0, ""))

    def test_log_records_each_call_when_asked(self):
        log = self.root / "trigger.log"
        env = dict(os.environ, CLAUDE_PLUGIN_DATA=str(self.data), SDD_TRIGGER_LOG=str(log))
        event = {"hook_event_name": "PostToolUse", "tool_name": "Write",
                 "tool_input": {"file_path": str(self.root / "x.kt")}, "session_id": "s1"}
        subprocess.run([sys.executable, "-I", str(SCRIPT)], input=json.dumps(event),
                       capture_output=True, text=True, env=env, timeout=30)
        self.assertEqual(json.loads(log.read_text())["event"], "PostToolUse")

    def test_unknown_event_shape_gives_no_output(self):
        self.with_domain()
        output = self.run_hook({"event": "postToolUse", "toolArgs": {"path": "x"}})
        self.assertIsNone(output)

    def test_hooks_json_points_at_the_script(self):
        config = json.loads(HOOKS_JSON.read_text())
        for event in ("PostToolUse", "Stop"):
            args = config["hooks"][event][0]["hooks"][0]["args"]
            self.assertEqual(args, ["${CLAUDE_PLUGIN_ROOT}/hooks/sdd_trigger.py"])


if __name__ == "__main__":
    unittest.main()

"""Find the plugin root, whichever repository layout is in use.

Original layout: the plugin root is skills/, holding the skill folders and hooks/.
Agent Plugins 1.0 layout: the plugin root is plugins/sdd/, holding plugin.json,
skills/<name>/ and hooks/.
"""

from pathlib import Path

REPO = Path(__file__).resolve().parents[1]


def plugin_root() -> Path:
    new = REPO / "plugins" / "sdd"
    return new if (new / "skills").is_dir() else REPO / "skills"


def skill_dir(name: str) -> Path:
    root = plugin_root()
    nested = root / "skills" / name
    return nested if nested.is_dir() else root / name


def hooks_dir() -> Path:
    return plugin_root() / "hooks"

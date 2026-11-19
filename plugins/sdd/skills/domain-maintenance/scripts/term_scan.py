#!/usr/bin/env python3
"""Scan specs, contracts and code for the terms in project/domain glossaries.

Reads every glossary.md under the domain directory (format: sdd:domain-define
references/formats.md) and reports, as JSON on stdout:

- glossary_problems: a heading defined twice in one glossary, a word that is
  both a heading and an Avoid alias in one glossary, or a word in two Avoid
  lists of one glossary
- alias_hits: every use of an Avoid alias in the scanned files, classified as
  spec, contract, code or code-comment
- term_hits: for each proposed or agreed term, how many lines use it in specs,
  contracts and code (zero everywhere marks a rot candidate)

Matching is on words, not raw text: "Usage Digest" matches UsageDigest,
usage_digest, usage-digest and "usage digests". Standard library only.

Usage:
    python3 term_scan.py --domain project/domain PATH [PATH ...]

Each PATH is a file or a directory. The domain directory itself is never
scanned. Exit code is 0 unless the arguments are wrong.
"""

from __future__ import annotations

import argparse
import json
import os
import re
import sys
from dataclasses import dataclass, field
from pathlib import Path

SKIP_DIRS = {
    ".git", ".gradle", ".idea", ".venv", "venv", "node_modules", "build",
    "target", "dist", "out", "__pycache__", ".next", ".terraform",
}
SPEC_EXT = {".md", ".markdown", ".adoc", ".txt", ".rst"}
CONTRACT_EXT = {".yaml", ".yml", ".json", ".avsc", ".proto", ".graphql", ".sql"}
CODE_EXT = {
    ".java", ".kt", ".kts", ".scala", ".groovy", ".clj", ".cljs", ".py",
    ".ts", ".tsx", ".js", ".jsx", ".go", ".rs", ".cs", ".rb", ".php",
    ".swift", ".tf", ".xml", ".properties",
}
COMMENT_PREFIXES = ("//", "#", "*", "/*", "--", "<!--", ";;", '"""', "'''")
STATUSES = {"proposed", "agreed", "deprecated"}

_TOKEN = re.compile(r"[A-Za-z0-9]+")
_CAMEL = re.compile(r"[A-Z]+(?=[A-Z][a-z]|\d|$)|[A-Z]?[a-z]+|[A-Z]+|\d+")


def words(text: str) -> list[str]:
    """Split text into lower-case words, breaking camelCase, snake_case and kebab-case."""
    out: list[str] = []
    for token in _TOKEN.findall(text):
        out.extend(part.lower() for part in _CAMEL.findall(token))
    return out


def _word_matches(pattern_word: str, word: str, last: bool) -> bool:
    if word == pattern_word:
        return True
    if not last:
        return False
    if word in (pattern_word + "s", pattern_word + "es"):
        return True
    return pattern_word.endswith("y") and word == pattern_word[:-1] + "ies"


def count_matches(pattern: list[str], line_words: list[str]) -> int:
    """Count occurrences of the word sequence in the line; the last word may be plural."""
    n = len(pattern)
    if n == 0 or len(line_words) < n:
        return 0
    hits = 0
    i = 0
    while i <= len(line_words) - n:
        if all(
            _word_matches(pattern[j], line_words[i + j], j == n - 1)
            for j in range(n)
        ):
            hits += 1
            i += n
        else:
            i += 1
    return hits


@dataclass
class Term:
    name: str
    context: str
    glossary: str
    line: int
    status: str = "unknown"
    avoid: list[str] = field(default_factory=list)


def parse_glossary(path: Path, domain_root: Path) -> tuple[str, list[Term]]:
    rel_parent = path.parent.relative_to(domain_root)
    context = "default" if str(rel_parent) == "." else rel_parent.parts[0]
    terms: list[Term] = []
    current: Term | None = None
    in_terms = True
    with path.open(encoding="utf-8") as fh:
        for number, raw in enumerate(fh, start=1):
            line = raw.rstrip("\n")
            if line.startswith("# Glossary:"):
                title = line.split(":", 1)[1].strip()
                if title:
                    context = title
                continue
            if line.startswith("## "):
                in_terms = line[3:].strip().lower() == "terms"
                current = None
                continue
            if line.startswith("### ") and in_terms:
                current = Term(
                    name=line[4:].strip(), context=context,
                    glossary=str(path), line=number,
                )
                terms.append(current)
                continue
            if current is None:
                continue
            label = re.match(r"^\s*-\s*\*\*(Avoid|Status):\*\*\s*(.*)$", line)
            if not label:
                continue
            key, value = label.group(1), label.group(2).strip()
            if key == "Avoid":
                current.avoid = [a.strip() for a in value.split(",") if a.strip()]
            else:
                first = value.split()[0].strip(",.").lower() if value else ""
                current.status = first if first in STATUSES else "unknown"
    return context, terms


def glossary_problems(terms: list[Term]) -> list[dict]:
    problems: list[dict] = []
    by_glossary: dict[str, list[Term]] = {}
    for term in terms:
        by_glossary.setdefault(term.glossary, []).append(term)
    for glossary, entries in by_glossary.items():
        headings: dict[str, Term] = {}
        for term in entries:
            key = " ".join(words(term.name))
            if key in headings:
                problems.append({
                    "kind": "duplicate-term", "glossary": glossary,
                    "term": term.name, "lines": [headings[key].line, term.line],
                })
            else:
                headings[key] = term
        # A deprecated entry keeps its heading so the decision stays readable;
        # its old name is expected in the replacement term's Avoid list.
        live_headings = {
            key: term for key, term in headings.items()
            if term.status != "deprecated"
        }
        avoided: dict[str, Term] = {}
        for term in entries:
            for alias in term.avoid:
                key = " ".join(words(alias))
                if key in live_headings:
                    problems.append({
                        "kind": "alias-is-term", "glossary": glossary,
                        "alias": alias, "term": term.name,
                        "heading_line": live_headings[key].line,
                    })
                if key in avoided and avoided[key] is not term:
                    problems.append({
                        "kind": "alias-in-two-terms", "glossary": glossary,
                        "alias": alias, "terms": [avoided[key].name, term.name],
                    })
                avoided.setdefault(key, term)
    return problems


def classify(path: Path, line: str) -> str | None:
    ext = path.suffix.lower()
    if ext in SPEC_EXT:
        return "spec"
    if ext in CONTRACT_EXT:
        return "contract"
    if ext in CODE_EXT:
        return "code-comment" if line.lstrip().startswith(COMMENT_PREFIXES) else "code"
    return None


def iter_files(paths: list[Path], domain_root: Path):
    domain_root = domain_root.resolve()
    seen: set[Path] = set()
    for base in paths:
        if base.is_file():
            candidates = [base]
        elif base.is_dir():
            candidates = []
            for root, dirs, files in os.walk(base):
                dirs[:] = [d for d in dirs if d not in SKIP_DIRS]
                candidates.extend(Path(root) / f for f in files)
        else:
            print(f"warning: {base} does not exist", file=sys.stderr)
            continue
        for candidate in candidates:
            resolved = candidate.resolve()
            if resolved in seen or domain_root in resolved.parents:
                continue
            seen.add(resolved)
            yield candidate


def scan(domain: Path, paths: list[Path]) -> dict:
    terms: list[Term] = []
    for glossary in sorted(domain.rglob("glossary.md")):
        _, parsed = parse_glossary(glossary, domain)
        terms.extend(parsed)

    live = [t for t in terms if t.status in ("proposed", "agreed")]
    term_patterns = [(t, words(t.name)) for t in live]
    alias_patterns = [
        (t, alias, words(alias)) for t in terms for alias in t.avoid
    ]
    counts = {
        id(t): {"spec": 0, "contract": 0, "code": 0, "code-comment": 0}
        for t in live
    }
    alias_hits: list[dict] = []
    alias_index: dict[tuple[str, str, int], dict] = {}
    files_scanned = 0

    for path in iter_files(paths, domain):
        if classify(path, "") is None:
            continue
        try:
            text = path.read_text(encoding="utf-8")
        except (UnicodeDecodeError, OSError):
            continue
        files_scanned += 1
        for number, line in enumerate(text.splitlines(), start=1):
            kind = classify(path, line)
            line_words = words(line)
            if not line_words:
                continue
            for term, pattern in term_patterns:
                found = count_matches(pattern, line_words)
                if found:
                    counts[id(term)][kind] += 1
            for term, alias, pattern in alias_patterns:
                if not count_matches(pattern, line_words):
                    continue
                key = (" ".join(pattern), str(path), number)
                if key in alias_index:
                    hit = alias_index[key]
                    suggestion = f"{term.name} ({term.context})"
                    if suggestion not in hit["use_instead"]:
                        hit["use_instead"].append(suggestion)
                    continue
                hit = {
                    "alias": alias,
                    "use_instead": [f"{term.name} ({term.context})"],
                    "file": str(path), "line": number, "kind": kind,
                    "text": line.strip()[:200],
                }
                alias_index[key] = hit
                alias_hits.append(hit)

    term_hits = []
    for term in live:
        c = counts[id(term)]
        term_hits.append({
            "term": term.name, "context": term.context,
            "status": term.status, "glossary": term.glossary,
            "spec": c["spec"], "contract": c["contract"],
            "code": c["code"] + c["code-comment"],
            "unused": sum(c.values()) == 0,
        })

    problems = glossary_problems(terms)
    return {
        "domain": str(domain),
        "glossaries": sorted({t.glossary for t in terms}),
        "terms_total": len(terms),
        "files_scanned": files_scanned,
        "glossary_problems": problems,
        "alias_hits": alias_hits,
        "term_hits": term_hits,
        "summary": {
            "glossary_problems": len(problems),
            "alias_hits_code_or_contract": sum(
                1 for h in alias_hits if h["kind"] in ("code", "contract")
            ),
            "alias_hits_spec": sum(1 for h in alias_hits if h["kind"] == "spec"),
            "alias_hits_comment": sum(
                1 for h in alias_hits if h["kind"] == "code-comment"
            ),
            "unused_terms": sum(1 for t in term_hits if t["unused"]),
        },
    }


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("--domain", default="project/domain", type=Path)
    parser.add_argument("paths", nargs="+", type=Path)
    args = parser.parse_args(argv)
    if not args.domain.is_dir():
        print(f"error: {args.domain} is not a directory", file=sys.stderr)
        return 2
    json.dump(scan(args.domain, args.paths), sys.stdout, indent=2)
    sys.stdout.write("\n")
    return 0


if __name__ == "__main__":
    sys.exit(main())

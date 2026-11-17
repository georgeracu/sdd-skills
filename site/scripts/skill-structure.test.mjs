import assert from 'node:assert/strict';
import { mkdirSync, mkdtempSync, rmSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { after, test } from 'node:test';
import { checkSkills } from './skill-structure.mjs';

const roots = [];
function repoWith(skills) {
  const repo = mkdtempSync(join(tmpdir(), 'skill-structure-'));
  roots.push(repo);
  mkdirSync(join(repo, 'skills/.claude-plugin'), { recursive: true });
  for (const [name, skillMd] of Object.entries(skills)) {
    mkdirSync(join(repo, 'skills', name));
    if (skillMd !== null) writeFileSync(join(repo, 'skills', name, 'SKILL.md'), skillMd);
  }
  return repo;
}
const skillMd = (kind) => `---\nname: demo\nmetadata:\n  sdd-kind: ${kind}\n---\n\n# Demo\n`;
const problemsOf = (skills, entries = Object.keys(skills)) => checkSkills(repoWith(skills), entries).problems;

after(() => roots.forEach((r) => rmSync(r, { recursive: true, force: true })));

test('a well-formed skill passes', () => {
  assert.deepEqual(problemsOf({ demo: skillMd('model-invoked') }), []);
});

test('a folder with no SKILL.md is named', () => {
  assert.deepEqual(problemsOf({ demo: null }), ['skills/demo/ has no SKILL.md', 'site/skills.yaml entry "demo" has no skills/demo/SKILL.md']);
});

test('a SKILL.md without front matter is named', () => {
  assert.deepEqual(problemsOf({ demo: '# Demo\n' }), ['skills/demo/SKILL.md: front matter is missing or is not valid YAML']);
});

test('front matter that is not valid YAML is named', () => {
  assert.deepEqual(problemsOf({ demo: '---\nname: [unclosed\n---\n' }), ['skills/demo/SKILL.md: front matter is missing or is not valid YAML']);
});

const kindProblem = 'skills/demo/SKILL.md: metadata.sdd-kind must be one of user-invoked, model-invoked, reference';

test('a missing metadata.sdd-kind is named', () => {
  assert.deepEqual(problemsOf({ demo: '---\nname: demo\n---\n' }), [kindProblem]);
});

test('an invalid metadata.sdd-kind is named', () => {
  assert.deepEqual(problemsOf({ demo: skillMd('background') }), [kindProblem]);
});

test('a skill with no entry in site/skills.yaml is named', () => {
  assert.deepEqual(problemsOf({ demo: skillMd('reference') }, []), ['skills/demo/ has no entry in site/skills.yaml']);
});

test('an entry with no skill folder is named', () => {
  assert.deepEqual(problemsOf({}, ['ghost']), ['site/skills.yaml entry "ghost" has no skills/ghost/SKILL.md']);
});

test('a user-invoked skill must disable model invocation and ship agents/openai.yaml', () => {
  assert.deepEqual(problemsOf({ demo: skillMd('user-invoked') }), [
    'skills/demo/SKILL.md: disable-model-invocation: true must be set exactly when the kind is user-invoked',
    'skills/demo/agents/openai.yaml must exist exactly when the kind is user-invoked',
  ]);
});

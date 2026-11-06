import { execFileSync } from 'node:child_process';
import { existsSync, readdirSync, readFileSync, writeFileSync } from 'node:fs';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';
import { parse, stringify } from 'yaml';

const site = join(dirname(fileURLToPath(import.meta.url)), '..');
const repo = join(site, '..');
const base = '/sdd-skills';
const github = 'https://github.com/georgeracu/sdd-skills';

const git = (...args) => execFileSync('git', args, { cwd: repo, encoding: 'utf8' }).trim();
// A shallow clone gives every file the date of the one fetched commit.
if (git('rev-parse', '--is-shallow-repository') === 'true') {
  console.error('sync-evals: the clone is shallow, so iteration dates would be wrong. Fetch the full history.');
  process.exit(1);
}

const config = parse(readFileSync(join(site, 'skills.yaml'), 'utf8'));
const names = config.groups.flatMap((group) =>
  Object.entries(config.skills)
    .filter(([, s]) => s.group === group)
    .sort(([, a], [, b]) => a.order - b.order)
    .map(([name]) => name),
);

const arms = { without_skill: 'no skill', old_skill: 'previous version' };
const against = { without_skill: 'Without the skill', old_skill: 'With the previous version' };
const pct = (x) => `${(x * 100).toFixed(1)}%`;
const points = (x) => `${x >= 0 ? '+' : '−'}${Math.abs(x * 100).toFixed(1)} points`;
const escape = (text) => text.replace(/</g, '&lt;').replace(/\|/g, '\\|');
const longDate = (d) => new Date(d).toLocaleDateString('en-GB', { day: 'numeric', month: 'long', year: 'numeric', timeZone: 'UTC' });

function latest(name) {
  const dir = join(repo, 'skills', name, 'evals', 'results');
  if (!existsSync(dir)) return null;
  const files = readdirSync(dir).filter((f) => /^iteration-\d+\.json$/.test(f));
  if (!files.length) return null;
  const file = files.sort((a, b) => parseInt(a.slice(10)) - parseInt(b.slice(10))).at(-1);
  const json = JSON.parse(readFileSync(join(dir, file), 'utf8'));
  const mdPath = join(dir, file.replace(/\.json$/, '.md'));
  const md = readFileSync(mdPath, 'utf8');
  const field = (key) => md.match(new RegExp(`^\\| ${key} \\| (.+?) \\|$`, 'm'))?.[1];
  const spread = md.match(/Run-to-run spread[^:]*: pass_rate ±([\d.]+)/)?.[1];
  const tests = md.match(/^\*\*What this iteration tests:\*\* (.+)$/m)?.[1];
  const rel = `skills/${name}/evals/results/${file.replace(/\.json$/, '.md')}`;
  const problems = [
    ['runner_model', field('runner_model')],
    ['judge_model', field('judge_model')],
    ['runs_per_arm', field('runs_per_arm')],
    ['cases_total', field('cases_total')],
    ['run-to-run spread', spread],
  ].filter(([, v]) => v === undefined);
  if (problems.length) {
    console.error(`sync-evals: ${rel} has no ${problems.map(([k]) => k).join(', ')}`);
    process.exit(1);
  }
  return {
    json,
    rel,
    tests,
    spread: Number(spread),
    runner: field('runner_model'),
    judge: field('judge_model'),
    runs: Number(field('runs_per_arm')),
    cases: Number(field('cases_total')),
    date: git('log', '-1', '--format=%cs', '--', rel),
  };
}

function regressions({ json }) {
  const out = [];
  for (const [slug, byArm] of Object.entries(json.cases)) {
    const skill = byArm.with_skill?.checks ?? [];
    const baseline = byArm[json.baseline]?.checks ?? [];
    for (const check of skill) {
      const other = baseline.find((c) => c.text === check.text && c.occurrence === check.occurrence);
      if (other && check.passed / check.total < other.passed / other.total) {
        out.push(`- \`${slug}\`: ${escape(check.text)} (${check.passed}/${check.total} against ${other.passed}/${other.total})`);
      }
    }
  }
  return out;
}

const rows = [];
const sections = [];
const spreads = [];
for (const name of names) {
  const r = latest(name);
  const issues = `${github}/issues?q=${encodeURIComponent(`is:issue is:open ${name} eval`)}`;
  if (!r) {
    rows.push(`| [${name}](#${name}) | No eval pack yet | | | | |`);
    sections.push(`## ${name}\n\nNo eval pack yet.\n`);
    continue;
  }
  const s = r.json.run_summary;
  const withSkill = s.with_skill.pass_rate.mean;
  const baseline = s[r.json.baseline].pass_rate.mean;
  const delta = withSkill - baseline;
  spreads.push(r.spread);
  const iteration = r.json.iteration.replace('iteration-', '');
  rows.push(
    `| [${name}](#${name}) | ${arms[r.json.baseline] ?? r.json.baseline} | ${pct(withSkill)} | ${pct(baseline)} | ${points(delta)} | ±${(r.spread * 100).toFixed(1)} points |`,
  );
  const within = Math.abs(delta) <= r.spread;
  const lost = regressions(r);
  sections.push(
    [
      `## ${name}`,
      '',
      `Iteration ${iteration}, committed ${longDate(r.date)}. ${r.cases} cases, ${r.runs} runs an arm. Runner model \`${r.runner}\`, judge model \`${r.judge}\`.`,
      ...(r.tests ? ['', `What this iteration tests: ${escape(r.tests)}`] : []),
      '',
      `With the skill, ${pct(withSkill)} of checks passed. ${against[r.json.baseline] ?? r.json.baseline}, ${pct(baseline)} passed, a difference of ${points(delta)}. The run-to-run spread in this iteration was ±${(r.spread * 100).toFixed(1)} points, so the difference is ${within ? 'within the spread and is not a signal on its own' : 'larger than the spread'}.`,
      '',
      lost.length ? `Checks where the skill arm scored below the baseline:\n\n${lost.join('\n')}` : 'No check scored below the baseline.',
      '',
      `[Full results](${github}/blob/main/${r.rel}) · [Open issues about this skill's evals](${issues}) · [Skill page](${base}/skills/${name}/)`,
      '',
    ].join('\n'),
  );
}

const range = spreads.length ? `±${(Math.min(...spreads) * 100).toFixed(1)} to ±${(Math.max(...spreads) * 100).toFixed(1)} points` : 'not measured';
const front = stringify({
  title: 'Evals',
  description: 'How each skill scores against a baseline run, from the results committed in the repository.',
  editUrl: false,
});
const page = `---
${front}---

Each skill with an eval pack is measured by running the same cases with the skill and without it, then grading both sets of answers against the same checks. This page shows the latest iteration for each skill. It is generated from the results files in the repository, so it changes only when new results are committed.

## How to read it

- **Arm.** One configuration of a run. The skill arm has the skill installed. The baseline arm does not.
- **Baseline.** Either no skill at all, or the previous version of the same skill. A previous-version baseline measures whether a change helped. It does not measure the skill against having nothing.
- **Pass rate.** The share of checks that passed, across every case and every run in the arm. A blind judge model grades the checks that need judgement.
- **Run-to-run spread.** The variation seen when the same arm runs the same content more than once. In the latest iterations it ranges from ${range}. A difference inside the spread is not a signal on its own.

## Latest iteration for each skill

| Skill | Compared with | With skill | Baseline | Difference | Spread |
|---|---|---|---|---|---|
${rows.join('\n')}

${sections.join('\n')}`;

writeFileSync(join(site, 'src/content/docs/evals.md'), page);
console.log(`sync-evals: wrote the Evals page for ${names.length} skills`);

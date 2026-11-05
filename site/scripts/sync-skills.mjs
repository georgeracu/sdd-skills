import { mkdirSync, readdirSync, readFileSync, rmSync, writeFileSync, existsSync } from 'node:fs';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';
import { parse, stringify } from 'yaml';

const site = join(dirname(fileURLToPath(import.meta.url)), '..');
const repo = join(site, '..');
const docs = join(site, 'src/content/docs');
const base = '/sdd-skills';
const github = 'https://github.com/georgeracu/sdd-skills/blob/main';
const exampleDir = 'examples/38-source-fidelity-and-accuracy-sla';

const config = parse(readFileSync(join(site, 'skills.yaml'), 'utf8'));

const folders = readdirSync(join(repo, 'skills'), { withFileTypes: true })
  .filter((d) => d.isDirectory() && existsSync(join(repo, 'skills', d.name, 'SKILL.md')))
  .map((d) => d.name);
const entries = Object.keys(config.skills);
const problems = [
  ...folders.filter((f) => !entries.includes(f)).map((f) => `skills/${f}/ has no entry in site/skills.yaml`),
  ...entries.filter((e) => !folders.includes(e)).map((e) => `site/skills.yaml entry "${e}" has no skills/${e}/SKILL.md`),
];
for (const [name, entry] of Object.entries(config.skills)) {
  if (!config.groups.includes(entry.group)) problems.push(`${name}: group "${entry.group}" is not in groups`);
  for (const file of entry.example) {
    if (!existsSync(join(repo, exampleDir, file))) problems.push(`${name}: ${exampleDir}/${file} does not exist`);
  }
}
if (problems.length) {
  console.error(problems.map((p) => `sync-skills: ${p}`).join('\n'));
  process.exit(1);
}

const skillLink = (name) => `${base}/skills/${name}/`;
const linkSkills = (text) => text.replace(/`sdd:([a-z-]+)`/g, (m, n) => (folders.includes(n) ? `[${m}](${skillLink(n)})` : m));

// SKILL.md bodies are written for agents, so a bare <placeholder> outside code would be read as an HTML tag.
function escapeOutsideCode(markdown) {
  return markdown
    .split(/(^```[\s\S]*?^```)/m)
    .map((part, i) => (i % 2 ? part : part.split(/(`[^`\n]*`)/).map((s, j) => (j % 2 ? s : s.replace(/</g, '&lt;'))).join('')))
    .join('');
}

function splitFrontMatter(text) {
  const match = text.match(/^---\n([\s\S]*?)\n---\n?([\s\S]*)$/);
  return { data: parse(match[1]), body: match[2] };
}

function pipelineRows() {
  const workflow = readFileSync(join(repo, 'skills/spec-workflow/SKILL.md'), 'utf8');
  const rows = [];
  for (const [heading, pipeline] of [['## Feature Pipeline', 'Feature'], ['## Bugfix Pipeline', 'Bugfix']]) {
    const section = workflow.split(heading)[1].split('\n## ')[0];
    for (const line of section.split('\n').filter((l) => /^\|\s*\d+\s*\|/.test(l))) {
      const [step, skill, output, next] = line.split('|').slice(1, -1).map((c) => c.trim());
      const name = skill.match(/`sdd:([a-z-]+)`/)?.[1];
      rows.push({ name, pipeline, step, skill: skill.replace(/`/g, ''), mode: skill.replace(/`sdd:[a-z-]+`/, '').trim(), output, next });
    }
  }
  return rows;
}

const longDate = (d) => new Date(d).toLocaleDateString('en-GB', { day: 'numeric', month: 'long', year: 'numeric', timeZone: 'UTC' });

function header(name, entry, rows) {
  const mine = rows.filter((r) => r.name === name);
  if (!mine.length) return `:::note[Where it fits]\n${entry.position}\n:::\n`;
  const table = mine.map((r) => `| ${r.pipeline} | ${r.step} | ${r.mode} | ${linkSkills(r.output)} | ${linkSkills(r.next)} |`);
  return [
    '| Pipeline | Step | Mode | Writes | Runs next |',
    '|---|---|---|---|---|',
    ...table,
    '',
    `Steps and outputs come from [\`sdd:spec-workflow\`](${skillLink('spec-workflow')}).`,
    '',
  ].join('\n');
}

function footer(name, entry) {
  const story = entry.post
    ? `[Read the post on Substack](${entry.post.url}), published ${longDate(entry.post.date)}. The post describes the skill as it was then, and this page shows it as it is now.`
    : 'No post yet.';
  const files = entry.example.map((f) => `- [\`${f}\`](${github}/${exampleDir}/${f})`);
  const example = [...files, ...(entry.example_note ? ['', entry.example_note] : []), '', `The [worked example](${base}/worked-example/) walks through every artefact.`];
  return [
    '## The story behind this skill',
    '',
    story,
    '',
    '## See it in the worked example',
    '',
    ...example,
    '',
    '## Source',
    '',
    `[View SKILL.md on GitHub](${github}/skills/${name}/SKILL.md)`,
    '',
  ].join('\n');
}

const allRows = pipelineRows();
const rows = allRows.filter((r) => r.name);
mkdirSync(join(site, 'src/data'), { recursive: true });
writeFileSync(
  join(site, 'src/data/pipeline.json'),
  JSON.stringify(allRows.filter((r) => r.pipeline === 'Feature').map((r) => ({ ...r, gate: r.mode.includes('(gate)') })), null, 2),
);
const skillsOut = join(docs, 'skills');
rmSync(skillsOut, { recursive: true, force: true });
mkdirSync(skillsOut, { recursive: true });

for (const [name, entry] of Object.entries(config.skills)) {
  const { data, body } = splitFrontMatter(readFileSync(join(repo, 'skills', name, 'SKILL.md'), 'utf8'));
  const content = escapeOutsideCode(body.replace(/^\s*# .*\n/, ''))
    .replace(/\]\((?!https?:|#|\/)([^)]+)\)/g, `](${github}/skills/${name}/$1)`);
  const front = stringify({
    title: data.name,
    description: data.description,
    editUrl: `https://github.com/georgeracu/sdd-skills/edit/main/skills/${name}/SKILL.md`,
  });
  writeFileSync(
    join(skillsOut, `${name}.md`),
    `---\n${front}---\n\n${header(name, entry, rows)}\n${content.trim()}\n\n---\n\n${footer(name, entry)}`,
  );
}

for (const [file, title] of [['CHANGELOG.md', 'Changelog'], ['ROADMAP.md', 'Roadmap']]) {
  const text = readFileSync(join(repo, file), 'utf8').replace(/^# .*\n/, '');
  const front = stringify({ title, editUrl: `https://github.com/georgeracu/sdd-skills/edit/main/${file}` });
  writeFileSync(join(docs, `${title.toLowerCase()}.md`), `---\n${front}---\n${escapeOutsideCode(text)}`);
}

console.log(`sync-skills: wrote ${entries.length} skill pages, changelog and roadmap`);

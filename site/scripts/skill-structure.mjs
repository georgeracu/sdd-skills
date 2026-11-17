import { existsSync, readdirSync, readFileSync } from 'node:fs';
import { join } from 'node:path';
import { parse } from 'yaml';

export const kinds = {
  'user-invoked': 'You start it with `/sdd:NAME`. The agent does not start it on its own.',
  'model-invoked': 'The agent starts it when the task or the pipeline calls for it. You can also start it with `/sdd:NAME`.',
  reference: 'The agent reads it for the pipeline order and its rules. It does no work of its own.',
};

export function splitFrontMatter(text) {
  const match = text.match(/^---\n([\s\S]*?)\n---\n?([\s\S]*)$/);
  return { data: parse(match[1]), body: match[2] };
}

export function checkSkills(repo, entries) {
  const dirs = readdirSync(join(repo, 'skills'), { withFileTypes: true })
    .filter((d) => d.isDirectory() && !d.name.startsWith('.'))
    .map((d) => d.name);
  const folders = dirs.filter((d) => existsSync(join(repo, 'skills', d, 'SKILL.md')));
  const problems = [
    ...dirs.filter((d) => !folders.includes(d)).map((d) => `skills/${d}/ has no SKILL.md`),
    ...folders.filter((f) => !entries.includes(f)).map((f) => `skills/${f}/ has no entry in site/skills.yaml`),
    ...entries.filter((e) => !folders.includes(e)).map((e) => `site/skills.yaml entry "${e}" has no skills/${e}/SKILL.md`),
  ];
  const kindOf = {};
  for (const name of folders) {
    let data;
    try {
      data = splitFrontMatter(readFileSync(join(repo, 'skills', name, 'SKILL.md'), 'utf8')).data;
    } catch {}
    if (!data || typeof data !== 'object') {
      problems.push(`skills/${name}/SKILL.md: front matter is missing or is not valid YAML`);
      continue;
    }
    const kind = data.metadata?.['sdd-kind'];
    if (!kinds[kind]) problems.push(`skills/${name}/SKILL.md: metadata.sdd-kind must be one of ${Object.keys(kinds).join(', ')}`);
    const userOnly = kind === 'user-invoked';
    if ((data['disable-model-invocation'] === true) !== userOnly)
      problems.push(`skills/${name}/SKILL.md: disable-model-invocation: true must be set exactly when the kind is user-invoked`);
    if (existsSync(join(repo, 'skills', name, 'agents/openai.yaml')) !== userOnly)
      problems.push(`skills/${name}/agents/openai.yaml must exist exactly when the kind is user-invoked`);
    kindOf[name] = kind;
  }
  return { folders, kindOf, problems };
}

import { readFileSync } from 'node:fs';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';
import { parse } from 'yaml';
import { checkSkills } from './skill-structure.mjs';

const repo = join(dirname(fileURLToPath(import.meta.url)), '../..');
const config = parse(readFileSync(join(repo, 'site/skills.yaml'), 'utf8'));
const { folders, problems } = checkSkills(repo, Object.keys(config.skills));
if (problems.length) {
  console.error(problems.map((p) => `check-skills: ${p}`).join('\n'));
  process.exit(1);
}
console.log(`check-skills: ${folders.length} skills pass`);

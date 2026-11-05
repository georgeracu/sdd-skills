import { readFileSync } from 'node:fs';
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';
import starlightLinksValidator from 'starlight-links-validator';
import lucode from 'lucode-starlight';
import { parse } from 'yaml';

const { groups, skills } = parse(readFileSync(new URL('./skills.yaml', import.meta.url), 'utf8'));
const skillGroups = groups.map((label) => ({
  label,
  items: Object.entries(skills)
    .filter(([, s]) => s.group === label)
    .sort(([, a], [, b]) => a.order - b.order)
    .map(([name]) => ({ slug: `skills/${name}` })),
}));

export default defineConfig({
  site: 'https://georgeracu.github.io',
  base: '/sdd-skills',
  integrations: [
    starlight({
      title: 'SDD Skills',
      description: 'Spec-driven development for coding agents, with quality gates that block and an audit trail that stays.',
      locales: { root: { label: 'English', lang: 'en-GB' } },
      social: [{ icon: 'github', label: 'GitHub', href: 'https://github.com/georgeracu/sdd-skills' }],
      editLink: { baseUrl: 'https://github.com/georgeracu/sdd-skills/edit/main/site/' },
      head: [
        { tag: 'link', attrs: { rel: 'alternate', type: 'application/rss+xml', title: 'SDD Skills changelog', href: '/sdd-skills/changelog.xml' } },
      ],
      plugins: [lucode(), starlightLinksValidator()],
      sidebar: [
        { label: 'Guides', items: [{ slug: 'getting-started' }, { slug: 'worked-example' }] },
        ...skillGroups,
        { label: 'Project', items: [{ slug: 'changelog' }, { slug: 'roadmap' }] },
      ],
    }),
  ],
});

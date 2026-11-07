import { readFileSync } from 'node:fs';
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';
import starlightLinksValidator from 'starlight-links-validator';
import lucode from 'lucode-starlight';
import { parse } from 'yaml';

const { groups, skills } = parse(readFileSync(new URL('./skills.yaml', import.meta.url), 'utf8'));
const variants = { 'user-invoked': 'caution', 'model-invoked': 'note', reference: 'default' };
const badge = (name) => {
  const skill = readFileSync(new URL(`../skills/${name}/SKILL.md`, import.meta.url), 'utf8');
  const kind = parse(skill.split('\n---\n')[0].replace(/^---\n/, '')).metadata['sdd-kind'];
  return { text: kind, variant: variants[kind] };
};
const skillGroups = groups.map((label) => ({
  label,
  items: Object.entries(skills)
    .filter(([, s]) => s.group === label)
    .sort(([, a], [, b]) => a.order - b.order)
    .map(([name]) => ({ slug: `skills/${name}`, badge: badge(name) })),
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
        { tag: 'meta', attrs: { property: 'og:image', content: 'https://georgeracu.github.io/sdd-skills/social-preview.png' } },
        { tag: 'meta', attrs: { property: 'og:image:alt', content: 'SDD Skills: the twelve pipeline steps, with the design review, task review and implementation audit marked as gates.' } },
      ],
      plugins: [
        lucode({
          footerText:
            'Built with [Starlight](https://starlight.astro.build/) and the [Lucode theme](https://lucas-labs.github.io/lucode-starlight-theme/) by [lucas-labs](https://github.com/lucas-labs), which is based on [starlight-theme-black](https://github.com/adrian-ub/starlight-theme-black) and inspired by the [shadcn/ui](https://ui.shadcn.com/) documentation theme.',
        }),
        starlightLinksValidator(),
      ],
      sidebar: [
        { label: 'Guides', items: [{ slug: 'getting-started' }, { slug: 'worked-example' }] },
        ...skillGroups,
        { label: 'Project', items: [{ slug: 'evals' }, { slug: 'changelog' }, { slug: 'roadmap' }] },
      ],
    }),
  ],
});

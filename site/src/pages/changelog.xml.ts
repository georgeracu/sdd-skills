import rss from '@astrojs/rss';
import type { APIContext } from 'astro';
import changelog from '../../../CHANGELOG.md?raw';

export function GET(context: APIContext) {
  const page = new URL(`${import.meta.env.BASE_URL.replace(/\/$/, '')}/changelog/`, context.site).href;
  const items = [...changelog.matchAll(/^## \[(\d+\.\d+\.\d+)\] - (\d{4}-\d{2}-\d{2})\n([\s\S]*?)(?=^## |^\[)/gm)].map(
    ([, version, date, body]) => ({
      title: `v${version}`,
      pubDate: new Date(date),
      link: `${page}#${version.replace(/\./g, '')}---${date}`,
      description: body.trim().replace(/`/g, ''),
    }),
  );
  return rss({ title: 'SDD Skills changelog', description: 'Releases of the SDD skills.', site: page, items });
}

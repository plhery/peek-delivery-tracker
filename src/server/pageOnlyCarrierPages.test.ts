import { renderToStaticMarkup } from 'react-dom/server';
import { afterEach, describe, expect, it, vi } from 'vitest';
import en from '../../shared/locales/en.json';

const request = vi.hoisted(() => ({ host: 'peek.example', page: null as string | null }));
vi.mock('next/headers', () => ({
  cookies: async () => ({ get: () => undefined }),
  headers: async () => new Headers({ host: request.host, 'x-forwarded-proto': 'https', ...request.page ? { 'x-page-language': request.page } : {} }),
}));
// Outside a request there is nothing to wait for.
vi.mock('next/server', async (original) => ({ ...await original<typeof import('next/server')>(), connection: async () => undefined }));

/**
 * No carrier is written in a language with pages alone yet: the list gains a Finnish page for the first
 * carrier written in English and another language, and its file is this one. It links another carrier,
 * which has no Finnish page, a guide and the tracker.
 */
const fixture = vi.hoisted(() => ({ id: '', neighbour: '', guide: '' }));
const page = () => [
  '---', 'title: Lähetysten seuranta', 'description: Mistä lähetyksen numero löytyy, mitä seurannan tilat tarkoittavat ja mitä tehdä, kun lähetys ei liiku.',
  'slug: lahetysten-seuranta', 'published: 2026-10-22', 'updated: 2026-10-22', '---', '',
  `Johdanto: [toinen kuljetusyhtiö](carrier:${fixture.neighbour}), [ohje](guide:${fixture.guide}) ja [Peek](/).`, '',
  '## Numerot', '', 'Kappale.', '',
  '## Kysymykset', '', '### Missä lähetys on?', '', 'Matkalla.', '', '### Kuka sen tuo?', '', 'Posti.', '', '### Mistä sen noutaa?', '', 'Noutopisteestä.', '',
  ':::sources', '- [Lähde](https://source.example/)', ':::', '',
].join('\n');

vi.mock('../generated/carriers', async (original) => {
  const { CARRIER_LINKS: links } = await original<typeof import('../generated/carriers')>();
  const host = links.en.find(({ id }) => Object.entries(links).some(([language, written]) => language !== 'en' && written.some((link) => link.id === id)))!;
  fixture.id = host.id;
  fixture.neighbour = links.en.find(({ id }) => id !== host.id)!.id;
  return { CARRIER_LINKS: { ...links, fi: [{ ...host, slug: 'lahetysten-seuranta', title: 'Lähetysten seuranta' }] } };
});
vi.mock('node:fs', async (original) => {
  const fs = await original<typeof import('node:fs')>();
  const readFileSync = ((file: Parameters<typeof fs.readFileSync>[0], ...rest: [never]) =>
    (String(file).endsWith(`/content/carriers/${fixture.id}/fi.md`) ? page() : fs.readFileSync(file, ...rest))) as typeof fs.readFileSync;
  return { ...fs, default: { ...fs, readFileSync }, readFileSync };
});

const { CARRIER_LINKS } = await import('../generated/carriers');
const { GUIDE_LINKS } = await import('../generated/guides');
fixture.guide = GUIDE_LINKS.en[0].id;
const { carrierAddresses, carrierAlternates, carrierLanguages, carrierText } = await import('./carrierTexts');
const { CarrierRoute, carrierMetadata } = await import('./carrierPages');
const { default: sitemap } = await import('../../app/sitemap');
const { default: RootLayout } = await import('../../app/layout');

afterEach(() => {
  vi.unstubAllEnvs();
  request.page = null;
});

const finnish = CARRIER_LINKS.fi[0];
const english = CARRIER_LINKS.en.find(({ id }) => id === fixture.id)!;
const neighbour = CARRIER_LINKS.en.find(({ id }) => id === fixture.neighbour)!;
const url = 'https://peek.example/fi/carriers/lahetysten-seuranta';
const graph = (html: string) => JSON.parse(/<script type="application\/ld\+json">(.*?)<\/script>/.exec(html)![1])['@graph'];

describe('a carrier’s page in a language with pages alone', () => {
  it('is one of the carrier’s languages, after the site’s own, and has an address only where it is written', () => {
    expect(carrierLanguages(fixture.id).at(-1)).toBe('fi');
    expect(carrierText('fi', fixture.id).title).toBe('Lähetysten seuranta');
    expect(carrierAddresses(fixture.id).fi).toBe('/fi/carriers/lahetysten-seuranta');
    expect(carrierAddresses(fixture.id).en).toBe(`/carriers/${english.slug}`);
    // It has no carriers' own page, and another carrier has no Finnish page.
    expect(carrierAddresses()).not.toHaveProperty('fi');
    expect(carrierAddresses(fixture.neighbour)).not.toHaveProperty('fi');
    expect(carrierAlternates(new URL('https://peek.example'), fixture.id)).toMatchObject({ fi: url, en: `https://peek.example/carriers/${english.slug}`, 'x-default': `https://peek.example/carriers/${english.slug}` });
  });

  it('tells search engines its own address, its other languages and its locale, with Peek’s English picture', async () => {
    const metadata = await carrierMetadata('fi', finnish.slug);
    expect(metadata.title).toBe('Lähetysten seuranta — Peek');
    expect(metadata.alternates).toMatchObject({ canonical: url, languages: { fi: url, en: `https://peek.example/carriers/${english.slug}` } });
    expect(metadata.openGraph).toMatchObject({
      type: 'article', url, locale: 'fi_FI',
      images: [{ url: expect.stringMatching(/^https:\/\/peek\.example\/og\.png\?v=/) }],
    });
    // A language it is not written in has no page, nor a slug it does not have.
    expect(await carrierMetadata('sv', finnish.slug)).toEqual({});
    await expect(CarrierRoute({ locale: 'sv', slug: finnish.slug })).rejects.toThrow(/NEXT_HTTP_ERROR_FALLBACK;404/);
    await expect(CarrierRoute({ locale: 'fi', slug: english.slug })).rejects.toThrow(/NEXT_HTTP_ERROR_FALLBACK;404/);
  });

  it('declares its language for the whole document, and English for the frame around its text', async () => {
    // The proxy names the page's language on its request.
    request.page = 'fi';
    expect((await RootLayout({ children: null })).props.lang).toBe('fi');
    request.page = null;
    expect((await RootLayout({ children: null })).props.lang).toBe('en');
    expect(renderToStaticMarkup(await CarrierRoute({ locale: 'fi', slug: finnish.slug }))).toContain('<div class="guide-page" lang="en">');
    // A page in one of the site's languages is that language throughout.
    expect(renderToStaticMarkup(await CarrierRoute({ locale: 'en', slug: english.slug }))).toContain('<div class="guide-page">');
  });

  it('writes its title and text in its language, and everything around them in English', async () => {
    const html = renderToStaticMarkup(await CarrierRoute({ locale: 'fi', slug: finnish.slug }));
    expect(html).toContain('<h1 lang="fi">Lähetysten seuranta</h1>');
    expect(html).toContain('<div class="guide-body guide-lead" lang="fi">');
    expect(html).toContain(`<h2 id="guide-sources" lang="en">${en['guides.sources']}</h2>`);
    expect(html).toContain(`Track a ${english.name} parcel`);
    expect(html).toContain(`<a class="guide__crumb" href="/carriers">${en['carriers.title']}</a>`);
    // A carrier without a Finnish page, a guide and the tracker lead to English.
    expect(html).toContain(`<a href="/carriers/${neighbour.slug}">toinen kuljetusyhtiö</a>`);
    expect(html).toContain(`<a href="/guides/${GUIDE_LINKS.en[0].slug}">ohje</a>`);
    expect(html).toContain('<a href="/">Peek</a>');
    // The other carriers, in English, and not itself.
    const others = [...html.matchAll(/class="guide-card carrier-card" href="([^"]+)"/g)].map(([, href]) => href);
    expect(others).toEqual(CARRIER_LINKS.en.filter(({ id }) => id !== fixture.id).map(({ slug }) => `/carriers/${slug}`));
    // Its foot names the site's languages, then its own, the current one.
    const languages = [...html.matchAll(/<a href="([^"]+)" hrefLang="([^"]+)" lang="[^"]+"( aria-current="page")?>([^<]+)<\/a>/g)].map(([, href, language, current, name]) => [language, href, Boolean(current), name]);
    expect(languages.map(([language]) => language)).toEqual(['en', 'de', 'fr', 'it', 'es', 'pt', 'pt-BR', 'pl', 'ru', 'zh', 'fi']);
    expect(languages.at(-1)).toEqual(['fi', '/fi/carriers/lahetysten-seuranta', true, 'Suomi']);
    expect(languages[0]).toEqual(['en', `/carriers/${english.slug}`, false, 'English']);
  });

  it('describes itself in its language, on a trail through the English pages', async () => {
    const [article, crumbs, faq] = graph(renderToStaticMarkup(await CarrierRoute({ locale: 'fi', slug: finnish.slug })));
    expect(article).toMatchObject({ '@type': 'Article', headline: 'Lähetysten seuranta', inLanguage: 'fi', mainEntityOfPage: url });
    expect(crumbs.itemListElement.map((item: { item: string }) => item.item)).toEqual(['https://peek.example/', 'https://peek.example/carriers', url]);
    expect(faq).toMatchObject({ '@type': 'FAQPage', inLanguage: 'fi', url });
    expect(faq.mainEntity.map((entry: { name: string }) => entry.name)).toEqual(['Missä lähetys on?', 'Kuka sen tuo?', 'Mistä sen noutaa?']);
  });

  it('is led to from the carrier’s other pages and listed in the sitemap with them', async () => {
    const html = renderToStaticMarkup(await CarrierRoute({ locale: 'en', slug: english.slug }));
    expect(html).toContain('<a href="/fi/carriers/lahetysten-seuranta" hrefLang="fi" lang="fi">Suomi</a>');
    const entries = await sitemap();
    const own = entries.find((entry) => entry.url === url)!;
    expect(own).toMatchObject({ lastModified: '2026-10-22', alternates: { languages: { fi: url, en: `https://peek.example/carriers/${english.slug}` } } });
    expect(entries.find((entry) => entry.url === `https://peek.example/carriers/${english.slug}`)!.alternates!.languages).toHaveProperty('fi', url);
    // No carriers' own page in Finnish.
    expect(entries.filter((entry) => entry.url.startsWith('https://peek.example/fi/'))).toEqual([own]);
  });
});

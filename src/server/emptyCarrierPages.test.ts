import { renderToStaticMarkup } from 'react-dom/server';
import { describe, expect, it, vi } from 'vitest';
import de from '../../shared/locales/de.json';

vi.mock('next/headers', () => ({
  cookies: async () => ({ get: () => undefined }),
  headers: async () => new Headers({ host: 'peek.example', 'x-forwarded-proto': 'https' }),
}));
// Outside a request there is nothing to wait for.
vi.mock('next/server', async (original) => ({ ...await original<typeof import('next/server')>(), connection: async () => undefined }));

/** German as if no carrier's page were written in it yet, as a language of the site is until its first one. */
vi.mock('../generated/carriers', async (original) => {
  const generated = await original<typeof import('../generated/carriers')>();
  return {
    ...generated,
    CARRIER_LINKS: { ...generated.CARRIER_LINKS, de: [] },
    CARRIER_LOCALES: generated.CARRIER_LOCALES.filter((locale) => locale !== 'de'),
  };
});

const { CARRIER_LINKS } = await import('../generated/carriers');
const { carrierListPath, hasCarrierPages } = await import('../carriers/paths');
const { carrierAddresses, carrierAlternates } = await import('./carrierTexts');
const { CarrierIndexRoute, carrierIndexMetadata } = await import('./carrierPages');
const { default: sitemap } = await import('../../app/sitemap');

const [english] = CARRIER_LINKS.en;

describe('the carriers’ own page of a language no carrier’s page is written in', () => {
  it('is led to by no link: its readers are sent to the English one', () => {
    expect(hasCarrierPages('de')).toBe(false);
    expect(carrierListPath('de')).toBe('/carriers');
    expect(carrierListPath('fr')).toBe('/fr/carriers');
    expect(carrierAddresses()).not.toHaveProperty('de');
    expect(carrierAddresses(english.id)).not.toHaveProperty('de');
    expect(carrierAddresses(english.id).fr).toBeDefined();
    expect(carrierAlternates(new URL('https://peek.example'))).not.toHaveProperty('de');
  });

  it('is kept out of search engines and the sitemap, and names no other language', async () => {
    const metadata = await carrierIndexMetadata('de');
    expect(metadata.robots).toEqual({ index: false, follow: true });
    expect(metadata.alternates).toBeUndefined();
    expect((await carrierIndexMetadata('fr')).robots).toBeUndefined();
    const listed = (await sitemap()).flatMap(({ url, alternates }) => [url, ...Object.values(alternates?.languages ?? {})]);
    expect(listed).toContain('https://peek.example/fr/carriers');
    expect(listed.filter((url) => url?.startsWith('https://peek.example/de/carriers'))).toEqual([]);
  });

  it('lists the English carriers’ pages in its own frame', async () => {
    const html = renderToStaticMarkup(await CarrierIndexRoute({ locale: 'de' }));
    expect([...html.matchAll(/class="guide-card carrier-card" href="([^"]+)"/g)].map(([, href]) => href)).toEqual(CARRIER_LINKS.en.map(({ slug }) => `/carriers/${slug}`));
    expect(html).toContain(de['carriers.heading']);
    expect(html).toContain(`<a href="/carriers">${de['carriers.all']}</a>`);
    expect(html).toMatch(/<a href="\/de\/carriers" hrefLang="de" lang="de" aria-current="page">/);
  });
});

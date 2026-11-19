import { renderToStaticMarkup } from 'react-dom/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import en from '../../shared/locales/en.json';
import { hrefLanguage, SUPPORTED_LOCALES } from '../lib/locale';

vi.mock('next/headers', () => ({
  cookies: async () => ({ get: () => undefined }),
  headers: async () => new Headers({ host: 'peek.example', 'x-forwarded-proto': 'https' }),
}));
// Outside a request there is nothing to wait for.
vi.mock('next/server', async (original) => ({ ...await original<typeof import('next/server')>(), connection: async () => undefined }));

const { assistantsAddresses, assistantsAlternates, assistantsPath, assistantsPathLanguage } = await import('../assistants/paths');
const { AssistantsRoute, assistantsMetadata } = await import('./assistantsPage');

const text = (html: string) => html.replace(/<[^>]+>/g, '').replaceAll('&#x27;', '\'').replaceAll('&quot;', '"').replaceAll('&amp;', '&');

describe('the page on connecting an assistant', () => {
  beforeEach(() => {
    vi.stubEnv('ASSISTANT_ACCOUNTS', '*');
    vi.stubEnv('CANONICAL_ORIGIN', 'https://peek.example.test');
  });
  afterEach(() => { vi.unstubAllEnvs(); });

  it('lives at /assistants in English and under each other language’s prefix, and nowhere else', () => {
    expect(SUPPORTED_LOCALES.map(assistantsPath)).toEqual(SUPPORTED_LOCALES.map((locale) => (locale === 'en' ? '/assistants' : `/${locale}/assistants`)));
    for (const locale of SUPPORTED_LOCALES) expect(assistantsPathLanguage(assistantsPath(locale))).toBe(locale);
    for (const other of ['/en/assistants', '/fi/assistants', '/xx/assistants', '/assistants/', '/assistants/claude', '/fr/assistants/x', '/guides/assistants']) {
      expect(assistantsPathLanguage(other), other).toBeNull();
    }
    expect(assistantsAddresses()).toMatchObject({ en: '/assistants', 'pt-br': '/pt-br/assistants' });
    expect(assistantsAlternates(new URL('https://peek.example'))).toMatchObject({
      fr: 'https://peek.example/fr/assistants', 'x-default': 'https://peek.example/assistants',
    });
  });

  it('tells a search engine the page in every language, on the canonical origin', async () => {
    const metadata = await assistantsMetadata('fr');
    expect(metadata.title).toBe('Connecter Peek à Claude, ChatGPT ou Gemini — Peek');
    expect(metadata.alternates).toEqual({
      canonical: 'https://peek.example.test/fr/assistants',
      languages: {
        ...Object.fromEntries(SUPPORTED_LOCALES.map((locale) => [hrefLanguage(locale), `https://peek.example.test${assistantsPath(locale)}`])),
        'x-default': 'https://peek.example.test/assistants',
      },
    });
    expect(metadata.openGraph).toMatchObject({ type: 'website', locale: 'fr_FR', url: 'https://peek.example.test/fr/assistants' });
    for (const locale of SUPPORTED_LOCALES) {
      const { title } = await assistantsMetadata(locale);
      expect(String(title).replace(/ — Peek$/, '').length, locale).toBeLessThanOrEqual(60);
    }
  });

  it('gives the address to add, each assistant’s steps, what to ask, what Peek shares and what to do when it goes wrong', async () => {
    const html = renderToStaticMarkup(await AssistantsRoute({ locale: 'en' }));
    const words = text(html);
    expect(html).toContain(`<h1>${en['assistants.page.title']}</h1>`);
    expect(words).toContain(en['assistants.experimental']);
    // The address assistants connect to, on the canonical origin, whatever host the page was asked on.
    expect(html).toContain('<code>https://peek.example.test/mcp</code>');
    for (const name of ['Claude', 'ChatGPT', 'Gemini', 'OpenClaw', 'Home Assistant']) expect(html).toContain(`<h3 translate="no">${name}</h3>`);
    expect(words).toContain('Sign in to Peek when it asks, then select Allow.');
    // OpenClaw's commands are code, and the one that saves Peek holds the address on the canonical origin.
    expect(html).toContain('<code class="assistants__command" translate="no">openclaw gateway stop</code>');
    expect(words).toContain(
      'add Peek with openclaw mcp set peek \'{"url":"https://peek.example.test/mcp","transport":"streamable-http","auth":"oauth"}\'.',
    );
    expect(html).toContain('<a class="add-by-email__ui" href="https://claude.ai/directory/peek-parcel-tracker" target="_blank" rel="noopener noreferrer" translate="no">Peek Parcel Tracker</a>');
    expect(words).toContain(`If Claude doesn’t list Peek Parcel Tracker, add Peek as a custom connector instead`);
    // At least three things to ask that work.
    for (const key of ['assistants.uses.email', 'assistants.uses.week', 'assistants.uses.where', 'assistants.uses.archive'] as const) expect(words).toContain(en[key]);
    expect(words).toContain(en['assistants.page.sharesBody']);
    expect(html).toContain('<a href="/privacy.html">privacy notice</a>');
    expect(words).toContain('In Peek, open Settings › Assistants and select Disconnect next to the assistant.');
    for (const key of ['assistants.page.signIn', 'assistants.page.stopped', 'assistants.page.limit'] as const) expect(html).toContain(`<h3>${en[key]}</h3>`);
    expect(html).toContain('<a href="/support.html">help page</a>');
    // Each section is reached from the contents by an address that is the same in every language.
    for (const id of ['can', 'address', 'setup', 'ask', 'shares', 'disconnect', 'troubleshooting']) {
      expect(html).toContain(`<h2 id="${id}">`);
      expect(html).toContain(`<a href="#${id}">`);
    }
    // The page in each of the site's languages, as links a search engine follows.
    for (const locale of SUPPORTED_LOCALES) expect(html).toContain(`href="${assistantsPath(locale)}"`);
  });

  it('fills every name it shows, in every language', async () => {
    for (const locale of SUPPORTED_LOCALES) {
      const html = renderToStaticMarkup(await AssistantsRoute({ locale }));
      expect(html, locale).not.toMatch(/\{\{|\}\}/);
      expect(html, locale).toContain('https://peek.example.test/mcp');
    }
  });
});

describe('a deployment where no assistant can connect', () => {
  afterEach(() => { vi.unstubAllEnvs(); });

  it('has no such page', async () => {
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    // Metadata is resolved outside the page's not-found boundary: it must not throw.
    expect(await assistantsMetadata('en')).toEqual({});
    await expect(AssistantsRoute({ locale: 'de' })).rejects.toThrow(/NEXT_HTTP_ERROR_FALLBACK;404/);
  });
});

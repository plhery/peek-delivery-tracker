import { expect, it, vi } from 'vitest';
import { CARRIER_LINKS } from '../generated/carriers';
import { PAGE_ONLY_LANGUAGES, SUPPORTED_LOCALES } from '../lib/locale';
import { carrierDestination, carrierPageLanguage, carrierPath, carrierPathLanguage, namesNoCarrier } from './paths';

// A carrier's page in Finnish, a language with pages alone, as the list would hold one.
vi.mock('../generated/carriers', async (original) => {
  const { CARRIER_LINKS: links } = await original<typeof import('../generated/carriers')>();
  return { CARRIER_LINKS: { ...links, fi: [{ ...links.en[0], slug: 'lahetysten-seuranta', title: 'Lähetysten seuranta' }] } };
});

it('puts the English carriers’ pages at /carriers and every other language under its prefix', () => {
  expect(carrierPath('en')).toBe('/carriers');
  expect(carrierPath('en', 'some-carrier-tracking')).toBe('/carriers/some-carrier-tracking');
  expect(carrierPath('fr')).toBe('/fr/carriers');
  expect(carrierPath('pl', 'sledzenie-przesylki')).toBe('/pl/carriers/sledzenie-przesylki');
});

it('reads a carrier page’s language from its address, and nothing from any other address', () => {
  expect(carrierPathLanguage('/carriers')).toBe('en');
  expect(carrierPathLanguage('/carriers/some-carrier')).toBe('en');
  expect(carrierPathLanguage('/de/carriers')).toBe('de');
  expect(carrierPathLanguage('/it/carriers/un-corriere')).toBe('it');
  // English has no prefix, and a prefix is one of the site's languages.
  expect(carrierPathLanguage('/en/carriers')).toBeNull();
  expect(carrierPathLanguage('/xx/carriers')).toBeNull();
  expect(carrierPathLanguage('/')).toBeNull();
  expect(carrierPathLanguage('/de')).toBeNull();
  expect(carrierPathLanguage('/carriersmore')).toBeNull();
  expect(carrierPathLanguage('/guides')).toBeNull();
  expect(carrierPathLanguage('/p/carriers')).toBeNull();
});

it('knows an address below a language’s carriers that names none written in it, letter for letter', () => {
  const everySlug = SUPPORTED_LOCALES.flatMap((locale) => CARRIER_LINKS[locale].map(({ slug }) => slug));
  expect(CARRIER_LINKS.en.length).toBeGreaterThan(0);
  for (const locale of SUPPORTED_LOCALES) {
    const own = CARRIER_LINKS[locale].map(({ slug }) => slug);
    // The carriers' own page exists in every language, with or without carriers in it.
    expect(namesNoCarrier(carrierPath(locale)), locale).toBe(false);
    for (const slug of own) expect(namesNoCarrier(carrierPath(locale, slug)), slug).toBe(false);
    // Another language's slug, a carrier this language has no page of, a made-up slug, or anything below a page is no page here.
    const missing = [...everySlug.filter((slug) => !own.includes(slug)), 'no-such-carrier', ...own.flatMap((slug) => [`${slug}x`, slug.toUpperCase(), `${slug}/more`])];
    for (const slug of missing) expect(namesNoCarrier(carrierPath(locale, slug)), slug).toBe(true);
  }
  // Nor is it anything to say of an address that is not the carriers'.
  for (const path of ['/', '/de', '/demo', '/carriersmore', '/en/carriers/x', '/CARRIERS/x', '/FR/carriers/x', '/guides/x']) expect(namesNoCarrier(path), path).toBe(false);
});

it('puts a page in a language with pages alone under its prefix, and shows it in English around its text', () => {
  expect(carrierPath('fi', 'lahetysten-seuranta')).toBe('/fi/carriers/lahetysten-seuranta');
  for (const language of Object.keys(PAGE_ONLY_LANGUAGES)) {
    expect(carrierPageLanguage(`/${language}/carriers/a-page`), language).toBe(language);
    expect(carrierPathLanguage(`/${language}/carriers/a-page`), language).toBe('en');
  }
  expect(carrierPageLanguage('/carriers/a-page')).toBe('en');
  expect(carrierPageLanguage('/de/carriers')).toBe('de');
  for (const path of ['/en/carriers', '/xx/carriers/a-page', '/fi', '/fi/guides/a-guide', '/fi/carriersmore']) expect(carrierPageLanguage(path), path).toBeNull();
  // Its page is there; its carriers' own page, any other slug and a language without pages are not.
  expect(namesNoCarrier('/fi/carriers/lahetysten-seuranta')).toBe(false);
  for (const path of ['/fi/carriers', '/fi/carriers/', '/fi/carriers/no-such-carrier', carrierPath('fi', CARRIER_LINKS.en[0].slug), '/fi/carriers/lahetysten-seuranta/more', '/sv/carriers', '/sv/carriers/lahetysten-seuranta']) {
    expect(namesNoCarrier(path), path).toBe(true);
  }
});

it('leads a carrier named by its id or another language’s slug to its page in the address’s language, else in English', () => {
  const page = (language: 'en' | 'de' | 'es' | 'zh', id: string) => CARRIER_LINKS[language].find((link) => link.id === id)!.slug;
  // Another language's slug under a language the carrier is written in.
  expect(carrierDestination(`/de/carriers/${page('en', 'j-and-t-de')}`)).toBe(`/de/carriers/${page('de', 'j-and-t-de')}`);
  expect(carrierDestination(`/es/carriers/${page('zh', 'jrt')}`)).toBe(`/es/carriers/${page('es', 'jrt')}`);
  // The carrier's id.
  expect(carrierDestination('/carriers/yanwen')).toBe(`/carriers/${page('en', 'yanwen')}`);
  expect(carrierDestination('/de/carriers/yanwen')).toBe(`/de/carriers/${page('de', 'yanwen')}`);
  // A language the carrier is not written in: its English page.
  const englishOnly = CARRIER_LINKS.en.find((link) => !CARRIER_LINKS.de.some(({ id }) => id === link.id))!;
  expect(carrierDestination(`/de/carriers/${englishOnly.id}`)).toBe(`/carriers/${englishOnly.slug}`);
  // A carrier's own page, a name no carrier has, anything below a page and any other address lead nowhere.
  for (const path of [`/carriers/${page('en', 'yanwen')}`, `/de/carriers/${page('de', 'jrt')}`, '/carriers', '/de/carriers', '/carriers/no-such-carrier',
    `/carriers/yanwen/more`, '/guides/yanwen', '/en/carriers/yanwen', '/yanwen']) expect(carrierDestination(path), path).toBeNull();
});

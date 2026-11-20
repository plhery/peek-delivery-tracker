import { expect, it } from 'vitest';
import { GUIDE_LINKS } from '../generated/guides';
import { GUIDE_LANGUAGES } from '../lib/locale';
import { guideHref, guideIdDestination, guidePath, guidePathLanguage, namesNoGuide } from './paths';

it('puts English guides at /guides and every other language under its prefix', () => {
  expect(guidePath('en')).toBe('/guides');
  expect(guidePath('en', 'some-guide')).toBe('/guides/some-guide');
  expect(guidePath('fr')).toBe('/fr/guides');
  expect(guidePath('pl', 'inny-poradnik')).toBe('/pl/guides/inny-poradnik');
});

it('reads a guide’s language from its address, and nothing from any other address', () => {
  expect(guidePathLanguage('/guides')).toBe('en');
  expect(guidePathLanguage('/guides/some-guide')).toBe('en');
  expect(guidePathLanguage('/de/guides')).toBe('de');
  expect(guidePathLanguage('/it/guides/una-guida')).toBe('it');
  // English has no prefix, and a prefix is one of the site's languages.
  expect(guidePathLanguage('/en/guides')).toBeNull();
  expect(guidePathLanguage('/nl/guides')).toBeNull();
  // Peek speaks these, but the guides are not written in them.
  expect(guidePathLanguage('/ru/guides')).toBeNull();
  expect(guidePathLanguage('/pt-br/guides')).toBeNull();
  expect(guidePathLanguage('/')).toBeNull();
  expect(guidePathLanguage('/de')).toBeNull();
  expect(guidePathLanguage('/guidesmore')).toBeNull();
  expect(guidePathLanguage('/p/guides')).toBeNull();
  expect(guidePathLanguage('/demo')).toBeNull();
});

it('knows an address below a language’s guides that names none of them, letter for letter', () => {
  const everySlug = GUIDE_LANGUAGES.flatMap((locale) => GUIDE_LINKS[locale].map(({ slug }) => slug));
  for (const locale of GUIDE_LANGUAGES) {
    const own = GUIDE_LINKS[locale].map(({ slug }) => slug);
    expect(namesNoGuide(guidePath(locale)), locale).toBe(false);
    for (const slug of own) expect(namesNoGuide(guidePath(locale, slug)), slug).toBe(false);
    // Another language's slug, a made-up one, one in other letters, or anything below a guide is no page in this language.
    const missing = [...everySlug.filter((slug) => !own.includes(slug)), 'no-such-guide', `${own[0]}x`, own[0].toUpperCase(), `${own[0]}/more`];
    for (const slug of missing) expect(namesNoGuide(guidePath(locale, slug)), slug).toBe(true);
  }
  // Nor is it anything to say of an address that is not the guides'.
  for (const path of ['/', '/de', '/demo', '/guidesmore', '/en/guides/x', '/GUIDES/x', '/FR/guides/x']) expect(namesNoGuide(path), path).toBe(false);
});

it('leads an address that names a guide by its id to its slug, in the address’s language', () => {
  const slug = (language: 'en' | 'fr', id: string) => GUIDE_LINKS[language].find((link) => link.id === id)!.slug;
  expect(guideIdDestination('/guides/amazon-orders')).toBe(`/guides/${slug('en', 'amazon-orders')}`);
  expect(guideIdDestination('/fr/guides/amazon-orders')).toBe(`/fr/guides/${slug('fr', 'amazon-orders')}`);
  // A slug, the index, an unknown id or a language without guides stays where it is.
  expect(guideIdDestination(`/guides/${slug('en', 'amazon-orders')}`)).toBeNull();
  expect(guideIdDestination('/guides')).toBeNull();
  expect(guideIdDestination('/guides/no-such-guide')).toBeNull();
  expect(guideIdDestination('/ru/guides/amazon-orders')).toBeNull();
  expect(guideIdDestination('/guides/amazon-orders/more')).toBeNull();
});

it('links a guide in the reader’s language, or the nearest one it is written in', () => {
  const slug = (language: 'en' | 'pt' | 'de', id: string) => GUIDE_LINKS[language].find((link) => link.id === id)!.slug;
  expect(guideHref('de', 'amazon-orders')).toBe(`/de/guides/${slug('de', 'amazon-orders')}`);
  expect(guideHref('pt-br', 'amazon-orders')).toBe(`/pt/guides/${slug('pt', 'amazon-orders')}`);
  expect(guideHref('zh', 'amazon-orders')).toBe(`/guides/${slug('en', 'amazon-orders')}`);
  expect(guideHref('en', 'no-such-guide')).toBeNull();
});

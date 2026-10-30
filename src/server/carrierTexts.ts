import 'server-only';
import { readFileSync } from 'node:fs';
import path from 'node:path';
import { carrierPath, hasCarrierPages } from '../carriers/paths';
import { CARRIER_LINKS, type CarrierLink } from '../generated/carriers';
import { parseCarrierText, typeset, updatedDay, type CarrierText } from '../guides/markdown';
import { isLocale, PAGE_LANGUAGES, SUPPORTED_LOCALES, type PageAddresses, type PageLanguage } from '../lib/locale';

// The build ships these files beside the server (`outputFileTracingIncludes` in `next.config.ts`).
// `./carriers` is the scraper's list of every carrier Peek tracks; this is the few with a page of their own.
const directory = path.join(process.cwd(), 'content', 'carriers');
const read = new Map<string, CarrierText>();
const days = new Map<string, string>();

/** The carriers with a page in a language, in the order the site shows them. */
export function carrierLinks(locale: PageLanguage): readonly CarrierLink[] {
  return CARRIER_LINKS[locale];
}

/** The carrier's page an address names, in the address's language. */
export function carrierLinkBySlug(locale: PageLanguage, slug: string): CarrierLink | undefined {
  return CARRIER_LINKS[locale].find((link) => link.slug === slug);
}

/** The languages a carrier's page is written in, the site's own first, in its order, then those with pages alone. */
export function carrierLanguages(id: string): PageLanguage[] {
  return PAGE_LANGUAGES.filter((locale) => CARRIER_LINKS[locale].some((link) => link.id === id));
}

/** A carrier's page, set as its language sets it. Its file is read once; while developing, every time, so an edit shows. */
export function carrierText(locale: PageLanguage, id: string): CarrierText {
  const key = `${id}/${locale}`;
  const kept = read.get(key);
  if (kept) return kept;
  const loaded = typeset(parseCarrierText(readFileSync(path.join(directory, id, `${locale}.md`), 'utf8'), `content/carriers/${key}.md`), locale);
  if (process.env.NODE_ENV === 'production') read.set(key, loaded);
  return loaded;
}

/** The day a carrier's page was last checked, from its fields alone: the sitemap dates every page without keeping its text. */
export function carrierUpdated(locale: PageLanguage, id: string): string {
  const key = `${id}/${locale}`;
  const kept = read.get(key)?.updated ?? days.get(key);
  if (kept) return kept;
  const day = updatedDay(readFileSync(path.join(directory, id, `${locale}.md`), 'utf8'), `content/carriers/${key}.md`);
  if (process.env.NODE_ENV === 'production') days.set(key, day);
  return day;
}

/**
 * Where a carrier's page lives in the languages of the site with carriers' pages (`hasCarrierPages`): its own
 * page in a language it is written in, the carriers' own page of any other. A language of the site no
 * carrier's page is written in yet has no address, so no link leads to its empty carriers' page; nor has a
 * language with pages alone, but where the carrier is written in it. With no carrier named, the carriers' own page.
 */
export function carrierAddresses(id?: string): PageAddresses {
  const slug = (language: PageLanguage) => CARRIER_LINKS[language].find((link) => link.id === id)?.slug;
  return Object.fromEntries([
    ...SUPPORTED_LOCALES.filter(hasCarrierPages).map((locale) => [locale, carrierPath(locale, slug(locale))]),
    ...(id ? carrierLanguages(id) : []).filter((language) => !isLocale(language)).map((language) => [language, carrierPath(language, slug(language))]),
  ]) as PageAddresses;
}

/**
 * A carrier's page, or the carriers' own page, in the languages it is written in, as search engines are
 * told: each language's address on `origin`, and the English one for a reader of none of them. A
 * carrier's page names only its own languages; the carriers' own page those of the site with carriers' pages.
 */
export function carrierAlternates(origin: URL, id?: string): Partial<Record<PageLanguage, string>> & Record<'x-default', string> {
  const addresses = carrierAddresses(id);
  return {
    ...Object.fromEntries((id ? carrierLanguages(id) : SUPPORTED_LOCALES.filter(hasCarrierPages)).map((locale) => [locale, new URL(addresses[locale]!, origin).href])),
    'x-default': new URL(addresses.en, origin).href,
  };
}

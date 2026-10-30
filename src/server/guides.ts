import 'server-only';
import { readFileSync } from 'node:fs';
import path from 'node:path';
import { GUIDE_LINKS, type GuideLink } from '../generated/guides';
import { parseGuide, typeset, updatedDay, type Guide } from '../guides/markdown';
import { guidePath } from '../guides/paths';
import { GUIDE_LANGUAGES, type GuideLanguage } from '../lib/locale';

// The build ships these files beside the server (`outputFileTracingIncludes` in `next.config.ts`).
const directory = path.join(process.cwd(), 'content', 'guides');
const read = new Map<string, Guide>();
const days = new Map<string, string>();

/** The guides of a language, in the order the site shows them. */
export function guideLinks(locale: GuideLanguage): readonly GuideLink[] {
  return GUIDE_LINKS[locale];
}

/** The guide an address names, in the address's language. */
export function guideLinkBySlug(locale: GuideLanguage, slug: string): GuideLink | undefined {
  return GUIDE_LINKS[locale].find((link) => link.slug === slug);
}

/** A guide's text, set as its language sets it. Its file is read once; while developing, every time, so an edit shows. */
export function guide(locale: GuideLanguage, id: string): Guide {
  const key = `${id}/${locale}`;
  const kept = read.get(key);
  if (kept) return kept;
  const loaded = typeset(parseGuide(readFileSync(path.join(directory, id, `${locale}.md`), 'utf8'), `content/guides/${key}.md`), locale);
  if (process.env.NODE_ENV === 'production') read.set(key, loaded);
  return loaded;
}

/** The day a guide's facts were last checked, from its fields alone: the sitemap dates every guide without keeping its text. */
export function guideUpdated(locale: GuideLanguage, id: string): string {
  const key = `${id}/${locale}`;
  const kept = read.get(key)?.updated ?? days.get(key);
  if (kept) return kept;
  const day = updatedDay(readFileSync(path.join(directory, id, `${locale}.md`), 'utf8'), `content/guides/${key}.md`);
  if (process.env.NODE_ENV === 'production') days.set(key, day);
  return day;
}

/** Where one guide lives in every language it is written in, or the guides' own page when no guide is named. */
export function guideAddresses(id?: string): Record<GuideLanguage, string> {
  return Object.fromEntries(GUIDE_LANGUAGES.map((locale) => [
    locale,
    guidePath(locale, id && GUIDE_LINKS[locale].find((link) => link.id === id)?.slug),
  ])) as Record<GuideLanguage, string>;
}

/**
 * A guide, or the guides' own page, in every language as search engines are told: each language's
 * address on `origin`, and the English one for a reader of none of them.
 */
export function guideAlternates(origin: URL, id?: string): Record<GuideLanguage | 'x-default', string> {
  const addresses = guideAddresses(id);
  return {
    ...Object.fromEntries(GUIDE_LANGUAGES.map((locale) => [locale, new URL(addresses[locale], origin).href])) as Record<GuideLanguage, string>,
    'x-default': new URL(addresses.en, origin).href,
  };
}

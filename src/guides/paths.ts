import { GUIDE_LINKS } from '../generated/guides';
import { guideLanguage, isGuideLanguage, type GuideLanguage, type Locale } from '../lib/locale';

/** Where the guides of a language live, or one of them. English has no prefix. */
export function guidePath(locale: GuideLanguage, slug?: string): string {
  return `${locale === 'en' ? '' : `/${locale}`}/guides${slug ? `/${slug}` : ''}`;
}

/**
 * The language a guides address is written in, or null at any other address: `/guides` is English,
 * `/de/guides` German. English has no prefix, so `/en/guides` is no guides address, and nor is one in a
 * language the guides are not written in, such as `/ru/guides`.
 */
export function guidePathLanguage(pathname: string): GuideLanguage | null {
  const match = /^\/(?:([a-z]{2})\/)?guides(?:\/|$)/.exec(pathname);
  if (!match) return null;
  if (!match[1]) return 'en';
  return isGuideLanguage(match[1]) && match[1] !== 'en' ? match[1] : null;
}

/**
 * Whether an address below a language's guides names none of them, letter for letter: the proxy answers it
 * with the site's 404 page. The guides' pages are rendered for each request, so `dynamicParams` has no list
 * to hold a slug against, and a `notFound()` thrown by the page leaves the 404 page for the browser to draw.
 */
export function namesNoGuide(pathname: string): boolean {
  const language = guidePathLanguage(pathname);
  if (!language) return false;
  const below = pathname.slice(guidePath(language).length);
  return below !== '' && !GUIDE_LINKS[language].some(({ slug }) => below === `/${slug}`);
}

/** A guide's address in the reader's language, or the nearest one it is written in; null for an id with no guide. */
export function guideHref(locale: Locale, id: string): string | null {
  const language = guideLanguage(locale);
  const guide = GUIDE_LINKS[language].find((link) => link.id === id);
  return guide ? guidePath(language, guide.slug) : null;
}

/**
 * Where an address that names a guide by its id instead of its slug leads, such as `/fr/guides/amazon-orders`: the
 * apps link a guide that way, as they hold no slugs. Null for any other address, a slug included.
 */
export function guideIdDestination(pathname: string): string | null {
  const language = guidePathLanguage(pathname);
  if (!language) return null;
  const below = pathname.slice(guidePath(language).length);
  const links = GUIDE_LINKS[language];
  if (!below || links.some(({ slug }) => below === `/${slug}`)) return null;
  const guide = links.find(({ id }) => below === `/${id}`);
  return guide ? guidePath(language, guide.slug) : null;
}

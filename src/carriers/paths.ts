import { CARRIER_LINKS, CARRIER_LOCALES } from '../generated/carriers';
import { interfaceLanguage, isLocale, isPageOnlyLanguage, type Locale, type PageLanguage } from '../lib/locale';

/** Where the carriers' pages of a language live, or one of them. English has no prefix. */
export function carrierPath(language: PageLanguage, slug?: string): string {
  return `${language === 'en' ? '' : `/${language}`}/carriers${slug ? `/${slug}` : ''}`;
}

/** Whether a carrier's page is written in one of Peek's languages, which then has its carriers' own page. */
export function hasCarrierPages(locale: Locale): boolean {
  return CARRIER_LOCALES.includes(locale);
}

/** The carriers' own page a reader of a language is sent to: its language's, or the English one until a carrier's page is written in it. */
export function carrierListPath(locale: Locale): string {
  return carrierPath(hasCarrierPages(locale) ? locale : 'en');
}

/**
 * The language a carriers address is written in, or null at any other address: `/carriers` is English,
 * `/de/carriers` German, `/fi/carriers` Finnish, a language with pages alone. English has no prefix, so
 * `/en/carriers` is no carriers address.
 */
export function carrierPageLanguage(pathname: string): PageLanguage | null {
  const match = /^\/(?:([a-z]{2}(?:-[a-z]{2})?)\/)?carriers(?:\/|$)/.exec(pathname);
  if (!match) return null;
  if (!match[1]) return 'en';
  return (isLocale(match[1]) && match[1] !== 'en') || isPageOnlyLanguage(match[1]) ? match[1] : null;
}

/** The language a carriers address is shown in: its own, or for a language with pages alone, the one around its pages. */
export function carrierPathLanguage(pathname: string): Locale | null {
  const language = carrierPageLanguage(pathname);
  return language && interfaceLanguage(language);
}

/**
 * Whether an address below a language's carriers names no carrier written in that language, letter for
 * letter: the proxy answers it with the site's 404 page, as it does an address that names no guide
 * (`namesNoGuide`). A carrier's page in a language it is not written in is no page either, and a language
 * with pages alone has no carriers' own page.
 */
export function namesNoCarrier(pathname: string): boolean {
  const language = carrierPageLanguage(pathname);
  if (!language) return false;
  const below = pathname.slice(carrierPath(language).length);
  if (below === '') return isPageOnlyLanguage(language);
  return !CARRIER_LINKS[language].some(({ slug }) => below === `/${slug}`);
}

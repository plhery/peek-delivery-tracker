import { isLocale, SUPPORTED_LOCALES, type Locale, type PageAddresses } from '../lib/locale';

/** Where the page on connecting an assistant lives in a language. English has no prefix. */
export function assistantsPath(locale: Locale): string {
  return `${locale === 'en' ? '' : `/${locale}`}/assistants`;
}

/**
 * The language of the page on connecting an assistant, or null at any other address: `/assistants` is English,
 * `/de/assistants` German. English has no prefix, so `/en/assistants` is not the page.
 */
export function assistantsPathLanguage(pathname: string): Locale | null {
  const match = /^\/(?:([a-z]{2}(?:-[a-z]{2})?)\/)?assistants$/.exec(pathname);
  if (!match) return null;
  if (!match[1]) return 'en';
  return isLocale(match[1]) && match[1] !== 'en' ? match[1] : null;
}

/** The page in every language: the site's own, each at its address. */
export function assistantsAddresses(): PageAddresses {
  return Object.fromEntries(SUPPORTED_LOCALES.map((locale) => [locale, assistantsPath(locale)])) as PageAddresses;
}

/** The page in every language as search engines are told: each language's address on `origin`, and English for a reader of none. */
export function assistantsAlternates(origin: URL): Record<Locale | 'x-default', string> {
  return {
    ...Object.fromEntries(SUPPORTED_LOCALES.map((locale) => [locale, new URL(assistantsPath(locale), origin).href])) as Record<Locale, string>,
    'x-default': new URL(assistantsPath('en'), origin).href,
  };
}

export const SUPPORTED_LOCALES = ['en', 'de', 'fr', 'it', 'es', 'pt', 'pl'] as const;
export type Locale = (typeof SUPPORTED_LOCALES)[number];

/** Mirrors a chosen language, so the server renders the first frame in it. */
export const LOCALE_COOKIE = 'sdt.locale';

/**
 * Names, on the request a page reads, the language its text is written in when that is a language with pages
 * alone: the proxy sets it, and never passes on one a client sent.
 */
export const PAGE_LANGUAGE_HEADER = 'x-page-language';

export function isLocale(value: unknown): value is Locale {
  return SUPPORTED_LOCALES.includes(value as Locale);
}

/**
 * The `lang` a page or an email declares in a language. Portuguese is European Portuguese, so
 * screen readers pick a voice from Portugal. Addresses, hreflang and the stored choice keep `pt`.
 */
export function documentLanguage(locale: PageLanguage): string {
  return locale === 'pt' ? 'pt-PT' : locale;
}

/**
 * Languages a carrier's page can be written in without Peek speaking them: a national post's or a local
 * courier's page in its country's own language. The page's text is in that language and everything around it
 * in an interface language (`interfaceLanguage`). Each has its name in that language and its Open Graph locale.
 */
export const PAGE_ONLY_LANGUAGES = {
  nl: { name: 'Nederlands', preview: 'nl_NL' },
  sv: { name: 'Svenska', preview: 'sv_SE' },
  da: { name: 'Dansk', preview: 'da_DK' },
  nb: { name: 'Norsk bokmål', preview: 'nb_NO' },
  fi: { name: 'Suomi', preview: 'fi_FI' },
  cs: { name: 'Čeština', preview: 'cs_CZ' },
  sk: { name: 'Slovenčina', preview: 'sk_SK' },
  hu: { name: 'Magyar', preview: 'hu_HU' },
  ro: { name: 'Română', preview: 'ro_RO' },
  bg: { name: 'Български', preview: 'bg_BG' },
  el: { name: 'Ελληνικά', preview: 'el_GR' },
  hr: { name: 'Hrvatski', preview: 'hr_HR' },
  sl: { name: 'Slovenščina', preview: 'sl_SI' },
  lt: { name: 'Lietuvių', preview: 'lt_LT' },
  lv: { name: 'Latviešu', preview: 'lv_LV' },
  et: { name: 'Eesti', preview: 'et_EE' },
  uk: { name: 'Українська', preview: 'uk_UA' },
  sr: { name: 'Srpski', preview: 'sr_RS' },
} as const satisfies Record<string, { name: string; preview: string }>;
export type PageOnlyLanguage = keyof typeof PAGE_ONLY_LANGUAGES;
/** A language a page can be written in: Peek's own, or one with pages alone. */
export type PageLanguage = Locale | PageOnlyLanguage;
export const PAGE_LANGUAGES: readonly PageLanguage[] = [...SUPPORTED_LOCALES, ...Object.keys(PAGE_ONLY_LANGUAGES) as PageOnlyLanguage[]];


/** A page's address in each of the site's languages, and in each language with pages alone it is written in. */
export type PageAddresses = Record<Locale, string> & Partial<Record<PageOnlyLanguage, string>>;

export function isPageOnlyLanguage(value: unknown): value is PageOnlyLanguage {
  return typeof value === 'string' && Object.hasOwn(PAGE_ONLY_LANGUAGES, value);
}

/** The language a page in `language` speaks around its text: its own when Peek speaks it, English otherwise. */
export function interfaceLanguage(language: PageLanguage): Locale {
  return isLocale(language) ? language : 'en';
}

export function detectLocale(languages: readonly string[] = []): Locale {
  for (const language of languages) {
    const base = language.toLowerCase().split('-')[0];
    if (isLocale(base)) return base;
  }
  return 'en';
}

/** A language whose landing has an address of its own: every one but English, whose landing is `/`. */
export type AddressLanguage = Exclude<Locale, 'en'>;
export const ADDRESS_LANGUAGES = SUPPORTED_LOCALES.filter((locale): locale is AddressLanguage => locale !== 'en');

/** The landing's address in a language: `/de` in German, `/` in English. */
export function languagePath(locale: Locale): string {
  return locale === 'en' ? '/' : `/${locale}`;
}

/**
 * The address of the installed app's manifest in a language: `/de/manifest.webmanifest` in German,
 * `/manifest.webmanifest` in English. A browser fetches a manifest without cookies, so its address
 * carries the language.
 */
export function manifestPath(locale: Locale): string {
  return `${locale === 'en' ? '' : `/${locale}`}/manifest.webmanifest`;
}

/** The language of a language address, or null at any other address: `/de` is German, `/` and `/en` are no language's own. */
export function pathLanguage(pathname: string): AddressLanguage | null {
  const language = pathname.startsWith('/') ? pathname.slice(1) : null;
  return isLocale(language) && language !== 'en' ? language : null;
}

/** The browser's languages in its preference order, as sent in Accept-Language. */
export function acceptedLanguages(header: string | null): string[] {
  return (header ?? '').split(',').map((part) => part.split(';')[0].trim()).filter(Boolean);
}

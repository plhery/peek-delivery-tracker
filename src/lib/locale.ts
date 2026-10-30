/**
 * The languages Peek speaks. Portuguese is European Portuguese, and Brazil's is a language of its own, named with
 * its country in lower case as addresses write it (`pt-br`). Chinese is Simplified Chinese.
 */
export const SUPPORTED_LOCALES = ['en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'] as const;
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
 * The language of Peek's a BCP 47 tag names, or null when it names none: its own for a tag with a region
 * (`de-CH` is `de`), Brazil's Portuguese for `pt-BR` and European Portuguese for any other Portuguese, and
 * Simplified Chinese for Chinese unless it is written in Traditional characters (`zh-Hant`, or Taiwan's,
 * Hong Kong's and Macau's Chinese without a script), which Peek doesn't speak.
 */
export function tagLocale(tag: string): Locale | null {
  const [language, ...subtags] = tag.trim().toLowerCase().replaceAll('_', '-').split('-');
  if (language === 'pt') return subtags.includes('br') ? 'pt-br' : 'pt';
  if (language === 'zh') {
    const script = subtags.find((subtag) => /^[a-z]{4}$/.test(subtag));
    return (script ? script === 'hant' : subtags.some((subtag) => ['tw', 'hk', 'mo'].includes(subtag))) ? null : 'zh';
  }
  return isLocale(language) ? language : null;
}

/** The app's language as the page declares it (`pt-PT` is `pt`, `pt-BR` `pt-br`), when it is one Peek speaks. */
export function pageLocale(): Locale | undefined {
  if (typeof document === 'undefined') return undefined;
  return tagLocale(document.documentElement.lang) ?? undefined;
}

/**
 * A page's language as hreflang names it: with its region in capitals, as BCP 47 writes it, `pt-BR` for the
 * `pt-br` of its address; any other as its address names it.
 */
export function hrefLanguage(language: PageLanguage): string {
  const [base, region] = language.split('-');
  return region ? `${base}-${region.toUpperCase()}` : base;
}

/** A page's addresses with each language named as hreflang names it (`hrefLanguage`), and the one for a reader of none as it is. */
export function hrefLanguages<Address>(addresses: Partial<Record<PageLanguage | 'x-default', Address>>): Record<string, Address> {
  return Object.fromEntries(Object.entries(addresses).map(([language, address]) => [language === 'x-default' ? language : hrefLanguage(language as PageLanguage), address]));
}

/**
 * The `lang` a page or an email declares in a language. Portuguese is European Portuguese, so
 * screen readers pick a voice from Portugal, and Chinese is Simplified Chinese, so browsers pick its
 * characters' shapes. Addresses, hreflang and the stored choice keep `pt` and `zh`. Any other language
 * is declared as hreflang names it (`hrefLanguage`).
 */
export function documentLanguage(locale: PageLanguage): string {
  if (locale === 'pt') return 'pt-PT';
  if (locale === 'zh') return 'zh-Hans';
  return hrefLanguage(locale);
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


/** A page's address in each language it is written in: English always, the site's others and those with pages alone where it is. */
export type PageAddresses = Record<'en', string> & Partial<Record<PageLanguage, string>>;

export function isPageOnlyLanguage(value: unknown): value is PageOnlyLanguage {
  return typeof value === 'string' && Object.hasOwn(PAGE_ONLY_LANGUAGES, value);
}

/** The language a page in `language` speaks around its text: its own when Peek speaks it, English otherwise. */
export function interfaceLanguage(language: PageLanguage): Locale {
  return isLocale(language) ? language : 'en';
}

/** The first of the browser's languages Peek speaks (`tagLocale`), else English. */
export function detectLocale(languages: readonly string[] = []): Locale {
  for (const language of languages) {
    const locale = tagLocale(language);
    if (locale) return locale;
  }
  return 'en';
}

/**
 * The languages the guides are written in, every guide in each. A reader of another language reads them in the
 * nearest (`guideLanguage`).
 */
export const GUIDE_LANGUAGES = ['en', 'de', 'fr', 'it', 'es', 'pt', 'pl'] as const satisfies readonly Locale[];
export type GuideLanguage = (typeof GUIDE_LANGUAGES)[number];

export function isGuideLanguage(value: unknown): value is GuideLanguage {
  return GUIDE_LANGUAGES.includes(value as GuideLanguage);
}

/** The language a reader of `locale` reads the guides in: their own, European Portuguese for Brazil's, else English. */
export function guideLanguage(locale: Locale): GuideLanguage {
  if (locale === 'pt-br') return 'pt';
  return isGuideLanguage(locale) ? locale : 'en';
}

/**
 * The language a reader of `locale` reads Universal Parcel Scraper's carrier names in, which it writes in the
 * languages the guides are: European Portuguese for Brazil's, English for the others it lacks.
 */
export function catalogLanguage(locale: Locale): GuideLanguage {
  return guideLanguage(locale);
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

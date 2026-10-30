import { describe, expect, it } from 'vitest';
import {
  ADDRESS_LANGUAGES, catalogLanguage, detectLocale, documentLanguage, guideLanguage, hrefLanguage, interfaceLanguage, isLocale,
  isPageOnlyLanguage, languagePath, PAGE_LANGUAGES, PAGE_ONLY_LANGUAGES, pathLanguage, SUPPORTED_LOCALES, tagLocale,
  type PageOnlyLanguage,
} from './locale';

describe('language addresses', () => {
  it('gives every language but English an address of its own, and English `/`', () => {
    expect(ADDRESS_LANGUAGES).toEqual(['de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh']);
    expect(SUPPORTED_LOCALES.map(languagePath)).toEqual(['/', '/de', '/fr', '/it', '/es', '/pt', '/pt-br', '/pl', '/ru', '/zh']);
  });

  it('reads the language of a language address, and of no other address', () => {
    for (const language of ADDRESS_LANGUAGES) expect(pathLanguage(languagePath(language))).toBe(language);
    for (const path of ['/', '/en', '/home', '/demo', '/DE', '/de/', '/de/more', '/deu', '/xx', '/p/de', 'de', '/pt-BR', '/zh-hans', '']) {
      expect(pathLanguage(path), path).toBeNull();
    }
  });
});

describe('language tags', () => {
  it('reads Brazil’s Portuguese, other Portuguese as Portugal’s, and Simplified Chinese but not Traditional', () => {
    const tags: [string, string | null][] = [
      ['de-CH', 'de'], ['pt-BR', 'pt-br'], ['pt_br', 'pt-br'], ['pt', 'pt'], ['pt-PT', 'pt'], ['pt-AO', 'pt'],
      ['ru-RU', 'ru'], ['ru', 'ru'], ['zh', 'zh'], ['zh-CN', 'zh'], ['zh-Hans', 'zh'], ['zh-Hans-HK', 'zh'], ['zh-SG', 'zh'],
      ['zh-TW', null], ['zh-HK', null], ['zh-MO', null], ['zh-Hant', null], ['zh-Hant-CN', null], ['nl-NL', null], ['', null],
    ];
    for (const [tag, locale] of tags) expect(tagLocale(tag), tag).toBe(locale);
    expect(detectLocale(['zh-TW', 'ru-RU'])).toBe('ru');
    expect(detectLocale(['zh-Hant', 'nl'])).toBe('en');
  });

  it('names Brazil’s Portuguese by its region where a tag is needed, and the others as their addresses do', () => {
    expect(SUPPORTED_LOCALES.map(hrefLanguage)).toEqual(['en', 'de', 'fr', 'it', 'es', 'pt', 'pt-BR', 'pl', 'ru', 'zh']);
    expect(SUPPORTED_LOCALES.map(documentLanguage)).toEqual(['en', 'de', 'fr', 'it', 'es', 'pt-PT', 'pt-BR', 'pl', 'ru', 'zh-Hans']);
  });

  it('gives Brazil’s Portuguese the Portuguese guides and carrier names, and Russian and Chinese the English', () => {
    expect(SUPPORTED_LOCALES.map(guideLanguage)).toEqual(['en', 'de', 'fr', 'it', 'es', 'pt', 'pt', 'pl', 'en', 'en']);
    expect(SUPPORTED_LOCALES.map(catalogLanguage)).toEqual(SUPPORTED_LOCALES.map(guideLanguage));
  });
});

describe('languages with pages alone', () => {
  it('are none of the site’s own, each named in its own words with its preview locale', () => {
    const languages = Object.keys(PAGE_ONLY_LANGUAGES);
    expect(languages.filter((language) => isLocale(language))).toEqual([]);
    expect(PAGE_LANGUAGES).toEqual([...SUPPORTED_LOCALES, ...languages]);
    for (const [language, { name, preview }] of Object.entries(PAGE_ONLY_LANGUAGES)) {
      expect(language).toMatch(/^[a-z]{2}$/);
      expect(preview, language).toMatch(new RegExp(`^${language}_[A-Z]{2}$`));
      expect(name.trim(), language).not.toBe('');
      expect(documentLanguage(language as PageOnlyLanguage)).toBe(language);
    }
  });

  it('stand in English around their text, and the site’s own languages in their own', () => {
    expect(isPageOnlyLanguage('fi')).toBe(true);
    for (const value of ['de', 'en', 'xx', 'FI', 'constructor', 'toString', undefined]) expect(isPageOnlyLanguage(value), String(value)).toBe(false);
    expect(interfaceLanguage('fi')).toBe('en');
    expect(interfaceLanguage('el')).toBe('en');
    for (const locale of SUPPORTED_LOCALES) expect(interfaceLanguage(locale)).toBe(locale);
  });
});

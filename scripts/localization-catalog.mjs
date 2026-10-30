import fs from 'node:fs';
import path from 'node:path';
import { SUPPORTED_LOCALES } from '../src/lib/locale.ts';

export const locales = [...SUPPORTED_LOCALES];

/** The name Apple gives a language's `.lproj` folder: its region or script in capitals, and Chinese by its script. */
export function appleLanguage(locale) {
  if (locale === 'zh') return 'zh-Hans';
  const [language, region] = locale.split('-');
  return region ? `${language}-${region.toUpperCase()}` : language;
}

export function validateLocalizationCatalogs(catalogs) {
  const englishKeys = Object.keys(catalogs.en ?? {}).sort();
  if (!englishKeys.length) throw new Error('Missing English localization catalog');
  const variables = (text) => [...text.matchAll(/\{\{([A-Za-z0-9_.-]+)\}\}/g)]
    .map((match) => match[1]).sort().join(',');
  for (const locale of locales) {
    const catalog = catalogs[locale];
    if (!catalog) throw new Error(`Missing ${locale} localization catalog`);
    const missing = englishKeys.filter((key) => !(key in catalog));
    const extra = Object.keys(catalog).filter((key) => !englishKeys.includes(key));
    if (missing.length || extra.length) {
      throw new Error(`${locale} catalog keys differ. Missing: ${missing.join(', ') || 'none'}. Extra: ${extra.join(', ') || 'none'}.`);
    }
    for (const key of englishKeys) {
      if (typeof catalog[key] !== 'string') throw new Error(`${locale}.${key} must be a string`);
      // Russian counts 21, 31 and 101 as one too, so a count's one form may write the number where English writes 1.
      const counted = key.endsWith('.one') && variables(catalog[key].replaceAll('{{count}}', '')) === variables(catalogs.en[key]);
      if (variables(catalog[key]) !== variables(catalogs.en[key]) && !counted) {
        throw new Error(`${locale}.${key} must preserve the English interpolation variables`);
      }
      // Russian reads 21 and 101 with the one form too, so it writes the number wherever the family's other forms do.
      const family = key.replace(/\.one$/, '');
      if (locale === 'ru' && family !== key && !catalog[key].includes('{{count}}')
        && [family, `${family}.few`, `${family}.many`].some((other) => catalog[other]?.includes('{{count}}'))) {
        throw new Error(`ru.${key} must write {{count}}, which Russian reads with it for 21 and 101`);
      }
      // French sets ? ! : ; » and « apart with no-break spaces, so a line never starts with the mark.
      if (locale === 'fr' && /\u0020[?!:;»]|«\u0020/.test(catalog[key])) {
        throw new Error(`fr.${key} needs a no-break space before ? ! : ; » and after «`);
      }
    }
  }
}

export function readLocalizationCatalogs(directory) {
  const catalogs = Object.fromEntries(locales.map((locale) => [
    locale, JSON.parse(fs.readFileSync(path.join(directory, `${locale}.json`), 'utf8')),
  ]));
  validateLocalizationCatalogs(catalogs);
  return Object.fromEntries(locales.map((locale) => [locale,
    Object.fromEntries(Object.entries(catalogs[locale]).sort(([a], [b]) => a.localeCompare(b))),
  ]));
}

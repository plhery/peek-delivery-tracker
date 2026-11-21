import type { MetadataRoute } from 'next';
import { assistantsAlternates } from '../src/assistants/paths';
import { CARRIER_LINKS, CARRIER_LOCALES } from '../src/generated/carriers';
import { GUIDE_LINKS } from '../src/generated/guides';
import { GUIDE_LANGUAGES, hrefLanguages, SUPPORTED_LOCALES, type GuideLanguage, type Locale } from '../src/lib/locale';
import { LEGAL_PAGE_PATHS } from '../src/lib/site';
import { assistantsConfigured } from '../src/server/assistants/settings';
import { carrierAlternates, carrierLanguages, carrierUpdated } from '../src/server/carrierTexts';
import { guideAlternates, guideUpdated } from '../src/server/guides';
import { guidePagesOn } from '../src/server/guideSettings';
import { landingAddress, landingAlternates } from '../src/server/landingMetadata';
import { siteOrigin } from '../src/server/requestOrigin';
import { legalPagesOn } from '../src/server/siteSettings';

const entities: Record<string, string> = { '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&apos;' };
/** An address as XML text. The file is written from these strings as they are, and a request's host may hold any of the five. */
const xml = (address: string) => address.replace(/[&<>"']/g, (character) => entities[character]);
/** A page's address in every language it is written in, as XML text. */
const xmlAddresses = <Addresses extends Partial<Record<string, string>>>(addresses: Addresses) =>
  Object.fromEntries(Object.entries(addresses).flatMap(([language, address]) => (address === undefined ? [] : [[language, xml(address)]]))) as Addresses;

/** The day the newest guide of a language was last checked: when its list last changed. */
const lastChecked = (locale: GuideLanguage) => GUIDE_LINKS[locale].map(({ id }) => guideUpdated(locale, id)).sort().at(-1);
/** The day the newest carrier's page of a language was last checked. */
const carriersChecked = (locale: Locale) => CARRIER_LINKS[locale].map(({ id }) => carrierUpdated(locale, id)).sort().at(-1);

/**
 * The pages meant to be found: the landing in each language, the guides and the
 * carriers' pages where the site serves them (`GUIDE_PAGES`), the page on
 * connecting an assistant in each language where assistants can connect, and
 * the privacy notice, the terms of use, the help page and the accessibility
 * page where the site serves them (`LEGAL_PAGES`). A parcel, an invitation and
 * the demo are not listed.
 */
export default async function sitemap(): Promise<MetadataRoute.Sitemap> {
  const origin = await siteOrigin();
  const languages = xmlAddresses(landingAlternates(origin));
  const assistants = xmlAddresses(assistantsAlternates(origin));
  return [
    ...SUPPORTED_LOCALES.map((locale) => ({ url: xml(landingAddress(origin, locale)), alternates: { languages: hrefLanguages(languages) } })),
    ...(guidePagesOn() ? guidePages(origin) : []),
    ...(assistantsConfigured() ? SUPPORTED_LOCALES.map((locale) => ({ url: assistants[locale], alternates: { languages: hrefLanguages(assistants) } })) : []),
    ...(legalPagesOn() ? LEGAL_PAGE_PATHS.map((path) => ({ url: xml(new URL(path, origin).href) })) : []),
  ];
}

/**
 * The guides' own page and every guide in each language they are written in,
 * each naming the others, then the carriers' own page in each language a
 * carrier's page is written in and every carrier's page in each of its
 * languages, naming only those, as hreflang names them. A guide or a carrier's
 * page is dated by the day its facts were last checked.
 */
function guidePages(origin: URL): MetadataRoute.Sitemap {
  const index = xmlAddresses(guideAlternates(origin));
  const carriers = xmlAddresses(carrierAlternates(origin));
  return [
    ...GUIDE_LANGUAGES.map((locale) => ({ url: index[locale], lastModified: lastChecked(locale), alternates: { languages: hrefLanguages(index) } })),
    ...GUIDE_LINKS.en.flatMap(({ id }) => {
      const addresses = xmlAddresses(guideAlternates(origin, id));
      return GUIDE_LANGUAGES.map((locale) => ({ url: addresses[locale], lastModified: guideUpdated(locale, id), alternates: { languages: hrefLanguages(addresses) } }));
    }),
    ...CARRIER_LOCALES.map((locale) => ({ url: carriers[locale]!, lastModified: carriersChecked(locale), alternates: { languages: hrefLanguages(carriers) } })),
    ...CARRIER_LINKS.en.flatMap(({ id }) => {
      const addresses = xmlAddresses(carrierAlternates(origin, id));
      return carrierLanguages(id).map((locale) => ({ url: addresses[locale]!, lastModified: carrierUpdated(locale, id), alternates: { languages: hrefLanguages(addresses) } }));
    }),
  ];
}

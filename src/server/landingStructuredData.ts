import 'server-only';
import { hrefLanguage, SUPPORTED_LOCALES, type Locale } from '../lib/locale';
import { SOURCE_URL } from '../lib/source';
import { siteTitle, wordsIn } from './sitePreview';

/**
 * Who makes Peek, as schema.org data: one node that the landing, the guides and
 * the carrier pages all name, with the public source as the place it is also
 * found, so "Peek" reads as this Peek and no other.
 */
export function peekOrganization(origin: URL) {
  return {
    '@type': 'Organization',
    '@id': `${origin.href}#organization`,
    name: 'Peek',
    url: origin.href,
    logo: { '@type': 'ImageObject', url: new URL('/icons/icon-512.png', origin).href },
    sameAs: [SOURCE_URL],
  };
}

/**
 * What the landing tells search engines, as schema.org data: the site with its
 * names, the web application at this address, described as the page describes
 * it, free, and who makes them. No rating, no count, nothing a reader could not
 * check.
 */
export function landingStructuredData({ origin, url, locale }: {
  /** The origin the site's addresses are written on. */
  origin: URL;
  /** The address of the page that carries the data. */
  url: string;
  locale: Locale;
}) {
  const t = wordsIn(locale);
  return {
    '@context': 'https://schema.org',
    '@graph': [
      {
        '@type': 'WebSite',
        name: t('app.title'),
        // The names people search for: the product's, and the host's as they type it.
        alternateName: ['Peek Tracker', origin.hostname],
        url: origin.href,
        inLanguage: SUPPORTED_LOCALES.map(hrefLanguage),
        publisher: { '@id': `${origin.href}#organization` },
      },
      {
        '@type': 'WebApplication',
        name: t('app.title'),
        alternateName: siteTitle(t),
        url,
        description: t('preview.landing.description'),
        applicationCategory: 'UtilitiesApplication',
        operatingSystem: 'Web, iOS',
        inLanguage: hrefLanguage(locale),
        isAccessibleForFree: true,
        offers: { '@type': 'Offer', price: '0', priceCurrency: 'EUR' },
      },
      peekOrganization(origin),
    ],
  };
}

/**
 * JSON for a `<script>` data block. `<`, `>` and `&` are written as JSON
 * escapes, so no value can close the element or open a comment in it.
 */
export function jsonForScript(value: unknown): string {
  return JSON.stringify(value).replace(/[<>&]/g, (character) => `\\${'u'}${character.charCodeAt(0).toString(16).padStart(4, '0')}`);
}

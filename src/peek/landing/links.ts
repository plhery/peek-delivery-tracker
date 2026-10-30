import type { MouseEvent } from 'react';
import type { Locale } from '../../i18n';
import { DEMO_PATH } from '../../lib/experience';

export { SOURCE_URL } from '../../lib/source';

/** Who makes Peek, on X. */
export const AUTHOR_URL = 'https://x.com/plhery';

/** The language a site written in `languages` (and English) speaks to a reader of `locale`: Portuguese to a Brazilian. */
function siteLanguage(locale: Locale, languages: readonly string[]): string {
  const language = locale === 'pt-br' ? 'pt' : locale;
  return languages.includes(language) ? language : 'en';
}

/**
 * What else its author makes, each at its own address in the reader's language where it is written in it, else in
 * English. ScootRadar's English is at its bare domain.
 */
export const OTHER_SITES = {
  horoscope: { name: 'X Horoscope', address: (locale: Locale) => `https://x-horoscope.com/${siteLanguage(locale, ['de', 'fr', 'it', 'es', 'pt', 'pl', 'zh'])}/x` },
  monkey: { name: 'UnfollowMonkey', address: (locale: Locale) => `https://unfollow-monkey.com/${siteLanguage(locale, ['de', 'fr', 'it', 'es', 'pt', 'pl'])}` },
  scootradar: {
    name: 'ScootRadar',
    address: (locale: Locale) => {
      const language = siteLanguage(locale, ['de', 'fr', 'it', 'es', 'pt']);
      return language === 'en' ? 'https://scootradar.com' : `https://scootradar.com/${language}`;
    },
  },
} as const;

/** Opens the demo deliveries at their own address, without leaving the page. Back returns to where the visitor was. */
export function openDemo(): void {
  window.history.pushState(null, '', DEMO_PATH);
  window.dispatchEvent(new PopStateEvent('popstate'));
}

/** For a link inside the app: a plain click is followed in place, a modified one is left to the browser. */
export function followInPlace(event: MouseEvent<HTMLAnchorElement>, open: () => void): void {
  if (event.metaKey || event.ctrlKey || event.shiftKey || event.altKey || event.button !== 0) return;
  event.preventDefault();
  open();
}

/** For a link to the demo deliveries. */
export function followDemoLink(event: MouseEvent<HTMLAnchorElement>): void {
  followInPlace(event, openDemo);
}

import type { MouseEvent } from 'react';
import type { Locale } from '../../i18n';
import { DEMO_PATH } from '../../lib/experience';

export { SOURCE_URL } from '../../lib/source';
export { publicLink } from '../../lib/publicLink';

/** Who makes Peek, on X. */
export const AUTHOR_URL = 'https://x.com/plhery';

/**
 * What else its author makes, each at its own address for the reader's language. ScootRadar's English is at its bare
 * domain, and it has no Polish: a Polish reader gets its English.
 */
export const OTHER_SITES = {
  horoscope: { name: 'X Horoscope', address: (locale: Locale) => `https://x-horoscope.com/${locale}/x` },
  monkey: { name: 'UnfollowMonkey', address: (locale: Locale) => `https://unfollow-monkey.com/${locale}` },
  scootradar: {
    name: 'ScootRadar',
    address: (locale: Locale) => locale === 'en' || locale === 'pl' ? 'https://scootradar.com' : `https://scootradar.com/${locale}`,
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

import { ANDROID_APP_LINK } from './androidTest';
import { appStoreLink } from './appStore';

/**
 * `/app`, the address of the landing's code to scan: one code for both phones. The server sends a phone whose user
 * agent names it on to its app: an iPhone or an iPad to the App Store, an Android phone where the landing's "Get the
 * Android app" leads. Anyone else, a computer or an iPad whose Safari says it is a Mac, gets a page with both apps,
 * which sends an iPad on to the App Store itself.
 */
export const PHONE_APP_PATH = '/app';

/** The `utm_source` the landing's code carries; the App Store counts its scans as `web-qr`. */
export const PHONE_APP_QR_SOURCE = 'landing-qr';

/** A `utm_source` passed on as it came, when it is one a link of the site's own could carry. */
export function phoneAppSource(value: unknown): string | undefined {
  return typeof value === 'string' && /^[a-z0-9-]{1,40}$/.test(value) ? value : undefined;
}

/** The App Store, counted as the landing's code when it was scanned, and as `/app` otherwise. */
export function phoneAppStoreLink(source?: string): string {
  return appStoreLink(source === PHONE_APP_QR_SOURCE ? 'web-qr' : 'web-app');
}

/** Peek for Android. An address on this site carries the source on, for its page view to count. */
export function phoneAppAndroidLink(source?: string): string {
  return source && ANDROID_APP_LINK.startsWith('/') ? `${ANDROID_APP_LINK}?utm_source=${source}` : ANDROID_APP_LINK;
}

/** Where `/app` sends the phone a user agent names, or null when it names none the server can tell. */
export function phoneAppDestination(userAgent: string, source?: string): string | null {
  if (/\b(?:iPhone|iPad|iPod)\b/.test(userAgent)) return phoneAppStoreLink(source);
  if (/\bAndroid\b/.test(userAgent)) return phoneAppAndroidLink(source);
  return null;
}

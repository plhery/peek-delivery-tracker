import { androidAppLink, type AndroidApp } from './androidTest';
import { appStoreLink, type AppStoreApp, type AppStoreCampaign } from './appStore';

/**
 * `/app`, the address of the codes to scan and of Settings' app row: one address for both phones, on a site with an
 * iPhone app (`APP_STORE_ID`); on another it is the 404 page and nothing links it. The server sends a phone whose user
 * agent names it on to its app: an iPhone or an iPad to the App Store, an Android phone where the landing's "Get the
 * Android app" leads. Anyone else, a computer or an iPad whose Safari says it is a Mac, gets a page with both apps,
 * which sends an iPad on to the App Store itself.
 */
export const PHONE_APP_PATH = '/app';

/** The `utm_source` the landing's code carries; the App Store counts its scans as `web-qr`. */
export const PHONE_APP_QR_SOURCE = 'landing-qr';
/** The `utm_source` of the app row in Settings › Notifications on a phone or a tablet (`web-settings`)… */
export const PHONE_APP_SETTINGS_SOURCE = 'settings';
/** …and of the code a computer shows there instead (`web-settings-qr`). */
export const PHONE_APP_SETTINGS_QR_SOURCE = 'settings-qr';

/** The App Store campaign of each source of the site's own; any other source counts as `/app` itself, `web-app`. */
const STORE_CAMPAIGNS = new Map<string, AppStoreCampaign>([
  [PHONE_APP_QR_SOURCE, 'web-qr'],
  [PHONE_APP_SETTINGS_SOURCE, 'web-settings'],
  [PHONE_APP_SETTINGS_QR_SOURCE, 'web-settings-qr'],
]);

/** `/app`, with the source its link or code carries. */
export function phoneAppLink(source: string): string {
  return `${PHONE_APP_PATH}?utm_source=${source}`;
}

/** A `utm_source` passed on as it came, when it is one a link of the site's own could carry. */
export function phoneAppSource(value: unknown): string | undefined {
  return typeof value === 'string' && /^[a-z0-9-]{1,40}$/.test(value) ? value : undefined;
}

/** The App Store, counted under the campaign of the link or code that led to `/app`. */
export function phoneAppStoreLink(app: AppStoreApp, source?: string): string {
  return appStoreLink(app, (source && STORE_CAMPAIGNS.get(source)) || 'web-app');
}

/** The Android app, or null without one. An address on this site carries the source on, for its page view to count. */
export function phoneAppAndroidLink(app: AndroidApp | null, source?: string): string | null {
  const link = androidAppLink(app);
  return link && source && link.startsWith('/') ? `${link}?utm_source=${source}` : link;
}

/** Where `/app` sends the phone a user agent names, or null when it names none the server can tell, or none of the site's apps runs on it. */
export function phoneAppDestination(apps: { appStore: AppStoreApp; android: AndroidApp | null }, userAgent: string, source?: string): string | null {
  if (/\b(?:iPhone|iPad|iPod)\b/.test(userAgent)) return phoneAppStoreLink(apps.appStore, source);
  if (/\bAndroid\b/.test(userAgent)) return phoneAppAndroidLink(apps.android, source);
  return null;
}

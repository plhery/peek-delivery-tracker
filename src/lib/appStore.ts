/** Peek's iPhone app on the App Store. */
export const APP_STORE_ID = '6823615370';
/** The App Store Connect account a campaign link is counted under. */
const PROVIDER_TOKEN = '129628155';

/**
 * Where a link to the App Store sits, as App Store Connect's campaign report names it (30
 * characters at most). `web-banner` is Safari's banner, `web-qr` the code a computer shows on
 * the landing, scanned with an iPhone or iPad, and `web-app` that code's address, `/app`, reached
 * any other way. `web-notify` is the app row of a parcel page's "Notify me". In Settings ›
 * Notifications, `web-settings` is the app row an iPhone or iPad shows outside the Home
 * Screen, and `web-settings-qr` the code a computer shows under its switch. `web-invite` is
 * the button of the deliveries' invitation to notifications in the same place.
 */
export type AppStoreCampaign = 'web-banner' | 'web-more' | 'web-moves' | 'web-footer' | 'web-qr' | 'web-app' | 'web-notify' | 'web-settings' | 'web-settings-qr' | 'web-invite';

/** The app's page on the App Store, counted under a campaign. */
export function appStoreLink(campaign: AppStoreCampaign): string {
  return `https://apps.apple.com/app/apple-store/id${APP_STORE_ID}?pt=${PROVIDER_TOKEN}&ct=${campaign}&mt=8`;
}

/** An iPad, whose Safari says it is a Mac but takes more than one finger. */
export function onIPad(): boolean {
  return typeof navigator !== 'undefined'
    && (/iPad/.test(navigator.userAgent) || (navigator.platform === 'MacIntel' && navigator.maxTouchPoints > 1));
}

/**
 * Safari's Smart App Banner on an iPhone: it offers the app, or, where the app is installed,
 * opens it at `address`. Safari reads it once, from the page as the server sends it.
 */
export function smartAppBanner(address: string): Record<string, string> {
  return { 'apple-itunes-app': `app-id=${APP_STORE_ID}, app-argument=${address}, affiliate-data=pt=${PROVIDER_TOKEN}&ct=web-banner` };
}

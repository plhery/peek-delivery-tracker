/**
 * The site's iPhone app on the App Store (`APP_STORE_ID`), and the App Store Connect account a campaign link is
 * counted under (`APP_STORE_PROVIDER_TOKEN`), where the site has one. Without an app, nothing links the App Store.
 */
export interface AppStoreApp {
  id: string;
  providerToken: string | null;
}

/**
 * Where a link to the App Store sits, as App Store Connect's campaign report names it (30
 * characters at most). `web-banner` is Safari's banner, `web-qr` the code a computer shows on
 * the landing, scanned with an iPhone or iPad, and `web-app` that code's address, `/app`, reached
 * any other way. `web-notify` is the app row of a parcel page's "Notify me". In Settings ›
 * Notifications, `web-settings` is the app row an iPhone or iPad shows outside the Home
 * Screen, or a phone or tablet under its switch or in a browser that can't notify, and
 * `web-settings-qr` the code a computer shows there instead; those two go through `/app`. `web-invite` is
 * the button of the deliveries' invitation to notifications in the same place. `web-check` is the app row under a
 * failed browser check on a phone or a tablet, through `/app`.
 */
export type AppStoreCampaign = 'web-banner' | 'web-more' | 'web-moves' | 'web-footer' | 'web-qr' | 'web-app' | 'web-notify' | 'web-settings' | 'web-settings-qr' | 'web-invite' | 'web-check';

/** The app's page on the App Store, counted under a campaign where the account is known. */
export function appStoreLink(app: AppStoreApp, campaign: AppStoreCampaign): string {
  return app.providerToken
    ? `https://apps.apple.com/app/apple-store/id${app.id}?pt=${app.providerToken}&ct=${campaign}&mt=8`
    : `https://apps.apple.com/app/id${app.id}`;
}

/** An iPad, whose Safari says it is a Mac but takes more than one finger. */
export function onIPad(): boolean {
  return typeof navigator !== 'undefined'
    && (/iPad/.test(navigator.userAgent) || (navigator.platform === 'MacIntel' && navigator.maxTouchPoints > 1));
}

/**
 * Safari's Smart App Banner on an iPhone: it offers the app, or, where the app is installed,
 * opens it at `address`. Safari reads it once, from the page as the server sends it. None without an app.
 */
export function smartAppBanner(app: AppStoreApp | null, address: string): Record<string, string> {
  if (!app) return {};
  const affiliate = app.providerToken ? `, affiliate-data=pt=${app.providerToken}&ct=web-banner` : '';
  return { 'apple-itunes-app': `app-id=${app.id}, app-argument=${address}${affiliate}` };
}

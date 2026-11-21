/**
 * The settings Peek's own site runs with, all of them public: its name, its apps in the stores, its testers' group,
 * its F-Droid repository and releases, its author's links, its documents and its guides. A test that checks Peek's
 * own output sets these, and the browser journeys' server runs with them (playwright.config.ts); the code itself names
 * none of them.
 */
export const PEEK_ENV = {
  SITE_NAME: 'Peek',
  SITE_ALTERNATE_NAMES: 'Peek Tracker',
  APP_STORE_ID: '6823615370',
  APP_STORE_PROVIDER_TOKEN: '129628155',
  ANDROID_APP_PACKAGE: 'com.plhery.peekdeliverytracker',
  ANDROID_APP_CERT_SHA256: 'F1:55:DB:E7:76:F4:5F:EA:F2:51:4F:73:3B:98:4D:A3:FC:5F:56:2A:DA:66:F2:BD:65:1F:C5:89:D2:BD:DE:60,'
    + 'F4:33:CB:F0:3C:68:76:6D:1E:6B:32:1D:10:20:E8:FE:E5:1F:75:78:0B:56:C0:39:3F:F7:42:0F:93:10:3F:73',
  ANDROID_TEST_GROUP: 'https://groups.google.com/g/peek-android-testers',
  ANDROID_FDROID_REPO: 'https://raw.githubusercontent.com/plhery/peek-delivery-tracker/fdroid/repo'
    + '?fingerprint=2BA268AF2C76EFB2C262DA7CA8499284B7CC7D0F76DCBE47A8ABA5703067F1A7',
  ANDROID_RELEASES_URL: 'https://github.com/plhery/peek-delivery-tracker/releases',
  AUTHOR_LINKS: 'on',
  LEGAL_PAGES: 'on',
  GUIDE_PAGES: 'on',
} as const;

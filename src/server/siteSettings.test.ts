import { describe, expect, it } from 'vitest';
import { PLAIN_SITE } from '../lib/site';
import { UPSTREAM_SOURCE } from '../lib/source';
import { PEEK_ENV } from '../test/peekEnv';
import {
  androidStoreApp,
  appStoreApp,
  assistantServerTitle,
  authorLinksOn,
  legalPagesOn,
  privacyAddress,
  siteAlternateNames,
  siteName,
  siteSettings,
  siteUserAgent,
  sourceUrl,
} from './siteSettings';

const env = (values: Record<string, string>) => values as unknown as NodeJS.ProcessEnv;

describe('a site whose settings set nothing', () => {
  it('is a parcel tracker with no apps, no author and no documents, that links the code where it comes from', () => {
    expect(siteSettings(env({}))).toEqual(PLAIN_SITE);
    expect(PLAIN_SITE).toEqual({
      name: 'Parcel Tracker',
      guidePages: false,
      appStore: null,
      android: null,
      source: UPSTREAM_SOURCE,
      author: false,
      legalPages: false,
      privacy: null,
    });
    expect(siteAlternateNames(env({}))).toEqual([]);
    expect(siteUserAgent(env({}))).toBe('Parcel Tracker');
  });
});

describe('each setting', () => {
  it('reads the name, and the other names separated by commas', () => {
    expect(siteName(env({ SITE_NAME: '  Colis  ' }))).toBe('Colis');
    expect(siteName(env({ SITE_NAME: '   ' }))).toBe('Parcel Tracker');
    expect(siteAlternateNames(env({ SITE_NAME: 'Colis', SITE_ALTERNATE_NAMES: ' Colis Tracker, , Mes colis ' })))
      .toEqual(['Colis Tracker', 'Mes colis']);
  });

  it('names the assistants\' server after the site and what it does, saying it once', () => {
    expect(assistantServerTitle(env({ SITE_NAME: 'Peek' }))).toBe('Peek Parcel Tracker');
    expect(assistantServerTitle(env({}))).toBe('Parcel Tracker');
    expect(assistantServerTitle(env({ SITE_NAME: 'Acme parcel tracker' }))).toBe('Acme parcel tracker');
  });

  it('reads the App Store app by its number, with a provider token only when it is one', () => {
    expect(appStoreApp(env({ APP_STORE_ID: ' 123 ' }))).toEqual({ id: '123', providerToken: null });
    expect(appStoreApp(env({ APP_STORE_ID: '123', APP_STORE_PROVIDER_TOKEN: '456' }))).toEqual({ id: '123', providerToken: '456' });
    expect(appStoreApp(env({ APP_STORE_ID: '123', APP_STORE_PROVIDER_TOKEN: 'abc' }))).toEqual({ id: '123', providerToken: null });
    expect(appStoreApp(env({ APP_STORE_ID: 'id123' }))).toBeNull();
    expect(appStoreApp(env({ APP_STORE_PROVIDER_TOKEN: '456' }))).toBeNull();
  });

  it('reads the Android app by its package, keeping only the https addresses that go with it', () => {
    expect(androidStoreApp(env({ ANDROID_APP_PACKAGE: 'com.example.parcels' })))
      .toEqual({ package: 'com.example.parcels', testGroup: null, fdroid: null, releases: null });
    expect(androidStoreApp(env({
      ANDROID_APP_PACKAGE: 'com.example.parcels',
      ANDROID_TEST_GROUP: 'https://groups.example/testers',
      ANDROID_FDROID_REPO: 'http://fdroid.example/repo',
      ANDROID_RELEASES_URL: 'not an address',
    }))).toEqual({ package: 'com.example.parcels', testGroup: 'https://groups.example/testers', fdroid: null, releases: null });
    expect(androidStoreApp(env({ ANDROID_APP_PACKAGE: 'parcels', ANDROID_TEST_GROUP: 'https://groups.example/testers' }))).toBeNull();
    expect(androidStoreApp(env({ ANDROID_TEST_GROUP: 'https://groups.example/testers' }))).toBeNull();
  });

  it('reads the source as an https address without its trailing slash', () => {
    expect(sourceUrl(env({ SOURCE_URL: 'https://git.example/parcels/' }))).toBe('https://git.example/parcels');
    expect(sourceUrl(env({ SOURCE_URL: 'http://git.example/parcels' }))).toBe(UPSTREAM_SOURCE);
  });

  it('turns the author links and the documents on with on, and nothing else', () => {
    for (const value of ['on', ' on ']) {
      expect(authorLinksOn(env({ AUTHOR_LINKS: value }))).toBe(true);
      expect(legalPagesOn(env({ LEGAL_PAGES: value }))).toBe(true);
    }
    for (const value of ['', 'true', '1', 'ON']) {
      expect(authorLinksOn(env({ AUTHOR_LINKS: value }))).toBe(false);
      expect(legalPagesOn(env({ LEGAL_PAGES: value }))).toBe(false);
    }
  });

  it('links the privacy notice the site names, else its own where it serves the documents', () => {
    expect(privacyAddress(env({ PRIVACY_URL: 'https://example.com/privacy' }))).toBe('https://example.com/privacy');
    expect(privacyAddress(env({ PRIVACY_URL: 'https://example.com/privacy', LEGAL_PAGES: 'on' }))).toBe('https://example.com/privacy');
    expect(privacyAddress(env({ LEGAL_PAGES: 'on' }))).toBe('/privacy.html');
    expect(privacyAddress(env({ PRIVACY_URL: 'mailto:privacy@example.com' }))).toBeNull();
  });

  it('names the server to other services by the site and its canonical origin', () => {
    expect(siteUserAgent(env({ SITE_NAME: 'Colis', CANONICAL_ORIGIN: 'https://colis.example' }))).toBe('Colis (https://colis.example)');
    expect(siteUserAgent(env({ SITE_NAME: 'Colis' }))).toBe('Colis');
  });
});

describe("Peek's own settings", () => {
  const peek = env({ ...PEEK_ENV, CANONICAL_ORIGIN: 'https://peektracker.com' });

  it('give back what the code named before they were settings', () => {
    expect(siteSettings(peek)).toEqual({
      name: 'Peek',
      guidePages: true,
      appStore: { id: '6823615370', providerToken: '129628155' },
      android: {
        package: 'com.plhery.peekdeliverytracker',
        testGroup: 'https://groups.google.com/g/peek-android-testers',
        fdroid: 'https://raw.githubusercontent.com/plhery/peek-delivery-tracker/fdroid/repo'
          + '?fingerprint=2BA268AF2C76EFB2C262DA7CA8499284B7CC7D0F76DCBE47A8ABA5703067F1A7',
        releases: 'https://github.com/plhery/peek-delivery-tracker/releases',
      },
      source: 'https://github.com/plhery/peek-delivery-tracker',
      author: true,
      legalPages: true,
      privacy: '/privacy.html',
    });
    expect(siteAlternateNames(peek)).toEqual(['Peek Tracker']);
  });

  it('name the server Peek, on its canonical origin', () => {
    expect(siteUserAgent(peek)).toBe('Peek (https://peektracker.com)');
  });
});

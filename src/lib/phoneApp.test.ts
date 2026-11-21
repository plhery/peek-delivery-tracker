import sharp from 'sharp';
import jsQR from 'jsqr';
import { describe, expect, it } from 'vitest';
import { PEEK_SITE } from '../test/site';
import { appStoreLink } from './appStore';
import {
  PHONE_APP_QR_SOURCE, PHONE_APP_SETTINGS_QR_SOURCE, PHONE_APP_SETTINGS_SOURCE, phoneAppDestination, phoneAppLink, phoneAppSource,
} from './phoneApp';

const IPHONE = 'Mozilla/5.0 (iPhone; CPU iPhone OS 19_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Mobile/15E148 Safari/604.1';
const IPAD_MOBILE = 'Mozilla/5.0 (iPad; CPU OS 19_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Mobile/15E148 Safari/604.1';
const ANDROID = 'Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Mobile Safari/537.36';
const ANDROID_WEBVIEW = 'Mozilla/5.0 (Linux; Android 14; Pixel 8 Build/AP2A; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/142.0.0.0 Mobile Safari/537.36';
/** An iPad's Safari by default, and a Mac's. */
const MAC = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Safari/605.1.15';
const WINDOWS = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36';

const store = PEEK_SITE.appStore!;
const peek = { appStore: store, android: PEEK_SITE.android };

describe('the code for both phones', () => {
  it('sends an iPhone or an iPad that says so to the App Store, counted as the landing’s code when scanned', () => {
    expect(phoneAppDestination(peek, IPHONE, PHONE_APP_QR_SOURCE)).toBe(appStoreLink(store, 'web-qr'));
    expect(phoneAppDestination(peek, IPAD_MOBILE, PHONE_APP_QR_SOURCE)).toBe(appStoreLink(store, 'web-qr'));
    expect(phoneAppDestination(peek, IPHONE)).toBe(appStoreLink(store, 'web-app'));
  });

  it('counts Settings’ row and code under their own campaigns, and any other source as `/app` itself', () => {
    expect(phoneAppDestination(peek, IPHONE, PHONE_APP_SETTINGS_SOURCE)).toBe(appStoreLink(store, 'web-settings'));
    expect(phoneAppDestination(peek, IPHONE, PHONE_APP_SETTINGS_QR_SOURCE)).toBe(appStoreLink(store, 'web-settings-qr'));
    expect(phoneAppDestination(peek, ANDROID, PHONE_APP_SETTINGS_QR_SOURCE)).toBe('/android?utm_source=settings-qr');
    for (const source of ['footer', 'constructor', '__proto__']) expect(phoneAppDestination(peek, IPHONE, source)).toBe(appStoreLink(store, 'web-app'));
    expect(phoneAppLink(PHONE_APP_SETTINGS_SOURCE)).toBe('/app?utm_source=settings');
  });

  it('sends an Android phone to the test’s steps, with the source for their page view', () => {
    expect(phoneAppDestination(peek, ANDROID, PHONE_APP_QR_SOURCE)).toBe('/android?utm_source=landing-qr');
    expect(phoneAppDestination(peek, ANDROID_WEBVIEW, PHONE_APP_QR_SOURCE)).toBe('/android?utm_source=landing-qr');
    expect(phoneAppDestination(peek, ANDROID)).toBe('/android');
  });

  it('leaves a computer, or an iPad whose Safari says it is a Mac, to the page', () => {
    expect(phoneAppDestination(peek, MAC, PHONE_APP_QR_SOURCE)).toBeNull();
    expect(phoneAppDestination(peek, WINDOWS)).toBeNull();
    expect(phoneAppDestination(peek, '')).toBeNull();
  });

  it('passes on only a source a link of the site’s own could carry', () => {
    expect(phoneAppSource('landing-qr')).toBe('landing-qr');
    for (const source of [undefined, '', ['landing-qr'], 'Landing', 'a b', 'x&utm_medium=y', 'a'.repeat(41)]) expect(phoneAppSource(source)).toBeUndefined();
  });

  it('sends an Android phone to Google Play once the test is closed, and nowhere on a site without an Android app', () => {
    const released = { appStore: store, android: { ...PEEK_SITE.android!, testGroup: null } };
    expect(phoneAppDestination(released, ANDROID, PHONE_APP_QR_SOURCE)).toBe('https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker');
    expect(phoneAppDestination({ appStore: store, android: null }, ANDROID)).toBeNull();
    expect(phoneAppDestination({ appStore: store, android: null }, IPHONE)).toBe(appStoreLink(store, 'web-app'));
  });

  it.each([['landing', PHONE_APP_QR_SOURCE], ['settings', PHONE_APP_SETTINGS_QR_SOURCE]])(
    'encodes the download address in the %s graphic', async (folder, source) => {
      const { data, info } = await sharp(`public/${folder}/app-qr.svg`, { density: 720 }).ensureAlpha().raw().toBuffer({ resolveWithObject: true });
      expect(jsQR(new Uint8ClampedArray(data), info.width, info.height)?.data).toBe(`https://peektracker.com/app?utm_source=${source}`);
    },
  );
});

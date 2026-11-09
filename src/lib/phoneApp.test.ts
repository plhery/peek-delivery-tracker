import { readFileSync } from 'node:fs';
import { describe, expect, it } from 'vitest';
import { appStoreLink } from './appStore';
import { PHONE_APP_QR_SOURCE, phoneAppDestination, phoneAppSource } from './phoneApp';

const IPHONE = 'Mozilla/5.0 (iPhone; CPU iPhone OS 19_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Mobile/15E148 Safari/604.1';
const IPAD_MOBILE = 'Mozilla/5.0 (iPad; CPU OS 19_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Mobile/15E148 Safari/604.1';
const ANDROID = 'Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Mobile Safari/537.36';
const ANDROID_WEBVIEW = 'Mozilla/5.0 (Linux; Android 14; Pixel 8 Build/AP2A; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/142.0.0.0 Mobile Safari/537.36';
/** An iPad's Safari by default, and a Mac's. */
const MAC = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Safari/605.1.15';
const WINDOWS = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36';

describe('the code for both phones', () => {
  it('sends an iPhone or an iPad that says so to the App Store, counted as the landing’s code when scanned', () => {
    expect(phoneAppDestination(IPHONE, PHONE_APP_QR_SOURCE)).toBe(appStoreLink('web-qr'));
    expect(phoneAppDestination(IPAD_MOBILE, PHONE_APP_QR_SOURCE)).toBe(appStoreLink('web-qr'));
    expect(phoneAppDestination(IPHONE)).toBe(appStoreLink('web-app'));
  });

  it('sends an Android phone to the test’s steps, with the source for their page view', () => {
    expect(phoneAppDestination(ANDROID, PHONE_APP_QR_SOURCE)).toBe('/android?utm_source=landing-qr');
    expect(phoneAppDestination(ANDROID_WEBVIEW, PHONE_APP_QR_SOURCE)).toBe('/android?utm_source=landing-qr');
    expect(phoneAppDestination(ANDROID)).toBe('/android');
  });

  it('leaves a computer, or an iPad whose Safari says it is a Mac, to the page', () => {
    expect(phoneAppDestination(MAC, PHONE_APP_QR_SOURCE)).toBeNull();
    expect(phoneAppDestination(WINDOWS)).toBeNull();
    expect(phoneAppDestination('')).toBeNull();
  });

  it('passes on only a source a link of the site’s own could carry', () => {
    expect(phoneAppSource('landing-qr')).toBe('landing-qr');
    for (const source of [undefined, '', ['landing-qr'], 'Landing', 'a b', 'x&utm_medium=y', 'a'.repeat(41)]) expect(phoneAppSource(source)).toBeUndefined();
  });

  it('draws the landing’s code for its address', () => {
    // The code itself is drawn once, by a QR encoder; its description names what it was drawn for.
    expect(readFileSync('public/landing/app-qr.svg', 'utf8')).toContain(`<desc>https://peektracker.com/app?utm_source=${PHONE_APP_QR_SOURCE}</desc>`);
  });
});

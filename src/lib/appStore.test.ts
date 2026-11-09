import { readFileSync } from 'node:fs';
import { describe, expect, it } from 'vitest';
import { appStoreLink, smartAppBanner } from './appStore';

describe('the App Store', () => {
  it('links the app’s page under the campaign each place on the web is counted as', () => {
    expect(appStoreLink('web-more')).toBe('https://apps.apple.com/app/apple-store/id6823615370?pt=129628155&ct=web-more&mt=8');
    expect(new URL(appStoreLink('web-notify')).searchParams.get('ct')).toBe('web-notify');
  });

  it('gives Safari’s banner the app, the address to open it at and the banner’s campaign', () => {
    expect(smartAppBanner('https://peek.example.test/p/abc')).toEqual({
      'apple-itunes-app': 'app-id=6823615370, app-argument=https://peek.example.test/p/abc, affiliate-data=pt=129628155&ct=web-banner',
    });
  });

  it('draws the code a computer shows in Settings for its campaign’s link', () => {
    // The code itself is drawn once, by a QR encoder; its description names what it was drawn for.
    const svg = readFileSync('public/settings/app-store-qr.svg', 'utf8');
    expect(svg).toContain(`<desc>${appStoreLink('web-settings-qr').replaceAll('&', '&amp;')}</desc>`);
  });
});

import { describe, expect, it } from 'vitest';
import { PEEK_SITE } from '../test/site';
import { appStoreLink, smartAppBanner } from './appStore';

const peek = PEEK_SITE.appStore!;

describe('the App Store', () => {
  it('links the app’s page under the campaign each place on the web is counted as', () => {
    expect(appStoreLink(peek, 'web-more')).toBe('https://apps.apple.com/app/apple-store/id6823615370?pt=129628155&ct=web-more&mt=8');
    expect(new URL(appStoreLink(peek, 'web-notify')).searchParams.get('ct')).toBe('web-notify');
  });

  it('gives Safari’s banner the app, the address to open it at and the banner’s campaign', () => {
    expect(smartAppBanner(peek, 'https://peek.example.test/p/abc')).toEqual({
      'apple-itunes-app': 'app-id=6823615370, app-argument=https://peek.example.test/p/abc, affiliate-data=pt=129628155&ct=web-banner',
    });
  });

  it('counts no campaign without a provider token, and offers no banner without an app', () => {
    const app = { id: '123', providerToken: null };
    expect(appStoreLink(app, 'web-more')).toBe('https://apps.apple.com/app/id123');
    expect(smartAppBanner(app, 'https://example.test/')).toEqual({ 'apple-itunes-app': 'app-id=123, app-argument=https://example.test/' });
    expect(smartAppBanner(null, 'https://example.test/')).toEqual({});
  });
});

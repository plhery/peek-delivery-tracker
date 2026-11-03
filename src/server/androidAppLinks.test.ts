import { afterEach, describe, expect, it, vi } from 'vitest';
import { GET } from '../../app/.well-known/assetlinks.json/route';
import { config } from '../../proxy';
import { androidApp, androidAssetLinks } from './androidAppLinks';

const env = (values: Record<string, string>) => values as unknown as NodeJS.ProcessEnv;
const fingerprint = 'AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89';
const other = '01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF';

afterEach(() => { vi.unstubAllEnvs(); });

describe('androidApp', () => {
  it("names Peek's app on Google Play when nothing is set", () => {
    const app = androidApp(env({}));
    expect(app?.package).toBe('com.plhery.peekdeliverytracker');
    expect(app?.fingerprints).toHaveLength(1);
    expect(app?.fingerprints[0]).toMatch(/^(?:[0-9A-F]{2}:){31}[0-9A-F]{2}$/);
  });

  it('prefers the configured app, with each fingerprint once and in capitals', () => {
    expect(androidApp(env({
      ANDROID_APP_PACKAGE: ' com.example.peek ',
      ANDROID_APP_CERT_SHA256: ` ${fingerprint.toLowerCase()} , ${other}, ${fingerprint} `,
    }))).toEqual({ package: 'com.example.peek', fingerprints: [fingerprint, other] });
  });

  it('has no app when what is set is half there or malformed', () => {
    expect(androidApp(env({ ANDROID_APP_PACKAGE: 'com.example.peek' }))).toBeNull();
    expect(androidApp(env({ ANDROID_APP_CERT_SHA256: fingerprint }))).toBeNull();
    expect(androidApp(env({ ANDROID_APP_PACKAGE: 'peek', ANDROID_APP_CERT_SHA256: fingerprint }))).toBeNull();
    expect(androidApp(env({ ANDROID_APP_PACKAGE: 'com.example.peek', ANDROID_APP_CERT_SHA256: 'AB:CD, ABCDEF' }))).toBeNull();
  });
});

describe('the asset links', () => {
  it("vouch for the app's links, and nothing else", () => {
    expect(androidAssetLinks(env({ ANDROID_APP_PACKAGE: 'com.example.peek', ANDROID_APP_CERT_SHA256: fingerprint }))).toEqual([{
      relation: ['delegate_permission/common.handle_all_urls'],
      target: { namespace: 'android_app', package_name: 'com.example.peek', sha256_cert_fingerprints: [fingerprint] },
    }]);
    expect(androidAssetLinks(env({ ANDROID_APP_PACKAGE: 'com.example.peek' }))).toBeNull();
  });

  it('are served as cacheable JSON, without a session', async () => {
    vi.stubEnv('ANDROID_APP_PACKAGE', 'com.example.peek');
    vi.stubEnv('ANDROID_APP_CERT_SHA256', fingerprint);
    const response = GET();
    expect(response.status).toBe(200);
    expect(response.headers.get('content-type')).toBe('application/json');
    expect(response.headers.get('cache-control')).toBe('public, max-age=3600');
    expect(response.headers.get('content-security-policy')).toBeNull();
    expect((await response.json())[0].target.sha256_cert_fingerprints).toEqual([fingerprint]);
  });

  it('do not exist when the configured app is malformed', async () => {
    vi.stubEnv('ANDROID_APP_PACKAGE', 'com.example.peek');
    vi.stubEnv('ANDROID_APP_CERT_SHA256', 'not-a-fingerprint');
    const response = GET();
    expect(response.status).toBe(404);
    expect(await response.text()).toBe('');
  });

  it('are left alone by the page proxy, which would mark them private and give them a page policy', () => {
    const pages = new RegExp(`^${config.matcher[0].source}$`);
    expect(pages.test('/.well-known/assetlinks.json')).toBe(false);
    // Anything else under .well-known is a page that does not exist.
    expect(pages.test('/.well-known/security.txt')).toBe(true);
    expect(pages.test('/p/k7Qm2xW9bTfR')).toBe(true);
  });
});

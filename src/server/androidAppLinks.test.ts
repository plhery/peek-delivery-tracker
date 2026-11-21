import { afterEach, describe, expect, it, vi } from 'vitest';
import { GET } from '../../app/.well-known/assetlinks.json/route';
import { config } from '../../proxy';
import { PEEK_ENV, stubPeekEnv } from '../test/peekEnv';
import { androidApp, androidAssetLinks } from './androidAppLinks';

const env = (values: Record<string, string>) => values as unknown as NodeJS.ProcessEnv;
const fingerprint = 'AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89';
const other = '01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF:01:23:45:67:89:AB:CD:EF';

afterEach(() => { vi.unstubAllEnvs(); });

describe('androidApp', () => {
  it('has none when nothing is set', () => {
    expect(androidApp(env({}))).toBeNull();
    expect(androidAssetLinks(env({}))).toBeNull();
  });

  it("names Peek's app with Peek's settings, as Google Play and the F-Droid repository sign it", () => {
    const app = androidApp(env({ ...PEEK_ENV }));
    expect(app?.package).toBe('com.plhery.peekdeliverytracker');
    // Google's app signing key, then Peek's upload key, which signs the fdroid build.
    expect(app?.fingerprints.map((value) => value.slice(0, 11))).toEqual(['F1:55:DB:E7', 'F4:33:CB:F0']);
    for (const value of app?.fingerprints ?? []) expect(value).toMatch(/^(?:[0-9A-F]{2}:){31}[0-9A-F]{2}$/);
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

  it("serve Peek's own, as they were before they were settings", async () => {
    stubPeekEnv();
    const response = GET();
    expect(response.status).toBe(200);
    expect(await response.json()).toEqual([{
      relation: ['delegate_permission/common.handle_all_urls'],
      target: {
        namespace: 'android_app',
        package_name: 'com.plhery.peekdeliverytracker',
        sha256_cert_fingerprints: [
          'F1:55:DB:E7:76:F4:5F:EA:F2:51:4F:73:3B:98:4D:A3:FC:5F:56:2A:DA:66:F2:BD:65:1F:C5:89:D2:BD:DE:60',
          'F4:33:CB:F0:3C:68:76:6D:1E:6B:32:1D:10:20:E8:FE:E5:1F:75:78:0B:56:C0:39:3F:F7:42:0F:93:10:3F:73',
        ],
      },
    }]);
  });

  it('do not exist where no app is set', async () => {
    vi.stubEnv('ANDROID_APP_PACKAGE', '');
    vi.stubEnv('ANDROID_APP_CERT_SHA256', '');
    const response = GET();
    expect(response.status).toBe(404);
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

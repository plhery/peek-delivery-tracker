import { afterEach, expect, it, vi } from 'vitest';
import { PEEK_ENV, stubPeekEnv } from '../test/peekEnv';

vi.mock('next/headers', () => ({
  headers: vi.fn(async () => new Headers({ 'accept-language': 'de' })),
  cookies: vi.fn(async () => ({ get: () => undefined })),
}));
vi.mock('next/server', async (original) => ({ ...await original<typeof import('next/server')>(), connection: vi.fn(async () => undefined) }));

afterEach(() => { vi.unstubAllEnvs(); });

it('is not found on a site without an Android app, or while its closed test has no testers’ group', async () => {
  const { default: page } = await import('../../app/android/page');
  await expect(page()).rejects.toThrow(/NEXT_HTTP_ERROR_FALLBACK;404/);
  vi.stubEnv('ANDROID_APP_PACKAGE', PEEK_ENV.ANDROID_APP_PACKAGE);
  await expect(page()).rejects.toThrow(/NEXT_HTTP_ERROR_FALLBACK;404/);
});

it('draws the steps in the request’s language while the test is open, and is never indexed', async () => {
  stubPeekEnv();
  const { default: page, generateMetadata } = await import('../../app/android/page');
  const element = await page();
  expect(element.props).toMatchObject({ initialLocale: 'de' });
  expect(await generateMetadata()).toEqual({ title: 'Peek — Teste Peek für Android', robots: { index: false, follow: false } });
});

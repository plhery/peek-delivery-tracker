import { afterEach, expect, it, vi } from 'vitest';

const switched = vi.hoisted(() => ({ open: false }));
vi.mock('../lib/androidTest', async (original) => ({
  ...await original<typeof import('../lib/androidTest')>(),
  get ANDROID_TEST_OPEN() { return switched.open; },
}));
vi.mock('next/headers', () => ({
  headers: vi.fn(async () => new Headers({ 'accept-language': 'de' })),
  cookies: vi.fn(async () => ({ get: () => undefined })),
}));
vi.mock('next/server', async (original) => ({ ...await original<typeof import('next/server')>(), connection: vi.fn(async () => undefined) }));

afterEach(() => { switched.open = false; });

it('is not found while the invitation is switched off', async () => {
  const { default: page } = await import('../../app/android/page');
  await expect(page()).rejects.toThrow(/NEXT_HTTP_ERROR_FALLBACK;404/);
});

it('draws the steps in the request’s language once it is on, and is never indexed', async () => {
  switched.open = true;
  const { default: page, generateMetadata } = await import('../../app/android/page');
  const element = await page();
  expect(element.props).toMatchObject({ initialLocale: 'de' });
  expect(await generateMetadata()).toEqual({ title: 'Peek — Teste Peek für Android', robots: { index: false, follow: false } });
});

import { expect, test, type Page } from '@playwright/test';

const IPHONE = 'Mozilla/5.0 (iPhone; CPU iPhone OS 19_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/19.0 Mobile/15E148 Safari/604.1';
const ANDROID = 'Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Mobile Safari/537.36';
const APP_STORE = 'https://apps.apple.com/app/apple-store/id6823615370?pt=129628155&ct=web-qr&mt=8';
/** The landing's code to scan. */
const CODE = '/app?utm_source=landing-qr';

// Where the code leads depends on the user agent alone, not on the screen.
test.skip(({ browserName, isMobile }) => browserName !== 'chromium' || isMobile, 'the same answer on every screen');

const errors = new WeakMap<Page, string[]>();
test.beforeEach(async ({ page }) => {
  errors.set(page, []);
  page.on('pageerror', (error) => errors.get(page)!.push(error.message));
  page.on('console', (message) => { if (message.type() === 'error') errors.get(page)!.push(message.text()); });
});
// A skipped test has no page of its own to check.
test.afterEach(async ({ page }) => { expect(errors.get(page) ?? []).toEqual([]); });

test('the code sends an iPhone to the App Store and an Android phone to the steps, an answer never stored', async ({ request, baseURL }) => {
  for (const [userAgent, destination] of [[IPHONE, APP_STORE], [ANDROID, new URL('/android?utm_source=landing-qr', baseURL).href]]) {
    const response = await request.get(CODE, { headers: { 'user-agent': userAgent }, maxRedirects: 0 });
    const location = new URL(response.headers().location ?? '', baseURL).href;
    expect([response.status(), location, response.headers()['cache-control']]).toEqual([302, destination, 'private, no-store']);
  }
});

test('a computer gets the code to scan and both apps', async ({ page }) => {
  await page.goto(CODE);
  await expect(page.getByRole('heading', { level: 1, name: 'Get the app' })).toBeVisible();
  await expect(page.getByText('Scan with your phone')).toBeVisible();
  await expect(page.locator('.phone-app__qr img')).toHaveJSProperty('complete', true);
  await expect(page.getByRole('link', { name: 'iPhone app' })).toHaveAttribute('href', APP_STORE);
  await expect(page.getByRole('link', { name: 'Android app' })).toHaveAttribute('href', '/android?utm_source=landing-qr');
});

test.describe('on an Android phone', () => {
  test.use({ userAgent: ANDROID });

  test('the code opens the test’s steps', async ({ page }) => {
    await page.goto(CODE);
    await expect(page.getByRole('heading', { level: 1, name: 'Try Peek for Android' })).toBeVisible();
    expect(new URL(page.url()).pathname + new URL(page.url()).search).toBe('/android?utm_source=landing-qr');
  });
});

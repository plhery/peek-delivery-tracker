import { expect, test, type Page } from '@playwright/test';

// The demo build keeps lookups in the browser; the number here is fictional.
const errors = new WeakMap<Page, string[]>();
test.beforeEach(async ({ page }) => {
  errors.set(page, []);
  page.on('pageerror', (error) => errors.get(page)!.push(error.message));
});
test.afterEach(async ({ page }) => { expect(errors.get(page)).toEqual([]); });

/**
 * What a browser translating the page does: each piece of text is swapped for a
 * node of the translator's own, and the text React wrote leaves the page. The
 * words stay as they were, so the page can still be found by them.
 */
const translate = (page: Page) => page.evaluate(() => {
  const walker = document.createTreeWalker(document.body, NodeFilter.SHOW_TEXT);
  const texts: Text[] = [];
  while (walker.nextNode()) if (walker.currentNode.nodeValue?.trim()) texts.push(walker.currentNode as Text);
  for (const text of texts) {
    if (text.parentElement?.closest('script, style')) continue;
    const font = document.createElement('font');
    font.textContent = text.nodeValue;
    text.replaceWith(font);
  }
  return texts.length;
});

test('a page the browser translates carries on, without the error screen', async ({ page }) => {
  await page.goto('/');
  await expect(page.getByText('No number handy? Tap Pip to open a sample.')).toBeVisible();
  // A browser translates the page React has taken over. Text swapped before hydration differs
  // from what the server sent, which React reports (#418) whatever happens next.
  await expect(page.getByRole('button', { name: 'Sign in', exact: true })).toBeEnabled();
  expect(await translate(page)).toBeGreaterThan(20);
  // The hint under Pip turns into another, in place of the text the translator took.
  await page.getByRole('link', { name: 'Open a sample parcel' }).click();
  await expect(page.getByText('Opening a sample…')).toBeVisible();
  await expect(page.getByText('Sample parcel', { exact: true })).toBeVisible();
  await expect(page.getByRole('heading', { name: 'Something went wrong.' })).toHaveCount(0);
});

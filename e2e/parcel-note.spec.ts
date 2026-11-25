import { expect, test, type Page } from '@playwright/test';
import { track } from './peek';

async function openDemo(page: Page) {
  await page.addInitScript(() => {
    localStorage.setItem('sdt.web.experience.v1', 'demo');
    localStorage.setItem('deliveryTrackerLocale', 'en');
  });
  await page.goto('/', { waitUntil: 'domcontentloaded' });
}

test('marks the parcel with a note in the list, shows the note on its page and keeps an edit', async ({ page }) => {
  await openDemo(page);
  const row = page.getByRole('button', { name: /^(?:Next up: )?Kind of Blue · vinyl 🎷, has a note —/ });
  await expect(row).toBeVisible();
  // The mark stays on the line of the name's last word.
  const mark = row.locator('.note-mark-join');
  await expect(mark).toContainText('🎷');
  await row.click();

  const card = page.getByRole('region', { name: 'Your note' });
  await expect(card).toContainText('Order RM-48213');
  await expect(card.getByRole('link', { name: 'round-midnight.example/returns' })).toHaveAttribute('href', 'https://round-midnight.example/returns');
  await expect(card.getByRole('button', { name: 'Photo' })).toBeVisible();
  // Never in the page's title.
  expect(await page.title()).not.toContain('RM-48213');

  await card.getByRole('button', { name: 'Edit note' }).click();
  const sheet = page.getByRole('dialog', { name: 'Your note' });
  const field = sheet.getByRole('textbox', { name: /^Note for / });
  await field.press('End');
  await field.pressSequentially(' Gift wrap.');
  await sheet.getByRole('button', { name: 'Save note' }).click();
  await expect(sheet).toHaveCount(0);
  await expect(card).toContainText('Gift wrap.');
  await expect(card.getByRole('button', { name: 'Edit note' })).toBeFocused();
});

test('opens a note’s photo full size, and its menu item sits under the name’s', async ({ page }) => {
  await openDemo(page);
  await page.getByRole('button', { name: /Kind of Blue · vinyl 🎷, has a note —/ }).click();
  const detail = page.locator('.detail--postcard');
  await detail.getByLabel('Parcel actions', { exact: true }).click();
  const menu = detail.locator('.detail__actions-menu');
  await expect(menu.getByRole('button').nth(0)).toHaveText('Edit parcel name');
  await expect(menu.getByRole('button').nth(1)).toHaveText('Edit note');
  await detail.getByLabel('Parcel actions', { exact: true }).click();

  await page.getByRole('region', { name: 'Your note' }).getByRole('button', { name: 'Photo' }).click();
  const viewer = page.getByRole('dialog', { name: 'Photo of the box' });
  await expect(viewer).toBeVisible();
  await expect(viewer.getByRole('link', { name: 'Save' })).toBeVisible();
  await page.keyboard.press('Escape');
  await expect(viewer).toHaveCount(0);
});

test('a guest keeps a note on its own lookup in this browser, across a reload', async ({ page }) => {
  await track(page, 'DEMOGLS20260009');
  await page.locator('.peekp-actions').getByRole('button', { name: 'Add a note' }).click();
  const sheet = page.getByRole('dialog', { name: 'Your note' });
  await expect(sheet).toContainText('Kept in this browser only.');
  await sheet.getByRole('textbox', { name: /^Note for / }).fill('Door code 4812');
  await sheet.getByRole('button', { name: 'Save note' }).click();
  await expect(sheet).toHaveCount(0);
  await page.reload();
  await expect(page.getByRole('region', { name: 'Your note' })).toContainText('Door code 4812');
  // The note never reaches the address or the title.
  expect(page.url()).not.toContain('4812');
  expect(await page.title()).not.toContain('4812');
});

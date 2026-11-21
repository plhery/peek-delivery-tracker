import { screen, waitFor, within } from '@testing-library/react';
import type { UserEvent } from '@testing-library/user-event';
import { SELECTABLE_CARRIERS } from '../lib/carriers';

/** The open carrier picker. */
export function carrierPicker(): HTMLElement {
  return screen.getByRole('dialog', { name: 'Carrier' });
}

/**
 * Waits until the open picker lists every carrier from A to Z: it draws the first rows at once and the rest a batch a frame.
 * Each batch is a transition that gives way to the wait's checks, which run when the list changes and otherwise once a
 * second. A check counts the rows letter by letter: a role query or a selector walks the whole list, thousands of rows,
 * each time it is asked, and on a busy machine running coverage those walks held the tests near their time limit.
 * Find the elements you need with role queries before the wait, while few rows are drawn.
 */
export async function everyCarrierListed(picker = carrierPicker()): Promise<void> {
  const list = picker.querySelector('[role="listbox"]');
  if (!list) throw new Error('The picker shows no list.');
  await waitFor(() => {
    let listed = 0;
    for (const group of list.children) for (const letter of group.children) {
      if (letter.hasAttribute('data-letter')) for (const row of letter.children) if (row.getAttribute('role') === 'option') listed++;
    }
    if (listed < SELECTABLE_CARRIERS.length) throw new Error('The A to Z list is still filling in.');
  }, { timeout: 15_000, interval: 1_000 });
}

/**
 * A group's rows with the names their `aria-labelledby` gives them, read in one pass over the DOM. A role query works
 * out every row's name and style, and over the whole A to Z list it held tests near their time limit under load.
 * Check the rows you keep with `toHaveAccessibleName`.
 */
export function listedCarriers(group: HTMLElement): { name: string; row: HTMLElement }[] {
  return [...group.querySelectorAll<HTMLElement>('[role="option"]')].map((row) => ({
    name: row.ownerDocument.getElementById(row.getAttribute('aria-labelledby') ?? '')?.textContent ?? '',
    row,
  }));
}

/** Opens the carrier picker with `opener`, searches for `name` and picks it. */
export async function pickCarrier(user: UserEvent, opener: HTMLElement, name: string): Promise<void> {
  await user.click(opener);
  const picker = await screen.findByRole('dialog', { name: 'Carrier' });
  // Pasted in one go: typing searches and redraws the results once per letter,
  // which held the tests that pick carriers close to their time limit under load.
  await user.click(within(picker).getByRole('combobox', { name: 'Search carriers' }));
  await user.paste(name);
  await user.click(within(picker).getByRole('option', { name }));
}

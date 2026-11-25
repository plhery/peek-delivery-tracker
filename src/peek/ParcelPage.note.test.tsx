import { fireEvent, render, screen, waitFor, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { MemoryIndexedDB } from '../test/memoryIndexedDB';
import { LINK_ID, OWNER_KEY, testView } from '../test/parcelLinks';
import { forgetAllLinkNotes } from './deviceNotes';
import type { ParcelLinkView } from './links';
import { deviceNoteFiles, resetDeviceNoteFilesForTests } from './noteFiles';
import { ParcelPage } from './ParcelPage';
import { forgetAllRecents, recentFor, rememberParcel } from './recents';

const mocks = vi.hoisted(() => ({ read: vi.fn() }));
vi.mock('./links', async (original) => ({ ...await original<typeof import('./links')>(), readParcelLink: mocks.read }));

function open(owner = true) {
  const shown = testView({ owner, parcel: { lastSyncedAt: new Date().toISOString(), expectedDelivery: '2099-01-05' } });
  const view: ParcelLinkView = { ...shown, link: { ...shown.link, gift: false, shared: true, alerts: { available: true, vapidPublicKey: null } } };
  history.replaceState(null, '', `/p/${LINK_ID}`);
  if (owner) rememberParcel({ id: LINK_ID, key: OWNER_KEY, view });
  mocks.read.mockResolvedValue(view);
  return render(<ParcelPage linkId={LINK_ID} />);
}
const actions = () => within(document.querySelector<HTMLElement>('.peekp-actions')!);

beforeEach(() => {
  mocks.read.mockReset();
  resetDeviceNoteFilesForTests();
  vi.stubGlobal('indexedDB', new MemoryIndexedDB());
  vi.stubGlobal('URL', Object.assign(URL, { createObjectURL: vi.fn(() => 'blob:http://localhost/photo'), revokeObjectURL: vi.fn() }));
});
afterEach(async () => {
  forgetAllRecents();
  forgetAllLinkNotes();
  await new Promise((resolve) => setTimeout(resolve, 20));
  resetDeviceNoteFilesForTests();
  vi.unstubAllGlobals();
  history.replaceState(null, '', '/');
});

describe("a guest's note on its own lookup", () => {
  it('is kept in this browser, beside the link, and says so', async () => {
    const user = userEvent.setup();
    open();
    await user.click(await actions().findByRole('button', { name: 'Add a note' }));
    const sheet = screen.getByRole('dialog', { name: 'Your note' });
    const field = within(sheet).getByRole('textbox', { name: /^Note for / });
    expect(field).toHaveAccessibleDescription('Kept in this browser only. Signing in moves it to your account; clearing the browser’s data deletes it.');
    await user.type(field, 'Door code 4812');
    await user.click(within(sheet).getByRole('button', { name: 'Save note' }));
    await waitFor(() => expect(screen.queryByRole('dialog', { name: 'Your note' })).toBeNull());
    expect(recentFor(LINK_ID)?.note).toBe('Door code 4812');
    const card = screen.getByRole('region', { name: 'Your note' });
    expect(card).toHaveTextContent('Door code 4812');
    expect(within(card).getByRole('button', { name: 'Edit note' })).toHaveFocus();
    expect(actions().getByRole('button', { name: 'Edit note' })).toBeVisible();
    // Never in the page's title.
    expect(document.title).not.toContain('Door code');
  });

  it('keeps its files in the browser’s IndexedDB', async () => {
    const user = userEvent.setup();
    open();
    await user.click(await actions().findByRole('button', { name: 'Add a note' }));
    const sheet = screen.getByRole('dialog', { name: 'Your note' });
    const chooser = sheet.querySelector<HTMLInputElement>('input[type="file"]')!;
    fireEvent.change(chooser, { target: { files: [new File(['%PDF'], 'Receipt.pdf', { type: 'application/pdf' })] } });
    await waitFor(() => expect(deviceNoteFiles().map((file) => [file.linkId, file.name])).toEqual([[LINK_ID, 'Receipt.pdf']]));
    expect(await within(sheet).findByRole('button', { name: 'Remove Receipt' })).toBeVisible();
    await user.click(within(sheet).getByRole('button', { name: 'Cancel' }));
    expect(await screen.findByRole('link', { name: 'Receipt, PDF' })).toHaveAttribute('href', 'blob:http://localhost/photo');
  });

  it('is not offered on a link someone else shared', async () => {
    open(false);
    expect(await screen.findByText('Shared with you · no account needed')).toBeVisible();
    expect(screen.queryByRole('button', { name: 'Add a note' })).toBeNull();
  });
});

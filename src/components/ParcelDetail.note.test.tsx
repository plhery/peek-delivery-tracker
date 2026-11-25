import { act, render, screen, waitFor, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { useState } from 'react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { testParcel } from '../test/parcelLinks';
import type { ParcelWithEvents } from '../types';
import { ParcelDetail } from './ParcelDetail';

const parcel = testParcel({ id: 'package-1', label: 'Kind of Blue' });

/** The page with the note's handlers, keeping the parcel as the account would. */
function open(values: Partial<ParcelWithEvents> = {}, withNote = true) {
  const saved = vi.fn<(note: string | null, keepalive?: boolean) => void>();
  const removed = vi.fn<(fileId: string, keepalive: boolean) => void>();
  function Page() {
    const [shown, setShown] = useState<ParcelWithEvents>({ ...parcel, ...values });
    return <ParcelDetail parcel={shown} onBack={vi.fn()} onRename={vi.fn()} onChangeCarrier={vi.fn()} onSetNotificationsMuted={vi.fn()}
      onRefresh={vi.fn()} onRestore={vi.fn()} onArchive={vi.fn()} onDelete={vi.fn()}
      {...(withNote ? {
        onSaveNote: async (_parcel: ParcelWithEvents, note: string | null, keepalive?: boolean) => {
          saved(note, keepalive);
          setShown((current) => {
            const next = { ...current, note: note ?? undefined };
            if (!note) delete next.note;
            return next;
          });
        },
        onAddNoteFile: vi.fn(async () => undefined),
        onRemoveNoteFile: async (_parcel: ParcelWithEvents, fileId: string, keepalive: boolean) => { removed(fileId, keepalive); },
      } : {})} />;
  }
  render(<Page />);
  return { saved, removed };
}

const menuItems = () => within(document.querySelector<HTMLElement>('.detail__actions-menu > div')!).getAllByRole('button').map((button) => button.textContent);

afterEach(() => {
  vi.useRealTimers();
});

describe("a parcel's note on its page", () => {
  it('is offered in the menu right under the name, and reads “Edit note” once there is one', async () => {
    const user = userEvent.setup();
    const { saved } = open();
    expect(menuItems().slice(0, 2)).toEqual(['Edit parcel name', 'Add a note']);
    await user.click(screen.getByLabelText('Parcel actions'));
    await user.click(screen.getByRole('button', { name: 'Add a note' }));
    const sheet = screen.getByRole('dialog', { name: 'Your note' });
    expect(within(sheet).getByText('Kind of Blue')).toBeVisible();
    await user.type(within(sheet).getByRole('textbox', { name: 'Note for Kind of Blue' }), 'Door code 4812');
    await user.click(within(sheet).getByRole('button', { name: 'Save note' }));
    await waitFor(() => expect(screen.queryByRole('dialog', { name: 'Your note' })).toBeNull());
    expect(saved).toHaveBeenCalledWith('Door code 4812', undefined);
    expect(menuItems().slice(0, 2)).toEqual(['Edit parcel name', 'Edit note']);
    const card = screen.getByRole('region', { name: 'Your note' });
    expect(card).toHaveTextContent('Door code 4812');
    expect(within(card).getByRole('button', { name: 'Edit note' })).toHaveFocus();
    // Before the tracking number and the facts.
    const information = document.querySelector('.detail__information')!;
    expect(card.compareDocumentPosition(information) & Node.DOCUMENT_POSITION_FOLLOWING).toBeTruthy();
  });

  it('gives the keyboard to Undo after a delete, then to the menu', async () => {
    const { saved } = open({ note: 'Door code 4812' });
    const user = userEvent.setup();
    await user.click(within(screen.getByRole('region', { name: 'Your note' })).getByRole('button', { name: 'Edit note' }));
    vi.useFakeTimers();
    act(() => { within(screen.getByRole('dialog', { name: 'Your note' })).getByRole('button', { name: 'Delete note' }).click(); });
    expect(screen.getByRole('button', { name: 'Undo' })).toHaveFocus();
    await act(async () => { vi.advanceTimersByTime(6_000); });
    await act(async () => { await vi.runOnlyPendingTimersAsync(); });
    expect(saved).toHaveBeenCalledWith(null, false);
    expect(screen.getByLabelText('Parcel actions')).toHaveFocus();
    expect(screen.queryByRole('region', { name: 'Your note' })).toBeNull();
  });

  it('is not offered where the page has nowhere to keep it', () => {
    open({}, false);
    expect(menuItems()).not.toContain('Add a note');
  });
});

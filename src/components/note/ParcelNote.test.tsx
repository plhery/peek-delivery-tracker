import { act, fireEvent, render, screen, waitFor, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { useMemo, useRef, useState } from 'react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { NoteFileError, type PreparedFile } from '../../lib/parcelNote';
import { noteFilesOf, ParcelNote, type NoteFile, type NoteStore } from './ParcelNote';

const photo = (id: string, name = 'Box.jpg', expiresAt?: string): NoteFile => ({
  id, name, contentType: 'image/jpeg', size: 140_000, url: `/api/files/${id}?v=full`, thumbnailUrl: `/api/files/${id}?v=thumb`,
  downloadUrl: `/api/files/${id}?v=full&download=1`, expiresAt,
});
const pdf = (id: string, name = 'Receipt.pdf'): NoteFile => ({
  id, name, contentType: 'application/pdf', size: 80_000, url: `/api/files/${id}?v=full`, thumbnailUrl: null, downloadUrl: `/api/files/${id}?v=full&download=1`,
});
const chosen = (name: string, type: string, size = 100) => {
  const file = new File([new Uint8Array(4)], name, { type });
  Object.defineProperty(file, 'size', { value: size });
  return file;
};

interface Options {
  text?: string;
  files?: NoteFile[];
  editing?: boolean;
  saveText?: (text: string | null) => Promise<void>;
  addFile?: (file: PreparedFile) => Promise<void>;
  reread?: () => NoteFile[];
}

/** A parcel's page around the note: its ••• menu, and a store that keeps the note as the account would. */
function setup({ text = '', files = [], editing = false, saveText, addFile, reread }: Options = {}) {
  const calls = {
    saveText: vi.fn<(text: string | null, keepalive?: boolean) => void>(),
    addFile: vi.fn<(name: string, size: number) => void>(),
    removeFile: vi.fn<(id: string, keepalive: boolean) => void>(),
    reread: vi.fn(),
  };
  let added = 0;
  function Page() {
    const [note, setNote] = useState(text);
    const [kept, setKept] = useState(files);
    const [open, setOpen] = useState(editing);
    const menu = useRef<HTMLButtonElement>(null);
    const store = useMemo<NoteStore>(() => ({
      async saveText(next, keepalive) {
        calls.saveText(next, keepalive);
        await saveText?.(next);
        setNote(next ?? '');
      },
      async addFile(file, onProgress) {
        calls.addFile(file.name, file.blob.size);
        await addFile?.(file);
        onProgress(file.blob.size);
        added += 1;
        setKept((current) => [...current, { ...photo(`added-${added}`, file.name), contentType: file.type }]);
      },
      async removeFile(id, keepalive) {
        calls.removeFile(id, keepalive);
        setKept((current) => current.filter((file) => file.id !== id));
      },
      async reread() {
        calls.reread();
        if (reread) setKept(reread());
      },
    }), []);
    return <>
      <button type="button" ref={menu}>Parcel actions</button>
      <button type="button" onClick={() => setOpen(true)}>Add a note</button>
      <ParcelNote text={note} files={kept} store={store} name="Kind of Blue" editing={open} onEditing={setOpen} fallbackFocus={menu} />
    </>;
  }
  render(<Page />);
  return calls;
}

const sheet = () => screen.getByRole('dialog', { name: 'Your note' });
const field = () => within(sheet()).getByRole('textbox', { name: 'Note for Kind of Blue' });
const chooser = () => sheet().querySelector<HTMLInputElement>('input[type="file"]')!;
const choose = (...picked: File[]) => fireEvent.change(chooser(), { target: { files: picked } });

let scrollHeight: PropertyDescriptor | undefined;
beforeEach(() => {
  scrollHeight = Object.getOwnPropertyDescriptor(HTMLElement.prototype, 'scrollHeight');
});

afterEach(() => {
  vi.useRealTimers();
  if (scrollHeight) Object.defineProperty(HTMLElement.prototype, 'scrollHeight', scrollHeight);
  else Reflect.deleteProperty(HTMLElement.prototype, 'scrollHeight');
  Reflect.deleteProperty(navigator, 'onLine');
});

/** A problem's line, whose file name is set apart within it. */
const line = (text: string) => (_: string, element: Element | null) =>
  element?.tagName === 'SPAN' && element.parentElement?.classList.contains('note-sheet__problem') === true && element.textContent === text;

describe("a parcel's note on its page", () => {
  it('shows nothing without a note, and the card with its words, links and files when there is one', () => {
    setup();
    expect(screen.queryByRole('region', { name: 'Your note' })).toBeNull();
  });

  it('makes web and e-mail addresses links, and shows photos and PDFs by name', () => {
    setup({ text: 'Exchange at little-forest.example/exchange or help@shop.example.', files: [photo('one'), pdf('two')] });
    const card = screen.getByRole('region', { name: 'Your note' });
    const web = within(card).getByRole('link', { name: 'little-forest.example/exchange' });
    expect(web).toHaveAttribute('href', 'https://little-forest.example/exchange');
    expect(web).toHaveAttribute('target', '_blank');
    expect(web).toHaveAttribute('rel', 'noopener noreferrer');
    const mail = within(card).getByRole('link', { name: 'help@shop.example' });
    expect(mail).toHaveAttribute('href', 'mailto:help@shop.example');
    expect(mail).not.toHaveAttribute('target');
    expect(within(card).getByRole('button', { name: 'Photo' })).toBeVisible();
    const receipt = within(card).getByRole('link', { name: 'Receipt, PDF' });
    expect(receipt).toHaveAttribute('href', '/api/files/two?v=full');
    expect(receipt).toHaveAttribute('target', '_blank');
    expect(within(card).getByRole('button', { name: 'Edit note' })).toBeVisible();
  });

  it('shows four lines, then More and Less', async () => {
    Object.defineProperty(HTMLElement.prototype, 'scrollHeight', {
      configurable: true, get(this: HTMLElement) { return this.classList.contains('note-card__text') ? 400 : 0; },
    });
    const user = userEvent.setup();
    setup({ text: 'A long note '.repeat(40) });
    const more = screen.getByRole('button', { name: 'Show the whole note' });
    expect(more).toHaveTextContent('More');
    expect(more).toHaveAttribute('aria-expanded', 'false');
    expect(document.querySelector('.note-card__text')).toHaveClass('note-card__text--clamped');
    await user.click(more);
    expect(more).toHaveTextContent('Less');
    expect(more).toHaveAttribute('aria-expanded', 'true');
    expect(document.querySelector('.note-card__text')).not.toHaveClass('note-card__text--clamped');
  });
});

describe("the note's sheet", () => {
  it('opens on the parcel’s name with the cursor after the words, and says who can see the note', async () => {
    const user = userEvent.setup();
    setup({ text: 'Door code 4812' });
    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    expect(within(sheet()).getByText('Kind of Blue')).toHaveClass('sheet__eyebrow');
    expect(field()).toHaveFocus();
    expect(field()).toHaveValue('Door code 4812');
    expect((field() as HTMLTextAreaElement).selectionStart).toBe(14);
    expect(field()).toHaveAccessibleDescription('Only you can see it, even when you share the parcel.');
    expect(field()).toHaveAttribute('placeholder', 'What’s inside, an order number, a door code…');
    expect(within(sheet()).getByRole('button', { name: 'Delete note' })).toBeVisible();
  });

  it('saves with the button or Ctrl+Enter, then gives the keyboard to the pen', async () => {
    const user = userEvent.setup();
    const calls = setup({ editing: true });
    expect(within(sheet()).queryByRole('button', { name: 'Delete note' })).toBeNull();
    await user.type(field(), '  Blue vinyl, handle with care  ');
    await user.keyboard('{Control>}{Enter}{/Control}');
    await waitFor(() => expect(screen.queryByRole('dialog')).toBeNull());
    expect(calls.saveText).toHaveBeenCalledWith('Blue vinyl, handle with care', undefined);
    expect(screen.getByRole('region', { name: 'Your note' })).toHaveTextContent('Blue vinyl, handle with care');
    expect(screen.getByRole('button', { name: 'Edit note' })).toHaveFocus();

    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    await user.type(field(), ' (gift)');
    await user.click(within(sheet()).getByRole('button', { name: 'Save note' }));
    await waitFor(() => expect(screen.queryByRole('dialog')).toBeNull());
    expect(calls.saveText).toHaveBeenLastCalledWith('Blue vinyl, handle with care (gift)', undefined);
  });

  it('drops typed words on Cancel and Escape', async () => {
    const user = userEvent.setup();
    const calls = setup({ text: 'Door code 4812' });
    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    await user.type(field(), ' and more');
    await user.click(within(sheet()).getByRole('button', { name: 'Cancel' }));
    expect(screen.queryByRole('dialog')).toBeNull();
    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    expect(field()).toHaveValue('Door code 4812');
    await user.type(field(), ' again');
    await user.keyboard('{Escape}');
    expect(screen.queryByRole('dialog')).toBeNull();
    expect(calls.saveText).not.toHaveBeenCalled();
    expect(screen.getByRole('region', { name: 'Your note' })).toHaveTextContent(/^Your noteDoor code 4812$/);
  });

  it('counts the last hundred characters, says the count once typing pauses, and stops at 1,000', async () => {
    const user = userEvent.setup();
    setup({ editing: true });
    fireEvent.change(field(), { target: { value: 'a'.repeat(899) } });
    expect(within(sheet()).queryByText(/characters? left/)).toBeNull();
    fireEvent.change(field(), { target: { value: 'a'.repeat(960) } });
    expect(within(sheet()).getByText('40 characters left')).toHaveAttribute('aria-hidden', 'true');
    const spoken = sheet().querySelector('[aria-live="polite"]')!;
    expect(spoken).toHaveTextContent('');
    await waitFor(() => expect(spoken).toHaveTextContent('40 characters left'));
    fireEvent.change(field(), { target: { value: `${'a'.repeat(999)}📦📦` } });
    expect(Array.from((field() as HTMLTextAreaElement).value)).toHaveLength(1000);
    expect(within(sheet()).getByText('0 characters left')).toBeVisible();
    await user.type(field(), 'b');
    expect(Array.from((field() as HTMLTextAreaElement).value)).toHaveLength(1000);
  });

  it('keeps the sheet and what was written when the note could not be saved', async () => {
    const user = userEvent.setup();
    setup({ editing: true, saveText: async () => { throw new Error('offline'); } });
    await user.type(field(), 'Door code');
    await user.click(within(sheet()).getByRole('button', { name: 'Save note' }));
    expect(await within(sheet()).findByRole('alert')).toHaveTextContent('Couldn’t save the note. Try again.');
    expect(field()).toHaveValue('Door code');
  });
});

describe('deleting a note', () => {
  it('removes the words and files at once, gives the keyboard to Undo, and sends the delete when its toast ends', async () => {
    const calls = setup({ text: 'Door code 4812', files: [photo('one')] });
    fireEvent.click(screen.getByRole('button', { name: 'Edit note' }));
    vi.useFakeTimers();
    fireEvent.click(within(sheet()).getByRole('button', { name: 'Delete note' }));
    expect(screen.queryByRole('dialog')).toBeNull();
    expect(screen.queryByRole('region', { name: 'Your note' })).toBeNull();
    expect(screen.getByRole('status')).toHaveTextContent('Note deleted.');
    const undo = screen.getByRole('button', { name: 'Undo' });
    expect(undo).toHaveFocus();
    expect(calls.saveText).not.toHaveBeenCalled();

    await act(async () => { vi.advanceTimersByTime(6_000); });
    await act(async () => { await vi.runOnlyPendingTimersAsync(); });
    expect(calls.saveText).toHaveBeenCalledWith(null, false);
    expect(calls.removeFile).toHaveBeenCalledWith('one', false);
    expect(screen.queryByRole('button', { name: 'Undo' })).toBeNull();
    // Undo has gone with the keyboard on it: the page's menu takes it.
    expect(screen.getByRole('button', { name: 'Parcel actions' })).toHaveFocus();
  });

  it('brings everything back with Undo, and sends nothing', async () => {
    const user = userEvent.setup();
    const calls = setup({ text: 'Door code 4812', files: [photo('one')] });
    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    await user.click(within(sheet()).getByRole('button', { name: 'Delete note' }));
    await user.click(screen.getByRole('button', { name: 'Undo' }));
    expect(screen.getByRole('region', { name: 'Your note' })).toHaveTextContent('Door code 4812');
    expect(screen.getByRole('button', { name: 'Photo' })).toBeVisible();
    expect(calls.saveText).not.toHaveBeenCalled();
    expect(calls.removeFile).not.toHaveBeenCalled();
  });

  it('deletes a note saved with no words and no files', async () => {
    const user = userEvent.setup();
    const calls = setup({ text: 'Door code 4812' });
    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    await user.clear(field());
    await user.click(within(sheet()).getByRole('button', { name: 'Save note' }));
    expect(screen.queryByRole('dialog')).toBeNull();
    expect(screen.getByRole('status')).toHaveTextContent('Note deleted.');
    expect(screen.getByRole('button', { name: 'Undo' })).toHaveFocus();
    // Leaving the page sends the delete at once, as the page goes.
    act(() => { window.dispatchEvent(new Event('pagehide')); });
    await waitFor(() => expect(calls.saveText).toHaveBeenCalledWith(null, true));
  });

  it('keeps a note whose words go while its files stay', async () => {
    const user = userEvent.setup();
    const calls = setup({ text: 'Door code 4812', files: [photo('one')] });
    await user.click(screen.getByRole('button', { name: 'Edit note' }));
    await user.clear(field());
    await user.click(within(sheet()).getByRole('button', { name: 'Save note' }));
    await waitFor(() => expect(screen.queryByRole('dialog')).toBeNull());
    expect(calls.saveText).toHaveBeenCalledWith(null, undefined);
    expect(screen.getByRole('region', { name: 'Your note' })).toBeVisible();
    expect(screen.getByRole('button', { name: 'Photo' })).toBeVisible();
  });
});

describe("a note's files", () => {
  it('uploads a chosen file at once, and removes one with Undo, sending the removal when the page goes', async () => {
    const user = userEvent.setup();
    const calls = setup({ text: 'Door code', files: [photo('one')], editing: true });
    expect(chooser()).toHaveAttribute('accept', 'image/jpeg,image/png,image/webp,image/heic,image/heif,application/pdf');
    expect(chooser()).toHaveAttribute('multiple');
    choose(chosen('Receipt.pdf', 'application/pdf', 2_000));
    await waitFor(() => expect(calls.addFile).toHaveBeenCalledWith('Receipt.pdf', 2_000));
    await waitFor(() => expect(within(sheet()).getByRole('button', { name: 'Remove Receipt' })).toBeVisible());

    await user.click(within(sheet()).getByRole('button', { name: 'Remove Box' }));
    expect(within(sheet()).queryByRole('button', { name: 'Remove Box' })).toBeNull();
    expect(within(sheet()).getByRole('status')).toHaveTextContent('Box removed');
    await user.click(within(sheet()).getByRole('button', { name: 'Undo' }));
    expect(within(sheet()).getByRole('button', { name: 'Remove Box' })).toBeVisible();
    // Undo went with the keyboard on it: the button that adds files takes it.
    expect(within(sheet()).getByRole('button', { name: 'Add a photo or file' })).toHaveFocus();

    await user.click(within(sheet()).getByRole('button', { name: 'Remove Box' }));
    act(() => { window.dispatchEvent(new Event('pagehide')); });
    await waitFor(() => expect(calls.removeFile).toHaveBeenCalledWith('one', true));
    // Cancel doesn't bring a file back.
    await user.click(within(sheet()).getByRole('button', { name: 'Cancel' }));
    expect(screen.queryByRole('button', { name: 'Photo' })).toBeNull();
  });

  it('says in one line why a file did not join the note', async () => {
    const user = userEvent.setup();
    let refuse: NoteFileError | Error | null = null;
    const calls = setup({ editing: true, addFile: async () => { if (refuse) throw refuse; } });
    choose(chosen('Song.mp3', 'audio/mpeg'));
    expect(await within(sheet()).findByText('Peek takes photos and PDFs.')).toBeVisible();
    choose(chosen('Return label.pdf', 'application/pdf', 24 * 1_048_576));
    expect(await within(sheet()).findByText(line('Return label.pdf is 24 MB. Files can be up to 10 MB.'))).toBeVisible();
    expect(within(sheet()).getByText('Return label.pdf', { selector: 'strong' })).toBeVisible();
    expect(within(sheet()).queryByText('Peek takes photos and PDFs.')).toBeNull();

    refuse = new NoteFileError('account-full');
    choose(chosen('Receipt.pdf', 'application/pdf'));
    expect(await within(sheet()).findByText('Your 200 MB for photos and files is full. Remove some to add more.')).toBeVisible();

    // A failure another try may get past keeps the file, with its retry.
    refuse = new Error('network');
    choose(chosen('Receipt.pdf', 'application/pdf'));
    expect(await within(sheet()).findByText(line('Couldn’t add Receipt.pdf.'))).toBeVisible();
    refuse = null;
    await user.click(within(sheet()).getByRole('button', { name: 'Try again' }));
    await waitFor(() => expect(within(sheet()).queryByText(line('Couldn’t add Receipt.pdf.'))).toBeNull());
    expect(calls.addFile).toHaveBeenCalledTimes(3);
    expect(within(sheet()).getByRole('button', { name: 'Remove Receipt' })).toBeVisible();
  });

  it('waits for a connection when offline, and sends once back online', async () => {
    Object.defineProperty(navigator, 'onLine', { configurable: true, value: false });
    const calls = setup({ editing: true });
    choose(chosen('Receipt.pdf', 'application/pdf'));
    expect(await within(sheet()).findByText('You’re offline. Photos and files are added once you’re back online.')).toBeVisible();
    expect(calls.addFile).not.toHaveBeenCalled();
    Object.defineProperty(navigator, 'onLine', { configurable: true, value: true });
    act(() => { window.dispatchEvent(new Event('online')); });
    await waitFor(() => expect(calls.addFile).toHaveBeenCalledWith('Receipt.pdf', 100));
  });

  it('holds five files: the sixth, and a chooser with no room, say so', async () => {
    const user = userEvent.setup();
    const calls = setup({ editing: true, files: [photo('1'), photo('2'), photo('3'), photo('4')] });
    choose(chosen('Five.pdf', 'application/pdf'), chosen('Six.pdf', 'application/pdf'));
    expect(await within(sheet()).findByText('A parcel holds up to 5 photos or files.')).toBeVisible();
    await waitFor(() => expect(calls.addFile).toHaveBeenCalledTimes(1));
    const click = vi.spyOn(chooser(), 'click');
    await user.click(within(sheet()).getByRole('button', { name: 'Add a photo or file' }));
    expect(click).not.toHaveBeenCalled();
    expect(within(sheet()).getByText('A parcel holds up to 5 photos or files.')).toBeVisible();
  });

  it('takes files dropped on the sheet', async () => {
    const calls = setup({ editing: true });
    const dataTransfer = { types: ['Files'], files: [chosen('Receipt.pdf', 'application/pdf')], dropEffect: 'none' };
    fireEvent.dragOver(sheet(), { dataTransfer });
    expect(within(sheet()).getByText('Drop a photo or PDF here')).toBeVisible();
    fireEvent.drop(sheet(), { dataTransfer });
    await waitFor(() => expect(calls.addFile).toHaveBeenCalledWith('Receipt.pdf', 100));
    expect(within(sheet()).queryByText('Drop a photo or PDF here')).toBeNull();
  });
});

describe("a note's photo, full screen", () => {
  it('shows the photo with Save and Remove', async () => {
    const user = userEvent.setup();
    const calls = setup({ text: 'Door code', files: [photo('one')] });
    await user.click(screen.getByRole('button', { name: 'Photo' }));
    const viewer = screen.getByRole('dialog', { name: 'Box' });
    expect(within(viewer).getByText('Photo · 0.1 MB')).toBeVisible();
    expect(within(viewer).getByRole('img', { name: 'Box' })).toHaveAttribute('src', '/api/files/one?v=full');
    const save = within(viewer).getByRole('link', { name: 'Save' });
    expect(save).toHaveAttribute('href', '/api/files/one?v=full&download=1');
    expect(save).toHaveAttribute('download', 'Box.jpg');
    expect(within(viewer).getByRole('button', { name: 'Close' })).toHaveFocus();
    await user.click(within(viewer).getByRole('button', { name: 'Remove' }));
    expect(screen.queryByRole('dialog')).toBeNull();
    expect(screen.queryByRole('button', { name: 'Photo' })).toBeNull();
    expect(screen.getByRole('status')).toHaveTextContent('Box removed');
    await user.click(screen.getByRole('button', { name: 'Undo' }));
    expect(screen.getByRole('button', { name: 'Photo' })).toBeVisible();
    expect(calls.removeFile).not.toHaveBeenCalled();
  });

  it('reads the parcel again for a photo whose address has expired', async () => {
    const user = userEvent.setup();
    const calls = setup({
      text: 'Door code', files: [photo('one', 'Box.jpg', '2020-01-01T00:00:00Z')],
      reread: () => [{ ...photo('one', 'Box.jpg', '2099-01-01T00:00:00Z'), url: '/api/files/one?v=full&e=2' }],
    });
    await user.click(screen.getByRole('button', { name: 'Photo' }));
    const viewer = await screen.findByRole('dialog', { name: 'Box' });
    expect(calls.reread).toHaveBeenCalledTimes(1);
    expect(within(viewer).getByRole('img', { name: 'Box' })).toHaveAttribute('src', '/api/files/one?v=full&e=2');
  });
});

describe("an account's files", () => {
  it('save signed addresses with download=1, and the demo’s as they are', () => {
    const files = noteFilesOf([
      { id: 'a', name: 'Box.jpg', contentType: 'image/jpeg', size: 1, createdAt: '', url: '/api/files/a?v=full&e=1&s=x', thumbnailUrl: '/api/files/a?v=thumb', expiresAt: '2026-11-25T11:00:00Z' },
      { id: 'b', name: 'Receipt.pdf', contentType: 'application/pdf', size: 1, createdAt: '', url: 'data:application/pdf;base64,AA', thumbnailUrl: null, expiresAt: '2099-01-01T00:00:00Z' },
    ]);
    expect(files.map((file) => file.downloadUrl)).toEqual(['/api/files/a?v=full&e=1&s=x&download=1', 'data:application/pdf;base64,AA']);
    expect(files[0].expiresAt).toBe('2026-11-25T11:00:00Z');
  });
});

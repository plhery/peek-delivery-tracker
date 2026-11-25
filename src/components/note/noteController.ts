import { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import { trackAction } from '../../lib/analytics';
import {
  FILES_PER_PARCEL, NoteFileError, prepareNoteFile, retriable, UNDO_MS,
  type NoteFileProblem, type PreparedFile,
} from '../../lib/parcelNote';
import type { ParcelFileType } from '../../types';

/** A photo or PDF of a note, as its card and sheet show it, wherever it is kept. */
export interface NoteFile {
  id: string;
  name: string;
  contentType: ParcelFileType;
  size: number;
  url: string;
  /** None for a PDF. */
  thumbnailUrl: string | null;
  /** Where Save takes the file from: the same address, saved under the file's name. */
  downloadUrl: string;
  /** Past this moment its addresses no longer work, and the parcel is read again for new ones. */
  expiresAt?: string;
}

/** Where a note is kept: the account's parcel, the demo, or this browser for a guest's own lookup. */
export interface NoteStore {
  /** Saves the text; null deletes it. `keepalive` as the page goes away. */
  saveText(text: string | null, keepalive?: boolean): Promise<void>;
  addFile(file: PreparedFile, onProgress: (sent: number) => void, signal: AbortSignal): Promise<void>;
  /** `keepalive` as the page goes away. */
  removeFile(id: string, keepalive: boolean): Promise<void>;
  /** Reads the note again for addresses that have expired. */
  reread?(): Promise<void>;
}

/** A file on its way into the note, or one that did not make it, shown in the sheet's row. */
export interface NoteUpload {
  key: string;
  name: string;
  kind: 'photo' | 'pdf';
  /** The chosen photo itself, while it uploads. */
  preview: string | null;
  sent: number;
  total: number;
  problem?: NoteFileProblem;
  size?: number;
}

/** A problem said in a line of its own, with no file to show: another kind of file, or no room left. */
export interface NoteLine { key: string; problem: NoteFileProblem; name: string; size?: number }

export interface NoteToast { key: number; kind: 'file' | 'note' | 'failed'; name?: string }


/** The name people know a file by: without its extension. */
export const fileTitle = (name: string) => name.replace(/\.[a-z0-9]{2,5}$/i, '') || name;

const problemOf = (error: unknown): NoteFileProblem => error instanceof NoteFileError ? error.problem
  : typeof navigator !== 'undefined' && navigator.onLine === false ? 'offline' : 'failed';

const aborted = (error: unknown) => error instanceof DOMException && error.name === 'AbortError';

interface Undoable { id: number; timer: ReturnType<typeof setTimeout>; commit: (keepalive: boolean) => Promise<void>; undo: () => void }

/**
 * Everything a note does besides its text: files upload at once with their progress, a removal (a file, or the whole
 * note) waits six seconds for Undo and is sent when its toast ends, or at once as the page goes away.
 */
export function useNoteController({ text, files, store }: { text: string; files: readonly NoteFile[]; store: NoteStore }) {
  const [hidden, setHidden] = useState<{ text: boolean; files: ReadonlySet<string> }>({ text: false, files: new Set() });
  const [uploads, setUploads] = useState<NoteUpload[]>([]);
  const [lines, setLines] = useState<NoteLine[]>([]);
  const [toast, setToast] = useState<NoteToast | null>(null);
  const storeRef = useRef(store);
  const filesRef = useRef(files);
  const waiting = useRef<Undoable | null>(null);
  const chosen = useRef(new Map<string, { file: File; prepared?: PreparedFile; sending?: AbortController }>());
  const serial = useRef(0);
  useEffect(() => { storeRef.current = store; filesRef.current = files; }, [store, files]);

  const visibleFiles = useMemo(() => files.filter((file) => !hidden.files.has(file.id)), [files, hidden.files]);
  const visibleText = hidden.text ? '' : text;
  const busy = uploads.filter((upload) => !upload.problem).length;
  /** Room for more files, counting those on their way. */
  const room = FILES_PER_PARCEL - visibleFiles.length - uploads.length;

  const unhide = useCallback((ids: readonly string[], textToo: boolean) => setHidden((current) => {
    const next = new Set(current.files);
    for (const id of ids) next.delete(id);
    return { text: textToo ? false : current.text, files: next };
  }), []);

  /** Sends what waits for its toast to end, now. */
  const commitNow = useCallback((keepalive = false) => {
    const entry = waiting.current;
    if (!entry) return;
    waiting.current = null;
    clearTimeout(entry.timer);
    setToast((current) => current?.key === entry.id ? null : current);
    void entry.commit(keepalive).catch(() => entry.undo());
  }, []);

  const wait = useCallback((kind: NoteToast['kind'], name: string | undefined, commit: Undoable['commit'], undo: () => void) => {
    commitNow();
    const id = ++serial.current;
    const timer = setTimeout(() => {
      if (waiting.current?.id !== id) return;
      waiting.current = null;
      setToast((current) => current?.key === id ? null : current);
      void commit(false).catch(() => {
        undo();
        if (kind === 'note') setToast({ key: ++serial.current, kind: 'failed' });
      });
    }, UNDO_MS);
    waiting.current = { id, timer, commit, undo };
    setToast({ key: id, kind, name });
  }, [commitNow]);

  const undo = useCallback(() => {
    const entry = waiting.current;
    if (!entry) return;
    waiting.current = null;
    clearTimeout(entry.timer);
    entry.undo();
    setToast(null);
  }, []);

  // A page that goes away sends what waits; so does a page closed on the parcel.
  useEffect(() => {
    const leave = () => commitNow(true);
    window.addEventListener('pagehide', leave);
    return () => {
      window.removeEventListener('pagehide', leave);
      commitNow(true);
    };
  }, [commitNow]);

  // A failed toast says its piece and goes.
  useEffect(() => {
    if (toast?.kind !== 'failed') return;
    const timer = setTimeout(() => setToast((current) => current === toast ? null : current), UNDO_MS);
    return () => clearTimeout(timer);
  }, [toast]);

  const removeFile = useCallback((file: NoteFile) => {
    setHidden((current) => ({ ...current, files: new Set([...current.files, file.id]) }));
    wait('file', fileTitle(file.name), async (keepalive) => {
      await storeRef.current.removeFile(file.id, keepalive);
      trackAction('parcel-file-remove', 'success');
      unhide([file.id], false);
    }, () => unhide([file.id], false));
  }, [wait, unhide]);

  /** Deletes the text and every file at once, with one Undo for all of it. */
  const deleteNote = useCallback(() => {
    const ids = filesRef.current.map((file) => file.id);
    const hadText = Boolean(text.trim());
    setHidden({ text: true, files: new Set(ids) });
    wait('note', undefined, async (keepalive) => {
      if (hadText) await storeRef.current.saveText(null, keepalive);
      for (const id of ids) await storeRef.current.removeFile(id, keepalive);
      trackAction('parcel-note-delete', 'success');
      unhide(ids, true);
    }, () => unhide(ids, true));
  }, [text, wait, unhide]);

  const update = useCallback((key: string, changes: Partial<NoteUpload>) => {
    setUploads((current) => current.map((upload) => upload.key === key ? { ...upload, ...changes } : upload));
  }, []);

  const drop = useCallback((key: string) => {
    setUploads((current) => {
      const gone = current.find((upload) => upload.key === key);
      if (gone?.preview) URL.revokeObjectURL(gone.preview);
      return current.filter((upload) => upload.key !== key);
    });
    chosen.current.get(key)?.sending?.abort();
    chosen.current.delete(key);
  }, []);

  const fail = useCallback((key: string, error: unknown, name: string, size?: number) => {
    const problem = problemOf(error);
    trackAction('parcel-file-add', 'error', undefined, problem);
    // A file another try may get through stays in the row with its retry; any other never joined the note.
    if (!retriable(problem)) {
      drop(key);
      setLines((current) => [
        ...current.filter((line) => line.problem !== problem || (problem === 'too-big' && line.name !== name)),
        { key, problem, name, size: error instanceof NoteFileError ? error.size ?? size : size },
      ]);
      return;
    }
    update(key, { problem, size: error instanceof NoteFileError ? error.size ?? size : size, sent: 0 });
  }, [drop, update]);

  const send = useCallback(async (key: string) => {
    const entry = chosen.current.get(key);
    if (!entry) return;
    update(key, { problem: undefined, sent: 0 });
    try {
      entry.prepared ??= await prepareNoteFile(entry.file);
      const { prepared } = entry;
      update(key, { name: prepared.name, total: prepared.blob.size, kind: prepared.type === 'application/pdf' ? 'pdf' : 'photo' });
      if (typeof navigator !== 'undefined' && navigator.onLine === false) throw new NoteFileError('offline');
      const sending = new AbortController();
      entry.sending = sending;
      await storeRef.current.addFile(prepared, (sent) => update(key, { sent }), sending.signal);
      entry.sending = undefined;
      trackAction('parcel-file-add', 'success');
      drop(key);
    } catch (error) {
      entry.sending = undefined;
      if (aborted(error) || !chosen.current.has(key)) { drop(key); return; }
      fail(key, error, entry.prepared?.name ?? entry.file.name, entry.prepared?.blob.size ?? entry.file.size);
    }
  }, [update, drop, fail]);

  /** Adds chosen files, each uploading at once, as many as the note has room for. */
  const add = useCallback((picked: readonly File[]) => {
    setLines([]);
    let left = room;
    for (const file of picked) {
      if (left <= 0) {
        trackAction('parcel-file-add', 'error', undefined, 'parcel-full');
        setLines((current) => [...current, { key: `full-${++serial.current}`, problem: 'parcel-full', name: file.name }]);
        break;
      }
      left -= 1;
      const key = `upload-${++serial.current}`;
      const photo = file.type.startsWith('image/') && !/hei[cf]/i.test(file.type);
      chosen.current.set(key, { file });
      setUploads((current) => [...current, {
        key, name: file.name, kind: file.type === 'application/pdf' || /\.pdf$/i.test(file.name) ? 'pdf' : 'photo',
        preview: photo && typeof URL.createObjectURL === 'function' ? URL.createObjectURL(file) : null, sent: 0, total: file.size,
      }]);
      void send(key);
    }
  }, [room, send]);

  const retry = useCallback((key: string) => { void send(key); }, [send]);
  const dismiss = useCallback((key: string) => {
    drop(key);
    setLines((current) => current.filter((line) => line.key !== key));
  }, [drop]);
  const clearLines = useCallback(() => setLines([]), []);
  /** Says the note holds as many files as it can, for a chooser that would add none. */
  const refuseFull = useCallback(() => {
    trackAction('parcel-file-add', 'error', undefined, 'parcel-full');
    setLines([{ key: `full-${++serial.current}`, problem: 'parcel-full', name: '' }]);
  }, []);


  // Back online, what waited for a connection goes.
  useEffect(() => {
    const online = () => {
      for (const upload of uploads) if (upload.problem === 'offline') void send(upload.key);
    };
    window.addEventListener('online', online);
    return () => window.removeEventListener('online', online);
  }, [uploads, send]);

  // Chosen photos are let go with the page.
  const previews = useRef(uploads);
  useEffect(() => { previews.current = uploads; }, [uploads]);
  useEffect(() => () => {
    for (const upload of previews.current) if (upload.preview) URL.revokeObjectURL(upload.preview);
  }, []);

  /** Saves the text; a note left with no words and no files is deleted instead, with its Undo. */
  const saveText = useCallback(async (next: string): Promise<'saved' | 'deleted' | 'unchanged'> => {
    const trimmed = next.trim();
    if (!trimmed && !visibleFiles.length && !busy) {
      if (!text.trim()) return 'unchanged';
      deleteNote();
      return 'deleted';
    }
    if (trimmed === text.trim()) return 'unchanged';
    try {
      await storeRef.current.saveText(trimmed || null);
      trackAction('parcel-note-save', 'success');
      return 'saved';
    } catch (error) {
      trackAction('parcel-note-save', 'error');
      throw error;
    }
  }, [visibleFiles.length, busy, text, deleteNote]);

  /** The file with addresses that work: the note is read again once they have expired. */
  const fresh = useCallback(async (file: NoteFile): Promise<NoteFile> => {
    if (!file.expiresAt || Date.parse(file.expiresAt) > Date.now() + 5_000 || !storeRef.current.reread) return file;
    await storeRef.current.reread().catch(() => undefined);
    // The read lands with the next render.
    for (let tries = 0; tries < 20; tries += 1) {
      const found = filesRef.current.find((candidate) => candidate.id === file.id);
      if (!found || found.expiresAt !== file.expiresAt) return found ?? file;
      await new Promise((resolve) => setTimeout(resolve, 50));
    }
    return file;
  }, []);

  return {
    text: visibleText,
    files: visibleFiles,
    /** The card shows: there are words or files. */
    shown: Boolean(visibleText.trim()) || visibleFiles.length > 0,
    uploads,
    lines,
    toast,
    busy,
    add,
    retry,
    dismiss,
    clearLines,
    refuseFull,
    room,
    removeFile,
    deleteNote,
    saveText,
    undo,
    commitNow,
    fresh,
  };
}

export type NoteController = ReturnType<typeof useNoteController>;

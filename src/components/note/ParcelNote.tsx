import {
  useCallback, useEffect, useId, useLayoutEffect, useRef, useState,
  type DragEvent, type KeyboardEvent, type MouseEvent, type ReactNode, type RefObject,
} from 'react';
import { createPortal } from 'react-dom';
import { Icon } from '../Icon';
import { useI18n, type MessageKey } from '../../i18n';
import { useModalDialog, useSheetDialog } from '../../lib/modal';
import {
  clipNote, fileProblemLine, megabytes, NOTE_COUNT_FROM, NOTE_FILE_ACCEPT, NOTE_MAX_LENGTH, noteLength, notePieces, retriable,
} from '../../lib/parcelNote';
import { Glyph } from '../../peek/parcel/glyphs';
import { Toast } from '../../peek/parcel/Toast';
import type { ParcelFile } from '../../types';
import { fileTitle, useNoteController, type NoteController, type NoteFile, type NoteStore, type NoteUpload } from './noteController';
import './ParcelNote.css';

export type { NoteFile, NoteStore } from './noteController';

/** How long the count waits after the last key before a screen reader hears it. */
const COUNT_PAUSE_MS = 900;

/** An account's or the demo's files, as the note shows them. A signed address saves its file with `download=1`. */
export function noteFilesOf(files: readonly ParcelFile[] | undefined): NoteFile[] {
  return (files ?? []).map((file) => ({
    id: file.id,
    name: file.name,
    contentType: file.contentType,
    size: file.size,
    url: file.url,
    thumbnailUrl: file.thumbnailUrl,
    downloadUrl: file.url.startsWith('/api/') ? `${file.url}${file.url.includes('?') ? '&' : '?'}download=1` : file.url,
    expiresAt: file.expiresAt,
  }));
}

function Paperclip() {
  return <svg className="note-clip" viewBox="0 0 24 24" aria-hidden="true" focusable="false">
    <path d="M19.5 11.4 12.2 18.7a4.9 4.9 0 0 1-7-7l7.9-7.9a3.3 3.3 0 0 1 4.7 4.7l-7.9 7.9a1.6 1.6 0 0 1-2.3-2.3l7.3-7.3" />
  </svg>;
}

/** A PDF, drawn as a page with its badge. */
function PdfTile() {
  return <span className="note-thumb__pdf" aria-hidden="true">
    <span className="note-thumb__page"><i /><i /><i /><i /><i /></span>
    <span className="note-thumb__badge">PDF</span>
  </span>;
}

const isPdf = (file: Pick<NoteFile, 'contentType'>) => file.contentType === 'application/pdf';

/** A photo of the note, at the size its place gives it. */
function Picture({ src, alt = '', onClick }: { src: string; alt?: string; onClick?: (event: MouseEvent<HTMLImageElement>) => void }) {
  // eslint-disable-next-line @next/next/no-img-element -- the reader's own photo, from a signed address or the page itself
  return <img src={src} alt={alt} draggable={false} onClick={onClick} />;
}

/** Whether the browser can hand a file of this kind to another app. */
function canShareFile(file: NoteFile): boolean {
  try {
    return typeof navigator.share === 'function' && typeof navigator.canShare === 'function'
      && navigator.canShare({ files: [new File([], file.name, { type: file.contentType })] });
  } catch {
    return false;
  }
}

/** The name a screen reader gives a file's button: "Receipt, PDF" or "Photo". */
function fileLabel(file: NoteFile, t: ReturnType<typeof useI18n>['t']) {
  return isPdf(file) ? t('note.files.open', { name: fileTitle(file.name), kind: t('note.files.pdf') }) : t('note.files.photo');
}

/** The note's words, web and e-mail addresses made links, four lines and then More. */
export function NoteText({ text }: { text: string }) {
  const { t } = useI18n();
  const words = useRef<HTMLParagraphElement>(null);
  const [open, setOpen] = useState(false);
  const [long, setLong] = useState(false);
  // Four lines at the reader's text size: measured again whenever the card's width or the text size changes.
  useLayoutEffect(() => {
    const element = words.current;
    if (!element) return;
    const measure = () => {
      const style = getComputedStyle(element);
      const line = Number.parseFloat(style.lineHeight) || Number.parseFloat(style.fontSize) * 1.5 || 24;
      setLong(element.scrollHeight > line * 4 + 2);
    };
    measure();
    if (typeof ResizeObserver !== 'function') return;
    const observer = new ResizeObserver(measure);
    observer.observe(element);
    return () => observer.disconnect();
  }, [text]);
  return <>
    <p ref={words} className={`note-card__text${long && !open ? ' note-card__text--clamped' : ''}`}>
      {notePieces(text).map((piece, index) => piece.href
        ? <a key={index} href={piece.href} {...(piece.href.startsWith('mailto:') ? {} : { target: '_blank', rel: 'noopener noreferrer' })}>{piece.text}</a>
        : <span key={index}>{piece.text}</span>)}
    </p>
    {long && <button type="button" className="note-card__more" aria-expanded={open}
      aria-label={open ? undefined : t('note.moreLabel')} onClick={() => setOpen(!open)}>
      {t(open ? 'note.less' : 'note.more')}
    </button>}
  </>;
}

/** One file of the note: its thumbnail, or a PDF's page. A PDF opens in a tab of its own, a photo in the full view. */
function FileThumb({ file, onPhoto, onPdf, children }: {
  file: NoteFile;
  onPhoto: (file: NoteFile) => void;
  onPdf: (file: NoteFile, event: MouseEvent<HTMLAnchorElement>) => void;
  children?: ReactNode;
}) {
  const { t } = useI18n();
  const picture = isPdf(file) ? <PdfTile /> : <Picture src={file.thumbnailUrl ?? file.url} />;
  return <span className="note-thumb">
    {isPdf(file)
      ? <a className="note-thumb__open" href={file.url} target="_blank" rel="noopener noreferrer" aria-label={fileLabel(file, t)} onClick={(event) => onPdf(file, event)}>{picture}</a>
      : <button type="button" className="note-thumb__open" aria-label={fileLabel(file, t)} onClick={() => onPhoto(file)}>{picture}</button>}
    {children}
  </span>;
}

/** The note on the parcel's page: "Your note", its pen, its words and its files. */
function NoteCard({ note, pen, onEdit, onPhoto, onPdf }: {
  note: NoteController;
  pen: RefObject<HTMLButtonElement | null>;
  onEdit: () => void;
  onPhoto: (file: NoteFile) => void;
  onPdf: (file: NoteFile, event: MouseEvent<HTMLAnchorElement>) => void;
}) {
  const { t } = useI18n();
  const title = useId();
  return <section className="note-card" aria-labelledby={title}>
    <div className="note-card__head">
      <h2 className="note-card__title" id={title}>{t('note.title')}</h2>
      <button ref={pen} type="button" className="note-card__pen" aria-label={t('note.edit')} onClick={onEdit}><Glyph name="pencil" /></button>
    </div>
    {note.text.trim() && <NoteText text={note.text} />}
    {note.files.length > 0 && <div className="note-files">
      {note.files.map((file) => <FileThumb key={file.id} file={file} onPhoto={onPhoto} onPdf={onPdf} />)}
    </div>}
  </section>;
}

/** A file on its way: the chosen photo, or a PDF's page, with its progress or what stopped it. */
function UploadTile({ upload, onRetry, onDismiss }: { upload: NoteUpload; onRetry: () => void; onDismiss: () => void }) {
  const { t } = useI18n();
  const progress = upload.total > 0 ? Math.min(1, upload.sent / upload.total) : 0;
  return <span className={`note-thumb note-thumb--upload${upload.problem ? ' note-thumb--stopped' : ''}`}>
    <span className="note-thumb__open">
      {upload.kind === 'pdf' || !upload.preview ? <PdfTile /> : <Picture src={upload.preview} />}
    </span>
    {upload.problem
      ? <button type="button" className="note-thumb__retry" aria-label={`${t('note.files.retry')}: ${upload.name}`} onClick={onRetry}>
        <Glyph name={upload.problem === 'offline' ? 'offline' : 'warning'} />
      </button>
      : <span className="note-thumb__progress" role="progressbar" aria-label={t('note.files.uploading', { name: upload.name })}
        aria-valuemin={0} aria-valuemax={100} aria-valuenow={Math.round(progress * 100)}>
        <span style={{ width: `${Math.max(8, progress * 100)}%` }} />
      </span>}
    <button type="button" className="note-thumb__remove" aria-label={t('note.files.remove', { name: fileTitle(upload.name) })} onClick={onDismiss}>
      <Icon name="close" />
    </button>
  </span>;
}

/** A problem's line with the file's name in the ink of the page, as the pictures have it. */
function NamedLine({ text, name }: { text: string; name: string }) {
  const at = name ? text.indexOf(name) : -1;
  if (at < 0) return <span>{text}</span>;
  return <span>{text.slice(0, at)}<strong>{name}</strong>{text.slice(at + name.length)}</span>;
}

/** What a file's trouble says, under the row: one plain line each, with Try again where another try may work. */
function ProblemLines({ note }: { note: NoteController }) {
  const { t, languageTag } = useI18n();
  const stopped = note.uploads.filter((upload) => upload.problem);
  // Waiting for a connection is said once, whatever the number of files waiting.
  const offline = stopped.filter((upload) => upload.problem === 'offline');
  const failed = stopped.filter((upload) => upload.problem !== 'offline');
  if (!note.lines.length && !stopped.length) return null;
  return <div className="note-sheet__problems">
    {note.lines.map((line) => <p key={line.key} className="note-sheet__problem" role="alert">
      <Glyph name="warning" /><NamedLine text={fileProblemLine(line.problem, line.name, line.size, t, languageTag)} name={line.name} />
    </p>)}
    {offline.length > 0 && <p className="note-sheet__problem" role="alert">
      <Glyph name="offline" /><span>{fileProblemLine('offline', '', undefined, t, languageTag)}</span>
    </p>}
    {failed.map((upload) => <p key={upload.key} className="note-sheet__problem" role="alert">
      <Glyph name="warning" />
      <NamedLine text={fileProblemLine(upload.problem!, upload.name, upload.size, t, languageTag)} name={upload.name} />
      {retriable(upload.problem!) && <button type="button" className="note-sheet__retry" onClick={() => note.retry(upload.key)}>{t('note.files.retry')}</button>}
    </p>)}
  </div>;
}

/**
 * The Undo of a removal, or the line that says a delete didn't go through. `onGone` hands the keyboard on when the toast
 * goes while Undo holds it: read as it goes, while Undo is still in the page.
 */
function UndoToast({ note, undo, onGone }: { note: NoteController; undo: RefObject<HTMLButtonElement | null>; onGone: () => void }) {
  const { t } = useI18n();
  const toast = note.toast;
  const gone = useRef(onGone);
  useLayoutEffect(() => { gone.current = onGone; });
  const key = toast?.key;
  useLayoutEffect(() => {
    const button = undo.current;
    return () => { if (button && button === document.activeElement) gone.current(); };
  }, [key, undo]);
  if (!toast) return null;
  const text = toast.kind === 'note' ? t('note.deleted') : toast.kind === 'file' ? t('note.files.removed', { name: toast.name ?? '' }) : t('note.deleteFailed');
  return <Toast key={toast.key} mark={<Icon name="trash" />}
    action={toast.kind === 'failed' ? undefined : <button ref={undo} type="button" onClick={note.undo}>{t('app.undo')}</button>}>
    {text}
  </Toast>;
}

/** The note's sheet: the parcel's name over "Your note", the field, the lock line, the files, and its buttons. */
function NoteSheet({ note, name, lock, existing, onDismissed, onSaved, onDeleted, undo }: {
  note: NoteController;
  name: string;
  lock: MessageKey;
  existing: boolean;
  onDismissed: () => void;
  onSaved: () => void;
  onDeleted: () => void;
  undo: RefObject<HTMLButtonElement | null>;
}) {
  const { t } = useI18n();
  const field = useRef<HTMLTextAreaElement>(null);
  const chooser = useRef<HTMLInputElement>(null);
  const addButton = useRef<HTMLButtonElement>(null);
  const [draft, setDraft] = useState(note.text);
  const [saving, setSaving] = useState(false);
  const [failed, setFailed] = useState(false);
  const [dropping, setDropping] = useState(false);
  const [spoken, setSpoken] = useState('');
  const title = useId();
  const lockId = useId();
  const [dialog, dismiss] = useSheetDialog<HTMLDivElement>(true, onDismissed, field, saving);
  const left = NOTE_MAX_LENGTH - noteLength(draft);
  const count = left <= NOTE_COUNT_FROM ? t('note.left.many', { count: left }) : '';

  // The keyboard comes up with the cursor after the last word.
  useEffect(() => {
    const element = field.current;
    if (!element) return;
    element.setSelectionRange(element.value.length, element.value.length);
    element.scrollTop = element.scrollHeight;
  }, []);

  // A screen reader hears the count once typing pauses, not on every key.
  useEffect(() => {
    const timer = setTimeout(() => setSpoken(count), COUNT_PAUSE_MS);
    return () => clearTimeout(timer);
  }, [count]);

  async function save() {
    if (saving) return;
    setSaving(true);
    setFailed(false);
    try {
      const result = await note.saveText(draft);
      if (result === 'deleted') onDeleted();
      else onSaved();
    } catch {
      setFailed(true);
      setSaving(false);
    }
  }

  function keys(event: KeyboardEvent<HTMLTextAreaElement>) {
    if (event.key === 'Enter' && (event.metaKey || event.ctrlKey)) {
      event.preventDefault();
      void save();
    }
  }

  function choose() {
    if (note.room <= 0) { note.refuseFull(); return; }
    chooser.current?.click();
  }

  const carriesFiles = (event: DragEvent) => Array.from(event.dataTransfer?.types ?? []).includes('Files');
  function dragOver(event: DragEvent<HTMLDivElement>) {
    if (!carriesFiles(event)) return;
    event.preventDefault();
    event.dataTransfer.dropEffect = 'copy';
    setDropping(true);
  }
  function drop(event: DragEvent<HTMLDivElement>) {
    if (!carriesFiles(event)) return;
    event.preventDefault();
    setDropping(false);
    note.add(Array.from(event.dataTransfer.files));
  }

  const fileToast = note.toast?.kind === 'file' ? note.toast : null;

  return createPortal(
    <div className="sheet-backdrop" onClick={(event) => { event.stopPropagation(); dismiss(); }}
      onPointerDown={(event) => event.stopPropagation()} onPointerUp={(event) => event.stopPropagation()}>
      <div
        ref={dialog}
        className={`sheet note-sheet${dropping ? ' note-sheet--dropping' : ''}`}
        role="dialog"
        aria-modal="true"
        aria-labelledby={title}
        tabIndex={-1}
        onClick={(event) => event.stopPropagation()}
        onDragEnter={dragOver}
        onDragOver={dragOver}
        onDragLeave={(event) => { if (!event.currentTarget.contains(event.relatedTarget as Node | null)) setDropping(false); }}
        onDrop={drop}
      >
        <div className="sheet__grabber" aria-hidden="true" />
        <div className="sheet__heading">
          <div>
            <p className="sheet__eyebrow">{name}</p>
            <h2 className="sheet__title" id={title}>{t('note.title')}</h2>
          </div>
          <button type="button" className="sheet__close" aria-label={t('common.close')} onClick={dismiss}>×</button>
        </div>
        <textarea
          ref={field}
          className="field__input note-sheet__field"
          value={draft}
          rows={5}
          aria-label={t('note.fieldNamed', { name })}
          aria-describedby={lockId}
          placeholder={t('note.placeholder')}
          enterKeyHint="enter"
          onChange={(event) => { setDraft(clipNote(event.target.value)); setFailed(false); }}
          onKeyDown={keys}
        />
        <div className="note-sheet__lock">
          <Icon name="lock" />
          <span id={lockId}>{t(lock)}</span>
          {count && <span className="note-sheet__count" aria-hidden="true">{count}</span>}
        </div>
        <span className="sr-only" aria-live="polite">{spoken}</span>
        {(note.files.length > 0 || note.uploads.length > 0) && <div className="note-files note-files--sheet">
          {note.files.map((file) => <span key={file.id} className="note-thumb">
            <span className="note-thumb__open">{isPdf(file) ? <PdfTile /> : <Picture src={file.thumbnailUrl ?? file.url} />}</span>
            <button type="button" className="note-thumb__remove" aria-label={t('note.files.remove', { name: fileTitle(file.name) })}
              onClick={() => note.removeFile(file)}><Icon name="close" /></button>
          </span>)}
          {note.uploads.map((upload) => <UploadTile key={upload.key} upload={upload}
            onRetry={() => note.retry(upload.key)} onDismiss={() => note.dismiss(upload.key)} />)}
        </div>}
        <ProblemLines note={note} />
        <div className="note-sheet__add">
          <button ref={addButton} type="button" className="note-sheet__add-button" onClick={choose}><Paperclip />{t('note.files.add')}</button>
          <input ref={chooser} type="file" accept={NOTE_FILE_ACCEPT} multiple hidden tabIndex={-1}
            onChange={(event) => {
              const picked = Array.from(event.target.files ?? []);
              event.target.value = '';
              if (picked.length) note.add(picked);
            }} />
        </div>
        {failed && <p className="sheet__error" role="alert">{t('note.failed')}</p>}
        <div className="sheet__actions">
          <button type="button" className="button button--secondary" onClick={dismiss} disabled={saving}>{t('common.cancel')}</button>
          <button type="button" className="button button--primary" onClick={() => void save()} disabled={saving}>{t('note.save')}</button>
        </div>
        {existing && <button type="button" className="text-button note-sheet__delete" disabled={saving}
          onClick={() => { note.deleteNote(); onDeleted(); }}>{t('note.delete')}</button>}
        {dropping && <div className="note-sheet__drop" aria-hidden="true"><Paperclip /><span>{t('note.files.drop')}</span></div>}
        {fileToast && <div className="note-sheet__toast">
          {/* An Undo that goes while it holds the keyboard hands it to the button that adds files. */}
          <UndoToast note={note} undo={undo} onGone={() => addButton.current?.focus({ preventScroll: true })} />
        </div>}
      </div>
    </div>,
    document.body,
  );
}

/** A photo, full screen, with Share, Save and Remove. */
function NoteFileViewer({ file, onClose, onRemove }: { file: NoteFile; onClose: () => void; onRemove: () => void }) {
  const { t, languageTag } = useI18n();
  const close = useRef<HTMLButtonElement>(null);
  const dialog = useModalDialog<HTMLDivElement>(true, onClose, close);
  const title = useId();
  const shareable = canShareFile(file);

  async function share() {
    try {
      const blob = await (await fetch(file.url)).blob();
      await navigator.share({ files: [new File([blob], file.name, { type: file.contentType })] });
    } catch {
      // Closed, or the browser would not share it: Save is still there.
    }
  }

  return createPortal(
    <div ref={dialog} className="note-viewer" role="dialog" aria-modal="true" aria-labelledby={title} tabIndex={-1}
      onClick={(event) => event.stopPropagation()} onPointerDown={(event) => event.stopPropagation()} onPointerUp={(event) => event.stopPropagation()}>
      <div className="note-viewer__top">
        <button ref={close} type="button" className="note-viewer__close" aria-label={t('common.close')} onClick={onClose}><Icon name="close" /></button>
        <div className="note-viewer__title">
          <h2 id={title}>{fileTitle(file.name)}</h2>
          <p>{t('note.files.photo')} · {megabytes(file.size, languageTag, t)}</p>
        </div>
      </div>
      <div className="note-viewer__picture" onClick={onClose}>
        <Picture src={file.url} alt={fileTitle(file.name)} onClick={(event) => event.stopPropagation()} />
      </div>
      <div className="note-viewer__actions">
        {shareable && <button type="button" onClick={() => void share()}><Icon name="share" /><span>{t('note.files.share')}</span></button>}
        <a href={file.downloadUrl} download={file.name}><Icon name="download" /><span>{t('note.files.save')}</span></a>
        <button type="button" onClick={onRemove}><Icon name="trash" /><span>{t('note.files.removeShort')}</span></button>
      </div>
    </div>,
    document.body,
  );
}

/**
 * A parcel's private note, where its page shows it: the card (when there is a note), its sheet, the full view of its
 * photos and the Undo of what was removed. Who keeps it is the store's business: the account, the demo or the browser.
 */
export function ParcelNote({ text, files, store, name, lock = 'note.private', editing, onEditing, fallbackFocus, onBusy }: {
  text: string;
  files: readonly NoteFile[];
  store: NoteStore;
  /** The parcel's name, over the sheet's title. */
  name: string;
  /** Who can see the note, and where it is kept. */
  lock?: MessageKey;
  /** The sheet is open. */
  editing: boolean;
  onEditing: (editing: boolean) => void;
  /** Where the keyboard goes once a deleted note's Undo has gone: the page's ••• menu. */
  fallbackFocus?: RefObject<HTMLElement | null>;
  /** The sheet or the full view is open: the page keeps its own gestures to itself. */
  onBusy?: (busy: boolean) => void;
}) {
  const note = useNoteController({ text, files, store });
  const pen = useRef<HTMLButtonElement>(null);
  const undo = useRef<HTMLButtonElement>(null);
  const [viewing, setViewing] = useState<NoteFile | null>(null);
  // What the sheet was opened on decides whether it offers Delete note.
  const [openedOn, setOpenedOn] = useState<boolean | null>(null);
  if (editing && openedOn === null) setOpenedOn(note.shown);
  if (!editing && openedOn !== null) setOpenedOn(null);
  const closedWith = useRef<'saved' | 'deleted' | null>(null);
  const { commitNow } = note;

  useEffect(() => { onBusy?.(editing || viewing !== null); }, [editing, viewing, onBusy]);

  // A sheet opened while a deleted note waits for its Undo starts afresh: the delete goes now.
  useEffect(() => {
    if (!editing) return;
    commitNow();
  }, [editing, commitNow]);

  // After saving, the keyboard is back on the pen; after deleting, on Undo.
  useEffect(() => {
    if (editing) return;
    const closed = closedWith.current;
    closedWith.current = null;
    if (closed === 'saved') (pen.current ?? fallbackFocus?.current)?.focus({ preventScroll: true });
    if (closed === 'deleted') undo.current?.focus({ preventScroll: true });
  }, [editing, fallbackFocus]);

  const toast = note.toast;

  const openPhoto = useCallback(async (file: NoteFile) => {
    setViewing(await note.fresh(file));
  }, [note]);

  const openPdf = useCallback((file: NoteFile, event: MouseEvent<HTMLAnchorElement>) => {
    if (!file.expiresAt || Date.parse(file.expiresAt) > Date.now() + 5_000) return;
    // Its address has expired: the tab opens now, while the tap allows it, and goes to the new address once read.
    event.preventDefault();
    const tab = window.open('', '_blank');
    void note.fresh(file).then((current) => {
      if (!tab) return;
      tab.opener = null;
      tab.location.href = current.url;
    });
  }, [note]);

  return <>
    {note.shown && <NoteCard note={note} pen={pen} onEdit={() => onEditing(true)} onPhoto={(file) => void openPhoto(file)} onPdf={openPdf} />}
    {editing && <NoteSheet
      note={note}
      name={name}
      lock={lock}
      existing={openedOn ?? note.shown}
      onDismissed={() => onEditing(false)}
      onSaved={() => { closedWith.current = 'saved'; onEditing(false); }}
      onDeleted={() => { closedWith.current = 'deleted'; onEditing(false); }}
      undo={undo}
    />}
    {viewing && <NoteFileViewer file={viewing} onClose={() => setViewing(null)} onRemove={() => {
      const file = viewing;
      setViewing(null);
      note.removeFile(file);
    }} />}
    {/* Once Undo has gone with the keyboard on it, the keyboard goes to the page's menu. */}
    {!editing && toast && <UndoToast note={note} undo={undo} onGone={() => (fallbackFocus?.current ?? pen.current)?.focus({ preventScroll: true })} />}
  </>;
}

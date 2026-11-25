import type { MessageKey, Translate } from './messages';
import type { Parcel, ParcelFileType } from '../types';

/** A note holds up to 1,000 characters, counted as code points, as the server counts them. */
export const NOTE_MAX_LENGTH = 1_000;
/** The count of what is left shows for the last hundred. */
export const NOTE_COUNT_FROM = 100;
export const FILES_PER_PARCEL = 5;
/** Each file as sent: 10 MB. */
export const FILE_MAX_BYTES = 10_485_760;
/** All of an account's files, or a guest browser's: 200 MB. */
export const FILES_TOTAL_BYTES = 209_715_200;
const FILE_NAME_MAX = 120;
/** Photos are made to fit this many pixels on their longest side before they are sent. */
const PHOTO_MAX_SIDE = 2_560;
const PHOTO_QUALITY = 0.85;
/** How long a removal can be undone. */
export const UNDO_MS = 6_000;

/** What the file chooser offers: photos, including an iPhone's HEIC ones, and PDFs. */
export const NOTE_FILE_ACCEPT = 'image/jpeg,image/png,image/webp,image/heic,image/heif,application/pdf';

export const noteLength = (text: string) => Array.from(text).length;

/** The text cut to the limit, never through a character. */
export function clipNote(text: string): string {
  const characters = Array.from(text);
  return characters.length > NOTE_MAX_LENGTH ? characters.slice(0, NOTE_MAX_LENGTH).join('') : text;
}

/** A parcel has a note when it has words or at least one file. */
export function hasNote(parcel: Pick<Parcel, 'note' | 'files'>): boolean {
  return Boolean(parcel.note?.trim()) || Boolean(parcel.files?.length);
}

/** The parcel without one of its note's files. */
export function withoutFile<T extends Pick<Parcel, 'files'>>(parcel: T, fileId: string): T {
  const next = { ...parcel, files: parcel.files?.filter((file) => file.id !== fileId) };
  if (!next.files?.length) delete next.files;
  return next;
}

/** The words a note's search matches: its text and its files' names. */
export function noteSearchText(note: string | undefined, fileNames: readonly string[] = []): string {
  return [note ?? '', ...fileNames].join(' ');
}

/** Why a file could not join a note, each said in one plain line (`note.files.*`). */
export type NoteFileProblem = 'too-big' | 'type' | 'parcel-full' | 'account-full' | 'device-full' | 'unreadable' | 'offline' | 'failed';

export class NoteFileError extends Error {
  /** `status` is the server's, for a file it refused. */
  constructor(readonly problem: NoteFileProblem, readonly size?: number, readonly status?: number) {
    super(`The file was not added (${problem})`);
    this.name = 'NoteFileError';
  }
}

/** The problem of an upload the server refused, by its status and code. */
export function uploadProblem(status: number, code?: string): NoteFileProblem {
  if (code === 'file_too_big' || status === 413) return 'too-big';
  if (code === 'file_type' || status === 415) return 'type';
  if (code === 'parcel_files_full') return 'parcel-full';
  if (code === 'account_files_full') return 'account-full';
  return 'failed';
}

/** A size in megabytes as people read it: one decimal under 20, whole above. */
export function megabytes(bytes: number, languageTag: string, t: Translate): string {
  const value = Math.max(0.1, bytes / 1_048_576);
  const size = new Intl.NumberFormat(languageTag, { maximumFractionDigits: value < 20 ? 1 : 0 }).format(value);
  return t('note.files.megabytes', { size });
}

/** The line that says why a file did not join the note. */
export function fileProblemLine(problem: NoteFileProblem, name: string, size: number | undefined, t: Translate, languageTag: string): string {
  const keys: Record<Exclude<NoteFileProblem, 'too-big' | 'failed'>, MessageKey> = {
    type: 'note.files.type',
    'parcel-full': 'note.files.full',
    'account-full': 'note.files.accountFull',
    'device-full': 'note.files.deviceFull',
    unreadable: 'note.files.unreadable',
    offline: 'note.files.offline',
  };
  if (problem === 'too-big') return t('note.files.tooBig', { name, size: megabytes(size ?? FILE_MAX_BYTES + 1, languageTag, t) });
  if (problem === 'failed') return t('note.files.failed', { name });
  return t(keys[problem]);
}

/** Problems a second try may get past. */
export const retriable = (problem: NoteFileProblem) => problem === 'failed' || problem === 'offline';

const PHOTO_TYPES = new Set(['image/jpeg', 'image/png', 'image/webp']);
const HEIC_TYPES = new Set(['image/heic', 'image/heif']);
const BY_EXTENSION: Record<string, string> = {
  jpg: 'image/jpeg', jpeg: 'image/jpeg', png: 'image/png', webp: 'image/webp', heic: 'image/heic', heif: 'image/heif', pdf: 'application/pdf',
};

/** What a chosen file is, by its type or, where the browser names none (a HEIC photo, often), by its extension. */
function chosenType(file: File): string {
  const type = file.type.toLowerCase();
  if (type && type !== 'application/octet-stream') return type;
  return BY_EXTENSION[file.name.split('.').pop()?.toLowerCase() ?? ''] ?? type;
}

/** The name kept for a file: within 120 characters with its extension, which follows a change of type. */
export function noteFileName(name: string, type: ParcelFileType): string {
  const extension = type === 'application/pdf' ? 'pdf' : type === 'image/png' ? 'png' : type === 'image/webp' ? 'webp' : 'jpg';
  const cleaned = Array.from(name.replace(/[\u0000-\u001f\u007f/\\]/g, ' ').replace(/\s+/g, ' ').trim()).join('');
  const dot = cleaned.lastIndexOf('.');
  const base = (dot >= 0 ? cleaned.slice(0, dot) : cleaned).trim() || 'File';
  const known = dot >= 0 ? cleaned.slice(dot + 1).toLowerCase() : '';
  const kept = known === extension || (extension === 'jpg' && known === 'jpeg') ? known : extension;
  const room = FILE_NAME_MAX - kept.length - 1;
  return `${Array.from(base).slice(0, room).join('')}.${kept}`;
}

interface Decoded { width: number; height: number; draw: (context: CanvasRenderingContext2D | OffscreenCanvasRenderingContext2D, width: number, height: number) => void; close: () => void }

async function decode(file: Blob): Promise<Decoded | null> {
  if (typeof createImageBitmap !== 'function') return null;
  try {
    // Photos keep the way up their camera recorded.
    const bitmap = await createImageBitmap(file, { imageOrientation: 'from-image' });
    return { width: bitmap.width, height: bitmap.height, draw: (context, width, height) => context.drawImage(bitmap, 0, 0, width, height), close: () => bitmap.close() };
  } catch {
    return null;
  }
}

async function encode(image: Decoded, type: 'image/jpeg' | 'image/png', scale: number): Promise<Blob | null> {
  const width = Math.max(1, Math.round(image.width * scale));
  const height = Math.max(1, Math.round(image.height * scale));
  if (typeof OffscreenCanvas === 'function') {
    const canvas = new OffscreenCanvas(width, height);
    const context = canvas.getContext('2d');
    if (!context) return null;
    if (type === 'image/jpeg') { context.fillStyle = '#fff'; context.fillRect(0, 0, width, height); }
    image.draw(context, width, height);
    return canvas.convertToBlob({ type, quality: PHOTO_QUALITY }).catch(() => null);
  }
  const canvas = document.createElement('canvas');
  canvas.width = width;
  canvas.height = height;
  const context = canvas.getContext('2d');
  if (!context) return null;
  if (type === 'image/jpeg') { context.fillStyle = '#fff'; context.fillRect(0, 0, width, height); }
  image.draw(context, width, height);
  return new Promise((resolve) => canvas.toBlob(resolve, type, PHOTO_QUALITY));
}

/** A file ready to be kept with a note. */
export interface PreparedFile { blob: Blob; name: string; type: ParcelFileType }

/**
 * Readies a chosen file for a note: a PDF as it is, a photo made to fit 2,560 px and re-encoded as JPEG when it is
 * larger, or a HEIC one (which only some browsers can read), a PNG kept as PNG while it stays under 10 MB (a
 * screenshot's text stays sharp). Refuses other kinds, and anything still over 10 MB.
 */
export async function prepareNoteFile(file: File): Promise<PreparedFile> {
  const type = chosenType(file);
  if (type === 'application/pdf') {
    if (file.size > FILE_MAX_BYTES) throw new NoteFileError('too-big', file.size);
    return { blob: file, name: noteFileName(file.name, 'application/pdf'), type: 'application/pdf' };
  }
  const heic = HEIC_TYPES.has(type);
  if (!PHOTO_TYPES.has(type) && !heic) throw new NoteFileError('type');
  const image = await decode(file);
  if (!image) {
    // A browser that cannot read the photo sends one it would not change anyway; the server reads it.
    if (heic) throw new NoteFileError('unreadable');
    if (file.size > FILE_MAX_BYTES) throw new NoteFileError('too-big', file.size);
    return { blob: file, name: noteFileName(file.name, type as ParcelFileType), type: type as ParcelFileType };
  }
  try {
    const scale = Math.min(1, PHOTO_MAX_SIDE / Math.max(image.width, image.height));
    if (!heic && scale === 1 && file.size <= FILE_MAX_BYTES) {
      return { blob: file, name: noteFileName(file.name, type as ParcelFileType), type: type as ParcelFileType };
    }
    if (type === 'image/png') {
      const png = await encode(image, 'image/png', scale);
      if (png && png.size <= FILE_MAX_BYTES) return { blob: png, name: noteFileName(file.name, 'image/png'), type: 'image/png' };
    }
    const jpeg = await encode(image, 'image/jpeg', scale);
    if (!jpeg) throw new NoteFileError('unreadable');
    if (jpeg.size > FILE_MAX_BYTES) throw new NoteFileError('too-big', jpeg.size);
    return { blob: jpeg, name: noteFileName(file.name, 'image/jpeg'), type: 'image/jpeg' };
  } finally {
    image.close();
  }
}

/** A run of a note's text: plain, or a web or e-mail address to follow. */
export interface NotePiece { text: string; href?: string }

/** Endings that make a bare name an address, as people write one ("shop.example/returns"). */
const DOMAIN_ENDINGS = new Set([
  'com', 'org', 'net', 'io', 'co', 'app', 'dev', 'shop', 'store', 'info', 'biz', 'eu', 'uk', 'de', 'fr', 'ch', 'it', 'es',
  'pt', 'br', 'pl', 'ru', 'cn', 'nl', 'be', 'at', 'ie', 'lu', 'se', 'no', 'dk', 'fi', 'cz', 'us', 'ca', 'au', 'jp', 'mx',
  'example',
]);
const ADDRESS = /([\w.+-]+@[\w-]+(?:\.[\w-]+)+)|(https?:\/\/[^\s<>"]+)|((?:www\.)?(?:[\p{L}\p{N}-]+\.)+([\p{L}]{2,})(?:\/[^\s<>"]*)?)/giu;

/**
 * The note's text with its web and e-mail addresses made into links; nothing else is formatted. A bare name counts as
 * an address with `www.` or a known ending; punctuation after an address stays text.
 */
export function notePieces(text: string): NotePiece[] {
  const pieces: NotePiece[] = [];
  let last = 0;
  for (const match of text.matchAll(ADDRESS)) {
    const [whole, email, web, bare, ending] = match;
    const start = match.index ?? 0;
    if (start > 0 && /[\p{L}\p{N}@/]/u.test(text[start - 1])) continue;
    if (bare && !bare.toLowerCase().startsWith('www.') && !DOMAIN_ENDINGS.has(ending.toLowerCase())) continue;
    let found = whole;
    // A sentence's full stop, a comma or a closing bracket the address did not open stay outside it.
    while (/[.,;:!?'"’”)\]]$/.test(found)) {
      if (found.endsWith(')') && (found.match(/\(/g)?.length ?? 0) >= (found.match(/\)/g)?.length ?? 0)) break;
      found = found.slice(0, -1);
    }
    if (!found.includes('.')) continue;
    const href = email ? `mailto:${found}` : web ? found : `https://${found}`;
    if (start > last) pieces.push({ text: text.slice(last, start) });
    pieces.push({ text: found, href });
    last = start + found.length;
  }
  if (last < text.length) pieces.push({ text: text.slice(last) });
  return pieces;
}

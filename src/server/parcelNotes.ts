import 'server-only';

import { createHmac, timingSafeEqual } from 'node:crypto';
import type { ApiPackageFile, ApiPackageFileType } from '../generated/apiContract';
import { isRecord, type JsonObject } from './types';

/**
 * A parcel's private note: its text and up to five photos or PDFs, read by its owner alone (migration
 * 20261125200000_parcel_notes). Only the owner's own reads embed it (`SupabaseUserClient`): the service role's reads,
 * and so links, passports, e-mails, notifications and previews, never select it. A file's bytes are served through
 * an address this server signs for its owner (`/api/files/{id}`), which stays the same for a quarter of an hour so
 * caches can keep it, and lasts until the next quarter has passed too.
 */

/** What the owner's own reads embed beside a parcel's columns: the note's text and the files' details. */
export const NOTE_EMBED = 'package_notes(body),package_files(id,name,content_type,size,width,height,created_at)';
/** What an assistant reads of a parcel's note: its text and the files' names and types. */
export const ASSISTANT_NOTE_EMBED = 'package_notes(body),package_files(name,content_type,created_at)';

export const PACKAGE_FILE_TYPES: readonly ApiPackageFileType[] = ['image/jpeg', 'image/png', 'image/webp', 'application/pdf'];
export const MAX_NOTE_LENGTH = 1_000;
export const MAX_FILE_BYTES = 10_485_760;
export const MAX_PARCEL_FILES = 5;
export const MAX_FILE_NAME_LENGTH = 120;

const QUARTER_HOUR_SECONDS = 900;
const UUID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;

export type FileVariant = 'full' | 'thumb';

/** The signing key, derived from the service-role key and kept apart from its other uses by a label. */
function signingKey(): Buffer | null {
  const secret = process.env.SUPABASE_SERVICE_ROLE_KEY?.trim() ?? '';
  return secret ? createHmac('sha256', secret).update('package-files-v1').digest() : null;
}

function signature(key: Buffer, fileId: string, ownerId: string, variant: FileVariant, expires: number): Buffer {
  return createHmac('sha256', key).update(`${fileId.toLowerCase()}.${ownerId.toLowerCase()}.${variant}.${expires}`).digest();
}

/** When an address made now expires, in Unix seconds: the next quarter hour's end, a quarter hour after it begins. */
export function fileAddressExpiry(now = Date.now()): number {
  return Math.ceil(now / 1_000 / QUARTER_HOUR_SECONDS) * QUARTER_HOUR_SECONDS + QUARTER_HOUR_SECONDS;
}

/** The address a file of `ownerId` is read at until `expires`; null without the service-role key to sign it. */
export function signedFileAddress(fileId: string, ownerId: string, variant: FileVariant, expires: number): string | null {
  const key = signingKey();
  if (!key) return null;
  const mac = signature(key, fileId, ownerId, variant, expires).toString('base64url');
  return `/api/files/${fileId.toLowerCase()}?v=${variant}&e=${expires}&s=${mac}`;
}

/** Whether this server signed the address for the file's owner and it has not expired. */
export function validFileSignature(
  fileId: string, ownerId: string, variant: FileVariant, expires: number, given: string, now = Date.now(),
): boolean {
  const key = signingKey();
  if (!key || !Number.isSafeInteger(expires) || expires * 1_000 <= now || !/^[A-Za-z0-9_-]{43}$/.test(given)) return false;
  const mac = Buffer.from(given, 'base64url');
  const expected = signature(key, fileId, ownerId, variant, expires);
  return mac.length === expected.length && timingSafeEqual(mac, expected);
}

function noteText(value: unknown): string | null {
  const stored = Array.isArray(value) ? value[0] : value;
  return isRecord(stored) && typeof stored.body === 'string' && stored.body.trim() ? stored.body : null;
}

function storedFiles(value: unknown): JsonObject[] {
  return (Array.isArray(value) ? value.filter(isRecord) : [])
    .filter((file) => typeof file.id === 'string' && PACKAGE_FILE_TYPES.includes(file.content_type as ApiPackageFileType))
    .sort((a, b) => String(a.created_at).localeCompare(String(b.created_at)) || String(a.id).localeCompare(String(b.id)));
}

function isoTime(value: unknown): string {
  const time = new Date(typeof value === 'string' ? value : 0);
  return Number.isFinite(time.getTime()) ? time.toISOString() : new Date(0).toISOString();
}

function positive(value: unknown): number | null {
  return typeof value === 'number' && Number.isSafeInteger(value) && value > 0 ? value : null;
}

/** A stored file as its owner is served it, with addresses signed until `expires`; null when they cannot be signed. */
export function servedFile(file: JsonObject, ownerId: string, expires = fileAddressExpiry()): ApiPackageFile | null {
  const id = String(file.id);
  const type = file.content_type as ApiPackageFileType;
  const url = signedFileAddress(id, ownerId, 'full', expires);
  if (!url) return null;
  const width = positive(file.width);
  const height = positive(file.height);
  return {
    id,
    name: String(file.name ?? ''),
    content_type: type,
    size: positive(file.size) ?? 1,
    ...(width ? { width } : {}),
    ...(height ? { height } : {}),
    created_at: isoTime(file.created_at),
    url,
    thumbnail_url: type === 'application/pdf' ? null : signedFileAddress(id, ownerId, 'thumb', expires),
    expires_at: new Date(expires * 1_000).toISOString(),
  };
}

/**
 * The owner's parcel row with its note (`note`) and files (`files`, oldest first) in place of the embedded rows,
 * each left out when there is none. Without the owner's id (`ownerId`) no address can be signed: the files are left
 * out, the text kept.
 */
export function withOwnerNote(row: JsonObject, ownerId: string | null, now = Date.now()): JsonObject {
  if (!('package_notes' in row) && !('package_files' in row)) return row;
  const { package_notes: storedNote, package_files: stored, ...parcel } = row;
  const note = noteText(storedNote);
  const expires = fileAddressExpiry(now);
  const files = ownerId && UUID.test(ownerId)
    ? storedFiles(stored).flatMap((file) => servedFile(file, ownerId, expires) ?? [])
    : [];
  return { ...parcel, ...(note ? { note } : {}), ...(files.length ? { files } : {}) };
}

/** A served parcel as the account export gives it: its note, and its files' names, types, sizes and dates, no addresses. */
export function withExportedNote(row: JsonObject): JsonObject {
  if (!Array.isArray(row.files)) return row;
  return {
    ...row,
    files: row.files.filter(isRecord).map((file) => ({
      name: file.name, content_type: file.content_type, size: file.size, created_at: file.created_at,
    })),
  };
}

/** What an assistant is told of a parcel's note, read with `ASSISTANT_NOTE_EMBED`. */
export interface AssistantNote {
  note: string | null;
  files: Array<{ name: string; type: ApiPackageFileType }>;
}

export function assistantNote(row: JsonObject): AssistantNote {
  return {
    note: noteText(row.package_notes),
    files: (Array.isArray(row.package_files) ? row.package_files.filter(isRecord) : [])
      .filter((file) => typeof file.name === 'string' && PACKAGE_FILE_TYPES.includes(file.content_type as ApiPackageFileType))
      .sort((a, b) => String(a.created_at).localeCompare(String(b.created_at)))
      .map((file) => ({ name: String(file.name), type: file.content_type as ApiPackageFileType })),
  };
}

/** Whether a row read with a note's embed has one: text (never blank once stored) or a file. */
export function hasNote(row: JsonObject): boolean {
  const note = Array.isArray(row.package_notes) ? row.package_notes[0] : row.package_notes;
  return isRecord(note) || (Array.isArray(row.package_files) && row.package_files.length > 0);
}

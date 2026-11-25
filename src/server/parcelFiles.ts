import 'server-only';

import sharp, { type OutputInfo } from 'sharp';
import type { ApiPackageFileType } from '../generated/apiContract';
import { HttpError } from './api';
import { MAX_FILE_BYTES, MAX_FILE_NAME_LENGTH, PACKAGE_FILE_TYPES } from './parcelNotes';

/**
 * What a file added to a parcel's note becomes before it is stored: a photo is decoded and written again in its own
 * format, turned upright, within 2,560 px and without its metadata (the place it was taken among them), with a 384 px
 * copy for the thumbnails; a PDF is kept as sent. Whatever its first bytes do not show to be of its declared type is
 * refused, a HEIC photo too: the apps turn it into a JPEG first.
 */

export const MAX_IMAGE_EDGE = 2_560;
export const THUMBNAIL_EDGE = 384;
/** The pixels a photo may have before it is read: a 100-megapixel camera's. */
export const MAX_INPUT_PIXELS = 100_000_000;

const EXTENSIONS: Record<ApiPackageFileType, readonly string[]> = {
  'image/jpeg': ['.jpg', '.jpeg'],
  'image/png': ['.png'],
  'image/webp': ['.webp'],
  'application/pdf': ['.pdf'],
};

export function fileTypeRefusal(cause?: unknown): HttpError {
  return new HttpError(415, 'Peek takes photos (JPEG, PNG or WebP) and PDFs', undefined, { code: 'file_type', cause });
}

export function fileSizeRefusal(cause?: unknown): HttpError {
  return new HttpError(413, 'Files can be up to 10 MB', undefined, { code: 'file_too_big', cause });
}

/** The type a request's Content-Type declares; anything else is refused with 415. */
export function declaredFileType(header: string | null): ApiPackageFileType {
  const type = (header ?? '').split(';', 1)[0]!.trim().toLowerCase();
  const named = type === 'image/jpg' ? 'image/jpeg' : type;
  if (!PACKAGE_FILE_TYPES.includes(named as ApiPackageFileType)) throw fileTypeRefusal();
  return named as ApiPackageFileType;
}

function startsWith(bytes: Uint8Array, signature: readonly number[], offset = 0): boolean {
  return bytes.length >= offset + signature.length && signature.every((byte, index) => bytes[offset + index] === byte);
}

/** The type the first bytes show, of those Peek keeps; null for any other. */
export function sniffedFileType(bytes: Uint8Array): ApiPackageFileType | null {
  if (startsWith(bytes, [0xff, 0xd8, 0xff])) return 'image/jpeg';
  if (startsWith(bytes, [0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a])) return 'image/png';
  if (startsWith(bytes, [0x52, 0x49, 0x46, 0x46]) && startsWith(bytes, [0x57, 0x45, 0x42, 0x50], 8)) return 'image/webp';
  if (startsWith(bytes, [0x25, 0x50, 0x44, 0x46, 0x2d])) return 'application/pdf';
  return null;
}

/**
 * The name a file is kept under: the `X-File-Name` header percent-decoded, without a folder, control characters or
 * the marks that reverse text, its extension the one of its type, within 120 characters ("photo" or "document" when
 * nothing is left). Null without a header, or one that does not decode.
 */
export function packageFileName(header: string | null, type: ApiPackageFileType): string | null {
  if (header === null) return null;
  let decoded: string;
  try {
    decoded = decodeURIComponent(header);
  } catch {
    return null;
  }
  const cleaned = (decoded.split(/[\\/]/).pop() ?? '')
    .replace(/[\p{Cc}‎‏‪-‮⁦-⁩]/gu, ' ')
    .replace(/\s+/g, ' ')
    .trim();
  const dot = cleaned.lastIndexOf('.');
  const extension = dot >= 0 ? cleaned.slice(dot).toLowerCase() : '';
  const known = EXTENSIONS[type].includes(extension);
  const base = (known || /^\.[a-z0-9]{1,5}$/.test(extension) ? cleaned.slice(0, dot) : cleaned).trim();
  const ending = known ? cleaned.slice(dot) : EXTENSIONS[type][0]!;
  const kept = [...base].slice(0, MAX_FILE_NAME_LENGTH - ending.length).join('').trim()
    || (type === 'application/pdf' ? 'document' : 'photo');
  return `${kept}${ending}`;
}

export interface StoredFile {
  type: ApiPackageFileType;
  content: Buffer;
  thumbnail: Buffer | null;
  width: number | null;
  height: number | null;
}

/** The file as it is stored, or a 413 or 415 refusal. */
export async function storedFile(
  bytes: Uint8Array, type: ApiPackageFileType, { maxPixels = MAX_INPUT_PIXELS }: { maxPixels?: number } = {},
): Promise<StoredFile> {
  if (bytes.byteLength > MAX_FILE_BYTES) throw fileSizeRefusal();
  if (sniffedFileType(bytes) !== type) throw fileTypeRefusal();
  if (type === 'application/pdf') return { type, content: Buffer.from(bytes), thumbnail: null, width: null, height: null };

  let photo: { data: Buffer; info: OutputInfo };
  let thumbnail: Buffer;
  try {
    const image = sharp(bytes, { limitInputPixels: maxPixels, failOn: 'error', autoOrient: true, animated: false })
      .resize({ width: MAX_IMAGE_EDGE, height: MAX_IMAGE_EDGE, fit: 'inside', withoutEnlargement: true });
    // Written again without keepMetadata: no EXIF, XMP or IPTC, the colours in sRGB.
    photo = await (type === 'image/jpeg' ? image.jpeg({ quality: 85 })
      : type === 'image/png' ? image.png({ compressionLevel: 9 })
        : image.webp({ quality: 85 })).toBuffer({ resolveWithObject: true });
    const small = sharp(photo.data).resize({ width: THUMBNAIL_EDGE, height: THUMBNAIL_EDGE, fit: 'inside', withoutEnlargement: true });
    thumbnail = await (type === 'image/jpeg' ? small.jpeg({ quality: 75 }) : small.webp({ quality: 75 })).toBuffer();
  } catch (error) {
    if (error instanceof Error && /pixel limit/i.test(error.message)) throw fileSizeRefusal(error);
    throw fileTypeRefusal(error);
  }
  if (photo.data.byteLength > MAX_FILE_BYTES) throw fileSizeRefusal();
  return { type, content: photo.data, thumbnail, width: photo.info.width, height: photo.info.height };
}

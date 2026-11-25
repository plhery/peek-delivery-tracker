import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  readBodyBytes,
  requireUserClient,
  type RouteParameters,
} from '../../../../../src/server/api';
import { declaredFileType, fileSizeRefusal, fileTypeRefusal, packageFileName, storedFile } from '../../../../../src/server/parcelFiles';
import { MAX_FILE_BYTES, MAX_PARCEL_FILES, servedFile } from '../../../../../src/server/parcelNotes';
import { clientInputs } from '../../../../../src/server/suggestedInputs';
import { SupabaseError } from '../../../../../src/server/supabase';
import { servedOwnParcel } from '../../../../../src/server/usualPlace';
import type { ApiAddPackageFileResponse, ApiPackageFile, ApiPackageRow } from '../../../../../src/generated/apiContract';

interface PackageParameters extends RouteParameters {
  id: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

function parcelFull(): HttpError {
  return new HttpError(409, 'A parcel can have up to 5 files', undefined, { code: 'parcel_files_full' });
}

/** What the database's own refusals mean (migration 20261125200000_parcel_notes). */
function addRefusal(error: unknown): never {
  if (error instanceof SupabaseError) {
    if (error.code === 'PF001') throw parcelFull();
    if (error.code === 'PF002') {
      throw new HttpError(409, 'Your files can take up to 200 MB in all', undefined, { code: 'account_files_full', cause: error });
    }
    if (error.code === 'PF003') throw fileSizeRefusal(error);
    if (error.code === 'PF004') throw fileTypeRefusal(error);
  }
  throw error;
}

/**
 * Adds a photo or PDF to the parcel's private note: the body is the file, its type the Content-Type, its name the
 * percent-encoded `X-File-Name`. A photo is stored written again (parcelFiles.ts); the database keeps the limits.
 */
export const POST = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const requestedId = parseUuid(id, 'package id');
  const { request } = context;
  const type = declaredFileType(request.headers.get('content-type'));
  const name = packageFileName(request.headers.get('x-file-name'), type);
  if (!name) throw new HttpError(400, 'Send the file name in X-File-Name');
  const declared = request.headers.get('content-length');
  if (declared !== null && Number(declared) > MAX_FILE_BYTES) {
    void request.body?.cancel().catch(() => undefined);
    throw fileSizeRefusal();
  }

  const client = requireUserClient(context);
  const found = await client.getPackage(requestedId);
  if (!found) throw new HttpError(404, 'Package not found');
  // A parcel merged into another still answers to its old id: add to the one found.
  const packageId = String(found.id);
  if (Array.isArray(found.files) && found.files.length >= MAX_PARCEL_FILES) {
    void request.body?.cancel().catch(() => undefined);
    throw parcelFull();
  }

  const stored = await storedFile(await readBodyBytes(request, MAX_FILE_BYTES, fileSizeRefusal), type);
  const added = await client.addPackageFile(packageId, { name, ...stored }).catch(addRefusal);
  const ownerId = client.ownerId;
  const file = ownerId ? servedFile(added, ownerId) : null;
  if (!file) throw new HttpError(503, 'Files are not available');
  const parcel = await client.getPackage(packageId);
  if (!parcel) throw new HttpError(404, 'Package not found');
  const served = await servedOwnParcel(client, parcel, clientInputs(request));
  return json({
    file: file satisfies ApiPackageFile,
    package: served as unknown as ApiPackageRow,
  } satisfies ApiAddPackageFileResponse, 201);
}, { serviceRequired: true });

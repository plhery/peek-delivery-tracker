import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  requireUserClient,
  type RouteParameters,
} from '../../../../../../src/server/api';
import { isRecord } from '../../../../../../src/server/types';

interface PackageFileParameters extends RouteParameters {
  id: string;
  fileId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Removes a file from the parcel's private note, its bytes with it. */
export const DELETE = apiRoute<PackageFileParameters>(async (context) => {
  const { id, fileId: rawFileId } = await context.route.params;
  const packageId = parseUuid(id, 'package id');
  const fileId = parseUuid(rawFileId, 'file id');
  const client = requireUserClient(context);
  const found = await client.getPackage(packageId);
  const files = found && Array.isArray(found.files) ? found.files : [];
  if (!files.some((file) => isRecord(file) && file.id === fileId) || !await client.deletePackageFile(fileId)) {
    throw new HttpError(404, 'File not found');
  }
  return json({ ok: true });
}, { serviceRequired: true });

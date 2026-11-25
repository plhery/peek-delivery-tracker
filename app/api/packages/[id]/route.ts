import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  readJsonObject,
  requireUserClient,
  type RouteParameters,
} from '../../../../src/server/api';
import { servedOwnParcel } from '../../../../src/server/usualPlace';
import { clientInputs } from '../../../../src/server/suggestedInputs';
import { packageChanges } from '../../../../src/server/validation';

interface PackageParameters extends RouteParameters {
  id: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** A new name, a new private note (null or blank deletes it), or both. */
export const PATCH = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const requestedId = parseUuid(id, 'package id');
  const changes = packageChanges(await readJsonObject(context.request));
  const client = requireUserClient(context);
  const found = await client.getPackage(requestedId);
  if (!found) throw new HttpError(404, 'Package not found');
  // A parcel merged into another still answers to its old id: change the one found.
  const packageId = String(found.id);
  if (changes.label !== undefined) await client.updatePackage(packageId, { label: changes.label });
  if (changes.note !== undefined && !await client.setPackageNote(packageId, changes.note)) {
    throw new HttpError(404, 'Package not found');
  }
  const parcel = await client.getPackage(packageId);
  if (!parcel) throw new HttpError(404, 'Package not found');
  return json(await servedOwnParcel(client, parcel, clientInputs(context.request)));
}, { serviceRequired: true });

export const DELETE = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const client = requireUserClient(context);
  const found = await client.getPackage(parseUuid(id, 'package id'));
  if (!found) throw new HttpError(404, 'Package not found');
  await client.archivePackage(String(found.id));
  return json({ ok: true });
}, { serviceRequired: true });

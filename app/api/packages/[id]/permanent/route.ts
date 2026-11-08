import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  requireUserClient,
  type RouteParameters,
} from '../../../../../src/server/api';

interface PackageParameters extends RouteParameters {
  id: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

export const DELETE = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const client = requireUserClient(context);
  const found = await client.getPackage(parseUuid(id, 'package id'));
  // A parcel merged into another still answers to its old id: delete the one found.
  if (!found || !await client.deletePackage(String(found.id))) {
    throw new HttpError(404, 'Package not found');
  }
  return json({ ok: true });
}, { serviceRequired: true });

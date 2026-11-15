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

/**
 * The account opened the parcel: what it holds is no longer new to it, on any of its devices. The
 * answer is the moment stored, which the parcel then carries as `seen_at`.
 */
export const POST = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const seenAt = await requireUserClient(context).markPackageSeen(parseUuid(id, 'package id'));
  if (!seenAt) throw new HttpError(404, 'Package not found');
  return json({ seen_at: seenAt });
}, { serviceRequired: true });

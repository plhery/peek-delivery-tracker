import { apiRoute, HttpError, requireService } from '../../../src/server/api';
import { tokenOwner } from '../../../src/server/assistants/mcp';
import { assistantsConfigured } from '../../../src/server/assistants/settings';
import {
  listParcels,
  MOST_LISTED,
  MOST_SEARCH_LENGTH,
  PARCEL_SORTS,
  PARCEL_STATUSES,
  type ParcelSort,
  type ParcelStatus,
} from '../../../src/server/assistants/tools';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * The account's parcels as JSON, for a token that may read them: an assistant's access token, or a personal token in a
 * script while those are on. It takes `list_parcels`'s status, sort, search and limit, and counts against the grant's
 * allowance, as `/mcp` does. It is not there when no assistant can connect.
 */
export const GET = apiRoute(async (context) => {
  const settings = assistantsConfigured();
  if (!settings) throw new HttpError(404, 'Not found');
  const query = new URL(context.request.url).searchParams;
  const status = query.get('status') ?? undefined;
  if (status !== undefined && !(PARCEL_STATUSES as readonly string[]).includes(status)) {
    throw new HttpError(400, `status is one of ${PARCEL_STATUSES.join(', ')}`);
  }
  const sort = query.get('sort') ?? undefined;
  if (sort !== undefined && !(PARCEL_SORTS as readonly string[]).includes(sort)) {
    throw new HttpError(400, `sort is one of ${PARCEL_SORTS.join(', ')}`);
  }
  const search = query.get('search') ?? undefined;
  if (search !== undefined && search.length > MOST_SEARCH_LENGTH) throw new HttpError(400, `search is at most ${MOST_SEARCH_LENGTH} characters`);
  const limit = Number(query.get('limit') ?? 20);
  if (!Number.isInteger(limit) || limit < 1 || limit > MOST_LISTED) throw new HttpError(400, `limit is a whole number from 1 to ${MOST_LISTED}`);

  const service = requireService(context);
  const owner = await tokenOwner(settings, service, context.request, context.token);
  if (owner instanceof Response) return owner;
  if (!owner.scopes.includes('parcels:read')) {
    return Response.json({ error: 'The token may not read parcels' }, {
      status: 403,
      headers: { 'WWW-Authenticate': 'Bearer error="insufficient_scope", scope="parcels:read"' },
    });
  }
  const parcels = await listParcels(service, owner.userId, { status: status as ParcelStatus | undefined, sort: sort as ParcelSort | undefined, search, limit });
  return Response.json(parcels, { headers: { 'Cache-Control': 'no-store' } });
}, { authenticated: false, serviceRequired: true, capability: true });

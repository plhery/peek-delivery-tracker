import { apiRoute, HttpError, requireService } from '../../../src/server/api';
import { tokenOwner } from '../../../src/server/assistants/mcp';
import { assistantsConfigured } from '../../../src/server/assistants/settings';
import { listParcels, MOST_LISTED, PARCEL_STATUSES, type ParcelStatus } from '../../../src/server/assistants/tools';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * The account's parcels as JSON, for a token that may read them: a personal token from Settings in a Home Assistant
 * rest sensor or a script, or an assistant's access token. It counts against the grant's allowance, as `/mcp` does.
 */
export const GET = apiRoute(async (context) => {
  const settings = assistantsConfigured();
  if (!settings) throw new HttpError(404, 'Not found');
  const query = new URL(context.request.url).searchParams;
  const status = query.get('status') ?? 'active';
  if (!(PARCEL_STATUSES as readonly string[]).includes(status)) {
    throw new HttpError(400, `status is one of ${PARCEL_STATUSES.join(', ')}`);
  }
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
  return Response.json(await listParcels(service, owner.userId, status as ParcelStatus, limit), { headers: { 'Cache-Control': 'no-store' } });
}, { authenticated: false, serviceRequired: true, capability: true });

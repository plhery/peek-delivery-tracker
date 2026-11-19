import { apiRoute, readJsonObject, requireService } from '../../../../src/server/api';
import { installHash, readSighting } from '../../../../src/server/installCounts';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Counts an app or browser as seen today: its counting id, sent alone at most once a day, and kept only
 * as a keyed hash. Requests are logged without their body, and errors are reported without it.
 */
export const POST = apiRoute(async (context) => {
  const sighting = readSighting(await readJsonObject(context.request));
  await requireService(context).recordInstallSeen(installHash(sighting.id), sighting.platform, sighting.signedIn, sighting.parcels);
  return new Response(null, { status: 204, headers: { 'Cache-Control': 'no-store' } });
}, {
  authenticated: false, serviceRequired: true, capability: true,
  publicRateLimit: { limit: 10, window: 60, site: true },
});

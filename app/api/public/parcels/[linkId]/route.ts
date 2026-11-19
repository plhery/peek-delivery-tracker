import {
  apiRoute,
  json,
  noContent,
  publicRetryAfter,
  readJsonObject,
  requireService,
  type RouteParameters,
} from '../../../../../src/server/api';
import { wakeSyncWorker } from '../../../../../src/server/background';
import { recordParcelShare, recordParcelsForgotten, recordPublicParcelRead } from '../../../../../src/server/metrics';
import {
  isParcelLinkId,
  ownerKeyHash,
  PARCEL_NOT_SHARED,
  PARCEL_UNAVAILABLE,
  parcelAlerts,
  publicParcelResponse,
} from '../../../../../src/server/publicParcels';
import { SupabaseError } from '../../../../../src/server/supabase';
import { isOpenedParcelSyncDue, isTrackingSyncDue } from '../../../../../src/server/trackingSync';
import type { JsonObject } from '../../../../../src/server/types';
import { shareSwitches } from '../../../../../src/server/validation';
import type { ApiPublicParcelResponse } from '../../../../../src/generated/apiContract';

interface LinkParameters extends RouteParameters {
  linkId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** One answer for every link that cannot be shown, whatever the reason. */
const unavailable = () => json(PARCEL_UNAVAILABLE, 404);

/** The least time between two checks a lookup's owner asks for by refreshing. */
const OWNER_REFRESH_MS = 2 * 60_000;
/** Checks a client may ask for by refreshing, across every lookup it holds. */
const OWNER_REFRESHES = { limit: 10, window: 60, bucket: 'public-parcel-refresh' };

/** Whether the owner's refresh may check the parcel now: the carrier's own pacing allows it, and the last check is not that recent. */
function refreshable(parcel: JsonObject, now: Date): boolean {
  const checked = Date.parse(String(parcel.last_synced_at ?? ''));
  return parcel.archived_at == null && isTrackingSyncDue(parcel, now)
    && !(now.getTime() - checked < OWNER_REFRESH_MS);
}

/**
 * Reads a parcel through its link. With the lookup's owner key the caller is
 * the owner; anyone else is a viewer, who is told when the sharing was
 * stopped. A read records that the link was opened, creates nothing, and
 * queues a check of a one-off parcel that is due one, which the answer says
 * (`checkQueued`) so the page reads its result as soon as it lands. A page out
 * of sight says so (`hidden=true`): its read does neither, so a tab left in the
 * background does not keep the parcel's checks at the pace of one on screen.
 * The owner's refresh (`refresh=true`) queues a check before it is due, at most
 * every 2 minutes and as often as the carrier allows.
 */
export const GET = apiRoute<LinkParameters>(async (context) => {
  const { linkId } = await context.route.params;
  const service = requireService(context);
  const query = new URL(context.request.url).searchParams;
  const hidden = query.get('hidden') === 'true';
  const found = isParcelLinkId(linkId)
    ? await service.publicParcel(linkId, ownerKeyHash(context.request.headers.get('x-parcel-key')), !hidden)
    : null;
  if (!found) {
    recordPublicParcelRead('not_found');
    return unavailable();
  }
  if (found === 'stopped') {
    recordPublicParcelRead('stopped');
    return json({ error: PARCEL_NOT_SHARED }, 410);
  }
  let checkQueued = false;
  const now = new Date();
  const due = !hidden && found.package.one_off === true && isOpenedParcelSyncDue(found.package, now);
  // Only the owner forces a check, and a client only so often across its lookups.
  const forced = !due && !hidden && query.get('refresh') === 'true' && found.link.owner === true
    && found.package.one_off === true && refreshable(found.package, now) && !publicRetryAfter(context.request, OWNER_REFRESHES);
  if (due || forced) {
    try {
      // Queued, or one already queued or under way for the parcel.
      await service.enqueueSyncJob({ packageId: String(found.package.id), reason: forced ? 'refresh' : 'opened' });
      checkQueued = true;
      wakeSyncWorker();
    } catch (error) {
      // The parcel is still readable; the next read asks again.
      if (!(error instanceof SupabaseError)) throw error;
    }
  }
  recordPublicParcelRead('ok');
  return json({ ...publicParcelResponse(found, parcelAlerts(service)), ...(checkQueued ? { checkQueued } : {}) } satisfies ApiPublicParcelResponse);
}, {
  authenticated: false,
  serviceRequired: true,
  capability: true,
  publicRateLimit: { limit: 120, window: 60, bucket: 'public-parcel' },
});

/**
 * Changes what a lookup's link shows its viewers, makes it a gift, or stops
 * and resumes its sharing. Only the owner key may: an unknown link and a
 * wrong or missing key answer alike.
 */
export const PATCH = apiRoute<LinkParameters>(async (context) => {
  const { linkId } = await context.route.params;
  const changes = shareSwitches(await readJsonObject(context.request), ['showNumber', 'gift', 'shared']);
  const keyHash = ownerKeyHash(context.request.headers.get('x-parcel-key'));
  const service = requireService(context);
  const updated = isParcelLinkId(linkId) && keyHash ? await service.updateParcelLink(linkId, keyHash, changes) : null;
  if (!updated) return unavailable();
  recordParcelShare('lookup', updated.transition ?? 'changed');
  return json(publicParcelResponse(updated, parcelAlerts(service)) satisfies ApiPublicParcelResponse);
}, {
  authenticated: false,
  serviceRequired: true,
  capability: true,
  publicRateLimit: { limit: 30, window: 60, bucket: 'public-parcel-update' },
});

/** Forgets a lookup. An unknown link and a wrong or missing key answer alike. */
export const DELETE = apiRoute<LinkParameters>(async (context) => {
  const { linkId } = await context.route.params;
  const keyHash = ownerKeyHash(context.request.headers.get('x-parcel-key'));
  const forgotten = isParcelLinkId(linkId) && keyHash
    ? await requireService(context).forgetParcelLink(linkId, keyHash)
    : { links: 0, packages: 0 };
  if (forgotten.links === 0) return unavailable();
  recordParcelsForgotten('asked', forgotten);
  return noContent();
}, {
  authenticated: false,
  serviceRequired: true,
  capability: true,
  publicRateLimit: { limit: 30, window: 60, bucket: 'public-parcel-forget' },
});

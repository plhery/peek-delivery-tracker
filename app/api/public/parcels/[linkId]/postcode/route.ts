import {
  apiRoute,
  HttpError,
  json,
  readJsonObject,
  requireService,
  type RouteParameters,
} from '../../../../../../src/server/api';
import { wakeSyncWorker } from '../../../../../../src/server/background';
import { checkLinkPostcode, LINK_POSTCODE_TRIES, type PostcodeCheck } from '../../../../../../src/server/linkPostcodes';
import { logOperationalEvent } from '../../../../../../src/server/observability';
import { postcodeCountryWrite } from '../../../../../../src/server/postcodeCountry';
import {
  isParcelLinkId,
  ownerKeyHash,
  PARCEL_UNAVAILABLE,
  parcelAlerts,
  publicParcelResponse,
} from '../../../../../../src/server/publicParcels';
import { SupabaseError } from '../../../../../../src/server/supabase';
import { packageCarrierValues } from '../../../../../../src/server/validation';
import type { ApiPublicParcelResponse } from '../../../../../../src/generated/apiContract';

interface LinkParameters extends RouteParameters {
  linkId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

type PostcodeOutcome = 'accepted' | 'refused' | 'limited' | 'unavailable' | 'not_asked' | 'unknown';

/**
 * Logs how an added postcode ended, with the carrier once the link asked for
 * it. The link, the number and the postcode never go in the log.
 */
function recordPostcode(outcome: PostcodeOutcome, carrier: string | undefined, requestId?: string): void {
  logOperationalEvent('parcel_link_postcode', { request_id: requestId, carrier, outcome });
}

function secondsUntil(time: number): string {
  return String(Math.max(1, Math.ceil((time - Date.now()) / 1_000)));
}

/**
 * Adds the delivery postcode that another carrier holding a lookup's parcel
 * asks for, for the holder of its owner key. The carrier is asked first. A
 * postcode it accepts files the lookup under that carrier, as the parcel with
 * that postcode, and queues its check at once. One it refuses shows nothing
 * and counts against the link: a few a day.
 */
export const POST = apiRoute<LinkParameters>(async (context) => {
  const { linkId } = await context.route.params;
  const keyHash = ownerKeyHash(context.request.headers.get('x-parcel-key'));
  const body = await readJsonObject(context.request);
  if (typeof body.carrier !== 'string' || typeof body.postcode !== 'string'
    || (body.postcodeCountry !== undefined && typeof body.postcodeCountry !== 'string')) {
    throw new HttpError(400, 'Send the carrier and the delivery postcode');
  }
  const carrier = body.carrier.slice(0, 64);
  const service = requireService(context);
  const claim = isParcelLinkId(linkId) && keyHash
    ? await service.claimParcelLinkPostcode(linkId, keyHash, carrier, LINK_POSTCODE_TRIES)
    : null;
  if (claim === null) {
    recordPostcode('unknown', undefined, context.requestId);
    return json({ error: PARCEL_UNAVAILABLE }, 404);
  }
  if (claim === 'not_asked') {
    recordPostcode('not_asked', undefined, context.requestId);
    return json({ error: 'No postcode is asked for this parcel' }, 409);
  }
  if ('retryAt' in claim) {
    recordPostcode('limited', carrier, context.requestId);
    return json({ error: 'Too many postcodes were tried for this parcel' }, 429, { 'Retry-After': secondsUntil(Date.parse(claim.retryAt)) });
  }

  // A try the carrier did not refuse is given back; filing the link clears them all.
  let settled = false;
  try {
    const values = packageCarrierValues({ carrier, dpdPostcode: body.postcode, dpdPostcodeCountry: body.postcodeCountry ?? '' }, claim.trackingNumber);
    if (!values.dpdPostcode) throw new HttpError(400, 'Invalid delivery postcode');
    const check: PostcodeCheck = await checkLinkPostcode(values.carrier, claim.trackingNumber, values.dpdPostcode,
      values.dpdPostcodeCountry, context.request.signal);
    if (check.outcome === 'refused') {
      settled = true;
      recordPostcode('refused', carrier, context.requestId);
      // After several wrong postcodes the carrier takes none for a while, the right one included.
      return check.retryAfterMs
        ? json({ error: 'The carrier takes no postcode for now' }, 429, { 'Retry-After': secondsUntil(Date.now() + check.retryAfterMs) })
        : json({ error: 'The carrier does not have the parcel for this postcode' }, 422);
    }
    if (check.outcome === 'unavailable') {
      recordPostcode('unavailable', carrier, context.requestId);
      return json({ error: 'The carrier could not be asked. Try again later.' }, 503);
    }

    const filed = await service.fileParcelLinkPostcode(linkId, keyHash!, values.carrier, values.dpdPostcode);
    if (filed === null || filed === 'not_asked') {
      recordPostcode(filed === null ? 'unknown' : 'not_asked', carrier, context.requestId);
      return filed === null ? json({ error: PARCEL_UNAVAILABLE }, 404) : json({ error: 'No postcode is asked for this parcel' }, 409);
    }
    settled = true;
    recordPostcode('accepted', carrier, context.requestId);
    try {
      // A parcel this postcode files for the first time keeps its country; one filed before keeps its own.
      const write = filed.created ? postcodeCountryWrite(filed.package, values.dpdPostcodeCountry) : null;
      if (write) await service.updatePackage(String(filed.package.id), write);
      await service.enqueueSyncJob({ packageId: String(filed.package.id), reason: filed.created ? 'added' : 'changed' });
      wakeSyncWorker();
    } catch (error) {
      // The parcel is filed; the next read of the link queues its check.
      if (!(error instanceof SupabaseError)) throw error;
    }
    return json(publicParcelResponse(filed, parcelAlerts(service)) satisfies ApiPublicParcelResponse);
  } finally {
    if (!settled) await service.releaseParcelLinkPostcode(claim.attempt).catch(() => undefined);
  }
}, {
  authenticated: false,
  serviceRequired: true,
  capability: true,
  publicRateLimit: { limit: 10, window: 60, bucket: 'public-parcel-postcode' },
});

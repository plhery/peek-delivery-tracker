import { verifyAmazonShippingAddition } from '../../../../../../src/server/amazonShippingEligibility';
import {
  apiRoute,
  clientIp,
  HttpError,
  json,
  readJsonBody,
  requireService,
  type ApiContext,
  type RouteParameters,
} from '../../../../../../src/server/api';
import { wakeSyncWorker } from '../../../../../../src/server/background';
import { lookupCountry, rememberLookupCountry } from '../../../../../../src/server/lookupCountry';
import { verifyLookupRequest } from '../../../../../../src/server/lookupVerification';
import { logOperationalEvent } from '../../../../../../src/server/observability';
import { holdsPostcodeCountry } from '../../../../../../src/server/postcodeCountry';
import {
  claimLookup,
  isParcelLinkId,
  lookupsUsedUp,
  ownerKeyHash,
  PARCEL_UNAVAILABLE,
  parcelAlerts,
  publicParcelResponse,
} from '../../../../../../src/server/publicParcels';
import { SupabaseError, type StoredParcelLink } from '../../../../../../src/server/supabase';
import type { JsonObject } from '../../../../../../src/server/types';
import { packageCarrierValues, type PackageCarrierValues } from '../../../../../../src/server/validation';
import type { ApiPublicParcelResponse } from '../../../../../../src/generated/apiContract';

interface LinkParameters extends RouteParameters {
  linkId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** How far a failed change got: its input, the browser proof, the daily allowance, Amazon or the database. */
type CarrierStage = 'input' | 'verification' | 'allowance' | 'amazon' | 'saving';
type CarrierOutcome = 'changed' | 'unchanged' | 'limited' | 'unknown' | 'failed';

/** The number and the carriers of a change, as its log line names them. The link, a capability, stays out. */
interface Change {
  tracking_number?: string;
  previous_carrier?: string;
  carrier?: string;
}

function recordChange(outcome: CarrierOutcome, requestId?: string, change?: Change, failure: JsonObject = {}): void {
  logOperationalEvent('parcel_link_carrier', { request_id: requestId, outcome, ...change, ...failure });
}

const stored = (value: unknown): string | null => typeof value === 'string' && value ? value : null;

/** Whether the parcel already has these details, so that nothing is to be changed. */
function holds(parcel: JsonObject, values: PackageCarrierValues): boolean {
  return parcel.carrier === values.carrier
    && stored(parcel.tracking_url) === values.trackingUrl
    && stored(parcel.dpd_postcode) === values.dpdPostcode
    && stored(parcel.phone_digits) === values.phoneDigits
    && holdsPostcodeCountry(parcel, values.dpdPostcodeCountry);
}

async function changeCarrier(context: ApiContext<LinkParameters>, progress: { stage: CarrierStage; change?: Change }): Promise<Response> {
  const { linkId } = await context.route.params;
  const keyHash = ownerKeyHash(context.request.headers.get('x-parcel-key'));
  const { raw, value: body } = await readJsonBody(context.request);
  // Read before anything is asked, as a lookup reads it.
  lookupCountry(context.request, body.lookupCountryHint);
  const service = requireService(context);
  const found = isParcelLinkId(linkId) && keyHash ? await service.publicParcel(linkId, keyHash, false) : null;
  if (!found || found === 'stopped' || found.link.owner !== true || found.package.one_off !== true
    || typeof found.package.tracking_number !== 'string') {
    recordChange('unknown', context.requestId);
    return json(PARCEL_UNAVAILABLE, 404);
  }
  const trackingNumber = found.package.tracking_number;
  const values = packageCarrierValues(body, trackingNumber);
  progress.change = { tracking_number: trackingNumber, previous_carrier: String(found.package.carrier), carrier: values.carrier };
  const answer = (parcel: StoredParcelLink) => json(publicParcelResponse(parcel, parcelAlerts(service)) satisfies ApiPublicParcelResponse);
  // The details the parcel has already change nothing, and cost nothing.
  if (holds(found.package, values)) {
    recordChange('unchanged', context.requestId, progress.change);
    return answer(found);
  }

  progress.stage = 'verification';
  const verified = await verifyLookupRequest(context.request, service, raw);
  progress.stage = 'allowance';
  const now = new Date();
  const allowance = await claimLookup(service, clientIp(context.request), now, verified);
  if (!allowance.allowed) {
    recordChange('limited', context.requestId, progress.change);
    return lookupsUsedUp(now);
  }
  progress.stage = 'amazon';
  await verifyAmazonShippingAddition(values.carrier, trackingNumber);

  progress.stage = 'saving';
  const changed = await service.changeParcelLinkCarrier(linkId, keyHash!, values);
  if (!changed) {
    recordChange('unknown', context.requestId, progress.change);
    return json(PARCEL_UNAVAILABLE, 404);
  }
  if (changed.created) {
    await rememberLookupCountry(service, changed.package, context.request, body.lookupCountryHint, values.dpdPostcodeCountry, body.language);
  }
  recordChange(changed.changed ? 'changed' : 'unchanged', context.requestId, progress.change);
  if (changed.changed) {
    try {
      await service.enqueueSyncJob({ packageId: String(changed.package.id), reason: changed.created ? 'added' : 'changed' });
      wakeSyncWorker();
    } catch (error) {
      // The link has moved; the next read of it queues the check.
      if (!(error instanceof SupabaseError)) throw error;
    }
  }
  return answer(changed);
}

/**
 * Changes a lookup's carrier, or the inputs that carrier takes, on the same
 * link, for the holder of its owner key. The link keeps everything it holds:
 * its sharing, gift, alerts and shown number. It moves to the parcel of its
 * number with those details, found or created as a new lookup would, which is
 * checked at once. A change asks a carrier as a lookup does, so it needs the
 * same browser proof and counts against the same daily allowance; details the
 * parcel already has change nothing and cost nothing.
 */
export const PATCH = apiRoute<LinkParameters>(async (context) => {
  const progress: { stage: CarrierStage; change?: Change } = { stage: 'input' };
  try {
    return await changeCarrier(context, progress);
  } catch (error) {
    recordChange('failed', context.requestId, progress.change, {
      reason: progress.stage,
      status: error instanceof HttpError ? error.status : undefined,
      error_class: error instanceof Error ? error.name : typeof error,
    });
    throw error;
  }
}, {
  authenticated: false,
  serviceRequired: true,
  capability: true,
  publicRateLimit: { limit: 6, window: 60, bucket: 'public-parcel-carrier' },
});

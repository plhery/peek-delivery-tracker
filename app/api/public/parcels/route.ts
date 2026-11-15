import { verifyAmazonShippingAddition } from '../../../../src/server/amazonShippingEligibility';
import { verifyLookupRequest } from '../../../../src/server/lookupVerification';
import { apiRoute, clientIp, HttpError, json, readJsonBody, requireService, type ApiContext } from '../../../../src/server/api';
import { wakeSyncWorker } from '../../../../src/server/background';
import { lookupCountry, rememberLookupCountry } from '../../../../src/server/lookupCountry';
import { recordPublicLookup, type PublicLookupOutcome } from '../../../../src/server/metrics';
import { settleTypedSupportCases } from '../../../../src/server/reviewQueues';
import { logOperationalEvent } from '../../../../src/server/observability';
import {
  claimLookup,
  newOwnerKey,
  ownerKeyHash,
  parcelAlerts,
  publicParcelResponse,
  secondsUntilUtcMidnight,
} from '../../../../src/server/publicParcels';
import { SupabaseError } from '../../../../src/server/supabase';
import { newPackageValues } from '../../../../src/server/validation';
import type { ApiPublicLookupResponse } from '../../../../src/generated/apiContract';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** How far a failed lookup got: its input, the browser proof, the daily allowance, Amazon or the database. */
type LookupStage = 'input' | 'verification' | 'allowance' | 'amazon' | 'saving';

/** Counts a lookup and logs how it ended, with its number and carrier once they were read. */
function recordLookup(outcome: PublicLookupOutcome, requestId?: string, parcel?: Parcel): void {
  recordPublicLookup(outcome);
  logOperationalEvent('public_lookup', { request_id: requestId, outcome, ...parcel });
}

/** The number and carrier of a lookup, as its log line names them. */
interface Parcel {
  tracking_number?: string;
  carrier?: string;
}

async function lookUp(context: ApiContext, progress: { stage: LookupStage; parcel?: Parcel }): Promise<Response> {
  const service = requireService(context);
  // The name stays on the device: only the number, the carrier and its inputs are read.
  const { raw, value: body } = await readJsonBody(context.request);
  const values = newPackageValues({ ...body, label: '' }, lookupCountry(context.request, body.lookupCountryHint));
  progress.parcel = { tracking_number: values.trackingNumber, carrier: values.carrier };
  progress.stage = 'verification';
  await verifyLookupRequest(context.request, service, raw);

  progress.stage = 'allowance';
  const now = new Date();
  const allowance = await claimLookup(service, clientIp(context.request), now);
  if (!allowance.allowed) {
    recordLookup(allowance.scope === 'global' ? 'limited_global'
      : allowance.scope === 'network' ? 'limited_network' : 'limited_daily', context.requestId, progress.parcel);
    // `scope` lets a client offer signing in instead of a countdown to midnight.
    return json(
      { error: 'No lookups are left for today. Sign in to keep going.', scope: 'daily' },
      429,
      { 'Retry-After': String(secondsUntilUtcMidnight(now)) },
    );
  }
  // Amazon is asked only for a lookup that was counted.
  progress.stage = 'amazon';
  await verifyAmazonShippingAddition(values.carrier, values.trackingNumber);

  progress.stage = 'saving';
  const key = newOwnerKey();
  const created = await service.createOneOffParcel(values, ownerKeyHash(key)!);
  if (created.created) {
    await rememberLookupCountry(service, created.package, context.request, body.lookupCountryHint, values.dpdPostcodeCountry, body.language);
  }
  await settleTypedSupportCases(service, values.trackingNumber);
  recordLookup(created.created ? 'created' : 'reused', context.requestId, progress.parcel);
  try {
    await service.enqueueSyncJob({ packageId: String(created.package.id), reason: 'added' });
    wakeSyncWorker();
  } catch (error) {
    // The first read of the link queues the check again.
    if (!(error instanceof SupabaseError)) throw error;
  }
  return json({ ...publicParcelResponse(created, parcelAlerts(service)), key } satisfies ApiPublicLookupResponse, 201);
}

/**
 * Follows one parcel without an account. The answer carries the link and,
 * this once, the owner key: the server keeps only its hash.
 */
export const POST = apiRoute(async (context) => {
  const progress: { stage: LookupStage; parcel?: Parcel } = { stage: 'input' };
  try {
    return await lookUp(context, progress);
  } catch (error) {
    // A refusal names its status; any other failure is named by its class.
    logOperationalEvent('public_lookup', {
      request_id: context.requestId, outcome: 'failed', reason: progress.stage, ...progress.parcel,
      status: error instanceof HttpError ? error.status : undefined,
      error_class: error instanceof Error ? error.name : typeof error,
    });
    throw error;
  }
}, {
  authenticated: false,
  capability: true,
  serviceRequired: true,
  publicRateLimit: {
    limit: 6, window: 60, bucket: 'public-lookup', onLimited: () => recordLookup('limited_burst'),
  },
});

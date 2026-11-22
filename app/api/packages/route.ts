import { verifyAmazonShippingAddition } from '../../../src/server/amazonShippingEligibility';
import { claimAccountTracking } from '../../../src/server/accountTrackingBudget';
import {
  apiRoute,
  HttpError,
  json,
  readJsonBody,
  requireService,
  requireUser,
  requireUserClient,
} from '../../../src/server/api';
import { withEventPlaces } from '../../../src/server/eventPlaces';
import { servedOwnParcel, withUsualPlaces } from '../../../src/server/usualPlace';
import { loadCarrierOutages } from '../../../src/server/carrierOutages';
import { captureOperationalError } from '../../../src/server/observability';
import { SupabaseError } from '../../../src/server/supabase';
import { lookupCountry, rememberLookupCountry } from '../../../src/server/lookupCountry';
import { checkAccountRequest } from '../../../src/server/nativeVerification';
import { queueFirstCheck } from '../../../src/server/parcelsFromText';
import { reviewDemoCarrier } from '../../../src/server/reviewDemo';
import { settleTypedSupportCases } from '../../../src/server/reviewQueues';
import { newPackageValues } from '../../../src/server/validation';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Lists the account's parcels and records that its apps read them, unless the read comes from an app or page out of
 * sight (`hidden=true`): their parcels are checked at the pace of ones on screen only while someone looks.
 */
export const GET = apiRoute(async (context) => {
  const search = new URL(context.request.url).searchParams;
  const includeArchived = search.get('includeArchived') === 'true';
  const client = requireUserClient(context);
  const [packages] = await Promise.all([
    client.listPackages(includeArchived),
    loadCarrierOutages(requireService(context)),
    // The parcels are still readable when this fails; the next read records it.
    search.get('hidden') === 'true' ? undefined : client.recordOpened().catch((error: unknown) => {
      if (!(error instanceof SupabaseError)) throw error;
      captureOperationalError(error, { component: 'packages', operation: 'record_account_opened' });
    }),
  ]);
  const served = packages.map((row) => withEventPlaces(row, { destination: true }));
  // With its archived parcels, the list holds every delivery the usual town is worked out from.
  return json({ packages: await withUsualPlaces(client, served, { complete: includeArchived }) });
}, { serviceRequired: true });

export const POST = apiRoute(async (context) => {
  const { raw, value: body } = await readJsonBody(context.request);
  const country = lookupCountry(context.request, body.lookupCountryHint);
  const values = newPackageValues(body, country);
  await checkAccountRequest(context.request, requireService(context), raw, 'account_lookup');
  // A review demo number is filed under its scenario's carrier, costs no allowance and is never checked (reviewDemo.ts).
  const demo = reviewDemoCarrier(requireUser(context).id, values.trackingNumber);
  if (!demo) {
    await claimAccountTracking(requireService(context), requireUser(context).id, 'lookup');
    await verifyAmazonShippingAddition(values.carrier, values.trackingNumber);
  }
  const client = requireUserClient(context);
  const service = requireService(context);
  let packageId;
  try {
    packageId = await service.createAccountPackage(
      requireUser(context).id,
      values.trackingNumber,
      values.label,
      demo ?? values.carrier,
      demo ? null : values.trackingUrl,
      demo ? null : values.dpdPostcode,
      demo ? null : values.phoneDigits,
    );
  } catch (error) {
    if (error instanceof SupabaseError && error.code === 'P0001') {
      throw new HttpError(409, 'Your delivery box has reached its parcel limit', undefined, {
        cause: error,
      });
    }
    if (error instanceof SupabaseError && error.status === 409) {
      const existing = await client.getPackageByTrackingNumber(values.trackingNumber);
      return json({
        error: 'This tracking number is already in your delivery box',
        ...(typeof existing?.id === 'string' ? { packageId: existing.id } : {}),
      }, 409);
    }
    throw error;
  }
  const parcel = await client.getPackage(packageId);
  if (!parcel) throw new SupabaseError('The new package could not be reloaded');

  if (demo) return json({ package: await servedOwnParcel(client, parcel), jobIds: [] }, 201);
  await rememberLookupCountry(service, parcel, country, values.dpdPostcodeCountry);
  await settleTypedSupportCases(service, values.trackingNumber);
  const jobId = await queueFirstCheck(service, requireUser(context).id, String(parcel.id));
  const jobIds = jobId ? [jobId] : [];
  return json({ package: await servedOwnParcel(client, parcel), jobIds }, 201);
}, { serviceRequired: true });

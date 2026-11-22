import { normalizeDeliveryPostcode } from 'universal-parcel-scraper';
import { verifyAmazonShippingAddition } from '../../../../../src/server/amazonShippingEligibility';
import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  readJsonObject,
  requireService,
  requireUser,
  requireUserClient,
  type RouteParameters,
} from '../../../../../src/server/api';
import { queueCarrierCheck } from '../../../../../src/server/carrierCheck';
import { withEventPlaces } from '../../../../../src/server/eventPlaces';
import { recordProviderInput } from '../../../../../src/server/metrics';
import { logOperationalEvent } from '../../../../../src/server/observability';
import { holdsPostcodeCountry, savePostcodeCountry } from '../../../../../src/server/postcodeCountry';
import { isRecord } from '../../../../../src/server/types';
import { packageCarrierValues } from '../../../../../src/server/validation';

interface PackageParameters extends RouteParameters {
  id: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

function nullableText(value: unknown): string | null {
  return typeof value === 'string' && value ? value : null;
}

export const PATCH = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const client = requireUserClient(context);
  const service = requireService(context);
  const original = await client.getPackage(parseUuid(id, 'package id'));
  if (!original || typeof original.tracking_number !== 'string') {
    throw new HttpError(404, 'Package not found');
  }
  // A parcel merged into another still answers to its old id: change the one found.
  const packageId = String(original.id);

  const body = await readJsonObject(context.request);
  const values = packageCarrierValues(
    body,
    original.tracking_number,
  );
  const unchanged = original.carrier === values.carrier
    && nullableText(original.tracking_url) === values.trackingUrl
    && nullableText(original.dpd_postcode) === values.dpdPostcode
    && nullableText(original.phone_digits) === values.phoneDigits
    && holdsPostcodeCountry(original, values.dpdPostcodeCountry);
  if (body.providerPostcode !== undefined && !unchanged) throw new HttpError(400, 'Save the carrier change before updating provider input');
  let providerPostcode: string | undefined;
  if (body.providerPostcode !== undefined) {
    if (typeof body.providerPostcode !== 'string') throw new HttpError(400, 'Invalid delivery postcode');
    try { providerPostcode = normalizeDeliveryPostcode(body.providerPostcode); }
    catch { throw new HttpError(400, 'Invalid delivery postcode'); }
  }
  if (unchanged && !providerPostcode) return json({ package: withEventPlaces(original, { destination: true }), jobIds: [] });

  await verifyAmazonShippingAddition(values.carrier, original.tracking_number);
  if (providerPostcode ? !await client.setProviderPostcode(packageId, providerPostcode) : !await client.changePackageCarrier(
    packageId,
    values.carrier,
    values.trackingUrl,
    values.dpdPostcode,
    values.phoneDigits,
  )) throw new HttpError(404, 'Package not found');
  if (!providerPostcode && (values.dpdPostcodeCountry || (isRecord(original.carrier_data) && original.carrier_data.postcode_country))) {
    // The postcode's country is kept with the postcode the change stored, unless another change was saved since.
    await savePostcodeCountry(service, packageId, values.dpdPostcode, values.dpdPostcodeCountry);
  }
  if (providerPostcode) {
    const routing = isRecord(original.carrier_data) && isRecord(original.carrier_data.routing) ? original.carrier_data.routing : {};
    const asked = isRecord(routing.provider_input_needed) ? routing.provider_input_needed.provider : undefined;
    recordProviderInput(typeof asked === 'string' ? asked : 'unknown', 'supplied');
  }
  logOperationalEvent(providerPostcode ? 'package_provider_input_changed' : 'package_carrier_changed', {
    package_id: packageId,
    previous_carrier: String(original.carrier ?? 'unknown'),
    carrier: values.carrier,
    archived: original.archived_at != null,
  });

  const jobIds = original.archived_at == null ? await queueCarrierCheck(service, requireUser(context).id, packageId) : [];

  const parcel = await client.getPackage(packageId);
  if (!parcel) throw new HttpError(404, 'Package not found');
  return json({ package: withEventPlaces(parcel, { destination: true }), jobIds });
}, { serviceRequired: true });

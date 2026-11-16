import { apiRoute, HttpError, json, readJsonObject } from '../../../../src/server/api';
import { refusesNumber } from '../../../../src/server/numberRefusal';
import { CARRIERS, KEPT_NUMBER_SHAPE, normalizeTrackingNumber, validTrackingNumber } from '../../../../src/lib/carriers';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Whether a carrier picked by hand refuses the number as one it does not
 * issue, so the pickers can say so before tracking starts. The carrier's
 * adapter checks the number itself and nothing is sent, so no verification
 * or allowance stands in front of it.
 */
export const POST = apiRoute(async ({ request }) => {
  const body = await readJsonObject(request);
  if (typeof body.carrier !== 'string' || !Object.hasOwn(CARRIERS, body.carrier)) throw new HttpError(400, 'Unknown carrier');
  if (typeof body.trackingNumber !== 'string' || body.trackingNumber.length > 80) throw new HttpError(400, 'Invalid tracking number');
  const trackingNumber = normalizeTrackingNumber(body.trackingNumber);
  if (!KEPT_NUMBER_SHAPE.test(trackingNumber) || !validTrackingNumber(body.trackingNumber)) throw new HttpError(400, 'Invalid tracking number');
  return json({ refused: await refusesNumber(body.carrier, trackingNumber) });
}, {
  authenticated: false, loadService: false, capability: true,
  publicRateLimit: { limit: 30, window: 60 },
});

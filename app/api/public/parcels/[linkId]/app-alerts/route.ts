import {
  apiRoute,
  HttpError,
  json,
  noContent,
  readJsonObject,
  requireService,
  type RouteParameters,
} from '../../../../../../src/server/api';
import { recordParcelAlertRemoved, recordParcelAlertSet } from '../../../../../../src/server/metrics';
import {
  isParcelLinkId,
  ownerKeyHash,
  PARCEL_NOT_SHARED,
  PARCEL_UNAVAILABLE,
} from '../../../../../../src/server/publicParcels';
import { pushServices } from '../../../../../../src/server/push';
import { parcelAppAlert, parcelAppAlertToken } from '../../../../../../src/server/validation';

interface LinkParameters extends RouteParameters {
  linkId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

const limits = { limit: 20, window: 60, bucket: 'public-parcel-alerts' };

/**
 * Whether this server sends the notifications of the app the token is for: the iPhone's through
 * APNs, Android's through FCM. A configuration that does not load reads as no.
 */
function appAlertsAvailable(service: Parameters<typeof pushServices>[0], transport: 'apns' | 'fcm'): boolean {
  try {
    const push = pushServices(service);
    return (transport === 'fcm' ? push.fcm : push.native) !== null;
  } catch {
    return false;
  }
}

/**
 * Turns on notifications in the iPhone or Android app for this one parcel,
 * until it is delivered, without an account. Anyone holding the link may, as
 * in a browser. The device token is stored and never returned or logged.
 */
export const PUT = apiRoute<LinkParameters>(async (context) => {
  const { linkId } = await context.route.params;
  const alert = parcelAppAlert(await readJsonObject(context.request));
  const service = requireService(context);
  if (!appAlertsAvailable(service, alert.transport)) {
    throw new HttpError(503, alert.transport === 'fcm' ? 'FCM is not configured' : 'APNs is not configured');
  }
  // A malformed id is an unknown link: the answer never says which.
  const outcome = isParcelLinkId(linkId)
    ? await service.addParcelLinkAppAlert(linkId, ownerKeyHash(context.request.headers.get('x-parcel-key')), alert)
    : null;
  recordParcelAlertSet(outcome ?? 'unavailable', alert.transport === 'fcm' ? 'android' : 'ios');
  if (outcome === null) return json({ error: PARCEL_UNAVAILABLE }, 404);
  if (outcome === 'stopped') return json({ error: PARCEL_NOT_SHARED }, 410);
  if (outcome === 'full') return json({ error: 'This parcel has all the alerts it can take' }, 409);
  // A journey that is over has nothing left to announce: the alert has already ended.
  return noContent();
}, { authenticated: false, serviceRequired: true, capability: true, publicRateLimit: limits });

/**
 * Turns the phone's alert off. Knowing the device token is the right to: only
 * the phone and the server have it. The answer is the same when there was no
 * such alert or no such link.
 */
export const DELETE = apiRoute<LinkParameters>(async (context) => {
  const { linkId } = await context.route.params;
  const token = parcelAppAlertToken(await readJsonObject(context.request));
  if (isParcelLinkId(linkId) && await requireService(context).removeParcelLinkAppAlert(linkId, token)) {
    recordParcelAlertRemoved('asked');
  }
  return noContent();
}, { authenticated: false, serviceRequired: true, capability: true, publicRateLimit: limits });

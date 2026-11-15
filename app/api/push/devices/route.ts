import {
  apiRoute,
  HttpError,
  json,
  readJsonObject,
  requireService,
  requireUser,
} from '../../../../src/server/api';
import { pushServices } from '../../../../src/server/push';
import { nativePushDevice, nativePushTransport } from '../../../../src/server/validation';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Registers a phone for its account's notifications: an iPhone's APNs token, an Android phone's
 * FCM token when `transport` is `fcm`, or the Android app's installation token when it is
 * `unifiedpush` (with the distributor's endpoint and keys) or `inbox`. A server without that
 * service answers 503: UnifiedPush needs the server's VAPID key, the inbox nothing more.
 */
export const POST = apiRoute(async (context) => {
  const service = requireService(context);
  const body = await readJsonObject(context.request);
  const push = pushServices(service);
  const notifier = {
    apns: push.native, fcm: push.fcm, unifiedpush: push.unifiedpush, inbox: push.inbox,
  }[nativePushTransport(body.transport)];
  if (!notifier) throw new HttpError(503, 'Native push notifications are not configured');
  const values = nativePushDevice(body);
  const device = await service.upsertNativePushDevice(
    requireUser(context).id,
    values.token,
    values.environment,
    values.locale,
    values.installationId,
    values.deviceName,
    values.transport,
    values.webPush,
  );
  let testSent = false;
  if (values.sendTest) {
    try {
      await notifier.sendTest(device);
      testSent = true;
    } catch {
      // Registration succeeds even when the push service is temporarily unavailable.
    }
  }
  return json({ ok: true, testSent }, 201);
}, { serviceRequired: true });

export const DELETE = apiRoute(async (context) => {
  const service = requireService(context);
  const values = nativePushDevice(await readJsonObject(context.request), false);
  await service.deleteNativePushDevice(requireUser(context).id, values.token, values.transport);
  return json({ ok: true });
}, { serviceRequired: true });

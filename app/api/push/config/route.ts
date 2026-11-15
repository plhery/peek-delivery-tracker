import { apiRoute, json } from '../../../../src/server/api';
import { pushServices } from '../../../../src/server/push';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Whether the server sends Web Push, and its VAPID public key: what a browser subscribes with, and what the Android
 * app without Google's services registers its UnifiedPush distributor with, signed in or not.
 */
export const GET = apiRoute(async (context) => {
  const notifier = context.service ? pushServices(context.service).web : null;
  return json({
    available: notifier !== null,
    publicKey: notifier?.publicKey ?? null,
  });
}, { authenticated: false, publicRateLimit: { limit: 60, window: 60, bucket: 'public-push-config' } });

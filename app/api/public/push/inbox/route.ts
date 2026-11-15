import { createHash } from 'node:crypto';
import { apiRoute, HttpError, json, readJsonObject, requireService } from '../../../../../src/server/api';
import { RateLimiter } from '../../../../../src/server/rateLimit';
import { installationToken } from '../../../../../src/server/validation';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Each token's own allowance, past the address's: a phone asks about every 15 minutes. */
const tokens = new RateLimiter();
const TOKEN_LIMIT = { limit: 12, window: 60 };

/**
 * Hands the Android app without Google's services the notices waiting for it, oldest first, and
 * forgets them: each the string map FCM would have carried. Knowing the installation token is the
 * right to them; a token with nothing waiting, or never registered, gets an empty list.
 */
export const POST = apiRoute(async (context) => {
  const token = installationToken((await readJsonObject(context.request)).token);
  const key = createHash('sha256').update(token).digest('base64url').slice(0, 22);
  const retryAfter = tokens.retryAfter(key, TOKEN_LIMIT);
  if (retryAfter) throw new HttpError(429, 'Too many requests. Try again shortly.', { 'Retry-After': String(retryAfter) });
  return json({ messages: await requireService(context).takePushInbox(token) });
}, {
  authenticated: false, serviceRequired: true, capability: true,
  publicRateLimit: { limit: 120, window: 60, bucket: 'public-push-inbox' },
});

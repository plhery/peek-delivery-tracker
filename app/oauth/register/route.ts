import { apiRoute, HttpError, readJsonObject, requireService } from '../../../src/server/api';
import { registerClient } from '../../../src/server/assistants/oauth';
import { assistantsConfigured } from '../../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** OAuth dynamic client registration, for assistants that register before they sign in. */
export const POST = apiRoute(async (context) => {
  if (!assistantsConfigured()) throw new HttpError(404, 'Not found');
  return registerClient(requireService(context), await readJsonObject(context.request));
}, {
  authenticated: false,
  serviceRequired: true,
  publicRateLimit: { limit: 20, window: 3_600, bucket: 'oauth-register' },
});

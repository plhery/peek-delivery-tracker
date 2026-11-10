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
  // Every client together may register 1,000 an hour (register_assistant_client): a home's /64s share their /48's 20.
  publicRateLimit: { limit: 20, window: 3_600, bucket: 'oauth-register', site: true },
});

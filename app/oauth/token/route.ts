import { apiRoute, HttpError, requireService } from '../../../src/server/api';
import { exchangeToken, OAuthRequestError, oauthError, readForm } from '../../../src/server/assistants/oauth';
import { assistantsConfigured } from '../../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The OAuth token endpoint: codes and refresh tokens for access tokens to `/mcp`. */
export const POST = apiRoute(async (context) => {
  const settings = assistantsConfigured();
  if (!settings) throw new HttpError(404, 'Not found');
  let form;
  try {
    form = await readForm(context.request);
  } catch (error) {
    if (error instanceof OAuthRequestError) return oauthError(error.error, error.message);
    throw error;
  }
  return exchangeToken(settings, requireService(context), form);
}, {
  authenticated: false,
  anyBody: true,
  serviceRequired: true,
  capability: true,
  // Every user of one assistant comes from its few addresses, so the allowance is wide.
  publicRateLimit: { limit: 600, window: 60, bucket: 'oauth-token' },
});

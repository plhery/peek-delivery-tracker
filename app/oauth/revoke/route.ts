import { apiRoute, HttpError, requireService } from '../../../src/server/api';
import { OAuthRequestError, oauthError, readForm, revokeToken } from '../../../src/server/assistants/oauth';
import { assistantsConfigured } from '../../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** OAuth token revocation: an assistant giving its token back when it is disconnected. */
export const POST = apiRoute(async (context) => {
  if (!assistantsConfigured()) throw new HttpError(404, 'Not found');
  let form;
  try {
    form = await readForm(context.request);
  } catch (error) {
    if (error instanceof OAuthRequestError) return oauthError(error.error, error.message);
    throw error;
  }
  return revokeToken(requireService(context), form);
}, {
  authenticated: false,
  anyBody: true,
  serviceRequired: true,
  capability: true,
  publicRateLimit: { limit: 120, window: 60, bucket: 'oauth-revoke' },
});

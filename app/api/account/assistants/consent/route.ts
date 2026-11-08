import { apiRoute, HttpError, json, readJsonObject, requireRecentSignIn, requireService, requireUser, validateAfresh } from '../../../../../src/server/api';
import { answerConsent } from '../../../../../src/server/assistants/consent';
import { assistantsOpenToAccount } from '../../../../../src/server/assistants/access';
import { assistantsConfigured, SCOPES, type Scope } from '../../../../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * The owner's answer on the consent page: `{"query": "<the authorization request>", "approve": true, "scopes": […]}`.
 * Answers where the browser goes next, the assistant's redirect address with a code or an error. The token is checked
 * with Auth afresh, and allowing needs a sign-in from the last ten minutes: the grant outlasts the session. The page
 * answers that 401 by asking for a sign-in in place, then allows again.
 */
export const POST = apiRoute(async (context) => {
  const settings = assistantsConfigured();
  const user = requireUser(context);
  if (!settings) throw new HttpError(404, 'Not found');
  if (!(await assistantsOpenToAccount(settings, requireService(context), user.id))) throw new HttpError(403, 'Assistants cannot connect to this account yet');
  const payload = await readJsonObject(context.request);
  if (typeof payload.query !== 'string' || payload.query.length > 8_000) throw new HttpError(400, 'query must be the authorization request');
  if (typeof payload.approve !== 'boolean') throw new HttpError(400, 'approve must be true or false');
  let kept: Scope[] | null = null;
  if (payload.scopes !== undefined) {
    if (!Array.isArray(payload.scopes)) throw new HttpError(400, 'scopes must be a list');
    kept = SCOPES.filter((scope) => (payload.scopes as unknown[]).includes(scope));
  }
  const owner = payload.approve
    ? await requireRecentSignIn(context, 'Sign in again before connecting an assistant')
    : await validateAfresh(context, 'Authentication is required');
  const redirect = await answerConsent(settings, requireService(context), owner.id, payload.query, payload.approve, kept);
  return json({ redirect });
}, { serviceRequired: true });

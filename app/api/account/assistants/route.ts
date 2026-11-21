import { apiRoute, HttpError, json, readJsonObject, requireRecentSignIn, requireService, requireUser, type ApiContext } from '../../../../src/server/api';
import { clientName } from '../../../../src/server/assistants/clients';
import { assistantsOpenToAccount } from '../../../../src/server/assistants/access';
import {
  assistantsConfigured,
  newSecret,
  personalTokensOpen,
  SCOPES,
  secretDigest,
  type AssistantSettings,
  type Scope,
} from '../../../../src/server/assistants/settings';
import { SupabaseError } from '../../../../src/server/supabase';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The settings, when assistants may connect to this account; otherwise the route is not there. */
async function openSettings(context: ApiContext): Promise<AssistantSettings> {
  const settings = assistantsConfigured();
  if (!settings || !(await assistantsOpenToAccount(settings, requireService(context), requireUser(context).id))) {
    throw new HttpError(404, 'Not found');
  }
  return settings;
}

/**
 * The account's connected assistants and personal tokens, the address assistants connect to, and whether each parcel
 * an assistant adds is notified.
 */
export const GET = apiRoute(async (context) => {
  const settings = await openSettings(context);
  const service = requireService(context);
  const userId = requireUser(context).id;
  const [connections, notifyAdded] = await Promise.all([service.assistantGrants(userId), service.assistantAddedNotify(userId)]);
  return json({ connections, mcpUrl: settings.resource, notifyAdded });
}, { serviceRequired: true });

/** Switches the notification of each parcel an assistant adds on or off (`{"notifyAdded": false}`). */
export const PATCH = apiRoute(async (context) => {
  const settings = await openSettings(context);
  const payload = await readJsonObject(context.request);
  if (typeof payload.notifyAdded !== 'boolean') throw new HttpError(400, 'notifyAdded must be true or false');
  const service = requireService(context);
  const userId = requireUser(context).id;
  const notifyAdded = await service.setAssistantAddedNotify(userId, payload.notifyAdded);
  return json({ connections: await service.assistantGrants(userId), mcpUrl: settings.resource, notifyAdded });
}, { serviceRequired: true });

/**
 * Makes a personal token for a script, `{"name": "…", "scopes": ["parcels:read"]}`. The token is shown this once. It
 * lasts until deleted, so it needs a sign-in from the last ten minutes. It is not there while personal tokens are off.
 */
export const POST = apiRoute(async (context) => {
  if (!personalTokensOpen()) throw new HttpError(404, 'Not found');
  await openSettings(context);
  const payload = await readJsonObject(context.request);
  const name = clientName(payload.name);
  if (!name) throw new HttpError(400, 'Give the token a name');
  const scopes = Array.isArray(payload.scopes) ? SCOPES.filter((scope) => (payload.scopes as unknown[]).includes(scope)) : [];
  if (!scopes.length || (payload.scopes as unknown[]).length !== scopes.length) {
    throw new HttpError(400, `scopes must list one or more of ${SCOPES.join(', ')}`);
  }
  const owner = await requireRecentSignIn(context, 'Sign in again before making a personal token');
  const token = newSecret('personal');
  try {
    const connection = await requireService(context).createPersonalToken(owner.id, name, scopes as Scope[], secretDigest(token));
    return json({ connection, token }, 201);
  } catch (error) {
    if (error instanceof SupabaseError && error.code === 'P0001') {
      throw new HttpError(409, 'This account has ten personal tokens. Revoke one first.', undefined, { cause: error });
    }
    throw error;
  }
}, { serviceRequired: true });

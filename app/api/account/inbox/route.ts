import { apiRoute, HttpError, json, noContent, readJsonObject, requireService, requireUser, type ApiContext } from '../../../../src/server/api';
import { inboundEmailConfigured, inboundEmailOpenTo, inboxAddress, newInboxToken, type InboundEmailSettings } from '../../../../src/server/inboundEmail/settings';
import type { InboundMailbox } from '../../../../src/server/supabase';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The settings, when this server takes email and the account may have an address; otherwise the route is not there. */
function openSettings(context: ApiContext): InboundEmailSettings {
  const settings = inboundEmailConfigured();
  if (!settings || !inboundEmailOpenTo(settings, requireUser(context))) throw new HttpError(404, 'Not found');
  return settings;
}

function shown(settings: InboundEmailSettings, mailbox: InboundMailbox | null) {
  if (!mailbox) return { inbox: null, dailyLimit: settings.perAccountPerDay };
  const { token, ...rest } = mailbox;
  return { inbox: { address: inboxAddress(settings, token), ...rest }, dailyLimit: settings.perAccountPerDay };
}

/** The account's forwarding address for adding parcels by email, and what its last email did. */
export const GET = apiRoute(async (context) => {
  const settings = openSettings(context);
  return json(shown(settings, await requireService(context).inboundMailbox(requireUser(context).id)));
}, { serviceRequired: true });

/** Gives the account an address, or a new one in place of the old (`{"renew": true}`), whose emails are then dropped. */
export const POST = apiRoute(async (context) => {
  const settings = openSettings(context);
  const payload = await readJsonObject(context.request);
  if (payload.renew !== undefined && typeof payload.renew !== 'boolean') throw new HttpError(400, 'renew must be true or false');
  const service = requireService(context);
  const userId = requireUser(context).id;
  const current = await service.inboundMailbox(userId);
  if (current && !payload.renew) return json(shown(settings, current));
  return json(shown(settings, await service.openInboundMailbox(userId, newInboxToken())), current ? 200 : 201);
}, { serviceRequired: true });

/**
 * Turns adding by email off: the address goes, and emails to it are dropped. An account the setting is no longer
 * open to may still do it.
 */
export const DELETE = apiRoute(async (context) => {
  await requireService(context).closeInboundMailbox(requireUser(context).id);
  return noContent();
}, { serviceRequired: true });

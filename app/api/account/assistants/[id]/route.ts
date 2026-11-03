import { apiRoute, HttpError, noContent, parseUuid, requireService, requireUser } from '../../../../../src/server/api';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Disconnects an assistant or revokes a personal token: its tokens stop working at once. An account the setting is
 * no longer open to may still do it.
 */
export const DELETE = apiRoute<{ id: string }>(async (context) => {
  const id = parseUuid((await context.route.params).id, 'connection');
  if (!(await requireService(context).revokeAssistantGrant(requireUser(context).id, id))) throw new HttpError(404, 'Not found');
  return noContent();
}, { serviceRequired: true });

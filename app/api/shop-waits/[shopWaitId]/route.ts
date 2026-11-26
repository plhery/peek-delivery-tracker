import { apiRoute, HttpError, json, parseUuid, requireService, requireUser, type RouteParameters } from '../../../../src/server/api';
import { removeShopWait } from '../../../../src/server/shopWaits';

interface ShopWaitParameters extends RouteParameters {
  shopWaitId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Stops waiting, and forgets the wait with its link. */
export const DELETE = apiRoute<ShopWaitParameters>(async (context) => {
  const id = parseUuid((await context.route.params).shopWaitId, 'wait id');
  if (!await removeShopWait(requireService(context), requireUser(context).id, id)) throw new HttpError(404, 'Wait not found');
  return json({ ok: true });
}, { serviceRequired: true });

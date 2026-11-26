import { apiRoute, json, requireService, requireUser } from '../../../src/server/api';
import { keepShopWaitsRoute } from '../../../src/server/shopLinkRoutes';
import { accountShopWaits } from '../../../src/server/shopWaits';
import type { ApiShopWaitsResponse } from '../../../src/generated/apiContract';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The account's waits for shops to ship, without their links. */
export const GET = apiRoute(async (context) => {
  const answer: ApiShopWaitsResponse = { shop_waits: await accountShopWaits(requireService(context), requireUser(context).id) };
  return json(answer);
}, { serviceRequired: true });

/** Waits for the parcels of a text's shop links. The body holds a link with a key to the reader's order. */
export const POST = apiRoute(keepShopWaitsRoute, { capability: true, serviceRequired: true });

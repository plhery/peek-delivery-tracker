import { apiRoute, requireUser } from '../../../src/server/api';
import { readShopLinks } from '../../../src/server/shopLinkRoutes';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The parcels a shop's tracking-page link names. The body holds a link with a key to the reader's order. */
export const POST = apiRoute(async (context) => readShopLinks(context, `account:${requireUser(context).id}`), {
  capability: true, loadService: false,
});

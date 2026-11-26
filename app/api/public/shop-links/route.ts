import { apiRoute, clientIp } from '../../../../src/server/api';
import { readShopLinks } from '../../../../src/server/shopLinkRoutes';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** `/api/shop-links` for a visitor not signed in, per network. The body holds a link with a key to the reader's order. */
export const POST = apiRoute(async (context) => readShopLinks(context, `visitor:${clientIp(context.request)}`), {
  authenticated: false,
  capability: true,
  loadService: false,
  publicRateLimit: { limit: 10, window: 600, site: true },
});

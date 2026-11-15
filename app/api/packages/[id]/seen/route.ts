import {
  apiRoute,
  HttpError,
  json,
  parseUuid,
  readJsonObject,
  requireUserClient,
  type RouteParameters,
} from '../../../../../src/server/api';
import { shownNews } from '../../../../../src/server/validation';

interface PackageParameters extends RouteParameters {
  id: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * The account opened the parcel: what it holds is no longer new to it, on any of its devices. The
 * parcel is seen as of the news its page showed (`news_at`), so a scan stored while the page was
 * opening stays new; a request that names none, as an older app's, marks the moment it arrives. The
 * answer is the moment stored, which the parcel then carries as `seen_at`.
 */
export const POST = apiRoute<PackageParameters>(async (context) => {
  const { id } = await context.route.params;
  const packageId = parseUuid(id, 'package id');
  const { request } = context;
  const news = request.body && request.headers.get('content-length') !== '0' ? shownNews(await readJsonObject(request)) : null;
  const seenAt = await requireUserClient(context).markPackageSeen(packageId, news);
  if (!seenAt) throw new HttpError(404, 'Package not found');
  return json({ seen_at: seenAt });
}, { serviceRequired: true });

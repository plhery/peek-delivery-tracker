import { createHash } from 'node:crypto';
import contract from '../../../contracts/openapi.json';
import { apiRoute } from '../../../src/server/api';
import { guidePagesOn } from '../../../src/server/guideSettings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

const carriers = contract['x-carriers'];
const digest = createHash('sha256').update(JSON.stringify(carriers)).digest('hex');
/**
 * The catalog's fingerprint, which also covers whether the site serves its guides and carriers' pages, so an app that
 * kept the catalog learns of a change in either.
 */
const etags = Object.fromEntries([true, false].map((pages) => [String(pages), `"${createHash('sha256').update(JSON.stringify({ guidePages: pages, carriers })).digest('hex')}"`]));

function matchesETag(value: string | null, etag: string): boolean {
  return value?.split(',').some((candidate) => {
    const normalized = candidate.trim();
    return normalized === '*' || normalized === etag || normalized === `W/${etag}`;
  }) ?? false;
}

export const GET = apiRoute(
  async ({ request }) => {
    const guidePages = guidePagesOn();
    const cacheHeaders = {
      'Cache-Control': 'public, max-age=300, stale-while-revalidate=86400',
      ETag: etags[String(guidePages)],
    };
    if (matchesETag(request.headers.get('if-none-match'), cacheHeaders.ETag)) {
      return new Response(null, { status: 304, headers: cacheHeaders });
    }
    return Response.json(
      {
        version: `sha256-${digest}`,
        guidePages,
        'x-carriers': carriers,
      },
      { headers: cacheHeaders },
    );
  },
  { authenticated: false, loadService: false },
);

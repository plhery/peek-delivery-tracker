import { androidAssetLinks } from '../../../src/server/androidAppLinks';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Read by Google's servers, without credentials and without following redirects. */
export function GET(): Response {
  const statements = androidAssetLinks();
  if (!statements) return new Response(null, { status: 404, headers: { 'Cache-Control': 'no-store' } });
  return Response.json(statements, { headers: { 'Cache-Control': 'public, max-age=3600' } });
}

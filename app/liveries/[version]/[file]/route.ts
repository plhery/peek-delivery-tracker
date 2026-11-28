import { LIVERY_VERSION } from '../../../../src/brand';
import { ALL_LIVERIES, allLiveriesFile, isLivery, liveryFile } from '../../../../src/server/liveries';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * A livery's file, `/liveries/<version>/<livery>.svg`, or the one holding every livery (`all.svg`), which the trucks'
 * `<use>` draw (`CarrierMark`). The current version's file never changes under its address, so a browser and the edge
 * keep it for good; a page of an older build asking for its own version gets today's drawing, kept an hour.
 */
export async function GET(_request: Request, { params }: { params: Promise<{ version: string; file: string }> }): Promise<Response> {
  const { version, file } = await params;
  const name = file.endsWith('.svg') ? file.slice(0, -'.svg'.length) : '';
  const body = name === ALL_LIVERIES ? allLiveriesFile() : isLivery(name) ? liveryFile(name) : null;
  if (body === null) return new Response(null, { status: 404, headers: { 'Cache-Control': 'no-store' } });
  return new Response(body, {
    headers: {
      'Content-Type': 'image/svg+xml; charset=utf-8',
      'Cache-Control': version === LIVERY_VERSION ? 'public, max-age=31536000, immutable' : 'public, max-age=3600',
    },
  });
}

import { apiRoute, HttpError, requireService, type RouteParameters } from '../../../../src/server/api';
import { sniffedFileType } from '../../../../src/server/parcelFiles';
import { validFileSignature, type FileVariant } from '../../../../src/server/parcelNotes';

interface FileParameters extends RouteParameters {
  fileId: string;
}

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

const UUID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;

function notFound(): HttpError {
  return new HttpError(404, 'File not found');
}

/** `filename` in ASCII for older readers, `filename*` with the name as it is. */
function disposition(kind: 'inline' | 'attachment', name: string): string {
  const ascii = name.normalize('NFKD').replace(/[^\x20-\x7e]/g, '').replace(/["\\]/g, '_').trim() || 'file';
  const encoded = encodeURIComponent(name).replace(/['()*]/g, (character) => `%${character.charCodeAt(0).toString(16).toUpperCase()}`);
  return `${kind}; filename="${ascii}"; filename*=UTF-8''${encoded}`;
}

/**
 * A file of a parcel's private note, through the address the server signed for its owner (parcelNotes.ts): no sign-in,
 * the address is the capability. A wrong, tampered or expired one reads as no file at all. The address stays out of
 * logs and Sentry (`capability`), and the answer out of shared caches, search engines and other sites' referrers.
 */
export const GET = apiRoute<FileParameters>(async (context) => {
  const { fileId } = await context.route.params;
  const search = new URL(context.request.url).searchParams;
  const variant = search.get('v');
  const expires = Number(search.get('e'));
  const given = search.get('s') ?? '';
  const now = Date.now();
  if (!UUID.test(fileId) || (variant !== 'full' && variant !== 'thumb') || !/^\d{1,12}$/.test(search.get('e') ?? '')
    || expires * 1_000 <= now || !/^[A-Za-z0-9_-]{43}$/.test(given)) throw notFound();

  const service = requireService(context);
  const file = await service.packageFile(fileId.toLowerCase());
  if (!file || !validFileSignature(file.id, file.userId, variant satisfies FileVariant, expires, given, now)) throw notFound();
  const thumbnail = variant === 'thumb' && file.contentType !== 'application/pdf'
    ? await service.packageFileContent(file.id, file.userId, true) : null;
  const content = thumbnail ?? await service.packageFileContent(file.id, file.userId, false);
  if (!content) throw notFound();
  const type = thumbnail ? sniffedFileType(thumbnail) ?? 'image/jpeg' : file.contentType;

  const download = search.get('download') === '1';
  return new Response(new Uint8Array(content), {
    status: 200,
    headers: {
      'Content-Type': type,
      'Content-Length': String(content.byteLength),
      'Content-Disposition': disposition(download ? 'attachment' : 'inline', file.name),
      // The same address serves the same bytes until it expires; only the reader's own cache keeps them.
      'Cache-Control': `private, max-age=${Math.max(0, Math.floor(expires - now / 1_000))}, immutable`,
      'X-Content-Type-Options': 'nosniff',
      'Referrer-Policy': 'no-referrer',
      'X-Robots-Tag': 'noindex',
      'Content-Security-Policy': type === 'application/pdf' ? "default-src 'none'" : "default-src 'none'; sandbox",
    },
  });
}, {
  authenticated: false,
  serviceRequired: true,
  capability: true,
  quiet: true,
  // A list of parcels can show many thumbnails at once.
  publicRateLimit: { limit: 600, window: 60, bucket: 'package-files' },
});

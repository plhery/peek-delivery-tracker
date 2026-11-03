import { authorizationServerMetadata } from '../../../src/server/assistants/oauth';
import { assistantsConfigured } from '../../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Peek's OAuth authorization server metadata (RFC 8414), read by assistants before they sign in. */
export function GET(): Response {
  const settings = assistantsConfigured();
  if (!settings) return new Response(null, { status: 404, headers: { 'Cache-Control': 'no-store' } });
  return Response.json(authorizationServerMetadata(settings), { headers: { 'Cache-Control': 'public, max-age=3600' } });
}

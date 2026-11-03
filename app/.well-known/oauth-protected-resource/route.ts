import { assistantsConfigured } from '../../../src/server/assistants/settings';
import { protectedResourceMetadata } from '../../../src/server/assistants/oauth';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The MCP endpoint's protected resource metadata (RFC 9728), where clients look when `/mcp` names none. */
export function GET(): Response {
  const settings = assistantsConfigured();
  if (!settings) return new Response(null, { status: 404, headers: { 'Cache-Control': 'no-store' } });
  return Response.json(protectedResourceMetadata(settings), { headers: { 'Cache-Control': 'public, max-age=3600' } });
}

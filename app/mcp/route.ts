import { apiRoute, HttpError, requireService } from '../../src/server/api';
import { serveMcp } from '../../src/server/assistants/mcp';
import { assistantsConfigured } from '../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * Peek's MCP server, for assistants an account owner connected: stateless Streamable HTTP. The
 * token in `Authorization` decides whose parcels the tools see; another site's page is refused.
 */
export const POST = apiRoute(async (context) => {
  const settings = assistantsConfigured();
  if (!settings) throw new HttpError(404, 'Not found');
  return serveMcp(settings, requireService(context), context.request, context.token);
}, { authenticated: false, serviceRequired: true, capability: true });

/** No stream to open and no session to end: each request stands alone. */
function notAllowed(): Response {
  return new Response(null, { status: 405, headers: { Allow: 'POST', 'Cache-Control': 'no-store' } });
}

export const GET = notAllowed;
export const DELETE = notAllowed;

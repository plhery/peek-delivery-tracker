import { homeAssistantClientDocument } from '../../../../src/server/assistants/oauth';
import { assistantsConfigured } from '../../../../src/server/assistants/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The Home Assistant integration's Client ID Metadata Document, read by Peek itself when the integration signs in. */
export function GET(): Response {
  const settings = assistantsConfigured();
  if (!settings) return new Response(null, { status: 404, headers: { 'Cache-Control': 'no-store' } });
  return Response.json(homeAssistantClientDocument(settings), { headers: { 'Cache-Control': 'public, max-age=3600' } });
}

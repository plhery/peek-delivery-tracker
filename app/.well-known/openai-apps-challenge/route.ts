import { openaiAppsChallenge } from '../../../src/server/openaiApps';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Read by OpenAI when it checks the domain: the token alone, as plain text. Without the setting, there is none. */
export function GET(): Response {
  const token = openaiAppsChallenge();
  if (!token) return new Response(null, { status: 404, headers: { 'Cache-Control': 'no-store' } });
  return new Response(token, { headers: { 'Content-Type': 'text/plain; charset=utf-8', 'Cache-Control': 'no-store' } });
}

import { apiRoute, json, noContent } from '../../../src/server/api';
import { imageCommit } from '../../../src/server/observability';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

// The deploy waits until the public site names the commit it deployed (scripts/wait-for-commit.sh).
export const GET = apiRoute(async () => json({ ok: true, commit: imageCommit() ?? null }), { authenticated: false, loadService: false, quiet: true });
export const HEAD = apiRoute(async () => noContent(200), { authenticated: false, loadService: false, quiet: true });

import { webAppManifestResponse } from '../../../src/server/webAppManifest';

// Written at each request: it names the site (`SITE_NAME`), a setting the server reads at runtime, not at build time.
export const dynamic = 'force-dynamic';

/** The installed app in Spanish, as pages in Spanish link it. */
export function GET() {
  return webAppManifestResponse('es');
}

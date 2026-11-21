import { webAppManifestResponse } from '../../src/server/webAppManifest';

// Written at each request: it names the site (`SITE_NAME`), a setting the server reads at runtime, not at build time.
export const dynamic = 'force-dynamic';

/**
 * The installed app in English. Each other language has its own, such as
 * `/de/manifest.webmanifest`, and each page links the one in its language. This is a
 * route rather than Next's `app/manifest.ts`, whose link would replace the page's.
 */
export function GET() {
  return webAppManifestResponse('en');
}

import 'server-only';

/**
 * Whether the site serves its guides and carriers' pages: off unless `GUIDE_PAGES` is `on`. Their texts, under
 * `content/`, are Peek's own and not under the code's license (TRADEMARKS.md), so a copy of the code serves none of them:
 * their addresses are the site's 404 page, the sitemap leaves them out, and no page or app links them.
 */
export function guidePagesOn(env: NodeJS.ProcessEnv = process.env): boolean {
  return env.GUIDE_PAGES?.trim() === 'on';
}

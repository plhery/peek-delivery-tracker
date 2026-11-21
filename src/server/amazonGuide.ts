import 'server-only';
import { guideHref } from '../guides/paths';
import { AMAZON_ACCOUNT_MESSAGE } from '../lib/amazon';
import type { Locale } from '../lib/locale';
import { guidePagesOn } from './guideSettings';
import { canonicalOrigin } from './siteHosts';

/**
 * The guide that shows where an Amazon order's carrier and tracking number are, at its address in `locale` or the
 * nearest language it is written in, on the canonical origin. Null on a site that serves no guides (`GUIDE_PAGES`)
 * or names no canonical origin: a message sent away from a request has no other origin to name.
 */
export function amazonGuideUrl(locale: Locale = 'en', env: NodeJS.ProcessEnv = process.env): string | null {
  const origin = canonicalOrigin(env);
  const path = guideHref(locale, 'amazon-orders');
  return guidePagesOn(env) && origin && path ? new URL(path, origin).href : null;
}

/** The Amazon account message for a client that shows it as it is, ending on the English guide's address where there is one. */
export function amazonAccountMessage(env: NodeJS.ProcessEnv = process.env): string {
  const guide = amazonGuideUrl('en', env);
  return guide ? `${AMAZON_ACCOUNT_MESSAGE.slice(0, -1)}: ${guide}` : AMAZON_ACCOUNT_MESSAGE;
}

import 'server-only';

import { createHash } from 'node:crypto';

const EMAIL = /^[^\s@]+@[^\s@]+$/;

/**
 * The account a directory's reviewers sign in to with a password, named by `REVIEW_ACCOUNT_EMAIL`, as the SHA-256
 * digest (hex) of its lowercase address: the consent page asks for a password when that address is typed, without
 * showing it. Null when the setting is unset or isn't an address.
 */
export function reviewAccountDigest(env: Partial<Record<string, string>> = process.env): string | null {
  const email = env.REVIEW_ACCOUNT_EMAIL?.trim().toLowerCase() ?? '';
  return EMAIL.test(email) ? createHash('sha256').update(email).digest('hex') : null;
}

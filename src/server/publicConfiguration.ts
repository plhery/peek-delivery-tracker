import 'server-only';

import { decodeJwt } from 'jose';
import { authConfigFromEnvironment } from '../auth/authConfig';
import type { PublicConfiguration } from '../lib/publicConfiguration';
import { publicLink } from '../lib/publicLink';

/** Reject server credentials even when they were assigned to the public-key setting by mistake. */
function privateKey(key: string, serviceRoleKey: string | undefined): boolean {
  if (key.startsWith('sb_secret_') || (serviceRoleKey?.trim() && key === serviceRoleKey.trim())) return true;
  try {
    return decodeJwt(key).role === 'service_role';
  } catch {
    return false;
  }
}

/**
 * Read the explicit browser allowlist at request time. Indirect environment
 * reads keep Next from freezing NEXT_PUBLIC values into the server bundle.
 */
export function publicConfiguration(env: Record<string, string | undefined> = process.env): PublicConfiguration {
  const value = (name: string) => env[name]?.trim();
  const supabaseUrl = value('SUPABASE_PUBLIC_URL') || value('NEXT_PUBLIC_SUPABASE_URL') || value('SUPABASE_URL');
  const supabasePublishableKey = value('SUPABASE_PUBLISHABLE_KEY') || value('NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY') || value('SUPABASE_ANON_KEY');
  const googleEnabled = value('NEXT_PUBLIC_AUTH_GOOGLE_ENABLED');
  const appleEnabled = value('NEXT_PUBLIC_AUTH_APPLE_ENABLED');
  const emailOtpEnabled = value('NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED');
  for (const setting of [googleEnabled, appleEnabled, emailOtpEnabled]) {
    if (setting && setting !== 'true' && setting !== 'false') throw new Error('Public auth method settings must be true or false');
  }
  if (supabasePublishableKey && privateKey(supabasePublishableKey, value('SUPABASE_SERVICE_ROLE_KEY'))) {
    throw new Error('A Supabase server key must never be used as the browser publishable key');
  }
  const oidcProvider = value('NEXT_PUBLIC_AUTH_OIDC_PROVIDER');
  const oidcName = value('NEXT_PUBLIC_AUTH_OIDC_NAME');
  const auth = authConfigFromEnvironment({ supabaseUrl, supabasePublishableKey, googleEnabled, appleEnabled, emailOtpEnabled, oidcProvider, oidcName });
  if (!auth && (supabaseUrl || supabasePublishableKey || oidcProvider || oidcName)) {
    throw new Error('Public authentication requires a valid Supabase origin, publishable key and complete provider settings');
  }
  if (auth && !(auth.googleEnabled || auth.appleEnabled || auth.emailOtpEnabled || auth.oidc)) {
    throw new Error('Public authentication requires at least one enabled sign-in method');
  }
  const rawIosAppUrl = value('NEXT_PUBLIC_IOS_APP_URL');
  const iosAppUrl = publicLink(rawIosAppUrl);
  if (rawIosAppUrl && !iosAppUrl) throw new Error('The public iPhone app address must use HTTPS');
  return { auth, iosAppUrl };
}

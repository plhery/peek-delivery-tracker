import { authConfigFromEnvironment } from './authConfig';

/** The site's sign-in, from the settings the build writes into the page. */
export const siteAuthConfig = authConfigFromEnvironment({
  supabaseUrl: process.env.NEXT_PUBLIC_SUPABASE_URL,
  supabasePublishableKey: process.env.NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY,
  googleEnabled: process.env.NEXT_PUBLIC_AUTH_GOOGLE_ENABLED,
  appleEnabled: process.env.NEXT_PUBLIC_AUTH_APPLE_ENABLED,
  emailOtpEnabled: process.env.NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED,
  oidcProvider: process.env.NEXT_PUBLIC_AUTH_OIDC_PROVIDER,
  oidcName: process.env.NEXT_PUBLIC_AUTH_OIDC_NAME,
});

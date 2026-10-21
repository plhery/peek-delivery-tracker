import { describe, expect, it } from 'vitest';
import { publicConfiguration } from './publicConfiguration';

const backend = {
  SUPABASE_URL: 'http://supabase.internal:8000',
  SUPABASE_PUBLIC_URL: 'https://auth.example.test',
  SUPABASE_PUBLISHABLE_KEY: 'sb_publishable_synthetic',
  SUPABASE_SERVICE_ROLE_KEY: 'private-service-role-canary',
};

describe('request-time public configuration', () => {
  it('uses the browser endpoint separately from the server backend and publishes only its allowlist', () => {
    const result = publicConfiguration({ ...backend, SMTP_PASS: 'smtp-secret-canary', NEXT_PUBLIC_AUTH_GOOGLE_ENABLED: 'true', NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED: 'false', NEXT_PUBLIC_IOS_APP_URL: 'https://apps.example.test/peek' });
    expect(result).toEqual({
      auth: { url: 'https://auth.example.test', publishableKey: 'sb_publishable_synthetic', googleEnabled: true, appleEnabled: false, emailOtpEnabled: false, oidc: null },
      iosAppUrl: 'https://apps.example.test/peek',
    });
    const serialized = JSON.stringify(result);
    for (const privateValue of ['private-service-role-canary', 'smtp-secret-canary', 'supabase.internal']) expect(serialized).not.toContain(privateValue);
  });

  it('reads changed instance settings on each call without rebuilding or resetting the module', () => {
    const environment: Record<string, string | undefined> = { ...backend, NEXT_PUBLIC_AUTH_OIDC_PROVIDER: 'custom:first', NEXT_PUBLIC_AUTH_OIDC_NAME: 'First provider' };
    const first = publicConfiguration(environment);
    environment.SUPABASE_PUBLIC_URL = 'https://second.example.test';
    environment.SUPABASE_PUBLISHABLE_KEY = 'sb_publishable_second';
    environment.NEXT_PUBLIC_AUTH_OIDC_PROVIDER = 'custom:second';
    environment.NEXT_PUBLIC_AUTH_OIDC_NAME = 'Second provider';
    const second = publicConfiguration(environment);
    expect(first.auth).toMatchObject({ url: 'https://auth.example.test', oidc: { provider: 'custom:first', name: 'First provider' } });
    expect(second.auth).toMatchObject({ url: 'https://second.example.test', publishableKey: 'sb_publishable_second', oidc: { provider: 'custom:second', name: 'Second provider' } });
  });

  it('retains public environment compatibility and the legacy anon key fallback', () => {
    expect(publicConfiguration({ NEXT_PUBLIC_SUPABASE_URL: 'https://public.example.test', NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY: 'public-key' }).auth?.url).toBe('https://public.example.test');
    expect(publicConfiguration({ SUPABASE_URL: 'https://single.example.test', SUPABASE_ANON_KEY: 'legacy-public-key' }).auth).toMatchObject({ url: 'https://single.example.test', publishableKey: 'legacy-public-key' });
    expect(publicConfiguration({ ...backend, NEXT_PUBLIC_SUPABASE_URL: 'https://old.example.test', NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY: 'old-key' }).auth).toMatchObject({ url: 'https://auth.example.test', publishableKey: 'sb_publishable_synthetic' });
    expect(publicConfiguration({ SUPABASE_URL: 'http://internal.example.test', NEXT_PUBLIC_SUPABASE_URL: 'https://browser.example.test', SUPABASE_ANON_KEY: 'legacy-key' }).auth?.url).toBe('https://browser.example.test');
  });

  it('preserves deliberately unconfigured development and rejects incomplete or malformed settings', () => {
    expect(publicConfiguration({})).toEqual({ auth: null, iosAppUrl: null });
    for (const settings of [
      { SUPABASE_URL: 'https://auth.example.test' },
      { SUPABASE_PUBLISHABLE_KEY: 'public-key' },
      { ...backend, SUPABASE_PUBLIC_URL: 'https://user:password@auth.example.test' },
      { ...backend, NEXT_PUBLIC_AUTH_OIDC_PROVIDER: 'custom:company' },
      { ...backend, NEXT_PUBLIC_AUTH_GOOGLE_ENABLED: 'yes' },
      { ...backend, NEXT_PUBLIC_AUTH_APPLE_ENABLED: 'yes' },
      { ...backend, NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED: 'yes' },
      { ...backend, NEXT_PUBLIC_AUTH_GOOGLE_ENABLED: 'false', NEXT_PUBLIC_AUTH_APPLE_ENABLED: 'false', NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED: 'false' },
      { ...backend, NEXT_PUBLIC_IOS_APP_URL: 'javascript:alert(1)' },
    ]) expect(() => publicConfiguration(settings)).toThrow();
  });

  it('refuses modern and legacy server keys accidentally placed in public settings', () => {
    const legacyServiceKey = `${Buffer.from('{"alg":"HS256"}').toString('base64url')}.${Buffer.from('{"role":"service_role"}').toString('base64url')}.synthetic-signature`;
    for (const key of [backend.SUPABASE_SERVICE_ROLE_KEY, 'sb_secret_synthetic', legacyServiceKey]) {
      expect(() => publicConfiguration({ ...backend, SUPABASE_PUBLISHABLE_KEY: key })).toThrow('server key');
    }
  });
});

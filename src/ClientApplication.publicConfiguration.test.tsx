import { render, screen } from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { useAuth } from './auth/AuthContext';
import { ClientApplication } from './ClientApplication';
import { PublicConfigurationProvider, type PublicConfiguration } from './lib/publicConfiguration';

vi.mock('./accountCode', () => ({ accountCode: { useEarly: () => undefined } }));
vi.mock('./parcelCode', () => ({ parcelCode: { useEarly: () => undefined } }));
vi.mock('./ApiApplication', () => ({
  ApiApplication: () => {
    const auth = useAuth();
    return <output aria-label="Sign-in methods">{JSON.stringify({ google: auth.googleEnabled, apple: auth.appleEnabled, email: auth.emailOtpEnabled, oidc: auth.oidcName })}</output>;
  },
}));

afterEach(() => { vi.unstubAllEnvs(); });

describe('server-provided browser auth settings', () => {
  it('initializes the existing auth provider from the page instead of browser build values', () => {
    vi.stubEnv('NEXT_PUBLIC_AUTH_GOOGLE_ENABLED', 'false');
    vi.stubEnv('NEXT_PUBLIC_AUTH_APPLE_ENABLED', 'true');
    const configuration: PublicConfiguration = {
      auth: { url: 'https://runtime.example.test', publishableKey: 'sb_publishable_synthetic', googleEnabled: true, appleEnabled: false, emailOtpEnabled: false, oidc: { provider: 'custom:runtime', name: 'Runtime SSO' } },
      iosAppUrl: null,
    };
    render(<PublicConfigurationProvider value={configuration}><ClientApplication /></PublicConfigurationProvider>);
    expect(screen.getByRole('status', { name: 'Sign-in methods' })).toHaveTextContent('{"google":true,"apple":false,"email":false,"oidc":"Runtime SSO"}');
  });

  it('preserves the deliberately unconfigured application without a public backend', () => {
    render(<PublicConfigurationProvider value={{ auth: null, iosAppUrl: null }}><ClientApplication /></PublicConfigurationProvider>);
    expect(screen.getByRole('status', { name: 'Sign-in methods' })).toHaveTextContent('{"google":false,"apple":false,"email":true,"oidc":null}');
  });
});

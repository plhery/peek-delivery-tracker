import { describe, expect, it } from 'vitest';
import {
  assistantSettings,
  assistantsConfigured,
  assistantsOpenTo,
  knownScopes,
  newClientId,
  newSecret,
  PKCE_CHALLENGE,
  PKCE_VERIFIER,
  pkceChallenge,
  secretDigest,
} from './settings';

// Synthetic accounts only.
const listed = '5b000000-0000-4000-a000-0000000000a1';
const other = '5b000000-0000-4000-a000-0000000000b2';
const origin = 'https://peek.example';

function env(values: Record<string, string>): NodeJS.ProcessEnv {
  return values as NodeJS.ProcessEnv;
}

describe('assistant settings', () => {
  it('connects nothing without ASSISTANT_ACCOUNTS', () => {
    expect(assistantSettings(env({ CANONICAL_ORIGIN: origin }))).toBeNull();
    expect(assistantSettings(env({ ASSISTANT_ACCOUNTS: '  ', CANONICAL_ORIGIN: origin }))).toBeNull();
  });

  it('fails at startup without CANONICAL_ORIGIN or with a malformed list, and connects nothing while serving', () => {
    expect(() => assistantSettings(env({ ASSISTANT_ACCOUNTS: '*' }))).toThrow('ASSISTANT_ACCOUNTS needs CANONICAL_ORIGIN');
    expect(() => assistantSettings(env({ ASSISTANT_ACCOUNTS: 'someone', CANONICAL_ORIGIN: origin }))).toThrow('ASSISTANT_ACCOUNTS');
    expect(assistantsConfigured(env({ ASSISTANT_ACCOUNTS: '*' }))).toBeNull();
    expect(assistantsConfigured(env({ ASSISTANT_ACCOUNTS: 'someone', CANONICAL_ORIGIN: origin }))).toBeNull();
  });

  it('issues tokens for the /mcp resource of the canonical origin', () => {
    const settings = assistantSettings(env({ ASSISTANT_ACCOUNTS: '*', CANONICAL_ORIGIN: `${origin}/` }))!;
    expect(settings.issuer).toBe(origin);
    expect(settings.resource).toBe(`${origin}/mcp`);
  });

  it('opens to the accounts listed by id or email, or to everyone', () => {
    const named = assistantSettings(env({ ASSISTANT_ACCOUNTS: `${listed.toUpperCase()}, Alex@Example.com`, CANONICAL_ORIGIN: origin }))!;
    expect(assistantsOpenTo(named, { id: listed })).toBe(true);
    expect(assistantsOpenTo(named, { id: other, email: 'ALEX@example.com' })).toBe(true);
    expect(assistantsOpenTo(named, { id: other, email: 'sam@example.com' })).toBe(false);
    expect(assistantsOpenTo(named, { id: other, email: null })).toBe(false);
    const everyone = assistantSettings(env({ ASSISTANT_ACCOUNTS: '*', CANONICAL_ORIGIN: origin }))!;
    expect(assistantsOpenTo(everyone, { id: other })).toBe(true);
  });

  it('keeps the scopes it knows, in its own order', () => {
    expect(knownScopes('parcels:write openid  parcels:read')).toEqual(['parcels:read', 'parcels:write']);
    expect(knownScopes('parcels:write')).toEqual(['parcels:write']);
    expect(knownScopes('profile parcels:read:all')).toEqual([]);
    expect(knownScopes('')).toEqual([]);
    expect(knownScopes(null)).toEqual([]);
    expect(knownScopes(undefined)).toEqual([]);
  });

  it('checks PKCE S256 as RFC 7636 appendix B does', () => {
    const verifier = 'dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk'; // gitleaks:allow -- RFC 7636 appendix B example
    const challenge = 'E9Melhoa2OwvFrEMTJguCHaoeK1t8URWbuGJSstw-cM'; // gitleaks:allow -- RFC 7636 appendix B example
    expect(pkceChallenge(verifier)).toBe(challenge);
    expect(PKCE_VERIFIER.test(verifier)).toBe(true);
    expect(PKCE_CHALLENGE.test(challenge)).toBe(true);
    expect(PKCE_VERIFIER.test('v'.repeat(42))).toBe(false);
    expect(PKCE_VERIFIER.test('v'.repeat(129))).toBe(false);
    expect(PKCE_CHALLENGE.test(`${challenge}=`)).toBe(false);
  });

  it('makes fresh prefixed secrets and stores only their SHA-256', () => {
    const access = newSecret('access');
    expect(access).toMatch(/^peek_at_[A-Za-z0-9_-]{43}$/);
    expect(newSecret('refresh')).toMatch(/^peek_rt_/);
    expect(newSecret('personal')).toMatch(/^peek_pat_/);
    expect(newSecret('code')).toMatch(/^peek_ac_/);
    expect(newSecret('access')).not.toBe(access);
    expect(secretDigest(access)).toMatch(/^[0-9a-f]{64}$/);
    expect(secretDigest(access)).not.toContain(access);
    expect(secretDigest('abc')).toBe('ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad');
    expect(newClientId()).toMatch(/^peek_c_[A-Za-z0-9_-]{22}$/);
  });
});

import { afterEach, describe, expect, it } from 'vitest';
import { sessionStorageKey } from './sessionStorage';

const URL_NOW = 'https://auth.example.com';

afterEach(() => localStorage.clear());

describe('sessionStorageKey', () => {
  it('names the key after the Supabase host when the browser holds no sign-in', () => {
    expect(sessionStorageKey(URL_NOW)).toBe('sb-auth-auth-token');
  });

  it('keeps a sign-in saved before Supabase moved to another host', () => {
    localStorage.setItem('sb-supabase-old-auth-token', '{"refresh_token":"saved"}');
    expect(sessionStorageKey(URL_NOW)).toBe('sb-supabase-old-auth-token');
  });

  it('keeps a sign-in under way, so its return finds the code verifier', () => {
    localStorage.setItem('sb-supabase-old-auth-token-code-verifier', 'verifier');
    expect(sessionStorageKey(URL_NOW)).toBe('sb-supabase-old-auth-token');
  });

  it('prefers a sign-in saved under the host key', () => {
    localStorage.setItem('sb-supabase-old-auth-token', '{"refresh_token":"older"}');
    localStorage.setItem('sb-auth-auth-token', '{"refresh_token":"newer"}');
    expect(sessionStorageKey(URL_NOW)).toBe('sb-auth-auth-token');
  });

  it('leaves a key that was signed out', () => {
    localStorage.setItem('sb-supabase-old-auth-token', '{"refresh_token":"saved"}');
    localStorage.setItem('sb-supabase-old-auth-token.signed-out', 'true');
    expect(sessionStorageKey(URL_NOW)).toBe('sb-auth-auth-token');
  });

  it('falls back to the host key without storage', () => {
    expect(sessionStorageKey(URL_NOW, null)).toBe('sb-auth-auth-token');
  });
});

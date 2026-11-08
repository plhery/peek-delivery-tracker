import { afterEach, describe, expect, it } from 'vitest';
import { dropSignInAgain, noteSignInAgain, SIGN_IN_AGAIN_STORAGE_KEY, takeSignInAgain } from './signInAgain';

const NOW = Date.parse('2026-11-08T10:00:00.000Z');
const token = { action: 'personal-token', userId: 'user-a', name: 'Home Assistant', writes: true } as const;

afterEach(() => window.sessionStorage.clear());

describe('what a sign-in asked for again resumes', () => {
  it('goes to the screen it was noted for, once', () => {
    noteSignInAgain(token, NOW);
    expect(takeSignInAgain(['assistant-consent'], NOW)).toBeNull();
    expect(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY)).not.toBeNull();
    expect(takeSignInAgain(['personal-token', 'delete-account'], NOW + 60_000)).toEqual(token);
    expect(takeSignInAgain(['personal-token', 'delete-account'], NOW)).toBeNull();
  });

  it('drops a note too old, from ahead, or unreadable', () => {
    noteSignInAgain({ action: 'delete-account', userId: 'user-a' }, NOW);
    expect(takeSignInAgain(['personal-token'], NOW + 15 * 60_000)).toBeNull();
    expect(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY)).toBeNull();
    noteSignInAgain({ action: 'delete-account', userId: 'user-a' }, NOW + 1);
    expect(takeSignInAgain(['delete-account'], NOW)).toBeNull();
    window.sessionStorage.setItem(SIGN_IN_AGAIN_STORAGE_KEY, '{');
    expect(takeSignInAgain(['delete-account'], NOW)).toBeNull();
    window.sessionStorage.setItem(SIGN_IN_AGAIN_STORAGE_KEY, JSON.stringify({ action: 'personal-token', userId: 'user-a', at: NOW }));
    expect(takeSignInAgain(['personal-token'], NOW)).toBeNull();
    expect(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY)).toBeNull();
  });

  it('keeps only the access Peek knows', () => {
    window.sessionStorage.setItem(SIGN_IN_AGAIN_STORAGE_KEY, JSON.stringify({
      action: 'assistant-consent', userId: 'user-a', query: 'client_id=peek_c_synthetic', scopes: ['parcels:write', 'everything'], at: NOW,
    }));
    expect(takeSignInAgain(['assistant-consent'], NOW))
      .toEqual({ action: 'assistant-consent', userId: 'user-a', query: 'client_id=peek_c_synthetic', scopes: ['parcels:write'] });
  });

  it('is taken back only by the screen it belongs to', () => {
    noteSignInAgain(token);
    dropSignInAgain(['assistant-consent']);
    expect(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY)).not.toBeNull();
    dropSignInAgain(['personal-token']);
    expect(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY)).toBeNull();
  });
});

import { afterEach, describe, expect, it } from 'vitest';
import { PENDING_CONSENT_STORAGE_KEY, rememberPendingConsent, takePendingConsent } from './pendingConsent';

afterEach(() => {
  window.sessionStorage.clear();
  window.history.replaceState(null, '', '/');
});

describe('an assistant\'s request kept across a provider\'s sign-in', () => {
  it('is noted on the consent page alone, and taken back once', () => {
    window.history.replaceState(null, '', '/?parcel=1');
    rememberPendingConsent(1_000);
    expect(window.sessionStorage.getItem(PENDING_CONSENT_STORAGE_KEY)).toBeNull();
    window.history.replaceState(null, '', '/oauth/authorize?client_id=peek_c_synthetic&state=s');
    rememberPendingConsent(1_000);
    expect(takePendingConsent(2_000)).toBe('/oauth/authorize?client_id=peek_c_synthetic&state=s');
    expect(takePendingConsent(2_000)).toBeNull();
  });

  it('is dropped once a quarter of an hour old, or when it isn\'t a query', () => {
    window.history.replaceState(null, '', '/oauth/authorize?client_id=peek_c_synthetic');
    rememberPendingConsent(0);
    expect(takePendingConsent(15 * 60_000)).toBeNull();
    window.sessionStorage.setItem(PENDING_CONSENT_STORAGE_KEY, JSON.stringify({ query: '//elsewhere.example', at: 0 }));
    expect(takePendingConsent(1)).toBeNull();
  });
});

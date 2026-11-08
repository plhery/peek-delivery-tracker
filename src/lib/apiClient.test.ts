import { afterEach, expect, it, vi } from 'vitest';
import { ApiAuthenticationError, ApiRecentSignInError, authenticatedFetch } from './apiClient';

afterEach(() => { vi.restoreAllMocks(); vi.unstubAllGlobals(); vi.useRealTimers(); });

it.each(['token', 'request', 'refresh'])('bounds a stalled %s without signing out a recoverable session', async (stage) => {
  vi.useFakeTimers();
  vi.spyOn(AbortSignal, 'timeout').mockImplementation(ms => {
    const controller = new AbortController();
    setTimeout(() => controller.abort(new DOMException('Timed out', 'TimeoutError')), ms);
    return controller.signal;
  });
  const never = () => new Promise<never>(() => {});
  const getAccessToken = vi.fn().mockImplementation(refresh => (stage === 'token' || (stage === 'refresh' && refresh)) ? never() : Promise.resolve('token'));
  const onAuthenticationFailure = vi.fn();
  vi.stubGlobal('fetch', vi.fn().mockImplementation(() => stage === 'request' ? never() : Promise.resolve(Response.json({}, { status: 401 }))));
  const pending = authenticatedFetch('/api/packages', { userId: 'A', getAccessToken, onAuthenticationFailure });
  const rejected = expect(pending).rejects.toMatchObject({ name: 'TimeoutError' });
  await vi.advanceTimersByTimeAsync(30_001);
  await rejected;
  expect(onAuthenticationFailure).not.toHaveBeenCalled();
});

it('does not sign out the next account when an old request returns unauthorized', async () => {
  const lifecycle = new AbortController();
  let finish!: (response: Response) => void;
  const fetcher = vi.fn(() => new Promise<Response>(resolve => { finish = resolve; }));
  vi.stubGlobal('fetch', fetcher);
  const onAuthenticationFailure = vi.fn();
  const getAccessToken = vi.fn().mockResolvedValue('token');
  const pending = authenticatedFetch('/api/packages', { userId: 'A', getAccessToken, onAuthenticationFailure, signal: lifecycle.signal });
  const rejected = expect(pending).rejects.toMatchObject({ name: 'AbortError' });
  await vi.waitFor(() => expect(fetcher).toHaveBeenCalledOnce());
  lifecycle.abort();
  finish(Response.json({}, { status: 401 }));
  await rejected;
  expect(getAccessToken).toHaveBeenCalledOnce();
  expect(onAuthenticationFailure).not.toHaveBeenCalled();
});

const RECENT = { error: 'Sign in again before making a personal token', code: 'recent_sign_in' };

it('signs out a session the server refuses, after one refreshed try', async () => {
  const fetcher = vi.fn(async () => Response.json({ error: 'Authentication is required' }, { status: 401 }));
  vi.stubGlobal('fetch', fetcher);
  const onAuthenticationFailure = vi.fn(async () => undefined);
  const getAccessToken = vi.fn(async (refresh?: boolean) => refresh ? 'refreshed' : 'token');
  await expect(authenticatedFetch('/api/account/assistants', { userId: 'A', getAccessToken, onAuthenticationFailure }, { method: 'POST', body: '{}' }))
    .rejects.toBeInstanceOf(ApiAuthenticationError);
  expect(getAccessToken.mock.calls).toEqual([[false], [true]]);
  expect(fetcher).toHaveBeenCalledTimes(2);
  expect(onAuthenticationFailure).toHaveBeenCalledOnce();
});

it('keeps the session when the server asks for a recent sign-in, without a refresh that keeps the old sign-in time', async () => {
  const fetcher = vi.fn(async () => Response.json(RECENT, { status: 401 }));
  vi.stubGlobal('fetch', fetcher);
  const onAuthenticationFailure = vi.fn(async () => undefined);
  const getAccessToken = vi.fn(async () => 'token');
  await expect(authenticatedFetch('/api/account/assistants', { userId: 'A', getAccessToken, onAuthenticationFailure }, { method: 'POST', body: '{}' }))
    .rejects.toBeInstanceOf(ApiRecentSignInError);
  expect(getAccessToken.mock.calls).toEqual([[false]]);
  expect(fetcher).toHaveBeenCalledOnce();
  expect(onAuthenticationFailure).not.toHaveBeenCalled();
});

it('keeps the session when an expired token, once refreshed, needs a recent sign-in', async () => {
  const fetcher = vi.fn()
    .mockResolvedValueOnce(Response.json({ error: 'Authentication is required' }, { status: 401 }))
    .mockResolvedValueOnce(Response.json(RECENT, { status: 401 }));
  vi.stubGlobal('fetch', fetcher);
  const onAuthenticationFailure = vi.fn(async () => undefined);
  const getAccessToken = vi.fn(async (refresh?: boolean) => refresh ? 'refreshed' : 'expired');
  await expect(authenticatedFetch('/api/account', { userId: 'A', getAccessToken, onAuthenticationFailure }, { method: 'DELETE', body: '{}' }))
    .rejects.toBeInstanceOf(ApiRecentSignInError);
  expect(fetcher).toHaveBeenCalledTimes(2);
  expect(onAuthenticationFailure).not.toHaveBeenCalled();
});

it('reads a refusal with another code, or none readable, as a lost session', async () => {
  for (const body of ['{"error":"Authentication is required","code":"other"}', 'not json']) {
    vi.stubGlobal('fetch', vi.fn(async () => new Response(body, { status: 401, headers: { 'content-type': 'application/json' } })));
    const onAuthenticationFailure = vi.fn(async () => undefined);
    await expect(authenticatedFetch('/api/account', { userId: 'A', getAccessToken: async () => 'token', onAuthenticationFailure }, { method: 'DELETE', body: '{}' }))
      .rejects.toBeInstanceOf(ApiAuthenticationError);
    expect(onAuthenticationFailure).toHaveBeenCalledOnce();
  }
});

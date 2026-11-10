import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { createApiLinks } from '../links';
import { forgetLookupProof, getLookupProof, lookupProof, LookupVerificationError, mountLookupVerification } from './verification';

let options: Record<string, unknown>;
let mounted: ReturnType<typeof mountLookupVerification>;
beforeEach(() => {
  forgetLookupProof(lookupProof());
  window.turnstile = {
    render: vi.fn((_element, config) => { options = config; return 'widget'; }), remove: vi.fn(),
  };
  vi.stubGlobal('fetch', vi.fn().mockImplementation(async () => Response.json({ siteKey: 'public-key' })));
  mounted = mountLookupVerification(document.createElement('div'), 'en');
});
afterEach(() => {
  mounted.dispose();
  forgetLookupProof(lookupProof());
  delete window.turnstile;
  vi.unstubAllGlobals();
  vi.restoreAllMocks();
  sessionStorage.clear();
});

const PROOF_KEY = 'sdt.lookup-proof.v1';
const saved = () => JSON.parse(sessionStorage.getItem(PROOF_KEY) ?? 'null') as unknown;
/** This module as a reloaded page has it: nothing in memory, the tab's session storage kept. */
async function reloaded() {
  vi.resetModules();
  return { ...await import('./verification'), ...await import('../links') };
}
const required = () => new Response(null, { status: 403, headers: { 'X-Lookup-Verification': 'required' } });

async function complete() {
  await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalled());
  vi.mocked(fetch).mockResolvedValueOnce(Response.json({ proof: 'verified-proof', expiresAt: Date.now() + 900_000 }));
  (options.callback as (token: string) => void)('single-use-token');
}

it('warms once, shares verification between requests, and reuses the resulting proof', async () => {
  mounted.warm();
  const a = getLookupProof();
  const b = getLookupProof();
  await complete();
  await expect(a).resolves.toBe('verified-proof');
  await expect(b).resolves.toBe('verified-proof');
  await expect(getLookupProof()).resolves.toBe('verified-proof');
  expect(window.turnstile?.render).toHaveBeenCalledTimes(1);
  expect(options).toMatchObject({ appearance: 'interaction-only', action: 'parcel_lookup', 'response-field': false });
  expect(JSON.parse(vi.mocked(fetch).mock.calls[1]![1]!.body as string)).toEqual({ token: 'single-use-token' });
});

it('lets detection cancel without cancelling verification needed by the lookup', async () => {
  const stop = new AbortController();
  const a = getLookupProof(stop.signal);
  const b = getLookupProof();
  const assertion = expect(a).rejects.toMatchObject({ name: 'AbortError' });
  stop.abort();
  await assertion;
  await complete();
  await expect(b).resolves.toBe('verified-proof');
});

it('recovers after verification fails without retaining a bad token', async () => {
  const first = getLookupProof();
  const failure = expect(first).rejects.toThrow('verification');
  await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalled());
  (options['error-callback'] as () => void)();
  await failure;
  expect(lookupProof()).toBeNull();
  const second = getLookupProof();
  await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalledTimes(2));
  await complete();
  await expect(second).resolves.toBe('verified-proof');
});

it('waits for the page verification instead of sending a request it would refuse', async () => {
  const request = vi.fn().mockResolvedValue(Response.json({ trackingNumber: 'TEST123456', carrier: 'unknown' }));
  const answer = createApiLinks(request).detectCarrierPublic('TEST123456');
  await complete();
  await expect(answer).resolves.toMatchObject({ carrier: 'unknown' });
  expect(request).toHaveBeenCalledTimes(1);
  expect(request.mock.calls[0]![1]).toMatchObject({ credentials: 'omit', headers: { 'X-Lookup-Proof': 'verified-proof' }, body: JSON.stringify({ trackingNumber: 'TEST123456' }) });
});

it('sends without a proof when the server verifies no one, asking it once', async () => {
  vi.mocked(fetch).mockImplementation(async () => Response.json({ siteKey: null }));
  const request = vi.fn().mockImplementation(async () => Response.json({ trackingNumber: 'TEST123456', carrier: 'unknown' }));
  const links = createApiLinks(request);
  await links.detectCarrierPublic('TEST123456');
  await links.detectCarrierPublic('TEST123456');
  expect(request.mock.calls.map(([, init]) => (init as RequestInit & { headers: Record<string, string> }).headers['X-Lookup-Proof']))
    .toEqual([undefined, undefined]);
  expect(fetch).toHaveBeenCalledTimes(1);
  expect(window.turnstile?.render).not.toHaveBeenCalled();
});

it('sends without a proof after verification fails, and does not verify a refusal again', async () => {
  const request = vi.fn().mockImplementation(async () => required());
  const answer = createApiLinks(request).detectCarrierPublic('TEST123456');
  const failure = expect(answer).rejects.toThrow('verification');
  await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalled());
  (options['error-callback'] as () => void)();
  await failure;
  expect(request).toHaveBeenCalledTimes(1);
  expect((request.mock.calls[0]![1] as RequestInit & { headers: Record<string, string> }).headers['X-Lookup-Proof']).toBeUndefined();
  expect(window.turnstile?.render).toHaveBeenCalledTimes(1);
});

it('drops a proof the server refuses right after verifying, without verifying again', async () => {
  const request = vi.fn().mockImplementation(async () => required());
  const answer = createApiLinks(request).detectCarrierPublic('TEST123456');
  const failure = expect(answer).rejects.toThrow('verification');
  await complete();
  await failure;
  expect(request).toHaveBeenCalledTimes(1);
  expect(window.turnstile?.render).toHaveBeenCalledTimes(1);
  expect(lookupProof()).toBeNull();
  expect(saved()).toBeNull();
});

it('does not loop or resend writes for ordinary forbidden responses', async () => {
  // A page without verification sends what it has.
  mounted.dispose();
  const request = vi.fn().mockResolvedValue(Response.json({ error: 'Forbidden' }, { status: 403 }));
  await expect(createApiLinks(request).detectCarrierPublic('TEST123456')).rejects.toThrow();
  expect(request).toHaveBeenCalledTimes(1);
  expect(fetch).not.toHaveBeenCalled();
});

it('fails closed on an unavailable configuration and can retry', async () => {
  vi.mocked(fetch).mockRejectedValueOnce(new TypeError('Failed to fetch'));
  await expect(getLookupProof()).rejects.toThrow('verification');
  const retry = getLookupProof();
  await complete();
  await expect(retry).resolves.toBe('verified-proof');
});

it('handles a blocked script without hanging, and retries with a fresh script', async () => {
  delete window.turnstile;
  const first = getLookupProof();
  const rejected = expect(first).rejects.toMatchObject({ reason: 'script' });
  await vi.waitFor(() => expect(document.querySelector('script[src*="turnstile"]')).not.toBeNull());
  document.querySelector('script[src*="turnstile"]')!.dispatchEvent(new Event('error'));
  await rejected;
  expect(document.querySelector('script[src*="turnstile"]')).toBeNull();
  const second = getLookupProof();
  await vi.waitFor(() => expect(document.querySelector('script[src*="turnstile"]')).not.toBeNull());
  window.turnstile = { render: vi.fn((_element, config) => { options = config; return 'recovered'; }), remove: vi.fn() };
  document.querySelector('script[src*="turnstile"]')!.dispatchEvent(new Event('load'));
  await complete();
  await expect(second).resolves.toBe('verified-proof');
  document.querySelector('script[src*="turnstile"]')!.remove();
});

it('keeps the proof in the tab through a reload, until it expires', async () => {
  mounted.warm();
  await complete();
  await expect(getLookupProof()).resolves.toBe('verified-proof');
  expect(saved()).toEqual({ proof: 'verified-proof', expiresAt: expect.any(Number) });
  const page = await reloaded();
  expect(page.lookupProof()).toBe('verified-proof');
  // No verification is mounted on the reloaded page: the saved proof is used as it is.
  await expect(page.getLookupProof()).resolves.toBe('verified-proof');
  const request = vi.fn().mockResolvedValue(Response.json({ trackingNumber: 'TEST123456', carrier: 'unknown' }));
  await page.createApiLinks(request).detectCarrierPublic('TEST123456');
  expect(request.mock.calls[0]![1]).toMatchObject({ headers: { 'X-Lookup-Proof': 'verified-proof' } });
  vi.spyOn(Date, 'now').mockReturnValue(Date.now() + 900_000);
  expect(page.lookupProof()).toBeNull();
  expect(saved()).toBeNull();
});

it('forgets a refused saved proof and saves the one that replaces it', async () => {
  sessionStorage.setItem(PROOF_KEY, JSON.stringify({ proof: 'refused-proof', expiresAt: Date.now() + 600_000 }));
  const page = await reloaded();
  const verifier = page.mountLookupVerification(document.createElement('div'), 'en');
  try {
    const request = vi.fn().mockResolvedValueOnce(required())
      .mockResolvedValueOnce(Response.json({ trackingNumber: 'TEST123456', carrier: 'unknown' }));
    const answer = page.createApiLinks(request).detectCarrierPublic('TEST123456');
    await complete();
    await expect(answer).resolves.toMatchObject({ carrier: 'unknown' });
    expect(request.mock.calls.map(([, init]) => (init as RequestInit & { headers: Record<string, string> }).headers['X-Lookup-Proof']))
      .toEqual(['refused-proof', 'verified-proof']);
    expect(request.mock.calls[1]![1]).toMatchObject({ credentials: 'omit', body: JSON.stringify({ trackingNumber: 'TEST123456' }) });
    expect(saved()).toMatchObject({ proof: 'verified-proof' });
  } finally {
    verifier.dispose();
  }
});

it('drops a saved proof the server refuses again after verifying', async () => {
  sessionStorage.setItem(PROOF_KEY, JSON.stringify({ proof: 'refused-proof', expiresAt: Date.now() + 600_000 }));
  const page = await reloaded();
  const verifier = page.mountLookupVerification(document.createElement('div'), 'en');
  try {
    const refused = vi.fn().mockImplementation(async () => required());
    const answer = page.createApiLinks(refused).detectCarrierPublic('TEST123456');
    const failure = expect(answer).rejects.toThrow('verification');
    await complete();
    await failure;
    expect(refused).toHaveBeenCalledTimes(2);
    expect(saved()).toBeNull();
    expect(page.lookupProof()).toBeNull();
  } finally {
    verifier.dispose();
  }
});

it('ignores an unreadable or expired saved proof', async () => {
  sessionStorage.setItem(PROOF_KEY, '{not json');
  expect((await reloaded()).lookupProof()).toBeNull();
  sessionStorage.setItem(PROOF_KEY, JSON.stringify({ proof: 42, expiresAt: Date.now() + 600_000 }));
  expect((await reloaded()).lookupProof()).toBeNull();
  sessionStorage.setItem(PROOF_KEY, JSON.stringify({ proof: 'old-proof', expiresAt: Date.now() - 1 }));
  expect((await reloaded()).lookupProof()).toBeNull();
  expect(saved()).toBeNull();
});

it('keeps the proof for the page when session storage is unavailable', async () => {
  const unavailable = () => { throw new DOMException('Storage is disabled', 'SecurityError'); };
  vi.stubGlobal('sessionStorage', { getItem: unavailable, setItem: unavailable, removeItem: unavailable });
  expect(() => sessionStorage.getItem(PROOF_KEY)).toThrow('disabled');
  const page = await reloaded();
  const verifier = page.mountLookupVerification(document.createElement('div'), 'en');
  try {
    const proof = page.getLookupProof();
    await complete();
    await expect(proof).resolves.toBe('verified-proof');
    expect(page.lookupProof()).toBe('verified-proof');
    page.forgetLookupProof('verified-proof');
    expect(page.lookupProof()).toBeNull();
  } finally {
    verifier.dispose();
  }
});

describe('why a check failed, in one word', () => {
  const reason = (promise: Promise<unknown>) => promise.then(() => 'passed', (error: unknown) =>
    error instanceof LookupVerificationError ? error.reason : 'not a verification error');
  const rendered = () => vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalled());

  it.each([
    ['300030', 'widget-300'],
    ['600010', 'widget-600'],
    ['110200', 'widget-110'],
    ['110600', 'timeout'],
    ['110620', 'timeout'],
    ['999999', 'widget'],
    [undefined, 'widget'],
  ])('names the widget error %s by its family: %s', async (code, word) => {
    const failed = reason(getLookupProof());
    await rendered();
    (options['error-callback'] as (code?: string) => void)(code);
    await expect(failed).resolves.toBe(word);
  });

  it.each(['timeout-callback', 'expired-callback'])('counts the %s as a timeout', async (callback) => {
    const failed = reason(getLookupProof());
    await rendered();
    (options[callback] as () => void)();
    await expect(failed).resolves.toBe('timeout');
  });

  it('counts no token within a minute as a timeout', async () => {
    vi.useFakeTimers();
    try {
      const failed = reason(getLookupProof());
      await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalled());
      await vi.advanceTimersByTimeAsync(60_000);
      await expect(failed).resolves.toBe('timeout');
    } finally { vi.useRealTimers(); }
  });

  it('tells a token Peek refused from one it never answered', async () => {
    const refused = reason(getLookupProof());
    await rendered();
    vi.mocked(fetch).mockResolvedValueOnce(Response.json({ error: 'Forbidden' }, { status: 403 }));
    (options.callback as (token: string) => void)('single-use-token');
    await expect(refused).resolves.toBe('refused');
    const unanswered = reason(getLookupProof());
    await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalledTimes(2));
    vi.mocked(fetch).mockRejectedValueOnce(new TypeError('Failed to fetch'));
    (options.callback as (token: string) => void)('single-use-token');
    await expect(unanswered).resolves.toBe('unanswered');
  });

  it('counts settings it cannot read as setup', async () => {
    vi.mocked(fetch).mockResolvedValueOnce(new Response('', { status: 503 }));
    await expect(reason(getLookupProof())).resolves.toBe('setup');
    vi.mocked(fetch).mockResolvedValueOnce(Response.json({ siteKey: 42 }));
    await expect(reason(getLookupProof())).resolves.toBe('setup');
  });

  it('sends the failed check\'s word with the refusal, and the proof\'s when the server refuses a proof', async () => {
    const request = vi.fn().mockImplementation(async () => required());
    const failed = reason(createApiLinks(request).detectCarrierPublic('TEST123456'));
    await rendered();
    (options['error-callback'] as (code: string) => void)('300030');
    await expect(failed).resolves.toBe('widget-300');
    const refusedProof = reason(createApiLinks(request).detectCarrierPublic('TEST123456'));
    await vi.waitFor(() => expect(window.turnstile?.render).toHaveBeenCalledTimes(2));
    await complete();
    await expect(refusedProof).resolves.toBe('proof');
  });
});

import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { requireRoyalBrowser, royalBrowserAllowed, RoyalBrowserPaused, withRoyalBrowserAdmission } from './royalBrowserAdmission';

const { incident, log } = vi.hoisted(() => ({ incident: vi.fn(), log: vi.fn() }));
vi.mock('./runtime', () => ({ serviceClient: () => ({ trackingProviderIncidentOpen: incident }) }));
vi.mock('./observability', () => ({ logOperationalEvent: log, trackingAttemptId: () => 'attempt' }));
const client = { trackingProviderIncidentOpen: incident };
beforeEach(() => { incident.mockReset().mockResolvedValue(true); log.mockReset(); });
afterEach(() => vi.restoreAllMocks());

it.each([
  ['royal-mail', 'royal-mail', 'tracking', true],
  ['swiss-post', 'royal-mail', 'tracking', false],
  ['unknown', 'royal-mail', 'tracking', false],
  ['parcelforce', 'parcelforce', 'tracking', false],
  ['royal-mail', 'parcelforce', 'tracking', false],
  ['royal-mail', 'royal-mail', 'recognition', false],
  ['royal-mail', 'royal-mail', 'handoff', false],
  ['ups', 'ups', 'tracking', true],
] as const)('filed %s asking %s for %s during the incident: %s', async (filed, carrier, phase, allowed) => {
  await expect(withRoyalBrowserAdmission(filed, client, () => royalBrowserAllowed(carrier, phase))).resolves.toBe(allowed);
  expect(incident).toHaveBeenCalledTimes(allowed ? 0 : 1);
  expect(log).toHaveBeenCalledTimes(allowed ? 0 : 1);
  if (!allowed) expect(log).toHaveBeenCalledWith('tracking_browser_paused', {
    carrier, filed_carrier: filed, phase, reason: 'incident_open', attempt_id: 'attempt',
  });
});

it('does not create recognition sessions without a filed parcel, and resumes when the incident closes', async () => {
  await expect(requireRoyalBrowser('royal-mail', 'recognition')).rejects.toBeInstanceOf(RoyalBrowserPaused);
  incident.mockResolvedValue(false);
  await expect(royalBrowserAllowed('royal-mail', 'recognition')).resolves.toBe(true);
  await expect(withRoyalBrowserAdmission('ups', client, () => royalBrowserAllowed('royal-mail'))).resolves.toBe(true);
});

it('keeps optional sessions paused on a state-read failure while filed Royal Mail checks remain eligible', async () => {
  incident.mockRejectedValue(new Error('Database unavailable'));
  await expect(withRoyalBrowserAdmission('ups', client, () => royalBrowserAllowed('royal-mail'))).resolves.toBe(false);
  expect(log).toHaveBeenLastCalledWith('tracking_browser_paused', expect.objectContaining({ reason: 'incident_unavailable' }));
  await expect(withRoyalBrowserAdmission('royal-mail', client, () => royalBrowserAllowed('royal-mail'))).resolves.toBe(true);
  expect(incident).toHaveBeenCalledOnce();
});

it('shares filed context between separately compiled modules and isolates concurrent parcels', async () => {
  vi.resetModules();
  const worker = await import('./royalBrowserAdmission');
  const allowed = await Promise.all([
    withRoyalBrowserAdmission('royal-mail', client, () => worker.royalBrowserAllowed('royal-mail')),
    withRoyalBrowserAdmission('ups', client, () => worker.royalBrowserAllowed('royal-mail')),
  ]);
  expect(allowed).toEqual([true, false]);
});

it('bounds the incident read by the caller budget and propagates cancellation without a pause event', async () => {
  const controller = new AbortController();
  incident.mockImplementation(async (_provider, options) => {
    expect(options).toEqual({ signal: controller.signal, timeoutMs: 7 });
    controller.abort(new Error('Cancelled'));
    throw new Error('Request aborted');
  });
  await expect(royalBrowserAllowed('royal-mail', 'recognition', { signal: controller.signal, budgetMs: 7 })).rejects.toThrow('Cancelled');
  expect(log).not.toHaveBeenCalled();
  incident.mockClear();
  await expect(royalBrowserAllowed('royal-mail', 'recognition', { signal: controller.signal })).rejects.toThrow('Cancelled');
  expect(incident).not.toHaveBeenCalled();
});

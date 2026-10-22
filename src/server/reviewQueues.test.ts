import { afterEach, describe, expect, it, vi } from 'vitest';
import manifest from '../../package.json';
import * as observability from './observability';
import {
  deployedVersion,
  MAX_REPLAYED_CASES,
  MAX_REPLAYED_OBSERVATIONS,
  replayReviewQueues,
  replaySupportCase,
  ReviewQueueReplay,
  settleTypedSupportCases,
  statusMapClosings,
} from './reviewQueues';
import { SupabaseError, SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

const scraper = `universal-parcel-scraper@${manifest.dependencies['universal-parcel-scraper']}`;
const HANDED = "DPD's map leaves it unmapped on purpose: it moves the parcel in transit without a milestone of its own.";
const NOTICE = 'A notice to the recipient, not a scan: it keeps the stage the parcel already had.';

/** Open wording as the review queue keeps it; the keys are synthetic. */
const observation = (key: string, carrier: string, code: string | null, description: string, extra: Record<string, string> = {}) => ({
  observation_key: key.repeat(64), carrier, provider_code: code, description_normalized: description,
  chosen_stage: 'in_transit', last_seen_version: 'v0', ...extra,
});
const OPEN_OBSERVATIONS = [
  observation('1', 'dpd', 'PARCEL_HANDED', 'parcel handed to dpd'),
  observation('2', 'dpd', 'DLO', 'your parcel is out for delivery', { chosen_stage: 'out_for_delivery' }),
  observation('3', 'tnt', 'RES', 'shipment delivered in good condition', { chosen_stage: 'pending' }),
  observation('4', 'chronopost', 'SM', 'destinataire informé par sms ou mail'),
  observation('5', 'dpd', null, 'your parcel is on its way'),
  // Unknown to every map.
  observation('6', 'ctt', '99', 'estado interno 99'),
  // This version saw it unmapped: its map covers it only sometimes.
  observation('7', 'dpd', 'DEY', 'delivered', { last_seen_version: 'v1' }),
];

function fakeClient(claims: Array<'claimed' | 'running' | 'done' | Error> = ['claimed']) {
  const claim = vi.fn();
  for (const answer of claims) {
    if (answer instanceof Error) claim.mockRejectedValueOnce(answer);
    else claim.mockResolvedValueOnce(answer);
  }
  return {
    claimTrackingReviewRun: claim.mockResolvedValue('done'),
    listOpenTrackingSupportCases: vi.fn().mockResolvedValue([
      // A Royal Mail shape the configured carrier now tracks directly.
      { id: 'case-royal', tracking_number: 'RR123456785GB', configured_carrier: 'royal-mail' },
      // Still ambiguous.
      { id: 'case-ambiguous', tracking_number: '123456789012', configured_carrier: 'fedex' },
      // The tracking platform still depends on universal providers.
      { id: 'case-adapter', tracking_number: 'ZZTEST1234', configured_carrier: 'shipup' },
    ]),
    fixReplayedTrackingSupportCases: vi.fn().mockResolvedValue(1),
    // The ambiguous case's parcel is still on its way.
    trackingNumberStages: vi.fn(async (numbers: string[]) => new Map(numbers.map((number) => [number, ['in_transit']]))),
    // No saved parcel for the numbers of unfiled cases.
    trackingNumberParcels: vi.fn(async (): Promise<Map<string, JsonObject[]>> => new Map()),
    ignoreReplayedTrackingSupportCases: vi.fn(async (_version: string, cases: unknown[]) => cases.length),
    listOpenTrackingStatusObservations: vi.fn().mockResolvedValue(OPEN_OBSERVATIONS),
    closeTrackingStatusObservations: vi.fn(async (_version: string, closing: { keys: string[] }) => closing.keys.length),
    finishTrackingReviewRun: vi.fn().mockResolvedValue(undefined),
  };
}

const SUMMARY = {
  cases_replayed: 3, cases_fixed: 1, cases_ignored: 0, observations_replayed: 7, observations_mapped: 2, observations_ignored: 3,
};

afterEach(() => {
  vi.useRealTimers();
  vi.restoreAllMocks();
});

describe('cases on the start of a number just saved', () => {
  afterEach(() => vi.restoreAllMocks());

  it('closes those only detection saw within the hour, and logs a failure without the number', async () => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const ignore = vi.spyOn(client, 'ignoreTypedTrackingSupportCases').mockResolvedValueOnce(1).mockRejectedValueOnce(new Error('timeout'));
    const log = vi.spyOn(console, 'error').mockImplementation(() => undefined);
    const now = new Date('2026-10-22T12:00:00Z');
    await settleTypedSupportCases(client, 'ZZ00123456', now);
    expect(ignore).toHaveBeenCalledExactlyOnceWith('ZZ00123456', new Date('2026-10-22T11:00:00Z'), deployedVersion(), expect.stringContaining('typed'));
    await settleTypedSupportCases(client, 'ZZ00123456', now);
    expect(log).toHaveBeenCalledOnce();
    expect(String(log.mock.calls[0][0])).not.toContain('ZZ00123456');
  });
});

describe('review queue replay', () => {
  it('names the scraper the app pins and, in an image, the app commit', () => {
    expect(deployedVersion({})).toBe(scraper);
    expect(deployedVersion({ IMAGE_COMMIT: 'not-a-commit' })).toBe(scraper);
    expect(deployedVersion({ IMAGE_COMMIT: `${'A1'.repeat(20)}\n` })).toBe(`${scraper} app@a1a1a1a1a1a1`);
    expect(deployedVersion({ IMAGE_COMMIT: 'a'.repeat(40) }).length).toBeLessThanOrEqual(100);
  });

  it('resolves a case only when detection and adapters leave no gap', () => {
    expect(replaySupportCase('RR123456785GB', 'royal-mail'))
      .toBe('Replay found no gap: detection names royal-mail with high confidence.');
    expect(replaySupportCase('123456789012', 'fedex')).toBeNull();
    expect(replaySupportCase('ZZTEST1234', 'shipup')).toBeNull();
    expect(replaySupportCase('RR123456785FI', null)).toBeNull();
    // Filed under the origin, verified directly by the local carrier the number names.
    expect(replaySupportCase('CNPRT12345678901234567890', 'aliexpress')).toBeNull();
    expect(replaySupportCase('CNPRT12345678901234567890', 'aliexpress', 'ecoscooting')).not.toBeNull();
    // An ambiguous shape its filed carrier tracked directly.
    expect(replaySupportCase('123456789012', 'fedex', 'fedex'))
      .toBe('Replay found no gap: fedex, one of the carriers its shape fits, tracks the number directly.');
  });

  it("fixes a case its parcel's latest check left without a reason", async () => {
    const client = fakeClient();
    client.listOpenTrackingSupportCases.mockResolvedValue([
      { id: 'case-settled', tracking_number: '123456789012', configured_carrier: 'fedex', reasons: [] },
      { id: 'case-ambiguous', tracking_number: '123456789012', configured_carrier: 'fedex', reasons: ['ambiguous_shape'] },
      { id: 'case-recognition', tracking_number: '123456789012', configured_carrier: 'fedex', reasons: ['recognition_unknown'] },
    ]);
    await replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1');
    expect(client.fixReplayedTrackingSupportCases).toHaveBeenCalledExactlyOnceWith('v1', [{
      id: 'case-settled', configured_carrier: 'fedex', note: 'Replay found no gap: the latest check of its parcel gave no reason.',
    }]);
  });

  it('ignores a pickup code filed under no carrier instead of fixing it', async () => {
    const client = fakeClient();
    client.listOpenTrackingSupportCases.mockResolvedValue([
      { id: 'case-pickup', tracking_number: '204617', configured_carrier: 'unknown', reasons: ['unknown_shape'] },
      // A carrier picked by hand takes six digits as its number.
      { id: 'case-picked', tracking_number: '204618', configured_carrier: 'pannon-xp', reasons: ['unknown_shape'] },
    ]);
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1'))
      .resolves.toMatchObject({ cases_replayed: 2, cases_fixed: 0, cases_ignored: 1 });
    expect(client.fixReplayedTrackingSupportCases).not.toHaveBeenCalled();
    expect(client.trackingNumberParcels).not.toHaveBeenCalled();
    expect(client.ignoreReplayedTrackingSupportCases).toHaveBeenCalledExactlyOnceWith('v1', [{ id: 'case-pickup',
      note: 'Six digits filed under no carrier: a pickup code, not a tracking number. The apps ask for the parcel’s tracking number instead.' }]);
  });

  it('ignores a case only its carrier could confirm once every parcel with the number has finished', async () => {
    const client = fakeClient();
    client.listOpenTrackingSupportCases.mockResolvedValue([
      { id: 'case-delivered', tracking_number: '123456789012', configured_carrier: 'fedex', reasons: ['ambiguous_shape'] },
      { id: 'case-saved-twice', tracking_number: '123456789023', configured_carrier: 'fedex', reasons: ['ambiguous_shape'] },
      { id: 'case-deleted', tracking_number: '123456789001', configured_carrier: 'fedex', reasons: ['ambiguous_shape'] },
      // Detection could still learn these: they stay open.
      { id: 'case-unknown', tracking_number: '123456789012', configured_carrier: 'unknown', reasons: ['ambiguous_shape'] },
      { id: 'case-adapter', tracking_number: 'ZZTEST1234', configured_carrier: 'shipup', reasons: ['unknown_shape', 'no_direct_adapter'] },
    ]);
    client.trackingNumberStages.mockResolvedValue(new Map([
      ['123456789012', ['delivered']], ['123456789023', ['delivered', 'in_transit']],
    ]));
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1'))
      .resolves.toMatchObject({ cases_replayed: 5, cases_ignored: 1 });
    expect(client.trackingNumberStages).toHaveBeenCalledExactlyOnceWith(['123456789012', '123456789023', '123456789001']);
    expect(client.ignoreReplayedTrackingSupportCases).toHaveBeenCalledExactlyOnceWith('v1', [{ id: 'case-delivered',
      note: 'Its parcel was delivered before fedex, one of the carriers its shape fits, confirmed the number directly: no check is left to confirm it.' }]);
  });

  it('ignores a case whose parcels are all one-off lookups that left the schedule before anyone answered', async () => {
    const client = fakeClient();
    const lookup = (values: JsonObject = {}): JsonObject => ({ one_off: true, carrier: 'unknown', current_stage: 'pending',
      created_at: '2026-09-10T05:00:00Z', carrier_data: {}, ...values });
    client.listOpenTrackingSupportCases.mockResolvedValue(['unseen', 'recent', 'account', 'answered', 'deleted'].map((name, index) => ({
      id: `case-${name}`, tracking_number: `12345678900${index}`, configured_carrier: 'unknown', reasons: ['ambiguous_shape', 'recognition_unknown'],
      // The deleted parcel's last check had an answer.
      last_outcome: 'updated',
    })));
    client.trackingNumberParcels.mockResolvedValue(new Map([
      ['123456789000', [lookup(), lookup({ created_at: '2026-09-09T05:00:00Z', archived_at: '2026-09-09T06:00:00Z' })]],
      // Still in the schedule: added less than 6 hours ago.
      ['123456789001', [lookup({ created_at: '2026-09-10T07:00:00Z' })]],
      // An account's parcel is still checked.
      ['123456789002', [lookup(), lookup({ one_off: false })]],
      ['123456789003', [lookup({ carrier_data: { routing: { version: 1, last_success_at: '2026-09-10T06:00:00Z' } } })]],
    ]));
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1', new Date('2026-09-10T12:00:00Z')))
      .resolves.toMatchObject({ cases_replayed: 5, cases_ignored: 1 });
    expect(client.trackingNumberParcels).toHaveBeenCalledExactlyOnceWith(
      ['123456789000', '123456789001', '123456789002', '123456789003', '123456789004']);
    expect(client.ignoreReplayedTrackingSupportCases).toHaveBeenCalledExactlyOnceWith('v1', [{ id: 'case-unseen',
      note: 'Every parcel with the number is a one-off lookup that left the schedule before any carrier or provider answered for it: no check is left to update the case.' }]);
  });

  it('ignores a case whose parcels are gone once a check of one got no answer, not one only detection saw', async () => {
    const client = fakeClient();
    const unfiled = (name: string, index: number, values: JsonObject): JsonObject => ({
      id: `case-${name}`, tracking_number: `12345678900${index}`, configured_carrier: 'unknown', reasons: ['unknown_shape'], ...values,
    });
    client.listOpenTrackingSupportCases.mockResolvedValue([
      unfiled('unanswered', 0, { last_outcome: 'waiting' }),
      // The Add sheet's recognition alone saw it: detection could still learn the shape.
      unfiled('detection', 1, { last_outcome: 'detection_unknown' }),
      unfiled('answered', 2, { last_outcome: 'unchanged' }),
      unfiled('verified', 3, { last_outcome: 'waiting', direct_verified_carrier: 'fedex' }),
      // Its lookup is still followed.
      unfiled('saved', 4, { last_outcome: 'waiting' }),
    ]);
    client.trackingNumberParcels.mockResolvedValue(new Map([
      ['123456789004', [{ one_off: true, carrier: 'unknown', current_stage: 'pending', created_at: '2026-09-10T11:00:00Z', carrier_data: {} }]],
    ]));
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1', new Date('2026-09-10T12:00:00Z')))
      .resolves.toMatchObject({ cases_replayed: 5, cases_ignored: 1 });
    expect(client.ignoreReplayedTrackingSupportCases).toHaveBeenCalledExactlyOnceWith('v1', [{ id: 'case-unanswered',
      note: 'No parcel with the number is saved any more, and its last check got no answer for it: no check is left to update the case.' }]);
  });

  it('fixes the resolved open cases once and records the outcome for the version', async () => {
    const client = fakeClient();
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1')).resolves.toEqual(SUMMARY);
    expect(client.claimTrackingReviewRun).toHaveBeenCalledExactlyOnceWith('v1', 600);
    expect(client.listOpenTrackingSupportCases).toHaveBeenCalledExactlyOnceWith(MAX_REPLAYED_CASES);
    expect(client.fixReplayedTrackingSupportCases).toHaveBeenCalledExactlyOnceWith('v1', [{
      id: 'case-royal', configured_carrier: 'royal-mail',
      note: 'Replay found no gap: detection names royal-mail with high confidence.',
    }]);
    expect(client.finishTrackingReviewRun).toHaveBeenCalledExactlyOnceWith('v1', SUMMARY);
  });

  it("closes the open wording the scraper's carrier maps stage or leave unmapped on purpose", async () => {
    const client = fakeClient();
    await replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1');
    expect(client.listOpenTrackingStatusObservations).toHaveBeenCalledExactlyOnceWith(MAX_REPLAYED_OBSERVATIONS);
    expect(client.closeTrackingStatusObservations.mock.calls).toEqual([
      ['v1', { resolution: 'ignored', note: HANDED, keys: ['1'.repeat(64)] }],
      ['v1', { resolution: 'mapped', note: 'The carrier status map gives it out_for_delivery.', keys: ['2'.repeat(64)] }],
      // A stage its sightings did not have says their stored scans may need a repair.
      ['v1', {
        resolution: 'mapped', note: 'The carrier status map gives it delivered; it was last seen as pending.',
        keys: ['3'.repeat(64)],
      }],
      ['v1', { resolution: 'ignored', note: NOTICE, keys: ['4'.repeat(64)] }],
      ['v1', {
        resolution: 'ignored', note: "The label of IN_TRANSIT, which DPD's map leaves unmapped on purpose.",
        keys: ['5'.repeat(64)],
      }],
    ]);
  });

  it('counts the rows a closing found still open, and closes nothing without an answer', async () => {
    const client = fakeClient();
    client.closeTrackingStatusObservations.mockResolvedValue(0);
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1')).resolves.toMatchObject({
      observations_replayed: 7, observations_mapped: 0, observations_ignored: 0,
    });
    client.listOpenTrackingStatusObservations.mockResolvedValue([observation('6', 'ctt', '99', 'estado interno 99')]);
    client.closeTrackingStatusObservations.mockClear();
    await replayReviewQueues(client as unknown as SupabaseServiceClient, 'v2');
    expect(client.closeTrackingStatusObservations).not.toHaveBeenCalled();
  });

  it('groups closings by resolution and note, and keeps only the gaps unless asked for mappings', () => {
    const handed = [observation('a', 'dpd', 'PARCEL_HANDED', 'parcel handed to dpd'),
      observation('b', 'dpd', 'IN_TRANSIT', 'paket unterwegs')];
    expect(statusMapClosings([...OPEN_OBSERVATIONS, ...handed])).toEqual([
      { resolution: 'ignored', note: HANDED, keys: ['1'.repeat(64), 'a'.repeat(64), 'b'.repeat(64)] },
      { resolution: 'ignored', note: NOTICE, keys: ['4'.repeat(64)] },
      { resolution: 'ignored', note: "The label of IN_TRANSIT, which DPD's map leaves unmapped on purpose.", keys: ['5'.repeat(64)] },
    ]);
    // Without a version, nothing says the wording was seen unmapped by this one.
    expect(statusMapClosings(OPEN_OBSERVATIONS, { mapped: true }).map(({ keys }) => keys[0]![0]))
      .toEqual(['1', '2', '3', '4', '5', '7']);
    expect(statusMapClosings([{ ...OPEN_OBSERVATIONS[1]!, observation_key: '' }], { mapped: true })).toEqual([]);
  });

  it('leaves the cases alone while another server replays them or once one did', async () => {
    for (const state of ['running', 'done'] as const) {
      const client = fakeClient([state]);
      await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1')).resolves.toBe(state);
      expect(client.listOpenTrackingSupportCases).not.toHaveBeenCalled();
      expect(client.finishTrackingReviewRun).not.toHaveBeenCalled();
    }
  });

  it('finishes a replay that resolved nothing without writing cases', async () => {
    const client = fakeClient();
    client.listOpenTrackingSupportCases.mockResolvedValue([
      { id: 'case-ambiguous', tracking_number: '123456789012', configured_carrier: 'fedex' },
    ]);
    await expect(replayReviewQueues(client as unknown as SupabaseServiceClient, 'v1'))
      .resolves.toMatchObject({ cases_replayed: 1, cases_fixed: 0 });
    expect(client.fixReplayedTrackingSupportCases).not.toHaveBeenCalled();
  });

  it('runs a minute after startup, waits for another server, and stops once done', async () => {
    vi.useFakeTimers();
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const client = fakeClient(['running', 'claimed']);
    const replay = new ReviewQueueReplay(client as unknown as SupabaseServiceClient, 'v1');
    replay.start();
    replay.start();
    await vi.advanceTimersByTimeAsync(59_000);
    expect(client.claimTrackingReviewRun).not.toHaveBeenCalled();
    await vi.advanceTimersByTimeAsync(1_000);
    expect(client.claimTrackingReviewRun).toHaveBeenCalledOnce();
    await vi.advanceTimersByTimeAsync(5 * 60_000);
    expect(client.claimTrackingReviewRun).toHaveBeenCalledTimes(2);
    expect(logged).toHaveBeenCalledExactlyOnceWith('review_queues_replayed', { version: 'v1', ...SUMMARY });
    replay.start();
    await vi.advanceTimersByTimeAsync(60 * 60_000);
    expect(client.claimTrackingReviewRun).toHaveBeenCalledTimes(2);
    expect(vi.getTimerCount()).toBe(0);
  });

  it('retries a failed replay, reporting it once and never a database outage', async () => {
    vi.useFakeTimers();
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const captured = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const outage = new SupabaseError('Supabase request failed', 503);
    const client = fakeClient([outage, new Error('replay failed'), new Error('replay failed'), 'done']);
    const replay = new ReviewQueueReplay(client as unknown as SupabaseServiceClient, 'v1');
    replay.start();
    await vi.advanceTimersByTimeAsync(60_000 + 3 * 5 * 60_000);
    expect(client.claimTrackingReviewRun).toHaveBeenCalledTimes(4);
    expect(logged.mock.calls.filter(([event]) => event === 'review_queue_replay_failed')).toEqual([
      ['review_queue_replay_failed', { version: 'v1', error_type: 'SupabaseError' }, 'error'],
      ['review_queue_replay_failed', { version: 'v1', error_type: 'Error' }, 'error'],
      ['review_queue_replay_failed', { version: 'v1', error_type: 'Error' }, 'error'],
    ]);
    expect(captured).toHaveBeenCalledExactlyOnceWith(expect.any(Error), { component: 'review-queues', operation: 'replay' });
    expect(vi.getTimerCount()).toBe(0);
  });

  it('does not run once stopped', async () => {
    vi.useFakeTimers();
    const client = fakeClient();
    const replay = new ReviewQueueReplay(client as unknown as SupabaseServiceClient, 'v1');
    replay.start();
    replay.stop();
    await vi.advanceTimersByTimeAsync(60 * 60_000);
    expect(client.claimTrackingReviewRun).not.toHaveBeenCalled();
  });
});

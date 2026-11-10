import { afterEach, expect, it, vi } from 'vitest';
import { SyncJobWorker, type BackgroundState } from './background';
import * as metrics from './metrics';
import * as monitoring from './observability';
import { SupabaseError, SupabaseServiceClient } from './supabase';
import { emptySyncSummary, TrackingSyncService } from './trackingSync';

afterEach(() => { vi.restoreAllMocks(); vi.useRealTimers(); });

function scheduledRun() {
  vi.useFakeTimers();
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  const client = new SupabaseServiceClient('https://database.test', 'test');
  vi.spyOn(client, 'claimSyncJob').mockResolvedValueOnce({ id: 'job', kind: 'scheduled' }).mockResolvedValue(null);
  vi.spyOn(client, 'takeLostScheduledRuns').mockResolvedValue([]);
  const finish = vi.spyOn(client, 'finishSyncJob').mockResolvedValue();
  vi.spyOn(client, 'archiveDeliveredBefore').mockResolvedValue(0);
  vi.spyOn(client, 'maintainSyncAudit').mockResolvedValue({ abandoned: 0, purged: 0 });
  const feedback = vi.spyOn(client, 'forgetOldParcelFeedback').mockResolvedValue(0);
  const review = vi.spyOn(client, 'forgetUnfollowedTrackingReview').mockResolvedValue({ cases: 0, signals: 0 });
  const usage = vi.spyOn(client, 'recordUsageMetrics').mockResolvedValue();
  const service = new TrackingSyncService(client);
  const sync = vi.spyOn(service, 'sync').mockResolvedValue(emptySyncSummary());
  const redetections = vi.spyOn(service, 'queueRedetections').mockResolvedValue(0);
  const state: BackgroundState = { workerHeartbeat: null, lastScheduledSync: null, nextScheduledSync: null,
    lastSummary: null, lastError: null, lastAutoArchived: 0 };
  return { client, feedback, finish, redetections, review, state, sync, usage, worker: new SyncJobWorker(service, state) };
}

it('forgets expired lookups, stopped links and finished alerts, and refreshes the usage gauges, after each scheduled sync', async () => {
  const { client, finish, redetections, sync, worker } = scheduledRun();
  const forget = vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 3, packages: 2, stopped: 4, alerts: 5 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 40, p50: 2, p90: 9, max: 15, detection: { buckets: 31, p50: 3, p90: 12, max: 60 } });
  const forgotten = vi.spyOn(metrics, 'recordParcelsForgotten');
  const ended = vi.spyOn(metrics, 'recordParcelAlertRemoved');
  const usage = vi.spyOn(metrics, 'recordPublicLookupUsage');
  const logged = vi.spyOn(monitoring, 'logOperationalEvent');
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  // A new catalog's checks are queued once the run is over.
  expect(redetections).toHaveBeenCalledOnce();
  expect(sync.mock.invocationCallOrder[0]).toBeLessThan(redetections.mock.invocationCallOrder[0]);
  expect(forget).toHaveBeenCalledOnce();
  expect(forgotten.mock.calls).toEqual([
    ['expired', { links: 3, packages: 2, stopped: 4, alerts: 5 }],
    ['stopped', { links: 4, packages: 0 }],
  ]);
  expect(ended).toHaveBeenCalledExactlyOnceWith('delivered', 5);
  expect(usage).toHaveBeenCalledExactlyOnceWith({ buckets: 40, p50: 2, p90: 9, max: 15, detection: { buckets: 31, p50: 3, p90: 12, max: 60 } });
  expect(logged).toHaveBeenCalledWith('parcel_links_forgotten', { links: 3, packages: 2, stopped: 4, alerts: 5 });
  expect(finish).toHaveBeenCalledExactlyOnceWith('job', worker.workerId, { result: expect.objectContaining({ checked: 0 }) });
});

it('finishes the scheduled sync when forgetting fails, and reports it', async () => {
  const { client, finish, state, worker } = scheduledRun();
  const failure = new SupabaseError('database down', 503);
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockRejectedValue(failure);
  const usage = vi.spyOn(client, 'publicLookupUsageSummary');
  const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, expect.objectContaining({ component: 'parcel-links', operation: 'maintenance' }));
  expect(usage).not.toHaveBeenCalled();
  expect(finish).toHaveBeenCalledExactlyOnceWith('job', worker.workerId, { result: expect.anything() });
  expect(state.lastError).toBeNull();
});

it('deletes what readers said of a parcel 90 days ago, after each scheduled sync', async () => {
  const { client, feedback, worker } = scheduledRun();
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 0, packages: 0, stopped: 0, alerts: 0 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 0, p50: 0, p90: 0, max: 0, detection: { buckets: 0, p50: 0, p90: 0, max: 0 } });
  feedback.mockResolvedValue(7);
  const logged = vi.spyOn(monitoring, 'logOperationalEvent');
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(feedback).toHaveBeenCalledOnce();
  expect(logged).toHaveBeenCalledWith('parcel_feedback_forgotten', { answers: 7 });
});

it('finishes the scheduled sync when deleting old answers fails, and reports it', async () => {
  const { client, feedback, finish, state, worker } = scheduledRun();
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 0, packages: 0, stopped: 0, alerts: 0 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 0, p50: 0, p90: 0, max: 0, detection: { buckets: 0, p50: 0, p90: 0, max: 0 } });
  const failure = new SupabaseError('database down', 503);
  feedback.mockRejectedValue(failure);
  const logged = vi.spyOn(monitoring, 'logOperationalEvent');
  const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, expect.objectContaining({ component: 'parcel-feedback', operation: 'maintenance' }));
  expect(logged).not.toHaveBeenCalledWith('parcel_feedback_forgotten', expect.anything());
  expect(finish).toHaveBeenCalledExactlyOnceWith('job', worker.workerId, { result: expect.anything() });
  expect(state.lastError).toBeNull();
});

it('deletes the review entries of numbers no parcel has followed for 10 days, after each scheduled sync', async () => {
  const { client, review, worker } = scheduledRun();
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 0, packages: 0, stopped: 0, alerts: 0 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 0, p50: 0, p90: 0, max: 0, detection: { buckets: 0, p50: 0, p90: 0, max: 0 } });
  review.mockResolvedValue({ cases: 3, signals: 1 });
  const logged = vi.spyOn(monitoring, 'logOperationalEvent');
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(review).toHaveBeenCalledOnce();
  expect(logged).toHaveBeenCalledWith('tracking_review_forgotten', { cases: 3, signals: 1 });
});

it('finishes the scheduled sync when deleting review entries fails, and reports it', async () => {
  const { client, finish, review, state, worker } = scheduledRun();
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 0, packages: 0, stopped: 0, alerts: 0 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 0, p50: 0, p90: 0, max: 0, detection: { buckets: 0, p50: 0, p90: 0, max: 0 } });
  const failure = new SupabaseError('database down', 503);
  review.mockRejectedValue(failure);
  const logged = vi.spyOn(monitoring, 'logOperationalEvent');
  const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, expect.objectContaining({ component: 'tracking-review', operation: 'maintenance' }));
  expect(logged).toHaveBeenCalledWith('tracking_review_maintenance_failed', { error_type: 'SupabaseError' }, 'error');
  expect(finish).toHaveBeenCalledExactlyOnceWith('job', worker.workerId, { result: expect.anything() });
  expect(state.lastError).toBeNull();
});

it('rewrites the usage counts after each scheduled sync, and finishes the sync when that fails', async () => {
  const { client, finish, state, usage, worker } = scheduledRun();
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 0, packages: 0, stopped: 0, alerts: 0 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 0, p50: 0, p90: 0, max: 0, detection: { buckets: 0, p50: 0, p90: 0, max: 0 } });
  const failure = new SupabaseError('database down', 503);
  usage.mockRejectedValue(failure);
  const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
  const logged = vi.spyOn(monitoring, 'logOperationalEvent');
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(usage).toHaveBeenCalledOnce();
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, expect.objectContaining({ component: 'usage-metrics', operation: 'record' }));
  expect(logged).toHaveBeenCalledWith('usage_metrics_failed', { error_type: 'SupabaseError' }, 'error');
  expect(finish).toHaveBeenCalledExactlyOnceWith('job', worker.workerId, { result: expect.anything() });
  expect(state.lastError).toBeNull();
});

it('leaves the upkeep to a scheduled run that gave way, and queues that run again behind the check it gave way to', async () => {
  const { client, finish, redetections, state, sync, usage, worker } = scheduledRun();
  sync.mockResolvedValue({ ...emptySyncSummary(), checked: 1, gave_way: true });
  const forget = vi.spyOn(client, 'forgetExpiredParcelLinks');
  const queue = vi.spyOn(client, 'enqueueSyncJob').mockResolvedValue({ row: {}, queued: true });
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(finish).toHaveBeenCalledExactlyOnceWith('job', worker.workerId, { result: expect.objectContaining({ checked: 1, gave_way: true }) });
  expect(queue).toHaveBeenCalledExactlyOnceWith({ scheduled: true });
  expect(finish.mock.invocationCallOrder[0]).toBeLessThan(queue.mock.invocationCallOrder[0]);
  for (const upkeep of [redetections, forget, usage, client.archiveDeliveredBefore]) expect(upkeep).not.toHaveBeenCalled();
  expect(state.lastScheduledSync).not.toBeNull();
});

it('queues no run again after a scheduled run that ended on its own', async () => {
  const { client, worker } = scheduledRun();
  vi.spyOn(client, 'forgetExpiredParcelLinks').mockResolvedValue({ links: 0, packages: 0, stopped: 0, alerts: 0 });
  vi.spyOn(client, 'publicLookupUsageSummary').mockResolvedValue({ buckets: 0, p50: 0, p90: 0, max: 0, detection: { buckets: 0, p50: 0, p90: 0, max: 0 } });
  const queue = vi.spyOn(client, 'enqueueSyncJob');
  worker.start();
  await vi.advanceTimersByTimeAsync(1);
  worker.stop();
  expect(queue).not.toHaveBeenCalled();
});

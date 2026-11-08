import { randomUUID } from 'node:crypto';
import { hostname } from 'node:os';
import type { SupabaseServiceClient } from './supabase';

/** How long a lease lasts on the database clock: a holder that crashed holds the work back this long at most. */
export const WORKER_LEASE_SECONDS = 60;
/** How often its holder renews it while it works. */
export const WORKER_LEASE_RENEW_MS = 15_000;
/**
 * A holder stops when no renewal has been confirmed for this long, half the lease: the send in
 * progress then has time to end and be recorded before another server could take the lease over.
 */
export const WORKER_LEASE_CONFIRMED_MS = 30_000;

/** The reason work stops when its lease could not be kept. */
export class WorkerLeaseLost extends Error {
  constructor(lease: string) {
    super(`Worker lease ${lease} was lost`);
    this.name = 'WorkerLeaseLost';
  }
}

type LeaseClient = Pick<SupabaseServiceClient, 'claimWorkerLease' | 'renewWorkerLease' | 'releaseWorkerLease'>;

/** Work that one server does at a time, under a named lease on the database clock. */
export class WorkerLease {
  readonly owner = `${hostname()}:${process.pid}:${randomUUID().slice(0, 12)}`;
  #running: Promise<void> | null = null;
  #held = false;

  constructor(readonly client: LeaseClient, readonly name: string, readonly now: () => number = () => Date.now()) {}

  /**
   * Runs `work` while this server holds the lease, renews it meanwhile and gives it up after. Null,
   * without running it, while another server holds the lease or this one is already running the work.
   * The work's signal aborts with `signal`, or with `WorkerLeaseLost` once the lease is gone or has
   * not been confirmed for `WORKER_LEASE_CONFIRMED_MS`.
   */
  async run<T>(work: (signal: AbortSignal) => Promise<T>, signal?: AbortSignal): Promise<T | null> {
    if (this.#running) return null;
    let finished!: () => void;
    this.#running = new Promise<void>((resolve) => { finished = resolve; });
    const lost = new AbortController();
    let heartbeat: NodeJS.Timeout | undefined;
    let deadline: NodeJS.Timeout | undefined;
    try {
      const asked = this.now();
      if (!await this.client.claimWorkerLease(this.name, this.owner, WORKER_LEASE_SECONDS)) return null;
      this.#held = true;
      const confirmed = (at: number) => {
        clearTimeout(deadline);
        deadline = setTimeout(() => lost.abort(new WorkerLeaseLost(this.name)), Math.max(0, at + WORKER_LEASE_CONFIRMED_MS - this.now()));
        deadline.unref();
      };
      confirmed(asked);
      let renewal: Promise<void> | null = null;
      // A renewal that does not answer is tried again at the next beat; the deadline stops the work if none does.
      heartbeat = setInterval(() => {
        if (renewal || lost.signal.aborted) return;
        const at = this.now();
        renewal = this.client.renewWorkerLease(this.name, this.owner, WORKER_LEASE_SECONDS).then((renewed) => {
          if (renewed) confirmed(at);
          else lost.abort(new WorkerLeaseLost(this.name));
        }, () => undefined).finally(() => { renewal = null; });
      }, WORKER_LEASE_RENEW_MS);
      heartbeat.unref();
      return await work(signal ? AbortSignal.any([signal, lost.signal]) : lost.signal);
    } finally {
      clearInterval(heartbeat);
      clearTimeout(deadline);
      if (this.#held) await this.release();
      this.#running = null;
      finished();
    }
  }

  /**
   * Gives the lease up for a server that stops. Work still running keeps it until it ends, since
   * the send in progress is not recorded yet, then gives it up itself; this waits for that.
   */
  async stop(): Promise<void> {
    if (this.#running) return await this.#running;
    if (this.#held) await this.release();
  }

  private async release(): Promise<void> {
    try {
      await this.client.releaseWorkerLease(this.name, this.owner);
      this.#held = false;
    } catch {
      // Kept as held, so stopping tries again; otherwise the lease runs out at its deadline.
    }
  }
}

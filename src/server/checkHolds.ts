import 'server-only';
import type { SharedLookups } from './sharedLookups';

/** A check under way: its parcel, what it holds, and the lookups a check of the same number waiting for it answers from. */
export interface CheckHold {
  readonly parcelId: string;
  completed?: { generation: unknown; carrier: unknown; postcode: unknown };
  readonly keys: readonly string[];
  /** Whether its lookups are expected to open a browser: it takes one of the browser slots. */
  readonly browser: boolean;
  readonly lookups?: SharedLookups;
}

/**
 * What the checks under way hold, the scheduled run's and the single parcels' alike (checkKeys in trackingSync.ts): no
 * two share a key at once, so the number, the account, the carrier's adapter and the provider stay one check's at a time
 * across lanes, and at most `browserSlots` checks whose lookups open a browser run at once. A check waiting for keys goes
 * before the run's next checks: the run starts none that needs what a waiting check needs.
 */
export class CheckHolds {
  readonly #held = new Map<string, CheckHold>();
  #browsers = 0;
  /** What the waiting checks need: each key's waiters, and the waiters that need a browser slot. */
  readonly #wanted = new Map<string, number>();
  #browserWanted = 0;
  #release: { promise: Promise<void>; resolve: () => void } | null = null;
  /** When a check of each parcel last began or ended (performance.now()), kept for `memoryMs`. */
  readonly #touched = new Map<string, number>();

  constructor(readonly browserSlots: number, readonly memoryMs = 10 * 60_000) {}

  /** The check that holds the key, if any. */
  holder(key: string): CheckHold | undefined {
    return this.#held.get(key);
  }

  /** Whether a check of the parcel began or ended at `since` (performance.now()) or later. */
  touchedSince(parcelId: string, since: number): boolean {
    return (this.#touched.get(parcelId) ?? Number.NEGATIVE_INFINITY) >= since;
  }

  /** Whether a check that needs these could start now; `yielding`, only when no waiting check needs any of them. */
  free(keys: readonly string[], browser: boolean, yielding = false): boolean {
    if (keys.some((key) => this.#held.has(key) || (yielding && this.#wanted.has(key)))) return false;
    return !browser || (this.#browsers < this.browserSlots && !(yielding && this.#browserWanted > 0));
  }

  /** Takes what the check holds, which must be free, and returns its release. */
  take(hold: CheckHold): () => void {
    for (const key of hold.keys) this.#held.set(key, hold);
    if (hold.browser) this.#browsers += 1;
    this.#touch(hold.parcelId);
    let released = false;
    return () => {
      if (released) return;
      released = true;
      for (const key of hold.keys) if (this.#held.get(key) === hold) this.#held.delete(key);
      if (hold.browser) this.#browsers -= 1;
      this.#touch(hold.parcelId);
      const waiting = this.#release;
      this.#release = null;
      waiting?.resolve();
    };
  }

  /** Settles at the next release, or once one of the signals aborts. */
  released(...signals: Array<AbortSignal | undefined>): Promise<void> {
    if (!this.#release) {
      let resolve!: () => void;
      const promise = new Promise<void>((settle) => { resolve = settle; });
      this.#release = { promise, resolve };
    }
    const { promise } = this.#release;
    const live = signals.filter((signal): signal is AbortSignal => signal !== undefined);
    if (live.length === 0) return promise;
    return new Promise<void>((resolve) => {
      const done = () => {
        for (const signal of live) signal.removeEventListener('abort', done);
        resolve();
      };
      if (live.some((signal) => signal.aborted)) return done();
      for (const signal of live) signal.addEventListener('abort', done, { once: true });
      void promise.then(done);
    });
  }

  /** Waits until what the check needs is free, ahead of the run's next checks, and takes it. Returns its release. */
  async acquire(hold: CheckHold, signal?: AbortSignal): Promise<() => void> {
    signal?.throwIfAborted();
    if (this.free(hold.keys, hold.browser)) return this.take(hold);
    for (const key of hold.keys) this.#wanted.set(key, (this.#wanted.get(key) ?? 0) + 1);
    if (hold.browser) this.#browserWanted += 1;
    try {
      while (!this.free(hold.keys, hold.browser)) {
        await this.released(signal);
        signal?.throwIfAborted();
      }
      return this.take(hold);
    } finally {
      for (const key of hold.keys) {
        const left = this.#wanted.get(key)! - 1;
        if (left > 0) this.#wanted.set(key, left);
        else this.#wanted.delete(key);
      }
      if (hold.browser) this.#browserWanted -= 1;
    }
  }

  #touch(parcelId: string): void {
    const now = performance.now();
    this.#touched.delete(parcelId);
    this.#touched.set(parcelId, now);
    for (const [id, at] of this.#touched) {
      if (now - at < this.memoryMs) break;
      this.#touched.delete(id);
    }
  }
}

import { carrierErrorKind } from 'universal-parcel-scraper';
import { logOperationalEvent } from './observability';
import { answeredWithoutHistory } from './trackingRouting';

/**
 * Carriers whose site refuses every route in spells: USPS turns TRAWL's sessions and the browser away alike for hours.
 * During one, each lookup waits out the same refusal, so scheduled checks rest the carrier for a moment instead.
 */
const RESTED_CARRIERS: ReadonlySet<string> = new Set(['usps']);
/** How many lookups in a row ending without the carrier's answer start a rest. */
export const REST_AFTER = 5;
/** How long a rest lasts. */
export const REST_MS = 3 * 60_000;
/** The lookup met a challenge, a page it could not read, or a rate limit, ours (`self_paced`) or the carrier's. */
const REFUSALS: ReadonlySet<string> = new Set(['challenge', 'indeterminate', 'rate_limited']);
/** The carrier answered for the number: not found, or as one it does not issue; without history too (answeredWithoutHistory). */
const ANSWERS: ReadonlySet<string> = new Set(['not_found', 'invalid_input']);

/**
 * Each lookup of a rested carrier counts toward its streak of lookups without an answer, whoever asked for the check;
 * an answer ends the streak. After REST_AFTER in a row, scheduled checks leave the carrier for REST_MS and ask the
 * universal providers, as while its failure's wait runs (tryDirect in trackingRouting.ts). A check someone asked for
 * (opened, added, refreshed) reads it all the same. The first lookup after the rest reads the carrier again: a refusal
 * rests it again, an answer ends the streak. Other failures (a transport error, a timeout) neither count nor end it.
 * Kept in memory by each server, which starts with none.
 */
export class CarrierRests {
  readonly #streaks = new Map<string, number>();
  readonly #until = new Map<string, number>();

  /** Records a lookup's end: `error` undefined for an answer. */
  noteLookup(carrier: string, error: unknown, now: number): void {
    if (!RESTED_CARRIERS.has(carrier)) return;
    const kind = error === undefined ? undefined : carrierErrorKind(error);
    if (error === undefined || ANSWERS.has(kind ?? '') || answeredWithoutHistory(error, true)) {
      const rested = this.rests(carrier, now);
      this.#streaks.delete(carrier);
      this.#until.delete(carrier);
      if (rested) logOperationalEvent('carrier_rest_ended', { carrier });
      return;
    }
    if (!REFUSALS.has(kind ?? '')) return;
    const streak = (this.#streaks.get(carrier) ?? 0) + 1;
    this.#streaks.set(carrier, streak);
    if (streak < REST_AFTER) return;
    if (!this.rests(carrier, now)) logOperationalEvent('carrier_rest_started', { carrier, streak, rest_ms: REST_MS });
    this.#until.set(carrier, now + REST_MS);
  }

  /** Whether scheduled checks leave the carrier at `now`. */
  rests(carrier: string, now: number): boolean {
    return this.until(carrier, now) > 0;
  }

  /** When the carrier's rest ends, as epoch milliseconds, zero when it is not resting at `now`. */
  until(carrier: string, now: number): number {
    const end = this.#until.get(carrier) ?? 0;
    return end > now ? end : 0;
  }
}

// The API and the instrumentation worker can evaluate separate copies of this module: one server keeps one streak.
const key = Symbol.for('peek.carrierRests');
const processScope = globalThis as typeof globalThis & { [key]?: CarrierRests };
export const carrierRests: CarrierRests = processScope[key] ??= new CarrierRests();

import { DateTime } from 'luxon';
import { EVENT_STAGE_ORDER } from '../lib/stages';
import { clockDay, scanDay } from './eventIdentity';
import type { JsonObject } from './types';

const SECOND_MS = 1_000;

/**
 * The instants of an answer's scans (`instants`, as `buildEvents` dates them) once a scan its carrier
 * dates by its day alone (`days`, which start on `dayZone`) also follows the newest scan of that day
 * in a stage no further on than its own (`stages`), of the answer or stored from any source: a second
 * after it. FedEx gives its pickup by its day; 17TRACK timed the label that day. A scan dated after
 * the check (`now`) leads none. The answer's own order stands: such a scan stays at its date when the
 * move would take it past the next scan the answer lists after it, a scan of that day in a later
 * stage, or a stage change Peek saw that day without a time, in its stage or a later one. Then every
 * instant stays as given. A wall clock in no zone (YunExpress's) keeps its date and leads none.
 */
export function afterSameDayScans(
  scans: readonly JsonObject[],
  days: readonly (string | null)[],
  dayZone: string,
  instants: readonly (string | null)[],
  stages: readonly string[],
  stored: readonly JsonObject[],
  now?: Date,
): (string | null)[] {
  const rank = (stage: unknown) => EVENT_STAGE_ORDER.indexOf(String(stage));
  const at = (value: unknown) => Date.parse(String(value ?? ''));
  const scan = (row: JsonObject) => !String(row.provider_event_id ?? '').startsWith('app:');
  // Scans with a clock in a stage of their own (Peek's pending is none), and stage changes seen without a time.
  const clocked = [
    ...instants.flatMap((instant, index) => days[index] ? [] : [{ at: at(instant), rank: rank(stages[index]) }]),
    ...stored.filter((row) => scan(row) && row.observed_without_provider_timestamp !== true && scanDay(row) === null && clockDay(row) === null)
      .map((row) => ({ at: at(row.occurred_at), rank: rank(row.stage) })),
  ].filter((row) => Number.isFinite(row.at) && row.rank > 0);
  const seen = stored.filter((row) => scan(row) && row.observed_without_provider_timestamp === true)
    .map((row) => ({ at: at(row.occurred_at), rank: rank(row.stage) })).filter((row) => Number.isFinite(row.at) && row.rank > 0);
  const moved = [...instants];
  let older = Number.NaN;
  let next = Number.POSITIVE_INFINITY;
  const nextClocked = instants.map((instant, index) => {
    const listed = next;
    if (!days[index] && Number.isFinite(at(instant))) next = at(instant);
    return listed;
  });
  for (let index = instants.length - 1; index >= 0; index -= 1) {
    const given = at(instants[index]);
    if (!Number.isFinite(given)) continue;
    const day = days[index];
    if (!day || scanDay(scans[index]) === null) {
      older = given;
      continue;
    }
    const start = DateTime.fromISO(day, { zone: dayZone });
    const ofDay = (row: { at: number }) => row.at >= start.toMillis() && row.at < start.plus({ days: 1 }).toMillis();
    const own = rank(stages[index]);
    const lead = Math.max(...clocked.filter((row) => ofDay(row) && row.rank <= own && (!now || row.at <= now.getTime())).map((row) => row.at));
    const to = Math.max(given, ofDay({ at: older }) ? older + SECOND_MS : given, lead + SECOND_MS);
    const bound = Math.min(nextClocked[index]!, ...clocked.filter((row) => ofDay(row) && row.rank > own).map((row) => row.at),
      ...seen.filter((row) => ofDay(row) && row.rank >= own).map((row) => row.at));
    if (to > given && to >= bound) return [...instants];
    if (to > given) moved[index] = DateTime.fromMillis(to, { zone: 'utc' }).toISO({ suppressMilliseconds: true });
    older = to;
  }
  return moved;
}

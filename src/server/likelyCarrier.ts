import 'server-only';
import type { RecognitionOutcome } from 'universal-parcel-scraper';
import { detectCarrierMatch } from '../lib/carriers';
import { CARRIER_DEFINITIONS } from 'universal-parcel-scraper';
import type { RoutingState } from './trackingRouting';
import { isRecord, type JsonObject } from './types';

/**
 * A rule fixing fewer characters of every number it takes, such as a bare run of digits or a letter and digits, takes
 * other carriers' numbers too often to name its carrier when that carrier does not list the number.
 */
const OWN_FORMAT_FIXED = 2;

/**
 * The fewest characters a detection rule's pattern names outright in every number it takes: `EOB` in BRT's
 * `^EOB\d{17}$`, the eight digits that open CTT Express's `^00001000\d{14}$`. Of alternatives, the one naming fewest;
 * a class, a lookaround or an optional part names none.
 */
export function fixedCharacters(pattern: string): number {
  let index = 0;
  const repeats = (): number => {
    let least = 1;
    const quantifier = /^(?:[?*+]|\{(\d+)(?:,\d*)?\})/.exec(pattern.slice(index));
    if (quantifier) {
      index += quantifier[0].length;
      least = quantifier[1] !== undefined ? Number(quantifier[1]) : quantifier[0] === '+' ? 1 : 0;
      if (pattern[index] === '?') index++;
    }
    return least;
  };
  const atom = (): number => {
    const char = pattern[index++]!;
    if (char === '^' || char === '$' || char === '.') return 0;
    if (char === '\\') return /[dDwWsSbB]/.test(pattern[index++] ?? '') ? 0 : 1;
    if (char === '[') {
      while (index < pattern.length && pattern[index] !== ']') index += pattern[index] === '\\' ? 2 : 1;
      index++;
      return 0;
    }
    if (char !== '(') return 1;
    const look = /^\?<?[=!]/.exec(pattern.slice(index));
    if (look) index += look[0].length;
    else if (pattern.startsWith('?:', index)) index += 2;
    const inner = alternatives();
    index++;
    return look ? 0 : inner;
  };
  const sequence = (): number => {
    let total = 0;
    while (index < pattern.length && pattern[index] !== '|' && pattern[index] !== ')') total += atom() * repeats();
    return total;
  };
  const alternatives = (): number => {
    let fewest = sequence();
    while (pattern[index] === '|') {
      index++;
      fewest = Math.min(fewest, sequence());
    }
    return fewest;
  };
  return alternatives();
}

/**
 * The carrier a number's format belongs to alone: detection's only candidate, through a rule of that carrier's that
 * names at least `OWN_FORMAT_FIXED` characters. A bare run of digits, which several carriers' rules take, names none.
 */
export function ownFormatCarrier(number: string): string | undefined {
  const { candidates } = detectCarrierMatch(number);
  const [carrier] = candidates;
  if (candidates.length !== 1 || !carrier) return undefined;
  return (CARRIER_DEFINITIONS[carrier]?.detectionRules ?? [])
    .some((rule) => new RegExp(rule.pattern).test(number) && fixedCharacters(rule.pattern) >= OWN_FORMAT_FIXED) ? carrier : undefined;
}

/** A parcel filed under no carrier, still on its way, that no carrier or provider has answered for or asked input for. */
function unanswered(parcel: JsonObject, state: RoutingState): boolean {
  const metadata = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  return String(parcel.carrier) === 'unknown' && !['delivered', 'returned'].includes(String(parcel.current_stage))
    && !metadata.original_carrier && !state.last_event_at && !state.confirmed_carrier && !state.discovered_carrier
    && !state.input_needed;
}

/** The likely carrier a check keeps from the last one: while the parcel is still unanswered and its number still that carrier's format alone. */
export function keptLikelyCarrier(parcel: JsonObject, state: RoutingState): RoutingState['likely_carrier'] {
  const kept = state.likely_carrier;
  return kept && unanswered(parcel, state) && ownFormatCarrier(String(parcel.tracking_number ?? '')) === kept.carrier
    ? kept : undefined;
}

/**
 * The likely carrier after a check's recognition: the carrier the number's format belongs to alone (ownFormatCarrier),
 * once recognition asked it and it did not say it knows the number, which it may not list until the sender hands the
 * parcel over. A carrier recognition did not ask keeps the last check's answer. `since` is when a check first found it so.
 */
export function likelyCarrierAfter(parcel: JsonObject, state: RoutingState, outcomes: readonly RecognitionOutcome[],
  now: Date): RoutingState['likely_carrier'] {
  const carrier = ownFormatCarrier(String(parcel.tracking_number ?? ''));
  const asked = outcomes.filter((outcome) => outcome.carrier === carrier);
  if (!carrier || !unanswered(parcel, state)) return undefined;
  if (!asked.length) return keptLikelyCarrier(parcel, state);
  if (asked.some((outcome) => outcome.status === 'known')) return undefined;
  return { carrier, since: state.likely_carrier?.carrier === carrier ? state.likely_carrier.since : now.toISOString() };
}

import 'server-only';
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

/**
 * The carrier a number's shape shares with others but whose own range it falls in: the only one detection prefers,
 * through a `preferred` rule of that carrier's that names at least `OWN_FORMAT_FIXED` characters, such as DPD
 * Switzerland's 060 and 061 depots among the fourteen digits every DPD network, SEUR, BRT and Ciblex use. A
 * preference for a check digit alone on a bare run of digits (FedEx's twelve, DHL Express's ten) names none.
 */
export function preferredCarrier(number: string): string | undefined {
  const { preferred } = detectCarrierMatch(number);
  const [carrier] = preferred;
  if (preferred.length !== 1 || !carrier) return undefined;
  return (CARRIER_DEFINITIONS[carrier]?.detectionRules ?? []).some((rule) => rule.preferred
    && new RegExp(rule.pattern).test(number) && fixedCharacters(rule.pattern) >= OWN_FORMAT_FIXED) ? carrier : undefined;
}

/** The carrier a number points to: the one its format belongs to alone, else the one whose own range it falls in. */
export function guessedCarrier(number: string): string | undefined {
  return ownFormatCarrier(number) ?? preferredCarrier(number);
}

/** A parcel filed under no carrier, still on its way, that no carrier or provider has answered for or asked input for. */
function unanswered(parcel: JsonObject, state: RoutingState): boolean {
  const metadata = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  return String(parcel.carrier) === 'unknown' && !['delivered', 'returned'].includes(String(parcel.current_stage))
    && !metadata.original_carrier && !state.last_event_at && !state.confirmed_carrier && !state.discovered_carrier
    && !state.input_needed;
}

/**
 * The carrier a parcel filed under none probably travels with: the one its number points to (guessedCarrier), once
 * asked, by recognition or directly, without saying it knows the number, which it may not list until the sender hands
 * the parcel over. A carrier that failed to answer recognition counts as asked; one that said it knows the number,
 * even with no more than a pre-advice, is more than likely, and is not named. `since` is when a check first found it so.
 */
export function likelyCarrier(parcel: JsonObject, state: RoutingState, now: Date): RoutingState['likely_carrier'] {
  const carrier = guessedCarrier(String(parcel.tracking_number ?? ''));
  if (!carrier || !unanswered(parcel, state)) return undefined;
  const probe = state.candidate_probes?.[carrier];
  const asked = probe ? !probe.known : ['not_found', 'no_history'].includes(state.failures[carrier]?.kind ?? '');
  if (!asked) return undefined;
  return { carrier, since: state.likely_carrier?.carrier === carrier ? state.likely_carrier.since : now.toISOString() };
}

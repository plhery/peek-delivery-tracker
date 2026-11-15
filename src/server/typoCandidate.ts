import 'server-only';

import {
  CARRIER_DEFINITIONS, detectCarrierMatch, normalizeTrackingNumber,
  type CarrierDetection, type CarrierId, type DetectionRule,
} from 'universal-parcel-scraper';
import { recordTypoCandidate } from './metrics';
import { logOperationalEvent } from './observability';

export interface TypoCandidate {
  /** The checksum of the detection rule the corrected number passes, such as `s10`. */
  checksum: NonNullable<DetectionRule['checksum']>;
  /** The carrier detection gives the corrected number. */
  carrier: CarrierId;
}

/**
 * The rule that decided detection's answer for one of its carriers, when that rule checked a
 * check digit. Detection names carriers, not rules: a carrier's deciding rule is its first rule
 * that fits the number and passes its checksum, of high confidence when the answer is. When all
 * the rules that could have decided name one checksum, the first of them passed it.
 */
function checksumRule(detection: CarrierDetection, carrier: CarrierId, number: string): DetectionRule | null {
  const fitting = CARRIER_DEFINITIONS[carrier].detectionRules.filter((rule) => new RegExp(rule.pattern).test(number)
    && (!rule.rawPattern || new RegExp(rule.rawPattern).test(number)));
  const deciding = detection.confidence === 'high' ? fitting.filter((rule) => rule.confidence === 'high') : fitting;
  const checksum = deciding[0]?.checksum;
  return checksum && deciding.every((rule) => rule.checksum === checksum) ? deciding[0] : null;
}

/**
 * Whether the number reads as a typed digit doubled: it passes no detection rule's check digit,
 * while leaving out one digit of two identical neighbours gives a number that a high-confidence
 * rule's check digit accepts. The UPU's S10 comes first. A lead for review only: nothing is
 * suggested or changed.
 */
export function typoCandidate(trackingNumber: string): TypoCandidate | null {
  const number = normalizeTrackingNumber(trackingNumber);
  const original = detectCarrierMatch(number);
  if (original.candidates.some((carrier) => checksumRule(original, carrier, number))) return null;
  const candidates: TypoCandidate[] = [];
  const variants = new Set<string>();
  for (let index = 0; index < number.length - 1; index++) {
    if (/\d/.test(number[index]) && number[index] === number[index + 1]) variants.add(number.slice(0, index) + number.slice(index + 1));
  }
  for (const variant of variants) {
    const detection = detectCarrierMatch(variant);
    for (const carrier of detection.candidates) {
      const rule = checksumRule(detection, carrier, variant);
      if (rule?.confidence === 'high') candidates.push({ checksum: rule.checksum!, carrier });
    }
  }
  return candidates.find((candidate) => candidate.checksum === 's10') ?? candidates[0] ?? null;
}

/**
 * Logs and counts the typo candidate of a number by checksum, carrier, whether an account or a
 * lookup added it, and its first check: `unanswered` when it failed or found nothing, `answered`
 * when it found scans all the same. The log line has the number as typed; the count never does.
 */
export function noteTypoCandidate(trackingNumber: string, origin: 'account' | 'lookup', firstCheck: 'answered' | 'unanswered'): void {
  try {
    const candidate = typoCandidate(trackingNumber);
    if (!candidate) return;
    recordTypoCandidate(candidate.checksum, candidate.carrier, origin, firstCheck);
    logOperationalEvent('typo_candidate', {
      tracking_number: trackingNumber, checksum: candidate.checksum, carrier: candidate.carrier, origin, first_check: firstCheck,
    });
  } catch { /* Telemetry must never change a tracking result. */ }
}

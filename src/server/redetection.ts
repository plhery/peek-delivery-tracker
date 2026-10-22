import 'server-only';
import { recognitionCandidates } from 'universal-parcel-scraper';
import { universalCarrierHints } from 'universal-parcel-scraper/app';
import { detectCarrierMatch } from '../lib/carriers';
import { AUTOMATIC_CARRIER_IDS } from './carriers';
import { localClockCarrier } from './directLocalHistory';
import { refusesNumber } from './numberRefusal';
import { asNewAs, catalogRules, directCarrier, opensBrowser, recognizers, releaseLapses, routingState } from './trackingRouting';
import { isRecord, type JsonObject } from './types';

/** Only parcels added this recently are looked at again, as carrier recognition only asks for them. */
export const REDETECTION_WINDOW_MS = 30 * 24 * 60 * 60 * 1_000;

/**
 * How long after a parcel's last check a release waits before asking again a carrier the
 * parcel already asked whose lookup can open a browser: its answer would most likely come
 * back the same. Another is asked again at once.
 */
export const RELEASE_REASK_MS = 12 * 60 * 60 * 1_000;

/**
 * The carrier the catalog's current rules point an open parcel to, when the
 * parcel was last routed with other rules and has not asked that carrier yet:
 * one added or last checked before a scraper release brought a detection rule,
 * a carrier name or an adapter, which nobody may check any more (a lookup
 * whose link nobody opens), one filed under a carrier that only
 * recognition asked before, or one whose filed carrier answered on local
 * clocks that the catalog now dates, or that the release may date, once the
 * parcel has gone RELEASE_REASK_MS without a check. A parcel filed under no carrier gets its detected
 * or named carrier, else a carrier that recognizes its number under this release
 * and did not under the one that last routed it, or else the first carrier its
 * check's recognition would ask, asked before or not, once that carrier's wait
 * is over. A carrier the parcel already asked whose lookup
 * can open a browser waits until the parcel has gone RELEASE_REASK_MS without a
 * check, unless the answer it gave, set aside as older than the parcel or on
 * local clocks, would now be taken. Null otherwise. Only parcels filed under no carrier, or showing a
 * universal provider's history, are looked at. Nothing is sent: the check
 * queued for the parcel asks the carrier. Carriers in `refused` are passed
 * over (rerouteAskable).
 */
export function rerouteCarrier(parcel: JsonObject, rules = catalogRules(), now = Date.now(),
  refused: ReadonlySet<string> = new Set()): string | null {
  return rerouteLead(parcel, rules, now, refused)?.carrier ?? null;
}

/**
 * The carrier a release's check would ask, and how likely that check is to change the
 * parcel's answer, the most likely first: 0, the carrier's own answer is now dated, it
 * refused the number under an older release, or the answer it gave that was set aside would
 * now be taken; 1, a carrier the parcel never asked, or one the release taught to recognize
 * the number of a parcel filed under no carrier; 2, one a parcel filed under no carrier
 * asks again, the first its recognition would ask, or the filed carrier whose answer on
 * local clocks the release may date. A release queues no check of a parcel filed under no
 * carrier at 2: its own schedule asks those again (queueRedetections).
 */
export interface RerouteLead { carrier: string; rank: 0 | 1 | 2 }

function rerouteLead(parcel: JsonObject, rules: string, now: number, refused: ReadonlySet<string>): RerouteLead | null {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const routing = routingState(parcel);
  const filed = String(parcel.carrier);
  const number = String(parcel.tracking_number ?? '');
  const unfiled = filed === 'unknown' || filed === 'intl-post';
  const provider = typeof data.tracking_provider === 'string';
  if (routing.rules === rules || data.original_carrier || (!unfiled && !provider)
    || ['delivered', 'returned'].includes(String(parcel.current_stage))) return null;
  // A carrier that refused the number under an older release counts as not asked:
  // this release's adapter may take it (rerouteAskable checks).
  const refusedBefore = (carrier: string) => routing.failures[carrier]?.user_error === 'carrier:invalid_input';
  // An answer set aside as older than the parcel that the parcel's newest scan has since
  // come back to, as a provider's corrected clock brings it, or one on local clocks that
  // the catalog now dates: the carrier's check would take it. One without movement wouldn't.
  const fits = (carrier: string) => {
    const aside = routing.set_aside?.[carrier];
    return aside?.reason === 'local_clock' ? !localClockCarrier(carrier)
      : asNewAs(Date.parse(aside?.newest ?? ''), Date.parse(routing.last_event_at ?? ''));
  };
  const asked = (carrier: string) => carrier === routing.confirmed_carrier || Boolean(routing.set_aside?.[carrier])
    || (Boolean(routing.failures[carrier]) && !refusedBefore(carrier)) || Boolean(routing.candidate_probes?.[carrier]);
  const checkedRecently = now - Date.parse(String(parcel.last_synced_at ?? '')) < RELEASE_REASK_MS;
  // A refusal or an unreadable answer waits no longer: the check this release queues asks again.
  const waiting = (carrier: string) => Date.parse(routing.candidate_probes?.[carrier]?.retry_at ?? '') > now
    || (Date.parse(routing.failures[carrier]?.retry_at ?? '') > now && !releaseLapses(carrier, routing.failures[carrier]))
    || (checkedRecently && opensBrowser(carrier) && asked(carrier) && !fits(carrier));
  // A carrier with an adapter of its own that the parcel never asked, or an unfiled parcel
  // can ask again, or one an unfiled parcel's check asks the providers about.
  const leads = (carrier: string | undefined): carrier is string => carrier !== undefined && carrier !== filed
    && !refused.has(carrier) && AUTOMATIC_CARRIER_IDS.has(carrier) && (!directCarrier(carrier) ? unfiled
      : unfiled ? carrier !== routing.confirmed_carrier && !waiting(carrier) : !asked(carrier));
  const lead = (carrier: string, rank: 0 | 1 | 2 = asked(carrier) ? 2 : 1): RerouteLead =>
    ({ carrier, rank: refusedBefore(carrier) ? 0 : rank });
  const taken = Object.keys(routing.set_aside ?? {}).find((carrier) => fits(carrier) && carrier !== filed
    && !refused.has(carrier) && AUTOMATIC_CARRIER_IDS.has(carrier) && directCarrier(carrier));
  if (taken) return lead(taken, 0);
  const detected = detectCarrierMatch(number);
  if (detected.confidence === 'high' && leads(detected.carrier)) return lead(detected.carrier);
  // The names a provider gave, read with today's aliases and number rules.
  const names = Array.isArray(data.reported_carriers)
    ? data.reported_carriers.filter((name): name is string => typeof name === 'string') : [];
  const named = names.length ? universalCarrierHints(names, number).discovered_carrier : undefined;
  if (leads(named) && (named !== routing.discovered_carrier || refusedBefore(named))) return lead(named);
  if (unfiled) {
    // A carrier that recognizes the number under this release and did not under the one
    // that last routed it: the release taught it the number's shape.
    const taught = recognizers(number).find((carrier) => routing.recognizers?.includes(carrier) === false && !refused.has(carrier));
    if (taught) return lead(taught, 1);
    const [first] = recognitionCandidates(number, { hint: routing.discovered_carrier,
      skip: (carrier) => waiting(carrier) || refused.has(carrier) });
    if (first) return lead(first.carrier, 2);
  }
  // The filed carrier's own adapter, new since the provider took over, or one only
  // recognition asked before the parcel was filed under it: such a probe drops an
  // answer on local clocks, which the filed carrier's own lookup keeps. Or the filed
  // carrier answered on local clocks, which the provider dated, and now dates its scans.
  if (!provider || unfiled || !directCarrier(filed) || refused.has(filed)) return null;
  const localHistory = isRecord(data.direct_local_history) && data.direct_local_history.carrier === filed;
  if (localHistory && !localClockCarrier(filed)) return lead(filed, 0);
  // A release can also date such an answer with the catalog's flag unchanged (Mayi Yinfu's
  // came to date the scans whose place settles their zone): asked again once the parcel
  // has gone RELEASE_REASK_MS without a check.
  if (localHistory && !checkedRecently && !waiting(filed)) return lead(filed, 2);
  return filed !== routing.confirmed_carrier && (!routing.failures[filed] || refusedBefore(filed)) ? lead(filed, 1) : null;
}

/**
 * The carrier rerouteCarrier points the parcel to, with its rank, passing over those
 * whose adapter refuses the number by its own check, which sends nothing: the queued
 * check could not ask them. A later release, whose adapters may take the number, looks again.
 */
export async function rerouteAskable(parcel: JsonObject, rules = catalogRules(), now = Date.now(),
  refuses: (carrier: string, number: string) => Promise<boolean> = refusesNumber): Promise<RerouteLead | null> {
  const number = String(parcel.tracking_number ?? '');
  const refused = new Set<string>();
  // Each refusal leaves that carrier out of the next pick, so the catalog bounds the loop.
  for (let lead = rerouteLead(parcel, rules, now, refused); lead; lead = rerouteLead(parcel, rules, now, refused)) {
    if (!await refuses(lead.carrier, number)) return lead;
    refused.add(lead.carrier);
  }
  return null;
}

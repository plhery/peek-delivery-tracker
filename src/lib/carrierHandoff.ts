import { LANDING_PATH } from './experience';
import { languagePath, type Locale } from './locale';

/**
 * The text typed into a carrier's page's box, on its way to the tracker. A
 * tracking number never goes into an address, so the box notes it for this
 * tab and opens the landing, which takes the note once and looks it up, with
 * the page's carrier as a hint: the landing asks that carrier first. A
 * parcel's page notes its number this way too, to `pick` its carrier: the
 * landing then opens its carrier picker instead of looking the number up, and
 * the carrier chosen goes to the page's own `link` rather than to a new one.
 */
export const CARRIER_HANDOFF_STORAGE_KEY = 'sdt.peek.carrierHandoff.v1'; // gitleaks:allow -- sessionStorage name, not a credential
// A note this old was left by a landing that never opened: it is not looked up on a later visit.
const MAX_AGE_MS = 10 * 60_000;
// The most the landing's field is handed, as from the share sheet.
const MAX_LENGTH = 10_000;
// A carrier id as the catalog writes it; the landing and the server check it against the catalog.
const CARRIER_ID = /^[a-z0-9][a-z0-9-]{0,62}$/;

/**
 * The landing a page's box opens: the one in the page's language, at the landing's own address, where
 * someone signed in sees it too (at `/` they see their deliveries).
 */
export function carrierHandoffLanding(locale: Locale): string {
  return locale === 'en' ? LANDING_PATH : languagePath(locale);
}

export interface CarrierHandoff {
  text: string;
  pick: boolean;
  /** The lookup the carrier is picked for, whose owner key this device holds. */
  link?: string;
  /** The carrier whose page the text was typed on, never with `pick`. */
  carrier?: string;
}

/** Notes the typed text for the landing. Without session storage the landing opens with its field empty. */
export function writeCarrierHandoff(text: string, now = Date.now(), { pick = false, link, carrier }: { pick?: boolean; link?: string; carrier?: string } = {}): void {
  try {
    window.sessionStorage.setItem(CARRIER_HANDOFF_STORAGE_KEY, JSON.stringify({
      text: text.trim().slice(0, MAX_LENGTH), at: now, ...(pick ? { pick, ...(link ? { link } : {}) } : carrier ? { carrier } : {}),
    }));
  } catch { /* The landing opens anyway, and the number can be typed again. */ }
}

/** The text a carrier's page handed over a moment ago, once: the note is dropped whether it is used or not. */
export function takeCarrierHandoff(now = Date.now()): CarrierHandoff | null {
  try {
    const written = window.sessionStorage.getItem(CARRIER_HANDOFF_STORAGE_KEY);
    if (written === null) return null;
    window.sessionStorage.removeItem(CARRIER_HANDOFF_STORAGE_KEY);
    const note = JSON.parse(written) as { text?: unknown; at?: unknown; pick?: unknown; link?: unknown; carrier?: unknown } | null;
    const age = now - Number(note?.at);
    if (typeof note?.text !== 'string' || !note.text.trim() || !(age >= 0 && age < MAX_AGE_MS)) return null;
    const pick = note.pick === true;
    return {
      text: note.text.trim().slice(0, MAX_LENGTH), pick, ...(pick && typeof note.link === 'string' ? { link: note.link } : {}),
      ...(!pick && typeof note.carrier === 'string' && CARRIER_ID.test(note.carrier) ? { carrier: note.carrier } : {}),
    };
  } catch {
    return null;
  }
}

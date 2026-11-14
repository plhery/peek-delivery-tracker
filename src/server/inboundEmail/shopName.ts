import 'server-only';

/**
 * A carrier's email names the shop it carries for, in its subject ("Colis de Example Shop livré", "Paket von
 * Example Shop ist unterwegs", "Your Example Shop shipment is on its way") or beside the number ("Sendungsnummer
 * 1234567890 von Example Shop wurde zugestellt", "Absender: Example Shop"). That name is the parcel's, where the
 * carrier's own mailbox name ("noreply", "Info") says nothing.
 */

/** How much of a line can be a name. */
const MOST_NAME_LENGTH = 80;
const MOST_NAME_WORDS = 6;
/** How much of an email's text is searched for the shop: the line that names it is near the top. */
const MOST_TEXT_LENGTH = 6_000;

const PARCEL_WORDS = String.raw`colis|paquet|envoi|commande|livraison|paket|p[aä]ckchen|sendung|lieferung|zustellung|bestellung`
  + String.raw`|parcel|package|shipment|delivery|order|pacco|spedizione|ordine|paquete|env[ií]o|pedido|encomenda|pakket|zending|bestelling`
  + String.raw`|przesy[lł]ka|paczka`;
const FROM_WORDS = String.raw`de\s+la\s+part\s+de|de\s+chez|de|von|vom\s+auftraggeber|vom|from|da|di|del|van|od`;
const SENDER_LABELS = String.raw`absender|versender|auftraggeber|exp[eé]diteur|sender|shipper|mittente|remitente|remetente|afzender|nadawca`;
const YOUR_WORDS = String.raw`your|ihre?|votre|il\s+tuo|la\s+tua|tu|uw|deine?`;
/** "Colis de X", "Paket von X", "Zustellung von X". */
const PARCEL_FROM = new RegExp(String.raw`(?<![\p{L}])(?:${PARCEL_WORDS})\s+(?:${FROM_WORDS})\s+(?!\s)(.{1,${MOST_NAME_LENGTH}})`, 'giu');
/** "Your X shipment", "Ihre X-Sendung". */
const YOUR_PARCEL = new RegExp(String.raw`(?<![\p{L}])(?:${YOUR_WORDS})\s+(?!\s)(.{1,${MOST_NAME_LENGTH}}?)[\s-]+(?:${PARCEL_WORDS})(?![\p{L}])`, 'giu');
/** "1234567890 von X wurde zugestellt": the shop right after the parcel's number. */
const NUMBER_FROM = new RegExp(String.raw`\d{6,}\s+(?:${FROM_WORDS})\s+(?!\s)(.{1,${MOST_NAME_LENGTH}})`, 'giu');
/** "Absender: X", "vom Auftraggeber X". */
const SENDER_LABEL = new RegExp(String.raw`(?<![\p{L}])(?:${SENDER_LABELS})\s*:?\s+(?!\s)(.{1,${MOST_NAME_LENGTH}})`, 'giu');
const A_PARCEL_WORD = new RegExp(`^(?:${PARCEL_WORDS})$`, 'iu');
/** How many places of a text are tried for one wording: the first that names somebody is taken. */
const MOST_TRIES = 12;

/** Where a name ends on its line: at punctuation, a dash set apart, or a sentence's end. */
const NAME_END = /[(:,;!?|•·<>]|\s[-–—]\s|\.(?=\s|$)/;
/** Small words a name holds between its capitalised ones: "Galeries de Paris", "Marks and Spencer". */
const CONNECTORS = new Set(['de', 'la', 'le', 'du', 'des', 'von', 'der', 'und', 'and', 'et', 'y', 'e', 'di', 'del', 'the', 'of', '&', '+']);
/** Words that follow a name, whatever their capitals: a subject in title case writes "Is On Its Way". */
const AFTER_NAME = new Set([
  'is', 'was', 'has', 'will', 'are', 'on', 'out', 'arrives', 'arriving', 'delivered', 'shipped',
  'ist', 'wird', 'wurde', 'wurden', 'hat', 'kommt', 'geht', 'durch', 'unterwegs', 'zugestellt', 'verzögert',
  'est', 'sera', 'a', 'arrive', 'livré', 'livrée', 'en', 'è', 'sarà', 'in', 'está', 'será', 'ha', 'foi', 'wordt', 'komt',
]);
/** A first word that names nobody: "Paket von Ihrem Händler", "Colis de votre commande", "von Montag bis Freitag". */
const NOT_A_NAME = new RegExp('^(?:ihr\\p{L}*|dein\\p{L}*|unser\\p{L}*|votre|vos|notre|nos|your|our|einem|einer|un|une|uno|una|an?|uns|ihnen|dir|vous|nous|you|us'
  + '|montag|dienstag|mittwoch|donnerstag|freitag|samstag|sonntag|lundi|mardi|mercredi|jeudi|vendredi|samedi|dimanche'
  + '|monday|tuesday|wednesday|thursday|friday|saturday|sunday|heute|morgen|today|tomorrow)$', 'iu');
/** A company's legal form, which nobody says: "Example Shop AG", "Example S.L.", "Example GmbH & Co. KG". */
const LEGAL_FORMS = new Set([
  'ag', 'gmbh', 'mbh', 'kg', 'ohg', 'ug', 'se', 'ek', 'sa', 'sarl', 'sàrl', 'sas', 'sasu', 'eurl', 'srl', 'spa', 'sl', 'slu',
  'ltd', 'limited', 'llc', 'inc', 'corp', 'co', 'plc', 'bv', 'nv', 'oy', 'ab', 'aps', 'as', 'sro', 'kft', 'doo', '&', '+', 'and', 'und', 'et',
]);

const capital = (word: string) => /^[\p{Lu}\p{N}]/u.test(word);
/** A name starts with a capital letter: a number there is an hour, a count or a price. */
const startsName = (word: string) => /^\p{Lu}/u.test(word) && !NOT_A_NAME.test(word) && !A_PARCEL_WORD.test(word);

/** The name at the start of what follows "from": its capitalised words, and the small words between them. */
function nameAtStart(rest: string): string {
  const words = rest.split(NAME_END, 1)[0]!.trim().split(/\s+/).filter(Boolean);
  if (!words.length || !startsName(words[0]!)) return '';
  const name: string[] = [];
  for (const [index, word] of words.entries()) {
    if (name.length >= MOST_NAME_WORDS) break;
    const lower = word.toLowerCase();
    if (index > 0 && AFTER_NAME.has(lower)) break;
    if (capital(word)) name.push(word);
    else if (CONNECTORS.has(lower) && words[index + 1] && capital(words[index + 1]!) && !AFTER_NAME.has(words[index + 1]!.toLowerCase())) name.push(word);
    else break;
  }
  return name.join(' ');
}

/** Words written in capitals as a name is said: "EXAMPLE SHOP" is "Example Shop", while "IKEA" and "H&M" stay. */
function saidAloud(name: string): string {
  const letters = name.replace(/[^\p{L}]/gu, '');
  if (letters.length <= 4 || letters !== letters.toUpperCase() || letters === letters.toLowerCase()) return name;
  return name.split(' ').map((word, index) => {
    const lower = word.toLowerCase();
    if (index > 0 && CONNECTORS.has(lower)) return lower;
    // A short word in capitals that is no connector is most likely initials: "ABC Store".
    if (word.length <= 3 && !CONNECTORS.has(lower)) return word;
    return word.replace(/\p{L}+/gu, (part) => part[0]! + part.slice(1).toLowerCase());
  }).join(' ');
}

/** The name without its legal form, in the capitals it is said with. */
function tidy(name: string): string {
  const words = name.replace(/\s+sp\.?\s*z\s*o\.?\s*o\.?$/i, '').split(' ').filter(Boolean);
  while (words.length > 1 && LEGAL_FORMS.has(words.at(-1)!.replace(/\./g, '').toLowerCase())) words.pop();
  return saidAloud(words.join(' ')).replace(/[.\s]+$/, '');
}

/**
 * The first place the wording names somebody. `whole` is for a name that stands between two words of the wording
 * ("Your X shipment"): every word between them must then be the name's.
 */
function named(pattern: RegExp, text: string, whole = false): string {
  let tries = 0;
  for (const found of text.matchAll(pattern)) {
    if ((tries += 1) > MOST_TRIES) break;
    const name = nameAtStart(found[1]!);
    if (!name || !/\p{L}/u.test(name) || whole && name !== found[1]!.trim().replace(/\s+/g, ' ')) continue;
    return tidy(name);
  }
  return '';
}

/**
 * The shop a carrier's email says it carries for, or empty. `carrierNames` are the carrier's own names, which its
 * emails also write where a shop's would stand ("Your DHL Express shipment").
 */
export function shopNamedIn(subject: string, text: string, carrierNames: readonly string[] = []): string {
  const title = subject.slice(0, 1_000);
  const body = text.slice(0, MOST_TEXT_LENGTH);
  const own = carrierNames.map((name) => name.toLowerCase()).filter(Boolean);
  const candidates = [
    () => named(PARCEL_FROM, title),
    () => named(YOUR_PARCEL, title, true),
    () => named(NUMBER_FROM, body),
    () => named(SENDER_LABEL, body),
  ];
  for (const candidate of candidates) {
    const name = candidate();
    if (name && !own.some((carrier) => name.toLowerCase().startsWith(carrier))) return name;
  }
  return '';
}

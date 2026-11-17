/**
 * Whether two scans are worded in two languages, as far as their words show it: a relay copy in another language is
 * the scan it tells, not a scan of its own (eventPlaces.ts). Letters of a script other than Latin name the script;
 * Latin words name a language only by words no other listed language uses, and a row with such words of two
 * languages, or none, names none. Unsure counts as one language: a copy is folded only when the rows plainly differ.
 */

/** Scripts other than Latin, each a language (or a few) a reader tells from any written in Latin letters. */
const SCRIPTS: readonly [string, RegExp][] = [
  ['ja', /[\p{Script=Hiragana}\p{Script=Katakana}]/u],
  ['zh', /\p{Script=Han}/u],
  ['ko', /\p{Script=Hangul}/u],
  ['cyrillic', /\p{Script=Cyrillic}/u],
  ['el', /\p{Script=Greek}/u],
  ['hy', /\p{Script=Armenian}/u],
  ['ka', /\p{Script=Georgian}/u],
  ['ar', /\p{Script=Arabic}/u],
  ['he', /\p{Script=Hebrew}/u],
  ['th', /\p{Script=Thai}/u],
];

/**
 * Words of scans that one listed language alone uses. Words two of them share ("la", "del", "centro", "paket", "in")
 * and loanwords ("hub", "transit") are left out.
 */
const WORDS: Readonly<Record<string, readonly string[]>> = {
  en: ['the', 'and', 'has', 'have', 'been', 'your', 'was', 'were', 'with', 'from', 'for', 'onto', 'into', 'our', 'will',
    'are', 'of', 'at', 'by', 'out', 'item', 'parcel', 'package', 'shipment', 'delivered', 'arrived', 'arrival', 'departed',
    'departure', 'received', 'processed', 'processing', 'sorting', 'facility', 'center', 'scanned', 'loaded', 'vehicle',
    'sorted', 'left', 'picked', 'ready', 'collection', 'attempted', 'recipient', 'sender', 'customs', 'delivery',
    'country', 'origin', 'warehouse', 'office', 'shipped', 'accepted', 'handed', 'carrier', 'label', 'created',
    'information', 'driver', 'way', 'order', 'airport'],
  fr: ['les', 'du', 'au', 'aux', 'est', 'été', 'votre', 'colis', 'pour', 'sur', 'dans', 'livré', 'livrée', 'livraison',
    'acheminement', 'cours', 'tri', 'trié', 'arrivé', 'arrivée', 'pris', 'expédié', 'remis', 'destinataire', 'envoi',
    'bureau', 'plateforme', 'dépôt', 'avis'],
  it: ['della', 'dello', 'delle', 'degli', 'alla', 'presso', 'pacco', 'spedizione', 'consegna', 'consegnato',
    'consegnata', 'smistamento', 'arrivato', 'arrivata', 'partito', 'partita', 'carico', 'presa', 'preso', 'ritiro', 'è',
    'stato', 'stata', 'nel', 'nella', 'sul', 'dal', 'dalla', 'registrato', 'lavorazione', 'uscito', 'transito'],
  es: ['el', 'los', 'las', 'envío', 'paquete', 'entregado', 'recibido', 'admitido', 'tránsito', 'oficina',
    'clasificación', 'salida', 'llegada', 'sido', 'reparto', 'origen', 'admisión', 'nuestro', 'nuestra'],
  pt: ['os', 'em', 'encomenda', 'foi', 'entregue', 'postado', 'saiu', 'recebido', 'unidade', 'tratamento', 'trânsito',
    'distribuição', 'destinatário', 'ao', 'aos', 'não', 'seu', 'pelo', 'pela'],
  de: ['der', 'die', 'das', 'ist', 'wurde', 'ihr', 'ihre', 'sendung', 'zum', 'zur', 'im', 'und', 'von', 'auf', 'wird',
    'zugestellt', 'zustellung', 'abgeholt', 'eingetroffen', 'verladen', 'bearbeitet', 'empfänger', 'abholung',
    'paketzentrum', 'ausgeliefert', 'mit', 'bei', 'dem'],
  nl: ['het', 'een', 'van', 'naar', 'uw', 'pakket', 'bezorgd', 'ontvangen', 'wordt', 'sorteercentrum', 'bezorger',
    'onderweg', 'zending', 'afgeleverd', 'opgehaald', 'aangemeld', 'op', 'bij', 'voor'],
  pl: ['przesyłka', 'przesyłki', 'paczka', 'nadana', 'nadanie', 'doręczona', 'doręczenie', 'odebrana', 'wydana',
    'placówka', 'placówki', 'sortowni', 'sortowania', 'oddział', 'dostarczona', 'został', 'została', 'przyjęta',
    'przyjęcie', 'jest', 'odbioru'],
  hu: ['küldemény', 'küldeményt', 'csomag', 'csomagot', 'kézbesítve', 'kézbesítés', 'címzett', 'feladva', 'átvette',
    'nemzetközi', 'beérkezett', 'és', 'az', 'egy'],
};

/** Letters one listed language alone writes, which show it in a word or two ("Doręczono", "Kézbesítő"). */
const LETTERS: Readonly<Record<string, string>> = { pl: 'łńśźżąęć', hu: 'őű', pt: 'ãõ', es: 'ñ', de: 'ß', fr: 'œ', it: 'ìò' };

const LANGUAGE_OF = new Map(Object.entries(WORDS).flatMap(([language, words]) => words.map((word) => [word, language] as const)));
const LETTER_OF = new Map(Object.entries(LETTERS).flatMap(([language, letters]) => [...letters].map((letter) => [letter, language] as const)));

/** The script a text's letters are in: Latin, another one, or null for no letters or several other scripts. */
function scriptOf(text: string): string | null {
  const scripts = SCRIPTS.filter(([, letters]) => letters.test(text)).map(([script]) => script);
  // Japanese writes Han letters too. A Latin place name in a row of another script leaves the row in that script.
  if (scripts.length === 1 || (scripts.length === 2 && scripts[0] === 'ja' && scripts[1] === 'zh')) return scripts[0]!;
  if (scripts.length) return null;
  return /\p{Script=Latin}/u.test(text) ? 'latin' : null;
}

/** The one language a text in Latin letters shows by its words and letters, or null. */
function latinLanguage(text: string): string | null {
  const lower = text.toLocaleLowerCase('en-US');
  const languages = new Set([...lower.split(/[^\p{L}\p{N}]+/u).map((word) => LANGUAGE_OF.get(word)),
    ...[...lower].map((letter) => LETTER_OF.get(letter))].filter((language): language is string => language !== undefined));
  return languages.size === 1 ? [...languages][0]! : null;
}

/** Whether two texts are plainly in two languages: in two scripts, or in Latin letters with words of two languages. */
export function otherLanguages(text: unknown, other: unknown): boolean {
  const words = String(text ?? '').normalize('NFC');
  const otherWords = String(other ?? '').normalize('NFC');
  const script = scriptOf(words);
  const otherScript = scriptOf(otherWords);
  if (script === null || otherScript === null) return false;
  if (script !== otherScript) return true;
  if (script !== 'latin') return false;
  const language = latinLanguage(words);
  const otherLanguage = latinLanguage(otherWords);
  return language !== null && otherLanguage !== null && language !== otherLanguage;
}

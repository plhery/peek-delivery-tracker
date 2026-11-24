import 'server-only';

import type { Locale } from '../../lib/locale';

/**
 * Whether an email tells its reader of a parcel they sent: a marketplace's or a parcel shop's notice to a seller
 * ("on its way to the buyer", "thanks for dropping off your parcel", "you sold an item"). Peek still follows such a
 * parcel, and its name says the reader sent it (`sentName`).
 *
 * Only words that speak to the sender count, in the subject and the email's opening, before the footers that explain
 * a marketplace's rules to everyone. A buyer's email speaks of the seller, of "buyer protection" ("Käuferschutz",
 * "Ochrona Kupującego") or of a parcel "dropped off" at a pickup point for them, and none of that counts. Accents and
 * case count for nothing.
 */
const PHRASES: readonly string[] = [
  // en
  String.raw`to\s+(?:the|your)\s+buyer|(?:for\s+)?your\s+buyer`,
  String.raw`thanks?\s+(?:you\s+)?for\s+dropping\s+(?:it\s+)?off`,
  String.raw`you(?:'ve|\s+have)?\s+(?:just\s+)?(?:dropped\s+(?:it\s+)?off|sent|shipped|sold)`,
  String.raw`your\s+outgoing\s+(?:parcel|package|shipment)`,
  // fr
  String.raw`(?:a|vers|chez)\s+(?:l'|ton\s+|votre\s+)acheteu(?:r|se)`,
  String.raw`(?:ton|votre)\s+acheteu(?:r|se)`,
  String.raw`(?:tu\s+as|vous\s+avez)\s+(?:bien\s+)?(?:envoye|expedie|depose|vendu)`,
  String.raw`d'avoir\s+(?:depose|envoye|expedie)`,
  // de
  String.raw`(?:an\s+(?:den|deinen|ihren)|zum|zu\s+(?:deinem|ihrem))\s+kaufer`,
  String.raw`(?:an\s+(?:die|deine|ihre)|zur|zu\s+(?:deiner|ihrer))\s+kauferin`,
  String.raw`(?:dein(?:e|em|en)?|ihr(?:e|em|en)?)\s+kaufer(?:in)?`,
  String.raw`(?:du\s+hast|sie\s+haben)\s+(?:\S+\s+){0,6}?(?:abgegeben|verschickt|versendet|versandt|eingeliefert|verkauft)`,
  String.raw`danke\s+fur\s+(?:die|deine|ihre)\s+(?:abgabe|einlieferung)`,
  // es
  String.raw`(?:enviad[oa]|entregad[oa]|camino)\s+(?:al|a\s+tu|a\s+su|hacia\s+(?:el|tu))\s+comprador(?:a)?`,
  String.raw`tu\s+comprador(?:a)?`,
  String.raw`has\s+(?:enviado|depositado|vendido)`,
  // it
  String.raw`(?:all'\s*|al\s+(?:tuo|suo)\s+|verso\s+l'\s*)acquirente`,
  String.raw`(?:il\s+)?tuo\s+acquirente`,
  String.raw`hai\s+(?:spedito|inviato|venduto)`,
  // pt
  String.raw`(?:enviad[oa]|entregue|caminho)\s+(?:ao|para\s+o|do)\s+comprador(?:a)?`,
  String.raw`(?:teu|seu)\s+comprador(?:a)?`,
  String.raw`enviaste|vendeste|voce\s+(?:enviou|vendeu)`,
  // pl
  String.raw`do\s+kupujac(?:ego|ej)`,
  String.raw`twoje(?:go|mu)?\s+kupujac(?:ego|ej|emu)?|twoj\s+kupujacy`,
  String.raw`(?:wysłał|nadał|sprzedał)(?:es|as)`,
  // ru
  String.raw`покупателю`,
  String.raw`ваш\s+покупатель`,
  String.raw`вы\s+(?:отправили|продали)`,
  // nl
  String.raw`(?:naar|aan)\s+(?:de|je|uw)\s+koper`,
  String.raw`(?:je|uw)\s+koper`,
  String.raw`je\s+hebt\s+(?:\S+\s+){0,4}?(?:verzonden|verstuurd|verkocht|afgegeven)`,
];
/** Chinese has no spaces: its phrases count wherever they stand. */
const UNSPACED: readonly string[] = ['给买家', '你的买家', '您的买家', '你已寄出', '您已寄出', '你寄出的', '您寄出的'];

/** How much of the email's text is read: its opening, before the footers. */
const OPENING = 2_000;

const fold = (text: string) => text.normalize('NFD').replace(/\p{M}/gu, '').replace(/[’ʼ`´]/g, "'");
const SENT_BY_READER = new RegExp(
  `(?<![\\p{L}\\p{N}])(?:${PHRASES.map(fold).join('|')})(?![\\p{L}\\p{N}])|${UNSPACED.join('|')}`,
  'iu',
);

export function sentByReader(subject: string, text: string): boolean {
  return SENT_BY_READER.test(fold(`${subject}\n${text.slice(0, OPENING)}`));
}

/** What a name says before the usual one, in the reader's language, for a parcel they sent. */
const SENT_NAME: Record<Locale, string> = {
  en: '(you sent) {{name}}',
  de: '(von dir verschickt) {{name}}',
  fr: '(envoyé par toi) {{name}}',
  it: '(spedito da te) {{name}}',
  es: '(enviado por ti) {{name}}',
  pt: '(enviada por ti) {{name}}',
  'pt-br': '(enviada por você) {{name}}',
  pl: '(wysłana przez ciebie) {{name}}',
  ru: '(вы отправили) {{name}}',
  zh: '（你寄出的）{{name}}',
};

/**
 * The name of a parcel the reader sent: the usual name after a few words that say so, in the language the account
 * last used. At most 80 characters, as every parcel's name, the usual name cut short first.
 */
export function sentName(locale: Locale, name: string): string {
  const [before, after] = SENT_NAME[locale].split('{{name}}') as [string, string];
  const room = 80 - [...before].length - [...after].length;
  const kept = [...name.trim()].slice(0, Math.max(0, room)).join('').trim();
  return `${before}${kept}${after}`.trim();
}

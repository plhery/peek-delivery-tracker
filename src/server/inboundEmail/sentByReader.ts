import 'server-only';

import type { Locale } from '../../lib/locale';

/**
 * Whether an email tells its reader of a parcel they sent: a marketplace's or a parcel shop's notice to a seller
 * ("on its way to the buyer", "thanks for dropping off your parcel", "you sold an item"), or a shop's or a carrier's
 * notice of a return the reader sends back ("your return has been received", "here's your return label"). Peek still
 * follows such a parcel, and its name says the reader sent it (`sentName`).
 *
 * Only words that speak to the sender count, in the subject and the email's opening, before the footers that explain
 * a marketplace's rules to everyone. A buyer's email speaks of the seller, of "buyer protection" ("Käuferschutz",
 * "Ochrona Kupującego") or of a parcel "dropped off" at a pickup point for them, and none of that counts. Nor do its
 * words about returns: "free returns", "use the enclosed label for your return", a parcel going back to its sender
 * when it could not be delivered ("retour afzender", "zwrot do nadawcy"), a refund ("zwrot pieniędzy", "возврат
 * денег"), or French "votre retour", which is also feedback. A return counts only once the email says what became of
 * it. Accents and case count for nothing.
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
/** Not "once your return is received", which a shop's rules say before any return. */
const NOT_YET = String.raw`(?<!(?:once|when|after|until|if|as)\s+)`;
/** A return the reader sends back, once the email says what became of it: registered, dropped off, on its way, received. */
const RETURNS: readonly string[] = [
  // en
  String.raw`${NOT_YET}your\s+return(?:\s+(?:parcel|package|shipment|item|items|order|request))?\s+(?:(?:has\s+been|was|is)\s+(?:received|registered|dropped\s+off|accepted|approved|processed|delivered|scanned|refunded|confirmed|created|booked|picked\s+up|collected|on\s+its\s+way|in\s+transit|being\s+(?:processed|checked|inspected))|has\s+(?:arrived|reached))`,
  String.raw`${NOT_YET}we(?:'ve|\s+have)?\s+received\s+your\s+return`,
  String.raw`thanks?\s+(?:you\s+)?for\s+(?:your|the)\s+return`,
  String.raw`here(?:'s|\s+is)\s+your\s+return\s+label|your\s+return\s+label\s+is\s+ready`,
  String.raw`return\s+(?:received|registered|confirmed|dropped\s+off|approved)`,
  // fr
  String.raw`(?:ton|votre)\s+(?:colis\s+(?:de\s+)?)?retour\s+(?:a\s+(?:bien\s+)?ete\s+|est\s+(?:bien\s+)?)(?:enregistre|recu|depose|valide|accepte|confirme|arrive|pris\s+en\s+charge|rembourse|en\s+(?:route|chemin|cours\s+d'acheminement))`,
  String.raw`nous\s+avons\s+(?:bien\s+)?recu\s+(?:ton|votre)\s+(?:colis\s+(?:de\s+)?)?retour`,
  String.raw`(?:ta|votre)\s+demande\s+de\s+retour\s+(?:a\s+(?:bien\s+)?ete|est)\s+(?:enregistree|acceptee|validee|confirmee|approuvee)`,
  String.raw`voici\s+(?:ton|votre)\s+etiquette\s+(?:de\s+)?retour`,
  String.raw`retour\s+(?:enregistre|confirme|valide|accepte)`,
  // de
  String.raw`(?:deine|ihre)\s+(?:retoure|rucksendung)\s+(?:ist|wurde)\s+(?:\S+\s+){0,4}?(?:eingegangen|angekommen|erhalten|registriert|angemeldet|bestatigt|abgegeben|unterwegs|auf\s+dem\s+weg|bearbeitet|gepruft)`,
  String.raw`wir\s+haben\s+(?:deine|ihre)\s+(?:retoure|rucksendung)\s+(?:\S+\s+){0,2}?erhalten`,
  String.raw`danke\s+fur\s+(?:deine|ihre)\s+(?:retoure|rucksendung)`,
  String.raw`hier\s+ist\s+(?:dein|ihr)\s+(?:retouren|rucksende)(?:etikett|label|schein)`,
  String.raw`(?:retoure|rucksendung)\s+(?:erhalten|eingegangen|angemeldet|registriert|bestatigt)`,
  // es
  String.raw`(?:tu|su)\s+devolucion\s+(?:ha\s+sido|se\s+ha|fue|esta\s+(?:en\s+camino|registrada|confirmada|aceptada|aprobada))`,
  String.raw`(?:hemos\s+recibido|gracias\s+por)\s+(?:tu|su)\s+devolucion`,
  String.raw`aqui\s+tienes\s+(?:tu|la)\s+etiqueta\s+de\s+devolucion`,
  String.raw`devolucion\s+(?:registrada|recibida|confirmada|aceptada|aprobada)`,
  // it
  String.raw`(?:il\s+)?tuo\s+reso\s+(?:e\s+stato|e\s+in\s+viaggio|e\s+arrivato)`,
  String.raw`(?:abbiamo\s+ricevuto|grazie\s+per)\s+il\s+tuo\s+reso`,
  String.raw`ecco\s+la\s+tua\s+etichetta\s+di\s+reso`,
  String.raw`reso\s+(?:registrato|ricevuto|confermato|accettato|approvato)`,
  // pt
  String.raw`(?:tua|sua)\s+devolucao\s+(?:foi|esta\s+(?:a\s+caminho|em\s+transito|registada|registrada|confirmada|aprovada|aceite|aceita))`,
  String.raw`(?:recebemos|obrigad[oa]\s+pela)\s+(?:a\s+)?(?:tua|sua)\s+devolucao`,
  String.raw`devolucao\s+(?:registada|registrada|recebida|confirmada|aprovada|aceite|aceita)`,
  // pl
  String.raw`twoj\s+zwrot\s+(?:zosta[łl]|jest\s+w\s+drodze|dotar[łl])`,
  String.raw`(?:otrzymalismy|dziekujemy\s+za)\s+(?:twoj\s+)?zwrot(?!\s+(?:pieniedzy|srodkow|kosztow|platnosci))`,
  String.raw`paczka\s+zwrotna\s+(?:zosta[łl]a|jest|dotar[łl]a)`,
  String.raw`oto\s+(?:twoja\s+)?etykieta\s+zwrotna`,
  String.raw`zwrot\s+(?:zosta[łl]\s+)?(?:przyjety|zarejestrowany|potwierdzony|nadany|odebrany)`,
  // ru
  String.raw`ваш\s+возврат\s+(?:оформлен|принят|зарегистрирован|получен|в\s+пути|доставлен)`,
  String.raw`(?:мы\s+получили|спасибо\s+за)\s+(?:ваш\s+)?возврат(?!\s+(?:денег|средств))`,
  String.raw`возврат\s+(?:оформлен|принят|зарегистрирован|получен)`,
  // nl
  String.raw`(?:je|uw)\s+(?:retour|retourzending|retourpakket)\s+(?:is|werd|wordt)\s+(?:\S+\s+){0,3}?(?:ontvangen|aangemeld|geregistreerd|bevestigd|onderweg|afgegeven|verwerkt|goedgekeurd)`,
  String.raw`wij?\s+hebben\s+(?:je|uw)\s+(?:retour|retourzending|retourpakket)\s+(?:\S+\s+){0,2}?ontvangen`,
  String.raw`bedankt\s+voor\s+(?:je|uw)\s+(?:retour|retourzending)`,
  String.raw`(?:retour|retourzending)\s+(?:aangemeld|ontvangen|geregistreerd|bevestigd)`,
];
/** Chinese has no spaces: its phrases count wherever they stand. */
const UNSPACED: readonly string[] = [
  '给买家', '你的买家', '您的买家', '你已寄出', '您已寄出', '你寄出的', '您寄出的',
  '您的退货已', '你的退货已', '已收到您的退货', '已收到你的退货', '退货已寄出', '退货包裹已', '感谢您的退货',
];

/** How much of the email's text is read: its opening, before the footers. */
const OPENING = 2_000;

const fold = (text: string) => text.normalize('NFD').replace(/\p{M}/gu, '').replace(/[’ʼ`´]/g, "'");
const SENT_BY_READER = new RegExp(
  `(?<![\\p{L}\\p{N}])(?:${[...PHRASES, ...RETURNS].map(fold).join('|')})(?![\\p{L}\\p{N}])|${UNSPACED.join('|')}`,
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

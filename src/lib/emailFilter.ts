/**
 * The words a shipping email's subject uses, by the language it is written in: what a shop or a carrier writes when a
 * parcel leaves, travels and arrives. A mail filter made of them forwards those emails and little else.
 *
 * Gmail matches whole words: it ignores case and accents and takes a plural in "s", but no other ending, so each
 * form a subject uses is listed. Words that mostly name something else are left out, such as "order" and the words
 * for "sent" that a bank or a form also writes.
 */
export const SHIPPING_SUBJECT_WORDS = {
  en: ['shipped', 'dispatched', 'shipment', 'shipping', 'parcel', 'package', 'delivery', 'delivered', 'tracking', 'on its way', 'on the way'],
  de: ['versandt', 'versendet', 'verschickt', 'Versand', 'Versandbestätigung', 'Sendung', 'Sendungsverfolgung', 'Paket', 'Lieferung', 'geliefert', 'zugestellt', 'Zustellung', 'unterwegs'],
  fr: ['expédié', 'expédiée', 'expédition', 'colis', 'livraison', 'livré', 'livrée', 'suivi', 'acheminement', 'distribution', 'en route', 'en chemin', 'en transit'],
  it: ['spedito', 'spedita', 'spedizione', 'pacco', 'consegna', 'consegnato', 'consegnata', 'tracciamento'],
  es: ['enviado', 'envío', 'paquete', 'entrega', 'entregado', 'seguimiento', 'en camino', 'en reparto'],
  pt: ['enviado', 'enviada', 'envio', 'expedida', 'encomenda', 'pacote', 'entrega', 'entregue', 'rastreio', 'rastreamento', 'a caminho'],
  nl: ['verzonden', 'verzending', 'zending', 'pakket', 'pakketje', 'bezorging', 'bezorgd', 'levering', 'geleverd', 'onderweg'],
  pl: ['wysłane', 'wysłana', 'wysłano', 'wysyłka', 'przesyłka', 'przesyłki', 'przesyłkę', 'paczka', 'paczki', 'paczkę', 'dostawa', 'dostawy', 'doręczona', 'doręczono', 'w drodze'],
  ru: ['отправлен', 'отправлена', 'отправлено', 'отправление', 'посылка', 'посылки', 'посылку', 'доставка', 'доставки', 'доставлен', 'доставлена', 'в пути'],
  zh: ['发货', '包裹', '快递', '派送', '签收', '物流'],
} as const satisfies Record<string, readonly string[]>;

export type FilterLanguage = keyof typeof SHIPPING_SUBJECT_WORDS;

/** The languages a filter can take, in the order they are offered. */
export const FILTER_LANGUAGES = Object.keys(SHIPPING_SUBJECT_WORDS) as FilterLanguage[];

const isFilterLanguage = (value: string): value is FilterLanguage => Object.hasOwn(SHIPPING_SUBJECT_WORDS, value);

/**
 * The languages a reader's filter starts with: the one Peek shows, those the browser asks pages for, and English,
 * which shops and carriers abroad write in. A language tag counts by its language alone, `pt-BR` as `pt`.
 */
export function filterLanguagesFor(tags: readonly string[]): FilterLanguage[] {
  const chosen = new Set<FilterLanguage>();
  for (const tag of [...tags, 'en']) {
    const language = tag.toLowerCase().split('-')[0] ?? '';
    if (isFilterLanguage(language)) chosen.add(language);
  }
  return FILTER_LANGUAGES.filter((language) => chosen.has(language));
}

/**
 * The search Gmail's filter takes for emails written in these languages: one of their words in the subject. A word
 * two languages share is named once. Empty when no language is chosen.
 */
export function gmailFilter(languages: readonly FilterLanguage[]): string {
  const words = new Map<string, string>();
  for (const language of FILTER_LANGUAGES) {
    if (!languages.includes(language)) continue;
    for (const word of SHIPPING_SUBJECT_WORDS[language]) {
      const key = word.toLowerCase();
      if (!words.has(key)) words.set(key, word.includes(' ') ? `"${word}"` : word);
    }
  }
  return words.size ? `subject:(${[...words.values()].join(' OR ')})` : '';
}

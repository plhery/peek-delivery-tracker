import rules from '../../shared/email-filter.json';

/**
 * The words a shipping email's subject uses, by the language it is written in: what a shop or a carrier writes when a
 * parcel leaves and travels. A mail filter made of them forwards those emails and little else. The iPhone and Android
 * read the same file.
 *
 * Gmail matches whole words: it ignores case and accents and takes a plural in "s", but no other ending, so each
 * form a subject uses is listed. Words that mostly name something else are left out, such as "order" and the words
 * for "sent" that a bank or a form also writes, and so are those only a delivery confirmation writes ("delivered",
 * "livré", "zugestellt"): once a parcel is delivered, adding it is too late.
 */
export const SHIPPING_SUBJECT_WORDS = rules.words;

export type FilterLanguage = keyof typeof SHIPPING_SUBJECT_WORDS;

/**
 * The few words of each language that most shipping subjects hold, the most telling first. A mail service whose
 * rule takes its words one at a time, or one a rule, is given these rather than the whole list.
 */
export const MAIN_SUBJECT_WORDS: Record<FilterLanguage, readonly string[]> = rules.mainWords;

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

/**
 * Gmail's settings (Settings › See all settings), from which the reader opens Forwarding and POP/IMAP. A link straight
 * to that tab (`#settings/fwdandpop`), or a reload of it, can show the tab without its "Add a forwarding address" button.
 */
export const GMAIL_SETTINGS_LINK = 'https://mail.google.com/mail/#settings/general';

/**
 * The main words of these languages, for a rule that asks "subject includes" (MAIN_SUBJECT_WORDS). Each language's
 * first word comes before any second one, so that a reader who stops early has covered every language.
 */
export function subjectWords(languages: readonly FilterLanguage[]): string[] {
  const lists: (readonly string[])[] = FILTER_LANGUAGES.filter((language) => languages.includes(language)).map((language) => MAIN_SUBJECT_WORDS[language]);
  const words = new Map<string, string>();
  for (let rank = 0; lists.some((list) => rank < list.length); rank += 1) {
    for (const list of lists) {
      const word = list[rank];
      if (word && !words.has(word.toLowerCase())) words.set(word.toLowerCase(), word);
    }
  }
  return [...words.values()];
}

/** The mail services Settings has steps for. `other` is any service whose rules can forward. */
export const MAIL_SERVICES = ['gmail', 'outlook', 'icloud', 'yahoo', 'other'] as const;
export type MailService = (typeof MAIL_SERVICES)[number];

/** Where Outlook.com lists its rules, and iCloud's mail on the web. */
export const OUTLOOK_RULES_LINK = 'https://outlook.live.com/mail/0/options/mail/rules';
export const ICLOUD_MAIL_LINK = 'https://www.icloud.com/mail';

/** The services' domains by their name, whatever the country ending: "hotmail.fr", "yahoo.co.uk". */
const SERVICE_DOMAIN_NAMES: Record<string, MailService> = {
  gmail: 'gmail', googlemail: 'gmail',
  outlook: 'outlook', hotmail: 'outlook', live: 'outlook', msn: 'outlook',
  icloud: 'icloud',
  yahoo: 'yahoo', ymail: 'yahoo', rocketmail: 'yahoo',
};
const ICLOUD_DOMAINS = ['me.com', 'mac.com'];

/**
 * The mail service an address is most likely read in, by its domain. An address of another domain starts on Gmail,
 * which carries most of them.
 */
export function mailServiceOf(email: string | undefined): MailService {
  const domain = email?.trim().toLowerCase().split('@')[1] ?? '';
  if (ICLOUD_DOMAINS.includes(domain)) return 'icloud';
  const name = domain.split('.')[0] ?? '';
  return Object.hasOwn(SERVICE_DOMAIN_NAMES, name) ? SERVICE_DOMAIN_NAMES[name] ?? 'gmail' : 'gmail';
}

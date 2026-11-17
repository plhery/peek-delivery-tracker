// What the edge guard (worker.js) needs to know about Peek.

const LANGUAGES = ['en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'];

// The address languages of the carriers' pages that have pages alone (src/lib/locale.ts, PAGE_ONLY_LANGUAGES).
const PAGE_ONLY = 'nl|sv|da|nb|fi|cs|sk|hu|ro|bg|el|hr|sl|lt|lv|et|uk|sr';

const NOTE = {
  en: { note: 'You’re reading a saved copy of this page. Peek is down for a few minutes of maintenance, so tracking is paused.', back: 'Back around {{time}}.' },
  de: { note: 'Du liest eine gespeicherte Kopie dieser Seite. Peek ist für ein paar Minuten in Wartung, die Sendungsverfolgung pausiert.', back: 'Wieder da gegen {{time}}.' },
  fr: { note: 'Tu lis une copie enregistrée de cette page. Peek est en maintenance pour quelques minutes : le suivi est en pause.', back: 'De retour vers {{time}}.' },
  it: { note: 'Stai leggendo una copia salvata di questa pagina. Peek è in manutenzione per qualche minuto, il tracciamento è in pausa.', back: 'Di nuovo attivo verso le {{time}}.' },
  es: { note: 'Estás leyendo una copia guardada de esta página. Peek está en mantenimiento unos minutos y el seguimiento está en pausa.', back: 'Vuelve hacia las {{time}}.' },
  pt: { note: 'Estás a ler uma cópia guardada desta página. O Peek está em manutenção durante alguns minutos e o seguimento está em pausa.', back: 'Volta por volta das {{time}}.' },
  'pt-br': { note: 'Você está lendo uma cópia salva desta página. O Peek está em manutenção por alguns minutos e o rastreamento está pausado.', back: 'Volta por volta das {{time}}.' },
  pl: { note: 'Czytasz zapisaną kopię tej strony. Peek ma kilkuminutową przerwę techniczną, śledzenie jest wstrzymane.', back: 'Wracamy około {{time}}.' },
  ru: { note: 'Это сохранённая копия страницы. Peek на техобслуживании несколько минут, отслеживание приостановлено.', back: 'Вернёмся примерно в {{time}}.' },
  zh: { note: '你正在阅读此页面的保存副本。Peek 正在进行几分钟的维护，包裹跟踪暂停。', back: '预计 {{time}} 左右恢复。' },
};

const API = {
  en: 'Peek is down for a few minutes of maintenance. Try again shortly.',
  de: 'Peek ist für ein paar Minuten in Wartung. Versuche es gleich noch einmal.',
  fr: 'Peek est en maintenance pour quelques minutes. Réessaie dans un instant.',
  it: 'Peek è in manutenzione per qualche minuto. Riprova tra poco.',
  es: 'Peek está en mantenimiento unos minutos. Inténtalo de nuevo en un momento.',
  pt: 'O Peek está em manutenção durante alguns minutos. Tenta novamente daqui a pouco.',
  'pt-br': 'O Peek está em manutenção por alguns minutos. Tente de novo daqui a pouco.',
  pl: 'Peek ma kilkuminutową przerwę techniczną. Spróbuj ponownie za chwilę.',
  ru: 'Peek на техобслуживании несколько минут. Попробуйте чуть позже.',
  zh: 'Peek 正在进行几分钟的维护，请稍后再试。',
};

function clock(iso, lang, timeZone) {
  try {
    return new Intl.DateTimeFormat(lang, { hour: 'numeric', minute: '2-digit', timeZone }).format(new Date(iso));
  } catch {
    return new Intl.DateTimeFormat('en', { hour: 'numeric', minute: '2-digit', timeZone: 'UTC' }).format(new Date(iso)) + ' UTC';
  }
}

const site = {
  /** The KV key of Peek's own announcements, beside `all`. */
  key: 'peek',
  languages: LANGUAGES,
  localeCookie: 'sdt.locale',
  /** Supabase's session cookie, if one is ever set on the site: a page asked for with it is never saved. */
  sessionCookie: /(?:^|;\s*)sb-[^=]*=/,
  /** Hosts that only ever answer JSON: Supabase's auth. */
  jsonHosts: ['auth.peektracker.com'],
  apiPath: /^\/(?:api|mcp|oauth\/(?:token|register|revoke))(?:\/|$)/,
  /** What checks the server: its answer goes out as it is, so a watcher still sees the server down. */
  healthPath: /^\/health(?:\/|$)/,
  /** The pages that are the same for everyone, written from content/: the carriers' pages and the guides. */
  savedPages: new RegExp(`^/(?:(?:de|fr|it|es|pt|pt-br|pl|ru|zh|${PAGE_ONLY})/)?(?:carriers|guides)(?:/[a-z0-9-]+)?/?$`),
  savedNote(lang, { planned, backAround, timeZone }) {
    const words = NOTE[lang] ?? NOTE.en;
    return planned && backAround ? `${words.note} ${words.back.replace('{{time}}', clock(backAround, lang, timeZone))}` : words.note;
  },
  /** The note's look, at the top of the saved page: Peek's soft yellow. */
  savedNoteStyle: "display:block;position:relative;z-index:2147483647;padding:10px 16px;background:#ffebab;color:#20251e;font:500 14px/1.4 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;text-align:center;border-bottom:1px solid #dce0d5",
  apiMessage(lang) {
    return API[lang] ?? API.en;
  },
};

export default site;

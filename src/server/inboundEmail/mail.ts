import 'server-only';

import PostalMime, { type Address, type Email } from 'postal-mime';

/** What Peek keeps from an email: the text to read numbers from, which it drops after, and who sent it. */
export interface InboundMail {
  /** The shop or sender that wrote the email, as the parcels it names are labelled. Empty when it has none. */
  sender: string;
  /** The address the email is from, in lower case. Empty when it has none. */
  from: string;
  /** The subject, the body as text, and the links of its HTML, one per line. */
  text: string;
  /** Gmail asking to confirm that it may forward to the address, with its code and link when they are there. */
  confirmation: { code: string | null; link: string | null } | null;
}

/** The longest label a parcel takes. */
const LABEL_LENGTH = 80;
/**
 * How much of an email is read, in UTF-16 units: a shipping email's HTML is far smaller, and every pattern below runs
 * on bounded text so that no email can hold the server up.
 */
const MOST_HTML_LENGTH = 400_000;
const MOST_SUBJECT_LENGTH = 1_000;
/** A forwarded message's From line is near its top, and short. */
const MOST_FORWARD_LINES = 300;
const MOST_LINE_LENGTH = 300;
const MOST_LINKS = 100;
const MOST_LINK_LENGTH = 2_000;
/** How the text read for numbers is shared, within the reading's own bound (`MOST_TEXT_LENGTH`). */
const BODY_SHARE = 12_000;
const LINKS_SHARE = 3_000;
const GMAIL_FORWARDING = 'forwarding-noreply@google.com';
/** Hosts Gmail's confirmation link may lead to: a link to anywhere else is never shown. */
const GMAIL_CONFIRMATION_HOSTS = new Set(['mail-settings.google.com', 'mail.google.com', 'isolated.mail.google.com']);
/** Subject prefixes a mail app puts on a message forwarded by hand, in the languages Peek speaks and a few more. */
const FORWARDED_SUBJECT = /^\s*(?:fwd?|fw|tr|wg|rv|enc|i|vs|doorst|pd|пер|переслать|转发|轉寄)\s*:/i;
/** The line a forwarded message's own sender is on, below the forward's marker. */
const FORWARDED_FROM = /^[\s>*]*(?:from|von|de|da|van|od|от|från|fra|lähettäjä|发件人|寄件者)\s*:([^]*)$/i;
/** Parts of a sender's domain that name the mail system rather than the shop. */
const MAIL_SUBDOMAINS = /^(?:mail|email|e|em|info|news|newsletter|notify|notifications?|noreply|no-reply|delivery|shipping|track|tracking|orders?|mailer|bounce|post)$/i;

/**
 * Characters that show nothing: a marketing email pads its preview line with thousands of them, soft hyphens and
 * zero-width marks between spaces, which would use up the share of the body that is read.
 */
const INVISIBLE = /[\u00AD\u034F\u061C\u180E\u200B-\u200F\u202A-\u202E\u2060-\u2064\uFEFF]/g;
/** The text without its invisible characters, and each run of spaces they leave as one space. */
const withoutPadding = (text: string) => text.replace(INVISIBLE, '').replace(/[ \t\f\v\u00A0]{2,}/g, ' ');

const ENTITIES: Record<string, string> = {
  amp: '&', lt: '<', gt: '>', quot: '"', apos: "'", nbsp: ' ', ensp: ' ', emsp: ' ', thinsp: ' ',
  shy: '\u00AD', zwnj: '\u200C', zwj: '\u200D', lrm: '\u200E', rlm: '\u200F',
};

function decodeEntities(text: string): string {
  return text.replace(/&(#x[0-9a-f]{1,6}|#\d{1,7}|[a-z]{2,8});/gi, (whole, entity: string) => {
    if (entity[0] === '#') {
      const code = entity[1] === 'x' || entity[1] === 'X' ? parseInt(entity.slice(2), 16) : parseInt(entity.slice(1), 10);
      return code > 0 && code <= 0x10ffff ? String.fromCodePoint(code) : ' ';
    }
    return ENTITIES[entity.toLowerCase()] ?? whole;
  });
}

/**
 * The HTML without its comments and the elements that hold no text to read, each found by a search that never looks
 * back, so that one left open drops the rest instead of being searched for again from every later opening.
 */
function withoutHiddenParts(html: string): string {
  const lower = html.toLowerCase();
  const opening = /<!--|<(head|script|style|title)\b/gi;
  let kept = '';
  let at = 0;
  for (let found = opening.exec(html); found; found = opening.exec(html)) {
    kept += `${html.slice(at, found.index)} `;
    const closing = found[1] ? lower.indexOf(`</${found[1].toLowerCase()}`, opening.lastIndex) : lower.indexOf('-->', opening.lastIndex);
    if (closing < 0) return kept;
    const end = found[1] ? lower.indexOf('>', closing) : closing + 2;
    if (end < 0) return kept;
    at = end + 1;
    opening.lastIndex = at;
  }
  return kept + html.slice(at);
}

/**
 * The HTML's text, a line per block and a row of a table on one line, so that a number stays on the line of the words
 * that name it, as `numbersInText` reads them. A tag ends at the first `<` or `>`, which keeps each search short.
 */
export function htmlText(html: string): string {
  return decodeEntities(withoutHiddenParts(html.slice(0, MOST_HTML_LENGTH))
    .replace(/<\s*br\b[^<>]*>/gi, '\n')
    .replace(/<\/\s*(p|div|tr|li|h[1-6]|table|tbody|thead|section|article|blockquote|center)\s*>/gi, '\n')
    .replace(/<\/\s*t[dh]\s*>/gi, ' ')
    .replace(/<[^<>]*>/g, ' '))
    .replace(/[ \t\f\v ]+/g, ' ')
    .replace(/ *\n[\s]*/g, '\n')
    .trim();
}

/** The addresses the HTML links to, decoded, each on its own line: a carrier's link names its number. */
export function htmlLinks(html: string): string[] {
  const links = new Set<string>();
  for (const [, quoted, bare] of html.slice(0, MOST_HTML_LENGTH).matchAll(/<a\b[^<>]*?\bhref\s*=\s*(?:"([^"<>]*)"|'([^'<>]*)')/gi)) {
    const link = decodeEntities((quoted ?? bare ?? '').trim());
    if (/^https?:\/\//i.test(link) && link.length <= MOST_LINK_LENGTH) links.add(link);
    if (links.size >= MOST_LINKS) break;
  }
  return [...links];
}

function mailbox(address: Address | undefined): { name: string; address: string } | null {
  if (!address) return null;
  if (address.group) return address.group[0] ?? null;
  return { name: address.name, address: address.address };
}

/** A name for an address that has none: its domain without the mail system's own part, as `amazon.co.uk`. */
function domainName(address: string): string {
  const domain = address.split('@')[1]?.toLowerCase() ?? '';
  const labels = domain.split('.').filter(Boolean);
  while (labels.length > 2 && MAIL_SUBDOMAINS.test(labels[0]!)) labels.shift();
  return labels.join('.');
}

function cleanLabel(name: string): string {
  const words = name.slice(0, MOST_LINE_LENGTH)
    .replace(/["“”«»]/g, '')
    .replace(/\s+via\s+.*$/i, '')
    .replace(/\s+/g, ' ')
    .trim();
  return [...words].slice(0, LABEL_LENGTH).join('').trim();
}

/** "Name <address>" or a bare address, as a forwarded message's From line writes it. */
function senderOfLine(line: string): string {
  const value = line.slice(0, MOST_LINE_LENGTH).replace(/^[\s*]+|[\s*]+$/g, '');
  const named = /^(.*?)\s*[<[]\s*(?:mailto:)?([^\s<>[\]]+@[^\s<>[\]]+)\s*[>\]]\s*$/.exec(value);
  if (named) return cleanLabel(named[1]!) || domainName(named[2]!);
  const bare = /^[^\s@]+@[^\s@]+$/.exec(value.trim());
  return bare ? domainName(bare[0]) : cleanLabel(value);
}

/**
 * Who wrote the email. Forwarding by a rule keeps the shop's own From; forwarding by hand puts the forwarder there
 * and the shop's on the first From line of the forwarded part, which is read instead.
 */
function senderOf(email: Email, text: string): string {
  if (FORWARDED_SUBJECT.test((email.subject ?? '').slice(0, MOST_SUBJECT_LENGTH))) {
    for (const line of text.split('\n', MOST_FORWARD_LINES)) {
      const found = FORWARDED_FROM.exec(line.slice(0, MOST_LINE_LENGTH));
      if (found) {
        const sender = senderOfLine(found[1]!);
        if (sender) return sender;
      }
    }
  }
  const from = mailbox(email.from);
  if (!from) return '';
  return cleanLabel(from.name) || domainName(from.address);
}

/** The link as stored, written the one way the database takes: on Google's mail hosts, with no user or port. */
function googleLink(candidate: string): string | null {
  try {
    const url = new URL(candidate);
    if (url.protocol !== 'https:' || !GMAIL_CONFIRMATION_HOSTS.has(url.hostname) || url.username || url.password || url.port) return null;
    return url.href.length <= 1_000 ? url.href : null;
  } catch {
    return null;
  }
}

/** Gmail's request to confirm forwarding: its code, from the subject or the body, and its link, if on Google. */
function gmailConfirmation(email: Email, text: string, links: string[]): InboundMail['confirmation'] {
  if (mailbox(email.from)?.address.toLowerCase() !== GMAIL_FORWARDING) return null;
  const code = /\(#(\d{6,12})\)/.exec((email.subject ?? '').slice(0, MOST_SUBJECT_LENGTH))?.[1] ?? /(?:^|\D)(\d{9})(?:\D|$)/.exec(text)?.[1] ?? null;
  const candidates = [...links, ...(text.match(/https:\/\/[^\s<>"')]{1,2000}/g) ?? [])];
  let link: string | null = null;
  for (const candidate of candidates) {
    link = googleLink(candidate);
    if (link) break;
  }
  return code || link ? { code, link } : null;
}

/** Reads a raw email (MIME). Attachments are never opened. */
export async function readInboundMail(raw: Uint8Array): Promise<InboundMail> {
  const email = await PostalMime.parse(raw, { attachmentEncoding: 'arraybuffer', maxPartCount: 500, maxNestingDepth: 32 });
  const html = email.html ?? '';
  const body = withoutPadding(email.text?.trim() ? email.text.slice(0, MOST_HTML_LENGTH) : htmlText(html));
  const links = htmlLinks(html);
  const read: string[] = [];
  let room = LINKS_SHARE;
  for (const link of links) {
    if (link.length >= room) continue;
    read.push(link);
    room -= link.length + 1;
  }
  const text = [(email.subject ?? '').slice(0, MOST_SUBJECT_LENGTH), body.slice(0, BODY_SHARE), ...read].join('\n');
  return {
    sender: senderOf(email, body),
    from: mailbox(email.from)?.address.trim().toLowerCase() ?? '',
    text,
    confirmation: gmailConfirmation(email, body, links),
  };
}

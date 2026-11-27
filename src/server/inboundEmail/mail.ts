import 'server-only';

import { decodeHTMLStrict } from 'entities';
import { Parser } from 'htmlparser2';
import PostalMime, { type Address, type Email } from 'postal-mime';
import { shopTrackingLink, type ShopTrackingLink } from 'universal-parcel-scraper';
import { MOST_SHOP_LINKS } from '../shopLinks';
import { carrierLink } from './carrierSender';

/** What Peek keeps from an email: the text to read numbers from, which it drops after, and who sent it. */
export interface InboundMail {
  /** The shop or sender that wrote the email, as the parcels it names are labelled. Empty when it has none. */
  sender: string;
  /** The address the email is from, in lower case. Empty when it has none. */
  from: string;
  /**
   * The address of who wrote it, in lower case: `from`, or the forwarded message's own sender when the email was
   * forwarded by hand. Empty when the forwarded part names nobody's address.
   */
  origin: string;
  /** The subject, as far as it is read. */
  subject: string;
  /** The subject, the body as text, and the links of its HTML, one per line. */
  text: string;
  /**
   * Its links to a carrier's site or a shop's tracking platform (`shopTrackingLink`: parcelLab's delivery-status.com),
   * as `text` holds them: all that is read of an email whose subject names no shipping word.
   */
  trackingLinks: string[];
  /**
   * Its mailing tool's click redirects whose words read like tracking ("Track your parcel"), when it has no link
   * to a carrier's site or a shop's tracking platform of its own: Peek follows them to that link (`shopTrackingLink`).
   */
  redirectLinks: ShopTrackingLink[];
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
/** A link's words are a button's or a line's: "Track your parcel", "Sendung verfolgen". */
const MOST_LINK_WORDS = 120;
/**
 * How the text read for numbers is shared, within the reading's own bound (`MOST_TEXT_LENGTH`). The body is read
 * without its links' addresses, which take the links' share: a shop's email holds dozens, each of hundreds of
 * characters, which would push its tracking number out of the body's share.
 */
const BODY_SHARE = 12_000;
const LINKS_SHARE = 3_000;
const GMAIL_FORWARDING = 'forwarding-noreply@google.com';
/** Hosts Gmail's confirmation link may lead to: a link to anywhere else is never shown. */
const GMAIL_CONFIRMATION_HOSTS = new Set(['mail-settings.google.com', 'mail.google.com', 'isolated.mail.google.com']);
/** Subject prefixes a mail app puts on a message forwarded by hand, in the languages Peek speaks and a few more. */
const FORWARDED_SUBJECT = /^\s*(?:fwd?|fw|tr|wg|rv|enc|i|vs|doorst|pd|пер|переслать|转发|轉寄)\s*:/i;
/** The line a forwarded message's own sender is on, below the forward's marker. */
const FORWARDED_FROM = /^[\s>*]*(?:from|von|de|da|van|od|от|från|fra|lähettäjä|发件人|寄件者)\s*:([^]*)$/i;
/**
 * The line some mail apps open a message forwarded whole with, under its own subject: its date and time, on a 24- or
 * 12-hour clock, then its sender ("22.11.2026 17:43:04 Example Shop <notice@example-shop.com>:"), or its address alone
 * when the message named none ("22.11.2026 17:43:04 notice@example-shop.com:").
 */
const DATED_SENDER = /^[\s>*]*(?:\d{1,2}[./-]\d{1,2}[./-]\d{2,4}|\d{4}-\d{1,2}-\d{1,2})[,\s]+\d{1,2}:\d{2}(?::\d{2})?(?:\s*[AaPp]\.?[Mm]\.?)?\s+([^<>]+<[^\s<>]+@[^\s<>]+>|[^\s<>@:]+@[^\s<>@:]+)\s*:\s*$/;
/** That line opens the forwarded message: it is among the body's first lines with words. */
const DATED_SENDER_LINES = 3;
/** Parts of a sender's domain that name the mail system rather than the shop. */
const MAIL_SUBDOMAINS = /^(?:mail|email|e|em|info|news|newsletter|notify|notifications?|noreply|no-reply|delivery|shipping|track|tracking|orders?|mailer|bounce|post)$/i;

/**
 * Characters that show nothing: a marketing email pads its preview line with thousands of them, soft hyphens and
 * zero-width marks between spaces, which would use up the share of the body that is read.
 */
const INVISIBLE = /[\u00AD\u034F\u061C\u180E\u200B-\u200F\u202A-\u202E\u2060-\u2064\uFEFF]/g;
/** The text without its invisible characters, and each run of spaces they leave as one space. */
const withoutPadding = (text: string) => text.replace(INVISIBLE, '').replace(/[ \t\f\v\u00A0]{2,}/g, ' ');

/**
 * The text with HTML's entities written out, as the labels are read: a mailing tool writes a French email's accents
 * as `&eacute;`. Only an entity that ends with `;` counts, so that an address in the text keeps its `&section=`, and
 * the wider spaces become plain ones.
 */
const decodeEntities = (text: string) => decodeHTMLStrict(text).replace(/[\u2002\u2003\u2009]/g, ' ');

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

/**
 * A web address in plain text, bare or between angle brackets: a mail app that writes an HTML email's text, as Gmail
 * does for one forwarded by hand, puts each link's address after its words ("Suivre mon colis <https://…>").
 */
const TEXT_LINK = /<?https?:\/\/[^\s<>"]+>?/gi;

/**
 * The words a plain text writes a link after: on its line ("Suivre mon colis <https://…>"), else on the line above
 * when the link stands alone on its own.
 */
function wordsBefore(text: string, at: number): string {
  const start = text.lastIndexOf('\n', at - 1) + 1;
  const line = text.slice(Math.max(start, at - MOST_LINK_WORDS), at).replace(TEXT_LINK, ' ');
  if (/\p{L}/u.test(line) || start === 0) return line.trim();
  const above = text.slice(Math.max(0, start - 1 - MOST_LINK_WORDS), start - 1);
  return above.slice(above.lastIndexOf('\n') + 1).replace(TEXT_LINK, ' ').trim();
}

/** Each link's words, those of all the places it is written. */
function addWords(links: Map<string, string>, link: string, words: string): void {
  const known = links.get(link);
  const said = words.replace(/\s+/g, ' ').trim();
  links.set(link, known && said ? `${known} · ${said}`.slice(0, MOST_LINK_WORDS * 2) : known || said);
}

/** The plain text with a space for each address it writes, and those addresses with their words, as `htmlLinks` keeps them. */
function withoutAddresses(text: string): { text: string; links: Map<string, string> } {
  const links = new Map<string, string>();
  const rest = text.replace(TEXT_LINK, (found, at: number) => {
    const link = found.replace(/^<|>$/g, '');
    if (link.length <= MOST_LINK_LENGTH && (links.has(link) || links.size < MOST_LINKS)) addWords(links, link, wordsBefore(text, at));
    return ' ';
  });
  return { text: rest, links };
}

/**
 * The addresses the HTML links to, decoded, in the order written, each with its words: its text, an image's
 * description and its own title. A carrier's link names its number; a mailing tool's redirect is followed only when
 * its words read like tracking.
 */
export function htmlLinks(html: string): Map<string, string> {
  const links = new Map<string, string>();
  let open: { link: string; words: string } | null = null;
  const close = () => {
    if (open) addWords(links, open.link, open.words);
    open = null;
  };
  const parser = new Parser({
    onopentag(name, attributes) {
      if (name === 'a') {
        close();
        const link = attributes.href?.trim();
        if (link && /^https?:\/\//i.test(link) && link.length <= MOST_LINK_LENGTH) {
          open = { link, words: [attributes.title, attributes['aria-label']].filter(Boolean).join(' ') };
          addWords(links, link, '');
        }
      } else if (open && name === 'img' && attributes.alt) {
        open.words += ` ${attributes.alt}`;
      }
      if (links.size >= MOST_LINKS) parser.pause();
    },
    ontext(text) {
      if (open && open.words.length < MOST_LINK_WORDS) open.words += text;
    },
    onclosetag(name) {
      if (name === 'a') close();
    },
  });
  parser.end(html.slice(0, MOST_HTML_LENGTH));
  close();
  return links;
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
function senderOfLine(line: string): { name: string; address: string } {
  const value = line.slice(0, MOST_LINE_LENGTH).replace(/^[\s*]+|[\s*]+$/g, '');
  const named = /^(.*?)\s*[<[]\s*(?:mailto:)?([^\s<>[\]]+@[^\s<>[\]]+)\s*[>\]]\s*$/.exec(value);
  if (named) return { name: cleanLabel(named[1]!) || domainName(named[2]!), address: named[2]!.toLowerCase() };
  const bare = /^[^\s@]+@[^\s@]+$/.exec(value.trim());
  return bare ? { name: domainName(bare[0]), address: bare[0].toLowerCase() } : { name: cleanLabel(value), address: '' };
}

/**
 * Who wrote the email. Forwarding by a rule keeps the shop's own From; forwarding by hand puts the forwarder there
 * and the shop's on the first From line of the forwarded part, which is read instead. Some mail apps forward a
 * message whole under its own subject, opened by a line with its date and sender: that sender is read too, unless it
 * is the forwarder's own address.
 */
function senderOf(email: Email, text: string): { name: string; address: string } {
  if (FORWARDED_SUBJECT.test((email.subject ?? '').slice(0, MOST_SUBJECT_LENGTH))) {
    for (const line of text.split('\n', MOST_FORWARD_LINES)) {
      const found = FORWARDED_FROM.exec(line.slice(0, MOST_LINE_LENGTH));
      if (found) {
        const sender = senderOfLine(found[1]!);
        if (sender.name) return sender;
      }
    }
  }
  const from = mailbox(email.from);
  const opening = text.split('\n', MOST_FORWARD_LINES).filter((line) => line.trim()).slice(0, DATED_SENDER_LINES);
  for (const line of opening) {
    const found = DATED_SENDER.exec(line.slice(0, MOST_LINE_LENGTH));
    if (!found) continue;
    const sender = senderOfLine(found[1]!);
    if (sender.name && sender.address !== from?.address.trim().toLowerCase()) return sender;
  }
  if (!from) return { name: '', address: '' };
  return { name: cleanLabel(from.name) || domainName(from.address), address: from.address.trim().toLowerCase() };
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
/** How much of an email is searched for its own From header: a header block is far shorter. */
const MOST_HEADER_BYTES = 65_536;

/**
 * The address in the email's own From header, in lower case, read from its header block alone so that nothing
 * else is parsed; empty when there is none. Its display name is left aside.
 */
export function headerFromAddress(raw: Uint8Array): string {
  const head = new TextDecoder('latin1').decode(raw.subarray(0, MOST_HEADER_BYTES));
  const end = head.search(/\r?\n\r?\n/);
  // A header can go on over several lines, each next one starting with a space.
  const lines = (end < 0 ? head : head.slice(0, end)).replace(/\r?\n[ \t]+/g, ' ').split(/\r?\n/);
  const value = lines.find((line) => /^from[ \t]*:/i.test(line))?.replace(/^[^:]*:/, '') ?? '';
  const address = /<([^<>\s@]+@[^<>\s@]+)>/.exec(value)?.[1] ?? /(?:^|[\s,])([^\s<>"(),;:@]+@[^\s<>"(),;:@]+)/.exec(value)?.[1] ?? '';
  return address.toLowerCase();
}

export async function readInboundMail(raw: Uint8Array): Promise<InboundMail> {
  const email = await PostalMime.parse(raw, { attachmentEncoding: 'arraybuffer', maxPartCount: 500, maxNestingDepth: 32 });
  const html = email.html ?? '';
  // A mailing tool may write HTML's entities into the plain part too.
  const plain = email.text?.trim() ? withoutAddresses(decodeEntities(email.text.slice(0, MOST_HTML_LENGTH))) : null;
  const body = withoutPadding(plain ? plain.text : htmlText(html));
  const words = htmlLinks(html);
  for (const [link, said] of plain?.links ?? []) addWords(words, link, said);
  const links = [...words.keys()];
  const read: string[] = [];
  let room = LINKS_SHARE;
  // A link to a carrier's site names the parcel's number, as a link to the shop's tracking page names its parcels
  // (shopTrackingLink): those are read first, before the shop's other links.
  const carriers = new Set(links.filter((link) => carrierLink(link) || shopTrackingLink(link)));
  // A mailing tool's redirect, followed only for want of a link of the shop's or the carrier's own: following one counts
  // as the reader's click.
  const redirects = carriers.size ? [] : links
    .map((link) => shopTrackingLink(link, words.get(link) ?? ''))
    .filter((link): link is ShopTrackingLink => link?.platform === 'redirect')
    .slice(0, MOST_SHOP_LINKS);
  for (const link of [...carriers, ...links.filter((link) => !carriers.has(link))]) {
    if (link.length >= room) continue;
    read.push(link);
    room -= link.length + 1;
  }
  const subject = (email.subject ?? '').slice(0, MOST_SUBJECT_LENGTH);
  const text = [subject, body.slice(0, BODY_SHARE), ...read].join('\n');
  const sender = senderOf(email, body);
  return {
    sender: sender.name,
    from: mailbox(email.from)?.address.trim().toLowerCase() ?? '',
    origin: sender.address,
    subject,
    text,
    trackingLinks: read.filter((link) => carriers.has(link)),
    redirectLinks: redirects,
    confirmation: gmailConfirmation(email, body, links),
  };
}

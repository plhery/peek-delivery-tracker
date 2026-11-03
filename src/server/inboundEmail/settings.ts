import 'server-only';

import { createHmac, randomBytes, timingSafeEqual } from 'node:crypto';
import { accountList, listsAccount, type AccountList } from '../accountList';

/**
 * Adding parcels by email. Each account that turns it on gets a forwarding address whose token names it. A
 * Cloudflare Email Worker (ops/cloudflare/inbound-email-worker.js) receives the mail and posts it here, signed with
 * the secret both share.
 */
export interface InboundEmailSettings {
  /** The address with `{token}` where each account's token goes, such as `add+{token}@in.example.com`. */
  address: string;
  /** Signs what the Worker posts. */
  secret: string;
  /** Who sees the setting: everyone, or the accounts named by id or email. */
  accounts: AccountList;
  /** Emails one address takes per UTC day. */
  perAccountPerDay: number;
  /** Emails every address together takes per UTC hour. */
  perHour: number;
}

const ADDRESS = /^[a-z0-9.!#$%&'*+/=?^_`|~-]*\{token\}[a-z0-9.!#$%&'*+/=?^_`|~-]*@[a-z0-9-]+(?:\.[a-z0-9-]+)+$/;
/** The largest email taken, as the Worker caps it: a shop's email is far smaller, and attachments are never read. */
export const MAX_INBOUND_EMAIL_BYTES = 1_048_576;
/** How long a signature holds: the Worker posts at once, so an older one is a replay. */
const SIGNATURE_WINDOW_SECONDS = 300;

function count(value: string | undefined, fallback: number, name: string): number {
  const text = value?.trim() ?? '';
  if (!text) return fallback;
  if (!/^\d{1,6}$/.test(text)) throw new Error(`${name} must be a whole number`);
  return Number(text);
}

/**
 * Reads the settings. Without `INBOUND_EMAIL_ADDRESS` the server takes no email and this returns null. A partial or
 * malformed set throws, so a deployment fails at startup.
 */
export function inboundEmailSettings(env: NodeJS.ProcessEnv = process.env): InboundEmailSettings | null {
  const address = env.INBOUND_EMAIL_ADDRESS?.trim().toLowerCase() ?? '';
  if (!address) return null;
  if (!ADDRESS.test(address) || address.split('{token}').length !== 2) {
    throw new Error('INBOUND_EMAIL_ADDRESS must be an address with {token} once in its local part, such as add+{token}@in.example.com');
  }
  const secret = env.INBOUND_EMAIL_SECRET?.trim() ?? '';
  if (secret.length < 32) throw new Error('INBOUND_EMAIL_ADDRESS needs INBOUND_EMAIL_SECRET, 32 characters or more');
  return {
    address,
    secret,
    accounts: accountList(env.INBOUND_EMAIL_ACCOUNTS, 'INBOUND_EMAIL_ACCOUNTS'),
    perAccountPerDay: count(env.INBOUND_EMAILS_PER_ACCOUNT_PER_DAY, 30, 'INBOUND_EMAILS_PER_ACCOUNT_PER_DAY'),
    perHour: count(env.INBOUND_EMAILS_PER_HOUR, 200, 'INBOUND_EMAILS_PER_HOUR'),
  };
}

/** The same reading for request handling: a malformed set takes no email, and startup reports it. */
export function inboundEmailConfigured(env: NodeJS.ProcessEnv = process.env): InboundEmailSettings | null {
  try {
    return inboundEmailSettings(env);
  } catch {
    return null;
  }
}

/** Whether the account may have a forwarding address. */
export function inboundEmailOpenTo(settings: InboundEmailSettings, account: { id: string; email?: string | null }): boolean {
  return listsAccount(settings.accounts, account);
}

const BASE32 = 'abcdefghijklmnopqrstuvwxyz234567';

/** A new token: 80 random bits as 16 lowercase base32 characters, which survive any mail system's handling of case. */
export function newInboxToken(): string {
  const bytes = randomBytes(10);
  let bits = 0;
  let value = 0;
  let token = '';
  for (const byte of bytes) {
    value = (value << 8) | byte;
    bits += 8;
    while (bits >= 5) {
      token += BASE32[(value >>> (bits - 5)) & 31];
      bits -= 5;
    }
  }
  return token;
}

export function inboxAddress(settings: Pick<InboundEmailSettings, 'address'>, token: string): string {
  return settings.address.replace('{token}', token);
}

/** The token an envelope recipient names, when it is an address of this form. */
export function tokenFromRecipient(settings: Pick<InboundEmailSettings, 'address'>, recipient: string): string | null {
  const [before, after] = settings.address.split('{token}') as [string, string];
  const value = recipient.trim().toLowerCase();
  if (!value.startsWith(before) || !value.endsWith(after)) return null;
  const token = value.slice(before.length, value.length - after.length);
  return /^[a-z2-7]{16}$/.test(token) ? token : null;
}

/** What the Worker signs: the time, the recipient and the mail as it arrived. */
export function inboundSignature(secret: string, timestamp: number, recipient: string, body: Uint8Array): string {
  return createHmac('sha256', secret).update(`${timestamp}.${recipient}.`).update(body).digest('hex');
}

/** Whether `X-Peek-Signature` (`t=<unix seconds>,v1=<hex>`) signs this recipient and mail, within five minutes. */
export function verifyInboundSignature(
  secret: string,
  header: string | null,
  recipient: string,
  body: Uint8Array,
  now = Date.now(),
): boolean {
  const match = /^t=(\d{1,12}),v1=([0-9a-f]{64})$/.exec(header?.trim() ?? '');
  if (!match) return false;
  const timestamp = Number(match[1]);
  if (Math.abs(now / 1_000 - timestamp) > SIGNATURE_WINDOW_SECONDS) return false;
  const expected = Buffer.from(inboundSignature(secret, timestamp, recipient, body), 'hex');
  return timingSafeEqual(expected, Buffer.from(match[2]!, 'hex'));
}

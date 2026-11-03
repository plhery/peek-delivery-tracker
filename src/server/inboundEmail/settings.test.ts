import { describe, expect, it } from 'vitest';
import worker, { sign } from '../../../ops/cloudflare/inbound-email-worker.js';
import {
  inboundEmailOpenTo,
  inboundEmailSettings,
  inboxAddress,
  newInboxToken,
  tokenFromRecipient,
  verifyInboundSignature,
} from './settings';

const SECRET = 'synthetic-inbound-secret-0123456789abcdef';
const env = (values: Record<string, string>) => ({ INBOUND_EMAIL_SECRET: SECRET, ...values }) as unknown as NodeJS.ProcessEnv;

describe('the settings', () => {
  it('are off without an address, and refuse a partial or malformed set', () => {
    expect(inboundEmailSettings(env({}))).toBeNull();
    expect(() => inboundEmailSettings(env({ INBOUND_EMAIL_ADDRESS: 'add@in.example.com' }))).toThrow(/\{token\}/);
    expect(() => inboundEmailSettings(env({ INBOUND_EMAIL_ADDRESS: '{token}{token}@in.example.com' }))).toThrow(/\{token\}/);
    expect(() => inboundEmailSettings({ INBOUND_EMAIL_ADDRESS: 'add+{token}@in.example.com' } as unknown as NodeJS.ProcessEnv)).toThrow(/SECRET/);
    expect(() => inboundEmailSettings(env({ INBOUND_EMAIL_ADDRESS: 'add+{token}@in.example.com', INBOUND_EMAIL_ACCOUNTS: 'someone' }))).toThrow(/ACCOUNTS/);
  });

  it('open the setting to the accounts they name, by id or email, or to everyone', () => {
    const settings = inboundEmailSettings(env({
      INBOUND_EMAIL_ADDRESS: 'Add+{token}@In.Example.com',
      INBOUND_EMAIL_ACCOUNTS: '5a000000-0000-4000-a000-000000000001, Alex@Example.com',
    }))!;
    expect(settings.address).toBe('add+{token}@in.example.com');
    expect(inboundEmailOpenTo(settings, { id: '5a000000-0000-4000-a000-000000000001' })).toBe(true);
    expect(inboundEmailOpenTo(settings, { id: '5a000000-0000-4000-a000-000000000002', email: 'alex@example.com' })).toBe(true);
    expect(inboundEmailOpenTo(settings, { id: '5a000000-0000-4000-a000-000000000002', email: 'sam@example.com' })).toBe(false);
    const everyone = inboundEmailSettings(env({ INBOUND_EMAIL_ADDRESS: 'add+{token}@in.example.com', INBOUND_EMAIL_ACCOUNTS: '*' }))!;
    expect(inboundEmailOpenTo(everyone, { id: '5a000000-0000-4000-a000-000000000002' })).toBe(true);
    expect(inboundEmailOpenTo(inboundEmailSettings(env({ INBOUND_EMAIL_ADDRESS: 'add+{token}@in.example.com' }))!, { id: 'x' })).toBe(false);
  });
});

describe('addresses', () => {
  const settings = { address: 'add+{token}@in.example.com' };

  it('carry a fresh 16-character token, read back from the recipient whatever its case', () => {
    const token = newInboxToken();
    expect(token).toMatch(/^[a-z2-7]{16}$/);
    expect(newInboxToken()).not.toBe(token);
    expect(tokenFromRecipient(settings, inboxAddress(settings, token).toUpperCase())).toBe(token);
  });

  it('name nobody when the recipient has another form', () => {
    expect(tokenFromRecipient(settings, 'add@in.example.com')).toBeNull();
    expect(tokenFromRecipient(settings, 'add+abcdefghijklmnop@other.example.com')).toBeNull();
    expect(tokenFromRecipient(settings, 'add+abcdefghijklmno1@in.example.com')).toBeNull();
    expect(tokenFromRecipient({ address: '{token}@in.example.com' }, 'abcdefghijklmnop@in.example.com')).toBe('abcdefghijklmnop');
  });
});

describe('the Worker\'s signature', () => {
  const body = new TextEncoder().encode('Subject: hello\r\n\r\nbody');
  const now = 1_793_000_000_000;

  it('is what the app checks, for that recipient and mail, within five minutes', async () => {
    const timestamp = now / 1_000;
    const header = `t=${timestamp},v1=${await sign(SECRET, timestamp, 'add+abc@in.example.com', body)}`;
    expect(verifyInboundSignature(SECRET, header, 'add+abc@in.example.com', body, now)).toBe(true);
    expect(verifyInboundSignature(SECRET, header, 'add+abd@in.example.com', body, now)).toBe(false);
    expect(verifyInboundSignature(SECRET, header, 'add+abc@in.example.com', new TextEncoder().encode('other'), now)).toBe(false);
    expect(verifyInboundSignature(`${SECRET}x`, header, 'add+abc@in.example.com', body, now)).toBe(false);
    expect(verifyInboundSignature(SECRET, header, 'add+abc@in.example.com', body, now + 301_000)).toBe(false);
    expect(verifyInboundSignature(SECRET, null, 'add+abc@in.example.com', body, now)).toBe(false);
    expect(verifyInboundSignature(SECRET, 'v1=00', 'add+abc@in.example.com', body, now)).toBe(false);
  });

  it('goes with each email the Worker passes on, and a too-large email is refused before', async () => {
    const posted: Request[] = [];
    const fetcher = globalThis.fetch;
    globalThis.fetch = (async (input: RequestInfo | URL, init?: RequestInit) => {
      posted.push(new Request(input, init));
      return new Response(null, { status: 202 });
    }) as typeof fetch;
    try {
      const rejected: string[] = [];
      const message = (size: number) => ({
        to: 'Add+abcdefghijklmnop@in.example.com',
        rawSize: size,
        raw: new Response(body).body,
        setReject: (reason: string) => { rejected.push(reason); },
      });
      const environment = { PEEK_INBOUND_URL: 'https://peek.example/api/inbound/email', PEEK_INBOUND_SECRET: SECRET };
      await worker.email(message(body.byteLength), environment);
      await worker.email(message(2_000_000), environment);
      expect(rejected).toHaveLength(1);
      expect(posted).toHaveLength(1);
      const request = posted[0]!;
      expect(request.headers.get('x-peek-recipient')).toBe('add+abcdefghijklmnop@in.example.com');
      const sent = new Uint8Array(await request.arrayBuffer());
      expect(verifyInboundSignature(SECRET, request.headers.get('x-peek-signature'), 'add+abcdefghijklmnop@in.example.com', sent)).toBe(true);
    } finally {
      globalThis.fetch = fetcher;
    }
  });
});

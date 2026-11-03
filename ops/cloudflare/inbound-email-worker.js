// Peek's Cloudflare Email Worker: passes each email sent to a forwarding address for adding
// parcels by email on to the app, signed, and keeps nothing. Email Routing sends it the
// address's mail (docs/DEPLOYMENT.md). The app reads the tracking numbers and drops the email.
//
// Variables: PEEK_INBOUND_URL, the app's https://<host>/api/inbound/email; secret:
// PEEK_INBOUND_SECRET, the same value as the app's INBOUND_EMAIL_SECRET.

/** The largest email passed on, as the app takes it. */
const MAX_BYTES = 1_048_576;
const TOO_LARGE = 'This email is too large for Peek. Forward the shipping email alone.';

function hex(bytes) {
  return [...new Uint8Array(bytes)].map((byte) => byte.toString(16).padStart(2, '0')).join('');
}

/** The signature the app checks: HMAC-SHA256 of "<unix seconds>.<recipient>." and the email as it arrived. */
export async function sign(secret, timestamp, recipient, body) {
  const encoder = new TextEncoder();
  const key = await crypto.subtle.importKey('raw', encoder.encode(secret), { name: 'HMAC', hash: 'SHA-256' }, false, ['sign']);
  const prefix = encoder.encode(`${timestamp}.${recipient}.`);
  const signed = new Uint8Array(prefix.byteLength + body.byteLength);
  signed.set(prefix);
  signed.set(body, prefix.byteLength);
  return hex(await crypto.subtle.sign('HMAC', key, signed));
}

const worker = {
  async email(message, env) {
    if (message.rawSize > MAX_BYTES) return message.setReject(TOO_LARGE);
    const body = new Uint8Array(await new Response(message.raw).arrayBuffer());
    if (body.byteLength > MAX_BYTES) return message.setReject(TOO_LARGE);
    const recipient = message.to.trim().toLowerCase();
    const timestamp = Math.floor(Date.now() / 1000);
    const response = await fetch(env.PEEK_INBOUND_URL, {
      method: 'POST',
      headers: {
        'content-type': 'message/rfc822',
        'x-peek-recipient': recipient,
        'x-peek-signature': `t=${timestamp},v1=${await sign(env.PEEK_INBOUND_SECRET, timestamp, recipient, body)}`,
      },
      body,
    });
    if (response.status === 413) return message.setReject(TOO_LARGE);
    // Anything else the app refuses is its own trouble, such as a deploy: failing here lets the sender try again.
    if (!response.ok) throw new Error(`Peek answered ${response.status}`);
  },
};

export default worker;

/**
 * What error reports and logs leave out: access values. Everything else stays, the parcel's number,
 * postcodes, phone digits, bodies, queries and addresses included (docs/OBSERVABILITY.md). Shared by
 * the server's reports and the pages'.
 */

export const FILTERED = '[Filtered]';

/** An address of any scheme, as text holds it. */
const ADDRESS = /\b[a-z][a-z\d+.-]*:\/\/[^\s"'`<>\\)]+/gi;

/** The words of a name that say its value is an access value. */
const CREDENTIAL_WORD = /^(?:auth|authorization|bearer|jwt|token|tokens|secret|secrets|session|sessions|sessionid|sid|password|passwd|pwd|passcode|otp|csrf|xsrf|credential|credentials|cookie|cookies|key|keys|apikey|signature|sig|nonce|verifier|dsn|p256dh)$/;

/**
 * Whether a field, header, query parameter or cookie named so holds an access value: a key, a token,
 * a password, a session, a signature or a cookie (`api_key`, `accessToken`, `set-cookie`).
 */
export function credentialName(name: string): boolean {
  return name.replace(/([a-z\d])([A-Z])/g, '$1 $2').toLowerCase().split(/[^a-z\d]+/)
    .some((word) => CREDENTIAL_WORD.test(word));
}

/** Peek's paths whose id is a capability: a shared parcel's link, an invite's preview. */
const CAPABILITY_ID = /^(\/(?:p|i)|\/api\/public\/parcels)\/[^/?#]+/;
/** Peek's paths whose query is a capability: an unsubscribe token, an invite's preview. */
const CAPABILITY_QUERY = /^\/(?:api\/email\/unsubscribe|invite)(?:\/|$)/;

/** Whether every query value on the path is a capability. */
export function capabilityQuery(path: string): boolean {
  return CAPABILITY_QUERY.test(path);
}

/** A query, without its `?`, with each access value filtered, or every value on a path whose query is a capability. */
export function scrubQuery(query: string, path = ''): string {
  const capability = capabilityQuery(path);
  return query.split('&').map((pair) => {
    const separator = pair.indexOf('=');
    if (separator < 0) return pair;
    const name = pair.slice(0, separator);
    let decoded = name;
    try { decoded = decodeURIComponent(name.replace(/\+/g, ' ')); } catch { /* keep it as written */ }
    return capability || credentialName(decoded) ? `${name}=${FILTERED}` : pair;
  }).join('&');
}

/**
 * An address, or a path, as reports keep it: whole, except its user and password, its fragment, where
 * sign-in tokens and invite codes travel, its access values and Peek's capability ids (`/p/:id`).
 */
export function scrubAddress(address: string): string {
  const hash = address.indexOf('#');
  const located = hash < 0 ? address : address.slice(0, hash);
  const origin = /^[a-z][a-z\d+.-]*:\/\/[^/?#]*/i.exec(located)?.[0] ?? '';
  const rest = located.slice(origin.length);
  const question = rest.indexOf('?');
  const path = question < 0 ? rest : rest.slice(0, question);
  return origin.replace(/\/\/[^@/]*@/, '//') + path.replace(CAPABILITY_ID, '$1/:id')
    + (question < 0 ? '' : `?${scrubQuery(rest.slice(question + 1), path)}`) + (hash < 0 ? '' : `#${FILTERED}`);
}

/** A name, then `:` or `=` and its value, as in a header line, JSON or a form. */
const NAMED_VALUE = /(["']?)([A-Za-z][\w.-]{0,60})\1(\s*[:=]\s*)("(?:\\.|[^"\\])*"|'(?:\\.|[^'\\])*'|[^\s,;&<>"'}\]]+)/g;

/** A form field's name and value, as a page's hidden input holds a token. */
const FORM_FIELD = /(\bname\s*=\s*["']([^"']{1,80})["'][^>]*?\bvalue\s*=\s*["'])([^"']*)/gi;

/**
 * Text with its access values filtered: in addresses, after a credential's name, in a form field named
 * after one, a bearer or basic authorization, and Peek's own tokens. Everything else stays.
 */
export function withoutCredentials(text: string): string {
  return text.replace(ADDRESS, scrubAddress)
    .replace(FORM_FIELD, (match, before: string, name: string) => credentialName(name) ? `${before}${FILTERED}` : match)
    .replace(/\b(Bearer|Basic)\s+[A-Za-z\d._~+/-]+=*/gi, `$1 ${FILTERED}`)
    .replace(/\bpeek_(?:at|rt|pat|ac)_[A-Za-z\d_-]+/g, FILTERED)
    .replace(NAMED_VALUE, (match, quote: string, name: string, separator: string, value: string) =>
      !credentialName(name) || value.startsWith(FILTERED.slice(0, -1)) ? match
        : `${quote}${name}${quote}${separator}${value.startsWith('"') ? `"${FILTERED}"` : FILTERED}`);
}

/** A copy where a string or object under a credential's name reads `[Filtered]`, and every other string is scrubbed. */
export function scrubValue<T>(value: T, ancestors = new Set<object>()): T {
  if (typeof value === 'string') return withoutCredentials(value) as T;
  if (!value || typeof value !== 'object') return value;
  if (ancestors.has(value) || ancestors.size > 20) return '[Truncated]' as T;
  ancestors.add(value);
  const copy = Array.isArray(value)
    ? value.map((item: unknown) => scrubValue(item, ancestors))
    : Object.fromEntries(Object.entries(value).map(([key, item]) =>
      [key, credentialName(key) && (typeof item === 'string' || (item && typeof item === 'object')) ? FILTERED
        : scrubValue(item, ancestors)]));
  ancestors.delete(value);
  return copy as T;
}

/** A request's query, in any of the shapes Sentry gives it (text, pairs or an object), scrubbed like an address's. */
export function scrubQueryString(query: unknown, path: string): unknown {
  if (typeof query === 'string') return scrubQuery(query.replace(/^\?/, ''), path);
  const filtered = (name: string) => capabilityQuery(path) || credentialName(name);
  if (Array.isArray(query)) {
    return query.map((pair: unknown) => Array.isArray(pair) && typeof pair[0] === 'string' && filtered(pair[0])
      ? [pair[0], FILTERED] : pair);
  }
  if (query && typeof query === 'object') {
    return Object.fromEntries(Object.entries(query).map(([name, value]) => [name, filtered(name) ? FILTERED : value]));
  }
  return query;
}

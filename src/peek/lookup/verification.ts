import { trackAction, type VerificationReason } from '../../lib/analytics';

/**
 * Browser verification is kept in this tab's session storage, so a reload keeps
 * it until it expires; never in persistent storage or a parcel link.
 */
interface Proof { proof: string; expiresAt: number }
interface Turnstile {
  render(container: HTMLElement, options: Record<string, unknown>): string;
  remove(id: string): void;
}
declare global { interface Window { turnstile?: Turnstile } }

export class LookupVerificationError extends Error {
  /** Why the check failed, as one fixed word for usage counts. */
  readonly reason: VerificationReason;
  constructor(reason: VerificationReason) {
    super('Browser verification could not finish. Please try again.');
    this.reason = reason;
  }
}

/** Turnstile's error code families, a code's first three digits (Cloudflare's client-side error codes). */
const WIDGET_FAMILIES: readonly VerificationReason[] = ['widget-100', 'widget-102', 'widget-103', 'widget-104', 'widget-105',
  'widget-106', 'widget-110', 'widget-120', 'widget-200', 'widget-300', 'widget-400', 'widget-600'];

/** The widget's error code as its family; its own timeouts (1106xx) as a timeout. */
function widgetFailure(code: unknown): VerificationReason {
  const text = String(code ?? '');
  if (/^1106\d\d$/.test(text)) return 'timeout';
  const family = /^\d{6}$/.test(text) ? WIDGET_FAMILIES.find((word) => word === `widget-${text.slice(0, 3)}`) : undefined;
  return family ?? 'widget';
}

const PROOF_KEY = 'sdt.lookup-proof.v1';

/** The tab's proof; undefined until session storage has been read. */
let proof: Proof | null | undefined;
let verify: ((refresh?: boolean) => Promise<string | null>) | null = null;
let script: Promise<void> | null = null;

function keep(next: Proof | null): void {
  proof = next;
  try {
    if (next) sessionStorage.setItem(PROOF_KEY, JSON.stringify(next));
    else sessionStorage.removeItem(PROOF_KEY);
  } catch {
    // Without session storage the proof lasts as long as the page.
  }
}

function current(): Proof | null {
  if (proof === undefined) {
    proof = null;
    try {
      const saved: unknown = JSON.parse(sessionStorage.getItem(PROOF_KEY) ?? 'null');
      const { proof: value, expiresAt } = (saved ?? {}) as Partial<Proof>;
      if (typeof value === 'string' && typeof expiresAt === 'number') proof = { proof: value, expiresAt };
    } catch {
      // An unreadable proof is no proof: verification starts again.
    }
  }
  if (proof && proof.expiresAt <= Date.now()) keep(null);
  return proof;
}

/**
 * The proof's expiry on this device's clock. The server states it on its own clock and its response's `Date` says what
 * time that clock read, so a device set minutes or hours wrong still keeps the proof as long as the server takes it.
 * `Date` is to the second, so a second comes off. Without a readable `Date` the server's expiry stands as given.
 */
function localExpiry(expiresAt: number, served: string | null): number {
  const serverNow = served ? Date.parse(served) : Number.NaN;
  return Number.isFinite(serverNow) ? Date.now() + expiresAt - serverNow - 1_000 : expiresAt;
}

export function lookupProof(): string | null {
  const saved = current();
  return saved && saved.expiresAt > Date.now() + 5_000 ? saved.proof : null;
}

/** The server refused this proof: it is not sent again, from this page or after a reload. */
export function forgetLookupProof(rejected: string | null): void {
  if (rejected !== null && current()?.proof === rejected) keep(null);
}

/** Whether this page runs verification: a lookup form has mounted it. */
export function canVerifyLookup(): boolean {
  return verify !== null;
}

export async function getLookupProof(signal?: AbortSignal, refresh = false): Promise<string | null> {
  signal?.throwIfAborted();
  if (lookupProof()) return lookupProof();
  if (!verify) throw new LookupVerificationError('other');
  const pending = verify(refresh);
  if (!signal) return pending;
  return new Promise((resolve, reject) => {
    const abort = () => reject(signal.reason);
    signal.addEventListener('abort', abort, { once: true });
    pending.then(resolve, reject).finally(() => signal.removeEventListener('abort', abort));
  });
}

function loadScript(): Promise<void> {
  if (window.turnstile) return Promise.resolve();
  if (script) return script;
  script = new Promise<void>((resolve, reject) => {
    const element = document.createElement('script');
    element.src = 'https://challenges.cloudflare.com/turnstile/v0/api.js?render=explicit';
    element.async = true;
    const nonce = document.querySelector<HTMLScriptElement>('script[nonce]')?.nonce;
    if (nonce) element.nonce = nonce;
    const timeout = setTimeout(() => finish(false), 12_000);
    function finish(ok: boolean) {
      clearTimeout(timeout);
      element.onload = element.onerror = null;
      if (ok && window.turnstile) resolve();
      else { element.remove(); reject(new LookupVerificationError('script')); }
    }
    element.onload = () => finish(true);
    element.onerror = () => finish(false);
    document.head.append(element);
  }).catch((error) => { script = null; throw error; });
  return script;
}

/** Failures of the widget itself, after which the next check is shown (`mountLookupVerification`). */
const WIDGET_FAILED = (reason: VerificationReason) => reason === 'timeout' || reason.startsWith('widget');

/**
 * The widget stays in the form; Cloudflare shows it only when interaction is needed. Once the widget has failed or
 * timed out on this page, the next check is shown from the start, with Cloudflare's box to tick where it asks for one,
 * and counted (`door-verification-shown`). A token that comes after a check gave up, from Cloudflare's own retry or a
 * box ticked late, still gives the tab its proof for the next try.
 */
export function mountLookupVerification(container: HTMLElement, language: string) {
  let disposed = false;
  let widget: string | null = null;
  let pending: Promise<string | null> | null = null;
  let cancel: (() => void) | null = null;
  /** The server verifies no one: asked once per page. */
  let unprotected = false;
  /** The widget failed on this page: the next check is shown. */
  let shown = false;

  async function start(): Promise<string | null> {
    if (lookupProof()) return lookupProof();
    if (unprotected) return null;
    let configuration: { siteKey?: unknown };
    try {
      const configResponse = await fetch('/api/public/verification', {
        credentials: 'omit', cache: 'no-store', referrerPolicy: 'no-referrer', signal: AbortSignal.timeout(8_000),
      });
      if (!configResponse.ok) throw new Error('No verification settings');
      configuration = (await configResponse.json() ?? {}) as { siteKey?: unknown };
    } catch {
      throw new LookupVerificationError('setup');
    }
    if (configuration.siteKey === null) { unprotected = true; return null; }
    if (typeof configuration.siteKey !== 'string' || !configuration.siteKey) throw new LookupVerificationError('setup');
    await loadScript();
    if (disposed || !window.turnstile) throw new LookupVerificationError(disposed ? 'other' : 'script');
    if (widget !== null) window.turnstile.remove(widget);
    const visible = shown;
    return new Promise<string>((resolve, reject) => {
      let finished = false;
      let exchanging = false;
      const timer = setTimeout(() => finish('timeout'), 60_000);
      function finish(result: Proof | VerificationReason) {
        if (finished) return;
        finished = true;
        clearTimeout(timer);
        cancel = null;
        const passed = typeof result === 'object' && !disposed;
        if (visible && result !== 'other') trackAction('door-verification-shown', passed ? 'success' : 'error', undefined,
          typeof result === 'string' ? result : undefined);
        if (typeof result === 'string' && WIDGET_FAILED(result)) shown = true;
        if (passed) { keep(result); resolve(result.proof); }
        else reject(new LookupVerificationError(typeof result === 'string' ? result : 'other'));
      }
      cancel = () => finish('other');
      widget = window.turnstile!.render(container, {
        sitekey: configuration.siteKey,
        action: 'parcel_lookup',
        appearance: visible ? 'always' : 'interaction-only',
        theme: 'auto',
        language,
        'response-field': false,
        callback: (token: string) => {
          if (exchanging || disposed) return;
          exchanging = true;
          void fetch('/api/public/verification', {
            method: 'POST', credentials: 'omit', cache: 'no-store', referrerPolicy: 'no-referrer',
            headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ token }),
            signal: AbortSignal.timeout(12_000),
          }).then(async (response) => {
            if (!response.ok) throw new LookupVerificationError('refused');
            const result = (await response.json().catch(() => null) ?? {}) as Partial<Proof>;
            if (typeof result.proof !== 'string' || typeof result.expiresAt !== 'number') throw new LookupVerificationError('refused');
            const expiresAt = localExpiry(result.expiresAt, response.headers.get('Date'));
            if (expiresAt <= Date.now()) throw new LookupVerificationError('refused');
            // After the check gave up, the proof waits in the tab for the reader's next try.
            if (finished) { if (!disposed) keep({ proof: result.proof, expiresAt }); }
            else finish({ proof: result.proof, expiresAt });
          }).catch((error: unknown) => finish(error instanceof LookupVerificationError ? error.reason : 'unanswered'))
            .finally(() => { exchanging = false; });
        },
        'error-callback': (code: unknown) => { finish(widgetFailure(code)); return true; },
        'expired-callback': () => finish('timeout'),
        'timeout-callback': () => finish('timeout'),
      });
    });
  }

  function run(refresh = false) {
    if (refresh) unprotected = false;
    if (!pending) pending = start().catch((error: unknown) => {
      throw error instanceof LookupVerificationError ? error : new LookupVerificationError('other');
    }).finally(() => { pending = null; });
    return pending;
  }
  verify = run;
  return {
    warm: () => { void run().catch(() => undefined); },
    dispose: () => {
      disposed = true;
      cancel?.();
      if (widget !== null) window.turnstile?.remove(widget);
      if (verify === run) verify = null;
    },
  };
}

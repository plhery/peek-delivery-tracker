'use client';

import { useEffect, useMemo, useRef, useState, type ReactNode } from 'react';
import { AuthProvider, useAuth } from '../auth/AuthContext';
import { siteAuthConfig } from '../auth/siteAuth';
import type { ApiAssistantScope } from '../generated/apiContract';
import { I18nProvider, LanguageControl, type Locale, type MessageKey, type Messages, useI18n } from '../i18n';
import { ApiAuthenticationError, ApiRecentSignInError } from '../lib/apiClient';
import { AppearanceProvider } from '../lib/appearance';
import { answerAssistantConsent, AssistantRequestError } from '../lib/assistants';
import { rememberPendingConsent } from '../lib/pendingConsent';
import { dropSignInAgain, noteSignInAgain, takeSignInAgain } from '../lib/signInAgain';
import { useTabTitle } from '../lib/tabTitle';
import type { SignInMethods } from '../peek/session';
import { PeekLockup } from './PeekMark';
import { SignInAgain } from './SignInAgain';
import { SignInScreen } from './SignInScreen';
import './EmailOff.css';
import './AssistantConsent.css';

/** What the page shows about an assistant's request: who asks, where the answer goes, and for what. */
export interface ConsentRequest {
  clientName: string;
  /** Where the name comes from, for a client that publishes its details; null for one that registered them. */
  clientHost: string | null;
  clientUri: string | null;
  redirectHost: string;
  /** The answer goes back to an app on this device, not to a website. */
  local: boolean;
  scopes: ApiAssistantScope[];
}

const SCOPE_WORDS: Record<ApiAssistantScope, MessageKey> = {
  'parcels:read': 'assistants.consent.read',
  'parcels:write': 'assistants.consent.write',
};

/**
 * Where an assistant (Claude, ChatGPT, or another MCP client) sends someone to connect Peek, at
 * `/oauth/authorize?…`. They sign in here if they need to, see who asks and for what, and allow or refuse. Nothing is
 * granted until Allow is pressed.
 */
export function AssistantConsentScreen({ initialLocale, initialMessages, request, query, busy = false }: {
  initialLocale?: Locale;
  initialMessages?: Messages;
  /** Null when Peek can't take the request: an unknown client or answer address, or a request it doesn't support. */
  request: ConsentRequest | null;
  /** The request as the assistant sent it, with the answer address the page shows, which the answer repeats. */
  query: string;
  /** The page was opened too often from this network: it asks to try again shortly. */
  busy?: boolean;
}) {
  return <I18nProvider initialLocale={initialLocale} initialMessages={initialMessages}>
    <AppearanceProvider>
      <AuthProvider config={siteAuthConfig}><Consent request={request} query={query} busy={busy} /></AuthProvider>
    </AppearanceProvider>
  </I18nProvider>;
}

function Card({ children }: { children: ReactNode }) {
  return <main className="auth-screen email-off assistant-consent">
    <div className="feedback-language"><LanguageControl /></div>
    <section className="auth-card" aria-labelledby="assistant-consent-title">
      <p className="auth-card__eyebrow"><PeekLockup /></p>
      {children}
    </section>
  </main>;
}

type Outcome = 'allowed' | 'denied';

function Consent({ request, query, busy }: { request: ConsentRequest | null; query: string; busy: boolean }) {
  const { t } = useI18n();
  const auth = useAuth();
  const [kept, setKept] = useState<ApiAssistantScope[]>(() => request?.scopes ?? []);
  const [working, setWorking] = useState<Outcome | null>(null);
  const [outcome, setOutcome] = useState<Outcome | null>(null);
  const [problem, setProblem] = useState<'invalid' | 'closed' | 'full' | 'failed' | null>(null);
  // A session Auth no longer confirms is signed out, and the person signs in again here.
  const [again, setAgain] = useState(false);
  // Allowing needs a recent sign-in: the account that signed in earlier signs in again in place, then allows.
  const [signingInAgain, setSigningInAgain] = useState<string | null>(null);
  const resumed = useRef(false);
  const userId = auth.user?.id;
  const apiAuth = useMemo(() => userId ? { userId, getAccessToken: auth.getAccessToken, signal: auth.signal } : undefined,
    [userId, auth.getAccessToken, auth.signal]);
  useTabTitle(`${t('app.title')} — ${t('assistants.consent.tab')}`);

  async function answer(approve: boolean, scopes = kept) {
    if (working || !apiAuth || (approve && !scopes.length)) return;
    const next: Outcome = approve ? 'allowed' : 'denied';
    setWorking(next);
    setProblem(null);
    try {
      const redirect = await answerAssistantConsent(apiAuth, { query, approve, scopes });
      // An app's own address may open it without leaving the page, which then says to go back to it.
      setOutcome(next);
      window.location.assign(redirect);
    } catch (reason) {
      setWorking(null);
      if (reason instanceof ApiRecentSignInError) {
        setSigningInAgain(apiAuth.userId);
        return;
      }
      if (reason instanceof ApiAuthenticationError) {
        // The same request shows again once signed in, here or after a provider's round trip.
        setAgain(true);
        void auth.signOut();
        return;
      }
      const status = reason instanceof AssistantRequestError ? reason.status : 0;
      setProblem(status === 400 ? 'invalid' : status === 403 ? 'closed' : status === 409 ? 'full' : 'failed');
    }
  }

  function resume(scopes: ApiAssistantScope[], allow: boolean) {
    setKept(scopes);
    if (allow) void answer(true, scopes);
  }

  // Back from a provider after signing in again: the request shows with the access kept, and the same account allows
  // it at once. Another account sees the request for itself, and answers it or not. Once, with the first account.
  useEffect(() => {
    if (resumed.current || !apiAuth || !request) return;
    resumed.current = true;
    const note = takeSignInAgain(['assistant-consent']);
    // eslint-disable-next-line react-hooks/set-state-in-effect -- session storage, which only the live page can read
    if (note?.query === query) resume(request.scopes.filter((scope) => note.scopes.includes(scope)), note.userId === apiAuth.userId);
  });

  if (busy) {
    return <Card>
      <h1 id="assistant-consent-title">{t('assistants.consent.tab')}</h1>
      <p className="auth-card__intro" role="alert">{t('assistants.consent.failed')}</p>
    </Card>;
  }

  if (!request || problem === 'invalid') {
    return <Card>
      <h1 id="assistant-consent-title">{t('assistants.consent.invalidTitle')}</h1>
      <p className="auth-card__intro">{t('assistants.consent.invalidBody')}</p>
      {/* The deliveries are another document, with a sign-in of their own. */}
      {/* eslint-disable-next-line @next/next/no-html-link-for-pages */}
      <a className="button button--primary" href="/">{t('email.off.open')}</a>
    </Card>;
  }
  const name = request.clientName;

  if (auth.status === 'loading') {
    return <Card><h1 id="assistant-consent-title">{t('assistants.consent.title', { name })}</h1>
      <p className="auth-card__intro" role="status">{t('auth.signingIn')}</p></Card>;
  }

  if (auth.status !== 'authenticated' || !apiAuth) {
    // A provider returns to `/` alone: the request is noted so that the deliveries come back here.
    const noted = (signIn: () => Promise<void>) => () => { rememberPendingConsent(); return signIn(); };
    return <main className="auth-screen assistant-consent">
      <div className="feedback-language"><LanguageControl /></div>
      <SignInScreen card configured={auth.status !== 'unconfigured'} title={t(again ? 'assistants.consent.signInAgain' : 'assistants.consent.signIn', { name })}
        subtitle={t('assistants.consent.signInBody', { name })}
        googleEnabled={auth.googleEnabled} appleEnabled={auth.appleEnabled} emailOtpEnabled={auth.emailOtpEnabled} oidcName={auth.oidcName}
        signInWithGoogle={noted(auth.signInWithGoogle)} signInWithApple={noted(auth.signInWithApple)} signInWithOidc={noted(auth.signInWithOidc)}
        sendCode={auth.sendCode} verifyCode={auth.verifyCode} />
    </main>;
  }

  if (signingInAgain === apiAuth.userId) {
    const signInWith: SignInMethods = {
      configured: true, googleEnabled: auth.googleEnabled, appleEnabled: auth.appleEnabled, emailOtpEnabled: auth.emailOtpEnabled, oidcName: auth.oidcName,
      signInWithGoogle: auth.signInWithGoogle, signInWithApple: auth.signInWithApple, signInWithOidc: auth.signInWithOidc, sendCode: auth.sendCode, verifyCode: auth.verifyCode,
    };
    // A provider returns to `/` alone: the request is noted so that the deliveries come back here, and allow it then.
    const noted = () => {
      rememberPendingConsent();
      noteSignInAgain({ action: 'assistant-consent', userId: apiAuth.userId, query, scopes: kept });
    };
    return <main className="auth-screen assistant-consent">
      <div className="feedback-language"><LanguageControl /></div>
      <SignInAgain methods={signInWith} email={auth.user?.email} title={t('assistants.consent.signInAgain', { name })}
        subtitle={t('assistants.consent.signInBody', { name })} onLeave={noted} onFailed={() => dropSignInAgain(['assistant-consent'])}
        onSignedIn={() => {
          setSigningInAgain(null);
          // Another account signed in: the request shows for it, and waits for its answer.
          if (!apiAuth.signal?.aborted) void answer(true);
        }}
        onCancel={() => setSigningInAgain(null)} />
    </main>;
  }

  if (outcome) {
    return <Card>
      <div aria-live="polite">
        <h1 id="assistant-consent-title">{t(outcome === 'allowed' ? 'assistants.consent.allowedTitle' : 'assistants.consent.deniedTitle', { name })}</h1>
        <p className="auth-card__intro">{t('assistants.consent.back', { name })}</p>
      </div>
    </Card>;
  }

  const toggle = (scope: ApiAssistantScope, on: boolean) => setKept((current) =>
    request.scopes.filter((asked) => asked === scope ? on : current.includes(asked)));
  const place = `${t('settings.title')} › ${t('assistants.row')}`;
  return <Card>
    <h1 id="assistant-consent-title">{t('assistants.consent.title', { name })}</h1>
    <p className="auth-card__intro">{t('assistants.consent.body', { name, email: auth.user?.email ?? '' })}</p>
    <p className={`assistant-consent__who${request.clientHost ? '' : ' assistant-consent__where--local'}`}>
      {request.clientHost ? t('assistants.consent.from', { host: request.clientHost }) : t('assistants.consent.unverified', { name })}
    </p>
    <fieldset className="assistant-consent__scopes">
      <legend>{t('assistants.consent.can', { name })}</legend>
      {request.scopes.map((scope) => <label key={scope} className="assistant-consent__scope">
        <input type="checkbox" checked={kept.includes(scope)} disabled={Boolean(working)}
          onChange={(event) => toggle(scope, event.target.checked)} />
        <span>{t(SCOPE_WORDS[scope])}</span>
      </label>)}
    </fieldset>
    <p className={`assistant-consent__where${request.local ? ' assistant-consent__where--local' : ''}`}>
      {t(request.local ? 'assistants.consent.local' : 'assistants.consent.returns', { host: request.redirectHost })}
    </p>
    <p className="assistant-consent__later">{t('assistants.consent.later', { place })}</p>
    {problem && <p className="sheet__error" role="alert">{t(problem === 'closed' ? 'assistants.consent.closed'
      : problem === 'full' ? 'assistants.consent.full' : 'assistants.consent.failed')}</p>}
    <div className="assistant-consent__actions">
      <button type="button" className="button button--secondary" disabled={Boolean(working)} aria-busy={working === 'denied' || undefined}
        onClick={() => void answer(false)}>{t('assistants.consent.deny')}</button>
      <button type="button" className="button button--primary" disabled={Boolean(working) || !kept.length} aria-busy={working === 'allowed' || undefined}
        onClick={() => void answer(true)}>{t(working === 'allowed' ? 'assistants.consent.working' : 'assistants.consent.allow')}</button>
    </div>
    <button type="button" className="auth-flow__text-button assistant-consent__switch" disabled={Boolean(working)}
      onClick={() => { setAgain(false); void auth.signOut(); }}>{t('assistants.consent.switch')}</button>
  </Card>;
}

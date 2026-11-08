import { userErrorKey, userErrorMessage } from '../lib/userMessages';
import { useState, type FormEvent, type ReactNode } from 'react';
import { useI18n } from '../i18n';
import Image from 'next/image';

export function SignInScreen({
  configured,
  card = false,
  googleEnabled = false,
  appleEnabled = false,
  emailOtpEnabled = true,
  oidcName,
  signInWithGoogle,
  signInWithApple,
  signInWithOidc,
  sendCode,
  verifyCode,
  title,
  subtitle,
  showConfigurationHelp = true,
  initialEmail = '',
  codeAction,
}: {
  card?: boolean;
  showConfigurationHelp?: boolean;
  title?: ReactNode;
  subtitle?: string;
  /** The address the email field starts with, as when the account signed in asks for a sign-in again. */
  initialEmail?: string;
  /** What the button that sends the code says, where it leads elsewhere than to the parcels. */
  codeAction?: string;
  configured: boolean;
  googleEnabled?: boolean;
  appleEnabled?: boolean;
  emailOtpEnabled?: boolean;
  oidcName?: string | null;
  signInWithGoogle?: () => Promise<void>;
  signInWithApple?: () => Promise<void>;
  signInWithOidc?: () => Promise<void>;
  sendCode: (email: string) => Promise<void>;
  verifyCode: (email: string, code: string) => Promise<void>;
}) {
  const { t } = useI18n();
  const [email, setEmail] = useState(initialEmail);
  const [code, setCode] = useState('');
  const [codeSent, setCodeSent] = useState(false);
  const socialEnabled = !!((googleEnabled && signInWithGoogle) || (appleEnabled && signInWithApple) || (oidcName && signInWithOidc));
  const [emailExpanded, setEmailExpanded] = useState(!socialEnabled);
  const [working, setWorking] = useState<'google' | 'apple' | 'oidc' | 'email' | 'code' | null>(null);
  const [error, setError] = useState<string | null>(null);
  const emailVisible = emailOtpEnabled && (!socialEnabled || emailExpanded);

  async function startSocialSignIn(provider: 'google' | 'apple' | 'oidc') {
    const signIn = provider === 'oidc' ? signInWithOidc : provider === 'apple' ? signInWithApple : signInWithGoogle;
    if (working || !signIn) return;
    setWorking(provider);
    setError(null);
    try {
      await signIn();
    } catch (reason) {
      setError(provider === 'oidc'
        ? t(userErrorKey(reason) ?? 'auth.oidcFailed', { provider: oidcName ?? '' })
        : userErrorMessage(reason, t, provider === 'apple' ? 'auth.appleFailed' : 'auth.googleFailed'));
      setWorking(null);
    }
  }

  async function requestCode(event: FormEvent) {
    event.preventDefault();
    if (working) return;
    setWorking('email');
    setError(null);
    try {
      await sendCode(email.trim().toLowerCase());
      setCodeSent(true);
    } catch (reason) {
      setError(userErrorMessage(reason, t, 'auth.sendFailed'));
    } finally {
      setWorking(null);
    }
  }

  async function submitCode(event: FormEvent) {
    event.preventDefault();
    if (working) return;
    setWorking('code');
    setError(null);
    try {
      await verifyCode(email.trim().toLowerCase(), code.replace(/\s/g, ''));
    } catch (reason) {
      setError(userErrorMessage(reason, t, 'auth.verifyFailed'));
    } finally {
      setWorking(null);
    }
  }

  return (
        <section className={`auth-flow${card ? ' auth-flow--card' : ''}`} aria-labelledby="sign-in-title">
          <div className="auth-flow__heading">
            <h1 id="sign-in-title" tabIndex={-1}>{title ?? t('arrival.signInTitle')}</h1>
            <p>{subtitle ?? t('arrival.signInSubtitle')}</p>
          </div>
        {!configured ? (
          <div className="auth-flow__configuration" role="alert">
            <strong>{t('auth.configTitle')}</strong>
            {showConfigurationHelp && <span>{t('native.configurationHelp')}</span>}
          </div>
        ) : codeSent && emailOtpEnabled ? (
          <form className="auth-flow__form auth-flow__form--code" onSubmit={(event) => void submitCode(event)}>
            <p className="auth-flow__intro">
              {t('auth.codeIntro', { email })}
            </p>
            <label htmlFor="sign-in-code">{t('auth.code')}</label>
            <input
              id="sign-in-code"
              type="text"
              inputMode="numeric"
              autoComplete="one-time-code"
              pattern="[0-9]{6}"
              minLength={6}
              maxLength={6}
              value={code}
              onChange={(event) => setCode(event.target.value.replace(/\D/g, ''))}
              autoFocus
              required
            />
            {error && <p className="auth-flow__error" role="alert">{error}</p>}
            <button className="button button--primary" type="submit" disabled={Boolean(working)}>
              {working === 'code' ? t('auth.signingIn') : codeAction ?? t('auth.openBox')}
            </button>
            <button
              className="auth-flow__text-button"
              type="button"
              onClick={() => {
                setCodeSent(false);
                setCode('');
                setError(null);
              }}
              disabled={Boolean(working)}
            >
              {t('auth.differentEmail')}
            </button>
          </form>
        ) : (
          <div className="auth-flow__methods">
            {oidcName && signInWithOidc && (
              <button
                className="button button--primary"
                type="button"
                disabled={Boolean(working)}
                aria-busy={working === 'oidc'}
                onClick={() => void startSocialSignIn('oidc')}
              >
                {t(working === 'oidc' ? 'auth.oidcOpening' : 'auth.oidc', { provider: oidcName })}
              </button>
            )}
            {googleEnabled && signInWithGoogle && (
              <button className="button auth-flow__google" type="button" disabled={Boolean(working)} onClick={() => void startSocialSignIn('google')}>
                <Image src="/icons/google-g.svg" alt="" width={22} height={22} unoptimized />
                {working === 'google' ? t('auth.googleOpening') : t('auth.google')}
              </button>
            )}
            {appleEnabled && signInWithApple && (
              <button className="button auth-flow__apple" type="button" disabled={Boolean(working)} onClick={() => void startSocialSignIn('apple')}>
                {/* Apple's logo as its own web button draws it. */}
                <svg aria-hidden="true" viewBox="20.5 16 15 19">
                  <path d="M28.2226562,20.3846154 C29.0546875,20.3846154 30.0976562,19.8048315 30.71875,19.0317864 C31.28125,18.3312142 31.6914062,17.352829 31.6914062,16.3744437 C31.6914062,16.2415766 31.6796875,16.1087095 31.65625,16 C30.7304687,16.0362365 29.6171875,16.640178 28.9492187,17.4494596 C28.421875,18.06548 27.9414062,19.0317864 27.9414062,20.0222505 C27.9414062,20.1671964 27.9648438,20.3121424 27.9765625,20.3604577 C28.0351562,20.3725366 28.1289062,20.3846154 28.2226562,20.3846154 Z M25.2929688,35 C26.4296875,35 26.9335938,34.214876 28.3515625,34.214876 C29.7929688,34.214876 30.109375,34.9758423 31.375,34.9758423 C32.6171875,34.9758423 33.4492188,33.792117 34.234375,32.6325493 C35.1132812,31.3038779 35.4765625,29.9993643 35.5,29.9389701 C35.4179688,29.9148125 33.0390625,28.9122695 33.0390625,26.0979021 C33.0390625,23.6579784 34.9140625,22.5588048 35.0195312,22.474253 C33.7773438,20.6382708 31.890625,20.5899555 31.375,20.5899555 C29.9804688,20.5899555 28.84375,21.4596313 28.1289062,21.4596313 C27.3554688,21.4596313 26.3359375,20.6382708 25.1289062,20.6382708 C22.8320312,20.6382708 20.5,22.5950413 20.5,26.2911634 C20.5,28.5861411 21.3671875,31.013986 22.4335938,32.5842339 C23.3476562,33.9129053 24.1445312,35 25.2929688,35 Z" />
                </svg>
                {working === 'apple' ? t('auth.appleOpening') : t('auth.apple')}
              </button>
            )}
            {socialEnabled && emailOtpEnabled && !emailVisible && (
              <button
                className="button button--secondary auth-flow__email-option"
                type="button"
                disabled={Boolean(working)}
                onClick={() => {
                  setError(null);
                  setEmailExpanded(true);
                }}
              >
                <svg aria-hidden="true" viewBox="0 0 24 24">
                  <rect x="3" y="5" width="18" height="14" rx="2" />
                  <path d="m4 7 8 6 8-6" />
                </svg>
                {t('auth.emailOption')}
              </button>
            )}
            {socialEnabled && emailVisible && (
              <div className="auth-flow__divider"><span>{t('auth.or')}</span></div>
            )}
            {emailVisible && (
              <form className="auth-flow__form auth-flow__form--email" onSubmit={(event) => void requestCode(event)}>
                <p className="auth-flow__intro">
                  {t(card ? 'auth.emailIntroShort' : 'auth.emailIntro')}
                </p>
                <label htmlFor="sign-in-email">{t('auth.email')}</label>
                <input
                  id="sign-in-email"
                  type="email"
                  inputMode="email"
                  autoComplete="email"
                  placeholder="you@example.com"
                  value={email}
                  onChange={(event) => setEmail(event.target.value)}
                  autoFocus
                  required
                />
                <button className="button button--primary" type="submit" disabled={Boolean(working)}>
                  {working === 'email' ? t('auth.sending') : t('auth.send')}
                </button>
              </form>
            )}
            {error && <p className="auth-flow__error" role="alert">{error}</p>}
          </div>
        )}
          <div className="auth-flow__privacy">
            <svg aria-hidden="true" viewBox="0 0 24 24">
              <rect x="5" y="10" width="14" height="11" rx="2" />
              <path d="M8 10V7a4 4 0 0 1 8 0v3" />
            </svg>
            <p>
              {t(card ? 'auth.privacyShort' : 'auth.privacy')}{' '}
              <a href="/privacy.html">{t(card ? 'auth.privacyLink' : 'auth.readPrivacy')}</a>
            </p>
          </div>
        </section>
  );
}

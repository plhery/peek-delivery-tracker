import { useLayoutEffect, useRef } from 'react';
import { useI18n } from '../i18n';
import type { SignInMethods } from '../peek/session';
import { SignInScreen } from './SignInScreen';

/**
 * A sign-in asked for again in place, with the ways this site signs in, while the account stays signed in: what
 * outlasts the session needs a recent one. `onLeave` notes what to resume before a provider takes the page away, and
 * `onVerify` before a code or a password completes the sign-in, since one for another account starts that account's
 * deliveries afresh; `onFailed` takes the note back. `onSignedIn` follows a code or a password that signed in here,
 * whichever the account: the caller tells.
 */
export function SignInAgain({ methods, email, title, subtitle, onLeave, onVerify, onFailed, onSignedIn, onCancel }: {
  methods: SignInMethods;
  /** The account's address, which the email field starts with. */
  email?: string;
  title: string;
  subtitle: string;
  onLeave?: () => void;
  onVerify?: () => void;
  onFailed?: () => void;
  onSignedIn: () => void;
  onCancel: () => void;
}) {
  const { t } = useI18n();
  const attempt = useRef(0);
  useLayoutEffect(() => () => { attempt.current++; }, []);
  const leaving = (signIn: (() => Promise<void>) | undefined) => signIn && (async () => {
    onLeave?.();
    try { await signIn(); } catch (error) { onFailed?.(); throw error; }
  });
  const signingIn = <Args extends unknown[]>(signIn: (...args: Args) => Promise<void>) => async (...args: Args) => {
    const current = ++attempt.current;
    onVerify?.();
    try { await signIn(...args); } catch (error) { onFailed?.(); throw error; }
    if (current === attempt.current) onSignedIn();
  };
  return <div className="sign-in-again">
    <SignInScreen {...methods} card title={title} subtitle={subtitle} initialEmail={email} codeAction={t('app.signInAgain')}
      signInWithGoogle={leaving(methods.signInWithGoogle)} signInWithApple={leaving(methods.signInWithApple)}
      signInWithOidc={leaving(methods.signInWithOidc)} verifyCode={signingIn(methods.verifyCode)}
      signInWithPassword={methods.signInWithPassword && signingIn(methods.signInWithPassword)} />
    <button type="button" className="auth-flow__text-button sign-in-again__cancel" onClick={() => { attempt.current++; onCancel(); }}>{t('common.cancel')}</button>
  </div>;
}

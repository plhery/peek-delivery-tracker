import { useRef } from 'react';
import { createPortal } from 'react-dom';
import { Icon } from '../components/Icon';
import { SignInScreen } from '../components/SignInScreen';
import { useI18n, type MessageKey } from '../i18n';
import { useSheetDialog } from '../lib/modal';
import type { SignInMethods } from './session';
import './Unlock.css';

/** What signing in unlocks, as the sheet says it. */
export type UnlockReason = 'several' | 'daily';

const REASONS: Record<UnlockReason, MessageKey> = { several: 'unlock.several', daily: 'unlock.daily' };

/**
 * "Sign in to unlock this": what a visitor can't do without an account, asked
 * on the page where they wanted it rather than on a sign-in screen of its own.
 * `before` notes what they were doing just before a sign-in leaves the page or
 * completes, so it carries on once they are signed in; `onClose` takes it back.
 */
export function UnlockSheet({ reason, methods, before, onClose }: {
  reason: UnlockReason;
  methods: SignInMethods;
  before?: () => void;
  onClose: () => void;
}) {
  const { t } = useI18n();
  const close = useRef<HTMLButtonElement>(null);
  const [dialog, dismiss] = useSheetDialog<HTMLDivElement>(true, onClose, close);
  const what = `${t(REASONS[reason])} ${t('unlock.comes')}`;
  const noted = <Arguments extends unknown[]>(signIn: ((...values: Arguments) => Promise<void>) | undefined) => signIn && ((...values: Arguments) => {
    before?.();
    return signIn(...values);
  });

  return createPortal(<div className="sheet-backdrop" onClick={dismiss}>
    <div ref={dialog} className="sheet peek-unlock" role="dialog" aria-modal="true" aria-labelledby="peek-unlock-title" tabIndex={-1} onClick={(event) => event.stopPropagation()}>
      <div className="sheet__grabber" aria-hidden="true" />
      <div className="sheet__heading">
        <h2 className="sheet__title" id="peek-unlock-title">{t('unlock.title')}</h2>
        <button ref={close} className="sheet__close" type="button" onClick={dismiss} aria-label={t('common.close')}><Icon name="close" /></button>
      </div>
      <p className="peek-unlock__what">{what}</p>
      <SignInScreen {...methods} card title={t('unlock.title')} subtitle={what}
        signInWithGoogle={noted(methods.signInWithGoogle)} signInWithApple={noted(methods.signInWithApple)} signInWithOidc={noted(methods.signInWithOidc)} verifyCode={noted(methods.verifyCode)!} />
      <button type="button" className="peek-unlock__later" onClick={dismiss}>{t('unlock.later')}</button>
    </div>
  </div>, document.body);
}

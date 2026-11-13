import { trackAction, trackOverlay } from '../lib/analytics';
import { userErrorMessage } from '../lib/userMessages';
import { useEffect, useRef, useState } from 'react';
import { createPortal } from 'react-dom';
import { LanguageControl, useI18n } from '../i18n';
import { useAppearance, type Appearance } from '../lib/appearance';
import { LANDING_PATH } from '../lib/experience';
import { useSheetDialog } from '../lib/modal';
import { ApiRecentSignInError, type ApiAuth } from '../lib/apiClient';
import { dropSignInAgain, noteSignInAgain, takeSignInAgain } from '../lib/signInAgain';
import { followInPlace } from '../peek/landing/links';
import { SwitchRow } from '../peek/parcel/Sheet';
import type { SignInMethods } from '../peek/session';
import { AddByEmailPage, useAddByEmail } from './AddByEmail';
import { AssistantsPage, useAssistants, type TokenDraft } from './Assistants';
import { Icon } from './Icon';
import { NotificationControl } from './NotificationControl';
import { SignInAgain } from './SignInAgain';
import './Settings.css';

type AccountAction = 'export' | 'delete' | 'sign-out' | 'reset-demo';
type Page = 'home' | 'account' | 'inbox' | 'assistants';

/**
 * Settings. A token or the account's deletion needs a recent sign-in: with `signIn`, Settings ask for one in place and
 * go on, and after a provider's round trip they open again where the person was. Another account signing in instead
 * finds Settings open there with a word that nothing was done.
 */
export function AccountMenu({ email, onExport, onDelete, onSignOut, onExitDemo, onResetDemo, onOpenLanding, apiAuth, signIn }: {
  email?: string;
  onExport?: () => Promise<void>;
  onDelete?: (confirmation: string) => Promise<void>;
  onSignOut?: () => Promise<void>;
  onExitDemo?: () => void;
  onResetDemo?: () => Promise<void>;
  /** Shows the landing, which someone signed in no longer meets at `/`. */
  onOpenLanding?: () => void;
  apiAuth?: ApiAuth;
  signIn?: SignInMethods;
}) {
  const { t, use24HourTime, setUse24HourTime } = useI18n();
  const [appearance, setAppearance] = useAppearance();
  const initial = email?.trim().charAt(0).toUpperCase();
  const [open, setOpen] = useState(false);
  const [page, setPage] = useState<Page>('home');
  const [addByEmail, setAddByEmail] = useAddByEmail(apiAuth, open);
  const [inboxBusy, setInboxBusy] = useState(false);
  const [assistants, setAssistants] = useAssistants(apiAuth, open);
  const [assistantsBusy, setAssistantsBusy] = useState(false);
  useEffect(() => { if (open) return trackOverlay('account'); }, [open]);
  const [working, setWorking] = useState<AccountAction | null>(null);
  const [confirmingDelete, setConfirmingDelete] = useState(false);
  const [confirmingReset, setConfirmingReset] = useState(false);
  const [confirmation, setConfirmation] = useState('');
  const [error, setError] = useState<string | null>(null);
  // The deletion waits for a sign-in asked for again, then for its confirmation once more.
  const [signingInAgain, setSigningInAgain] = useState(false);
  const [tokenDraft, setTokenDraft] = useState<TokenDraft>();
  const [otherAccount, setOtherAccount] = useState<'personal-token' | 'delete-account' | null>(null);
  const resumed = useRef(false);
  const closeButton = useRef<HTMLButtonElement>(null);
  const heading = useRef<HTMLHeadingElement>(null);
  const accountButton = useRef<HTMLButtonElement>(null);
  const inboxButton = useRef<HTMLButtonElement>(null);
  const assistantsButton = useRef<HTMLButtonElement>(null);
  const busy = Boolean(working) || inboxBusy || assistantsBusy;
  // What the person leaves behind stays behind: a sign-in asked for again, a draft, a word about another account.
  function leaveActions() {
    setConfirmingReset(false); setConfirmingDelete(false); setSigningInAgain(false); setTokenDraft(undefined); setOtherAccount(null); setError(null);
    dropSignInAgain(['personal-token', 'delete-account']);
  }
  const [dialog, close] = useSheetDialog<HTMLDivElement>(open, () => {
    setOpen(false); setPage('home'); leaveActions();
  }, closeButton, busy);
  const accountTitle = t(onExitDemo ? 'settings.demoData' : 'settings.accountData');

  // Back from a provider: Settings open where the sign-in was asked for. The same account makes its token at once, or
  // confirms its deletion once more; another account is told that nothing was done, and does nothing.
  useEffect(() => {
    if (resumed.current || !apiAuth) return;
    resumed.current = true;
    const note = takeSignInAgain(['personal-token', 'delete-account']);
    if (!note) return;
    const same = note.userId === apiAuth.userId;
    // eslint-disable-next-line react-hooks/set-state-in-effect -- session storage, which only the live page can read
    setOpen(true);
    if (!same) setOtherAccount(note.action);
    if (note.action === 'personal-token') {
      setPage('assistants');
      setTokenDraft({ name: note.name, writes: note.writes, make: same });
    } else {
      setPage('account');
      if (same && email) { setConfirmingDelete(true); setConfirmation(email); }
    }
  }, [apiAuth, email]);

  function navigate(next: Page) {
    const from = page;
    setPage(next); leaveActions();
    requestAnimationFrame(() => (next !== 'home' ? heading.current : from === 'inbox' ? inboxButton.current : from === 'assistants' ? assistantsButton.current : accountButton.current)?.focus());
  }

  async function run(action: AccountAction, operation: () => Promise<void>) {
    if (working) return;
    setWorking(action); setError(null); setOtherAccount(null);
    try { await operation(); if (action === 'export' || action === 'reset-demo') setWorking(null); }
    catch (reason) {
      setWorking(null);
      // Not signed out: the account signs in again here, then confirms once more.
      if (action === 'delete' && signIn && reason instanceof ApiRecentSignInError) setSigningInAgain(true);
      else setError(userErrorMessage(reason, t, 'account.actionFailed'));
    }
  }

  const noteDeletion = () => { if (apiAuth) noteSignInAgain({ action: 'delete-account', userId: apiAuth.userId }); };
  const otherAccountNotice = otherAccount && email
    ? <p className="settings-notice" role="status">{t(otherAccount === 'personal-token' ? 'assistants.otherAccount' : 'account.otherAccount', { email })}</p>
    : null;

  return <>
    <button type="button" className="account-trigger" aria-label={email ? t('account.options', { email }) : t('native.account')}
      aria-haspopup="dialog" onClick={() => setOpen(true)}>{initial || <Icon name="account" />}</button>
    {open && createPortal(<div className="sheet-backdrop" onClick={close}>
      <div ref={dialog} className="sheet settings-sheet account-menu refined-settings" role="dialog" aria-modal="true" aria-labelledby="settings-title" tabIndex={-1} onClick={(event) => event.stopPropagation()}>
        <div className="sheet__grabber" aria-hidden="true" />
        {page !== 'home' && <button type="button" className="settings-back" disabled={busy} onClick={() => navigate('home')}><span aria-hidden="true">‹</span>{t('settings.title')}</button>}
        <div className="sheet__heading"><h2 ref={heading} tabIndex={-1} className="sheet__title" id="settings-title">{page === 'home' ? t('settings.title') : page === 'inbox' ? t('inbox.row') : page === 'assistants' ? t('assistants.row') : accountTitle}</h2>
          <button ref={closeButton} className="sheet__close" type="button" disabled={busy} onClick={close} aria-label={t('common.close')}><Icon name="close" /></button></div>
        <div hidden={page !== 'home'}>
          <div className="settings-profile"><span className="settings-profile__avatar">{initial || 'D'}</span>
            <div><strong>{email || t('app.demo')}</strong>{email && <span>{t('account.signedIn')}</span>}</div></div>
          {apiAuth && <section className="settings-group" aria-labelledby="settings-deliveries"><h3 id="settings-deliveries" className="settings-group__title">{t('native.deliveries')}</h3><div className="settings-box"><NotificationControl apiAuth={apiAuth} email={email} variant="row" />
            {addByEmail && <button ref={inboxButton} className="settings-row" type="button" onClick={() => navigate('inbox')}><Icon name="mail" /><span>{t('inbox.row')}</span><span className="settings-chevron" aria-hidden="true">›</span></button>}
            {assistants && <button ref={assistantsButton} className="settings-row" type="button" onClick={() => navigate('assistants')}><Icon name="globe" /><span>{t('assistants.row')}</span><span className="settings-chevron" aria-hidden="true">›</span></button>}</div></section>}
          <section className="settings-group" aria-labelledby="settings-preferences"><h3 className="settings-group__title" id="settings-preferences">{t('settings.preferences')}</h3>
            <div className="settings-box"><fieldset className="settings-appearance"><legend>{t('native.appearance.title')}</legend>
              <div>{(['system', 'light', 'dark'] as Appearance[]).map((option) => <button type="button" key={option} aria-pressed={appearance === option} onClick={() => { setAppearance(option); trackAction('appearance-change'); }}><span className={`settings-mini-screen settings-mini-screen--${option}`} aria-hidden="true"><i /><i /></span>{t(`native.appearance.${option}`)}</button>)}</div>
            </fieldset>
              <LanguageControl className="language-control--account" />
              <SwitchRow title={t('settings.use24HourTime')} checked={use24HourTime} onChange={setUse24HourTime} />
            </div>
          </section>
          <div className="settings-box"><button ref={accountButton} className="settings-row" type="button" onClick={() => navigate('account')}>{accountTitle}<span className="settings-chevron" aria-hidden="true">›</span></button>
            {onOpenLanding && <a className="settings-row" href={LANDING_PATH} onClick={(event) => followInPlace(event, onOpenLanding)}>{t('app.homePage')}<span className="settings-value">{window.location.host}</span><span className="settings-chevron" aria-hidden="true">›</span></a>}</div>
        </div>
        {page === 'inbox' && apiAuth && addByEmail && <AddByEmailPage apiAuth={apiAuth} offer={addByEmail} onChange={setAddByEmail} onBusy={setInboxBusy} />}
        {page === 'assistants' && otherAccountNotice}
        {page === 'assistants' && apiAuth && assistants && <AssistantsPage apiAuth={apiAuth} offer={assistants} onChange={setAssistants}
          onBusy={(next) => { setAssistantsBusy(next); if (next) setOtherAccount(null); }} signIn={signIn} email={email} draft={tokenDraft} />}
        {page === 'account' && <>
          <p className="settings-description">{email || t('app.demo')}</p>
          {otherAccountNotice}
          {error && <p className="sheet__error" role="alert">{error}</p>}
          {confirmingDelete && email && signingInAgain && signIn ? <SignInAgain methods={signIn} email={email}
            title={t('account.signInAgain')} subtitle={t('account.signInAgainBody')}
            onLeave={noteDeletion} onVerify={noteDeletion} onFailed={() => dropSignInAgain(['delete-account'])}
            onSignedIn={() => {
              // Another account signed in: its own Settings say so, and nothing is deleted.
              if (apiAuth?.signal?.aborted) return;
              dropSignInAgain(['delete-account']);
              setSigningInAgain(false);
            }}
            onCancel={() => { setSigningInAgain(false); setConfirmingDelete(false); setConfirmation(''); }} />
          : confirmingDelete && email ? <div className="settings-delete">
            <h3>{t('account.deleteQuestion')}</h3><p>{t('account.deleteDescription')}</p>
            <label className="field" htmlFor="delete-account-confirmation"><span>{t('account.typeToConfirm', { email })}</span>
              <input className="field__input" id="delete-account-confirmation" type="email" autoComplete="off" value={confirmation} onChange={(event) => setConfirmation(event.target.value)} autoFocus /></label>
            <div className="sheet__actions"><button className="button button--secondary" type="button" disabled={Boolean(working)} onClick={() => { setConfirmingDelete(false); setConfirmation(''); setError(null); }}>{t('common.cancel')}</button>
              <button className="button button--danger" type="button" disabled={Boolean(working) || confirmation.trim().toLowerCase() !== email.toLowerCase()}
                onClick={() => void run('delete', () => onDelete?.(confirmation) ?? Promise.resolve())}>{working === 'delete' ? t('account.deleting') : t('account.deletePermanent')}</button></div>
          </div> : confirmingReset && onResetDemo ? <div className="settings-reset">
            <h3>{t('native.resetDemoQuestion')}</h3><p>{t('native.resetDemoDescription')}</p>
            <div className="sheet__actions">
              <button className="button button--secondary" type="button" autoFocus disabled={Boolean(working)} onClick={() => { setConfirmingReset(false); setError(null); }}>{t('common.cancel')}</button>
              <button className="button button--primary" type="button" disabled={Boolean(working)} aria-busy={working === 'reset-demo'} onClick={() => void run('reset-demo', async () => { await onResetDemo(); setOpen(false); setPage('home'); setConfirmingReset(false); })}>{t('native.resetDemo')}</button>
            </div>
          </div> : <>
            <section className="settings-group"><h3 className="settings-group__title">{t('settings.yourData')}</h3><div className="settings-box">
              {onExport && <button className="settings-row" type="button" disabled={Boolean(working)} onClick={() => void run('export', onExport)}><Icon name="download" />{working === 'export' ? t('account.exporting') : t('account.export')}</button>}
              <a className="settings-row" href="/privacy.html" onClick={() => trackAction('privacy-open')}><Icon name="lock" />{t('account.privacy')}<span className="settings-chevron" aria-hidden="true">›</span></a>
            </div></section>
            {(onExitDemo || onResetDemo) && <section className="settings-group"><h3 className="settings-group__title">{t('app.demo')}</h3><div className="settings-box">
              {onResetDemo && <button className="settings-row" type="button" disabled={Boolean(working)} onClick={() => setConfirmingReset(true)}>{t('native.resetDemo')}<span className="settings-chevron" aria-hidden="true">›</span></button>}
              {onExitDemo && <button type="button" className="settings-row" disabled={Boolean(working)} onClick={onExitDemo}>{t('native.exitDemo')}</button>}
            </div></section>}
            {onSignOut && <div className="settings-box"><button className="settings-row" type="button" disabled={Boolean(working)} onClick={() => void run('sign-out', onSignOut)}>{working === 'sign-out' ? t('account.signingOut') : t('account.signOut')}</button></div>}
            {onDelete && <button className="settings-delete-link" type="button" disabled={Boolean(working)} onClick={() => { setConfirmingDelete(true); setOtherAccount(null); }}>{t('account.delete')}</button>}
          </>}
        </>}
      </div>
    </div>, document.body)}
  </>;
}

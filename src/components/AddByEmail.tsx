import { useEffect, useState } from 'react';
import type { ApiAddByEmail, ApiAddByEmailResponse } from '../generated/apiContract';
import { useI18n } from '../i18n';
import { closeAddByEmail, openAddByEmail, readAddByEmail } from '../lib/addByEmail';
import type { ApiAuth } from '../lib/apiClient';
import { localizedRelativeTime, type Translate } from '../lib/messages';
import { userErrorMessage } from '../lib/userMessages';
import { Icon } from './Icon';

/**
 * Whether the server offers this account a forwarding address, read each time Settings opens. Null hides the row:
 * the server answers 404 until the feature is set up and open to the account.
 */
export function useAddByEmail(apiAuth: ApiAuth | undefined, open: boolean) {
  const [offer, setOffer] = useState<{ userId: string; value: ApiAddByEmailResponse | null } | null>(null);
  useEffect(() => {
    if (!open || !apiAuth) return;
    let current = true;
    void readAddByEmail(apiAuth).then((value) => { if (current) setOffer({ userId: apiAuth.userId, value }); }, () => undefined);
    return () => { current = false; };
  }, [apiAuth, open]);
  const value = apiAuth && offer?.userId === apiAuth.userId ? offer.value : null;
  const update = (next: ApiAddByEmailResponse | null) => { if (apiAuth) setOffer({ userId: apiAuth.userId, value: next }); };
  return [value, update] as const;
}

function lastResult(t: Translate, inbox: ApiAddByEmail): string | null {
  if (!inbox.lastResult) return null;
  if (inbox.lastResult === 'added') return t('inbox.result.added.many', { count: inbox.lastAdded ?? 0 });
  return t(`inbox.result.${inbox.lastResult}`);
}

/** The forwarding address's page in Settings: make it, copy it, see Gmail's confirmation and the last email. */
export function AddByEmailPage({ apiAuth, offer, onChange, onBusy }: {
  apiAuth: ApiAuth;
  offer: ApiAddByEmailResponse;
  onChange: (next: ApiAddByEmailResponse) => void;
  onBusy?: (busy: boolean) => void;
}) {
  const { t, languageTag } = useI18n();
  const [working, setWorking] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [copied, setCopied] = useState(false);
  const [confirmingRenew, setConfirmingRenew] = useState(false);
  const inbox = offer.inbox;

  async function run(operation: () => Promise<ApiAddByEmailResponse>) {
    if (working) return;
    setWorking(true); onBusy?.(true); setError(null);
    try { onChange(await operation()); setCopied(false); setConfirmingRenew(false); }
    catch (reason) { setError(userErrorMessage(reason, t, 'inbox.failed')); }
    finally { setWorking(false); onBusy?.(false); }
  }

  async function copy(address: string) {
    try { await navigator.clipboard.writeText(address); setCopied(true); }
    catch { /* The address stays on screen to select by hand. */ }
  }

  const result = inbox && lastResult(t, inbox);
  return <>
    <p className="settings-description">{t('inbox.intro')}</p>
    {error && <p className="sheet__error" role="alert">{error}</p>}
    {!inbox ? <button className="button button--primary add-by-email__start" type="button" disabled={working} aria-busy={working || undefined}
      onClick={() => void run(() => openAddByEmail(apiAuth))}><Icon name="mail" />{t('inbox.start')}</button> : <>
      <section className="settings-group" aria-labelledby="add-by-email-address"><h3 className="settings-group__title" id="add-by-email-address">{t('inbox.address')}</h3>
        <div className="settings-box">
          <p className="add-by-email__address" translate="no">{inbox.address}</p>
          <button className="settings-row" type="button" onClick={() => void copy(inbox.address)}><Icon name={copied ? 'check' : 'copy'} />{t(copied ? 'inbox.copied' : 'inbox.copy')}</button>
        </div>
        <p className="add-by-email__note">{t('inbox.gmail')}</p>
      </section>
      {inbox.confirmation && <section className="settings-group" aria-labelledby="add-by-email-confirmation"><h3 className="settings-group__title" id="add-by-email-confirmation">{t('inbox.confirm.title')}</h3>
        <div className="settings-box">
          {inbox.confirmation.code && <p className="add-by-email__code">{t('inbox.confirm.code', { code: inbox.confirmation.code })}</p>}
          {inbox.confirmation.link && <a className="settings-row" href={inbox.confirmation.link} target="_blank" rel="noopener noreferrer">{t('inbox.confirm.link')}<span className="settings-chevron" aria-hidden="true">›</span></a>}
        </div>
      </section>}
      {(inbox.lastMailAt && result || inbox.usedToday > 0) && <section className="settings-group" aria-labelledby="add-by-email-last"><h3 className="settings-group__title" id="add-by-email-last">{t('inbox.lastMail')}</h3>
        {inbox.lastMailAt && result && <div className="settings-box">
          <p className="settings-row add-by-email__last"><span>{result}</span><span className="settings-value">{localizedRelativeTime(inbox.lastMailAt, t, languageTag)}</span></p>
        </div>}
        {inbox.usedToday > 0 && <p className="add-by-email__note">{t('inbox.usage', { used: inbox.usedToday, limit: offer.dailyLimit })}</p>}
      </section>}
      <div className="settings-box">
        {confirmingRenew
          ? <div className="add-by-email__renew"><p>{t('inbox.renewNote')}</p>
            <div className="sheet__actions"><button className="button button--secondary" type="button" disabled={working} onClick={() => setConfirmingRenew(false)}>{t('common.cancel')}</button>
              <button className="button button--primary" type="button" disabled={working} aria-busy={working || undefined} onClick={() => void run(() => openAddByEmail(apiAuth, true))}>{t('inbox.renew')}</button></div></div>
          : <button className="settings-row" type="button" disabled={working} onClick={() => setConfirmingRenew(true)}><Icon name="refresh" />{t('inbox.renew')}</button>}
        <button className="settings-row" type="button" disabled={working} onClick={() => void run(async () => { await closeAddByEmail(apiAuth); return { ...offer, inbox: null }; })}><Icon name="trash" />{t('inbox.off')}</button>
      </div>
    </>}
  </>;
}

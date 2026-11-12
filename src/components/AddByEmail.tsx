import { useEffect, useMemo, useState } from 'react';
import type { ApiAddByEmail, ApiAddByEmailResponse } from '../generated/apiContract';
import { useI18n } from '../i18n';
import { closeAddByEmail, openAddByEmail, readAddByEmail } from '../lib/addByEmail';
import type { ApiAuth } from '../lib/apiClient';
import { FILTER_LANGUAGES, filterLanguagesFor, gmailFilter, type FilterLanguage } from '../lib/emailFilter';
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

/** Each filter language's name in the language Peek shows, capitalised as a choice is. */
function languageNames(languageTag: string): Record<FilterLanguage, string> {
  let names: Intl.DisplayNames | null = null;
  try { names = new Intl.DisplayNames([languageTag], { type: 'language' }); } catch { /* The codes name the languages instead. */ }
  return Object.fromEntries(FILTER_LANGUAGES.map((language) => {
    const name = names?.of(language) ?? language.toUpperCase();
    return [language, name.slice(0, 1).toLocaleUpperCase(languageTag) + name.slice(1)];
  })) as Record<FilterLanguage, string>;
}

/**
 * Which emails to forward: the reader picks the languages their shops and carriers write in, and copies the search
 * Gmail's filter takes for them. It starts with the language Peek shows, the browser's and English.
 */
function ShippingFilter() {
  const { t, locale, languageTag } = useI18n();
  const [languages, setLanguages] = useState(() => filterLanguagesFor([locale, ...(typeof navigator === 'undefined' ? [] : navigator.languages ?? [])]));
  const [copied, setCopied] = useState(false);
  const names = useMemo(() => languageNames(languageTag), [languageTag]);
  const filter = gmailFilter(languages);

  function toggle(language: FilterLanguage) {
    // A filter of no language would forward nothing: the last one stays chosen.
    const next = languages.includes(language) ? languages.filter((chosen) => chosen !== language) : [...languages, language];
    if (!next.length) return;
    setLanguages(next); setCopied(false);
  }

  async function copy() {
    try { await navigator.clipboard.writeText(filter); setCopied(true); }
    catch { /* The filter stays on screen to select by hand. */ }
  }

  return <section className="settings-group" aria-labelledby="add-by-email-filter"><h3 className="settings-group__title" id="add-by-email-filter">{t('inbox.filter.title')}</h3>
    <p className="add-by-email__note add-by-email__note--lead" id="add-by-email-languages">{t('inbox.filter.languages')}</p>
    <div className="add-by-email__languages" role="group" aria-labelledby="add-by-email-languages">
      {FILTER_LANGUAGES.map((language) => <button key={language} className="add-by-email__language" type="button" aria-pressed={languages.includes(language)} onClick={() => toggle(language)}>{names[language]}</button>)}
    </div>
    <div className="settings-box">
      <p className="add-by-email__address" translate="no">{filter}</p>
      <button className="settings-row" type="button" onClick={() => void copy()}><Icon name={copied ? 'check' : 'copy'} />{t(copied ? 'inbox.copied' : 'inbox.filter.copy')}</button>
    </div>
    <p className="add-by-email__note">{t('inbox.filter.note')}</p>
  </section>;
}

/** The forwarding address's page in Settings: make it, copy it and the filter that feeds it, see Gmail's confirmation and the last email. */
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
      <ShippingFilter />
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

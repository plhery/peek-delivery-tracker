import { useEffect, useMemo, useState } from 'react';
import type { ApiAddByEmail, ApiAddByEmailEntry, ApiAddByEmailResponse } from '../generated/apiContract';
import { useI18n } from '../i18n';
import { closeAddByEmail, openAddByEmail, readAddByEmail } from '../lib/addByEmail';
import type { ApiAuth } from '../lib/apiClient';
import {
  FILTER_LANGUAGES, GMAIL_FORWARDING_LINK, ICLOUD_MAIL_LINK, MAIL_SERVICES, OUTLOOK_RULES_LINK, filterLanguagesFor, gmailFilter, gmailFilterLink,
  mailServiceOf, subjectWords, type FilterLanguage, type MailService,
} from '../lib/emailFilter';
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

/** What an email did, in a few words. */
function resultText(t: Translate, entry: ApiAddByEmailEntry): string {
  return entry.result === 'added' ? t('inbox.result.added.many', { count: entry.added }) : t(`inbox.result.${entry.result}`);
}

/** How many of the last emails the page lists. */
const EMAILS_SHOWN = 5;

/** The last emails, newest first. A mailbox from before they were kept shows its one last email. */
function recentEmails(inbox: ApiAddByEmail): ApiAddByEmailEntry[] {
  if (inbox.recent.length) return inbox.recent.slice(0, EMAILS_SHOWN);
  return inbox.lastMailAt && inbox.lastResult ? [{ at: inbox.lastMailAt, sender: '', result: inbox.lastResult, added: inbox.lastAdded ?? 0 }] : [];
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

/** The services' own names, which no language translates. */
const SERVICE_NAMES: Record<Exclude<MailService, 'other'>, string> = { gmail: 'Gmail', outlook: 'Outlook', icloud: 'iCloud', yahoo: 'Yahoo' };

/** A step's link to the page of the mail service it speaks of. */
function StepLink({ href, children }: { href: string; children: string }) {
  return <a className="add-by-email__link" href={href} target="_blank" rel="noopener noreferrer">{children}<span aria-hidden="true"> ›</span></a>;
}

/**
 * How to have a mail service forward shipping emails by itself. The reader names their service, which starts on the
 * one their account's address belongs to, and the languages their shops and carriers write in, which start with the
 * language Peek shows, the browser's and English. Gmail opens its filter already filled; a service whose rule takes
 * words one at a time is given the main ones, each copied by a tap; Yahoo, which forwards no chosen email, says so.
 */
function ForwardingSteps({ email, confirming }: { email?: string; confirming: boolean }) {
  const { t, locale, languageTag } = useI18n();
  // Gmail is the one service whose confirmation Peek shows: while one waits, its steps are the ones to follow.
  const [service, setService] = useState<MailService>(() => confirming ? 'gmail' : mailServiceOf(email));
  const [languages, setLanguages] = useState(() => filterLanguagesFor([locale, ...(typeof navigator === 'undefined' ? [] : navigator.languages ?? [])]));
  const [copied, setCopied] = useState<string | null>(null);
  const names = useMemo(() => languageNames(languageTag), [languageTag]);
  const filter = gmailFilter(languages);

  function toggle(language: FilterLanguage) {
    // A filter of no language would forward nothing: the last one stays chosen.
    const next = languages.includes(language) ? languages.filter((chosen) => chosen !== language) : [...languages, language];
    if (!next.length) return;
    setLanguages(next); setCopied(null);
  }

  async function copy(text: string) {
    try { await navigator.clipboard.writeText(text); setCopied(text); }
    catch { /* The text stays on screen to select by hand. */ }
  }

  return <>
    <p className="add-by-email__note add-by-email__note--lead" id="add-by-email-service">{t('inbox.service.title')}</p>
    <div className="add-by-email__choices" role="group" aria-labelledby="add-by-email-service">
      {MAIL_SERVICES.map((choice) => <button key={choice} className="add-by-email__choice" type="button" aria-pressed={service === choice} translate={choice === 'other' ? undefined : 'no'}
        onClick={() => { setService(choice); setCopied(null); }}>{choice === 'other' ? t('inbox.service.other') : SERVICE_NAMES[choice]}</button>)}
    </div>
    {service === 'yahoo' ? <p className="add-by-email__steps add-by-email__steps--none">{t('inbox.yahoo')}</p> : <>
      <ol className="add-by-email__steps">
        {service === 'gmail' && <>
          <li>{t('inbox.gmail.add')}<StepLink href={GMAIL_FORWARDING_LINK}>{t('inbox.gmail.addLink')}</StepLink></li>
          <li>{t('inbox.auto.confirm')}</li>
          <li>{t('inbox.gmail.filter')}</li>
        </>}
        {service === 'outlook' && <>
          <li>{t('inbox.outlook.rule')}<StepLink href={OUTLOOK_RULES_LINK}>{t('inbox.outlook.link')}</StepLink></li>
          <li>{t('inbox.rule.words')}</li>
        </>}
        {service === 'icloud' && <>
          <li>{t('inbox.icloud.rule')}<StepLink href={ICLOUD_MAIL_LINK}>{t('inbox.icloud.link')}</StepLink></li>
          <li>{t('inbox.icloud.words')}</li>
        </>}
        {service === 'other' && <>
          <li>{t('inbox.other.rule')}</li>
          <li>{t('inbox.rule.words')}</li>
        </>}
      </ol>
      <p className="add-by-email__note add-by-email__note--lead" id="add-by-email-languages">{t('inbox.filter.languages')}</p>
      <div className="add-by-email__choices" role="group" aria-labelledby="add-by-email-languages">
        {FILTER_LANGUAGES.map((language) => <button key={language} className="add-by-email__choice" type="button" aria-pressed={languages.includes(language)} onClick={() => toggle(language)}>{names[language]}</button>)}
      </div>
      {service === 'gmail' ? <div className="settings-box">
        <p className="add-by-email__address" translate="no">{filter}</p>
        <a className="settings-row" href={gmailFilterLink(languages)} target="_blank" rel="noopener noreferrer"><Icon name="mail" />{t('inbox.gmail.filterLink')}<span className="settings-chevron" aria-hidden="true">›</span></a>
        <button className="settings-row" type="button" onClick={() => void copy(filter)}><Icon name={copied === filter ? 'check' : 'copy'} />{t(copied === filter ? 'inbox.copied' : 'inbox.filter.copy')}</button>
      </div> : <>
        <div className="add-by-email__words" role="group" aria-label={t('inbox.words.title')} translate="no">
          {subjectWords(languages).map((word) => <button key={word} className="add-by-email__word" type="button" onClick={() => void copy(word)}>{word}<Icon name={copied === word ? 'check' : 'copy'} /></button>)}
        </div>
        <p className="add-by-email__note" role="status">{copied && copied !== filter ? t('inbox.copied') : t('inbox.words.copy')}</p>
        {service === 'other' && <p className="add-by-email__note">{t('inbox.other.link')}</p>}
      </>}
    </>}
  </>;
}

/**
 * The forwarding address's page in Settings. Forwarding one email by hand is all it asks: the address to copy, then
 * what the last emails did. Having the mail service forward by itself, with its steps, stays folded away until wanted,
 * or until Gmail's confirmation is waiting.
 */
export function AddByEmailPage({ apiAuth, email, offer, onChange, onBusy }: {
  apiAuth: ApiAuth;
  /** The account's address, which tells the mail service the steps start on. */
  email?: string;
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

  const recent = inbox ? recentEmails(inbox) : [];
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
      </section>
      {inbox.confirmation && <section className="settings-group" aria-labelledby="add-by-email-confirmation"><h3 className="settings-group__title" id="add-by-email-confirmation">{t('inbox.confirm.title')}</h3>
        <div className="settings-box">
          {inbox.confirmation.code && <p className="add-by-email__code">{t('inbox.confirm.code', { code: inbox.confirmation.code })}</p>}
          {inbox.confirmation.link && <a className="settings-row" href={inbox.confirmation.link} target="_blank" rel="noopener noreferrer">{t('inbox.confirm.link')}<span className="settings-chevron" aria-hidden="true">›</span></a>}
        </div>
      </section>}
      {recent.length > 0 && <section className="settings-group" aria-labelledby="add-by-email-recent"><h3 className="settings-group__title" id="add-by-email-recent">{t('inbox.recent')}</h3>
        <ul className="settings-box add-by-email__recent">
          {recent.map((entry) => <li key={entry.at} className="settings-row add-by-email__mail">
            <span className="add-by-email__what">{entry.sender ? <><strong>{entry.sender}</strong><small>{resultText(t, entry)}</small></> : <strong>{resultText(t, entry)}</strong>}</span>
            <span className="settings-value">{localizedRelativeTime(entry.at, t, languageTag)}</span>
          </li>)}
        </ul>
        {recent.some(({ result }) => result === 'none') && <p className="add-by-email__note">{t('inbox.noNumber')}</p>}
        {inbox.usedToday * 2 >= offer.dailyLimit && <p className="add-by-email__note">{t('inbox.usage', { used: inbox.usedToday, limit: offer.dailyLimit })}</p>}
      </section>}
      <details className="settings-box add-by-email__auto" open={Boolean(inbox.confirmation) || undefined}>
        <summary className="settings-row"><Icon name="settings" /><span>{t('inbox.auto.title')}</span><span className="settings-chevron" aria-hidden="true">›</span></summary>
        <div className="add-by-email__auto-body">
          <ForwardingSteps email={email} confirming={Boolean(inbox.confirmation)} />
        </div>
      </details>
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

import { Fragment, useEffect, useMemo, useState } from 'react';
import type { ApiAddByEmail, ApiAddByEmailEntry, ApiAddByEmailResponse } from '../generated/apiContract';
import { GUIDE_LINKS } from '../generated/guides';
import { guidePath } from '../guides/paths';
import { useI18n } from '../i18n';
import { closeAddByEmail, openAddByEmail, readAddByEmail, setAddByEmailNotify } from '../lib/addByEmail';
import type { ApiAuth } from '../lib/apiClient';
import {
  FILTER_LANGUAGES, GMAIL_SETTINGS_LINK, ICLOUD_MAIL_LINK, MAIL_SERVICES, OUTLOOK_RULES_LINK, filterLanguagesFor, gmailFilter,
  mailServiceOf, subjectWords, type FilterLanguage, type MailService,
} from '../lib/emailFilter';
import { documentLanguage, guideLanguage, type Locale } from '../lib/locale';
import { localizedRelativeTime, type Translate } from '../lib/messages';
import { userErrorMessage } from '../lib/userMessages';
import { Icon } from './Icon';
import { PipEmpty, PipPerch } from './PipPerch';

/** How soon the reader coming back to Peek reads the offer again: the tab and the window tell of the same return. */
const REREAD_AFTER_MS = 2_000;

/**
 * Whether the server offers this account a forwarding address, read each time Settings opens, and again each time
 * the reader comes back to Peek's tab or window: Gmail's confirmation, sent while they were in Gmail, then shows
 * without a reload. Null hides the row: the server answers 404 until the feature is set up and open to the account.
 */
export function useAddByEmail(apiAuth: ApiAuth | undefined, open: boolean) {
  const [offer, setOffer] = useState<{ userId: string; value: ApiAddByEmailResponse | null } | null>(null);
  useEffect(() => {
    if (!open || !apiAuth) return;
    let current = true;
    let readAt = 0;
    const read = () => {
      readAt = Date.now();
      void readAddByEmail(apiAuth).then((value) => { if (current) setOffer({ userId: apiAuth.userId, value }); }, () => undefined);
    };
    const onReturn = () => { if (document.visibilityState === 'visible' && Date.now() - readAt >= REREAD_AFTER_MS) read(); };
    read();
    document.addEventListener('visibilitychange', onReturn);
    window.addEventListener('focus', onReturn);
    return () => { current = false; document.removeEventListener('visibilitychange', onReturn); window.removeEventListener('focus', onReturn); };
  }, [apiAuth, open]);
  const value = apiAuth && offer?.userId === apiAuth.userId ? offer.value : null;
  const update = (next: ApiAddByEmailResponse | null) => { if (apiAuth) setOffer({ userId: apiAuth.userId, value: next }); };
  return [value, update] as const;
}

/**
 * What an email did, in a few words. One that held no number from a shop that shows it only in the reader's account
 * with it (`account`) says where it is.
 */
function resultText(t: Translate, entry: ApiAddByEmailEntry): string {
  if (entry.result === 'none' && entry.account) return t('inbox.result.account', { shop: entry.account });
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

/** The guide to adding parcels by email, in the reader's language or the nearest one it is written in. */
function GuideLink({ locale, children }: { locale: Locale; children: string }) {
  const language = guideLanguage(locale);
  const guide = GUIDE_LINKS[language].find(({ id }) => id === 'add-parcels-by-email');
  if (!guide) return null;
  return <p className="add-by-email__note"><a className="add-by-email__link" href={guidePath(language, guide.slug)} target="_blank" rel="noopener"
    hrefLang={language === locale ? undefined : documentLanguage(language)}>{children}<span aria-hidden="true"> ›</span></a></p>;
}

/** A step's link to the page of the mail service it speaks of. */
function StepLink({ href, children }: { href: string; children: string }) {
  return <a className="add-by-email__link" href={href} target="_blank" rel="noopener noreferrer">{children}<span aria-hidden="true"> ›</span></a>;
}

/**
 * Gmail's "Show search options" button as Gmail draws it, three sliders, set in the step that has it pressed: it has
 * no written name on screen, only this.
 */
function SearchOptionsGlyph() {
  return <svg className="add-by-email__glyph" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" aria-hidden="true">
    <path d="M4 6h9M17 6h3M4 12h3M11 12h9M4 18h11M19 18h1" /><circle cx="15" cy="6" r="2" /><circle cx="9" cy="12" r="2" /><circle cx="17" cy="18" r="2" />
  </svg>;
}

/**
 * A step's text with the mail service's own button and field names, which the translation marks `**…**`, in bold,
 * and Gmail's options button drawn where the translation puts `{{options}}`.
 */
function StepText({ text }: { text: string }) {
  return <>{text.split(/(\*\*[^*]+\*\*|\[\[options\]\])/).map((piece, index) => {
    if (piece === '[[options]]') return <SearchOptionsGlyph key={index} />;
    if (piece.startsWith('**') && piece.endsWith('**') && piece.length > 4) return <strong key={index} className="add-by-email__ui">{piece.slice(2, -2)}</strong>;
    return <Fragment key={index}>{piece}</Fragment>;
  })}</>;
}

/**
 * How to have a mail service forward shipping emails by itself, each service's steps in the order it asks for them,
 * with its own names for its buttons. The reader names their service, which starts on the one their account's address
 * belongs to, and the languages their shops and carriers write in, which start with the language Peek shows, the
 * browser's and English.
 * - Each service's first step names the way to its settings as well as linking there, and says what to check when they
 *   don't show what the step speaks of: a phone, another account, an administrator who turned forwarding off. Gmail's
 *   link opens its settings, not the forwarding tab: opened from a link or reloaded, that tab can leave out its button,
 *   so the tab is a step of its own.
 * - Gmail forwards only to an address it has confirmed, and then only the emails a filter picks: the address is added
 *   and confirmed first (Peek shows Gmail's confirmation in the step), then the filter is made from a search to copy.
 *   Its forwarding settings also offer to forward every email, which the steps say to leave off.
 * - A service whose rule takes words one at a time is given the main ones, each copied by a tap.
 * - Yahoo, whose filters only move emails and whose forwarding takes every email, says so.
 */
function ForwardingSteps({ email, confirmation }: { email?: string; confirmation: ApiAddByEmail['confirmation'] }) {
  const { t, locale, languageTag } = useI18n();
  // Gmail is the one service whose confirmation Peek shows: while one waits, its steps are the ones to follow.
  const [service, setService] = useState<MailService>(() => confirmation ? 'gmail' : mailServiceOf(email));
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

  const step = (key: Parameters<typeof t>[0]) => <StepText text={t(key, { options: '[[options]]' })} />;
  const missing = (key: Parameters<typeof t>[0]) => <p className="add-by-email__note add-by-email__missing"><StepText text={t(key)} /></p>;
  const languagePicker = <>
    <p className="add-by-email__note add-by-email__note--lead" id="add-by-email-languages">{t('inbox.filter.languages')}</p>
    <div className="add-by-email__choices" role="group" aria-labelledby="add-by-email-languages">
      {FILTER_LANGUAGES.map((language) => <button key={language} className="add-by-email__choice" type="button" aria-pressed={languages.includes(language)} onClick={() => toggle(language)}>{names[language]}</button>)}
    </div>
  </>;
  const words = <div className="add-by-email__step-box">
    {languagePicker}
    <div className="add-by-email__words" role="group" aria-label={t('inbox.words.title')} translate="no">
      {subjectWords(languages).map((word) => <button key={word} className="add-by-email__word" type="button" onClick={() => void copy(word)}>{word}<Icon name={copied === word ? 'check' : 'copy'} /></button>)}
    </div>
    <p className="add-by-email__note" role="status">{copied ? t('inbox.copied') : t('inbox.words.copy')}</p>
  </div>;

  return <>
    <p className="add-by-email__note add-by-email__note--lead" id="add-by-email-service">{t('inbox.service.title')}</p>
    <div className="add-by-email__choices" role="group" aria-labelledby="add-by-email-service">
      {MAIL_SERVICES.map((choice) => <button key={choice} className="add-by-email__choice" type="button" aria-pressed={service === choice} translate={choice === 'other' ? undefined : 'no'}
        onClick={() => { setService(choice); setCopied(null); }}>{choice === 'other' ? t('inbox.service.other') : SERVICE_NAMES[choice]}</button>)}
    </div>
    {service === 'yahoo' ? <p className="add-by-email__steps add-by-email__steps--none">{t('inbox.yahoo')}</p> : <ol className="add-by-email__steps">
      {service === 'gmail' && <>
        <li>{step('inbox.gmail.settings')}<StepLink href={GMAIL_SETTINGS_LINK}>{t('inbox.gmail.settingsLink')}</StepLink></li>
        <li>{step('inbox.gmail.add')}{missing('inbox.gmail.noButton')}</li>
        <li>{step('inbox.gmail.confirm')}
          {confirmation && <div className="settings-box add-by-email__step-box add-by-email__confirmation">
            {confirmation.link && <a className="settings-row" href={confirmation.link} target="_blank" rel="noopener noreferrer"><Icon name="mail" />{t('inbox.confirm.link')}<span className="settings-chevron" aria-hidden="true">›</span></a>}
            {confirmation.code && <p className="add-by-email__code">{t('inbox.confirm.code', { code: confirmation.code })}</p>}
          </div>}
        </li>
        <li>{step('inbox.gmail.keepOff')}</li>
        <li>{step('inbox.gmail.search')}
          <div className="add-by-email__step-box">
            {languagePicker}
            <div className="settings-box">
              <p className="add-by-email__address" translate="no">{filter}</p>
              <button className="settings-row" type="button" onClick={() => void copy(filter)}><Icon name={copied === filter ? 'check' : 'copy'} />{t(copied === filter ? 'inbox.copied' : 'inbox.filter.copy')}</button>
            </div>
          </div>
        </li>
        <li>{step('inbox.gmail.filter')}</li>
        <li>{step('inbox.gmail.forward')}</li>
      </>}
      {service === 'outlook' && <>
        <li>{step('inbox.outlook.rule')}<StepLink href={OUTLOOK_RULES_LINK}>{t('inbox.outlook.link')}</StepLink>{missing('inbox.outlook.work')}</li>
        <li>{step('inbox.outlook.condition')}{words}</li>
        <li>{step('inbox.outlook.action')}</li>
      </>}
      {service === 'icloud' && <>
        <li>{step('inbox.icloud.rule')}<StepLink href={ICLOUD_MAIL_LINK}>{t('inbox.icloud.link')}</StepLink>{missing('inbox.icloud.noMail')}</li>
        <li>{step('inbox.icloud.words')}{words}</li>
        <li>{step('inbox.icloud.more')}</li>
      </>}
      {service === 'other' && <li>{step('inbox.other.rule')}{words}</li>}
    </ol>}
    {service === 'other' && <p className="add-by-email__note">{t('inbox.other.link')}</p>}
  </>;
}

/**
 * The forwarding address's page in Settings. Forwarding one email by hand is all it asks: the address to copy, then
 * what the last emails did, and whether each parcel an email adds is notified (on unless switched off). Having the mail
 * service forward by itself, with its steps, stays folded away until wanted, or until Gmail's confirmation is waiting.
 */
export function AddByEmailPage({ apiAuth, email, offer, onChange, onBusy }: {
  apiAuth: ApiAuth;
  /** The account's address, which tells the mail service the steps start on. */
  email?: string;
  offer: ApiAddByEmailResponse;
  onChange: (next: ApiAddByEmailResponse) => void;
  onBusy?: (busy: boolean) => void;
}) {
  const { t, languageTag, locale } = useI18n();
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
  const notify = inbox?.notify !== false;
  return <>
    <p className="settings-description">{t('inbox.intro')}</p>
    <GuideLink locale={locale}>{t('inbox.guide')}</GuideLink>
    {error && <p className="sheet__error" role="alert">{error}</p>}
    {!inbox ? <button className="button button--primary add-by-email__start" type="button" disabled={working} aria-busy={working || undefined}
      onClick={() => void run(() => openAddByEmail(apiAuth))}><Icon name="mail" />{t('inbox.start')}</button> : <>
      <section className="settings-group" aria-labelledby="add-by-email-address"><h3 className="settings-group__title" id="add-by-email-address">{t('inbox.address')}</h3>
        <PipPerch happy={copied}><div className="settings-box">
          <p className="add-by-email__address" translate="no">{inbox.address}</p>
          <button className="settings-row" type="button" onClick={() => void copy(inbox.address)}><Icon name={copied ? 'check' : 'copy'} />{t(copied ? 'inbox.copied' : 'inbox.copy')}</button>
        </div></PipPerch>
      </section>
      <section className="settings-group" aria-labelledby="add-by-email-recent"><h3 className="settings-group__title" id="add-by-email-recent">{t('inbox.recent')}</h3>
        {recent.length ? <ul className="settings-box add-by-email__recent">
          {recent.map((entry) => <li key={entry.at} className="settings-row add-by-email__mail">
            <span className="add-by-email__what">{entry.sender ? <><strong>{entry.sender}</strong><small>{resultText(t, entry)}</small></> : <strong>{resultText(t, entry)}</strong>}</span>
            <span className="settings-value">{localizedRelativeTime(entry.at, t, languageTag)}</span>
          </li>)}
        </ul> : <div className="settings-box"><PipEmpty>{t('inbox.empty')}</PipEmpty></div>}
        {recent.some(({ result, account }) => result === 'none' && !account) && <p className="add-by-email__note">{t('inbox.noNumber')}</p>}
        {inbox.usedToday * 2 >= offer.dailyLimit && <p className="add-by-email__note">{t('inbox.usage', { used: inbox.usedToday, limit: offer.dailyLimit })}</p>}
      </section>
      <details className="settings-box add-by-email__auto" open={Boolean(inbox.confirmation) || undefined}>
        <summary className="settings-row"><Icon name="settings" /><span>{t('inbox.auto.title')}</span><span className="settings-chevron" aria-hidden="true">›</span></summary>
        <div className="add-by-email__auto-body">
          <ForwardingSteps email={email} confirmation={inbox.confirmation} />
        </div>
      </details>
      <div className="settings-box">
        {confirmingRenew
          ? <div className="add-by-email__renew"><p>{t('inbox.renewNote')}</p>
            <div className="sheet__actions"><button className="button button--secondary" type="button" disabled={working} onClick={() => setConfirmingRenew(false)}>{t('common.cancel')}</button>
              <button className="button button--primary" type="button" disabled={working} aria-busy={working || undefined} onClick={() => void run(() => openAddByEmail(apiAuth, true))}>{t('inbox.renew')}</button></div></div>
          : <button className="settings-row" type="button" disabled={working} onClick={() => setConfirmingRenew(true)}><Icon name="refresh" />{t('inbox.renew')}</button>}
        <button className="settings-row add-by-email__notify" type="button" role="switch" aria-checked={notify} aria-busy={working || undefined} disabled={working}
          onClick={() => void run(() => setAddByEmailNotify(apiAuth, !notify))}>
          <Icon name="bell" /><span>{t('inbox.notify')}</span><span className="settings-switch" aria-hidden="true" />
        </button>
        <button className="settings-row" type="button" disabled={working} onClick={() => void run(async () => { await closeAddByEmail(apiAuth); return { ...offer, inbox: null }; })}><Icon name="trash" />{t('inbox.off')}</button>
      </div>
    </>}
  </>;
}

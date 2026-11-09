'use client';

import { useEffect, useRef, useState, useSyncExternalStore } from 'react';
import { holdsSignIn } from '../auth/savedSignIn';
import { I18nProvider, LanguageControl, type Locale, type MessageKey, type Messages, useI18n } from '../i18n';
import { startAnalytics, trackAction, trackScreen } from '../lib/analytics';
import {
  ANDROID_TEST_LINKS, neverAndroidTest, noteAndroidTestStep, readAndroidTestNote, subscribeAndroidTest, type AndroidTestStep,
} from '../lib/androidTest';
import { AppearanceProvider } from '../lib/appearance';
import { backOnSite } from '../lib/backOnSite';
import { useTabTitle } from '../lib/tabTitle';
import { Icon } from './Icon';
import { PeekMark } from './PeekMark';
import './AndroidTestScreen.css';

const STEPS: { step: AndroidTestStep; event: string; title: MessageKey; body: MessageKey; action: MessageKey }[] = [
  { step: 'group', event: 'android-test-group',
    title: 'androidTest.step.group.title', body: 'androidTest.step.group.body', action: 'androidTest.step.group.action' },
  { step: 'optIn', event: 'android-test-opt-in',
    title: 'androidTest.step.optIn.title', body: 'androidTest.step.optIn.body', action: 'androidTest.step.optIn.action' },
  { step: 'install', event: 'android-test-install',
    title: 'androidTest.step.install.title', body: 'androidTest.step.install.body', action: 'androidTest.step.install.action' },
];

const nothing = () => null;

/**
 * Peek for Android's three steps, at `/android`: join the testers' group, become a tester on Google Play, install.
 * Each opens Google's page in a new tab, so Back returns here, where the next step then leads. Google Play lets in
 * only the group's members, so the last two steps stay locked until the group's link was followed. Anyone who
 * reaches the page sees it, on any device: a computer can join and opt in for the phone's Google account too.
 */
export function AndroidTestScreen({ initialLocale, initialMessages }: { initialLocale?: Locale; initialMessages?: Messages }) {
  return <I18nProvider initialLocale={initialLocale} initialMessages={initialMessages}>
    <AppearanceProvider><AndroidTest /></AppearanceProvider>
  </I18nProvider>;
}

function AndroidTest() {
  const { t } = useI18n();
  const note = useSyncExternalStore(subscribeAndroidTest, readAndroidTestNote, nothing);
  const [declined, setDeclined] = useState(false);
  const said = useRef<HTMLParagraphElement>(null);
  useTabTitle(`${t('app.title')} — ${t('androidTest.page.title')}`);
  useEffect(() => {
    trackScreen('android-test', holdsSignIn() ? 'account' : 'anonymous');
    void startAnalytics({ open: false });
  }, []);
  // The button goes as it is pressed: the focus moves to what it did.
  useEffect(() => { if (declined) said.current?.focus(); }, [declined]);

  // Google Play turns away anyone outside the group: until its link was followed, it leads and the others wait.
  const joined = !!note?.steps?.group;
  // The first step not taken yet leads; once all are, none does.
  const next = joined ? STEPS.find(({ step }) => !note?.steps?.[step])?.step : 'group';

  return <div className="android-steps">
    <header className="android-steps__header">
      {/* The home page is another document. */}
      {/* eslint-disable-next-line @next/next/no-html-link-for-pages */}
      <a href="/" onClick={backOnSite}><Icon name="back" />{t('androidTest.page.back')}</a>
      <LanguageControl />
    </header>
    <main className="android-steps__main" aria-labelledby="android-steps-title">
      <div className="android-steps__intro">
        <PeekMark size={60} />
        <h1 id="android-steps-title">{t('androidTest.page.title')}</h1>
        <p>{t('androidTest.page.lead')}</p>
      </div>
      <ol className="android-steps__list">
        {STEPS.map(({ step, event, title, body, action }, index) => {
          const locked = step !== 'group' && !joined;
          const done = !locked && !!note?.steps?.[step];
          return <li key={step} data-next={next === step || undefined} data-done={done || undefined} data-locked={locked || undefined}>
            <span className="android-steps__number">
              {done ? <><Icon name="check" /><span className="sr-only">{t('androidTest.step.done')}</span></> : index + 1}
            </span>
            <div>
              <strong>{t(title)}</strong>
              <p>{t(body)}</p>
              {locked
                ? <button type="button" className="button button--secondary" disabled><Icon name="lock" />{t('androidTest.step.locked')}</button>
                : <a className={`button ${next === step ? 'button--primary' : 'button--secondary'}`} href={ANDROID_TEST_LINKS[step]}
                  target="_blank" rel="noopener noreferrer" onClick={() => { trackAction(event); noteAndroidTestStep(step); }}>
                  {t(action)}<Icon name="arrow" />
                </a>}
            </div>
          </li>;
        })}
      </ol>
      <p className="android-steps__note">{t('androidTest.page.note')}</p>
      {note?.never
        ? <p ref={said} className="android-steps__never" tabIndex={-1}>{t('androidTest.page.neverDone')}</p>
        : <button type="button" className="android-steps__never" onClick={() => {
          trackAction('android-test-never');
          neverAndroidTest();
          setDeclined(true);
        }}>{t('androidTest.page.never')}</button>}
    </main>
  </div>;
}

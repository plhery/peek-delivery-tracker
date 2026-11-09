'use client';

import { useEffect, useSyncExternalStore } from 'react';
import { holdsSignIn } from '../auth/savedSignIn';
import { I18nProvider, LanguageControl, type Locale, type Messages, useI18n } from '../i18n';
import { startAnalytics, trackAction, trackScreen } from '../lib/analytics';
import { AppearanceProvider } from '../lib/appearance';
import { onIPad } from '../lib/appStore';
import { backOnSite } from '../lib/backOnSite';
import { phoneAppAndroidLink, phoneAppStoreLink } from '../lib/phoneApp';
import { useTabTitle } from '../lib/tabTitle';
import { AndroidRobotCredit, PlatformLogo } from '../peek/landing/glyphs';
import { Icon } from './Icon';
import { PeekMark } from './PeekMark';
import './PhoneAppScreen.css';

/**
 * `/app` for whoever the server could not send on to an app ([phoneApp](../lib/phoneApp.ts)): a computer gets the
 * code to scan with its phone and both apps, and an iPad whose Safari says it is a Mac goes on to the App Store.
 */
export function PhoneAppScreen({ source, initialLocale, initialMessages }: {
  /** The `utm_source` the address came with, passed on to the app's link. */
  source?: string;
  initialLocale?: Locale;
  initialMessages?: Messages;
}) {
  return <I18nProvider initialLocale={initialLocale} initialMessages={initialMessages}>
    <AppearanceProvider><PhoneApp source={source} /></AppearanceProvider>
  </I18nProvider>;
}

/** Whether the browser is an iPad does not change while the page is open. */
const stays = () => () => {};
const notKnown = () => false;

function PhoneApp({ source }: { source?: string }) {
  const { t } = useI18n();
  const appStore = phoneAppStoreLink(source);
  // The server sends the page a computer gets; an iPad hides it as soon as it runs and goes on to the App Store.
  const iPad = useSyncExternalStore(stays, onIPad, notKnown);
  useTabTitle(`${t('app.title')} — ${t('phoneApp.title')}`);
  useEffect(() => {
    if (iPad) {
      location.replace(appStore);
      return;
    }
    trackScreen('get-app', holdsSignIn() ? 'account' : 'anonymous');
    void startAnalytics({ open: false });
  }, [iPad, appStore]);

  return <div className="phone-app" hidden={iPad}>
    <header className="phone-app__header">
      {/* The home page is another document. */}
      {/* eslint-disable-next-line @next/next/no-html-link-for-pages */}
      <a href="/" onClick={backOnSite}><Icon name="back" />{t('androidTest.page.back')}</a>
      <LanguageControl />
    </header>
    <main className="phone-app__main" aria-labelledby="phone-app-title">
      <PeekMark size={60} />
      <h1 id="phone-app-title">{t('phoneApp.title')}</h1>
      {/* On a computer the phone is a scan away. */}
      <div className="phone-app__qr">
        {/* eslint-disable-next-line @next/next/no-img-element -- a small drawing, fetched only where it shows */}
        <img src="/landing/app-qr.svg" alt="" width={168} height={168} />
        <span><strong>{t('landing.more.qr.title')}</strong><span>{t('landing.more.qr.body')}</span></span>
      </div>
      <div className="phone-app__buttons">
        <a className="button button--apple" href={appStore} target="_blank" rel="noopener noreferrer"><PlatformLogo name="apple" />{t('landing.more.app')}</a>
        <a className="button button--android" href={phoneAppAndroidLink(source)} onClick={() => trackAction('android-test-open', undefined, 'get-app')}>
          <PlatformLogo name="android" />{t('landing.more.android')}
        </a>
      </div>
      <AndroidRobotCredit className="phone-app__credit" />
    </main>
  </div>;
}

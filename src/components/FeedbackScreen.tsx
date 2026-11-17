'use client';

import { useSyncExternalStore } from 'react';
import { AppearanceProvider } from '../lib/appearance';
import { I18nProvider, LanguageControl, type Locale, type MessageKey, type Messages, useI18n } from '../i18n';
import { ParcelIllustration } from './Icon';
import { PeekLockup } from './PeekMark';

function onConnectivity(notify: () => void): () => void {
  window.addEventListener('online', notify);
  window.addEventListener('offline', notify);
  return () => {
    window.removeEventListener('online', notify);
    window.removeEventListener('offline', notify);
  };
}

/** Whether the device says it is online; the server's page says nothing of it. */
const useOnline = () => useSyncExternalStore(onConnectivity, () => navigator.onLine, () => false);

type FeedbackProps = { title: MessageKey; description: MessageKey; retry?: () => void };
export function FeedbackScreen({ initialLocale, initialMessages, ...props }: FeedbackProps & { initialLocale?: Locale; initialMessages?: Messages }) {
  return <I18nProvider initialLocale={initialLocale} initialMessages={initialMessages}><AppearanceProvider><FeedbackContent {...props} /></AppearanceProvider></I18nProvider>;
}
function FeedbackContent({ title, description, retry }: FeedbackProps) {
  const { t } = useI18n();
  // The offline page on a device that is online: the server is what is down, for maintenance.
  const online = useOnline();
  const down = title === 'maintenance.title' || (title === 'offline.title' && online);
  return <main className="auth-screen"><div className="feedback-language"><LanguageControl /></div>
    <section className="auth-card" aria-labelledby="feedback-title">
      <ParcelIllustration />
      <p className="auth-card__eyebrow"><PeekLockup /></p>
      <h1 id="feedback-title">{t(down ? 'maintenance.title' : title)}</h1><p className="auth-card__intro">{t(down ? 'maintenance.description' : description)}</p>
      {retry ? <button type="button" className="button button--primary" onClick={retry}>{t('app.tryAgain')}</button>
        // A page the server could not answer for maintenance is asked for again as it is.
        : title === 'maintenance.title' ? <button type="button" className="button button--primary" onClick={() => window.location.reload()}>{t('app.tryAgain')}</button>
        // A document navigation also retries connectivity from the offline fallback.
        // eslint-disable-next-line @next/next/no-html-link-for-pages
        : <a className="button button--primary" href="/">{t(title === 'offline.title' ? 'app.tryAgain' : 'web.backHome')}</a>}
    </section>
  </main>;
}

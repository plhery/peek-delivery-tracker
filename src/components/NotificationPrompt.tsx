import { useEffect, useRef, useState, useSyncExternalStore } from 'react';
import { useI18n } from '../i18n';
import type { ApiAuth } from '../lib/apiClient';
import { trackAction } from '../lib/analytics';
import { appStoreLink, onIPad } from '../lib/appStore';
import {
  dismissNotificationInvitation,
  notificationInvitationDismissed,
  subscribeToNotificationInvitation,
} from '../lib/notificationInvitation';
import { enablePushNotifications, inspectPushState, type PushState } from '../lib/pushNotifications';
import { useSite } from '../SiteContext';
import { HomeScreenSteps } from './HomeScreenSteps';
import { Icon } from './Icon';
import './NotificationPrompt.css';

/** A nonmodal invitation: no automatic permission request or focus change. */
export function NotificationPrompt({ apiAuth, eligible }: { apiAuth: ApiAuth; eligible: boolean }) {
  const { t, locale } = useI18n();
  const { appStore } = useSite();
  const dismissed = useSyncExternalStore(
    subscribeToNotificationInvitation,
    () => notificationInvitationDismissed(apiAuth.userId),
    () => true,
  );
  const [closed, setClosed] = useState(false);
  const [state, setState] = useState<PushState | null>(null);
  const [help, setHelp] = useState(false);
  const [busy, setBusy] = useState(false);
  const [failed, setFailed] = useState(false);
  const inFlight = useRef(false);
  const alive = useRef(true);
  const revision = useRef(0);
  const panel = useRef<HTMLElement>(null);
  const primary = useRef<HTMLButtonElement>(null);

  useEffect(() => {
    alive.current = true;
    return () => { alive.current = false; };
  }, []);

  // The Home Screen link that opened the steps is gone; its tap carries on to "Got it".
  useEffect(() => { if (help) primary.current?.focus({ preventScroll: true }); }, [help]);

  useEffect(() => {
    if (!eligible || dismissed || closed) return;
    let cancelled = false;
    const inspect = () => {
      if (document.visibilityState === 'hidden' || inFlight.current) return;
      const current = ++revision.current;
      void inspectPushState(apiAuth).then((next) => {
        if (!cancelled && revision.current === current) setState(next);
      }).catch(() => {
        if (!cancelled && revision.current === current) setState(null);
      });
    };
    inspect();
    window.addEventListener('focus', inspect);
    document.addEventListener('visibilitychange', inspect);
    return () => {
      cancelled = true;
      window.removeEventListener('focus', inspect);
      document.removeEventListener('visibilitychange', inspect);
    };
  }, [apiAuth, eligible, dismissed, closed]);

  function dismiss() {
    // Return focus only when it would otherwise be stranded in the removed popup.
    if (panel.current?.contains(document.activeElement)) {
      document.querySelector<HTMLButtonElement>('.account-trigger')?.focus({ preventScroll: true });
    }
    dismissNotificationInvitation(apiAuth.userId);
    setClosed(true);
  }

  function getApp() {
    trackAction('notifications-app-get');
    // Close after the tap: a link taken out of the page before then follows nowhere.
    window.setTimeout(dismiss);
  }

  async function enable() {
    if (state?.kind !== 'prompt' || inFlight.current) return;
    inFlight.current = true;
    ++revision.current;
    setBusy(true);
    setFailed(false);
    try {
      // Keep this call directly in the click handler for browser user activation.
      await enablePushNotifications(state.publicKey, apiAuth, locale);
      trackAction('notifications-enable', 'success');
      if (alive.current) dismiss();
    } catch {
      trackAction('notifications-enable', 'error');
      if (!alive.current) return;
      if (Notification.permission !== 'granted') dismiss();
      else setFailed(true);
    } finally {
      inFlight.current = false;
      if (alive.current) setBusy(false);
    }
  }

  if (!eligible || dismissed || closed || (state?.kind !== 'prompt' && state?.kind !== 'install')) return null;
  // Outside the Home Screen, the app comes first and the Home Screen's steps a link below it; without an app, the steps.
  const steps = help || (state.kind === 'install' && !appStore);
  const offer = state.kind === 'install' && !steps ? appStore : null;
  const [orBefore, orAfter] = t('notifications.prompt.homeScreen').split('{{link}}');

  return (
    <section
      ref={panel}
      className="notification-prompt"
      aria-labelledby="notification-prompt-title"
      onKeyDown={(event) => {
        if (event.key === 'Escape' && !busy) { event.stopPropagation(); dismiss(); }
      }}
    >
      <button className="notification-prompt__close" type="button" aria-label={t('common.close')} disabled={busy} onClick={dismiss}>
        <Icon name="close" />
      </button>
      <h2 id="notification-prompt-title">{t(steps ? 'notifications.prompt.installTitle' : 'notifications.prompt.title')}</h2>
      {steps ? <HomeScreenSteps then="notifications" /> : <p>{t('notifications.prompt.description')}</p>}
      {failed && <p className="notification-prompt__error" role="alert">{t('notifications.error.enable')}</p>}
      <div className="notification-prompt__actions">
        {offer
          ? <a className="button button--primary" href={appStoreLink(offer, 'web-invite')} onClick={getApp}>
            {t(onIPad() ? 'notifications.prompt.app.ipad' : 'notifications.prompt.app')}
          </a>
          : <button ref={primary} className="button button--primary" type="button" disabled={busy} aria-busy={busy} onClick={steps ? dismiss : () => void enable()}>
            {t(steps ? 'notifications.prompt.gotIt' : busy ? 'notifications.enabling' : failed ? 'common.retry' : 'notifications.enable')}
          </button>}
        {!steps && <button className="notification-prompt__later" type="button" disabled={busy} onClick={dismiss}>{t('onboarding.notifications.notNow')}</button>}
      </div>
      {offer && <p className="notification-prompt__or">
        {orBefore}<button type="button" onClick={() => setHelp(true)}>{t('notifications.prompt.homeScreenLink')}</button>{orAfter}
      </p>}
    </section>
  );
}

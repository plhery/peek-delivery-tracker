import { useState, type ReactNode } from 'react';
import { useI18n } from '../i18n';
import { homeScreenPath } from '../lib/homeScreen';
import { HomeScreenSteps } from './HomeScreenSteps';
import { PeekMark } from './PeekMark';
import './AppOffer.css';

/**
 * Notifications in the Peek app, offered where Safari notifies only from the
 * Home Screen: Peek's mark, what comes along into the app, and its link, "Get"
 * to the App Store or "Open" in the app.
 */
export function AppOffer({ title, body, className = '', children }: {
  /** Notifications in the Peek app, unless the row offers something else. */
  title?: string;
  className?: string;
  body: string;
  /** The link. */
  children: ReactNode;
}) {
  const { t } = useI18n();
  return <div className={`app-offer ${className}`.trim()}>
    <span aria-hidden="true"><PeekMark size={30} /></span>
    <span><strong>{title ?? t('alerts.app.title')}</strong><small>{body}</small></span>
    {children}
  </div>;
}

/**
 * Beside the app, the Home Screen is the other way: one line, with its steps a
 * tap away. An app's own browser, which has no way to the Home Screen, gets the
 * way to Safari instead.
 */
export function HomeScreenInstead({ text, then }: {
  text: string;
  then: 'ping' | 'notifications';
}) {
  const { t } = useI18n();
  const [steps, setSteps] = useState(false);
  if (homeScreenPath() === 'safari') return <HomeScreenSteps then={then} cards />;
  return <>
    <p className="app-offer__instead">{text}{!steps && <> <button type="button" onClick={() => setSteps(true)}>{t('alerts.app.steps')}</button></>}</p>
    {steps && <HomeScreenSteps then={then} cards />}
  </>;
}

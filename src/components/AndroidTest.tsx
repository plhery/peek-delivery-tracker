import { useEffect, useState, useSyncExternalStore } from 'react';
import { useI18n } from '../i18n';
import { trackAction, type AnalyticsSpot } from '../lib/analytics';
import {
  ANDROID_TEST_OPEN, ANDROID_TEST_PATH, androidTestShows, closeAndroidTestThanks, neverAndroidTest, noteAndroidAppReferrer,
  noteAndroidTestThanked, readAndroidDevice, readAndroidTestNote, restartAndroidTest, snoozeAndroidTest, subscribeAndroidTest,
  type AndroidTestShows,
} from '../lib/androidTest';
import { Toast } from '../peek/parcel/Toast';
import { AppOffer } from './AppOffer';
import { Icon } from './Icon';
import { PeekMark } from './PeekMark';
import './AndroidTest.css';

/** How long the toast after × stays. */
const HIDDEN_TOAST_MS = 6_000;

/** Whether this device can take the app, once the browser has said; read once per page load. */
let device: boolean | null = null;
let asking: Promise<void> | null = null;
const deviceListeners = new Set<() => void>();

function subscribeToDevice(listener: () => void) {
  deviceListeners.add(listener);
  asking ??= readAndroidDevice().catch(() => false).then((answer) => {
    device = answer;
    for (const each of [...deviceListeners]) each();
  });
  return () => { deviceListeners.delete(listener); };
}

const off = () => () => undefined;
const nothing = () => null;
/** The places already counted as shown on this page load. */
const counted = new Set<AnalyticsSpot>();

/**
 * What a place shows of the invitation to test Peek for Android, and the toast after its ×. Nothing is drawn by the
 * server, nor before the browser has said what it runs on, nor while the invitation is switched off.
 */
function useAndroidTest(spot: AnalyticsSpot, { thanks }: { thanks: boolean }) {
  const note = useSyncExternalStore(ANDROID_TEST_OPEN ? subscribeAndroidTest : off, ANDROID_TEST_OPEN ? readAndroidTestNote : nothing, nothing);
  const known = useSyncExternalStore(ANDROID_TEST_OPEN ? subscribeToDevice : off, ANDROID_TEST_OPEN ? () => device : nothing, nothing);
  const [now] = useState(() => Date.now());
  const [hidden, setHidden] = useState(false);
  const shows: AndroidTestShows = note && known !== null
    ? androidTestShows(note, { open: ANDROID_TEST_OPEN, device: known, now, thanks })
    : null;

  useEffect(() => { if (ANDROID_TEST_OPEN) noteAndroidAppReferrer(document.referrer); }, []);
  useEffect(() => {
    if (shows === 'thanks') noteAndroidTestThanked();
    if (shows !== 'invite' || counted.has(spot)) return;
    counted.add(spot);
    trackAction('android-test-view', undefined, spot);
  }, [shows, spot]);
  useEffect(() => {
    if (!hidden) return;
    const timer = setTimeout(() => setHidden(false), HIDDEN_TOAST_MS);
    return () => clearTimeout(timer);
  }, [hidden]);

  return {
    shows,
    open: () => trackAction('android-test-open', undefined, spot),
    snooze: () => { trackAction('android-test-dismiss', undefined, spot); snoozeAndroidTest(); setHidden(true); },
    closeThanks: closeAndroidTestThanks,
    // The page goes to the steps: the invitation the restart brings back isn't counted as shown on its way out.
    retry: () => { counted.add(spot); trackAction('android-test-open', undefined, spot); restartAndroidTest(); },
    toast: hidden && <HiddenToast spot={spot} onDone={() => setHidden(false)} />,
  };
}

/** After ×: hidden for a week, and the way to never see it again. */
function HiddenToast({ spot, onDone }: { spot: AnalyticsSpot; onDone: () => void }) {
  const { t } = useI18n();
  return <Toast mark={<Icon name="clock" />} action={<button type="button" onClick={() => {
    trackAction('android-test-never', undefined, spot);
    neverAndroidTest();
    onDone();
  }}>{t('androidTest.never')}</button>}>{t('androidTest.hidden')}</Toast>;
}

function PhoneGlyph() {
  return <svg viewBox="0 0 24 24" aria-hidden="true"><rect x="6" y="2" width="12" height="20" rx="3" /><path d="M11 18h2" /></svg>;
}

function CloseButton({ label, onClick }: { label: string; onClick: () => void }) {
  return <button type="button" className="android-test__close" aria-label={label} onClick={onClick}><Icon name="close" /></button>;
}

/** The thanks, after the install link was followed, with the way back to the steps for when it didn't work. */
function Thanks({ banner, onClose, onRetry }: { banner?: boolean; onClose: () => void; onRetry: () => void }) {
  const { t } = useI18n();
  return <div className={banner ? 'android-test-banner android-test-banner--thanks' : 'android-test-row android-test-row--thanks'}>
    <span className="android-test__done" aria-hidden="true"><Icon name="check" /></span>
    <span className="android-test__words">
      <strong>{t('androidTest.thanks.title')}</strong>
      {!banner && <small>{t('androidTest.thanks.body')}</small>}
      <a className="android-test__retry" href={ANDROID_TEST_PATH} onClick={onRetry}>{t('androidTest.thanks.retry')}</a>
    </span>
    <CloseButton label={t('common.close')} onClick={onClose} />
  </div>;
}

/** P1: a row on the parcel page, under its actions. */
export function AndroidTestRow() {
  const { t } = useI18n();
  const { shows, open, snooze, closeThanks, retry, toast } = useAndroidTest('parcel', { thanks: true });
  return <>
    {shows === 'invite' && <div className="android-test-row">
      <a className="android-test-row__link" href={ANDROID_TEST_PATH} onClick={open}>
        <span className="android-test-row__mark" aria-hidden="true"><PeekMark size={46} /><span><PhoneGlyph /></span></span>
        <span className="android-test__words">
          {/* The space is for a screen reader, which reads the two as the link's name. */}
          <strong>{t('androidTest.invite.title')}</strong>{' '}
          <span className="android-test-row__action">{t('androidTest.invite.action')}<Icon name="chevron" /></span>
        </span>
      </a>
      <CloseButton label={t('androidTest.invite.later')} onClick={snooze} />
    </div>}
    {shows === 'thanks' && <Thanks onClose={closeThanks} onRetry={retry} />}
    {toast}
  </>;
}

/** P2: a row in the parcel's "Notify me". It has no ×: the sheet closes. */
export function AndroidTestOffer() {
  const { t } = useI18n();
  const { shows, open } = useAndroidTest('alerts', { thanks: false });
  if (shows !== 'invite') return null;
  return <AppOffer className="android-test-offer" title={t('androidTest.alerts.title')} body={t('androidTest.alerts.body')}>
    <a href={ANDROID_TEST_PATH} onClick={open}>{t('androidTest.alerts.action')}</a>
  </AppOffer>;
}

/** P3: a slim banner at the top of the home page. */
export function AndroidTestBanner() {
  const { t } = useI18n();
  const { shows, open, snooze, closeThanks, retry, toast } = useAndroidTest('home', { thanks: true });
  return <>
    {shows === 'invite' && <div className="android-test-banner">
      <span className="android-test-banner__glyph" aria-hidden="true"><PhoneGlyph /></span>
      <a className="android-test-banner__link" href={ANDROID_TEST_PATH} onClick={open}>
        <strong>{t('androidTest.banner.title')}</strong>{' '}
        <span>{t('androidTest.banner.action')}<Icon name="chevron" /></span>
      </a>
      <CloseButton label={t('androidTest.invite.later')} onClick={snooze} />
    </div>}
    {shows === 'thanks' && <Thanks banner onClose={closeThanks} onRetry={retry} />}
    {toast}
  </>;
}

import { useId, useState, useSyncExternalStore } from 'react';
import { localizedDeliveryEstimate, useI18n } from '../i18n';
import { trackAction } from '../lib/analytics';
import type { ApiAuth } from '../lib/apiClient';
import { carrierBrand } from '../lib/carrierBrand';
import { carrierInfo, displayedCarrierId } from '../lib/carriers';
import { parcelDeliveryEstimate } from '../lib/parcelStatus';
import {
  parcelPeekAi, peekAiCarrierWord, peekAiClaim, peekAiHeading, peekAiRecord, peekAiStrip, peekAiStripSpoken, peekAiWhy, useShowPeekAi,
  useShowPeekAiChoice,
  type PeekAiWording,
} from '../lib/peekAi';
import type { ParcelWithEvents, PeekAi } from '../types';
import { Sheet, SwitchRow } from '../peek/parcel/Sheet';
import { Glyph } from '../peek/parcel/glyphs';
import { CarrierMark } from './CarrierMark';
import { ParcelIllustration } from './Icon';
import './PeekAi.css';

// Every card with an estimate shares one timer, set for the next minute; a page coming back into view looks again.
const minuteListeners = new Set<() => void>();
let minuteTimer: ReturnType<typeof setTimeout> | undefined;
const notifyMinute = () => minuteListeners.forEach((notify) => notify());
function waitForNextMinute() {
  minuteTimer = setTimeout(() => { notifyMinute(); waitForNextMinute(); }, 60_000 - (Date.now() % 60_000) + 20);
}
function lookOnReturn() {
  if (document.visibilityState === 'visible') notifyMinute();
}
function subscribeToMinutes(notify: () => void) {
  minuteListeners.add(notify);
  if (minuteListeners.size === 1) {
    waitForNextMinute();
    document.addEventListener('visibilitychange', lookOnReturn);
  }
  return () => {
    minuteListeners.delete(notify);
    if (minuteListeners.size) return;
    clearTimeout(minuteTimer);
    document.removeEventListener('visibilitychange', lookOnReturn);
  };
}
const unwatched = () => () => {};
const thisMinute = () => Math.floor(Date.now() / 60_000);

/**
 * The time to the minute, looked at again each minute while `watching`: on a list or page left open, an estimate goes
 * when its hour or its day passes, and "5 hours ago" moves on.
 */
function usePeekAiNow(watching: boolean): number {
  return useSyncExternalStore(watching ? subscribeToMinutes : unwatched, thisMinute, thisMinute) * 60_000;
}

/** Peek AI's estimate for a parcel, while it holds and the reader shows them. */
export function useParcelPeekAi(parcel: ParcelWithEvents): PeekAi | null {
  const shown = useShowPeekAi();
  const now = usePeekAiNow(shown && !!parcel.peekAi);
  return shown ? parcelPeekAi(parcel, now) : null;
}

/** Peek AI's sparkle. */
export function PeekAiSparkle() {
  return <svg className="peek-ai-sparkle" viewBox="0 0 24 24" aria-hidden="true">
    <path fill="currentColor" d="M10 2.5c.5 4.6 2.4 6.5 7 7-4.6.5-6.5 2.4-7 7-.5-4.6-2.4-6.5-7-7 4.6-.5 6.5-2.4 7-7Zm8.5 11c.3 2.3 1.2 3.2 3.5 3.5-2.3.3-3.2 1.2-3.5 3.5-.3-2.3-1.2-3.2-3.5-3.5 2.3-.3 3.2-1.2 3.5-3.5Z" />
  </svg>;
}

/** "✦ Peek AI" in a pill a shade darker than what it sits on, then its words: Peek's, never the carrier's. */
export function PeekAiPhrase({ words, className = '' }: { words: string; className?: string }) {
  const { t } = useI18n();
  return <span className={`peek-ai-phrase ${className}`}>
    <span className="peek-ai-sig"><PeekAiSparkle />{t('peekAi.name')}</span>
    <span className="peek-ai-phrase__words">{words}</span>
  </span>;
}

const line = (d: string) => <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true"><path d={d} /></svg>;
const BULB = 'M12 3a6 6 0 0 0-3.5 10.9V16h7v-2.1A6 6 0 0 0 12 3ZM9.5 20h5';
const CHART = 'M4 19V5m0 14h16M8 15v-3m4 3V8m4 7v-5';
const EYE = 'M2.5 12s3.5-6.5 9.5-6.5S21.5 12 21.5 12s-3.5 6.5-9.5 6.5S2.5 12 2.5 12Zm9.5 2.8a2.8 2.8 0 1 0 0-5.6 2.8 2.8 0 0 0 0 5.6Z';

/** The days Peek AI expects, the carrier's date ringed; or, out for delivery, the hours. */
function PeekAiStrip({ ai, carrier, wording }: { ai: PeekAi; carrier: string; wording: PeekAiWording }) {
  const { t } = wording;
  const strip = peekAiStrip(ai, wording);
  if (!strip) return null;
  // Its tints and ring say which days are likely and which is the carrier's: a screen reader hears them in words.
  return <div className="peek-ai-strip">
    <p className="sr-only">{peekAiStripSpoken(strip, carrier, wording)}</p>
    <div className="peek-ai-strip__cells" aria-hidden="true">
      {strip.cells.map((cell) => <span key={cell.key} data-likely={cell.likely || undefined} data-maybe={cell.maybe || undefined}
        data-carrier={cell.carrier || undefined} data-past={cell.past || undefined}>
        {cell.over && <span>{cell.over}</span>}<b>{cell.main}</b>
      </span>)}
    </div>
    <div className="peek-ai-strip__key" aria-hidden="true">
      <span><i data-likely="" />{t(strip.kind === 'hours' ? 'peekAi.sheet.hours' : 'peekAi.sheet.days')}</span>
      {strip.cells.some((cell) => cell.carrier) && <span><i data-carrier="" />{t('peekAi.sheet.carrierDate', { carrier })}</span>}
    </div>
  </div>;
}

/**
 * The (i): what Peek AI expects on the parcel's own card, with the carrier's word under it, the days or hours, why, how
 * often it is right, the switch that hides it, and what it is.
 */
export function PeekAiSheet({ parcel, ai, auth, onClose }: { parcel: ParcelWithEvents; ai: PeekAi; auth?: ApiAuth; onClose: () => void }) {
  const { t, locale, languageTag, use24HourTime } = useI18n();
  const caption = useId();
  const [shown, setShown] = useShowPeekAiChoice(auth);
  const carrier = carrierInfo(displayedCarrierId(parcel), locale);
  const wording: PeekAiWording = { t, languageTag, use24HourTime };
  const estimate = parcelDeliveryEstimate(parcel);
  const carrierDate = estimate ? localizedDeliveryEstimate(parcel.expectedDeliveryFrom, estimate, t, languageTag, undefined, use24HourTime) : null;
  return <Sheet title={t('peekAi.name')} className="peek-ai-sheet" onClose={onClose}>
    <div role="group" aria-labelledby={caption}>
      <p className="peeks-preview__caption" id={caption}>{t('peekAi.sheet.caption')}</p>
      <div className="peeks-preview" style={carrierBrand(carrier).style}>
        <div className="peeks-preview__text">
          <CarrierMark carrier={carrier} />
          <strong>{peekAiHeading(ai, wording)}</strong>
          <span className="peeks-preview__detail">{peekAiCarrierWord(ai, carrier.name, carrierDate, wording)}</span>
        </div>
        <span className="peeks-preview__pip"><ParcelIllustration /></span>
      </div>
    </div>
    <PeekAiStrip ai={ai} carrier={carrier.name} wording={wording} />
    <div className="peeks-switches">
      <div className="peeks-switch peek-ai-fact"><div>
        <span className="peeks-switch__icon">{line(BULB)}</span>
        <span className="peeks-switch__text"><strong>{t('peekAi.sheet.why')}</strong><small>{peekAiWhy(ai, carrier.name, wording)}</small></span>
      </div></div>
      <div className="peeks-switch peek-ai-fact"><div>
        <span className="peeks-switch__icon">{line(CHART)}</span>
        <span className="peeks-switch__text"><strong>{t('peekAi.sheet.sure')}</strong><small>{peekAiRecord(ai, wording)}</small></span>
      </div></div>
      <SwitchRow icon={line(EYE)} title={t('peekAi.sheet.show')} hint={t('peekAi.sheet.showHint')} checked={shown}
        onChange={(next) => { setShown(next); trackAction(next ? 'peek-ai-on' : 'peek-ai-off'); }} />
    </div>
    <p className="peek-ai-sheet__trained">{t('peekAi.sheet.trained', { carrier: carrier.name })}</p>
  </Sheet>;
}

/**
 * Peek AI on the parcel's page: a short card under the parcel's, "✦ Peek AI today is unlikely (i)" and one line of why.
 * Its sheet stays when Peek AI is switched off there, and goes with the estimate once its hour or day has passed.
 */
export function PeekAiCard({ parcel, auth, className = '' }: { parcel: ParcelWithEvents; auth?: ApiAuth; className?: string }) {
  const { t, locale, languageTag, use24HourTime } = useI18n();
  const shown = useShowPeekAi();
  const [open, setOpen] = useState(false);
  const now = usePeekAiNow(!!parcel.peekAi && (shown || open));
  const current = parcelPeekAi(parcel, now);
  if (open && !current) setOpen(false);
  const ai = shown ? current : null;
  if (!ai && !(open && current)) return null;
  const carrier = carrierInfo(displayedCarrierId(parcel), locale).name;
  const wording: PeekAiWording = { t, languageTag, use24HourTime, now };
  return <>
    {ai && <div className={`peekp-note peek-ai-card ${className}`} role="note">
      <div className="peek-ai-card__head">
        <PeekAiPhrase words={peekAiClaim(ai, wording)} />
        <button type="button" className="peek-ai-card__info" aria-label={t('peekAi.about')}
          onClick={() => { setOpen(true); trackAction('peek-ai-open'); }}><Glyph name="info" /></button>
      </div>
      <p>{peekAiWhy(ai, carrier, wording)}</p>
    </div>}
    {open && current && <PeekAiSheet parcel={parcel} ai={current} auth={auth} onClose={() => setOpen(false)} />}
  </>;
}

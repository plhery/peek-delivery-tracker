import { useState, useSyncExternalStore } from 'react';
import { useDateTitle } from '../lib/dateTitle';
import { dayAndTime, localizedCalendarDate, localizedClockTime, scanDate, timeZoneName } from '../lib/format';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import { localizedEventDescription, localizedScanWording, stageLabel, useI18n } from '../i18n';
import { scanPlace } from '../lib/scanPlace';
import { currentEvent, sortEventsDesc, withRelays } from '../lib/stages';
import type { EventPlace as EventPlaceData, PlannedEvent, TrackingEvent, WrittenPlace } from '../types';

/** A folded journal keeps this many days open, and this many more as one line each. */
const FOLD_FROM_EVENTS = 12;
const OPEN_DAYS = 2;
const LISTED_DAYS = 3;

/**
 * The whole journey grouped by local calendar day, newest first. With `fold`,
 * a long journey shows its newest days, the days before as one line each to
 * open, and keeps the oldest behind a button. One line opens it when the
 * carrier's site shows steps that are no scans: those it planned (`planned`)
 * and those it wrote in advance whose time has passed (`written`). It says
 * they're only predictions and keeps them folded behind it, apart from the
 * scans. For a carrier that writes its route ahead (`writtenAheadBy`, its name),
 * the line names it and says its site lists the whole route in advance, delivery
 * included, and it shows even with no step to list.
 */
export function TrackingJournal({ events: allEvents, planned = [], written = [], writtenAheadBy, syncing = false, fold = false, returnHeld = false }: {
  events: TrackingEvent[]; planned?: PlannedEvent[]; written?: PlannedEvent[]; writtenAheadBy?: string; syncing?: boolean; fold?: boolean;
  /** The server holds the parcel's return (`currentEvent`). */
  returnHeld?: boolean;
}) {
  const { languageTag, locale, t, use24HourTime } = useI18n();
  const dateTitle = useDateTitle();
  const [oldestShown, setOldestShown] = useState(false);
  const { listed: events, relays } = withRelays(allEvents);
  const current = currentEvent(events, returnHeld);
  const wording = (event: TrackingEvent) => localizedScanWording(event, t, locale) || stageLabel(t, event.stage);
  const groups = new Map<string, { date: Date | null; events: TrackingEvent[] }>();
  for (const event of sortEventsDesc(events)) {
    const date = scanDate(event)?.date ?? null;
    const key = date ? `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}` : event.occurredAt;
    if (!groups.has(key)) groups.set(key, { date, events: [] });
    groups.get(key)!.events.push(event);
  }
  const now = new Date();
  const predicted = [...planned.filter((step) => plannedAhead(step, now.getTime())), ...written];
  const note = writtenAheadBy ? t('timeline.writtenAhead.note', { carrier: writtenAheadBy }) : t('timeline.predicted.note');
  const yesterday = new Date(now.getFullYear(), now.getMonth(), now.getDate() - 1);
  const sameDay = (a: Date, b: Date) => a.toDateString() === b.toDateString();
  const dayLabel = (date: Date | null, fallback: string) => !date ? fallback
    : sameDay(date, now) ? t('time.today') : sameDay(date, yesterday) ? t('time.yesterday')
      : localizedCalendarDate(date, languageTag, now.getTime());
  const updates = (count: number) => t(count === 1 ? 'detail.updateCount.one' : 'detail.updateCount.many', { count });
  const zone = useReaderTimeZone(languageTag);
  // The times are the reader's, wherever the carrier scanned: one quiet line says so under the history.
  const timed = events.some((event) => scanDate(event)?.timed) || predicted.some((step) => scanDate(step)?.timed);

  // A scan dated by its day alone can sort among the day before's scans, west of the carrier's zone.
  const days = [...groups].sort(([, a], [, b]) => (b.date?.getTime() ?? 0) - (a.date?.getTime() ?? 0));
  const folded = fold && events.length > FOLD_FROM_EVENTS && days.length > OPEN_DAYS;
  const listed = folded && !oldestShown ? days.slice(0, OPEN_DAYS + LISTED_DAYS) : days;
  const oldest = days.slice(listed.length).reduce((count, [, group]) => count + group.events.length, 0);

  const scans = (group: { events: TrackingEvent[] }) => <ol className="tracking-journal__events">
    {group.events.map(event => {
      const when = scanDate(event);
      const isCurrent = event.id === current?.id;
      // A copy's words stay in sight, in case it was a scan of its own; words composed another way are the same words.
      const seen = new Set([wording(event).normalize('NFC')]);
      const relayed = (relays.get(event.id) ?? []).map(wording).filter(line => {
        const words = line.normalize('NFC');
        if (seen.has(words)) return false;
        seen.add(words);
        return true;
      });
      return <li key={event.id} className={isCurrent ? 'tracking-journal__current' : undefined} aria-current={isCurrent ? 'step' : undefined}>
        {/* A scan dated by its day alone has no time to show. */}
        {when && !when.timed ? <time dateTime={event.day} />
          : <time dateTime={when ? event.occurredAt : undefined} title={dateTitle(when)}>{when ? localizedClockTime(when.date, languageTag, use24HourTime) : '—'}</time>}
        <div><p>{syncing && isCurrent && event.stage === 'pending' ? t('timeline.syncing') : wording(event)}</p>
          {event.location?.trim() && <EventPlace location={event.location} placed={event.place} written={event.writtenPlace} />}
          {relayed.map(line => <p key={line} className="tracking-journal__relay">{line}</p>)}
        </div>
      </li>;
    })}
  </ol>;

  return <details className={use24HourTime ? 'tracking-journal' : 'tracking-journal tracking-journal--am-pm'} open>
    <summary><span>{t('timeline.label')}</span><span className="tracking-journal__count">{updates(events.length)}<svg aria-hidden="true" viewBox="0 0 20 20"><path d="m5 8 5 5 5-5" /></svg></span></summary>
    {predicted.length > 0 ? <PredictedSteps steps={predicted} note={note} />
      : writtenAheadBy && <p className="tracking-journal__predicted-note">{note}</p>}
    {!events.length ? <p className="timeline-empty">{t(syncing ? 'timeline.emptySyncing' : 'timeline.empty')}</p>
      : <ol className="tracking-journal__days" aria-label={t('timeline.label')}>
        {listed.map(([key, group], index) => <li key={key}>
          {folded && index >= OPEN_DAYS && index < OPEN_DAYS + LISTED_DAYS ? <details className="tracking-journal__day">
            <summary><h3 title={dateTitle(group.date && { date: group.date })}>{dayLabel(group.date, key)}</h3><span className="tracking-journal__count">{updates(group.events.length)}<svg aria-hidden="true" viewBox="0 0 20 20"><path d="m8 5 5 5-5 5" /></svg></span></summary>
            {scans(group)}
          </details> : <>
            <h3 title={dateTitle(group.date && { date: group.date })}>{dayLabel(group.date, key)}</h3>
            {scans(group)}
          </>}
        </li>)}
      </ol>}
    {oldest > 0 && <button type="button" className="tracking-journal__oldest" onClick={() => setOldestShown(true)}>
      <svg aria-hidden="true" viewBox="0 0 20 20"><path d="m5 8 5 5 5-5" /></svg>{t('link.showOldest.many', { count: oldest })}
    </button>}
    {timed && zone && <p className="tracking-journal__zone">{t('timeline.timeZone', { zone })}</p>}
  </details>;
}

const DAY_MS = 24 * 60 * 60 * 1000;

const never = () => () => undefined;

/**
 * The zone the reader's browser shows times in, named in the reader's language; null on the server and while the page
 * comes alive, which only know the server's zone: a page drawn there names none rather than a wrong one.
 */
function useReaderTimeZone(languageTag: string): string | null {
  return useSyncExternalStore(never, () => timeZoneName(languageTag), () => null);
}

/**
 * Whether a planned step's time is still ahead, as the server serves it (plannedSteps.ts): a step dated by its day
 * alone stands through that day, which starts at `occurredAt` in the carrier's zone. One whose time passes while the
 * page is open goes at the next render, so a carrier's pre-written "Delivered" never shows as planned for a time gone by.
 */
export function plannedAhead(step: Pick<PlannedEvent, 'occurredAt' | 'day' | 'expiresAt'>, now: number): boolean {
  if (step.expiresAt) return Date.parse(step.expiresAt) > now;
  const at = Date.parse(step.occurredAt);
  return Number.isFinite(at) && (step.day ? at + DAY_MS : at) > now;
}

/**
 * The steps the carrier's site shows that no scan confirms, behind one line (`note`) that says they're only predictions:
 * opened, they list newest first, greyed, each on a dashed mark instead of a time, with the day it was predicted for,
 * or, once that day has passed, the day it was written in advance for. They are no scans and count as no update.
 */
function PredictedSteps({ steps, note }: { steps: PlannedEvent[]; note: string }) {
  const { languageTag, t, use24HourTime } = useI18n();
  const dateTitle = useDateTitle();
  const now = new Date();
  const offset = (days: number) => new Date(now.getFullYear(), now.getMonth(), now.getDate() + days).toDateString();
  const when = (step: PlannedEvent) => {
    const scan = scanDate(step);
    if (!scan) return '';
    const { date, timed } = scan;
    const day = date.toDateString() === offset(0) ? t('time.today') : date.toDateString() === offset(1) ? t('time.tomorrow')
      : date.toDateString() === offset(-1) ? t('time.yesterday') : localizedCalendarDate(date, languageTag, now.getTime());
    return t(plannedAhead(step, now.getTime()) ? 'timeline.predicted.when' : 'timeline.writtenAhead.when',
      { when: timed ? dayAndTime(day, localizedClockTime(date, languageTag, use24HourTime), languageTag) : day });
  };
  const newestFirst = [...steps].sort((a, b) => (scanDate(b)?.date.getTime() ?? 0) - (scanDate(a)?.date.getTime() ?? 0));
  return <details className="tracking-journal__predicted">
    <summary><span className="tracking-journal__predicted-note">{note}</span>{' '}
      <span className="tracking-journal__predicted-show">{t('timeline.predicted.show')}</span>
      <span className="tracking-journal__predicted-hide">{t('timeline.predicted.hide')}</span></summary>
    <ol className="tracking-journal__events">
      {newestFirst.map((step, index) => <li key={`${step.occurredAt}-${index}`}>
        <svg className="tracking-journal__predicted-mark" aria-hidden="true" viewBox="0 0 12 12"><circle cx="6" cy="6" r="4.6" /></svg>
        <div>
          <p>{localizedEventDescription(step.description, t) || stageLabel(t, step.stage)}</p>
          <time className="tracking-journal__predicted-when" dateTime={step.day ?? step.occurredAt} title={dateTitle(scanDate(step))}>{when(step)}</time>
          {step.location?.trim() && <EventPlace location={step.location} placed={step.place} written={step.writtenPlace} />}
        </div>
      </li>)}
    </ol>
  </details>;
}

/**
 * The flag leads and the country closes the place, named in the reader's language, as the server read the words
 * (`written`): a final code that is also a state or canton ("Haiger, DE", "Wilmington, DE") names a country only when
 * the scan was placed in that country or one of the parcel's countries writes its regions so. A place written in a
 * script other than Latin and the reader's own is named by the town it was placed in (`scanPlace`), a planned step's
 * as a scan's.
 */
function EventPlace({ location, placed, written }: { location: string; placed?: EventPlaceData | null; written?: WrittenPlace }) {
  const { languageTag, locale } = useI18n();
  const { country, place } = scanPlace(location, placed, locale, written);
  if (!country) return <span className="tracking-journal__location">{place}</span>;
  const name = countryName(country, languageTag);
  return <span className="tracking-journal__location">
    <span className="tracking-journal__flag" aria-hidden="true">{countryFlag(country)}</span>
    {place ? `${place}, ${name}` : name}
  </span>;
}

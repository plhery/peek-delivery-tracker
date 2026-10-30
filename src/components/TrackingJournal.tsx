import { useState } from 'react';
import { localizedCalendarDate, scanDate } from '../lib/format';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import { localizedEventDescription, stageLabel, useI18n } from '../i18n';
import { scanPlace } from '../lib/scanPlace';
import { currentEvent, sortEventsDesc } from '../lib/stages';
import type { TrackingEvent } from '../types';

/** A folded journal keeps this many days open, and this many more as one line each. */
const FOLD_FROM_EVENTS = 12;
const OPEN_DAYS = 2;
const LISTED_DAYS = 3;

/**
 * The scans the journal lists, and the relay copies each one has: a scan of a
 * relay pair (`relayOf`) is a line under the scan it names, and keeps a row of
 * its own when that scan is not there.
 */
function withRelays(events: TrackingEvent[]): { listed: TrackingEvent[]; relays: Map<string, TrackingEvent[]> } {
  const byId = new Map(events.map(event => [event.id, event]));
  const relays = new Map<string, TrackingEvent[]>();
  const folded = new Set<TrackingEvent>();
  for (const event of events) {
    const scan = event.relayOf ? byId.get(event.relayOf) : undefined;
    if (!scan || scan === event || scan.relayOf) continue;
    relays.set(scan.id, [...(relays.get(scan.id) ?? []), event]);
    folded.add(event);
  }
  return { listed: events.filter(event => !folded.has(event)), relays };
}

/**
 * The whole journey grouped by local calendar day, newest first. With `fold`,
 * a long journey shows its newest days, the days before as one line each to
 * open, and keeps the oldest behind a button.
 */
export function TrackingJournal({ events: allEvents, syncing = false, fold = false }: { events: TrackingEvent[]; syncing?: boolean; fold?: boolean }) {
  const { languageTag, t } = useI18n();
  const [oldestShown, setOldestShown] = useState(false);
  const { listed: events, relays } = withRelays(allEvents);
  const current = currentEvent(events);
  const wording = (event: TrackingEvent) => localizedEventDescription(event.description, t) || stageLabel(t, event.stage);
  const groups = new Map<string, { date: Date | null; events: TrackingEvent[] }>();
  for (const event of sortEventsDesc(events)) {
    const date = scanDate(event)?.date ?? null;
    const key = date ? `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}` : event.occurredAt;
    if (!groups.has(key)) groups.set(key, { date, events: [] });
    groups.get(key)!.events.push(event);
  }
  const now = new Date();
  const yesterday = new Date(now.getFullYear(), now.getMonth(), now.getDate() - 1);
  const sameDay = (a: Date, b: Date) => a.toDateString() === b.toDateString();
  const dayLabel = (date: Date | null, fallback: string) => !date ? fallback
    : sameDay(date, now) ? t('time.today') : sameDay(date, yesterday) ? t('time.yesterday')
      : localizedCalendarDate(date, languageTag, date.getFullYear() !== now.getFullYear());
  const updates = (count: number) => t(count === 1 ? 'detail.updateCount.one' : 'detail.updateCount.many', { count });

  // A scan dated by its day alone can sort among the day before's scans, west of the carrier's zone.
  const days = [...groups].sort(([, a], [, b]) => (b.date?.getTime() ?? 0) - (a.date?.getTime() ?? 0));
  const folded = fold && events.length > FOLD_FROM_EVENTS && days.length > OPEN_DAYS;
  const listed = folded && !oldestShown ? days.slice(0, OPEN_DAYS + LISTED_DAYS) : days;
  const oldest = days.slice(listed.length).reduce((count, [, group]) => count + group.events.length, 0);

  const scans = (group: { events: TrackingEvent[] }) => <ol className="tracking-journal__events">
    {group.events.map(event => {
      const when = scanDate(event);
      const isCurrent = event.id === current?.id;
      // A copy's words stay in sight, in case it was a scan of its own.
      const relayed = [...new Set((relays.get(event.id) ?? []).map(wording))].filter(line => line !== wording(event));
      return <li key={event.id} className={isCurrent ? 'tracking-journal__current' : undefined} aria-current={isCurrent ? 'step' : undefined}>
        {/* A scan dated by its day alone has no time to show. */}
        {when && !when.timed ? <time dateTime={event.day} />
          : <time dateTime={when ? event.occurredAt : undefined}>{when ? new Intl.DateTimeFormat(languageTag, { hour: '2-digit', minute: '2-digit', hourCycle: 'h23' }).format(when.date) : '—'}</time>}
        <div><p>{syncing && isCurrent && event.stage === 'pending' ? t('timeline.syncing') : wording(event)}</p>
          {event.location?.trim() && <EventPlace location={event.location} placed={event.place?.country} />}
          {relayed.map(line => <p key={line} className="tracking-journal__relay">{line}</p>)}
        </div>
      </li>;
    })}
  </ol>;

  return <details className="tracking-journal" open>
    <summary><span>{t('timeline.label')}</span><span className="tracking-journal__count">{updates(events.length)}<svg aria-hidden="true" viewBox="0 0 20 20"><path d="m5 8 5 5 5-5" /></svg></span></summary>
    {!events.length ? <p className="timeline-empty">{t(syncing ? 'timeline.emptySyncing' : 'timeline.empty')}</p>
      : <ol className="tracking-journal__days" aria-label={t('timeline.label')}>
        {listed.map(([key, group], index) => <li key={key}>
          {folded && index >= OPEN_DAYS && index < OPEN_DAYS + LISTED_DAYS ? <details className="tracking-journal__day">
            <summary><h3>{dayLabel(group.date, key)}</h3><span className="tracking-journal__count">{updates(group.events.length)}<svg aria-hidden="true" viewBox="0 0 20 20"><path d="m8 5 5 5-5 5" /></svg></span></summary>
            {scans(group)}
          </details> : <>
            <h3>{dayLabel(group.date, key)}</h3>
            {scans(group)}
          </>}
        </li>)}
      </ol>}
    {oldest > 0 && <button type="button" className="tracking-journal__oldest" onClick={() => setOldestShown(true)}>
      <svg aria-hidden="true" viewBox="0 0 20 20"><path d="m5 8 5 5 5-5" /></svg>{t('link.showOldest.many', { count: oldest })}
    </button>}
  </details>;
}

/**
 * The flag leads and the country closes the place, named in the reader's language. A final code
 * that is also a state or canton ("Haiger, DE", "Wilmington, DE") names a country only when the
 * scan was placed in that country.
 */
function EventPlace({ location, placed }: { location: string; placed?: string }) {
  const { languageTag } = useI18n();
  const { country, place } = scanPlace(location, placed);
  if (!country) return <span className="tracking-journal__location">{place}</span>;
  const name = countryName(country, languageTag);
  return <span className="tracking-journal__location">
    <span className="tracking-journal__flag" aria-hidden="true">{countryFlag(country)}</span>
    {place ? `${place}, ${name}` : name}
  </span>;
}

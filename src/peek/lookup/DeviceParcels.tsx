import { useEffect, useMemo, useRef, useState, useSyncExternalStore, type MouseEvent } from 'react';
import { CarrierMark } from '../../components/CarrierMark';
import { Icon } from '../../components/Icon';
import { MaintenanceStrip } from '../../components/MaintenanceStrip';
import { pipMood } from '../../components/map/Pip';
import { defaultMode } from '../../components/map/route';
import { WorldMap } from '../../components/map/WorldMap';
import { CardRoute, useParcelRoute } from '../../components/ParcelMap';
import { ProgressTrack } from '../../components/ProgressTrack';
import { localizedDeliveryEstimate, useI18n, type Locale } from '../../i18n';
import { carrierBrand } from '../../lib/carrierBrand';
import { ON_THE_WAY, useDeliveredHold } from '../../lib/deliveredHold';
import type { CardList } from '../../lib/deliveredMove';
import { carrierInfo, displayedCarrierId, formatTrackingNumber, normalizeTrackingNumber } from '../../lib/carriers';
import { useMarkNewUpdates } from '../../lib/newUpdate';
import { localizedParcelCompletionDate, parcelDeliveryEstimate, parcelStatusLabel } from '../../lib/parcelStatus';
import { parcelMatchesSearch } from '../../lib/parcelView';
import type { Translate } from '../../lib/messages';
import { pickupPoint } from '../../lib/pickupPoint';
import { isFinal } from '../../lib/stages';
import { maskedNumber } from '../links';
import { useMedia, useNow } from '../parcel/hooks';
import { parcelDetail, parcelFreshness, parcelHeadline, parcelStage } from '../parcel/summary';
import { frozenOnTheWay, hasUnopenedNews, strandedParcels, useRecents, type RecentParcel } from '../recents';
import { openParcelLink, parcelLinkPath } from '../route';
import { usePeekSession } from '../session';
import { deviceListDown, deviceListReadAt, forgetDeviceParcels, leadParcel, onDeviceListDown, watchDeviceParcels } from './deviceList';

/** A long number as a card writes it: its two ends. A short one stays whole. */
export function shortNumber(number: string, carrier: RecentParcel['carrier']): string {
  const plain = normalizeTrackingNumber(number);
  return plain.length > 14 ? `${plain.slice(0, 7)}…${plain.slice(-4)}` : formatTrackingNumber(number, carrier);
}

/** What a card calls its parcel: the name this device gave it, else its number. */
function useParcelTitle(recent: RecentParcel): string {
  const { t } = useI18n();
  const { parcel, numberHint } = recent.snapshot;
  return recent.name ?? (parcel.trackingNumber ? shortNumber(parcel.trackingNumber, parcel.carrier)
    : numberHint ? maskedNumber(numberHint) : t('common.parcel'));
}

/**
 * A scan since this device last opened the parcel, as the account's cards mark it: a dot before the status, and
 * "New update." first for a screen reader, ahead of everything the card says. The dot stays in place when off, so it
 * fades as it clears.
 */
function useNewUpdate(recent: RecentParcel) {
  const { t } = useI18n();
  const marked = useMarkNewUpdates() && hasUnopenedNews(recent);
  return {
    said: marked && <><span className="sr-only">{`${t('parcel.newUpdate')}.`}</span>{' '}</>,
    dot: <i className="parcel-card__new" data-on={marked ? '' : undefined} aria-hidden="true" />,
  };
}

/** A modified click opens the link the browser's way, in a new tab or window. Any other opens the page out of the card. */
function openOnClick(id: string) {
  return (event: MouseEvent<HTMLElement>) => {
    if (event.metaKey || event.ctrlKey || event.shiftKey || event.altKey || event.button !== 0) return;
    event.preventDefault();
    openParcelLink(id, { from: event.currentTarget });
  };
}

/** The list's cards, for what moves when one of its parcels has arrived. */
const DOOR_CARDS: CardList = { card: '[data-parcel-link]', id: 'data-parcel-link', name: '.door-nextup__name, strong', large: '.door-nextup', blocks: '.door-device__list > li' };

/**
 * A card of the list, with the parcel's route small at its end once a scan has a place. `arrived` marks one whose
 * parcel has just been delivered; `archived`, one found by a search though archived.
 */
function DeviceParcel({ recent, arrived = false, archived = false }: { recent: RecentParcel; arrived?: boolean; archived?: boolean }) {
  const { t, locale, languageTag, use24HourTime } = useI18n();
  const { parcel } = recent.snapshot;
  const carrier = carrierInfo(displayedCarrierId(parcel), locale);
  const stale = frozenOnTheWay(recent);
  const estimate = stale ? null : parcelDeliveryEstimate(parcel);
  const when = estimate ? localizedDeliveryEstimate(parcel.expectedDeliveryFrom, estimate, t, languageTag, undefined, use24HourTime) : localizedParcelCompletionDate(parcel, languageTag, t);
  const title = useParcelTitle(recent);
  const { placed, route } = useParcelRoute(parcel, languageTag);
  const news = useNewUpdate(recent);
  return <a className={`door-parcel${placed ? ' door-parcel--route' : ''}`} style={carrierBrand(carrier).style} href={parcelLinkPath(recent.id)} data-parcel-link={recent.id} data-arrived={arrived ? '' : undefined} onClick={openOnClick(recent.id)}>
    {placed && <CardRoute route={route} />}
    <span className="door-parcel__top">{news.said}<CarrierMark carrier={carrier} />{when && <span className="door-parcel__when">{when}</span>}</span>
    <strong>{title}</strong>
    <span className="door-parcel__state">{news.dot}{parcelStatusLabel(parcel, t, locale)}{stale && ` · ${t('link.frozen.label')}`}{archived && ` · ${t('app.archived')}`}</span>
  </a>;
}

/** The door's phone layout ends at this width: from here the lead card writes beside its map instead of under it. */
const BESIDE = '(min-width: 761px)';
/** Room for the card's top row, and beside the words for the land to fade in from under them. */
const LEAD_INSETS = {
  beside: { top: 44, right: 22, bottom: 26, left: 76 },
  under: { top: 44, right: 16, bottom: 30, left: 16 },
};
/** Pip keeps below the card's top row and, on a phone, above the words written over the foot of the map. */
const LEAD_CEILING = 46;
const LEAD_FLOOR = 150;

/**
 * The parcel the list leads with, on the card its own page gives it: the
 * status as the headline, what is known about its arrival, and its route in
 * the carrier's colours with Pip beside the parcel.
 */
function LeadParcel({ recent, arrived = false }: { recent: RecentParcel; arrived?: boolean }) {
  const { t, locale, languageTag, use24HourTime } = useI18n();
  const now = useNow();
  const beside = useMedia(BESIDE);
  const [time] = useState(() => new Date());
  const { parcel } = recent.snapshot;
  const carrier = carrierInfo(displayedCarrierId(parcel), locale);
  const stage = parcelStage(parcel);
  const title = useParcelTitle(recent);
  const { route } = useParcelRoute(parcel, languageTag);
  const wording = { t, languageTag, now, use24HourTime };
  const freshness = parcelFreshness({ parcel, checking: false, offline: false, trouble: false, seenAt: recent.lastSeenAt }, wording);
  // A parcel waiting to be collected says where.
  const detail = parcelDetail(parcel, wording) ?? (stage === 'ready_for_pickup' ? pickupPoint(parcel.pickupPoint)?.name : null);
  const mood = pipMood(stage ?? undefined);
  const news = useNewUpdate(recent);
  return <a className="door-nextup" style={carrierBrand(carrier).style} href={parcelLinkPath(recent.id)} data-parcel-link={recent.id} data-arrived={arrived ? '' : undefined} onClick={openOnClick(recent.id)}>
    <span className="door-nextup__map" aria-hidden="true">
      {route && <WorldMap route={route} mode={defaultMode(route, stage ?? undefined)} time={time} look="tint" labels="ends" context={false}
        live={!stage || !isFinal(stage)} pip={mood && { mood, ceiling: LEAD_CEILING, floor: beside ? undefined : LEAD_FLOOR }} languageTag={languageTag}
        insets={beside ? LEAD_INSETS.beside : LEAD_INSETS.under} className="door-nextup__canvas" />}
    </span>
    <span className="door-nextup__top">
      {news.said}
      <CarrierMark carrier={carrier} />
      <span className="door-nextup__fresh">{freshness.dot && <i aria-hidden="true" />}{freshness.label}</span>
    </span>
    <span className="door-nextup__text">
      <span className="door-nextup__name">{title}</span>
      <strong>{news.dot}{parcelHeadline(parcel, t)}</strong>
      {detail && <span className="door-nextup__detail">{detail}</span>}
    </span>
    {/* The headline already says how far the parcel is. */}
    <div className="door-nextup__progress" aria-hidden="true"><ProgressTrack stage={stage} /></div>
  </a>;
}

/** From this many parcels on the device, the list can be searched. */
export const SEARCH_FROM = 5;

/** Whether a parcel answers a search, as the account's list reads it: its name, number, carrier, status and scans. */
function found(recent: RecentParcel, query: string, t: Translate, locale: Locale): boolean {
  const { parcel } = recent.snapshot;
  return parcelMatchesSearch({ ...parcel, label: recent.name ?? '', lastStatusText: parcelStatusLabel(parcel, t, locale) }, query);
}

/** The parcels archived on this device, folded away at the end of the list. */
function ArchivedParcels({ archived }: { archived: RecentParcel[] }) {
  const { t } = useI18n();
  return <details className="door-archived">
    <summary>
      <Icon name="archive" /><span>{t('app.archived')}</span>
      <span className="door-archived__count">{archived.length}</span>
      <Icon name="chevron" className="door-archived__chevron" />
    </summary>
    <ul className="door-archived__list">
      {archived.map((recent) => <li key={recent.id}><DeviceParcel recent={recent} /></li>)}
    </ul>
  </details>;
}

/**
 * "On this device": the parcels this browser looked up or opened, each in its
 * carrier's colours with its route. The one that matters now leads the list
 * on a card of its own, and the archived ones wait folded at its end. A longer
 * list can be searched, archived parcels included. They live in the browser
 * only; forgetting them all also forgets, on the server, the lookups this
 * device made.
 */
export function DeviceParcels({ onSignIn, onForgotten, covered = false }: {
  onSignIn: () => void;
  /** The list is gone: the door takes the focus back. */
  onForgotten: () => void;
  /** A parcel's page lies over the list, and reads its parcel itself. */
  covered?: boolean;
}) {
  const { t, locale } = useI18n();
  const { account } = usePeekSession();
  const recents = useRecents();
  const [searching, setSearching] = useState(false);
  const [query, setQuery] = useState('');
  // Saved parcels this version can't list keep their owner keys: Forget all is offered for them too.
  const stranded = strandedParcels().length;
  const [asking, setAsking] = useState(false);
  // Which parcel Forget all has reached, of how many: the server takes a few a minute, and the rest wait their turn.
  const [forgetting, setForgetting] = useState<{ done: number; count: number } | null>(null);
  const [failed, setFailed] = useState(false);
  const down = useSyncExternalStore(onDeviceListDown, deviceListDown, () => null);
  const question = useRef<HTMLDivElement>(null);
  const list = useRef(recents);
  useEffect(() => { list.current = recents; });
  const listed = recents.length > 0;
  const active = useMemo(() => recents.filter((recent) => !recent.archivedAt), [recents]);
  const archived = recents.filter((recent) => recent.archivedAt);
  const searchable = recents.length >= SEARCH_FROM;
  const matches = searching && searchable && query.trim() ? recents.filter((recent) => found(recent, query, t, locale)) : null;
  const watching = listed && !covered;
  useEffect(() => watching ? watchDeviceParcels(() => list.current) : undefined, [watching]);
  useEffect(() => { if (asking) question.current?.focus(); }, [asking]);
  // A parcel delivered since the list was last shown says so where its card stands, before the list is led by another.
  const cards = useRef<HTMLUListElement>(null);
  const parcels = useMemo(() => active.map((recent) => ({ ...recent.snapshot.parcel, id: recent.id })), [active]);
  const delivered = useDeliveredHold(parcels, watching && !asking && !matches, cards, DOOR_CARDS, ON_THE_WAY.device);
  function closeSearch() {
    setSearching(false);
    setQuery('');
  }

  async function forget() {
    setForgetting({ done: 0, count: 0 });
    setFailed(false);
    const kept = await forgetDeviceParcels(list.current, (done, count) => setForgetting({ done, count }));
    setForgetting(null);
    setAsking(false);
    setFailed(kept > 0);
    if (!kept) onForgotten();
  }

  if (!listed && !stranded) return null;
  const shown = new Map(delivered.arranged.map((parcel) => [parcel.id, parcel]));
  const led = leadParcel(active.map((recent) => ({ ...recent, snapshot: { ...recent.snapshot, parcel: shown.get(recent.id) ?? recent.snapshot.parcel } })));
  const lead = active.find((recent) => recent.id === led?.id) ?? null;
  const arrived = (recent: RecentParcel) => delivered.arrived({ ...recent.snapshot.parcel, id: recent.id });
  return <section className="door-device" aria-labelledby="door-device-title">
    <div className="door-device__heading">
      <h2 id="door-device-title">{t('peek.onThisDevice')}</h2>
      {!asking && <div className="door-device__tools">
        {searchable && <button type="button" className="door-device__search" aria-expanded={searching} aria-controls="door-device-search"
          aria-label={t(searching ? 'view.hideSearch' : 'view.search')} onClick={() => searching ? closeSearch() : setSearching(true)}>
          <Icon name="search" />
        </button>}
        <button type="button" className="door-device__forget" onClick={() => { setFailed(false); setAsking(true); }}>{t('door.recents.forgetAll')}</button>
      </div>}
    </div>
    {searching && searchable && <div id="door-device-search" className="door-device__find" role="search">
      <label>
        <span className="sr-only">{t('view.search')}</span>
        <Icon name="search" />
        <input type="search" autoFocus value={query} placeholder={t('view.searchPlaceholder')} enterKeyHint="search" autoComplete="off"
          onChange={(event) => setQuery(event.target.value)} onKeyDown={(event) => { if (event.key === 'Escape') closeSearch(); }} />
      </label>
      <p className="sr-only" aria-live="polite">{matches && t('view.shown', { count: matches.length })}</p>
    </div>}
    {asking && <div ref={question} className="door-device__question" role="group" aria-labelledby="door-device-question" tabIndex={-1}>
      <p id="door-device-question" aria-live="polite">{forgetting && forgetting.count > 1
        ? t('door.recents.forgetting', forgetting)
        : t('door.recents.forget.many', { count: recents.length + stranded })}</p>
      <div>
        <button type="button" className="door-device__cancel" disabled={!!forgetting} onClick={() => setAsking(false)}>{t('common.cancel')}</button>
        <button type="button" className="door-device__confirm" disabled={!!forgetting} onClick={() => void forget()}>{t('door.recents.forget.yes')}</button>
      </div>
    </div>}
    {failed && <p className="door-message" role="alert">{t('door.recents.forget.failed')}</p>}
    {down && listed && <MaintenanceStrip notice={down} at={deviceListReadAt(recents)} />}
    {/* A search lists what it found in the list's order, archived parcels said so, and no card leads. */}
    {matches && (matches.length
      ? <ul className="door-device__list door-device__found">
        {matches.map((recent) => <li key={recent.id}><DeviceParcel recent={recent} archived={!!recent.archivedAt} /></li>)}
      </ul>
      : <p className="door-message">{t('view.noResultsTitle')}</p>)}
    <ul ref={cards} className="door-device__list" hidden={!active.length || !!matches}>
      {lead && <li key={lead.id} className="door-device__lead"><LeadParcel recent={lead} arrived={arrived(lead)} /></li>}
      {active.filter((recent) => recent !== lead).map((recent) => <li key={recent.id}><DeviceParcel recent={recent} arrived={arrived(recent)} /></li>)}
    </ul>
    {archived.length > 0 && !matches && <ArchivedParcels archived={archived} />}
    <p className="door-aside">{t('door.recents.kept')}{account !== 'signed-in' && listed && <>{' '}<button type="button" className="door-link" onClick={onSignIn}>{t('door.recents.signIn')}</button></>}</p>
  </section>;
}

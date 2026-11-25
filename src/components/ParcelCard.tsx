import { AutoCarrierNotice } from './AutoCarrierNotice';
import { useEffect, useRef, useState } from 'react';
import { deliveringCarrierId, deliveryLineKey, displayedCarrierId, carrierInfo } from '../lib/carriers';
import { localizedDatePhrase, localizedDeliveryEstimate, useI18n } from '../i18n';
import { numberToCheck } from '../lib/messages';
import { hasNewUpdate, useMarkNewUpdates } from '../lib/newUpdate';
import { awaitedCarrier, localizedParcelCompletionDate, parcelDeliveryEstimate, parcelHasCarrierUpdate, parcelStatusLabel, shownCarrier } from '../lib/parcelStatus';
import { pickupPoint } from '../lib/pickupPoint';
import { currentEvent } from '../lib/stages';
import { userErrorMessage } from '../lib/userMessages';
import type { ParcelWithEvents } from '../types';
import { CarrierMark } from './CarrierMark';
import { carrierBrand } from '../lib/carrierBrand';
import { Icon } from './Icon';
import { CardRoute, NextUpEngraving, useParcelRoute } from './ParcelMap';
import { ParcelStamp } from './ParcelStamp';
import { bindSwipeRow, type SwipeRow } from '../lib/swipeRow';
import { Glyph } from '../peek/parcel/glyphs';
import { NameWithMark } from './note/NoteMark';
import { hasNote } from '../lib/parcelNote';
import { searchRanges } from '../lib/searchMatch';
import { NoteMatch, SearchMarked, useListSearch, useNoteMatch } from './SearchMark';
import { peekAiCardWords } from '../lib/peekAi';
import { PeekAiPhrase, useParcelPeekAi } from './PeekAi';

export function ParcelCard({ parcel, onOpen, onArchive, notice, variant = 'regular', arrived = false }: {
  parcel: ParcelWithEvents;
  onOpen: (parcel: ParcelWithEvents, source: HTMLButtonElement) => void;
  onArchive?: (parcel: ParcelWithEvents) => Promise<unknown>;
  notice?: string;
  variant?: 'regular' | 'hero';
  /** Just delivered, and still shown where it stood: the card says so before it leaves for the past deliveries. */
  arrived?: boolean;
}) {
  const { locale, languageTag, t, use24HourTime } = useI18n();
  const carrier = shownCarrier(parcel, carrierInfo(displayedCarrierId(parcel), locale), t, locale);
  const current = currentEvent(parcel.events, parcel.returnHeld);
  const deliveryCarrier = deliveringCarrierId(parcel);
  const deliveryLabel = deliveryCarrier ? t(deliveryLineKey(parcel, current?.stage), { carrier: carrierInfo(deliveryCarrier, locale).name }) : null;
  const estimate = parcelDeliveryEstimate(parcel);
  const expectedDelivery = estimate ? localizedDeliveryEstimate(parcel.expectedDeliveryFrom, estimate, t, languageTag, undefined, use24HourTime) : null;
  const statusLabel = parcelStatusLabel(parcel, t, locale);
  const completionDate = localizedParcelCompletionDate(parcel, languageTag, t);
  // Peek AI's own estimate, named as Peek's: under the carrier's date, or in its place; beside the status on Next up.
  const peekAi = useParcelPeekAi(parcel);
  const peekAiWords = peekAi && !completionDate ? peekAiCardWords(peekAi, expectedDelivery ? estimate : null, { t, languageTag, use24HourTime }) : null;
  // A parcel waiting for collection says where.
  const pickupPlace = current?.stage === 'ready_for_pickup' ? pickupPoint(parcel.pickupPoint)?.name : undefined;
  const parcelName = parcel.label || t('common.parcel');
  const branding = carrierBrand(carrier);
  const hero = variant === 'hero';
  // Next up draws its route across the card; every other card carries it small, at its end.
  const { placed, route } = useParcelRoute(parcel, languageTag);
  // Customs and a pickup say all they need in the status line; after a missed delivery or a problem, or while customs asks
  // the reader for something, the carrier waits on the reader.
  const actionNeeded = Boolean(current?.recipientAction) || ['failed_attempt', 'exception'].includes(current?.stage ?? '');
  const carrierIssue = !actionNeeded && ['customs', 'ready_for_pickup'].includes(current?.stage ?? '');
  // The carrier's site being down tells why no update comes, unless the carrier waits on the reader.
  const outage = parcel.carrierOutage && !actionNeeded
    ? t('attention.carrier_outage', { carrier: carrierInfo(parcel.carrierOutage.carrier, locale).name }) : null;
  // A number to check, or a carrier that could not be reached yet, says so in the status line: no update was missed.
  const flag = (notice && !carrierIssue && !outage ? notice : null) ?? outage
    ?? (parcel.syncStatus === 'error' && !numberToCheck(parcel.syncError) && !awaitedCarrier(parcel) ? t('attention.sync_error') : null);
  const flagIcon = notice && actionNeeded ? <Glyph name="warning" /> : <Icon name={parcel.syncStatus === 'error' || outage ? 'refresh' : 'clock'} />;
  const flagChip = flag && <span className={`parcel-card__notice${flag === outage ? ' parcel-card__notice--calm' : ''}`}>{flagIcon}{flag}</span>;
  const row = useRef<HTMLDivElement>(null);
  const button = useRef<HTMLButtonElement>(null);
  const tray = useRef<HTMLDivElement>(null);
  const block = useRef<HTMLDivElement>(null);
  const ears = useRef<HTMLSpanElement>(null);
  const action = useRef<HTMLButtonElement>(null);
  const swipe = useRef<SwipeRow | null>(null);
  const [open, setOpen] = useState(false);
  const [archiving, setArchiving] = useState(false);
  const [archiveError, setArchiveError] = useState<string | null>(null);
  const latest = useRef({ parcel, onArchive, t });
  useEffect(() => { latest.current = { parcel, onArchive, t }; });
  const swipeable = Boolean(onArchive);
  useEffect(() => {
    if (!swipeable || !row.current || !button.current || !tray.current || !block.current || !ears.current || !action.current) return;
    const controller = bindSwipeRow({ row: row.current, card: button.current, tray: tray.current, block: block.current, ears: ears.current, action: action.current }, {
      // The next parcel takes the hero's place, so the page keeps its height.
      reflow: !hero,
      onOpenChange: setOpen,
      onArchiveStart: () => { setArchiveError(null); setArchiving(true); },
      onArchive: async () => {
        const { parcel, onArchive, t } = latest.current;
        try {
          await onArchive?.(parcel);
          return true;
        } catch (reason) {
          setArchiving(false);
          setArchiveError(userErrorMessage(reason, t, 'detail.archiveFailed'));
          return false;
        }
      },
    });
    swipe.current = controller;
    return () => { controller.destroy(); swipe.current = null; };
  }, [swipeable, hero]);
  const statusSummary = completionDate ? `${statusLabel} ${localizedDatePhrase(completionDate, t)}`
    : pickupPlace ? `${statusLabel} · ${pickupPlace}` : statusLabel;
  // A private note shows as a quiet mark after the name, and the row says so.
  const noted = hasNote(parcel);
  // While the reader's own list is searching, the name marks the match, and a match in the note shows its few words.
  const nameRanges = searchRanges(parcel.label ?? '', useListSearch());
  const noteHit = useNoteMatch(parcel.note, parcel.files?.map((file) => file.name) ?? []);
  const spokenName = noteHit ? `${parcelName}, ${t('note.title')}: ${noteHit.text}` : noted ? `${parcelName}, ${t('note.hasNote')}` : parcelName;
  const statusAria = expectedDelivery ? t('parcel.ariaExpected', { name: spokenName, status: statusSummary, date: expectedDelivery }) : t('parcel.aria', { name: spokenName, status: statusSummary });
  const shownName = noted ? <NameWithMark name={parcelName} files={Boolean(parcel.files?.length)} ranges={nameRanges} />
    : <SearchMarked text={parcelName} ranges={nameRanges} />;
  const noteMatch = noteHit && <NoteMatch snippet={noteHit} className="parcel-card__note-match" />;

  // New since the parcel was last opened: a dot before the status line, which a screen reader hears first.
  const marked = useMarkNewUpdates() && hasNewUpdate(parcel);
  const described = [statusAria, peekAiWords && t('peekAi.says', { words: peekAiWords }), flag, deliveryLabel].filter(Boolean).join('. ');
  const placedLabel = hero && !arrived ? `${t('app.nextUp')}: ${described}` : described;
  const label = marked ? `${t('parcel.newUpdate')}. ${placedLabel}` : placedLabel;
  // Always there, so it fades and the words slide back as it clears.
  const mark = <i className="parcel-card__new" data-on={marked ? '' : undefined} aria-hidden="true" />;

  const check = current?.stage === 'delivered' && <Icon name="check" />;

  return <div ref={row} data-parcel-id={parcel.id} data-carrier={carrier.id} data-arrived={arrived ? '' : undefined} style={branding.style} className={`parcel-card-swipe${hero ? ' parcel-card-swipe--hero' : ''}`}>
    <div className="parcel-card-swipe__clip">
      {onArchive && <div ref={tray} className="parcel-card-swipe__tray">
        <div ref={block} className="parcel-card-swipe__block">
          <span ref={ears} className="parcel-card-swipe__ears" />
          <button ref={action} type="button" className="parcel-card-swipe__archive" aria-label={t('parcel.archiveAria', { name: parcelName })}
            aria-hidden={!open} tabIndex={open ? 0 : -1} disabled={archiving} onClick={() => swipe.current?.archive()}><Icon name="archive" /><span>{t('parcel.archive')}</span></button>
        </div>
      </div>}
      <button ref={button} type="button" className={`parcel-card${hero ? ' parcel-card--hero' : ''}${placed ? (hero ? ' parcel-card--map' : ' parcel-card--route') : ''}${parcel.archivedAt ? ' parcel-card--archived' : ''}`}
        disabled={archiving} aria-busy={archiving} aria-label={label}
        onClick={(event) => { if (!swipe.current?.consumeClick()) onOpen(parcel, event.currentTarget); }}>
        {hero ? <>
          {placed && <NextUpEngraving route={route} stage={current?.stage} />}
          <span className="parcel-card__hero-top"><CarrierMark carrier={carrier} /><span className="parcel-card__next-label">{t('app.nextUp')}</span></span>
          <span className="parcel-card__hero-main"><strong className="parcel-card__label">{shownName}</strong><ParcelStamp parcel={parcel} /></span>
          {noteMatch}
          {deliveryLabel && <span className="parcel-card__sender">{deliveryLabel}</span>}
          <AutoCarrierNotice parcel={parcel} className="parcel-card__sender" />
          <span className="parcel-card__summary"><span className="parcel-card__state">{mark}{arrived && check}{statusLabel}</span>{pickupPlace && <><span aria-hidden="true">·</span><span className="parcel-card__place">{pickupPlace}</span></>}{(expectedDelivery || (arrived && completionDate)) && <><span aria-hidden="true">·</span><span className="parcel-card__eta">{expectedDelivery || completionDate}</span></>}{peekAiWords && <><span aria-hidden="true">·</span><PeekAiPhrase words={peekAiWords} className="parcel-card__eta" /></>}</span>
          {flagChip}
        </> : <>
          {placed && <CardRoute route={route} />}
          <span className="parcel-card__top"><CarrierMark carrier={carrier} />{peekAiWords
            ? <span className="parcel-card__dates">{expectedDelivery && <span className="parcel-card__eta">{expectedDelivery}</span>}<PeekAiPhrase words={peekAiWords} /></span>
            : (expectedDelivery || completionDate) && <span className={completionDate ? 'parcel-card__completion' : 'parcel-card__eta'}>{expectedDelivery || completionDate}</span>}</span>
          <strong className="parcel-card__label">{shownName}</strong>
          {noteMatch}
          {deliveryLabel && <span className="parcel-card__sender">{deliveryLabel}</span>}
          <AutoCarrierNotice parcel={parcel} className="parcel-card__sender" />
          {(!flag || parcelHasCarrierUpdate(parcel)) && <span className="parcel-card__state">{mark}{check}{statusLabel}{pickupPlace && <><span aria-hidden="true">·</span><span className="parcel-card__place">{pickupPlace}</span></>}</span>}
          {flagChip}
        </>}
      </button>
    </div>
    {archiveError && <p className="parcel-card__error" role="alert">{archiveError}</p>}
  </div>;
}

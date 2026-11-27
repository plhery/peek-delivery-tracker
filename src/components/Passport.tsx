import { localizedCalendarDate } from '../lib/format';
import { trackAction } from '../lib/analytics';
import { useEffect, useId, useRef, useState, type ReactNode } from 'react';
import { useI18n } from '../i18n';
import { carrierInfo } from '../lib/carriers';
import { countrySeenKey, formatJourneyDuration, stampSeenKey, type StampProgress } from '../lib/passport';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import { placeBubble } from '../lib/bubble';
import { PassportSeal } from './passport/PassportSeal';
import { STAMP_COPY, stampDay, stampFullDay, stampProgress } from './passport/copy';
import type { PassportNews } from './passport/news';
import { inkDay, YearCard } from './passport/YearCard';
import { CountryInk } from './passport/CountryInk';
import { PassportPages } from './passport/PassportPages';

const COVER_SEAL = { tone: 'green', glyph: 'parcel' } as const;

const COUNTRIES = 0, STAMPS = 1;

export function Passport({ news, loading }: { news: PassportNews; loading: boolean }) {
  const { t, locale, languageTag } = useI18n();
  const { stats, stamps, countries, numbers } = news.book;
  const [expandedCard, setExpandedCard] = useState<string | null>(null);
  const [showAllStamps, setShowAllStamps] = useState(false);
  // What was new when the passport opened stays new, and is pressed in, until the reader leaves it.
  const [fresh, setFresh] = useState<ReadonlySet<string>>(() => new Set());
  const { unseen, markSeen } = news;
  // The passport opens on the page with news, countries first; each page's news presses in once that page is shown.
  const [page, setPage] = useState(() => unseen.some((item) => item.country) ? COUNTRIES : unseen.some((item) => item.stamp) ? STAMPS : COUNTRIES);
  const [shownPages, setShownPages] = useState<ReadonlySet<number>>(() => new Set([page]));
  const turn = (next: number) => {
    setPage(next);
    setShownPages((current) => current.has(next) ? current : new Set([...current, next]));
  };
  useEffect(() => {
    if (loading || !unseen.length) return;
    setFresh((current) => new Set([...current, ...unseen.map((item) => item.seen)]));
    markSeen();
  }, [loading, markSeen, unseen]);
  const idPrefix = useId();
  const detailId = (id: string) => `${idPrefix}-${id}`;
  const anchors = useRef(new Map<string, HTMLButtonElement>());
  useEffect(() => {
    if (!expandedCard) return;
    const bubble = document.getElementById(`${idPrefix}-${expandedCard}`);
    const dismiss = (event: Event) => {
      if (event.target instanceof Node && bubble?.contains(event.target)) return;
      if (bubble?.matches(':popover-open')) bubble.hidePopover();
    };
    window.addEventListener('resize', dismiss);
    window.addEventListener('scroll', dismiss, true);
    return () => {
      window.removeEventListener('resize', dismiss);
      window.removeEventListener('scroll', dismiss, true);
    };
  }, [expandedCard, idPrefix]);
  const button = (id: string, className: string, label: string, children: ReactNode, onOpen?: () => void) => <button type="button" className={className} aria-label={label}
    ref={(node) => { if (node) anchors.current.set(id, node); else anchors.current.delete(id); }}
    popoverTarget={detailId(id)} aria-haspopup="dialog" aria-expanded={expandedCard === id} aria-controls={detailId(id)}
    onClick={() => { if (expandedCard !== id) onOpen?.(); }}>{children}</button>;
  const detail = (id: string, title: string, explanation: string) => <div id={detailId(id)} className="passport-bubble" popover="auto" role="dialog"
    aria-labelledby={`${detailId(id)}-title`} tabIndex={-1} onToggle={(event) => {
      const bubble = event.currentTarget;
      const open = bubble.matches(':popover-open');
      setExpandedCard((current) => open ? id : current === id ? null : current);
      if (!open) { delete bubble.dataset.positioned; return; }
      const anchor = anchors.current.get(id);
      if (!anchor) { bubble.hidePopover(); return; }
      const rect = anchor.getBoundingClientRect();
      placeBubble(bubble, rect);
      bubble.dataset.positioned = 'true';
      bubble.focus({ preventScroll: true });
    }}><h3 id={`${detailId(id)}-title`}>{title}</h3><p>{explanation}</p></div>;
  const format = (duration: number) => duration < 60_000 ? t('passport.underOneMinute') : formatJourneyDuration(duration, languageTag);
  // Earned stamps first, in the passport's order, then the next three to reach; the rest behind "All stamps".
  const earned = stamps.filter((stamp) => stamp.earned);
  const ahead = stamps.filter((stamp) => !stamp.earned);
  const visibleStamps = showAllStamps ? [...earned, ...ahead] : [...earned, ...ahead.slice(0, 3)];
  const pressed = earned.filter((stamp) => fresh.has(stampSeenKey(stamp))).map((stamp) => stamp.key);
  const landing = countries.filter((country) => fresh.has(countrySeenKey(country))).map((country) => country.code);
  const pressing = shownPages.has(STAMPS) ? pressed : [];
  const dropping = shownPages.has(COUNTRIES) ? landing : [];
  const earnedLine = (stamp: StampProgress) => stamp.earnedAt === null ? null : t('passport.earnedOn', { date: stampFullDay(stamp.earnedAt, languageTag) });
  const stampDetail = (stamp: StampProgress) => [
    t(STAMP_COPY[stamp.key].explanation),
    stamp.earned ? stamp.earnedAt !== null && `${earnedLine(stamp)} · ${stamp.label || t('common.parcel')}` : stampProgress(stamp, t),
  ].filter(Boolean).join('\n');
  const timingExplanation = `${t(stats.durationSampleCount === 1 ? 'passport.timedJourneys.one' : 'passport.timedJourneys.many', { count: stats.durationSampleCount })}. ${t('passport.timingExplanation')}`;
  if (loading) return <div className="passport-loading" role="status" aria-label={t('app.loadingParcels')}><div className="skeleton" /><div className="skeleton" /></div>;

  const stampsPage = <>
    <div className="passport-stamps__grid" id={`${idPrefix}-stamps`}>{visibleStamps.map((stamp) => {
      const press = pressing.indexOf(stamp.key);
      const title = t(STAMP_COPY[stamp.key].title);
      const under = stamp.earned ? stamp.earnedAt === null ? null : stampDay(stamp.earnedAt, languageTag) : stampProgress(stamp, t);
      return <div key={stamp.key} className="stamp-card-cell">{button(stamp.key, `stamp-card${stamp.earned ? ' stamp-card--earned' : ''}`,
        [title, stamp.earned ? earnedLine(stamp) : stampProgress(stamp, t), press >= 0 ? t('passport.new') : null].filter(Boolean).join(', '),
        <>
          <PassportSeal stamp={stamp} earned={stamp.earned} press={press >= 0 ? press : undefined} />
          {press >= 0 && <span className="passport-new-pill" aria-hidden="true">{t('passport.new')}</span>}
          <span className="stamp-card__name">{title}</span>
          {under && <span className="stamp-card__day" aria-hidden="true">{under}</span>}
        </>, () => trackAction('stamp-open'))}</div>;
    })}</div>
    {ahead.length > 3 && <button type="button" className="passport-collection-toggle" aria-expanded={showAllStamps} aria-controls={`${idPrefix}-stamps`} onClick={() => setShowAllStamps((value) => !value)}>{t(showAllStamps ? 'passport.showLess' : 'passport.showAll')}</button>}
    {visibleStamps.map((stamp) => <div key={stamp.key}>{detail(stamp.key, t(STAMP_COPY[stamp.key].title), stampDetail(stamp))}</div>)}
  </>;

  const countriesPage = countries.length ? <>
    <div className="passport-countries__grid">{countries.map((country) => {
      const drop = dropping.indexOf(country.code);
      const name = countryName(country.code, languageTag);
      const reached = t('passport.firstReached', { date: stampFullDay(country.firstAt, languageTag) });
      const parcels = t('passport.parcels.many', { count: country.count });
      const id = `country-${country.code}`;
      return <div key={country.code} className="country-ink-cell">{button(id, `country-ink${drop >= 0 ? ' country-ink--new' : ''}`,
        [name, reached, parcels, drop >= 0 ? t('passport.new') : null].filter(Boolean).join(', '),
        <>
          <CountryInk code={country.code} number={country.number} day={inkDay(country.firstAt, languageTag)} drop={drop >= 0 ? drop : undefined} />
          {drop >= 0 && <span className="passport-new-pill" aria-hidden="true">{t('passport.new')}</span>}
          <span className="country-ink__name"><span aria-hidden="true">{countryFlag(country.code)}</span> {name}</span>
          <span className="country-ink__count">{parcels}</span>
        </>)}
        {detail(id, name, `${reached} · ${country.label || t('common.parcel')}\n${parcels}`)}
      </div>;
    })}</div>
  </> : <div className="passport-countries__empty"><span className="passport-countries__ghost" aria-hidden="true" /><p>{t('passport.countriesEmpty')}</p></div>;

  const month = numbers.busiestMonth && new Intl.DateTimeFormat(languageTag, { month: 'long', year: 'numeric', timeZone: 'UTC' })
    .format(Date.UTC(Number(numbers.busiestMonth.month.slice(0, 4)), Number(numbers.busiestMonth.month.slice(5, 7)) - 1, 15));
  const topCarrier = numbers.topCarrier && carrierInfo(numbers.topCarrier.carrier as Parameters<typeof carrierInfo>[0], locale).name;
  const numbersPage = <>
    <div className="passport-numbers">
      <div className="passport-number"><strong>{stats.deliveredCount.toLocaleString(languageTag)}</strong><span>{t('passport.delivered', { count: stats.deliveredCount })}</span></div>
      <div className="passport-number"><strong>{countries.length.toLocaleString(languageTag)}</strong><span>{t('passport.countriesLabel', { count: countries.length })}</span></div>
      {stats.averageDeliveryDuration != null && stats.fastestDelivery && <>
        {button('average', 'passport-number', `${t('passport.average')}, ${format(stats.averageDeliveryDuration)}`, <><strong>{format(stats.averageDeliveryDuration)}</strong><span>{t('passport.average')}</span></>)}
        {button('best', 'passport-number', `${t('passport.personalBest')}, ${format(stats.fastestDelivery.duration)}`, <><strong>{format(stats.fastestDelivery.duration)}</strong><span>{t('passport.personalBest')}</span></>)}
      </>}
    </div>
    {stats.averageDeliveryDuration != null && stats.fastestDelivery ? <>
      {detail('average', t('passport.average'), timingExplanation)}
      {detail('best', t('passport.personalBest'), `${stats.fastestDelivery.label || t('common.parcel')} · ${localizedCalendarDate(new Date(stats.fastestDelivery.deliveredAt), languageTag)}\n${timingExplanation}`)}
    </> : <p className="passport-note">{t('passport.waitingForTimes')}</p>}
    {(month || topCarrier) && <dl className="passport-number-rows">
      {month && <div><dt>{t('passport.busiestMonth')}</dt><dd>{month} · {t('passport.parcels.many', { count: numbers.busiestMonth!.count })}</dd></div>}
      {topCarrier && <div><dt>{t('passport.carriersUsed')}</dt><dd>{numbers.carrierCount > 1 ? t('passport.carriersTop', { count: numbers.carrierCount, carrier: topCarrier }) : topCarrier}</dd></div>}
    </dl>}
    <YearCard book={news.book} />
  </>;

  return <div className="passport-page">
    <div className="passport-cover">
      {button('delivered', 'passport-cover__main', `${stats.deliveredCount} ${t('passport.delivered', { count: stats.deliveredCount })}`, <>
        <span><strong className="passport-cover__count">{stats.deliveredCount.toLocaleString(languageTag)}</strong><span>{t('passport.delivered', { count: stats.deliveredCount })}</span></span><PassportSeal stamp={COVER_SEAL} size="cover" />
      </>)}
      {detail('delivered', t('passport.delivered', { count: stats.deliveredCount }), t('passport.deliveredExplanation'))}
    </div>
    <PassportPages label={t('passport.pages.label')} page={page} onPage={turn} tabs={[
      { label: t('passport.pages.countries'), dot: landing.length > 0 && !shownPages.has(COUNTRIES), content: countriesPage },
      { label: t('passport.stamps'), dot: pressed.length > 0 && !shownPages.has(STAMPS), content: stampsPage },
      { label: t('passport.pages.numbers'), dot: false, content: numbersPage },
    ]} />
  </div>;
}

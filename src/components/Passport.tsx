import { localizedCalendarDate } from '../lib/format';
import { trackAction } from '../lib/analytics';
import { useEffect, useId, useRef, useState, type ReactNode } from 'react';
import { useI18n } from '../i18n';
import { formatJourneyDuration, type StampProgress } from '../lib/passport';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import { placeBubble } from '../lib/bubble';
import { PassportSeal } from './passport/PassportSeal';
import { STAMP_COPY, stampDay, stampFullDay, stampProgress } from './passport/copy';
import type { PassportNews } from './passport/news';
import { YearCard } from './passport/YearCard';

const COVER_SEAL = { tone: 'green', glyph: 'parcel' } as const;

export function Passport({ news, loading }: { news: PassportNews; loading: boolean }) {
  const { t, languageTag } = useI18n();
  const { stats, stamps } = news.book;
  const [expandedCard, setExpandedCard] = useState<string | null>(null);
  const [showAllStamps, setShowAllStamps] = useState(false);
  // What was new when the passport opened stays new, and is pressed in, until the reader leaves it.
  const [fresh, setFresh] = useState<ReadonlySet<string>>(() => new Set());
  const { unseen, markSeen } = news;
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
  const pressed = earned.filter((stamp) => fresh.has(`stamp:${stamp.key}`)).map((stamp) => stamp.key);
  const earnedLine = (stamp: StampProgress) => stamp.earnedAt === null ? null : t('passport.earnedOn', { date: stampFullDay(stamp.earnedAt, languageTag) });
  const stampDetail = (stamp: StampProgress) => [
    t(STAMP_COPY[stamp.key].explanation),
    stamp.earned ? stamp.earnedAt !== null && `${earnedLine(stamp)} · ${stamp.label || t('common.parcel')}` : stampProgress(stamp, t),
  ].filter(Boolean).join('\n');
  const timingExplanation = `${t(stats.durationSampleCount === 1 ? 'passport.timedJourneys.one' : 'passport.timedJourneys.many', { count: stats.durationSampleCount })}. ${t('passport.timingExplanation')}`;
  if (loading) return <div className="passport-loading" role="status" aria-label={t('app.loadingParcels')}><div className="skeleton" /><div className="skeleton" /></div>;

  return <div className="passport-page">
    <div className="passport-cover">
      {button('delivered', 'passport-cover__main', `${stats.deliveredCount} ${t('passport.delivered', { count: stats.deliveredCount })}`, <>
        <span><strong className="passport-cover__count">{stats.deliveredCount.toLocaleString(languageTag)}</strong><span>{t('passport.delivered', { count: stats.deliveredCount })}</span></span><PassportSeal stamp={COVER_SEAL} size="cover" />
      </>)}
      {detail('delivered', t('passport.delivered', { count: stats.deliveredCount }), t('passport.deliveredExplanation'))}
    </div>
    <section className="passport-stamps" aria-labelledby="passport-stamps-title">
      <h2 id="passport-stamps-title">{t('passport.stamps')}</h2>
      <div className="passport-stamps__grid" id={`${idPrefix}-stamps`}>{visibleStamps.map((stamp) => {
        const press = pressed.indexOf(stamp.key);
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
    </section>
    <section className="passport-times" aria-label={t('passport.deliveryTimes')}>
      {stats.averageDeliveryDuration != null && stats.fastestDelivery ? <>
        <div className="passport-times__grid">
          {button('average', 'time-card', `${t('passport.average')}, ${format(stats.averageDeliveryDuration)}`, <><strong>{format(stats.averageDeliveryDuration)}</strong><span>{t('passport.average')}</span></>)}
          {button('best', 'time-card', `${t('passport.personalBest')}, ${format(stats.fastestDelivery.duration)}`, <><strong>{format(stats.fastestDelivery.duration)}</strong><span>{t('passport.personalBest')}</span></>)}
        </div>
        {detail('average', t('passport.average'), timingExplanation)}
        {detail('best', t('passport.personalBest'), `${stats.fastestDelivery.label || t('common.parcel')} · ${localizedCalendarDate(new Date(stats.fastestDelivery.deliveredAt), languageTag)}\n${timingExplanation}`)}
      </> : <p className="passport-note">{t('passport.waitingForTimes')}</p>}
    </section>
    <YearCard book={news.book} />
    {stats.originCountries.length > 0 && <section className="passport-countries" aria-labelledby="passport-countries-title">
      <div className="passport-section-heading"><h2 id="passport-countries-title">
        {button('countries', 'passport-heading-button', t('passport.firstSeenIn'), t('passport.firstSeenIn'))}
      </h2></div>
      {detail('countries', t('passport.firstSeenIn'), t('passport.countryExplanation'))}
      <div className="country-list">{stats.originCountries.slice(0, 3).map((country) => <div className="country-row" key={country.code}>
        <span className="country-row__flag" aria-hidden="true">{countryFlag(country.code)}</span>
        <span>{countryName(country.code, languageTag)}</span><span className="country-row__count">{country.count.toLocaleString(languageTag)}</span>
      </div>)}</div>
    </section>}
  </div>;
}

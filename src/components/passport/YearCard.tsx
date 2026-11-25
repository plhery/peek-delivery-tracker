import { useEffect, useMemo, useRef, useState } from 'react';
import { countryName } from 'universal-parcel-scraper/app';
import { useI18n, type Translate } from '../../i18n';
import { formatJourneyDuration, yearInParcels, type PassportBook, type YearInParcels } from '../../lib/passport';
import { Icon } from '../Icon';
import { CARD_HEIGHT, CARD_WIDTH, drawYearCard, type CardWords } from './cardDrawing';

const ADDRESS = 'peektracker.com';

/** The year a card is offered for: this one, in December only. */
export function cardYear(now: number): number | null {
  const date = new Date(now);
  return date.getMonth() === 11 ? date.getFullYear() : null;
}

/** A country stamp's day, as its ink carries it: "12 AUG 2026". */
export function inkDay(at: number, languageTag: string): string {
  return new Intl.DateTimeFormat(languageTag, { day: 'numeric', month: 'short', year: 'numeric' }).format(new Date(at)).replaceAll('.', '').toLocaleUpperCase(languageTag);
}

export function cardWords(year: YearInParcels, t: Translate, languageTag: string): CardWords & { description: string } {
  const delivered = t('passport.card.delivered', { count: year.deliveredCount });
  const countries = year.countryCount ? t('passport.card.countries', { count: year.countryCount }) : null;
  const fastest = year.fastest === null ? null : t('passport.card.fastest', { time: formatJourneyDuration(year.fastest, languageTag) });
  const names = year.countries.map((country) => countryName(country.code, languageTag));
  return {
    inParcels: t('passport.card.inParcels'),
    lineOne: countries ? `${delivered} · ${countries}` : delivered,
    lineTwo: fastest ? `${fastest} · ${ADDRESS}` : ADDRESS,
    onlyCountry: year.countries.length === 1 ? names[0]! : null,
    inkDay: (at) => inkDay(at, languageTag),
    description: [t('passport.card.alt', { year: year.year }), countries ? `${delivered}, ${countries}: ${names.join(', ')}` : delivered, fastest].filter(Boolean).join('. ') + '.',
  };
}

async function renderCard(year: YearInParcels, words: CardWords): Promise<Blob | null> {
  await document.fonts?.ready;
  const canvas = document.createElement('canvas');
  canvas.width = CARD_WIDTH;
  canvas.height = CARD_HEIGHT;
  const context = canvas.getContext('2d');
  if (!context) return null;
  drawYearCard(context, year, words, getComputedStyle(document.body).fontFamily || 'system-ui, sans-serif');
  return new Promise((resolve) => canvas.toBlob(resolve, 'image/png'));
}

/** The year's card, offered in December once a parcel was delivered that year: a picture to share or save. */
export function YearCard({ book, now }: { book: PassportBook; now?: number }) {
  const { t, languageTag } = useI18n();
  // The day the passport opened decides; a page left open over midnight keeps its card until it opens again.
  const [opened] = useState(() => Date.now());
  const shownYear = cardYear(now ?? opened);
  const year = useMemo(() => shownYear === null ? null : yearInParcels(book, shownYear), [book, shownYear]);
  const words = useMemo(() => year && cardWords(year, t, languageTag), [languageTag, t, year]);
  const dialog = useRef<HTMLDialogElement>(null);
  const [picture, setPicture] = useState<{ blob: Blob; url: string } | null>(null);
  useEffect(() => () => { if (picture) URL.revokeObjectURL(picture.url); }, [picture]);
  if (!year || !words || !year.deliveredCount) return null;
  const file = () => picture && new File([picture.blob], `peek-${year.year}.png`, { type: 'image/png' });
  const canShare = typeof navigator !== 'undefined' && !!picture && !!navigator.canShare?.({ files: [file()!] });

  async function open() {
    dialog.current?.showModal();
    if (picture) return;
    const blob = await renderCard(year!, words!);
    if (blob) setPicture({ blob, url: URL.createObjectURL(blob) });
  }

  return <section className="passport-year" aria-label={t('passport.card.button', { year: year.year })}>
    <button type="button" className="passport-year__open" onClick={() => void open()}><Icon name="share" /><span>{t('passport.card.button', { year: year.year })}</span></button>
    <dialog ref={dialog} className="passport-year__sheet" aria-label={t('passport.card.button', { year: year.year })} onClick={(event) => { if (event.target === event.currentTarget) event.currentTarget.close(); }}>
      <div className="passport-year__picture">
        {/* eslint-disable-next-line @next/next/no-img-element -- a picture made on the device, from a blob */}
        {picture ? <img src={picture.url} alt={words.description} width={CARD_WIDTH} height={CARD_HEIGHT} /> : <span className="skeleton" role="status" aria-label={words.description} />}
      </div>
      <div className="passport-year__actions">
        {canShare && <button type="button" className="button button--primary" onClick={() => void navigator.share({ files: [file()!], title: t('passport.card.alt', { year: year.year }) }).catch(() => undefined)}>{t('passport.card.share')}</button>}
        {picture && <a className={`button ${canShare ? 'button--secondary' : 'button--primary'}`} href={picture.url} download={`peek-${year.year}.png`}>{t('passport.card.save')}</a>}
        <button type="button" className="button button--secondary" onClick={() => dialog.current?.close()}>{t('common.close')}</button>
      </div>
    </dialog>
  </section>;
}

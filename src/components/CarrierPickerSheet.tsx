import { memo, startTransition, useEffect, useId, useMemo, useRef, useState, type KeyboardEvent, type PointerEvent, type ReactNode } from 'react';
import { createPortal, flushSync } from 'react-dom';
import { useI18n } from '../i18n';
import { carrierInfo, SELECTABLE_CARRIERS, type CarrierInfo } from '../lib/carriers';
import {
  alphabetSections,
  countryLine,
  searchCarriers,
  type CarrierSearchResult,
} from '../lib/carrierPicker';
import { useSheetDialog } from '../lib/modal';
import { countryName } from 'universal-parcel-scraper/app';
import type { CarrierId } from '../types';
import { CarrierTruck, TruckDefinitions } from './CarrierMark';
import { Icon } from './Icon';
import './CarrierPickerSheet.css';

export interface CarrierPickerSection {
  key: string;
  title: string;
  carriers: readonly CarrierId[];
}

export interface CarrierPickerTag {
  label: string;
  tone: 'quiet' | 'found';
}

type Choice = CarrierId | 'auto';

interface CarrierOption {
  id: string;
  carrier: CarrierInfo;
  result?: CarrierSearchResult;
  /** The line under the name: the other name that matched, or the carrier's countries. */
  sub: string;
}

type Option = CarrierOption | { id: string; carrier?: undefined };

/** A run of rows under one heading, and where its first row stands among all the rows. */
interface Run<Section> { start: number; options: CarrierOption[]; section: Section }

/** Runs one after another, the first starting at row `first`. */
function runs<Section>(sections: readonly Omit<Run<Section>, 'start'>[], first: number): Run<Section>[] {
  let start = first;
  return sections.map((run) => {
    start += run.options.length;
    return { ...run, start: start - run.options.length };
  });
}

/**
 * Rows drawn as the sheet opens, a few screens' worth; the rest follow a batch a frame, so a slow phone opens the
 * sheet at once and the A to Z list fills in below the fold.
 */
const FIRST_ROWS = 40;
const ROWS_PER_FRAME = 120;

/** The name with the searched text marked, when the name itself matched. */
function highlighted(name: string, range?: readonly [number, number]): ReactNode {
  if (!range) return name;
  const characters = Array.from(name);
  return <>
    {characters.slice(0, range[0]).join('')}
    <mark>{characters.slice(range[0], range[1]).join('')}</mark>
    {characters.slice(range[1]).join('')}
  </>;
}

/**
 * One carrier's row. It draws again only when its own state changes, so a new batch of rows or a moved highlight
 * leaves the others alone.
 */
const CarrierRow = memo(function CarrierRow({ item, index, active, selected, tag, trucks, onSelect, onActive }: {
  item: CarrierOption;
  index: number;
  active: boolean;
  selected: boolean;
  tag?: CarrierPickerTag;
  /** The id of the sheet's `TruckDefinitions`. */
  trucks: string;
  onSelect: (choice: Choice) => void;
  onActive: (index: number) => void;
}) {
  const { id, carrier, result, sub } = item;
  return <div id={id} role="option" aria-selected={selected} className={`carrier-picker__option${active ? ' is-active' : ''}`}
    aria-labelledby={`${id}-name`} aria-describedby={sub || tag ? `${id}-more` : undefined}
    onClick={() => onSelect(carrier.id)} onPointerMove={() => onActive(index)}>
    <CarrierTruck carrier={carrier} shared={trucks} />
    <span className="carrier-picker__text">
      <span id={`${id}-name`} className="carrier-picker__name">{highlighted(carrier.name, result?.highlight)}</span>
      {sub && <span className="carrier-picker__sub">{sub}</span>}
    </span>
    {(sub || tag) && <span id={`${id}-more`} hidden>{[sub, tag?.label].filter(Boolean).join(', ')}</span>}
    {tag && <span className={`carrier-picker__tag carrier-picker__tag--${tag.tone}`} aria-hidden="true">{tag.label}</span>}
    {selected && <Icon name="check" className="carrier-picker__check" />}
  </div>;
});

/**
 * Choose one of the catalog's carriers: automatic detection first when it is
 * offered, then the sections the caller passes (the carriers that fit the
 * number, the ones used before), then every carrier from A to Z. Typing
 * searches names, other names and countries.
 *
 * The sections keep the order they opened with: an answer that arrives while
 * the sheet is open only changes tags and the automatic row's text, so nothing
 * moves under the reader's finger.
 *
 * The rows come in batches (`FIRST_ROWS`, then `ROWS_PER_FRAME`), and the
 * trucks share one drawing of what they have in common. A letter on the rail
 * or the keyboard's highlight draws the rows it reaches at once.
 */
export function CarrierPickerSheet({
  selected,
  auto,
  sections,
  tags = {},
  onSelect,
  onClose,
}: {
  selected: Choice;
  /** Offers automatic detection, described by what the carrier check knows so far. */
  auto?: { description: string; recommended: boolean; busy: boolean };
  sections: readonly CarrierPickerSection[];
  tags?: Partial<Record<CarrierId, CarrierPickerTag>>;
  onSelect: (choice: Choice) => void;
  onClose: () => void;
}) {
  const { locale, languageTag, t } = useI18n();
  const [query, setQuery] = useState('');
  const [active, setActive] = useState(0);
  const [drawn, setDrawn] = useState(FIRST_ROWS);
  const [openingSections] = useState(sections);
  const search = useRef<HTMLInputElement>(null);
  const list = useRef<HTMLDivElement>(null);
  const rail = useRef<HTMLElement>(null);
  // The letter under a finger held on the rail, and where its bubble sits.
  const [scrub, setScrub] = useState<{ letter: string; y: number } | null>(null);
  const scrubbing = useRef<string | null>(null);
  const scrollToActive = useRef(false);
  const baseId = useId();
  const titleId = `${baseId}-title`;
  const listId = `${baseId}-list`;
  // Referred to from `href`, so only characters a URL fragment keeps as they are.
  const trucks = `${baseId.replace(/[^\w-]/g, '')}-truck`;
  // A touch keyboard would cover the list, so only a pointer device starts in the search field.
  const [startInSearch] = useState(() => window.matchMedia?.('(pointer: fine)').matches ?? true);
  const [dialog, close] = useSheetDialog<HTMLDivElement>(true, onClose, startInSearch ? search : undefined);

  const carriers = useMemo(
    () => SELECTABLE_CARRIERS.map((carrier) => carrierInfo(carrier.id, locale)),
    [locale],
  );
  // Countries are shown in the reader's language and searched in theirs and in English.
  const countryNameLists = useMemo(() => new Map(
    [...new Set(carriers.flatMap((carrier) => carrier.countries))].map((code) =>
      [code, [...new Set([countryName(code, languageTag), countryName(code, 'en')])]]),
  ), [carriers, languageTag]);
  const countryNames = useMemo(
    () => (code: string): readonly string[] => countryNameLists.get(code) ?? [countryName(code, languageTag)],
    [countryNameLists, languageTag],
  );

  const trimmed = query.trim();
  const results = useMemo(() => trimmed ? searchCarriers(trimmed, carriers, {
    languageTag,
    countryNames,
    preferred: new Set(openingSections[0]?.carriers ?? []),
  }) : null, [trimmed, carriers, languageTag, countryNames, openingSections]);

  // The rows, built once for each list so that a row keeps its props from one batch to the next.
  const offersAuto = Boolean(auto);
  const { autoOption, browsing, alphabet, letters } = useMemo(() => {
    const subtitle = (carrier: CarrierInfo) => countryLine(carrier.countries, (code) => countryNames(code)[0]);
    const option = (key: string, carrier: CarrierInfo): CarrierOption => ({
      id: `${baseId}-${key}-${carrier.id}`, carrier, sub: subtitle(carrier),
    });
    const letters = alphabetSections(carriers, languageTag);
    const first = offersAuto ? 1 : 0;
    const browsing = runs(openingSections.map((section) => ({
      section, options: section.carriers.map((id) => option(section.key, carrierInfo(id, locale))),
    })), first);
    const alphabet = runs(letters.map((section) => ({
      section, options: section.carriers.map((carrier) => option('all', carrier)),
    })), first + browsing.reduce((count, run) => count + run.options.length, 0));
    return { autoOption: offersAuto ? { id: `${baseId}-auto` } : null, browsing, alphabet, letters };
  }, [offersAuto, baseId, carriers, countryNames, languageTag, locale, openingSections]);
  const found = useMemo(() => results?.map((result): CarrierOption => ({
    id: `${baseId}-result-${result.carrier.id}`, carrier: result.carrier, result,
    sub: result.alias ? t('picker.alias', { name: result.alias }) : countryLine(result.carrier.countries, (code) => countryNames(code)[0]),
  })) ?? null, [results, baseId, t, countryNames]);
  const options: Option[] = useMemo(() => found
    ?? [...(autoOption ? [autoOption] : []), ...browsing.flatMap((run) => run.options), ...alphabet.flatMap((run) => run.options)],
  [found, autoOption, browsing, alphabet]);
  const activeIndex = Math.min(active, options.length - 1);
  const activeOption = options[activeIndex];
  // Rows up to the highlighted one are always drawn, however far the keyboard took it.
  const shown = Math.max(drawn, activeIndex + 1);

  // One more batch a frame, as a transition: typing or a tap goes first.
  const total = options.length;
  useEffect(() => {
    if (drawn >= total) return;
    const frame = requestAnimationFrame(() => startTransition(() => setDrawn((count) => count + ROWS_PER_FRAME)));
    return () => cancelAnimationFrame(frame);
  }, [drawn, total]);

  const activeId = activeOption?.id;
  useEffect(() => {
    if (!scrollToActive.current || !activeId) return;
    scrollToActive.current = false;
    document.getElementById(activeId)?.scrollIntoView?.({ block: 'nearest' });
  }, [activeId]);

  function move(step: number) {
    scrollToActive.current = true;
    setActive((current) => Math.max(0, Math.min(options.length - 1, Math.min(current, options.length - 1) + step)));
  }

  function onKeyDown(event: KeyboardEvent<HTMLInputElement>) {
    // Keys that work an input method's candidates are its own: Safari sends the Enter that picks one after the
    // composition ended, with key code 229.
    if (event.nativeEvent.isComposing || event.keyCode === 229) return;
    if (event.key === 'ArrowDown') { event.preventDefault(); move(1); }
    else if (event.key === 'ArrowUp') { event.preventDefault(); move(-1); }
    else if (event.key === 'Enter' && activeOption) { event.preventDefault(); onSelect(activeOption.carrier?.id ?? 'auto'); }
  }

  function changeQuery(value: string) {
    setQuery(value);
    setActive(0);
    setDrawn(FIRST_ROWS);
    list.current?.scrollTo?.({ top: 0 });
  }

  function jump(letter: string) {
    // The letter's rows, and a screen's worth after them so that it can reach the top of the list, are drawn first.
    const run = alphabet.find((candidate) => candidate.section.letter === letter);
    const reach = run ? run.start + run.options.length + FIRST_ROWS : 0;
    if (reach > shown) flushSync(() => setDrawn(reach));
    list.current?.querySelector(`[data-letter="${CSS.escape(letter)}"]`)?.scrollIntoView?.({ block: 'start' });
  }

  /**
   * The rail answers the finger, not the release: pressing a letter jumps to
   * it, and sliding jumps to each letter it crosses. Past either end the first
   * or last letter holds, as in the iPhone's section index.
   */
  function scrubTo(clientY: number) {
    const buttons = Array.from(rail.current?.querySelectorAll<HTMLButtonElement>('button') ?? []);
    let nearest: HTMLButtonElement | undefined;
    let distance = Infinity;
    for (const button of buttons) {
      const { top, bottom } = button.getBoundingClientRect();
      const gap = clientY < top ? top - clientY : clientY > bottom ? clientY - bottom : 0;
      if (gap < distance) { distance = gap; nearest = button; }
    }
    const letter = nearest?.dataset.letter;
    if (!nearest || !letter || letter === scrubbing.current) return;
    scrubbing.current = letter;
    setScrub({ letter, y: nearest.offsetTop + nearest.offsetHeight / 2 });
    jump(letter);
  }

  function startScrub(event: PointerEvent<HTMLElement>) {
    if (event.pointerType === 'mouse' && event.button !== 0) return;
    // No text selection, focus change or page scroll while the finger is down.
    event.preventDefault();
    event.currentTarget.setPointerCapture?.(event.pointerId);
    scrubbing.current = null;
    scrubTo(event.clientY);
  }

  function endScrub() {
    scrubbing.current = null;
    setScrub(null);
  }

  const row = (item: CarrierOption, index: number) => <CarrierRow key={item.id} item={item} index={index} active={index === activeIndex}
    selected={item.carrier.id === selected} tag={tags[item.carrier.id]} trucks={trucks} onSelect={onSelect} onActive={setActive} />;
  // The drawn rows of a run: none when it starts past them.
  const rows = <Section,>(run: Run<Section>) => run.options.slice(0, Math.max(0, shown - run.start)).map((item, offset) => row(item, run.start + offset));

  return createPortal(
    <div className="sheet-backdrop carrier-picker-backdrop" onClick={close}>
      <div
        ref={dialog}
        className="sheet carrier-picker"
        role="dialog"
        aria-modal="true"
        aria-labelledby={titleId}
        tabIndex={-1}
        onClick={(event) => event.stopPropagation()}
      >
        <TruckDefinitions id={trucks} />
        <div className="carrier-picker__heading">
          <button type="button" className="carrier-picker__cancel" onClick={close}>{t('common.cancel')}</button>
          <h2 className="carrier-picker__title" id={titleId}>{t('add.carrier')}</h2>
        </div>
        <label className="carrier-picker__search">
          <Icon name="search" />
          <input
            ref={search}
            type="search"
            role="combobox"
            aria-label={t('picker.searchLabel')}
            aria-expanded="true"
            aria-controls={listId}
            aria-autocomplete="list"
            aria-activedescendant={activeId}
            placeholder={t('picker.search', { count: carriers.length })}
            value={query}
            onChange={(event) => changeQuery(event.target.value)}
            onKeyDown={onKeyDown}
            enterKeyHint="done"
            autoComplete="off"
            autoCorrect="off"
            autoCapitalize="none"
            spellCheck={false}
          />
          {query && (
            <button type="button" className="carrier-picker__clear" aria-label={t('picker.clear')} onClick={() => { changeQuery(''); search.current?.focus(); }}>
              <Icon name="close" />
            </button>
          )}
        </label>
        <div className="carrier-picker__body">
          <div ref={list} id={listId} role="listbox" aria-labelledby={titleId} className="carrier-picker__list">
            {found ? <>
              {found.length > 0 && <div className="carrier-picker__section-title" aria-hidden="true">
                {t('picker.results.many', { count: found.length })}
              </div>}
              {found.slice(0, shown).map(row)}
            </> : <>
              {autoOption && <div id={autoOption.id} role="option" aria-selected={selected === 'auto'}
                className={`carrier-picker__option${activeIndex === 0 ? ' is-active' : ''} carrier-picker__option--auto`}
                onClick={() => onSelect('auto')} onPointerMove={() => setActive(0)}>
                <span className={`carrier-picker__detect${auto?.busy ? ' is-busy' : ''}`}><Icon name="detect" /></span>
                <span className="carrier-picker__text">
                  <span className="carrier-picker__name">{t('add.detect')}</span>
                  <span className="carrier-picker__sub">
                    {auto?.recommended && <><span className="carrier-picker__tag carrier-picker__tag--quiet">{t('picker.recommended')}</span>{' '}</>}
                    {auto?.description}
                  </span>
                </span>
                {selected === 'auto' && <Icon name="check" className="carrier-picker__check" />}
              </div>}
              {browsing.filter((run) => run.options.length > 0 && run.start < shown).map((run) => (
                <div key={run.section.key} role="group" aria-labelledby={`${baseId}-${run.section.key}`}>
                  <div id={`${baseId}-${run.section.key}`} role="presentation" className="carrier-picker__section-title">{run.section.title}</div>
                  {rows(run)}
                </div>
              ))}
              <div role="group" aria-labelledby={`${baseId}-all`}>
                <div id={`${baseId}-all`} role="presentation" className="carrier-picker__section-title">{t('picker.section.all')}</div>
                {alphabet.filter((run) => run.start < shown).map((run) => (
                  // Each letter holds its own rows, so its sticky heading leaves with them.
                  <div key={run.section.letter} role="presentation" data-letter={run.section.letter}>
                    <div className="carrier-picker__letter" aria-hidden="true">{run.section.letter}</div>
                    {rows(run)}
                  </div>
                ))}
              </div>
            </>}
          </div>
          {found?.length === 0 && <p className="carrier-picker__empty" role="status">{t('picker.empty', { query: trimmed })}</p>}
          {!found && (
            <nav
              ref={rail}
              className="carrier-picker__rail"
              aria-label={t('picker.jump')}
              onPointerDown={startScrub}
              onPointerMove={(event) => { if (scrub) scrubTo(event.clientY); }}
              onPointerUp={endScrub}
              onPointerCancel={endScrub}
              onLostPointerCapture={endScrub}
            >
              {letters.map((section) => (
                <button
                  key={section.letter}
                  type="button"
                  tabIndex={-1}
                  data-letter={section.letter}
                  className={scrub?.letter === section.letter ? 'is-current' : undefined}
                  // A press already jumped; this click is an assistive technology's activation.
                  onClick={(event) => { if (event.detail === 0) jump(section.letter); }}
                >
                  {section.letter}
                </button>
              ))}
              {scrub && <span className="carrier-picker__bubble" style={{ top: scrub.y }} aria-hidden="true">{scrub.letter}</span>}
            </nav>
          )}
        </div>
      </div>
    </div>,
    document.body,
  );
}

import { useId, useLayoutEffect, useRef, useState, useSyncExternalStore, type CSSProperties, type KeyboardEvent, type PointerEvent, type ReactNode } from 'react';

/** Wide enough for the passport to lie open: two pages side by side. */
const OPEN_BOOK = '(min-width: 960px)';

function subscribeOpenBook(notify: () => void) {
  const query = window.matchMedia?.(OPEN_BOOK);
  query?.addEventListener('change', notify);
  return () => query?.removeEventListener('change', notify);
}

const isOpenBook = () => !!window.matchMedia?.(OPEN_BOOK).matches;

export interface PassportPageTab { label: string; dot: boolean; content: ReactNode }

/**
 * The passport's pages on one track under their labels, the neighbour's edge showing. A label or a swipe turns the page;
 * the track follows the finger and commits past a quarter of a page or a quick flick. On a wide screen the book lies
 * open: the first two pages side by side, or the last two once the last is chosen.
 */
export function PassportPages({ label, tabs, page, onPage }: { label: string; tabs: PassportPageTab[]; page: number; onPage: (page: number) => void }) {
  const id = useId();
  const open = useSyncExternalStore(subscribeOpenBook, isOpenBook, () => false);
  const viewport = useRef<HTMLDivElement>(null);
  const gesture = useRef<{ x: number; y: number; at: number; moved: boolean } | null>(null);
  const swiped = useRef(false);
  const [drag, setDrag] = useState<number | null>(null);
  const last = tabs.length - 1;
  // The first page in view: on an open book the spread holds the chosen page.
  const offset = open ? (page === last ? last - 1 : 0) : page;
  const shown = (index: number) => index === offset || (open && index === offset + 1);
  const lastOffset = open ? last - 1 : last;
  const track = useRef<HTMLDivElement>(null);
  // The pages out of view stand as tall as the one in view, so a short page leaves no empty paper below it.
  const [height, setHeight] = useState<number | null>(null);
  useLayoutEffect(() => {
    const sheets = [...track.current?.querySelectorAll<HTMLElement>(':scope > .is-shown') ?? []];
    const measure = () => setHeight(Math.max(0, ...sheets.map((sheet) => sheet.offsetHeight)) || null);
    measure();
    if (typeof ResizeObserver === 'undefined') return;
    const observer = new ResizeObserver(measure);
    for (const sheet of sheets) observer.observe(sheet);
    return () => observer.disconnect();
  }, [offset, open]);

  function step() {
    const width = viewport.current?.clientWidth ?? 0;
    return open ? width / 2 + 5 : width - 18;
  }

  function down(event: PointerEvent<HTMLDivElement>) {
    if (event.pointerType === 'mouse' && event.button !== 0) return;
    gesture.current = { x: event.clientX, y: event.clientY, at: event.timeStamp, moved: false };
    swiped.current = false;
  }

  function move(event: PointerEvent<HTMLDivElement>) {
    const current = gesture.current;
    if (!current) return;
    let dx = event.clientX - current.x;
    const dy = event.clientY - current.y;
    if (!current.moved) {
      if (Math.abs(dy) > 8 && Math.abs(dy) > Math.abs(dx)) { gesture.current = null; return; }
      if (Math.abs(dx) < 8) return;
      current.moved = true;
      swiped.current = true;
      viewport.current?.setPointerCapture(event.pointerId);
    }
    if ((offset === 0 && dx > 0) || (offset === lastOffset && dx < 0)) dx *= 0.3;
    setDrag(dx);
  }

  function up(event: PointerEvent<HTMLDivElement>) {
    const current = gesture.current;
    gesture.current = null;
    if (!current?.moved) { setDrag(null); return; }
    const dx = event.clientX - current.x;
    const speed = Math.abs(dx) / Math.max(1, event.timeStamp - current.at) * 1000;
    setDrag(null);
    if (Math.abs(dx) < step() * 0.25 && speed < 400) return;
    const target = Math.max(0, Math.min(lastOffset, offset + (dx < 0 ? 1 : -1)));
    if (target !== offset) onPage(open ? (target === lastOffset ? last : 0) : target);
  }

  function key(event: KeyboardEvent<HTMLButtonElement>, index: number) {
    const next = event.key === 'ArrowRight' ? index + 1 : event.key === 'ArrowLeft' ? index - 1 : event.key === 'Home' ? 0 : event.key === 'End' ? last : null;
    if (next === null || next < 0 || next > last) return;
    event.preventDefault();
    onPage(next);
    document.getElementById(`${id}-tab-${next}`)?.focus();
  }

  const travel = open ? `${-offset} * (50% + 5px)` : `${-offset} * (100% - 18px)`;
  return <div className={`passport-pages${open ? ' passport-pages--open' : ''}`}>
    <div className="passport-pages__tabs" role="tablist" aria-label={label} style={{ '--tab': page, '--tabs': tabs.length } as CSSProperties}>
      <span className="passport-pages__pill" aria-hidden="true" />
      {tabs.map((tab, index) => <button key={tab.label} type="button" role="tab" id={`${id}-tab-${index}`} aria-selected={index === page}
        aria-controls={`${id}-page-${index}`} tabIndex={index === page ? 0 : -1} className={index === page ? 'is-selected' : undefined}
        onClick={() => onPage(index)} onKeyDown={(event) => key(event, index)}>
        {tab.label}{tab.dot && <i className="passport-pages__dot" aria-hidden="true" />}
      </button>)}
    </div>
    <div ref={viewport} className="passport-pages__viewport" onPointerDown={down} onPointerMove={move} onPointerUp={up} onPointerCancel={() => { gesture.current = null; setDrag(null); }}
      onClickCapture={(event) => { if (swiped.current) { event.preventDefault(); event.stopPropagation(); swiped.current = false; } }}>
      <div ref={track} className={`passport-pages__track${drag === null ? '' : ' is-dragging'}`} style={{ transform: `translateX(calc(${travel} + ${drag ?? 0}px))` }}>
        {tabs.map((tab, index) => <section key={tab.label} id={`${id}-page-${index}`} role="tabpanel" aria-labelledby={`${id}-tab-${index}`}
          className={`passport-sheet${shown(index) ? ' is-shown' : ''}`} inert={!shown(index)} style={shown(index) || height === null ? undefined : { height }}>
          {tab.content}
          <span className="passport-sheet__number" aria-hidden="true">{index + 1}</span>
        </section>)}
      </div>
    </div>
  </div>;
}

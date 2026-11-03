import { useEffect, useLayoutEffect, useMemo, useRef, useState, type RefObject } from 'react';
import { throwConfetti } from './deliveredConfetti';
import { DELIVERY_CARDS, moveDeliveredCards, type CardList } from './deliveredMove';
import { justDelivered } from './justDelivered';
import { isActiveParcel } from './parcelPriority';
import { currentEvent, isDelivered, isFinal } from './stages';
import type { ParcelWithEvents } from '../types';

/** Long enough to read "Delivered" on the card and to see the postmark land on its stamp. */
export const DELIVERED_HOLD_MS = 950;
/** Pip's box, which opens with the news, is open by then: the paper comes out of it. */
const CONFETTI_AT_MS = 330;
/** After a finger lifts, long enough for its tap to have opened what it was aimed at. */
const AFTER_TOUCH_MS = 150;
/** A delivery older than this is no news by the time the list is seen again. */
export const RECENT_DELIVERY_MS = 7 * 24 * 3_600_000;
/** Where this browser keeps the parcels each list last showed on their way. */
export const ON_THE_WAY = { deliveries: 'sdt.deliveries.onTheWay.v1', device: 'sdt.device.onTheWay.v1' } as const;

/** The parcels that have just arrived, and every parcel as the list showed it before they did. */
interface Hold {
  arrivals: ReadonlySet<string>;
  shown: ReadonlyMap<string, ParcelWithEvents>;
}
const NONE: Hold = { arrivals: new Set(), shown: new Map() };

/** The parcel as it stood on its way, before its delivery: its journal without the final scans. */
export function onItsWay(parcel: ParcelWithEvents): ParcelWithEvents {
  return { ...parcel, events: parcel.events.filter((event) => !isFinal(event.stage)) };
}

/** The parcels a list last showed on their way, as this browser keeps them under `key`. */
function recorded(key: string): ReadonlySet<string> {
  try {
    const ids: unknown = JSON.parse(localStorage.getItem(key) ?? '[]');
    return new Set(Array.isArray(ids) ? ids.filter((id): id is string => typeof id === 'string') : []);
  } catch {
    return new Set();
  }
}

/**
 * Of `parcels`, those the list last showed on their way (the record under `key`) that have
 * been delivered since, at most a week before `now`, and aren't archived.
 */
export function missedDeliveries(key: string, parcels: ParcelWithEvents[], now = Date.now()): ParcelWithEvents[] {
  const ids = recorded(key);
  if (!ids.size) return [];
  return parcels.filter((parcel) => {
    if (!ids.has(parcel.id) || parcel.archivedAt || !isDelivered(parcel.events)) return false;
    const at = Date.parse(currentEvent(parcel.events)?.occurredAt ?? '');
    return now - at < RECENT_DELIVERY_MS;
  });
}

/**
 * The list is in view, showing `parcels`: those on their way are the ones it last showed so,
 * with the ones in `holding`, delivered and held where they stood but not yet shown so. A
 * list with no parcel, as one still loading is, changes nothing.
 */
function remember(key: string, parcels: ParcelWithEvents[], holding: ReadonlySet<string>) {
  if (!parcels.length) return;
  const ids = JSON.stringify([...new Set([...parcels.filter(isActiveParcel).map((parcel) => parcel.id), ...holding])].sort());
  try {
    if (localStorage.getItem(key) !== ids) localStorage.setItem(key, ids);
  } catch {
    // A browser that keeps nothing has nothing to catch up with.
  }
}

/** These parcels have been shown delivered: the list counts them so. */
function forget(key: string, arrived: ReadonlySet<string>) {
  const ids = recorded(key);
  if (![...arrived].some((id) => ids.has(id))) return;
  try {
    localStorage.setItem(key, JSON.stringify([...ids].filter((id) => !arrived.has(id)).sort()));
  } catch {
    // Nothing kept, nothing to forget.
  }
}

/** Whether a card can be shown moving: where it cannot, or should not, the list changes at once. */
function moves() {
  return typeof Element.prototype.animate === 'function'
    && !(window.matchMedia?.('(prefers-reduced-motion: reduce)').matches ?? false);
}

/**
 * A parcel delivered since the list was last shown does not jump to the past deliveries.
 * Its card first turns to Delivered where it stands, then travels there. While `watching`,
 * the list is arranged from `arranged`, in which every parcel is still as it was shown;
 * `arrived` tells which cards to draw with the news, and paper is thrown from the first of
 * them. Every card stays what it is under a finger, and a list that is covered, or that shows
 * no motion, changes without any of this. `list` describes the cards of a list other than
 * the deliveries.
 *
 * With a `record`, the browser keeps under that key which parcels the list last showed on
 * their way. One delivered while the list was out of view (its page open, another tab, the
 * browser's tab hidden, or the page reloaded) arrives on the list the next time it is in
 * view, as one seen arriving would, if the delivery is at most a week old: once, as the list
 * then counts it delivered. An archived or deleted parcel never does.
 */
export function useDeliveredHold(parcels: ParcelWithEvents[], watching: boolean, root: RefObject<HTMLElement | null>, list: CardList = DELIVERY_CARDS, record?: string) {
  const [seen, setSeen] = useState(parcels);
  const [looked, setLooked] = useState(false);
  const [held, setHeld] = useState(NONE);
  let holding = held;
  if (parcels !== seen || watching !== looked) {
    if (parcels !== seen) setSeen(parcels);
    if (watching !== looked) setLooked(watching);
    const current = new Map(parcels.map((parcel) => [parcel.id, parcel]));
    // A held parcel stays in its place for as long as it is still a delivery to show.
    const arrivals = new Set([...held.arrivals].filter((id) => {
      const parcel = current.get(id);
      return parcel !== undefined && !parcel.archivedAt && isDelivered(parcel.events);
    }));
    const live = watching && moves() ? justDelivered(seen, parcels).filter((was) => !arrivals.has(was.id)) : [];
    // Parcels that arrived while the list was out of view arrive now that it is in view.
    const late = watching && moves() && record
      ? missedDeliveries(record, parcels).filter((parcel) => !arrivals.has(parcel.id) && !live.some((was) => was.id === parcel.id))
      : [];
    if (live.length || late.length || arrivals.size !== held.arrivals.size) {
      // The whole list keeps the arrangement it had: another parcel's news must not move the arrival out of its place.
      // One caught up with holds only itself where it stood on its way.
      const shown = new Map(held.arrivals.size ? held.shown : live.length ? seen.map((parcel) => [parcel.id, parcel]) : []);
      for (const was of live) {
        arrivals.add(was.id);
        if (!shown.has(was.id)) shown.set(was.id, was);
      }
      for (const parcel of late) {
        arrivals.add(parcel.id);
        shown.set(parcel.id, onItsWay(parcel));
      }
      holding = arrivals.size ? { arrivals, shown } : NONE;
    }
  }
  if (!watching) holding = NONE;
  if (holding !== held) setHeld(holding);

  // The parcels whose arrival has had its paper: those that arrive together share one burst.
  const celebrated = useRef<ReadonlySet<string>>(NONE.arrivals);
  // The list in view says which parcels it shows on their way, with those held where they stood that have yet to have
  // their paper: a hold cut short before then still has them to show.
  useEffect(() => {
    if (record && watching) remember(record, parcels, new Set([...holding.arrivals].filter((id) => !celebrated.current.has(id))));
  }, [record, watching, parcels, holding]);
  useLayoutEffect(() => {
    if (!holding.arrivals.size) {
      celebrated.current = NONE.arrivals;
      return;
    }
    let timer = 0;
    let paper = 0;
    let pressing = false;
    let due = false;
    const release = () => {
      // The list does not change under a finger that is down: a tap lands on the card it was aimed at.
      if (pressing) {
        due = true;
        return;
      }
      moveDeliveredCards(root.current, [...holding.arrivals], list);
      setHeld(NONE);
    };
    // A list that nobody sees keeps the card where it is until someone looks.
    const wait = () => {
      window.clearTimeout(timer);
      window.clearTimeout(paper);
      if (document.hidden) return;
      timer = window.setTimeout(release, DELIVERED_HOLD_MS);
      const fresh = [...holding.arrivals].filter((id) => !celebrated.current.has(id));
      if (fresh.length) paper = window.setTimeout(() => {
        celebrated.current = holding.arrivals;
        throwConfetti(root.current, fresh, list);
        // Shown delivered: not again, even should the list go out of view before the cards leave.
        if (record) forget(record, holding.arrivals);
      }, CONFETTI_AT_MS);
    };
    const press = () => { pressing = true; };
    const lift = () => {
      pressing = false;
      if (!due) return;
      due = false;
      window.clearTimeout(timer);
      timer = window.setTimeout(release, AFTER_TOUCH_MS);
    };
    wait();
    document.addEventListener('visibilitychange', wait);
    window.addEventListener('pointerdown', press, { capture: true, passive: true });
    window.addEventListener('pointerup', lift, { capture: true, passive: true });
    window.addEventListener('pointercancel', lift, { capture: true, passive: true });
    return () => {
      window.clearTimeout(timer);
      window.clearTimeout(paper);
      document.removeEventListener('visibilitychange', wait);
      window.removeEventListener('pointerdown', press, { capture: true });
      window.removeEventListener('pointerup', lift, { capture: true });
      window.removeEventListener('pointercancel', lift, { capture: true });
    };
  }, [holding, root, list, record]);

  const arranged = useMemo(() => holding.arrivals.size ? parcels.map((parcel) => {
    // A parcel archived or brought back in the meantime is arranged as it is now.
    const was = holding.shown.get(parcel.id);
    return was && Boolean(was.archivedAt) === Boolean(parcel.archivedAt) ? was : parcel;
  }) : parcels, [parcels, holding]);
  const fresh = useMemo(() => new Map(holding.arrivals.size ? parcels.map((parcel) => [parcel.id, parcel]) : []), [parcels, holding]);
  return {
    /** The parcels as the list arranges them. */
    arranged,
    /** Whether this parcel has just been delivered and is still shown where it stood. */
    arrived: (parcel: ParcelWithEvents) => holding.arrivals.has(parcel.id),
    /** The parcel as it is now, for a card arranged as it was. */
    current: (parcel: ParcelWithEvents) => fresh.get(parcel.id) ?? parcel,
  };
}

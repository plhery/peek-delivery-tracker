import { useEffect, useLayoutEffect, useRef, useState } from 'react';
import { CARRIER_DEFINITIONS, shopTrackingLinks } from 'universal-parcel-scraper';
import type { ApiShopLinkResponse, ApiUnresolvedShopLink } from '../../generated/apiContract';
import type { ApiAuth } from '../../lib/apiClient';
import { keepShopWait, readShopLink } from '../../lib/shopWaitsApi';
import type { CarrierId } from '../../types';

export type ShopLinkReading =
  | { state: 'none' }
  | { state: 'reading' }
  /** The link names no parcel yet, or none Peek can read: `link` says why and for which shop. */
  | { state: 'unresolved'; link: ApiUnresolvedShopLink }
  /** The shop's platform did not answer. */
  | { state: 'failed' };

/**
 * A shop's tracking-page link pasted or shared into Add, where the text holds no number of its own: Peek asks the
 * shop's platform what it names. A parcel it names fills the number in (`onFound`), with its carrier; when it names
 * none yet, the sheet says so and, signed in, offers to wait for the shop (`wait`).
 */
export function useShopLinkReading(
  text: string,
  hasNumber: boolean,
  auth: ApiAuth | undefined,
  onFound: (trackingNumber: string, carrier: CarrierId | null) => void,
) {
  const [reading, setReading] = useState<{ text: string; value: ShopLinkReading }>({ text: '', value: { state: 'none' } });
  const found = useRef(onFound);
  useLayoutEffect(() => { found.current = onFound; });
  const applies = !hasNumber && shopTrackingLinks(text).length > 0;

  useEffect(() => {
    if (!applies) return;
    const controller = new AbortController();
    readShopLink(text, auth, controller.signal).then((answer: ApiShopLinkResponse) => {
      if (controller.signal.aborted) return;
      const parcel = answer.parcels[0];
      if (parcel) {
        setReading({ text, value: { state: 'none' } });
        const carrier = parcel.carrier in CARRIER_DEFINITIONS && parcel.carrier !== 'unknown' ? parcel.carrier as CarrierId : null;
        found.current(parcel.tracking_number, carrier);
        return;
      }
      setReading({ text, value: answer.unresolved[0] ? { state: 'unresolved', link: answer.unresolved[0] } : { state: 'failed' } });
    }, () => {
      if (!controller.signal.aborted) setReading({ text, value: { state: 'failed' } });
    });
    return () => controller.abort();
  }, [applies, text, auth]);

  const value: ShopLinkReading = applies && reading.text === text ? reading.value : applies ? { state: 'reading' } : { state: 'none' };
  const canWait = Boolean(auth) && value.state === 'unresolved' && value.link.waitable;
  return {
    reading: value,
    /** The sheet reads the link: its own "no number" notice gives way. */
    applies,
    canWait,
    shop: value.state === 'unresolved' ? value.link.shop_name : null,
    /** Waits for the link's parcels, named `label`. */
    wait: (label: string) => (auth ? keepShopWait(text, label, auth) : Promise.reject(new Error('Sign in to wait'))),
  };
}

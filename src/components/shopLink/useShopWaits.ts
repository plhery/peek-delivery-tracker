import { useCallback, useEffect, useLayoutEffect, useRef, useState } from 'react';
import type { ApiShopWait } from '../../generated/apiContract';
import type { ApiAuth } from '../../lib/apiClient';
import { listShopWaits, removeShopWait, SHOP_WAITS_CHANGED } from '../../lib/shopWaitsApi';

/** How often the list reads its waits while open: the server asks the shops every few hours. */
const READ_EVERY_MS = 10 * 60_000;
const DAY_MS = 86_400_000;

/** The demo's wait: a coat H&M hasn't shipped yet. */
export function demoShopWait(now = Date.now()): ApiShopWait {
  return {
    id: 'demo-shop-wait', platform: 'parcellab', platform_name: 'parcelLab', shop_name: 'H&M', label: 'Wool coat',
    created_at: new Date(now - DAY_MS).toISOString(), expires_at: new Date(now + 13 * DAY_MS).toISOString(), expired_at: null,
  };
}

/**
 * The account's waits for shops to ship, read when the list opens, comes back into view, every few minutes and when
 * Add keeps one. A wait that goes without the reader removing it became parcels: `onResolved` reads the list again.
 * The demo shows one; a guest's device has none.
 */
export function useShopWaits(mode: string, auth: ApiAuth | undefined, onResolved?: () => void) {
  const [demo, setDemo] = useState<ApiShopWait[]>(() => [demoShopWait()]);
  const [account, setAccount] = useState<ApiShopWait[]>([]);
  const waits = mode === 'demo' ? demo : mode === 'api' && auth ? account : [];
  const known = useRef(new Set<string>());
  const removed = useRef(new Set<string>());
  const resolved = useRef(onResolved);
  useLayoutEffect(() => { resolved.current = onResolved; });

  const read = useCallback((signal?: AbortSignal) => {
    if (mode !== 'api' || !auth) return;
    listShopWaits(auth, signal).then((next) => {
      const ids = new Set(next.map(({ id }) => id));
      const gone = [...known.current].some((id) => !ids.has(id) && !removed.current.has(id));
      known.current = ids;
      setAccount(next);
      if (gone) resolved.current?.();
    }, () => {
      // The list keeps the waits it showed; the next read tries again.
    });
  }, [mode, auth]);

  useEffect(() => {
    if (mode !== 'api' || !auth) return;
    known.current = new Set();
    const controller = new AbortController();
    read(controller.signal);
    const again = () => { if (document.visibilityState !== 'hidden') read(controller.signal); };
    const timer = window.setInterval(again, READ_EVERY_MS);
    window.addEventListener(SHOP_WAITS_CHANGED, again);
    document.addEventListener('visibilitychange', again);
    return () => {
      controller.abort();
      window.clearInterval(timer);
      window.removeEventListener(SHOP_WAITS_CHANGED, again);
      document.removeEventListener('visibilitychange', again);
    };
  }, [mode, auth, read]);

  const remove = useCallback(async (id: string) => {
    removed.current.add(id);
    const without = (current: ApiShopWait[]) => current.filter((wait) => wait.id !== id);
    if (mode !== 'api') setDemo(without);
    else setAccount(without);
    if (mode === 'api' && auth) {
      try {
        await removeShopWait(id, auth);
      } catch {
        removed.current.delete(id);
        read();
      }
    }
  }, [mode, auth, read]);

  return { waits, remove };
}

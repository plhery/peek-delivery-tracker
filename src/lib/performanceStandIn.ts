type PerformanceHolder = { performance?: Performance | null };

/**
 * Firefox with `dom.enable_performance` off, as some hardened builds ship it,
 * gives `performance` as null. React's scheduler takes null for an object and
 * reads `performance.now` while the page loads, so the page never starts, and
 * the page's own animations read `performance.now()` too. Such a browser gets
 * a stand-in: the time since the page started from `Date.now()`, and no
 * marks, measures or entries.
 */
export function standInForPerformance(holder: PerformanceHolder = window as PerformanceHolder): void {
  if (holder.performance != null) return;
  const timeOrigin = Date.now();
  const none = () => [];
  const standIn = {
    timeOrigin,
    now: () => Date.now() - timeOrigin,
    mark: () => undefined,
    measure: () => undefined,
    clearMarks: () => undefined,
    clearMeasures: () => undefined,
    clearResourceTimings: () => undefined,
    getEntries: none,
    getEntriesByName: none,
    getEntriesByType: none,
    toJSON: () => ({ timeOrigin }),
  };
  try {
    Object.defineProperty(holder, 'performance', { value: standIn, configurable: true, writable: true });
  } catch {
    // A browser that will not let the property be replaced keeps its own.
  }
}

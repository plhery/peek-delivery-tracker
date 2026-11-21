'use client';

import { createContext, useContext, type ReactNode } from 'react';

const GuidePagesContext = createContext(false);

/** Tells the pages below whether the site serves its guides and carriers' pages (`GUIDE_PAGES`), which the server alone reads. */
export function GuidePagesProvider({ on, children }: { on: boolean; children: ReactNode }) {
  return <GuidePagesContext value={on}>{children}</GuidePagesContext>;
}

/** Whether to link the guides and the carriers' pages: never outside a provider that says the site serves them. */
export function useGuidePages(): boolean {
  return useContext(GuidePagesContext);
}

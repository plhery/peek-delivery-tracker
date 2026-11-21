'use client';

import type { ReactNode } from 'react';
import { SiteProvider, useSite } from '../SiteContext';

/** Tells the pages below whether the site serves its guides and carriers' pages (`GUIDE_PAGES`), whatever the site's provider says. */
export function GuidePagesProvider({ on, children }: { on: boolean; children: ReactNode }) {
  const site = useSite();
  return <SiteProvider site={{ ...site, guidePages: on }}>{children}</SiteProvider>;
}

/** Whether to link the guides and the carriers' pages: never outside a provider that says the site serves them. */
export function useGuidePages(): boolean {
  return useSite().guidePages;
}

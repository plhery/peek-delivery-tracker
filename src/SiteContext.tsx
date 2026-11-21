'use client';

import { createContext, useContext, type ReactNode } from 'react';
import { PLAIN_SITE, type Site } from './lib/site';

const SiteContext = createContext<Site>(PLAIN_SITE);

/** Tells the pages below what the server's settings say about the site, which the server alone reads. */
export function SiteProvider({ site, children }: { site: Site; children: ReactNode }) {
  return <SiteContext value={site}>{children}</SiteContext>;
}

/** The site's settings: a plain site's, with nothing set, outside a provider. */
export function useSite(): Site {
  return useContext(SiteContext);
}

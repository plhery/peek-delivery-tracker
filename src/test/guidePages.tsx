import type { ReactNode } from 'react';
import { GuidePagesProvider } from '../guides/GuidePagesContext';

/** A wrapper to render with on a site that serves its guides and carriers' pages, as Peek's own does (`GUIDE_PAGES`). */
export function servingGuides({ children }: { children: ReactNode }) {
  return <GuidePagesProvider on>{children}</GuidePagesProvider>;
}

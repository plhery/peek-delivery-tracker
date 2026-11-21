import { render, type RenderOptions } from '@testing-library/react';
import type { ComponentType, ReactElement, ReactNode } from 'react';
import type { Site } from '../lib/site';
import { SiteProvider } from '../SiteContext';
import { siteSettings } from '../server/siteSettings';
import { PEEK_ENV } from './peekEnv';

export { PEEK_ENV, stubPeekEnv } from './peekEnv';

/** What Peek's own pages are told about the site. */
export const PEEK_SITE: Site = siteSettings({ ...PEEK_ENV } as unknown as NodeJS.ProcessEnv);

/** A wrapper to render with on Peek's own site. */
export function onPeek({ children }: { children: ReactNode }) {
  return <SiteProvider site={PEEK_SITE}>{children}</SiteProvider>;
}

/** Testing Library's `render` on Peek's own site, around the test's own wrapper where it gives one. */
export function renderOnPeek(ui: ReactElement, options: RenderOptions = {}) {
  const Inner = options.wrapper as ComponentType<{ children: ReactNode }> | undefined;
  const wrapper = Inner
    ? ({ children }: { children: ReactNode }) => <SiteProvider site={PEEK_SITE}><Inner>{children}</Inner></SiteProvider>
    : onPeek;
  return render(ui, { ...options, wrapper });
}

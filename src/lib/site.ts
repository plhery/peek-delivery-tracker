import type { AndroidApp } from './androidTest';
import type { AppStoreApp } from './appStore';
import { UPSTREAM_SOURCE } from './source';

/**
 * What the server's settings say about the site that its pages need too: the product's name, the apps it offers,
 * where its code and its operator's documents are. The server reads them from its environment at each request
 * (src/server/siteSettings.ts, where each setting is described) and hands them to the pages through `SiteProvider`;
 * none of them is built into the scripts.
 */
export interface Site {
  /** The product's name where the code writes it (`SITE_NAME`). The app's words name it themselves (`app.title`). */
  name: string;
  /** Whether the site serves the guides and the carriers' pages (`GUIDE_PAGES`). */
  guidePages: boolean;
  /** The iPhone app on the App Store, or null. */
  appStore: AppStoreApp | null;
  /** The Android app, or null. */
  android: AndroidApp | null;
  /** Where the code lives. */
  source: string;
  /** Whether the landing names its author and the other sites they make (`AUTHOR_LINKS`). */
  author: boolean;
  /** Whether the site serves its operator's help, terms and accessibility pages (`LEGAL_PAGES`). */
  legalPages: boolean;
  /** Where the privacy notice is, or null where the site links none. */
  privacy: string | null;
}

/** A site whose settings set nothing: what the pages assume outside a provider. */
export const PLAIN_SITE: Site = {
  name: 'Parcel Tracker',
  guidePages: false,
  appStore: null,
  android: null,
  source: UPSTREAM_SOURCE,
  author: false,
  legalPages: false,
  privacy: null,
};

/** The operator's documents, in `public/`, that the site serves only where `LEGAL_PAGES` is on. */
export const LEGAL_PAGE_PATHS: readonly string[] = ['/privacy.html', '/terms.html', '/support.html', '/accessibility.html'];

/**
 * The service worker keeps the privacy notice for offline reading. A site that serves none registers it with this
 * query, and the worker leaves the notice out: the 404 page in its place would stop it installing.
 */
export const WORKER_WITHOUT_DOCUMENTS = 'documents=off';


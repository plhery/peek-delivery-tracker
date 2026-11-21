import 'server-only';
import type { AndroidApp } from '../lib/androidTest';
import type { AppStoreApp } from '../lib/appStore';
import { PLAIN_SITE, type Site } from '../lib/site';
import { UPSTREAM_SOURCE } from '../lib/source';
import { guidePagesOn } from './guideSettings';
import { canonicalOrigin } from './siteHosts';

/*
 * What a deployment says about itself, read from the server's environment at each request: none of it is built into
 * the scripts. Unset, each is neutral: a site named "Parcel Tracker" with no apps, no author and no documents of an
 * operator's own, which links the code where it comes from.
 */

const packagePattern = /^[A-Za-z][A-Za-z0-9_]*(?:\.[A-Za-z][A-Za-z0-9_]*)+$/;

/** An `https:` address, or null. */
function webAddress(value: string | undefined): string | null {
  const text = value?.trim();
  if (!text) return null;
  try {
    return new URL(text).protocol === 'https:' ? text : null;
  } catch {
    return null;
  }
}

/** The product's name where the code writes it: titles, previews, notifications, the assistants' server (`SITE_NAME`). */
export function siteName(env: NodeJS.ProcessEnv = process.env): string {
  return env.SITE_NAME?.trim() || PLAIN_SITE.name;
}

/**
 * What assistants list the MCP server and its sign-in as: the product's name with what it does, as Claude's connector
 * directory lists it ("Peek Parcel Tracker"), since a name alone says little among a user's other connectors.
 */
export function assistantServerTitle(env: NodeJS.ProcessEnv = process.env): string {
  const name = siteName(env);
  return /parcel tracker/i.test(name) ? name : `${name} Parcel Tracker`;
}

/** The other names search engines are told the site goes by, before its host (`SITE_ALTERNATE_NAMES`, separated by commas). */
export function siteAlternateNames(env: NodeJS.ProcessEnv = process.env): string[] {
  return (env.SITE_ALTERNATE_NAMES ?? '').split(',').map((name) => name.trim()).filter(Boolean);
}

/**
 * The iPhone app on the App Store: its id (`APP_STORE_ID`), with the App Store Connect provider token its campaign
 * links are counted under (`APP_STORE_PROVIDER_TOKEN`). Null without an id: no banner, no App Store link or code, and
 * `/app` is the 404 page.
 */
export function appStoreApp(env: NodeJS.ProcessEnv = process.env): AppStoreApp | null {
  const id = env.APP_STORE_ID?.trim();
  if (!id || !/^\d+$/.test(id)) return null;
  const token = env.APP_STORE_PROVIDER_TOKEN?.trim();
  return { id, providerToken: token && /^\d+$/.test(token) ? token : null };
}

/**
 * The Android app on Google Play (`ANDROID_APP_PACKAGE`), with its closed test's group of testers while the site
 * invites to it (`ANDROID_TEST_GROUP`), its F-Droid repository (`ANDROID_FDROID_REPO`) and its releases
 * (`ANDROID_RELEASES_URL`). Null without a package: no Android button or invitation, and `/android` is not found.
 */
export function androidStoreApp(env: NodeJS.ProcessEnv = process.env): AndroidApp | null {
  const name = env.ANDROID_APP_PACKAGE?.trim();
  if (!name || !packagePattern.test(name)) return null;
  return {
    package: name,
    testGroup: webAddress(env.ANDROID_TEST_GROUP),
    fdroid: webAddress(env.ANDROID_FDROID_REPO),
    releases: webAddress(env.ANDROID_RELEASES_URL),
  };
}

/** Where the code lives (`SOURCE_URL`): the repository it comes from unless another is set. */
export function sourceUrl(env: NodeJS.ProcessEnv = process.env): string {
  return webAddress(env.SOURCE_URL)?.replace(/\/+$/, '') ?? UPSTREAM_SOURCE;
}

/**
 * Whether the landing names its author, on X, and the other sites they make: off unless `AUTHOR_LINKS` is `on`. The
 * words that name them are Peek's own, so only Peek's site turns them on.
 */
export function authorLinksOn(env: NodeJS.ProcessEnv = process.env): boolean {
  return env.AUTHOR_LINKS?.trim() === 'on';
}

/**
 * Whether the site serves its operator's documents, the privacy notice, the terms of use, the help page and the
 * accessibility page (`public/*.html`): off unless `LEGAL_PAGES` is `on`. They name Peek's operator, so only Peek's
 * own site serves them; elsewhere their addresses are the site's 404 page and nothing links them.
 */
export function legalPagesOn(env: NodeJS.ProcessEnv = process.env): boolean {
  return env.LEGAL_PAGES?.trim() === 'on';
}

/** Where the pages and emails link the privacy notice: `PRIVACY_URL`, else the site's own where it serves one, else nowhere. */
export function privacyAddress(env: NodeJS.ProcessEnv = process.env): string | null {
  return webAddress(env.PRIVACY_URL) ?? (legalPagesOn(env) ? '/privacy.html' : null);
}

/** Who the server says it is when it asks another service: its name, and its canonical origin where one is set. */
export function siteUserAgent(env: NodeJS.ProcessEnv = process.env): string {
  const origin = canonicalOrigin(env);
  return origin ? `${siteName(env)} (${origin})` : siteName(env);
}

/** Everything above that the pages read too, for `SiteProvider`. */
export function siteSettings(env: NodeJS.ProcessEnv = process.env): Site {
  return {
    name: siteName(env),
    guidePages: guidePagesOn(env),
    appStore: appStoreApp(env),
    android: androidStoreApp(env),
    source: sourceUrl(env),
    author: authorLinksOn(env),
    legalPages: legalPagesOn(env),
    privacy: privacyAddress(env),
  };
}

import { createHmac } from 'node:crypto';
import { HttpError } from './api';
import type { JsonObject } from './types';

/** Where an install runs: the website in a tab or installed as an app, or one of the apps. */
export const INSTALL_PLATFORMS = ['web', 'pwa', 'iphone', 'ipad', 'mac', 'android'] as const;
export type InstallPlatform = (typeof INSTALL_PLATFORMS)[number];

/** What an install sends once a day: its counting id alone, with nothing that names a person or a parcel. */
export interface InstallSighting {
  id: string;
  platform: InstallPlatform;
  signedIn: boolean;
  /** The parcels it follows without an account. */
  parcels: number;
}

/** The website's 22 base64url characters, or an app's UUID. */
const COUNTING_ID = /^[A-Za-z0-9_-]{16,64}$/;

export function readSighting(body: JsonObject): InstallSighting {
  const { id, platform, signedIn, parcels } = body;
  if (typeof id !== 'string' || !COUNTING_ID.test(id)
    || typeof platform !== 'string' || !(INSTALL_PLATFORMS as readonly string[]).includes(platform)
    || typeof signedIn !== 'boolean'
    || typeof parcels !== 'number' || !Number.isInteger(parcels) || parcels < 0 || parcels > 100_000) {
    throw new HttpError(400, 'Send a counting id, a platform, whether it is signed in and its parcels');
  }
  return { id, platform: platform as InstallPlatform, signedIn, parcels };
}

/**
 * The counting id as the database keeps it: a hash keyed with `COUNTING_KEY`, or without it with a key made
 * from the service key. Its own key lets the service key change without every install counting as new.
 */
export function installHash(id: string, env: Record<string, string | undefined> = process.env): string {
  const key = env.COUNTING_KEY?.trim()
    || createHmac('sha256', env.SUPABASE_SERVICE_ROLE_KEY?.trim() ?? '').update('install-count').digest('hex');
  return createHmac('sha256', key).update(`install-v1\n${id}`).digest('hex');
}

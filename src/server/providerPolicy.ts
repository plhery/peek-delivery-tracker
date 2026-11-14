import 'server-only';

/** A provider's name as the setting may spell it: whatever its case, spaces or hyphens. */
const spelled = (name: string) => name.toLowerCase().replace(/[^a-z0-9]/g, '');

/**
 * Whether checks and the Add preflight may ask `provider`, a universal provider, under
 * `setting`, the value of `TRACKING_UNIVERSAL_PROVIDERS`: every provider when it is unset
 * or empty, none for `none`, else the ones it names, separated by commas (`ParcelsApp,
 * Ship24`). A name of no provider allows nobody, so a typo asks fewer providers, never more.
 */
export function providerAllowed(provider: string, setting: string | undefined): boolean {
  const names = (setting ?? '').split(',').map(spelled).filter(Boolean);
  return !names.length || names.includes(spelled(provider));
}

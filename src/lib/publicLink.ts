/** A public address, taken only when it is a secure link. */
export function publicLink(value: string | undefined): string | null {
  try {
    const url = new URL(value?.trim() ?? '');
    return url.protocol === 'https:' ? url.href : null;
  } catch {
    return null;
  }
}

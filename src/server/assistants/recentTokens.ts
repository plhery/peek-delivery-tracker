import 'server-only';

/**
 * Access tokens issued or found valid lately, by digest, until each expires. A network refused for presenting too many
 * unknown tokens still has these looked up: assistants call from shared addresses, and a stranger flooding one must not
 * lock out everyone connected through it. The database lookup still decides; this only lets it happen.
 */
const MOST_REMEMBERED = 10_000;
const recent = new Map<string, number>();

export function noteValidToken(digest: string, until: number): void {
  recent.delete(digest);
  if (recent.size >= MOST_REMEMBERED) recent.delete(recent.keys().next().value!);
  recent.set(digest, until);
}

export function validLately(digest: string, now = Date.now()): boolean {
  return (recent.get(digest) ?? 0) > now;
}

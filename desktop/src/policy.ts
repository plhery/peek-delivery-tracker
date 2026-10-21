import { z } from 'zod';

const address = z.string().trim().min(1).max(2048);
const loopbackHosts = new Set(['localhost', '127.0.0.1', '[::1]']);

export function serverOrigin(input: string, allowLoopback = false): string {
  const value = address.parse(input);
  const url = new URL(value);
  if (url.username || url.password || url.search || url.hash || url.pathname !== '/') {
    throw new Error('Enter only the server address, without a path, query, or sign-in details.');
  }
  if (url.protocol !== 'https:' && !(allowLoopback && url.protocol === 'http:' && loopbackHosts.has(url.hostname))) {
    throw new Error('Use an HTTPS server address.');
  }
  return url.origin;
}

export function navigationPolicy(target: string, origin: string): 'allow' | 'external' | 'block' {
  try {
    const url = new URL(target);
    if (url.username || url.password) return 'block';
    if (url.origin === origin && ['https:', 'http:'].includes(url.protocol)) return 'allow';
    if (url.protocol === 'https:') return 'external';
    return 'block';
  } catch {
    return 'block';
  }
}

export function trustedSender(senderId: number, shellId: number, frameUrl: string, shellUrl: string, isMainFrame: boolean): boolean {
  return senderId === shellId && isMainFrame && frameUrl === shellUrl;
}

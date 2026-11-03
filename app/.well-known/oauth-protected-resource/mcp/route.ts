import { GET as resourceMetadata } from '../route';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** The same metadata at the address clients try first for `/mcp`. */
export function GET(): Response {
  return resourceMetadata();
}

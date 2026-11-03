import 'server-only';

import { createHash, randomBytes } from 'node:crypto';
import { accountList, listsAccount, type AccountList } from '../accountList';
import { canonicalOrigin } from '../siteHosts';

/**
 * Connecting assistants (Claude, ChatGPT, Gemini and other MCP clients) and scripts to an account. Peek is its own
 * OAuth authorization server at the site's origin, and serves the MCP endpoint at `/mcp`.
 */
export interface AssistantSettings {
  /** The OAuth issuer: the site's canonical origin. */
  issuer: string;
  /** The MCP endpoint, the one resource tokens are issued for. */
  resource: string;
  /** Who may connect: everyone, or the accounts named by id or email. */
  accounts: AccountList;
}

export const SCOPES = ['parcels:read', 'parcels:write'] as const;
export type Scope = (typeof SCOPES)[number];

export const ACCESS_TOKEN_SECONDS = 3_600;
export const REFRESH_TOKEN_DAYS = 60;

/**
 * Reads the settings. Without `ASSISTANT_ACCOUNTS` no assistant can connect and this returns null. A malformed list,
 * or one without `CANONICAL_ORIGIN`, throws, so a deployment fails at startup.
 */
export function assistantSettings(env: NodeJS.ProcessEnv = process.env): AssistantSettings | null {
  if (!env.ASSISTANT_ACCOUNTS?.trim()) return null;
  const accounts = accountList(env.ASSISTANT_ACCOUNTS, 'ASSISTANT_ACCOUNTS');
  const issuer = canonicalOrigin(env);
  if (!issuer) throw new Error('ASSISTANT_ACCOUNTS needs CANONICAL_ORIGIN');
  return { issuer, resource: `${issuer}/mcp`, accounts };
}

/** The same reading for request handling: a malformed set connects nothing, and startup reports it. */
export function assistantsConfigured(env: NodeJS.ProcessEnv = process.env): AssistantSettings | null {
  try {
    return assistantSettings(env);
  } catch {
    return null;
  }
}

export function assistantsOpenTo(settings: AssistantSettings, account: { id: string; email?: string | null }): boolean {
  return listsAccount(settings.accounts, account);
}

/** The scopes named in a space-separated list, in Peek's order; others are left out. */
export function knownScopes(value: string | null | undefined): Scope[] {
  const named = new Set((value ?? '').split(/\s+/));
  return SCOPES.filter((scope) => named.has(scope));
}

export type SecretKind = 'access' | 'refresh' | 'personal' | 'code';
const PREFIXES: Record<SecretKind, string> = { access: 'peek_at_', refresh: 'peek_rt_', personal: 'peek_pat_', code: 'peek_ac_' };

/** A new token or code: 256 random bits, after a prefix that says what it is. */
export function newSecret(kind: SecretKind): string {
  return PREFIXES[kind] + randomBytes(32).toString('base64url');
}

/** How a token or code is stored: its SHA-256, never itself. */
export function secretDigest(secret: string): string {
  return createHash('sha256').update(secret).digest('hex');
}

/** A self-registered client's id. */
export function newClientId(): string {
  return `peek_c_${randomBytes(16).toString('base64url')}`;
}

/** PKCE S256: the challenge a verifier answers. */
export function pkceChallenge(verifier: string): string {
  return createHash('sha256').update(verifier).digest('base64url');
}

export const PKCE_VERIFIER = /^[A-Za-z0-9._~-]{43,128}$/;
export const PKCE_CHALLENGE = /^[A-Za-z0-9_-]{43}$/;

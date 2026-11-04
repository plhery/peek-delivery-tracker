import 'server-only';

import { createPrivateKey, sign, type KeyObject } from 'node:crypto';
import { isRecord } from './types';

const TOKEN_URL = 'https://oauth2.googleapis.com/token';
const MESSAGING_SCOPE = 'https://www.googleapis.com/auth/firebase.messaging';
/** An access token is renewed this long before Google says it expires. */
const RENEW_BEFORE_SECONDS = 5 * 60;
/** As long as APNs keeps a notification for an iPhone that is offline. */
const MESSAGE_TTL_SECONDS = 86_400;
const TIMEOUT_MS = 15_000;

/** FCM refused a message: its HTTP status, FCM's error code, and whether the token itself was refused. */
export class FCMError extends Error {
  constructor(
    readonly statusCode: number,
    readonly reason = '',
    readonly tokenRejected = false,
  ) {
    super(reason || `FCM returned HTTP ${statusCode}`);
    this.name = 'FCMError';
  }
}

/** Google refused the service account's request for an access token; no device is at fault. */
export class FCMAuthError extends Error {
  constructor(
    readonly statusCode: number,
    readonly reason = '',
  ) {
    super(reason ? `Google OAuth refused the FCM service account: ${reason}` : `Google OAuth returned HTTP ${statusCode}`);
    this.name = 'FCMAuthError';
  }
}

/** Reads FCM's error answer: the FcmError code in its details, else the status it names. */
export function fcmError(statusCode: number, body: unknown): FCMError {
  const error = isRecord(body) && isRecord(body.error) ? body.error : {};
  const details = Array.isArray(error.details) ? error.details.filter(isRecord) : [];
  const detailCode = details.map((detail) => detail.errorCode).find((code) => typeof code === 'string');
  const reason = typeof detailCode === 'string' ? detailCode : typeof error.status === 'string' ? error.status : '';
  const tokenField = details.some((detail) => Array.isArray(detail.fieldViolations)
    && detail.fieldViolations.some((violation) => isRecord(violation) && violation.field === 'message.token'));
  const message = typeof error.message === 'string' ? error.message : '';
  // INVALID_ARGUMENT also names a malformed message: only one about the token retires the device.
  const tokenRejected = reason === 'INVALID_ARGUMENT' && (tokenField || /registration token/i.test(message));
  return new FCMError(statusCode, reason, tokenRejected);
}

/** Whether FCM says the token will never take a message again, as APNs says of an expired token. */
export function fcmTokenGone(error: unknown): boolean {
  return error instanceof FCMError
    && (error.statusCode === 404 || error.reason === 'UNREGISTERED' || error.tokenRejected);
}

/**
 * Firebase Cloud Messaging's HTTP v1 API, as a Google service account. The account signs an
 * RS256 assertion, trades it for an access token, and keeps that token until shortly before
 * it expires. Messages are data-only, so the Android app writes each notification itself.
 */
export class FcmClient {
  #privateKey: KeyObject;
  #access: { token: string; renewAt: number } | null = null;
  #pending: Promise<string> | null = null;

  constructor(
    readonly projectId: string,
    readonly clientEmail: string,
    privateKey: string,
    readonly now: () => number = () => Date.now() / 1_000,
    readonly fetcher: typeof fetch = fetch,
  ) {
    try {
      const key = createPrivateKey(privateKey.replaceAll('\\n', '\n').trim());
      if (key.asymmetricKeyType !== 'rsa') throw new TypeError('FCM_PRIVATE_KEY must be an RSA private key');
      this.#privateKey = key;
    } catch (error) {
      throw new TypeError('FCM_PRIVATE_KEY must be an RSA private key', { cause: error });
    }
  }

  /** The signed request for an access token that may send messages, valid for an hour. */
  assertion(issuedAt: number): string {
    const part = (value: object) => Buffer.from(JSON.stringify(value)).toString('base64url');
    const unsigned = `${part({ alg: 'RS256', typ: 'JWT' })}.${part({
      iss: this.clientEmail,
      scope: MESSAGING_SCOPE,
      aud: TOKEN_URL,
      iat: issuedAt,
      exp: issuedAt + 3_600,
    })}`;
    return `${unsigned}.${sign('RSA-SHA256', Buffer.from(unsigned), this.#privateKey).toString('base64url')}`;
  }

  /** The cached access token, or a new one; sends that ask together share one request. */
  async accessToken(): Promise<string> {
    if (this.#access && this.now() < this.#access.renewAt) return this.#access.token;
    this.#pending ??= this.requestAccessToken().finally(() => {
      this.#pending = null;
    });
    return await this.#pending;
  }

  private async requestAccessToken(): Promise<string> {
    const issuedAt = Math.floor(this.now());
    const response = await this.fetcher(TOKEN_URL, {
      method: 'POST',
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({
        grant_type: 'urn:ietf:params:oauth:grant-type:jwt-bearer',
        assertion: this.assertion(issuedAt),
      }).toString(),
      signal: AbortSignal.timeout(TIMEOUT_MS),
    });
    const body: unknown = await response.json().catch(() => null);
    if (!response.ok || !isRecord(body) || typeof body.access_token !== 'string' || !body.access_token) {
      throw new FCMAuthError(response.status, isRecord(body) && typeof body.error === 'string' ? body.error : '');
    }
    const lifetime = typeof body.expires_in === 'number' && body.expires_in > 0 ? body.expires_in : 3_600;
    this.#access = { token: body.access_token, renewAt: issuedAt + Math.max(0, lifetime - RENEW_BEFORE_SECONDS) };
    return body.access_token;
  }

  /** The message FCM is asked to deliver: high priority, kept a day, replacing an earlier one with its collapse key. */
  message(token: string, data: Record<string, string>, collapseKey: string): JsonMessage {
    return {
      message: {
        token,
        data,
        android: {
          priority: 'HIGH',
          ttl: `${MESSAGE_TTL_SECONDS}s`,
          ...collapseKey ? { collapse_key: collapseKey } : {},
        },
      },
    };
  }

  async send(token: string, data: Record<string, string>, collapseKey: string): Promise<void> {
    const response = await this.fetcher(
      `https://fcm.googleapis.com/v1/projects/${encodeURIComponent(this.projectId)}/messages:send`,
      {
        method: 'POST',
        headers: { authorization: `Bearer ${await this.accessToken()}`, 'content-type': 'application/json' },
        body: JSON.stringify(this.message(token, data, collapseKey)),
        signal: AbortSignal.timeout(TIMEOUT_MS),
      },
    );
    if (response.ok) {
      await response.text().catch(() => '');
      return;
    }
    // A token Google stopped taking early is renewed for the next send.
    if (response.status === 401) this.#access = null;
    throw fcmError(response.status, await response.json().catch(() => null));
  }
}

interface JsonMessage {
  message: {
    token: string;
    data: Record<string, string>;
    android: { priority: 'HIGH'; ttl: string; collapse_key?: string };
  };
}

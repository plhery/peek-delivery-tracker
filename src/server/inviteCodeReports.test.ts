import { NextRequest } from 'next/server';
import * as Sentry from '@sentry/node';
import type { Event } from '@sentry/node';
import { afterEach, expect, it, vi } from 'vitest';
import { flushObservability, initObservability } from './observability';

const captured = vi.hoisted(() => ({ events: [] as Event[] }));
const failing = vi.hoisted(() => async () => { throw new Error('database down'); });

// The database fails every call, so each route answers 5xx and reports it.
vi.mock('./runtime', () => ({ serviceClient: () => ({ request: failing, publicParcel: failing }) }));

// The real SDK pipeline, with a transport that keeps the events here.
vi.mock('@sentry/node', async (importOriginal) => {
  const sdk = await importOriginal<typeof import('@sentry/node')>();
  return {
    ...sdk,
    init: vi.fn((options: Parameters<typeof sdk.init>[0]) => sdk.init({
      ...options,
      transport: () => ({
        send: async (envelope) => {
          for (const [header, payload] of envelope[1]) if (header.type === 'event') captured.events.push(payload as Event);
          return { statusCode: 200 };
        },
        flush: async () => true,
      }),
    })),
  };
});

import { POST as invitePreview } from '../../app/api/friends/invite-preview/route';

afterEach(async () => {
  await Sentry.close();
  Reflect.deleteProperty(globalThis, '__deliveryObservabilityInitialized');
  captured.events.length = 0;
  vi.unstubAllEnvs();
});

/** Calls a route as the SDK's HTTP integration sees the request: its address and its body (`httpBodies: ['incomingRequest']`). */
async function call(path: string, body: unknown, handler: (request: NextRequest) => Promise<Response>): Promise<{ status: number; report: Event }> {
  const url = `https://delivery.example.test${path}`;
  const data = JSON.stringify(body);
  let status = 0;
  await Sentry.withIsolationScope(async (isolation) => {
    isolation.setSDKProcessingMetadata({ normalizedRequest: { url, method: 'POST', headers: { 'content-type': 'application/json' }, data } });
    isolation.setTransactionName(`POST ${path}`);
    const response = await handler(new NextRequest(url, {
      method: 'POST', headers: { 'Content-Type': 'application/json', 'Sec-Fetch-Site': 'same-origin' }, body: data,
    }));
    status = response.status;
  });
  expect(await flushObservability()).toBe(true);
  return { status, report: captured.events.at(-1)! };
}

// An invitation's code is what accepts it (docs/FRIENDS.md), so a route whose body carries one leaves the request out
// of its error reports.
const CODE = 'Zq7Wm2Kx9Pd4Lt6V';

it('a failing invitation preview keeps the invitation code out of its error report', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);
  const { status, report } = await call('/api/friends/invite-preview', { code: CODE },
    (request) => invitePreview(request, { params: Promise.resolve({}) }));
  expect(status).toBeGreaterThanOrEqual(500);
  expect(report).toBeDefined();
  // The stack frames quote this file; nothing else of the report may name the code.
  expect(JSON.stringify({ ...report, exception: undefined })).not.toContain(CODE);
});

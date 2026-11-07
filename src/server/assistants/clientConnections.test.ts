import { createServer, type IncomingMessage, type RequestOptions, type Server, type ServerResponse } from 'node:http';
import type { AddressInfo, Socket } from 'node:net';
import { afterAll, afterEach, beforeAll, describe, expect, it, vi } from 'vitest';
import type { SupabaseServiceClient } from '../supabase';
import { findClient, forgetClientDocuments } from './clients';

/**
 * Metadata documents come from a server on this computer, over plain HTTP: the real client, sockets and timers, with
 * only the address the client connects to changed. Each socket the client opens is counted until it has closed.
 */
const local = vi.hoisted(() => ({ port: 0, open: new Set<object>(), mostOpen: 0 }));

vi.mock('node:https', async () => {
  const http = await import('node:http');
  return {
    request: (url: string, options: RequestOptions, answer: (response: IncomingMessage) => void) => {
      const outgoing = http.request(url.replace(/^https:/, 'http:'), { ...options, hostname: '127.0.0.1', port: local.port }, answer);
      outgoing.once('socket', (socket) => {
        local.open.add(socket);
        local.mostOpen = Math.max(local.mostOpen, local.open.size);
        socket.once('close', () => local.open.delete(socket));
      });
      return outgoing;
    },
  };
});

const service = {} as SupabaseServiceClient;
const id = 'https://assistant.example.com/oauth/client.json';
const document = { client_id: id, client_name: 'Example Assistant', redirect_uris: ['https://assistant.example.com/oauth/callback'] };

let server: Server;
let answer: (response: ServerResponse) => void = (response) => { response.end(); };
const connections = new Set<Socket>();
let connectionsSeen = 0;

/** Headers, a first part of the body, and then one more byte every 100 ms for as long as the connection lasts. */
function trickle(status: number, type: string, first = ' ') {
  return (response: ServerResponse) => {
    response.writeHead(status, { 'content-type': type });
    response.write(first);
    const timer = setInterval(() => { if (!response.destroyed) response.write(' '); }, 100);
    response.once('close', () => clearInterval(timer));
  };
}

/** Resolves once the server has seen every connection close, and fails after `within` milliseconds. */
function serverSeesAllClosed(within: number): Promise<void> {
  const started = Date.now();
  return new Promise((resolve, reject) => {
    const check = () => {
      if (!connections.size) return resolve();
      if (Date.now() - started > within) return reject(new Error(`${connections.size} connections still open after ${within} ms`));
      setTimeout(check, 10);
    };
    check();
  });
}

beforeAll(async () => {
  server = createServer((_request, response) => answer(response));
  server.on('connection', (socket) => {
    connectionsSeen += 1;
    connections.add(socket);
    socket.once('close', () => connections.delete(socket));
  });
  await new Promise<void>((resolve) => server.listen(0, '127.0.0.1', resolve));
  local.port = (server.address() as AddressInfo).port;
});

afterEach(() => {
  forgetClientDocuments();
  server.closeAllConnections();
  answer = (response) => { response.end(); };
  connectionsSeen = 0;
  local.mostOpen = 0;
});

afterAll(async () => {
  await new Promise((resolve) => server.close(resolve));
});

describe('metadata document connections', () => {
  it('reads a document and closes its connection', async () => {
    answer = (response) => {
      response.writeHead(200, { 'content-type': 'application/json' });
      response.end(JSON.stringify(document));
    };
    await expect(findClient(service, id)).resolves.toEqual({ id, name: 'Example Assistant', uri: null, redirectUris: document.redirect_uris });
    expect(local.open.size).toBe(0);
    await serverSeesAllClosed(1_000);
  });

  it('closes the connection at once on a wrong status, type or size, however slowly the rest comes', async () => {
    for (const [index, refusal] of [
      trickle(404, 'application/json'),
      trickle(200, 'text/html'),
      trickle(302, 'text/plain'),
      trickle(200, 'application/json', `{"padding":"${'x'.repeat(11_000)}`),
    ].entries()) {
      answer = refusal;
      const started = Date.now();
      await expect(findClient(service, `https://assistant.example.com/refused/${index}.json`)).resolves.toBeNull();
      expect(Date.now() - started).toBeLessThan(2_000);
      // The lookup settles only once its socket has closed.
      expect(local.open.size).toBe(0);
      await serverSeesAllClosed(1_000);
    }
  });

  it('never holds more than 16 connections open, however many lookups come', async () => {
    answer = trickle(404, 'text/html');
    for (let round = 0; round < 4; round += 1) {
      const found = await Promise.all(Array.from({ length: 20 }, (_, index) => findClient(service, `https://assistant.example.com/${round}/${index}.json`)));
      expect(found.every((client) => client === null)).toBe(true);
    }
    expect(connectionsSeen).toBe(64);
    expect(local.mostOpen).toBe(16);
    await serverSeesAllClosed(1_000);
  });

  it('gives up on a document still coming after five seconds, and closes its connection', async () => {
    answer = trickle(200, 'application/json', '{"client_id":');
    const started = Date.now();
    await expect(findClient(service, id)).resolves.toBeNull();
    expect(Date.now() - started).toBeGreaterThanOrEqual(4_900);
    expect(local.open.size).toBe(0);
    await serverSeesAllClosed(1_000);
  }, 20_000);
});

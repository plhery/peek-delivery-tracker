import { createServer, request } from 'node:http';
import type { AddressInfo } from 'node:net';
import { describe, expect, it, vi } from 'vitest';
import {
  isPublicAddress,
  isPublicHost,
  NonPublicAddressError,
  pinnedLookup,
  postToPublicEndpoint,
  publicAddresses,
  urlHost,
} from './publicAddress';

describe('isPublicAddress', () => {
  it.each([
    '1.1.1.1', '8.8.8.8', '93.184.216.34', '100.63.255.255', '100.128.0.1', '172.15.255.255', '172.32.0.1',
    '2606:4700:4700::1111', '2a00:1450:4007:80e::200e', '2001:4860:4860::8888', '::ffff:8.8.8.8',
    '::ffff:808:808', '64:ff9b::8.8.8.8', '2001:200::1',
  ])('takes %s', (address) => {
    expect(isPublicAddress(address)).toBe(true);
  });

  it.each([
    // IPv4: this network, private, shared, loopback, link-local, documentation, benchmarking, multicast, reserved.
    '0.0.0.0', '10.0.0.1', '100.64.0.1', '100.127.255.254', '127.0.0.1', '127.255.255.255', '169.254.169.254',
    '172.16.0.1', '172.31.255.255', '192.0.0.8', '192.0.2.1', '192.168.1.1', '198.18.0.1', '198.51.100.7',
    '203.0.113.9', '224.0.0.251', '239.255.255.250', '240.0.0.1', '255.255.255.255',
    // IPv6: unspecified, loopback, link-local, unique-local, multicast, documentation, protocol ranges, 6to4.
    '::', '::1', '0:0:0:0:0:0:0:1', 'fe80::1', 'FE80::abcd', 'fc00::1', 'fd12:3456:789a::1', 'ff02::1',
    '2001:db8::1', '3fff::1', '2001::1', '2001:0:4136:e378::1', '2002:c0a8:101::1',
    // IPv4 inside IPv6: mapped, in hex, expanded, NAT64 and the deprecated compatible form.
    '::ffff:127.0.0.1', '::ffff:7f00:1', '::FFFF:10.1.2.3', '0:0:0:0:0:ffff:0a01:0203', '::ffff:169.254.169.254',
    '::ffff:100.64.0.1', '64:ff9b::10.0.0.1', '64:ff9b::7f00:1', '::127.0.0.1', '64:ff9b:1::8.8.8.8',
  ])('refuses %s', (address) => {
    expect(isPublicAddress(address)).toBe(false);
  });

  it.each(['fe80::1%eth0', '2606:4700::1%1', 'localhost', '1.2.3', '0x7f.1', '010.0.0.1', '', '::ffff:1.2.3.4.5', '2606:4700:::1'])(
    'refuses what is no plain address: %s',
    (value) => {
      expect(isPublicAddress(value)).toBe(false);
    },
  );
});

describe('isPublicHost', () => {
  it.each(['ntfy.sh', 'push.example.com', 'up.my-server.eu', 'xn--bcher-kva.de', '8.8.8.8', '2606:4700:4700::1111'])(
    'takes %s',
    (host) => {
      expect(isPublicHost(host)).toBe(true);
    },
  );

  it.each([
    'localhost', 'foo.localhost', 'printer.local', 'db.internal', 'nas.lan', 'router.home', 'box.home.arpa',
    '1.0.0.127.in-addr.arpa', 'intranet', 'server.corp', 'app.test', 'x.invalid', 'example', 'abc.onion',
    'under_score.example.com', '-dash.example.com', 'host.123', '127.0.0.1', '::1', '',
  ])('refuses %s', (host) => {
    expect(isPublicHost(host)).toBe(false);
  });

  it('reads a URL\'s host as the address it normalizes to', () => {
    expect(urlHost(new URL('https://0x7f.1/'))).toBe('127.0.0.1');
    expect(urlHost(new URL('https://2130706433/'))).toBe('127.0.0.1');
    expect(urlHost(new URL('https://[::ffff:127.0.0.1]/'))).toBe('::ffff:7f00:1');
    expect(urlHost(new URL('https://LOCALHOST./'))).toBe('localhost');
    for (const url of ['https://0x7f.1/', 'https://2130706433/', 'https://[::ffff:127.0.0.1]/', 'https://LOCALHOST./']) {
      expect(isPublicHost(urlHost(new URL(url)))).toBe(false);
    }
  });
});

describe('publicAddresses', () => {
  it('answers with every address a name resolves to when all are public', async () => {
    const lookup = vi.fn().mockResolvedValue([{ address: '2606:4700::1', family: 6 }, { address: '104.16.0.1', family: 4 }]);
    await expect(publicAddresses('push.example.com', lookup)).resolves.toEqual([
      { address: '2606:4700::1', family: 6 }, { address: '104.16.0.1', family: 4 },
    ]);
    expect(lookup).toHaveBeenCalledWith('push.example.com', { all: true });
  });

  it.each([
    [[{ address: '104.16.0.1', family: 4 }, { address: '10.0.0.5', family: 4 }]],
    [[{ address: '::ffff:192.168.0.1', family: 6 }]],
    [[{ address: 'fd00::5', family: 6 }]],
    [[]],
  ])('refuses a name when any address is not public: %j', async (addresses) => {
    await expect(publicAddresses('push.example.com', vi.fn().mockResolvedValue(addresses)))
      .rejects.toBeInstanceOf(NonPublicAddressError);
  });

  it('checks an address without resolving it, and refuses a private name before asking DNS', async () => {
    const lookup = vi.fn();
    await expect(publicAddresses('8.8.8.8', lookup)).resolves.toEqual([{ address: '8.8.8.8', family: 4 }]);
    await expect(publicAddresses('10.0.0.1', lookup)).rejects.toBeInstanceOf(NonPublicAddressError);
    await expect(publicAddresses('printer.local', lookup)).rejects.toBeInstanceOf(NonPublicAddressError);
    expect(lookup).not.toHaveBeenCalled();
  });
});

describe('pinnedLookup', () => {
  const addresses = [{ address: '2606:4700::1', family: 6 }, { address: '104.16.0.1', family: 4 }];

  it('answers with the checked addresses, of the family asked for', () => {
    const lookup = pinnedLookup(addresses);
    const all = vi.fn();
    lookup('anything.example', { all: true }, all);
    expect(all).toHaveBeenCalledWith(null, addresses);
    const one = vi.fn();
    lookup('anything.example', { family: 4 }, one);
    expect(one).toHaveBeenCalledWith(null, '104.16.0.1', 4);
    const missing = vi.fn();
    pinnedLookup([addresses[1]!])('anything.example', { family: 6 }, missing);
    expect(missing.mock.calls[0]![0]).toMatchObject({ code: 'ENOTFOUND' });
  });

  it('connects to the pinned address whatever the name says', async () => {
    const server = createServer((incoming, response) => {
      response.end(incoming.headers.host);
    });
    await new Promise<void>((resolve) => server.listen(0, '127.0.0.1', resolve));
    const { port } = server.address() as AddressInfo;
    try {
      const host = await new Promise<string>((resolve, reject) => {
        // A name that resolves nowhere: only the pinned lookup can reach the local server.
        request({ hostname: 'push.peek.invalid', port, lookup: pinnedLookup([{ address: '127.0.0.1', family: 4 }]) }, (response) => {
          let text = '';
          response.setEncoding('utf8').on('data', (chunk: string) => { text += chunk; }).on('end', () => resolve(text));
        }).on('error', reject).end();
      });
      expect(host).toBe(`push.peek.invalid:${port}`);
    } finally {
      await new Promise((resolve) => server.close(resolve));
    }
  });
});

describe('postToPublicEndpoint', () => {
  it('refuses before connecting when the name resolves to a private address', async () => {
    const lookup = vi.fn().mockResolvedValue([{ address: '127.0.0.1', family: 4 }]);
    await expect(postToPublicEndpoint('https://push.example.com/up/abc', { headers: {}, body: null, timeoutMs: 1_000, lookup }))
      .rejects.toBeInstanceOf(NonPublicAddressError);
    expect(lookup).toHaveBeenCalledOnce();
  });

  it('refuses an endpoint that is not HTTPS, carries credentials or names a private host', async () => {
    const lookup = vi.fn();
    for (const endpoint of ['http://push.example.com/', 'https://user:pass@push.example.com/', 'https://[::1]/', 'https://169.254.169.254/']) {
      await expect(postToPublicEndpoint(endpoint, { headers: {}, body: null, timeoutMs: 1_000, lookup }))
        .rejects.toBeInstanceOf(NonPublicAddressError);
    }
    expect(lookup).not.toHaveBeenCalled();
  });

  it('gives up when resolving takes longer than the time given', async () => {
    const lookup = vi.fn(() => new Promise<never>(() => {}));
    await expect(postToPublicEndpoint('https://push.example.com/', { headers: {}, body: null, timeoutMs: 20, lookup }))
      .rejects.toMatchObject({ name: 'TimeoutError' });
  });
});

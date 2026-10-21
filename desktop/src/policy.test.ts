import { test } from 'node:test';
import assert from 'node:assert/strict';
import { navigationPolicy, serverOrigin, trustedSender } from './policy.ts';

test('requires an HTTPS origin and normalizes it', () => {
  assert.equal(serverOrigin('  https://PEEK.example.com:443/  '), 'https://peek.example.com');
  assert.equal(serverOrigin('https://peek.example.com:8443'), 'https://peek.example.com:8443');
  for (const value of ['http://peek.example.com', 'javascript:alert(1)', 'https://user:secret@peek.example.com', 'https://peek.example.com/path', 'https://peek.example.com/?token=secret', 'https://peek.example.com/#token', 'file:///tmp/page', 'not a URL']) {
    assert.throws(() => serverOrigin(value), value);
  }
});

test('HTTP loopback needs an explicit development allowance and exact host', () => {
  for (const value of ['http://localhost:3000', 'http://127.0.0.1:3000', 'http://[::1]:3000']) {
    assert.throws(() => serverOrigin(value));
    assert.equal(serverOrigin(value, true), value);
  }
  for (const value of ['http://localhost.evil.example', 'http://127.0.0.2', 'http://192.168.1.20']) assert.throws(() => serverOrigin(value, true));
});

test('navigation trusts only exact server origin, offers HTTPS links externally and rejects dangerous schemes', () => {
  const origin = 'https://peek.example.com';
  assert.equal(navigationPolicy(`${origin}/parcels/synthetic?tab=events#latest`, origin), 'allow');
  for (const target of ['https://peek.example.com.evil.example/', 'https://other.example/', 'https://peek.example.com:444/']) assert.equal(navigationPolicy(target, origin), 'external');
  for (const target of ['javascript:alert(1)', 'file:///etc/passwd', 'data:text/html,test', 'http://other.example/', 'https://user:password@peek.example.com/', 'invalid']) assert.equal(navigationPolicy(target, origin), 'block');
});

test('desktop IPC requires the exact local main frame and owning webContents', () => {
  const local = 'file:///app/build/ui/index.html';
  assert.equal(trustedSender(1, 1, local, local, true), true);
  assert.equal(trustedSender(2, 1, local, local, true), false);
  assert.equal(trustedSender(1, 1, 'https://peek.example.com', local, true), false);
  assert.equal(trustedSender(1, 1, `${local}#other`, local, true), false);
  assert.equal(trustedSender(1, 1, local, local, false), false);
});

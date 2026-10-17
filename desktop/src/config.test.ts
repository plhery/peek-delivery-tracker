import { test } from 'node:test';
import assert from 'node:assert/strict';
import { mkdtemp, readFile, rm, writeFile } from 'node:fs/promises';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { configSchema, readConfig, saveConfig } from './config.ts';

test('a first launch has no implicit server; saves only a normalized origin across restart', async () => {
  const directory = await mkdtemp(join(tmpdir(), 'peek-config-'));
  try {
    assert.equal(await readConfig(directory), null);
    await saveConfig(directory, 'https://PEEK.example.com:443/');
    assert.equal(await readConfig(directory), 'https://peek.example.com');
    assert.deepEqual(configSchema.parse(JSON.parse(await readFile(join(directory, 'server.json'), 'utf8'))), { version: 1, serverOrigin: 'https://peek.example.com' });
    await saveConfig(directory, 'https://other.example.com');
    assert.equal(await readConfig(directory), 'https://other.example.com');
    await assert.rejects(saveConfig(directory, 'https://user:password@peek.example.com'));
    assert.equal(await readConfig(directory), 'https://other.example.com');
  } finally { await rm(directory, { recursive: true, force: true }); }
});

test('malformed or broadened saved configuration fails visibly instead of trusting it', async () => {
  const directory = await mkdtemp(join(tmpdir(), 'peek-config-'));
  try {
    for (const content of ['not json', '{"version":2,"serverOrigin":"https://peek.example.com"}', '{"version":1,"serverOrigin":"file:///etc/passwd"}', '{"version":1,"serverOrigin":"https://peek.example.com","token":"synthetic"}']) {
      await writeFile(join(directory, 'server.json'), content);
      await assert.rejects(readConfig(directory));
    }
  } finally { await rm(directory, { recursive: true, force: true }); }
});

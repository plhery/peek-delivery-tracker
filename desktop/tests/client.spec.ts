import { test, expect, _electron as electron } from '@playwright/test';
import { createServer } from 'node:http';
import { mkdtemp, rm } from 'node:fs/promises';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { fileURLToPath } from 'node:url';

const desktopPath = fileURLToPath(new URL('..', import.meta.url));

test('first run, real renderer isolation, server persistence, reconnect and failure', async () => {
  const profile = await mkdtemp(join(tmpdir(), 'peek-desktop-smoke-'));
  const fixture = createServer((request, response) => {
    if (request.url === '/provider') {
      response.writeHead(302, { Location: 'https://identity.example.com/authorize' });
      response.end();
      return;
    }
    response.setHeader('Content-Type', 'text/html');
    response.end(`<html lang="en"><head><title>Synthetic Peek server</title></head><body><h1>Synthetic deliveries</h1><a href="/parcel/synthetic">Synthetic parcel</a><button onclick="window.open('file:///etc/passwd')">Unsafe popup</button><script>document.body.dataset.bridge = typeof window.peekDesktop;document.body.dataset.node = typeof require;</script><p>${request.url === '/parcel/synthetic' ? 'Synthetic parcel details' : 'Fixture only'}</p></body></html>`);
  });
  await new Promise<void>((resolve) => fixture.listen(0, '127.0.0.1', resolve));
  const address = fixture.address();
  if (!address || typeof address === 'string') throw new Error('Missing fixture address.');
  const origin = `http://127.0.0.1:${address.port}`;
  const environment: Record<string, string> = { PEEK_ALLOW_LOOPBACK: '1', PEEK_TEST_PROFILE: profile };
  for (const [key, value] of Object.entries(process.env)) {
    if (value !== undefined && key !== 'ELECTRON_RUN_AS_NODE') environment[key] = value;
  }
  const launch = () => electron.launch({ args: [desktopPath], env: environment });
  let client = await launch();
  try {
    const setup = await client.firstWindow();
    await expect(setup.getByRole('heading', { name: 'A little closer to your parcels.' })).toBeVisible();
    await setup.getByLabel('Server address').fill('https://user:secret@peek.example.com');
    await setup.getByRole('button', { name: 'Connect to Peek' }).click();
    await expect(setup.getByRole('alert')).toContainText('without a path');
    await setup.getByLabel('Server address').fill(origin);
    await setup.getByRole('button', { name: 'Connect to Peek' }).click();
    await expect.poll(() => client.evaluate(({ webContents }) => webContents.getAllWebContents().some((contents) => contents.getURL().startsWith('http://127.0.0.1:')))).toBe(true);
    const remoteContents = () => client.evaluate(async ({ webContents }) => {
      const remote = webContents.getAllWebContents().find((contents) => contents.getURL().startsWith('http://127.0.0.1:'));
      if (!remote) return null;
      return remote.executeJavaScript('({bridge: typeof window.peekDesktop, node: typeof require, title: document.title})');
    });
    await expect.poll(remoteContents).toEqual({ bridge: 'undefined', node: 'undefined', title: 'Synthetic Peek server' });
    await client.evaluate(async ({ webContents }) => {
      const remote = webContents.getAllWebContents().find((contents) => contents.getURL().startsWith('http://127.0.0.1:'));
      await remote?.executeJavaScript("document.querySelector('a').click()");
    });
    await expect.poll(() => client.evaluate(({ webContents }) => webContents.getAllWebContents().some((contents) => contents.getURL().endsWith('/parcel/synthetic')))).toBe(true);
    await client.evaluate(async ({ webContents }) => {
      const remote = webContents.getAllWebContents().find((contents) => contents.getURL().startsWith('http://127.0.0.1:'));
      await remote?.executeJavaScript("document.querySelector('button').click()");
    });
    expect(await client.evaluate(({ BrowserWindow }) => BrowserWindow.getAllWindows().length)).toBe(1);
    await client.evaluate(async ({ webContents }) => {
      const remote = webContents.getAllWebContents().find((contents) => contents.getURL().startsWith('http://127.0.0.1:'));
      await remote?.executeJavaScript("window.location.assign('/provider')");
    });
    await expect(setup.getByRole('status')).toContainText('If your server requires a provider');
    await setup.getByRole('button', { name: 'Try again' }).click();
    await expect(setup.locator('body')).toHaveAttribute('data-mode', 'connected');
    await client.close();
    client = await launch();
    const shell = await client.firstWindow();
    await expect(shell.locator('body')).toHaveAttribute('data-mode', 'connected');
    await shell.getByRole('button', { name: 'Change server', exact: true }).click();
    await expect(shell.getByLabel('Server address')).toHaveValue(origin);
    await shell.getByRole('button', { name: 'Connect to Peek' }).click();
    await expect(shell.locator('body')).toHaveAttribute('data-mode', 'connected');
    await new Promise<void>((resolve, reject) => fixture.close((error) => error ? reject(error) : resolve()));
    await shell.getByRole('button', { name: 'Change server', exact: true }).click();
    await shell.getByRole('button', { name: 'Connect to Peek' }).click();
    await expect(shell.getByRole('heading', { name: 'Let’s reconnect.' })).toBeVisible();
    await expect(shell.getByRole('button', { name: 'Try again' })).toBeVisible();
    await shell.screenshot({ path: 'test-results/connection-error.png' });
  } finally {
    await client.close();
    fixture.close();
    await rm(profile, { recursive: true, force: true });
  }
});

import { expect, test } from '@playwright/test';
import { spawn } from 'node:child_process';
import { once } from 'node:events';
import { createServer } from 'node:net';
import fr from '../shared/locales/fr.json' with { type: 'json' };
import de from '../shared/locales/de.json' with { type: 'json' };
import frDemo from '../shared/demo-locales/fr.json' with { type: 'json' };

// Shut down a dedicated origin to exercise real network loss. WebKit's emulated
// offline mode can abort navigations before its service worker handles them.
// A cold server on a busy runner can take longer than an assertion's 5 s to
// answer its first page, and the port reserved for it can be taken before it
// binds: wait until it answers or exits, and try another port when it exits.
async function freePort() {
  const reservation = createServer();
  reservation.listen(0, '127.0.0.1');
  await once(reservation, 'listening');
  const address = reservation.address();
  if (!address || typeof address === 'string') throw new Error('No test port available');
  await new Promise<void>((resolve) => reservation.close(() => resolve()));
  return address.port;
}

async function dedicatedOrigin() {
  for (let attempt = 1; ; attempt += 1) {
    const port = await freePort();
    const child = spawn(process.execPath, ['.next/standalone/server.js'], {
      env: { ...process.env, HOSTNAME: '127.0.0.1', PORT: String(port) },
      stdio: ['ignore', 'ignore', 'pipe'],
    });
    let errors = '';
    child.stderr.on('data', (chunk: Buffer) => { errors = (errors + chunk.toString()).slice(-2_000); });
    let running = true;
    const exited = once(child, 'exit').then(() => { running = false; });
    const url = `http://127.0.0.1:${port}`;
    const stop = async () => {
      if (running) child.kill('SIGTERM');
      await exited;
    };
    const deadline = Date.now() + 60_000;
    while (running && Date.now() < deadline) {
      const status = await fetch(url, { signal: AbortSignal.timeout(15_000) })
        .then(async (response) => { await response.arrayBuffer(); return response.status; })
        .catch(() => 0);
      if (status === 200) return { url, stop };
      await new Promise((resolve) => setTimeout(resolve, 250));
    }
    const exitedEarly = !running;
    await stop();
    if (!exitedEarly || attempt === 3) {
      throw new Error(`The dedicated server on port ${port} ${exitedEarly ? 'exited' : 'did not answer'}: ${errors}`);
    }
  }
}

test('serves a translated, interactive fallback when the server becomes unreachable', async ({ page }) => {
  test.skip(!process.env.CI && !process.env.PLAYWRIGHT_PRODUCTION, 'Requires the production service worker.');
  // Starting a second server comes on top of the journey.
  test.setTimeout(90_000);
  const origin = await dedicatedOrigin();
  try {
    const securityErrors: string[] = [];
    page.on('console', (message) => {
      if (/Content Security Policy|violates.*directive/i.test(message.text())) securityErrors.push(message.text());
    });
    page.on('pageerror', (error) => securityErrors.push(error.message));
    await page.addInitScript(() => localStorage.setItem('sdt.web.experience.v1', 'demo'));
    await page.goto(origin.url);
    await expect(page.getByText('Coffee beans ☕', { exact: true })).toBeVisible();
    await page.evaluate(() => navigator.serviceWorker.ready);
    await page.waitForFunction(() => navigator.serviceWorker.controller !== null);
    await page.goto(`${origin.url}/~offline`);
    await page.getByRole('combobox', { name: 'Language' }).selectOption('fr');
    await expect(page.getByRole('heading', { level: 1 })).toHaveText(fr['offline.title']);
    await page.goto(origin.url);
    // The demo is written in the language just chosen.
    await expect(page.getByText(frDemo['Coffee beans ☕'], { exact: true })).toBeVisible();
    await origin.stop();
    await page.goto(`${origin.url}/uncached-offline-navigation`);
    await expect(page).toHaveURL(`${origin.url}/~offline`);
    await expect(page.getByRole('heading', { level: 1 })).toHaveText(fr['offline.title']);
    await page.getByRole('combobox').selectOption('de');
    await expect(page.getByRole('heading', { level: 1 })).toHaveText(de['offline.title']);
    expect(securityErrors).toEqual([]);
  } finally {
    await origin.stop();
  }
});

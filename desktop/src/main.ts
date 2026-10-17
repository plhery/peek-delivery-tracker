import { app, BrowserWindow, WebContentsView, Menu, dialog, ipcMain, shell, session, type IpcMainInvokeEvent, type MenuItemConstructorOptions } from 'electron';
import { createHash } from 'node:crypto';
import { join } from 'node:path';
import { pathToFileURL } from 'node:url';
import { z } from 'zod';
import { readConfig, saveConfig } from './config.ts';
import { navigationPolicy, trustedSender } from './policy.ts';
import { releaseSchema, channelIdentity, type Release } from './release.ts';
import type { ClientState, ConnectResult } from './bridge.ts';

declare const __PEEK_RELEASE__: Release;
const release = releaseSchema.parse(__PEEK_RELEASE__);
const identity = channelIdentity(release.channel);
const allowLoopback = !app.isPackaged && process.env.PEEK_ALLOW_LOOPBACK === '1';
app.setName(identity.productName);
const profile = !app.isPackaged && process.env.PEEK_TEST_PROFILE
  ? process.env.PEEK_TEST_PROFILE
  : join(app.getPath('appData'), identity.profile);
app.setPath('userData', profile);
if (process.platform === 'win32') app.setAppUserModelId(identity.appId);
app.enableSandbox();

let mainWindow: BrowserWindow | null = null;
let remote: WebContentsView | null = null;
let state: ClientState = { mode: 'setup', origin: null, message: '', release };
const shellUrl = pathToFileURL(join(__dirname, 'ui/index.html')).href;
let loadSequence = 0;
let connecting = false;
const configuredSessions = new Set<string>();

function setState(mode: ClientState['mode'], message = '') {
  state = { ...state, mode, message };
  mainWindow?.webContents.send('peek:state-changed', state);
}

function removeRemote() {
  loadSequence += 1;
  if (remote) {
    if (mainWindow && !mainWindow.isDestroyed()) mainWindow.contentView.removeChildView(remote);
    remote.webContents.close();
    remote = null;
  }
}

function setup() {
  removeRemote();
  setState('setup');
}

function bounds() {
  if (!remote || !mainWindow) return;
  const { width, height } = mainWindow.getContentBounds();
  remote.setBounds({ x: 0, y: 76, width, height: Math.max(0, height - 76) });
}

async function openExternal(target: string, origin: string) {
  if (!mainWindow || navigationPolicy(target, origin) !== 'external') return;
  const answer = await dialog.showMessageBox(mainWindow, {
    type: 'question', title: 'Open in your browser?',
    message: 'This link opens outside Peek.', detail: `${target}\n\nThis preview supports email sign-in. Signing in through a provider in your browser won’t sign you into the desktop app. If your server requires a provider, use Peek in your browser.`,
    buttons: ['Cancel', 'Open browser'], defaultId: 0, cancelId: 0, noLink: true,
  });
  if (answer.response === 1) {
    try { await shell.openExternal(target); } catch {
      if (mainWindow) await dialog.showMessageBox(mainWindow, { type: 'error', message: 'Couldn’t open your browser.', detail: 'Open the link in your browser to continue.' });
    }
  }
}

async function connect() {
  if (!state.origin || !mainWindow) return;
  const origin = state.origin;
  removeRemote();
  const sequence = loadSequence;
  setState('loading');
  const partition = `persist:server-${createHash('sha256').update(origin).digest('hex')}`;
  const serverSession = session.fromPartition(partition);
  if (!configuredSessions.has(partition)) {
    serverSession.setPermissionRequestHandler((_contents, _permission, callback) => callback(false));
    serverSession.setPermissionCheckHandler(() => false);
    serverSession.on('will-download', (event) => event.preventDefault());
    configuredSessions.add(partition);
  }
  const view = new WebContentsView({ webPreferences: {
    partition, nodeIntegration: false, nodeIntegrationInSubFrames: false,
    contextIsolation: true, sandbox: true, webSecurity: true,
    allowRunningInsecureContent: false, webviewTag: false,
    navigateOnDragDrop: false, spellcheck: false,
  } });
  remote = view;
  const contents = view.webContents;
  contents.setWindowOpenHandler(({ url }) => {
    if (navigationPolicy(url, origin) === 'allow') {
      void contents.loadURL(url).catch(() => fail());
    } else {
      void openExternal(url, origin);
    }
    return { action: 'deny' };
  });
  const fail = (message = 'We couldn’t reach your Peek server. Check your connection and server address, then try again.') => {
    if (sequence !== loadSequence) return;
    removeRemote();
    setState('error', message);
  };
  contents.on('will-navigate', (event, url) => {
    if (navigationPolicy(url, origin) !== 'allow') {
      event.preventDefault();
      void openExternal(url, origin);
    }
  });
  contents.on('will-redirect', (event, url, _isInPlace, isMainFrame) => {
    if (isMainFrame && navigationPolicy(url, origin) !== 'allow') {
      event.preventDefault();
      fail('Your server redirected to another site. This preview supports email sign-in. If your server requires a provider, use Peek in your browser.');
    }
  });
  contents.on('will-attach-webview', (event) => event.preventDefault());
  contents.on('render-process-gone', () => fail());
  contents.on('did-navigate', (_event, _url, responseCode) => { if (responseCode >= 400) fail(); });
  contents.on('did-fail-load', (_event, code, _description, _url, isMainFrame) => {
    if (isMainFrame && code !== -3) fail();
  });
  let timeout: ReturnType<typeof setTimeout> | undefined = setTimeout(() => fail(), 20000);
  contents.once('destroyed', () => { clearTimeout(timeout); });
  contents.once('did-finish-load', () => {
    clearTimeout(timeout);
    timeout = undefined;
    if (sequence !== loadSequence || !mainWindow || remote !== view) return;
    if (navigationPolicy(contents.getURL(), origin) !== 'allow') { fail(); return; }
    mainWindow.contentView.addChildView(view);
    bounds();
    setState('connected');
    contents.focus();
  });
  await contents.loadURL(origin).catch(() => fail());
}

function assertSender(event: IpcMainInvokeEvent) {
  if (!mainWindow || !trustedSender(event.sender.id, mainWindow.webContents.id,
    event.senderFrame?.url ?? '', shellUrl, event.senderFrame === mainWindow.webContents.mainFrame)) {
    throw new Error('Untrusted desktop request.');
  }
}

ipcMain.handle('peek:state', (event) => { assertSender(event); return state; });
ipcMain.handle('peek:change-server', (event) => { assertSender(event); setup(); });
ipcMain.handle('peek:retry', (event) => { assertSender(event); void connect(); });
ipcMain.handle('peek:connect', async (event, input: string): Promise<ConnectResult> => {
  assertSender(event);
  if (connecting) return { ok: false, message: 'Please wait while your address is saved.' };
  connecting = true;
  try {
    const origin = await saveConfig(profile, z.string().max(2048).parse(input), allowLoopback);
    state = { ...state, origin };
    void connect();
    return { ok: true, message: '' };
  } catch (error) {
    return { ok: false, message: error instanceof Error && error.message.startsWith('Enter only')
      ? error.message : 'Couldn’t save this address. Use an HTTPS server address and check that Peek can save its settings.' };
  } finally {
    connecting = false;
  }
});

async function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1160, height: 820, minWidth: 600, minHeight: 560,
    title: identity.productName, backgroundColor: '#fffaf0',
    webPreferences: { preload: join(__dirname, 'preload.cjs'), nodeIntegration: false, contextIsolation: true, sandbox: true, webSecurity: true, webviewTag: false },
  });
  mainWindow.webContents.setWindowOpenHandler(() => ({ action: 'deny' }));
  mainWindow.webContents.on('will-navigate', (event) => event.preventDefault());
  mainWindow.webContents.on('will-attach-webview', (event) => event.preventDefault());
  mainWindow.on('resize', bounds);
  mainWindow.on('closed', () => { removeRemote(); mainWindow = null; });
  const menu: MenuItemConstructorOptions[] = [
    ...(process.platform === 'darwin' ? [{ role: 'appMenu' }] satisfies MenuItemConstructorOptions[] : []),
    { label: 'Peek', submenu: [
      { label: 'Change server…', click: setup },
      { label: 'Reconnect', click: () => { void connect(); } },
      { label: 'About this build', click: () => {
        if (mainWindow) void dialog.showMessageBox(mainWindow, { title: identity.productName, message: `${identity.productName} ${release.version}`, detail: `Channel: ${release.channel}\nSource: ${release.sha}\nBuild: ${release.buildNumber}` });
      } },
      { type: 'separator' }, { role: 'quit' },
    ] },
    { role: 'editMenu' },
    { label: 'View', submenu: [{ role: 'resetZoom' }, { role: 'zoomIn' }, { role: 'zoomOut' }, { role: 'togglefullscreen' }] },
  ];
  Menu.setApplicationMenu(Menu.buildFromTemplate(menu));
  await mainWindow.loadURL(shellUrl);
  if (state.origin) void connect();
}

app.whenReady().then(async () => {
  try {
    state.origin = await readConfig(profile, allowLoopback);
  } catch {
    state = { ...state, mode: 'setup', message: 'Your saved server address couldn’t be read. Enter it again to continue.' };
  }
  await createWindow();
  app.on('activate', () => { if (!mainWindow) void createWindow(); });
}).catch(() => {
  dialog.showErrorBox('Peek couldn’t start', 'Check that Peek can access its settings folder, then open it again.');
  app.quit();
});
app.on('window-all-closed', () => { if (process.platform !== 'darwin') app.quit(); });

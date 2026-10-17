import { contextBridge, ipcRenderer } from 'electron';
import { z } from 'zod';
import { connectResultSchema, stateSchema, type DesktopBridge, type ClientState } from './bridge.ts';

const bridge: DesktopBridge = {
  state: async () => stateSchema.parse(await ipcRenderer.invoke('peek:state')),
  connect: async (origin) => connectResultSchema.parse(await ipcRenderer.invoke('peek:connect', z.string().max(2048).parse(origin))),
  changeServer: async () => { await ipcRenderer.invoke('peek:change-server'); },
  retry: async () => { await ipcRenderer.invoke('peek:retry'); },
  onState: (listener) => {
    const handle = (_event: Electron.IpcRendererEvent, state: ClientState) => listener(stateSchema.parse(state));
    ipcRenderer.on('peek:state-changed', handle);
    return () => { ipcRenderer.removeListener('peek:state-changed', handle); };
  },
};
contextBridge.exposeInMainWorld('peekDesktop', bridge);

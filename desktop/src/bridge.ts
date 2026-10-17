import { z } from 'zod';
import { releaseSchema } from './release.ts';

export const stateSchema = z.object({
  mode: z.enum(['setup', 'loading', 'connected', 'error']),
  origin: z.string().nullable(),
  message: z.string(),
  release: releaseSchema,
}).strict();
export type ClientState = z.infer<typeof stateSchema>;
export const connectResultSchema = z.object({ ok: z.boolean(), message: z.string() }).strict();
export type ConnectResult = z.infer<typeof connectResultSchema>;
export interface DesktopBridge {
  state(): Promise<ClientState>;
  connect(origin: string): Promise<ConnectResult>;
  changeServer(): Promise<void>;
  retry(): Promise<void>;
  onState(listener: (state: ClientState) => void): () => void;
}

declare global {
  interface Window { peekDesktop: DesktopBridge }
}

import { mkdir, readFile, rename, writeFile } from 'node:fs/promises';
import { join } from 'node:path';
import { z } from 'zod';
import { serverOrigin } from './policy.ts';

export const configSchema = z.object({ version: z.literal(1), serverOrigin: z.string() }).strict();

export async function readConfig(directory: string, allowLoopback = false): Promise<string | null> {
  let contents: string;
  try {
    contents = await readFile(join(directory, 'server.json'), 'utf8');
  } catch (error) {
    if (error instanceof Error && 'code' in error && error.code === 'ENOENT') return null;
    throw error;
  }
  const config = configSchema.parse(JSON.parse(contents));
  return serverOrigin(config.serverOrigin, allowLoopback);
}

export async function saveConfig(directory: string, input: string, allowLoopback = false): Promise<string> {
  const origin = serverOrigin(input, allowLoopback);
  await mkdir(directory, { recursive: true, mode: 0o700 });
  const temporaryPath = join(directory, 'server.json.tmp');
  await writeFile(temporaryPath, JSON.stringify({ version: 1, serverOrigin: origin }), { mode: 0o600 });
  await rename(temporaryPath, join(directory, 'server.json'));
  return origin;
}

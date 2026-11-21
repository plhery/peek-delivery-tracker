import { vi } from 'vitest';
import { PEEK_ENV } from './peekSettings';

export { PEEK_ENV };

/** Sets Peek's own settings for this test; `vi.unstubAllEnvs()` takes them away. */
export function stubPeekEnv(): void {
  for (const [name, value] of Object.entries(PEEK_ENV)) vi.stubEnv(name, value);
}

import 'server-only';

/** How close together two refusals must come to find a provider blocked. */
export const REFUSAL_WINDOW_MS = 30 * 60_000;
/** How long a blocked provider rests before a lookup tries it again. */
export const BLOCK_PAUSE_MS = 30 * 60_000;

/**
 * Universal providers that answer "no history" for numbers they tracked. One such answer can be
 * the provider losing one parcel; two numbers within REFUSAL_WINDOW_MS mean it refuses this server
 * whatever it is asked, as ParcelsApp does for hours to an address it blocks. After a block, one
 * refusal within REFUSAL_WINDOW_MS of the pause ending finds it still in place.
 */
export class ProviderRefusals {
  private readonly recent = new Map<string, Map<string, number>>();
  private readonly blockedAt = new Map<string, number>();

  /** Records that `provider` found no history for `number`, which it had tracked; true when that finds it blocked. */
  refused(provider: string, number: string, at: number): boolean {
    const numbers = this.recent.get(provider) ?? new Map<string, number>();
    for (const [seen, time] of numbers) if (at - time >= REFUSAL_WINDOW_MS) numbers.delete(seen);
    numbers.set(number, at);
    this.recent.set(provider, numbers);
    const last = this.blockedAt.get(provider);
    const blocked = numbers.size >= 2 || (last !== undefined && at - last < BLOCK_PAUSE_MS + REFUSAL_WINDOW_MS);
    if (blocked) this.blockedAt.set(provider, at);
    return blocked;
  }
}

// Shared by every check in the process, however many bundles load this module.
const key = Symbol.for('peek.providerRefusals');
const scope = globalThis as typeof globalThis & { [key]?: ProviderRefusals };
export const providerRefusals: ProviderRefusals = scope[key] ??= new ProviderRefusals();

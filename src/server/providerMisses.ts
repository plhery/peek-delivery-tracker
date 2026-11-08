import 'server-only';

/** How far back a provider's answers without history count against its budget. */
export const MISS_WINDOW_MS = 30 * 60_000;

/**
 * The most answers without history a provider gives this server in MISS_WINDOW_MS before
 * checks nobody asked for stop asking it about numbers it never tracked. ParcelsApp refuses
 * an address for hours, whatever it is asked (providerRefusals.ts), after about forty in half
 * an hour; the other providers answer more than that without refusing, so they have none.
 */
const MISS_BUDGETS: Readonly<Record<string, number>> = { ParcelsApp: 30 };

/** Each universal provider's recent answers without history, across checks. */
export class ProviderMisses {
  private readonly misses = new Map<string, number[]>();
  private readonly spentAt = new Map<string, number>();

  constructor(private readonly budgets: Readonly<Record<string, number>> = MISS_BUDGETS) {}

  private recent(provider: string, at: number): number[] {
    const times = (this.misses.get(provider) ?? []).filter((time) => at - time < MISS_WINDOW_MS);
    this.misses.set(provider, times);
    return times;
  }

  /** Records that `provider` answered without history; true when that spends its budget, once in MISS_WINDOW_MS at most. */
  missed(provider: string, at: number): boolean {
    const budget = this.budgets[provider];
    if (budget === undefined) return false;
    const times = this.recent(provider, at);
    times.push(at);
    if (times.length < budget) return false;
    const last = this.spentAt.get(provider);
    this.spentAt.set(provider, at);
    return last === undefined || at - last >= MISS_WINDOW_MS;
  }

  /** Whether `provider` has no answers without history left to give in this window. */
  spent(provider: string, at: number): boolean {
    const budget = this.budgets[provider];
    return budget !== undefined && this.recent(provider, at).length >= budget;
  }
}

// Shared by every check and Add in the process, however many bundles load this module.
const key = Symbol.for('peek.providerMisses');
const scope = globalThis as typeof globalThis & { [key]?: ProviderMisses };
export const providerMisses: ProviderMisses = scope[key] ??= new ProviderMisses();

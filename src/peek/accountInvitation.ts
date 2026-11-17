/**
 * How often a visitor is invited to an account (`AccountRow`), as this browser remembers it. The invitation can be
 * closed once it has been shown in more than five visits; closed, it stays away for 30 days, then comes back for one
 * visit, and after that visit stays away another 30 days. Nothing of it leaves the browser.
 */
export const ACCOUNT_INVITATION_STORAGE_KEY = 'sdt.accountInvitation.v1'; // gitleaks:allow -- public localStorage name

/** Showings this close together are one visit. */
const VISIT_MS = 30 * 60_000;
/** Visits shown before the invitation can be closed. */
const CLOSABLE_AFTER = 5;
const AWAY_MS = 30 * 24 * 3_600_000;

export interface AccountInvitation {
  /** Visits it was shown in. */
  displays: number;
  /** When the last visit counted began. */
  lastShownAt?: number;
  /** Closed, it stays away until then. */
  hiddenUntil?: number;
  /** Back after its time away, it stays for the visit until then. */
  showingUntil?: number;
}

export function invitationShows(memory: AccountInvitation, now: number): boolean {
  return memory.hiddenUntil === undefined || now >= memory.hiddenUntil || (memory.showingUntil !== undefined && now < memory.showingUntil);
}

/** The invitation was shown: once a visit is counted, and back after its time away, for that one visit. */
export function invitationSeen(memory: AccountInvitation, now: number): AccountInvitation {
  let next = memory;
  if (next.hiddenUntil !== undefined && now >= next.hiddenUntil) next = { ...next, hiddenUntil: now + AWAY_MS, showingUntil: now + VISIT_MS };
  // A clock set back starts another visit.
  if (next.lastShownAt === undefined || now - next.lastShownAt >= VISIT_MS || now < next.lastShownAt) next = { ...next, displays: next.displays + 1, lastShownAt: now };
  return next;
}

export const invitationClosable = (memory: AccountInvitation) => memory.displays > CLOSABLE_AFTER;

export function invitationClosed(memory: AccountInvitation, now: number): AccountInvitation {
  return { displays: memory.displays, lastShownAt: memory.lastShownAt, hiddenUntil: now + AWAY_MS };
}

const time = (value: unknown) => typeof value === 'number' && Number.isFinite(value) ? value : undefined;

/** A stored memory, read back: anything else is none. */
export function accountInvitation(value: unknown): AccountInvitation {
  if (!value || typeof value !== 'object') return { displays: 0 };
  const { displays, lastShownAt, hiddenUntil, showingUntil } = value as Record<string, unknown>;
  const memory: AccountInvitation = { displays: typeof displays === 'number' && Number.isSafeInteger(displays) && displays > 0 ? displays : 0 };
  for (const [key, at] of [['lastShownAt', lastShownAt], ['hiddenUntil', hiddenUntil], ['showingUntil', showingUntil]] as const) {
    const kept = time(at);
    if (kept !== undefined) memory[key] = kept;
  }
  return memory;
}

// Storage that cannot be read or written leaves the memory working for as long as the page lives.
let fallback: AccountInvitation | null = null;

function read(): AccountInvitation {
  if (fallback) return fallback;
  try {
    return accountInvitation(JSON.parse(window.localStorage.getItem(ACCOUNT_INVITATION_STORAGE_KEY) ?? 'null'));
  } catch {
    return { displays: 0 };
  }
}

function write(memory: AccountInvitation) {
  try {
    window.localStorage.setItem(ACCOUNT_INVITATION_STORAGE_KEY, JSON.stringify(memory));
    fallback = null;
  } catch {
    fallback = memory;
  }
}

/** The invitation is about to show: whether it does on this visit, counted if so, and whether it can be closed. */
export function showAccountInvitation(now = Date.now()): { shows: boolean; closable: boolean } {
  const memory = read();
  if (!invitationShows(memory, now)) return { shows: false, closable: false };
  const seen = invitationSeen(memory, now);
  write(seen);
  return { shows: true, closable: invitationClosable(seen) };
}

export function closeAccountInvitation(now = Date.now()) {
  write(invitationClosed(read(), now));
}

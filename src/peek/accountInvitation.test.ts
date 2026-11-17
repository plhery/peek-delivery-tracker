import { describe, expect, it } from 'vitest';
import {
  ACCOUNT_INVITATION_STORAGE_KEY,
  accountInvitation,
  closeAccountInvitation,
  invitationClosable,
  invitationClosed,
  invitationSeen,
  invitationShows,
  showAccountInvitation,
  type AccountInvitation,
} from './accountInvitation';

const NOW = Date.parse('2026-11-17T09:00:00.000Z');
const MINUTE = 60_000;
const DAY = 24 * 60 * MINUTE;

/** Shown once a day, `visits` times. */
function shown(visits: number, from = NOW): { memory: AccountInvitation; at: number } {
  let memory: AccountInvitation = { displays: 0 };
  let at = from;
  for (let visit = 0; visit < visits; visit += 1) {
    at = from + visit * DAY;
    memory = invitationSeen(memory, at);
  }
  return { memory, at };
}

describe('the invitation to an account', () => {
  it('counts a visit once however often it is shown during it, and a clock set back as another', () => {
    let memory = invitationSeen({ displays: 0 }, NOW);
    memory = invitationSeen(memory, NOW + 5 * MINUTE);
    memory = invitationSeen(memory, NOW + 29 * MINUTE);
    expect(memory).toEqual({ displays: 1, lastShownAt: NOW });
    expect(invitationSeen(memory, NOW + 30 * MINUTE)).toEqual({ displays: 2, lastShownAt: NOW + 30 * MINUTE });
    expect(invitationSeen(memory, NOW - DAY)).toEqual({ displays: 2, lastShownAt: NOW - DAY });
  });

  it('can be closed from the sixth visit on', () => {
    expect(invitationClosable(shown(5).memory)).toBe(false);
    expect(invitationClosable(shown(6).memory)).toBe(true);
    expect(invitationClosable(shown(20).memory)).toBe(true);
  });

  it('stays away for 30 days once closed, then comes back for one visit, and stays away again after it', () => {
    const { memory: before, at } = shown(6);
    const closed = invitationClosed(before, at + MINUTE);
    expect(invitationShows(closed, at + 2 * MINUTE)).toBe(false);
    expect(invitationShows(closed, at + 29 * DAY)).toBe(false);
    const back = at + 31 * DAY;
    expect(invitationShows(closed, back)).toBe(true);
    const seen = invitationSeen(closed, back);
    expect(seen).toMatchObject({ displays: 7, hiddenUntil: back + 30 * DAY, showingUntil: back + 30 * MINUTE });
    expect(invitationClosable(seen)).toBe(true);
    // The visit it came back for: still there on another page of it, gone after it.
    expect(invitationShows(seen, back + 20 * MINUTE)).toBe(true);
    expect(invitationShows(invitationSeen(seen, back + 20 * MINUTE), back + 20 * MINUTE)).toBe(true);
    expect(invitationShows(seen, back + 31 * MINUTE)).toBe(false);
    expect(invitationShows(seen, back + 29 * DAY)).toBe(false);
    expect(invitationShows(seen, back + 30 * DAY)).toBe(true);
    // Closed during the visit it came back for: gone at once, for another 30 days.
    const again = invitationClosed(seen, back + MINUTE);
    expect(invitationShows(again, back + 2 * MINUTE)).toBe(false);
    expect(invitationShows(again, back + MINUTE + 30 * DAY)).toBe(true);
  });

  it('reads back only what it stored', () => {
    expect(accountInvitation(null)).toEqual({ displays: 0 });
    expect(accountInvitation('shown')).toEqual({ displays: 0 });
    expect(accountInvitation({ displays: 3, lastShownAt: NOW, hiddenUntil: 'soon', showingUntil: Infinity, more: 1 })).toEqual({ displays: 3, lastShownAt: NOW });
    expect(accountInvitation({ displays: -2, hiddenUntil: NOW })).toEqual({ displays: 0, hiddenUntil: NOW });
  });

  it('is remembered by the browser', () => {
    for (let visit = 0; visit < 5; visit += 1) expect(showAccountInvitation(NOW + visit * DAY)).toEqual({ shows: true, closable: false });
    expect(showAccountInvitation(NOW + 5 * DAY)).toEqual({ shows: true, closable: true });
    closeAccountInvitation(NOW + 5 * DAY + MINUTE);
    expect(showAccountInvitation(NOW + 6 * DAY)).toEqual({ shows: false, closable: false });
    expect(JSON.parse(localStorage.getItem(ACCOUNT_INVITATION_STORAGE_KEY)!)).toEqual({ displays: 6, lastShownAt: NOW + 5 * DAY, hiddenUntil: NOW + 35 * DAY + MINUTE });
    expect(showAccountInvitation(NOW + 36 * DAY)).toEqual({ shows: true, closable: true });
    expect(showAccountInvitation(NOW + 37 * DAY)).toEqual({ shows: false, closable: false });
  });
});

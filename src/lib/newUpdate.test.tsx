import { act, renderHook, waitFor } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { getNotificationPreferences, saveNotificationPreferences, type NotificationPreferences } from './pushNotifications';
import { loadNotificationPreferences } from '../store/notificationPreferences';
import type { Parcel } from '../types';
import {
  hasNewUpdate, MARK_NEW_UPDATES_STORAGE_KEY, MARK_NEW_UPDATES_UNSAVED_KEY, useMarkNewUpdates, useMarkNewUpdatesChoice, useSeenWhileOpen,
} from './newUpdate';

vi.mock('./pushNotifications', () => ({ getNotificationPreferences: vi.fn(), saveNotificationPreferences: vi.fn() }));

const SAVED: NotificationPreferences = {
  enabledStages: ['delivered'], quietHoursStart: null, quietHoursEnd: null, timezone: 'Europe/Zurich', emailOnDelivery: true, emailAvailable: true, markNewUpdates: true,
};
const session = () => ({ userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') });
const stored = () => window.localStorage.getItem(MARK_NEW_UPDATES_STORAGE_KEY);

beforeEach(() => {
  vi.mocked(getNotificationPreferences).mockReset().mockResolvedValue(SAVED);
  vi.mocked(saveNotificationPreferences).mockReset().mockImplementation(async (preferences) => ({ ...SAVED, ...preferences }));
});
afterEach(() => {
  window.localStorage.removeItem(MARK_NEW_UPDATES_STORAGE_KEY);
  window.localStorage.removeItem(MARK_NEW_UPDATES_UNSAVED_KEY);
});

describe('hasNewUpdate', () => {
  it('holds for a parcel whose latest scan arrived after it was last opened', () => {
    expect(hasNewUpdate({ newsAt: '2026-11-15T10:00:01.000Z', seenAt: '2026-11-15T10:00:00.000Z' })).toBe(true);
    // The server writes its times in its own way: they are compared as moments, not as text.
    expect(hasNewUpdate({ newsAt: '2026-11-15T10:00:00.5+00:00', seenAt: '2026-11-15T11:00:00+02:00' })).toBe(true);
  });

  it('does not hold once it was opened, without a scan, or before the server says when it was seen', () => {
    expect(hasNewUpdate({ newsAt: '2026-11-15T10:00:00.000Z', seenAt: '2026-11-15T10:00:00.000Z' })).toBe(false);
    expect(hasNewUpdate({ newsAt: '2026-11-15T10:00:00.000Z', seenAt: '2026-11-15T12:00:00.000Z' })).toBe(false);
    expect(hasNewUpdate({ seenAt: '2026-11-15T12:00:00.000Z' })).toBe(false);
    expect(hasNewUpdate({ newsAt: '2026-11-15T10:00:00.000Z' })).toBe(false);
    expect(hasNewUpdate({})).toBe(false);
  });
});

describe('the choice to mark new updates', () => {
  it('is on until switched off, and kept by the browser for someone without an account', () => {
    const shown = renderHook(() => useMarkNewUpdates());
    const choice = renderHook(() => useMarkNewUpdatesChoice(undefined));
    expect(shown.result.current).toBe(true);
    expect(choice.result.current[0]).toBe(true);

    act(() => choice.result.current[1](false));

    expect(shown.result.current).toBe(false);
    expect(choice.result.current[0]).toBe(false);
    expect(stored()).toBe('off');
    expect(saveNotificationPreferences).not.toHaveBeenCalled();
    // A page opened later starts from what the browser kept.
    expect(renderHook(() => useMarkNewUpdates()).result.current).toBe(false);

    act(() => choice.result.current[1](true));
    expect(shown.result.current).toBe(true);
    expect(stored()).toBeNull();
  });

  it('follows another tab of the same browser', () => {
    const shown = renderHook(() => useMarkNewUpdates());
    act(() => {
      window.localStorage.setItem(MARK_NEW_UPDATES_STORAGE_KEY, 'off');
      window.dispatchEvent(new StorageEvent('storage', { key: MARK_NEW_UPDATES_STORAGE_KEY }));
    });
    expect(shown.result.current).toBe(false);
  });

  it('is saved for an account with nothing else changed, and stays here when the server cannot take it', async () => {
    const auth = session();
    await loadNotificationPreferences(auth);
    const choice = renderHook(() => useMarkNewUpdatesChoice(auth));

    act(() => choice.result.current[1](false));

    expect(choice.result.current[0]).toBe(false);
    await waitFor(() => expect(saveNotificationPreferences).toHaveBeenCalledTimes(1));
    expect(vi.mocked(saveNotificationPreferences).mock.calls[0][0]).toEqual({
      markNewUpdates: false,
    });

    vi.mocked(saveNotificationPreferences).mockRejectedValueOnce(new Error('offline'));
    act(() => choice.result.current[1](true));
    await waitFor(() => expect(saveNotificationPreferences).toHaveBeenCalledTimes(2));
    expect(choice.result.current[0]).toBe(true);
    expect(stored()).toBeNull();
  });

  it('takes the account’s choice from its other devices when the preferences are read', async () => {
    const auth = session();
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...SAVED, markNewUpdates: false });
    const shown = renderHook(() => useMarkNewUpdates());
    renderHook(() => useMarkNewUpdatesChoice(auth));
    expect(shown.result.current).toBe(true);

    await act(async () => { await loadNotificationPreferences(auth); });

    expect(shown.result.current).toBe(false);
    expect(stored()).toBe('off');
    // And back, the next time they are read.
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...SAVED, markNewUpdates: true });
    await act(async () => { await loadNotificationPreferences(auth); });
    expect(shown.result.current).toBe(true);
  });

  it('keeps a choice the server could not take until it is saved, at the next read', async () => {
    const auth = session();
    await loadNotificationPreferences(auth);
    const choice = renderHook(() => useMarkNewUpdatesChoice(auth));
    vi.mocked(saveNotificationPreferences).mockRejectedValueOnce(new Error('offline'));

    act(() => choice.result.current[1](false));
    await waitFor(() => expect(saveNotificationPreferences).toHaveBeenCalledTimes(1));
    await act(() => new Promise((settled) => setTimeout(settled, 0)));

    // The account still says on: the choice made here stands, and goes to the account.
    vi.mocked(getNotificationPreferences).mockImplementation(async () => ({ ...SAVED }));
    await act(async () => { await loadNotificationPreferences(auth); });
    expect(choice.result.current[0]).toBe(false);
    await waitFor(() => expect(saveNotificationPreferences).toHaveBeenCalledTimes(2));
    expect(vi.mocked(saveNotificationPreferences).mock.calls[1][0].markNewUpdates).toBe(false);
    await waitFor(() => expect(window.localStorage.getItem(MARK_NEW_UPDATES_UNSAVED_KEY)).toBeNull());

    // Saved, it follows the account again.
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...SAVED, markNewUpdates: true });
    await act(async () => { await loadNotificationPreferences(auth); });
    expect(choice.result.current[0]).toBe(true);
    expect(saveNotificationPreferences).toHaveBeenCalledTimes(2);
  });

  it('takes a choice made without an account to the account signed in to next', async () => {
    const guest = renderHook(() => useMarkNewUpdatesChoice(undefined));
    act(() => guest.result.current[1](false));
    guest.unmount();

    const auth = session();
    const choice = renderHook(() => useMarkNewUpdatesChoice(auth));
    await act(async () => { await loadNotificationPreferences(auth); });

    expect(choice.result.current[0]).toBe(false);
    await waitFor(() => expect(saveNotificationPreferences).toHaveBeenCalledTimes(1));
    expect(vi.mocked(saveNotificationPreferences).mock.calls[0][0].markNewUpdates).toBe(false);
  });

  it('keeps this browser’s choice against a server that does not name one', async () => {
    const auth = session();
    window.localStorage.setItem(MARK_NEW_UPDATES_STORAGE_KEY, 'off');
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...SAVED, markNewUpdates: undefined });
    const choice = renderHook(() => useMarkNewUpdatesChoice(auth));
    await act(async () => { await loadNotificationPreferences(auth); });
    expect(choice.result.current[0]).toBe(false);
  });
});

describe('useSeenWhileOpen', () => {
  const news = (newsAt: string): Parcel => ({
    id: 'tea', label: 'Tea', trackingNumber: '993412345612345678', carrier: 'swiss-post', syncStatus: 'ok', createdAt: '2026-11-01T08:00:00.000Z',
    newsAt, seenAt: '2026-11-15T09:00:00.000Z',
  });
  const visibility = (state: DocumentVisibilityState) => {
    Object.defineProperty(document, 'visibilityState', { configurable: true, get: () => state });
    document.dispatchEvent(new Event('visibilitychange'));
  };
  afterEach(() => { delete (document as { visibilityState?: DocumentVisibilityState }).visibilityState; });

  it('marks the open parcel once for each piece of news, and none for a parcel that has none', () => {
    const markSeen = vi.fn().mockResolvedValue(undefined);
    const open = renderHook(({ parcel }) => useSeenWhileOpen(parcel, markSeen), { initialProps: { parcel: null as Parcel | null } });
    expect(markSeen).not.toHaveBeenCalled();

    open.rerender({ parcel: news('2026-11-15T10:00:00.000Z') });
    expect(markSeen).toHaveBeenCalledTimes(1);
    expect(markSeen).toHaveBeenCalledWith('tea');
    // The list is read again with nothing new.
    open.rerender({ parcel: news('2026-11-15T10:00:00.000Z') });
    expect(markSeen).toHaveBeenCalledTimes(1);
    // Seen: nothing to mark.
    open.rerender({ parcel: { ...news('2026-11-15T10:00:00.000Z'), seenAt: '2026-11-15T10:00:00.000Z' } });
    expect(markSeen).toHaveBeenCalledTimes(1);
    // A scan arrives under the reader's eyes.
    open.rerender({ parcel: { ...news('2026-11-15T11:00:00.000Z'), seenAt: '2026-11-15T10:00:00.000Z' } });
    expect(markSeen).toHaveBeenCalledTimes(2);
  });

  it('marks an email-added parcel without scans when it becomes visible', () => {
    const markSeen = vi.fn().mockResolvedValue(undefined);
    visibility('hidden');
    const parcel = { ...news(''), seenAt: '1970-01-01T00:00:00+00:00' };
    renderHook(() => useSeenWhileOpen(parcel, markSeen));
    expect(markSeen).not.toHaveBeenCalled();
    visibility('visible');
    expect(markSeen).toHaveBeenCalledExactlyOnceWith('tea');
  });

  it('waits for the page to be in front', () => {
    const markSeen = vi.fn().mockResolvedValue(undefined);
    visibility('hidden');
    const open = renderHook(() => useSeenWhileOpen(news('2026-11-15T10:00:00.000Z'), markSeen));
    expect(markSeen).not.toHaveBeenCalled();

    visibility('visible');
    expect(markSeen).toHaveBeenCalledTimes(1);
    visibility('hidden');
    visibility('visible');
    expect(markSeen).toHaveBeenCalledTimes(1);

    open.unmount();
  });

  it('marks nothing for a page closed while it was out of view', () => {
    const markSeen = vi.fn().mockResolvedValue(undefined);
    visibility('hidden');
    renderHook(() => useSeenWhileOpen(news('2026-11-15T10:00:00.000Z'), markSeen)).unmount();
    visibility('visible');
    expect(markSeen).not.toHaveBeenCalled();
  });
});

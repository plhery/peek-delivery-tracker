import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { clearSharedParcelInput, isShareDraftId, readSharedParcel, shareTargetAddress } from './shareTarget';

const DRAFT = '00000000-0000-4000-8000-00000000000a';
const OTHER_DRAFT = '00000000-0000-4000-8000-00000000000b';

beforeEach(() => window.history.replaceState({}, '', '/'));
afterEach(() => vi.unstubAllGlobals());

describe('PWA share target', () => {
  it('reads a one-time service-worker draft without putting private content in the URL', async () => {
    window.history.replaceState({}, '', shareTargetAddress({ draft: DRAFT }));
    expect(window.location.search).toBe(`?share-target=${DRAFT}`);
    const fetch = vi.fn().mockResolvedValue(new Response(JSON.stringify({
      label: 'New shoes',
      trackingInput: 'https://service.post.ch/track\nTracking 993412345699999980',
    }), { status: 200, headers: { 'Content-Type': 'application/json' } }));
    vi.stubGlobal('fetch', fetch);

    await expect(readSharedParcel()).resolves.toEqual({ input: {
      label: 'New shoes',
      trackingInput: 'https://service.post.ch/track\nTracking 993412345699999980',
    } });
    expect(fetch).toHaveBeenCalledWith(`/share-target/draft/${DRAFT}`, {
      cache: 'no-store',
      credentials: 'same-origin',
    });
    expect(window.location.href).not.toContain('993412345699999980');
    // The draft is handed out once: a screen drawn again reads the same answer without asking.
    await expect(readSharedParcel()).resolves.toEqual({ input: expect.objectContaining({ label: 'New shoes' }) });
    expect(fetch).toHaveBeenCalledOnce();
  });

  it('reads each share from its own draft, so a second share leaves the first one whole', async () => {
    const drafts: Record<string, string> = { [OTHER_DRAFT]: 'Tracking TESTPARCEL123', '00000000-0000-4000-8000-00000000000c': 'Tracking TESTPARCEL456' };
    vi.stubGlobal('fetch', vi.fn(async (address: string) => Response.json({ label: '', trackingInput: drafts[address.split('/').pop()!] })));
    for (const [draft, trackingInput] of Object.entries(drafts)) {
      window.history.replaceState({}, '', shareTargetAddress({ draft }));
      await expect(readSharedParcel()).resolves.toEqual({ input: { label: '', trackingInput } });
    }
  });

  it('knows a draft id by the worker’s random shape alone', () => {
    expect(isShareDraftId(DRAFT)).toBe(true);
    for (const id of ['1', 'failed', `${DRAFT}0`, DRAFT.toUpperCase(), 'TESTPARCEL123', '../draft']) expect(isShareDraftId(id)).toBe(false);
  });

  it('ignores ordinary query strings and removes only the share marker', async () => {
    window.history.replaceState({}, '', `/?parcel=parcel-1&share-target=${DRAFT}`);
    clearSharedParcelInput();
    expect(window.location.search).toBe('?parcel=parcel-1');

    const fetch = vi.fn();
    vi.stubGlobal('fetch', fetch);
    await expect(readSharedParcel()).resolves.toBeNull();
    // A marker the worker never writes is not a share, nor is the one an earlier version wrote.
    for (const marker of ['yes', '1']) {
      window.history.replaceState({}, '', `/?share-target=${marker}`);
      await expect(readSharedParcel()).resolves.toBeNull();
      clearSharedParcelInput();
      expect(window.location.search).toBe(`?share-target=${marker}`);
    }
    expect(fetch).not.toHaveBeenCalled();
  });

  it('tells the app why a share did not come through, whatever the worker could not keep', async () => {
    const fetch = vi.fn().mockResolvedValue(new Response(null, { status: 404 }));
    vi.stubGlobal('fetch', fetch);
    // The worker named the reason: no draft to ask for.
    for (const failure of ['too-large', 'failed'] as const) {
      window.history.replaceState({}, '', `${shareTargetAddress({ failure })}&parcel=parcel-1`);
      await expect(readSharedParcel()).resolves.toEqual({ failure });
      clearSharedParcelInput();
      expect(window.location.search).toBe('?parcel=parcel-1');
    }
    expect(fetch).not.toHaveBeenCalled();

    // A draft that is gone, expired or empty is a share that failed too.
    const drafts = ['00000000-0000-4000-8000-0000000000c1', '00000000-0000-4000-8000-0000000000c2', '00000000-0000-4000-8000-0000000000c3'];
    fetch.mockResolvedValueOnce(new Response(null, { status: 404 }))
      .mockResolvedValueOnce(Response.json({ label: 'Order', trackingInput: '   ' }))
      .mockRejectedValueOnce(new TypeError('offline'));
    for (const draft of drafts) {
      window.history.replaceState({}, '', shareTargetAddress({ draft }));
      await expect(readSharedParcel()).resolves.toEqual({ failure: 'failed' });
    }
  });
});

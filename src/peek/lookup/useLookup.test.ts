import { act, renderHook } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { LINK_ID, OWNER_KEY, pendingView } from '../../test/parcelLinks';
import { forgetAllRecents } from '../recents';
import { unanswered } from './machine';
import { RECOGNISE_BEAT_MS, useLookup } from './useLookup';

const mocks = vi.hoisted(() => ({ lookup: vi.fn(), detect: vi.fn() }));
vi.mock('../links', async (original) => ({
  ...await original<typeof import('../links')>(),
  lookupParcel: mocks.lookup,
  detectCarrierPublic: mocks.detect,
}));

beforeEach(() => {
  vi.useFakeTimers();
  mocks.lookup.mockResolvedValue({ id: LINK_ID, key: OWNER_KEY, view: pendingView() });
  mocks.detect.mockImplementation((number: string) => Promise.resolve(unanswered(number)));
});
afterEach(() => {
  vi.useRealTimers();
  forgetAllRecents();
});

describe('useLookup', () => {
  it('leaves no hand-over behind when taking the parcel closes the door', async () => {
    // The front door starts over under the parcel's page at once, in the hand-over itself.
    const onTracked = vi.fn(() => view.unmount());
    const view = renderHook(() => useLookup(onTracked));
    act(() => {
      view.result.current.send({ type: 'edit', text: '1234577899', via: 'typing' });
      view.result.current.send({ type: 'submit' });
    });
    await act(async () => { await vi.advanceTimersByTimeAsync(RECOGNISE_BEAT_MS); });
    expect(onTracked).toHaveBeenCalledOnce();
    expect(vi.getTimerCount()).toBe(0);
  });
});

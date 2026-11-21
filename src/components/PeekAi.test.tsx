import { act, render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import type { ApiPackageRow } from '../generated/apiContract';
import { carrierInfo } from '../lib/carriers';
import { SHOW_PEEK_AI_STORAGE_KEY } from '../lib/peekAi';
import { toParcel } from '../store/apiRepo';
import type { ParcelWithEvents, PeekAi, TrackingEvent } from '../types';
import { ParcelCard } from './ParcelCard';
import { ParcelDetail } from './ParcelDetail';

// Tuesday 24 November 2026, 15:30 on this device's clock. Synthetic parcels only.
const NOW = new Date(2026, 10, 24, 15, 30);
const at = (day: number, hour: number) => new Date(2026, 10, day, hour, 12).toISOString();
const scan = (stage: TrackingEvent['stage'], day: number, hour: number): TrackingEvent => ({
  id: `${stage}-${day}-${hour}`, parcelId: 'parcel', stage, description: 'Scan', occurredAt: at(day, hour),
});
const NOT_TODAY: PeekAi = { says: 'not_today', why: 'no_out_for_delivery', carrier_day: '2026-11-24', sure: 0.82 };
const BY_TIME: PeekAi = {
  says: 'by_time', why: 'out_for_delivery', time: '16:00', sure: 0.86, out_since: '2026-11-24T08:12:00+01:00',
  hours: [{ time: '14:00', share: 0.3 }, { time: '15:00', share: 0.4 }, { time: '16:00', share: 0.1 }],
};
const NO_DATE: PeekAi = { says: 'by_day', why: 'no_date', day: '2026-11-26', sure: 0.81, days: [{ day: '2026-11-25', p: 0.4 }, { day: '2026-11-26', p: 0.45 }] };
const parcel: ParcelWithEvents = {
  id: 'parcel', carrier: 'dpd', trackingNumber: '01234567890123', label: 'Trail weekend kit', createdAt: '2026-11-20T12:00:00Z',
  syncStatus: 'ok', expectedDelivery: '2026-11-24', peekAi: NOT_TODAY, events: [scan('in_transit', 24, 6)],
};
const DPD = carrierInfo('dpd', 'en').name;
const detail = (values: Partial<ParcelWithEvents> = {}) => render(<ParcelDetail parcel={{ ...parcel, ...values }} onBack={vi.fn()}
  onRename={vi.fn()} onChangeCarrier={vi.fn()} onSetNotificationsMuted={vi.fn()} onRefresh={vi.fn()} onRestore={vi.fn()}
  onArchive={vi.fn()} onDelete={vi.fn()} />);

beforeEach(() => {
  vi.useFakeTimers({ toFake: ['Date'] });
  vi.setSystemTime(NOW);
});
afterEach(() => {
  vi.useRealTimers();
  window.localStorage.clear();
});

describe('Peek AI on a card', () => {
  it('sits under the carrier’s date, a comment on its today, and is heard with the card', () => {
    render(<ParcelCard parcel={parcel} onOpen={vi.fn()} />);
    const card = screen.getByRole('button', { name: /Trail weekend kit/ });
    const dates = card.querySelector('.parcel-card__dates')!;
    expect(dates.querySelector('.parcel-card__eta')).toHaveTextContent(/^today$/i);
    expect(within(dates as HTMLElement).getByText('Peek AI').closest('.peek-ai-sig')).toBeInTheDocument();
    expect(dates.querySelector('.peek-ai-phrase__words')).toHaveTextContent('unlikely');
    expect(card.getAttribute('aria-label')).toContain('Peek AI: unlikely');
  });

  it('takes the date’s place when the carrier gives none, and says nothing once its day has passed', () => {
    const { container, unmount } = render(<ParcelCard parcel={{ ...parcel, expectedDelivery: undefined, peekAi: NO_DATE }} onOpen={vi.fn()} />);
    const dates = container.querySelector('.parcel-card__dates')!;
    expect(dates.querySelector('.parcel-card__eta')).toBeNull();
    expect(dates).toHaveTextContent('Peek AIby Thu 26 Nov');
    unmount();
    vi.setSystemTime(new Date(2026, 10, 27, 9));
    render(<ParcelCard parcel={{ ...parcel, expectedDelivery: undefined, peekAi: NO_DATE }} onOpen={vi.fn()} />);
    expect(screen.queryByText('Peek AI')).toBeNull();
  });

  it('sits beside the status on Next up: out for delivery, by the hour', () => {
    const { container } = render(<ParcelCard variant="hero" parcel={{ ...parcel, expectedDelivery: undefined, peekAi: BY_TIME,
      events: [scan('in_transit', 23, 20), scan('out_for_delivery', 24, 8)] }} onOpen={vi.fn()} />);
    const summary = container.querySelector('.parcel-card__summary')!;
    expect(summary).toHaveTextContent('Out for delivery·Peek AIby 16:00');
  });

  it('goes at the hour it named, on a list left open', () => {
    vi.useFakeTimers({ toFake: ['Date', 'setTimeout', 'clearTimeout'] });
    vi.setSystemTime(NOW);
    const { container } = render(<ParcelCard variant="hero" parcel={{ ...parcel, expectedDelivery: undefined, peekAi: BY_TIME,
      events: [scan('in_transit', 23, 20), scan('out_for_delivery', 24, 8)] }} onOpen={vi.fn()} />);
    const summary = container.querySelector('.parcel-card__summary')!;
    act(() => { vi.advanceTimersByTime(29 * 60_000); });
    expect(summary).toHaveTextContent('Peek AIby 16:00');
    act(() => { vi.advanceTimersByTime(60_000 + 100); });
    expect(summary).toHaveTextContent(/^Out for delivery$/);
  });

  it('is gone everywhere once switched off', () => {
    window.localStorage.setItem(SHOW_PEEK_AI_STORAGE_KEY, 'off');
    const { container } = render(<ParcelCard parcel={parcel} onOpen={vi.fn()} />);
    expect(screen.queryByText('Peek AI')).toBeNull();
    expect(container.querySelector('.parcel-card__top > .parcel-card__eta')).toHaveTextContent(/^today$/i);
  });
});

describe('Peek AI on the parcel’s page', () => {
  it('says what it expects and why, and its (i) tells how sure it is, with the switch that hides it', async () => {
    const user = userEvent.setup();
    detail();
    const note = screen.getAllByRole('note').find((element) => element.classList.contains('peek-ai-card'))!;
    expect(note).toHaveTextContent('Peek AItoday is unlikely');
    expect(note).toHaveTextContent(`${DPD} says today, but it hasn’t gone out for delivery.`);

    await user.click(within(note).getByRole('button', { name: 'About Peek AI' }));
    const sheet = screen.getByRole('dialog', { name: 'Peek AI' });
    expect(within(sheet).getByText('Today is unlikely')).toBeInTheDocument();
    expect(within(sheet).getByText(`${DPD} says today`)).toBeInTheDocument();
    expect(within(sheet).getByText('Right 8 times in 10 at this hour.')).toBeInTheDocument();
    expect(sheet).toHaveTextContent(`This is Peek’s own estimate: ${DPD} has not promised it.`);

    const shown = within(sheet).getByRole('switch', { name: 'Show Peek AI' });
    expect(shown).toBeChecked();
    await user.click(shown);
    // The sheet stays while it is open, and the page's card is gone.
    expect(within(screen.getByRole('dialog', { name: 'Peek AI' })).getByRole('switch', { name: 'Show Peek AI' })).not.toBeChecked();
    expect(document.querySelector('.peek-ai-card')).toBeNull();
    expect(window.localStorage.getItem(SHOW_PEEK_AI_STORAGE_KEY)).toBe('off');
  });

  it('goes with its sheet once the day it spoke of has passed, on a page left open', async () => {
    vi.useFakeTimers({ toFake: ['Date', 'setTimeout', 'clearTimeout'], shouldAdvanceTime: true });
    vi.setSystemTime(NOW);
    const user = userEvent.setup({ advanceTimers: vi.advanceTimersByTime });
    detail();
    await user.click(screen.getByRole('button', { name: 'About Peek AI' }));
    expect(screen.getByRole('dialog', { name: 'Peek AI' })).toBeInTheDocument();
    act(() => { vi.advanceTimersByTime(8.5 * 3_600_000); });
    expect(screen.queryByRole('dialog', { name: 'Peek AI' })).toBeNull();
    expect(document.querySelector('.peek-ai-card')).toBeNull();
  });

  it('shows the days it expects when the carrier gives no date', async () => {
    const user = userEvent.setup();
    detail({ expectedDelivery: undefined, peekAi: NO_DATE });
    await user.click(screen.getByRole('button', { name: 'About Peek AI' }));
    const sheet = screen.getByRole('dialog', { name: 'Peek AI' });
    expect(within(sheet).getByText('Likely by Thu 26 Nov')).toBeInTheDocument();
    expect(within(sheet).getByText('Most likely days')).toBeInTheDocument();
    expect(within(sheet).getByText('Right 8 times in 10 on parcels like this one.')).toBeInTheDocument();
  });
});

describe('Peek AI from the API', () => {
  it('is read with the parcel, and left out when the server gives none', () => {
    const row = { id: 'parcel', tracking_number: '01234567890123', carrier: 'dpd', created_at: '2026-11-20T12:00:00Z', sync_status: 'ok',
      tracking_events: [], peek_ai: NOT_TODAY };
    expect(toParcel(row as unknown as ApiPackageRow).peekAi).toEqual(NOT_TODAY);
    expect(toParcel({ ...row, peek_ai: undefined } as unknown as ApiPackageRow)).not.toHaveProperty('peekAi');
  });
});

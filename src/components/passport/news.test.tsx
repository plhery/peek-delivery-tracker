import { act, render, screen } from '@testing-library/react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { Passport } from '../Passport';
import { NewStampLine } from './NewStampLine';
import { usePassportNews } from './news';
import type { ParcelWithEvents } from '../../types';

const delivered = (id: string, carrier: string, at: string): ParcelWithEvents => ({
  id, label: `Parcel ${id}`, carrier: carrier as ParcelWithEvents['carrier'], trackingNumber: `T${id}`, syncStatus: 'ok', createdAt: '2026-09-01T00:00:00Z',
  events: [
    { id: `${id}-1`, parcelId: id, stage: 'in_transit', occurredAt: at.replace('T12', 'T08'), description: 'In transit' },
    { id: `${id}-2`, parcelId: id, stage: 'delivered', occurredAt: at, description: 'Delivered' },
  ],
});

function Harness({ parcels, open }: { parcels: ParcelWithEvents[]; open: boolean }) {
  const news = usePassportNews(parcels, 'test', false);
  return <>
    <span data-testid="news">{news.unseen.map((item) => item.seen).join(',')}</span>
    {parcels.map((parcel) => <NewStampLine key={parcel.id} items={news.byParcel.get(parcel.id)} onOpen={() => undefined} />)}
    {open && <Passport news={news} loading={false} />}
  </>;
}

describe('passport news', () => {
  beforeEach(() => { localStorage.clear(); vi.useRealTimers(); });

  it('counts what a passport already holds as seen, then brings each new stamp to its card until the passport opens', () => {
    const first = [delivered('a', 'dhl', '2026-09-03T12:00:00Z'), delivered('b', 'ups', '2026-09-04T12:00:00Z')];
    const { rerender } = render(<Harness parcels={first} open={false} />);
    expect(screen.getByTestId('news')).toHaveTextContent(/^$/);
    expect(screen.queryByRole('button', { name: /New stamp/ })).toBeNull();

    // A third carrier's delivery earns Well connected.
    const next = [...first, delivered('c', 'fedex', '2026-09-05T12:00:00Z')];
    rerender(<Harness parcels={next} open={false} />);
    expect(screen.getByTestId('news')).toHaveTextContent('stamp:connected');
    expect(screen.getByRole('button', { name: 'New stamp: Well connected' })).toBeVisible();

    act(() => { rerender(<Harness parcels={next} open />); });
    expect(screen.getByTestId('news')).toHaveTextContent(/^$/);
    expect(screen.queryByRole('button', { name: /New stamp/ })).toBeNull();
    expect(screen.getByRole('button', { name: 'Well connected, Earned on 5 September 2026, New' })).toBeVisible();
    expect(document.querySelector('.passport-seal--press')).not.toBeNull();
  });

  it('names every new stamp a delivery brought on one line', () => {
    const { rerender } = render(<Harness parcels={[]} open={false} />);
    rerender(<Harness parcels={[delivered('a', 'dhl', '2026-12-03T12:00:00Z')]} open={false} />);
    expect(screen.getByRole('button', { name: '3 new stamps: First arrival, Express arrival, Home for the holidays' })).toBeVisible();
  });
});

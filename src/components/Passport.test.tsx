import { fireEvent, render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import { Passport as PassportPage } from './Passport';
import { usePassportNews } from './passport/news';
import type { ParcelWithEvents } from '../types';

function Passport({ parcels, loading }: { parcels: ParcelWithEvents[]; loading: boolean }) {
  return <PassportPage news={usePassportNews(parcels, 'test', loading)} loading={loading} />;
}

describe('Passport journal', () => {
  it('keeps stamps available without inventing timing or country records for a new passport', () => {
    const { container } = render(<Passport parcels={[]} loading={false} />);
    expect(container.querySelector('.passport-cover__count')).toHaveTextContent('0');
    expect(container.querySelectorAll('.stamp-card')).toHaveLength(3);
    expect(container.querySelectorAll('.country-ink')).toHaveLength(0);
    expect(screen.getByText(/^Every country a parcel of yours goes through/)).toBeVisible();
    expect(container.querySelector('.passport-note')).toHaveTextContent(/./);
    expect(container.textContent).not.toContain('—');
    expect(screen.getByRole('button', { name: 'First arrival, Not yet' })).toHaveAttribute('aria-haspopup', 'dialog');
  });

  it('reveals all nineteen stamps on request and keeps Century club out of the collection', () => {
    const { container } = render(<Passport parcels={[]} loading={false} />);
    fireEvent.click(screen.getByRole('button', { name: 'All stamps' }));
    expect(container.querySelectorAll('.stamp-card')).toHaveLength(19);
    for (const name of ['Across borders', 'Around the world', 'The regular', 'Right next door', 'Worth the wait', 'A busy doorstep', 'Picked up', 'Home for the holidays']) {
      expect(screen.getByRole('button', { name: new RegExp(`^${name},`) })).toBeVisible();
    }
    expect(container.textContent).not.toContain('Century club');
    fireEvent.click(screen.getByRole('button', { name: 'Show less' }));
    expect(container.querySelectorAll('.stamp-card')).toHaveLength(3);
  });

  it('earns The regular at 25 deliveries, including archived arrivals', () => {
    const parcels: ParcelWithEvents[] = Array.from({ length: 25 }, (_, index) => ({
      id: String(index), label: 'Parcel', carrier: 'dhl', trackingNumber: String(index), syncStatus: 'ok',
      createdAt: '2026-09-01T00:00:00Z', archivedAt: '2026-09-04T00:00:00Z',
      events: [{ id: `event-${index}`, parcelId: String(index), stage: 'delivered', occurredAt: '2026-09-03T12:00:00Z', description: 'Delivered' }],
    }));
    const { rerender } = render(<Passport parcels={parcels.slice(0, 24)} loading={false} />);
    fireEvent.click(screen.getByRole('button', { name: 'All stamps' }));
    expect(screen.getByRole('button', { name: 'The regular, 24 of 25' })).not.toHaveClass('stamp-card--earned');
    rerender(<Passport parcels={parcels} loading={false} />);
    expect(screen.getByRole('button', { name: /^The regular, Earned on/ })).toHaveClass('stamp-card--earned');
  });

  it('counts an archived arrival with partial history without presenting a made-up duration', () => {
    const parcel: ParcelWithEvents = {
      id: 'archived', label: 'Books', carrier: 'dhl', trackingNumber: '1234567890', syncStatus: 'ok',
      createdAt: '2026-09-01T00:00:00Z', archivedAt: '2026-09-04T00:00:00Z',
      events: [{ id: 'arrival', parcelId: 'archived', stage: 'delivered', occurredAt: '2026-09-03T12:00:00Z', description: 'Delivered' }],
    };
    const { container } = render(<Passport parcels={[parcel]} loading={false} />);
    expect(container.querySelector('.passport-cover__count')).toHaveTextContent('1');
    expect(screen.getByRole('button', { name: /^First arrival, Earned on 3 September 2026$/ })).toBeVisible();
    expect(container.querySelectorAll('.country-ink')).toHaveLength(0);
    expect(screen.queryByRole('button', { name: /^Average,/ })).toBeNull();
  });

  it('turns between the countries, stamps and numbers pages, with no first-scan list left', () => {
    const through = (id: string, carrier: string, createdAt: string, places: string[], delivered: string): ParcelWithEvents => ({
      id, label: `Parcel ${id}`, carrier: carrier as ParcelWithEvents['carrier'], trackingNumber: `T${id}`, syncStatus: 'ok', createdAt,
      events: [
        ...places.map((location, index) => ({ id: `${id}-${index}`, parcelId: id, stage: 'in_transit' as const, occurredAt: `${delivered.slice(0, 10)}T0${index}:00:00Z`, description: 'Sorted', location })),
        { id: `${id}-done`, parcelId: id, stage: 'delivered', occurredAt: `${delivered.slice(0, 10)}T12:00:00Z`, description: 'Delivered', location: places.at(-1) },
      ],
    });
    render(<Passport loading={false} parcels={[
      through('a', 'dhl', '2026-03-01T09:00:00Z', ['Wien, AT', 'Zürich, CH'], '2026-03-02'),
      through('b', 'ups', '2026-03-10T09:00:00Z', ['Zürich, CH'], '2026-03-12'),
      through('c', 'dhl', '2026-05-01T09:00:00Z', ['Zürich, CH'], '2026-05-03'),
    ]} />);
    const tabs = screen.getAllByRole('tab');
    expect(tabs.map((tab) => tab.textContent)).toEqual(['Countries', 'Stamps', 'Numbers']);
    expect(screen.getByRole('tablist', { name: 'Passport pages' })).toBeVisible();
    expect(tabs[0]).toHaveAttribute('aria-selected', 'true');
    expect(screen.getByRole('button', { name: 'Austria, First reached on 2 March 2026, 1 parcel' })).toBeVisible();
    expect(screen.getByRole('button', { name: 'Switzerland, First reached on 2 March 2026, 3 parcels' })).toBeVisible();
    expect(screen.queryByText('First scanned in')).toBeNull();

    fireEvent.keyDown(tabs[0]!, { key: 'End' });
    expect(tabs[2]).toHaveAttribute('aria-selected', 'true');
    expect(tabs[2]).toHaveFocus();
    const numbers = screen.getByRole('tabpanel', { name: 'Numbers' });
    expect(numbers).not.toHaveAttribute('inert');
    expect(numbers).toHaveTextContent('Busiest monthMarch 2026 · 2 parcels');
    expect(numbers).toHaveTextContent('Carriers you’ve used2 · most often DHL');
    expect(screen.getByRole('tabpanel', { name: 'Countries' })).toHaveAttribute('inert');
    fireEvent.click(tabs[1]!);
    expect(tabs[1]).toHaveAttribute('aria-selected', 'true');
    expect(screen.getByRole('tabpanel', { name: 'Stamps' })).not.toHaveAttribute('inert');
  });
});

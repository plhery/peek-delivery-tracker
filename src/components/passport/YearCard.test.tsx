import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import { passportBook, yearInParcels } from '../../lib/passport';
import { translateMessage, type Messages } from '../../lib/messages';
import en from '../../../shared/locales/en.json';
import type { ParcelWithEvents } from '../../types';
import { countryLayout } from './cardDrawing';
import { cardWords, inkDay, YearCard } from './YearCard';

const parcel = (id: string, delivered: string, location?: string): ParcelWithEvents => ({
  id, label: `Order ${id}`, carrier: 'dhl', trackingNumber: `T${id}`, syncStatus: 'ok', createdAt: '2026-01-01T00:00:00Z',
  events: [
    { id: `${id}-1`, parcelId: id, stage: 'in_transit', occurredAt: delivered.replace('T12', 'T00'), description: 'Sorted', location },
    { id: `${id}-2`, parcelId: id, stage: 'delivered', occurredAt: delivered, description: 'Delivered', location },
  ],
});
const t = (key: Parameters<typeof translateMessage>[1], variables?: Record<string, string | number>) => translateMessage('en', key, variables, en as Messages);

describe('the year in parcels card', () => {
  it('is offered in December once a parcel was delivered that year', () => {
    const book = passportBook([parcel('a', '2026-03-02T12:00:00Z', 'Zürich, CH')]);
    const { rerender } = render(<YearCard book={book} now={new Date('2026-11-30T12:00:00').getTime()} />);
    expect(screen.queryByRole('button', { name: 'Your 2026 in parcels' })).toBeNull();
    rerender(<YearCard book={book} now={new Date('2026-12-01T09:00:00').getTime()} />);
    expect(screen.getByRole('button', { name: 'Your 2026 in parcels' })).toBeVisible();
    rerender(<YearCard book={passportBook([parcel('a', '2025-03-02T12:00:00Z')])} now={new Date('2026-12-01T09:00:00').getTime()} />);
    expect(screen.queryByRole('button', { name: 'Your 2026 in parcels' })).toBeNull();
  });

  it('says only counts, countries and the fastest time', () => {
    const year = yearInParcels(passportBook([parcel('a', '2026-03-02T12:00:00Z', 'Zürich, CH')]), 2026);
    const words = cardWords(year, t, 'en');
    expect(words.lineOne).toBe('1 delivered · 1 country');
    expect(words.lineTwo).toBe('Fastest 12h · peektracker.com');
    expect(words.onlyCountry).toBe('Switzerland');
    expect(words.description).toBe('2026 in parcels. 1 delivered, 1 country: Switzerland. Fastest 12h.');
    expect(JSON.stringify(words)).not.toContain('Order');
    expect(inkDay(Date.UTC(2026, 7, 12, 12), 'en-GB')).toBe('12 AUG 2026');
  });

  it('lays out up to six country stamps in the zone, rows centred', () => {
    const five = countryLayout(5, 430, 900);
    expect(five.map((box) => [Math.round(box.x), Math.round(box.y)])).toEqual([[120, 461], [416, 461], [712, 461], [268, 685], [564, 685]]);
    expect(countryLayout(1, 430, 900)[0]).toMatchObject({ x: 330, w: 420, h: 312 });
  });
});

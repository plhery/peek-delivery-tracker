import { render, screen } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import type { ParcelWithEvents } from '../types';
import { ParcelCard } from './ParcelCard';

// Friday 27 November 2026, 18:00 on this device's clock. Synthetic parcels only.
const NOW = new Date(2026, 10, 27, 18, 0);
// The carrier has only the label, made yesterday, and gives today as the day.
const labelOnly: ParcelWithEvents = {
  id: 'parcel', carrier: 'planzer', trackingNumber: 'TEST00000001', label: 'Winter coat', createdAt: '2026-11-27T05:00:00Z',
  syncStatus: 'ok', expectedDelivery: '2026-11-27',
  events: [{ id: 'label', parcelId: 'parcel', stage: 'registered', description: 'Scan', occurredAt: new Date(2026, 10, 26, 13, 33).toISOString() }],
};

beforeEach(() => {
  vi.useFakeTimers({ toFake: ['Date'] });
  vi.setSystemTime(NOW);
});
afterEach(() => vi.useRealTimers());

describe('a card’s day', () => {
  it('says the day is expected, as a bare one reads as the day the parcel came', () => {
    render(<ParcelCard parcel={labelOnly} onOpen={vi.fn()} />);
    const card = screen.getByRole('button', { name: /Winter coat/ });
    expect(card.querySelector('.parcel-card__top > .parcel-card__eta')).toHaveTextContent(/^expected today$/);
  });

  it('follows the stage on Next up', () => {
    render(<ParcelCard parcel={labelOnly} variant="hero" onOpen={vi.fn()} />);
    const summary = screen.getByRole('button', { name: /Winter coat/ }).querySelector('.parcel-card__summary');
    expect(summary?.querySelector('.parcel-card__eta')).toHaveTextContent(/^expected today$/);
    expect(summary?.querySelector('.parcel-card__state')).not.toHaveTextContent(/today/);
  });

  it('gives the stage alone once the day has passed with no scan', () => {
    vi.setSystemTime(new Date(2026, 10, 28, 9, 0));
    render(<ParcelCard parcel={labelOnly} variant="hero" onOpen={vi.fn()} />);
    const card = screen.getByRole('button', { name: /Winter coat/ });
    expect(card.querySelector('.parcel-card__eta')).toBeNull();
    expect(card).not.toHaveTextContent(/today|expected/);
  });
});

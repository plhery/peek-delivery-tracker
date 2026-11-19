import { render, screen } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import { toParcel } from '../store/apiRepo';
import type { ApiPackageRow } from '../generated/apiContract';
import type { ParcelWithEvents, TrackingEvent } from '../types';
import { ParcelCard } from './ParcelCard';
import { ParcelDetail } from './ParcelDetail';

const PAGE = 'https://colisprive.com/moncolis/pages/DetailColis.aspx?numColis=TESTCP00001&lang=fr';
const scan = (stage: TrackingEvent['stage'], day: number): TrackingEvent => ({
  id: `scan-${day}`, parcelId: 'parcel', stage, description: 'Scan', occurredAt: `2026-11-${day}T08:00:00Z`,
});
const parcel: ParcelWithEvents = {
  id: 'parcel', carrier: 'colis-prive', trackingNumber: 'TESTCP00001', label: 'Shoes', createdAt: '2026-11-17T12:00:00Z',
  syncStatus: 'error', syncError: 'carrier:transport', events: [scan('in_transit', 18)],
  carrierOutage: { carrier: 'colis-prive', since: '2026-11-19T12:20:41Z', trackingUrl: PAGE },
};
const detail = (values: Partial<ParcelWithEvents> = {}) => render(<ParcelDetail parcel={{ ...parcel, ...values }} onBack={vi.fn()}
  onRename={vi.fn()} onChangeCarrier={vi.fn()} onSetNotificationsMuted={vi.fn()} onRefresh={vi.fn()} onRestore={vi.fn()}
  onArchive={vi.fn()} onDelete={vi.fn()} />);

describe('a carrier whose site is down', () => {
  it('reads the outage the API serves', () => {
    const row = { id: 'parcel', tracking_number: 'TESTCP00001', carrier: 'colis-prive', created_at: '2026-11-17T12:00:00Z',
      sync_status: 'error', tracking_events: [], carrier_outage: { carrier: 'colis-prive', since: '2026-11-19T12:20:41Z', tracking_url: PAGE } };
    expect(toParcel(row as unknown as ApiPackageRow).carrierOutage).toEqual(parcel.carrierOutage);
    expect(toParcel({ ...row, carrier_outage: undefined } as unknown as ApiPackageRow)).not.toHaveProperty('carrierOutage');
  });

  it('says so calmly on the card, in place of the failed check', () => {
    const { container, unmount } = render(<ParcelCard parcel={parcel} onOpen={vi.fn()} />);
    expect(screen.getByText('Colis Privé’s tracking is having problems')).toHaveClass('parcel-card__notice--calm');
    expect(screen.queryByText('Couldn’t get the latest update')).not.toBeInTheDocument();
    // The card stays one button: the carrier's page is on the parcel's own page.
    expect(container.querySelector('a')).toBeNull();
    unmount();
    render(<ParcelCard parcel={{ ...parcel, carrierOutage: undefined }} onOpen={vi.fn()} />);
    expect(screen.getByText('Couldn’t get the latest update')).toBeInTheDocument();
  });

  it('keeps the card on what the carrier waits for from the reader', () => {
    render(<ParcelCard parcel={{ ...parcel, events: [scan('failed_attempt', 19)] }} notice="Check the carrier’s next steps" onOpen={vi.fn()} />);
    expect(screen.getByText('Check the carrier’s next steps')).toBeInTheDocument();
    expect(screen.queryByText(/tracking is having problems/)).not.toBeInTheDocument();
  });

  it('says so on the parcel\'s page with a link to the carrier\'s own page, promising no time', () => {
    detail();
    expect(screen.getByRole('status')).toHaveTextContent(
      'Colis Privé’s tracking is having problems right now. Your tracking will update as soon as it’s back.');
    const link = screen.getByRole('link', { name: 'Check on Colis Privé’s site' });
    expect(link).toHaveAttribute('href', PAGE);
    expect(link).toHaveAttribute('target', '_blank');
    expect(screen.queryByText(/couldn’t be reached|Couldn’t get/i)).not.toBeInTheDocument();
  });

  it('shows no link when the API serves none, and nothing once the parcel is delivered', () => {
    const { unmount } = detail({ carrierOutage: { carrier: 'colis-prive', since: '2026-11-19T12:20:41Z' } });
    expect(screen.getByText(/tracking is having problems right now/)).toBeInTheDocument();
    expect(screen.queryByRole('link', { name: /Check on/ })).not.toBeInTheDocument();
    unmount();
    detail({ events: [scan('delivered', 19), scan('in_transit', 18)], syncStatus: 'ok', syncError: undefined });
    expect(screen.queryByText(/tracking is having problems/)).not.toBeInTheDocument();
  });
});

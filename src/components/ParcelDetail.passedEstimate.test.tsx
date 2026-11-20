import { render, screen } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import fr from '../../shared/locales/fr.json';
import { I18nProvider } from '../i18n';
import type { ParcelWithEvents } from '../types';
import { ParcelCard } from './ParcelCard';
import { ParcelDetail } from './ParcelDetail';

const DAY = 86_400_000;
const isoDay = (date: Date) => `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
/** Three days ago, as the server serves a passed day: 17 November 2026 when the test ran on the 20th. */
const passed = new Date(Date.now() - 3 * DAY);
const parcel: ParcelWithEvents = {
  id: 'parcel', carrier: 'swiss-post', trackingNumber: '993412345699999980', label: 'Books', createdAt: '2026-09-10T12:00:00Z',
  syncStatus: 'ok', expectedDeliveryPassed: isoDay(passed),
  events: [{ id: 'scan', parcelId: 'parcel', stage: 'in_transit', description: 'Sorted', occurredAt: new Date(Date.now() - 4 * DAY).toISOString() }],
};
const day = new Intl.DateTimeFormat('en', { weekday: 'short', day: 'numeric', month: 'short' }).formatToParts(passed);
const named = (type: string) => day.find((part) => part.type === type)!.value;

function show(values: Partial<ParcelWithEvents> = {}) {
  return render(<ParcelDetail parcel={{ ...parcel, ...values }} onBack={vi.fn()} onRename={vi.fn()} onChangeCarrier={vi.fn()}
    onSetNotificationsMuted={vi.fn()} onRefresh={vi.fn()} onRestore={vi.fn()} onArchive={vi.fn()} onDelete={vi.fn()} />);
}

describe('an expected day that passed', () => {
  it('is said, muted, on the parcel page, in place of the estimate', () => {
    show();
    const line = screen.getByText(`Was expected ${named('weekday')} ${named('day')} ${named('month')}`);
    expect(line).toHaveClass('detail__arrival', 'detail__arrival--passed');
  });

  it('gives way to the estimate the carrier names again, and is not said of an archived parcel', () => {
    const view = show({ expectedDelivery: isoDay(new Date(Date.now() + 2 * DAY)) });
    expect(screen.queryByText(/^Was expected/)).toBeNull();
    view.unmount();
    show({ archivedAt: new Date().toISOString() });
    expect(screen.queryByText(/^Was expected/)).toBeNull();
  });

  it('is left off the parcel’s card', () => {
    render(<ParcelCard parcel={parcel} onOpen={vi.fn()} />);
    expect(screen.queryByText(/^Was expected/)).toBeNull();
  });

  it('is written in the reader’s language and date format', () => {
    render(<I18nProvider initialLocale="fr" initialMessages={fr}>
      <ParcelDetail parcel={parcel} onBack={vi.fn()} onRename={vi.fn()} onChangeCarrier={vi.fn()} onSetNotificationsMuted={vi.fn()}
        onRefresh={vi.fn()} onRestore={vi.fn()} onArchive={vi.fn()} onDelete={vi.fn()} />
    </I18nProvider>);
    const parts = new Intl.DateTimeFormat('fr', { weekday: 'short', day: 'numeric', month: 'short' }).formatToParts(passed);
    const part = (type: string) => parts.find((entry) => entry.type === type)!.value.replaceAll('.', '');
    expect(screen.getByText(`Devait arriver le ${part('weekday')} ${part('day')} ${part('month')}`)).toBeInTheDocument();
  });
});

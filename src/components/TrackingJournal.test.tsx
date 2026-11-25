import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { act } from 'react';
import { hydrateRoot } from 'react-dom/client';
import { renderToString } from 'react-dom/server';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { timeZoneName } from '../lib/format';
import { TrackingJournal, plannedAhead } from './TrackingJournal';
import type { TrackingEvent } from '../types';

it('keeps a day-only plan until the end of a 25-hour carrier day', () => {
  expect(plannedAhead({ day: '2026-10-25', occurredAt: '2026-10-24T22:00:00Z', expiresAt: '2026-10-25T23:00:00Z' },
    Date.parse('2026-10-25T22:30:00Z'))).toBe(true);
});

function event(id: string, occurredAt: string, description: string, location?: string): TrackingEvent {
  return { id, parcelId: 'parcel', stage: 'in_transit', occurredAt, description, location };
}

// Planned steps show while their time is ahead: these are read in the year before their journeys.
const beforeTheJourneys = () => { vi.useFakeTimers({ toFake: ['Date'] }); vi.setSystemTime(new Date('2023-12-01T00:00:00Z')); };

describe('dates in full on a desktop', () => {
  afterEach(() => { vi.unstubAllGlobals(); });
  const pointer = (hovers: boolean) => vi.stubGlobal('matchMedia', (query: string) =>
    ({ matches: hovers && query === '(hover: hover) and (pointer: fine)', addEventListener() {}, removeEventListener() {} }));
  const journal = () => render(<TrackingJournal events={[event('scan', '2024-11-28T14:32:00', 'With courier')]} />);

  it('names each day, and each scan\'s day and time, in full on hovering them', () => {
    pointer(true);
    journal();
    expect(screen.getByRole('heading', { name: 'Thu 28 Nov 2024' })).toHaveAttribute('title', 'Thursday, 28 November 2024');
    expect(screen.getByText('14:32')).toHaveAttribute('title', 'Thursday, 28 November 2024, 14:32');
  });

  it('names none on touch, where nothing hovers', () => {
    pointer(false);
    journal();
    expect(screen.getByRole('heading', { name: 'Thu 28 Nov 2024' })).not.toHaveAttribute('title');
    expect(screen.getByText('14:32')).not.toHaveAttribute('title');
  });
});

describe('TrackingJournal', () => {
  afterEach(() => { vi.useRealTimers(); });

  it('keeps all scans in newest-first calendar-day groups, including older years', () => {
    const { container } = render(<TrackingJournal events={[
      event('first', '2024-09-07T10:00:00', 'Collected', 'Germany'),
      event('third', '2024-09-08T22:15:00', 'Departed sorting center'),
      event('second', '2024-09-08T06:20:00', 'Arrived at sorting center'),
      event('fourth', '2024-09-09T08:42:00', 'With courier'),
    ]} />);
    expect(container.querySelector('details')).toHaveAttribute('open');
    expect(screen.getByText('4 updates')).toBeInTheDocument();
    const groups = within(screen.getByRole('list', { name: 'Tracking history' })).getAllByRole('heading');
    expect(groups.map(group => group.textContent)).toEqual(['Mon 9 Sep 2024', 'Sun 8 Sep 2024', 'Sat 7 Sep 2024']);
    expect([...container.querySelectorAll('.tracking-journal__events p')].map(el => el.textContent))
      .toEqual(['With courier', 'Departed sorting center', 'Arrived at sorting center', 'Collected']);
    expect(container.querySelector('.tracking-journal__location')).toHaveTextContent(/^🇩🇪Germany$/u);
    expect(screen.getByText('08:42')).toHaveAttribute('dateTime', '2024-09-09T08:42:00');
  });

  it('folds the steps the carrier planned behind one line that says they’re only predictions, newest first, without counting them', async () => {
    beforeTheJourneys();
    const { container } = render(<TrackingJournal events={[event('picked', '2024-09-07T10:00:00', 'Picked up', 'Shenzhen, China')]} planned={[
      { stage: 'in_transit', description: 'Departed the origin facility', occurredAt: '2024-09-08T09:00:00', location: 'Shenzhen, China' },
      { stage: 'delivered', description: 'Delivered', occurredAt: '2024-09-15T22:00:00.000Z', day: '2024-09-16' },
    ]} />);
    expect(screen.getByText('1 update')).toBeInTheDocument();
    const predicted = container.querySelector<HTMLDetailsElement>('.tracking-journal__predicted')!;
    expect(predicted).not.toHaveAttribute('open');
    const line = within(predicted).getByText('Other sites may show more steps, but they’re only predictions.').closest('summary')!;
    expect(line).toHaveTextContent(/only predictions\. Show themHide them$/);
    expect([...predicted.querySelectorAll('li p')].map(step => step.textContent)).toEqual(['Delivered', 'Departed the origin facility']);
    expect([...predicted.querySelectorAll('time')].map(time => [time.getAttribute('dateTime'), time.textContent]))
      .toEqual([['2024-09-16', 'Predicted for Mon 16 Sep 2024'], ['2024-09-08T09:00:00', 'Predicted for Sun 8 Sep 2024, 09:00']]);
    expect(predicted.querySelector('.tracking-journal__location')).toHaveTextContent(/^🇨🇳Shenzhen, China$/u);
    expect(container.querySelectorAll('.tracking-journal__predicted-mark')).toHaveLength(2);
    // It opens in place, and sits above the scans, which keep their own list and day groups.
    vi.useRealTimers();
    await userEvent.setup().click(line);
    expect(predicted).toHaveAttribute('open');
    const history = screen.getByRole('list', { name: 'Tracking history' });
    expect(predicted.compareDocumentPosition(history) & Node.DOCUMENT_POSITION_FOLLOWING).toBeTruthy();
    expect(within(history).getByText('Picked up')).toBeInTheDocument();
    expect(within(history).queryByText(/Departed|Delivered/)).not.toBeInTheDocument();
  });

  it('keeps a planned step only while its time is ahead, one dated by its day alone through that day', () => {
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(new Date('2024-09-08T10:00:00Z'));
    const picked = [event('picked', '2024-09-07T10:00:00Z', 'Picked up')];
    const plan = [
      { stage: 'in_transit' as const, description: 'Departed the origin facility', occurredAt: '2024-09-08T09:00:00Z' },
      { stage: 'customs' as const, description: 'Customs cleared', occurredAt: '2024-09-06T22:00:00.000Z', day: '2024-09-07' },
      { stage: 'out_for_delivery' as const, description: 'Out for delivery', occurredAt: '2024-09-07T22:00:00.000Z', day: '2024-09-08' },
      { stage: 'delivered' as const, description: 'Delivered', occurredAt: '2024-09-08T15:00:00Z' },
    ];
    const { container, rerender } = render(<TrackingJournal events={picked} planned={plan} />);
    expect([...container.querySelectorAll('.tracking-journal__predicted li p')].map(line => line.textContent))
      .toEqual(['Delivered', 'Out for delivery']);
    // Once the carrier's pre-written "Delivered" is due, nothing planned is left to show.
    vi.setSystemTime(new Date('2024-09-08T22:00:00Z'));
    rerender(<TrackingJournal events={picked} planned={plan} />);
    expect(container.querySelector('.tracking-journal__predicted')).toBeNull();
    expect(screen.queryByText(/only predictions/)).not.toBeInTheDocument();
  });

  it('folds the steps a carrier wrote in advance whose time has passed among the predictions, a delivery among them, without counting them', () => {
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(new Date('2024-09-20T00:00:00Z'));
    const { container } = render(<TrackingJournal events={[]} planned={[
      { stage: 'in_transit', description: 'Proof of delivery filed', occurredAt: '2024-09-25T02:00:00Z' },
    ]} written={[
      { stage: 'in_transit', description: 'Departed the origin facility', occurredAt: '2024-09-08T01:00:00Z', location: 'Guangzhou, China' },
      { stage: 'delivered', description: 'Delivered', occurredAt: '2024-09-15T07:00:00Z' },
    ]} writtenAheadBy="GZYL" />);
    expect(screen.getByText('0 updates')).toBeInTheDocument();
    expect(screen.getByText('No tracking updates yet.')).toBeInTheDocument();
    const predicted = container.querySelector('.tracking-journal__predicted')!;
    // The line names the carrier and says its site lists the whole route in advance.
    expect(predicted.querySelector('summary')).toHaveTextContent(
      /^GZYL’s site lists the whole route in advance, delivery included\. Those steps aren’t real scans\. Show themHide them$/u);
    // Those still ahead and those gone by are one list, newest first: the day each was predicted for, or written in advance
    // for once it has passed.
    expect([...predicted.querySelectorAll('li p')].map(line => line.textContent))
      .toEqual(['Proof of delivery filed', 'Delivered', 'Departed the origin facility']);
    expect([...predicted.querySelectorAll('time')].map(time => time.textContent)).toEqual([
      expect.stringMatching(/^Predicted for Wed 25 Sep, \d\d:\d\d$/u),
      expect.stringMatching(/^Written in advance for Sun 15 Sep, \d\d:\d\d$/u),
      expect.stringMatching(/^Written in advance for Sun 8 Sep, \d\d:\d\d$/u),
    ]);
    expect(predicted.querySelector('.tracking-journal__location')).toHaveTextContent(/^🇨🇳Guangzhou, China$/u);
    expect(screen.queryByText(/Nothing confirms|only predictions/i)).not.toBeInTheDocument();
    expect(screen.queryByRole('list', { name: 'Tracking history' })).not.toBeInTheDocument();
  });

  it('says a carrier that writes its route ahead lists the whole route on its site, even with none to list, and says nothing for another carrier', () => {
    const scans = [event('picked', '2024-09-07T10:00:00Z', 'Picked up')];
    const { container, rerender } = render(<TrackingJournal events={scans} writtenAheadBy="Xinghe Freight" />);
    expect(screen.getByText('Xinghe Freight’s site lists the whole route in advance, delivery included. Those steps aren’t real scans.'))
      .toHaveClass('tracking-journal__predicted-note');
    expect(container.querySelector('.tracking-journal__predicted')).toBeNull();
    rerender(<TrackingJournal events={scans} />);
    expect(screen.queryByText(/whole route in advance|only predictions/)).not.toBeInTheDocument();
  });

  it('says under the history that its times are the reader’s, and names the reader’s zone', () => {
    const { container } = render(<TrackingJournal events={[event('scan', '2024-09-09T08:42:00', 'With courier', 'Helsinki, FI')]} />);
    const line = container.querySelector('.tracking-journal__zone');
    expect(line).toHaveTextContent(`Times are in your time zone: ${timeZoneName('en-CH')}.`);
    expect(container.querySelector('details')!.lastElementChild).toBe(line);
  });

  it('names no zone where no time of day is shown', () => {
    const { container, rerender } = render(<TrackingJournal events={[{ ...event('day', '2024-09-08T22:00:00Z', 'Delivered'), day: '2024-09-09' }]} />);
    expect(container.querySelector('.tracking-journal__zone')).toBeNull();
    rerender(<TrackingJournal events={[]} />);
    expect(container.querySelector('.tracking-journal__zone')).toBeNull();
  });

  it('names no zone on the server, which knows only its own, and the reader’s once the page comes alive', async () => {
    const journal = <TrackingJournal events={[event('scan', '2024-09-09T08:42:00', 'With courier')]} />;
    const html = renderToString(journal);
    expect(html).toContain('With courier');
    expect(html).not.toContain('tracking-journal__zone');
    const host = document.createElement('div');
    host.innerHTML = html;
    document.body.append(host);
    const recovered = vi.fn();
    await act(async () => { hydrateRoot(host, journal, { onRecoverableError: recovered }); });
    expect(host.querySelector('.tracking-journal__zone')).toHaveTextContent(timeZoneName('en-CH'));
    expect(recovered).not.toHaveBeenCalled();
    host.remove();
  });

  it('leads each place with its flag and closes it with the country’s name', () => {
    const { container } = render(<TrackingJournal events={[
      event('city', '2024-09-09T10:00:00', 'With courier', 'ZUERICH, CH'),
      event('country', '2024-09-09T09:00:00', 'Departed', 'FR'),
      event('leading', '2024-09-09T08:00:00', 'Handed over', 'Germany Bielefeld'),
      event('origin', '2024-09-09T07:30:00', 'Collected', 'Shenzhen, China'),
      event('facility', '2024-09-09T07:00:00', 'Sorted', 'Zürich Briefzentrum'),
    ]} />);
    const places = [...container.querySelectorAll('.tracking-journal__location')];
    expect(places.map(place => place.textContent)).toEqual([
      '🇨🇭ZUERICH, Switzerland', '🇫🇷France', '🇩🇪Bielefeld, Germany', '🇨🇳Shenzhen, China', 'Zürich Briefzentrum',
    ]);
    const flag = places[0].firstElementChild!;
    // A code that is also a state or canton names the country the scan was placed in, and only that one.
    const placed = (country: string) => ({ latitude: 0, longitude: 0, precision: 'city' as const, country, name: 'Town' });
    const { container: coded } = render(<TrackingJournal events={[
      { ...event('placed', '2024-09-09T10:00:00', 'Sorted', 'Haiger, DE'), place: placed('DE') },
      { ...event('bracket', '2024-09-09T09:00:00', 'Sorted', 'Haiger (DE)'), place: placed('DE') },
      { ...event('state', '2024-09-09T08:00:00', 'Sorted', 'Wilmington, DE'), place: placed('US') },
      event('unplaced', '2024-09-09T07:00:00', 'Sorted', 'Hannover, DE'),
      { ...event('texas', '2024-09-09T06:00:00', 'Sorted', 'Austin, TX'), place: placed('US') },
      { ...event('california', '2024-09-09T05:00:00', 'Sorted', 'Los Angeles, CA'), place: placed('US') },
      { ...event('canada', '2024-09-09T04:00:00', 'Sorted', 'Toronto, CA'), place: placed('CA') },
    ]} />);
    expect([...coded.querySelectorAll('.tracking-journal__location')].map(place => place.textContent)).toEqual([
      '🇩🇪Haiger, Germany', '🇩🇪Haiger, Germany', 'Wilmington, DE', 'Hannover, DE', 'Austin, TX', 'Los Angeles, CA', '🇨🇦Toronto, Canada',
    ]);
    expect(flag).toHaveClass('tracking-journal__flag');
    expect(flag).toHaveAttribute('aria-hidden', 'true');
  });

  it('names a place written in Chinese by the town it was placed in, keeping it as written where it was placed nowhere', () => {
    beforeTheJourneys();
    const { container } = render(<TrackingJournal events={[
      { ...event('xiamen', '2024-09-09T10:00:00', 'Departed', '厦门'), place: { latitude: 24.48, longitude: 118.09, precision: 'city', country: 'CN', name: 'Xiamen' } },
      event('unplaced', '2024-09-09T09:00:00', 'Accepted', '台北'),
    ]} planned={[
      { stage: 'in_transit', description: 'Departed', occurredAt: '2024-09-12T09:00:00', location: '广州市',
        place: { latitude: 23.13, longitude: 113.26, precision: 'city', country: 'CN', name: 'Guangzhou' } },
      { stage: 'in_transit', description: 'Arrived', occurredAt: '2024-09-11T09:00:00', location: '台北' },
    ]} />);
    expect([...container.querySelectorAll('.tracking-journal__location')].map(place => place.textContent))
      .toEqual(['🇨🇳Guangzhou, China', '台北', '🇨🇳Xiamen, China', '台北']);
  });

  it('shows an earlier carrier\'s relay copy as a line under the scan it repeats, keeping its words', () => {
    const { container } = render(<TrackingJournal events={[
      event('arrived', '2024-09-09T14:16:51', 'Arrival in destination country'),
      { ...event('copy', '2024-09-09T14:16:00', 'The shipment has arrived in the destination country'), relayOf: 'arrived' },
      // A scan of its own taken for a copy is still there to read.
      event('sorted', '2024-09-09T15:40:38', 'Sorted'),
      { ...event('loaded', '2024-09-09T15:40:00', 'Loaded onto a vehicle'), relayOf: 'sorted' },
      // Told after the scan in the same words: nothing more to show, and the scan stays the current step.
      { ...event('delivered', '2024-09-09T18:00:00', 'Delivered'), stage: 'delivered' },
      { ...event('delivered-copy', '2024-09-09T18:00:30', 'Delivered'), stage: 'delivered', relayOf: 'delivered' },
      // The same words composed another way (an e and its accent apart).
      { ...event('sorted-copy', '2024-09-09T15:40:10', 'Sorted, re\u0301expe\u0301die\u0301'), relayOf: 'sorted' },
      { ...event('sorted-again', '2024-09-09T15:40:20', 'Sorted, r\u00e9exp\u00e9di\u00e9'), relayOf: 'sorted' },
      // Without the scan it repeats, a copy keeps its row.
      { ...event('orphan', '2024-09-09T10:00:00', 'Handed over'), relayOf: 'not-served' },
    ]} />);
    expect(screen.getByText('4 updates')).toBeInTheDocument();
    const rows = [...container.querySelectorAll('.tracking-journal__events li')];
    expect(rows.map(row => [...row.querySelectorAll('p')].map(line => line.textContent))).toEqual([
      ['Delivered'], ['Sorted', 'Loaded onto a vehicle', 'Sorted, re\u0301expe\u0301die\u0301'], ['Arrival in destination country', 'The shipment has arrived in the destination country'], ['Handed over'],
    ]);
    expect([...container.querySelectorAll('.tracking-journal__relay')].map(line => line.textContent))
      .toEqual(['Loaded onto a vehicle', 'Sorted, re\u0301expe\u0301die\u0301', 'The shipment has arrived in the destination country']);
    expect(rows[0]).toHaveAttribute('aria-current', 'step');
  });

  it('lists a scan dated by its day alone under that day, with no time', () => {
    const { container } = render(<TrackingJournal events={[
      event('sorted', '2024-09-07T22:30:00', 'Sorted'),
      // West of the carrier's zone, the start of its day is the evening before.
      { ...event('delivered', '2024-09-07T22:00:00', 'Delivered'), day: '2024-09-08' },
    ]} />);
    const groups = within(screen.getByRole('list', { name: 'Tracking history' })).getAllByRole('heading');
    expect(groups.map(group => group.textContent)).toEqual(['Sun 8 Sep 2024', 'Sat 7 Sep 2024']);
    const times = [...container.querySelectorAll('.tracking-journal__events time')];
    expect(times.map(time => [time.getAttribute('datetime'), time.textContent])).toEqual([['2024-09-08', ''], ['2024-09-07T22:30:00', '22:30']]);
  });

  it('shows no time for a status Peek saw at a check, listed under the check\'s day', () => {
    const { container } = render(<TrackingJournal events={[
      // The carrier gave no time: the row's time is the check's.
      { ...event('seen', '2024-09-08T14:05:00', 'Inbound into customs'), observed: true },
      event('sorted', '2024-09-07T22:30:00', 'Sorted'),
    ]} />);
    const groups = within(screen.getByRole('list', { name: 'Tracking history' })).getAllByRole('heading');
    expect(groups.map(group => group.textContent)).toEqual(['Sun 8 Sep 2024', 'Sat 7 Sep 2024']);
    const times = [...container.querySelectorAll('.tracking-journal__events time')];
    expect(times.map(time => time.textContent)).toEqual(['', '22:30']);
  });

  it('handles missing descriptions, invalid scan times and empty syncing history', () => {
    const { rerender } = render(<TrackingJournal events={[event('invalid', 'Unknown date', '')]} />);
    expect(screen.getByText('Unknown date')).toBeInTheDocument();
    expect(screen.getByText('In transit')).toBeInTheDocument();
    expect(screen.getByText('1 update')).toBeInTheDocument();
    rerender(<TrackingJournal events={[]} syncing />);
    expect(screen.getByText('0 updates')).toBeInTheDocument();
    expect(screen.queryByRole('list')).not.toBeInTheDocument();
    expect(screen.getByText(/checking|first update|first tracking/i)).toBeInTheDocument();
  });

  it('folds a long journey: the two newest days open, three more as one line each, the oldest behind a button', async () => {
    const day = (number: number, count: number) => Array.from({ length: count }, (_, index) =>
      event(`d${number}-${index}`, `2024-09-${String(number).padStart(2, '0')}T${String(8 + index).padStart(2, '0')}:00:00`, `Scan ${number}.${index}`));
    const events = [...day(9, 1), ...day(8, 2), ...day(7, 3), ...day(6, 4), ...day(5, 2), ...day(4, 2), ...day(3, 1)];
    const { container, rerender } = render(<TrackingJournal events={events} fold />);
    expect(screen.getByText('15 updates')).toBeInTheDocument();
    const folded = [...container.querySelectorAll<HTMLDetailsElement>('.tracking-journal__day')];
    expect(folded.map((entry) => entry.querySelector('summary')!.textContent)).toEqual(['Sat 7 Sep 20243 updates', 'Fri 6 Sep 20244 updates', 'Thu 5 Sep 20242 updates']);
    expect(folded.every((entry) => !entry.open)).toBe(true);
    // The open days show their scans; the oldest are not in the page yet.
    expect(screen.getByText('Scan 9.0')).toBeVisible();
    expect(screen.getByText('Scan 8.1')).toBeVisible();
    expect(screen.queryByText('Scan 4.0')).not.toBeInTheDocument();
    const user = userEvent.setup();
    await user.click(folded[0].querySelector('summary')!);
    expect(folded[0].open).toBe(true);
    await user.click(screen.getByRole('button', { name: 'Show the 3 oldest updates' }));
    expect(screen.getByText('Scan 4.0')).toBeVisible();
    expect(screen.getByText('Scan 3.0')).toBeVisible();
    expect(screen.queryByRole('button', { name: /oldest/ })).not.toBeInTheDocument();
    // A short journey, and a journal that is not asked to fold, show everything.
    rerender(<TrackingJournal events={events.slice(0, 12)} fold />);
    expect(container.querySelector('.tracking-journal__day')).toBeNull();
    rerender(<TrackingJournal events={events} />);
    expect(container.querySelector('.tracking-journal__day')).toBeNull();
    expect(screen.getByText('Scan 3.0')).toBeVisible();
  });
});

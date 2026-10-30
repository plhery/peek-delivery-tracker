import { describe, expect, it } from 'vitest';
import { datedAfterCheck, insteadOfAheadLine, withoutAheadWatermark, withoutFutureSummary } from './futureScans';

describe('scans dated after the check', () => {
  const now = new Date('2026-10-30T13:21:00Z');

  it('allows a clock up to an hour fast', () => {
    expect(datedAfterCheck('2026-10-30T14:15:00Z', 'UTC', now)).toBe(false);
    expect(datedAfterCheck('2026-10-30T19:16:41+01:00', 'UTC', now)).toBe(true);
    expect(datedAfterCheck('not a time', 'UTC', now)).toBe(false);
  });

  it('takes the summary from the newest scan before the check, and leaves a summary no future scan gave', () => {
    const notice = { time: '2026-10-30T19:16:41+01:00', description: 'Synthetic notice', stage: 'accepted' };
    const deposit = { time: '2026-10-30T10:56:57+01:00', description: 'Synthetic deposit', stage: 'in_transit' };
    expect(withoutFutureSummary({
      status: 'pending', current_stage: 'accepted', last_status_text: notice.description, last_update: notice.time,
      events: [notice, deposit],
    }, 'swiss-post', now)).toMatchObject({
      status: 'in_transit', current_stage: 'in_transit', last_status_text: deposit.description, last_update: deposit.time,
      events: [notice, deposit],
    });
    const current = { status: 'in_transit' as const, current_stage: 'in_transit', last_status_text: deposit.description,
      last_update: deposit.time, events: [notice, deposit] };
    expect(withoutFutureSummary(current, 'swiss-post', now)).toBe(current);
    // Once its time has passed, the row stored hours before it still keeps the notice out.
    const later = new Date('2026-10-30T20:00:00Z');
    const answer = { status: 'pending' as const, current_stage: 'accepted', last_status_text: notice.description,
      last_update: notice.time, events: [notice, deposit] };
    expect(withoutFutureSummary(answer, 'swiss-post', later)).toBe(answer);
    expect(withoutFutureSummary(answer, 'swiss-post', later, [{ time: notice.time, description: notice.description,
      occurred_at: '2026-10-30T18:16:41Z', created_at: '2026-10-30T13:21:00Z' }])).toMatchObject({
      current_stage: 'in_transit', last_status_text: deposit.description,
    });
    // With no scan before the check, the result stays as it is.
    const onlyAhead = { status: 'pending' as const, last_status_text: notice.description, last_update: notice.time, events: [notice] };
    expect(withoutFutureSummary(onlyAhead, 'swiss-post', now)).toBe(onlyAhead);
  });

  it('brings a watermark a scan dated ahead set back to the newest other scan', () => {
    const at = (time: string) => Date.parse(time);
    const notice = { provider_event_id: 'swiss-post:notice', occurred_at: '2026-10-30T18:16:41Z', description: 'Synthetic notice',
      raw_data: { time: '2026-10-30T19:16:41+01:00' } };
    const deposit = { provider_event_id: 'swiss-post:deposit', occurred_at: '2026-10-30T09:56:57Z', description: 'Synthetic deposit' };
    const departure = { provider_event_id: 'aliexpress:departure', occurred_at: '2026-10-30T10:53:06Z', description: 'Synthetic departure',
      time: '2026-10-30T10:53:06Z', created_at: '2026-10-30T11:00:00Z' };
    // Capped at the check, the scan is still ahead: the watermark is the newest scan before it, stored or answered.
    expect(withoutAheadWatermark(now.getTime(), [notice, deposit], [departure], now)).toBe(at(departure.occurred_at));
    // Once its time has passed, the row stored hours before it is still no scan to count.
    const later = new Date('2026-10-30T20:50:00Z');
    const storedNotice = { ...notice, time: notice.raw_data.time, created_at: '2026-10-30T13:21:00Z' };
    expect(withoutAheadWatermark(at(notice.occurred_at), [notice, deposit], [storedNotice, departure], later)).toBe(at(departure.occurred_at));
    // A watermark a real scan set stands, and so does one with no scan ahead or no other scan.
    expect(withoutAheadWatermark(at(departure.occurred_at), [notice, deposit], [departure], now)).toBe(at(departure.occurred_at));
    expect(withoutAheadWatermark(now.getTime(), [deposit], [departure], now)).toBe(now.getTime());
    expect(withoutAheadWatermark(now.getTime(), [notice], [], now)).toBe(now.getTime());
    // Peek's own rows and sightings are no scans.
    expect(withoutAheadWatermark(now.getTime(), [notice, { provider_event_id: 'app:added', occurred_at: '2026-10-30T13:00:00Z' }], [departure], now))
      .toBe(at(departure.occurred_at));
  });

  it('gives a status line only scans dated ahead tell to the newest other stored scan', () => {
    const later = new Date('2026-10-30T22:50:00Z');
    const notice = { provider_event_id: 'swiss-post:notice', occurred_at: '2026-10-30T18:16:41+00:00', description: 'Synthetic notice',
      time: '2026-10-30T19:16:41+01:00', created_at: '2026-10-30T13:21:00Z' };
    const deposit = { provider_event_id: 'swiss-post:deposit', occurred_at: '2026-10-30T09:56:57+00:00', description: 'Synthetic deposit',
      time: '2026-10-30T09:56:57Z', created_at: '2026-10-30T12:01:00Z' };
    const departure = { provider_event_id: 'aliexpress:departure', occurred_at: '2026-10-30T10:53:06+00:00', description: 'Synthetic departure',
      time: '2026-10-30T10:53:06Z', created_at: '2026-10-30T11:00:00Z' };
    expect(insteadOfAheadLine(notice.description, [notice, deposit, departure], later)).toBe(departure);
    // A line a real scan tells too, another scan's line, and a parcel with no other scan keep theirs.
    expect(insteadOfAheadLine(notice.description, [notice, { ...deposit, description: notice.description }, departure], later)).toBeNull();
    expect(insteadOfAheadLine(deposit.description, [notice, deposit, departure], later)).toBeNull();
    expect(insteadOfAheadLine(notice.description, [notice], later)).toBeNull();
  });
});

import { describe, expect, it } from 'vitest';
import { datedAfterCheck, withoutFutureSummary } from './futureScans';

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
});

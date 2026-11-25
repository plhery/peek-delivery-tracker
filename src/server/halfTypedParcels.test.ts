import { afterEach, describe, expect, it, vi } from 'vitest';
import { HALF_TYPED_MS, replaceHalfTyped } from './halfTypedParcels';
import * as observability from './observability';
import { SupabaseError, SupabaseServiceClient } from './supabase';

// Synthetic identifiers and numbers only.
const user = '56000000-0000-4000-a000-000000000001';
const otherUser = '56000000-0000-4000-a000-000000000002';
const phone = '56000000-0000-4000-b000-000000000001';
const laptop = '56000000-0000-4000-b000-000000000002';
const now = new Date('2026-11-24T12:00:00Z');
const minutesAgo = (minutes: number) => new Date(now.getTime() - minutes * 60_000).toISOString();

interface Row {
  id: string;
  user_id: string;
  tracking_number: string;
  label: string;
  archived_at: string | null;
  current_stage: string;
  created_at: string;
  added_session: string | null;
  tracking_events: { stage: string }[];
  package_notes?: { package_id: string } | null;
  package_files?: { id: string }[];
}

const row = (id: string, values: Partial<Row> = {}): Row => ({
  id, user_id: user, tracking_number: 'TEST1234', label: '', archived_at: null, current_stage: 'pending',
  created_at: minutesAgo(2), added_session: phone, tracking_events: [{ stage: 'pending' }], ...values,
});

/** The packages table as PostgREST filters it, for the three requests the replacement makes. */
function table(rows: Row[]) {
  const service = new SupabaseServiceClient('https://database.example', 'service-key');
  const matches = (candidate: Row, params: URLSearchParams) => [...params].every(([key, filter]) => {
    if (key === 'select') return true;
    const value = candidate[key as keyof Row];
    if (filter === 'is.null') return value === null;
    if (filter.startsWith('gte.')) return Date.parse(String(value)) >= Date.parse(filter.slice(4));
    if (filter.startsWith('eq.')) return String(value ?? '') === filter.slice(3);
    throw new Error(`Unexpected filter ${key}=${filter}`);
  });
  const request = vi.spyOn(service, 'request').mockImplementation(async (path, options = {}) => {
    const url = new URL(path, 'https://database.example');
    expect(url.pathname).toBe('/rest/v1/packages');
    const found = rows.filter((candidate) => matches(candidate, url.searchParams));
    if (options.method === 'PATCH') {
      found.forEach((candidate) => Object.assign(candidate, options.body));
      return null;
    }
    if (options.method === 'DELETE') {
      found.forEach((candidate) => rows.splice(rows.indexOf(candidate), 1));
      return found.map(({ id }) => ({ id }));
    }
    return found.map((candidate) => ({ ...candidate }));
  });
  return { service, request, ids: () => rows.map(({ id }) => id) };
}

describe('an account parcel added from Add', () => {
  afterEach(() => { vi.restoreAllMocks(); });

  it('takes the place of its number\'s start added from the same sign-in minutes before', async () => {
    const { service, ids } = table([row('start'), row('whole', { tracking_number: 'TEST12345678', created_at: now.toISOString(), added_session: null })]);
    await expect(replaceHalfTyped(service, user, phone, { id: 'whole', trackingNumber: 'TEST12345678' }, now))
      .resolves.toEqual(['start']);
    expect(ids()).toEqual(['whole']);
  });

  it('notes the sign-in it came from, so the next add from it can replace it', async () => {
    const rows = [row('whole', { tracking_number: 'TEST12345678', created_at: now.toISOString(), added_session: null })];
    const { service } = table(rows);
    await expect(replaceHalfTyped(service, user, phone, { id: 'whole', trackingNumber: 'TEST12345678' }, now)).resolves.toEqual([]);
    expect(rows[0].added_session).toBe(phone);
  });

  it('compares the numbers as typed, spaces and case aside', async () => {
    const { service, ids } = table([row('start', { tracking_number: 'test 1234' }), row('whole', { tracking_number: 'TEST12345678', added_session: null })]);
    await expect(replaceHalfTyped(service, user, phone, { id: 'whole', trackingNumber: 'TEST 1234 5678' }, now))
      .resolves.toEqual(['start']);
    expect(ids()).toEqual(['whole']);
  });

  it.each([
    ['added more than 10 minutes before', row('start', { created_at: new Date(now.getTime() - HALF_TYPED_MS - 1).toISOString() })],
    ['added from another sign-in', row('start', { added_session: laptop })],
    ['added another way, as from an email', row('start', { added_session: null })],
    ['named', row('start', { label: 'Shoes' })],
    ['archived', row('start', { archived_at: minutesAgo(1) })],
    ['with a note', row('start', { package_notes: { package_id: 'start' }, package_files: [] })],
    ['with a file on its note', row('start', { package_notes: null, package_files: [{ id: '56000000-0000-4000-c000-000000000001' }] })],
    ['with a scan', row('start', { current_stage: 'in_transit', tracking_events: [{ stage: 'pending' }, { stage: 'in_transit' }] })],
    ['with a label scan', row('start', { current_stage: 'registered', tracking_events: [{ stage: 'registered' }] })],
    ['with a number that is not the start of the new one', row('start', { tracking_number: 'TEST1299' })],
    ['with the same number', row('start', { tracking_number: 'TEST12345678' })],
    ['in another account', row('start', { user_id: otherUser })],
  ])('keeps a parcel %s', async (_case, earlier) => {
    const { service, ids } = table([earlier, row('whole', { tracking_number: 'TEST12345678', added_session: null })]);
    await expect(replaceHalfTyped(service, user, phone, { id: 'whole', trackingNumber: 'TEST12345678' }, now)).resolves.toEqual([]);
    expect(ids()).toEqual(['start', 'whole']);
  });

  it('replaces nothing for a token that names no sign-in', async () => {
    const { service, request, ids } = table([row('start'), row('whole', { tracking_number: 'TEST12345678', added_session: null })]);
    await expect(replaceHalfTyped(service, user, null, { id: 'whole', trackingNumber: 'TEST12345678' }, now)).resolves.toEqual([]);
    expect(request).not.toHaveBeenCalled();
    expect(ids()).toEqual(['start', 'whole']);
  });

  it('keeps an earlier parcel that got its first scan between the read and the delete', async () => {
    const rows = [row('start'), row('whole', { tracking_number: 'TEST12345678', added_session: null })];
    const { service, ids } = table(rows);
    const read = service.listAddedFromSession.bind(service);
    vi.spyOn(service, 'listAddedFromSession').mockImplementation(async (...args) => {
      const listed = await read(...args);
      rows[0].current_stage = 'in_transit';
      return listed;
    });
    await expect(replaceHalfTyped(service, user, phone, { id: 'whole', trackingNumber: 'TEST12345678' }, now)).resolves.toEqual([]);
    expect(ids()).toEqual(['start', 'whole']);
  });

  it('still adds the parcel when the database fails, and reports it', async () => {
    const { service, request, ids } = table([row('start'), row('whole', { tracking_number: 'TEST12345678', added_session: null })]);
    const failure = new SupabaseError('column unavailable', 400);
    request.mockRejectedValueOnce(failure);
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    await expect(replaceHalfTyped(service, user, phone, { id: 'whole', trackingNumber: 'TEST12345678' }, now)).resolves.toEqual([]);
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'packages', operation: 'replace_half_typed' });
    expect(ids()).toEqual(['start', 'whole']);
  });
});

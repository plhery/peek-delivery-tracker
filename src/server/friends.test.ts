import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as exportGET } from '../../app/api/account/export/route';
import { GET, POST } from '../../app/api/friends/route';
import { GET as activityGET } from '../../app/api/friends/activity/route';
import { SupabaseAuthenticator } from './auth';
import { SupabaseError, SupabaseServiceClient, SupabaseUserClient } from './supabase';
import { friendCard, friendsAction, friendsActionResponse, friendsSnapshot, friendsActivity } from './friends';
import * as shopWaits from './shopWaits';
import { withEventPlaces } from './eventPlaces';
import { sharedPassport } from '../lib/passport';
import { toParcel } from '../store/apiRepo';
import type { ApiPackageRow } from '../generated/apiContract';
import { postgrestTables } from '../test/postgrest';
import type { JsonObject } from './types';

const id = '11000000-0000-4000-8000-000000000001';
const card = { id, nickname: 'Mila', stats: { deliveredCount: 12, averageDays: 3, stamps: ['first', 'ten'] }, arrivedThisWeek: null };
const snapshot = { profile: { nickname: 'Alex', shareStats: true, shareArrival: false }, ownCard: card, friends: [card] };
/** The card and snapshot as the database gives them: the server works out the shared stats. */
const storedCard = { id, nickname: 'Mila', sharesStats: true, arrivedThisWeek: null };
const storedSnapshot = { ...snapshot, ownCard: storedCard, friends: [storedCard] };
const owner = '11000000-0000-4000-8000-0000000000aa';
/** A stored parcel row, as the database gives it to the server: each scan its stage, time and location. */
function stored(index: number, scans: Array<[string, string, string?]>) {
  const parcel = `12000000-0000-4000-8000-${String(index).padStart(12, '0')}`;
  return { id: parcel, tracking_number: `PRIVATE${index}`, label: 'Private purchase', carrier: 'swiss-post', created_at: '2026-08-01T00:00:00Z', sync_status: 'ok',
    tracking_events: scans.map(([stage, occurred_at, location], scan) => ({ id: `${parcel}-${scan}`, package_id: parcel, stage, description: stage, location: location ?? null, occurred_at, provider_event_id: `carrier:${index}:${scan}` })) };
}
/** Twelve three-day journeys arriving on different days: the card's 12 arrivals, 3 days and two stamps. */
const parcels = Array.from({ length: 12 }, (_, index) => stored(index, [['accepted', `2026-08-${10 + index}T10:00:00Z`, 'Private address'], ['delivered', `2026-08-${13 + index}T10:00:00Z`]]));
function call(body?: unknown, authenticated = true) {
  const request = new NextRequest('https://delivery.example/api/friends', { method: body ? 'POST' : 'GET', headers: authenticated ? { Authorization: 'Bearer friends-test-session' } : {}, ...(body ? { body: JSON.stringify(body) } : {}) });
  return (body ? POST : GET)(request, { params: Promise.resolve({}) });
}
beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example'); vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'public-key');
  vi.spyOn(console, 'log').mockImplementation(() => undefined); vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id, email: null, authenticatedAt: null, sessionId: null });
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service');
  vi.spyOn(SupabaseServiceClient.prototype, 'passportSharers').mockImplementation(async (ids) => new Map(ids.filter((profile) => profile === id).map((profile) => [profile, owner])));
  vi.spyOn(SupabaseServiceClient.prototype, 'passportParcels').mockImplementation(async (owners) => new Map(owners.map((account) => [account, parcels])));
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('Friends privacy boundary', () => {
  it('reads only whether a card shares stats, never stats the database gives', () => {
    expect(friendCard(storedCard)).toEqual(storedCard);
    expect(friendCard({ ...storedCard, sharesStats: false })).toEqual({ ...storedCard, sharesStats: false });
    expect(friendsSnapshot({ ...storedSnapshot, ownCard: { ...storedCard, stats: card.stats } })).toEqual(storedSnapshot);
  });

  it('works out a shared card with the app\'s Passport rules over its owner\'s parcels, and sends only its counts and stamps', async () => {
    const hidden = '11000000-0000-4000-8000-000000000002', stopped = '11000000-0000-4000-8000-000000000003';
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ ...storedSnapshot, friends: [storedCard, { ...storedCard, id: hidden, sharesStats: false }, { ...storedCard, id: stopped }] });
    const sharers = vi.mocked(SupabaseServiceClient.prototype.passportSharers);
    const read = vi.mocked(SupabaseServiceClient.prototype.passportParcels);
    const response = await call();
    expect(response.status).toBe(200);
    const body = await response.json();
    expect(body.ownCard).toEqual(card);
    expect(body.ownCard.stats).toEqual(sharedPassport(parcels.map((row) => toParcel(withEventPlaces(row) as unknown as ApiPackageRow))));
    // A card that shares nothing is not looked up; one whose owner stopped sharing in between loses its stats.
    expect(body.friends).toEqual([card, { ...card, id: hidden, stats: null }, { ...card, id: stopped, stats: null }]);
    expect(sharers.mock.calls).toEqual([[[id, id, stopped]], [[id], id]]);
    expect(read).toHaveBeenCalledExactlyOnceWith([owner]);
    expect(JSON.stringify(body)).not.toMatch(/PRIVATE|Private/);
    expect(JSON.stringify(body)).not.toContain(owner);
    // An accepted friend's card is worked out the same way.
    vi.mocked(SupabaseUserClient.prototype.request).mockResolvedValue({ snapshot: storedSnapshot, acceptedFriend: storedCard });
    expect(await (await call({ action: 'accept_invite', code: 'a'.repeat(32) })).json()).toEqual({ snapshot, acceptedFriend: card });
  });

  it('counts a code that is also a state toward a friend\'s stamps only where the server placed the scan', async () => {
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue(storedSnapshot);
    vi.mocked(SupabaseServiceClient.prototype.passportParcels).mockResolvedValue(new Map([[owner, [
      stored(0, [['accepted', '2026-08-04T10:00:00Z', 'Haiger, DE'], ['delivered', '2026-08-05T10:00:00Z', 'Koblenz, DE']]),
    ]]]));
    const served = toParcel(withEventPlaces(stored(0, [['accepted', '2026-08-04T10:00:00Z', 'Haiger, DE'], ['delivered', '2026-08-05T10:00:00Z', 'Koblenz, DE']])) as unknown as ApiPackageRow);
    expect(served.events.map((event) => event.place?.country)).toEqual(['DE', 'DE']);
    expect((await (await call()).json()).friends[0].stats.stamps).toEqual(['first', 'express', 'rightNextDoor']);
  });

  it('returns only shared nicknames and profile IDs in authenticated sender notices', async () => {
    const updates = { updates: [{ friendId: id, nickname: 'Mila' }] };
    const rpc = vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ updates: [{ ...updates.updates[0], email: 'private', parcel: 'private' }] });
    const response = await activityGET(new NextRequest('https://delivery.example/api/friends/activity', { headers: { Authorization: 'Bearer activity-test' } }), { params: Promise.resolve({}) });
    expect(response.status).toBe(200); expect(await response.json()).toEqual(updates);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(rpc).toHaveBeenCalledWith('/rest/v1/rpc/friends_activity', { method: 'POST', body: {} });
    expect((await activityGET(new NextRequest('https://delivery.example/api/friends/activity'), { params: Promise.resolve({}) })).status).toBe(401);
    expect(() => friendsActivity({ updates: [{ friendId: id, nickname: '\u202espoof' }] })).toThrow();
    expect(() => friendsActivity({ updates: Array(51).fill(updates.updates[0]) })).toThrow();
    expect(friendsAction({ action: 'acknowledge_friend', friendId: id })).toEqual({ action: 'acknowledge_friend', friendId: id });
  });
  it('includes the accepted profile without exposing extra fields', () => {
    expect(friendsActionResponse({ snapshot: storedSnapshot, acceptedFriend: { ...storedCard, email: 'private' } }, 'accept_invite')).toEqual({ snapshot: storedSnapshot, acceptedFriend: storedCard });
    expect(friendsActionResponse({ snapshot: storedSnapshot, acceptedFriend: storedCard }, 'save_profile')).toEqual({ snapshot: storedSnapshot });
  });
  it('projects an allowlist at every nesting level, excluding all parcel and account data', async () => {
    const privateCard = { ...storedCard, email: 'private@example.test', trackingNumber: 'PRIVATE123', parcel: { label: 'Private purchase' }, stats: { ...card.stats, location: 'Private address' } };
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ ...storedSnapshot, ownCard: privateCard, friends: [privateCard], invites: ['secret-code'] });
    const response = await call(); expect(response.status).toBe(200); expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.json()).toEqual(snapshot);
  });
  it('exports the owner’s profile and connections without copying friends’ statistics', async () => {
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue(storedSnapshot);
    vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'accountLastOpened').mockResolvedValue(null);
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(null);
    vi.spyOn(SupabaseServiceClient.prototype, 'assistantGrants').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'twinNumbers').mockResolvedValue([]);
    vi.spyOn(shopWaits, 'accountShopWaits').mockResolvedValue([]);
    const response = await exportGET(new NextRequest('https://delivery.example/api/account/export', { headers: { Authorization: 'Bearer friends-export-test' } }), { params: Promise.resolve({}) });
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    const exported = await response.json();
    expect(exported.friends).toEqual({ profile: snapshot.profile, connections: [{ id, nickname: 'Mila' }] });
    expect(exported.friends).not.toHaveProperty('ownCard');
    expect(exported.friends.connections[0]).not.toHaveProperty('stats');
  });
  it('uses the authenticated database RPC, never a client-supplied owner', async () => {
    const rpc = vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ snapshot: storedSnapshot });
    expect((await call({ action: 'save_profile', nickname: ' Alex ', shareStats: true, shareArrival: false })).status).toBe(200);
    expect(rpc).toHaveBeenCalledWith('/rest/v1/rpc/friends_action', { method: 'POST', body: { p_action: 'save_profile', p_nickname: 'Alex', p_share_stats: true, p_share_arrival: false, p_code: null, p_friend_id: null } });
    expect((await call({ action: 'disable', userId: id })).status).toBe(400);
  });
  it('denies unauthenticated reads and validates before accessing the database', async () => {
    const rpc = vi.spyOn(SupabaseUserClient.prototype, 'request');
    expect((await call(undefined, false)).status).toBe(401);
    expect((await call({ action: 'accept_invite', code: 'short' })).status).toBe(400);
    expect(rpc).not.toHaveBeenCalled();
  });
  it('requires a bearer token to cancel older invitations and supports revoking one specific link', async () => {
    const code = 'a'.repeat(32);
    expect(friendsAction({ action: 'revoke_invite', code })).toEqual({ action: 'revoke_invite', code });
    expect(friendsAction({ action: 'revoke_previous_invites', code })).toEqual({ action: 'revoke_previous_invites', code });
    for (const body of [{ action: 'revoke_previous_invites' }, { action: 'revoke_invite', code: null }, { action: 'revoke_previous_invites', code: 'short' }, { action: 'revoke_previous_invites', code, userId: id }]) {
      expect(() => friendsAction(body)).toThrow();
    }
    const rpc = vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ snapshot: storedSnapshot, previousInviteCount: 0 });
    const response = await call({ action: 'revoke_previous_invites', code });
    expect(await response.json()).toEqual({ snapshot, previousInviteCount: 0 });
    expect(rpc).toHaveBeenCalledWith('/rest/v1/rpc/friends_action', { method: 'POST', body: { p_action: 'revoke_previous_invites', p_code: code, p_nickname: null, p_share_stats: null, p_share_arrival: null, p_friend_id: null } });
  });
  it('projects only a valid owner invitation count and never adds it to public previews', () => {
    const invite = { inviteCode: 'a'.repeat(32), expiresAt: '2026-09-16T00:00:00Z', previousInviteCount: 6 };
    expect(friendsActionResponse({ ...invite, emails: ['private'] }, 'create_invite')).toEqual(invite);
    expect(friendsActionResponse({ previewNickname: 'Paul', previousInviteCount: 6 }, 'preview_invite')).toEqual({ previewNickname: 'Paul' });
    for (const previousInviteCount of [-1, 1.5, '6', null, Number.MAX_SAFE_INTEGER + 1]) {
      expect(() => friendsActionResponse({ ...invite, previousInviteCount }, 'create_invite')).toThrow();
      expect(() => friendsActionResponse({ snapshot: storedSnapshot, previousInviteCount }, 'revoke_previous_invites')).toThrow();
    }
  });
  it('revokes the caller’s outstanding invitations without needing a saved link or accepting another owner', async () => {
    const rpc = vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ snapshot: storedSnapshot });
    expect((await call({ action: 'revoke_invite' }, false)).status).toBe(401);
    expect((await call({ action: 'revoke_invite', userId: id })).status).toBe(400);
    expect(rpc).not.toHaveBeenCalled();
    const response = await call({ action: 'revoke_invite' });
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.json()).toEqual({ snapshot });
    expect(rpc).toHaveBeenCalledExactlyOnceWith('/rest/v1/rpc/friends_action', {
      method: 'POST', body: { p_action: 'revoke_invite', p_nickname: null, p_share_stats: null, p_share_arrival: null, p_code: null, p_friend_id: null },
    });
  });
  it.each([
    { action: 'save_profile', nickname: '', shareStats: true, shareArrival: false },
    { action: 'save_profile', nickname: 'x'.repeat(25), shareStats: true, shareArrival: false },
    { action: 'save_profile', nickname: '\u202espoof', shareStats: true, shareArrival: false },
    { action: 'save_profile', nickname: 'Alex', shareStats: 'true', shareArrival: false },
    { action: 'save_profile', nickname: 'Alex', shareStats: true },
    { action: 'save_profile', nickname: 'Alex', shareStats: true, shareArrival: false, stats: { deliveredCount: 9000 } },
    { action: 'remove_friend', friendId: 'not-an-id' }, { action: 'bad' }, { action: '__proto__' }, { action: 1 },
  ])('rejects malformed or extra fields: %j', (body) => { expect(() => friendsAction(body)).toThrow(); });
  it('round-trips hidden stats and absent profiles without filling fake data', () => {
    expect(friendCard({ ...storedCard, sharesStats: false, arrivedThisWeek: false })).toEqual({ ...storedCard, sharesStats: false, arrivedThisWeek: false });
    expect(friendsSnapshot({ profile: null, ownCard: null, friends: [] })).toEqual({ profile: null, ownCard: null, friends: [] });
    expect(() => friendsSnapshot({ profile: null, ownCard: storedCard, friends: [storedCard] })).toThrow();
    expect(() => friendsSnapshot({ ...storedSnapshot, friends: Array(51).fill(storedCard) })).toThrow();
  });
  it.each([null, {}, card, { ...storedCard, sharesStats: 'true' }, { ...storedCard, arrivedThisWeek: '2026-09-09T12:13:14Z' }])('fails closed on malformed summaries: %j', (value) => { expect(() => friendCard(value)).toThrow(); });
  it('strips invitation metadata and prevents previewing any stats', () => {
    expect(friendsActionResponse({ previewNickname: 'Mila', ...card }, 'preview_invite')).toEqual({ previewNickname: 'Mila' });
    expect(friendsActionResponse({ inviteCode: 'a'.repeat(32), expiresAt: '2026-09-16T00:00:00Z', userId: id }, 'create_invite')).toEqual({ inviteCode: 'a'.repeat(32), expiresAt: '2026-09-16T00:00:00Z' });
    expect(() => friendsActionResponse({ inviteCode: 'short' }, 'create_invite')).toThrow();
    expect(() => friendsActionResponse({}, 'preview_invite')).toThrow();
    expect(() => friendsActionResponse(null, 'disable')).toThrow();
  });
  it('projects a validated short preview ID and rejects malformed IDs', () => {
    const result = { inviteCode: 'a'.repeat(32), previewId: 'Ab7kP2mQ9xR4tY6n', expiresAt: '2026-09-16T00:00:00Z' };
    expect(friendsActionResponse({ ...result, privateUserId: id }, 'create_invite')).toEqual(result);
    for (const previewId of ['short', 'a'.repeat(64), 'a'.repeat(15) + '/', null, 123]) {
      expect(() => friendsActionResponse({ ...result, previewId }, 'create_invite')).toThrow();
    }
  });
  it.each([['P0002',404], ['P0003',409], ['P0004',422], ['22023',400], ['PGRST202',503], ['42883',503], ['other',502]] as const)('maps safe database errors: %s', async (code, status) => {
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockRejectedValue(new SupabaseError('PRIVATE_SERVER_PAYLOAD', 400, code));
    const response = await call({ action: 'accept_invite', code: 'a'.repeat(32) });
    expect(response.status).toBe(status); expect(await response.text()).not.toContain('PRIVATE_SERVER_PAYLOAD');
  });
});

it('accepts standalone keys only through the authenticated action endpoint', async () => {
  const code = 'Ab7kP2mQ9xR4tY6n';
  const rpc = vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ snapshot: storedSnapshot });
  expect((await call({ action: 'accept_invite', code }, false)).status).toBe(401);
  expect(rpc).not.toHaveBeenCalled();
  expect((await call({ action: 'accept_invite', code })).status).toBe(200);
  expect(rpc).toHaveBeenCalledWith('/rest/v1/rpc/friends_action', expect.objectContaining({
    body: expect.objectContaining({ p_action: 'accept_invite', p_code: code }),
  }));
  for (const action of ['preview_invite', 'revoke_invite', 'revoke_previous_invites']) {
    expect(friendsAction({ action, code })).toEqual({ action, code });
  }
  // A legacy public preview hash must never become an acceptance credential.
  expect((await call({ action: 'accept_invite', code: 'a'.repeat(64) })).status).toBe(400);
});

it.each(['already_accepted', 'already_friends'] as const)('returns the private %s outcome without a new friendship receipt', async (invitationState) => {
  const result = { previewNickname: 'Paul', invitationState };
  vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ ...result, recipientId: id });
  const response = await call({ action: 'preview_invite', code: 'ab'.repeat(16) });
  expect(response.status).toBe(200);
  expect(await response.json()).toEqual(result);
  expect(friendsActionResponse({ snapshot: storedSnapshot, invitationState, acceptedFriend: storedCard }, 'accept_invite')).toEqual({ snapshot: storedSnapshot, invitationState });
});
it.each(['unknown', null, 123])('rejects malformed invitation outcomes: %s', (invitationState) => {
  expect(() => friendsActionResponse({ previewNickname: 'Paul', invitationState }, 'preview_invite')).toThrow();
  expect(() => friendsActionResponse({ snapshot: storedSnapshot, invitationState }, 'accept_invite')).toThrow();
});

describe('a circle\'s shared Passports', () => {
  // Synthetic accounts, profiles and parcels only. The viewer signs in as `id`.
  const account = (index: number) => `14000000-0000-4000-8000-${String(index).padStart(12, '0')}`;
  const profileOf = (index: number) => `13000000-0000-4000-8000-${String(index).padStart(12, '0')}`;
  const ownProfile = profileOf(0);
  let parcelCount = 0;
  /** A parcel as the database holds it: each scan its stage, time and place, and any column of its own (`provider_event_id`, `raw_data`). */
  function held(user_id: string, carrier: string, scans: Array<[string, string, string?, JsonObject?]>, extra: JsonObject = {}): JsonObject {
    const parcel = `15000000-0000-4000-8000-${String(parcelCount += 1).padStart(12, '0')}`;
    return {
      id: parcel, user_id, tracking_number: `SYNTHETIC${parcelCount}`, label: 'Private purchase', carrier, created_at: '2026-01-01T00:00:00Z',
      expected_delivery: null, last_status_text: 'Private status', last_synced_at: '2026-11-01T00:00:00Z', sync_status: 'ok', sync_error: null,
      tracking_url: null, dpd_postcode: null, phone_digits: null, carrier_data: {}, archived_at: null, notifications_muted: false, email_muted: false,
      seen_at: null, ...extra,
      tracking_events: scans.map(([stage, occurred_at, location, own], scan) => ({
        id: `${parcel}-${scan}`, package_id: parcel, stage, description: `Synthetic ${stage}`, location: location ?? null, occurred_at,
        created_at: occurred_at, provider_event_id: `${carrier}:${parcelCount}:${scan}`, raw_data: {}, ...own,
      })),
    };
  }
  /** One journey of a carrier: accepted at `from`, then delivered at `to` (an `[stage, time, place]` scan in between, if any). */
  const journey = (user_id: string, carrier: string, from: [string, string], to: [string, string], between: Array<[string, string, string]> = [], extra: JsonObject = {}) =>
    held(user_id, carrier, [['accepted', ...from], ...between, ['delivered', ...to]], extra);
  const storedCardOf = (index: number, nickname: string, sharesStats = true) => ({ id: profileOf(index), nickname, sharesStats, arrivedThisWeek: null });
  const profile = { nickname: 'Alex', shareStats: true, shareArrival: false };
  /** The cards and stats as the server worked them out before the circle was read at once: each owner's parcels read on their own. */
  async function readAlone(owners: Map<string, string>): Promise<Map<string, unknown>> {
    const service = new SupabaseServiceClient('https://database.example', 'service-key');
    return new Map(await Promise.all([...owners].map(async ([card, owner]) => [card,
      sharedPassport((await service.listAccountPackages(owner)).map((row) => toParcel(withEventPlaces(row) as unknown as ApiPackageRow)))] as const)));
  }
  const paths = (request: { mock: { calls: unknown[][] } }) => request.mock.calls.map(([path]) => String(path).split('?')[0]);

  beforeEach(() => {
    vi.mocked(SupabaseServiceClient.prototype.passportSharers).mockRestore();
    vi.mocked(SupabaseServiceClient.prototype.passportParcels).mockRestore();
  });

  it('reads a full circle\'s parcels in one read, in as many reads as for one friend', async () => {
    const reads: string[][] = [];
    for (const size of [1, 50]) {
      const friends = Array.from({ length: size }, (_, index) => index + 1);
      const tables = {
        friend_profiles: [{ id: ownProfile, user_id: id, share_stats: true }, ...friends.map((index) => ({ id: profileOf(index), user_id: account(index), share_stats: true }))],
        friend_connections: friends.map((index) => ({ user_low: id, user_high: account(index) })),
        packages: [id, ...friends.map(account)].flatMap((owner) => [
          journey(owner, 'swiss-post', ['2026-03-02T08:00:00Z', 'Bern, CH'], ['2026-03-03T09:00:00Z', 'Zürich, CH']),
          journey(owner, 'dhl', ['2026-06-01T10:00:00Z', 'Haiger, DE'], ['2026-06-09T10:00:00Z', 'Bern, CH']),
        ]),
      };
      const request = vi.spyOn(SupabaseServiceClient.prototype, 'request').mockImplementation(postgrestTables(tables, 1000));
      vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({
        profile, ownCard: { ...storedCardOf(0, 'Alex') }, friends: friends.map((index) => storedCardOf(index, `Friend ${index}`)),
      });
      const response = await call();
      expect(response.status).toBe(200);
      const body = await response.json();
      expect(body.friends).toHaveLength(size);
      expect([body.ownCard, ...body.friends].map(({ stats }) => stats)).toEqual(Array(size + 1).fill({ deliveredCount: 2, averageDays: 5, stamps: ['first', 'express', 'acrossBorders', 'rightNextDoor'] }));
      const packages = request.mock.calls.filter(([path]) => String(path).startsWith('/rest/v1/packages?'));
      expect(packages).toHaveLength(1);
      expect(new URLSearchParams(String(packages[0]![0]).split('?')[1]).get('user_id')).toBe(`in.(${[id, ...friends.map(account)].join(',')})`);
      reads.push(paths(request));
      vi.mocked(SupabaseServiceClient.prototype.request).mockRestore();
    }
    // Each friend used to take a read of their own: 52 reads for a full circle, 3 for one friend.
    expect(reads[1]).toEqual(reads[0]);
    expect(reads[0]).toEqual(['/rest/v1/friend_profiles', '/rest/v1/packages', '/rest/v1/friend_profiles', '/rest/v1/friend_connections']);
  });

  it('shows a mixed circle the same cards, counts and stamps as reading each owner\'s parcels alone', async () => {
    const [mila, noor, sam, lou, stranger] = [1, 2, 3, 4, 5].map(account);
    const tables = {
      friend_profiles: [
        { id: ownProfile, user_id: id, share_stats: true }, { id: profileOf(1), user_id: mila, share_stats: true },
        { id: profileOf(2), user_id: noor, share_stats: false }, { id: profileOf(3), user_id: sam, share_stats: true },
        { id: profileOf(4), user_id: lou, share_stats: true }, { id: profileOf(5), user_id: stranger, share_stats: true },
      ],
      friend_connections: [mila, noor, sam, lou].map((friend) => ({ user_low: id, user_high: friend })),
      packages: [
        // The viewer: a domestic express journey, an archived one in December and one on its way.
        journey(id, 'swiss-post', ['2026-03-02T08:00:00Z', 'Bern, CH'], ['2026-03-03T09:00:00Z', 'Zürich, CH']),
        journey(id, 'swiss-post', ['2025-12-10T08:00:00Z', 'Basel, CH'], ['2025-12-12T09:00:00Z', 'Bern, CH'], [], { archived_at: '2026-01-05T00:00:00Z' }),
        held(id, 'swiss-post', [['in_transit', '2026-11-10T08:00:00Z', 'Basel, CH']]),
        // Mila: collected at a pickup point after crossing borders, a long wait, a state code placed by its point, three
        // arrivals in one day, a return, and a parcel only announced after Peek's own row.
        journey(mila, 'swiss-post', ['2026-05-01T10:00:00Z', 'Shenzhen, CN'], ['2026-05-07T15:00:00Z', 'Bern, CH'], [
          ['in_transit', '2026-05-04T10:00:00Z', 'Leipzig, DE'], ['ready_for_pickup', '2026-05-06T09:00:00Z', 'Bern, CH']]),
        journey(mila, 'la-poste', ['2026-01-02T10:00:00Z', 'Lyon, FR'], ['2026-02-20T10:00:00Z', 'Bern, CH'], [], { archived_at: '2026-03-01T00:00:00Z' }),
        held(mila, 'dhl', [['accepted', '2026-06-01T10:00:00Z', 'Haiger, DE', { raw_data: { point: { latitude: 50.74, longitude: 8.2 } } }],
          ['delivered', '2026-06-02T10:00:00Z', 'Koblenz, DE']]),
        ...['08', '12', '16'].map((hour) => journey(mila, 'ups', ['2026-07-10T10:00:00Z', 'Louisville, KY, US'], [`2026-07-15T${hour}:00:00Z`, 'Bern, CH'])),
        held(mila, 'poste-italiane', [['accepted', '2026-08-01T10:00:00Z', 'Milano, IT'], ['returned', '2026-08-20T10:00:00Z', 'Milano, IT']]),
        held(mila, 'swiss-post', [['pending', '2026-11-01T10:00:00Z', undefined, { provider_event_id: 'app:added' }], ['registered', '2026-11-02T10:00:00Z']]),
        // Sam: a provider's copy of a scan ten hours off, left out as a copy of the confirmed carrier's, and a placeholder
        // number whose carrier answers are other people's shipments.
        held(sam, 'swiss-post', [['accepted', '2026-09-01T10:00:00Z', 'Bern, CH'], ['delivered', '2026-09-02T09:00:00Z', 'Zürich, CH'],
          ['accepted', '2026-09-01T00:00:00Z', 'Bern, CH', { provider_event_id: 'unknown:parcelsapp:1', raw_data: { universal_provider: 'parcelsapp' } }]],
        { carrier_data: { routing: { confirmed_carrier: 'swiss-post' } } }),
        journey(sam, 'dhl', ['2026-04-01T10:00:00Z', 'Berlin, DE'], ['2026-04-02T10:00:00Z', 'Berlin, DE'], [], { tracking_number: '000000000' }),
        // Noor shares no stats, and the stranger is no friend: neither's parcels are read.
        journey(noor, 'dhl', ['2026-04-01T10:00:00Z', 'Berlin, DE'], ['2026-04-02T10:00:00Z', 'Berlin, DE']),
        journey(stranger, 'dhl', ['2026-04-01T10:00:00Z', 'Berlin, DE'], ['2026-04-02T10:00:00Z', 'Berlin, DE']),
      ],
    };
    const request = vi.spyOn(SupabaseServiceClient.prototype, 'request').mockImplementation(postgrestTables(tables, 1000));
    const before = await readAlone(new Map([[ownProfile, id], [profileOf(1), mila], [profileOf(3), sam], [profileOf(4), lou]]));
    request.mockClear();
    const stored = {
      profile, ownCard: storedCardOf(0, 'Alex'), friends: [storedCardOf(1, 'Mila'), storedCardOf(2, 'Noor', false), storedCardOf(3, 'Sam'), storedCardOf(4, 'Lou')],
    };
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue(stored);
    const response = await call();
    expect(response.status).toBe(200);
    const restamped = (card: ReturnType<typeof storedCardOf>) => {
      const { sharesStats, ...shown } = card;
      return { ...shown, stats: sharesStats ? before.get(card.id) : null };
    };
    expect(await response.json()).toEqual({ profile, ownCard: restamped(stored.ownCard), friends: stored.friends.map(restamped) });
    // The circle is varied enough to tell: most stamps, a provider's copy and a placeholder number left out, an empty
    // Passport and a private one.
    expect(before.get(profileOf(1))).toMatchObject({ deliveredCount: 6, stamps: expect.arrayContaining(['acrossBorders', 'aroundWorld', 'worthTheWait', 'busyDoorstep', 'pickedUp']) });
    expect(before.get(ownProfile)).toMatchObject({ deliveredCount: 2, stamps: expect.arrayContaining(['homeForHolidays', 'rightNextDoor']) });
    expect(before.get(profileOf(3))).toEqual({ deliveredCount: 1, averageDays: 1, stamps: ['first', 'express', 'rightNextDoor'] });
    expect(before.get(profileOf(4))).toEqual({ deliveredCount: 0, averageDays: null, stamps: [] });
    const read = request.mock.calls.filter(([path]) => String(path).startsWith('/rest/v1/packages?'));
    expect(read).toHaveLength(1);
    expect(new URLSearchParams(String(read[0]![0]).split('?')[1]).get('user_id')).toBe(`in.(${id},${mila},${sam},${lou})`);
    // The POST answer after an edit is worked out the same way.
    vi.mocked(SupabaseUserClient.prototype.request).mockResolvedValue({ snapshot: stored });
    expect(await (await call({ action: 'save_profile', nickname: 'Alex', shareStats: true, shareArrival: false })).json())
      .toEqual({ snapshot: { profile, ownCard: restamped(stored.ownCard), friends: stored.friends.map(restamped) } });
  });

  it('shows nothing of a friend who stops sharing or leaves the circle while the parcels are read', async () => {
    const [mila, noor, sam] = [1, 2, 3].map(account);
    const tables = {
      friend_profiles: [
        { id: ownProfile, user_id: id, share_stats: true }, { id: profileOf(1), user_id: mila, share_stats: true },
        { id: profileOf(2), user_id: noor, share_stats: true }, { id: profileOf(3), user_id: sam, share_stats: true },
      ],
      friend_connections: [{ user_low: id, user_high: mila }, { user_low: id, user_high: noor }, { user_low: id, user_high: sam }],
      packages: [id, mila, noor, sam].map((owner) => journey(owner, 'swiss-post', ['2026-03-02T08:00:00Z', 'Bern, CH'], ['2026-03-03T09:00:00Z', 'Zürich, CH'])),
    };
    const fake = postgrestTables(tables, 1000);
    vi.spyOn(SupabaseServiceClient.prototype, 'request').mockImplementation(async (path: string) => {
      const answer = await fake(path);
      if (path.startsWith('/rest/v1/packages?')) {
        // While the parcels are read, Mila stops sharing her stats and Noor leaves the circle.
        tables.friend_profiles[1]!.share_stats = false;
        tables.friend_connections.splice(1, 1);
      }
      return answer;
    });
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({
      profile, ownCard: storedCardOf(0, 'Alex'), friends: [storedCardOf(1, 'Mila'), storedCardOf(2, 'Noor'), storedCardOf(3, 'Sam')],
    });
    const body = await (await call()).json();
    const stats = { deliveredCount: 1, averageDays: 2, stamps: ['first', 'express', 'rightNextDoor'] };
    expect(body.ownCard.stats).toEqual(stats);
    expect(body.friends.map(({ nickname, stats }: { nickname: string; stats: unknown }) => [nickname, stats])).toEqual([['Mila', null], ['Noor', null], ['Sam', stats]]);
  });
});

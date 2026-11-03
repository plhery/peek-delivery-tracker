import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as exportGET } from '../../app/api/account/export/route';
import { GET, POST } from '../../app/api/friends/route';
import { GET as activityGET } from '../../app/api/friends/activity/route';
import { SupabaseAuthenticator } from './auth';
import { SupabaseError, SupabaseServiceClient, SupabaseUserClient } from './supabase';
import { friendCard, friendsAction, friendsActionResponse, friendsSnapshot, friendsActivity } from './friends';
import { withEventPlaces } from './eventPlaces';
import { sharedPassport } from '../lib/passport';
import { toParcel } from '../store/apiRepo';
import type { ApiPackageRow } from '../generated/apiContract';

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
  vi.spyOn(SupabaseServiceClient.prototype, 'listAccountPackages').mockResolvedValue(parcels);
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
    const read = vi.mocked(SupabaseServiceClient.prototype.listAccountPackages);
    const response = await call();
    expect(response.status).toBe(200);
    const body = await response.json();
    expect(body.ownCard).toEqual(card);
    expect(body.ownCard.stats).toEqual(sharedPassport(parcels.map((row) => toParcel(withEventPlaces(row) as unknown as ApiPackageRow))));
    // A card that shares nothing is not looked up; one whose owner stopped sharing in between loses its stats.
    expect(body.friends).toEqual([card, { ...card, id: hidden, stats: null }, { ...card, id: stopped, stats: null }]);
    expect(sharers).toHaveBeenCalledExactlyOnceWith([id, id, stopped]);
    expect(read).toHaveBeenCalledExactlyOnceWith(owner);
    expect(JSON.stringify(body)).not.toMatch(/PRIVATE|Private/);
    expect(JSON.stringify(body)).not.toContain(owner);
    // An accepted friend's card is worked out the same way.
    vi.mocked(SupabaseUserClient.prototype.request).mockResolvedValue({ snapshot: storedSnapshot, acceptedFriend: storedCard });
    expect(await (await call({ action: 'accept_invite', code: 'a'.repeat(32) })).json()).toEqual({ snapshot, acceptedFriend: card });
  });

  it('counts a code that is also a state toward a friend\'s stamps only where the server placed the scan', async () => {
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue(storedSnapshot);
    vi.mocked(SupabaseServiceClient.prototype.listAccountPackages).mockResolvedValue([
      stored(0, [['accepted', '2026-08-04T10:00:00Z', 'Haiger, DE'], ['delivered', '2026-08-05T10:00:00Z', 'Koblenz, DE']]),
    ]);
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

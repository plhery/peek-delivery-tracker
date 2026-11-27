import 'server-only';
import { createHash } from 'node:crypto';
import { isInvitationCode, isInvitationPreviewId } from '../lib/invitationLinkFormat';
import { sharedPassport } from '../lib/passport';
import { toParcel } from '../store/apiRepo';
import type { ApiFriendCard, ApiFriendProfile, ApiFriendStats, ApiFriendsActionRequest, ApiFriendsActionResponse, ApiFriendsSnapshot, ApiFriendsActivity, ApiPackageRow } from '../generated/apiContract';
import { HttpError } from './api';
import { withEventPlaces } from './eventPlaces';
import { isRecord, type JsonObject } from './types';
import { SupabaseError, type SupabaseUserClient, type SupabaseServiceClient } from './supabase';

const uuid = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
const fields: Record<ApiFriendsActionRequest['action'], string[]> = {
  save_profile: ['nickname', 'shareStats', 'shareArrival'], create_invite: [], revoke_invite: [], revoke_previous_invites: ['code'],
  preview_invite: ['code'], accept_invite: ['code'], remove_friend: ['friendId'], acknowledge_friend: ['friendId'], disable: [],
};
const invalid = () => new HttpError(400, 'Invalid Friends request');
function nickname(value: unknown): value is string {
  return typeof value === 'string' && [...value.trim()].length >= 1 && [...value.trim()].length <= 24 && !/[\p{Cc}\p{Cf}]/u.test(value);
}
export function friendsAction(payload: JsonObject): ApiFriendsActionRequest {
  if (typeof payload.action !== 'string' || !Object.hasOwn(fields, payload.action)) throw invalid();
  const required = fields[payload.action as ApiFriendsActionRequest['action']];
  const allowed = payload.action === 'revoke_invite' ? [...required, 'code'] : payload.action === 'save_profile' ? [...required, 'shareCountries'] : required;
  if (Object.keys(payload).some((key) => key !== 'action' && !allowed.includes(key)) || required.some((key) => payload[key] === undefined)) throw invalid();
  if (payload.action === 'save_profile' && (!nickname(payload.nickname) || typeof payload.shareStats !== 'boolean' || typeof payload.shareArrival !== 'boolean' || !(payload.shareCountries === undefined || typeof payload.shareCountries === 'boolean'))) throw invalid();
  if (allowed.includes('code') && payload.code !== undefined && !isInvitationCode(payload.code)) throw invalid();
  if (allowed.includes('friendId') && (typeof payload.friendId !== 'string' || !uuid.test(payload.friendId))) throw invalid();
  return { ...payload, ...(typeof payload.nickname === 'string' ? { nickname: payload.nickname.trim() } : {}) } as unknown as ApiFriendsActionRequest;
}
function corrupt(): never { throw new HttpError(502, 'Friends is temporarily unavailable'); }

/** A bearer invitation reveals only its sender's nickname, without consuming it. */
export async function invitationPreview(client: SupabaseServiceClient, payload: JsonObject): Promise<{ previewNickname: string }> {
  if (Object.keys(payload).length !== 1 || !isInvitationCode(payload.code)) throw invalid();
  return isInvitationPreviewId(payload.code)
    ? invitationPreviewById(client, payload.code)
    : invitationPreviewByHash(client, createHash('sha256').update(payload.code).digest('hex'));
}

/** The public preview hash can reveal a nickname, but cannot accept an invitation. */
export async function invitationPreviewByHash(client: SupabaseServiceClient, hash: string): Promise<{ previewNickname: string }> {
  if (!/^[a-f0-9]{64}$/.test(hash)) throw invalid();
  return invitationPreviewLookup(client, 'code_hash', hash);
}

export async function invitationPreviewById(client: SupabaseServiceClient, previewId: string): Promise<{ previewNickname: string }> {
  if (!isInvitationPreviewId(previewId)) throw invalid();
  return invitationPreviewLookup(client, 'preview_id', previewId);
}

async function invitationPreviewLookup(client: SupabaseServiceClient, column: 'code_hash' | 'preview_id', value: string): Promise<{ previewNickname: string }> {
  const query = new URLSearchParams({
    select: 'friend_profiles!inner(nickname)',
    [column]: `eq.${value}`,
    expires_at: `gt.${new Date().toISOString()}`,
    limit: '1',
  });
  const result = await client.request(`/rest/v1/friend_invites?${query}`);
  if (!Array.isArray(result)) return corrupt();
  if (!result.length) throw new HttpError(404, 'Invitation unavailable');
  const sender = isRecord(result[0]) ? result[0].friend_profiles : null;
  if (!isRecord(sender) || !nickname(sender.nickname)) return corrupt();
  return { previewNickname: sender.nickname };
}
function profile(value: unknown): ApiFriendProfile | null {
  if (value === null) return null;
  if (!isRecord(value) || !nickname(value.nickname) || typeof value.shareStats !== 'boolean' || typeof value.shareArrival !== 'boolean' || typeof value.shareCountries !== 'boolean') return corrupt();
  return { nickname: value.nickname, shareStats: value.shareStats, shareArrival: value.shareArrival, shareCountries: value.shareCountries };
}
/** A card as the database gives it: whether its owner shares stats and countries, which the server works out (withSharedPassports). */
export type StoredFriendCard = Omit<ApiFriendCard, 'stats'> & { sharesStats: boolean; sharesCountries: boolean };
export type StoredFriendsSnapshot = Omit<ApiFriendsSnapshot, 'ownCard' | 'friends'> & { ownCard: StoredFriendCard | null; friends: StoredFriendCard[] };
type StoredFriendsActionResponse = Omit<ApiFriendsActionResponse, 'snapshot' | 'acceptedFriend'> & { snapshot?: StoredFriendsSnapshot; acceptedFriend?: StoredFriendCard };
/** Explicit projection is a second boundary: adding a DB field can never publish it. */
export function friendCard(value: unknown): StoredFriendCard {
  if (!isRecord(value) || typeof value.id !== 'string' || !uuid.test(value.id) || !nickname(value.nickname) || typeof value.sharesStats !== 'boolean' || typeof value.sharesCountries !== 'boolean' || !(value.arrivedThisWeek === null || typeof value.arrivedThisWeek === 'boolean')) return corrupt();
  return { id: value.id, nickname: value.nickname, sharesStats: value.sharesStats, sharesCountries: value.sharesCountries, arrivedThisWeek: value.arrivedThisWeek };
}
export function friendsSnapshot(value: unknown): StoredFriendsSnapshot {
  if (!isRecord(value) || !Array.isArray(value.friends) || value.friends.length > 50) return corrupt();
  const own = profile(value.profile);
  if (!own && (value.ownCard !== null || value.friends.length)) return corrupt();
  return { profile: own, ownCard: value.ownCard === null ? null : friendCard(value.ownCard), friends: value.friends.map(friendCard) };
}
/**
 * A shared card's counts and stamps, and its countries while its owner shares those too, worked out with the
 * app's own Passport rules over the parcels its owner's apps read, as served to them. The whole circle's parcels
 * come in one read (`passportParcels`). The card still carries only those, and only while its owner shares them
 * with the viewer: that is checked again once the parcels are read, so a card whose owner stopped sharing or left
 * the viewer's circle in between loses them.
 */
export async function withSharedPassports(service: SupabaseServiceClient, viewer: string, snapshot: StoredFriendsSnapshot, acceptedFriend?: StoredFriendCard): Promise<{ snapshot: ApiFriendsSnapshot; acceptedFriend?: ApiFriendCard }> {
  const shared = [snapshot.ownCard, ...snapshot.friends, acceptedFriend].filter((card): card is StoredFriendCard => Boolean(card?.sharesStats));
  const owners = await service.passportSharers(shared.map(({ id }) => id));
  const parcels = await service.passportParcels([...owners.values()].map(({ owner }) => owner));
  const allowed = owners.size ? await service.passportSharers([...owners.keys()], viewer) : owners;
  const passports = new Map<string, ApiFriendStats>();
  const passport = (owner: string) => {
    if (!passports.has(owner)) passports.set(owner, sharedPassport((parcels.get(owner) ?? []).map((row) => toParcel(withEventPlaces(row, { peekAi: false }) as unknown as ApiPackageRow)), { countries: true }));
    return passports.get(owner)!;
  };
  const restamp = ({ sharesStats, sharesCountries, ...card }: StoredFriendCard): ApiFriendCard => {
    const sharer = sharesStats ? owners.get(card.id) : undefined;
    const still = sharer && allowed.get(card.id);
    if (!sharer || !still || still.owner !== sharer.owner) return { ...card, stats: null };
    const { countries, ...stats } = passport(sharer.owner);
    return { ...card, stats: sharesCountries && sharer.countries && still.countries ? { ...stats, countries } : stats };
  };
  return {
    snapshot: { ...snapshot, ownCard: snapshot.ownCard && restamp(snapshot.ownCard), friends: snapshot.friends.map(restamp) },
    ...(acceptedFriend ? { acceptedFriend: restamp(acceptedFriend) } : {}),
  };
}
export async function friendsRPC(client: SupabaseUserClient, action?: ApiFriendsActionRequest): Promise<unknown> {
  try {
    return await client.request(`/rest/v1/rpc/${action ? 'friends_action' : 'friends_snapshot'}`, {
      method: 'POST', body: action ? {
        p_action: action.action, p_nickname: action.nickname ?? null, p_share_stats: action.shareStats ?? null,
        p_share_arrival: action.shareArrival ?? null, p_code: action.code ?? null, p_friend_id: action.friendId ?? null,
        ...(action.action === 'save_profile' ? { p_share_countries: action.shareCountries ?? null } : {}),
      } : {},
    });
  } catch (error) {
    if (error instanceof SupabaseError) {
      if (error.code === 'P0002') throw new HttpError(404, 'Invitation unavailable');
      if (error.code === 'P0003') throw new HttpError(409, 'Your circle is full');
      if (error.code === 'P0004') throw new HttpError(422, 'Cannot accept your own invitation');
      if (error.code === '22023') throw invalid();
      if (error.code === 'PGRST202' || error.code === '42883') throw new HttpError(503, 'Friends is temporarily unavailable');
    }
    throw error;
  }
}
export function friendsActionResponse(value: unknown, action: ApiFriendsActionRequest['action']): StoredFriendsActionResponse {
  if (!isRecord(value)) return corrupt();
  const invitationState = (): Pick<StoredFriendsActionResponse, 'invitationState'> => {
    if (value.invitationState === undefined) return {};
    if (value.invitationState !== 'already_accepted' && value.invitationState !== 'already_friends') return corrupt();
    return { invitationState: value.invitationState };
  };
  const invitationCount = () => {
    if (value.previousInviteCount === undefined) return {};
    if (!Number.isSafeInteger(value.previousInviteCount) || Number(value.previousInviteCount) < 0) return corrupt();
    return { previousInviteCount: Number(value.previousInviteCount) };
  };
  if (action === 'create_invite') {
    if (typeof value.inviteCode !== 'string' || !/^[a-f0-9]{32}$/.test(value.inviteCode) || typeof value.expiresAt !== 'string' || !Number.isFinite(Date.parse(value.expiresAt))) return corrupt();
    if (value.previewId !== undefined && !isInvitationPreviewId(value.previewId)) return corrupt();
    return { inviteCode: value.inviteCode, expiresAt: value.expiresAt, ...(value.previewId !== undefined ? { previewId: value.previewId } : {}), ...invitationCount() };
  }
  if (action === 'preview_invite') {
    if (!nickname(value.previewNickname)) return corrupt();
    return { previewNickname: value.previewNickname, ...invitationState() };
  }
  if (action === 'accept_invite' && value.invitationState !== undefined) return { snapshot: friendsSnapshot(value.snapshot), ...invitationState() };
  return { snapshot: friendsSnapshot(value.snapshot), ...(action === 'accept_invite' && value.acceptedFriend ? { acceptedFriend: friendCard(value.acceptedFriend) } : {}), ...(action === 'revoke_previous_invites' ? invitationCount() : {}) };
}

export function friendsActivity(value: unknown): ApiFriendsActivity {
  if (!isRecord(value) || !Array.isArray(value.updates) || value.updates.length > 50) return corrupt();
  return { updates: value.updates.map((item) => {
    if (!isRecord(item) || typeof item.friendId !== 'string' || !uuid.test(item.friendId) || !nickname(item.nickname)) return corrupt();
    return { friendId: item.friendId, nickname: item.nickname };
  }) };
}

import 'server-only';

import { McpServer } from '@modelcontextprotocol/server';
import { z } from 'zod';
import type { ApiPackageRow, ApiTokenParcel, ApiTokenParcelStatus, ApiTokenParcelsResponse } from '../../generated/apiContract';
import { carrierInfo, normalizeTrackingNumber } from '../../lib/carriers';
import { parcelAttention } from '../../lib/parcelPriority';
import { viewParcels } from '../../lib/parcelView';
import { currentEvent, currentStage, sortEventsDesc } from '../../lib/stages';
import { toParcel } from '../../store/apiRepo';
import type { ParcelWithEvents } from '../../types';
import { withEventPlaces } from '../eventPlaces';
import { addParcelsFromText, MOST_TEXT_LENGTH, type ParcelAddition } from '../parcelsFromText';
import type { SupabaseServiceClient } from '../supabase';
import type { Scope } from './settings';

const UUID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
const MOST_EVENTS = 40;
/** Which parcels a list holds: those on their way (the default), those that need the user, those expected today… */
export const PARCEL_STATUSES = ['active', 'attention', 'today', 'delivered', 'archived', 'all'] as const satisfies readonly ApiTokenParcelStatus[];
export type ParcelStatus = typeof PARCEL_STATUSES[number];
export const MOST_LISTED = 50;

/** Who a call is for: the account behind the token, and what the token may do. */
export interface AssistantCaller {
  userId: string;
  scopes: readonly Scope[];
}

/** A parcel as a tool describes it: what an assistant needs to answer, without the recipient's name or postcode. */
function parcelSummary(parcel: ParcelWithEvents, now = Date.now()): ApiTokenParcel {
  const latest = currentEvent(parcel.events);
  return {
    id: parcel.id,
    name: parcel.label || null,
    carrier: carrierInfo(parcel.carrier).name,
    trackingNumber: parcel.trackingNumber,
    status: currentStage(parcel.events) ?? 'pending',
    // The newest scan, as the apps head the parcel and as `status` and `lastUpdate` read it: the saved summary is the
    // delivery carrier's after a handoff, while the earlier carrier may have scanned the parcel since.
    statusText: latest?.description ?? parcel.lastStatusText ?? null,
    needsAttention: parcelAttention(parcel, now),
    expectedDelivery: parcel.expectedDelivery ?? null,
    lastUpdate: latest ? { time: latest.day ?? latest.occurredAt, description: latest.description, location: latest.location ?? null } : null,
    pickupPoint: parcel.pickupPoint ?? null,
    sender: parcel.senderName ?? null,
    archived: Boolean(parcel.archivedAt),
    addedAt: parcel.createdAt,
  };
}

/** The account's parcels with a status, most urgent first, as `list_parcels` and `GET /api/parcels` give them. */
export async function listParcels(
  service: SupabaseServiceClient, userId: string, status: ParcelStatus = 'active', limit = 20,
): Promise<ApiTokenParcelsResponse> {
  const now = Date.now();
  const parcels = viewParcels((await service.listAccountPackages(userId)).map(asParcel), { query: '', status, sort: 'priority', now });
  return { total: parcels.length, parcels: parcels.slice(0, limit).map((parcel) => parcelSummary(parcel, now)) };
}

function asParcel(row: Parameters<typeof withEventPlaces>[0]): ParcelWithEvents {
  return toParcel(withEventPlaces(row) as unknown as ApiPackageRow);
}

/** The account's parcel a tool's `parcel` names: its id, or its tracking number. */
async function ownedParcel(service: SupabaseServiceClient, userId: string, named: string): Promise<ParcelWithEvents | null> {
  const value = named.trim();
  let id: string | null = UUID.test(value) ? value.toLowerCase() : null;
  if (!id) {
    const number = normalizeTrackingNumber(value);
    if (!number) return null;
    id = (await service.accountTrackingNumbers(userId, [number])).get(number) ?? null;
  }
  const row = id ? await service.getOwnedPackage(id, userId) : null;
  return row ? asParcel(row) : null;
}

function answer(value: object, text?: string) {
  return { content: [{ type: 'text' as const, text: text ?? JSON.stringify(value) }], structuredContent: value as Record<string, unknown> };
}

function refusal(text: string) {
  return { content: [{ type: 'text' as const, text }], isError: true };
}

const OUTCOME_TEXT: Record<ParcelAddition['outcome'], string> = {
  added: 'added',
  known: 'already followed',
  refused: 'not added',
  limited: 'not added: today\'s allowance is used up',
  full: 'not added: the account follows as many parcels as it may',
  unavailable: 'not added: try again later',
};

function additionSummary(addition: ParcelAddition) {
  return {
    trackingNumber: addition.trackingNumber,
    carrier: carrierInfo(addition.carrier as Parameters<typeof carrierInfo>[0]).name,
    outcome: addition.outcome,
    ...('packageId' in addition && addition.packageId ? { id: addition.packageId } : {}),
    ...(addition.outcome === 'refused' ? { reason: addition.reason } : {}),
  };
}

/**
 * The MCP server for one request: Peek's tools, as far as the token's scopes allow. Each tool works on the caller's
 * own account only, through the server's owner-checked functions.
 */
export function assistantServer(service: SupabaseServiceClient, caller: AssistantCaller): McpServer {
  const server = new McpServer(
    { name: 'peek', title: 'Peek', version: '1.0.0' },
    {
      instructions: 'Peek follows the user\'s parcels. add_parcels reads tracking numbers out of text, such as a shipping '
        + 'email\'s, and follows them; list_parcels and get_parcel tell where each parcel is.',
    },
  );
  const reads = caller.scopes.includes('parcels:read');
  const writes = caller.scopes.includes('parcels:write');

  if (writes) {
    server.registerTool('add_parcels', {
      title: 'Add parcels',
      description: 'Finds the tracking numbers in a text, such as the shipping part of an order email, and adds each to '
        + 'the user\'s Peek, which then follows it. Send only the shipping part: the tracking numbers, the carrier names '
        + 'next to them and tracking links; never addresses, names or payment details. Peek keeps the numbers and the '
        + 'name, nothing else. A number the user already follows is left as it is. At most 10 numbers a call.',
      inputSchema: z.object({
        text: z.string().min(1).max(MOST_TEXT_LENGTH).describe('Text holding one or more tracking numbers'),
        name: z.string().max(80).optional().describe('What to call the parcel, usually the shop it comes from, such as "IKEA"'),
      }),
      annotations: { readOnlyHint: false, destructiveHint: false, idempotentHint: true, openWorldHint: false },
    }, async ({ text, name }) => {
      const additions = await addParcelsFromText(service, caller.userId, text, name?.trim() ?? '');
      if (!additions.length) {
        return answer({ parcels: [] }, 'No tracking number found. Peek adds numbers whose carrier it recognises; an order number is never one.');
      }
      const parcels = additions.map(additionSummary);
      const lines = additions.map((addition) => `${addition.trackingNumber} (${carrierInfo(addition.carrier as Parameters<typeof carrierInfo>[0]).name}): ${OUTCOME_TEXT[addition.outcome]}`);
      return answer({ parcels }, `${lines.join('\n')}\n\n${JSON.stringify({ parcels })}`);
    });

    server.registerTool('archive_parcel', {
      title: 'Archive a parcel',
      description: 'Moves one of the user\'s parcels to the archive, out of the list of deliveries. The user can restore it in Peek.',
      inputSchema: z.object({ parcel: z.string().min(1).max(100).describe('The parcel\'s id from list_parcels, or its tracking number') }),
      annotations: { readOnlyHint: false, destructiveHint: false, idempotentHint: true, openWorldHint: false },
    }, async ({ parcel: named }) => {
      const parcel = await ownedParcel(service, caller.userId, named);
      if (!parcel) return refusal('No such parcel in this Peek account.');
      if (!parcel.archivedAt) await service.archivePackage(parcel.id);
      return answer({ id: parcel.id, archived: true });
    });
  }

  if (reads) {
    server.registerTool('list_parcels', {
      title: 'List parcels',
      description: 'The user\'s parcels in Peek, most urgent first, with each one\'s status, expected delivery day and '
        + 'latest scan. "active" (the default) is every parcel still on its way; "attention" the ones that need the user; '
        + '"today" the ones expected today.',
      inputSchema: z.object({
        status: z.enum(PARCEL_STATUSES).optional().describe('Which parcels: active (default), attention, today, delivered, archived or all'),
        limit: z.number().int().min(1).max(MOST_LISTED).optional().describe('How many at most; 20 unless given'),
      }),
      annotations: { readOnlyHint: true, openWorldHint: false },
    }, async ({ status, limit }) => answer(await listParcels(service, caller.userId, status, limit)));

    server.registerTool('get_parcel', {
      title: 'Get a parcel',
      description: 'One of the user\'s parcels: its status, expected delivery day, pickup point and the carrier\'s scans, newest first.',
      inputSchema: z.object({ parcel: z.string().min(1).max(100).describe('The parcel\'s id from list_parcels, or its tracking number') }),
      annotations: { readOnlyHint: true, openWorldHint: false },
    }, async ({ parcel: named }) => {
      const parcel = await ownedParcel(service, caller.userId, named);
      if (!parcel) return refusal('No such parcel in this Peek account.');
      const history = sortEventsDesc(parcel.events).slice(0, MOST_EVENTS).map((event) => ({
        time: event.day ?? event.occurredAt,
        status: event.stage,
        description: event.description,
        location: event.location ?? null,
      }));
      return answer({ ...parcelSummary(parcel), history });
    });
  }

  return server;
}

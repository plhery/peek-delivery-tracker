import { generateKeyPairSync } from 'node:crypto';
import { readdirSync, readFileSync } from 'node:fs';
import { join, relative } from 'node:path';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import type { ApiPackageRow } from '../generated/apiContract';
import { stubPeekEnv } from '../test/peekEnv';
import { deliveryEmailContent } from './email/content';
import { withSharedPassports } from './friends';
import { publicParcelResponse, viewerParcel } from './publicParcels';
import {
  DeliveryLiveActivityNotificationService,
  NativePushNotificationService,
  ParcelLinkAlertService,
  WebPushNotificationService,
} from './push';
import { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

// The e-mail's picture has tests of its own: here it is a stand-in whose input is read.
const drawn = vi.hoisted(() => ({ card: vi.fn() }));
vi.mock('./email/card', () => ({ deliveryCard: drawn.card }));

// Synthetic values only. Whatever carries them, a row read for its owner, should never reach anyone else.
const NOTE = 'Synthetic gate code 4711, behind the bins';
const FILE_NAME = 'Synthetic receipt.jpg';
const PRIVATE = /Synthetic gate code|Synthetic receipt|package_notes|package_files|api\/files/;
const parcelId = '7b000000-0000-4000-a000-000000000001';
const fileId = '7b000000-0000-4000-b000-000000000001';

/** What a parcel's row may carry of its note: the embedded rows as stored, and the note and files as its owner is served them. */
const noted = {
  package_notes: { body: NOTE },
  package_files: [{ id: fileId, name: FILE_NAME, content_type: 'image/jpeg', size: 1_024, width: 800, height: 600, created_at: '2026-11-25T09:00:00Z' }],
  note: NOTE,
  files: [{ id: fileId, name: FILE_NAME, content_type: 'image/jpeg', size: 1_024, url: `/api/files/${fileId}?v=full`, thumbnail_url: `/api/files/${fileId}?v=thumb` }],
};

const scan = (stage: string, occurred_at: string) => ({
  id: `${parcelId}-${stage}`, package_id: parcelId, stage, description: stage, location: null, occurred_at, place: null,
});

function parcel(overrides: JsonObject = {}): JsonObject {
  return {
    id: parcelId, tracking_number: 'TESTNOTES0001', label: 'Boots', carrier: 'dhl', created_at: '2026-11-20T08:00:00Z',
    expected_delivery: null, last_status_text: 'Delivered', last_synced_at: '2026-11-25T09:30:00Z', sync_status: 'ok', sync_error: null,
    tracking_url: null, dpd_postcode: null, archived_at: null, notifications_muted: false, carrier_data: {}, current_stage: 'delivered',
    tracking_events: [scan('accepted', '2026-11-21T09:00:00Z'), scan('delivered', '2026-11-25T09:12:00Z')],
    ...noted,
    ...overrides,
  };
}

const alerts = { available: true, vapidPublicKey: `B${'A'.repeat(86)}` };

beforeEach(() => {
  stubPeekEnv();
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'synthetic-service-key');
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('a parcel\'s private note', () => {
  it('is read only by the owner\'s client, the assistants\' reads, the half-typed check and the file route', () => {
    // Every server file that names the note's tables or embeds, and where.
    const root = join(__dirname, '../..');
    const sources = (dir: string): string[] => readdirSync(join(root, dir), { withFileTypes: true, recursive: true })
      .filter((entry) => entry.isFile() && /\.tsx?$/.test(entry.name) && !/\.test\.tsx?$/.test(entry.name))
      .map((entry) => relative(root, join(entry.parentPath, entry.name)));
    const naming = /package_notes|package_files|package_file_content|NOTE_EMBED/;
    const files = [...sources('src/server'), ...sources('app')].filter((file) => naming.test(readFileSync(join(root, file), 'utf8')));
    expect(files.sort()).toEqual(['src/server/parcelNotes.ts', 'src/server/supabase.ts']);

    // In the database client: the method each mention is in.
    const lines = readFileSync(join(root, 'src/server/supabase.ts'), 'utf8').split('\n');
    const method = /^ {2}(?:(?:async|protected|override|private|public|static)\s+)*(\w+)\s*[(<]/;
    const methods = new Set<string>();
    let current = '(imports)';
    for (const line of lines) {
      current = method.exec(line)?.[1] ?? current;
      // A method's doc comment comes before it: the code is what counts.
      if (naming.test(line) && !/^\s*(?:\/\*\*|\*|\/\/)/.test(line)) methods.add(current);
    }
    expect([...methods].sort()).toEqual([
      '(imports)', 'getOwnedPackage', 'listAccountPackages', 'listAddedFromSession', 'packageFile', 'packageFileContent', 'packageSelect',
    ]);

    // Only an assistant asks the service client for a parcel with its note.
    const asking = [...sources('src/server'), ...sources('app')]
      .filter((file) => /\.(?:getOwnedPackage|listAccountPackages)\([^)]*\{\s*note\b/.test(readFileSync(join(root, file), 'utf8')));
    expect(asking).toEqual(['src/server/assistants/tools.ts']);
  });

  it('never reaches a link\'s owner or viewer, a gift\'s recipient or a link\'s preview', async () => {
    const answers = [
      publicParcelResponse({ link: { id: 'k7Qm2xHd9RtW', owner: true, show_number: true, created_at: '2026-11-20T08:00:00Z' }, package: parcel() }, alerts),
      publicParcelResponse({ link: { id: 'k7Qm2xHd9RtW', show_number: true, created_at: '2026-11-20T08:00:00Z' }, package: parcel() }, alerts),
      publicParcelResponse({
        link: { id: 'k7Qm2xHd9RtW', gift: true, gift_words: { note: 'Happy birthday!', from: 'Sam', early: true }, created_at: '2026-11-20T08:00:00Z' },
        package: parcel({ current_stage: 'in_transit', tracking_events: [scan('accepted', '2026-11-21T09:00:00Z')] }),
      }, alerts),
    ];
    for (const answer of answers) expect(JSON.stringify(answer)).not.toMatch(PRIVATE);
    expect(answers[2]!.link.giftWords).toMatchObject({ note: 'Happy birthday!' });

    const service = new SupabaseServiceClient('https://database.example', 'service-key');
    vi.spyOn(service, 'publicParcel').mockResolvedValue({ link: { id: 'k7Qm2xHd9RtW', show_number: false, created_at: '2026-11-20T08:00:00Z' }, package: parcel() });
    vi.spyOn(service, 'request').mockResolvedValue([]);
    const preview = await viewerParcel(service, 'k7Qm2xHd9RtW');
    expect(preview.status).toBe('shown');
    expect(JSON.stringify(preview)).not.toMatch(PRIVATE);
  });

  it('never reaches a friend\'s Passport', async () => {
    const friend = '7b000000-0000-4000-c000-000000000001';
    const owner = '7b000000-0000-4000-c000-0000000000aa';
    const service = new SupabaseServiceClient('https://database.example', 'service-key');
    vi.spyOn(service, 'passportSharers').mockImplementation(async (ids) => new Map(ids.filter((id) => id === friend).map((id) => [id, owner])));
    vi.spyOn(service, 'passportParcels').mockImplementation(async (owners) => new Map(owners.map((account) => [account, [parcel()]])));
    const card = { id: friend, nickname: 'Mila', sharesStats: true, arrivedThisWeek: null };
    const { snapshot } = await withSharedPassports(service, '7b000000-0000-4000-c000-000000000002', { profile: null, ownCard: null, friends: [card] });
    expect(snapshot.friends[0]!.stats).toMatchObject({ deliveredCount: 1 });
    expect(JSON.stringify(snapshot)).not.toMatch(PRIVATE);
  });

  it('never reaches the delivery e-mail or its picture', async () => {
    drawn.card.mockReset().mockResolvedValue({ png: new Uint8Array([0x89, 0x50, 0x4e, 0x47]), ends: { from: 'Hamburg', to: 'Zürich' }, mapped: true });
    const email = await deliveryEmailContent({
      parcel: parcel() as unknown as ApiPackageRow, locale: 'en', timezone: 'Europe/Zurich',
      journeyUrl: `https://peek.example.test/?parcel=${parcelId}`, offUrl: 'https://peek.example.test/email/off#t=made-up', now: new Date('2026-11-25T10:00:00Z'),
    });
    expect(email.subject).toContain('Boots');
    for (const part of [email.subject, email.text, email.html, JSON.stringify(drawn.card.mock.calls)]) expect(part).not.toMatch(PRIVATE);
  });

  it('never reaches a notification, the owner\'s own or a link\'s', () => {
    const now = Date.parse('2026-11-25T10:00:00Z');
    const key = generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
    const native = new NativePushNotificationService({} as never, 'TEAM', 'KEY', key, 'com.example.delivery', () => now / 1_000);
    const web = new WebPushNotificationService({} as never, 'public-key', 'private-key', 'https://delivery.example', () => now);
    const live = new DeliveryLiveActivityNotificationService({} as never, native);
    const link = new ParcelLinkAlertService(web);
    // A notification's row, should it ever carry the note.
    const row: JsonObject = {
      ...noted, label: 'Boots', package_id: parcelId, link_id: 'k7Qm2xHd9RtW', stage: 'delivered', occurred_at: '2026-11-25T09:12:00Z',
      event_created_at: '2026-11-25T09:20:00Z', event_has_time: true, timezone: 'Europe/Zurich', description: 'Delivered',
    };
    const payloads = [web.payload(row), native.eventPayload(row), live.payload(row, 'end'), link.payload(row)];
    expect(JSON.stringify(payloads[0])).toContain('Boots');
    for (const payload of payloads) expect(JSON.stringify(payload)).not.toMatch(PRIVATE);
  });
});

import { afterEach, describe, expect, it, vi } from 'vitest';
import { AssistantAddedPushService, AssistantAddedPushWorker, assistantAddedNotification } from './assistantAddedPush';
import * as observability from './observability';
import { fcmData } from './push';
import { SupabaseError } from './supabase';
import type { JsonObject } from './types';

// Synthetic ids, a made-up assistant and shop.
const parcelID = '33333333-3333-4333-8333-333333333333';
const row = {
  id: 'delivery', lease_token: 'lease', package_id: parcelID, assistant: 'Example Assistant', label: 'Example Shop',
  carrier: 'la-poste', subscription_id: 'browser', locale: 'fr',
};
function fixture(deliveries: JsonObject[] = [row]) {
  const client = {
    claimAssistantAddedPush: vi.fn().mockResolvedValue(deliveries), finishAssistantAddedPush: vi.fn().mockResolvedValue(undefined),
    updatePushSubscription: vi.fn(), updateNativePushDevice: vi.fn(),
  };
  const web = { send: vi.fn().mockResolvedValue(undefined) };
  const native = { send: vi.fn().mockResolvedValue(undefined), isExpired: vi.fn().mockReturnValue(false) };
  return { client, web, native, service: new AssistantAddedPushService(client as never, web as never, native as never) };
}
afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); });

describe('"Added by <assistant>"', () => {
  it.each([
    ['en', 'Added by Example Assistant'], ['fr-FR', 'Ajouté par Example Assistant'], ['de', 'Von Example Assistant hinzugefügt'],
    ['it', 'Aggiunto da Example Assistant'], ['es', 'Añadido por Example Assistant'], ['pt-PT', 'Adicionada por Example Assistant'],
    ['pt-BR', 'Adicionada por Example Assistant'], ['pl', 'Dodano przez Example Assistant'], ['ru', 'Добавлено через Example Assistant'],
    ['zh-CN', '由 Example Assistant 添加'], ['unknown', 'Added by Example Assistant'],
  ])('names the assistant, then the parcel and its carrier, in the device\'s language, and opens the parcel: %s', (locale, title) => {
    const payload = assistantAddedNotification({ ...row, locale, tracking_number: 'PRIVATE', email: 'PRIVATE' });
    expect(payload.web).toMatchObject({ title, body: 'Example Shop · La Poste / Colissimo', badge: '/icons/badge-96.png', tag: `parcel-${parcelID}` });
    expect(payload.web.data).toEqual({ url: `/?parcel=${parcelID}` });
    expect(payload.native).toEqual({ aps: { alert: { title, body: 'Example Shop · La Poste / Colissimo' }, sound: 'default', badge: 1, 'thread-id': parcelID }, parcel_id: parcelID });
    expect(JSON.stringify(payload)).not.toContain('PRIVATE');
  });

  it('shortens a long assistant name, and names the carrier alone for a parcel without a name', () => {
    const long = assistantAddedNotification({ ...row, assistant: `Assistant ${'x'.repeat(100)}`, label: '', locale: 'en' });
    expect([...(long.web.title as string).replace('Added by ', '')]).toHaveLength(40);
    expect(long.web.body).toBe('La Poste / Colissimo');
  });

  it('reaches an Android phone with the parcel to open, as its other notifications do', () => {
    expect(fcmData(assistantAddedNotification({ ...row, locale: 'en' }).native)).toEqual({
      title: 'Added by Example Assistant', body: 'Example Shop · La Poste / Colissimo', parcel_id: parcelID,
    });
  });

  it('claims what this server sends and finishes each delivery with its lease', async () => {
    const { service, client, web, native } = fixture([row, { ...row, id: 'phone', subscription_id: null, device_id: 'iphone', transport: 'apns' }]);
    expect(await service.dispatch()).toEqual({ attempted: 2, sent: 2, failed: 0, expired: 0 });
    expect(client.claimAssistantAddedPush).toHaveBeenCalledWith(true, true, false, false, false);
    expect(web.send).toHaveBeenCalledExactlyOnceWith(row, expect.objectContaining({ title: 'Ajouté par Example Assistant' }));
    expect(native.send).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ device_id: 'iphone' }), expect.objectContaining({ parcel_id: parcelID }));
    expect(client.finishAssistantAddedPush).toHaveBeenCalledWith('delivery', 'lease', true);
    expect(client.finishAssistantAddedPush).toHaveBeenCalledWith('phone', 'lease', true);
  });

  it('claims nothing on a server that sends no notification', async () => {
    const { client } = fixture();
    expect(await new AssistantAddedPushService(client as never, null, null).dispatch()).toMatchObject({ attempted: 0 });
    expect(client.claimAssistantAddedPush).not.toHaveBeenCalled();
  });

  it('runs as its own background job, and reports a refusal under its name', async () => {
    vi.useFakeTimers();
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const refusal = new SupabaseError('Supabase POST request failed (404)', 404, 'PGRST202');
    const dispatch = vi.fn().mockRejectedValueOnce(refusal).mockResolvedValue({});
    const worker = new AssistantAddedPushWorker({ dispatch } as never);
    worker.start(); worker.wake();
    await vi.advanceTimersByTimeAsync(1);
    expect(dispatch).toHaveBeenCalledOnce();
    expect(logged).toHaveBeenCalledExactlyOnceWith('assistant_added_notification_failed', { error_type: 'SupabaseError' }, 'error');
    expect(report).toHaveBeenCalledExactlyOnceWith(refusal, { component: 'assistant-added-notifications', operation: 'dispatch' });
    worker.stop();
  });
});

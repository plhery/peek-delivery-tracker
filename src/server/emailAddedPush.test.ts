import { afterEach, describe, expect, it, vi } from 'vitest';
import { EmailAddedPushService, EmailAddedPushWorker, emailAddedNotification } from './emailAddedPush';
import * as observability from './observability';
import { fcmData } from './push';
import { SupabaseError } from './supabase';
import type { JsonObject } from './types';

// Synthetic ids and a made-up shop.
const parcelID = '22222222-2222-4222-8222-222222222222';
const row = { id: 'delivery', lease_token: 'lease', package_id: parcelID, label: 'Example Shop', carrier: 'la-poste', subscription_id: 'browser', locale: 'fr' };
function fixture(deliveries: JsonObject[] = [row]) {
  const client = {
    claimEmailAddedPush: vi.fn().mockResolvedValue(deliveries), finishEmailAddedPush: vi.fn().mockResolvedValue(undefined),
    updatePushSubscription: vi.fn(), updateNativePushDevice: vi.fn(),
  };
  const web = { send: vi.fn().mockResolvedValue(undefined) };
  const native = { send: vi.fn().mockResolvedValue(undefined), isExpired: vi.fn().mockReturnValue(false) };
  return { client, web, native, service: new EmailAddedPushService(client as never, web as never, native as never) };
}
afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); });

describe('"Added from your email"', () => {
  it.each([
    ['en', 'Added from your email'], ['fr-FR', 'Ajouté depuis ton e-mail'], ['de', 'Aus deiner E-Mail hinzugefügt'],
    ['it', 'Aggiunto dalla tua email'], ['es', 'Añadido desde tu correo'], ['pt-PT', 'Adicionado a partir do teu e-mail'],
    ['pt-BR', 'Adicionado a partir do seu e-mail'], ['pl', 'Dodano z Twojego e-maila'], ['ru', 'Добавлено из вашего письма'],
    ['zh-CN', '已从你的邮件添加'], ['unknown', 'Added from your email'],
  ])('names the parcel and its carrier in the device\'s language, and opens the parcel: %s', (locale, title) => {
    const payload = emailAddedNotification({ ...row, locale, tracking_number: 'PRIVATE', email: 'PRIVATE' });
    expect(payload.web).toMatchObject({ title, body: 'Example Shop · La Poste / Colissimo', badge: '/icons/badge-96.png', tag: `parcel-${parcelID}` });
    expect(payload.web.data).toEqual({ url: `/?parcel=${parcelID}` });
    expect(payload.native).toEqual({ aps: { alert: { title, body: 'Example Shop · La Poste / Colissimo' }, sound: 'default', badge: 1, 'thread-id': parcelID }, parcel_id: parcelID });
    expect(JSON.stringify(payload)).not.toContain('PRIVATE');
  });

  it('names the carrier alone for a parcel without a name, and shortens a long name', () => {
    expect(emailAddedNotification({ ...row, label: '  ', locale: 'en' }).web.body).toBe('La Poste / Colissimo');
    expect(emailAddedNotification({ ...row, label: null, carrier: 'unknown-carrier', locale: 'en' }).web.body).toBe('unknown-carrier');
    const long = emailAddedNotification({ ...row, label: 'x'.repeat(200), locale: 'en' }).web.body as string;
    expect([...long.split(' · ')[0]!]).toHaveLength(80);
  });

  it('reaches an Android phone with the parcel to open, as its other notifications do', () => {
    expect(fcmData(emailAddedNotification({ ...row, locale: 'en' }).native)).toEqual({
      title: 'Added from your email', body: 'Example Shop · La Poste / Colissimo', parcel_id: parcelID,
    });
  });

  it('claims what this server sends and finishes each delivery with its lease', async () => {
    const { service, client, web, native } = fixture([row, { ...row, id: 'phone', subscription_id: null, device_id: 'iphone', transport: 'apns' }]);
    expect(await service.dispatch()).toEqual({ attempted: 2, sent: 2, failed: 0, expired: 0 });
    expect(client.claimEmailAddedPush).toHaveBeenCalledWith(true, true, false, false, false);
    expect(web.send).toHaveBeenCalledExactlyOnceWith(row, expect.objectContaining({ title: 'Ajouté depuis ton e-mail' }));
    expect(native.send).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ device_id: 'iphone' }), expect.objectContaining({ parcel_id: parcelID }));
    expect(client.finishEmailAddedPush).toHaveBeenCalledWith('delivery', 'lease', true);
    expect(client.finishEmailAddedPush).toHaveBeenCalledWith('phone', 'lease', true);
  });

  it('releases a failed send for retry, and disables a browser that is gone', async () => {
    const { service, client, web } = fixture([row, { ...row, id: 'gone', subscription_id: 'old-browser' }]);
    web.send.mockRejectedValueOnce(new Error('offline')).mockRejectedValueOnce({ statusCode: 410 });
    expect(await service.dispatch()).toEqual({ attempted: 2, sent: 0, failed: 1, expired: 1 });
    expect(client.updatePushSubscription).toHaveBeenCalledExactlyOnceWith('old-browser', expect.objectContaining({ disabled_at: expect.any(String) }));
    expect(client.finishEmailAddedPush).toHaveBeenCalledWith('delivery', 'lease', false);
    expect(client.finishEmailAddedPush).toHaveBeenCalledWith('gone', 'lease', false);
  });

  it('claims nothing on a server that sends no notification', async () => {
    const { client } = fixture();
    expect(await new EmailAddedPushService(client as never, null, null).dispatch()).toMatchObject({ attempted: 0 });
    expect(client.claimEmailAddedPush).not.toHaveBeenCalled();
  });

  it('runs as its own background job, and reports a refusal under its name', async () => {
    vi.useFakeTimers();
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const refusal = new SupabaseError('Supabase POST request failed (404)', 404, 'PGRST202');
    const dispatch = vi.fn().mockRejectedValueOnce(refusal).mockResolvedValue({});
    const worker = new EmailAddedPushWorker({ dispatch } as never);
    worker.start(); worker.wake();
    await vi.advanceTimersByTimeAsync(1);
    expect(dispatch).toHaveBeenCalledOnce();
    expect(logged).toHaveBeenCalledExactlyOnceWith('email_added_notification_failed', { error_type: 'SupabaseError' }, 'error');
    expect(report).toHaveBeenCalledExactlyOnceWith(refusal, { component: 'email-added-notifications', operation: 'dispatch' });
    await vi.advanceTimersByTimeAsync(15_001);
    expect(dispatch).toHaveBeenCalledTimes(2);
    worker.stop();
  });
});

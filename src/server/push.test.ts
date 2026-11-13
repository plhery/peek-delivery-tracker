import { generateKeyPairSync } from 'node:crypto';
import { readFileSync } from 'node:fs';
import { resolve } from 'node:path';
import { runInNewContext } from 'node:vm';
import { describe, expect, it, vi } from 'vitest';
import {
  CompositePushNotificationService,
  DeliveryLiveActivityNotificationService,
  NativePushNotificationService,
  PushDispatchError,
  WebPushNotificationService,
} from './push';
import { SUPPORTED_LOCALES } from '../lib/locale';
import type { JsonObject } from './types';

const now = Date.parse('2026-09-07T12:40:00Z');
const privateKey = generateKeyPairSync('ec', { namedCurve: 'prime256v1' })
  .privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
const native = new NativePushNotificationService(
  {} as never, 'TEAM', 'KEY', privateKey, 'com.example.delivery', () => now / 1_000,
);
const web = new WebPushNotificationService(
  {} as never, 'public-key', 'private-key', 'https://delivery.example', () => now,
);
const live = new DeliveryLiveActivityNotificationService({} as never, native);
const delivered: JsonObject = {
  label: 'Coffee beans',
  package_id: 'package-1',
  stage: 'delivered',
  occurred_at: '2026-09-07T12:32:00Z',
  event_created_at: '2026-09-07T12:39:00Z',
  event_has_time: true,
  timezone: 'Europe/Zurich',
  expected_delivery: '2026-09-07',
  expected_delivery_changed: true,
};

function alert(payload: JsonObject): JsonObject {
  return (payload.aps as JsonObject).alert as JsonObject;
}

describe('friendly parcel notifications', () => {
  it.each(['web', 'native', 'live'])('leads with the news, then names the parcel and when it arrived, for %s alerts', (channel) => {
    const payload = channel === 'web' ? web.payload(delivered)
      : alert(channel === 'native' ? native.eventPayload(delivered) : live.payload(delivered, 'end'));
    expect(payload).toMatchObject({
      title: 'It’s arrived', body: 'Coffee beans · delivered at 14:32',
    });
    // Keep tapping the notification linked to the same parcel.
    expect(web.payload(delivered).data).toEqual({ url: '/?parcel=package-1' });
    expect(native.eventPayload(delivered).parcel_id).toBe('package-1');
  });

  it.each([
    ['registered', 'Label created'],
    ['accepted', 'Received by carrier'],
    ['in_transit', 'On its way'],
    ['customs', 'At customs'],
    ['exception', 'Needs attention'],
    ['out_for_delivery', 'Out for delivery'],
    ['ready_for_pickup', 'Ready for pickup'],
    ['failed_attempt', 'Delivery missed'],
    ['returned', 'On its way back'],
    ['something_new', 'Parcel update'],
  ])('titles a %s scan with the news, in its own words or the app\'s', (stage, title) => {
    expect(web.payload({ ...delivered, stage }).title).toBe(title);
    expect(alert(native.eventPayload({ ...delivered, stage })).title).toBe(title);
  });

  it.each([
    ['en', 'Coffee beans · delivered at 14:32'],
    ['de-CH', 'Coffee beans · um 14:32 Uhr zugestellt'],
    ['fr', 'Coffee beans · livré à 14:32'],
    ['it', 'Coffee beans · consegnato alle 14:32'],
    ['es-ES', 'Coffee beans · entregado a las 14:32'],
    ['pt-PT', 'Coffee beans · entregue às 14:32'],
    ['pl-PL', 'Coffee beans · dostarczono o 14:32'],
  ])('localizes delivery sentences for %s devices', (locale, body) => {
    expect(alert(native.eventPayload({ ...delivered, locale })).body).toBe(body);
  });

  it.each([
    ['en', 'Delivered at 14:32', 'Peek is keeping an eye on it'],
    ['de', 'Um 14:32 Uhr zugestellt', 'Peek behält es im Auge'],
    ['pt', 'Entregue às 14:32', 'O Peek está de olho nela'],
  ])('starts the line with a capital when a %s parcel has no name', (locale, arrived, customs) => {
    expect(web.payload({ ...delivered, locale, label: '' }).body).toBe(arrived);
    expect(web.payload({ ...delivered, locale, label: null, stage: 'customs', expected_delivery: null }).body).toBe(customs);
  });

  it('uses the recipient timezone and a safe fallback for an invalid timezone', () => {
    expect(web.payload({ ...delivered, timezone: 'America/New_York' }).body)
      .toBe('Coffee beans · delivered at 08:32');
    expect(web.payload({ ...delivered, timezone: 'Invalid/Zone' }).body)
      .toBe('Coffee beans · delivered at 14:32');
  });

  it('tells a delivery on its scan\'s clock when the account chose no timezone', () => {
    const unchosen = { ...delivered, timezone_chosen: false };
    const pacific = { ...unchosen, event_time: '2026-09-07T05:32:00-07:00' };
    expect(web.payload(pacific).body).toBe('Coffee beans · delivered at 05:32');
    expect(alert(native.eventPayload(pacific)).body).toBe('Coffee beans · delivered at 05:32');
    // The evening before, there: not "at 01:51", which is Zurich's night.
    expect(web.payload({ ...unchosen, occurred_at: '2026-09-06T23:51:00Z', event_time: '2026-09-06T16:51:00-07:00' }).body)
      .toBe('Coffee beans · delivered on 6 September at 16:51');
    // A time in UTC, or without its offset, says nothing of the place: Zurich's clock.
    expect(web.payload({ ...unchosen, event_time: '2026-09-07T12:32:00Z' }).body).toBe('Coffee beans · delivered at 14:32');
    expect(web.payload({ ...unchosen, event_time: '07.09.2026 14:32' }).body).toBe('Coffee beans · delivered at 14:32');
    expect(web.payload({ ...unchosen, event_time: null }).body).toBe('Coffee beans · delivered at 14:32');
    // A time zone the account chose is the one its devices read the app in.
    expect(web.payload({ ...pacific, timezone: 'America/New_York', timezone_chosen: true }).body)
      .toBe('Coffee beans · delivered at 08:32');
  });

  it('lets the device write a delivery\'s time on its own clock, and tells nothing else that way', () => {
    const clock = {
      scan_at: '2026-09-07T12:32:00.000Z',
      body_same_day: 'Coffee beans · delivered at {{time}}',
      body_other_day: 'Coffee beans · delivered on {{date}} at {{time}}',
      date_locale: 'en-CH',
      body_12_hour: 'Coffee beans · delivered at 2:32 pm',
    };
    expect(web.payload(delivered)).toMatchObject({ body: 'Coffee beans · delivered at 14:32', ...clock });
    const app = native.eventPayload(delivered);
    expect(app).toMatchObject({ aps: { 'mutable-content': 1, alert: { body: 'Coffee beans · delivered at 14:32' } }, ...clock });
    expect(web.payload({ ...delivered, label: '', location: 'Zürich' }))
      .toMatchObject({ body_same_day: 'Delivered at {{time}}\nZürich', body_other_day: 'Delivered on {{date}} at {{time}}\nZürich' });
    expect(web.payload({ ...delivered, locale: 'de' })).toMatchObject({
      body_same_day: 'Coffee beans · um {{time}} Uhr zugestellt', date_locale: 'de-CH',
    });
    for (const changes of [{ stage: 'out_for_delivery' }, { event_has_time: false }, { occurred_at: '2026-09-08T12:32:00Z' }]) {
      const payload = web.payload({ ...delivered, ...changes });
      expect(Object.keys(payload)).not.toContain('scan_at');
      expect(payload).not.toHaveProperty('body_12_hour');
      const quiet = native.eventPayload({ ...delivered, ...changes });
      expect(Object.keys(quiet)).not.toContain('scan_at');
      expect(quiet.aps).not.toHaveProperty('mutable-content');
    }
  });

  it('sends both clock formats for a timed delivery estimate', () => {
    const estimate = { ...delivered, stage: 'out_for_delivery', expected_delivery: '2026-09-07 14:00–16:30' };
    const browser = web.payload(estimate);
    expect(browser.body).toContain('14:00–16:30');
    expect(browser.body_12_hour).toContain('2:00 pm–4:30 pm');
    expect(native.eventPayload(estimate)).toMatchObject({
      aps: { 'mutable-content': 1, alert: { body: browser.body } },
      body_12_hour: browser.body_12_hour,
    });
  });

  it('does not call delayed alerts recent, and dates deliveries from a previous day', () => {
    expect(web.payload({ ...delivered, occurred_at: '2026-09-07T09:32:00Z' }).body)
      .toBe('Coffee beans · delivered at 11:32');
    expect(web.payload({ ...delivered, occurred_at: '2026-09-06T12:32:00Z' }).body)
      .toBe('Coffee beans · delivered on 6 September at 14:32');
  });

  it.each([
    { event_has_time: false },
    { event_has_time: undefined },
    { occurred_at: null },
    { occurred_at: 'invalid' },
    { occurred_at: '2026-09-08T12:32:00Z' },
  ])('omits the clock time when the carrier time is unavailable or unreliable: %j', (changes) => {
    expect(web.payload({ ...delivered, ...changes }).body).toBe('Coffee beans · delivered');
  });

  it.each([
    ['ready_for_pickup', 'Coffee beans · waiting at the pickup point'],
    ['failed_attempt', 'Coffee beans · the carrier couldn’t deliver it. Tap to see what happens next.'],
    ['returned', 'Coffee beans · returning to the sender'],
    ['exception', 'Coffee beans · the carrier flagged a problem. Tap to see what to do.'],
  ])('does not repeat an obsolete estimate for %s', (stage, body) => {
    expect(web.payload({ ...delivered, stage }).body).toBe(body);
  });

  it('ends a Live Activity on a reported problem, with the missed-attempt grace period', () => {
    const row = { ...delivered, stage: 'exception', update_token: 'a'.repeat(64) };
    expect(live.deliveryKind(row)).toBe('end');
    const aps = live.payload(row, 'end').aps as JsonObject;
    expect(((aps['content-state'] as JsonObject).parcel as JsonObject).phase).toBe('exception');
    expect(aps['dismissal-date'])
      .toBe((live.payload({ ...row, stage: 'failed_attempt' }, 'end').aps as JsonObject)['dismissal-date']);
  });

  it('gives a parcel out for delivery only the hours the carrier gives for today', () => {
    const row = { ...delivered, stage: 'out_for_delivery', expected_delivery_changed: false };
    const slot = { ...row, expected_delivery: '2026-09-07 14:00–16:00' };
    expect(web.payload(slot).body).toBe('Coffee beans · arriving today, 14:00–16:00');
    expect(alert(live.payload(slot, 'start')).body).toBe(web.payload(slot).body);
    expect(web.payload({ ...row, expected_delivery_from: '2026-09-07T14:00:00+02:00', expected_delivery: '2026-09-07T16:00:00+02:00' }).body)
      .toBe('Coffee beans · arriving today, 14:00–16:00');
    // Out for delivery says today already; a day, or the last day of a window, would contradict it.
    for (const changes of [
      { expected_delivery: '2026-09-07' },
      { expected_delivery: '2026-09-08' },
      { expected_delivery_from: '2026-09-06', expected_delivery: '2026-09-08' },
      { expected_delivery: '2026-09-08 09:00–12:00' },
      // A window of days that ends today: its end time is not today's hours, and the cards drop it too.
      { expected_delivery_from: '2026-09-05', expected_delivery: '2026-09-07T18:00:00+02:00' },
      { expected_delivery: null },
    ]) expect(web.payload({ ...row, ...changes }).body).toBe('Coffee beans · almost there');
  });

  it('says a window of days whole until it begins, then by its last day', () => {
    const row = { ...delivered, stage: 'in_transit', expected_delivery_changed: false };
    const body = (changes: JsonObject) => web.payload({ ...row, ...changes }).body;
    expect(body({ expected_delivery_from: '2026-09-09', expected_delivery: '2026-09-11' }))
      .toBe('Coffee beans · arriving between 9 September and 11 September');
    expect(body({ expected_delivery_from: '2026-09-09', expected_delivery: '2026-09-11', expected_delivery_changed: true }))
      .toBe('Coffee beans · now arriving between 9 September and 11 September');
    expect(body({ expected_delivery_from: '2026-09-07', expected_delivery: '2026-09-11' })).toBe('Coffee beans · arriving by 11 September');
    expect(body({ expected_delivery_from: '2026-09-06', expected_delivery: '2026-09-08' })).toBe('Coffee beans · arriving by tomorrow');
    expect(body({ expected_delivery_from: '2026-09-05T08:00:00+02:00', expected_delivery: '2026-09-07T18:00:00+02:00' }))
      .toBe('Coffee beans · arriving today');
    // Two times on one day are a slot on it.
    expect(body({ expected_delivery_from: '2026-09-08T08:00:00+02:00', expected_delivery: '2026-09-08T12:00:00+02:00' }))
      .toBe('Coffee beans · arriving tomorrow, 08:00–12:00');
    // A start that does not come before the end is not a window.
    expect(body({ expected_delivery_from: '2026-09-12', expected_delivery: '2026-09-11' })).toBe('Coffee beans · arriving 11 September');
  });

  it('reads an estimate on the offset it states when the reader chose no timezone', () => {
    const row = { ...delivered, stage: 'in_transit', expected_delivery_changed: false, timezone_chosen: false };
    const body = (changes: JsonObject) => web.payload({ ...row, ...changes }).body;
    // A slot in New York that ends after Zurich's midnight is one day, there.
    const evening = { expected_delivery_from: '2026-09-07T14:00:00-04:00', expected_delivery: '2026-09-07T20:00:00-04:00' };
    expect(body(evening)).toBe('Coffee beans · arriving today, 14:00–20:00');
    expect(body({ ...evening, stage: 'out_for_delivery' })).toBe('Coffee beans · arriving today, 14:00–20:00');
    expect(body({ expected_delivery_from: '2026-09-08T07:00:00+01:00', expected_delivery: '2026-09-08T09:00:00+01:00' }))
      .toBe('Coffee beans · arriving tomorrow, 07:00–09:00');
    // Today is the estimate's: 18:00 in California is still today there.
    expect(body({ expected_delivery: '2026-09-07T18:00:00-07:00' })).toBe('Coffee beans · arriving today, 18:00');
    // A time in UTC says nothing of the place, and a chosen timezone is the reader's.
    expect(body({ expected_delivery: '2026-09-08T07:00:00Z' })).toBe('Coffee beans · arriving tomorrow, 09:00');
    expect(body({ ...evening, timezone_chosen: true })).toBe('Coffee beans · arriving by tomorrow');
  });

  it('says when the parcel arrives instead of what the step means, once that is known', () => {
    const row = { ...delivered, stage: 'in_transit', expected_delivery_changed: false };
    expect(web.payload({ ...row, expected_delivery: null }).body).toBe('Coffee beans · one step closer');
    expect(web.payload({ ...row, expected_delivery: '2026-09-08' }).body).toBe('Coffee beans · arriving tomorrow');
    expect(web.payload({ ...row, expected_delivery: '2026-09-11' }).body).toBe('Coffee beans · arriving 11 September');
    expect(web.payload({ ...row, expected_delivery: '2026-09-11', expected_delivery_changed: true }).body)
      .toBe('Coffee beans · now arriving 11 September');
  });

  it.each([
    ['de', 'kommt morgen', 'kommt am 11. September', 'neuer Liefertermin: 11. September'],
    ['fr', 'arrive demain', 'arrive le 11 septembre', 'arrive désormais le 11 septembre'],
    ['it', 'arriva domani', 'arrivo previsto: 11 settembre', 'nuova data prevista: 11 settembre'],
    ['es', 'llega mañana', 'llega el 11 de septiembre', 'ahora llega el 11 de septiembre'],
    ['pt', 'chega amanhã', 'chega a 11 de setembro', 'afinal chega a 11 de setembro'],
    ['pt-br', 'chega amanhã', 'chega em 11 de setembro', 'agora chega em 11 de setembro'],
    ['pl', 'dotrze jutro', 'dotrze 11 września', 'nowy termin: 11 września'],
    ['ru', 'прибудет завтра', 'прибудет 11 сентября', 'теперь прибудет 11 сентября'],
    ['zh', '预计送达：明天', '预计送达：9月11日', '预计送达改为：9月11日'],
  ])('fits a day of the year into a %s sentence', (locale, tomorrow, dated, changed) => {
    const row = { ...delivered, locale, stage: 'in_transit', expected_delivery_changed: false };
    expect(web.payload({ ...row, expected_delivery: '2026-09-08' }).body).toBe(`Coffee beans · ${tomorrow}`);
    expect(web.payload({ ...row, expected_delivery: '2026-09-11' }).body).toBe(`Coffee beans · ${dated}`);
    expect(web.payload({ ...row, expected_delivery: '2026-09-11', expected_delivery_changed: true }).body)
      .toBe(`Coffee beans · ${changed}`);
  });

  it.each([
    ['en', 'arriving by tomorrow', 'arriving by 11 September', 'arriving between 9 September and 11 September'],
    ['de', 'kommt bis morgen', 'kommt bis zum 11. September', 'kommt zwischen dem 9. September und dem 11. September'],
    ['fr', 'arrive d’ici demain', 'arrive d’ici le 11 septembre', 'arrive entre le 9 septembre et le 11 septembre'],
    ['it', 'arriva entro domani', 'arriva entro 11 settembre', 'arrivo previsto: 9 settembre – 11 settembre'],
    ['es', 'llega para mañana', 'llega para el 11 de septiembre', 'llega entre el 9 de septiembre y el 11 de septiembre'],
    ['pt', 'chega até amanhã', 'chega até 11 de setembro', 'chega entre 9 de setembro e 11 de setembro'],
    ['pt-br', 'chega até amanhã', 'chega até 11 de setembro', 'chega entre 9 de setembro e 11 de setembro'],
    ['pl', 'dotrze najpóźniej jutro', 'dotrze najpóźniej 11 września', 'dotrze w dniach 9 września – 11 września'],
    ['ru', 'прибудет до завтра', 'прибудет до 11 сентября', 'прибудет с 9 сентября по 11 сентября'],
    ['zh', '预计明天前送达', '预计9月11日前送达', '预计9月9日至9月11日送达'],
  ])('fits a window of days into a %s sentence', (locale, byTomorrow, byDate, ahead) => {
    const row = { ...delivered, locale, stage: 'in_transit', expected_delivery_changed: false };
    const body = (expected_delivery_from: string, expected_delivery: string, changed = false) =>
      web.payload({ ...row, expected_delivery_from, expected_delivery, expected_delivery_changed: changed }).body;
    expect(body('2026-09-06', '2026-09-08')).toBe(`Coffee beans · ${byTomorrow}`);
    expect(body('2026-09-06', '2026-09-11')).toBe(`Coffee beans · ${byDate}`);
    expect(body('2026-09-09', '2026-09-11')).toBe(`Coffee beans · ${ahead}`);
    for (const [from, to] of [['2026-09-06', '2026-09-08'], ['2026-09-06', '2026-09-11'], ['2026-09-09', '2026-09-11']]) {
      const changed = String(body(from!, to!, true));
      expect(changed).not.toMatch(/\{\{|undefined/);
      expect(changed).not.toBe(body(from!, to!));
    }
  });

  it('names a place written in another script by its town, unless the reader reads that script', () => {
    const departed = { ...delivered, stage: 'in_transit', expected_delivery: null, label: '' };
    expect(web.payload({ ...departed, location: '广东省深圳市' }).body).toBe('One step closer\nShenzhen, China');
    expect(alert(native.eventPayload({ ...departed, location: '厦门', locale: 'de' })).body).toMatch(/\nXiamen, China$/u);
    expect(web.payload({ ...departed, location: '中国' }).body).toMatch(/\nChina$/u);
    expect(web.payload({ ...departed, location: '深圳市', locale: 'zh' }).body).toMatch(/\n深圳市$/u);
    expect(web.payload({ ...departed, location: 'МОСКВА', locale: 'fr' }).body).toMatch(/\nMoscow, Russie$/u);
    expect(web.payload({ ...departed, location: 'МОСКВА', locale: 'ru' }).body).toMatch(/\nМОСКВА$/u);
    // Placed nowhere, or written in Latin script, the place stays as written.
    expect(web.payload({ ...departed, location: '台北' }).body).toMatch(/\n台北$/u);
    expect(web.payload({ ...departed, location: 'SHENZHEN' }).body).toMatch(/\nSHENZHEN$/u);
  });

  it('keeps the delivery message readable before a long Unicode location', () => {
    const body = String(web.payload({ ...delivered, location: '😀'.repeat(250) }).body);
    expect(body).toMatch(/^Coffee beans · delivered at 14:32\n😀+…$/u);
    expect([...body].length).toBeLessThanOrEqual(220);
  });

  it('sends the mark\'s eyes as the badge Android reduces to an outline', () => {
    expect(web.payload(delivered)).toMatchObject({ icon: '/icons/icon-192.png', badge: '/icons/badge-96.png' });
  });
});

describe('useful, localized tracking updates', () => {
  it.each(SUPPORTED_LOCALES)('uses the same %s copy on web, iOS and Live Activities', (locale) => {
    for (const stage of ['pending', 'registered', 'accepted', 'in_transit', 'customs', 'exception', 'out_for_delivery', 'ready_for_pickup', 'delivered', 'failed_attempt', 'returned']) {
      for (const expected_delivery of ['2026-09-07', '2026-09-11', null]) {
        const row = { ...delivered, locale, stage, expected_delivery };
        const browser = web.payload(row);
        expect(browser.lang).toBe(locale);
        expect(browser.title).toBe(alert(native.eventPayload(row)).title);
        expect(browser.body).toBe(alert(native.eventPayload(row)).body);
        if (['out_for_delivery', 'delivered', 'ready_for_pickup', 'failed_attempt', 'returned', 'exception'].includes(stage)) {
          expect(alert(live.payload(row, stage === 'out_for_delivery' ? 'start' : 'end'))).toEqual({ title: browser.title, body: browser.body });
        }
        expect(`${String(browser.title)} ${String(browser.body)}`).not.toMatch(/\{\{|undefined|ETA/);
        expect(String(browser.body)).toMatch(/^Coffee beans · \S/);
        if (locale !== 'en') expect(browser.body).not.toBe(web.payload({ ...row, locale: 'en' }).body);
      }
    }
  });

  it('keeps a useful delivery window but removes past or malformed estimates', () => {
    const row = { ...delivered, stage: 'out_for_delivery', locale: 'fr', expected_delivery_changed: false };
    expect(web.payload({ ...row, expected_delivery: '2026-09-07 14:00–16:00' }).body)
      .toBe('Coffee beans · arrive aujourd’hui, 14:00–16:00');
    for (const expected_delivery of ['2026-09-06', 'invalid']) {
      expect(web.payload({ ...row, expected_delivery }).body).toBe('Coffee beans · presque arrivé');
    }
    expect(web.payload({ ...row, expected_delivery: '2026-09-07' }).body).toBe('Coffee beans · presque arrivé');
    // Chinese runs the day on into its window after a space.
    expect(web.payload({ ...row, locale: 'zh', expected_delivery: '2026-09-07 14:00–16:00' }).body).toMatch(/今天 14:00–16:00$/);
  });

  it('writes a Live Activity in the app\'s own words', () => {
    const row = { ...delivered, stage: 'out_for_delivery', locale: 'fr', expected_delivery_changed: false };
    const parcel = (changes: JsonObject) => ((live.payload({ ...row, ...changes }, 'start').aps as JsonObject)['content-state'] as JsonObject).parcel;
    expect(parcel({})).toMatchObject({ label: 'Coffee beans', detail: 'En livraison', status: 'En livraison' });
    expect(parcel({ expected_delivery: '2026-09-07 15:00–18:00' })).toMatchObject({ detail: 'aujourd’hui, 15:00–18:00', status: 'En livraison' });
    expect(parcel({ expected_delivery_from: '2026-09-06', expected_delivery: '2026-09-08' })).toMatchObject({ detail: 'En livraison' });
    expect(parcel({ label: '', stage: 'failed_attempt' })).toMatchObject({ label: 'Colis', status: 'Tentative de livraison' });
  });

  it.each(SUPPORTED_LOCALES)('confirms that notifications are on in %s', async (locale) => {
    const sent: JsonObject[] = [];
    const browser = new WebPushNotificationService({} as never, 'public-key', 'private-key', 'https://delivery.example', () => now);
    vi.spyOn(browser, 'send').mockImplementation(async (_row, payload) => { sent.push(payload!); });
    await browser.sendTest({ locale });
    await browser.sendTest({ locale: 'en' });
    expect(sent[0]).toMatchObject({ lang: locale, badge: '/icons/badge-96.png', data: { url: '/' } });
    expect(String(sent[0]!.title)).toContain('Peek');
    // Chinese says as much in fewer characters.
    expect(String(sent[0]!.body).length).toBeGreaterThan(locale === 'zh' ? 20 : 40);
    if (locale !== 'en') expect(sent[0]!.body).not.toBe(sent[1]!.body);
    expect(sent[1]).toMatchObject({ title: 'You’ll hear from Peek' });
  });
});

describe('service worker fallback copy', () => {
  const worker = readFileSync(resolve(process.cwd(), 'public/push-sw.js'), 'utf8');

  it.each(SUPPORTED_LOCALES)('matches the server %s update copy', (locale) => {
    const listeners: Record<string, (event: unknown) => void> = {};
    const shown: [string, { body: string; lang: string }][] = [];
    runInNewContext(worker, {
      caches: { delete: async () => true },
      self: {
        addEventListener: (type: string, listener: (event: unknown) => void) => { listeners[type] = listener; },
        registration: { showNotification: async (title: string, options: { body: string; lang: string }) => { shown.push([title, options]); } },
      },
    });
    listeners.push!({ data: { json: () => ({ lang: locale }) }, waitUntil: () => undefined });

    const expected = web.payload({ locale, stage: 'update', package_id: 'package-1' });
    expect(shown).toHaveLength(1);
    expect(shown[0]![0]).toBe(expected.title);
    expect(shown[0]![1].body).toBe(expected.body);
    expect(shown[0]![1].lang).toBe(locale);
  });

  it.each([true, false])('writes a delivery on the device clock with 24 hour time set to %s', async (use24HourTime) => {
    const zone = process.env.TZ;
    process.env.TZ = 'America/Los_Angeles';
    try {
      const shown: string[] = [];
      const listeners: Record<string, (event: unknown) => void> = {};
      vi.useFakeTimers({ now, toFake: ['Date'] });
      runInNewContext(worker, {
        Date,
        caches: {
          delete: async () => true,
          match: async () => use24HourTime ? undefined : { text: async () => 'false' },
        },
        self: {
          addEventListener: (type: string, listener: (event: unknown) => void) => { listeners[type] = listener; },
          registration: { showNotification: async (_title: string, options: { body: string }) => { shown.push(options.body); } },
        },
      });
      const show = (payload: JsonObject) => new Promise<void>((resolve, reject) => {
        listeners.push!({ data: { json: () => payload }, waitUntil: (work: Promise<unknown>) => { void work.then(() => resolve(), reject); } });
      });
      await show(web.payload(delivered));
      // The evening before, there, though it is the same day in Zurich.
      await show(web.payload({ ...delivered, occurred_at: '2026-09-06T23:51:00Z' }));
      await show(web.payload({ ...delivered, locale: 'de', occurred_at: '2026-09-06T23:51:00Z' }));
      await show({ ...web.payload(delivered), scan_at: '2026-09-07T12:50:00Z' });
      await show({ ...web.payload(delivered), date_locale: 'not a language' });
      const eta = web.payload({ ...delivered, stage: 'out_for_delivery', expected_delivery: '2026-09-07 14:00–16:30' });
      await show(eta);
      expect(shown.map((message) => message.toLowerCase())).toEqual(use24HourTime ? [
        'Coffee beans · delivered at 05:32',
        'Coffee beans · delivered on 6 September at 16:51',
        'Coffee beans · am 6. September um 16:51 Uhr zugestellt',
        // A scan the device's clock calls the future is told as the service wrote it.
        'Coffee beans · delivered at 14:32',
        'Coffee beans · delivered at 05:32',
        eta.body,
      ].map((message) => String(message).toLowerCase()) : [
        'coffee beans · delivered at 5:32 am',
        'coffee beans · delivered on 6 september at 4:51 pm',
        'coffee beans · am 6. september um 4:51 pm uhr zugestellt',
        'coffee beans · delivered at 2:32 pm',
        'coffee beans · delivered at 5:32 am',
        String(eta.body_12_hour).toLowerCase(),
      ]);
    } finally {
      vi.useRealTimers();
      if (zone === undefined) delete process.env.TZ;
      else process.env.TZ = zone;
    }
  });

  it('tells open windows to show the new state at once', async () => {
    const listeners: Record<string, (event: unknown) => void> = {};
    const postMessage = vi.fn();
    let settled: Promise<unknown> = Promise.resolve();
    runInNewContext(worker, {
      caches: { delete: async () => true },
      self: {
        addEventListener: (type: string, listener: (event: unknown) => void) => { listeners[type] = listener; },
        registration: { showNotification: async () => undefined },
        clients: { matchAll: async () => [{ postMessage }] },
      },
    });
    listeners.push!({ data: { json: () => ({}) }, waitUntil: (promise: Promise<unknown>) => { settled = promise; } });
    await settled;
    expect(postMessage).toHaveBeenCalledWith({ type: 'sdt:server-update' });
  });
});

describe('push channel isolation', () => {
  it('still sends browser and iPhone alerts when the Live Activity queue fails', async () => {
    const summary = (sent: number) => ({ attempted: sent, sent, failed: 0, expired: 0 });
    const outage = new Error('relation "pending_live_activity_events" does not exist');
    const composite = new CompositePushNotificationService(
      { dispatch: async () => summary(2) } as never,
      { dispatch: async () => summary(1) } as never,
      { dispatch: async () => { throw outage; } } as never,
    );

    const error = await composite.dispatch().catch((reason: unknown) => reason);
    expect(error).toBeInstanceOf(PushDispatchError);
    expect((error as PushDispatchError).errors).toEqual([outage]);
    expect((error as PushDispatchError).summary).toEqual({ ...summary(3), sentByChannel: { web: 2, native: 1 } });
  });
});

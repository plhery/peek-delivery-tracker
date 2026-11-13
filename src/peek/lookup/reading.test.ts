import { describe, expect, it } from 'vitest';
import { checkDigitTrouble, isContainerNumber, labelledNumbersInText, looksLikeNumber, nonTrackingNote, nonTrackingShape, readText } from './reading';
import type { Translate } from '../../lib/messages';
import { isPickupCode, normalizeTrackingNumber } from '../../lib/carriers';
import rules from '../../../shared/reading.json';

// Every number here is fictional.
describe('reading the field', () => {
  it('reads one number out of a number, a link and a message', () => {
    expect(readText('1ZDEMO202600000001').match).toMatchObject({ trackingNumber: '1ZDEMO202600000001', carrier: 'ups', source: 'number' });
    expect(readText('https://www.dhl.com/ch-en/home/tracking.html?tracking-id=1234567899&submit=1').match)
      .toMatchObject({ trackingNumber: '1234567899', carrier: 'dhl', source: 'link' });
    expect(readText('Your parcel 99.34.123456.78901234 is on its way').match)
      .toMatchObject({ trackingNumber: '99.34.123456.78901234', carrier: 'swiss-post', source: 'text' });
    expect(readText('  ').match.trackingNumber).toBe('');
    // Six letters typed alone are a GLS Track ID; in a message they are a word.
    expect(readText('ABCDEF').match).toMatchObject({ trackingNumber: 'ABCDEF', source: 'number', confidence: 'low' });
  });

  it('takes the one number a message introduces even when its shape proves no carrier', () => {
    const message = 'Hi Alex, good news: your order has shipped!\nTrack it with DHL: 1234567899\nExpected delivery: Wednesday';
    expect(readText(message).match).toMatchObject({ trackingNumber: '1234567899', carrier: 'unknown', confidence: 'low', source: 'text' });
  });

  // The iPhone's Add sheet lists the same numbers.
  it.each(rules.several)('lists every certain number in a text once, in the order written: $text', (example) => {
    expect(readText(example.text).numbers.map(({ normalized, match }) => ({ number: normalized, carrier: match.carrier }))).toEqual(example.numbers);
  });

  // The iPhone's Add sheet replays the same examples.
  it.each(rules.examples)('reads a message’s number, or a shop’s order number: $text', (example) => {
    const reading = readText(example.text);
    expect(reading.order).toBe(example.shop);
    if ('number' in example) expect(reading.match.trackingNumber).toBe(example.number);
    if ('carrier' in example) expect(reading.match).toMatchObject({ carrier: example.carrier, confidence: 'high' });
    if ('pickup' in example) expect(isPickupCode(normalizeTrackingNumber(reading.match.trackingNumber))).toBe(example.pickup);
  });

  it('lists the numbers a label calls tracking numbers though their shape proves no carrier', () => {
    const labelled = (text: string) => labelledNumbersInText(text).map(({ normalized, match }) => `${normalized} ${match.confidence}`);
    expect(labelled('Sendungsnummer 1234567899\nVotre colis 01234567890123 sera livré demain\nTracking: 1234567899'))
      .toEqual(['1234567899 low', '01234567890123 low']);
    expect(labelled('Tracking number: 1234567899 | Parcel number: 9987654321')).toEqual(['1234567899 low', '9987654321 low']);
    for (const text of [
      // A certain number is listed with the certain ones.
      'Tracking number: 1ZDEMO202600000001',
      // No label, or another number's label.
      'Call us on 0441234567', 'Order number: 12345678', 'Numéro de client : 4455667788',
      // An order number, a date, a code too short for a tracking number, and a reference no carrier's shape fits.
      'Parcel PO-000 12345678901234567', 'Sendung 12.10.2026', 'Parcel 123456', 'Numéro de suivi : zzqx-000-000000000',
      // A date written year first.
      "Date d'envoi : 2026-11-12", 'Fecha de envío: 2026-11-12', 'Tracking: 2026.11.12', 'Sendung 2026/1/5',
      // A link's number is the link's.
      'Track your parcel https://example.test/track?id=1234567899',
    ]) expect(labelled(text), text).toEqual([]);
  });

  it('sets a typed number in the label’s type, and prose as prose', () => {
    expect(looksLikeNumber('1ZDEMO2026')).toBe(true);
    expect(looksLikeNumber('99.34.123456.78901234')).toBe(true);
    expect(looksLikeNumber('1Z 999 AA1 01')).toBe(true);
    expect(looksLikeNumber('Your parcel 1234567899')).toBe(false);
    expect(looksLikeNumber('https://example.test/1234567899')).toBe(false);
    expect(looksLikeNumber('')).toBe(false);
  });
});

// The iPhone's Add sheet asks about the same numbers.
describe('check digits', () => {
  it.each(rules.checkDigits)('questions a postal number whose check digit does not add up: $number', (example) => {
    expect(checkDigitTrouble(example.number)).toEqual(example.trouble ? { suggestion: example.suggestion ?? null } : null);
  });
});

describe('a looked-up number no carrier answered for', () => {
  it.each([
    // Amazon's order number without its hyphens, in one of its stores or for a digital order.
    ['30245719832294617', { kind: 'order', shop: 'amazon' }],
    ['11245719832294617', { kind: 'order', shop: 'amazon' }],
    ['D0112345671234567', { kind: 'order', shop: 'amazon' }],
    ['3041234567890123', { kind: 'order', shop: 'aliexpress' }],
    ['8121234567890123', { kind: 'order', shop: 'aliexpress' }],
    // The door's own shops, as a lookup stores their numbers.
    ['PO00012345678901234567', { kind: 'order', shop: 'temu' }],
    ['GSH0A1B2C3D4E5F', { kind: 'order', shop: 'shein' }],
    ['261025ABCD1234', { kind: 'order', shop: 'shopee' }],
    ['10293', { kind: 'short' }],
    ['AB12C3', { kind: 'short' }],
    // Synthetic shipping container numbers whose ISO 6346 check digit adds up, one through its remainder of 10.
    ['TEMU1234565', { kind: 'container' }],
    ['ZZZU0000005', { kind: 'container' }],
    ['TEMU0000080', { kind: 'container' }],
    // 17 or 16 digits Amazon's or AliExpress's don't start with, and the shapes of carriers' numbers.
    ['99945719832294617', null],
    ['1234567890123456', null],
    ['12345678', null],
    ['EOB10293847561029384', null],
    // A container's shape whose check digit doesn't add up, and four letters and seven digits that aren't a freight
    // container's although their check digit does.
    ['TEMU1234567', null],
    ['ZZZJ0000008', null],
    ['ABCD1234560', null],
    // Synthetic numbers that start like UPS's or J&T Australia's, with a character missing or one too many.
    ['1Z999AA1012345678', { kind: 'mistyped', carrier: 'ups', change: 'missing' }],
    ['1Z999AA101234567840', { kind: 'mistyped', carrier: 'ups', change: 'extra' }],
    ['JTAU12345678901', { kind: 'mistyped', carrier: 'j-and-t-au', change: 'missing' }],
    // UPS's own length, and a DHL rule that takes numbers of several lengths: neither says the number is mistyped.
    ['1Z999AA10123456784', null],
    ['JJD0000000000000000', null],
    // Two characters short, and a character short of two carriers' numbers at once.
    ['1Z999AA10123456', null],
    ['JTE12345678901', null],
  ])('%s', (number, shape) => {
    expect(nonTrackingShape(number)).toEqual(shape);
  });

  it('says where the tracking number is for each shape', () => {
    const t = ((key: string, values?: Record<string, string>) => `${key}${values?.shop ? `:${values.shop}` : ''}`) as Translate;
    expect(nonTrackingNote({ kind: 'order', shop: 'aliexpress' }, t)).toBe('link.notTracking.order:AliExpress');
    expect(nonTrackingNote({ kind: 'container' }, t)).toBe('link.notTracking.container');
    expect(nonTrackingNote({ kind: 'short' }, t)).toBe('link.notTracking.short');
  });

  it('names the carrier a mistyped number starts like', () => {
    const t = ((key: string, values?: Record<string, string>) => `${key}:${values?.carrier}`) as Translate;
    expect(nonTrackingNote({ kind: 'mistyped', carrier: 'ups', change: 'missing' }, t, 'en')).toBe('link.notTracking.missing:UPS');
    expect(nonTrackingNote({ kind: 'mistyped', carrier: 'ups', change: 'extra' }, t, 'en')).toBe('link.notTracking.extra:UPS');
  });

  it('reads a container number only as normalized', () => {
    expect(isContainerNumber('TEMU1234565')).toBe(true);
    expect(isContainerNumber('temu1234565')).toBe(false);
    expect(nonTrackingShape(normalizeTrackingNumber('temu 123456-5'))).toEqual({ kind: 'container' });
  });
});

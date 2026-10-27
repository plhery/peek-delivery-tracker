import { describe, expect, it } from 'vitest';
import { checkDigitTrouble, looksLikeNumber, nonTrackingShape, readText } from './reading';
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
    // 17 or 16 digits Amazon's or AliExpress's don't start with, and the shapes of carriers' numbers.
    ['99945719832294617', null],
    ['1234567890123456', null],
    ['12345678', null],
    ['EOB10293847561029384', null],
  ])('%s', (number, shape) => {
    expect(nonTrackingShape(number)).toEqual(shape);
  });
});

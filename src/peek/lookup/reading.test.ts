import { describe, expect, it } from 'vitest';
import { checkDigitTrouble, looksLikeNumber, numbersInText, readText, uncertainNumberInText } from './reading';

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
    expect(readText('Thanks for your order! We’ll let you know as soon as it ships.').match.trackingNumber).toBe('');
  });

  it('takes the one number a message introduces even when its shape proves no carrier', () => {
    const message = 'Hi Alex, good news: your order has shipped!\nTrack it with DHL: 1234567899\nExpected delivery: Wednesday';
    expect(readText(message).match).toMatchObject({ trackingNumber: '1234567899', carrier: 'unknown', confidence: 'low', source: 'text' });
    expect(uncertainNumberInText('Tracking number: 01234567890123')?.trackingNumber).toBe('01234567890123');
    // Two candidates: the one on the line that speaks of tracking.
    expect(uncertainNumberInText('Call us on 0441234567.\nYour parcel number is 1234567899.')?.trackingNumber).toBe('1234567899');
    // Two candidates and nothing to tell them apart: none is taken.
    expect(uncertainNumberInText('1234567899 or 9987654321')).toBeNull();
    // A word is never the number, though a carrier's rule may fit its length.
    expect(readText('Order confirmation').match.trackingNumber).toBe('');
    expect(uncertainNumberInText('Your registration is complete. Parcel number 1234567899')?.trackingNumber).toBe('1234567899');
    expect(uncertainNumberInText('Order 302-4571983-2294617')).toBeNull();
    // Nor is the part of a Temu order number after its PO, which leaves the tracking number beside it alone.
    expect(uncertainNumberInText('Track it with DHL: 1234567899 (order PO-000-12345678901234567)')?.trackingNumber).toBe('1234567899');
  });

  it('lists every certain number in a text once, in the order written, links included', () => {
    const text = 'Your order ships in 3 parcels:\nUPS 1ZDEMO202600000001\nUPS 1ZDEMO202600000029\nSwiss Post 99.34.123456.78901234\nagain: 1zdemo202600000001';
    expect(numbersInText(text).map(({ normalized, match }) => [normalized, match.carrier])).toEqual([
      ['1ZDEMO202600000001', 'ups'], ['1ZDEMO202600000029', 'ups'], ['993412345678901234', 'swiss-post'],
    ]);
    expect(readText(text).numbers).toHaveLength(3);
    expect(numbersInText('https://www.dhl.com/track?tracking-id=1234567899 and 1ZDEMO202600000001').map(({ normalized }) => normalized))
      .toEqual(['1234567899', '1ZDEMO202600000001']);
    // One number is not a choice.
    expect(readText('1ZDEMO202600000001').numbers).toEqual([]);
    // Numbers on neighbouring lines never run together.
    expect(numbersInText('RR123456785CH\nLX123456785DE').map(({ normalized }) => normalized)).toEqual(['RR123456785CH', 'LX123456785DE']);
  });

  it('tells an Amazon order number from a tracking number', () => {
    expect(readText('302-4571983-2294617')).toMatchObject({ order: 'amazon' });
    expect(readText('Order #302-4571983-2294617 is confirmed').order).toBe('amazon');
    expect(readText('D01-1234567-1234567').order).toBe('amazon');
    expect(readText('30245719832294617').order).toBeNull();
    // A real number beside the order number wins.
    expect(readText('Order 302-4571983-2294617, tracking 1ZDEMO202600000001')).toMatchObject({ order: null, match: { carrier: 'ups' } });
  });

  it('tells a Shein order number from a tracking number', () => {
    for (const text of ['GSH0A1B2C3D4E5F', 'gsh0a1b2c3d4e5f', 'Your Shein order GSH0A1B2C3D4E5F has shipped']) {
      expect(readText(text).order, text).toBe('shein');
    }
    // A character too many or too few is no Shein order number.
    expect(readText('GSH0A1B2C3D4E5F6').order).toBeNull();
    expect(readText('GSH0A1B2C3D4E5').order).toBeNull();
  });

  it('tells a Temu order number from a tracking number, however it is typed', () => {
    for (const text of ['PO-000-00000000000000000', 'PO00000000000000000000', 'po-000-00000000000000000', 'PO 000 00000000000000000',
      'Your Temu order confirmation (#PO-000-12345678901234567)', 'Order PO-000-12345678901234567\nThanks for shopping with us']) {
      expect(readText(text).order, text).toBe('temu');
    }
    // A digit too many or too few, or no PO, is no Temu order number.
    expect(readText('PO-000-000000000000000000').order).toBeNull();
    expect(readText('000-00000000000000000').order).toBeNull();
    expect(readText('XPO-000-00000000000000000').order).toBeNull();
    // A carrier's number that starts with PO stays a tracking number: here Australia Post's, a digit short of Temu's.
    expect(readText('PO0000000000000000000')).toMatchObject({ order: null, match: { carrier: 'australia-post', confidence: 'high' } });
    expect(readText('Order PO-000-00000000000000000, tracking 1ZDEMO202600000001')).toMatchObject({ order: null, match: { carrier: 'ups' } });
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

describe('check digits', () => {
  it('suggests the digit a letter was read for', () => {
    expect(checkDigitTrouble('LX1234567B5DE')).toEqual({ suggestion: 'LX123456785DE' });
    expect(checkDigitTrouble('lx 1234567b5 de')).toEqual({ suggestion: 'LX123456785DE' });
    expect(checkDigitTrouble('RR12345678SCH')).toEqual({ suggestion: 'RR123456785CH' });
    // A digit where a letter stands.
    expect(checkDigitTrouble('1X123456785DE')).toEqual({ suggestion: 'IX123456785DE' });
    expect(checkDigitTrouble('LX123456785D3')).toBeNull();
  });

  it('asks without guessing when a digit is wrong: many corrections would add up', () => {
    expect(checkDigitTrouble('LX123456789DE')).toEqual({ suggestion: null });
    expect(checkDigitTrouble('LX123465785DE')).toEqual({ suggestion: null });
  });

  it('leaves alone what adds up, and what is not a postal number', () => {
    expect(checkDigitTrouble('LX123456785DE')).toBeNull();
    expect(checkDigitTrouble('1ZDEMO202600000001')).toBeNull();
    expect(checkDigitTrouble('1234567899')).toBeNull();
    expect(checkDigitTrouble('DEMOGLS202600')).toBeNull();
    // Letters that stand for no digit make it another kind of number.
    expect(checkDigitTrouble('LX12X4567Y5DE')).toBeNull();
  });
});

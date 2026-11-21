import { describe, expect, it } from 'vitest';
import { trackingNumbersInText } from './parcelsFromText';

// Every number here is made up, but UPS's published sample.
const UPS = '1Z999AA10123456784';
const read = (...args: Parameters<typeof trackingNumbersInText>) => trackingNumbersInText(...args)
  .map(({ normalized, match }) => `${normalized} ${match.carrier}`);

describe('the numbers a text names, by how far its sender is trusted', () => {
  it('reads an email for the numbers a carrier claims or a label names, never for one standing alone', () => {
    expect(read(`Shipped with UPS: ${UPS}`)).toEqual([`${UPS} ups`]);
    expect(read('Tracking number: 1234567899')).toEqual(['1234567899 unknown']);
    expect(read('1234567899')).toEqual([]);
    expect(read('Tracking ID: AB12CD34EF')).toEqual([]);
  });

  it('takes a text an assistant sends that is one number alone, when its shape fits a carrier', () => {
    expect(read('1234567899', [], 'alone')).toEqual(['1234567899 unknown']);
    expect(read(' 1234 5678 9012 ', [], 'alone')).toEqual(['123456789012 unknown']);
    for (const text of [
      // A shape no carrier's fits needs its carrier named.
      'AB12CD34EF', 'Tracking ID: AB12CD34EF',
      // A sentence, a word, a date, a link, a code too short and an Amazon order number are no number alone.
      'your order 12345678 shipped', 'additional', '2026-11-21', '21.11.2026', 'https://example.test/track/1234567899', '123456', '123-1234567-1234567',
    ]) expect(read(text, [], 'alone'), text).toEqual([]);
  });

  it('takes a number whatever its shape when the assistant names its carrier', () => {
    expect(read('AB12CD34EF', ['jp-home'], 'named')).toEqual(['AB12CD34EF jp-home']);
    expect(read('Tracking ID: AB12CD34EF', ['jp-home'], 'named')).toEqual(['AB12CD34EF jp-home']);
    expect(read('123456', ['jp-home'], 'named')).toEqual(['123456 jp-home']);
    expect(read('1234567899', ['jp-home'], 'named')).toEqual(['1234567899 jp-home']);
    // A number another carrier claims stays that carrier's.
    expect(read(UPS, ['jp-home'], 'named')).toEqual([`${UPS} ups`]);
    // Still no order number, no sentence and no link.
    for (const text of ['123-1234567-1234567', 'Order 123-1234567-1234567', 'your order 12345678 shipped', 'https://example.test/track/AB12CD34EF']) {
      expect(read(text, ['jp-home'], 'named'), text).toEqual([]);
    }
  });
});

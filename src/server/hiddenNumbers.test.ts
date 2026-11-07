import { describe, expect, it } from 'vitest';
import { numberMask, parcelNumbers } from './hiddenNumbers';

// Synthetic numbers only: no carrier issued any of these.
describe('the numbers a parcel hides', () => {
  it('are its own and every one its carrier data keeps under a number key, lists aside', () => {
    expect(parcelNumbers({
      tracking_number: 'TESTPARCEL983456',
      carrier_data: {
        active_tracking_number: 'TESTACTIVE1234', international_tracking_number: 'TESTINTL5678', universal_input: { number: 'TESTINPUT9012' },
        routing: { preferred_number: 'TESTPREF3456', cleared_confirmation: { carrier: 'dhl', number: 'TESTCLEAR7890' }, attempts: 3 },
        upu_history: { events: [{ number: 'TESTLISTED1234' }] }, sender_name: 'TESTSHOP1234', weight_number: 2,
      },
    })).toEqual(['TESTPARCEL983456', 'TESTACTIVE1234', 'TESTINTL5678', 'TESTINPUT9012', 'TESTPREF3456', 'TESTCLEAR7890']);
    expect(parcelNumbers({ tracking_number: null, carrier_data: null })).toEqual([]);
  });

  it('are found after a label naming a number and in a web address\'s parameters, only with six letters and digits and a digit', () => {
    const texts = [
      'Tracking number: TESTONE123456', 'tracking #TESTTWO123456', 'AWB 123-45678901', 'Tracking ID: TESTTHREE1234',
      'Waybill no. TESTFOUR123456', 'https://track.example/?id=TESTFIVE123456', 'Tracking No.: SHRT1', 'tracking now TESTSIX123456',
    ];
    const mask = numberMask([], texts);
    expect(texts.map(mask)).toEqual([
      'Tracking number: ••• 456', 'tracking #••• 456', 'AWB ••• 01', 'Tracking ID: ••• 234',
      'Waybill no. ••• 456', 'https://track.example/?id=••• 456', 'Tracking No.: SHRT1', 'tracking now TESTSIX123456',
    ]);
    // Each one found is masked wherever the parcel's words repeat it.
    expect(mask('Sorted: 12345678901, testone-123456')).toBe('Sorted: ••• 01, ••• 456');
  });

  it('mask the longest first, and nothing without a number to mask', () => {
    expect(numberMask(['TEST123456', 'TEST12345678'], [])('TEST12345678 and TEST123456')).toBe('••• 678 and ••• 56');
    expect(numberMask([null, 42, ''], ['Delivered'])('Delivered TEST123456')).toBe('Delivered TEST123456');
  });
});

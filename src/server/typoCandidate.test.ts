import { afterEach, describe, expect, it, vi } from 'vitest';
import { detectCarrierMatch } from 'universal-parcel-scraper';
import * as metrics from './metrics';
import { doubled, s10 } from '../test/trackingNumbers';
import { noteTypoCandidate, typoCandidate } from './typoCandidate';

afterEach(() => vi.restoreAllMocks());

describe('typo candidates', () => {
  it('reads an S10 number with one digit typed twice as its carrier\'s', () => {
    const typed = doubled(`RR${s10('12345678')}FR`, 5);
    expect(typed).toMatch(/^RR\d{10}FR$/);
    expect(typoCandidate(typed)).toEqual({ checksum: 's10', carrier: 'la-poste' });
    expect(typoCandidate(`${typed.slice(0, 2).toLowerCase()} ${typed.slice(2, 7)}-${typed.slice(7)}`))
      .toEqual({ checksum: 's10', carrier: 'la-poste' });
    // A carrier whose high-confidence rule shares the check with a low-confidence one.
    expect(typoCandidate(doubled(`RA${s10('20481357')}IT`, 9))).toEqual({ checksum: 's10', carrier: 'poste-italiane' });
    // Fourteen characters, which no rule takes, of a German item.
    expect(typoCandidate(doubled(`CP${s10('12345678')}DE`, 5))).toEqual({ checksum: 's10', carrier: 'dhl' });
  });

  it('reads other high-confidence check digits too', () => {
    const ups = Array.from({ length: 10 }, (_, digit) => `1Z5R7W24031862795${digit}`)
      .find((number) => detectCarrierMatch(number).carrier === 'ups')!;
    expect(typoCandidate(doubled(ups, 9))).toEqual({ checksum: 'ups', carrier: 'ups' });
  });

  it('leaves out numbers a check digit already accepts, or that no high-confidence check digit backs', () => {
    // Valid as typed, doubled digits and all.
    expect(typoCandidate(`RR${s10('11223344')}FR`)).toBeNull();
    // No digit typed twice.
    expect(typoCandidate('RR1234567890FR')).toBeNull();
    // Only a low-confidence rule names this suffix's check digit.
    expect(typoCandidate(doubled(`LP${s10('31415926')}TY`, 6))).toBeNull();
    // A high-confidence rule that checks no digit.
    expect(typoCandidate('JJDA00123456789')).toBeNull();
  });

  it('logs and counts the checksum, carrier, origin and first check, and logs the number', () => {
    const log = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const counted = vi.spyOn(metrics, 'recordTypoCandidate').mockImplementation(() => undefined);
    const typed = doubled(`RR${s10('12345678')}FR`, 5);
    const answered = doubled(`CP${s10('12345678')}DE`, 5);
    noteTypoCandidate(typed, 'lookup', 'unanswered');
    noteTypoCandidate('RR1234567890FR', 'account', 'unanswered');
    noteTypoCandidate(answered, 'lookup', 'answered');
    expect(counted.mock.calls).toEqual([['s10', 'la-poste', 'lookup', 'unanswered'], ['s10', 'dhl', 'lookup', 'answered']]);
    expect(log).toHaveBeenCalledTimes(2);
    const lines = log.mock.calls.map(([line]) => JSON.parse(String(line)));
    expect(lines).toEqual([
      { timestamp: expect.any(String), level: 'info', event: 'typo_candidate', tracking_number: typed, checksum: 's10', carrier: 'la-poste',
        origin: 'lookup', first_check: 'unanswered', number_pattern: expect.any(String) },
      { timestamp: expect.any(String), level: 'info', event: 'typo_candidate', tracking_number: answered, checksum: 's10', carrier: 'dhl',
        origin: 'lookup', first_check: 'answered', number_pattern: expect.any(String) },
    ]);
  });

  it('never lets telemetry fail a check', () => {
    vi.spyOn(metrics, 'recordTypoCandidate').mockImplementation(() => { throw new Error('registry'); });
    expect(() => noteTypoCandidate(doubled(`RR${s10('12345678')}FR`, 5), 'account', 'unanswered')).not.toThrow();
  });
});

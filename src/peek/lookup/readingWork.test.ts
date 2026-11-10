import { beforeEach, describe, expect, it, vi } from 'vitest';
import { detectCarrierMatch, parseTrackingInput } from '../../lib/carriers';
import { MOST_CANDIDATES_READ, MOST_LINKS_READ, numbersInText } from './reading';

// The work a text costs, counted through the detection it asks for. Every number here is fictional.
vi.mock('../../lib/carriers', async (importOriginal) => {
  const actual = await importOriginal<typeof import('../../lib/carriers')>();
  return { ...actual, detectCarrierMatch: vi.fn(actual.detectCarrierMatch), parseTrackingInput: vi.fn(actual.parseTrackingInput) };
});

const detections = vi.mocked(detectCarrierMatch);
const parses = vi.mocked(parseTrackingInput);
const letters = (value: number) => value.toString(26).replace(/./g, (digit) => String.fromCharCode(97 + parseInt(digit, 26)));

beforeEach(() => {
  detections.mockClear();
  parses.mockClear();
});

describe('the work a text costs', () => {
  it('reads a word or a link met again once', () => {
    expect(numbersInText(Array(3000).fill('ab1d').join(' '))).toEqual([]);
    expect(detections).toHaveBeenCalledTimes(1);
    expect(numbersInText(Array(600).fill('https://example.test/track/abcd').join('\n'))).toEqual([]);
    expect(parses).toHaveBeenCalledTimes(1);
  });

  it('asks about no word that holds no digit', () => {
    expect(numbersInText(Array.from({ length: 900 }, (_, index) => `w${letters(index)}`).join(' '))).toEqual([]);
    expect(detections).not.toHaveBeenCalled();
  });

  it('reads no more than its share of distinct candidates and links', () => {
    expect(numbersInText(Array.from({ length: 2000 }, (_, index) => String(1_000_000 + index * 7)).join(' '))).toEqual([]);
    expect(detections.mock.calls.length).toBeLessThanOrEqual(MOST_CANDIDATES_READ);
    expect(numbersInText(Array.from({ length: 500 }, (_, index) => `https://e${index}.example.test/a`).join(' '))).toEqual([]);
    expect(parses).toHaveBeenCalledTimes(MOST_LINKS_READ);
  });

  it('still reads a number after a long message’s words', () => {
    const words = Array.from({ length: 900 }, (_, index) => `w${letters(index)}`);
    const lines = Array.from({ length: 30 }, (_, line) => words.slice(line * 30, line * 30 + 30).join(' '));
    expect(numbersInText([...lines, 'Your parcel 1ZDEMO202600000001 is on its way'].join('\n')))
      .toMatchObject([{ normalized: '1ZDEMO202600000001', match: { carrier: 'ups', source: 'text' } }]);
  });
});

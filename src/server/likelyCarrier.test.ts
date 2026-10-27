import { describe, expect, it } from 'vitest';
import { fixedCharacters, ownFormatCarrier } from './likelyCarrier';

describe('fixedCharacters', () => {
  it.each([
    ['^EOB\\d{17}$', 3],
    ['^00001000\\d{14}$', 8],
    ['^PA0{2}\\d{8}$', 4],
    ['^(?:870|880)\\d{11}[0-9A-Z]$', 3],
    ['^[A-Z]{2}\\d{9}IT$', 2],
    ['^(?:RGL|PAR)\\d{8}$', 3],
    // Bare digits, a class, an optional or repeated part, a lookaround: none, and an alternative naming fewer counts.
    ['^\\d{15}$', 0],
    ['^(?:\\d{8}|\\d{10}|\\d{12})$', 0],
    ['^(?=[A-Z0-9]{8}$)(?=.*[A-Z])(?=.*\\d)[A-Z0-9]{8}$', 0],
    ['^D\\d{11}$', 1],
    ['^(?:420\\d{5})?9[23]61\\d{18}$', 3],
    ['^(?:GF\\d{13}|\\d{11})$', 0],
    ['^NV(?:SG|MY)(?!STAMP)[A-Z0-9]{8,20}$', 4],
  ])('%s names %i', (pattern, fixed) => {
    expect(fixedCharacters(pattern)).toBe(fixed);
  });
});

describe('ownFormatCarrier', () => {
  it('names the only carrier whose rule takes a number by characters it names', () => {
    // Synthetic numbers of BRT's EOB format and GoFo's GF format.
    expect(ownFormatCarrier('EOB10293847561029384')).toBe('brt');
    expect(ownFormatCarrier('GF1029384756102')).toBe('gofo');
  });

  it('names none for a bare run of digits or a shape several carriers share', () => {
    // Fifteen digits are BRT's and Yunda's; fourteen, DPD's networks' and more.
    expect(ownFormatCarrier('102938475610293')).toBeUndefined();
    expect(ownFormatCarrier('06080000000002')).toBeUndefined();
    expect(ownFormatCarrier('NOSHAPE42')).toBeUndefined();
  });
});

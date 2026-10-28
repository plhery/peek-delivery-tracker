import { describe, expect, it } from 'vitest';
import { fixedCharacters, guessedCarrier, likelyCarrier, ownFormatCarrier, preferredCarrier } from './likelyCarrier';
import { routingState } from './trackingRouting';

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

describe('preferredCarrier', () => {
  it('names the only carrier whose own range a shared shape falls in', () => {
    // Synthetic fourteen-digit numbers: DPD Switzerland's 060 and 061 depots, DPD Germany's 01xx.
    expect(preferredCarrier('06080000000002')).toBe('dpd');
    expect(preferredCarrier('01230000000002')).toBe('dpd-de');
    expect(guessedCarrier('06080000000002')).toBe('dpd');
  });

  it('names none for a shape nobody leads, or a preference for a check digit alone', () => {
    expect(preferredCarrier('40000000000001')).toBeUndefined();
    expect(guessedCarrier('40000000000001')).toBeUndefined();
    // Twelve digits whose FedEx check digit holds: other carriers' numbers pass it too.
    expect(preferredCarrier('123456789012')).toBeUndefined();
  });
});

describe('likelyCarrier', () => {
  const now = new Date('2026-10-28T12:00:00Z');
  const unfiled = (routing: Record<string, unknown>, extra: Record<string, unknown> = {}) => {
    const parcel = { carrier: 'unknown', tracking_number: '06080000000002', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {}, consecutive_failures: 0, ...routing } }, ...extra };
    return { parcel, state: routingState(parcel) };
  };
  const asked = { candidate_probes: { dpd: { count: 1, retry_at: '2026-10-28T13:00:00Z' } } };

  it('names the carrier the number points to once asked, and keeps when a check first found it so', () => {
    const { parcel, state } = unfiled(asked);
    expect(likelyCarrier(parcel, state, now)).toEqual({ carrier: 'dpd', since: now.toISOString() });
    const kept = unfiled({ ...asked, likely_carrier: { carrier: 'dpd', since: '2026-10-27T10:00:00Z' } });
    expect(likelyCarrier(kept.parcel, kept.state, now)).toEqual({ carrier: 'dpd', since: '2026-10-27T10:00:00Z' });
    // Asked directly, its "not found" counts too; its outage does not.
    const direct = unfiled({ failures: { dpd: { count: 1, kind: 'not_found', retry_at: '2026-10-28T13:00:00Z' } } });
    expect(likelyCarrier(direct.parcel, direct.state, now)?.carrier).toBe('dpd');
    const outage = unfiled({ failures: { dpd: { count: 1, kind: 'transport', retry_at: '2026-10-28T13:00:00Z' } } });
    expect(likelyCarrier(outage.parcel, outage.state, now)).toBeUndefined();
  });

  it('names none before the carrier is asked, once it says it knows the number, or once a carrier answers', () => {
    const before = unfiled({});
    expect(likelyCarrier(before.parcel, before.state, now)).toBeUndefined();
    const known = unfiled({ candidate_probes: { dpd: { count: 2, retry_at: '2026-10-28T14:00:00Z', known: true } } });
    expect(likelyCarrier(known.parcel, known.state, now)).toBeUndefined();
    // Another carrier confirmed the number, or a provider's scan came: the usual display takes over.
    for (const answered of [{ confirmed_carrier: 'seur' }, { discovered_carrier: 'brt' }, { last_event_at: '2026-10-28T09:00:00Z' }]) {
      const { parcel, state } = unfiled({ ...asked, likely_carrier: { carrier: 'dpd', since: '2026-10-27T10:00:00Z' }, ...answered });
      expect(likelyCarrier(parcel, state, now)).toBeUndefined();
    }
    const filed = unfiled(asked, { carrier: 'seur' });
    expect(likelyCarrier(filed.parcel, filed.state, now)).toBeUndefined();
  });
});

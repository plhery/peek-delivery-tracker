import { describe, expect, it } from 'vitest';
import { testView } from '../../test/parcelLinks';
import type { RecentParcel } from '../recents';
import { deviceExport } from './deviceExport';

const recent = (overrides: Partial<RecentParcel> = {}): RecentParcel => {
  const view = testView();
  return {
    id: view.link.id, key: 'f'.repeat(64), name: null, carrier: view.parcel.carrier, stage: 'in_transit', syncStatus: 'ok',
    expectedDelivery: null, updatedAt: null, lastSeenAt: '2026-11-18T10:00:00.000Z', snapshot: view, ...overrides,
  };
};

describe('deviceExport', () => {
  it('writes each parcel as last shown, with its name, link and archive date, and never its owner key', () => {
    const file = deviceExport([recent({ name: 'Winter boots', archivedAt: '2026-11-18T12:00:00.000Z' }), recent()],
      new Date('2026-11-19T08:00:00.000Z'), 'https://peek.example');
    expect(file.exportedAt).toBe('2026-11-19T08:00:00.000Z');
    expect(file.mode).toBe('device');
    expect(file.packages[0]).toMatchObject({ label: 'Winter boots', archivedAt: '2026-11-18T12:00:00.000Z' });
    expect(file.packages[0].link).toMatch(/^https:\/\/peek\.example\/p\//);
    expect(file.packages[1]).not.toHaveProperty('archivedAt');
    expect(JSON.stringify(file)).not.toContain('f'.repeat(64));
  });
});

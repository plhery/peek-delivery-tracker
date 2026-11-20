import { afterEach, expect, it, vi } from 'vitest';
import { rememberPreflightRecognition, takePreflightRecognition } from './preflightRecognition';

afterEach(() => { vi.useRealTimers(); });

it('gives an answer to the Add sheet once, for five minutes, and only for its carrier and number', () => {
  vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-09-10T12:00:00Z') });
  rememberPreflightRecognition('brt', '00000000000011', { known: true, lastActivityAt: '2026-09-10T11:00:00Z' });
  rememberPreflightRecognition('seur', '00000000000011', { known: false });
  expect(takePreflightRecognition('brt', '00000000000012')).toBeUndefined();
  expect(takePreflightRecognition('ciblex', '00000000000011')).toBeUndefined();
  vi.setSystemTime(new Date('2026-09-10T12:04:59Z'));
  expect(takePreflightRecognition('brt', '00000000000011')).toEqual({ known: true, lastActivityAt: '2026-09-10T11:00:00Z' });
  expect(takePreflightRecognition('brt', '00000000000011')).toBeUndefined();
  // Past five minutes the carrier is asked again.
  vi.setSystemTime(new Date('2026-09-10T12:05:00Z'));
  expect(takePreflightRecognition('seur', '00000000000011')).toBeUndefined();
});

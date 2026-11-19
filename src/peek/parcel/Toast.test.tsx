import { act, cleanup, render, screen } from '@testing-library/react';
import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { announceNotice, NoticeToast } from './Toast';

beforeEach(() => { vi.useFakeTimers(); });
afterEach(() => { vi.useRealTimers(); });

it('shows a notice for a few seconds, and not later on a screen that opens after its moment', () => {
  render(<NoticeToast />);
  act(() => announceNotice('move.missed'));
  expect(screen.getByRole('status')).toHaveTextContent('Peek has forgotten these parcels.');
  act(() => { vi.advanceTimersByTime(4_000); });
  expect(screen.queryByRole('status')).toBeNull();
  cleanup();

  // Said where no screen shows notices, such as a signed-in account before this one was there to.
  announceNotice('move.missed');
  vi.advanceTimersByTime(5_000);
  render(<NoticeToast place="top" />);
  expect(screen.queryByRole('status')).toBeNull();
  act(() => announceNotice('move.failed'));
  expect(screen.getByRole('status')).toHaveClass('peekp-toast--top');
});

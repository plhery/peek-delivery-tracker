import { render, screen } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import type { ParcelWithEvents, TrackingEvent } from '../../types';
import { ParcelCard } from '../ParcelCard';
import { NameWithMark } from './NoteMark';

const scan: TrackingEvent = { id: 'scan', parcelId: 'parcel', stage: 'in_transit', description: 'Scan', occurredAt: '2026-11-24T08:00:00Z' };
const parcel: ParcelWithEvents = {
  id: 'parcel', carrier: 'ups', trackingNumber: '1ZTEST0000000001', label: 'Kind of Blue · vinyl', createdAt: '2026-11-20T12:00:00Z',
  syncStatus: 'ok', events: [scan],
};
const file = { id: 'f', name: 'Box.jpg', contentType: 'image/jpeg' as const, size: 1, createdAt: '', url: '', thumbnailUrl: null, expiresAt: '' };

describe("the list's note mark", () => {
  it('holds on to the name’s last word, so it never wraps onto a line alone', () => {
    const { container } = render(<span><NameWithMark name="Kind of Blue · vinyl" /></span>);
    expect(container.textContent).toBe('Kind of Blue · vinyl');
    const joined = container.querySelector('.note-mark-join')!;
    expect(joined.textContent).toBe('vinyl');
    expect(joined.querySelector('svg.note-mark')).toHaveAttribute('aria-hidden', 'true');
  });

  it('holds on to the last characters of one long word', () => {
    const { container } = render(<span><NameWithMark name="Schallplattenspieler" files /></span>);
    expect(container.querySelector('.note-mark-join')?.textContent).toBe('nspieler');
    // A paperclip once the note holds a file.
    expect(container.querySelector('.note-mark path')?.getAttribute('d')).toMatch(/^M13 7.6/);
  });

  it('marks a card whose parcel has a note, and says so with the row', () => {
    const { unmount } = render(<ParcelCard parcel={parcel} onOpen={vi.fn()} />);
    expect(screen.getByRole('button', { name: /^Kind of Blue · vinyl — / })).toBeVisible();
    expect(document.querySelector('.note-mark')).toBeNull();
    unmount();

    render(<ParcelCard parcel={{ ...parcel, note: 'Door code 4812', files: [file] }} onOpen={vi.fn()} />);
    expect(screen.getByRole('button', { name: /^Kind of Blue · vinyl, has a note — / })).toBeVisible();
    expect(document.querySelector('.parcel-card__label .note-mark')).not.toBeNull();
    // The note itself is never on the card.
    expect(document.body).not.toHaveTextContent('Door code');
  });
});

import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import App from './App';
import { MARK_NEW_UPDATES_STORAGE_KEY } from './lib/newUpdate';
import { ParcelsProvider } from './store/ParcelsContext';
import type { ParcelRepo, ParcelWithEvents, Stage } from './types';

const HOUR = 3_600_000;
const ago = (hours: number) => new Date(Date.now() - hours * HOUR).toISOString();
const parcel = (id: string, label: string, stages: Stage[], more: Partial<ParcelWithEvents> = {}): ParcelWithEvents => ({
  id, label, trackingNumber: `99341234561234567${id.length}`, carrier: 'swiss-post', syncStatus: 'ok', createdAt: ago(80),
  events: stages.map((stage, index) => ({ id: `${id}-${stage}`, parcelId: id, stage, description: stage, occurredAt: ago((stages.length - index) * 2) })),
  ...more,
});

// The tea moved on an hour ago and was last opened five hours ago. The lamp was opened since its last scan, and the
// book has no scan yet.
const tea = parcel('tea', 'Tea', ['accepted', 'in_transit'], { newsAt: ago(1), seenAt: ago(5) });
const lamp = parcel('lamp', 'Lamp', ['accepted', 'in_transit'], { newsAt: ago(6), seenAt: ago(5) });
const book = parcel('book', 'Book', [], { seenAt: ago(5) });

function account(saved: ParcelWithEvents[], answer: ParcelWithEvents[] = saved) {
  const markSeen = vi.fn(async () => new Date().toISOString());
  const repo: ParcelRepo = {
    mode: 'api', cachedList: () => saved, list: vi.fn().mockResolvedValue(answer), add: vi.fn(), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(), markSeen,
  };
  render(<ParcelsProvider repo={repo}><App /></ParcelsProvider>);
  return { markSeen, list: vi.mocked(repo.list) };
}

const card = (label: string) => screen.getByRole('button', { name: new RegExp(`${label} — `) });
const dot = (id: string) => document.querySelector(`[data-parcel-id="${id}"] .parcel-card__new`);
const dots = () => document.querySelectorAll('.parcel-card__new[data-on]');

describe('the mark on a parcel with updates since it was last opened', () => {
  beforeEach(() => window.history.replaceState({}, '', '/'));
  afterEach(() => window.localStorage.removeItem(MARK_NEW_UPDATES_STORAGE_KEY));

  it('shows on a parcel scanned after it was last opened, and is read first', async () => {
    const { list } = account([tea, lamp, book]);
    await waitFor(() => expect(list).toHaveBeenCalled());
    expect(card('Tea')).toHaveAccessibleName(/^New update\. (Next up: )?Tea — /);
    expect(dot('tea')).toHaveAttribute('data-on');
    // The dot is drawn, not read: the card's name says it.
    expect(dot('tea')).toHaveAttribute('aria-hidden', 'true');
    // It stands first on the status line.
    expect(dot('tea')!.parentElement).toHaveClass('parcel-card__state');
    expect(dot('tea')!.previousSibling).toBeNull();
    // A parcel opened since its last scan and one without a scan carry none.
    expect(card('Lamp')).not.toHaveAccessibleName(/New update/);
    expect(card('Book')).not.toHaveAccessibleName(/New update/);
    expect(dot('lamp')).not.toHaveAttribute('data-on');
    expect(dots()).toHaveLength(1);
  });

  it('shows nowhere when the app starts with the parcels it had: each starts as seen', async () => {
    const started = new Date().toISOString();
    const { list, markSeen } = account([tea, lamp, book].map((existing) => ({ ...existing, seenAt: started })));
    await waitFor(() => expect(list).toHaveBeenCalled());
    expect(dots()).toHaveLength(0);
    expect(screen.queryByRole('button', { name: /New update/ })).not.toBeInTheDocument();
    expect(markSeen).not.toHaveBeenCalled();
  });

  it('clears when the parcel is opened, at once and for the account', async () => {
    const { list, markSeen } = account([tea, lamp, book]);
    await waitFor(() => expect(list).toHaveBeenCalled());
    expect(markSeen).not.toHaveBeenCalled();

    fireEvent.click(card('Tea'));

    expect(await screen.findByRole('dialog', { name: 'Tea' })).toBeInTheDocument();
    await waitFor(() => expect(markSeen).toHaveBeenCalledWith('tea', tea.newsAt));
    // The dot stays in the card, switched off, so it can fade and the words slide back.
    await waitFor(() => expect(dot('tea')).not.toHaveAttribute('data-on'));
    expect(dot('tea')).toBeInTheDocument();
    expect(dots()).toHaveLength(0);
    // Once is enough for one piece of news.
    expect(markSeen).toHaveBeenCalledTimes(1);
  });

  it('clears when the account opened the parcel on another device', async () => {
    // This device's saved list still has the news; the server answers with the moment the other device opened it.
    const { markSeen } = account([tea, lamp], [{ ...tea, seenAt: ago(0.5) }, lamp]);
    await waitFor(() => expect(dots()).toHaveLength(0));
    expect(card('Tea')).not.toHaveAccessibleName(/New update/);
    expect(markSeen).not.toHaveBeenCalled();
  });

  it('stays hidden with the setting off, while opening a parcel still counts as seeing it', async () => {
    window.localStorage.setItem(MARK_NEW_UPDATES_STORAGE_KEY, 'off');
    const { list, markSeen } = account([tea, lamp, book]);
    await waitFor(() => expect(list).toHaveBeenCalled());
    expect(dots()).toHaveLength(0);
    expect(card('Tea')).not.toHaveAccessibleName(/New update/);

    fireEvent.click(card('Tea'));

    await waitFor(() => expect(markSeen).toHaveBeenCalledWith('tea', tea.newsAt));
  });

  it('leaves a parcel without news alone when it is opened', async () => {
    const { list, markSeen } = account([tea, lamp, book]);
    await waitFor(() => expect(list).toHaveBeenCalled());
    fireEvent.click(card('Lamp'));
    expect(await screen.findByRole('dialog', { name: 'Lamp' })).toBeInTheDocument();
    expect(markSeen).not.toHaveBeenCalled();
    expect(dot('tea')).toHaveAttribute('data-on');
  });
});

import { act, fireEvent, render, screen, within } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { AddParcelSheet } from './AddParcelSheet';

beforeEach(() => vi.useFakeTimers());
afterEach(() => vi.useRealTimers());

function renderSheet() {
  render(<AddParcelSheet onAdd={vi.fn()} onClose={vi.fn()} />);
  return screen.getByRole('textbox', { name: /^Tracking number or link/ });
}

const notFound = /couldn’t find a tracking number/i;
const pickup = 'That looks like a pickup code';

describe('Add parcel feedback while typing', () => {
  it('waits for a pause before saying that typed text has no tracking number', () => {
    const tracking = renderSheet();

    fireEvent.change(tracking, { target: { value: 'LP' } });
    expect(screen.queryByText(notFound)).not.toBeInTheDocument();

    act(() => vi.advanceTimersByTime(800));
    expect(screen.getByText(notFound)).toBeInTheDocument();

    fireEvent.change(tracking, { target: { value: 'LP-' } });
    expect(screen.getByText(notFound)).toBeInTheDocument();

    fireEvent.change(tracking, { target: { value: 'LP12' } });
    expect(screen.queryByText(notFound)).not.toBeInTheDocument();
  });

  it('answers a paste at once', () => {
    const tracking = renderSheet();

    fireEvent.paste(tracking);
    fireEvent.change(tracking, { target: { value: 'Thanks for your order!' } });

    expect(screen.getByText(notFound)).toBeInTheDocument();
  });

  it('leaves an unrecognized number to automatic detection, with the picker one tap away', () => {
    const tracking = renderSheet();

    fireEvent.change(tracking, { target: { value: 'ZZ1234' } });
    const line = screen.getByRole('button', { name: /^Detect automatically/ });
    expect(screen.queryByRole('dialog', { name: 'Carrier' })).not.toBeInTheDocument();

    fireEvent.click(line);
    expect(screen.getByRole('dialog', { name: 'Carrier' })).toBeInTheDocument();
  });

  it('takes six digits for a pickup code, and adds them only under a carrier picked by hand', () => {
    const tracking = renderSheet();
    const add = screen.getByRole('button', { name: 'Add parcel' });

    fireEvent.change(tracking, { target: { value: '204617' } });
    expect(add).toBeDisabled();
    // The start of a longer number is still being typed.
    expect(screen.queryByText(pickup)).not.toBeInTheDocument();

    act(() => vi.advanceTimersByTime(800));
    expect(screen.getByText(pickup)).toBeInTheDocument();

    fireEvent.click(screen.getByRole('button', { name: /^Detect automatically/ }));
    const picker = screen.getByRole('dialog', { name: 'Carrier' });
    fireEvent.change(within(picker).getByRole('combobox', { name: 'Search carriers' }), { target: { value: 'Pannon' } });
    fireEvent.click(within(picker).getByRole('option', { name: /^Pannon/ }));
    expect(screen.queryByText(pickup)).not.toBeInTheDocument();
    expect(add).toBeEnabled();
  });
});

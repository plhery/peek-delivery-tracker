import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { ChangeCarrierSheet } from './ChangeCarrierSheet';
import { pickCarrier } from '../test/carrierPicker';
import type { ParcelWithEvents } from '../types';

afterEach(() => vi.unstubAllGlobals());
const parcel: ParcelWithEvents = { id: 'parcel', carrier: 'seur', trackingNumber: '12345678901235', label: 'Books', createdAt: '2026-09-10T12:00:00Z', syncStatus: 'ok', events: [] };

describe('ChangeCarrierSheet', () => {
  it('says before saving that the chosen carrier issues no number like this one', async () => {
    const refusal = vi.fn(async (_path: string, init?: RequestInit) => Response.json({ refused: JSON.parse(String(init?.body)).carrier === 'nz-couriers' }));
    vi.stubGlobal('fetch', refusal);
    const onChange = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    render(<ChangeCarrierSheet parcel={parcel} onChange={onChange} onClose={vi.fn()} />);
    const sheet = screen.getByRole('dialog', { name: 'Change carrier' });
    const carrier = within(sheet).getByRole('button', { name: /^Carrier / });

    await pickCarrier(user, carrier, 'New Zealand Couriers');
    expect(await within(sheet).findByText('New Zealand Couriers doesn’t issue numbers like this one')).toHaveClass('is-attention');
    expect(carrier).toHaveAccessibleDescription('New Zealand Couriers doesn’t issue numbers like this one');
    expect(refusal).toHaveBeenCalledWith('/api/public/refusal', expect.objectContaining({
      method: 'POST', body: JSON.stringify({ carrier: 'nz-couriers', trackingNumber: '12345678901235' }) }));

    await pickCarrier(user, carrier, 'DPD Switzerland');
    expect(within(sheet).queryByText(/doesn’t issue numbers/)).not.toBeInTheDocument();
    // A warning, not a refusal: the change can still be saved.
    await pickCarrier(user, carrier, 'New Zealand Couriers');
    await within(sheet).findByText(/doesn’t issue numbers/);
    await user.click(within(sheet).getByRole('button', { name: 'Save carrier' }));
    expect(onChange).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'nz-couriers' }));
  });

  /** Opens the picker with `opener` and picks automatic detection. */
  async function chooseAutomatic(user: ReturnType<typeof userEvent.setup>, opener: HTMLElement) {
    await user.click(opener);
    await user.click(within(await screen.findByRole('dialog', { name: 'Carrier' })).getByRole('option', { name: /^Detect automatically/ }));
  }

  it('goes back to automatic detection, filed under no carrier for a number no shape settles', async () => {
    vi.stubGlobal('fetch', vi.fn(async () => Response.json({ refused: false })));
    const onChange = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    render(<ChangeCarrierSheet parcel={parcel} onChange={onChange} onClose={vi.fn()} />);
    const sheet = screen.getByRole('dialog', { name: 'Change carrier' });
    await chooseAutomatic(user, within(sheet).getByRole('button', { name: /^Carrier / }));
    expect(within(sheet).getByRole('button', { name: 'Carrier Detect automatically' })).toHaveAccessibleDescription('Peek finds the carrier for you.');
    await user.click(within(sheet).getByRole('button', { name: 'Save carrier' }));
    expect(onChange).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'unknown' }));
  });

  it('goes back to automatic detection, filed under the carrier the number’s shape names', async () => {
    vi.stubGlobal('fetch', vi.fn(async () => Response.json({ refused: false })));
    const onChange = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    render(<ChangeCarrierSheet parcel={{ ...parcel, carrier: 'dpd', trackingNumber: '1Z999AA10123456784' }} onChange={onChange} onClose={vi.fn()} />);
    const sheet = screen.getByRole('dialog', { name: 'Change carrier' });
    await chooseAutomatic(user, within(sheet).getByRole('button', { name: /^Carrier / }));
    expect(within(sheet).getByRole('button', { name: 'Carrier Detect automatically' })).toHaveAccessibleDescription('UPS, from the number');
    await user.click(within(sheet).getByRole('button', { name: 'Save carrier' }));
    expect(onChange).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'ups' }));
  });

  it('offers no automatic detection for an Amazon number, tracked through Amazon Shipping alone', async () => {
    vi.stubGlobal('fetch', vi.fn(async () => Response.json({ refused: false })));
    const user = userEvent.setup();
    render(<ChangeCarrierSheet parcel={{ ...parcel, carrier: 'amazon-shipping', trackingNumber: 'TBA123456789012' }} onChange={vi.fn()} onClose={vi.fn()} />);
    await user.click(within(screen.getByRole('dialog', { name: 'Change carrier' })).getByRole('button', { name: /^Carrier / }));
    expect(within(await screen.findByRole('dialog', { name: 'Carrier' })).queryByRole('option', { name: /^Detect automatically/ })).not.toBeInTheDocument();
  });

  it('heads the picker with the parcel’s own carrier unless the number’s carriers show it already', async () => {
    vi.stubGlobal('fetch', vi.fn(async () => Response.json({ refused: false })));
    const user = userEvent.setup();
    const carriers = (group: HTMLElement) => within(group).getAllByRole('option').map((option) => option.id.split('-').at(-1));
    const open = async (carrier: ParcelWithEvents['carrier']) => {
      const view = render(<ChangeCarrierSheet parcel={{ ...parcel, carrier, trackingNumber: '1Z999AA10123456784' }}
        usedCarriers={['chronopost', 'dpd']} onChange={vi.fn()} onClose={vi.fn()} />);
      await user.click(within(screen.getByRole('dialog', { name: 'Change carrier' })).getByRole('button', { name: /^Carrier / }));
      return { view, picker: await screen.findByRole('dialog', { name: 'Carrier' }) };
    };

    // A carrier that doesn't fit the number, still offered: on top, and not again among the ones used before.
    const chronopost = await open('chronopost');
    const [current, detected, used] = within(chronopost.picker).getAllByRole('group');
    expect(current).toHaveAccessibleName('Current');
    expect(carriers(current)).toEqual(['chronopost']);
    expect(within(current).getByRole('option')).toHaveAttribute('aria-selected', 'true');
    expect(detected).toHaveAccessibleName('Detected');
    expect(carriers(detected)).toEqual(['ups']);
    expect(used).toHaveAccessibleName('You’ve used');
    expect(carriers(used)).toEqual(['dpd']);
    chronopost.view.unmount();

    // The carrier the number names is shown once, where it fits.
    const ups = await open('ups');
    expect(within(ups.picker).queryByRole('group', { name: 'Current' })).not.toBeInTheDocument();
    expect(within(within(ups.picker).getByRole('group', { name: 'Detected' })).getByRole('option')).toHaveAttribute('aria-selected', 'true');
  });

  it('opens on automatic detection for a parcel filed under no carrier', async () => {
    vi.stubGlobal('fetch', vi.fn(async () => Response.json({ refused: false })));
    const user = userEvent.setup();
    render(<ChangeCarrierSheet parcel={{ ...parcel, carrier: 'unknown' }} onChange={vi.fn()} onClose={vi.fn()} />);
    const sheet = screen.getByRole('dialog', { name: 'Change carrier' });
    expect(within(sheet).getByRole('button', { name: 'Save carrier' })).toBeDisabled();
    // Automatic detection stands for the parcel's own entry.
    await user.click(within(sheet).getByRole('button', { name: 'Carrier Detect automatically' }));
    const picker = await screen.findByRole('dialog', { name: 'Carrier' });
    expect(within(picker).getByRole('option', { name: /^Detect automatically/ })).toHaveAttribute('aria-selected', 'true');
    expect(within(picker).queryByText('Current')).not.toBeInTheDocument();
  });

  it('takes a provider’s postcode request in the carrier’s own postcode field', async () => {
    vi.stubGlobal('fetch', vi.fn(async () => Response.json({ refused: false })));
    const onChange = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    const asked = { ...parcel, providerInputNeeded: { provider: 'ParcelsApp', field: 'dpdPostcode' as const } };
    const { unmount } = render(<ChangeCarrierSheet parcel={{ ...asked, carrier: 'spring-gds', trackingNumber: '3SABCD826405193' }}
      onChange={onChange} onClose={vi.fn()} />);
    const sheet = screen.getByRole('dialog', { name: 'Change carrier' });
    // PostNL's optional postcode is the one asked for: every provider receives it.
    const [field] = within(sheet).getAllByRole('textbox');
    expect(within(sheet).getAllByRole('textbox')).toHaveLength(1);
    expect(field).toBeRequired();
    expect(within(sheet).getByText('ParcelsApp needs the delivery postcode to look up this parcel.')).toBeInTheDocument();
    await user.type(field, '1234 AB');
    await user.click(within(sheet).getByRole('button', { name: 'Save carrier' }));
    expect(onChange).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'spring-gds', dpdPostcode: '1234 AB', providerPostcode: undefined }));
    unmount();
    // A carrier taking no postcode leaves the request a field of its own.
    onChange.mockClear();
    render(<ChangeCarrierSheet parcel={asked} onChange={onChange} onClose={vi.fn()} />);
    const own = screen.getByRole('dialog', { name: 'Change carrier' });
    await user.type(within(own).getByRole('textbox', { name: /^Delivery postcode/ }), '28001');
    await user.click(within(own).getByRole('button', { name: 'Save carrier' }));
    expect(onChange).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'seur', providerPostcode: '28001', dpdPostcode: undefined }));
  });
});

import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, expect, it, onTestFinished, vi } from 'vitest';
import { ParcelDetail } from './ParcelDetail';
import { carrierInfo, parcelTrackingLinks } from '../lib/carriers';
import type { ParcelWithEvents } from '../types';

const parcel: ParcelWithEvents = { id: 'parcel', carrier: 'heppner', trackingNumber: '12345678', label: 'Books', createdAt: '2026-09-10T12:00:00Z', syncStatus: 'ok', events: [] };
function show(values: Partial<ParcelWithEvents> = {}, onChangeCarrier = vi.fn()) {
  return render(<ParcelDetail parcel={{ ...parcel, ...values }} onBack={vi.fn()} onRename={vi.fn()} onChangeCarrier={onChangeCarrier} onSetNotificationsMuted={vi.fn()} onRefresh={vi.fn()} onRestore={vi.fn()} onArchive={vi.fn()} onDelete={vi.fn()} />);
}

/** Makes the device's clock read `timeZone`, the country a postcode field starts on. */
function deviceIn(timeZone: string) {
  const clock = vi.spyOn(Intl.DateTimeFormat.prototype, 'resolvedOptions')
    .mockReturnValue({ ...new Intl.DateTimeFormat().resolvedOptions(), timeZone });
  onTestFinished(() => clock.mockRestore());
}

describe('shipment details', () => {
  it('shows the PostLogistics dash after the stored number has been normalized', () => {
    show({ carrier: 'postlogistics', trackingNumber: '12345678001' });
    expect(screen.getByText('12345678-001')).toBeInTheDocument();
    expect(screen.getByRole('link', { name: /PostLogistics/ })).toHaveAttribute(
      'href', parcelTrackingLinks({ carrier: 'postlogistics', trackingNumber: '12345678001' })[0].url,
    );
  });

  it('shows a parcel handed to a second carrier with both marks, who delivers, and each website under its number', () => {
    const handed = { carrier: 'gls-de', trackingNumber: '12345678901', originalCarrier: 'gls-de', originalTrackingNumber: '12345678901',
      trackingSource: 'swiss-post', activeTrackingNumber: '993412345612345678' } as const;
    const view = show(handed);
    const change = screen.getByRole('button', { name: 'Change carrier from GLS Germany. Delivery with Swiss Post' });
    expect([...change.querySelectorAll('.carrier-mark')].map(mark => mark.getAttribute('title'))).toEqual(['GLS Germany', 'Swiss Post']);
    expect(change.querySelector('.detail__delivery-mark')).toHaveTextContent('Swiss Post');
    expect(screen.getByText('Delivery with Swiss Post')).toHaveClass('detail__delivery');
    const links = screen.getAllByRole('link', { name: /^Open the .+ website$/ });
    // Delivery first, each link right after the ticket of its own number.
    expect(links.map(link => link.closest('.detail__carrier-links')!.previousElementSibling!.querySelector('.detail__tracking-label')!.textContent))
      .toEqual(['Swiss Post', 'GLS Germany']);
    expect(links.map(link => link.getAttribute('href'))).toEqual(parcelTrackingLinks(handed, 'en').map(link => link.url));
    expect(screen.queryByText('Earlier journey')).not.toBeInTheDocument();
    expect(screen.queryByLabelText('Tracking sources')).not.toBeInTheDocument();
    view.unmount();

    show();
    expect(screen.getByRole('button', { name: 'Change carrier from Heppner' }).querySelectorAll('.carrier-mark')).toHaveLength(1);
    expect(screen.queryByText(/^Delivery with/)).not.toBeInTheDocument();
  });

  it('says who will deliver before that carrier is followed, under the followed number', () => {
    const named = { carrier: 'chronopost', trackingNumber: 'XY123456789FR', deliveryCarrier: 'dpd-de', deliveryTrackingNumber: '01234567890123' } as const;
    show(named);
    const change = screen.getByRole('button', { name: 'Change carrier from Chronopost. Delivery with DPD Germany' });
    expect([...change.querySelectorAll('.carrier-mark')].map(mark => mark.getAttribute('title'))).toEqual(['Chronopost', 'DPD Germany']);
    expect(screen.getByText('Delivery with DPD Germany')).toHaveClass('detail__delivery');
    // The followed number stays on top; the delivering carrier's follows, each with its own website.
    expect([...document.querySelectorAll('.detail__tracking-label')].map(label => label.textContent)).toEqual(['Chronopost', 'DPD Germany']);
    const links = screen.getAllByRole('link', { name: /^Open the .+ website$/ });
    expect(links.map(link => link.closest('.detail__carrier-links')!.previousElementSibling!.querySelector('.detail__tracking-label')!.textContent))
      .toEqual(['Chronopost', 'DPD Germany']);
    expect(links.map(link => link.getAttribute('href'))).toEqual(parcelTrackingLinks(named, 'en').map(link => link.url));
    expect(screen.queryByText('Not ready yet')).not.toBeInTheDocument();
  });

  it('shows the number the carrier tracks the parcel by under the one typed', () => {
    show({ carrier: 'hermes-de', trackingNumber: '00000000000001', canonicalTrackingNumber: 'H0000000000001' });
    expect([...document.querySelectorAll('.detail__tracking-label')].map(label => label.textContent))
      .toEqual(['Tracking number', 'Hermes Germany tracks this as']);
    expect(screen.getByRole('button', { name: 'Copy tracking number — Hermes Germany tracks this as' })).toBeVisible();
    expect(screen.getAllByRole('link', { name: /^Open the .+ website$/ })[0].closest('.detail__carrier-links')!.previousElementSibling!
      .querySelector('.detail__tracking-label')!.textContent).toBe('Tracking number');
  });

  it('says the sender still has the parcel once its only scan, the label, is a day old', () => {
    const label = (hoursAgo: number) => [{ id: 'label', parcelId: 'parcel', stage: 'registered' as const, description: 'Label created', occurredAt: new Date(Date.now() - hoursAgo * 3_600_000).toISOString() }];
    const note = 'The sender has created the label but hasn’t handed the parcel to Chronopost yet. Tracking starts when they do.';
    let view = show({ carrier: 'chronopost', events: label(30) });
    expect(screen.getByText(note)).toHaveClass('detail__handover');
    view.unmount();

    view = show({ carrier: 'chronopost', events: label(20) });
    expect(screen.queryByText(note)).not.toBeInTheDocument();
    view.unmount();

    show({ carrier: 'chronopost', events: label(30), archivedAt: '2026-09-12T12:00:00Z' });
    expect(screen.queryByText(note)).not.toBeInTheDocument();
  });

  it('shows available facts and omits unknown or invalid measurements', () => {
    const view = show({ pickupPoint: 'Corner shop\n12 Main Street', receiverName: 'Alex', weightKg: 1.25, dimensionsText: '20 × 30 × 10 cm' });
    expect(screen.getByText('Pickup point')).toBeInTheDocument();
    expect(screen.getByText(/Corner shop/)).toHaveTextContent('12 Main Street');
    expect(screen.getByText('Alex')).toBeInTheDocument();
    expect(screen.getByText('1.25 kg')).toBeInTheDocument();
    expect(screen.getByText('20 × 30 × 10 cm')).toBeInTheDocument();
    view.unmount();
    show({ weightKg: -1 });
    expect(screen.queryByText('Weight')).not.toBeInTheDocument();
    expect(screen.queryByText('Pickup point')).not.toBeInTheDocument();
  });

  it('lists the import charges first, with their online price while they can be paid online', () => {
    const charges = { currency: 'EUR', customs: 23.41, carrier_fee: 11, carrier_fee_online: 8.09 };
    let view = show({ carrier: 'poste-italiane', importCharges: charges, weightKg: 1.2 });
    const facts = screen.getByText('Import charges').closest('dl')!;
    expect(within(facts).getAllByRole('term').map((term) => term.textContent)).toEqual(['Import charges', 'Weight']);
    expect(within(facts).getByText('€34.41 (€31.50 if paid online)')).toBeInTheDocument();
    // The owner reads the number: nobody is sent to ask for it.
    expect(screen.queryByText(/ask the person who shared/)).not.toBeInTheDocument();
    view.unmount();

    view = show({ carrier: 'poste-italiane', importCharges: { ...charges, paid: true, status_code: 'PAGATO' } });
    expect(screen.getByText('Paid (€34.41 on delivery, €31.50 online)')).toBeInTheDocument();
    view.unmount();

    show({ carrier: 'poste-italiane', importCharges: { currency: 'EUR' } });
    expect(screen.queryByText('Import charges')).not.toBeInTheDocument();
  });

  it.each([
    ['failed_attempt', 'Check the carrier’s next steps'],
    ['exception', 'The carrier reported a problem'],
  ] as const)('flags a carrier waiting on the reader after %s and quotes it', (stage, flag) => {
    const events = [{ id: stage, parcelId: 'parcel', stage, description: ' Receiver not home. Please call us. ', occurredAt: '2026-09-11T12:00:00Z' }];
    const view = show({ events });
    expect(screen.getByText(flag)).toHaveClass('detail__flag');
    const note = screen.getByRole('note');
    expect(note).toHaveTextContent('What Heppner says');
    expect(note.querySelector('blockquote')).toHaveTextContent(/^Receiver not home\. Please call us\.$/);
    view.unmount();

    // An archived parcel waits on nobody.
    show({ events, archivedAt: '2026-09-12T12:00:00Z' });
    expect(screen.queryByText(flag)).not.toBeInTheDocument();
    expect(screen.queryByText('What Heppner says')).not.toBeInTheDocument();
  });

  it('flags a customs request waiting on the reader and quotes it', () => {
    show({ events: [{ id: 'held', parcelId: 'parcel', stage: 'customs', description: 'Need to inform recipient\'s CPF/CNPJ',
      occurredAt: '2026-09-11T12:00:00Z', recipientAction: 'tax_id' }] });
    expect(screen.getByText('Customs needs your tax number')).toHaveClass('detail__flag');
    expect(screen.getByRole('note').querySelector('blockquote')).toHaveTextContent(/^Need to inform recipient's CPF\/CNPJ$/);
  });

  it('keeps the flag and the carrier\'s words off a parcel on its way', () => {
    show({ events: [{ id: 'moving', parcelId: 'parcel', stage: 'in_transit', description: 'Sorted', occurredAt: '2026-09-11T12:00:00Z' }] });
    expect(document.querySelector('.detail__flag')).toBeNull();
    expect(screen.queryByText('What Heppner says')).not.toBeInTheDocument();
  });

  it('explains a parcel that went quiet, with the help page of a carrier that has one', () => {
    const quiet = [{ id: 'moving', parcelId: 'parcel', stage: 'in_transit', description: 'Sorted', occurredAt: new Date(Date.now() - 5 * 86_400_000).toISOString() }] as const;
    const view = show({ carrier: 'dhl', events: [...quiet] });
    const note = within(document.querySelector<HTMLElement>('.detail__quiet')!);
    expect(note.getByText(/^Parcels often go quiet on long flights.+Peek keeps asking DHL/)).toBeVisible();
    expect(note.getByRole('link', { name: 'Get help from DHL' })).toHaveAttribute('href', carrierInfo('dhl').helpUrl);
    view.unmount();

    show({ events: [...quiet] });
    expect(document.querySelector('.detail__quiet')).toHaveTextContent('Peek keeps asking Heppner');
    expect(within(document.querySelector<HTMLElement>('.detail__quiet')!).queryByRole('link')).toBeNull();
  });

  it('shows a waiting parcel\'s pickup point as a card and a collected one as a fact', async () => {
    const at = (stage: 'ready_for_pickup' | 'delivered') => [{ id: stage, parcelId: 'parcel', stage, description: 'Scan', occurredAt: '2026-09-11T12:00:00Z' }];
    const writeText = vi.fn().mockResolvedValue(undefined);
    Object.defineProperty(navigator, 'clipboard', { configurable: true, value: { writeText } });
    const view = show({ pickupPoint: 'Corner shop\n12 Main Street', events: at('ready_for_pickup') });
    const card = screen.getByRole('region', { name: 'Pickup point' });
    expect(card).toHaveTextContent('Corner shop12 Main Street');
    expect(screen.getByRole('link', { name: 'Directions' })).toHaveAttribute('href', expect.stringContaining('Corner%20shop%2C%2012%20Main%20Street'));
    expect(screen.getByRole('link', { name: 'Directions' })).toHaveAttribute('rel', 'noopener noreferrer');
    await userEvent.click(screen.getByRole('button', { name: 'Copy address' }));
    expect(writeText).toHaveBeenCalledWith('Corner shop, 12 Main Street');
    expect(await screen.findByRole('button', { name: 'Copied' })).toBeInTheDocument();
    expect(screen.getAllByText('Pickup point')).toHaveLength(1);
    view.unmount();

    const office = show({ pickupPoint: 'Post office 42', events: at('ready_for_pickup') });
    expect(screen.getByRole('link', { name: 'Show on map' })).toHaveAttribute('href', expect.stringContaining('Post%20office%2042'));
    expect(screen.queryByRole('button', { name: 'Copy address' })).not.toBeInTheDocument();
    office.unmount();

    show({ pickupPoint: 'Corner shop\n12 Main Street', events: at('delivered') });
    expect(screen.queryByRole('region', { name: 'Pickup point' })).not.toBeInTheDocument();
    expect(screen.getByText('Picked up at')).toBeInTheDocument();
    expect(screen.getByText(/Corner shop/)).toHaveTextContent('12 Main Street');
  });

  it.each(['carrier:input_required', 'carrier:invalid_input'])('opens the existing tracking editor for missing or refused input (%s)', async (syncError) => {
    show({ syncStatus: 'error', syncError });
    await userEvent.click(screen.getByRole('button', { name: 'Update tracking details' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
    expect(screen.queryByText(syncError)).not.toBeInTheDocument();
  });

  it('says the carrier does not issue the number, and opens the carrier editor', async () => {
    show({ syncStatus: 'error', syncError: 'carrier:refused_number' });
    expect(screen.getByText('Check the tracking number')).toBeInTheDocument();
    expect(screen.queryByText('Update unavailable')).not.toBeInTheDocument();
    const box = screen.getByText('This carrier doesn’t issue tracking numbers like this one. Check the number, or choose another carrier.').closest('.detail__sync-error')!;
    expect(within(box as HTMLElement).queryByRole('button', { name: /^Switch to/ })).not.toBeInTheDocument();
    await userEvent.click(within(box as HTMLElement).getByRole('button', { name: 'Change carrier' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
  });

  it('names the one carrier a refused number fits, and switches to it in one tap', async () => {
    const onChangeCarrier = vi.fn().mockResolvedValue(undefined);
    const refused = { trackingNumber: 'JJD000390012345678', syncStatus: 'error', syncError: 'carrier:refused_number' } as const;
    const view = show(refused, onChangeCarrier);
    const box = screen.getByText('This carrier doesn’t issue tracking numbers like this one. It looks like one from DHL.').closest('.detail__sync-error') as HTMLElement;
    await userEvent.click(within(box).getByRole('button', { name: 'Switch to DHL' }));
    expect(onChangeCarrier).toHaveBeenCalledWith(expect.objectContaining({ id: 'parcel' }), { carrier: 'dhl' });
    expect(screen.queryByRole('button', { name: 'Save carrier' })).not.toBeInTheDocument();
    // Another carrier can still be chosen.
    await userEvent.click(within(box).getByRole('button', { name: 'Change carrier' }));
    expect(screen.getByRole('button', { name: 'Carrier Heppner' })).toBeInTheDocument();
    view.unmount();
    // A switch that fails says so.
    show(refused, vi.fn().mockRejectedValue(new TypeError('Failed to fetch')));
    await userEvent.click(screen.getByRole('button', { name: 'Switch to DHL' }));
    expect(screen.getByRole('alert')).toHaveTextContent('Check your internet connection and try again.');
    expect(screen.getByRole('button', { name: 'Switch to DHL' })).toBeEnabled();
  });

  it('opens the carrier editor on the carrier a refused number fits when it needs the postcode', async () => {
    const onChangeCarrier = vi.fn();
    show({ trackingNumber: '3SABCD826405193', syncStatus: 'error', syncError: 'carrier:refused_number' }, onChangeCarrier);
    await userEvent.click(screen.getByRole('button', { name: 'Switch to PostNL' }));
    expect(onChangeCarrier).not.toHaveBeenCalled();
    expect(screen.getByRole('button', { name: 'Carrier PostNL' })).toBeInTheDocument();
    expect(screen.getByRole('textbox', { name: /^Delivery postcode/ })).toBeInTheDocument();
  });

  it('keeps today’s warning when the refused number fits the parcel’s own carrier', () => {
    show({ carrier: 'dhl', trackingNumber: 'JJD000390012345678', syncStatus: 'error', syncError: 'carrier:refused_number' });
    expect(screen.getByText('This carrier doesn’t issue tracking numbers like this one. Check the number, or choose another carrier.')).toBeInTheDocument();
    expect(screen.queryByRole('button', { name: /^Switch to/ })).not.toBeInTheDocument();
  });

  it('says when DPD rejected the postcode and opens the editor to change it', async () => {
    const view = show({ carrier: 'dpd', trackingNumber: '06080000000002', dpdPostcode: '8000', dpdPostcodeVerified: false });
    expect(screen.getByText(/DPD Switzerland didn’t accept postcode 8000, so it shows fewer details\./)).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Edit postcode' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
    view.unmount();
    show({ carrier: 'dpd', trackingNumber: '06080000000002', dpdPostcode: '8000', dpdPostcodeVerified: true });
    expect(screen.queryByText(/didn’t accept postcode/)).not.toBeInTheDocument();
  });

  it('says when the parcel’s own carrier found no parcel with its postcode, and opens the editor to change it', async () => {
    const view = show({ carrier: 'spring-gds', trackingNumber: '3SABCD826405193', dpdPostcode: '1234 AB', postcodeRefused: true });
    expect(screen.getByText(/PostNL didn’t find this parcel with postcode 1234 AB\./)).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Edit postcode' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
    view.unmount();
    show({ carrier: 'spring-gds', trackingNumber: '3SABCD826405193', dpdPostcode: '1234 AB' });
    expect(screen.queryByText(/didn’t find this parcel/)).not.toBeInTheDocument();
  });

  it('offers the carrier that recognized the number and needs a postcode', async () => {
    const view = show({ carrier: 'unknown', trackingNumber: '12345678901', inputNeeded: { carrier: 'gls-ch', field: 'dpdPostcode' } });
    const prompt = screen.getByText('GLS Switzerland has this parcel. Add the delivery postcode to track it.');
    expect(prompt).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Add postcode' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Carrier GLS Switzerland' })).toBeInTheDocument();
    expect(screen.getByRole('textbox', { name: /^Delivery postcode/ })).toBeInTheDocument();
    view.unmount();
    // Nothing to ask once it is filed under that carrier, or when it is delivered.
    const filed = show({ carrier: 'gls-ch', trackingNumber: '12345678901', inputNeeded: { carrier: 'gls-ch', field: 'dpdPostcode' } });
    expect(screen.queryByText(/has this parcel/)).not.toBeInTheDocument();
    filed.unmount();
    show({ carrier: 'unknown', trackingNumber: '12345678901', inputNeeded: { carrier: 'gls-ch', field: 'dpdPostcode' },
      events: [{ id: 'event', parcelId: 'parcel', stage: 'delivered', description: 'Delivered', occurredAt: '2026-09-10T10:00:00Z' }] });
    expect(screen.queryByText(/has this parcel/)).not.toBeInTheDocument();
  });

  it('asks for the postcode the parcel’s own carrier needs to find it', async () => {
    deviceIn('Europe/Amsterdam');
    const asked = { carrier: 'spring-gds', trackingNumber: '3SABCD826405193', inputNeeded: { carrier: 'spring-gds', field: 'dpdPostcode' } } as const;
    const view = show(asked);
    expect(screen.getByText('PostNL needs the delivery postcode to find this parcel.')).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Add postcode' }));
    expect(screen.getByRole('button', { name: 'Carrier PostNL' })).toBeInTheDocument();
    expect(screen.getByRole('textbox', { name: /^Delivery postcode/ })).toHaveAttribute('placeholder', '1234 AB');
    view.unmount();
    // Not once it has one, while the error already asks, or once it is delivered.
    const others: Partial<ParcelWithEvents>[] = [{ dpdPostcode: '1234 AB' }, { syncStatus: 'error', syncError: 'carrier:input_required' },
      { events: [{ id: 'event', parcelId: 'parcel', stage: 'delivered', description: 'Delivered', occurredAt: '2026-09-10T10:00:00Z' }] }];
    for (const values of others) {
      const other = show({ ...asked, ...values });
      expect(screen.queryByText(/needs the delivery postcode/)).not.toBeInTheDocument();
      other.unmount();
    }
  });

  it('takes a postcode from the country the parcel goes to, named beside the field', async () => {
    deviceIn('Europe/Amsterdam');
    const onChangeCarrier = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    show({ carrier: 'spring-gds', trackingNumber: '3SABCD826405193', destinationCountry: 'BE',
      inputNeeded: { carrier: 'spring-gds', field: 'dpdPostcode' } }, onChangeCarrier);
    await user.click(screen.getByRole('button', { name: 'Add postcode' }));
    const postcode = screen.getByRole('textbox', { name: /^Delivery postcode/ });
    const country = screen.getByRole('combobox', { name: 'Delivery country' });
    // The parcel goes to Belgium: its postcode is Belgian, and goes with the country.
    expect(country).toHaveValue('BE');
    expect(postcode).toHaveAttribute('placeholder', '1000');
    await user.type(postcode, '1000');
    expect(postcode).toBeValid();
    await user.click(screen.getByRole('button', { name: 'Save carrier' }));
    expect(onChangeCarrier).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'spring-gds' }),
      expect.objectContaining({ carrier: 'spring-gds', dpdPostcode: '1000', dpdPostcodeCountry: 'BE' }));
    // A Dutch one goes without a country, as it always has.
    await user.click(screen.getByRole('button', { name: 'Add postcode' }));
    await user.selectOptions(screen.getByRole('combobox', { name: 'Delivery country' }), 'NL');
    expect(screen.getByRole('textbox', { name: /^Delivery postcode/ })).toHaveAttribute('placeholder', '1234 AB');
  });

  it('offers the carrier a number points to when it needs a postcode to be asked', async () => {
    const view = show({ carrier: 'unknown', trackingNumber: '12345678', inputSuggested: { carrier: 'mondial-relay', field: 'dpdPostcode' } });
    // Nothing says the carrier has it.
    expect(screen.getByText('Mondial Relay may have this parcel. Add the delivery postcode to track it.')).toBeInTheDocument();
    expect(screen.queryByText(/has this parcel/)).not.toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Add postcode' }));
    expect(screen.getByRole('button', { name: 'Carrier Mondial Relay' })).toBeInTheDocument();
    expect(screen.getByRole('textbox', { name: /^Delivery postcode/ })).toBeInTheDocument();
    view.unmount();
    // Not once it is filed, or once a scan came.
    const filed = show({ carrier: 'mondial-relay', trackingNumber: '12345678', inputSuggested: { carrier: 'mondial-relay', field: 'dpdPostcode' } });
    expect(screen.queryByText(/may have this parcel/)).not.toBeInTheDocument();
    filed.unmount();
    show({ carrier: 'unknown', trackingNumber: '12345678', inputSuggested: { carrier: 'mondial-relay', field: 'dpdPostcode' },
      events: [{ id: 'event', parcelId: 'parcel', stage: 'registered', description: 'Label created', occurredAt: '2026-09-10T10:00:00Z' }] });
    expect(screen.queryByText(/may have this parcel/)).not.toBeInTheDocument();
  });

  it('names the carrier a number’s own format points to, and after a week offers to choose another', async () => {
    const day = 24 * 3_600_000;
    const view = show({ carrier: 'unknown', trackingNumber: 'EOB10293847561029384', likelyCarrier: { carrier: 'brt', since: new Date(Date.now() - day).toISOString() } });
    expect(screen.getByText(/^This looks like a number from BRT, but BRT doesn’t list it yet\./)).toBeInTheDocument();
    expect(screen.queryByRole('button', { name: 'Choose another carrier' })).not.toBeInTheDocument();
    view.unmount();
    show({ carrier: 'unknown', trackingNumber: 'EOB10293847561029384', likelyCarrier: { carrier: 'brt', since: new Date(Date.now() - 8 * day).toISOString() } });
    expect(screen.getByText('BRT still doesn’t list it after a week. Check the number with the sender, or choose another carrier.')).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Choose another carrier' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
  });

  it('names the probable carrier where the header names the carrier, and waits for its first scan', () => {
    // A synthetic number in DPD Switzerland's depot range, which no carrier has answered for yet.
    const likely = { carrier: 'dpd' as const, since: new Date(Date.now() - 3_600_000).toISOString() };
    const view = show({ carrier: 'unknown', trackingNumber: '06080000000002', syncStatus: 'waiting', likelyCarrier: likely });
    expect(screen.getByRole('button', { name: 'Change carrier from Probably DPD Switzerland' })).toBeInTheDocument();
    expect(screen.getAllByText('Waiting for DPD Switzerland’s first scan').length).toBeGreaterThan(0);
    expect(screen.queryByText('No tracking history yet')).not.toBeInTheDocument();
    view.unmount();
    // Once another carrier answers for the number, the parcel is filed under it and the guess goes.
    show({ carrier: 'seur', trackingNumber: '06080000000002', syncStatus: 'waiting', likelyCarrier: likely });
    expect(screen.getByRole('button', { name: 'Change carrier from SEUR' })).toBeInTheDocument();
    expect(screen.queryByText(/Probably|first scan/)).not.toBeInTheDocument();
  });

  it('tells an order number or a short number no carrier answered for, and offers to choose the carrier', async () => {
    // Synthetic: a bare Amazon order number and a five-digit one.
    const view = show({ carrier: 'unknown', trackingNumber: '30245719832294617' });
    expect(screen.getByText(/^This looks like an order number from Amazon, not a tracking number\./)).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Choose the carrier' }));
    expect(screen.getByRole('dialog')).toBeInTheDocument();
    view.unmount();
    const short = show({ carrier: 'unknown', trackingNumber: '10293' });
    expect(screen.getByText(/^This is shorter than most tracking numbers\./)).toBeInTheDocument();
    short.unmount();
    // A tracking number's length, or a carrier filed: nothing to tell.
    show({ carrier: 'unknown', trackingNumber: '12345678' });
    expect(screen.queryByRole('button', { name: 'Choose the carrier' })).not.toBeInTheDocument();
  });

  it('never displays legacy diagnostics', () => {
    show({ syncStatus: 'error', syncError: 'private upstream error details' });
    expect(screen.queryByText('private upstream error details')).not.toBeInTheDocument();
    expect(screen.queryByRole('button', { name: 'Update tracking details' })).not.toBeInTheDocument();
  });
});

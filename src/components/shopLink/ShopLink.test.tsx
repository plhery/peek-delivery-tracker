import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { AddParcelSheet } from '../AddParcelSheet';
import { keepShopWait, readShopLink } from '../../lib/shopWaitsApi';
import { ShopWaitCards } from './ShopWaitCards';
import { demoShopWait } from './useShopWaits';

vi.mock('../../lib/carrierDetection', () => ({ lookupCarrier: vi.fn(async () => ({ trackingNumber: '', carrier: 'unknown', asked: [] })) }));
vi.mock('../../lib/shopWaitsApi', () => ({ readShopLink: vi.fn(), keepShopWait: vi.fn(), SHOP_WAITS_CHANGED: 'peek:shop-waits' }));
afterEach(() => { vi.resetAllMocks(); });

// A made-up order key.
const LINK = 'https://hm.delivery-status.com/pl/?orderNo=9900000000001&s=0000aaaa1111bbbb2222cccc3333dddd';
const auth = { userId: 'test-user', getAccessToken: async () => 'test-token' };
const notYet = { links: 1, parcels: [], unresolved: [{ platform: 'parcellab' as const, platform_name: 'parcelLab', shop_name: 'H&M', reason: 'not_found' as const, waitable: true }] };

describe('a shop link in Add', () => {
  it('says the shop hasn\'t shipped and waits for it, named as the reader wrote', async () => {
    vi.mocked(readShopLink).mockResolvedValue(notYet);
    vi.mocked(keepShopWait).mockResolvedValue([]);
    const onClose = vi.fn();
    const user = userEvent.setup();
    render(<AddParcelSheet apiAuth={auth} onAdd={vi.fn()} onClose={onClose} initialTrackingInput={LINK} />);
    expect(await screen.findByText('H&M hasn’t named a parcel on this page yet: it hasn’t shipped.')).toBeInTheDocument();
    expect(screen.getByText(/Peek checks every few hours for 14 days/)).toBeInTheDocument();
    expect(screen.queryByText(/couldn’t find a tracking number/)).not.toBeInTheDocument();
    await user.type(screen.getByPlaceholderText('Coffee beans, new shoes…'), 'Coat');
    await user.click(screen.getByRole('button', { name: 'Wait for H&M' }));
    await waitFor(() => expect(onClose).toHaveBeenCalled());
    expect(keepShopWait).toHaveBeenCalledWith(LINK, 'Coat', auth);
    expect(readShopLink).toHaveBeenCalledTimes(1);
  });

  it('asks a visitor to sign in to wait', async () => {
    vi.mocked(readShopLink).mockResolvedValue(notYet);
    render(<AddParcelSheet onAdd={vi.fn()} onClose={vi.fn()} initialTrackingInput={LINK} />);
    expect(await screen.findByText(/Sign in, and Peek waits for it/)).toBeInTheDocument();
    expect(readShopLink).toHaveBeenCalledWith(LINK, undefined, expect.anything());
    expect(screen.getByRole('button', { name: /^add parcel$/i })).toBeDisabled();
  });

  it('fills in the number and carrier the shop\'s platform names', async () => {
    vi.mocked(readShopLink).mockResolvedValue({ links: 1, parcels: [{ tracking_number: '00340000000000000001', carrier: 'dhl', confidence: 'high' }], unresolved: [] });
    render(<AddParcelSheet apiAuth={auth} onAdd={vi.fn()} onClose={vi.fn()} initialTrackingInput={LINK} />);
    await waitFor(() => expect(screen.getByRole('textbox', { name: /tracking number/i })).toHaveValue('00340000000000000001'));
    expect(screen.getByRole('button', { name: /^add parcel$/i })).toBeInTheDocument();
  });

  it('says plainly when the page can\'t be read', async () => {
    vi.mocked(readShopLink).mockResolvedValue({ links: 1, parcels: [], unresolved: [{ ...notYet.unresolved[0], reason: 'unsupported', waitable: false }] });
    render(<AddParcelSheet apiAuth={auth} onAdd={vi.fn()} onClose={vi.fn()} initialTrackingInput={LINK} />);
    expect(await screen.findByText(/can’t read this shop’s tracking page/)).toBeInTheDocument();
    expect(screen.queryByRole('button', { name: 'Wait for H&M' })).not.toBeInTheDocument();
  });
});

describe('a wait in the list', () => {
  it('names the shop and its platform, says until when Peek asks, and stops', async () => {
    const onRemove = vi.fn();
    const wait = demoShopWait(new Date(2026, 10, 26, 12).getTime());
    render(<ShopWaitCards waits={[wait]} onRemove={onRemove} />);
    expect(screen.getByText('H&M via parcelLab')).toBeInTheDocument();
    expect(screen.getByText('Wool coat')).toBeInTheDocument();
    expect(screen.getByText('Waiting for H&M to ship')).toBeInTheDocument();
    expect(screen.getByText(/^Peek checks every few hours until /)).toBeInTheDocument();
    await userEvent.click(screen.getByRole('button', { name: 'Stop waiting' }));
    expect(onRemove).toHaveBeenCalledWith(wait.id);
  });

  it('says plainly when 14 days passed without a parcel, and removes it', () => {
    const wait = { ...demoShopWait(), expired_at: new Date().toISOString(), shop_name: null };
    render(<ShopWaitCards waits={[wait]} onRemove={vi.fn()} />);
    expect(screen.getByText('The shop named no parcel in 14 days')).toBeInTheDocument();
    expect(screen.getByText('Via parcelLab')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Remove' })).toBeInTheDocument();
  });
});

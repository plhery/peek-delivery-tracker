import { afterEach, describe, expect, it, vi } from 'vitest';
import { ChallengeError, IndeterminateError, InputRequiredError, InvalidInputError, NotFoundError } from 'universal-parcel-scraper';
import * as scraper from 'universal-parcel-scraper/node';
import { checkLinkPostcode } from './linkPostcodes';

vi.mock('universal-parcel-scraper/node', async importOriginal => ({
  ...await importOriginal<typeof import('universal-parcel-scraper/node')>(), trackCarrier: vi.fn(),
}));
vi.mock('./adapterRegistry', () => ({
  createAdapterRegistry: () => ({ for: (carrier: string) => carrier === 'dhl' ? { steps: ['direct'] } : undefined }),
}));
vi.mock('./stepRecorder', () => ({ hostStepRecorder: () => ({}) }));

// Synthetic: a twelve-digit number of the shape DHL asks a postcode for.
const number = '990000000001';
const tracked = vi.mocked(scraper.trackCarrier);
afterEach(() => vi.resetAllMocks());

describe('asking a carrier about the postcode a lookup adds', () => {
  it('asks the carrier\'s own tracking with the postcode, within a budget', async () => {
    tracked.mockResolvedValue({ status: 'in_transit', events: [] } as never);
    const signal = new AbortController().signal;
    expect(await checkLinkPostcode('dhl', number, '99999', null, signal)).toEqual({ outcome: 'accepted' });
    expect(tracked).toHaveBeenCalledExactlyOnceWith('dhl', { number, trackingUrl: null, postcode: '99999' },
      expect.objectContaining({ budgetMs: 25_000, signal }));
  });

  it('sends a postcode from abroad with its country', async () => {
    tracked.mockResolvedValue({ status: 'in_transit', events: [] } as never);
    expect(await checkLinkPostcode('dhl', number, '1000', 'BE')).toEqual({ outcome: 'accepted' });
    expect(tracked).toHaveBeenCalledExactlyOnceWith('dhl', { number, trackingUrl: null, postcode: '1000', postcodeCountry: 'BE' },
      expect.anything());
  });

  it('reads a request for the input again, an invalid input and a parcel not found as a refusal', async () => {
    for (const error of [
      new InputRequiredError('DHL', 'the delivery postcode', 'not for this postcode', { reason: 'postcode_mismatch' }),
      new InvalidInputError('DHL', 'DHL delivery postcode is invalid'),
      new Error('wrapped', { cause: new NotFoundError('DHL') }),
      // A portal that finds nothing with the postcode, as PostNL's domestic tracker answers a wrong one.
      new Error('wrapped', { cause: new IndeterminateError('DHL', 'No parcel for that number and postcode', { reason: 'portal_not_found' }) }),
    ]) {
      tracked.mockRejectedValueOnce(error);
      expect(await checkLinkPostcode('dhl', number, '99999')).toEqual({ outcome: 'refused' });
    }
    tracked.mockRejectedValueOnce(new InputRequiredError('DHL', 'the delivery postcode', 'blocked', {
      reason: 'postcode_mismatch', retryAfterMs: 90_000,
    }));
    expect(await checkLinkPostcode('dhl', number, '99999')).toEqual({ outcome: 'refused', retryAfterMs: 90_000 });
  });

  it('says nothing about the postcode when the carrier could not be asked', async () => {
    tracked.mockRejectedValueOnce(new ChallengeError('DHL', 'DHL rejected the tracking session'));
    expect(await checkLinkPostcode('dhl', number, '99999')).toEqual({ outcome: 'unavailable' });
    tracked.mockRejectedValueOnce(new TypeError('fetch failed'));
    expect(await checkLinkPostcode('dhl', number, '99999')).toEqual({ outcome: 'unavailable' });
    tracked.mockRejectedValueOnce(new IndeterminateError('DHL', 'DHL could not complete the tracking query'));
    expect(await checkLinkPostcode('dhl', number, '99999')).toEqual({ outcome: 'unavailable' });
    // A carrier tracked only through providers cannot accept a postcode itself.
    expect(await checkLinkPostcode('gls-ch', number, '9999')).toEqual({ outcome: 'unavailable' });
    expect(tracked).toHaveBeenCalledTimes(3);
  });

  it('passes on the caller\'s cancellation', async () => {
    const controller = new AbortController();
    tracked.mockImplementationOnce(async () => {
      controller.abort(new Error('owner left'));
      throw new ChallengeError('DHL', 'cancelled');
    });
    await expect(checkLinkPostcode('dhl', number, '99999', null, controller.signal)).rejects.toThrow('owner left');
  });
});

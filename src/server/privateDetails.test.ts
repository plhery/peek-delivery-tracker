import { describe, expect, it } from 'vitest';
import { privateValues, VIEWER_DELIVERY_DESCRIPTION, viewerScan, viewerStatusText } from './privateDetails';

describe('a scan as a viewer reads it', () => {
  const scan = (stage: string, description: string, location: string | null = 'Example Town') => ({ stage, description, location, place: { name: 'x' } });
  const values = privateValues({ signed_by: 'A. Example', receiver_name: 'Ann Example', receiver_address: '12 Example Street', pickup_code: '48', other: 'Sorted' });

  it('takes the recipient details of the carrier data, three characters or more', () => {
    expect(values).toEqual(['a. example', 'ann example', '12 example street']);
    expect(privateValues(null)).toEqual([]);
  });

  it('reads every delivery as a delivery, and leaves out any other scan that names the recipient or a code', () => {
    expect(viewerScan(scan('delivered', 'DELIVERED TO RECEPTION'))).toEqual(scan('delivered', VIEWER_DELIVERY_DESCRIPTION));
    const delivered = scan('delivered', VIEWER_DELIVERY_DESCRIPTION);
    expect(viewerScan(delivered)).toBe(delivered);
    expect(viewerScan(scan('out_for_delivery', 'Out for delivery, PIN: 4711'))).toBeNull();
    expect(viewerScan(scan('available_for_pickup', 'Ready, Abholcode 123456'))).toBeNull();
    expect(viewerScan(scan('failed_attempt', 'ANN EXAMPLE not at home'), values)).toBeNull();
    const sorted = scan('in_transit', 'Sorted');
    expect(viewerScan(sorted, values)).toBe(sorted);
  });

  it('leaves out a place that names a door or the recipient\'s address, with its point', () => {
    expect(viewerScan(scan('in_transit', 'Sorted', 'House number 4, Example Street')))
      .toEqual({ stage: 'in_transit', description: 'Sorted', location: null, place: null });
    expect(viewerScan(scan('delivered', 'Delivered', '12 Example Street, Example Town'), values))
      .toEqual({ stage: 'delivered', description: 'Delivered', location: null, place: null });
    expect(viewerScan({ stage: 'in_transit', description: 'Sorted', location: 'Door no 3' })).toEqual({ stage: 'in_transit', description: 'Sorted', location: null });
  });

  it('reads no status line for a delivered parcel, nor one that names the recipient', () => {
    expect(viewerStatusText('Delivered to reception', 'delivered')).toBeNull();
    expect(viewerStatusText('Out for delivery to Ann Example', 'out_for_delivery', values)).toBeNull();
    expect(viewerStatusText('In transit', 'in_transit', values)).toBe('In transit');
    expect(viewerStatusText(null, 'in_transit')).toBeNull();
  });
});

import { describe, expect, it } from 'vitest';
import { PRIVATE_DELIVERY_DESCRIPTION, viewerScan, viewerStatusText } from './privateDetails';

describe('a scan as a viewer reads it', () => {
  const scan = (stage: string, description: string, location: string | null = 'Example Town') => ({ stage, description, location, place: { name: 'x' } });

  it('reads a delivery that names its signatory or a code as a delivery, and leaves out any other scan that does', () => {
    expect(viewerScan(scan('delivered', 'Delivered, signed by A. Example'))).toEqual({ ...scan('delivered', PRIVATE_DELIVERY_DESCRIPTION) });
    expect(viewerScan(scan('out_for_delivery', 'Out for delivery, PIN: 4711'))).toBeNull();
    expect(viewerScan(scan('available_for_pickup', 'Ready, Abholcode 123456'))).toBeNull();
    const sorted = scan('in_transit', 'Sorted');
    expect(viewerScan(sorted)).toBe(sorted);
  });

  it('leaves out a place that names a door, with its point', () => {
    expect(viewerScan(scan('delivered', 'Delivered', 'House number 4, Example Street')))
      .toEqual({ stage: 'delivered', description: 'Delivered', location: null, place: null });
    expect(viewerScan({ stage: 'in_transit', description: 'Sorted', location: 'Door no 3' })).toEqual({ stage: 'in_transit', description: 'Sorted', location: null });
  });

  it('reads no status line that names one', () => {
    expect(viewerStatusText('Delivered, signé par A. Example')).toBeNull();
    expect(viewerStatusText('In transit')).toBe('In transit');
    expect(viewerStatusText(null)).toBeNull();
  });
});

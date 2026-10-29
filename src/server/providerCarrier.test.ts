import { expect, it } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import { namesBrandOf, providerCarrier } from './providerCarrier';

const history: CarrierResult = { tracking_provider: 'Ship24', discovered_carrier: 'dhl-express',
  reported_carriers: ['DHL Express'], events: [{ time: '2026-09-10T11:00:00Z', stage: 'delivered', description: 'Delivered' }] };

it('uses a single named carrier with dated movement', () => {
  expect(providerCarrier(history, '1234567891')).toBe('dhl-express');
});

it.each<Partial<CarrierResult>>([
  { reported_carriers: ['DHL'] },
  { reported_carriers: ['DHL Express', 'Swiss Post'] },
  { reported_carriers: ['Unknown Carrier'] },
  { discovered_carrier: 'dhl' },
  { reported_carriers: undefined },
  { tracking_provider: 'UPU' },
  { events: [] },
  { events: [{ stage: 'registered', time: '2026-09-10T11:00:00Z', description: 'Label created' }] },
  { events: [{ stage: 'delivered', time: '2026-09-10T11:00:00', description: 'Delivered' }] },
  { events: [{ stage: 'delivered', time: '9999-09-10T11:00:00Z', description: 'Delivered' }] },
])('keeps ambiguous names and undated or registration-only answers unresolved: %j', (change) => {
  expect(providerCarrier({ ...history, ...change }, '1234567891')).toBeUndefined();
});

it('uses the journey owner of several named carriers when the number points to it alone', () => {
  const journey = { ...history, tracking_provider: 'ParcelsApp', discovered_carrier: 'mayiyinfu',
    reported_carriers: ['DHLink', 'Mayiyinfu', 'Deutsche Post - DHL Paket', 'DHL Parcel Spain'] };
  expect(providerCarrier(journey, 'MY1234567890US')).toBe('mayiyinfu');
  // The provider settled on another leg, or the number fits several carriers' rules.
  expect(providerCarrier({ ...journey, discovered_carrier: 'dhlink' }, 'MY1234567890US')).toBeUndefined();
  expect(providerCarrier({ ...journey, discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express', 'Swiss Post'] }, '1234567891')).toBeUndefined();
  expect(providerCarrier({ ...journey, discovered_carrier: undefined }, 'MY1234567890US')).toBeUndefined();
  // More than four names is no journey the database files a parcel from.
  expect(providerCarrier({ ...journey, reported_carriers: [...journey.reported_carriers, 'Cainiao'] }, 'MY1234567890US')).toBeUndefined();
});

it('keeps a carrier requiring recipient input unresolved', () => {
  expect(providerCarrier({ ...history, discovered_carrier: 'gls-ch', reported_carriers: ['GLS Switzerland'] }, '12345678')).toBeUndefined();
});

it('names the network of a brand the caller settled on, only for the brand\'s name alone', () => {
  const gls = { ...history, discovered_carrier: 'gls-at', reported_carriers: ['GLS'] };
  expect(namesBrandOf(gls, 'gls-at')).toBe(true);
  expect(providerCarrier(gls, '12345678901', 'gls-at')).toBe('gls-at');
  // Without that network, a network outside the brand, or with another name beside it, the name settles nothing.
  expect(providerCarrier(gls, '12345678901')).toBeUndefined();
  expect(namesBrandOf(gls, 'dpd')).toBe(false);
  expect(providerCarrier({ ...gls, discovered_carrier: 'dpd' }, '12345678901', 'dpd')).toBeUndefined();
  expect(namesBrandOf({ ...gls, reported_carriers: ['GLS', 'DPD'] }, 'gls-at')).toBe(false);
  // A name the catalog places itself is not left to the caller.
  expect(namesBrandOf({ ...gls, reported_carriers: ['GLS Switzerland'] }, 'gls-ch')).toBe(false);
  // Nor is a network whose lookup requires the postcode taken.
  expect(providerCarrier({ ...gls, discovered_carrier: 'gls-de' }, '12345678901', 'gls-de')).toBeUndefined();
});
